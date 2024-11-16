package io.github.lap1597;

public class Items {
    private int energy;
    private static final int MAX_ITEMS = 30;

    public Items(int initialEnergy) {

        this.energy = initialEnergy;
    }

    public int getEnergyCount() {
        return energy;
    }



    public void useEnergy() {
        if (energy > 0) {
            energy--;
        }
    }


    public void pickupEnergy() {
        energy = Math.min(energy, MAX_ITEMS);  // Ensure it doesn't exceed max
    }


}
