package com.geico.roadsideAssistance.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Assistant {

    @NonNull
    public Integer assistantID ;

    public String assistantName;

    public String streetName;

    public String phone;

    public String city;

    public String zip;

    public Boolean assigned;

    @NonNull
    public String latitude;

    @NonNull
    public String longitude;

}
