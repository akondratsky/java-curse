-- Revoke connections
REVOKE CONNECT ON DATABASE postgres FROM PUBLIC;

-- Terminate existing connections
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'your_database_name';

-- Drop schema
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;