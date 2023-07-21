package com.geico.roadsideAssistance.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Geolocation {


    private String countryName;
    private String city;
    private String zipCode;
    private String latitude;
    private String longitude;

}
