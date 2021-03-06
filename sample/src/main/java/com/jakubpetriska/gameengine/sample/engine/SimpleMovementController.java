package com.jakubpetriska.gameengine.sample.engine;

import com.jakubpetriska.gameengine.api.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows controlling {@link Component}'s movement. Movement is controlled
 * by messages that must be sent into the engine.
 */
public class SimpleMovementController extends Component {

    private static final float MOVEMENT_PER_SECOND = 5;

    public enum MovementVertical {
        FORWARD(1), BACKWARD(-1), NONE(0);

        private float movementMultiplier;

        MovementVertical(float movementMultiplier) {
            this.movementMultiplier = movementMultiplier;
        }

        public float getMovementMultiplier() {
            return movementMultiplier;
        }
    }

    public enum MovementHorizontal {
        LEFT(-1), RIGHT(1), NONE(0);

        private float movementMultiplier;

        MovementHorizontal(float movementMultiplier) {
            this.movementMultiplier = movementMultiplier;
        }

        public float getMovementMultiplier() {
            return movementMultiplier;
        }
    }

    private MovementVertical movementVertical = MovementVertical.NONE;
    private MovementHorizontal movementHorizontal = MovementHorizontal.NONE;

    private List<MovementVertical> verticalMovementsList = new ArrayList<>();
    private List<MovementHorizontal> horizontalMovementsList = new ArrayList<>();

    @Override
    public void update() {
        getApplication().getMessenger().getMessages(MovementVertical.class, verticalMovementsList);
        if(verticalMovementsList.size() > 0) {
            movementVertical = verticalMovementsList.get(verticalMovementsList.size() - 1);
            verticalMovementsList.clear();
        }

        getApplication().getMessenger().getMessages(MovementHorizontal.class, horizontalMovementsList);
        if(horizontalMovementsList.size() > 0) {
            movementHorizontal = horizontalMovementsList.get(horizontalMovementsList.size() - 1);
            horizontalMovementsList.clear();
        }

        getGameObject().transform.translateBy(
                MOVEMENT_PER_SECOND * movementHorizontal.getMovementMultiplier() * getApplication().getTime().getTimeDelta(),
                0,
                MOVEMENT_PER_SECOND * movementVertical.getMovementMultiplier() * getApplication().getTime().getTimeDelta()
        );
    }
}
