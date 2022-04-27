.include "m8515def.inc"
.def temp=r16
.def var=r17

.org $00
rjmp INIT
.org $01
rjmp EXT_INT0_HANDLER
.org $02
rjmp EXT_INT1_HANDLER

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

DELAY2:
ldi r21, 1
ldi r22, 0xFF
ldi r23, 0xFF
CONT2:
dec r23
brne CONT2
dec r22
brne CONT2
dec r21
brne CONT2
ret

INIT:
ldi temp, high(RAMEND)
out SPH, temp
ldi temp, low(RAMEND)
out SPL, temp
; Setting PORTA dan PORTB, PORTC
ldi temp, 0b11111111
out DDRA, temp
ldi temp, 0xFF
out DDRB, temp
ldi temp, 0xFF
out DDRC, temp
;setting interrupt
ldi temp, 0b11000000
out GICR, temp
ldi temp, 0b00001111
out MCUCR, temp
sei

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



EXT_INT0_HANDLER:
ldi temp, 0b10101010
out PORTC, temp
rcall DELAY2
ldi temp, 0b01010101
out PORTC, temp
rcall DELAY2
ldi temp, 0b10101010
out PORTC, temp
rcall DELAY2
ldi temp, 0b01010101
out PORTC, temp
rcall DELAY2
ldi temp, 0b10101010
out PORTC, temp
rcall DELAY2
ldi temp, 0b01010101
out PORTC, temp
rcall DELAY2
reti

EXT_INT1_HANDLER:
ldi temp, 0b11110000
out PORTC, temp
rcall DELAY2
ldi temp, 0b00001111
out PORTC, temp
rcall DELAY2
ldi temp, 0b11110000
out PORTC, temp
rcall DELAY2
ldi temp, 0b00001111
out PORTC, temp
rcall DELAY2
ldi temp, 0b11110000
out PORTC, temp
rcall DELAY2
ldi temp, 0b00001111
out PORTC, temp
rcall DELAY2
reti
