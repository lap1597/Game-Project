package io.github.lap1597;

public class Items {
    private int bulletCount;
    private int energyCount;
    private static final int MAX_ITEMS = 30;

    public Items(int initialBullets, int initialEnergy) {

        this.bulletCount = initialBullets;
        this.energyCount = initialEnergy;
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public int getEnergyCount() {
        return energyCount;
    }

    public void useBullet() {
        if (bulletCount > 0) {
            bulletCount--;
        }
    }

    public void useEnergy() {
        if (energyCount > 0) {
            energyCount--;
        }
    }
    public void unload(){
        bulletCount = 0;
        energyCount = 0;

    }

    public void pickupBullet(int amount) {
        bulletCount = Math.min(bulletCount + amount, MAX_ITEMS);  // Ensure it doesn't exceed max
    }

    public void pickupEnergy(int amount) {
        energyCount = Math.min(energyCount + amount, MAX_ITEMS);  // Ensure it doesn't exceed max
    }

    public boolean hasNoItems() {
        return bulletCount == 0 && energyCount == 0;
    }
}
