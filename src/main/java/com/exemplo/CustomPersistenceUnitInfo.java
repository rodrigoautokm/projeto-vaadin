package com.exemplo;

import org.hibernate.jpa.boot.spi.PersistenceUnitDescriptor;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

    private final String name;
    private final DataSource dataSource;
    private final String modelPackage;

    public CustomPersistenceUnitInfo(String name, DataSource dataSource, String modelPackage) {
        this.name = name;
        this.dataSource = dataSource;
        this.modelPackage = modelPackage;
    }

    @Override public String getPersistenceUnitName() { return name; }
    @Override public String getPersistenceProviderClassName() { return "org.hibernate.jpa.HibernatePersistenceProvider"; }
    @Override public PersistenceUnitTransactionType getTransactionType() { return PersistenceUnitTransactionType.RESOURCE_LOCAL; }
    @Override public DataSource getJtaDataSource() { return null; }
    @Override public DataSource getNonJtaDataSource() { return dataSource; }
    @Override public List<String> getMappingFileNames() { return Collections.emptyList(); }
    @Override public List<URL> getJarFileUrls() { return Collections.emptyList(); }
    @Override public URL getPersistenceUnitRootUrl() { return null; }
    @Override public List<String> getManagedClassNames() { return Collections.emptyList(); }
    @Override public boolean excludeUnlistedClasses() { return false; }
    @Override public SharedCacheMode getSharedCacheMode() { return SharedCacheMode.ENABLE_SELECTIVE; }
    @Override public ValidationMode getValidationMode() { return ValidationMode.NONE; }
    @Override public Properties getProperties() { return new Properties(); }
    @Override public String getPersistenceXMLSchemaVersion() { return "2.1"; }
    @Override public ClassLoader getClassLoader() { return Thread.currentThread().getContextClassLoader(); }
    @Override public void addTransformer(ClassTransformer transformer) { }
    @Override public ClassLoader getNewTempClassLoader() { return Thread.currentThread().getContextClassLoader(); }
}