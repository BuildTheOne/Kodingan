.include "m8515def.inc"

.def var=r17
.def temp=r16

rjmp init

loop:
lpm r0, Z+
add r0, var
st Y+, r0
dec var
brne loop
ret

init:
ldi temp, low(RAMEND)
out SPL, temp
ldi temp, high(RAMEND)
out SPH, temp
ldi ZH, high(DATA*2)
ldi ZL, low(DATA*2)

getvar:
lpm r0, Z+
push r0
lpm
push r0
adiw ZL, 1
pop var
pop var

getdata:
lpm r0, Z+
mov YL, r0
rcall loop

forever:
rjmp forever

DATA:
.db $4, $6
.db $82, $83
.db $84, $85
.db $86, $87
.db $88, $89
.db $8A, $8B
