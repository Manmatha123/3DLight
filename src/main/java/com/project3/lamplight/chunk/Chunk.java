package com.project3.lamplight.chunk;

import java.util.ArrayList;
import java.util.List;

import com.project3.lamplight.model.Entity;

public class Chunk {

    public float startZ;
    public List<Entity> entities = new ArrayList<>();

    public Chunk(float startZ) {
        this.startZ = startZ;
    }
        public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
