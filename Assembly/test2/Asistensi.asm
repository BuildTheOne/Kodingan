.include "m8515def.inc"
.def temp = r16 ; temporary register
.def led_data = r17
.def counter_port = r20

.org $00
rjmp MAIN
.org $01
rjmp GANTI_LED

MAIN:

INIT_STACK:
	ldi temp, low(RAMEND)
	ldi temp, high(RAMEND)
	out SPH, temp

INIT_LED:
	ser temp ; load $FF to temp
	out DDRC,temp ; Set PORTC to output
	out DDRA, temp ; Set PORTA to output

INIT_INTERRUPT:
	ldi temp,0b00000010 ; soal
	out MCUCR,temp
	ldi temp,0b01000000 ; soal
	out GICR,temp
	sei

LED_PORTA:
	ldi led_data,0b10101010
	out PORTA,led_data
	rcall DELAY
	ldi led_data,0b01010101
	out PORTA,led_data
	rcall DELAY
	cpi counter_port, 0xFF
	breq LED_PORTC
	rjmp LED_PORTA

LED_PORTC:
	ldi led_data,0b10101010
	out PORTC,led_data
	rcall DELAY
	ldi led_data,0b01010101
	out PORTC,led_data
	rcall DELAY
	cpi counter_port, 0x00
	breq LED_PORTA
	rjmp LED_PORTC

GANTI_LED:
	ldi temp, 0b11111111
	EOR counter_port, temp
	cpi counter_port, 0x00
	breq LED_PORTA
	brne LED_PORTC

DELAY:
	ldi  r18, 208
	ldi  r19, 202

L2: 
	dec  r19
	brne L2
	dec  r18
	brne L2
	nop
	reti
