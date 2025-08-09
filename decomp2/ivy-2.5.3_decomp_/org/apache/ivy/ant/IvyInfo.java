package org.apache.ivy.ant;

import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.tools.ant.BuildException;

public class IvyInfo extends IvyTask {
   private File file = null;
   private String organisation;
   private String module;
   private String branch;
   private String revision;
   private String property = "ivy";

   public File getFile() {
      return this.file;
   }

   public void setFile(File file) {
      this.file = file;
   }

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String organisation) {
      this.organisation = organisation;
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getProperty() {
      return this.property;
   }

   public void setProperty(String prefix) {
      this.property = prefix;
   }

   public void doExecute() throws BuildException {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();

      try {
         if (this.organisation == null && this.module == null && this.revision == null && this.branch == null) {
            if (this.file == null) {
               this.file = this.getProject().resolveFile(this.getProperty(settings, "ivy.dep.file"));
            }

            ModuleDescriptor md = ModuleDescriptorParserRegistry.getInstance().parseDescriptor(settings, this.file.toURI().toURL(), this.doValidate(settings));
            ModuleRevisionId mrid = md.getModuleRevisionId();
            this.setProperties(md, mrid);
         } else {
            if (this.organisation == null) {
               throw new BuildException("no organisation provided for ivy info task");
            }

            if (this.module == null) {
               throw new BuildException("no module name provided for ivy info task");
            }

            if (this.revision == null) {
               throw new BuildException("no revision provided for ivy info task");
            }

            if (this.branch == null) {
               settings.getDefaultBranch(new ModuleId(this.organisation, this.module));
            }

            ResolvedModuleRevision rmr = ivy.findModule(ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision));
            if (rmr != null) {
               ModuleDescriptor md = rmr.getDescriptor();
               ModuleRevisionId mrid = rmr.getId();
               this.setProperties(md, mrid);
            }
         }

      } catch (MalformedURLException e) {
         throw new BuildException("unable to convert given ivy file to url: " + this.file + ": " + e, e);
      } catch (ParseException e) {
         this.log(e.getMessage(), 0);
         throw new BuildException("syntax errors in ivy file: " + e, e);
      } catch (Exception e) {
         throw new BuildException("impossible to resolve dependencies: " + e, e);
      }
   }

   private void setProperties(ModuleDescriptor md, ModuleRevisionId mrid) {
      this.getProject().setProperty(this.property + ".organisation", mrid.getOrganisation());
      this.getProject().setProperty(this.property + ".module", mrid.getName());
      if (mrid.getBranch() != null) {
         this.getProject().setProperty(this.property + ".branch", mrid.getBranch());
      }

      this.getProject().setProperty(this.property + ".revision", mrid.getRevision());
      this.getProject().setProperty(this.property + ".status", md.getStatus());
      if (md.getPublicationDate() != null) {
         this.getProject().setProperty(this.property + ".publication", Long.toString(md.getPublicationDate().getTime()));
      }

      for(Map.Entry entry : mrid.getExtraAttributes().entrySet()) {
         this.getProject().setProperty(this.property + ".extra." + (String)entry.getKey(), (String)entry.getValue());
      }

      this.getProject().setProperty(this.property + ".configurations", this.mergeConfs(md.getConfigurationsNames()));
      List<String> publicConfigsList = new ArrayList();

      for(Configuration config : md.getConfigurations()) {
         String name = config.getName();
         if (Configuration.Visibility.PUBLIC.equals(config.getVisibility())) {
            publicConfigsList.add(name);
         }

         if (config.getDescription() != null) {
            this.getProject().setProperty(this.property + ".configuration." + name + ".desc", config.getDescription());
         }
      }

      String[] publicConfigs = (String[])publicConfigsList.toArray(new String[publicConfigsList.size()]);
      this.getProject().setProperty(this.property + ".public.configurations", this.mergeConfs(publicConfigs));
      List<Artifact> artifacts = Arrays.asList(md.getAllArtifacts());

      for(Artifact artifact : artifacts) {
         int id = artifacts.indexOf(artifact) + 1;
         this.getProject().setProperty(this.property + ".artifact." + id + ".name", artifact.getName());
         this.getProject().setProperty(this.property + ".artifact." + id + ".type", artifact.getType());
         this.getProject().setProperty(this.property + ".artifact." + id + ".ext", artifact.getExt());
         this.getProject().setProperty(this.property + ".artifact." + id + ".conf", this.mergeConfs(artifact.getConfigurations()));

         for(Map.Entry entry : artifact.getExtraAttributes().entrySet()) {
            this.getProject().setProperty(this.property + ".artifact." + id + ".extra." + (String)entry.getKey(), (String)entry.getValue());
         }
      }

   }
}
