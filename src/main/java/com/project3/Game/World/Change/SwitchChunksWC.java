package com.project3.Game.World.Change;

import com.project3.Game.World.Position.ChunkPos;
import com.project3.Game.GameObject.GameObject;
import com.project3.Game.World.World;

public class SwitchChunksWC extends WorldChange {
    ChunkPos prevPos, newPos;
    GameObject object;

    public SwitchChunksWC(ChunkPos prevPos, ChunkPos newPos, GameObject object) {
        this.prevPos = prevPos;
        this.newPos = newPos;
        this.object = object;
    }

    @Override
    public void makeChange(World world) {
        world.getChunk(prevPos).removeGameObject(object);
        world.getChunk(newPos).addGameObject(object);
    }
}
