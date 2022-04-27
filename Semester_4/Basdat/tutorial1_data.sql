SET search_path to SIBIOSKOP;

INSERT INTO PENGGUNA VALUES
	('inez_fitri@bazdat.com', 'inez_fitri', 'LBQr4mPs'),
	('almya_yudhita@bazdat.com', 'almya_y', 'ReX4TPHk');

INSERT INTO ADMIN VALUES
	('inez_fitri@bazdat.com', 'keuangan');

INSERT INTO CUSTOMER VALUES
	('almya_yudhita@bazdat.com', 200);

INSERT INTO BIOSKOP VALUES
	('bio001', 'Bassura XXI', 'Bassura City Lt. 2. Jl. Basuki Rahmat No. 1. Jakarta Timur', '08:00:00', '21:00:00');

INSERT INTO FILM VALUES
	('F0001', 'The Protege', 'Diselamatkan pada saat kecil oleh pembunuh legendaris Moody (Samuel L. Jackson), Anna (Maggie Q) dibentuk serta dilatih untuk bisnis keluarga. Kini Anna adalah pembunuh bayaran paling terampil di dunia.', 8, 108, 'Action');

INSERT INTO STUDIO VALUES
	('bio001', 's01');

INSERT INTO JADWAL_FILM VALUES
	('J001', 's01', 'bio001', 'F0001', '08:30:00', '10:30:00');

INSERT INTO TRANSAKSI_PEMBELIAN VALUES
	('p00001', 'almya_yudhita@bazdat.com', 100000);

INSERT INTO TIKET VALUES
	('tick00001', 'J001', 's01', 'bio001', 'F0001', 'M6', 50000, 'p00001');

INSERT INTO PENGGUNA VALUES
    ('revin_cavalera@bazdat.com','revin.cavalera','NR8kX5D4'),
    ('aisha_a@bazdat.com','aisha_aundra','UMJDfQ3H'),
    ('mayandra_talitha@bazdat.com','mayandra_t','vaz8cHnL'),
    ('rahel_venca@bazdat.com','rahel_v','5fMDjFNg'),
    ('nikho_xabi@bazdat.com','xabi.nikho','snQtu5dg'),
    ('dhio_sigit@bazdat.com','dhio_s','wJXjD4uY'),
    ('kemal_pahlepi@bazdat.com','kemal.pahlepi','6PYUVmXh'),
    ('munaa_mun@bazdat.com','munaa','LF4Ws8X6'),
    ('ata_mumtaz@bazdat.com','ataaa','jU6v9TxZ'),
    ('upi_upi@bazdat.com','upi_lestari','uaEQGZ9x'),
    ('karina_walid@bazdat.com','walid.karina','wtJm4kDr'),
    ('fira_sha@bazdat.com','shafirazz','VQtX8fqk'),
    ('risnitya_k@bazdat.com','risnitya_k','4TcRxsdW'),
    ('rahmadiati@bazdat.com','rahmadiati','uife8aAk'),
    ('sanind@bazdat.com','sanindd','fa09jHAd'),
	('reonaldo@bazdat.com','reonaldo','dahfaw9G'),
    ('frinfrin@bazdat.com','efrinze','ADHif7da');

INSERT INTO ADMIN VALUES
    ('revin_cavalera@bazdat.com','kasir'),
    ('aisha_a@bazdat.com','kasir'),
    ('nikho_xabi@bazdat.com','CEO'),
    ('dhio_sigit@bazdat.com','admin film');

INSERT INTO CUSTOMER VALUES
    ('rahmadiati@bazdat.com',NULL),
    ('mayandra_talitha@bazdat.com',100.0),
    ('rahel_venca@bazdat.com',20.0),
    ('kemal_pahlepi@bazdat.com',0.0),
    ('munaa_mun@bazdat.com',NULL),
    ('ata_mumtaz@bazdat.com',78.0),
    ('upi_upi@bazdat.com',34.0),
    ('karina_walid@bazdat.com',40.0),
    ('fira_sha@bazdat.com',30.0),
    ('risnitya_k@bazdat.com',45.0),
    ('sanind@bazdat.com',NULL),
    ('reonaldo@bazdat.com',NULL),
    ('frinfrin@bazdat.com',NULL);
	
