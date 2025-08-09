package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.util.StringUtils;

public class QueryResultMetaData extends MetaData {
   private static final long serialVersionUID = -3001099437789070838L;
   protected final String name;
   protected List persistentTypeMappings;
   protected List scalarColumns;
   protected List ctrTypeMappings;

   public QueryResultMetaData(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void addPersistentTypeMapping(String className, Map fieldColumnMap, String discrimColumn) {
      if (this.persistentTypeMappings == null) {
         this.persistentTypeMappings = new ArrayList();
      }

      PersistentTypeMapping m = new PersistentTypeMapping();
      m.className = className;
      m.discriminatorColumn = StringUtils.isWhitespace(discrimColumn) ? null : discrimColumn;
      m.fieldColumnMap = fieldColumnMap;
      this.persistentTypeMappings.add(m);
   }

   public void addMappingForPersistentTypeMapping(String className, String fieldName, String columnName) {
      PersistentTypeMapping m = null;
      if (this.persistentTypeMappings == null) {
         this.persistentTypeMappings = new ArrayList();
      } else {
         for(PersistentTypeMapping mapping : this.persistentTypeMappings) {
            if (mapping.className.equals(className)) {
               m = mapping;
               break;
            }
         }
      }

      if (m == null) {
         m = new PersistentTypeMapping();
         m.className = className;
      }

      if (m.fieldColumnMap == null) {
         m.fieldColumnMap = new HashMap();
      }

      m.fieldColumnMap.put(fieldName, columnName);
   }

   public PersistentTypeMapping[] getPersistentTypeMappings() {
      return this.persistentTypeMappings == null ? null : (PersistentTypeMapping[])this.persistentTypeMappings.toArray(new PersistentTypeMapping[this.persistentTypeMappings.size()]);
   }

   public void addScalarColumn(String columnName) {
      if (this.scalarColumns == null) {
         this.scalarColumns = new ArrayList();
      }

      this.scalarColumns.add(columnName);
   }

   public String[] getScalarColumns() {
      return this.scalarColumns == null ? null : (String[])this.scalarColumns.toArray(new String[this.scalarColumns.size()]);
   }

   public void addConstructorTypeMapping(String className, List colNames) {
      if (this.ctrTypeMappings == null) {
         this.ctrTypeMappings = new ArrayList();
      }

      ConstructorTypeMapping m = new ConstructorTypeMapping();
      m.className = className;
      m.ctrColumns = new ArrayList(colNames);
      this.ctrTypeMappings.add(m);
   }

   public ConstructorTypeMapping[] getConstructorTypeMappings() {
      return this.ctrTypeMappings == null ? null : (ConstructorTypeMapping[])this.ctrTypeMappings.toArray(new ConstructorTypeMapping[this.ctrTypeMappings.size()]);
   }

   public static class PersistentTypeMapping {
      String className;
      Map fieldColumnMap;
      String discriminatorColumn;

      public String getClassName() {
         return this.className;
      }

      public String getDiscriminatorColumn() {
         return this.discriminatorColumn;
      }

      public String getColumnForField(String fieldName) {
         return this.fieldColumnMap == null ? null : (String)this.fieldColumnMap.get(fieldName);
      }
   }

   public static class ConstructorTypeMapping {
      String className;
      List ctrColumns;

      public String getClassName() {
         return this.className;
      }

      public List getColumnsForConstructor() {
         return this.ctrColumns == null ? null : this.ctrColumns;
      }
   }

   public static class ConstructorTypeColumn {
      String columnName;
      Class javaType;

      public ConstructorTypeColumn(String colName, Class type) {
         this.columnName = colName;
         if (type != Void.TYPE) {
            this.javaType = type;
         }

      }

      public String getColumnName() {
         return this.columnName;
      }

      public Class getJavaType() {
         return this.javaType;
      }
   }
}
