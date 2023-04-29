package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class Hyperboloid2Side extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    public Hyperboloid2Side(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint,
                            Float radiusX, Float radiusY, Float radiusZ, int sectorCount,int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        createHyperboloid2Side();
        setupVAOVBO();
    }

    public void draw(){
        //drawSetup();
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP,
                0,
                vertices.size());
    }


    public void createHyperboloid2Side(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

//        sheet1
        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.tan(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.tan(v) * Math.sin(u));
                float z = 0.5f * (float)(1/Math.cos(v));
                temp.add(new Vector3f(x,y,z));
            }
        }

//        sheet2
        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = Math.PI/2; u<= 3*Math.PI/2; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.tan(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.tan(v) * Math.sin(u));
                float z = -0.5f * (float)(1/Math.cos(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }
}
