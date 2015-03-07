#!/bin/bash
clear
echo -e '\E[37;44m'
echo '**********************************************************************'
echo '*                                                                    *'
echo '*          SNUC -  Smart Network University Communications           *'
echo '*                 (GNU General Public Licens - GPL)                  *'
echo '* Mainteiners: Leandro Russo, Daniele Invincibile, Nicola Didomenico *'   
echo '*                                                                    *'                                                              
echo '**********************************************************************'
sleep 2
echo 'CRETATE folder snuc_russo_invincibile_didomenico'
mkdir snuc_russo_invincibile_didomenico
cd snuc_russo_invincibile_didomenico
echo 'CLONING BRANCH MASTER REPOSITORY 1, source ASTAH and LATEX in folder: project_se_snuc_astah_latex'
git clone https://github.com/gnunicky/project_se_snuc_astah_latex.git
sleep 1
echo 'CLONING BRANCH MASTER REPOSITORY 2, source JAVA: project_se_snuc_java'
sleep 1
git clone https://github.com/gnunicky/project_se_snuc_java.git
cd project_se_snuc_java/
sleep 1
echo 'CREATE BRANCH AND CHECKOUT REVISION ITERAZIONE 1 source JAVA in folder: project_se_snuc_java'
sleep 1
git checkout -b iterazione_1 origin/iterazione_1
sleep 1
echo 'CREATE BRANCH AND CHECKOUT REVISION ITERAZIONE 1 REFACTORED source JAVA in folder: project_se_snuc_java'
sleep 1
git checkout -b iterazione_1_refactored origin/iterazione_1_refactored
sleep 1
echo 'CREATE BRANCH AND CHECKOUT REVISION ITERAZIONE 2 source JAVA in folder: project_se_snuc_java'
sleep 1
git checkout -b iterazione_2 origin/iterazione_2
sleep 1
echo 'CREATE BRANCH AND CHECKOUT REVISION ITERAZIONE 3 source JAVA in folder: project_se_snuc_java'
sleep 1
git checkout -b iterazione_3 origin/iterazione_3
sleep 1
echo 'Shows all local and remote branches and set default branch master'
git checkout master
git branch -a
cd ..
echo 'OPERATIONS TERMINATED read README.TXT in the project_se_snuc_java and project_se_snuc_astah_latex'


