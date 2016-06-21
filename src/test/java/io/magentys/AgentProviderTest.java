package io.magentys;

import static io.magentys.AgentProvider.provideAgent;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import io.magentys.utils.Any;

/**
 * Created by kostasmamalis on 19/05/16.
 */
public class AgentProviderTest {

    StringMemory stringMemory = StringMemory.stringMemory();

    @Test
    public void shouldProvideAPlainVanillaAgent() throws Exception {
        final Agent<StringMemory, String> ourAgent = provideAgent().get();
        assertThat(ourAgent.getMemory(), instanceOf(CoreMemory.class));
        assertThat(ourAgent.getMemory().isEmpty(), is(true));
        assertThat(ourAgent.getTools().isEmpty(), is(true));
    }

    @Test
    public void shouldCreateAnAgentWithMemory() throws Exception {
        final Agent<StringMemory, String> ourAgent = provideAgent().withMemory(stringMemory).get();
        assertThat(ourAgent.getMemory(), notNullValue());
        assertThat(ourAgent.getMemory().isEmpty(), is(true));
    }

    @Test
    public void shouldCreateAnAgentWithTools() throws Exception {
        final Agent<StringMemory, String> ourAgent = provideAgent().withTools(new SwissKnife(), new Jack()).get();
        assertThat(ourAgent.getTools().isEmpty(), is(false));
        assertThat(ourAgent.usingThe(SwissKnife.class), instanceOf(SwissKnife.class));
        assertThat(ourAgent.usingThe(Jack.class), instanceOf(Jack.class));
    }

    @Test
    // cannot switch types of memory??
    public void shouldOverrideMemoryAndTools() throws Exception {
        final AgentProvider<StringMemory, String> agentProvider = provideAgent();
        final Agent<StringMemory, String> ourAgent = agentProvider
            .withMemory(new StringMemory())
            .withTools(new Jack())
            .withMemory(stringMemory).get();
        assertThat(ourAgent.getMemory(), instanceOf(StringMemory.class));
        assertThat(ourAgent.getTools().size(), is(1));

    }

    /*
    private class DummyMemory implements Memory<Double> {

        @Override
        public <VALUE> void remember(Double aDouble, VALUE value) {

        }

        @Override
        public void remember(Double aDouble, Any any) {

        }

        @Override
        public <VALUE> VALUE recall(Double aDouble, Class<VALUE> clazz) {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void transferTo(Memory<Double> memory, Double aDouble) {

        }

        @Override
        public Any recall(Double aDouble) {
            return null;
        }
    }
    */

    private class SwissKnife {
    }

    private class Jack {
    }
}
