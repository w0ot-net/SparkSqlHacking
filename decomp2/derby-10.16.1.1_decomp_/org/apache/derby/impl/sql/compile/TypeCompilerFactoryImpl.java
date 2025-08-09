package org.apache.derby.impl.sql.compile;

import java.lang.reflect.InvocationTargetException;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.types.TypeId;

public class TypeCompilerFactoryImpl implements TypeCompilerFactory {
   private static final String PACKAGE_NAME = "org.apache.derby.impl.sql.compile.";
   static TypeCompiler bitTypeCompiler;
   static TypeCompiler booleanTypeCompiler;
   static TypeCompiler charTypeCompiler;
   static TypeCompiler decimalTypeCompiler;
   static TypeCompiler doubleTypeCompiler;
   static TypeCompiler intTypeCompiler;
   static TypeCompiler longintTypeCompiler;
   static TypeCompiler longvarbitTypeCompiler;
   static TypeCompiler longvarcharTypeCompiler;
   static TypeCompiler realTypeCompiler;
   static TypeCompiler smallintTypeCompiler;
   static TypeCompiler tinyintTypeCompiler;
   static TypeCompiler dateTypeCompiler;
   static TypeCompiler timeTypeCompiler;
   static TypeCompiler timestampTypeCompiler;
   static TypeCompiler varbitTypeCompiler;
   static TypeCompiler varcharTypeCompiler;
   static TypeCompiler refTypeCompiler;
   static TypeCompiler blobTypeCompiler;
   static TypeCompiler clobTypeCompiler;
   static TypeCompiler xmlTypeCompiler;

   public TypeCompiler getTypeCompiler(TypeId var1) {
      return staticGetTypeCompiler(var1);
   }

   static TypeCompiler staticGetTypeCompiler(TypeId var0) {
      switch (var0.getJDBCTypeId()) {
         case -7:
         case 16:
            return booleanTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.BooleanTypeCompiler", booleanTypeCompiler, var0);
         case -6:
            return tinyintTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.NumericTypeCompiler", tinyintTypeCompiler, var0);
         case -5:
            return longintTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.NumericTypeCompiler", longintTypeCompiler, var0);
         case -4:
            return longvarbitTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.BitTypeCompiler", longvarbitTypeCompiler, var0);
         case -3:
            return varbitTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.BitTypeCompiler", varbitTypeCompiler, var0);
         case -2:
            return bitTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.BitTypeCompiler", bitTypeCompiler, var0);
         case -1:
            String var5 = var0.getSQLTypeName();
            return longvarcharTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.CharTypeCompiler", longvarcharTypeCompiler, var0);
         case 1:
            String var4 = var0.getSQLTypeName();
            return charTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.CharTypeCompiler", charTypeCompiler, var0);
         case 2:
         case 3:
            return decimalTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.NumericTypeCompiler", decimalTypeCompiler, var0);
         case 4:
            return intTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.NumericTypeCompiler", intTypeCompiler, var0);
         case 5:
            return smallintTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.NumericTypeCompiler", smallintTypeCompiler, var0);
         case 7:
            return realTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.NumericTypeCompiler", realTypeCompiler, var0);
         case 8:
            return doubleTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.NumericTypeCompiler", doubleTypeCompiler, var0);
         case 12:
            String var3 = var0.getSQLTypeName();
            return varcharTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.CharTypeCompiler", varcharTypeCompiler, var0);
         case 91:
            return dateTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.DateTypeCompiler", dateTypeCompiler, var0);
         case 92:
            return timeTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.TimeTypeCompiler", timeTypeCompiler, var0);
         case 93:
            return timestampTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.TimestampTypeCompiler", timestampTypeCompiler, var0);
         case 1111:
         case 2000:
            if (var0.isRefTypeId()) {
               return refTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.RefTypeCompiler", refTypeCompiler, var0);
            }

            UserDefinedTypeCompiler var2 = new UserDefinedTypeCompiler();
            ((BaseTypeCompiler)var2).setTypeId(var0);
            return var2;
         case 2004:
            return blobTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.LOBTypeCompiler", blobTypeCompiler, var0);
         case 2005:
            String var1 = var0.getSQLTypeName();
            return clobTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.CLOBTypeCompiler", clobTypeCompiler, var0);
         case 2009:
            return xmlTypeCompiler = getAnInstance("org.apache.derby.impl.sql.compile.XMLTypeCompiler", xmlTypeCompiler, var0);
         default:
            return null;
      }
   }

   private static TypeCompiler getAnInstance(String var0, TypeCompiler var1, TypeId var2) {
      if (var1 == null) {
         Object var3 = null;
         Object var4 = null;

         try {
            Class var11 = Class.forName(var0);
            var1 = (TypeCompiler)var11.getConstructor().newInstance();
            ((BaseTypeCompiler)var1).setTypeId(var2);
         } catch (ClassNotFoundException var6) {
         } catch (IllegalAccessException var7) {
         } catch (InstantiationException var8) {
         } catch (NoSuchMethodException var9) {
         } catch (InvocationTargetException var10) {
         }
      }

      return var1;
   }
}
