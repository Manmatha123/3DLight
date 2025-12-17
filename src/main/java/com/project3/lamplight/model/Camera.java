package com.project3.lamplight.model;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;

public class Camera {

    private float x = 0;
    private float y = 1.5f;
    private float z = 0;

    private float yaw = 0;   // left / right
    private float pitch = 0; // up / down

    private float speed = 0.1f;

    public float getZ(){
        return this.z;
    }

    public void move() {

        // -------- FORWARD / BACKWARD --------
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            z -= speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            z += speed;
        }

        // -------- LEFT / RIGHT --------
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            x -= speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            x += speed;
        }

        // -------- UP / DOWN --------
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            y += speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            y -= speed;
        }

        // -------- LOOK LEFT / RIGHT --------
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            yaw -= 1f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            yaw += 1f;
        }

        // -------- LOOK UP / DOWN --------
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            pitch -= 1f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            pitch += 1f;
        }
    }

    public void applyView() {
        glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw,   0, 1, 0);
        glTranslatef(-x, -y, -z);
    }
}
