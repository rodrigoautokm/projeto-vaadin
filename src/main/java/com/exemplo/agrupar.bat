@echo off
setlocal EnableDelayedExpansion

set "diretorio=.\*.java"
set "arquivo_pom=D:\projeto-vaadin\pom.xml"
set "arquivo_css=D:\projeto-vaadin\frontend/styles/styles.css"
set "arquivo_base=saida"
set "extensao=.txt"
set "contador=0"
set "arquivo_saida=%arquivo_base%%extensao%"
set "zip_base=projeto"
set "zip_extensao=.zip"
set "arquivo_zip=%zip_base%%zip_extensao%"

:: Verifica se o arquivo de saída já existe e encontra um nome sequencial
:verificar_nome_saida
if exist "%arquivo_saida%" (
    set /a contador+=1
    set "arquivo_saida=%arquivo_base%!contador!%extensao%"
    goto verificar_nome_saida
)

:: Verifica se o arquivo ZIP já existe e encontra um nome sequencial
:verificar_nome_zip
if exist "%arquivo_zip%" (
    set /a contador+=1
    set "arquivo_zip=%zip_base%!contador!%zip_extensao%"
    goto verificar_nome_zip
)

:: Itera sobre todos os arquivos .java no diretório
for %%F in (%diretorio%) do (
    echo // Arquivo: %%F >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    type "%%F" >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
)

:: Adiciona o arquivo pom.xml ao final
if exist "%arquivo_pom%" (
    echo // Arquivo: %arquivo_pom% >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    type "%arquivo_pom%" >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
) else (
    echo Aviso: Arquivo %arquivo_pom% nao encontrado. >> "%arquivo_saida%"
)

:: Adiciona o arquivo styles.css ao final
if exist "%arquivo_css%" (
    echo // Arquivo: %arquivo_css% >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    type "%arquivo_css%" >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
) else (
    echo Aviso: Arquivo %arquivo_css% nao encontrado. >> "%arquivo_saida%"
)

:: Cria o arquivo ZIP contendo todos os arquivos .java, pom.xml, styles.css e o arquivo de saída
:: Adiciona arquivos .java ao ZIP
for %%F in (%diretorio%) do (
    tar -rf "%arquivo_zip%" "%%F"
)

:: Adiciona pom.xml ao ZIP, se existir
if exist "%arquivo_pom%" (
    tar -rf "%arquivo_zip%" "%arquivo_pom%"
)

:: Adiciona styles.css ao ZIP, se existir
if exist "%arquivo_css%" (
    tar -rf "%arquivo_zip%" "%arquivo_css%"
)

:: Adiciona o arquivo de saída ao ZIP
tar -rf "%arquivo_zip%" "%arquivo_saida%"

echo Arquivo %arquivo_saida% criado com sucesso!
echo Arquivo compactado %arquivo_zip% criado com sucesso!
pause