import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class art_things extends PApplet {

boolean random = true;
float r;
float[] rq1 = new float[3];
float[] rq2 = new float[3];

int[] AB = new int[2];
int[] AC = new int[2];
int[] AD = new int[2];
int[] nAnB = new int[2];
int[] nAnC = new int[2];
int[] nAnD = new int[2];



public void setup() {
  
  background(0);
  if (random) {
    r=0;
    while (r==0) {
      r = random(1);
    }
    AB[0]=round(r*100);
    r=0;
    while (r==0) {
      r = random(1);
    }
    
    AB[1]=-round(r*100);
  } else {
    AB[0] = 50;
    AB[1] = -25;
  }
  for (int i=0; i<3; i++) {
    rq1[i] = random(1);
    rq1[i]=rq1[i]*255;
  }
  for (int i=0; i<3; i++) {
    rq2[i] = random(1);
    rq2[i]=rq2[i]*255;
  }
  println("AB[0] = "+AB[0]);
  println("AB[1] = "+AB[1]);
  AC[0]=2*AB[0];
  AC[1]=0;
  AD[0]=AB[0];
  AD[1]=-AB[1];

  nAnB[0]=-AB[1];
  nAnB[1]=-AB[0];
  nAnC[0]=2*nAnB[0];
  nAnC[1]=0;
  nAnD[0]=AD[1];
  nAnD[1]=AD[0];
  int taille=1000;
  int[][] points = new int[4][2];
  int[] A = {0, abs(AB[1])};
  int[] B = {A[0]+AB[0], A[1]+AB[1]};
  int[] D = {A[0]+AD[0], A[1]+AD[1]};
  int[] C = {D[0]+AB[0], A[1]};
  points[0]=A;
  points[1]=B;
  points[2]=C;
  points[3]=D;
  //quadrilatere(points[0], points[1], points[2], points[3]);
  //motif(points);
  if (nAnB[0]>AB[0]) {
    A[0]=abs(nAnB[0]-AB[0]);
  } else {
    A[0]=0;
  }
  for (int y=0; y*(abs(AB[1])+abs(nAnB[1]))<height; y++) {
    for (int i=0; i*(AC[0]+nAnC[0])<width; i++) {
      q1(A, B, C, D);
      A[0]=A[0]+AC[0];
      q2(A, B, C, D);
      A[0]=A[0]+nAnC[0];
    }
    y++;
    if (AB[0]>nAnB[0]) {
      A[0]=abs(nAnB[0]-AB[0]);
    } else {
      A[0]=0;
    }
    A[1]=A[1]+abs(AB[1])+abs(nAnB[1]);
    for (int i=0; i*(AC[0]+nAnC[0])<width; i++) {
      q2(A, B, C, D);
      A[0]=A[0]+nAnC[0];
      q1(A, B, C, D);
      A[0]=A[0]+AC[0];
    }
    if (nAnB[0]>AB[0]) {
      A[0]=abs(nAnB[0]-AB[0]);
    } else {
      A[0]=0;
    }
    A[1]=A[1]+abs(AB[1])+abs(nAnB[1]);
  }
}



public void draw() {
  //for (int i=0; i<2; i++){
  //}
}


public void quadrilatere(int[] A, int[] B, int[] C, int[] D) {
  beginShape();
  //line(A[0], A[1], B[0], B[1]);
  //line(B[0], B[1], C[0], C[1]);
  //line(C[0], C[1], D[0], D[1]);
  //line(D[0], D[1], A[0], A[1]);
  vertex(A[0], A[1]);
  vertex(B[0], B[1]);
  vertex(C[0], C[1]);
  vertex(D[0], D[1]);
  endShape(CLOSE);
}



public void q1 (int[] A, int[] B, int[] C, int[] D) {
  fill(rq1[0], rq1[1], rq1[2]);
  //fill(40,140,170);
  B[0]=A[0]+AB[0];
  B[1]=A[1]+AB[1];

  C[0]=A[0]+AC[0];
  C[1]=A[1]+AC[1];

  D[0]=A[0]+AD[0];
  D[1]=A[1]+AD[1];

  quadrilatere(A, B, C, D);
}

public void q2 (int[] A, int[] B, int[] C, int[] D) {
  //fill(200,117,36);
  fill(rq2[0], rq2[1], rq2[2]);
  B[0]=A[0]+nAnB[0];
  B[1]=A[1]+nAnB[1];

  C[0]=A[0]+nAnC[0];
  C[1]=A[1]+nAnC[1];

  D[0]=A[0]+nAnD[0];
  D[1]=A[1]+nAnD[1];

  quadrilatere(A, B, C, D);
}






public void motif(int[][] points) {
  quadrilatere(points[0], points[1], points[2], points[3]);

  points[0][0]=points[2][0];
  points[0][1]=points[2][1];

  points[1][0]=points[0][0]+nAnB[0];
  points[1][1]=points[0][1]+nAnB[1];

  points[3][0]=points[0][0]+nAnD[0];
  points[3][1]=points[0][1]+nAnD[1];

  points[2][0]=points[3][0]+nAnB[0];
  points[2][1]=points[3][1]+nAnB[1];

  quadrilatere(points[0], points[1], points[2], points[3]);
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "art_things" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
