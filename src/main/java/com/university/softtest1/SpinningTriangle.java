package com.university.softtest1;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class SpinningTriangle {

    private long window;
    private double lastX, lastY;
    private float angleX = 0.0f, angleY = 0.0f;
    private boolean isDragging = false;

    public static void main(String[] args) {
        new SpinningTriangle().run();
    }

    public void run() {
        init();
        loop();

        // Cleanup
        Callbacks.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create the window
        window = GLFW.glfwCreateWindow(800, 600, "Interactive 3D Pyramid", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        // Set up viewport and perspective projection
        GL11.glViewport(0, 0, 800, 600);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glFrustum(-1.0, 1.0, -0.75, 0.75, 1.0, 10.0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // Set up mouse callbacks
        GLFW.glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
            if (isDragging) {
                float dx = (float) (xpos - lastX);
                float dy = (float) (ypos - lastY);
                angleX += dy * 0.3f;
                angleY += dx * 0.3f;
            }
            lastX = xpos;
            lastY = ypos;
        });

        GLFW.glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                isDragging = (action == GLFW.GLFW_PRESS);
            }
        });

        // Initialize last mouse position
        lastX = 800 / 2.0;
        lastY = 600 / 2.0;
    }

    private void loop() {
        // Run the rendering loop until the user attempts to close the window
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Clear the framebuffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            // Reset the view matrix
            GL11.glLoadIdentity();

            // Move the camera back a bit
            GL11.glTranslatef(0.0f, 0.0f, -4.0f);

            // Apply rotation based on user input
            GL11.glRotatef(angleX, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(angleY, 0.0f, 1.0f, 0.0f);

            // Draw the 3D pyramid
            drawPyramid();

            // Swap the color buffers
            GLFW.glfwSwapBuffers(window);

            // Poll for window events
            GLFW.glfwPollEvents();
        }
    }

    private void drawPyramid() {
        // Draw the pyramid with 4 triangular faces
        GL11.glBegin(GL11.GL_TRIANGLES);

        // Front face (Red)
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 1.0f, 0.0f); // Top
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom left
        GL11.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom right

        // Right face (Green)
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex3f(0.0f, 1.0f, 0.0f); // Top
        GL11.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom left
        GL11.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom right

        // Back face (Blue)
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(0.0f, 1.0f, 0.0f); // Top
        GL11.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom left
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom right

        // Left face (Yellow)
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
        GL11.glVertex3f(0.0f, 1.0f, 0.0f); // Top
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom left
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom right

        GL11.glEnd();

        // Draw the base as a square (optional for a closed pyramid base)
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(0.5f, 0.5f, 0.5f); // Gray base
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glEnd();
    }
}