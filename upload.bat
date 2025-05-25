@echo off
setlocal EnableDelayedExpansion

set "FILE_IO_URL=https://transfer.sh"
set "TEMP_FILE=temp_response_%RANDOM%.txt"
set "ZIP_FILE=temp_archive_%RANDOM%.zip"

set /p FILE_TO_UPLOAD=Digite o nome do arquivo para enviar (ex: arquivo.txt): 

if "!FILE_TO_UPLOAD!"=="" (
    echo Erro: Nenhum arquivo especificado.
    pause
    exit /b
)

if not exist "!FILE_TO_UPLOAD!" (
    echo Erro: O arquivo "!FILE_TO_UPLOAD!" não existe.
    pause
    exit /b
)

where curl >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo Erro: O comando curl não foi encontrado.
    pause
    exit /b
)

echo Compactando o arquivo "!FILE_TO_UPLOAD!"...
powershell -command "Compress-Archive -Path '!FILE_TO_UPLOAD!' -DestinationPath '%ZIP_FILE%'"

if not exist %ZIP_FILE% (
    echo Erro: O arquivo compactado %ZIP_FILE% não foi criado.
    pause
    exit /b
)

echo Enviando arquivo compactado...
curl --upload-file "%ZIP_FILE%" "%FILE_IO_URL%/%ZIP_FILE%" > %TEMP_FILE% 2> curl_error.txt

if %ERRORLEVEL% neq 0 (
    echo Erro ao enviar. Veja curl_error.txt.
    type curl_error.txt
    del %ZIP_FILE%
    del %TEMP_FILE%
    pause
    exit /b
)

set /p LINK=<%TEMP_FILE%
echo Arquivo enviado com sucesso!
echo Link gerado: !LINK!

del %TEMP_FILE%
del %ZIP_FILE%
if exist curl_error.txt del curl_error.txt

pause
exit /b