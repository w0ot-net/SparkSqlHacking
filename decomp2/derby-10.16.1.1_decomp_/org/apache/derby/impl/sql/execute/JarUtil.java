package org.apache.derby.impl.sql.execute;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.security.Securable;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.FileInfoDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

public class JarUtil {
   private LanguageConnectionContext lcc;
   private String schemaName;
   private String sqlName;
   private FileResource fr;
   private DataDictionary dd;
   private DataDescriptorGenerator ddg;

   private JarUtil(LanguageConnectionContext var1, String var2, String var3) throws StandardException {
      this.schemaName = var2;
      this.sqlName = var3;
      this.lcc = var1;
      this.fr = var1.getTransactionExecute().getFileHandler();
      this.dd = var1.getDataDictionary();
      this.ddg = this.dd.getDataDescriptorGenerator();
   }

   public static long install(LanguageConnectionContext var0, String var1, String var2, String var3) throws StandardException {
      SecurityUtil.authorize(Securable.INSTALL_JAR);
      JarUtil var4 = new JarUtil(var0, var1, var2);
      InputStream var5 = null;

      long var6;
      try {
         var5 = openJarURL(var3);
         var6 = var4.add(var5);
      } catch (IOException var16) {
         throw StandardException.newException("46001", var16, new Object[]{var3});
      } finally {
         try {
            if (var5 != null) {
               var5.close();
            }
         } catch (IOException var15) {
         }

      }

      return var6;
   }

   private long add(InputStream var1) throws StandardException {
      this.dd.startWriting(this.lcc);
      FileInfoDescriptor var2 = this.getInfo();
      if (var2 != null) {
         throw StandardException.newException("X0Y32.S", new Object[]{var2.getDescriptorType(), this.sqlName, var2.getSchemaDescriptor().getDescriptorType(), this.schemaName});
      } else {
         SchemaDescriptor var3 = this.dd.getSchemaDescriptor(this.schemaName, (TransactionController)null, true);

         long var8;
         try {
            this.notifyLoader(false);
            this.dd.invalidateAllSPSPlans();
            UUID var4 = BaseActivation.getMonitor().getUUIDFactory().createUUID();
            String var5 = mkExternalName(var4, this.schemaName, this.sqlName, this.fr.getSeparatorChar());
            long var6 = this.setJar(var5, var1, true, 0L);
            var2 = this.ddg.newFileInfoDescriptor(var4, var3, this.sqlName, var6);
            this.dd.addDescriptor(var2, var3, 12, false, this.lcc.getTransactionExecute());
            var8 = var6;
         } finally {
            this.notifyLoader(true);
         }

         return var8;
      }
   }

   public static void drop(LanguageConnectionContext var0, String var1, String var2) throws StandardException {
      SecurityUtil.authorize(Securable.REMOVE_JAR);
      JarUtil var3 = new JarUtil(var0, var1, var2);
      var3.drop();
   }

   private void drop() throws StandardException {
      this.dd.startWriting(this.lcc);
      FileInfoDescriptor var1 = this.getInfo();
      if (var1 == null) {
         throw StandardException.newException("X0X13.S", new Object[]{this.sqlName, this.schemaName});
      } else {
         String var2 = PropertyUtil.getServiceProperty(this.lcc.getTransactionExecute(), "derby.database.classpath");
         if (var2 != null) {
            String[][] var3 = IdUtil.parseDbClassPath(var2);
            boolean var4 = false;

            for(int var5 = 0; var5 < var3.length; ++var5) {
               if (var3.length == 2 && var3[var5][0].equals(this.schemaName) && var3[var5][1].equals(this.sqlName)) {
                  var4 = true;
               }
            }

            if (var4) {
               throw StandardException.newException("X0X07.S", new Object[]{IdUtil.mkQualifiedName(this.schemaName, this.sqlName), var2});
            }
         }

         try {
            this.notifyLoader(false);
            this.dd.invalidateAllSPSPlans();
            DependencyManager var9 = this.dd.getDependencyManager();
            var9.invalidateFor(var1, 17, this.lcc);
            UUID var10 = var1.getUUID();
            this.dd.dropFileInfoDescriptor(var1);
            this.fr.remove(mkExternalName(var10, this.schemaName, this.sqlName, this.fr.getSeparatorChar()), var1.getGenerationId());
         } finally {
            this.notifyLoader(true);
         }

      }
   }

