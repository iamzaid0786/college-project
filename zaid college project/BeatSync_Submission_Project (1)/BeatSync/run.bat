@echo off
setlocal
cd /d "%~dp0"
where mvn >nul 2>nul
if errorlevel 1 (
 echo Maven was not found. Install Maven and add its bin folder to PATH.
 pause
 exit /b 1
)
echo Starting BeatSync at http://localhost:8080
call mvn spring-boot:run
pause
