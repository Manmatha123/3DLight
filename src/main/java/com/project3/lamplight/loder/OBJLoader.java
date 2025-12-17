package com.project3.lamplight.loder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.project3.lamplight.model.RawModel;



public class OBJLoader {

    public static RawModel loadObjModel(String fileName, Loader loader) {
        List<float[]> vertices = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        List<Float> finalVertices = new ArrayList<>();

        try {
            InputStream ins=OBJLoader.class.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ")) {
                    String[] tokens = line.split(" ");
                    vertices.add(new float[] {
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]),
                            Float.parseFloat(tokens[3])
                    });
                } else if (line.startsWith("f ")) {
                    String[] tokens = line.split(" ");
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                }
            }
            reader.close();

            for (int index : indices) {
                float[] v = vertices.get(index);
                finalVertices.add(v[0]);
                finalVertices.add(v[1]);
                finalVertices.add(v[2]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        float[] verticesArray = new float[finalVertices.size()];
        for (int i = 0; i < finalVertices.size(); i++) {
            verticesArray[i] = finalVertices.get(i);
        }

        return loader.loadToVBO(verticesArray);
    }
}