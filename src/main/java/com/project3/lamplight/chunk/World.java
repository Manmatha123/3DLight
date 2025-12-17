package com.project3.lamplight.chunk;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.project3.lamplight.model.Entity;
import com.project3.lamplight.model.RawModel;

public class World {

    private static final float CHUNK_SIZE = 50f;
    private static final int VIEW_DISTANCE = 3; // chunks kept on each side

    private Map<Integer, Chunk> loadedChunks = new HashMap<>();
    private RawModel treeModel;
    private RawModel houseModel;
    private RawModel dragonModel;

    public World(RawModel treeModel, RawModel houseModel, RawModel dragonModel) {
        this.treeModel = treeModel;
        this.houseModel = houseModel;
        this.dragonModel = dragonModel;
    }

    public void update(float cameraZ) {

        int currentChunk = (int) Math.floor(cameraZ / CHUNK_SIZE);

        // -------- LOAD REQUIRED CHUNKS --------
        for (int i = currentChunk - VIEW_DISTANCE; i <= currentChunk + VIEW_DISTANCE; i++) {

            if (!loadedChunks.containsKey(i)) {
                loadChunk(i);
            }
        }

        // -------- REMOVE FAR CHUNKS --------
        Iterator<Map.Entry<Integer, Chunk>> it = loadedChunks.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Integer, Chunk> entry = it.next();

            if (Math.abs(entry.getKey() - currentChunk) > VIEW_DISTANCE) {
                it.remove(); // 🔥 UNLOAD CHUNK
            }
        }
    }

    private void loadChunk(int index) {

        Chunk chunk = new Chunk(index * CHUNK_SIZE);

        // Add Trees
        for (int i = 0; i < 5; i++) {
            float x = (float) (Math.random() * 20 - 10);
            float z = chunk.startZ - (float) (Math.random() * CHUNK_SIZE);

            chunk.addEntity(new Entity(treeModel, x, 0, z, 1f));
        }

        // Add Houses
        for (int i = 0; i < 2; i++) {
            float x = (float) (Math.random() * 30 - 15);
            float z = chunk.startZ - (float) (Math.random() * CHUNK_SIZE);

            chunk.addEntity(new Entity(houseModel, x, 0, z, 2f));
        }

        // Add Dragons
        for (int i = 0; i < 1; i++) {
            float x = (float) (Math.random() * 40 - 20);
            float z = chunk.startZ - (float) (Math.random() * CHUNK_SIZE);

            chunk.addEntity(new Entity(dragonModel, x, 0, z, 0.2f));
        }

        loadedChunks.put(index, chunk);
    }

    public Collection<Chunk> getChunks() {
        return loadedChunks.values();
    }
}
