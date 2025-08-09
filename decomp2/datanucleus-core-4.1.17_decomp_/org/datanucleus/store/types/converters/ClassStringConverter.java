package org.datanucleus.store.types.converters;

import org.datanucleus.ClassLoaderResolver;

public class ClassStringConverter implements TypeConverter {
   private static final long serialVersionUID = 913106642606912411L;
   ClassLoaderResolver clr = null;

   public void setClassLoaderResolver(ClassLoaderResolver clr) {
      this.clr = clr;
   }

   public Class toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         try {
            return this.clr != null ? this.clr.classForName(str) : Class.forName(str);
         } catch (ClassNotFoundException var3) {
            return null;
         }
      }
   }

   public String toDatastoreType(Class cls) {
      return cls != null ? cls.getName() : null;
   }
}
