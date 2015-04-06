package com.sefa;

import com.google.gson.Gson;
import com.sefa.events.RandomsGenerated;
import com.sefa.receiver.TimedMessageReceiver;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class RandomListenerServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RandomListenerServlet.class);
    private Gson gson = new Gson();

    @Inject
    private TimedMessageReceiver messageReceiver;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Got randoms from remote");
        RandomsGenerated rg = gson.fromJson(req.getReader(), RandomsGenerated.class);
        messageReceiver.receiveTimedMessage(rg);
    }
}