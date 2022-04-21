-- Tugas Individu 3 Basis Data 2022

--
-- Database: ExcellentReads
--

-- --------------------------------------------------------
-- Delete DROP SCHEMA query below if finished
DROP SCHEMA IF EXISTS excellent_reads CASCADE;
CREATE SCHEMA IF NOT EXISTS excellent_reads;
SET search_path TO excellent_reads;

--
-- Table structure for table PENGGUNA
--
CREATE TABLE pengguna (
	username VARCHAR(20) PRIMARY KEY,
	email VARCHAR(30) NOT NULL,
	password VARCHAR(20) NOT NULL,
	nama_depan VARCHAR(15) NOT NULL,
	nama_belakang VARCHAR(15) NOT NULL,
	tgl_gabung DATE NOT NULL,
	domisili_kota VARCHAR(15),
	domisili_negara VARCHAR(15)
);

--
-- Table structure for table PENULIS
--
CREATE TABLE penulis (
	username_penulis VARCHAR(20) PRIMARY KEY REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	website TEXT,
	biografi TEXT NOT NULL
);

--
-- Table structure for table PENGHARGAAN_PENULIS
--
CREATE TABLE penghargaan_penulis (
	username_penulis VARCHAR(20) REFERENCES penulis(username_penulis) ON UPDATE RESTRICT ON DELETE RESTRICT,
	penghargaan TEXT,
	PRIMARY KEY (username_penulis, penghargaan)
);

--
-- Table structure for table UNDANGAN
--
CREATE TABLE undangan (
	username_pengirim VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	no_urut INT,
	wktDikirim TIMESTAMP NOT NULL,
	wktDiterima TIMESTAMP,
	wktDihapus TIMESTAMP,
	username_penerima VARCHAR(20) NOT NULL
	-- 	FOREIGN KEY (username_penerima) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table PESAN
--
CREATE TABLE pesan (
	ID_Pesan VARCHAR(20),
	Username_Pengirim VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Isi_Pesan TEXT NOT NULL,
	Waktu TIME NOT NULL,
	Tanggal DATE NOT NULL,
	Judul_Pesan VARCHAR(60),
	Root_Pesan VARCHAR(20),
	Username_Tujuan VARCHAR(20) NOT NULL,
	Root_Username_Pengirim VARCHAR(20)
);

--
-- Table structure for table BUKU
--
CREATE TABLE buku (
	No_ISBN VARCHAR(15) PRIMARY KEY,
	Judul TEXT NOT NULL,
	Sinopsis TEXT NOT NULL,
	Edisi INT,
	Penerbit VARCHAR(30) NOT NULL,
	Jml_halaman INT NOT NULL,
	Bahasa VARCHAR(20) NOT NULL
);

--
-- Table structure for table GENRE
--
CREATE TABLE genre (
	No_Urut INT PRIMARY KEY,
	Nama VARCHAR(20) NOT NULL
);

--
-- Table structure for table MEMPUNYAI_GENRE
--
CREATE TABLE mempunyai_genre (
	ISBN_buku VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	NoUrut_genre INT REFERENCES genre(No_Urut) ON UPDATE RESTRICT ON DELETE RESTRICT,
	PRIMARY KEY (ISBN_buku, NoUrut_genre)
);

--
-- Table structure for table PENGHARGAAN_BUKU
--
CREATE TABLE penghargaan_buku (
	ISBN_buku VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Penghargaan TEXT,
	PRIMARY KEY (ISBN_buku, Penghargaan)
);

--
-- Table structure for table MENYUKAI_GENRE
--
CREATE TABLE menyukai_genre (
	Username_Pengguna VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	NoUrut_genre INT REFERENCES genre(No_Urut) ON UPDATE RESTRICT ON DELETE RESTRICT,
	PRIMARY KEY (Username_Pengguna, NoUrut_genre)
);

--
-- Table structure for table DAFTAR_BACA
--
CREATE TABLE daftar_baca (
	Username_Pengguna VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	nama VARCHAR(50) NOT NULL UNIQUE, -- Add constraints NOT NULL and UNIQUE to handle error
	PRIMARY KEY (Username_Pengguna, nama)
);

--
-- Table structure for table MEMILIKI_BUKU
--
CREATE TABLE memiliki_buku (
	nama_daftarBaca VARCHAR(50) REFERENCES daftar_baca(nama) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Username_Pengguna VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	ISBN_buku VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	PRIMARY KEY (nama_daftarBaca, Username_Pengguna, ISBN_buku)
);

--
-- Table structure for table MEMBERI_RATING
--
CREATE TABLE memberi_rating (
	Username_Pengguna VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	ISBN_buku VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Angka_rating INT NOT NULL,
	PRIMARY KEY (Username_Pengguna, ISBN_buku)
);

--
-- Table structure for table MENULIS_BUKU
--
CREATE TABLE menulis_buku (
	username_penulis VARCHAR(20) REFERENCES penulis(username_penulis) ON UPDATE RESTRICT ON DELETE RESTRICT,
	ISBN_buku VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	PRIMARY KEY (username_penulis, ISBN_buku)
);

--
-- Table structure for table ULASAN
--
CREATE TABLE ulasan (
	ISBN_buku VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	No_urut INT,
	Username_Pengguna VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	Judul TEXT NOT NULL,
	Isi TEXT NOT NULL,
	Tgl_dibuat DATE NOT NULL,
	PRIMARY KEY (ISBN_buku, No_urut)
);

--
-- Table structure for table MENYUKAI_ULASAN
--
CREATE TABLE menyukai_ulasan (
	ISBN_bukuDiulas VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	NoUrut_ulasan INT,
	Username_Pengguna VARCHAR(20) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	PRIMARY KEY (ISBN_bukuDiulas, NoUrut_ulasan, Username_Pengguna)
);

--
-- Table structure for table KOMENTAR_ULASAN
--
CREATE TABLE komentar_ulasan (
	ISBN_buku VARCHAR(15) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	No_Urut_Ulasan INT, 
);











