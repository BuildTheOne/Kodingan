SET search_path TO sibioskop;

-- 1. [SQL] Jalankan contoh 1 hingga contoh 9 diatas.
-- 2. View 
-- -- a. [Trivia] Apa yang akan terjadi jika kita membuat View menggunakan nama yang sama dengan nama tabel yang ada pada database? Jelaskan! 
-- -- b. [Trivia] Apa fungsi TEMP atau TEMPORARY di View? 
-- -- c. [SQL] Buat View yang menyimpan nama bioskop yang memiliki jam buka atau jam tutup tidak tepat pada menit 00. 
CREATE OR REPLACE VIEW bioskop_jam00 AS
SELECT B.nama, B.jam_buka, B.jam_tutup
FROM bioskop AS B
WHERE to_char(B.jam_buka, 'HH24:MI:SS') NOT LIKE '%:00:%'
OR to_char(B.jam_tutup, 'HH24:MI:SS') NOT LIKE '%:00:%';
-- -- d. [SQL] Buat View yang menyimpan nama customer yang membeli tiket dengan nomor bangku pada baris F. 
CREATE OR REPLACE VIEW customer_F AS
SELECT DISTINCT P.username
FROM tiket AS T
INNER JOIN transaksi_pembelian AS TB
ON TB.id_pembelian = T.pembelian 
INNER JOIN pengguna AS P
ON P.email = TB.id_customer
WHERE T.no_bangku LIKE 'F%';

-- 3. [SQL] Analyze (perbandingan execution time) Diberikan query berikut
SELECT * FROM studio ORDER BY id_studio ASC;
SELECT * FROM transaksi_pembelian ORDER BY total_harga DESC;
-- -- a. [SQL] Jalankan perintah EXPLAIN ANALYZE untuk setiap query di atas. Screenshot eksekusinya dan tulis hasilnya pada tabel di bawah, sertakan dalam laporan submisi Anda
-- -- b. [SQL] Buat index berikut (method nya terserah Anda): 
-- -- -- i. index_total_harga pada tabel TRANSAKSI_PEMBELIAN kolom total_harga.
-- -- -- ii. index_studio pada tabel STUDIO kolom id_studio.
-- -- c. [SQL] Jalankan kembali setiap query SELECT di atas dari pertanyaan nomor 2a menggunakan perintah EXPLAIN ANALYZE. Screenshot eksekusinya dan tulis hasilnya, sertakan dalam laporan submisi Anda. 
-- -- d. [Trivia] Bandingkan planning time dan execution time dari query saat tanpa index dan setelah menggunakan index. Mana yang lebih baik? Berikan penjelasan!
