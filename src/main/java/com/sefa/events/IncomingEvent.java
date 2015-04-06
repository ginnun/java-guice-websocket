package com.sefa.events;

public class IncomingEvent extends AbstractEvent<IncomingEvent> {
    public int value;

    @Override
    String getType() {
        return type;
    }

    @Override
    Class<IncomingEvent> getMyclass() {
        return IncomingEvent.class;
    }
}
