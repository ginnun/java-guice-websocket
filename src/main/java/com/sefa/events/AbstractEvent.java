package com.sefa.events;

import com.google.gson.Gson;

import javax.websocket.*;


abstract class AbstractEvent<T> implements Encoder.Text<T>, Decoder.Text<T> {
    private static Gson gson = new Gson();
    public String type = getType();

    @Override
    public String encode(T t) throws EncodeException {
        return gson.toJson(t);
    }

    @Override
    public T decode(String s) throws DecodeException {
        return gson.fromJson(s, this.getMyclass());
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    abstract String getType();

    abstract Class<T> getMyclass();
}
