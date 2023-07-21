package com.geico.roadsideAssistance.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssistantLocation {


    @NonNull
    public Integer assistantID ;

    @NonNull
    public String latitude;

    @NonNull
    public String longitude;
}
