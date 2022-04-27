-- Tugas Individu 3 Basis Data 2022

--
-- Database: ExcellentReads
--

-- --------------------------------------------------------
-- DROP SCHEMA IF EXISTS excellent_reads CASCADE;
CREATE SCHEMA IF NOT EXISTS excellent_reads;
SET search_path TO excellent_reads;

--
-- Table structure for table PENGGUNA
--
CREATE TABLE pengguna (
	username VARCHAR(20),
	email VARCHAR(30) NOT NULL,
	password VARCHAR(20) NOT NULL,
	nama_depan VARCHAR(15) NOT NULL,
	nama_belakang VARCHAR(15) NOT NULL,
	tgl_gabung DATE NOT NULL,
	domisili_kota VARCHAR(15),
	domisili_negara VARCHAR(15),
	PRIMARY KEY (username)
);

--
-- Table structure for table PENULIS
--
CREATE TABLE penulis (
	username_penulis VARCHAR(20),
	website TEXT,
	biografi TEXT NOT NULL,
	PRIMARY KEY (username_penulis),
	FOREIGN KEY (username_penulis) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table PENGHARGAAN_PENULIS
--
CREATE TABLE penghargaan_penulis (
	username_penulis VARCHAR(20),
	penghargaan TEXT,
	PRIMARY KEY (username_penulis, penghargaan),
	FOREIGN KEY (username_penulis) REFERENCES penulis(username_penulis) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table UNDANGAN
--
CREATE TABLE undangan (
	username_pengirim VARCHAR(20),
	no_urut INT,
	wktDikirim TIMESTAMP NOT NULL,
	wktDiterima TIMESTAMP,
	wktDihapus TIMESTAMP,
	username_penerima VARCHAR(20) NOT NULL,
	PRIMARY KEY(username_pengirim, no_urut),
	FOREIGN KEY (username_pengirim) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (username_penerima) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table PESAN
--
CREATE TABLE pesan (
	ID_Pesan VARCHAR(20),
	Username_Pengirim VARCHAR(20),
	Isi_Pesan TEXT NOT NULL,
	Waktu TIME NOT NULL,
	Tanggal DATE NOT NULL,
	Judul_Pesan VARCHAR(60),
	Root_Pesan VARCHAR(20),
	Username_Tujuan VARCHAR(20) NOT NULL,
	Root_Username_Pengirim VARCHAR(20),
	PRIMARY KEY (ID_Pesan, Username_Pengirim),
	FOREIGN KEY (Username_Pengirim) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	-- Docs masih error
	FOREIGN KEY (Root_Pesan, Root_Username_Pengirim) REFERENCES pesan(ID_Pesan, Username_Pengirim) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (Username_Tujuan) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table BUKU
--
CREATE TABLE buku (
	No_ISBN VARCHAR(15),
	Judul TEXT NOT NULL,
	Sinopsis TEXT,
	Edisi INT,
	Penerbit VARCHAR(30) NOT NULL,
	Jml_halaman INT NOT NULL,
	Bahasa VARCHAR(20) NOT NULL,
	PRIMARY KEY (No_ISBN)
);

--
-- Table structure for table GENRE
--
CREATE TABLE genre (
	No_Urut INT,
	Nama VARCHAR(20) NOT NULL,
	PRIMARY KEY (No_Urut)
);

--
-- Table structure for table MEMPUNYAI_GENRE
--
CREATE TABLE mempunyai_genre (
	ISBN_buku VARCHAR(15),
	NoUrut_genre INT,
	PRIMARY KEY (ISBN_buku, NoUrut_genre),
	FOREIGN KEY (ISBN_buku) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (NoUrut_genre) REFERENCES genre(No_Urut) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table PENGHARGAAN_BUKU
--
CREATE TABLE penghargaan_buku (
	ISBN_buku VARCHAR(15),
	Penghargaan TEXT,
	PRIMARY KEY (ISBN_buku, Penghargaan),
	FOREIGN KEY (ISBN_buku) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table MENYUKAI_GENRE
--
CREATE TABLE menyukai_genre (
	Username_Pengguna VARCHAR(20),
	NoUrut_genre INT,
	PRIMARY KEY (Username_Pengguna, NoUrut_genre),
	FOREIGN KEY (Username_Pengguna) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (NoUrut_genre) REFERENCES genre(No_Urut) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table DAFTAR_BACA
--
CREATE TABLE daftar_baca (
	Username_Pengguna VARCHAR(20),
	nama VARCHAR(50),
	PRIMARY KEY (Username_Pengguna, nama),
	FOREIGN KEY (Username_Pengguna) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table MEMILIKI_BUKU
--
CREATE TABLE memiliki_buku (
	nama_daftarBaca VARCHAR(50),
	Username_Pengguna VARCHAR(20),
	ISBN_buku VARCHAR(15),
	PRIMARY KEY (nama_daftarBaca, Username_Pengguna, ISBN_buku),
	-- Docs masih error
	FOREIGN KEY (Username_Pengguna, nama_daftarBaca) REFERENCES daftar_baca(Username_Pengguna, nama) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (ISBN_buku) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table MEMBERI_RATING
--
CREATE TABLE memberi_rating (
	Username_Pengguna VARCHAR(20),
	ISBN_buku VARCHAR(15),
	Angka_rating INT NOT NULL,
	PRIMARY KEY (Username_Pengguna, ISBN_buku),
	FOREIGN KEY (Username_Pengguna) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (ISBN_buku) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table MENULIS_BUKU
--
CREATE TABLE menulis_buku (
	username_penulis VARCHAR(20),
	ISBN_buku VARCHAR(15),
	PRIMARY KEY (username_penulis, ISBN_buku),
	FOREIGN KEY (username_penulis) REFERENCES penulis(username_penulis) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (ISBN_buku) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table ULASAN
--
CREATE TABLE ulasan (
	ISBN_buku VARCHAR(15),
	No_urut INT,
	Username_Pengguna VARCHAR(20),
	Judul TEXT NOT NULL,
	Isi TEXT NOT NULL,
	Tgl_dibuat DATE NOT NULL,
	PRIMARY KEY (ISBN_buku, No_urut),
	FOREIGN KEY (ISBN_buku) REFERENCES buku(No_ISBN) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (Username_Pengguna) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table MENYUKAI_ULASAN
--
CREATE TABLE menyukai_ulasan (
	ISBN_bukuDiulas VARCHAR(15),
	No_Urut_Ulasan INT,
	Username_Pengguna VARCHAR(20),
	PRIMARY KEY (ISBN_bukuDiulas, No_Urut_Ulasan, Username_Pengguna),
	FOREIGN KEY (ISBN_bukuDiulas, No_Urut_Ulasan) REFERENCES ulasan(ISBN_buku, No_urut) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (Username_Pengguna) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table KOMENTAR_ULASAN
--
CREATE TABLE komentar_ulasan (
	ISBN_bukuDiulas VARCHAR(15),
	No_Urut_Ulasan INT,
	No_Urut_Komentar_Ulasan INT,
	Isi TEXT NOT NULL,
	Tanggal DATE NOT NULL,
	Jumlah_Like INT DEFAULT 0,
	Username_Pembuat VARCHAR(20),
	PRIMARY KEY (ISBN_bukuDiulas, No_Urut_Ulasan, No_Urut_Komentar_Ulasan),
	-- ?
	FOREIGN KEY (ISBN_bukuDiulas, No_Urut_Ulasan) REFERENCES ulasan(ISBN_buku, No_urut) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (Username_Pembuat) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table LIKE_KOMENTAR_ULASAN
--
CREATE TABLE like_komentar_ulasan (
	ISBN_bukuDiulas VARCHAR(15),
	No_Urut_Ulasan INT,
	No_Urut_Komentar_Ulasan INT,
	Username_Like VARCHAR(20),
	PRIMARY KEY (ISBN_bukuDiulas, No_Urut_Ulasan, No_Urut_Komentar_Ulasan, Username_Like),
	FOREIGN KEY (ISBN_bukuDiulas, No_Urut_Ulasan, No_Urut_Komentar_Ulasan) REFERENCES komentar_ulasan(ISBN_bukuDiulas, No_Urut_Ulasan, No_Urut_Komentar_Ulasan) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (Username_Like) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table GRUP
--
CREATE TABLE grup (
	Nomor_Grup VARCHAR(20),
	Nama VARCHAR(60) NOT NULL,
	Deskripsi TEXT,
	Username_Pembuat VARCHAR(20) NOT NULL,
	PRIMARY KEY (Nomor_Grup),
	FOREIGN KEY (Username_Pembuat) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table ANGGOTA_GRUP
--
CREATE TABLE anggota_grup (
	Nomor_Grup VARCHAR(20),
	Username_Pengguna VARCHAR(20),
	PRIMARY KEY (Nomor_Grup, Username_Pengguna),
	FOREIGN KEY (Nomor_Grup) REFERENCES grup(Nomor_Grup) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (Username_Pengguna) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);

--
-- Table structure for table POST
--
CREATE TABLE post (
	ID_Post VARCHAR(20),
	Judul VARCHAR(60) NOT NULL,
	Isi TEXT NOT NULL,
	No_Grup VARCHAR(20),
	Username_Pengguna VARCHAR(20),
	PRIMARY KEY (ID_Post),
	FOREIGN KEY (No_Grup) REFERENCES grup(Nomor_Grup) ON UPDATE RESTRICT ON DELETE RESTRICT,
	FOREIGN KEY (Username_Pengguna) REFERENCES pengguna(username) ON UPDATE RESTRICT ON DELETE RESTRICT
);