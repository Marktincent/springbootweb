@echo off & color 3d & setlocal enabledelayedexpansion 
  ::ipconfig>ip.txt
netstat -ano |findstr :8031>pid.txt
for /f "delims=" %%a in (pid.txt) do (
   for /f "tokens=1* delims=:" %%i in ('call echo %%a^|find /i "TCP"') do (
      echo %%a
      ::ready file content and write other text
      rem Echo %%a>>"text.txt"
   )
)
rem ready file content
set /P OEM=<pid.txt
rem substr file chars
set selpid=%OEM:~71,76%
echo %selpid%
for /f "eol=" %%a in (pid.txt) do set tmp=1
if defined tmp (echo %selpid%) else ( taskkill /f /pid %selpid% )

rmdir /s/q D:\Software\Jenkins\syn-data-jar\jiraprono-0.0.1.jar
md D:\Software\Jenkins\syn-data-jar\
xcopy .\jiraprono-0.0.1.jar D:\Software\Jenkins\syn-data-jar /e /y
start javaw -jar D:\Software\Jenkins\syn-data-jar\jiraprono-0.0.1.jar
exit
