package com.sefa.timer;

import com.sefa.events.IncomingEvent;
import com.sefa.receiver.EventReceiver;
import org.apache.log4j.Logger;

import javax.inject.Singleton;

@Singleton
public class RemoteRepeatedGenerator implements RepeatedGenerator, EventReceiver {
    private static final Logger log = Logger.getLogger(RemoteRepeatedGenerator.class);

    @Override
    public void changeGenerateRate(int value) {
        log.info("changing remote random generation rate");
        // @todo send to remote, listen from remote
    }

    @Override
    public void changeTimeToShow(int value) {
        // @todo send to remote, listen from remote
    }

    @Override
    public void receiveIncomingEvent(IncomingEvent msg) {
        if (msg.type != null && msg.type.equals("gr")) {
            changeGenerateRate(msg.value);
        } else if (msg.type != null && msg.type.equals("tts")) {
            changeTimeToShow(msg.value);
        }
    }
}
