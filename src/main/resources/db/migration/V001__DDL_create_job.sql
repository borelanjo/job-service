CREATE SCHEMA IF NOT EXISTS job;
create table job.t_job (
    id BIGSERIAL PRIMARY KEY,
    code UUID NOT NULL,
    type VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    description VARCHAR,
    created_at TIMESTAMP NOT NULL,
    started_at TIMESTAMP,
    ended_at TIMESTAMP
);