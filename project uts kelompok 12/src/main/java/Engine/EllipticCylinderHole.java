

package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class EllipticCylinderHole extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    /*public EllipticCylinderHole(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint,
                            Float radiusX, Float radiusY, Float radiusZ, int sectorCount, int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        //createEllipsoid();
        //createEllipticCylinder();
        setupVAOVBO();

    }*/
    public EllipticCylinderHole(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint,
                     Float radiusX, Float radiusY, Float radiusZ, int sectorCount, int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        createHollowEllipticCylinder();
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


//    public void createEllipsoid(){
//        vertices.clear();
//        ArrayList<Vector3f> temp = new ArrayList<>();
//
//        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
//            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
//                float x = radiusX * (float)(Math.cos(v) * Math.cos(u));
//                float y = radiusY * (float)(Math.cos(v) * Math.sin(u));
//                float z = radiusZ * (float)(Math.sin(v));
//                temp.add(new Vector3f(x,y,z));
//            }
//        }
//        vertices = temp;
//    }
/*

    public void createEllipticCylinderHole(float holeRadius){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
//        float h = 2.0f; // Set cylinder height
        float z = 0.0f; // Set constant z coordinate

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/stackCount){
            float radiusZ = radiusX * (float)Math.cos(v); // Calculate radius at this height
            for(double u = -Math.PI; u<= Math.PI; u+=2*Math.PI/sectorCount){
                float x = radiusX * (float)(Math.cos(u));
                float y = radiusY * (float)(Math.sin(u));
                float distanceFromCenter = (float) Math.sqrt(x * x + y * y); // Calculate distance from center
                if(distanceFromCenter >= holeRadius){ // Check if vertex is outside the hole
                    temp.add(new Vector3f(x,y,z));
                }
            }
        }
        vertices = temp;
    }
*/


    public void createHollowEllipticCylinder() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        float innerRadius = radiusX / 2.0f; // radius of the inner hole
        float outerRadius = radiusX; // radius of the outer ellipse

        for (double u = -Math.PI; u <= Math.PI; u += Math.PI / sectorCount) {
            for (double v = -1; v <= 1; v += 2.0 / stackCount) {
                float x = outerRadius * (float) Math.cos(u);
                float y = radiusY * (float) Math.sin(u);
                float z = radiusZ * (float) v;

                // check if vertex is inside the inner radius
                if (Math.sqrt(x * x + y * y) > innerRadius) {
                    temp.add(new Vector3f(x, y, z));
                }
            }
        }

        vertices = temp;
    }


}


