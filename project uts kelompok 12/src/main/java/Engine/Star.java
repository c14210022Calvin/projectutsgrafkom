package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Star extends Object {
    double centerpointX,centerpointY,r;
    public Star(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, double centerpointX,double centerpointY,double r, int numPoints, float innerRadius) {
        super(shaderModuleDataList, vertices, color);
        this.centerpointX=centerpointX;
        this.centerpointY=centerpointY;
        this.r = r;
        //createStar();
        createLineStar(numPoints, innerRadius);
        setupVAOVBO();
    }

    public void createLineStar(int numPoints, float innerRadius) {
        vertices.clear();
        double angleIncrement = 2*Math.PI / numPoints;
        for (int i = 0; i < numPoints; i++) {
            // Outer vertex
            double outerAngle = i * angleIncrement;
            double outerX = centerpointX + r*Math.cos(outerAngle);
            double outerY = centerpointY + r*Math.sin(outerAngle);
            vertices.add(new Vector3f((float)outerX, (float)outerY, 0.0f));

            // Inner vertex
            double innerAngle = outerAngle + angleIncrement/2.0;
            double innerX = centerpointX + innerRadius*Math.cos(innerAngle);
            double innerY = centerpointY + innerRadius*Math.sin(innerAngle);
            vertices.add(new Vector3f((float)innerX, (float)innerY, 0.0f));
        }
    }


    public void draw(){
        //drawSetup();
        glDrawArrays(GL_POLYGON,0,vertices.size());
    }
}