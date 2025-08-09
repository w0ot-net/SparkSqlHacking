package org.apache.ivy.osgi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
   public static void zip(File sourceDir, OutputStream targetStream) throws IOException {
      if (sourceDir.isFile() || sourceDir.isDirectory()) {
         ZipOutputStream cpZipOutputStream = new ZipOutputStream(targetStream);
         cpZipOutputStream.setLevel(9);
         zipFiles(sourceDir, sourceDir, cpZipOutputStream);
         cpZipOutputStream.finish();
         cpZipOutputStream.close();
      }
   }

   private static void zipFiles(File rootDir, File currDir, ZipOutputStream zos) throws IOException {
      if (currDir.isDirectory()) {
         for(File file : currDir.listFiles()) {
            zipFiles(rootDir, file, zos);
         }
      } else {
         String strAbsPath = currDir.getPath();
         String strZipEntryName = strAbsPath.substring(rootDir.getPath().length() + 1);
         byte[] b = new byte[(int)currDir.length()];
         FileInputStream fis = new FileInputStream(currDir);
         fis.read(b);
         fis.close();
         ZipEntry entry = new ZipEntry(strZipEntryName);
         zos.putNextEntry(entry);
         zos.write(b, 0, (int)currDir.length());
         zos.closeEntry();
      }

   }
}
