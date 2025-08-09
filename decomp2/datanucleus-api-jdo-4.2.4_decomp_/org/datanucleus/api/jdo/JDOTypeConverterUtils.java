package org.datanucleus.api.jdo;

import java.lang.reflect.Method;
import javax.jdo.AttributeConverter;
import org.datanucleus.util.NucleusLogger;

public class JDOTypeConverterUtils {
   public static Class getAttributeTypeForAttributeConverter(Class converterCls, Class attrTypeFallback) {
      Class attrType = attrTypeFallback;
      Method[] methods = converterCls.getMethods();
      if (methods != null) {
         for(int j = 0; j < methods.length; ++j) {
            if (methods[j].getName().equals("convertToAttribute")) {
               Class returnCls = methods[j].getReturnType();
               if (returnCls != Object.class) {
                  attrType = returnCls;
                  break;
               }
            }
         }
      }

      return attrType;
   }

   public static Class getDatastoreTypeForAttributeConverter(Class converterCls, Class attrType, Class dbTypeFallback) {
      Class dbType = dbTypeFallback;

      try {
         Class returnCls = converterCls.getMethod("convertToDatastore", attrType).getReturnType();
         if (returnCls != Object.class) {
            dbType = returnCls;
         }
      } catch (Exception e) {
         NucleusLogger.GENERAL.error("Exception in lookup", e);
      }

      return dbType;
   }
}
