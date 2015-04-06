package com.sefa.events;

public class RandomsGenerated extends AbstractEvent<RandomsGenerated> {
    private long randomLong;
    private String randomString;
    private int timeToShow;

    /**
     * Necessary
     */
    public RandomsGenerated() {

    }

    public RandomsGenerated(long randomLong, String randomString, int timeToShow) {
        this.randomLong = randomLong;
        this.randomString = randomString;
        this.timeToShow = timeToShow;
    }

    public long getRandomLong() {
        return randomLong;
    }

    public String getRandomString() {
        return randomString;
    }

    @Override
    String getType() {
        return "RandomsGenerated";
    }

    @Override
    Class<RandomsGenerated> getMyclass() {
        return RandomsGenerated.class;
    }

    public int getTimeToShow() {
        return timeToShow;
    }

    public void setTimeToShow(int timeToShow) {
        this.timeToShow = timeToShow;
    }
}
