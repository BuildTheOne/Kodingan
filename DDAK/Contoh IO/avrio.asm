.include "m8515def.inc"

.def temp=r16
.def var=r17
rjmp INIT

DELAY:
ldi r18, 1
ldi r19, 0xFF
ldi r20, 0xFF
CONT:
dec r20
brne CONT
dec r19
brne CONT
dec r18
brne CONT
ret

INIT:
ldi temp, high(RAMEND)
out SPH, temp
ldi temp, low(RAMEND)
out SPL, temp
; Setting PORTA dan PORTB
ldi temp, 0b11111111
out DDRA, temp
ldi temp, 0xFF
out DDRB, temp

loop:
ldi temp, 0b10101010
out PORTA, temp
ldi temp, 0b11000000
out PORTB, temp
rcall DELAY

ldi temp, 0b01010101
out PORTA, temp
ldi temp, 0b00110000
out PORTB, temp
rcall DELAY

ldi temp, 0b10101010
out PORTA, temp
ldi temp, 0b00001100
out PORTB, temp
rcall DELAY

ldi temp, 0b01010101
out PORTA, temp
ldi temp, 0b00000011
out PORTB, temp
rcall DELAY

rjmp loop






