package com.geico.roadsideAssistance.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @NonNull
    public Integer customerID;

    public String customerName;

    public String streetName;

    public String phone;

    public String city;

    @NonNull
    public String zip;

    public String latitude;

    public String longitude;


}
