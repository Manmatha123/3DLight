package com.project3.lamplight.model;

public class RawModel {

    private int vboID;
    private int vertexCount;

    public RawModel(int vboID, int vertexCount) {
        this.vboID = vboID;
        this.vertexCount = vertexCount;
    }

    public int getVboID() {
        return vboID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}