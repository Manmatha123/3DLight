package com.project3.lamplight.loder;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

import com.project3.lamplight.model.RawModel;

import static org.lwjgl.opengl.GL15.*;

public class Loader {

    private List<Integer> vbos = new ArrayList<>();

    public RawModel loadToVBO(float[] vertices) {
        int vboID = glGenBuffers();
        vbos.add(vboID);

        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(vertices);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        return new RawModel(vboID, vertices.length / 3);
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public void cleanUp() {
        for (int vbo : vbos) {
            glDeleteBuffers(vbo);
        }
    }
}