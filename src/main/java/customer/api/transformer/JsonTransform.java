package customer.api.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.ResponseTransformer;

public class JsonTransform implements ResponseTransformer{

	@Override
	public String render(Object model) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(model);
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	}
	
}
