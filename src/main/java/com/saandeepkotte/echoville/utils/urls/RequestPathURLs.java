package com.saandeepkotte.echoville.utils.urls;

public class RequestPathURLs {
    public static final String DEFAULT = "/";
    public static final String CREATE_ADMIN = "/createAdmin/{companyId}";
    public static final String FOR_COMPANY_ID = "/{companyId}";
    public static final String FOR_COMMUNITY = "/community/{communityId}";
    public static final String ASSIGN_HOUSE = "/{userId}/house/{houseId}";
    public static final String HOUSE_BILL = "/{houseId}/bill";
    public static final String LOGIN = "/login";
    public static final String BILL = "/{billId}";
    public static final String GET_HOUSE = "/{houseId}";
    public static final String USER_HOUSE = "/{userId}/house";
    public static final String USER_BILLS = "/{userId}/bills";
    public static final String USER_ISSUE = "/user/{userId}";
    public static final String UPDATE_ISSUE_STATUS = "/updateStatus";
}
