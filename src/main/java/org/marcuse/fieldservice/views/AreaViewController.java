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
			@RequestParam(value = "campaign", required = false) Long campaign,
			@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime assignmentCreationDate) {

		List<AreaView> areaViewList = new ArrayList<>();
		List<Area> areaList;
		final Campaign selectedCampaign;
		final LocalDateTime creationDate = assignmentCreationDate;

		if(areaType != null) {
			areaList = areaRepository.findByType(areaType);
		}
		else {
			areaList = areaRepository.findAll();
		}

		if(campaign != null) {
			selectedCampaign = campaignRepository.findOne(campaign);
		}
		else {
			selectedCampaign = null;
		}

		areaList.forEach(area -> {
			areaViewList.add(areaView(area, selectedCampaign, creationDate));
		});

		sortAreas(areaViewList);

		return areaViewList;
	}

	private AreaView areaView (Area area, Campaign campaign, LocalDateTime creationDate) {
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

		if (campaign != null) {
			booleanBuilder.and(
					assignment.area.eq(area)).and(
					assignment.campaign.eq(campaign));
		}
		else if (creationDate != null) {
			booleanBuilder.and(
					assignment.area.eq(area)).and(
					assignment.creationDate.after(creationDate).or(
					assignment.closeDate.after(creationDate))
			);
		}
		else {
			booleanBuilder.and(assignment.area.eq(area));
		}

		areaView.setAssignments((ArrayList<Assignment>) assignmentRepository.findAll(booleanBuilder));

		sortAssignments(areaView.getAssignments());

		return areaView;
	}

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
