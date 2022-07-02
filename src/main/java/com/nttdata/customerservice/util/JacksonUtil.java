package com.nttdata.customerservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtil {

    private JacksonUtil() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static String objectToString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
