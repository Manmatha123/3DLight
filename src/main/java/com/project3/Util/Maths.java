package com.project3.Util;


import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.project3.Game.World.Position.Pos3D;

public class Maths {

    public static Matrix4f createTransformationMatrix(Pos3D pos) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(new Vector3f(pos.getX(), pos.getY(), pos.getZ()), matrix, matrix);
        Matrix4f.rotate((float)java.lang.Math.toRadians(pos.getRot().getPitch()), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float)java.lang.Math.toRadians(pos.getRot().getYaw()), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float)java.lang.Math.toRadians(pos.getRot().getRoll()), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(pos.getScale(), pos.getScale(), pos.getScale()), matrix, matrix);
        return matrix;
    }

    public static Matrix4f createViewMatrix(Pos3D pos) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.rotate((float)java.lang.Math.toRadians(pos.getRot().getPitch()), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float)java.lang.Math.toRadians(pos.getRot().getYaw()), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float)java.lang.Math.toRadians(pos.getRot().getRoll()), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.translate(new Vector3f(-pos.getX(), -pos.getY(), -pos.getZ()), matrix, matrix);
        return matrix;
    }
}
