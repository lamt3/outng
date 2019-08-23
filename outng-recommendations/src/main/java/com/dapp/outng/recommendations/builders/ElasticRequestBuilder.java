package com.dapp.outng.recommendations.builders;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentType;

import com.dapp.outng.common.models.user.Location;
import com.dapp.outng.common.models.user.OutngUser;
import com.dapp.outng.common.models.user.UserDetail;
import com.dapp.outng.recommendations.models.userrec.UserElasticDoc;
import com.dapp.outng.recommendations.models.userrec.UserElasticDoc.SearchLocation;
import com.google.gson.Gson;

public class ElasticRequestBuilder {

	public static IndexRequest buildUserIndexRequest(OutngUser outngUser) {
		
		IndexRequest request = new IndexRequest("user_test3"); 
		request.id(outngUser.getUserId());
		UserElasticDoc userDoc = buildUserElasticDoc(outngUser);
		
		Gson gson = new Gson();
		String userPayload = gson.toJson(userDoc);
		request.source(userPayload, XContentType.JSON);
		
		return request;
		
	}
	
	public static UpdateRequest buildUserUpdateRequest(OutngUser outngUser) {
		
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index("user_test3");
		updateRequest.id(outngUser.getUserId());
		
		UserElasticDoc userDoc = buildUserElasticDoc(outngUser);
		
		Gson gson = new Gson();
		String userPayload = gson.toJson(userDoc);
		updateRequest.doc(XContentType.JSON, userPayload);
		
		return updateRequest;
		
	}
	
	private static UserElasticDoc buildUserElasticDoc(OutngUser outngUser){
		
		Optional<UserDetail> uOp = Optional.of(outngUser.getUserDetail());
		
		SearchLocation searchLoc = new SearchLocation(Optional.of(outngUser.getLocation()).map(Location::getLatitude).orElse(null), 
				Optional.of(outngUser.getLocation()).map(Location::getLongitude).orElse(null));
		return  new UserElasticDoc(outngUser.getUserId(), outngUser.getName(), 
				uOp.map(UserDetail::getGender).orElse(null), 
				StringUtils.isBlank(outngUser.getAge()) ? null : Integer.valueOf(outngUser.getAge()), 
				uOp.map(UserDetail::getSituation).orElse(null), 
				uOp.map(UserDetail::getInterests).orElse(null), 
				uOp.map(UserDetail::getHeight).orElse(null), 
				uOp.map(UserDetail::getWeight).orElse(null), 
				uOp.map(UserDetail::getOccupation).orElse(null), 
				uOp.map(UserDetail::getSchool).orElse(null), 
				uOp.map(UserDetail::getReligion).orElse(null), 
				uOp.map(UserDetail::getQuirks).orElse(null),
				uOp.map(UserDetail::getSmoking).orElse(null), 
				uOp.map(UserDetail::getDrinking).orElse(null), 
				uOp.map(UserDetail::getEthnicity).orElse(null),
				uOp.map(UserDetail::getBio).orElse(null), 
				outngUser.getPhotos(), searchLoc);
	}
	
}
