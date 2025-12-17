package com.project3.lamplight.gameEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.project3.lamplight.chunk.Chunk;
import com.project3.lamplight.chunk.World;
import com.project3.lamplight.loder.Loader;
import com.project3.lamplight.loder.OBJLoader;
import com.project3.lamplight.model.Camera;
import com.project3.lamplight.model.Entity;
import com.project3.lamplight.model.RawModel;
import com.project3.lamplight.renderer.Renderer;

public class GameEngine {

    public static void gameEngine() {

        // ===================== DISPLAY =====================
        try {
            Display.setDisplayMode(new DisplayMode(1280, 720));
            Display.setTitle("LWJGL 2.9.3 OBJ Loader");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // ===================== OPENGL SETUP =====================
        glEnable(GL_DEPTH_TEST);
        glClearColor(0f, 0f, 0f, 1f);

        // ---------- PROJECTION MATRIX (ONCE) ----------
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        float aspect = 1280f / 720f;
        gluPerspective(70f, aspect, 0.1f, 100f);
        glMatrixMode(GL_MODELVIEW);

        // ===================== LOAD MODEL =====================
        Loader loader = new Loader();
        RawModel dragonModel = OBJLoader.loadObjModel("dragon.obj", loader);
        RawModel treeModel = OBJLoader.loadObjModel("LAMP_BLEND.obj", loader);
        RawModel houseModel = OBJLoader.loadObjModel("tree.obj", loader);
        Renderer renderer = new Renderer();
        Entity tree1 = new Entity(treeModel, 5f, 0f, -20f, 1f);
        Entity tree2 = new Entity(treeModel, -5f, 0f, -25f, 1f);
        Entity house = new Entity(houseModel, 0f, 0f, -15f, 2f);
        Entity dragon = new Entity(dragonModel, -3f, 0f, -30f, 0.2f);
        // ===================== GAME LOOP =====================
        Camera camera = new Camera();
        World world = new World(treeModel, houseModel, dragonModel);

        while (!Display.isCloseRequested()) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // ---------- MODELVIEW MATRIX (EVERY FRAME) ----------
            glLoadIdentity();

            // Move camera back (dragon models are huge)
            glTranslatef(0f, -1.0f, -8.0f);

            // Scale model to fit screen
            glScalef(0.4f, 0.4f, 0.4f);
            // renderer.render(model);

            camera.move();
            world.update(camera.getZ());
            camera.applyView();

            for (Chunk chunk : world.getChunks()) {
                for (Entity entity : chunk.entities) {
                    renderer.render(entity);
                }
            }


            Display.update();
            Display.sync(60);
        }

        // ===================== CLEANUP =====================
        loader.cleanUp();
        Display.destroy();
    }
}
