package com.dapp.outng.common.models.user;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@Document(collection = "Seen_Users")
public class SeenUsers {
	@Id
	private String userId;
	private List<String> seenUserIds;
}
