SET search_path TO excellent_reads;

-- 1. Tampilkan username penulis beserta banyaknya penghargaan yang dimiliki oleh penulis tersebut. 
--    Urutkan penulis berdasarkan penghargaan yang paling banyak✅
SELECT username_penulis, COUNT(penghargaan) AS jumlah_penghargaan 
FROM penghargaan_penulis
GROUP BY username_penulis
ORDER BY COUNT(penghargaan) DESC;

-- 2. Dua pengguna bisa saling berteman setelah penerima menerima undangan berteman. 
--    Tampilkan username pengirim dan penerima undangan beserta lama waktu hingga undangan 
--    pertemanan tersebut diterima.✅
-- Asumsi: Jika tidak diterima (wktDiterima=NULL), tidak dihitung
SELECT username_pengirim, username_penerima, 
(wktDiterima - wktDikirim) AS lama_diterima 
FROM undangan
WHERE (wktDiterima - wktDikirim) IS NOT NULL;

-- 3. Tampilkan username pengguna yang pernah membuat komentar ulasan, total jumlah like
--    pada semua komentar ulasan yang dibuat oleh pengguna tersebut, serta semua username
--    pengguna yang menyukai komentar ulasan pengguna tersebut dalam satu baris dipisahkan
--    dengan koma, diurutkan dari total jumlah like terbanyak✅
-- Asumsi: Komentar dengan like 0 tetap tampil, sehingga mungkin ada username_like yang bernilai kosong
SELECT K.Username_Pembuat AS username, K.Jumlah_Like AS total_like, 
COALESCE(string_agg(L.username_like, ', '), '') AS username_yang_menyukai
FROM komentar_ulasan AS K
FULL JOIN like_komentar_ulasan AS L
ON (L.No_Urut_Komentar_Ulasan, L.ISBN_bukuDiulas, L.No_Urut_Ulasan) = (K.No_Urut_Komentar_Ulasan, K.ISBN_bukuDiulas, K.No_Urut_Ulasan)
GROUP BY K.Username_Pembuat, K.Jumlah_Like
ORDER BY K.Jumlah_Like DESC;

-- 4. Tampilkan nomor grup, nama grup, jumlah anggota, serta jumlah post dari grup yang
--    memiliki anggota lebih dari lima.✅
SELECT A.Nomor_Grup, G.nama AS nama_grup, 
COUNT(DISTINCT A.Username_Pengguna) AS jumlah_anggota, 
COUNT(DISTINCT P.ID_Post) AS jumlah_post
FROM anggota_grup AS A
INNER JOIN grup AS G
ON A.Nomor_Grup = G.Nomor_Grup
INNER JOIN post AS P
ON A.Nomor_Grup = P.No_Grup
GROUP BY A.Nomor_Grup, G.nama, P.no_grup
HAVING COUNT(DISTINCT A.Username_Pengguna) > 5;

-- 5. Tampilkan nama genre beserta rerata rating buku yang memiliki genre tersebut. Tampilkan
--    hanya genre yang buku-bukunya telah dinilai oleh minimal 3 penilaian berbeda.✅
SELECT G.nama AS nama_genre, AVG(R.angka_rating) AS rerata_rating_buku
FROM mempunyai_genre AS MG
INNER JOIN genre AS G
ON MG.nourut_genre = G.no_urut
INNER JOIN buku AS B
ON MG.isbn_buku = B.no_isbn
INNER JOIN memberi_rating AS R
ON MG.isbn_buku = R.isbn_buku
GROUP BY G.nama
HAVING COUNT(R.isbn_buku) > 2;

-- 6. Tampilkan banyaknya thread pesan yang dibuat oleh masing-masing pengguna.
SELECT P.username, SUM(
	CASE WHEN PS.root_pesan IS NULL 
			AND PS.id_pesan IS NOT NULL 
			THEN 1 
			ELSE 0 END)
FROM PENGGUNA P
LEFT OUTER JOIN PESAN PS
ON PS.username_pengirim = P.username
GROUP BY P.username;

-- 7. Tampilkan username pengguna beserta seluruh daftar baca yang dimilikinya. Tampilkan
--    juga semua judul buku yang ada dalam daftar bacanya, serta nama penulis dan penghargaan
--    dari buku tersebut (tetap tampilkan buku yang tidak memiliki penghargaan).✅
SELECT MB.Username_Pengguna, DB.nama AS daftar_baca, 
B.judul AS judul_buku, M.username_penulis AS penulis, 
COALESCE(PB.penghargaan, '') AS penghargaan_buku
FROM memiliki_buku AS MB
INNER JOIN daftar_baca AS DB
ON (DB.Username_Pengguna, DB.nama) = (MB.Username_Pengguna, MB.nama_daftarBaca)
INNER JOIN buku AS B
ON MB.ISBN_buku = B.no_isbn
INNER JOIN menulis_buku AS M
ON M.isbn_buku = B.no_isbn
FULL JOIN penghargaan_buku AS PB
ON PB.ISBN_buku = B.no_isbn;

-- 8. Tampilkan ISBN Buku serta jumlah ulasannya yang disukai oleh pengguna yang menyukai
--    genre Petualangan.✅
SELECT U.isbn_buku, COUNT(MU.ISBN_bukuDiulas) AS jumlah_ulasan
FROM ulasan AS U
INNER JOIN menyukai_ulasan AS MU
ON (MU.ISBN_bukuDiulas, MU.No_Urut_Ulasan) = (U.ISBN_buku, U.No_urut)
INNER JOIN menyukai_genre AS MG
ON MG.Username_Pengguna = MU.Username_Pengguna
INNER JOIN genre AS G
ON G.No_Urut = MG.NoUrut_genre
WHERE G.nama = 'Petualangan'
GROUP BY U.isbn_buku;

-- 9. Tampilkan seluruh pengguna beserta status keaktifannya (Aktif/Tidak Aktif). Seorang
--    pengguna dikatakan aktif apabila dia merupakan anggota suatu grup serta pernah menulis
--    ulasan terkait buku-buku yang masuk dalam daftar bacaanya.✅
SELECT DISTINCT P.username, 
	CASE WHEN 
			A.username_pengguna IS NOT NULL 
			AND U.Username_Pengguna IS NOT NULL
			AND U.isbn_buku IS NOT NULL
		 	AND MB.Username_Pengguna IS NOT NULL 
		 THEN 'Aktif'
		 ELSE 'Tidak Aktif' END
	AS status_keaktifan
FROM pengguna AS P
FULL JOIN anggota_grup AS A
ON A.username_pengguna = P.username
FULL JOIN ulasan AS U
ON U.Username_Pengguna = P.username
FULL JOIN memiliki_buku AS MB
ON (MB.Username_Pengguna) = (U.Username_Pengguna)
WHERE P.username IS NOT NULL;

-- 10. Untuk seluruh grup, tampilkan nomor grup, nama grup, serta tentukan banyak penulis dan
--     non-penulis yang tergabung di dalam grup tersebut, diurut berdasarkan nomor grup (Bedakan
--     antara penulis dan pengguna biasa).✅
SELECT G.nomor_grup, G.nama AS nama_grup, 
COUNT(PN.username_penulis) AS penulis,
(COUNT(P.username) - COUNT(PN.username_penulis)) AS pengguna_biasa
FROM anggota_grup AS A
INNER JOIN grup AS G
ON G.nomor_grup = A.nomor_grup
FULL JOIN pengguna AS P
ON A.username_pengguna = P.username
FULL JOIN penulis AS PN
ON A.username_pengguna = PN.username_penulis
GROUP BY G.nomor_grup
ORDER BY G.nomor_grup ASC;
