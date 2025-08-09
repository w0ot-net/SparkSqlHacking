package org.apache.derby.diag;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleClosureIterator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTITemplate;

public class ContainedRoles extends VTITemplate {
   RoleClosureIterator rci;
   String nextRole;
   boolean initialized;
   String role;
   boolean inverse;
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("ROLEID", 12, false, 128)};
   private static final ResultSetMetaData metadata;

   public ContainedRoles(String var1, int var2) throws SQLException {
      try {
         if (var1 != null) {
            this.role = IdUtil.parseSQLIdentifier(var1);
         }

         this.inverse = var2 != 0;
      } catch (StandardException var4) {
         throw PublicAPI.wrapStandardException(var4);
      }
   }

   public ContainedRoles(String var1) throws SQLException {
      this(var1, 0);
   }

   public boolean next() throws SQLException {
      try {
         if (!this.initialized) {
            this.initialized = true;
            LanguageConnectionContext var1 = ConnectionUtil.getCurrentLCC();
            DataDictionary var2 = var1.getDataDictionary();
            RoleGrantDescriptor var3 = var2.getRoleDefinitionDescriptor(this.role);
            if (var3 != null) {
               var1.beginNestedTransaction(true);

               try {
                  int var4 = var2.startReading(var1);

                  try {
                     this.rci = var2.createRoleClosureIterator(var1.getLastActivation().getTransactionController(), this.role, !this.inverse);
                  } finally {
                     var2.doneReading(var4, var1);
                  }
               } finally {
                  var1.commitNestedTransaction();
               }
            }
         }

         return this.rci != null && (this.nextRole = this.rci.next()) != null;
      } catch (StandardException var15) {
         throw PublicAPI.wrapStandardException(var15);
      }
   }

   public void close() {
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   public String getString(int var1) throws SQLException {
      return this.nextRole;
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
