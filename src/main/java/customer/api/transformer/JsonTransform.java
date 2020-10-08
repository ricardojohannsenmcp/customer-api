package customer.api.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.ResponseTransformer;

public class JsonTransform implements ResponseTransformer{

	@Override
	public String render(Object model) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(model);
	}
	
	
	

}
