#!/bin/bash

printf "\t\t\t\t\t\tRakul Mahenthiran\n"
printf "\t\t\t\t\t\t822 240 982\n"
echo

echo File1 and File2 content concatinated to DataFile3.dat:
cat file1 file2 > DataFile3.dat
echo

echo Displaying content in DataFile3.dat:
cat DataFile3.dat
echo

echo Display all ports listening to unix connection:
netstat -lx
echo

echo Display all ports listening to UDP connection:
netstat -lu
echo

echo Display all statistics for UDP protocol on the client interface:
netstat -su
echo

echo Display detailed information on all the interfaces connected to the client:
netstat -i

