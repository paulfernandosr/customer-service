package com.nttdata.customerservice.model;

import com.nttdata.customerservice.util.Constants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder(toBuilder = true)
@Document(collection = Constants.BUSINESS_CUSTOMERS_COLLECTION)
public class BusinessCustomer {

    @Id
    private final String id;
    private final String name;
    private final String city;
    private final String address;

}
