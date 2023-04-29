import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
    (800,800,"Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectsRectangle
            = new ArrayList<>();

    private ArrayList<Object> objectsPointsControl
            = new ArrayList<>();

    private MouseInput mouseInput;
    int countDegree = 0;
    float hydraulic1 = 0;
    float hydraulic2 = 0;
    float hydraulic3 = 0;

    Projection projection = new Projection(window.getWidth(), window.getHeight());

    Camera camera = new Camera();


    public void init() {
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        //dari belakang
        /*camera.setPosition(-0.5f,0,3f);
        camera.setRotation((float)Math.toRadians(0.0f),(float)Math.toRadians(0f));*/
        //dari kiri atas
        camera.setPosition(-3f, 0.6f, -0.95f);
        camera.setRotation((float) Math.toRadians(-10.0f), (float) Math.toRadians(110.0f));

        //dari depan
        /*camera.setPosition(0f,0.6f,-3f);
        camera.setRotation((float)Math.toRadians(-10.0f),(float)Math.toRadians(180.0f));*/
        //dari kanan atas
        /*camera.setPosition(4f,0.6f,-1f);
        camera.setRotation((float)Math.toRadians(-10.0f),(float)Math.toRadians(250.0f));*/

        //Calvin
        {
            // badan truck
            objects.add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.135f,
                    0.175f,
                    36,
                    500
            ));
            /*objects.get(0).scaleObject(2f,2f,2f);*/

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.075f,
                    0.125f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(0).translateObject(0.0f, -0.03f, 0.145f);

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.170f,
                    0.065f,
                    0.7f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(1).translateObject(0.16f, 0.035f, 0.42f);

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.170f,
                    0.065f,
                    0.7f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(2).translateObject(-0.16f, 0.035f, 0.42f);

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.025f,
                    0.07f,
                    0.7f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(3).translateObject(0.065f, -0.025f, 0.42f);

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.025f,
                    0.07f,
                    0.7f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(4).translateObject(-0.065f, -0.025f, 0.42f);

            //buat roda 1
            //objects.get(0).getChildObject().get(1).getChildObject().add
            objects.get(0).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.2f, 0.2f, 0.2f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.015f,
                    0.015f,
                    0.6f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(5).translateObject(-0.5f, -0.16f, -0.3f);
            //rotate the object
            objects.get(0).getChildObject().get(5).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //besi roda
            objects.get(0).getChildObject().get(5).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(5).getChildObject().get(0).translateObject
                    (-0.5f, -0.16f, -0.32f);
            //rotate the object
            objects.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            objects.get(0).getChildObject().get(5).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(5).getChildObject().get(1).translateObject
                    (-0.5f, -0.16f, 0.22f);
            //rotate the object
            objects.get(0).getChildObject().get(5).getChildObject().get(1).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //karet roda
            objects.get(0).getChildObject().get(5).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(5).getChildObject().get(2).translateObject
                    (-0.5f, -0.16f, 0.22f);
            //rotate the object
            objects.get(0).getChildObject().get(5).getChildObject().get(2).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            objects.get(0).getChildObject().get(5).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(5).getChildObject().get(3).translateObject
                    (-0.5f, -0.16f, -0.32f);
            //rotate the object
            objects.get(0).getChildObject().get(5).getChildObject().get(3).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            //buat roda 2
            objects.get(0).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.2f, 0.2f, 0.2f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.015f,
                    0.015f,
                    0.6f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(6).translateObject(-0.8f, -0.16f, -0.3f);
            //rotate the object
            objects.get(0).getChildObject().get(6).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //besi roda
            objects.get(0).getChildObject().get(6).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(6).getChildObject().get(0).translateObject
                    (-0.8f, -0.16f, -0.32f);
            //rotate the object
            objects.get(0).getChildObject().get(6).getChildObject().get(0).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            objects.get(0).getChildObject().get(6).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(6).getChildObject().get(1).translateObject
                    (-0.8f, -0.16f, 0.22f);
            //rotate the object
            objects.get(0).getChildObject().get(6).getChildObject().get(1).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //karet roda
            objects.get(0).getChildObject().get(6).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(6).getChildObject().get(2).translateObject
                    (-0.8f, -0.16f, 0.22f);
            //rotate the object
            objects.get(0).getChildObject().get(6).getChildObject().get(2).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            objects.get(0).getChildObject().get(6).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(6).getChildObject().get(3).translateObject
                    (-0.8f, -0.16f, -0.32f);
            //rotate the object
            objects.get(0).getChildObject().get(6).getChildObject().get(3).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            //gerbong bagian depan
            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.135f,
                    0.125f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(7).translateObject(0.0f, 0.03f, -0.145f);

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.03f,
                    0.4f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(8).translateObject(0.0f, 0.085f, -0.4f);

            //HYDROLIC
            objects.get(0).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.2f, 0.2f, 0.2f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.015f,
                    0.015f,
                    0.2f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(9).translateObject(-0.95f, -0.0f, -0.1f);
            //rotate the object
            objects.get(0).getChildObject().get(9).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            objects.get(0).getChildObject().get(9).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.11f, 0.14f, 0.11f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.3f,
                    0.05f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(0).translateObject
                    (0.0f, 0.15f, 0.64f);

            objects.get(0).getChildObject().get(9).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.11f, 0.14f, 0.11f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.3f,
                    0.38f,
                    0.05f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(1).translateObject
                    (0.0f, 0.3f, 0.64f);


            //buat tempat rocket
            //kiri bawah
            objects.get(0).getChildObject().get(9).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.247f, 0.294f, 0.309f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    1.0f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(2).translateObject(-0.18f, 0.2f, -0.2f);

            //kanan bawah
            objects.get(0).getChildObject().get(9).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.247f, 0.294f, 0.309f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    1.0f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(3).translateObject(0.18f, 0.2f, -0.2f);

            //kiri atas
            objects.get(0).getChildObject().get(9).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.247f, 0.294f, 0.309f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    1.0f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(4).translateObject(-0.12f, 0.4f, -0.2f);

            //kanan atas
            objects.get(0).getChildObject().get(9).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.247f, 0.294f, 0.309f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    1.0f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(5).translateObject(0.12f, 0.4f, -0.2f);

            //setengah lingkaran kanan atas
            objects.get(0).getChildObject().get(9).getChildObject().add(new HalfSphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.388f, 0.431f, 0.435f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    0.05f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(6).translateObject(0.12f, 0.4f, 0.8f);

            //setengah lingkaran kiri atas
            objects.get(0).getChildObject().get(9).getChildObject().add(new HalfSphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.388f, 0.431f, 0.435f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    0.05f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(7).translateObject(-0.12f, 0.4f, 0.8f);

            //setengah lingkaran kanan bawah
            objects.get(0).getChildObject().get(9).getChildObject().add(new HalfSphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.388f, 0.431f, 0.435f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    0.05f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(8).translateObject(0.18f, 0.2f, 0.8f);

            //setengah lingkaran kiri bawah
            objects.get(0).getChildObject().get(9).getChildObject().add(new HalfSphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.388f, 0.431f, 0.435f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    0.05f,
                    36,
                    1000
            ));
            objects.get(0).getChildObject().get(9).getChildObject().get(9).translateObject(-0.18f, 0.2f, 0.8f);

            //badan truk bagian depan
            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.125f,
                    0.075f,
                    0.6f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(10).translateObject(0.0f, 0.12f, -0.35f);

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.125f,
                    0.075f,
                    0.27f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(11).translateObject(0.0f, 0.17f, -0.74f);

            objects.get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.3f,
                    0.18f,
                    0.125f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(12).translateObject(0.0f, 0.24f, -0.83f);

            objects.get(0).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.2f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.07f,
                    0.07f,
                    0.1f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(13).translateObject(0.13f, 0.24f, -0.76f);

            objects.get(0).getChildObject().add(new EllipticCylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.2f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.07f,
                    0.07f,
                    0.1f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(14).translateObject(-0.13f, 0.24f, -0.76f);

            //hydraulic kanan depan
            objects.get(0).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(01.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.01f,
                    0.01f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(15).translateObject(0.17f, -0.03f, -0.15f);

            objects.get(0).getChildObject().get(15).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.02f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(15).getChildObject().get(0).translateObject(0.17f, -0.07f, -0.15f);

            objects.get(0).getChildObject().get(15).getChildObject().get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(15).getChildObject().get(0).getChildObject().get(0).translateObject(0.17f, -0.07f, -0.15f);

            objects.get(0).getChildObject().get(15).getChildObject().get(0).getChildObject().add(new Ellipsoid(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.174f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.05f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(15).getChildObject().get(0).getChildObject().get(1).translateObject(0.35f, 0.21f, -0.2f);
            objects.get(0).getChildObject().get(15).getChildObject().get(0).getChildObject().get(1).rotateObject(1.575f, -0.7f, 0.0f, 0.0f);

            //hydraulic kiri depan
            objects.get(0).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(01.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.01f,
                    0.01f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(16).translateObject(-0.17f, -0.03f, -0.15f);
            objects.get(0).getChildObject().get(16).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.02f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(16).getChildObject().get(0).translateObject(-0.17f, -0.07f, -0.15f);

            objects.get(0).getChildObject().get(16).getChildObject().get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(16).getChildObject().get(0).getChildObject().get(0).translateObject(-0.17f, -0.07f, -0.15f);


            objects.get(0).getChildObject().get(16).getChildObject().get(0).getChildObject().add(new Ellipsoid(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.174f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.05f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(16).getChildObject().get(0).getChildObject().get(1).translateObject(-0.35f, 0.21f, -0.2f);
            objects.get(0).getChildObject().get(16).getChildObject().get(0).getChildObject().get(1).rotateObject(1.575f, -0.7f, 0.0f, 0.0f);


            //hydraulic kiri belakang
            objects.get(0).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(01.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.01f,
                    0.01f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(17).translateObject(-0.09f, -0.03f, 0.75f);
            objects.get(0).getChildObject().get(17).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.02f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(17).getChildObject().get(0).translateObject(-0.09f, -0.07f, 0.75f);

            objects.get(0).getChildObject().get(17).getChildObject().get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(17).getChildObject().get(0).getChildObject().get(0).translateObject(-0.09f, -0.07f, 0.75f);


            objects.get(0).getChildObject().get(17).getChildObject().get(0).getChildObject().add(new Ellipsoid(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.174f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.05f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(17).getChildObject().get(0).getChildObject().get(1).translateObject(-0.18f, -1.07f, -0.2f);
            objects.get(0).getChildObject().get(17).getChildObject().get(0).getChildObject().get(1).rotateObject(1.575f, -0.7f, 0.0f, 0.0f);


            //hydraulic kanan belakang
            objects.get(0).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(01.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.01f,
                    0.01f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(18).translateObject(0.09f, -0.03f, 0.75f);

            objects.get(0).getChildObject().get(18).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.24f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.02f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(18).getChildObject().get(0).translateObject(0.09f, -0.07f, 0.75f);

            objects.get(0).getChildObject().get(18).getChildObject().get(0).getChildObject().add(new Kotak(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.1f,
                    0.02f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(18).getChildObject().get(0).getChildObject().get(0).translateObject(0.09f, -0.07f, 0.75f);

            objects.get(0).getChildObject().get(18).getChildObject().get(0).getChildObject().add(new Ellipsoid(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.174f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.05f,
                    0.05f,
                    36,
                    500
            ));
            objects.get(0).getChildObject().get(18).getChildObject().get(0).getChildObject().get(1).translateObject(0.18f, -1.07f, -0.2f);
            objects.get(0).getChildObject().get(18).getChildObject().get(0).getChildObject().get(1).rotateObject(1.575f, -0.7f, 0.0f, 0.0f);
        }

        //Markus
        {
            // Titik putar roket
            objects.add(new Tabung(Arrays.asList(
                    //shaderFile lokasi menyesuaikan objectnya
                    new ShaderProgram.ShaderModuleData
                            ("resources/shaders/scene.vert"
                                    , GL_VERTEX_SHADER),
                    new ShaderProgram.ShaderModuleData
                            ("resources/shaders/scene.frag"
                                    , GL_FRAGMENT_SHADER)
            ),
                    new ArrayList<>(),
//                new Vector4f(0.2f,0.3f,0.0f,1.0f),
                    new Vector4f(0f, 0, 0, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.001f, 0.001f, 0.001f, 20, 50
            ));

            // Badan Roket
            objects.get(1).getChildObject().add(new Tabung(Arrays.asList(
                    //shaderFile lokasi menyesuaikan objectnya
                    new ShaderProgram.ShaderModuleData
                            ("resources/shaders/scene.vert"
                                    , GL_VERTEX_SHADER),
                    new ShaderProgram.ShaderModuleData
                            ("resources/shaders/scene.frag"
                                    , GL_FRAGMENT_SHADER)
            ),
                    new ArrayList<>(),
//                new Vector4f(0.2f,0.3f,0.0f,1.0f),
                    new Vector4f(0.65f, 0.65f, 0.65f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.08f, 0.08f, 0.08f, 20, 50
            ));
            objects.get(1).getChildObject().get(0).scaleObject(1.0f, 1.0f, 1.0f * 2.8f);
            objects.get(1).getChildObject().get(0).translateObject(-0.12f, -0.4f, -0f);
            objects.get(1).getChildObject().get(0).rotateObject(3.15f, 1f, 0.0f, 0.0f);

            // Tutup Roket (Dari Object Quadratic)
            objects.get(1).getChildObject().add(new Quadratic(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
//                new Vector4f(0.1f,0.3f,0.0f,1.0f),
                    new Vector4f(0.65f, 0.65f, 0.65f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.08f, 0.08f, 0.08f, 20, 30
            ));
            objects.get(1).getChildObject().get(1).scaleObject(0.65f, 0.65f, 0.65f);
            objects.get(1).getChildObject().get(1).translateObject(-0.12f, -0.4f, 0.33f);
            objects.get(1).getChildObject().get(1).rotateObject(3.15f, 1f, 0.0f, 0.0f);


            // Bawah Roket (Dari Object Quadratic)
            objects.get(1).getChildObject().add(new Quadratic(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.0f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f, 0.1f, 0.1f, 20, 30
            ));
            objects.get(1).getChildObject().get(2).scaleObject(0.5f, 0.5f, 0.5f);
            objects.get(1).getChildObject().get(2).translateObject(-0.12f, -0.4f, -0.2f);
            objects.get(1).getChildObject().get(2).rotateObject(3.15f, 1f, 0.0f, 0.0f);

            // Red Nose Roket
            objects.get(1).getChildObject().add(new Tabung(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(1f, 0f, 0.0f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f, 0.1f, 0.1f, 20, 30
            ));
            objects.get(1).getChildObject().get(3).scaleObject(0.125f, 0.125f, 0.121f);
            objects.get(1).getChildObject().get(3).translateObject(-0.12f, -0.4f, 0.53f);
            objects.get(1).getChildObject().get(3).rotateObject(3.15f, 1f, 0.0f, 0.0f);

            //titik pusat buat api
            objects.get(1).getChildObject().add(new Tabung(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(1f, 0f, 0.0f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.01f,
                    0.01f,
                    0.01f,
                    20,
                    500
            ));
            objects.get(1).getChildObject().get(4).translateObject(-0.12f, 0.4f, 0.23f);

            // Api api
            // Membuat beberapa objek silinder sebagai api
            float length = 0.125f; // panjang silinder
            float radius = 0.025f; // radius silinder
            int numCylinders = 10; // jumlah silinder

            for (int i = 0; i < numCylinders; i++) {
                Quadratic cylinder = new Quadratic(Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                        new ArrayList<>(),
                        new Vector4f(1.0f, 0.5f, 0.0f, 1.0f), // warna api (disini menggunakan warna kuning)
                        Arrays.asList(0.0f, 0.0f, 0.0f),
                        radius, radius, length, 20, 1
                );

                // Menempatkan silinder pada posisi yang tepat
                float angle = i * (360.0f / numCylinders);
                float x = radius * (float) Math.cos(Math.toRadians(angle));
                float y = radius * (float) Math.sin(Math.toRadians(angle));
                cylinder.translateObject(x, y, 0.6f);
                cylinder.translateObject(-0.12f, 0.4f, -0.4f);
//            cylinder.scaleObject(0);
                cylinder.rotateObject(3.15f, 1.0f, 0f, 0.0f); // membalikkan silinder agar menghadap ke bawah
                cylinder.rotateObject(3.15f, 1.0f, 0.0f, 0.0f);

                // Menambahkan silinder ke daftar objek
                objects.get(1).getChildObject().get(4).getChildObject().add(cylinder);

            }

            System.out.println(objects.get(1).getChildObject().get(2).getClass());
        }

        //Ricky
        {
            //Truck Sinyal Militer
            //trailer Sinyal
            objects.add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.135f,
                    0.175f,
                    36,
                    50
            ));
            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.075f,
                    0.125f,
                    36,
                    50
            ));
            objects.get(2).getChildObject().get(0).translateObject(0.0f, -0.03f, 0.145f);
            objects.get(2).getChildObject().get(0).translateObject(2.0f, 0.0f, 0.0f);

            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.510f,
                    0.065f,
                    1.3f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(1).translateObject(0.0f, 0.035f, 0.8f);
            objects.get(2).getChildObject().get(1).translateObject(2.0f, 0.0f, 0.0f);


            //buat roda ke 1
            objects.get(2).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.2f, 0.2f, 0.2f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.015f,
                    0.015f,
                    0.6f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(2).translateObject(-1.8f, -0.16f, -0.3f);
            objects.get(2).getChildObject().get(2).translateObject(0.0f, 0.0f, 2.8f);

            objects.get(2).getChildObject().get(2).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //velg
            objects.get(2).getChildObject().get(2).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(2).getChildObject().get(0).translateObject(-1.8f, -0.16f, -0.32f);
            objects.get(2).getChildObject().get(2).getChildObject().get(0).translateObject(0.0f, 0.0f, 2.8f);

            objects.get(2).getChildObject().get(2).getChildObject().get(0).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            objects.get(2).getChildObject().get(2).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(2).getChildObject().get(1).translateObject(-1.8f, -0.16f, 0.22f);
            objects.get(2).getChildObject().get(2).getChildObject().get(1).translateObject(0f, 0.0f, 2.8f);

            objects.get(2).getChildObject().get(2).getChildObject().get(1).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //ban
            objects.get(2).getChildObject().get(2).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(2).getChildObject().get(2).translateObject(-1.8f, -0.16f, 0.22f);
            objects.get(2).getChildObject().get(2).getChildObject().get(2).translateObject(0f, 0.0f, 2.8f);

            objects.get(2).getChildObject().get(2).getChildObject().get(2).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            objects.get(2).getChildObject().get(2).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(2).getChildObject().get(3).translateObject(-1.8f, -0.16f, -0.32f);
            objects.get(2).getChildObject().get(2).getChildObject().get(3).translateObject(0f, 0.0f, 2.8f);

            objects.get(2).getChildObject().get(2).getChildObject().get(3).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //roda 2
            objects.get(2).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.2f, 0.2f, 0.2f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.015f,
                    0.015f,
                    0.6f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(3).translateObject(-1.5f, -0.16f, -0.3f);
            objects.get(2).getChildObject().get(3).translateObject(0f, -0f, 2.8f);

            objects.get(2).getChildObject().get(3).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //velg
            objects.get(2).getChildObject().get(3).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(3).getChildObject().get(0).translateObject(-1.5f, -0.16f, -0.32f);
            objects.get(2).getChildObject().get(3).getChildObject().get(0).translateObject(0f, 0f, 2.8f);

            objects.get(2).getChildObject().get(3).getChildObject().get(0).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            objects.get(2).getChildObject().get(3).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.6f, 0.6f, 0.6f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.05f,
                    0.075f,
                    0.1f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(3).getChildObject().get(1).translateObject(-1.5f, -0.16f, 0.22f);
            objects.get(2).getChildObject().get(3).getChildObject().get(1).translateObject(0f, 0f, 2.8f);

            objects.get(2).getChildObject().get(3).getChildObject().get(1).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //ban
            objects.get(2).getChildObject().get(3).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(3).getChildObject().get(2).translateObject(-1.5f, -0.16f, 0.22f);
            objects.get(2).getChildObject().get(3).getChildObject().get(2).translateObject(0f, 0f, 2.8f);

            objects.get(2).getChildObject().get(3).getChildObject().get(2).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);


            objects.get(2).getChildObject().get(3).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.1f, 0.1f, 0.1f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.12f,
                    0.17f,
                    0.09f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(3).getChildObject().get(3).translateObject(-1.5f, -0.16f, -0.32f);
            objects.get(2).getChildObject().get(3).getChildObject().get(3).translateObject(0f, 0f, 2.8f);

            objects.get(2).getChildObject().get(3).getChildObject().get(3).rotateObject(1.575f, 0.0f, 0.7f, 0.0f);

            //box genset
            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.3f,
                    0.25f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(4).translateObject(0.0f, 0.22f, 1.32f);
            objects.get(2).getChildObject().get(4).translateObject(2.0f, 0.0f, 0.0f);

            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.135f,
                    0.125f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(5).translateObject(0.0f, 0.03f, 0.145f);
            objects.get(2).getChildObject().get(5).translateObject(2.0f, 0.0f, 0.0f);

            //penyangga kaitan trailer
            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.03f,
                    0.4f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(6).translateObject(0.0f, 0.085f, 0.0f);
            objects.get(2).getChildObject().get(6).translateObject(2.0f, 0.0f, 0.0f);
            //kaitan trailer
            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    0.5f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(7).translateObject(0.0f, 0.11f, -0.2f);
            objects.get(2).getChildObject().get(7).translateObject(2.0f, 0.0f, 0.0f);

            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.3f, 0.34f, 0.274f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.1f,
                    0.1f,
                    0.1f,
                    36,
                    18
            ));
            objects.get(2).getChildObject().get(8).translateObject(0.0f, 0.05f, -0.4f);
            objects.get(2).getChildObject().get(8).translateObject(2.0f, 0.0f, 0.0f);

            //dudukan sinyal
            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.5f,
                    0.15f,
                    0.5f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(9).translateObject(0.0f, 0.14f, 0.7f);
            objects.get(2).getChildObject().get(9).translateObject(2.0f, 0.0f, 0.0f);

            objects.get(2).getChildObject().add(new SquareBox(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.3f,
                    0.35f,
                    0.3f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(10).translateObject(0.0f, 0.39f, 0.7f);
            objects.get(2).getChildObject().get(10).translateObject(2.0f, 0.0f, 0.0f);

            //parabola
            objects.get(2).getChildObject().get(10).getChildObject().add(new HalfSphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.3f,
                    0.3f,
                    0.25f,
                    36,
                    1000
            ));
            objects.get(2).getChildObject().get(10).getChildObject().get(0).translateObject(0.0f, 1.0f, 0.5f);
            objects.get(2).getChildObject().get(10).getChildObject().get(0).translateObject(2.0f, 0.0f, 0.0f);

            //tiang parabola
            objects.get(2).getChildObject().get(10).getChildObject().add(new Cylinder(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.596f, 0.611f, 0.392f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.03f,
                    0.03f,
                    0.8f,
                    36,
                    500
            ));
            objects.get(2).getChildObject().get(10).getChildObject().get(1).translateObject(0.0f, 0.65f, -1.1f);
            objects.get(2).getChildObject().get(10).getChildObject().get(1).translateObject(2.0f, 0.0f, 0.0f);
            objects.get(2).getChildObject().get(10).getChildObject().get(1).rotateObject(1.57f, 1.0f, 0.0f, 0.0f);
        }

        objects.add(new Kotak(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.4f, 0.4f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                7.0f,
                0.1f,
                7.0f,
                36,
                50
        ));
        objects.get(3).translateObject(2.0f, -0.21f, 0.0f);


        }



    public void input(){
        // Memutar objek-objek silinder sehingga terlihat seperti apigg
//        for (int i = 0; i < objects.get(1).getChildObject().get(4).getChildObject().size(); i++) {
//            objects.get(1).getChildObject().get(4).getChildObject().get(i).rotateObject(-1f, 0.0f, 0.0f, 1.0f);
//        }


       /* for (Object y : objects.get(1).getChildObject().get(4).getChildObject()) {
            List<Float> temp1 = new ArrayList<>(objects.get(1).getChildObject().get(4).getCenterPoint());
            y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);
            y.rotateObject( -1f, 0.0f, 0.0f, 1.0f);
            y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
        }*/


        // Roket Berputar
        /*for (int i = 0; i < objects.get(1).getChildObject().size(); i++) {
            objects.get(1).getChildObject().get(i).rotateObject(90f, 0.0f, 0.0f, 1.0f);
        }*/

        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(0.03f);
        }

        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(0.03f);
        }

        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(0.03f);
        }

        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(0.03f);
        }

        if (window.isKeyPressed(GLFW_KEY_Q)){
            camera.addRotation(0.0f, -0.01f);
        }

        if (window.isKeyPressed(GLFW_KEY_E)){
            camera.addRotation(0.0f, 0.01f);
        }

        if (window.isKeyPressed(GLFW_KEY_Z)) {
            camera.moveUp(0.03f);
        }

        if (window.isKeyPressed(GLFW_KEY_X)) {
            camera.moveDown(0.03f);
        }


        if(window.isKeyPressed(GLFW_KEY_SPACE)) {
            objects.get(1).translateObject(0.0f, 0.0125f, 0.0f);
        }

        // Roket Terbang
        if(window.isKeyPressed(GLFW_KEY_F)) {

            List<Float> temp1 = new ArrayList<>(objects.get(1).getCenterPoint());

            for (Object rocketChild : objects.get(1).getChildObject()) {
                rocketChild.translateObject(-temp1.get(0), -temp1.get(1), -temp1.get(2));
                rocketChild.rotateObject(0.025f, -1.0f, 0.0f, 0.0f);
                rocketChild.translateObject(temp1.get(0), temp1.get(1), temp1.get(2));
            }

        }

        // Roket meluncur
        if(window.isKeyPressed(GLFW_KEY_G)) {
            objects.get(1).translateObject(0.0f, 0.025f, -0.025f);
        }


        if (window.isKeyPressed(GLFW_KEY_U)) {
            countDegree++;
            for (Object y : objects.get(0).getChildObject().get(9).getChildObject()) {
                    List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(9).getCenterPoint());
                    y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                    if(countDegree >= 0){
                        countDegree = 0;
                    }
                    else
                    {y.rotateObject((float) Math.toRadians(0.5f), -1.0f, 0.0f, 0.0f);}

                    y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                    System.out.println(countDegree);
                }
            for (Object y : objects.get(1).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(9).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(countDegree >= 0){
                    countDegree = 0;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), -1.0f, 0.0f, 0.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(countDegree);
            }
        }


        if (window.isKeyPressed(GLFW_KEY_J)) {
            countDegree--;

            //limit the rotation

            for(Object y:objects.get(0).getChildObject().get(9).getChildObject()){

                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(9).getCenterPoint());
                y.translateObject(temp1.get(0)*-1,temp1.get(1)*-1,temp1.get(2)*-1);
                if(countDegree <= -180){
                    countDegree = -180;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f),1.0f,0.0f,-0.0f);}
                y.translateObject(temp1.get(0)*1,temp1.get(1)*1,temp1.get(2)*1);
                System.out.println(countDegree);
            }



            for(Object y:objects.get(1).getChildObject()){

                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(9).getCenterPoint());
                y.translateObject(temp1.get(0)*-1,temp1.get(1)*-1,temp1.get(2)*-1);
                if(countDegree <= -180){
                    countDegree = -180;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f),1.0f,0.0f,-0.0f);}
                y.translateObject(temp1.get(0)*1,temp1.get(1)*1,temp1.get(2)*1);
                System.out.println(countDegree);
            }

        }


        if (window.isKeyPressed(GLFW_KEY_I)) {
            hydraulic1--;
            hydraulic2--;

            for (Object y : objects.get(0).getChildObject().get(15).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(15).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic1 <= -170){
                    hydraulic1 = -170;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, 1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic1);
            }

            for (Object y : objects.get(0).getChildObject().get(16).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(16).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic2 <= -170){
                    hydraulic2 = -170;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, -1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic2);
            }

            for (Object y : objects.get(0).getChildObject().get(17).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(17).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic1 <= -170){
                    hydraulic1 = -170;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, -1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic1);
            }

            for (Object y : objects.get(0).getChildObject().get(18).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(18).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic2 <= -170){
                    hydraulic2 = -170;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, 1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic2);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_K)) {
            hydraulic1++;
            hydraulic2++;

            for (Object y : objects.get(0).getChildObject().get(15).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(15).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic1 >= 0){
                    hydraulic1 = 0;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, -1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic1);
            }

            for (Object y : objects.get(0).getChildObject().get(16).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(16).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic2 >= 0){
                    hydraulic2 = 0;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, 1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic2);
            }

            for (Object y : objects.get(0).getChildObject().get(17).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(17).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic1 >= 0){
                    hydraulic1 = 0;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, 1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic1);
            }

            for (Object y : objects.get(0).getChildObject().get(18).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(18).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic2 >= 0){
                    hydraulic2 = 0;
                }
                else
                {y.rotateObject((float) Math.toRadians(0.5f), 0.0f, 0.0f, -1.0f);}

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic2);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_O)) {
            hydraulic3--;
            for (Object y : objects.get(0).getChildObject().get(16).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(16).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 <= -45){
                    hydraulic3 = -45;
                }
                else
                {
                    y.translateObject(0f, -0.001f, 0f);
                    System.out.println(hydraulic3);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);
            }

            for (Object y : objects.get(0).getChildObject().get(15).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(15).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 <= -45){
                    hydraulic3 = -45;
                }
                else
                {
                    y.translateObject(0f, -0.001f, 0f);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);
            }

            for (Object y : objects.get(0).getChildObject().get(17).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(17).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 <= -45){
                    hydraulic3 = -45;
                }
                else
                {
                    y.translateObject(0f, -0.001f, 0f);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);
            }

            for (Object y : objects.get(0).getChildObject().get(18).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(18).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 <= -45){
                    hydraulic3 = -45;
                }
                else
                {
                    y.translateObject(0f, -0.001f, 0f);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);
            }
        }

        if (window.isKeyPressed(GLFW_KEY_L)) {
            hydraulic3++;
            for (Object y : objects.get(0).getChildObject().get(16).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(16).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 >= 0){
                    hydraulic3 = 0;
                }
                else
                {
                    y.translateObject(0f, 0.001f, 0f);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);
            }

            for (Object y : objects.get(0).getChildObject().get(15).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(15).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 >= 0){
                    hydraulic3 = 0;
                }
                else
                {

                    y.translateObject(0f, 0.001f, 0f);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);
            }

            for (Object y : objects.get(0).getChildObject().get(17).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(17).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 >= 0){
                    hydraulic3 = 0;
                }
                else
                {
                    y.translateObject(0f, 0.001f, 0f);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);
            }

            for (Object y : objects.get(0).getChildObject().get(18).getChildObject().get(0).getChildObject()) {
                List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(18).getChildObject().get(0).getCenterPoint());
                y.translateObject(temp1.get(0) * -1, temp1.get(1) * -1, temp1.get(2) * -1);

                if(hydraulic3 >= 0){
                    hydraulic3 = 0;
                }
                else
                {
                    y.translateObject(0f, 0.001f, 0f);
                }

                y.translateObject(temp1.get(0) * 1, temp1.get(1) * 1, temp1.get(2) * 1);
                System.out.println(hydraulic3);

            }
        }

        for(Object y:objects.get(2).getChildObject().get(10).getChildObject()){
            List<Float> temp1 = new ArrayList<>(objects.get(2).getChildObject().get(10).getCenterPoint());
            y.translateObject(temp1.get(0)*-1,temp1.get(1)*-1,temp1.get(2)*-1);
            y.rotateObject((float) Math.toRadians(0.5f),0.0f,2.0f,0.0f);
            y.translateObject(temp1.get(0)*1,temp1.get(1)*1,temp1.get(2)*1);
        }



    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.1f,
                    0.22f, 0.5f,
                    0.0f);
            GL.createCapabilities();
            input();

            //code
            for(Object object: objects){
                object.draw(camera, projection);
            }
//            for(Object object: objectsRectangle){
//                object.draw(camera, projection);
//            }
//            for(Object object: objectsPointsControl){
//                object.drawLine();
//            }

            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}