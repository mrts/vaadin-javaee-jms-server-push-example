package com.serverpushexample;

import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.ParallelTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunLocally(Browser.CHROME)
public class SendAndReceiveMessageTest extends ParallelTest {

    private static final String RECEIVE_URL = "http://localhost:8080/vaadin-javaee-server-push-example-1.0-SNAPSHOT/receive-message";
    private static final String SEND_URL = "http://localhost:8080/vaadin-javaee-server-push-example-1.0-SNAPSHOT/send-message";

    /**
     * Open the receive message page, wait until the message sent in testSendMessage() appears, check that it
     * is the expected message.
     *
     * @throws InterruptedException
     */
    @Test
    public void testReceiveMessage() throws InterruptedException {
        getDriver().get(RECEIVE_URL);
        final String beforeSendMessage = $(LabelElement.class).last().getText();
        assertEquals("Received messages", beforeSendMessage);

        // sleep to leave time for message processing
        Thread.sleep(4000);

        final String afterSendMessage = $(LabelElement.class).last().getText();
        assertEquals("Message from testSendMessage", afterSendMessage);
    }

    /**
     * Open the send message page, wait until the receive message page is open in testReceiveMessage(), send
     * the test message.
     *
     * @throws InterruptedException
     */
    @Test
    public void testSendMessage() throws InterruptedException {
        getDriver().get(SEND_URL);

        $(TextFieldElement.class).first().setValue("Message from testSendMessage");
        $(ButtonElement.class).first().click();

        final String text = $(LabelElement.class).last().getText();
        assertEquals("Sent message 'Message from testSendMessage'", text);
    }

}
