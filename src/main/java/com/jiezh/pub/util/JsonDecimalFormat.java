package com.jiezh.pub.util;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * json数字的格式化
 */
public class JsonDecimalFormat extends JsonSerializer<BigDecimal> {

    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
        String result = "";
        if(value != null){
            result = decimalFormat.format(value.doubleValue());
        }
        jsonGenerator.writeString(result);
    }
}
