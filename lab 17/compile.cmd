@echo off
chcp 65001 > nul
set PATH=%PATH%;C:\Program Files\Java\jdk-25.0.2\bin
javac -encoding UTF-8 -d classes -sourcepath sources sources/ua/edu/sumdu/j2se/pr9/*.java
pause