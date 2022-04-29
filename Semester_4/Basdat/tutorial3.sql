--
-- Tutorial 3 Basis Data 2022
--

-- --------------------------------------------------------
-- Set search_path ke schema sibioskop
SET search_path TO sibioskop;

-- --------------------------------------------------------
-- I. View
-- -- Contoh 1
CREATE VIEW daftar_pengguna AS
SELECT email, username, password
FROM pengguna;

-- -- Contoh 2
SELECT * FROM daftar_pengguna;

SELECT email FROM daftar_pengguna
WHERE username LIKE 'a%';

-- -- Contoh 3
DROP VIEW daftar_pengguna;

-- II. Indexing
-- -- Contoh 4
CREATE INDEX index_username_pengguna
ON PENGGUNA(username);

-- -- Contoh 5
CREATE INDEX index_jabatan_admin
ON ADMIN USING HASH (jabatan);

-- -- Contoh 6
CREATE INDEX index_bioskop
ON BIOSKOP (id_bioskop, nama);

-- -- Contoh 7
CREATE INDEX index_customer_email
ON CUSTOMER(email);

CREATE INDEX index_customer_reward
ON CUSTOMER(reward_point);

-- -- Contoh 8
DROP INDEX index_username_pengguna;

-- III. Explain
-- -- Contoh 9
EXPLAIN ANALYZE
SELECT * FROM bioskop
WHERE nama LIKE '%XXI';
