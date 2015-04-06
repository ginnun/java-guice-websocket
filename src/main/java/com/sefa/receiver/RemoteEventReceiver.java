package com.sefa.receiver;

import com.sefa.events.IncomingEvent;
import com.sefa.remote.RemoteClient;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RemoteEventReceiver implements EventReceiver {
    private static final Logger log = Logger.getLogger(RemoteEventReceiver.class);

    @Inject
    RemoteClient client;

    @Override
    public void receiveIncomingEvent(IncomingEvent event) {
        log.info("sending incoming event to remote");
        client.sendIncomingEvent(event);
    }
}