INSERT INTO BIOSKOP VALUES
    ('bio002','Blok M XXI','Blok M Plaza Lantai 6. Jl. Bulungan No. 76 Kebayoran Baru Jakarta Selatan','09:00:00','21:00:00'),
    ('bio003','One Belpark XXI','One Bel Park Lt.2 Jln. Rumah Sakit Fatmawati No.1 Kel. Pondok Labu, Kec. Cilandak Jakarta Selatan','08:00:00','21:00:00'),
    ('bio004','Kalibata XXI','KALIBATA MALL Lantai 3 Jl. Raya Kalibata Jakarta Selatan','08:00:00','22:00:00'),
    ('bio005','Koja Trade Mall XXI','KOJA TRADE MALL XXI. Koja Trade Mall Lt. 2. Jln. Kramat Jaya No. 30. Jakarta Utara','09:00:00','19:00:00'),
    ('bio006','Gandaria City Premiere','Gandaria City Level 2. Jl Sultan Iskandar Muda, Kebayoran Lama. Jakarta Selatan.','09:30:00','17:00:00'),
    ('bio007','Kota Kasablangka Premiere','Kota Kasablanka Mall Lantai 2. Jl. Casablanca Kav 88. Jakarta Pusat','08:30:00','21:00:00'),
    ('bio008','Kelapa Gading Premiere','MALL KELAPA GADING 3, Lantai 3. Jl. Bulevar Kelapa Gading. Jakarta Utara','09:00:00','20:00:00'),
    ('bio009','Kemang Village Premiere','Mal Kemang Village Lantai 3. Jl. P. Antasari No. 36. Jakarta Selatan','10:00:00','20:00:00'),
    ('bio010','Baywalk Pluit Premiere','MAL BAYWALK PLUIT Lantai.6. Jln. Pluit Karang Ayu Blok B1 Utara. Jakarta Utara','17:00:00','20:00:00');

INSERT INTO FILM VALUES
    ('F0002','The Batman','Di tahun kedua memerangi kejahatan, Batman mengungkap korupsi besar di Gotham City yang menghubungkan keluarganya sendiri dan menghadapi pembunuh berantai yang dikenal sebagai Riddler.',9.0,120.0,'Action'),
    ('F0003','Garis Waktu','APRIL, seorang gadis dengan kegemaran menulis puisi bertemu dengan SENANDIKA, musisi yang berhasil mencuri perhatian dengan filosofi dan prinsipnya. April yang melihat potensi pada diri Sena -- nama panggilan yang April berikan pada Senandika -- pun memperkenalkannya pada SANYA, produser muda sahabat April yang selalu menjalani hidup dengan bebas.',8.0,100.0,'Drama'),
    ('F0004','Uncharted','Nathan Drake (Tom Holland) adalah seorang bartender yang pintar. Ia mengaku sebagai keturunan dari penjelajah kenamaan asal Inggris bernama Sir Francis Drake.',7.0,116.0,'Adventure'),
    ('F0005','Marley','Marley, seekor anjing bersahabat dengan Doni, guru matematika. Satu saat, Doni dipecat dari sekolah karena dia menerapkan sistem belajar yang berbeda. Akhirnya Doni membuka les matematika sendiri. Les yang awalnya sepi, akhirnya ramai. Disanalah Doni bertemu Vina dan muncul rasa suka diantara mereka berdua. Ketegangan terjadi ketika ada lomba matematika, Marley diculik oleh pedagang daging anjing. ',8.0,106.0,'Drama'),
    ('F0006','Ambulance','Dua perampok membajak mobil ambulance setelah aksi perampokan mereka tidak berjalan sesuai rencana.',6.0,120.0,'Crime'),
    ('F0007','The King''s Man','The King’s Man merupakan prekuel dari dua film sebelumnya. Maka dari itu, tentu alur ceritanya mundur jauh ke masa lalu, tepatnya saat awal mula berdirinya organisasi Kingsman. Selain itu, film The King’s Man mengangkat cerita tentang Orlando Oxford (Ralph Fiennes) dan anaknya, Conrad Oxford (Harris Dickinson), yang ingin menghentikan perang dunia pertama.',6.0,104.0,'Action'),
    ('F0008','Eternals','Baru-baru ini, Marvel Studios meluncurkan trailer abadi yang direncanakan untuk dipamalkan dari siaran pada November 2021 nanti. Film ini sangat diharapkan oleh pecinta Marvel. Adegan pertama tampak seperti pemandangan yang sedikit bergelombang dan melihat pantai di sana. Tampaknya ada koloni yang diduduki oleh sekelompok alumni.',8.0,103.0,'Adventure');

