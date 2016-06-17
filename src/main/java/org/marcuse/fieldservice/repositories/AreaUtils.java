package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.views.AreaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielmarcuse on 03/06/16.
 */
@Component(value="areaUtils")
public class AreaUtils {

	@Autowired
	private AreaRepository areaRepository;

	@Transactional
	public List<AreaView> getAreasByAddress(Address address) {
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);

		List<Area> areas = address.getAreas();
		List<AreaView> displayAreas = new ArrayList<>();

		if (areas.size() > 0) {
			areas.forEach(area -> {
				AreaView displayArea = new AreaView();
				displayArea.setId(area.getId());
				displayArea.setNumber(area.getNumber());
				displayArea.setType(area.getType().name());
				displayArea.setCity(area.getCity());
				displayArea.setAssignments(null);
				displayAreas.add(displayArea);
			});
		}

		return displayAreas;
	}

	@Transactional
	public AreaView getAreaByAssignment(Assignment assignment) {
		Area area = assignment.getArea();

		if (area != null) {
			AreaView displayArea = new AreaView();
			displayArea.setId(area.getId());
			displayArea.setNumber(area.getNumber());
			displayArea.setType(area.getType().name());
			displayArea.setCity(area.getCity());
			displayArea.setAssignments(null);

			return displayArea;
		}
		else {
			return null;
		}
	}

}
