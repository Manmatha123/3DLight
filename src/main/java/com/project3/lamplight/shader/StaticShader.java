package com.project3.lamplight.shader;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.project3.lamplight.light.Light;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "vertexShader.txt";
    private static final String FRAGMENT_FILE = "fragmentShader.txt";

    private int location_lightPosition;
    private int location_lightColor;
    private int location_cameraPosition;
    private int location_modelMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
private int location_emissive;



public void loadEmissive(boolean isEmissive) {
    loadFloat(location_emissive, isEmissive ? 1.0f : 0.0f);
}
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
        bindAttributes();
        getAllUniformLocations();
    }

    public void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "texCoord");
        super.bindAttribute(2, "normal");
    }

    public void getAllUniformLocations() {
         location_emissive = getUniformLocation("emissive");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColor    = super.getUniformLocation("lightColor");
        location_cameraPosition = super.getUniformLocation("cameraPosition");
        location_modelMatrix    = super.getUniformLocation("modelMatrix");
        location_viewMatrix     = super.getUniformLocation("viewMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    public void loadLight(Light light) {
        super.loadVector(location_lightPosition, light.getPosition());
        super.loadVector(location_lightColor, light.getColor());
    }

    public void loadCamera(Vector3f cameraPos) {
        super.loadVector(location_cameraPosition, cameraPos);
    }

    public void loadModelMatrix(Matrix4f matrix) {
        super.loadMatrix(location_modelMatrix, matrix);
    }

    public void loadViewMatrix(Matrix4f matrix) {
        super.loadMatrix(location_viewMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }
}
