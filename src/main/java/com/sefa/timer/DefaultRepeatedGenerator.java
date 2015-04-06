package com.sefa.timer;

import com.sefa.events.IncomingEvent;
import com.sefa.random.RandomLongProvider;
import com.sefa.random.RandomStringProvider;
import com.sefa.receiver.EventReceiver;
import com.sefa.receiver.TimedMessageReceiver;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
public class DefaultRepeatedGenerator implements RepeatedGenerator, EventReceiver {
    private static final Logger log = Logger.getLogger(DefaultRepeatedGenerator.class);

    private TimedMessageReceiver timedReceiver;
    private RandomLongProvider longProvider;
    private RandomStringProvider stringProvider;
    private Timer timer = new Timer("DefaultRepeatedGenerator");
    private RandomTimerTask task;
    private int rate = 5000;
    private int timeToShow = 5;

    @Inject
    public DefaultRepeatedGenerator(TimedMessageReceiver timedReceiver, RandomLongProvider longProvider, RandomStringProvider stringProvider) {
        this.timedReceiver = timedReceiver;
        this.longProvider = longProvider;
        this.stringProvider = stringProvider;
        schedule();
    }

    @Override
    public void changeGenerateRate(int value) {
        rate = value * 1000;
        schedule();
    }

    @Override
    public void changeTimeToShow(int value) {
        this.timeToShow = value;
        task.setTimeToShow(timeToShow);
    }

    private void schedule() {
        cancelTask(task);
        task = new RandomTimerTask(longProvider, stringProvider, timedReceiver);
        task.setTimeToShow(timeToShow);
        timer.schedule(task, rate, rate);
    }

    private void cancelTask(TimerTask t) {
        if (t != null) {
            t.cancel();
        }
    }

    @Override
    public void receiveIncomingEvent(IncomingEvent msg) {
        log.info("received event");
        if (msg.type != null && msg.type.equals("gr")) {
            changeGenerateRate(msg.value);
        } else if (msg.type != null && msg.type.equals("tts")) {
            changeTimeToShow(msg.value);
        }
    }
}
