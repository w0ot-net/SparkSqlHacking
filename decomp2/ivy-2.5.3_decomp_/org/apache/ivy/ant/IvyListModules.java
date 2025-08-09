package org.apache.ivy.ant;

import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.search.SearchEngine;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.tools.ant.BuildException;

public class IvyListModules extends IvyTask {
   private String organisation;
   private String module;
   private String branch = "*";
   private String revision;
   private String matcher = "exactOrRegexp";
   private String property;
   private String value;
   private String resolver;

   public String getMatcher() {
      return this.matcher;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
   }

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getProperty() {
      return this.property;
   }

   public void setProperty(String name) {
      this.property = name;
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

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public void setResolver(String resolver) {
      this.resolver = resolver;
   }

   public String getResolver() {
      return this.resolver;
   }

   public void doExecute() throws BuildException {
      if (this.organisation == null) {
         throw new BuildException("no organisation provided for ivy listmodules task");
      } else if (this.module == null) {
         throw new BuildException("no module name provided for ivy listmodules task");
      } else if (this.revision == null) {
         throw new BuildException("no revision provided for ivy listmodules task");
      } else if (this.property == null) {
         throw new BuildException("no property provided for ivy listmodules task");
      } else if (this.value == null) {
         throw new BuildException("no value provided for ivy listmodules task");
      } else {
         Ivy ivy = this.getIvyInstance();
         IvySettings settings = ivy.getSettings();
         SearchEngine searcher = new SearchEngine(settings);
         PatternMatcher patternMatcher = settings.getMatcher(this.matcher);
         ModuleRevisionId[] mrids;
         if (this.resolver == null) {
            mrids = searcher.listModules(ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision), patternMatcher);
         } else {
            DependencyResolver depResolver = settings.getResolver(this.resolver);
            if (depResolver == null) {
               throw new BuildException("Unknown resolver: " + this.resolver);
            }

            mrids = searcher.listModules(depResolver, ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision), patternMatcher);
         }

         for(ModuleRevisionId mrid : mrids) {
            String name = IvyPatternHelper.substitute(settings.substitute(this.property), mrid);
            String value = IvyPatternHelper.substitute(settings.substitute(this.value), mrid);
            this.getProject().setProperty(name, value);
         }

      }
   }
}
