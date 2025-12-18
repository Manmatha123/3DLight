package com.project3.lamplight.model;

public class Entity {

    private RawModel model;
    private float x, y, z;
    private float rx, ry, rz;
    private float scale;
    private boolean emissive = false;

    public boolean isEmissive() {
        return emissive;
    }

    public void setEmissive(boolean emissive) {
        this.emissive = emissive;
    }

    public Entity(RawModel model, float x, float y, float z, float scale) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.z = z;
        this.scale = scale;
    }

    public RawModel getModel() {
        return model;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getScale() {
        return scale;
    }
}
