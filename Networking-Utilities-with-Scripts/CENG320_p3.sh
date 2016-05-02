#!/bin/bash

printf "\t\t\t\t\t\tRakul Mahenthiran\n"
printf "\t\t\t\t\t\t822 240 982\n"
echo

echo Display maping of opened ports and summary of protocols ports are listening to on humber.ca and munro.humber.ca:
nmap -v humber.ca munro.humber.ca
echo

echo Display the scanned subnets of google.ca:
nmap -sL google.ca/24
echo

echo Display ports that are listening for TCP applications and count the ports open and listening for TCP applications on humber.ca:
nmap -sT humber.ca
echo "Number of ports open/listening on humber.ca: " `nmap -sT humber.ca | grep -r "open" | wc -l`
echo

echo Display ports that are listening for UDP applications and count the ports open and listening for UDP applications on humber.ca:
nmap -sU humber.ca
echo "Number of ports open/listening on humber.ca: " `nmap -sU humber.ca | grep -r "open" | wc -l`
echo

echo Display ping test foR google.ca using nmap utility: 
nmap -sP google.ca







