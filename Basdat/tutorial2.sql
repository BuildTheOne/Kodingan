--
-- Tutorial 2 Basis Data 2022
--

-- --------------------------------------------------------
-- Set search_path ke schema sibioskop
SET search_path TO sibioskop;


-- --------------------------------------------------------
-- 1. Basic SQL

-- -- Contoh 1: Penggunaan WHERE
SELECT alamat
FROM BIOSKOP
WHERE nama = 'Kalibata XXI';


-- -- Contoh 2: Penggunaan ORDER BY
SELECT judul, durasi, genre
FROM FILM
ORDER BY rating DESC;


-- -- Contoh 3: Penggunaan operasi aritmatika dasar
SELECT email
FROM CUSTOMER
WHERE (reward_point >= 20) AND (reward_point <= 70);

SELECT email
FROM CUSTOMER
WHERE (reward_point BETWEEN 20 AND 70);


-- -- Contoh 4: Penggunaan wildcards
SELECT *
FROM BIOSKOP
WHERE nama LIKE '%Premiere';


-- -- Contoh 5-7: Penggunaan operasi set pada database
(SELECT *
FROM TRANSAKSI_PEMBELIAN
WHERE id_customer = 'rahel_venca@bazdat.com')
UNION
(SELECT *
FROM TRANSAKSI_PEMBELIAN
WHERE total_harga >= 50000);

(SELECT *
FROM TRANSAKSI_PEMBELIAN
WHERE id_customer = 'rahel_venca@bazdat.com')
INTERSECT
(SELECT *
FROM TRANSAKSI_PEMBELIAN
WHERE total_harga >= 50000);

(SELECT *
FROM TRANSAKSI_PEMBELIAN
WHERE id_customer = 'rahel_venca@bazdat.com')
EXCEPT
(SELECT *
FROM TRANSAKSI_PEMBELIAN
WHERE total_harga >= 50000);


-- -- Contoh 8: Penggunaan DISTINCT
SELECT DISTINCT id_customer
FROM TRANSAKSI_PEMBELIAN
WHERE total_harga <= 60000;


-- -- Contoh 9: Penggunaan EXTRACT
SELECT DISTINCT nama
FROM BIOSKOP
WHERE EXTRACT(HOUR FROM jam_tutup)='20';


-- --------------------------------------------------------
-- 2. JOIN pada tabel database

-- -- Contoh 10-11: Penggunaan CROSS JOIN
SELECT *
FROM PENGGUNA CROSS JOIN ADMIN;

SELECT *
FROM PENGGUNA, ADMIN;


-- -- Contoh 12: Penggunaan INNER JOIN
SELECT *
FROM PENGGUNA P INNER JOIN ADMIN A
ON P.email = A.email;


-- -- Contoh 13: Penggunaan LEFT OUTER JOIN
SELECT *
FROM PENGGUNA P LEFT OUTER JOIN ADMIN A
ON P.email = A.email;


-- -- Contoh 14: Penggunaan RIGHT OUTER JOIN
SELECT *
FROM PENGGUNA P RIGHT OUTER JOIN ADMIN A
ON P.email = A.email;


-- -- Contoh 15: Penggunaan FULL OUTER JOIN
SELECT *
FROM PENGGUNA P FULL OUTER JOIN ADMIN A
ON P.email = A.email;


-- -- Contoh 16: Penggunaan NATURAL JOIN
SELECT *
FROM PENGGUNA P NATURAL JOIN ADMIN A;


-- --------------------------------------------------------
-- 3. Advanced SQL

-- -- Contoh 17: Conditional NULL value
SELECT email
FROM CUSTOMER
WHERE reward_point IS NOT NULL;


-- -- Contoh 18: Conditional IN value
SELECT judul
FROM FILM
WHERE id_film IN
(SELECT id_film
FROM JADWAL_FILM
WHERE id_bioskop = 'bio002');


-- -- Contoh 19: Conditional EXISTS value
SELECT C.email
FROM CUSTOMER C
WHERE NOT EXISTS
(SELECT *
FROM TRANSAKSI_PEMBELIAN TP
WHERE C.email = TP.id_customer);


-- --------------------------------------------------------
-- 4. Aggregate, Grouping, and Having

-- -- Contoh 20: Penggunaan MAX
SELECT MAX(reward_point)
FROM CUSTOMER;


-- -- Contoh 21: Penggunaan MIN
SELECT MIN(durasi)
FROM FILM;


-- -- Contoh 22: Penggunaan COUNT
SELECT COUNT(*)
FROM BIOSKOP;


-- -- Contoh 23: Penggunaan SUM
SELECT SUM(harga)
FROM TIKET
WHERE pembelian='p00013';


-- -- Contoh 24: Penggunaan AVG
SELECT AVG(harga)
FROM TIKET;


-- -- Contoh 25: Penggunaan GROUP BY
SELECT id_film, AVG(harga)
FROM TIKET
GROUP BY id_film;


-- -- Contoh 26: Penggunaan HAVING
SELECT id_bioskop, nama, COUNT(DISTINCT id_film)
FROM BIOSKOP B
NATURAL JOIN JADWAL_FILM J
GROUP BY B.id_bioskop
HAVING COUNT(DISTINCT id_film) > 1;


-- --------------------------------------------------------
-- 5. Latihan Soal

-- -- 1. Screenshot semua hasil query di atas
-- -- 2. Tampilkan judul film dan nama bioskop yang menayangkan film tersebut (tanpa duplikasi) yang tiketnya dijual dengan harga lebih dari 35000.
-- -- 3. Tampilkan nama bioskop yang menayangkan lebih dari 2 film berbeda.
-- -- 4. Tampilkan username pengguna beserta bioskop (tanpa duplikasi) yang pernah dikunjungi oleh pengguna tersebut untuk menonton film

