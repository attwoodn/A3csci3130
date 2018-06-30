package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Activity for viewing, updating, and deleting business contact information
 */
public class DetailViewActivity extends Activity {

    private EditText businessNameTextField, addressTextField, businessNumberTextField;
    private Spinner businessTypeSpinner, provinceSpinner;
    private MyApplicationData appState;
    Contact receivedBusinessInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        receivedBusinessInfo = (Contact) getIntent().getSerializableExtra("Contact");

        businessNameTextField = (EditText) findViewById(R.id.businessNameTextField);
        businessNumberTextField = (EditText) findViewById(R.id.businessNumberTextField);
        businessTypeSpinner = (Spinner) findViewById(R.id.businessTypeSpinner);
        provinceSpinner = (Spinner) findViewById(R.id.provinceSpinner);
        addressTextField = (EditText) findViewById(R.id.addressTextField);

        if(receivedBusinessInfo != null){
            businessNameTextField.setText(receivedBusinessInfo.name);
            businessNumberTextField.setText(receivedBusinessInfo.businessId);
            businessTypeSpinner.setSelection(((ArrayAdapter)businessTypeSpinner.getAdapter()).getPosition(receivedBusinessInfo.businessType));
            provinceSpinner.setSelection(((ArrayAdapter)provinceSpinner.getAdapter()).getPosition(receivedBusinessInfo.province));
            addressTextField.setText(receivedBusinessInfo.address);
        }
    }

    public void updateContact(View v){
        //each entry needs a unique ID
        String databaseId = receivedBusinessInfo.databaseId;

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
        new DatabaseTaskConfigurator().configureDatabaseTask(dbtask, getString(R.string.database_contact_submission_error), this);
    }

    public void eraseContact(View v) {
        String databaseId = receivedBusinessInfo.databaseId;

        Task<Void> dbtask = appState.firebaseReference.child(databaseId).removeValue();

        new DatabaseTaskConfigurator().configureDatabaseTask(dbtask, getString(R.string.database_contact_submission_error), this);
    }
}
