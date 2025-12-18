package com.project3.lamplight.light;


import org.lwjgl.util.vector.Vector3f;


public class Light {
private Vector3f position;
private Vector3f color;
    private Vector3f attenuation = new Vector3f(1, 0.09f, 0.032f);


public Light(Vector3f position, Vector3f color) {
this.position = position;
this.color = color;
}

 public Vector3f getAttenuation() { return attenuation; }
public Vector3f getPosition() { return position; }
public Vector3f getColor() { return color; }
}