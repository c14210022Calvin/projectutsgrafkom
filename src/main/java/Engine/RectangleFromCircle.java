package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class RectangleFromCircle extends Object {
    double x,y,centerpointX,centerpointY,r;

    public RectangleFromCircle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, double centerpointX,double centerpointY,double r) {
        super(shaderModuleDataList, vertices, color);
        this.centerpointX=centerpointX;
        this.centerpointY=centerpointY;
        this.r = r;
        createCircle();
        setupVAOVBO();
    }


    public void createCircle(){
        vertices.clear();

        for (double i=45; i<360; i+=90f){
            x = this.centerpointX + r * Math.cos(Math.toRadians(i));
            y = this.centerpointY + r * Math.sin(Math.toRadians(i));
            vertices.add(new Vector3f((float) x,(float) y,0.0f));

            ArrayList<Float> radiusRectangle = new ArrayList<>();
            radiusRectangle.add((float) x);
            radiusRectangle.add((float) y);

//            for(Float j : radiusRectangle) {
//                System.out.println("j: "+ j);
//            }

            //System.out.println(x + "                                 " + y);
        }
    }

//    public double getRectangleHeight(){
//        double diagonal = 2 * r;
//        return diagonal / Math.sqrt(2);
//    }
//
//    public double getRectangleWidth(){
//        double diagonal = 2 * r;
//        return diagonal / Math.sqrt(2);
//    }

    public boolean isPointInside(double x, double y) {
        double distance = Math.sqrt(Math.pow(x - centerpointX, 2) + Math.pow(y - centerpointY, 2));
        return distance <= 2*r;
    }

    public double get_centerX() {
        return centerpointX;
    }
    public double get_centerY() {
        return centerpointY;
    }

    public void set_centerX(double newCenter_X) {
        this.centerpointX = newCenter_X;
    }
    public void set_centerY(double newCenter_Y) {
        this.centerpointY = newCenter_Y;
    }

    public double get_X() {
        return this.x;
    }
    public double get_Y() {
        return this.y;
    }
//
//    // Untuk mendapatkan Width Rectangle
//    public double getWidth() {
//        // ambil titik pertama pada lingkaran sebagai titik awal
//        Vector3f startPoint = vertices.get(0);
//
//        // ambil titik kedua pada lingkaran sebagai titik akhir
//        Vector3f endPoint = vertices.get(1);
//
//        // hitung jarak antara dua titik pada lingkaran menggunakan rumus jarak Euclidean
//        double distance = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2) + Math.pow(endPoint.y - startPoint.y, 2));
//
//        // lebar persegi = jarak antara dua titik pada lingkaran
//        double width = distance;
//
//        return width;
//    }



    public void draw(){
        //drawSetup();
        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_POLYGON,0,vertices.size());
    }
}