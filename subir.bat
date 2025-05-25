@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul

set "JAR=target\projeto-vaadin.jar"
set "LIB=%CD%\lib"
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.9.9-hotspot"
set "JAVA=%JAVA_HOME%\bin\java.exe"
set "JAVA_OPTS=-Djava.library.path=%LIB% -Dspring.devtools.restart.enabled=false"

echo.
echo =============================
echo   Iniciando Projeto Java
echo =============================

if not exist "%JAVA%" (
    echo [ERRO] Java nao encontrado em: %JAVA%
    echo Certifique-se de que o Java 17 esta instalado em %JAVA_HOME%.
    pause
    exit /b
)

echo Usando Java: "%JAVA%"
"%JAVA%" -version

if not exist "%JAR%" (
    echo [ERRO] JAR nao encontrado: %JAR%
    echo Execute 'mvn clean package' para gerar o JAR.
    pause
    exit /b
)

echo Verificando se a classe com.exemplo.Application esta presente no JAR...
jar tf "%JAR%" | findstr "com/exemplo/Application.class" >nul
if errorlevel 1 (
    echo [ERRO] Classe com.exemplo.Application nao encontrada no JAR: %JAR%
    echo Certifique-se de que Application.java esta em src/main/java/com/exemplo/ e recompile o projeto com 'mvn clean package'.
    pause
    exit /b
)

echo Executando com: %JAVA_OPTS%
"%JAVA%" %JAVA_OPTS% -jar "%JAR%"

echo.
echo =============================
echo   Execucao finalizada
echo =============================
pause