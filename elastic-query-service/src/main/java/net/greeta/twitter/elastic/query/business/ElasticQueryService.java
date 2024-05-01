package net.greeta.twitter.elastic.query.business;

import net.greeta.twitter.elastic.query.common.model.ElasticQueryServiceResponseModel;
import net.greeta.twitter.elastic.query.model.ElasticQueryServiceAnalyticsResponseModel;

import java.util.List;

public interface ElasticQueryService {

    ElasticQueryServiceResponseModel getDocumentById(String id);

    ElasticQueryServiceAnalyticsResponseModel getDocumentByText(String text, String accessToken);

    List<ElasticQueryServiceResponseModel> getAllDocuments();
}
