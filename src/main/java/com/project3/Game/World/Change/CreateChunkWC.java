
package com.project3.Game.World.Change;

import com.project3.Game.World.Position.ChunkPos;
import com.project3.Game.World.World;

public class CreateChunkWC extends WorldChange {
    private ChunkPos pos;

    public CreateChunkWC(ChunkPos pos){
        this.pos = pos;
    }

    @Override
    public void makeChange(World world) {

    }
}
