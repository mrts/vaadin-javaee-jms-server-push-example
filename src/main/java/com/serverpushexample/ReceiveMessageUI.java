package com.serverpushexample;

import com.serverpushexample.messaging.Broadcaster;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.function.Consumer;

@Push
@CDIUI("receive-message")
@Theme("serverpushexampletheme")
public class ReceiveMessageUI extends UI implements Consumer<String> {

    private final VerticalLayout messages = new VerticalLayout();

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        messages.addComponent(new Label("<h2>Received messages</h2>", ContentMode.HTML));
        setContent(messages);
        Broadcaster.register(this);
    }

    @Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }

    @Override
    public void accept(final String message) {
        access(() -> messages.addComponent(new Label(message)));
    }

}
