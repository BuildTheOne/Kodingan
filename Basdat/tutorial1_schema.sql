SET search_path to SIBIOSKOP;

CREATE TABLE PENGGUNA(
	email VARCHAR(32) NOT NULL,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(15) NOT NULL,
	PRIMARY KEY (email)
);

CREATE TABLE ADMIN(
	email VARCHAR(32) NOT NULL,
	jabatan VARCHAR(32) NOT NULL,
	PRIMARY KEY (email),
	FOREIGN KEY (email) REFERENCES PENGGUNA(email) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CUSTOMER(
	email VARCHAR(32) NOT NULL,
	reward_point INTEGER,
	PRIMARY KEY (email),
	FOREIGN KEY (email) REFERENCES PENGGUNA(email) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE BIOSKOP(
	id_bioskop VARCHAR(10) NOT NULL,
	nama VARCHAR(32) NOT NULL,
	alamat VARCHAR(100) NOT NULL,
	jam_buka TIME NOT NULL,
	jam_tutup TIME NOT NULL,
	PRIMARY KEY (id_bioskop)
);

CREATE TABLE FILM(
	id_film VARCHAR(10) NOT NULL,
	judul VARCHAR(32) NOT NULL,
	deskripsi VARCHAR(500) NOT NULL,
	rating INTEGER,
	durasi INTEGER NOT NULL,
	genre VARCHAR(32) NOT NULL,
	PRIMARY KEY (id_film)
);

CREATE TABLE STUDIO(
	id_bioskop VARCHAR(10) NOT NULL,
	id_studio VARCHAR(10) NOT NULL,
	PRIMARY KEY (id_bioskop, id_studio),
	FOREIGN KEY (id_bioskop) REFERENCES BIOSKOP(id_bioskop) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE JADWAL_FILM(
	id_jadwal VARCHAR(10) NOT NULL,
	id_studio VARCHAR(10) NOT NULL,
	id_bioskop VARCHAR(10) NOT NULL,
	id_film VARCHAR(10) NOT NULL,
	jam_mulai TIME NOT NULL,
	jam_selesai TIME NOT NULL,
	PRIMARY KEY (id_jadwal, id_studio, id_bioskop, id_film),
	FOREIGN KEY (id_bioskop, id_studio) REFERENCES STUDIO(id_bioskop, id_studio) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (id_film) REFERENCES FILM(id_film) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE TRANSAKSI_PEMBELIAN(
	id_pembelian VARCHAR(10) NOT NULL,
	id_customer VARCHAR(32) NOT NULL,
	total_harga INTEGER NOT NULL,
	PRIMARY KEY (id_pembelian),
	FOREIGN KEY (id_customer) REFERENCES CUSTOMER(email) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE TIKET(
	id_tiket VARCHAR(10) NOT NULL,
	id_jadwal VARCHAR(10) NOT NULL,
	id_studio VARCHAR(10) NOT NULL,
	id_bioskop VARCHAR(10) NOT NULL,
	id_film VARCHAR(10) NOT NULL,
	no_bangku VARCHAR(10) NOT NULL,
	harga INTEGER NOT NULL,
	pembelian VARCHAR(10) NOT NULL,
	PRIMARY KEY (id_tiket),
	FOREIGN KEY (id_jadwal, id_studio, id_bioskop, id_film) REFERENCES JADWAL_FILM(id_jadwal, id_studio, id_bioskop, id_film) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (pembelian) REFERENCES TRANSAKSI_PEMBELIAN(id_pembelian) ON UPDATE CASCADE ON DELETE CASCADE
);