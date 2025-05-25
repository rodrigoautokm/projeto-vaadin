@echo off
setlocal EnableDelayedExpansion

:: Configuracoes iniciais
set "diretorio=.\*.java"
set "arquivo_pom=D:\projeto-vaadin\pom.xml"
set "arquivo_css=D:\projeto-vaadin\frontend\styles\styles.css"
set "arquivo_props=D:\projeto-vaadin\src\main\resources\application.properties"
set "arquivo_gridxml=D:\projeto-vaadin\src\main\resources\grid-columns-config.xml"
set "arquivo_base=saida"
set "extensao=.txt"
set "contador=0"
set "arquivo_saida=%arquivo_base%%extensao%"

:: Gerar data e hora para o zip
for /f "tokens=1-5 delims=/: " %%d in ("%date% %time%") do (
    set "dia=%%d"
    set "mes=%%e"
    set "ano=%%f"
    set "hora=%%g"
    set "minuto=%%h"
)
:: Remover qualquer caracter invalido para nomes de arquivo
set "datahora=%ano%%mes%%dia%_%hora%%minuto%"
set "zip_base=projeto_%datahora%"
set "zip_extensao=.zip"
set "arquivo_zip=%zip_base%%zip_extensao%"

:: Verifica se o arquivo de saída já existe e encontra um nome sequencial
:verificar_nome_saida
if exist "%arquivo_saida%" (
    set /a contador+=1
    set "arquivo_saida=%arquivo_base%!contador!%extensao%"
    goto verificar_nome_saida
)

:: Itera sobre todos os arquivos .java no diretório atual
echo Processando arquivos .java no diretorio atual...
for %%F in (%diretorio%) do (
    echo Adicionando %%F ao %arquivo_saida%
    echo // Arquivo: %%~nxF >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    type "%%F" >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
)

:: Adiciona o arquivo pom.xml ao final
echo Verificando %arquivo_pom%...
if exist "%arquivo_pom%" (
    echo Adicionando %arquivo_pom% ao %arquivo_saida%
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
echo Verificando %arquivo_css%...
if exist "%arquivo_css%" (
    echo Adicionando %arquivo_css% ao %arquivo_saida%
    echo // Arquivo: %arquivo_css% >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    type "%arquivo_css%" >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
    echo // ------------------------- >> "%arquivo_saida%"
    echo. >> "%arquivo_saida%"
) else (
    echo Aviso: Arquivo %arquivo_css% nao encontrado. >> "%arquivo_saida%"
)

:: Monta a lista de arquivos para zipar
set "arquivos_para_zipar=%diretorio%"

if exist "%arquivo_pom%" set "arquivos_para_zipar=!arquivos_para_zipar! %arquivo_pom%"
if exist "%arquivo_css%" set "arquivos_para_zipar=!arquivos_para_zipar! %arquivo_css%"
if exist "%arquivo_props%" set "arquivos_para_zipar=!arquivos_para_zipar! %arquivo_props%"
if exist "%arquivo_gridxml%" set "arquivos_para_zipar=!arquivos_para_zipar! %arquivo_gridxml%"
if exist "%arquivo_saida%" set "arquivos_para_zipar=!arquivos_para_zipar! %arquivo_saida%"

:: Cria o ZIP de verdade
if defined arquivos_para_zipar (
    echo Criando %arquivo_zip%...
    tar -a -c -f "%arquivo_zip%" !arquivos_para_zipar!
    echo.
    echo Arquivo %arquivo_saida% criado com sucesso!
    echo Arquivo compactado %arquivo_zip% criado com sucesso: %arquivo_zip%
) else (
    echo Nenhum arquivo encontrado para zipar.
)

pause
