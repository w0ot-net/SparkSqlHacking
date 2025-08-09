package org.apache.derby.iapi.security;

import java.security.Permission;
import java.util.ArrayList;
import java.util.HashSet;
import javax.security.auth.Subject;
import org.apache.derby.authentication.SystemPrincipal;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.sql.conn.Authorizer;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.StatementRoutinePermission;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.security.SystemPermission;

public class SecurityUtil {
   private static final SystemPermission USE_DERBY_INTERNALS = new SystemPermission("engine", "usederbyinternals");

   public static Subject createSystemPrincipalSubject(String var0) {
      HashSet var1 = new HashSet();
      if (var0 != null) {
         var1.add(new SystemPrincipal(var0));
         var1.add(new SystemPrincipal(getAuthorizationId(var0)));
      }

      HashSet var2 = new HashSet();
      return new Subject(true, var1, var2, var2);
   }

   private static String getAuthorizationId(String var0) {
      if (var0 == null) {
         throw new NullPointerException("name can't be null");
      } else if (var0.length() == 0) {
         throw new IllegalArgumentException("name can't be empty");
      } else {
         try {
            return IdUtil.getUserAuthorizationId(var0);
         } catch (StandardException var2) {
            throw new IllegalArgumentException(var2.getMessage());
         }
      }
   }

   public static void checkSubjectHasPermission(Subject var0, Permission var1) {
   }

   public static void checkUserHasPermission(String var0, Permission var1) {
   }

   public static void authorize(Securable var0) throws StandardException {
      LanguageConnectionContext var1 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      if (var1.usesSqlAuthorization()) {
         Authorizer var2 = var1.getAuthorizer();
         DataDictionary var3 = var1.getDataDictionary();
         AliasDescriptor var4 = (AliasDescriptor)var3.getRoutineList(var0.routineSchemaID, var0.routineName, var0.routineType).get(0);
         ArrayList var5 = new ArrayList();
         StatementRoutinePermission var6 = new StatementRoutinePermission(var4.getObjectID());
         var5.add(var6);
         var2.authorize(var5, var1.getLastActivation());
      }

   }

   public static void checkDerbyInternalsPrivilege() {
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }
}
