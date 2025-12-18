package com.project3.lamplight.model;

import static org.lwjgl.opengl.GL11.*;

import javax.swing.text.View;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.project3.Render.Shader.Shader;

public class Camera {

    private Vector3f position = new Vector3f(0, 1.5f, 00);
    // private float x = 0;
    // private float y = 1.5f;
    // private float z = 0;
   private float roll = 0; 
    private float yaw = 0;   // left / right
    private float pitch = 0; // up / down

    private float speed = 0.1f;

    public float getZ(){
        return this.position.z;
    }

    public void move() {

        // -------- FORWARD / BACKWARD --------
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.position.z -= speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
             this.position.z += speed;
        }

        // -------- LEFT / RIGHT --------
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
             this.position.x -= speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
             this.position.x += speed;
        }

        // -------- UP / DOWN --------
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
             this.position.y += speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
             this.position.y -= speed;
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
        glTranslatef(- this.position.x, - this.position.y, - this.position.z);
    }
 public Matrix4f getViewMatrix() {

        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();

        // Rotate first
        Matrix4f.rotate((float)Math.toRadians(pitch),
                new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float)Math.toRadians(yaw),
                new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float)Math.toRadians(roll),
                new Vector3f(0, 0, 1), viewMatrix, viewMatrix);

        // Then translate
        Vector3f negativePosition = new Vector3f(
                -position.x,
                -position.y,
                -position.z
        );

        Matrix4f.translate(negativePosition, viewMatrix, viewMatrix);

        return viewMatrix;
    }

    public Vector3f getPosition() { return position; }
}
