package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CityShapeDeserializer extends JsonDeserializer<String>
{
	@Override
	public String deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {
		JsonNode node = jsonparser.getCodec().readTree(jsonparser);

		return node.toString();
	}
}