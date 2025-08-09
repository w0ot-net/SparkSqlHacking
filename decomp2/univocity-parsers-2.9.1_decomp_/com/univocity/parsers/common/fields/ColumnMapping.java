package com.univocity.parsers.common.fields;

import com.univocity.parsers.annotations.helpers.FieldMapping;
import com.univocity.parsers.annotations.helpers.MethodDescriptor;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ColumnMapping implements ColumnMapper {
   private NameMapping attributeMapping;
   private NameMapping methodNameMapping;
   private MethodMapping methodMapping;

   private static String getCurrentAttributePrefix(String prefix, String name) {
      if (!name.startsWith(prefix)) {
         return null;
      } else {
         int off = prefix.isEmpty() ? 0 : 1;
         int dot = name.indexOf(46, prefix.length() + off);
         if (dot != -1) {
            String attributePrefix = name.substring(prefix.length() + off, dot);
            return attributePrefix;
         } else {
            return null;
         }
      }
   }

   public ColumnMapping() {
      this("", (ColumnMapping)null);
   }

   public ColumnMapping(String prefix, ColumnMapping parent) {
      this.attributeMapping = new NameMapping(prefix, parent == null ? null : parent.attributeMapping);
      this.methodNameMapping = new NameMapping(prefix, parent == null ? null : parent.methodNameMapping);
      this.methodMapping = new MethodMapping(prefix, parent == null ? null : parent.methodMapping);
   }

   public void attributeToColumnName(String attributeName, String columnName) {
      this.attributeMapping.mapToColumnName(attributeName, columnName);
   }

   public void attributeToColumn(String attributeName, Enum column) {
      this.attributeMapping.mapToColumn(attributeName, column);
   }

   public void attributeToIndex(String attributeName, int columnIndex) {
      this.attributeMapping.mapToColumnIndex(attributeName, columnIndex);
   }

   public void attributesToColumnNames(Map mappings) {
      this.attributeMapping.mapToColumnNames(mappings);
   }

   public void attributesToColumns(Map mappings) {
      this.attributeMapping.mapToColumns(mappings);
   }

   public void attributesToIndexes(Map mappings) {
      this.attributeMapping.mapToColumnIndexes(mappings);
   }

   private void methodToColumnName(MethodDescriptor method, String columnName) {
      this.methodMapping.mapToColumnName(method, columnName);
   }

   private void methodToColumn(MethodDescriptor method, Enum column) {
      this.methodMapping.mapToColumn(method, column);
   }

   private void methodToIndex(MethodDescriptor method, int columnIndex) {
      this.methodMapping.mapToColumnIndex(method, columnIndex);
   }

   public boolean isMapped(MethodDescriptor method, String targetName) {
      return this.methodMapping.isMapped(method) || this.attributeMapping.isMapped(targetName) || this.methodNameMapping.isMapped(targetName);
   }

   public boolean updateMapping(FieldMapping fieldMapping, String targetName, MethodDescriptor method) {
      if (this.methodMapping.isMapped(method)) {
         return this.methodMapping.updateFieldMapping(fieldMapping, method);
      } else if (this.attributeMapping.isMapped(targetName)) {
         return this.attributeMapping.updateFieldMapping(fieldMapping, targetName);
      } else {
         return this.methodNameMapping.isMapped(targetName) ? this.methodNameMapping.updateFieldMapping(fieldMapping, targetName) : false;
      }
   }

   public String getPrefix() {
      return this.methodMapping.prefix;
   }

   public void methodToColumnName(String methodName, String columnName) {
      this.methodNameMapping.mapToColumnName(methodName, columnName);
   }

   public void methodToColumn(String methodName, Enum column) {
      this.methodNameMapping.mapToColumn(methodName, column);
   }

   public void methodToIndex(String methodName, int columnIndex) {
      this.methodNameMapping.mapToColumnIndex(methodName, columnIndex);
   }

   public void methodsToColumnNames(Map mappings) {
      this.methodNameMapping.mapToColumnNames(mappings);
   }

   public void methodsToColumns(Map mappings) {
      this.methodNameMapping.mapToColumns(mappings);
   }

   public void methodsToIndexes(Map mappings) {
      this.methodNameMapping.mapToColumnIndexes(mappings);
   }

   public void remove(String methodOrAttributeName) {
      this.attributeMapping.remove(methodOrAttributeName);
      this.methodNameMapping.remove(methodOrAttributeName);
      this.methodMapping.remove(methodOrAttributeName);
   }

   public void methodToColumnName(String setterName, Class parameterType, String columnName) {
      this.methodToColumnName(MethodDescriptor.setter(setterName, parameterType), columnName);
   }

   public void methodToColumn(String setterName, Class parameterType, Enum column) {
      this.methodToColumn(MethodDescriptor.setter(setterName, parameterType), column);
   }

   public void methodToIndex(String setterName, Class parameterType, int columnIndex) {
      this.methodToIndex(MethodDescriptor.setter(setterName, parameterType), columnIndex);
   }

   public Set getNestedAttributeNames() {
      Set<String> out = new HashSet();
      this.attributeMapping.extractPrefixes(out);
      this.methodNameMapping.extractPrefixes(out);
      this.methodMapping.extractPrefixes(out);
      return out;
   }

   public ColumnMapper clone() {
      try {
         ColumnMapping out = (ColumnMapping)super.clone();
         out.attributeMapping = (NameMapping)this.attributeMapping.clone();
         out.methodNameMapping = (NameMapping)this.methodNameMapping.clone();
         out.methodMapping = (MethodMapping)this.methodMapping.clone();
         return out;
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   private class NameMapping extends AbstractColumnMapping {
      public NameMapping(String prefix, NameMapping parent) {
         super(prefix, parent);
      }

      String prefixKey(String prefix, String key) {
         return prefix.isEmpty() ? key : prefix + '.' + key;
      }

      String getKeyPrefix(String prefix, String key) {
         return ColumnMapping.getCurrentAttributePrefix(prefix, key);
      }

      String findKey(String nameWithPrefix) {
         return nameWithPrefix;
      }
   }

   private class MethodMapping extends AbstractColumnMapping {
      public MethodMapping(String prefix, MethodMapping parent) {
         super(prefix, parent);
      }

      MethodDescriptor prefixKey(String prefix, MethodDescriptor key) {
         return key.getPrefix().equals(prefix) ? key : null;
      }

      String getKeyPrefix(String prefix, MethodDescriptor key) {
         return ColumnMapping.getCurrentAttributePrefix(prefix, key.getPrefixedName());
      }

      MethodDescriptor findKey(String nameWithPrefix) {
         for(MethodDescriptor k : this.mapping.keySet()) {
            if (k.getPrefixedName().equals(nameWithPrefix)) {
               return k;
            }
         }

         return null;
      }
   }
}
