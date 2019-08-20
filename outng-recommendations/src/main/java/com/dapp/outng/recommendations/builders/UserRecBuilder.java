package com.dapp.outng.recommendations.builders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.dapp.outng.recommendations.models.userrec.UserElasticDoc;
import com.dapp.outng.recommendations.models.userrec.UserRecQuery;
import com.google.gson.Gson;

import software.amazon.awssdk.utils.StringUtils;

public class UserRecBuilder {

	public static BoolQueryBuilder buildQuery(UserRecQuery userRecQuery) {

		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		if (userRecQuery.getAgeRange() != null && userRecQuery.getAgeRange().get(0) != null
				&& userRecQuery.getAgeRange().get(1) != null) {
			qb.must(QueryBuilders.rangeQuery("age").from(userRecQuery.getAgeRange().get(0))
					.to(userRecQuery.getAgeRange().get(1)));
		}
		if (userRecQuery.getSituation() != null) {
			qb.must(QueryBuilders.termsQuery("situation", userRecQuery.getSituation()));
		}

		if (StringUtils.isNotBlank(userRecQuery.getLookingForGender())) {
			qb.must(QueryBuilders.matchQuery("gender", userRecQuery.getLookingForGender()));
		}

		if (userRecQuery.getSeenIds() != null) {
			String[] seenIds = userRecQuery.getSeenIds().toArray(new String[userRecQuery.getSeenIds().size()]);
			qb.mustNot(QueryBuilders.idsQuery().addIds(seenIds));
		}
		if (userRecQuery.getLocation() != null && StringUtils.isNotBlank(userRecQuery.getDistanceFilter())
				&& userRecQuery.getLocation().getLatitude() != null
				&& userRecQuery.getLocation().getLongitude() != null) {
			qb.filter(QueryBuilders.geoDistanceQuery("location").distance(userRecQuery.getDistanceFilter())
					.point(userRecQuery.getLocation().getLatitude(), userRecQuery.getLocation().getLongitude()));
		}
		
		return qb;

	}
	
	public static void buildUserRecResponse(SearchResponse searchResponse) {	
		List<SearchHit> searchHits = Arrays.asList(searchResponse.getHits().getHits());
		List<UserElasticDoc> returnList = searchHits.stream().map(h -> convert(h)).collect(Collectors.toList());		
	}

	private static UserElasticDoc convert(SearchHit hit) {
		Gson gson = new Gson();
		UserElasticDoc doc = gson.fromJson(hit.getSourceAsString(), UserElasticDoc.class);
//		doc.setUserId(hit.getId());
		return doc;
	}
	
		

}
