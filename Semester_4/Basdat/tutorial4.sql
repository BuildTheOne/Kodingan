SET search_path TO sibioskop;

-- I. Stored Procedure dan Function
-- -- Contoh 1
CREATE OR REPLACE FUNCTION
SIBIOSKOP.total_price(id_pembelian_in VARCHAR(10))
RETURNS INTEGER AS
$$
DECLARE
total INTEGER;
BEGIN
SELECT COALESCE(SUM(harga), 0) AS sum_harga INTO total
FROM TIKET
WHERE pembelian = id_pembelian_in;

UPDATE TRANSAKSI_PEMBELIAN
SET total_harga = total
WHERE id_pembelian = id_pembelian_in;

RETURN total;
END;
$$
LANGUAGE plpgsql;

-- -- Contoh 2
UPDATE TRANSAKSI_PEMBELIAN
SET total_harga = 0
WHERE id_pembelian = 'p00003';

SELECT total_harga FROM TRANSAKSI_PEMBELIAN WHERE id_pembelian = 'p00003';

SELECT total_price('p00003');

SELECT total_harga FROM TRANSAKSI_PEMBELIAN WHERE id_pembelian = 'p00003';

-- -- Contoh 3
SELECT total_price(id_pembelian) FROM TRANSAKSI_PEMBELIAN;

-- -- Contoh 4
CREATE OR REPLACE FUNCTION update_all_total_prices()
RETURNS void AS
$$
DECLARE
temp_row RECORD;
total INTEGER;
BEGIN
FOR temp_row IN
SELECT * FROM TRANSAKSI_PEMBELIAN
LOOP
SELECT COALESCE(SUM(harga), 0) AS sum_harga
INTO total
FROM TIKET
WHERE pembelian = temp_row.id_pembelian;

UPDATE TRANSAKSI_PEMBELIAN
SET total_harga = total
WHERE id_pembelian = temp_row.id_pembelian;
END LOOP;
END;
$$
LANGUAGE plpgsql;

SELECT update_all_total_prices();

SELECT * FROM TRANSAKSI_PEMBELIAN LIMIT 10;

-- -- Contoh 5
DROP FUNCTION total_price(id_pembelian_in VARCHAR(10));

-- II. PostgreSQL Triggers
-- -- Contoh 6
ALTER TABLE CUSTOMER
ALTER COLUMN reward_point
SET DEFAULT NULL;

CREATE OR REPLACE FUNCTION accumulate_reward()
RETURNS trigger AS
$$
DECLARE
old_reward_point INTEGER;
cust_id VARCHAR(32);
BEGIN
SELECT COALESCE(NEW."reward_point",0), TP.id_customer
INTO old_reward_point, cust_id
FROM CUSTOMER C, TRANSAKSI_PEMBELIAN TP
WHERE NEW.pembelian = TP.id_pembelian
AND TP.id_customer = C.email;

UPDATE CUSTOMER
SET reward_point = reward_point + FLOOR(NEW.harga/10000)
WHERE email = cust_id;

RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- -- Contoh 7
CREATE TRIGGER trigger_accumulate_reward
AFTER INSERT ON TIKET
FOR EACH ROW
EXECUTE PROCEDURE accumulate_reward();

DROP TRIGGER trigger_accumulate_reward ON tiket;

-- -- Contoh 8
SELECT * FROM CUSTOMER;
INSERT INTO TIKET VALUES ('tick00034', 'J001', 's01', 'bio001', 'F0001', 'M6', 43000, 'p00012');
SELECT * FROM CUSTOMER;
