SET search_path TO sibioskop;

EXPLAIN ANALYZE
SELECT * FROM bioskop
WHERE nama LIKE '%XXI';