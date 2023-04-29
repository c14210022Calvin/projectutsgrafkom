package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class Ellipsoid extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    public Ellipsoid(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint,
                     Float radiusX, Float radiusY, Float radiusZ, int sectorCount,int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
//        createEllipsoid();
        createHalfEllipsoid(true);
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


    public void createEllipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(Math.cos(v) * Math.cos(u));
                float y = radiusY * (float)(Math.cos(v) * Math.sin(u));
                float z = radiusZ * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    public void createHalfEllipsoid(boolean isTopHalf) {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        double startV = isTopHalf ? 0 : -Math.PI/2;
        double endV = isTopHalf ? Math.PI/2 : 0;

        for(double v = startV; v <= endV; v += Math.PI/60) {
            for(double u = -Math.PI; u <= Math.PI; u += Math.PI/60) {
                float x = radiusX * (float)(Math.cos(v) * Math.cos(u));
                float y = radiusY * (float)(Math.cos(v) * Math.sin(u));
                float z = radiusZ * (float)(Math.sin(v));

                if (isTopHalf) {
                    temp.add(new Vector3f(x, y, z));
                } else {
                    temp.add(new Vector3f(x, y, -z));
                }
            }
        }
        vertices = temp;
    }

}

