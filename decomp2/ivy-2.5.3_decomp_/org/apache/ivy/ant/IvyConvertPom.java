package org.apache.ivy.ant;

import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.plugins.parser.m2.PomModuleDescriptorParser;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.tools.ant.BuildException;

public class IvyConvertPom extends IvyTask {
   private File pomFile = null;
   private File ivyFile = null;

   public File getPomFile() {
      return this.pomFile;
   }

   public void setPomFile(File file) {
      this.pomFile = file;
   }

   public File getIvyFile() {
      return this.ivyFile;
   }

   public void setIvyFile(File ivyFile) {
      this.ivyFile = ivyFile;
   }

   public void doExecute() throws BuildException {
      try {
         if (this.pomFile == null) {
            throw new BuildException("source pom file is required for convertpom task");
         } else if (this.ivyFile == null) {
            throw new BuildException("destination ivy file is required for convertpom task");
         } else {
            ModuleDescriptor md = PomModuleDescriptorParser.getInstance().parseDescriptor(this.getSettings(), this.pomFile.toURI().toURL(), false);
            PomModuleDescriptorParser.getInstance().toIvyFile(this.pomFile.toURI().toURL().openStream(), new URLResource(this.pomFile.toURI().toURL()), this.getIvyFile(), md);
         }
      } catch (MalformedURLException e) {
         throw new BuildException("unable to convert given pom file to url: " + this.pomFile + ": " + e, e);
      } catch (ParseException e) {
         this.log(e.getMessage(), 0);
         throw new BuildException("syntax errors in pom file " + this.pomFile + ": " + e, e);
      } catch (Exception e) {
         throw new BuildException("impossible convert given pom file to ivy file: " + e + " from=" + this.pomFile + " to=" + this.ivyFile, e);
      }
   }
}
