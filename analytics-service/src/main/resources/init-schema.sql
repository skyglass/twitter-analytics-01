DROP SCHEMA IF EXISTS analytics CASCADE;

CREATE SCHEMA analytics;

DROP TABLE IF EXISTS analytics.twitter_analytics CASCADE;

CREATE TABLE analytics.twitter_analytics
(
    id uuid NOT NULL,
    word character varying COLLATE pg_catalog."default" NOT NULL,
    word_count bigint NOT NULL,
    record_date TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT twitter_analytics_pkey PRIMARY KEY (id)
);