INSERT INTO STUDIO VALUES
    ('bio001','s02'),
    ('bio001','s03'),
    ('bio001','s04'),
    ('bio001','s05'),
    ('bio002','s01'),
    ('bio002','s02'),
    ('bio003','s01'),
    ('bio003','s02'),
    ('bio004','s01'),
    ('bio005','s01'),
    ('bio005','s02'),
    ('bio005','s03'),
    ('bio006','s01'),
    ('bio006','s02'),
    ('bio007','s01'),
    ('bio007','s02'),
    ('bio008','s01'),
    ('bio008','s02'),
    ('bio008','s03'),
    ('bio008','s04'),
    ('bio008','s05'),
    ('bio009','s01'),
    ('bio010','s01'),
    ('bio005','s04'),
    ('bio005','s05'),
    ('bio005','s06'),
    ('bio005','s07'),
    ('bio005','s08'),
    ('bio005','s09');

INSERT INTO JADWAL_FILM VALUES
    ('J002','s02','bio001','F0001','08:30:00','10:30:00'),
    ('J003','s03','bio001','F0001','08:30:00','10:30:00'),
    ('J004','s01','bio001','F0005','12:00:00','14:30:00'),
    ('J005','s02','bio001','F0005','12:00:00','14:30:00'),
    ('J006','s01','bio001','F0001','15:00:00','18:00:00'),
    ('J007','s02','bio001','F0001','15:00:00','18:00:00'),
    ('J008','s03','bio001','F0001','15:00:00','18:00:00'),
    ('J009','s04','bio001','F0001','15:00:00','18:00:00'),
    ('J010','s05','bio001','F0001','15:00:00','18:00:00'),
    ('J011','s01','bio001','F0005','18:30:00','21:00:00'),
    ('J012','s02','bio001','F0005','19:30:00','22:00:00'),
    ('J001','s01','bio002','F0003','09:00:00','11:30:00'),
    ('J002','s02','bio002','F0003','09:00:00','11:30:00'),
    ('J003','s01','bio002','F0003','12:00:00','15:00:00'),
    ('J004','s02','bio002','F0004','15:00:00','18:00:00'),
    ('J005','s01','bio002','F0004','18:30:00','21:00:00'),
    ('J001','s01','bio003','F0002','08:30:00','10:30:00'),
    ('J002','s02','bio003','F0002','08:30:00','10:30:00'),
    ('J003','s01','bio003','F0003','11:00:00','13:00:00'),
    ('J004','s02','bio003','F0003','12:00:00','14:30:00'),
    ('J001','s01','bio004','F0002','08:00:00','10:00:00'),
    ('J002','s01','bio004','F0002','10:30:00','12:00:00'),
    ('J003','s01','bio004','F0003','12:30:00','14:30:00'),
    ('J004','s01','bio004','F0003','15:00:00','17:30:00'),
    ('J005','s01','bio004','F0004','18:30:00','21:00:00'),
    ('J001','s01','bio005','F0001','09:00:00','11:30:00'),
    ('J002','s02','bio005','F0001','09:00:00','11:30:00'),
    ('J003','s03','bio005','F0001','09:00:00','11:30:00'),
    ('J004','s01','bio005','F0005','15:00:00','18:00:00'),
    ('J001','s01','bio006','F0001','09:30:00','11:30:00'),
    ('J002','s01','bio006','F0005','12:00:00','14:00:00'),
    ('J003','s02','bio006','F0001','09:30:00','11:30:00'),
    ('J004','s02','bio006','F0005','12:00:00','14:00:00'),
    ('J005','s01','bio006','F0004','15:00:00','17:00:00'),
    ('J001','s01','bio007','F0007','09:00:00','12:00:00'),
    ('J002','s02','bio007','F0007','09:00:00','12:00:00'),
    ('J003','s01','bio007','F0007','15:00:00','17:30:00'),
    ('J004','s02','bio007','F0007','15:00:00','17:30:00'),
    ('J001','s01','bio010','F0002','18:00:00','20:00:00'),
    ('J003','s01','bio002','F0004','13:00:00','15:00:00'),
    ('J004','s04','bio005','F0001','18:30:00','21:00:00'),
    ('J005','s05','bio005','F0001','12:30:00','14:30:00'),
    ('J006','s06','bio005','F0001','12:00:00','14:30:00'),
    ('J007','s07','bio005','F0001','11:00:00','13:00:00'),
    ('J008','s08','bio005','F0001','08:30:00','10:30:00'),
    ('J009','s09','bio005','F0001','15:00:00','17:30:00');
	
