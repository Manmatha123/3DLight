package com.project3.Render.Renderer;

import com.project3.Game.GameObject.ModeledObject;
import com.project3.Render.Model.RawModel;
import com.project3.Render.Model.TexturedModel;
import com.project3.Render.Shader.StaticShader;
import com.project3.Render.Texture.ModelTexture;
import com.project3.Util.Maths;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

import java.util.List;
import java.util.Map;

public class ModelRenderer {
    private StaticShader shader;

    public ModelRenderer(StaticShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Map<TexturedModel, List<ModeledObject>> objects) {
        for(TexturedModel model : objects.keySet()) {
            prepareTexturedModel(model);
            List<ModeledObject> batch = objects.get(model);
            for(ModeledObject object : batch) {
                prepareInstance(object);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = model.getTexture();
        if(texture.hasTransparency()) {
            GameRenderer.disableCulling();
        }
        shader.loadFakeLightingVariable(texture.useFakeLighting());
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());
    }

    private void unbindTexturedModel() {
        GameRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(ModeledObject object) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(object.getPos());
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
