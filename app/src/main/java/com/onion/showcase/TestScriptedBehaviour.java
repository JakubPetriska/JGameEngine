package com.onion.showcase;

import com.onion.api.Component;
import com.onion.api.Core;
import com.onion.api.GameObject;
import com.onion.api.Touch;

import java.util.List;

public class TestScriptedBehaviour extends Component {

    private static final float FACTOR = 0.005f;

    public TestScriptedBehaviour(Core core, GameObject owner) {
        super(core, owner);
    }

    private float mLastTouchX = -1;
    private float mLastTouchY = -1;

    @Override
    public void update() {
        List<Touch> touches = core.touchInput.getTouches();
        if(touches.size() > 0) {
            Touch touch = touches.get(0);
            float currentTouchX = touch.getX();
            float currentTouchY = touch.getY();

            if(mLastTouchX != -1 && mLastTouchY != -1) {
                gameObject.transform.position.x -= (currentTouchX - mLastTouchX) * FACTOR;
                gameObject.transform.position.y -= (currentTouchY - mLastTouchY) * FACTOR;
            }

            if(touch.getState() == Touch.STATE_ENDED) {
                mLastTouchX = -1;
                mLastTouchY = -1;
            } else {
                mLastTouchX = currentTouchX;
                mLastTouchY = currentTouchY;
            }
        }
    }

}