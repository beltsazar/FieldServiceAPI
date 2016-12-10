package org.marcuse.fieldservice.views;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.marcuse.fieldservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class AreaViewController {

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private CampaignRepository campaignRepository;

	@RequestMapping(path = "/api/areas/view", method = RequestMethod.GET)
	public List<AreaView> areaViewList(
			@RequestParam(value = "type", required = false) AreaType areaType,
			@RequestParam(value = "campaign", required = false) ArrayList<Long> campaign,
			@RequestParam(value = "creationDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime assignmentCreationDate,
			@RequestParam(value = "creationCloseDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime assignmentCreationCloseDate) {

		List<AreaView> areaViewList = new ArrayList<>();
		List<Area> areaList;
		final ArrayList<Campaign> selectedCampaigns = new ArrayList<>();
		final LocalDateTime creationDate = assignmentCreationDate;
		final LocalDateTime creationCloseDate = assignmentCreationCloseDate;



		if(areaType != null) {
			areaList = areaRepository.findByType(areaType);
		}
		else {
			areaList = areaRepository.findAll();
		}

		if(campaign != null) {
			for (Long campaignId : campaign) {
				selectedCampaigns.add(campaignRepository.findOne(campaignId));
			}
		}

		areaList.forEach(area -> {
			areaViewList.add(areaView(area, selectedCampaigns, creationDate, creationCloseDate));
		});

		sortAreas(areaViewList);

		return areaViewList;
	}

	private AreaView areaView (Area area, ArrayList<Campaign> campaigns, LocalDateTime creationDate, LocalDateTime creationCloseDate) {
		AreaView areaView = new AreaView();

		areaView.setId(area.getId());
		areaView.setNumber(area.getNumber());
		if(area.getType() != null) {
			areaView.setType(area.getType().name());
		}
		areaView.setShape(area.getShape());
		areaView.setCity(area.getCity());

		/**
		 * Use queryDsl for dynamic query
		 */
		QAssignment assignment = QAssignment.assignment;
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		/* At least the assignment should belong to the area */
		booleanBuilder.and(assignment.area.eq(area));

		/* If there are campaigns selected, add them to query */
		if (campaigns.size() > 0) {
			booleanBuilder.and(assignment.campaign.in(campaigns));
		}

		/* If start date is present, add it to query */
		if (creationDate != null) {
			booleanBuilder.and(
					assignment.creationDate.after(creationDate)
			);
		}

		/* If start date is present, add it to query */
		if (creationCloseDate != null) {
			booleanBuilder.and(
				assignment.creationDate.after(creationCloseDate).or(
				assignment.closeDate.after(creationCloseDate))
			);
		}

		ArrayList<Assignment> assignmentList = (ArrayList<Assignment>) assignmentRepository.findAll(booleanBuilder);

		/**
		 * Remove area object from assignment object
		 */
		assignmentList.forEach(assignmentItem -> {
			assignmentItem.setArea(null);
		});

		areaView.setAssignments(assignmentList);
		sortAssignments(areaView.getAssignments());
		return areaView;
	}

	/**
	 * Sort assignments: first active, second creation date (newest first)
	 * @param assignmentList
	 */
	private void sortAssignments(List<Assignment> assignmentList) {
		assignmentList.sort(new Comparator<Assignment>() {
			public int compare(Assignment v1, Assignment v2) {
				int compareResultActive;

				Boolean active1 = v1.isActive();
				Boolean active2 = v2.isActive();

				compareResultActive = active2.compareTo(active1);

				if (compareResultActive == 0) {
					LocalDateTime date1 = v1.getCreationDate();
					LocalDateTime date2 = v2.getCreationDate();

					return date2.compareTo(date1);
				}
				else {
					return compareResultActive;
				}

			}
		});
	}

	private void sortAreas(List<AreaView> areaViewList) {
		areaViewList.sort(new Comparator<AreaView>() {
			public int compare(AreaView v1, AreaView v2) {
				String type1 = v1.getType();
				String type2 = v2.getType();

				int compareResultType;

				if(type1 != null && type2 != null) {
					compareResultType = type1.compareTo(type2);
				}
				else {
					compareResultType = 0;
				}

				if (compareResultType == 0) {
					String city1 = v1.getCity().getName();
					String city2 = v2.getCity().getName();

					int compareResultCity = city1.compareTo(city2);

					if (compareResultCity == 0) {
						Integer number1 = v1.getNumber();
						Integer number2 = v2.getNumber();

						return number1.compareTo(number2);
					} else {
						return compareResultCity;
					}
				}
				else {
					return compareResultType;
				}

			}
		});
	}

}
