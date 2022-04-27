; Nama      : Ignatius Henriyanto Primai Renda
; NPM       : 2006525002
; Kelas     : C
; No. Soal  : 1
;
.include "m8515def.inc"  ; include the 8515 definition file

.def sisi=R16            ; define sisi ke register 16
.def tinggiSegitiga=R17  ; define tinggiSegitiga ke register 17
.def tinggiPrisma=R18    ; define tinggiPrisma ke register 18
.def temp=R19            ; define temp register ke register 19

; Keliling segitiga sama sisi dapat dicari dengan mengalikan sisi dengan 3
; sisi di-load dari register 16 (sisi)
; 3 di-define dan di-load dari register 19 (temp)
KelilingAlas:
	LDI temp, 0x03   ; load nilai 3 ke temp register
	MUL sisi, temp   ; kalikan sisi (yang nilainya 10) dengan 3 memakai mul. Hasilnya akan ada di R0
	MOV R3, R0       ; Pindahkan nilai hasil dari mul dari R0 ke R3

; Luas alas segitiga sama sisi yang diketahui sisi dan tinggi dapat dicari dengan 
; mengalikan sisi dengan tinggi lalu dibagi 2
; sisi di-load dari R16 (sisi)
; tinggi segitiga di-load dari R17 (tinggiSegitiga)
; pembagian dilakukan dengan teknik pengurangan berulang
LuasAlas:
	MUL sisi, tinggiSegitiga   ; Mengalikan sisi dengan tinggi segitiga
	MOV temp, R0               ; Hasil perkalian dari mul R0 dipindah ke temp. 
                                   ; Nilai temp disini digunakan sebagai yang dibagi (dividend)
	; Dari sini, dilakukan pembagian dengan metode pengurangan berulang
	LDI R20, 0x02              ; Load nilai 2 ke R20 sebagai pembagi (divisor)
	LDI R21, 0                 ; Load nilai 1 ke R21 sebagai counter yang akan dijadikan hasil pembagian (result)
	loop:
		SUB temp, R20      ; Pengurangan antara temp dengan R20 (2)
		INC R21            ; Increment nilai R21
		CP temp, R20       ; Compare nilai temp dengan R20 (2)
		BRSH loop          ; branch dan loop jika nilai temp lebih dari atau sama dengan R20 (2)
	MOV R4, R21            ; Pindahkan nilai dari R21 (result) ke R4

; Luas permukaan prisma segitiga dapat dicari dengan mengalikan luas alas dengan 2,
; mengalikan sisi tegak dengan 3, dan menambahkan keduanya
LuasPermukaanPrisma:
	LDI temp, 0x02             ; load nilai temp dengan 2
	MUL R4, temp               ; kalikan luas alas dengan temp (2)
	MOV R20, R0                ; pindahkan nilai hasil mul dari R0 ke R20 (nilai sementara)
	LDI temp, 0x03             ; load nilai temp dengan 3
	MUL sisi, tinggiPrisma     ; kalikan sisi dengan tinggi prisma
	MUL R0, temp               ; kalikan lagi hasilnya dengan temp (3)
	MOV R21, R0                ; pindahkan nilai hasil mul dari R0 ke R21 (nilai sementara)
	ADD R20, R21               ; tambahkan nilai tutup prisma (R20) dengan sisi tegak prisma (R21)
	MOV R5, R20                ; pindahkan nilai penjumlahan dari R20 ke R5

; Volume prisma dapat dicari dengan mengalikan alas prisma dengan tinggi prisma
VolumePrisma:
	MUL R4, tinggiPrisma       ; kalikan luas alas (R4) dan tinggiPrisma
	MOV R6, R0                 ; pindahkan hasil perkalian dari R0 ke R6

; End digunakan untuk melakukan infinite loop dan "mengakhiri" program
End:
	RJMP End
