create database demomatheus;

-- Performance Tuning
ALTER SYSTEM SET max_connections = 1500;
ALTER SYSTEM SET work_mem = 16384;
ALTER SYSTEM SET effective_cache_size = 6291456;
ALTER SYSTEM SET maintenance_work_mem = 2097152;
