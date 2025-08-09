package org.datanucleus.store.schema.table;

import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.schema.naming.ColumnType;

public interface Column {
   String getName();

   Table getTable();

   MemberColumnMapping getMemberColumnMapping();

   boolean isPrimaryKey();

   Column setPrimaryKey();

   Column setNullable(boolean var1);

   boolean isNullable();

   Column setDefaultable(Object var1);

   boolean isDefaultable();

   Object getDefaultValue();

   Column setUnique(boolean var1);

   boolean isUnique();

   ColumnType getColumnType();

   Column setJdbcType(JdbcType var1);

   JdbcType getJdbcType();

   Column setTypeName(String var1);

   String getTypeName();

   Column setPosition(int var1);

   int getPosition();

   Column setColumnMetaData(ColumnMetaData var1);

   ColumnMetaData getColumnMetaData();
}
