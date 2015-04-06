package com.sefa.random;

import java.security.SecureRandom;


public class BasicRandomLongProvider implements RandomLongProvider {
    private SecureRandom random = new SecureRandom();

    @Override
    public long nextRandomLong() {
        return random.nextLong();
    }
}
