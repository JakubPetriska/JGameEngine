package com.jakubpetriska.gameengine.sample.engine;

import com.jakubpetriska.gameengine.api.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link Component} switching between scenes as a reaction on the incoming message.
 */
public class SceneSwitchingController extends Component {

    public static final String SWITCH_SCENES_MESSAGE = "SWITCH_SCENES";

    private static final String ONE_CUBE_SCENE = "one_cube_scene";
    private static final String TWO_CUBES_SCENE = "two_cubes_scene";

    private List<String> mMessageList = new ArrayList<>();

    @Override
    public void update() {
        getApplication().getMessenger().getMessages(String.class, mMessageList);
        for(String message : mMessageList) {
            if(SWITCH_SCENES_MESSAGE.equals(message)) {
                switchScenes();
                break;
            }
        }
        mMessageList.clear();
    }

    private void switchScenes() {
        String newSceneName;
        if(ONE_CUBE_SCENE.equals(getApplication().getCurrentSceneName())) {
            newSceneName = TWO_CUBES_SCENE;
        } else {
            newSceneName = ONE_CUBE_SCENE;
        }
        getApplication().changeScene(newSceneName);
    }
}
