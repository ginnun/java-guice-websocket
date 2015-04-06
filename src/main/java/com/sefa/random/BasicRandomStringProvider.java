package com.sefa.random;

import java.math.BigInteger;
import java.security.SecureRandom;


public class BasicRandomStringProvider implements RandomStringProvider {
    private SecureRandom random = new SecureRandom();

    @Override
    public String nextRandomString() {
        return new BigInteger(64, random).toString(32);
    }
}
