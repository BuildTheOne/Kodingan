SET search_path TO excellent_reads;

-- 1. Tampilkan username, tanggal bergabung, dan nama depan pengguna yang bergabung sebelum tanggal 28 Februari 2021.
SELECT username, tgl_gabung, nama_depan
FROM pengguna
WHERE tgl_gabung<'2021-02-28';

-- 2. Tampilkan username penulis, kota domisili penulis, dan jumlah penghargaan yang diterimanya.
SELECT PP.username_penulis, domisili_kota, COUNT(penghargaan)
FROM pengguna P
INNER JOIN penghargaan_penulis PP
ON PP.username_penulis=P.username
GROUP BY PP.username_penulis, domisili_kota;

-- 3. Tampilkan username dan domisili negara pengguna yang bukan merupakan seorang penulis.
SELECT P.username, P.domisili_negara
FROM pengguna P
FULL JOIN penulis PN
ON PN.username_penulis=P.username
WHERE PN.username_penulis IS NULL;

-- 4. Tampilkan username pengguna, daftar baca yang dimiliki, serta jumlah buku di dalam setiap daftar baca tersebut.
SELECT DB.Username_Pengguna, DB.nama, COUNT(MB.ISBN_buku)
FROM daftar_baca DB
INNER JOIN memiliki_buku MB
ON (MB.Username_Pengguna, MB.nama_daftarBaca) = (DB.Username_Pengguna, DB.nama)
GROUP BY DB.Username_Pengguna, DB.nama;

-- 5. Tampilkan nama grup, jumlah anggota, serta jumlah post dari grup yang memiliki anggota lebih dari 1.
SELECT G.nama, COUNT(*), COUNT(DISTINCT P.ID_Post)
FROM grup G
INNER JOIN anggota_grup AG
ON AG.Nomor_Grup=G.Nomor_Grup
INNER JOIN post P
ON P.No_Grup=G.Nomor_Grup
GROUP BY G.nama
HAVING COUNT(DISTINCT P.ID_Post) > 1;

-- 6. Tampilkan username dan email pengguna yang belum pernah mengirim, menerima maupun menolak undangan pertemanan dari pengguna lain.
SELECT P.username, P.email
FROM pengguna P
FULL JOIN undangan UA
ON UA.username_pengirim=P.username
FULL JOIN undangan UB
ON UB.username_penerima=P.username
WHERE UA.wktDikirim IS NULL AND UA.wktDiterima IS NULL AND UA.wktDihapus IS NULL
AND UB.wktDikirim IS NULL AND UB.wktDiterima IS NULL AND UB.wktDihapus IS NULL;

-- 7. Tampilkan username pengguna yang menulis ulasan dan judul buku dari ulasan yang belum pernah disukai.
SELECT U.Username_Pengguna, B.No_ISBN, COUNT(MU.Username_Pengguna)
FROM ulasan U
INNER JOIN buku B
ON B.No_ISBN=U.ISBN_buku
FULL JOIN menyukai_ulasan MU
ON (MU.ISBN_bukuDiulas,MU.No_Urut_Ulasan)=(U.ISBN_buku,U.No_urut)
GROUP BY U.Username_Pengguna, B.No_ISBN
HAVING COUNT(MU.Username_Pengguna) = 0;

-- 8. Tampilkan judul buku, jumlah genre, dan jumlah ulasan dari setiap buku.
SELECT B.No_ISBN, COUNT(DISTINCT MG.NoUrut_genre), COUNT(DISTINCT U.No_urut)
FROM buku B
INNER JOIN mempunyai_genre MG
ON MG.ISBN_buku=B.No_ISBN
FULL JOIN ulasan U
ON U.ISBN_buku=B.No_ISBN
GROUP BY B.No_ISBN;

-- 9. Tampilkan jumlah pengguna yang memberi rating dan rata-rata nilai rating dari setiap buku.
SELECT B.No_ISBN, COUNT(MR.Username_Pengguna), AVG(MR.Angka_rating)
FROM buku B
FULL JOIN memberi_rating MR
ON MR.ISBN_buku=B.No_ISBN
GROUP BY B.No_ISBN;

-- 10. Tampilkan nama depan pengirim, nama depan penerima, dan jumlah pesan yang telah dikirim di mana pengirim telah mengirim pesan ke penerima tersebut lebih dari sekali.
SELECT PN.nama_depan, PN.nama_belakang, COUNT(*)
FROM pesan P
INNER JOIN pengguna PN
ON PN.username=P.Username_Pengirim
GROUP BY PN.nama_depan, PN.nama_belakang
HAVING COUNT(*) > 1;