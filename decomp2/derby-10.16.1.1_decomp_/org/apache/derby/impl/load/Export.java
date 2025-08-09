package org.apache.derby.impl.load;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.derby.iapi.security.Securable;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public class Export extends ExportAbstract {
   private String outputFileName;
   private String lobsFileName;

   private void doExport() throws SQLException {
      try {
         if (this.entityName == null && this.selectStatement == null) {
            throw LoadError.entityNameMissing();
         } else if (this.outputFileName == null) {
            throw LoadError.dataFileNull();
         } else if (this.dataFileExists(this.outputFileName)) {
            throw LoadError.dataFileExists(this.outputFileName);
         } else if (this.lobsFileName != null && this.lobsFileExists(this.lobsFileName)) {
            throw LoadError.lobsFileExists(this.lobsFileName);
         } else {
            try {
               this.doAllTheWork();
            } catch (IOException var2) {
               throw LoadError.errorWritingData(var2);
            }
         }
      } catch (Exception var3) {
         throw LoadError.unexpectedError(var3);
      }
   }

   private Export(Connection var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8) throws SQLException {
      this.con = var1;
      this.schemaName = var2;
      this.entityName = var3;
      this.selectStatement = var4;
      this.outputFileName = var5;

      try {
         this.controlFileReader = new ControlInfo();
         this.controlFileReader.setControlProperties(var6, var7, var8);
      } catch (Exception var10) {
         throw LoadError.unexpectedError(var10);
      }
   }

   private void setLobsExtFileName(String var1) throws SQLException {
      if (var1 == null) {
         throw PublicAPI.wrapStandardException(StandardException.newException("XIE0Q.S", new Object[0]));
      } else {
         this.lobsFileName = var1;
         this.lobsInExtFile = true;
      }
   }

   private boolean lobsFileExists(String var1) throws SQLException {
      if (var1 == null) {
         throw PublicAPI.wrapStandardException(StandardException.newException("XIE0Q.S", new Object[0]));
      } else {
         var1 = FileUtil.stripProtocolFromFileName(var1);
         File var2 = new File(var1);
         return this.fileExists(var2);
      }
   }

   private boolean dataFileExists(String var1) throws SQLException {
      if (var1 == null) {
         throw PublicAPI.wrapStandardException(StandardException.newException("XIE05.S", new Object[0]));
      } else {
         var1 = FileUtil.stripProtocolFromFileName(var1);
         File var2 = new File(var1);
         return this.fileExists(var2);
      }
   }

   private final boolean fileExists(File var1) {
      return var1.exists();
   }

   public static void exportTable(Connection var0, String var1, String var2, String var3, String var4, String var5, String var6) throws SQLException {
      try {
         SecurityUtil.authorize(Securable.EXPORT_TABLE);
      } catch (StandardException var8) {
         throw PublicAPI.wrapStandardException(var8);
      }

      Export var7 = new Export(var0, var1, var2, (String)null, var3, var5, var4, var6);
      var7.doExport();
   }

   public static void exportTable(Connection var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7) throws SQLException {
      Export var8 = new Export(var0, var1, var2, (String)null, var3, var5, var4, var6);
      var8.setLobsExtFileName(var7);
      var8.doExport();
   }

   public static void exportQuery(Connection var0, String var1, String var2, String var3, String var4, String var5) throws SQLException {
      Export var6 = new Export(var0, (String)null, (String)null, var1, var2, var4, var3, var5);
      var6.doExport();
   }

   public static void exportQuery(Connection var0, String var1, String var2, String var3, String var4, String var5, String var6) throws SQLException {
      Export var7 = new Export(var0, (String)null, (String)null, var1, var2, var4, var3, var5);
      var7.setLobsExtFileName(var6);
      var7.doExport();
   }

   protected ExportWriteDataAbstract getExportWriteData() throws Exception {
      return this.lobsInExtFile ? new ExportWriteData(this.outputFileName, this.lobsFileName, this.controlFileReader) : new ExportWriteData(this.outputFileName, this.controlFileReader);
   }
}
