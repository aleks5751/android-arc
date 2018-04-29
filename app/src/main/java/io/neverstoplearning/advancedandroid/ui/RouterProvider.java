package io.neverstoplearning.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * To be implemented by the Activity that initializes a Router. This is used in
 * {@link ScreenNavigator} implementations.
 */
public interface RouterProvider {

    Router getRouter();

    Controller initialScreen();
}
