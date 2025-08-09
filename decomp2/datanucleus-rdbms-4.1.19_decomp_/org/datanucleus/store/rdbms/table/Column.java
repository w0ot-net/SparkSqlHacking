package org.datanucleus.store.rdbms.table;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.ColumnDefinitionException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;

public interface Column extends org.datanucleus.store.schema.table.Column {
   int WRAPPER_FUNCTION_SELECT = 0;
   int WRAPPER_FUNCTION_INSERT = 1;
   int WRAPPER_FUNCTION_UPDATE = 2;

   RDBMSStoreManager getStoreManager();

   Table getTable();

   String getStoredJavaType();

   void setIdentifier(DatastoreIdentifier var1);

   DatastoreIdentifier getIdentifier();

   Column setIdentity(boolean var1);

   boolean isIdentity();

   void setDatastoreMapping(DatastoreMapping var1);

   DatastoreMapping getDatastoreMapping();

   JavaTypeMapping getJavaTypeMapping();

   String applySelectFunction(String var1);

   void copyConfigurationTo(Column var1);

   AbstractMemberMetaData getMemberMetaData();

   boolean isUnlimitedLength();

   Column setTypeInfo(SQLTypeInfo var1);

   SQLTypeInfo getTypeInfo();

   String getSQLDefinition();

   void initializeColumnInfoFromDatastore(RDBMSColumnInfo var1);

   void validate(RDBMSColumnInfo var1);

   Column setConstraints(String var1);

   String getConstraints();

   void checkPrimitive() throws ColumnDefinitionException;

   void checkInteger() throws ColumnDefinitionException;

   void checkDecimal() throws ColumnDefinitionException;

   void checkString() throws ColumnDefinitionException;

   void setWrapperFunction(String var1, int var2);

   String getWrapperFunction(int var1);
}
