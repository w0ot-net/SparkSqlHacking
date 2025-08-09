package org.apache.ivy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile extends Properties {
   private File file;
   private String header;

   public PropertiesFile(File file, String header) {
      this.file = file;
      this.header = header;
      if (file.exists()) {
         FileInputStream fis = null;

         try {
            fis = new FileInputStream(file);
            this.load(fis);
         } catch (Exception ex) {
            Message.warn("exception occurred while reading properties file " + file, ex);
         }

         try {
            if (fis != null) {
               fis.close();
            }
         } catch (IOException var5) {
         }
      }

   }

   public void save() {
      FileOutputStream fos = null;

      try {
         if (this.file.getParentFile() != null && !this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
         }

         fos = new FileOutputStream(this.file);
         this.store(fos, this.header);
      } catch (Exception ex) {
         Message.warn("exception occurred while writing properties file " + this.file, ex);
      }

      try {
         if (fos != null) {
            fos.close();
         }
      } catch (IOException var3) {
      }

   }
}
