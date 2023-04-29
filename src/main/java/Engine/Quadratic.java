package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;

public class Quadratic extends Circle{

    int stackCount, sectorCount;
    float radiusZ;

    public Quadratic(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ, int sectorCount, int stackCount) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;

//        createEllipsoid();
//        createHyperboloid1Side();
//        createHyperboloid2Side();
        createEllipticCone();
//        createEllipticParaboloid();
//        createHyperboloidParaboloid();

        setupVAOVBO();
    }

    public void draw(){
//        drawSetup();
        glLineWidth(100); //ketebalan garis
        glPointSize(100); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP,
                0,
                vertices.size());
    }

    /*public void createTriangle() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        // Tambahkan titik-titik segitiga
        temp.add(new Vector3f(0.0f, 0.0f, 0.0f)); // A
        temp.add(new Vector3f(1.0f, 0.0f, 0.0f)); // B
        temp.add(new Vector3f(0.0f, 1.0f, 0.0f)); // C

        // Tambahkan indeks untuk membentuk segitiga
        indices.clear();
        indices.add(0); indices.add(1); indices.add(2);

        vertices = temp;
    }*/


    public void createCylinder(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        int height = 5;

        float x, y, z, sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = height / stackCount;
        float sectorAngle, stackAngle;

        for(int i = 0; i <= stackCount; i++) {
            stackAngle = height/2 - i * stackStep;

            for(int j = 0; j <= sectorCount; j++) {
                sectorAngle = j * sectorStep;

                x = (float)(radiusX * Math.cos(sectorAngle));
                y = (float)(radiusY * Math.sin(sectorAngle));
                z = stackAngle;

                temp.add(new Vector3f(x,y,z));
            }
        }

        vertices = temp;
    }

    public void createEllipticCone(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)v * (float)(Math.cos(u));
                float y = radiusY * (float)v * (float)(Math.sin(u));
                float z = 4 * radiusZ * (float)(1-v);
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    public void createEllipsoid() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.cos(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.cos(v) * Math.sin(u));
                float z = 0.5f * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
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

    public void createHyperboloid2Side(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        // Sheet 1
        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.tan(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.tan(v) * Math.sin(u));
                float z = 0.5f * (float)(1/Math.cos(v));
                temp.add(new Vector3f(x,y,z));
            }
        }

        // Sheet 2
        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = Math.PI/2; u<= 3*(Math.PI/2); u+=Math.PI/60){
                float x = 0.5f * (float)(Math.tan(v) * Math.cos(u));
                float z = 0.5f * (float)(Math.tan(v) * Math.sin(u));
                float y = 0.5f * (float)(1/Math.cos(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    public void createEllipticParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)v * (float)(Math.cos(u));
                float y = radiusY * (float)v * (float)(Math.sin(u));
                float z = (float)(Math.pow(v, 2));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    public void createHyperboloidParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)v * (float)(Math.tan(u));
                float y = radiusY * (float)v * (float)(1/Math.cos(u));
                float z = (float)(Math.pow(v, 2));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }





    // Untuk Roket
    /*public void rotate(float angle, Vector3f axis) {
        Matrix4f rotationMatrix = new Matrix4f().rotate(angle, axis);
        for (int i = 0; i < vertices.size(); i++) {
            Vector4f vertex = new Vector4f(vertices.get(i), 1);
            vertex = rotationMatrix.transform(vertex);
            vertices.set(i, new Vector3f(vertex.x, vertex.y, vertex.z));
        }
    }*/

    // Translasi
    /*public void translate(Vector3f translation) {
        Matrix4f translationMatrix = new Matrix4f().translate(translation);
        for (int i = 0; i < vertices.size(); i++) {
            Vector4f vertex = new Vector4f(vertices.get(i), 1);
            vertex = translationMatrix.transform(vertex);
            vertices.set(i, new Vector3f(vertex.x, vertex.y, vertex.z));
        }
    }*/

    public void translate(Vector3f translationVector) {
        Matrix4f translationMatrix = new Matrix4f().translate(translationVector);
        for (int i = 0; i < vertices.size(); i++) {
            Vector4f vertex = new Vector4f(vertices.get(i), 1);
            vertex = translationMatrix.transform(vertex);
            vertices.set(i, new Vector3f(vertex.x, vertex.y, vertex.z));
        }
    }



}
