.include "m8515def.inc"

.equ	DATA1	=..		;TODO  start address of SRAM array #1
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
	


FUNGSI2:
	
