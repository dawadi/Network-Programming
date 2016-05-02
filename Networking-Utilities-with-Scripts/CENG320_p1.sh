#!/bin/bash

printf "\t\t\t\t\t\tRakul Mahenthiran\n" 
printf "\t\t\t\t\t\t822 240 982\n"
echo

echo "Today's date is:" `date`
echo

echo "Users currently logged in and user count: " `who -q`
echo

echo Display processes running: 
ps -u mhnr0075
echo

echo Long listing of files in current directory: 
ls -la | grep '^-'
echo

echo "Number of Subdirectories in current Directory: " `ls -la | grep '^d' | wc -l`
echo "Number of Files in current Directory: " `ls -la | grep '^-' | wc -l`  
