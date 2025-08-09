package org.apache.derby.impl.sql;

import java.io.Serializable;
import java.util.Dictionary;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.property.PropertySetCallback;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;

public class LanguageDbPropertySetter implements PropertySetCallback {
   public void init(boolean var1, Dictionary var2) {
   }

   public boolean validate(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (var1.trim().equals("DataDictionaryVersion")) {
         throw StandardException.newException("XCY02.S", new Object[]{var1, var2});
      } else {
         if (var1.trim().equals("derby.database.sqlAuthorization")) {
            LanguageConnectionContext var4 = (LanguageConnectionContext)getContext("LanguageConnectionContext");
            if (var4.usesSqlAuthorization() && !Boolean.valueOf((String)var2)) {
               throw StandardException.newException("XCY02.S", new Object[]{var1, var2});
            }
         }

         if (var1.equals("derby.language.stalePlanCheckInterval")) {
            PropertyUtil.intPropertyValue("derby.language.stalePlanCheckInterval", var2, 5, Integer.MAX_VALUE, 100);
            return true;
         } else {
            return false;
         }
      }
   }

   public Serviceable apply(String var1, Serializable var2, Dictionary var3) {
      return null;
   }

   public Serializable map(String var1, Serializable var2, Dictionary var3) {
      return null;
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
