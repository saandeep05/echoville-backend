package com.saandeepkotte.echoville.utils.urls;

public class RequestPathURLs {
    public static final String DEFAULT = "/";
    public static final String CREATE_ADMIN = "/createAdmin/{companyId}";
    public static final String FOR_COMPANY_ID = "/{companyId}";
    public static final String COMMUNITY_RESIDENTS = "/{communityId}/residents";
    public static final String COMMUNITY_HOUSES = "/{communityId}/houses";
    public static final String ASSIGN_HOUSE = "/{userId}/house/{houseId}";
    public static final String HOUSE_BILL = "/{houseId}/bill";
}
