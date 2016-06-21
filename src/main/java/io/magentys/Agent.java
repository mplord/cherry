package io.magentys;

import static io.magentys.utils.Any.any;
import static io.magentys.utils.Requires.requires;
import static io.magentys.utils.Requires.requiresNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.magentys.annotations.Narrate;
import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;
import io.magentys.utils.Strings;
import io.magentys.utils.UniqueId;

public class Agent<M extends Memory<K>, K> {


    protected M memory;
    protected List<Any<?>> tools = new ArrayList<Any<?>>();

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name = UniqueId.incrementalId();


    public Set<Narrator> getNarrators() {
        return narrators;
    }

    protected Set<Narrator> narrators = new HashSet<Narrator>();

    public Agent(final M memory) {
        this.memory = memory;
    }

    public void setMemory(final M mem) {
        this.memory = mem;
    }

    public <RESULT> RESULT performs(final Mission<RESULT, M, K> mission) {
        narrateBefore(mission);
        RESULT result = mission.accomplishAs(this);
        narrateAfter(mission);
        return result;
    }

    protected <RESULT> void narrateBefore(Mission<RESULT, M, K> mission) {
        if(mission.getClass().isAnnotationPresent(Narrate.class)){
            Narrate narrate = mission.getClass().getAnnotation(Narrate.class);
            narrateThat(narrate.value());
        }
    }

    protected <RESULT> void narrateAfter(Mission<RESULT, M, K> mission) {
        if(mission.getClass().isAnnotationPresent(Narrate.class)){
            Narrate narrate = mission.getClass().getAnnotation(Narrate.class);
            if(!Strings.empty.equals(narrate.after()) && narrate.after() != null) narrateThat(narrate.after());
        }
    }

    public void narrateThat(String message){
        for(Narrator narrator : narrators) {
            narrator.narrate(name, "info", message);
        }
    }

    public void narrateThat(String level, String message){
        for(Narrator narrator : narrators){
            narrator.narrate(name, "info", message);
        }
    }

    public <R> Agent<M, K> performAll(final Mission<?, M, K>... missions) {
        requires(missions != null && missions.length > 0, "No Missions were passed");
        for (final Mission<?, M, K> mission : missions) {
            performs(mission);
        }
        return this;
    }

    public Agent<M, K> obtains(final Object... tools) {
        requiresNotNull(tools, "tools were empty");
        for (final Object tool : tools) {
            this.tools.add(any(tool));
        }
        return this;
    }

    public Agent<M, K> reportsUsing(final Narrator... narrators) {
        requiresNotNull(narrators, "narrators were null");
        for(final Narrator narrator : narrators){
            this.narrators.add(narrator);
        }
        return this;
    }

    public Agent<M, K> setTools(List<Any<?>> tools) {
        this.tools = tools;
        return this;
    }

    public Agent<M, K> setNarrators(Set<Narrator> narrators) {
        this.narrators = narrators;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <TOOL> TOOL usingThe(final Class<TOOL> toolClass) {

        for (final Any<?> tool : tools) {
            if (Clazz.isClassOrSubclass(toolClass, tool.get().getClass())) {
                return (TOOL) tool.get();
            }
        }

        throw new NotAvailableException("I don't know this skill: " + toolClass);
    }

    public <VALUE> void keepsInMind(final K key, final VALUE value) {
        this.memory.remember(key, value);
    }

    public <VALUE> VALUE recalls(final K key, final Class<VALUE> clazz) {
        return (VALUE) memory.recall(key, clazz);
    }

    @SuppressWarnings("unchecked")
    public Agent<M, K> and(final Mission<?, M, K> mission) {
        performAll(mission);
        return this;
    }

    public Agent<M, K> andHe(final Mission<?, M, K>... missions) {
        return performAll(missions);
    }

    public Agent<M, K> andShe(final Mission<?, M, K>... missions) {
        return performAll(missions);
    }


    public List<Any<?>> getTools() {
        return tools;
    }

    public Memory<K> getMemory() {
        return memory;
    }

    public Agent<M, K> addNarrators(Narrator... narrators) {
        requiresNotNull(narrators, "Narrators passed were null");
        for(Narrator narrator : narrators){
            requiresNotNull(narrator, "narrator was null");
            this.narrators.add(narrator);
        }
        return this;
    }

    public Agent<M, K> clone() {
        return new Agent<M, K>(memory).setTools(tools).setNarrators(narrators);
    }

    public <KEY> Agent<M, K> askThe(final Agent<M, KEY> anotherAgent, KEY theirKey, K myKey) {
        anotherAgent.getMemory().transferTo(theirKey, memory, myKey);
        return this;
    }


}
