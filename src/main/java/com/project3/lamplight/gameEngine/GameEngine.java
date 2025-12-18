package com.project3.lamplight.gameEngine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.project3.lamplight.chunk.Chunk;
import com.project3.lamplight.chunk.World;
import com.project3.lamplight.light.Light;
import com.project3.lamplight.loder.Loader;
import com.project3.lamplight.loder.OBJLoader;
import com.project3.lamplight.model.Camera;
import com.project3.lamplight.model.Entity;
import com.project3.lamplight.model.Matrix4X4;
import com.project3.lamplight.model.RawModel;
import com.project3.lamplight.renderer.Renderer;
import com.project3.lamplight.shader.StaticShader;

public class GameEngine {

    public static void gameEngine() {

        // ===================== DISPLAY =====================
        try {
            Display.setDisplayMode(new DisplayMode(1280, 720));
            Display.setTitle("LWJGL 2.9.3 Lighting Engine");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        glEnable(GL_DEPTH_TEST);
        glClearColor(0f, 0f, 0f, 1f);

        // ===================== PROJECTION MATRIX =====================
        Matrix4f projectionMatrix = new Matrix4f();
        float aspect = 1280f / 720f;
        float fov = 70f;
        float near = 0.1f;
        float far = 100f;

        float yScale = (float) (1f / Math.tan(Math.toRadians(fov / 2f)));
        float xScale = yScale / aspect;
        float frustum = far - near;

        projectionMatrix.m00 = xScale;
        projectionMatrix.m11 = yScale;
        projectionMatrix.m22 = -((far + near) / frustum);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * near * far) / frustum);
        projectionMatrix.m33 = 0;

        // ===================== SHADER =====================
        StaticShader shader = new StaticShader();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();

        // ===================== LOAD MODELS =====================
        Loader loader = new Loader();
        RawModel lampModel = OBJLoader.loadObjModel("LAMP_BLEND.obj", loader);

        Renderer renderer = new Renderer();
        Camera camera = new Camera();
        Light lampLight = new Light(
                new Vector3f(5f, 2.3f, -10f),
                new Vector3f(1.0f, 0.95f, 0.8f));

        World world = new World(lampModel, lampModel, lampModel);

        // Visible lamp entity
        Entity lampEntity = new Entity(lampModel, 5, 2, -10, 1f);
        lampEntity.setEmissive(true);
        while (!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            camera.move();
            world.update(camera.getZ());

            shader.start();
            shader.loadViewMatrix(camera.getViewMatrix());
            shader.loadLight(lampLight);
            shader.loadCamera(camera.getPosition());

            for (Chunk chunk : world.getChunks()) {
                for (Entity entity : chunk.entities) {
                    shader.loadEmissive(entity.isEmissive());
                    shader.loadModelMatrix(Matrix4X4.createModelMatrix(entity));
                    renderer.render(entity);
                }
            }

            // Render lamp mesh
            shader.loadEmissive(true);
            shader.loadModelMatrix(Matrix4X4.createModelMatrix(lampEntity));
            renderer.render(lampEntity);

            shader.stop();

            Display.update();
            Display.sync(60);
        }

        loader.cleanUp();
        shader.cleanUp();
        Display.destroy();
    }
}
