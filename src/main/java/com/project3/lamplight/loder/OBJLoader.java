package com.project3.lamplight.loder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.project3.lamplight.model.RawModel;

public class OBJLoader {

    public static RawModel loadObjModel(String fileName, Loader loader) {


        List<Vector3f> positions = new ArrayList<>();
        List<Vector2f> texCoords = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();

        List<Float> finalVertices = new ArrayList<>();
        List<Float> finalTexCoords = new ArrayList<>();
        List<Float> finalNormals = new ArrayList<>();

        try {
            InputStream ins = OBJLoader.class.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("v ")) {
                    String[] s = line.split("\\s+");
                    positions.add(new Vector3f(
                            Float.parseFloat(s[1]),
                            Float.parseFloat(s[2]),
                            Float.parseFloat(s[3])));
                } else if (line.startsWith("vt ")) {
                    String[] s = line.split("\\s+");
                    texCoords.add(new Vector2f(
                            Float.parseFloat(s[1]),
                            1 - Float.parseFloat(s[2])));
                } else if (line.startsWith("vn ")) {
                    String[] s = line.split("\\s+");
                    normals.add(new Vector3f(
                            Float.parseFloat(s[1]),
                            Float.parseFloat(s[2]),
                            Float.parseFloat(s[3])));
                } else if (line.startsWith("f ")) {
                    String[] face = line.split("\\s+");

                    // triangulate (works for triangles & quads)
                    processFaceVertex(face[1], positions, texCoords, normals, finalVertices, finalTexCoords,
                            finalNormals);
                    processFaceVertex(face[2], positions, texCoords, normals, finalVertices, finalTexCoords,
                            finalNormals);
                    processFaceVertex(face[3], positions, texCoords, normals, finalVertices, finalTexCoords,
                            finalNormals);

                    if (face.length == 5) { // quad
                        processFaceVertex(face[1], positions, texCoords, normals, finalVertices, finalTexCoords,
                                finalNormals);
                        processFaceVertex(face[3], positions, texCoords, normals, finalVertices, finalTexCoords,
                                finalNormals);
                        processFaceVertex(face[4], positions, texCoords, normals, finalVertices, finalTexCoords,
                                finalNormals);
                    }
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        float[] verticesArray = toArray(finalVertices);
        float[] texArray = toArray(finalTexCoords);
        float[] normalsArray = toArray(finalNormals);

        return loader.loadToVAO(verticesArray, texArray, normalsArray);
    }

    private static void processFaceVertex(
            String data,
            List<Vector3f> positions,
            List<Vector2f> texCoords,
            List<Vector3f> normals,
            List<Float> vOut,
            List<Float> tOut,
            List<Float> nOut) {

        String[] parts = data.split("/");

        int vIndex = Integer.parseInt(parts[0]) - 1;
        Vector3f pos = positions.get(vIndex);
        vOut.add(pos.x);
        vOut.add(pos.y);
        vOut.add(pos.z);

        if (parts.length > 1 && !parts[1].isEmpty()) {
            Vector2f tex = texCoords.get(Integer.parseInt(parts[1]) - 1);
            tOut.add(tex.x);
            tOut.add(tex.y);
        } else {
            tOut.add(0f);
            tOut.add(0f);
        }

        if (parts.length > 2) {
            Vector3f norm = normals.get(Integer.parseInt(parts[2]) - 1);
            nOut.add(norm.x);
            nOut.add(norm.y);
            nOut.add(norm.z);
        } else {
            nOut.add(0f);
            nOut.add(1f);
            nOut.add(0f);
        }
    }

    private static float[] toArray(List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++)
            array[i] = list.get(i);
        return array;
    }
}