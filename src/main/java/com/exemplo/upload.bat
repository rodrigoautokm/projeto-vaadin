@echo off
setlocal EnableDelayedExpansion

:: Verifica se pelo menos um argumento foi passado
if "%~1"=="" (
    echo Erro: Nenhum arquivo especificado.
    echo Uso: %0 "caminho_completo" ou %0 "nome_do_arquivo"
    exit /b 1
)

:: Define o arquivo
set "input=%~1"

:: Verifica se o input é um caminho completo ou apenas o nome do arquivo
if "!input:~1,1!"==":" (
    :: Caminho completo (ex.: C:\Temp\teste.txt)
    set "caminho_completo=%input%"
) else (
    :: Apenas nome do arquivo, assume a pasta atual
    set "caminho_completo=%CD%\%input%"
)

:: Verifica se o arquivo existe
if not exist "!caminho_completo!" (
    echo Erro: Arquivo "!caminho_completo!" nao encontrado.
    exit /b 1
)

:: Extrai apenas o nome do arquivo para o título do paste
for %%F in ("!caminho_completo!") do set "nome_arquivo=%%~nxF"

:: Chave da API do Pastebin
set "api_key=W7sWN8hnekO__sYyWaJNRdNWKvVOaWEu"

:: Faz o upload usando curl
for /f "delims=" %%i in ('type "!caminho_completo!"') do set "conteudo=!conteudo!%%i\n"
curl -X POST -d "api_dev_key=%api_key%" -d "api_option=paste" -d "api_paste_code=!conteudo!" -d "api_paste_private=1" -d "api_paste_name=%nome_arquivo%" -d "api_paste_expire_date=1D" -d "api_paste_format=text" https://pastebin.com/api/api_post.php

:: Fim
echo.
echo Upload concluido.
endlocal
pause