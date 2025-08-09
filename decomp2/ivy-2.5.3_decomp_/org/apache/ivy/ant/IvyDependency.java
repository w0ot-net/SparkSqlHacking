package org.apache.ivy.ant;

import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultExcludeRule;
import org.apache.ivy.core.module.descriptor.DefaultIncludeRule;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.tools.ant.BuildException;

public class IvyDependency {
   private List confs = new ArrayList();
   private List artifacts = new ArrayList();
   private List excludes = new ArrayList();
   private List includes = new ArrayList();
   private String org;
   private String name;
   private String rev;
   private String branch;
   private String conf;
   private boolean changing;
   private boolean force;
   private boolean transitive = true;

   public IvyDependencyConf createConf() {
      IvyDependencyConf c = new IvyDependencyConf();
      this.confs.add(c);
      return c;
   }

   public IvyDependencyArtifact createArtifact() {
      IvyDependencyArtifact artifact = new IvyDependencyArtifact();
      this.artifacts.add(artifact);
      return artifact;
   }

   public IvyDependencyExclude createExclude() {
      IvyDependencyExclude exclude = new IvyDependencyExclude();
      this.excludes.add(exclude);
      return exclude;
   }

   public IvyDependencyInclude createInclude() {
      IvyDependencyInclude include = new IvyDependencyInclude();
      this.includes.add(include);
      return include;
   }

   public String getOrg() {
      return this.org;
   }

   public void setOrg(String org) {
      this.org = org;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getRev() {
      return this.rev;
   }

   public void setRev(String rev) {
      this.rev = rev;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getConf() {
      return this.conf;
   }

   public void setConf(String conf) {
      this.conf = conf;
   }

   public boolean isChanging() {
      return this.changing;
   }

   public void setChanging(boolean changing) {
      this.changing = changing;
   }

   public boolean isForce() {
      return this.force;
   }

   public void setForce(boolean force) {
      this.force = force;
   }

   public boolean isTransitive() {
      return this.transitive;
   }

   public void setTransitive(boolean transitive) {
      this.transitive = transitive;
   }

   DependencyDescriptor asDependencyDescriptor(ModuleDescriptor md, String masterConf, IvySettings settings) {
      if (this.org == null) {
         throw new BuildException("'org' is required on ");
      } else if (this.name == null) {
         throw new BuildException("'name' is required when using inline mode");
      } else {
         ModuleRevisionId mrid = ModuleRevisionId.newInstance(this.org, this.name, this.branch, this.rev);
         DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(md, mrid, this.force, this.changing, this.transitive);
         if (this.conf != null) {
            dd.addDependencyConfiguration(masterConf, this.conf);
         } else {
            dd.addDependencyConfiguration(masterConf, "*");
         }

         for(IvyDependencyConf c : this.confs) {
            c.addConf(dd, masterConf);
         }

         for(IvyDependencyArtifact artifact : this.artifacts) {
            artifact.addArtifact(dd, masterConf);
         }

         for(IvyDependencyExclude exclude : this.excludes) {
            DefaultExcludeRule rule = exclude.asRule(settings);
            dd.addExcludeRule(masterConf, rule);
         }

         for(IvyDependencyInclude include : this.includes) {
            DefaultIncludeRule rule = include.asRule(settings);
            dd.addIncludeRule(masterConf, rule);
         }

         return dd;
      }
   }
}
