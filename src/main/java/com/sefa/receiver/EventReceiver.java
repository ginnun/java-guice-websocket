package com.sefa.receiver;

import com.sefa.events.IncomingEvent;

public interface EventReceiver {
    public void receiveIncomingEvent(IncomingEvent msg);
}
