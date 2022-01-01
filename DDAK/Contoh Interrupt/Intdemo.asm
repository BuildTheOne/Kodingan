.include "m8515def.inc"

;.org $00
rjmp main
;.org $01
rjmp ext_int0
;.org $02
rjmp ext_int1
.org $0D
rjmp ext_int2

main:
	ldi r16, low(RAMEND)
	out SPL, r16
	ldi r16, high(RAMEND)
	out SPH, r16

	ldi r16, $ff
	out DDRA, r16	; PortA sbg output
	ldi r16, $00
	out DDRD, r16	; PortD sbg input
	out DDRE, r16	; PortE sbg input
	out PORTD, r16
	out PORTE, r16
	ldi r17,0b00001010 	
	out MCUCR,r17		; falling edge activated

enableinterrupt:
	ldi r17,0b11100000	; enabled for INT_0+1+2	 
	out GICR,r17		; 
	sei

loop: // dummy codes for demo
	ldi r20,34
	mov r3,r20
	mov r4,r3
	rjmp loop

ext_int0: ; service for Int0
	push r16
	in r16,sreg
	push r16

	sei

	ldi r17,$f0
	out PORTA,r17
	ldi r17,$f0
	out PORTA,r17	
	ldi r17,$f0
	out PORTA,r17	
	ldi r17,$f0
	out PORTA,r17	
	ldi r17,$f0
	out PORTA,r17	
	ldi r17,$f0
	out PORTA,r17

	cli
	pop r16
	out sreg,r16
	pop r16
	reti

ext_int1: ; service for Int1
	push r16
	in r16,sreg
	push r16

	sei
	ldi r17,$0f
	out PORTA,r17
	ldi r17,$0f
	out PORTA,r17
	ldi r17,$0f
	out PORTA,r17
	ldi r17,$0f
	out PORTA,r17
	ldi r17,$0f
	out PORTA,r17
	ldi r17,$0f
	out PORTA,r17

	cli
	pop r16
	out sreg,r16
	pop r16
	reti

ext_int2: ; service for Int2
	push r16
	in r16,sreg
	push r16

	ldi r17,$ff
	out PORTB,r17

	pop r16
	out sreg,r16
	pop r16
	reti
