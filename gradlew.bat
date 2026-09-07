@echo off
setlocal enabledelayedexpansion
set GRADLE_VERSION=9.1.0
if "%GRADLE_USER_HOME%"=="" (
  set BASE_DIR=%USERPROFILE%\.gradle\bootstrap-wrapper
) else (
  set BASE_DIR=%GRADLE_USER_HOME%\bootstrap-wrapper
)
set DIST_DIR=%BASE_DIR%\gradle-%GRADLE_VERSION%
set ZIP_FILE=%BASE_DIR%\gradle-%GRADLE_VERSION%-bin.zip
set GRADLE_BIN=%DIST_DIR%\gradle-%GRADLE_VERSION%\bin\gradle.bat

if not exist "%GRADLE_BIN%" (
  if not exist "%BASE_DIR%" mkdir "%BASE_DIR%"
  if not exist "%DIST_DIR%" mkdir "%DIST_DIR%"
  if not exist "%ZIP_FILE%" (
    echo Downloading Gradle %GRADLE_VERSION%...
    powershell -NoProfile -ExecutionPolicy Bypass -Command "Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip' -OutFile '%ZIP_FILE%'"
    if errorlevel 1 exit /b 1
  )
  echo Installing Gradle %GRADLE_VERSION%...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "if (Test-Path '%DIST_DIR%\gradle-%GRADLE_VERSION%') { Remove-Item -Recurse -Force '%DIST_DIR%\gradle-%GRADLE_VERSION%' }; Expand-Archive -Path '%ZIP_FILE%' -DestinationPath '%DIST_DIR%' -Force"
  if errorlevel 1 exit /b 1
)

call "%GRADLE_BIN%" %*
endlocal