INSERT INTO TRANSAKSI_PEMBELIAN VALUES
    ('p00002','mayandra_talitha@bazdat.com',40000.0),
    ('p00003','rahel_venca@bazdat.com',105000.0),
    ('p00004','ata_mumtaz@bazdat.com',50000.0),
    ('p00005','upi_upi@bazdat.com',40000.0),
    ('p00006','karina_walid@bazdat.com',50000.0),
    ('p00007','fira_sha@bazdat.com',50000.0),
    ('p00008','risnitya_k@bazdat.com',100000.0),
    ('p00009','rahel_venca@bazdat.com',100000.0),
    ('p00010','ata_mumtaz@bazdat.com',100000.0),
    ('p00011','karina_walid@bazdat.com',300000.0),
    ('p00012','upi_upi@bazdat.com',35000.0),
    ('p00013','mayandra_talitha@bazdat.com',400000.0),
    ('p00014','rahel_venca@bazdat.com',30000.0),
    ('p00015','rahel_venca@bazdat.com',40000.0);
	
INSERT INTO TIKET VALUES
--  ('tick00001','J001','s01','bio001','F0001','M6',50000.0,'p00001'),
    ('tick00002','J001','s01','bio001','F0001','G7',50000.0,'p00001'),
    ('tick00003','J001','s01','bio002','F0003','F7',40000.0,'p00002'),
    ('tick00004','J003','s01','bio003','F0003','Q8',35000.0,'p00003'),
    ('tick00005','J003','s01','bio003','F0003','Q9',35000.0,'p00003'),
    ('tick00006','J003','s01','bio003','F0003','Q10',35000.0,'p00003'),
    ('tick00007','J004','s01','bio004','F0003','J8',50000.0,'p00004'),
    ('tick00008','J003','s01','bio002','F0004','J10',40000.0,'p00005'),
    ('tick00009','J002','s02','bio005','F0001','F6',50000.0,'p00006'),
    ('tick00010','J003','s03','bio005','F0001','F6',50000.0,'p00007'),
    ('tick00011','J002','s02','bio002','F0003','N17',40000.0,'p00008'),
    ('tick00012','J002','s02','bio002','F0003','N18',40000.0,'p00008'),
    ('tick00013','J002','s02','bio002','F0003','F19',40000.0,'p00009'),
    ('tick00014','J002','s02','bio002','F0003','F20',40000.0,'p00009'),
    ('tick00015','J002','s02','bio002','F0003','Q20',40000.0,'p00010'),
    ('tick00016','J002','s02','bio002','F0003','Q21',40000.0,'p00010'),
    ('tick00017','J003','s01','bio004','F0003','F4',50000.0,'p00011'),
    ('tick00018','J003','s01','bio004','F0003','F5',50000.0,'p00011'),
    ('tick00019','J003','s01','bio004','F0003','F6',50000.0,'p00011'),
    ('tick00020','J003','s01','bio004','F0003','F7',50000.0,'p00011'),
    ('tick00021','J003','s01','bio004','F0003','F8',50000.0,'p00011'),
    ('tick00022','J003','s01','bio004','F0003','F9',50000.0,'p00011'),
    ('tick00023','J003','s01','bio004','F0003','F10',35000.0,'p00012'),
    ('tick00024','J002','s02','bio005','F0001','L1',50000.0,'p00013'),
    ('tick00025','J003','s03','bio005','F0001','L2',50000.0,'p00013'),
    ('tick00026','J004','s04','bio005','F0001','L3',50000.0,'p00013'),
    ('tick00027','J005','s05','bio005','F0001','L4',50000.0,'p00013'),
    ('tick00028','J006','s06','bio005','F0001','G5',50000.0,'p00013'),
    ('tick00029','J007','s07','bio005','F0001','G6',50000.0,'p00013'),
    ('tick00030','J008','s08','bio005','F0001','G7',50000.0,'p00013'),
    ('tick00031','J009','s09','bio005','F0001','G8',50000.0,'p00013'),
    ('tick00032','J004','s02','bio003','F0003','J9',30000.0,'p00014'),
    ('tick00033','J004','s02','bio003','F0003','J9',40000.0,'p00015');
