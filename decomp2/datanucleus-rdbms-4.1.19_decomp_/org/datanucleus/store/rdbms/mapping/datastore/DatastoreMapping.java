package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;

public interface DatastoreMapping {
   boolean isNullable();

   Column getColumn();

   JavaTypeMapping getJavaTypeMapping();

   boolean isDecimalBased();

   boolean isIntegerBased();

   boolean isStringBased();

   boolean isBitBased();

   boolean isBooleanBased();

   void setBoolean(PreparedStatement var1, int var2, boolean var3);

   void setChar(PreparedStatement var1, int var2, char var3);

   void setByte(PreparedStatement var1, int var2, byte var3);

   void setShort(PreparedStatement var1, int var2, short var3);

   void setInt(PreparedStatement var1, int var2, int var3);

   void setLong(PreparedStatement var1, int var2, long var3);

   void setFloat(PreparedStatement var1, int var2, float var3);

   void setDouble(PreparedStatement var1, int var2, double var3);

   void setString(PreparedStatement var1, int var2, String var3);

   void setObject(PreparedStatement var1, int var2, Object var3);

   boolean getBoolean(ResultSet var1, int var2);

   char getChar(ResultSet var1, int var2);

   byte getByte(ResultSet var1, int var2);

   short getShort(ResultSet var1, int var2);

   int getInt(ResultSet var1, int var2);

   long getLong(ResultSet var1, int var2);

   float getFloat(ResultSet var1, int var2);

   double getDouble(ResultSet var1, int var2);

   String getString(ResultSet var1, int var2);

   Object getObject(ResultSet var1, int var2);
}
