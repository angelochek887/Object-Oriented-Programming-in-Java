@echo off
set CLASSPATH=.;classes;lib/*
java -cp "%CLASSPATH%" ua.edu.sumdu.j2se.pr9.PhoneDriver db.properties
pause