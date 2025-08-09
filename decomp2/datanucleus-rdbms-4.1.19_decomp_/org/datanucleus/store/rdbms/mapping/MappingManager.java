package org.datanucleus.store.rdbms.mapping;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;

public interface MappingManager {
   void loadDatastoreMapping(PluginManager var1, ClassLoaderResolver var2, String var3);

   DatastoreMapping createDatastoreMapping(JavaTypeMapping var1, AbstractMemberMetaData var2, int var3, Column var4);

   DatastoreMapping createDatastoreMapping(JavaTypeMapping var1, Column var2, String var3);

   JavaTypeMapping getMapping(Class var1);

   JavaTypeMapping getMapping(Class var1, boolean var2, boolean var3, String var4);

   JavaTypeMapping getMappingWithDatastoreMapping(Class var1, boolean var2, boolean var3, ClassLoaderResolver var4);

   JavaTypeMapping getMapping(Table var1, AbstractMemberMetaData var2, ClassLoaderResolver var3, FieldRole var4);

   Column createColumn(JavaTypeMapping var1, String var2, int var3);

   Column createColumn(JavaTypeMapping var1, String var2, ColumnMetaData var3);

   Column createColumn(AbstractMemberMetaData var1, Table var2, JavaTypeMapping var3, ColumnMetaData var4, Column var5, ClassLoaderResolver var6);
}
