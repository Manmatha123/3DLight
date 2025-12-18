package com.project3.lamplight.model;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Matrix4X4 {

    public static Matrix4f createModelMatrix(Entity entity) {
        Matrix4f modelMatrix = new Matrix4f();
        modelMatrix.setIdentity();

        // Translate
        Matrix4f.translate(new Vector3f(entity.getX(), entity.getY(), entity.getZ()), modelMatrix, modelMatrix);

        // Scale
        Matrix4f.scale(new Vector3f(entity.getScale(), entity.getScale(), entity.getScale()), modelMatrix, modelMatrix);

        // If you want rotation, add here:
        // Matrix4f.rotate(rotationAngle, new Vector3f(1,0,0), modelMatrix,
        // modelMatrix);

        return modelMatrix;
    }

}
