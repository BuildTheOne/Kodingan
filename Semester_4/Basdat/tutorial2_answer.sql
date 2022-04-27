SET search_path TO sibioskop;

-- 1. Screenshot semua hasil query di atas
-- 2. Tampilkan judul film dan nama bioskop yang menayangkan film tersebut (tanpa duplikasi) yang tiketnya dijual dengan harga lebih dari 35000.
--    Asumsi: Judul film dan bioskop sama dengan harga berbeda akan dihitung satu
SELECT DISTINCT B.nama, F.judul
FROM tiket T
INNER JOIN bioskop B
ON T.id_bioskop = B.id_bioskop
INNER JOIN film F
ON T.id_film = F.id_film
WHERE T.harga > 35000;

-- 3. Tampilkan nama bioskop yang menayangkan lebih dari 2 film berbeda.
SELECT B.nama
FROM bioskop B
NATURAL JOIN jadwal_film J
GROUP BY B.id_bioskop
HAVING COUNT (DISTINCT J.id_film) > 2;

-- 4. Tampilkan username pengguna beserta bioskop (tanpa duplikasi) yang pernah dikunjungi oleh pengguna tersebut untuk menonton film
SELECT DISTINCT P.username, B.nama
FROM pengguna P
INNER JOIN customer C
ON P.email = C.email
INNER JOIN transaksi_pembelian TB
ON C.email = TB.id_customer
INNER JOIN tiket T
ON TB.id_pembelian = T.pembelian
INNER JOIN bioskop B
ON B.id_bioskop = T.id_bioskop;