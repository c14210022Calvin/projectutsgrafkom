package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class Hyperboloid1Side extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    public Hyperboloid1Side(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint,
                            Float radiusX, Float radiusY, Float radiusZ, int sectorCount,int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        //createBox();
        createHyperboloid1Side();
        setupVAOVBO();
    }

    public void draw(){
        //drawSetup();
        glLineWidth(3); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP,
                0,
                vertices.size());
    }


    public void createHyperboloid1Side(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(1/Math.cos(v) * Math.cos(u));
                float y = radiusY * (float)(1/Math.cos(v) * Math.sin(u));
                float z = radiusZ * (float)(Math.tan(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }
}
