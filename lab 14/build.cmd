@echo off
set PATH=%PATH%;C:\Program Files\Java\jdk-25.0.2\bin
jar -cvfm build/program.jar res/manifest.mf -C classes .
pause