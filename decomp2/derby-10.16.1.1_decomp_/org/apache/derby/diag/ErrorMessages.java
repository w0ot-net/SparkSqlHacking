package org.apache.derby.diag;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.vti.VTICosting;
import org.apache.derby.vti.VTIEnvironment;
import org.apache.derby.vti.VTITemplate;

public final class ErrorMessages extends VTITemplate implements VTICosting {
   private Properties p;
   private Enumeration keys;
   private String k;
   private String SQLState;
   private String message;
   private int severity;
   private int msgFile;
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("SQL_STATE", 12, true, 5), EmbedResultSetMetaData.getResultColumnDescriptor("MESSAGE", 12, true, 32672), EmbedResultSetMetaData.getResultColumnDescriptor("SEVERITY", 4, true)};
   private static final ResultSetMetaData metadata;

   public ErrorMessages() throws IOException {
      this.loadProperties();
   }

   public boolean next() {
      boolean var1 = true;
      if (!this.keys.hasMoreElements()) {
         this.close();
         var1 = false;
         return var1;
      } else {
         this.k = (String)this.keys.nextElement();
         if (this.notAnException()) {
            var1 = this.next();
         }

         if (var1) {
            this.SQLState = StandardException.getSQLStateFromIdentifier(this.k);
            this.message = MessageService.getTextMessage(this.k, new Object[0]);
            this.message = StringUtil.truncate(this.message, 32672);
         }

         return var1;
      }
   }

   public void close() {
      this.p = null;
      this.k = null;
      this.keys = null;
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   public String getString(int var1) throws SQLException {
      switch (var1) {
         case 1 -> {
            return this.SQLState;
         }
         case 2 -> {
            return this.message;
         }
         default -> {
            return super.getString(var1);
         }
      }
   }

   public int getInt(int var1) throws SQLException {
      switch (var1) {
         case 3 -> {
            return this.severity;
         }
         default -> {
            return super.getInt(var1);
         }
      }
   }

   private void loadProperties() throws IOException {
      this.p = new Properties();

      for(int var1 = 0; var1 < 50; ++var1) {
         this.msgFile = var1;
         InputStream var2 = this.run();
         if (var2 != null) {
            try {
               this.p.load(var2);
            } finally {
               try {
                  var2.close();
               } catch (IOException var9) {
               }

            }
         }
      }

      this.keys = this.p.keys();
   }

   private boolean notAnException() {
      if (this.k.length() < 5) {
         return true;
      } else {
         int var1 = StandardException.getSeverityFromIdentifier(this.k);
         if (var1 < 1) {
            return true;
         } else {
            this.severity = var1;
            return false;
         }
      }
   }

   public double getEstimatedRowCount(VTIEnvironment var1) {
      return (double)1000.0F;
   }

   public double getEstimatedCostPerInstantiation(VTIEnvironment var1) {
      return (double)5000.0F;
   }

   public boolean supportsMultipleInstantiations(VTIEnvironment var1) {
      return true;
   }

   public final InputStream run() {
      InputStream var1 = this.getClass().getResourceAsStream("/org/apache/derby/loc/m" + this.msgFile + "_en.properties");
      this.msgFile = 0;
      return var1;
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
