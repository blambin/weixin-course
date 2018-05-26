package com.jiezh.pub.util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
/**
 * Jackson返回的日期格式为时间戳，不方便回显，此类帮助格式化
 */
public class JsonDateFormat  extends JsonSerializer<Date>{

    @Override
    public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jsonGenerator.writeString(sdf.format(value));
    }
}
