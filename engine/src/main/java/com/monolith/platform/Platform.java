package com.monolith.platform;

import java.io.InputStream;

/**
 * Created by Jakub Petriska on 3. 1. 2015.
 */
public interface Platform {

    /**
     * Opens InputStreams to project files.
     *
     * In case of any exception this method must return null so
     * caller can deal with problems.
     * @param path Path to the file relative to platform's specific project files folder.
     */
    InputStream getAssetFileInputStream(String path);

    void log(String message);
}
