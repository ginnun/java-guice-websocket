package com.sefa.timer;

import com.sefa.receiver.TimedMessageReceiver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TimedMessager {

    private static Set<TimedMessageReceiver> listeners =
            Collections.synchronizedSet(new HashSet<TimedMessageReceiver>());

    public void registerMe(TimedMessageReceiver receiver) {
        listeners.add(receiver);
    }
}