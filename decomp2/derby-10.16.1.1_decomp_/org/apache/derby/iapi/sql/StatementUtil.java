package org.apache.derby.iapi.sql;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class StatementUtil {
   private static final String[] TypeNames = new String[]{"", "INSERT", "INSERT", "UPDATE", "DELETE", "ENABLED", "DISABLED"};

   private StatementUtil() {
   }

   public static String typeName(int var0) {
      String var1;
      switch (var0) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
            var1 = TypeNames[var0];
            break;
         default:
            var1 = "UNKNOWN";
      }

      return var1;
   }

   public static SchemaDescriptor getSchemaDescriptor(String var0, boolean var1, DataDictionary var2, LanguageConnectionContext var3, CompilerContext var4) throws StandardException {
      SchemaDescriptor var5 = null;
      boolean var6 = false;
      boolean var7 = false;
      if (var0 == null) {
         var5 = var4.getCompilationSchema();
         if (var5 == null) {
            var5 = var3.getDefaultSchema();
            var6 = true;
            var4.setCompilationSchema(var5);
         } else {
            var7 = true;
         }

         var0 = var5.getSchemaName();
      }

      SchemaDescriptor var8 = var2.getSchemaDescriptor(var0, var3.getTransactionCompile(), var1);
      if (var6 || var7) {
         if (var8 != null && var8.getUUID() != null) {
            if (!var8.getUUID().equals(var5.getUUID())) {
               if (var6) {
                  var3.setDefaultSchema(var8);
               }

               var4.setCompilationSchema(var8);
            }
         } else {
            var5.setUUID((UUID)null);
            var8 = var5;
         }
      }

      return var8;
   }
}
