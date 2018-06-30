package com.acme.a3csci3130;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class for holding the application data (references, instances, etc.)
 */
public class MyApplicationData extends Application {

    public DatabaseReference firebaseReference;
    public FirebaseDatabase firebaseDBInstance;

}
