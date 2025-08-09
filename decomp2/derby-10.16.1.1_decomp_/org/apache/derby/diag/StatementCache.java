package org.apache.derby.diag;

import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Vector;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.impl.sql.GenericPreparedStatement;
import org.apache.derby.impl.sql.GenericStatement;
import org.apache.derby.impl.sql.conn.CachedStatement;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTITemplate;

public final class StatementCache extends VTITemplate {
   private int position = -1;
   private Vector data;
   private GenericPreparedStatement currentPs;
   private boolean wasNull;
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("ID", 1, false, 36), EmbedResultSetMetaData.getResultColumnDescriptor("SCHEMANAME", 12, true, 128), EmbedResultSetMetaData.getResultColumnDescriptor("SQL_TEXT", 12, false, 32672), EmbedResultSetMetaData.getResultColumnDescriptor("UNICODE", -7, false), EmbedResultSetMetaData.getResultColumnDescriptor("VALID", -7, false), EmbedResultSetMetaData.getResultColumnDescriptor("COMPILED_AT", 93, true)};
   private static final ResultSetMetaData metadata;

   public StatementCache() throws StandardException {
      DiagUtil.checkAccess();
      LanguageConnectionContext var1 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      CacheManager var2 = var1.getLanguageConnectionFactory().getStatementCache();
      if (var2 != null) {
         Collection var3 = var2.values();
         this.data = new Vector(var3.size());

         for(CachedStatement var5 : var3) {
            GenericPreparedStatement var6 = var5.getPreparedStatement();
            this.data.add(var6);
         }
      }

   }

   public boolean next() {
      if (this.data == null) {
         return false;
      } else {
         ++this.position;

         while(this.position < this.data.size()) {
            this.currentPs = (GenericPreparedStatement)this.data.get(this.position);
            if (this.currentPs != null) {
               return true;
            }

            ++this.position;
         }

         this.data = null;
         return false;
      }
   }

   public void close() {
      this.data = null;
      this.currentPs = null;
   }

   public String getString(int var1) {
      this.wasNull = false;
      switch (var1) {
         case 1:
            return this.currentPs.getObjectName();
         case 2:
            return ((GenericStatement)this.currentPs.statement).getCompilationSchema();
         case 3:
            String var2 = this.currentPs.getSource();
            var2 = StringUtil.truncate(var2, 32672);
            return var2;
         default:
            return null;
      }
   }

   public boolean getBoolean(int var1) {
      this.wasNull = false;
      switch (var1) {
         case 4 -> {
            return true;
         }
         case 5 -> {
            return this.currentPs.isValid();
         }
         default -> {
            return false;
         }
      }
   }

   public Timestamp getTimestamp(int var1) {
      Timestamp var2 = this.currentPs.getEndCompileTimestamp();
      this.wasNull = var2 == null;
      return var2;
   }

   public boolean wasNull() {
      return this.wasNull;
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
