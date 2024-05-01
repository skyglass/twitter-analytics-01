package net.greeta.twitter.analytics.business;

import net.greeta.twitter.analytics.model.AnalyticsResponseModel;

import java.util.Optional;

public interface AnalyticsService {

    Optional<AnalyticsResponseModel> getWordAnalytics(String word);
}

