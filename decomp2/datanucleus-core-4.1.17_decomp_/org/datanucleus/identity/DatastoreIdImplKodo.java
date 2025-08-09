package org.datanucleus.identity;

import java.io.Serializable;
import org.datanucleus.util.Localiser;

public class DatastoreIdImplKodo implements Serializable, DatastoreId, Comparable {
   private static final long serialVersionUID = -427334762583525878L;
   protected static final transient String STRING_DELIMITER = "-";
   public final Object keyAsObject;
   public final String targetClassName;
   public final String toString;
   public final int hashCode;

   public DatastoreIdImplKodo() {
      this.keyAsObject = null;
      this.targetClassName = null;
      this.toString = null;
      this.hashCode = -1;
   }

   public DatastoreIdImplKodo(String pcClass, Object object) {
      this.targetClassName = pcClass;
      this.keyAsObject = object;
      StringBuilder s = new StringBuilder();
      s.append(this.targetClassName);
      s.append("-");
      s.append(this.keyAsObject.toString());
      this.toString = s.toString();
      this.hashCode = this.toString.hashCode();
   }

   public DatastoreIdImplKodo(String str) throws IllegalArgumentException {
      if (str.length() < 2) {
         throw new IllegalArgumentException(Localiser.msg("038000", str));
      } else {
         int separatorPosition = str.indexOf("-");
         this.targetClassName = str.substring(0, separatorPosition);
         String oidStr = str.substring(separatorPosition + 1);
         Object oidValue = null;

         try {
            oidValue = Long.valueOf(oidStr);
         } catch (NumberFormatException var6) {
            oidValue = oidStr;
         }

         this.keyAsObject = oidValue;
         this.toString = str;
         this.hashCode = this.toString.hashCode();
      }
   }

   public Object getKeyAsObject() {
      return this.keyAsObject;
   }

   public String getTargetClassName() {
      return this.targetClassName;
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (obj == this) {
         return true;
      } else if (!obj.getClass().getName().equals(DatastoreIdImplKodo.class.getName())) {
         return false;
      } else if (this.hashCode() != obj.hashCode()) {
         return false;
      } else {
         return ((DatastoreId)obj).toString().equals(this.toString);
      }
   }

   public int compareTo(Object o) {
      if (o instanceof DatastoreIdImplKodo) {
         DatastoreIdImplKodo c = (DatastoreIdImplKodo)o;
         return this.toString.compareTo(c.toString);
      } else if (o == null) {
         throw new ClassCastException("object is null");
      } else {
         throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
      }
   }

   public int hashCode() {
      return this.hashCode;
   }

   public String toString() {
      return this.toString;
   }
}
