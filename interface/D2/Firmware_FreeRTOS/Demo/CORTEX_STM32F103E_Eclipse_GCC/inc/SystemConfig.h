/*

	This file is part of the OOBD.org distribution.

	OOBD.org is free software; you can redistribute it and/or modify it
	under the terms of the GNU General Public License (version 2) as published
	by the Free Software Foundation and modified by the FreeRTOS exception.

	OOBD.org is distributed in the hope that it will be useful, but WITHOUT
	ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
	FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
	more details.

	You should have received a copy of the GNU General Public License along
	with FreeRTOS.org; if not, write to the Free Software Foundation, Inc., 59
	Temple Place, Suite 330, Boston, MA  02111-1307  USA.


	1 tab == 4 spaces!

	Please ensure to read the configuration and relevant port sections of the
	online documentation.


	OOBD is using FreeRTOS (www.FreeRTOS.org)

*/

/* Define to prevent recursive inclusion -------------------------------------*/
#ifndef __SystemConfig_H
#define __SystemConfig_H

/* -------- Create Global variables ---------------------------------------------------*/
void 		System_Configuration(void);
void        RCC_Configuration(void);
void        GPIO_Configuration(void);
void        NVIC_Configuration(void);
void        SysTick_Configuration(void);
void        ADC_Configuration(void);
/* void        SPI_Configuration(void);  */

#define USART1_BAUDRATE_DEFAULT USART1_BAUDRATE_115200
#define USART1_BAUDRATE_4800    4800
#define USART1_BAUDRATE_9600    9600
#define USART1_BAUDRATE_19200   19200
#define USART1_BAUDRATE_38400   38400
#define USART1_BAUDRATE_57600   57600
#define USART1_BAUDRATE_115200  115200
#define USART1_BAUDRATE_230400  230400
#define USART1_BAUDRATE_460800  460800

#endif  /* __SystemConfig_H */
