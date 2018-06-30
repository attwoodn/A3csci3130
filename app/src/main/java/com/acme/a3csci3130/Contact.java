package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public String databaseId;
    public String businessId;
    public String name;
    public String businessType;
    public String address;
    public String province;


    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String databaseId, String businessId, String name, String businessType){
        this(databaseId, businessId, name, businessType, "", "");
    }

    public Contact(String databaseId, String businessId, String name, String businessType, String address, String province){
        this.databaseId = databaseId;
        this.businessId = businessId;
        this.name = name;
        this.address = address;
        this.businessType = businessType;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("businessId", businessId);
        result.put("name", name);
        result.put("address", address);
        result.put("province", province);
        result.put("businessType", businessType);

        return result;
    }
}
