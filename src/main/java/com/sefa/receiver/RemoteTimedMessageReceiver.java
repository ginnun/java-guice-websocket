package com.sefa.receiver;

import com.sefa.events.RandomsGenerated;
import com.sefa.remote.RemoteClient;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RemoteTimedMessageReceiver implements TimedMessageReceiver {
    private static final Logger log = Logger.getLogger(RemoteTimedMessageReceiver.class);
    @Inject
    RemoteClient client;

    @Inject
    public RemoteTimedMessageReceiver() {
    }

    @Override
    public void receiveTimedMessage(RandomsGenerated generateds) {
        log.info("sending randoms to remote");
        client.sendGenerateds(generateds);
    }

}
