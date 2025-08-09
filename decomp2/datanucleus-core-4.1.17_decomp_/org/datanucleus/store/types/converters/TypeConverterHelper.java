package org.datanucleus.store.types.converters;

import java.lang.reflect.Method;
import org.datanucleus.util.NucleusLogger;

public class TypeConverterHelper {
   public static Class getMemberTypeForTypeConverter(TypeConverter conv, Class datastoreType) {
      try {
         Method m = conv.getClass().getMethod("toMemberType", datastoreType);
         return m.getReturnType();
      } catch (Exception var5) {
         try {
            Method m = conv.getClass().getMethod("getMemberClass");
            return (Class)m.invoke(conv);
         } catch (Exception var4) {
            NucleusLogger.GENERAL.warn("Converter " + conv + " didn't have adequate information from toMemberType nor from getMemberClass");
            return null;
         }
      }
   }

   public static Class getDatastoreTypeForTypeConverter(TypeConverter conv, Class memberType) {
      try {
         Method m = conv.getClass().getMethod("toDatastoreType", memberType);
         return m.getReturnType();
      } catch (Exception var7) {
         try {
            Method m = conv.getClass().getMethod("getDatastoreClass");
            return (Class)m.invoke(conv);
         } catch (Exception var6) {
            try {
               Method[] methods = conv.getClass().getMethods();
               if (methods != null) {
                  for(int i = 0; i < methods.length; ++i) {
                     Class[] paramTypes = methods[i].getParameterTypes();
                     if (methods[i].getName().equals("toDatastoreType") && methods[i].getReturnType() != Object.class && paramTypes != null && paramTypes.length == 1) {
                        return methods[i].getReturnType();
                     }
                  }
               }
            } catch (Exception var5) {
               NucleusLogger.GENERAL.warn("Converter " + conv + " didn't have adequate information from toDatastoreType nor from getDatastoreClass");
            }

            return null;
         }
      }
   }
}
