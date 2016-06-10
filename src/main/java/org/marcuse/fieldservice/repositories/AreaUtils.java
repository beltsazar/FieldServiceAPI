package org.marcuse.fieldservice.repositories;

import org.marcuse.fieldservice.views.WorksheetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by danielmarcuse on 03/06/16.
 */
@Component(value="areaUtils")
public class AreaUtils {
/*
	@Autowired
	private CampaignRepository campaignRepository;

	public List<Assignment> filterAssignments(Area area) {
		List<Assignment> assignments = area.getAssignments();

		List<Campaign> activeCampaigns = campaignRepository.findByActive(true);
		if(activeCampaigns.size() > 0) {
			Campaign activeCampaign = activeCampaigns.get(0);

			ListIterator<Assignment> assignmentListIterator = assignments.listIterator();

			while(assignmentListIterator.hasNext()) {
				Assignment assignment = assignmentListIterator.next();
				if (!activeCampaign.equals(assignment.getCampaign())) {
					assignmentListIterator.remove();
				}
			}
		}

		assignments.sort(new Comparator<Assignment>() {
			public int compare(Assignment v1, Assignment v2) {
				return v2.getCreationDate().compareTo(v1.getCreationDate());
			}
		});

		return assignments;
	}
	*/


}
