package com.project3.lamplight.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import com.project3.lamplight.model.Entity;
import com.project3.lamplight.model.RawModel;

public class Renderer {

    public void render(Entity entity) {

        RawModel model = entity.getModel();

        glBindBuffer(GL_ARRAY_BUFFER, model.getVboID());
        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(3, GL_FLOAT, 0, 0L);

        // -------- APPLY TRANSFORM --------
        glPushMatrix();

        glTranslatef(entity.getX(), entity.getY(), entity.getZ());
        glScalef(entity.getScale(), entity.getScale(), entity.getScale());

        glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());

        glPopMatrix();

        glDisableClientState(GL_VERTEX_ARRAY);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

}