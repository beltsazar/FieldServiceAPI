package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

@Entity
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private int number;

	@Lob
	@JsonRawValue
	@JsonDeserialize(using = ShapeDeserializer.class)
	@Getter
	@Setter
	private String shape;

	@Getter
	@ManyToOne
	private City city;

	@OneToMany(mappedBy = "area")
	private List<Address> addresses;

	@OneToMany(mappedBy = "area")
	private List<Assignment> assignments;

}

class ShapeDeserializer extends JsonDeserializer<String>
{
	@Override
	public String deserialize(JsonParser jsonparser,
							  DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

		JsonNode node = jsonparser.getCodec().readTree(jsonparser);
		return node.toString();
	}

}
