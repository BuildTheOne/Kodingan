;====================================================================
; Processor		: ATmega8515
; Compiler		: AVRASM
;====================================================================

;====================================================================
; DEFINITIONS
;====================================================================

.include "m8515def.inc"
.def temp = r16 ; temporary register
.def led_data = r17
;todo

;====================================================================
; RESET and INTERRUPT VECTORS
;====================================================================

.org $00
rjmp MAIN
;todo

;====================================================================
; CODE SEGMENT
;====================================================================

MAIN:

INIT_STACK:
	ldi temp, low(RAMEND)
	ldi temp, high(RAMEND)
	out SPH, temp

INIT_LED:
	ser temp ; load $FF to temp
	out DDRC,temp ; Set PORTC to output

INIT_INTERRUPT:
	ldi temp, ;todo
	out MCUCR,temp
	ldi temp, ;todo
	out GICR,temp
	sei

LED_LOOP_MASUK:
	ldi led_data,0x00
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY

    ;todo

	rjmp LED_LOOP_MASUK

LED_LOOP_KELUAR:
	ldi led_data,0x00
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY
	ldi led_data, ;todo
	out PORTC,led_data ; Update LEDS
	rcall DELAY

    ;todo

	rjmp LED_LOOP_KELUAR

DELAY:
    ;todo

GANTI_ARAH:
    ;todo

GANTI_DELAY:
    ;todo

DELAY_01:
; Generated by delay loop calculator
; at http://www.bretmulvey.com/avrdelay.html
;
; DELAY_CONTROL 40 000 cycles
; 5ms at 8.0 MHz

	ldi  r18, 52
	ldi  r19, 242
	
L1: 
	dec  r19
	brne L1
	dec  r18
	brne L1
	nop
	ret

DELAY_02:
; Generated by delay loop calculator
; at http://www.bretmulvey.com/avrdelay.html
;
; Delay 160 000 cycles
; 20ms at 8.0 MHz

	ldi  r18, 208
	ldi  r19, 202


L2: 
	dec  r19
	brne L2
	dec  r18
	brne L2
	nop
	ret