package com.serverpushexample.messaging;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = MessageSenderBean.JMS_DESTINATION),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageReceiverBean implements MessageListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageReceiverBean.class);

    @Override
    public void onMessage(Message receivedMessage) {
        try {
            final String message = ((TextMessage) receivedMessage).getText();
            LOGGER.info("Message '" + message + "' received from JMS destination "
                    + receivedMessage.getJMSDestination());
            Broadcaster.broadcast(message);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
