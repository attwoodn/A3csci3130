package com.acme.a3csci3130;

public enum Province {
    ALBERTA("AB"),
    BRITISH_COLUMBIA("BC"),
    MANITOBA("MB"),
    NEW_BRUNSWICK("NB"),
    NEWFOUNDLAND_AND_LABRADOR("NL"),
    NOVA_SCOTIA("NS"),
    NORTH_WEST_TERRITORIES("NT"),
    NUNAVUT("NT"),
    ONTARIO("ON"),
    PRINCE_EDWARD_ISLAND("PE"),
    QUEBEC("QC"),
    SASKATCHEWAN("SK"),
    YUKON("YT"),
    NULL("");

    String abbreviation;

    Province(String abbreviation){
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
