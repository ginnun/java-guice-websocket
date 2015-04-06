package com.sefa;

import com.sefa.events.IncomingEvent;
import com.sefa.events.RandomsGenerated;
import com.sefa.guice.GuiceWebsocketConfigurator;
import com.sefa.receiver.EventReceiver;
import com.sefa.receiver.TimedMessageReceiver;
import com.sefa.timer.RepeatedGenerator;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/ws/echo", encoders = {RandomsGenerated.class, IncomingEvent.class}, decoders = {IncomingEvent.class},
        configurator = GuiceWebsocketConfigurator.class)
public class RandomsWebsocketEndpoint implements TimedMessageReceiver {


    private static Set<Session> clients =
            Collections.synchronizedSet(new HashSet<Session>());

    private static final Logger log = Logger.getLogger(RandomsWebsocketEndpoint.class);
    private EventReceiver eventReceiver;

    @Inject
    public RandomsWebsocketEndpoint(RepeatedGenerator generator, EventReceiver eventReceiver) {
        this.eventReceiver = eventReceiver;

        log.info("Creating Instance of " + RandomsWebsocketEndpoint.class.getName());
        log.info("Instantiation EchoAnnotation for thread id " + Thread.currentThread().getId());

        generator.changeGenerateRate(5);
    }


    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        clients.add(session);
        log.info("session opened: " + session.getId());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        clients.remove(session);
        log.info("session closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        clients.remove(session);
        log.info("session error: " + session.getId() + throwable.getMessage());
    }

    @OnMessage
    public void receiveIncomingMessage(Session session, IncomingEvent msg) {
        eventReceiver.receiveIncomingEvent(msg);
    }

    private void sendMessage(Session session, RandomsGenerated generateds) {
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendObject(generateds);
            }
        } catch (IOException e) {
            try {
                session.close();
                clients.remove(session);
                log.error(e);
            } catch (IOException e1) {
                // Ignore
                clients.remove(session);
                log.error(e1);
            }
        } catch (EncodeException e) {
            log.error(e);
        }
    }

    @Override
    public void receiveTimedMessage(RandomsGenerated generateds) {
        log.info("sending randoms to all clients via websocket " + clients.size());
        for (Session session : clients) {
            sendMessage(session, generateds);
        }
    }
}
