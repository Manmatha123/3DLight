package com.project3.Game;

import com.project3.Game.World.Terrain.Terrain;
import com.project3.Input.Input;
import com.project3.Input.KeyboardInput;
import com.project3.Render.Lighting.Light;
import com.project3.Render.Model.OBJFileLoader;
import com.project3.Render.Model.RawModel;
import com.project3.Render.Model.TexturedModel;
import com.project3.Render.Texture.ModelTexture;
import com.project3.Render.Texture.TerrainTexture;
import com.project3.Render.Texture.TerrainTexturePack;
import com.project3.Game.GameObject.FreeCameraObject;
import com.project3.Game.GameObject.ModeledObject;
import com.project3.Game.World.Position.ChunkPos;
import com.project3.Game.World.Position.Pos3D;

import org.lwjgl.util.vector.Vector3f;

import java.util.*;

/**
 * Class Description Here
 *
 * @author Andrew Graber
 * @version 9/8/2017
 */
public class ExampleGame extends Game {

    @Override
    public void initialize() {
        super.initialize();

        Input input = new KeyboardInput();
        light = new Light(new Vector3f(256, 200, 256), new Vector3f(1, 1, 1));

        RawModel model = com.project3.Render.Model.OBJFileLoader.loadObjModel("dragon", modelLoader);
        ModelTexture texture = new ModelTexture(modelLoader.loadTexture("playerTexture"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        TexturedModel texturedModel = new TexturedModel(model, texture);
        new ModeledObject(texturedModel, new Pos3D(256, 200, 256, 0, 180, 0), world);

        ModeledObject player = new ModeledObject(texturedModel, new Pos3D(256, 0, 256, 0, 0, 0, 0.5f), world);
        camera = new FreeCameraObject(new Pos3D(256, 1, 256), world, input);

        //*********************************** TERRAIN TEXTURE PACK STUFF ****************************************
        TerrainTexture backgroundTexture = new TerrainTexture(modelLoader.loadTexture("grass"));
        TerrainTexture rTexture = new TerrainTexture(modelLoader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(modelLoader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(modelLoader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(modelLoader.loadTexture("blendMap"));
        //*******************************************************************************************************

        Terrain terrain = new Terrain(modelLoader, texturePack, blendMap, "heightmap");
        world.getChunk(new ChunkPos(0, 0, 0)).addTerrain(terrain);
        terrain = new Terrain(modelLoader, texturePack, blendMap, "heightmap");
        world.getChunk(new ChunkPos(1, 0, 0)).addTerrain(terrain);
        terrain = new Terrain(modelLoader, texturePack, blendMap, "heightmap");
        world.getChunk(new ChunkPos(1, 0, 1)).addTerrain(terrain);
        terrain = new Terrain(modelLoader, texturePack, blendMap, "heightmap");
        world.getChunk(new ChunkPos(0, 0, 1)).addTerrain(terrain);

        Random random = new Random();

        // TexturedModel treeModel = new TexturedModel(OBJFileLoader.loadObjModel("tree", modelLoader), new ModelTexture(modelLoader.loadTexture("tree")));
        // for(int i=0; i<200; i++) {
        //     Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0, 4);
        //     new ModeledObject(treeModel, pos, world);
        // }

        // TexturedModel fernModel = new TexturedModel(OBJFileLoader.loadObjModel("fern", modelLoader), new ModelTexture(modelLoader.loadTexture("fern"), true));
        // for(int i=0; i<200; i++) {
        //     Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0, 0.5f);
        //     new ModeledObject(fernModel, pos, world);
        // }

        // TexturedModel grassModel = new TexturedModel(OBJFileLoader.loadObjModel("grassModel", modelLoader), new ModelTexture(modelLoader.loadTexture("grassTexture"), true, true));
        // for(int i=0; i<200; i++) {
        //     Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0);
        //     new ModeledObject(grassModel, pos, world);
        // }

        // TexturedModel flowerModel = new TexturedModel(OBJFileLoader.loadObjModel("grassModel", modelLoader), new ModelTexture(modelLoader.loadTexture("flower"), true, true));
        // for(int i=0; i<200; i++) {
        //     Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0);
        //     new ModeledObject(flowerModel, pos, world);
        // }
    }
}