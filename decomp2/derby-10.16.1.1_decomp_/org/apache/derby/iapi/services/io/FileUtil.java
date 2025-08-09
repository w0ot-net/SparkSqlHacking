package org.apache.derby.iapi.services.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Collections;
import java.util.EnumSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.WritableStorageFactory;

public abstract class FileUtil {
   private static final int BUFFER_SIZE = 16384;

   public static boolean removeDirectory(File var0) {
      if (var0 == null) {
         return false;
      } else if (!var0.exists()) {
         return true;
      } else if (!var0.isDirectory()) {
         return false;
      } else {
         String[] var1 = var0.list();
         if (var1 != null) {
            for(int var2 = 0; var2 < var1.length; ++var2) {
               File var3 = new File(var0, var1[var2]);
               if (var3.isDirectory()) {
                  if (!removeDirectory(var3)) {
                     return false;
                  }
               } else if (!var3.delete()) {
                  return false;
               }
            }
         }

         return var0.delete();
      }
   }

   public static boolean copyFile(File var0, File var1, byte[] var2) {
      if (var2 == null) {
         var2 = new byte[16384];
      }

      FileInputStream var3 = null;
      FileOutputStream var4 = null;

      boolean var6;
      try {
         var3 = new FileInputStream(var0);
         var4 = new FileOutputStream(var1);
         limitAccessToOwner(var1);

         for(int var5 = var3.read(var2); var5 != -1; var5 = var3.read(var2)) {
            var4.write(var2, 0, var5);
         }

         var3.close();
         var3 = null;
         var4.getFD().sync();
         var4.close();
         var4 = null;
         return true;
      } catch (IOException var20) {
         var6 = false;
      } finally {
         if (var3 != null) {
            try {
               var3.close();
            } catch (IOException var19) {
            }
         }

         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var18) {
            }
         }

      }

      return var6;
   }

   public static boolean copyDirectory(StorageFactory var0, StorageFile var1, File var2, byte[] var3, String[] var4, boolean var5) {
      if (var1 == null) {
         return false;
      } else if (!var1.exists()) {
         return true;
      } else if (!var1.isDirectory()) {
         return false;
      } else if (var2.exists()) {
         return false;
      } else if (!var2.mkdirs()) {
         return false;
      } else {
         try {
            limitAccessToOwner(var2);
         } catch (IOException var10) {
            return false;
         }

         String[] var6 = var1.list();
         if (var6 != null) {
            if (var3 == null) {
               var3 = new byte[16384];
            }

            label61:
            for(int var7 = 0; var7 < var6.length; ++var7) {
               String var8 = var6[var7];
               if (var4 != null) {
                  for(int var9 = 0; var9 < var4.length; ++var9) {
                     if (var8.equals(var4[var9])) {
                        continue label61;
                     }
                  }
               }

               StorageFile var11 = var0.newStorageFile(var1, var8);
               if (var11.isDirectory()) {
                  if (var5 && !copyDirectory(var0, var11, new File(var2, var8), var3, var4, var5)) {
                     return false;
                  }
               } else if (!copyFile(var0, var11, new File(var2, var8), var3)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static boolean copyFile(StorageFactory var0, StorageFile var1, File var2) {
      return copyFile(var0, var1, var2, (byte[])null);
   }

   public static boolean copyFile(StorageFactory var0, StorageFile var1, File var2, byte[] var3) {
      InputStream var4 = null;
      FileOutputStream var5 = null;

      boolean var7;
      try {
         var4 = var1.getInputStream();
         var5 = new FileOutputStream(var2);
         limitAccessToOwner(var2);
         if (var3 == null) {
            var3 = new byte[16384];
         }

         for(int var6 = var4.read(var3); var6 != -1; var6 = var4.read(var3)) {
            var5.write(var3, 0, var6);
         }

         var4.close();
         var4 = null;
         var5.getFD().sync();
         var5.close();
         var5 = null;
         return true;
      } catch (IOException var21) {
         var7 = false;
      } finally {
         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var20) {
            }
         }

         if (var5 != null) {
            try {
               var5.close();
            } catch (IOException var19) {
            }
         }

      }

      return var7;
   }

   public static boolean copyDirectory(WritableStorageFactory var0, File var1, StorageFile var2) {
      return copyDirectory(var0, var1, var2, (byte[])null, (String[])null);
   }

   public static boolean copyDirectory(WritableStorageFactory var0, File var1, StorageFile var2, byte[] var3, String[] var4) {
      if (var1 == null) {
         return false;
      } else if (!var1.exists()) {
         return true;
      } else if (!var1.isDirectory()) {
         return false;
      } else if (var2.exists()) {
         return false;
      } else if (!var2.mkdirs()) {
         return false;
      } else {
         try {
            var2.limitAccessToOwner();
         } catch (IOException var9) {
            return false;
         }

         String[] var5 = var1.list();
         if (var5 != null) {
            if (var3 == null) {
               var3 = new byte[16384];
            }

            label59:
            for(int var6 = 0; var6 < var5.length; ++var6) {
               String var7 = var5[var6];
               if (var4 != null) {
                  for(int var8 = 0; var8 < var4.length; ++var8) {
                     if (var7.equals(var4[var8])) {
                        continue label59;
                     }
                  }
               }

               File var10 = new File(var1, var7);
               if (var10.isDirectory()) {
                  if (!copyDirectory(var0, var10, var0.newStorageFile(var2, var7), var3, var4)) {
                     return false;
                  }
               } else if (!copyFile(var0, var10, var0.newStorageFile(var2, var7), var3)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static boolean copyFile(WritableStorageFactory var0, File var1, StorageFile var2) {
      return copyFile(var0, var1, var2, (byte[])null);
   }

   public static boolean copyFile(WritableStorageFactory var0, File var1, StorageFile var2, byte[] var3) {
      InputStream var4 = null;
      OutputStream var5 = null;

      boolean var7;
      try {
         var4 = new FileInputStream(var1);
         var5 = var2.getOutputStream();
         if (var3 == null) {
            var3 = new byte[16384];
         }

         for(int var6 = var4.read(var3); var6 != -1; var6 = var4.read(var3)) {
            var5.write(var3, 0, var6);
         }

         var4.close();
         var4 = null;
         var0.sync(var5, false);
         var5.close();
         var5 = null;
         return true;
      } catch (IOException var21) {
         var7 = false;
      } finally {
         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var20) {
            }
         }

         if (var5 != null) {
            try {
               var5.close();
            } catch (IOException var19) {
            }
         }

      }

      return var7;
   }

   public static boolean copyFile(WritableStorageFactory var0, StorageFile var1, StorageFile var2) {
      InputStream var3 = null;
      OutputStream var4 = null;

      boolean var6;
      try {
         var3 = var1.getInputStream();
         var4 = var2.getOutputStream();
         byte[] var5 = new byte[16384];

         for(int var24 = var3.read(var5); var24 != -1; var24 = var3.read(var5)) {
            var4.write(var5, 0, var24);
         }

         var3.close();
         var3 = null;
         var0.sync(var4, false);
         var4.close();
         var4 = null;
         return true;
      } catch (IOException var20) {
         var6 = false;
      } finally {
         if (var3 != null) {
            try {
               var3.close();
            } catch (IOException var19) {
            }
         }

         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var18) {
            }
         }

      }

      return var6;
   }

   public static String stripProtocolFromFileName(String var0) {
      String var1 = var0;

      try {
         URL var2 = new URL(var0);
         var1 = var2.getFile();
      } catch (MalformedURLException var3) {
      }

      return var1;
   }

   public static void limitAccessToOwner(File var0) throws IOException {
      String var1 = PropertyUtil.getSystemProperty("derby.storage.useDefaultFilePermissions");
      if (var1 != null) {
         if (Boolean.parseBoolean(var1.trim())) {
            return;
         }
      } else if (!PropertyUtil.getSystemBoolean("derby.__serverStartedFromCmdLine", false)) {
         return;
      }

      if (!limitAccessToOwnerViaFile(var0)) {
         limitAccessToOwnerViaFileAttributeView(var0);
      }
   }

   private static boolean limitAccessToOwnerViaFile(File var0) {
      boolean var1 = var0.setWritable(false, false);
      var1 &= var0.setWritable(true, true);
      var1 &= var0.setReadable(false, false);
      var1 &= var0.setReadable(true, true);
      if (var0.isDirectory()) {
         var1 &= var0.setExecutable(false, false);
         var1 &= var0.setExecutable(true, true);
      }

      return var1;
   }

   private static boolean limitAccessToOwnerViaFileAttributeView(File var0) throws IOException {
      Path var1 = var0.toPath();
      PosixFileAttributeView var2 = (PosixFileAttributeView)Files.getFileAttributeView(var1, PosixFileAttributeView.class);
      if (var2 != null) {
         EnumSet var5 = EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
         if (var0.isDirectory()) {
            var5.add(PosixFilePermission.OWNER_EXECUTE);
         }

         var2.setPermissions(var5);
         return true;
      } else {
         AclFileAttributeView var3 = (AclFileAttributeView)Files.getFileAttributeView(var1, AclFileAttributeView.class);
         if (var3 != null) {
            AclEntry var4 = AclEntry.newBuilder().setPrincipal(Files.getOwner(var1)).setType(AclEntryType.ALLOW).setPermissions(EnumSet.allOf(AclEntryPermission.class)).build();
            var3.setAcl(Collections.singletonList(var4));
            return true;
         } else {
            return false;
         }
      }
   }
}
