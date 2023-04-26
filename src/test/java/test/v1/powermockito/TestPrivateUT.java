package test.v1.powermockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TestPrivate.class)
public class TestPrivateUT {
    @InjectMocks
    private TestPrivate testPrivate;

    @Test
    public void dd() throws Exception {
        try {
//            TestPrivate mock = PowerMockito.spy(testPrivate);
            TestPrivate mock = PowerMockito.spy(new TestPrivate());

            PowerMockito.when(mock, PowerMockito.method(TestPrivate.class, "hello",
                    String.class, int.class))
                    .withArguments(Mockito.anyString(), Mockito.anyInt())
                    .thenReturn("fuck you");

            String text = mock.test();
            Assert.assertEquals("ok", text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
