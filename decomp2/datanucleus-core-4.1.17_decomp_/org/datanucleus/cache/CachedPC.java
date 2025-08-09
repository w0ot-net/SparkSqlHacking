package org.datanucleus.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.StringUtils;

public class CachedPC implements Serializable {
   private static final long serialVersionUID = 1326244752228266953L;
   private Class cls;
   private Map fieldValues = null;
   private Object version;
   private boolean[] loadedFields;

   public CachedPC(Class cls, boolean[] loadedFields, Object vers) {
      this.cls = cls;
      this.loadedFields = new boolean[loadedFields.length];

      for(int i = 0; i < loadedFields.length; ++i) {
         this.loadedFields[i] = loadedFields[i];
      }

      this.version = vers;
   }

   public Class getObjectClass() {
      return this.cls;
   }

   public void setFieldValue(Integer fieldNumber, Object value) {
      if (this.fieldValues == null) {
         this.fieldValues = new HashMap();
      }

      this.fieldValues.put(fieldNumber, value);
   }

   public Object getFieldValue(Integer fieldNumber) {
      return this.fieldValues == null ? null : this.fieldValues.get(fieldNumber);
   }

   public void setVersion(Object ver) {
      this.version = ver;
   }

   public Object getVersion() {
      return this.version;
   }

   public boolean[] getLoadedFields() {
      return this.loadedFields;
   }

   public int[] getLoadedFieldNumbers() {
      return ClassUtils.getFlagsSetTo(this.loadedFields, true);
   }

   public void setLoadedField(int fieldNumber, boolean loaded) {
      this.loadedFields[fieldNumber] = loaded;
   }

   public synchronized CachedPC getCopy() {
      CachedPC<T> copy = new CachedPC(this.cls, this.loadedFields, this.version);
      if (this.fieldValues != null) {
         copy.fieldValues = new HashMap(this.fieldValues.size());

         for(Map.Entry entry : this.fieldValues.entrySet()) {
            Integer key = (Integer)entry.getKey();
            Object val = entry.getValue();
            if (val != null && val instanceof CachedPC) {
               val = ((CachedPC)val).getCopy();
            }

            copy.fieldValues.put(key, val);
         }
      }

      return copy;
   }

   public String toString() {
      return this.toString(false);
   }

   public String toString(boolean debug) {
      return "CachedPC : cls=" + this.cls.getName() + " version=" + this.version + " loadedFlags=" + StringUtils.booleanArrayToString(this.loadedFields) + (debug ? " vals=" + StringUtils.mapToString(this.fieldValues) : "");
   }

   public static class CachedId implements Serializable, Comparable {
      private static final long serialVersionUID = -2806783207184913323L;
      String className;
      Object id;

      public CachedId(String className, Object id) {
         this.className = className;
         this.id = id;
      }

      public String getClassName() {
         return this.className;
      }

      public Object getId() {
         return this.id;
      }

      public boolean equals(Object obj) {
         if (obj != null && obj instanceof CachedId) {
            CachedId other = (CachedId)obj;
            return other.className.equals(this.className) && other.id.equals(this.id);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.className.hashCode() ^ this.id.hashCode();
      }

      public int compareTo(CachedId obj) {
         return obj == null ? 1 : this.hashCode() - obj.hashCode();
      }
   }
}
