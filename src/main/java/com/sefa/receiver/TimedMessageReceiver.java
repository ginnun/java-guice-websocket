package com.sefa.receiver;

import com.sefa.events.RandomsGenerated;

public interface TimedMessageReceiver {
    public void receiveTimedMessage(RandomsGenerated generateds);
}