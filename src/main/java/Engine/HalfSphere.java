package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class HalfSphere extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    public HalfSphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ,
                  int sectorCount,int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        createHalfSphere();
        setupVAOVBO();
    }

    public void draw(){
        //drawSetup();
        glLineWidth(2); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        glDrawArrays(GL_TRIANGLE_FAN,
                0,
                vertices.size());
    }


    public void createHalfSphere(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * pi / sectorCount;
        float stackStep = pi / (2 * stackCount);
        float sectorAngle, stackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {
            stackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(stackAngle);
            y = radiusY * (float)Math.cos(stackAngle);
            z = radiusZ * (float)Math.sin(stackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                if (temp_vector.z >= centerPoint.get(2)) {
                    vertices.add(temp_vector);
                }
            }
        }
    }

}
