package org.datanucleus.identity;

import java.io.Serializable;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.util.Localiser;

public class DatastoreIdImpl implements Serializable, DatastoreId, Comparable {
   private static final long serialVersionUID = -1841930829956222995L;
   protected static final transient String STRING_DELIMITER = "[OID]";
   public final Object keyAsObject;
   public final String targetClassName;
   public final String toString;
   public final int hashCode;

   public DatastoreIdImpl() {
      this.keyAsObject = null;
      this.targetClassName = null;
      this.toString = null;
      this.hashCode = -1;
   }

   public DatastoreIdImpl(String pcClass, Object object) {
      this.targetClassName = pcClass;
      this.keyAsObject = object;
      StringBuilder s = new StringBuilder();
      s.append(this.keyAsObject.toString());
      s.append("[OID]");
      s.append(this.targetClassName);
      this.toString = s.toString();
      this.hashCode = this.toString.hashCode();
   }

   public DatastoreIdImpl(String str) throws IllegalArgumentException {
      if (str.length() < 2) {
         throw new IllegalArgumentException(Localiser.msg("038000", str));
      } else if (str.indexOf("[OID]") < 0) {
         throw new IllegalArgumentException(Localiser.msg("038000", str));
      } else {
         int start = 0;
         int end = str.indexOf("[OID]", start);
         String oidStr = str.substring(start, end);
         Object oidValue = null;

         try {
            oidValue = Long.valueOf(oidStr);
         } catch (NumberFormatException var7) {
            oidValue = oidStr;
         }

         this.keyAsObject = oidValue;
         start = end + "[OID]".length();
         this.targetClassName = str.substring(start, str.length());
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
      } else if (!obj.getClass().getName().equals(ClassNameConstants.IDENTITY_OID_IMPL)) {
         return false;
      } else if (this.hashCode() != obj.hashCode()) {
         return false;
      } else {
         return ((DatastoreId)obj).toString().equals(this.toString);
      }
   }

   public int compareTo(Object o) {
      if (o instanceof DatastoreIdImpl) {
         DatastoreIdImpl c = (DatastoreIdImpl)o;
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
