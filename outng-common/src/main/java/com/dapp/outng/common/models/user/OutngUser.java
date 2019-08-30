package com.dapp.outng.common.models.user;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OutngUser {
	@Id
	private String userId;
	private String name;
	private String birthDate;
	private String age;
	private String email;
	private Point location;
	private String partnerUserId;
	private String partnerUserType;
	private UserPreference preference;
	private UserDetail userDetail;
	private List<String> photos;


}
