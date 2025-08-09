package org.apache.derby.diag;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Locale;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTITemplate;

public class StatementDuration extends VTITemplate {
   private boolean gotFile;
   private InputStreamReader inputFileStreamReader;
   private InputStream inputStream;
   private BufferedReader bufferedReader;
   private String inputFileName;
   private Hashtable hashTable;
   private String line;
   private int endTimestampIndex;
   private int threadIndex;
   private int xidIndex;
   private int lccidIndex;
   private String[] currentRow;
   private static final String END_TIMESTAMP = " Thread";
   private static final String BEGIN_THREAD_STRING = "[";
   private static final String END_THREAD_STRING = "]";
   private static final String BEGIN_XID_STRING = "= ";
   private static final String END_XID_STRING = ")";
   private static final String BEGIN_EXECUTING_STRING = "Executing prepared";
   private static final String END_EXECUTING_STRING = " :End prepared";
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("TS", 12, false, 29), EmbedResultSetMetaData.getResultColumnDescriptor("THREADID", 12, false, 80), EmbedResultSetMetaData.getResultColumnDescriptor("XID", 12, false, 15), EmbedResultSetMetaData.getResultColumnDescriptor("LCCID", 12, false, 10), EmbedResultSetMetaData.getResultColumnDescriptor("LOGTEXT", 12, true, 32672), EmbedResultSetMetaData.getResultColumnDescriptor("DURATION", 12, false, 10)};
   private static final ResultSetMetaData metadata;

   public StatementDuration() throws StandardException {
      DiagUtil.checkAccess();
      String var1 = System.getProperty("derby.system.home");
      this.inputFileName = "derby.log";
      if (var1 != null) {
         this.inputFileName = var1 + "/" + this.inputFileName;
      }

   }

   public StatementDuration(String var1) throws StandardException {
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
         } catch (FileNotFoundException var10) {
            throw new SQLException(var10.getMessage());
         }

         this.hashTable = new Hashtable();
      }

      while(true) {
         try {
            this.line = this.bufferedReader.readLine();
         } catch (IOException var9) {
            throw new SQLException(var9.getMessage());
         }

         if (this.line == null) {
            return false;
         }

         this.endTimestampIndex = this.line.indexOf(" Thread");
         this.threadIndex = this.line.indexOf("[");
         this.xidIndex = this.line.indexOf("= ");
         this.lccidIndex = this.line.indexOf("= ", this.xidIndex + 1);
         if (this.endTimestampIndex != -1 && this.threadIndex != -1 && this.xidIndex != -1) {
            String[] var1 = new String[6];

            for(int var2 = 1; var2 <= 5; ++var2) {
               var1[var2 - 1] = this.setupColumn(var2);
            }

            String[] var11 = (String[])this.hashTable.put(var1[3], var1);
            if (var11 != null) {
               this.currentRow = var11;
               Timestamp var3 = this.stringToTimestamp(var1[0]);
               long var4 = var3.getTime() + (long)(var3.getNanos() / 1000000);
               Timestamp var6 = this.stringToTimestamp(this.currentRow[0]);
               long var7 = var6.getTime() + (long)(var6.getNanos() / 1000000);
               this.currentRow[5] = Long.toString(var4 - var7);
               return true;
            }
         }
      }
   }

   private Timestamp stringToTimestamp(String var1) throws SQLException {
      String var2 = var1.trim();
      if (!Character.isDigit(var2.charAt(var2.length() - 1))) {
         var2 = var2.substring(0, var2.length() - 4);
         return Timestamp.valueOf(var2);
      } else {
         SimpleDateFormat var3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

         try {
            return new Timestamp(var3.parse(var2).getTime());
         } catch (Exception var5) {
            throw new SQLException(var5.getMessage());
         }
      }
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
      return this.currentRow[var1 - 1];
   }

   private String setupColumn(int var1) throws SQLException {
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
            StringBuffer var2 = new StringBuffer(64);
            if (this.line.indexOf("Executing prepared") == -1) {
               var2.append(this.line.substring(this.line.indexOf(")", this.lccidIndex) + 3));
            } else {
               int var3 = this.line.indexOf(" :End prepared", this.lccidIndex);
               if (var3 == -1) {
                  var2.append(this.line.substring(this.line.indexOf(")", this.lccidIndex) + 3));
               } else {
                  var2.append(this.line.substring(this.line.indexOf(")", this.lccidIndex) + 3, var3));
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
            return null;
      }
   }

   public boolean wasNull() {
      return false;
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
