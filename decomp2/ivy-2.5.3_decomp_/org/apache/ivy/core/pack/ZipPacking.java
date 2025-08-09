package org.apache.ivy.core.pack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;

public class ZipPacking extends ArchivePacking {
   private static final String[] NAMES = new String[]{"zip", "jar", "war"};

   public String[] getNames() {
      return NAMES;
   }

   public String getUnpackedExtension(String ext) {
      if (ext.endsWith("zip") || ext.endsWith("jar") || ext.endsWith("war")) {
         ext = ext.substring(0, ext.length() - 3);
         if (ext.endsWith(".")) {
            ext = ext.substring(0, ext.length() - 1);
         }
      }

      return ext;
   }

   public void unpack(InputStream packed, File dest) throws IOException {
      ZipInputStream zip = new ZipInputStream(packed);
      Throwable var4 = null;

      try {
         ZipEntry entry = null;

         while((entry = zip.getNextEntry()) != null) {
            String entryName = entry.getName();
            File f = FileUtil.resolveFile(dest, entryName);
            if (!FileUtil.isLeadingPath(dest, f, true)) {
               Message.verbose("\t\tskipping " + entryName + " as its target " + f.getCanonicalPath() + " is outside of " + dest.getCanonicalPath() + ".");
            } else {
               Message.verbose("\t\texpanding " + entryName + " to " + f);
               File dirF = f.getParentFile();
               if (dirF != null) {
                  dirF.mkdirs();
               }

               if (entry.isDirectory()) {
                  f.mkdirs();
               } else {
                  this.writeFile(zip, f);
               }

               f.setLastModified(entry.getTime());
            }
         }
      } catch (Throwable var16) {
         var4 = var16;
         throw var16;
      } finally {
         if (zip != null) {
            if (var4 != null) {
               try {
                  zip.close();
               } catch (Throwable var15) {
                  var4.addSuppressed(var15);
               }
            } else {
               zip.close();
            }
         }

      }

   }

   protected void writeFile(InputStream zip, File f) throws IOException {
      FileOutputStream out = new FileOutputStream(f);
      Throwable var4 = null;

      try {
         FileUtil.copy((InputStream)zip, (OutputStream)out, (CopyProgressListener)null, false);
      } catch (Throwable var13) {
         var4 = var13;
         throw var13;
      } finally {
         if (out != null) {
            if (var4 != null) {
               try {
                  out.close();
               } catch (Throwable var12) {
                  var4.addSuppressed(var12);
               }
            } else {
               out.close();
            }
         }

      }

   }
}
