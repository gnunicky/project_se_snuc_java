/**
 * SNUC  is a program written in Java SE (version 1.8.0_31) during a project of 
 * course Software Engineering in University of Catania academic year 2014-15.
 * SNUC is Smart Network University Communications.
 * 
 * Copyright (C) 2015 onwards Leandro Russo (leandrorusso90@gmail.com)
 * Copyright (C) 2015 onwards Invincibile Daniele (d.invincibile@gmail.com)
 * Copyright (C) 2015 onwards Nicola Didomenico (nicola.didomenico@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public Licens along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

1. Come prima operazione bisogna scaricare il software con i relativi sorgenti:
   a) digitare da terminale:
       wget https://github.com/gnunicky/project_se_snuc_java/blob/master/download_snuc_repository.sh 
      digitare da terminale:
	   chmod +x download_snuc_repository.sh
	  avviare lo script, digitando da terminale:
	   ./download_snuc_repository.sh
   oppure
   b) digitare da terminale:
    git clone https://github.com/gnunicky/project_se_snuc_java.git
   oppure
   c) digitare da terminale: 
    wget https://github.com/gnunicky/project_se_snuc_java/archive/iterazione_1.zip
2. Sono presenti le seguenti cartelle:
   - ../config: in tale cartella sono presenti 2 file testo: il Config.txt, contenente il tipo di comunicazione (TCP) e il
                il file Room.txt contenente la lista delle stanze presenti nel server;
   - ../doc: in tale cartella è presente la documentazione realizzata in javadocs 8 e una guida utente;
   - ../src: in tale cartella sono presenti i sorgenti dell'ultima iterazione dell'applicazione;
   - ../test: in tale cartella sono presenti i test realizzati in junit 4;
3. Per chi visualizza il codice sorgente, bisogna avere installato Netbeans (IDE), clonando o importando i vari repository
   delle varie iterazioni. Per avviare il server basta lanciare il file SnucServerMain.java presente nel package SnucServer, invece 
   per avviare i client occorre lanciare SnucMain.java presente nel package. Inoltre è presente un package test con i vari test e
   un package docs in cui è presente la javadocs e una guida utente.

N.B.
REQUISITI DEL CLIENT: per un corretto avvio del client deve essere presente un file testo Config.txt nella cartella ../config. Nel file è presente
                      la comunicazione utilizzata che è di tipo TCP. Naturalmente il server dovrà comunicare con lo stesso tipo di protocollo del
                      client.

REQUISITI DEL SERVER: per un corretto avvio del server è necessario inserire nella cartella /config dei file di testo chiamati Room.txt e Config.txt.
- Nel primo file saranno presenti la lista delle stanze caricate dall'amministratore del sistema. Secondo la medesima sintassi:
  #Room1
  #Room2
  [...]
  #RoomN 
- Nel secondo file è presente il tipo di comunicazione utilizzata. Come prima riga del file occorre inserire: TCP. Naturalmente il client dovrà
  comunicare con lo stesso tipo di protocollo del server. 
 
REQUISITI SOFTWARE:
- Java Runtime Environment Version 8 e/o Java Platform (JDK) 8;
- NetBeans IDE 8.0.2;
- Platforms: Windows, Mac, Linux


