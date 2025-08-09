package com.univocity.parsers.common.fields;

import com.univocity.parsers.annotations.helpers.FieldMapping;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

abstract class AbstractColumnMapping implements Cloneable {
   final String prefix;
   Map mapping;

   AbstractColumnMapping(String prefix, AbstractColumnMapping parent) {
      if (parent != null) {
         this.mapping = parent.mapping;
         this.prefix = parent.prefix.isEmpty() ? prefix : parent.prefix + '.' + prefix;
      } else {
         this.mapping = new LinkedHashMap();
         this.prefix = prefix;
      }

   }

   void mapToColumnName(Object key, String columnName) {
      this.mapping.put(key, columnName);
   }

   void mapToColumn(Object key, Enum column) {
      this.mapping.put(key, column);
   }

   void mapToColumnIndex(Object key, int columnIndex) {
      this.mapping.put(key, columnIndex);
   }

   void mapToColumnNames(Map mappings) {
      this.mapping.putAll(mappings);
   }

   void mapToColumns(Map mappings) {
      this.mapping.putAll(mappings);
   }

   void mapToColumnIndexes(Map mappings) {
      this.mapping.putAll(mappings);
   }

   boolean isMapped(Object key) {
      return this.getMappedColumn(key) != null;
   }

   abstract Object prefixKey(String var1, Object var2);

   private Object getMappedColumn(Object key) {
      if (key == null) {
         return null;
      } else {
         key = (K)this.prefixKey(this.prefix, key);
         Object out = this.mapping.get(key);
         return out;
      }
   }

   boolean updateFieldMapping(FieldMapping fieldMapping, Object key) {
      Object mappedColumn = this.getMappedColumn(key);
      if (mappedColumn != null) {
         if (mappedColumn instanceof Enum) {
            mappedColumn = ((Enum)mappedColumn).name();
         }

         if (mappedColumn instanceof String) {
            fieldMapping.setFieldName((String)mappedColumn);
            fieldMapping.setIndex(-1);
            return true;
         } else if (mappedColumn instanceof Integer) {
            fieldMapping.setIndex((Integer)mappedColumn);
            return true;
         } else {
            throw new IllegalStateException("Unexpected mapping of '" + key + "' to " + mappedColumn);
         }
      } else {
         return false;
      }
   }

   void extractPrefixes(Set out) {
      for(Object key : this.mapping.keySet()) {
         String keyPrefix = this.getKeyPrefix(this.prefix, key);
         if (keyPrefix != null) {
            out.add(keyPrefix);
         }
      }

   }

   abstract String getKeyPrefix(String var1, Object var2);

   public AbstractColumnMapping clone() {
      try {
         AbstractColumnMapping<K> out = (AbstractColumnMapping)super.clone();
         out.mapping = new LinkedHashMap(this.mapping);
         return out;
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   abstract Object findKey(String var1);

   void remove(String nameWithPrefix) {
      while(true) {
         K key;
         if ((key = (K)this.findKey(nameWithPrefix)) != null) {
            if (this.mapping.remove(key) != null) {
               continue;
            }

            return;
         }

         return;
      }
   }
}
