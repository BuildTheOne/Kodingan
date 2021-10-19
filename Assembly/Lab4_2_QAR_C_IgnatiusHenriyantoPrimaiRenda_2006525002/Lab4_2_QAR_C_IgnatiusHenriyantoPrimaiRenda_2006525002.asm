; Nama      : Ignatius Henriyanto Primai Renda
; NPM       : 2006525002
; Kelas     : C
; No. Soal  : 2
; Deskripsi : Program ini mencari luas permukaan balok dan volume balok
;             Luas Permukaan Balok disimpan di Register 3
;             Volume Balok disimpan di Register 4
;
.include "m8515def.inc"  ; include the 8515 definition file

.def panjang=R16    ; define panjang balok di R16 
.def lebar=R17      ; define lebar balok di R17
.def tinggi=R18     ; define tinggi balok di R18
.def temp=R19       ; define temp register di R19

; Luas permukaan balok dapat dicari dengan mengalikan panjang dan lebar, panjang dan tinggi, dan lebar dan tinggi, mengalikan masing-masing dengan 2, dan menambahkan semuanya
LuasPermukaanBalok:
	LDI temp, 0x02        ; Load nilai 2 ke dalam temp sesuai rumus
	MUL panjang, lebar    ; kalikan panjang dan lebar. Hasil dari operasi MUL akan masuk ke R0
	MUL R0, temp          ; kalikan hasilnya dengan 2. Hasil dari operasi MUL akan masuk ke R0
	MOV R20, R0           ; pindahkan hasilnya dari R0 ke R20
	MUL lebar, tinggi     ; kalikan lebar dan tinggi
	MUL R0, temp          ; kalikan hasilnya dengan 2
	MOV R21, R0           ; pindahkan hasilnya dari R0 ke R21
	MUL panjang,tinggi    ; kalikan panjang dan tinggi
	MUL R0, temp          ; kalikan hasilnya dengan 2
	MOV R22, R0           ; pindahkan hasilnya dari R0 ke R22
	ADD R20, R21          ; tambahkan hasil dari perhitungan pertama dan kedua. Hasil akan masuk ke R20
	ADD R20, R22          ; tambahkan hasil dari perhitungan sebelumnya dan ketiga. Hasil akan masuk ke R20
	MOV R3, R20           ; pindahkan hasilnya dari R19 ke R3

; Volume balok dapat dicari dengan mengalikan panjang, tinggi, dan lebar
VolumeBalok:
	MUL panjang, lebar    ; kalikan panjang dan lebar. Hasil dari operasi MUL akan masuk ke R0
	MUL R0, tinggi        ; kalikan hasil dari perhitungan sebelumnya dengan tinggi. Hasil dari operasi MUL akan masuk ke R0
	MOV R4, R0            ; pindahkan hasilnya dari R0 ke R4

; End digunakan untuk melakukan infinite loop dan "mengakhiri" program
End:
	RJMP End
