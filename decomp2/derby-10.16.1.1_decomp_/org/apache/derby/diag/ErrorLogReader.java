package org.apache.derby.diag;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTITemplate;

public class ErrorLogReader extends VTITemplate {
   private boolean gotFile;
   private InputStreamReader inputFileStreamReader;
   private InputStream inputStream;
   private BufferedReader bufferedReader;
   private String inputFileName;
   private String line;
   private int endTimestampIndex;
   private int threadIndex;
   private int xidIndex;
   private int lccidIndex;
   private int databaseIndex;
   private int drdaidIndex;
   private static final String END_TIMESTAMP = " Thread";
   private static final String PARAMETERS_STRING = "Parameters:";
   private static final String BEGIN_THREAD_STRING = "[";
   private static final String END_THREAD_STRING = "]";
   private static final String BEGIN_XID_STRING = "= ";
   private static final String END_XID_STRING = ")";
   private static final String BEGIN_DATABASE_STRING = "(DATABASE =";
   private static final String END_DATABASE_STRING = ")";
   private static final String BEGIN_DRDAID_STRING = "(DRDAID =";
   private static final String END_DRDAID_STRING = ")";
   private static final String BEGIN_EXECUTING_STRING = "Executing prepared";
   private static final String END_EXECUTING_STRING = " :End prepared";
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("TS", 12, false, 29), EmbedResultSetMetaData.getResultColumnDescriptor("THREADID", 12, false, 40), EmbedResultSetMetaData.getResultColumnDescriptor("XID", 12, false, 15), EmbedResultSetMetaData.getResultColumnDescriptor("LCCID", 12, false, 15), EmbedResultSetMetaData.getResultColumnDescriptor("DATABASE", 12, false, 128), EmbedResultSetMetaData.getResultColumnDescriptor("DRDAID", 12, true, 50), EmbedResultSetMetaData.getResultColumnDescriptor("LOGTEXT", 12, false, 32672)};
   private static final ResultSetMetaData metadata;

   public ErrorLogReader() throws StandardException {
      DiagUtil.checkAccess();
      String var1 = System.getProperty("derby.system.home");
      this.inputFileName = "derby.log";
      if (var1 != null) {
         this.inputFileName = var1 + "/" + this.inputFileName;
      }

   }

   public ErrorLogReader(String var1) throws StandardException {
      DiagUtil.checkAccess();
      this.inputFileName = var1;
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   public boolean next() throws SQLException {
      if (!this.gotFile) {
         this.gotFile = true;

         try {
            this.inputFileStreamReader = new InputStreamReader(new FileInputStream(this.inputFileName));
            this.bufferedReader = new BufferedReader(this.inputFileStreamReader, 32768);
         } catch (FileNotFoundException var3) {
            throw new SQLException(var3.getMessage());
         }
      }

      do {
         try {
            this.line = this.bufferedReader.readLine();
         } catch (IOException var2) {
            throw new SQLException(var2.getMessage());
         }

         if (this.line == null) {
            return false;
         }

         this.endTimestampIndex = this.line.indexOf(" Thread");
         this.threadIndex = this.line.indexOf("[");
         this.xidIndex = this.line.indexOf("= ");
         this.lccidIndex = this.line.indexOf("= ", this.xidIndex + 1);
         this.databaseIndex = this.line.indexOf("(DATABASE =", this.lccidIndex + 1);
         this.drdaidIndex = this.line.indexOf("(DRDAID =", this.databaseIndex + 1);
      } while(this.line.indexOf("Parameters:") != -1 || this.endTimestampIndex == -1 || this.threadIndex == -1 || this.xidIndex == -1 || this.databaseIndex == -1);

      return true;
   }

   public void close() {
      if (this.bufferedReader != null) {
         try {
            this.bufferedReader.close();
            this.inputFileStreamReader.close();
         } catch (IOException var5) {
         } finally {
            this.bufferedReader = null;
            this.inputFileStreamReader = null;
         }
      }

   }

   public String getString(int var1) throws SQLException {
      switch (var1) {
         case 1:
            return this.line.substring(0, this.endTimestampIndex);
         case 2:
            return this.line.substring(this.threadIndex + 1, this.line.indexOf("]"));
         case 3:
            return this.line.substring(this.xidIndex + 2, this.line.indexOf(")", this.xidIndex));
         case 4:
            return this.line.substring(this.lccidIndex + 2, this.line.indexOf(")", this.lccidIndex));
         case 5:
            return this.line.substring(this.databaseIndex + "(DATABASE =".length(), this.line.indexOf(")", this.databaseIndex));
         case 6:
            return this.line.substring(this.drdaidIndex + "(DRDAID =".length(), this.line.indexOf(")", this.drdaidIndex));
         case 7:
            StringBuffer var2 = new StringBuffer(64);
            if (this.line.indexOf("Executing prepared") == -1) {
               var2.append(this.line.substring(this.line.indexOf(")", this.drdaidIndex) + 3));
            } else {
               int var3 = this.line.indexOf(" :End prepared", this.drdaidIndex);
               if (var3 == -1) {
                  var2.append(this.line.substring(this.line.indexOf(")", this.drdaidIndex) + 3));
               } else {
                  var2.append(this.line.substring(this.line.indexOf(")", this.drdaidIndex) + 3, var3));
               }

               while(var3 == -1) {
                  try {
                     this.line = this.bufferedReader.readLine();
                  } catch (IOException var5) {
                     throw new SQLException("Error reading file " + var5);
                  }

                  var3 = this.line.indexOf(" :End prepared");
                  if (var3 == -1) {
                     var2.append(this.line);
                  } else {
                     var2.append(this.line.substring(0, var3));
                  }
               }
            }

            return StringUtil.truncate(var2.toString(), 32672);
         default:
            return "";
      }
   }

   public boolean wasNull() {
      return false;
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
