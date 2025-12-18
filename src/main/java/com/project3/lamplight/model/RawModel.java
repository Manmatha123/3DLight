package com.project3.lamplight.model;

public class RawModel {

    private int vaoID;
    private int vertexCount;

    public RawModel(int vboID, int vertexCount) {
        this.vaoID = vboID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}