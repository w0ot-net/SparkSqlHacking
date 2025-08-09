package org.apache.derby.impl.sql.conn;

import java.util.List;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.Authorizer;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.StatementPermission;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

class GenericAuthorizer implements Authorizer {
   private static final int NO_ACCESS = 0;
   private static final int READ_ACCESS = 1;
   private static final int FULL_ACCESS = 2;
   private int userAccessLevel;
   boolean readOnlyConnection;
   private final LanguageConnectionContext lcc;

   GenericAuthorizer(LanguageConnectionContext var1) throws StandardException {
      this.lcc = var1;
      this.refresh();
   }

   private boolean connectionMustRemainReadOnly() {
      return this.lcc.getDatabase().isReadOnly() || this.userAccessLevel == 1;
   }

   public void authorize(int var1) throws StandardException {
      this.authorize((Activation)null, var1);
   }

   public void authorize(Activation var1, int var2) throws StandardException {
      short var3 = this.lcc.getStatementContext().getSQLAllowed();
      switch (var2) {
         case 0:
         case 5:
            if (this.isReadOnlyConnection()) {
               throw StandardException.newException("25502", new Object[0]);
            }

            if (var3 > 0) {
               throw externalRoutineException(var2, var3);
            }
            break;
         case 1:
            if (var3 > 1) {
               throw externalRoutineException(var2, var3);
            }
            break;
         case 2:
         case 3:
            if (var3 == 3) {
               throw externalRoutineException(var2, var3);
            }
            break;
         case 4:
         case 6:
            if (this.isReadOnlyConnection()) {
               throw StandardException.newException("25503", new Object[0]);
            }

            if (var3 > 0) {
               throw externalRoutineException(var2, var3);
            }
      }

      if (var1 != null) {
         List var4 = var1.getPreparedStatement().getRequiredPermissionsList();
         this.authorize(var4, var1);
      }

   }

   public void authorize(List var1, Activation var2) throws StandardException {
      DataDictionary var3 = this.lcc.getDataDictionary();
      if (var1 != null && !var1.isEmpty() && !this.lcc.getCurrentUserId(var2).equals(var3.getAuthorizationDatabaseOwner())) {
         int var4 = var3.startReading(this.lcc);
         this.lcc.beginNestedTransaction(true);

         try {
            try {
               for(StatementPermission var6 : var1) {
                  var6.check(this.lcc, false, var2);
               }
            } finally {
               var3.doneReading(var4, this.lcc);
            }
         } finally {
            this.lcc.commitNestedTransaction();
         }
      }

   }

   private static StandardException externalRoutineException(int var0, int var1) {
      String var2;
      if (var1 == 1) {
         var2 = "38002";
      } else if (var1 == 2) {
         switch (var0) {
            case 0:
            case 4:
            case 5:
            case 6:
               var2 = "38002";
               break;
            case 1:
            case 2:
            case 3:
            default:
               var2 = "38004";
         }
      } else {
         var2 = "38001";
      }

      return StandardException.newException(var2, new Object[0]);
   }

   private void getUserAccessLevel() throws StandardException {
      this.userAccessLevel = 0;
      if (this.userOnAccessList("derby.database.fullAccessUsers")) {
         this.userAccessLevel = 2;
      }

      if (this.userAccessLevel == 0 && this.userOnAccessList("derby.database.readOnlyAccessUsers")) {
         this.userAccessLevel = 1;
      }

      if (this.userAccessLevel == 0) {
         this.userAccessLevel = this.getDefaultAccessLevel();
      }

   }

   private int getDefaultAccessLevel() throws StandardException {
      TransactionController var1 = this.lcc.getTransactionExecute();
      String var2 = PropertyUtil.getServiceProperty(var1, "derby.database.defaultConnectionMode");
      if (var2 == null) {
         return 2;
      } else if (StringUtil.SQLEqualsIgnoreCase(var2, "NOACCESS")) {
         return 0;
      } else if (StringUtil.SQLEqualsIgnoreCase(var2, "READONLYACCESS")) {
         return 1;
      } else {
         return StringUtil.SQLEqualsIgnoreCase(var2, "FULLACCESS") ? 2 : 2;
      }
   }

   private boolean userOnAccessList(String var1) throws StandardException {
      TransactionController var2 = this.lcc.getTransactionExecute();
      String var3 = PropertyUtil.getServiceProperty(var2, var1);
      return IdUtil.idOnList(this.lcc.getSessionUserId(), var3);
   }

   public boolean isReadOnlyConnection() {
      return this.readOnlyConnection;
   }

   public void setReadOnlyConnection(boolean var1, boolean var2) throws StandardException {
      if (var2 && !var1 && this.connectionMustRemainReadOnly()) {
         throw StandardException.newException("25505", new Object[0]);
      } else {
         this.readOnlyConnection = var1;
      }
   }

   public final void refresh() throws StandardException {
      this.getUserAccessLevel();
      if (!this.readOnlyConnection) {
         this.readOnlyConnection = this.connectionMustRemainReadOnly();
      }

      if (this.userAccessLevel == 0) {
         throw StandardException.newException("08004.C.3", new Object[0]);
      }
   }
}
