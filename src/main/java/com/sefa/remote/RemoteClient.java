package com.sefa.remote;

import com.sefa.events.IncomingEvent;
import com.sefa.events.RandomsGenerated;

public interface RemoteClient {
    void sendGenerateds(RandomsGenerated generateds);

    void sendIncomingEvent(IncomingEvent event);

}
