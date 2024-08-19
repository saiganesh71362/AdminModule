package com.acruent.admin.appconstants;

public class AdminModuleAppConstants {

	// Private constructor to prevent instantiation
	private AdminModuleAppConstants() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static final String EMPTY_STRING = "";

	// CONTROLLER CLASS CREATE CATEGORY

	public static final String REQUEST_SEND_NEW_CATEGORY = "Request received to create a new category: {}";
	public static final String CATEGORY_CREATED = "Category Created Successfully: {}";
	public static final String CATEGORY_CREATION_FAILD = "Failed To Create Category.";

	// CONTROLLER CLASS FIND BY ID

	public static final String REQUEST_GET_CATEGORY_BYID = "Received request to get Category by ID: {}";

	public static final String SUCCESSFULLY_RETRIVE_RECORDES = "Successfully retrieved Category with ID: {}";

	// RETRIVE
	public static final String GET_ALL_CATEGORIES = "Received request to get all categories";

	public static final String GET_SUCCESSFULLY_RETRIVED = "Successfully retrieved {} categories";

	public static final String NO_RECORD_FOUND = "No categories found";

	// UPDATE

	public static final String RECIVED_UPDATE_REQUEST = "Received request to update Category with ID: {}";
}
