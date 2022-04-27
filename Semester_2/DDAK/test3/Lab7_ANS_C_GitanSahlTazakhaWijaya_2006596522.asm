.include "m8515def.inc"
.def temp=r17
.def mode=r18
.def counter=r19
.def reverse_mode=r20

; Lengkapi bagian ini
.org $00
rjmp START
.org $01
rjmp REVERSE
.org $02
rjmp CHECK_SWITCH_MODE
.org $0E
rjmp CHECK_SWITCH_MODE

START:
ldi temp,low(RAMEND)
out SPL,temp
ldi temp,high(RAMEND)
out SPH,temp
ldi temp,$ff
out DDRA, temp ; Set port A as output
out DDRB, temp ; Set port B as output
out DDRC, temp ; Set port C as output

SETUP_EXT_INTERRUPT:
ldi r16, 0b00001010
out MCUCR, r16
ldi r16, 0b11000000
out GICR, r16

; Lengkapi bagian ini
SETUP_TIMER_INTERRUPT:
ldi temp, (1 << CS02) | (1 << CS00)
out TCCR0, temp
ldi temp, (1 << OCF0)
out TIFR, temp
ldi temp, (1 << OCIE0)
out TIMSK, temp
ldi temp, 230
out OCR0, temp
sei

MAIN:
cpi mode, 0x00
breq ModeA
cpi mode, 0x01
breq ModeB
cpi mode, 0x02
breq ModeC
rjmp MAIN

; Lengkapi bagian ini
ModeA :
ldi r16, 0b00000101
out PORTA, r16
ldi r16, 0b00000010
out PORTB, r16
ldi r16, 0b00000010
out PORTC, r16
rjmp MAIN

; Lengkapi bagian ini
ModeB:
ldi r16, 0b00000011
out PORTA, r16
ldi r16, 0b00000101
out PORTB, r16
ldi r16, 0b00000010
out PORTC, r16
rjmp MAIN

; Lengkapi bagian ini
ModeC:
ldi r16, 0b00000011
out PORTA, r16
ldi r16, 0b00000010
out PORTB, r16
ldi r16, 0b00000101
out PORTC, r16
rjmp MAIN

; Lengkapi bagian ini
CHECK_SWITCH_MODE:
cpi reverse_mode, 0x00
breq SWITCH_MODE_1
cpi reverse_mode, 0x01
breq SWITCH_MODE_2

SWITCH_MODE_1:
inc mode
cpi mode, 0x03
breq RESET_1
reti

RESET_1:
ldi mode, 0x00
reti

SWITCH_MODE_2:
cpi mode, 0x00
breq RESET_2
dec mode
reti

RESET_2:
ldi mode, 0x02
reti

REVERSE:
ldi temp, 0x01
eor reverse_mode, temp
reti

Exit_Int:
reti
