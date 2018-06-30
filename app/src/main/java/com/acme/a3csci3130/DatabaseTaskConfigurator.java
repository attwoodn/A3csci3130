package com.acme.a3csci3130;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Class that contains methods for configuring listeners for asynchronous database tasks
 */
public class DatabaseTaskConfigurator {

    /**
     * Configures listeners for an asynchronous database task. Finishes the current Activity upon success or shows an error upon failure
     *
     * @param databaseTask - The asynchronous database task to be configured
     *                     Ex: Task<Void> databaseTask = appState.firebaseReference.child(databaseId).setValue(contact);
     * @param toastTextUponError - The text to be put into the Toast if the databaseTask fails
     * @param callingClass - The Activity in which the error Toast will be displayed upon failure of the database task,
     *                     or that will be finished upon success of the database task
     */
    public void configureDatabaseTask(Task<Void> databaseTask, final String toastTextUponError, final Activity callingClass){
        databaseTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callingClass.finish();
            }
        });

        databaseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast errorToast = Toast.makeText(callingClass, toastTextUponError, Toast.LENGTH_LONG);
                errorToast.show();
            }
        });
    }

}
