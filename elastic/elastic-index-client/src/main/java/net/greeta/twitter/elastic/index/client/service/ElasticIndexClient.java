package net.greeta.twitter.elastic.index.client.service;

import net.greeta.twitter.elastic.model.index.IndexModel;

import java.util.List;

public interface ElasticIndexClient<T extends IndexModel> {
    List<String> save(List<T> documents);
}
