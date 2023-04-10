/**
 * HealthMate
 *
 * MyObserver Interface
 *
 * This interface defines the OnChange() method for observer classes in the HealthMate application.
 * Classes that implement this interface are able to react to changes in observable objects,
 * allowing for efficient and flexible communication between different components of the app.
 */
package com.example.healthmate;

public interface MyObserver {

    /**
     * OnChange method is called when there is a change in the observable object.
     * Classes implementing this interface should provide their own implementation
     * of this method to specify the actions to be performed upon receiving a change notification.
     */
    void OnChange();
}
