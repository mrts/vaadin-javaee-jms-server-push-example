package com.serverpushexample.messaging;

import com.serverpushexample.ReceiveMessageUI;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Broadcaster {

    private static final List<Consumer<String>> listeners = new LinkedList<>();
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static synchronized void register(Consumer<String> listener) {
        listeners.add(listener);
    }

    public static synchronized void unregister(Consumer<String> listener) {
        listeners.remove(listener);
    }

    public static synchronized void broadcast(final String message) {
        for (final Consumer<String> listener : listeners) {
            executorService.execute(() -> listener.accept(message));
        }
    }

}
