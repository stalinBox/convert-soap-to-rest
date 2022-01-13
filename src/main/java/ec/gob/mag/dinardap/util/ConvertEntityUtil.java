package ec.gob.mag.dinardap.util;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("convertEntityUtil")
public class ConvertEntityUtil {

	@Autowired
	@Qualifier("consumer")
	private Consumer consumer;

	public <T> T ConvertSingleEntity(String pathMicro, String auth, Class<T> clazz) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String jsonString = null;
		ObjectMapper mprObjecto = new ObjectMapper();
		mprObjecto.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		System.out.println("URL VALUE: " + pathMicro);
		Object responseEntity = consumer.doGet(pathMicro, auth);
		jsonString = mprObjecto.writeValueAsString(responseEntity);
		return mprObjecto.readValue(jsonString, clazz);
	}

	public <T> T ConvertListEntity(String pathMicro, String auth, Class<T> clazz) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String jsonString = null;
		ObjectMapper mprObjecto = new ObjectMapper();
		mprObjecto.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		System.out.println("URL VALUE: " + pathMicro);
		Object responseEntity = consumer.doGet(pathMicro, auth);
		jsonString = mprObjecto.writeValueAsString(responseEntity);
		return mprObjecto.readValue(jsonString, mprObjecto.getTypeFactory().constructCollectionType(List.class, clazz));
	}

}
