#version 330

out vec4 fragColor;
uniform vec4 uni_color;

void main() {
    //fragColor = vec4(1.0, 0.0, 0.0, 1.0);
    fragColor = uni_color;
}