package org.apache.ivy.ant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.jar.Manifest;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.BundleInfoAdapter;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.core.ManifestParser;
import org.apache.ivy.osgi.core.OSGiManifestParser;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorWriter;
import org.apache.tools.ant.BuildException;

public class ConvertManifestTask extends IvyTask {
   private File manifest = null;
   private File ivyFile = null;
   private ExecutionEnvironmentProfileProvider profileProvider;

   public void setProfileProvider(ExecutionEnvironmentProfileProvider profileProvider) {
      this.profileProvider = profileProvider;
   }

   public void setManifest(File manifest) {
      this.manifest = manifest;
   }

   public void setIvyFile(File ivyFile) {
      this.ivyFile = ivyFile;
   }

   public void doExecute() throws BuildException {
      if (this.ivyFile == null) {
         throw new BuildException("destination ivy file is required for convertmanifest task");
      } else if (this.manifest == null) {
         throw new BuildException("source manifest file is required for convertmanifest task");
      } else {
         if (this.profileProvider == null) {
            try {
               this.profileProvider = new ExecutionEnvironmentProfileProvider();
            } catch (IOException e) {
               throw new BuildException("Enable to load the default environment profiles", e);
            }
         }

         Manifest m;
         try {
            m = new Manifest(new FileInputStream(this.manifest));
         } catch (FileNotFoundException e) {
            throw new BuildException("the manifest file '" + this.manifest + "' was not found", e);
         } catch (IOException e) {
            throw new BuildException("the manifest file '" + this.manifest + "' could not be read", e);
         }

         BundleInfo bundleInfo;
         try {
            bundleInfo = ManifestParser.parseManifest(m);
         } catch (ParseException e) {
            throw new BuildException("Incorrect manifest file '" + this.manifest + "'", e);
         }

         ModuleDescriptor md = BundleInfoAdapter.toModuleDescriptor(OSGiManifestParser.getInstance(), (URI)null, bundleInfo, m, this.profileProvider);

         try {
            XmlModuleDescriptorWriter.write(md, this.ivyFile);
         } catch (IOException e) {
            throw new BuildException("The ivyFile '" + this.ivyFile + "' could not be written", e);
         }
      }
   }
}
