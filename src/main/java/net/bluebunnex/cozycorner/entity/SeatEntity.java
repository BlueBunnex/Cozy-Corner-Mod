package net.bluebunnex.cozycorner.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class SeatEntity extends Entity {

    private final int forwardYaw;

    public SeatEntity(World world, double x, double y, double z, int facing) {
        super(world);

        this.setPosition(x, y, z);
        this.setBoundingBoxSpacing(0.0F, 0.0F);

        this.forwardYaw = ((facing + 2) % 4) * 90;
    }

    public SeatEntity(World world, double x, double y, double z) {
        this(world, x, y, z, -1);
    }

    @Override
    protected void initDataTracker() {}

    @Override
    public void tick() {
        super.tick();

        if (this.passenger == null || this.passenger.dead) {

            this.markDead();
        }

        if (this.passenger instanceof PlayerEntity player) {

            if (player.isSneaking()) {
                player.setVehicle(null);
                this.markDead();
            }
        }
    }

    @Override
    public void onPlayerInteraction(PlayerEntity player) {

        if (this.passenger == null) {
            player.setVehicle(this);
        }
    }

    @Override
    public Box getBoundingBox() {
        return Box.create(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean shouldRender(double distance) {
        return false;
    }

    @Override
    protected void readNbt(NbtCompound nbt) {}

    @Override
    protected void writeNbt(NbtCompound nbt) {}

    @Override
    public void updatePassengerPosition() {

        if (this.passenger != null) {

            this.passenger.setPosition(this.x, this.y + 1, this.z);

            // lock player direction within 75deg of forward
            if (forwardYaw >= 0 && this.passenger instanceof PlayerEntity) {

                ((PlayerEntity) this.passenger).bodyYaw = forwardYaw;

                if (Math.abs(this.passenger.yaw - forwardYaw) > 75) {

                    this.passenger.yaw = forwardYaw + Math.copySign(75, this.passenger.yaw - forwardYaw);
                }
            }
        }
    }
}