package org.apache.derby.impl.tools.dblook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.tools.dblook;

public class DB_Jar {
   public static void doJars(String var0, Connection var1, boolean var2) throws SQLException {
      String var3 = System.getProperty("file.separator");
      Statement var4 = var1.createStatement();
      ResultSet var5 = var4.executeQuery("SELECT FILENAME, SCHEMAID, GENERATIONID, FILEID FROM SYS.SYSFILES");
      boolean var6 = true;

      while(true) {
         StringBuffer var7;
         while(true) {
            if (!var5.next()) {
               var4.close();
               var5.close();
               return;
            }

            var7 = new StringBuffer();
            String var8 = var5.getString(1);
            String var9 = var5.getString(2);
            String var10 = var5.getString(3);
            String var11 = var5.getString(4);
            String var12 = dblook.lookupSchemaId(var9);
            if (!dblook.isIgnorableSchema(var12)) {
               doHeader(var6);
               if (var2) {
                  String var21 = dblook.unExpandDoubleQuotes(dblook.stripQuotes(dblook.lookupSchemaId(var9)));
                  StringBuffer var22 = new StringBuffer();
                  var22.append(var11);
                  var22.append(".jar.G");
                  var22.append(var10);
                  StringBuffer var23 = new StringBuffer();
                  var23.append(var0);
                  var23.append(var3);
                  var23.append("jar");
                  var23.append(var3);
                  var23.append(var22.toString());
                  Object var25 = null;

                  try {
                     String var10002 = System.getProperty("user.dir");
                     File var27 = new File(var10002 + var3 + "DBJARS");
                     var26 = var27.getAbsolutePath();
                     var27.mkdirs();
                     doCopy(var23.toString(), var26 + var3 + var22);
                  } catch (Exception var18) {
                     Logs.debug("DBLOOK_FailedToLoadJar", var25 + var3 + var22.toString());
                     Logs.debug(var18);
                     var6 = false;
                     continue;
                  }

                  var7.append("CALL SQLJ.INSTALL_JAR('file:");
                  var7.append(var26);
                  var7.append(var3);
                  var7.append(var22);
                  var7.append("', '");
                  var7.append(dblook.addQuotes(dblook.expandDoubleQuotes(var21)));
                  var7.append(".");
                  var7.append(dblook.addQuotes(dblook.expandDoubleQuotes(var8)));
               } else {
                  var8 = dblook.addQuotes(dblook.expandDoubleQuotes(var8));
                  String var13 = dblook.stripQuotes(var12);
                  StringBuffer var14 = new StringBuffer(var3);
                  var14.append(dblook.stripQuotes(var8));
                  var14.append(".jar.G");
                  var14.append(var10);
                  StringBuffer var15 = new StringBuffer();
                  var15.append(var0);
                  var15.append(var3);
                  var15.append("jar");
                  var15.append(var3);
                  var15.append(var13);
                  var15.append(var14);
                  Object var16 = null;

                  try {
                     File var17 = new File(System.getProperty("user.dir") + var3 + "DBJARS" + var3 + var13);
                     var24 = var17.getAbsolutePath();
                     var17.mkdirs();
                     doCopy(var15.toString(), var24 + var14);
                  } catch (Exception var19) {
                     Logs.debug("DBLOOK_FailedToLoadJar", var16 + var14.toString());
                     Logs.debug(var19);
                     var6 = false;
                     continue;
                  }

                  var7.append("CALL SQLJ.INSTALL_JAR('file:");
                  var7.append(var24);
                  var7.append(var14);
                  var7.append("', '");
                  var7.append(var12);
                  var7.append(".");
                  var7.append(var8);
               }
               break;
            }
         }

         var7.append("', 0)");
         Logs.writeToNewDDL(var7.toString());
         Logs.writeStmtEndToNewDDL();
         Logs.writeNewlineToNewDDL();
         var6 = false;
      }
   }

   private static void doHeader(boolean var0) {
      if (var0) {
         Logs.reportString("----------------------------------------------");
         Logs.reportMessage("DBLOOK_JarsHeader");
         Logs.reportMessage("DBLOOK_Jar_Note");
         Logs.reportString("----------------------------------------------\n");
      }

   }

   private static void doCopy(String var0, String var1) throws IOException {
      FileInputStream var2 = new FileInputStream(var0);
      FileOutputStream var3 = new FileOutputStream(var1);

      while(var2.available() != 0) {
         byte[] var4 = new byte[var2.available()];
         var2.read(var4);
         var3.write(var4);
      }

      var2.close();
      var3.close();
   }
}