   public static long replace(LanguageConnectionContext var0, String var1, String var2, String var3) throws StandardException {
      SecurityUtil.authorize(Securable.REPLACE_JAR);
      JarUtil var4 = new JarUtil(var0, var1, var2);
      InputStream var5 = null;

      long var6;
      try {
         var5 = openJarURL(var3);
         var6 = var4.replace(var5);
      } catch (IOException var16) {
         throw StandardException.newException("46001", var16, new Object[]{var3});
      } finally {
         try {
            if (var5 != null) {
               var5.close();
            }
         } catch (IOException var15) {
         }

      }

      return var6;
   }

   private long replace(InputStream var1) throws StandardException {
      this.dd.startWriting(this.lcc);
      FileInfoDescriptor var2 = this.getInfo();
      if (var2 == null) {
         throw StandardException.newException("X0X13.S", new Object[]{this.sqlName, this.schemaName});
      } else {
         long var7;
         try {
            this.notifyLoader(false);
            this.dd.invalidateAllSPSPlans();
            this.dd.dropFileInfoDescriptor(var2);
            String var3 = mkExternalName(var2.getUUID(), this.schemaName, this.sqlName, this.fr.getSeparatorChar());
            long var4 = this.setJar(var3, var1, false, var2.getGenerationId());
            FileInfoDescriptor var6 = this.ddg.newFileInfoDescriptor(var2.getUUID(), var2.getSchemaDescriptor(), this.sqlName, var4);
            this.dd.addDescriptor(var6, var2.getSchemaDescriptor(), 12, false, this.lcc.getTransactionExecute());
            var7 = var4;
         } finally {
            this.notifyLoader(true);
         }

         return var7;
      }
   }

   private FileInfoDescriptor getInfo() throws StandardException {
      SchemaDescriptor var1 = this.dd.getSchemaDescriptor(this.schemaName, (TransactionController)null, true);
      return this.dd.getFileInfoDescriptor(var1, this.sqlName);
   }

   private void notifyLoader(boolean var1) throws StandardException {
      ClassFactory var2 = this.lcc.getLanguageConnectionFactory().getClassFactory();
      var2.notifyModifyJar(var1);
   }

   private static InputStream openJarURL(String var0) throws IOException {
      try {
         return (new URL(var0)).openStream();
      } catch (MalformedURLException var2) {
         return new FileInputStream(var0);
      }
   }

   private long setJar(String var1, InputStream var2, boolean var3, long var4) throws StandardException {
      long var6;
      if (var3) {
         var6 = this.fr.add(var1, var2);
      } else {
         var6 = this.fr.replace(var1, var4, var2);
      }

      return var6;
   }

   public static String mkExternalName(UUID var0, String var1, String var2, char var3) throws StandardException {
      return mkExternalNameInternal(var0, var1, var2, var3, false, false);
   }

   private static String mkExternalNameInternal(UUID var0, String var1, String var2, char var3, boolean var4, boolean var5) throws StandardException {
      StringBuffer var6 = new StringBuffer(30);
      var6.append("jar");
      var6.append(var3);
      boolean var7 = false;
      if (!var4) {
         LanguageConnectionContext var8 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
         var7 = var8.getDataDictionary().checkVersion(210, (String)null);
      }

      if ((var4 || !var7) && (!var4 || !var5)) {
         var6.append(var1);
         var6.append(var3);
         var6.append(var2);
         var6.append(".jar");
      } else {
         var6.append(var0.toString());
         var6.append(".jar");
      }

      return var6.toString();
   }

   public static void upgradeJar(TransactionController var0, FileInfoDescriptor var1) throws StandardException {
      FileResource var2 = var0.getFileHandler();
      StorageFile var3 = var2.getAsFile(mkExternalNameInternal(var1.getUUID(), var1.getSchemaDescriptor().getSchemaName(), var1.getName(), File.separatorChar, true, false), var1.getGenerationId());
      StorageFile var4 = var2.getAsFile(mkExternalNameInternal(var1.getUUID(), var1.getSchemaDescriptor().getSchemaName(), var1.getName(), File.separatorChar, true, true), var1.getGenerationId());
      FileUtil.copyFile((File)(new File(var3.getPath())), (File)(new File(var4.getPath())), (byte[])null);
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }
}
