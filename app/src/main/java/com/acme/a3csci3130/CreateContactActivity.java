package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity for adding business contacts to the database
 */
public class CreateContactActivity extends Activity {

    private EditText businessNameTextField, businessNumberTextField, addressTextField;
    private Spinner businessTypeSpinner, provinceSpinner;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_activity);

        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        // get reference to the UI components
        businessNameTextField = (EditText) findViewById(R.id.businessNameTextField);
        businessNumberTextField = (EditText) findViewById(R.id.businessNumberTextField);
        addressTextField = (EditText) findViewById(R.id.addressTextField);
        businessTypeSpinner = (Spinner) findViewById(R.id.businessTypeSpinner);
        provinceSpinner = (Spinner) findViewById(R.id.provinceSpinner);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String databaseId = appState.firebaseReference.push().getKey();

        System.out.println("business type: " + businessTypeSpinner.getSelectedItem().toString() + "     province: " + provinceSpinner.getSelectedItem().toString());

        String businessName = businessNameTextField.getText().toString();
        String businessNumber = businessNumberTextField.getText().toString();
        String address = addressTextField.getText().toString();

        String selectedBusinessType = "", selectedProvince = "";

        if (!provinceSpinner.getSelectedItem().toString().equals(getString(R.string.spinner_default_province))){
            selectedProvince = provinceSpinner.getSelectedItem().toString();
        }

        if (!businessTypeSpinner.getSelectedItem().toString().equals(getString(R.string.spinner_default_business_type))){
            selectedBusinessType = businessTypeSpinner.getSelectedItem().toString();
        }

        Contact contact = new Contact(databaseId, businessNumber, businessName, selectedBusinessType, address, selectedProvince);

        Task<Void> dbtask = appState.firebaseReference.child(databaseId).setValue(contact);

        dbtask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                finish();
            }
        });

        dbtask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError();
            }
        });
    }

    private void showError(){
        Toast errorToast = Toast.makeText(this, "Contact did not match expected criteria", Toast.LENGTH_LONG);
        errorToast.show();
    }
}
