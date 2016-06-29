package org.marcuse.fieldservice.repositories;

import org.marcuse.fieldservice.views.AddressView;
import org.marcuse.fieldservice.views.AreaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielmarcuse on 03/06/16.
 */
@Component(value="addressUtils")
public class AddressUtils {

	public AddressView getAddressByAnnotation(AddressAnnotation addressAnnotation) {
		AddressView addressView = new AddressView();
		Address address = addressAnnotation.getAddress();

		if(address != null) {

			addressView.setId(address.getId());
			addressView.setNumber(address.getNumber());
			addressView.setSuffix(address.getSuffix());
			addressView.setStreet(address.getStreet().getName());
			addressView.setCity(address.getCity().getName());

			return addressView;
		}
		else {
			return null;
		}

	}

}
