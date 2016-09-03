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

public class Agent<KEY> {

    protected Memory<KEY> memory;
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

    public Agent(final Memory<KEY> memory) {
        this.memory = memory;
    }

    public void setMemory(final Memory<KEY> mem) {
        this.memory = mem;
    }

    public <RESULT> RESULT performs(final Mission<RESULT, KEY> mission) {
        narrateBefore(mission);
        RESULT result = mission.accomplishAs(this);
        narrateAfter(mission);
        return result;
    }

    protected <RESULT> void narrateBefore(Mission<RESULT, ?> mission) {
        if (mission.getClass().isAnnotationPresent(Narrate.class)) {
            Narrate narrate = mission.getClass().getAnnotation(Narrate.class);
            narrateThat(narrate.value());
        }
    }

    protected <RESULT> void narrateAfter(Mission<RESULT, ?> mission) {
        if (mission.getClass().isAnnotationPresent(Narrate.class)) {
            Narrate narrate = mission.getClass().getAnnotation(Narrate.class);
            if (!Strings.empty.equals(narrate.after()) && narrate.after() != null)
                narrateThat(narrate.after());
        }
    }

    public void narrateThat(String message) {
        for (Narrator narrator : narrators) {
            narrator.narrate(name, "info", message);
        }
    }

    public void narrateThat(String level, String message) {
        for (Narrator narrator : narrators) {
            narrator.narrate(name, "info", message);
        }
    }

    public Agent<KEY> performAll(final Mission<?, KEY>... missions) {
        requires(missions != null && missions.length > 0, "No Missions were passed");
        for (final Mission<?, KEY> mission : missions) {
            performs(mission);
        }
        return this;
    }

    public Agent<KEY> obtains(final Object... tools) {
        requiresNotNull(tools, "tools were empty");
        for (final Object tool : tools) {
            this.tools.add(any(tool));
        }
        return this;
    }

    public Agent<KEY> reportsUsing(final Narrator... narrators) {
        requiresNotNull(narrators, "narrators were null");
        for (final Narrator narrator : narrators) {
            this.narrators.add(narrator);
        }
        return this;
    }

    public Agent<KEY> setTools(List<Any<?>> tools) {
        this.tools = tools;
        return this;
    }

    public Agent<KEY> setNarrators(Set<Narrator> narrators) {
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

    public <VALUE> void keepsInMind(final KEY key, final VALUE value) {
        this.memory.remember(key, value);
    }

    public <VALUE> VALUE recalls(final KEY key, final Class<VALUE> clazz) {
        return memory.recall(key, clazz);
    }

    public Agent<KEY> and(final Mission<?, KEY> mission) {
        performAll(mission);
        return this;
    }

    public Agent<KEY> andHe(final Mission<?, KEY>... missions) {
        return performAll(missions);
    }

    public Agent<KEY> andShe(final Mission<?, KEY>... missions) {
        return performAll(missions);
    }

    public List<Any<?>> getTools() {
        return tools;
    }

    public Memory<KEY> getMemory() {
        return memory;
    }

    public Agent<KEY> addNarrators(Narrator... narrators) {
        requiresNotNull(narrators, "Narrators passed were null");
        for (Narrator narrator : narrators) {
            requiresNotNull(narrator, "narrator was null");
            this.narrators.add(narrator);
        }
        return this;
    }

    public Agent<KEY> clone() {
        return new Agent<KEY>(memory).setTools(tools).setNarrators(narrators);
    }

    public Agent<KEY> askThe(final Agent<KEY> anotherAgent, KEY key) {
        anotherAgent.getMemory().transferTo(memory, key);
        return this;
    }

}
