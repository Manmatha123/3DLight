package com.project3.Game.World.Chunk;

import com.project3.Game.World.World;
import com.project3.Game.World.Position.ChunkPos;


public class ChunkAlreadyExistsException extends Exception {
    public ChunkAlreadyExistsException(ChunkPos pos, World world) {
        super("Tried to create a chunk at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ") in World \"" +
            world.getName() + "\" that already existed!");
    }
}
