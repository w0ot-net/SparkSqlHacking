package org.datanucleus.store.rdbms.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;

public interface Table extends org.datanucleus.store.schema.table.Table {
   RDBMSStoreManager getStoreManager();

   DatastoreIdentifier getIdentifier();

   Column addColumn(String var1, DatastoreIdentifier var2, JavaTypeMapping var3, ColumnMetaData var4);

   boolean hasColumn(DatastoreIdentifier var1);

   Column getColumn(DatastoreIdentifier var1);

   JavaTypeMapping getIdMapping();

   JavaTypeMapping getMemberMapping(AbstractMemberMetaData var1);

   DiscriminatorMetaData getDiscriminatorMetaData();

   JavaTypeMapping getDiscriminatorMapping(boolean var1);

   JavaTypeMapping getMultitenancyMapping();

   VersionMetaData getVersionMetaData();

   JavaTypeMapping getVersionMapping(boolean var1);

   void preInitialize(ClassLoaderResolver var1);

   void initialize(ClassLoaderResolver var1);

   void postInitialize(ClassLoaderResolver var1);

   boolean isInitialized();

   boolean isInitializedModified();

   boolean validate(Connection var1, boolean var2, boolean var3, Collection var4) throws SQLException;

   boolean isValidated();

   boolean exists(Connection var1, boolean var2) throws SQLException;

   boolean create(Connection var1) throws SQLException;

   void drop(Connection var1) throws SQLException;
}
