/* autogenerated by Processing revision 1279 on 2022-02-24 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import processing.serial.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class mallari_rgb extends PApplet {


Serial myPort;

float sliderWidth = 500;
float sliderHeight = 30;

float rX, rY;
float rWidth = 0;
int red = 0;
boolean overRed = false;

float gX, gY;
float gWidth = 0;
int green = 0;
boolean overGreen = false;

float bX, bY;
float bWidth = 0;
int blue = 0;
boolean overBlue = false;

byte[] rgb = new byte[3];

PFont f;

 public void setup() {
  /* size commented out by preprocessor */;

  String portName = Serial.list()[1];
  myPort = new Serial(this, portName, 9600);
  
  // SETS THE POSITIONS OF THE SLIDERS
  rX = width / 2 - 250;
  rY = height / 2 - 15;
  
  gX = rX;
  gY = height / 2 + 35;
  
  bX = rX;
  bY = height / 2 + 85;
  
  rectMode(CENTER);
  textAlign(CENTER);
  
  // TEXT
  f = createFont("Arial",16,true); // Arial, 16 point, anti-aliasing on
}

 public void draw() {
  background(213,214,219);
  update(mouseX, mouseY);
  noStroke();
  rectMode(CENTER);
  
  // color box
  fill(red, green, blue);
  rect(width / 2, height / 2 - 70, 80, 80);
  
  // container for red
  rectMode(CENTER);
  fill(0);
  rect(width / 2, height / 2, sliderWidth, sliderHeight);
  // slider for red
  rectMode(CORNER);
  fill(255, 0, 0);
  rect(rX, rY, rWidth, sliderHeight);
  
  // container for green
  rectMode(CENTER);
  fill(0);
  rect(width / 2, height / 2 + 50, sliderWidth, sliderHeight);
  // slider for green
  rectMode(CORNER);
  fill(0, 255, 0);
  rect(gX, gY, gWidth, sliderHeight);
  
  // container for blue
  rectMode(CENTER);
  fill(0);
  rect(width / 2, height / 2 + 100, sliderWidth, sliderHeight);
  // slider for blue
  rectMode(CORNER);
  fill(0, 0, 255);
  rect(bX, bY, bWidth, sliderHeight);
  
  // CHECKS WHICH SLIDER THE MOUSE IF OVER AND CHANGES
  // THE CORRESPONDING VALUE ACCORDINGLY
  if (mousePressed) {
    if (overRed) {
      rWidth = mouseX - 200;
      // 500 / 255 = 1.96
      red = floor(rWidth / 1.96f);
    } else if (overGreen) {
      gWidth = mouseX - 200;
      green = floor(gWidth / 1.96f);
    } else if (overBlue) {
      bWidth = mouseX - 200;
      blue = floor(bWidth / 1.96f);
    }
  }
  
  // TEXT
  textFont(f, 24);

  // LABELS FOR THE SLIDERS
  fill(255, 0, 0);
  text("R", 180, height / 2 + 10);
  fill(0, 128, 0);
  text("G", 180, height / 2 + 60);
  fill(0, 0, 200);
  text("B", 180, height / 2 + 110);

  // DISPLAY THE VALUE OF THE SLIDERS
  fill(255, 0, 0);
  text(red, width / 2 + 280, height / 2 + 10);
  fill(0, 128, 0);
  text(green, width / 2 + 280, height / 2 + 60);
  fill(0, 0, 200);
  text(blue, width / 2 + 280, height / 2 + 110);
  
  rgb[0] = PApplet.parseByte(red);
  rgb[1] = PApplet.parseByte(green);
  rgb[2] = PApplet.parseByte(blue);

  myPort.write(rgb);
}

 public void update(int x, int y) {
  if (overRect(rX, rY, sliderWidth, sliderHeight)) {
    overRed = true;
    overGreen = overBlue = false;
  }  else if (overRect(gX, gY, sliderWidth, sliderHeight)) {
    overGreen = true;
    overRed = overBlue = false;
  } else if (overRect(bX, bY, sliderWidth, sliderHeight)) {
    overBlue = true;
    overRed = overGreen = false;
  }
  else {
    overRed = overGreen = overBlue = false;
  }
}

 public boolean overRect(float x, float y, float width, float height)  {
  if (mouseX >= x && mouseX <= x + width && 
    mouseY >= y && mouseY <= y + height) {
    return true;
  } else {
    return false;
  }
}


  public void settings() { size(900, 400); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "mallari_rgb" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}