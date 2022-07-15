package com.nttdata.customerservice.model;

import com.nttdata.customerservice.util.Constants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder(toBuilder = true)
@Document(collection = Constants.CUSTOMERS_COLLECTION)
public class Customer {

    @Id
    private final String id;

    private final String firstName;
    private final String lastName;
    private final String dni;

    private final String name;
    private final String city;
    private final String address;

    private final String type;

}
