package com.celfocus.training.domain.frontend.user;

import com.celfocus.training.domain.user.User;

public interface UserFrontendRequest {
	public static final String DATE_FORMAT = "dd/mm/yyyy";

	public String getFrontendUser(User user) throws Exception;

}
