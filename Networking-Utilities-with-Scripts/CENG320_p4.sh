#!/bin/bash

printf "\t\t\t\t\t\tRakul Mahenthiran\n"
printf "\t\t\t\t\t\t822 240 982\n"
echo

echo Display hops from host to www.google.com:
traceroute www.google.com
echo

echo Diplay the number of hops from host to www.google.com: `traceroute www.google.com | grep -v "traceroute" | wc -l`
echo

echo Display the rows from /etc/passwd that has my user ID:  
grep -r 'mhnr0075' /etc/passwd
echo

echo Display the count of rows which my user ID appears in /etc/passwd: `grep -r 'mhnr0075' /etc/passwd | wc -l`
echo
