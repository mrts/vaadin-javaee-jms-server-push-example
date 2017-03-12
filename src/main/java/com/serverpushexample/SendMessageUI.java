package com.serverpushexample;

import com.serverpushexample.messaging.MessageSenderBean;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.ejb.EJB;

@CDIUI("send-message")
@Theme("serverpushexampletheme")
public class SendMessageUI extends UI {

    @EJB
    private MessageSenderBean sender;

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("<h2>Sending messages</h2>", ContentMode.HTML));

        final TextField messageField = new TextField();
        messageField.setCaption("Type the message to send here:");

        Button button = new Button("Send message");
        button.addClickListener(e -> {
            final String message = messageField.getValue();
            sender.send(message);
            layout.addComponent(new Label("Sent message '" + message + "'"));
        });

        layout.addComponents(messageField, button);

        setContent(layout);
    }

}
