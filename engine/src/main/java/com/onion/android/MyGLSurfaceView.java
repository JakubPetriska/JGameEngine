package com.onion.android;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.onion.android.rendering.RendererImpl;
import com.onion.engine.Engine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLSurfaceView extends GLSurfaceView {

    private Engine mEngine;
    private TouchInputImpl mTouchInput;

    public MyGLSurfaceView(Context context) {
        super(context);
        mTouchInput = new TouchInputImpl();

        RendererImpl renderer = new EngineControllingRendererImpl();
        mEngine = new Engine(null, new AndroidPlatform(getContext()), renderer, mTouchInput);

        setEGLContextClientVersion(2);
        setRenderer(renderer);
    }

    public Engine getEngine() {
        return mEngine;
    }

    private class EngineControllingRendererImpl extends RendererImpl {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            super.onSurfaceCreated(gl, config);
            mEngine.onStart();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            super.onSurfaceChanged(gl, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            super.onDrawFrame(gl);
            mEngine.onUpdate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTouchInput.onTouchEvent(event);
        return true;
    }
}
