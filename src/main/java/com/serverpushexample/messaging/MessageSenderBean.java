package com.serverpushexample.messaging;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;

/**
 * Definition of the JMS queue to have it automatically created
 */
// @formatter:off
@JMSDestinationDefinitions(value = {
        @JMSDestinationDefinition(name = "java:/" + MessageSenderBean.JMS_DESTINATION,
            interfaceName = "javax.jms.Queue",
            destinationName = MessageSenderBean.QUEUE_NAME)
})
// @formatter:on

@Stateless
public class MessageSenderBean {

    static final String QUEUE_NAME = "ServerPushExampleQueue";
    static final String JMS_DESTINATION = "queue/" + QUEUE_NAME;

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageReceiverBean.class);

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:/" + JMS_DESTINATION)
    private Queue queue;

    public void send(String message) {
        LOGGER.info("Sending message '" + message + "' to queue " + queue);
        jmsContext.createProducer().send(queue, message);
    }

}
