//Game3d


package com.lhw.sketchware.game1;

import android.app.*;
import android.content.*;
import android.os.*;


public class MainActivity extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		android.opengl.GLSurfaceView glview = new android.opengl.GLSurfaceView(this);
		glview.setRenderer(new MyRenderer(this));
		setContentView(glview);
    }
	
	private static class MyRenderer implements android.opengl.GLSurfaceView.Renderer
	{
		private Context context;
		
		private float vertices[] = {
			-1.0f,  1.0f, -1.0f,  
			-1.0f, -1.0f, -1.0f,  
			1.0f, -1.0f, -1.0f,  
			1.0f,  1.0f, -1.0f,  
		};
		private short[] indices = { 0, 1, 2, 0, 2, 3 };
		private java.nio.FloatBuffer vertexBuffer;
		private java.nio.ShortBuffer indexBuffer;
		private float angle = 0;
		
		public MyRenderer(Context context)
		{
			this.context = context;
			
			java.nio.ByteBuffer vbb = java.nio.ByteBuffer.allocateDirect(vertices.length * 4);
			vbb.order(java.nio.ByteOrder.nativeOrder());
			vertexBuffer = vbb.asFloatBuffer();
			vertexBuffer.put(vertices);
			vertexBuffer.position(0);
			
			java.nio.ByteBuffer ibb = java.nio.ByteBuffer.allocateDirect(indices.length * 2);
			ibb.order(java.nio.ByteOrder.nativeOrder());
			indexBuffer = ibb.asShortBuffer();
			indexBuffer.put(indices);
			indexBuffer.position(0);
		}
		
		@Override
		public void onSurfaceCreated(javax.microedition.khronos.opengles.GL10 gl, javax.microedition.khronos.egl.EGLConfig config)
		{
			gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
			// Enable Smooth Shading, default not really needed.
			gl.glShadeModel(gl.GL_SMOOTH);
			// Depth buffer setup.
			gl.glClearDepthf(1.0f);
			// Enables depth testing.
			gl.glEnable(gl.GL_DEPTH_TEST);
			// The type of depth testing to do.
			gl.glDepthFunc(gl.GL_LEQUAL);
			// Really nice perspective calculations.
			gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT,
					  gl.GL_NICEST);
		}
		
		
		public void draw(javax.microedition.khronos.opengles.GL10 gl) {
			gl.glFrontFace(gl.GL_CCW);
			gl.glEnable(gl.GL_CULL_FACE);
			gl.glCullFace(gl.GL_BACK);
			gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
			gl.glVertexPointer(3, gl.GL_FLOAT, 0,
							   vertexBuffer);
							   
			gl.glDrawElements(gl.GL_TRIANGLES, indices.length,
							  gl.GL_UNSIGNED_SHORT, indexBuffer);
			gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
			gl.glDisable(gl.GL_CULL_FACE);
		}
		
		@Override
		public void onSurfaceChanged(javax.microedition.khronos.opengles.GL10 gl, int w, int h)
		{
			// Sets the current view port to the new size.
			gl.glViewport(0, 0, w, h);
			// Select the projection matrix
			gl.glMatrixMode(gl.GL_PROJECTION);
			// Reset the projection matrix
			gl.glLoadIdentity();
			// Calculate the aspect ratio of the window
			android.opengl.GLU.gluPerspective(gl, 45.0f,
											  (float) w / (float) h,
											  0.1f, 100.0f);
			// Select the modelview matrix
			gl.glMatrixMode(gl.GL_MODELVIEW);
			// Reset the modelview matrix
			gl.glLoadIdentity();
		}
		
		@Override
		public void onDrawFrame(javax.microedition.khronos.opengles.GL10 gl)
		{
			// Clears the screen and depth buffer.
			gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
			// Replace the current matrix with the identity matrix
			gl.glLoadIdentity();
			// Translates 10 units into the screen.
			gl.glTranslatef(0, 0, -10);
			
			// SQUARE A
			// Save the current matrix.
			gl.glPushMatrix();
			// Rotate square A counter-clockwise.
			gl.glRotatef(angle, 0, 1, 1);
			// Draw square A.
			draw(gl);
			// Restore the last matrix.
			gl.glPopMatrix();
			
			// SQUARE B
			// Save the current matrix
			gl.glPushMatrix();
			// Rotate square B before moving it, making it rotate around A.
			gl.glRotatef(-angle, 0, 1, 1);
			// Move square B.
			gl.glTranslatef(2, 0, 0);
			// Scale it to 50% of square A
			gl.glScalef(.5f, .5f, .5f);
			// Draw square B.
			draw(gl);
			
			// SQUARE C
			// Save the current matrix
			gl.glPushMatrix();
			// Make the rotation around B
			gl.glRotatef(-angle, 0, 1, 1);
			gl.glTranslatef(2, 0, 0);
			// Scale it to 50% of square B
			gl.glScalef(.5f, .5f, .5f);
			// Rotate around it's own center.
			gl.glRotatef(angle*10, 0, 0, 1);
			// Draw square C.
			draw(gl);
			
			// Restore to the matrix as it was before C.
			gl.glPopMatrix();
			// Restore to the matrix as it was before B.
			gl.glPopMatrix();
			
			// Increse the angle.
			
			angle++;
			if(angle >= 360){
				angle = 0;
			}
		}
	}
}
