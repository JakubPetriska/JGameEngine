package com.jakubpetriska.gameengine.sample.android.examples;

import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.jakubpetriska.gameengine.api.android.GameEngineFragment;
import com.jakubpetriska.gameengine.sample.engine.SimpleMovementController;

import butterknife.InjectView;

public abstract class MovementControlsActivity extends FragmentActivity {

    @InjectView(com.jakubpetriska.gameengine.sample.R.id.controls_left)
    ImageButton controlsLeft;
    @InjectView(com.jakubpetriska.gameengine.sample.R.id.controls_forward)
    ImageButton controlsForward;
    @InjectView(com.jakubpetriska.gameengine.sample.R.id.controls_backward)
    ImageButton controlsBackward;
    @InjectView(com.jakubpetriska.gameengine.sample.R.id.controls_right)
    ImageButton controlsRight;

    private boolean mForwardPressed;
    private boolean mBackwardPressed;
    private boolean mLeftPressed;
    private boolean mRightPressed;

    protected abstract GameEngineFragment getGameEngineFragment();

    protected void setupMovementControls() {
        controlsForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mForwardPressed = true;
                        onVerticalMovementChanged();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mForwardPressed = false;
                        onVerticalMovementChanged();
                        break;
                }
                return false;
            }
        });
        controlsBackward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mBackwardPressed = true;
                        onVerticalMovementChanged();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mBackwardPressed = false;
                        onVerticalMovementChanged();
                        break;
                }
                return false;
            }
        });

        controlsLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mLeftPressed = true;
                        onHorizontalMovementChanged();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mLeftPressed = false;
                        onHorizontalMovementChanged();
                        break;
                }
                return false;
            }
        });
        controlsRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mRightPressed = true;
                        onHorizontalMovementChanged();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mRightPressed = false;
                        onHorizontalMovementChanged();
                        break;
                }
                return false;
            }
        });
    }

    private void onVerticalMovementChanged() {
        SimpleMovementController.MovementVertical movement;
        if ((mForwardPressed && mBackwardPressed)
                || (!mForwardPressed && !mBackwardPressed)) {
            movement = SimpleMovementController.MovementVertical.NONE;
        } else if (mForwardPressed) {
            movement = SimpleMovementController.MovementVertical.FORWARD;
        } else {
            movement = SimpleMovementController.MovementVertical.BACKWARD;
        }
        getGameEngineFragment().getMessenger().sendMessage(movement);
    }

    private void onHorizontalMovementChanged() {
        SimpleMovementController.MovementHorizontal movement;
        if ((mLeftPressed && mRightPressed)
                || (!mLeftPressed && !mRightPressed)) {
            movement = SimpleMovementController.MovementHorizontal.NONE;
        } else if (mLeftPressed) {
            movement = SimpleMovementController.MovementHorizontal.LEFT;
        } else {
            movement = SimpleMovementController.MovementHorizontal.RIGHT;
        }
        getGameEngineFragment().getMessenger().sendMessage(movement);
    }
}
