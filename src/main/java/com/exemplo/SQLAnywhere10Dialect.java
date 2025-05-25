package com.exemplo;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import java.sql.Types;

public class SQLAnywhere10Dialect extends Dialect {

    public SQLAnywhere10Dialect() {
        super();
        // Configurações específicas para o SQL Anywhere
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.BINARY, "binary");
        registerColumnType(Types.BIT, "bit");
        registerColumnType(Types.BLOB, "long binary");
        registerColumnType(Types.BOOLEAN, "bit");
        registerColumnType(Types.CHAR, "char($l)");
        registerColumnType(Types.CLOB, "long varchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.DECIMAL, "decimal($p,$s)");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.LONGNVARCHAR, "long nvarchar");
        registerColumnType(Types.LONGVARBINARY, "long binary");
        registerColumnType(Types.LONGVARCHAR, "long varchar");
        registerColumnType(Types.NCHAR, "nchar($l)");
        registerColumnType(Types.NUMERIC, "numeric($p,$s)");
        registerColumnType(Types.NVARCHAR, "nvarchar($l)");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.VARBINARY, "varbinary($l)");
        registerColumnType(Types.VARCHAR, "varchar($l)");
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLAnywhereIdentityColumnSupport(); // Agora usa a implementação personalizada
    }

    @Override
    public boolean hasAlterTable() {
        return true;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public String getAddColumnString() {
        return "add";
    }

    @Override
    public String getNullColumnString() {
        return " null";
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public String getDropForeignKeyString() {
        return " drop foreign key ";
    }

	@Override
	public String getCurrentSchemaCommand() {
		return null; // Assume que não há schema, típico em bancos SQL Anywhere 9
	}

}