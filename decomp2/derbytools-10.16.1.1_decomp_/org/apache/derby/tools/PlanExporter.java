package org.apache.derby.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.impl.tools.planexporter.AccessDatabase;
import org.apache.derby.impl.tools.planexporter.CreateHTMLFile;
import org.apache.derby.impl.tools.planexporter.CreateXMLFile;

public class PlanExporter {
   private static String dbURL = null;
   private static String xslStyleSheetName = "resources/vanilla_html.xsl";
   private static final int XML = 1;
   private static final int HTML = 2;
   private static final int XSL = 3;
   private static final LocalizedResource LOC_RES = LocalizedResource.getInstance();

   public static void main(String[] var0) {
      try {
         if (var0.length > 4 && var0.length < 10) {
            dbURL = var0[0];
            AccessDatabase var1 = new AccessDatabase(dbURL, var0[1], var0[2]);
            if (var1.verifySchemaExistance()) {
               if (var1.initializeDataArray()) {
                  var1.createXMLFragment();
                  var1.markTheDepth();
                  String var2 = var1.statement();
                  String var3 = var1.time();
                  var1.closeConnection();
                  if (var0.length == 8 && var0[3].equalsIgnoreCase("-adv")) {
                     int var10 = selectArg(var0[4]);
                     int var12 = selectArg(var0[6]);
                     if (var10 == 1 && var12 == 3) {
                        if (var0[7].toUpperCase().endsWith(".XSL")) {
                           generateXML(var1, var0[5], var2, var3, var0[7]);
                        } else {
                           generateXML(var1, var0[5], var2, var3, var0[7] + ".xsl");
                        }
                     } else if (var10 == 3 && var12 == 1) {
                        if (var0[5].toUpperCase().endsWith(".XSL")) {
                           generateXML(var1, var0[7], var2, var3, var0[5]);
                        } else {
                           generateXML(var1, var0[7], var2, var3, var0[5] + ".xsl");
                        }
                     } else {
                        printHelp();
                     }
                  } else if (var0.length == 5) {
                     int var4 = selectArg(var0[3]);
                     if (var4 != 0 && var4 != 3) {
                        if (var4 == 1) {
                           generateXML(var1, var0[4], var2, var3, (String)null);
                        } else {
                           generateXML(var1, "temp.xml", var2, var3, (String)null);
                           generateHTML("temp.xml", var0[4], xslStyleSheetName, true);
                           deleteFile("temp.xml");
                        }
                     } else {
                        printHelp();
                     }
                  } else if (var0.length == 7) {
                     int var8 = selectArg(var0[3]);
                     int var5 = selectArg(var0[5]);
                     if (var8 != 0 && var5 != 0) {
                        if (var8 == 1 && var5 == 2) {
                           generateXML(var1, var0[4], var2, var3, (String)null);
                           generateHTML(var0[4], var0[6], xslStyleSheetName, true);
                        } else if (var8 == 2 && var5 == 1) {
                           generateXML(var1, var0[6], var2, var3, (String)null);
                           generateHTML(var0[6], var0[4], xslStyleSheetName, true);
                        } else if (var8 == 2 && var5 == 3) {
                           generateXML(var1, "temp.xml", var2, var3, (String)null);
                           generateHTML("temp.xml", var0[4], var0[6], false);
                           deleteFile("temp.xml");
                        } else if (var8 == 3 && var5 == 2) {
                           generateXML(var1, "temp.xml", var2, var3, (String)null);
                           generateHTML("temp.xml", var0[6], var0[4], false);
                           deleteFile("temp.xml");
                        } else {
                           printHelp();
                        }
                     } else {
                        printHelp();
                     }
                  } else if (var0.length == 9) {
                     int var9 = selectArg(var0[3]);
                     int var11 = selectArg(var0[5]);
                     int var6 = selectArg(var0[7]);
                     if (var9 != 0 && var11 != 0 && var6 != 0) {
                        if (var9 == 1 && var11 == 2 && var6 == 3) {
                           generateXML(var1, var0[4], var2, var3, (String)null);
                           generateHTML(var0[4], var0[6], var0[8], false);
                        } else if (var9 == 2 && var11 == 3 && var6 == 1) {
                           generateXML(var1, var0[8], var2, var3, (String)null);
                           generateHTML(var0[8], var0[4], var0[6], false);
                        } else if (var9 == 3 && var11 == 1 && var6 == 2) {
                           generateXML(var1, var0[6], var2, var3, (String)null);
                           generateHTML(var0[6], var0[8], var0[4], false);
                        } else if (var9 == 1 && var11 == 3 && var6 == 2) {
                           generateXML(var1, var0[4], var2, var3, (String)null);
                           generateHTML(var0[4], var0[8], var0[6], false);
                        } else if (var9 == 2 && var11 == 1 && var6 == 3) {
                           generateXML(var1, var0[6], var2, var3, (String)null);
                           generateHTML(var0[6], var0[4], var0[8], false);
                        } else if (var9 == 3 && var11 == 2 && var6 == 1) {
                           generateXML(var1, var0[8], var2, var3, (String)null);
                           generateHTML(var0[8], var0[6], var0[4], false);
                        } else {
                           printHelp();
                        }
                     } else {
                        printHelp();
                     }
                  } else {
                     printHelp();
                  }
               } else {
                  System.out.println(LOC_RES.getTextMessage("PE_NoStatisticsCaptured"));
               }
            } else {
               System.out.println(LOC_RES.getTextMessage("PE_ErrorSchemaNotExist"));
            }
         } else {
            printHelp();
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   private static int selectArg(String var0) {
      if (var0.equalsIgnoreCase("-xml")) {
         return 1;
      } else if (var0.equalsIgnoreCase("-html")) {
         return 2;
      } else {
         return var0.equalsIgnoreCase("-xsl") ? 3 : 0;
      }
   }

   private static void generateXML(AccessDatabase var0, String var1, String var2, String var3, String var4) throws IOException {
      CreateXMLFile var5 = new CreateXMLFile(var0);
      String var6 = var1.toUpperCase().endsWith(".XML") ? var1 : var1 + ".xml";
      OutputStreamWriter var7 = new OutputStreamWriter(new FileOutputStream(var6), "UTF-8");

      try {
         var5.writeTheXMLFile(var2, var3, var7, var4);
      } finally {
         ((Writer)var7).close();
      }

   }

   private static void generateHTML(String var0, String var1, String var2, boolean var3) throws Exception {
      CreateHTMLFile var4 = new CreateHTMLFile();
      if (var0.toUpperCase().endsWith(".XML")) {
         var4.getHTML(var0, var2, var1, var3);
      } else {
         var4.getHTML(var0.concat(".xml"), var2, var1, var3);
      }

   }

   private static void printHelp() {
      System.out.println(LOC_RES.getTextMessage("PE_HelpText"));
   }

   private static void deleteFile(String var0) {
      File var1 = new File(var0);
      if (var1.exists()) {
         var1.delete();
      }
   }
}
