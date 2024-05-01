package net.greeta.twitter.analytics.transformer;

import net.greeta.twitter.analytics.config.AvroIdGenerator;
import net.greeta.twitter.analytics.dataaccess.entity.AnalyticsEntity;
import net.greeta.twitter.kafka.avro.model.TwitterAnalyticsAvroModel;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class AvroToDbEntityModelTransformer {

    private final AvroIdGenerator avroIdGenerator;

    public AvroToDbEntityModelTransformer(AvroIdGenerator avroIdGenerator) {
        this.avroIdGenerator = avroIdGenerator;
    }

    public List<AnalyticsEntity> getEntityModel(List<TwitterAnalyticsAvroModel> avroModels) {
        return avroModels.stream()
                .map(avroModel -> new AnalyticsEntity(
                        avroIdGenerator.generateId()
                        , avroModel.getWord()
                        , avroModel.getWordCount()
                        , LocalDateTime.ofInstant(Instant.ofEpochSecond(avroModel.getCreatedAt()), ZoneOffset.UTC)))
                .collect(toList());
    }


}
