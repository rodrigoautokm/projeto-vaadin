# -*- coding: utf-8 -*-
import jaydebeapi

# Parâmetros de conexão
driver_class = "com.sybase.jdbc3.jdbc.SybDriver"
url = "jdbc:sybase:Tds:10.0.14.130:2688/autokm?CHARSET=iso_1&LOGIN_TYPE=standard"
username = "dbo"
password = "dircri17"
driver_path = r"D:\projeto-vaadin\lib\jconn3.jar"

try:
    # Estabelecer a conexão
    print("Tentando conectar ao Sybase...")
    conn = jaydebeapi.connect(driver_class, url, [username, password], driver_path)
    print("Conexao bem-sucedida!")

    # Testar uma consulta simples
    cursor = conn.cursor()
    cursor.execute("SELECT 1")
    result = cursor.fetchone()
    print("Consulta de teste bem-sucedida:", result)

    # Fechar a conexão
    cursor.close()
    conn.close()

except Exception as e:
    print("Erro ao conectar ao Sybase:", str(e))