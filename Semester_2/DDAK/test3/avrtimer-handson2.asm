.include "m8515def.inc"
.def temp=r16
.def var=r17
.def var2=r26

.org $00
rjmp INIT
.org $07
rjmp TIMER_OV0_HANDLER
.org $0E
rjmp TIMER_CP0_HANDLER

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
; Setting PORTA, PORTB, PORTC
ldi temp, 0b11111111
out DDRA, temp
ldi temp, 0b11111111
out DDRB, temp
ldi temp, 0b11111111
out DDRC, temp
; Setting Timer Interrupt
;ldi temp, 0b00000101
; 1 << (CS02) = 0b00000001 << 2 = 00000100
; 1 << (CS00) = 0b00000001 << 0 = 00000001
ldi temp, (1 << CS02) | (1 << CS00)
out TCCR0, temp
ldi temp, (1 << TOV0) | (1 << OCF0)
out TIFR, temp
ldi temp, (1 << TOIE0) | (1 << OCIE0)
out TIMSK, temp
ldi temp, 100
out OCR0, temp
sei

ldi var, 0x00
ldi var2,0xF0

loop:
ldi temp, 0b10101010
out PORTA, temp
rcall DELAY
ldi temp, 0b01010101
out PORTA, temp
rcall DELAY
rjmp loop


TIMER_OV0_HANDLER:
ldi temp, 0b11111111
eor var, temp
out PORTB, var
reti


TIMER_CP0_HANDLER:
ldi temp, 0b11111111
eor var2, temp
out PORTC, var2
reti
