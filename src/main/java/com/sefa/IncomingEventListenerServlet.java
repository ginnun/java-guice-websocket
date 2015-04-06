package com.sefa;

import com.google.gson.Gson;
import com.sefa.events.IncomingEvent;
import com.sefa.receiver.EventReceiver;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class IncomingEventListenerServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(IncomingEventListenerServlet.class);
    private Gson gson = new Gson();

    @Inject
    private EventReceiver receiver;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Got incoming event from remote");
        IncomingEvent event = gson.fromJson(req.getReader(), IncomingEvent.class);
        receiver.receiveIncomingEvent(event);
    }
}