package com.geico.roadsideAssistance.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geico.roadsideAssistance.pojo.Assistant;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class ApplicationUtil {

    public static List<Assistant> assistantlst = null;

    static {

        ObjectMapper mapper = new ObjectMapper();

        try {
            URL url = ApplicationUtil.class.getResource("../../../../assistant.json");
            File file = new File(url.getPath());
            assistantlst = Arrays.asList(mapper.readValue(file, Assistant[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static List<Assistant> getAssistant() {
        return assistantlst;

    }

    public static Map<Integer, Assistant> getAssistantMap() {

        Map<Integer, Assistant> assistantMap = new HashMap<>();
        ApplicationUtil applicationUtil = new ApplicationUtil();
        List<Assistant> assistantlst = getAssistant();

        for (Assistant assistant : assistantlst) {
            assistantMap.put(assistant.getAssistantID(), assistant);
        }

        return assistantMap;

    }


}
