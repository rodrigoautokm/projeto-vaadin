package com.exemplo;

import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLAnywhereIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) {
        return "SELECT @@IDENTITY";
    }

    @Override
    public String getIdentityColumnString(int type) {
        return "IDENTITY";
    }

    @Override
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    @Override
    public String getIdentityInsertString() {
        return "DEFAULT";
    }
}