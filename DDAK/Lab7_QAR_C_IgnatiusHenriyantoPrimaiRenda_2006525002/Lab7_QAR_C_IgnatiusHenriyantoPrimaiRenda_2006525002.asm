.include "m8515def.inc"
.def temp=r17
.def mode=r18
.def counter=r19
.def reverse_mode=r20

; Lengkapi bagian ini
.org $00 rjmp START
.org $01 rjmp REVERSE
.org $02 rjmp CHECK_SWITCH_MODE
; todo
; Aktivasi penanda interrupt timer0 compare untuk jump ke CHECK_SWITCH_MODE
.org $0E rjmp CHECK_SWITCH_MODE

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
; setting TCCR0 -> Timer 0 Compare Clock, clk/1024 -> 101
ldi temp, (1<<CS02)|(1<<CS00)
out TCCR0, temp
; setting TIFR -> Timer 0 dan Output Compare Flag, OCF0
ldi temp, (1<<OCF0)
out TIFR, temp
; setting TIMSK -> Enable Timer 0 compare, OCIE0 -> 0b00000001
ldi temp, 1<<OCIE0
out TIMSK, temp
; setting OCR0 -> Setting value untuk di-compare, 230=E6
ldi temp, 0xE6
out OCR0, temp
; Aktivasi interrupt
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
; Mode A adalah konfigurasi LED lampu hijau di jalan Alpukat
ModeA :
ldi r16, 0b00000101 ; todo
out PORTA, r16
ldi r16, 0b00000010 ; todo
out PORTB, r16
ldi r16, 0b00000010 ; todo
out PORTC, r16
rjmp MAIN

; Lengkapi bagian ini
; Mode B adalah konfigurasi LED lampu hijau di jalan Beri
ModeB:
ldi r16, 0b00000011 ; todo
out PORTA, r16
ldi r16, 0b00000101 ; todo
out PORTB, r16
ldi r16, 0b00000010 ; todo
out PORTC, r16
rjmp MAIN

; Lengkapi bagian ini
; Mode C adalah konfigurasi LED lampu hijau di jalan Ceri
ModeC:
ldi r16, 0b00000011 ; todo
out PORTA, r16
ldi r16, 0b00000010 ; todo
out PORTB, r16
ldi r16, 0b00000101 ; todo
out PORTC, r16
rjmp MAIN

; Lengkapi bagian ini
; Tambahan REVERSE subroutine yang tidak ada di template
; REVERSE digunakan untuk aktivasi interrupt yang mengakibatkan lampu berjalan dalam mode terbalik (reverse)
REVERSE:
ldi temp, 0x01
eor reverse_mode, temp
reti

; CHECK_SWITCH_MODE berfungsi untuk mengecek apakah Reverse mode aktif (bernilai 1)
; Jika bernilai 0, aktivasi SWITCH_MODE_1, jika tidak aktivasi SWITCH_MODE_2
; CHECK_SWITCH_MODE dapat diaktivasi dengan external interrupt maupun timer interrupt yang dicompare dengan nilai 230
CHECK_SWITCH_MODE:
; todo
cpi reverse_mode, 0x00
breq SWITCH_MODE_1
cpi reverse_mode, 0x01
breq SWITCH_MODE_2

; SWITCH_MODE_1 adalah konfigurasi gerakan pada mode normal (A-B-C)
; Cara kerja: increment nilai mode (maka gerakan akan menjadi A-B-C), jika sudah mencapai 03 (setelah C), ubah mode ke nilai 00
SWITCH_MODE_1:
; todo
inc mode
cpi mode, 0x03
breq RESET_1
reti
RESET_1:
ldi mode, 0x00
reti

; SWITCH_MODE_2 adalah konfigurasi gerakan pada mode reverse (C-B-A)
; Cara kerja: Jika sudah mencapai 00 (A), ubah mode ke nilai 02 (C), lalu decrement nilai mode (maka gerakan akan menjadi C-B-A)
SWITCH_MODE_2:
; todo
cpi mode, 0x00
breq RESET_2
dec mode
reti
RESET_2:
ldi mode, 0x02
reti

Exit_Int:
reti
