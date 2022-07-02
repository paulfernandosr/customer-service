package com.nttdata.customerservice.model;

import com.nttdata.customerservice.util.Constants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder(toBuilder = true)
@Document(collection = Constants.PERSONAL_CUSTOMERS_COLLECTION)
public class PersonalCustomer {

    @Id
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String dni;

}
