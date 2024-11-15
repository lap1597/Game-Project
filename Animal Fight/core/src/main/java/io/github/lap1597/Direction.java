package io.github.lap1597;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int xMultiplier;
    private final int yMultiplier;

    /**
     * Constructor for Direction enum.
     *
     * @param xMultiplier The factor to multiply for x-axis movement
     * @param yMultiplier The factor to multiply for y-axis movement
     */
    Direction(int xMultiplier, int yMultiplier) {
        this.xMultiplier = xMultiplier;
        this.yMultiplier = yMultiplier;
    }

    /**
     * Returns the multiplier for x-axis movement.
     *
     * @return x-axis multiplier
     */
    public int getXMultiplier() {
        return xMultiplier;
    }

    /**
     * Returns the multiplier for y-axis movement.
     *
     * @return y-axis multiplier
     */
    public int getYMultiplier() {
        return yMultiplier;
    }
}
