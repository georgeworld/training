package com.georgeinfo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSON {
    private static final Logger LOG = LoggerFactory.getLogger(JSON.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJSONString(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (Exception ex) {
            LOG.error("序列化对象时出现异常", ex);
            return null;
        }
    }
}
