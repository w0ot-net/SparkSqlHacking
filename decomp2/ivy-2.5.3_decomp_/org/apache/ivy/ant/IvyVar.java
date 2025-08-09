package org.apache.ivy.ant;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.tools.ant.BuildException;

public class IvyVar extends IvyTask {
   private String name;
   private String value;
   private File file;
   private String url;
   private String prefix;

   public File getFile() {
      return this.file;
   }

   public void setFile(File aFile) {
      this.file = aFile;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String aName) {
      this.name = aName;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String aPrefix) {
      this.prefix = aPrefix;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String aUrl) {
      this.url = aUrl;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String aValue) {
      this.value = aValue;
   }

   public void doExecute() throws BuildException {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();
      if (this.getName() != null) {
         settings.setVariable(this.getVarName(this.getName()), this.getValue());
      } else {
         Properties props = new Properties();
         InputStream is = null;

         try {
            if (this.getFile() != null) {
               is = new FileInputStream(this.getFile());
            } else {
               if (this.getUrl() == null) {
                  throw new BuildException("specify either name or file or url to ivy var task");
               }

               is = (new URL(this.getUrl())).openStream();
            }

            props.load(is);
         } catch (Exception ex) {
            throw new BuildException("impossible to load variables from file: " + ex, ex);
         } finally {
            if (is != null) {
               try {
                  is.close();
               } catch (Exception var12) {
               }
            }

         }

         for(Map.Entry entry : props.entrySet()) {
            settings.setVariable(this.getVarName((String)entry.getKey()), (String)entry.getValue());
         }
      }

   }

   private String getVarName(String name) {
      String prefix = this.getPrefix();
      if (prefix != null) {
         return prefix.endsWith(".") ? prefix + name : prefix + "." + name;
      } else {
         return name;
      }
   }
}
