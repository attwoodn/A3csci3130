package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText businessNameTextField, addressTextField, businessNumberTextField;
    private Spinner businessTypeSpinner, provinceSpinner;
    Contact receivedBusinessInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
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
        //TODO: Update contact funcionality
    }

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
    }
}
