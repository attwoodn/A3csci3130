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

    public int businessId;
    public String name;
    public BusinessType businessType;
    public String address;
    public Province province;


    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(int businessId, String name, BusinessType businessType){
        this(businessId, name, businessType, "", Province.NULL);
    }

    public Contact(int businessId, String name, BusinessType businessType, String address, Province province){
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
        result.put("province", province.getAbbreviation());
        result.put("businessType", businessType.name());

        return result;
    }
}
