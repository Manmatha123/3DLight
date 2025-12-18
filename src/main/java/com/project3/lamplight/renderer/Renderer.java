package com.project3.lamplight.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.project3.Render.Texture.ModelTexture;
import com.project3.Util.Maths;
import com.project3.lamplight.model.Entity;
import com.project3.lamplight.model.RawModel;

public class Renderer {

  public void render(Entity entity) {

    RawModel model = entity.getModel();

    // Bind VAO
    GL30.glBindVertexArray(model.getVaoID());

    // Enable attributes
    GL20.glEnableVertexAttribArray(0); // position
    GL20.glEnableVertexAttribArray(1); // texture
    GL20.glEnableVertexAttribArray(2); // normal

    // Apply transform
    glPushMatrix();
    glTranslatef(entity.getX(), entity.getY(), entity.getZ());
    glScalef(entity.getScale(), entity.getScale(), entity.getScale());

    // Draw
    glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());

    // Restore matrix
    glPopMatrix();

    // Disable attributes
    GL20.glDisableVertexAttribArray(0);
    GL20.glDisableVertexAttribArray(1);
    GL20.glDisableVertexAttribArray(2);

    // Unbind VAO
    GL30.glBindVertexArray(0);
}

}