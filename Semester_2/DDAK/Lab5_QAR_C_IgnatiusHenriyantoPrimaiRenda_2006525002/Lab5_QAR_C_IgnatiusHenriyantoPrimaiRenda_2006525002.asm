.include "m8515def.inc"

.equ	DATA1	=$66	;TODO  start address of SRAM array #1
 						;$66 digunakan karena subroutine FUNGSI1 melakukan hitung mundur
.equ	DATA2	=$80	;start address of SRAM array #2
.def	temp	=r16	;temporary storage variable
.def	size	=r17	;size of block to be copied

START:
	ldi	temp,low(RAMEND)
	out	SPL,temp			;init Stack Pointer		
	ldi	temp,high(RAMEND)
	out	SPH,temp

INIT_BLOCK1:
	ldi	ZH,high(TABLE*2)
	ldi	ZL,low(TABLE*2)		;init Z-pointer
	ldi	YH,high(DATA1)
	ldi	YL,low(DATA1)		;init Y-pointer
	ldi	size,6
	rcall	FUNGSI1	;

INIT_BLOCK2:
	ldi	ZH,high(TABLE*2)
	ldi	ZL,low(TABLE*2)		;init Z-pointer
	ldi	XH,high(DATA2)
	ldi	XL,low(DATA2)		;init X-pointer
	ldi	size,6
	rcall	FUNGSI2	;


FOREVER:
	rjmp FOREVER

TABLE:
	.db	0,1		;start of table (6 bytes)
	.db	2,3
	.db	4,5
	

;TODO
FUNGSI1:
	lpm				; Ambil constant dari Z-Pointer dan load ke r0
	st	 -Y,r0		; Simpan nilai Z dari r0 ke Y-pointer (INIT-BLOCK 1 memakai Y-pointer) 
					; dan decrement Y-pointer data (Dimulai dari 66,65,...60 karena dilakukan hitung mundur)
	adiw ZL,1		; Increment Z-pointer data
	dec	 size		; Decrement ukuran size menjadi sisa size yang belum diambil
	brne FUNGSI1	; Jika size belum 0, loop
	ret				

FUNGSI2:
	lpm				; Ambil constant dari Z-Pointer dan load ke r0
	ldi  r18,2		; Load dan multiply nilai dari memory dengan 2
	mul  r0,r18
	st	 X+,r0		; Simpan nilai Z dari r0 ke X-pointer (INIT-BLOCK 2 memakai X-pointer)
					; dan increment X-pointer data
	adiw ZL,1		; Increment Z-pointer data
	dec	 size		; Decrement ukuran size menjadi sisa size yang belum diambil
	brne FUNGSI2	; Jika size belum 0, loop
	ret
