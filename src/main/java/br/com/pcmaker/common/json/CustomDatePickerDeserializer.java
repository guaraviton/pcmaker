package br.com.pcmaker.common.json;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.pcmaker.common.exception.GlobalException;
import br.com.pcmaker.common.util.DateUtils;
import br.com.pcmaker.common.util.StringUtils;

public class CustomDatePickerDeserializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try{
			String data = jp.getText();
			return StringUtils.isNotBlank(data) ? parse(data) : null;
		}catch (Exception e){
			throw new GlobalException(jp.getCurrentName(), e);
		}
	}

	private Date parse(String data) {
		if(data.length() == 10){
			return DateUtils.parse(data);
		}else{
			return DateUtils.parse(data, DateUtils.PATTERN_YYYY_MM_DD);
		}
	}
}
