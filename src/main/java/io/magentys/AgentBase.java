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

public class AgentBase<AGENT extends AgentBase<AGENT>> {

    protected List<Any> tools = new ArrayList<Any>();

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

    public AgentBase() {
    }

    public <RESULT> RESULT performs(final Mission<RESULT, AGENT> mission) {
        narrateBefore(mission);
        RESULT result = mission.accomplishAs((AGENT) this);
        narrateAfter(mission);
        return result;
    }

    protected <RESULT> void narrateBefore(Mission<RESULT, AGENT> mission) {
        if (mission.getClass().isAnnotationPresent(Narrate.class)) {
            Narrate narrate = mission.getClass().getAnnotation(Narrate.class);
            narrateThat(narrate.value());
        }
    }

    protected <RESULT> void narrateAfter(Mission<RESULT, AGENT> mission) {
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

    public AGENT performAll(final Mission... missions) {
        requires(missions != null && missions.length > 0, "No Missions were passed");
        for (final Mission mission : missions) {
            performs(mission);
        }
        return (AGENT) this;
    }

    public AGENT obtains(final Object... tools) {
        requiresNotNull(tools, "tools were empty");
        for (final Object tool : tools) {
            this.tools.add(any(tool));
        }
        return (AGENT) this;
    }

    public AGENT reportsUsing(final Narrator... narrators) {
        requiresNotNull(narrators, "narrators were null");
        for (final Narrator narrator : narrators) {
            this.narrators.add(narrator);
        }
        return (AGENT) this;
    }

    public AGENT setTools(List<Any> tools) {
        this.tools = tools;
        return (AGENT) this;
    }

    public AGENT setNarrators(Set<Narrator> narrators) {
        this.narrators = narrators;
        return (AGENT) this;
    }

    public <TOOL> TOOL usingThe(final Class<TOOL> toolClass) {

        for (final Any tool : tools) {
            if (Clazz.isClassOrSubclass(toolClass, tool.get().getClass())) {
                return (TOOL) tool.get();
            }
        }

        throw new NotAvailableException("I don't know this skill: " + toolClass);
    }

    public AGENT and(final Mission mission) {
        performAll(mission);
        return (AGENT) this;
    }

    public AGENT andHe(final Mission... missions) {
        return performAll(missions);
    }

    public AGENT andShe(final Mission... missions) {
        return performAll(missions);
    }

    public List<Any> getTools() {
        return tools;
    }

    public AGENT addNarrators(Narrator... narrators) {
        requiresNotNull(narrators, "Narrators passed were null");
        for (Narrator narrator : narrators) {
            requiresNotNull(narrator, "narrator was null");
            this.narrators.add(narrator);
        }
        return (AGENT) this;
    }

}
