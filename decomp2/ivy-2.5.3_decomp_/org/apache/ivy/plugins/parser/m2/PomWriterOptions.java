package org.apache.ivy.plugins.parser.m2;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PomWriterOptions {
   private String[] confs;
   private String licenseHeader;
   private ConfigurationScopeMapping mapping;
   private boolean printIvyInfo = true;
   private String artifactName;
   private String artifactPackaging;
   private List extraDependencies = new ArrayList();
   private String description;
   private File template;

   public File getTemplate() {
      return this.template;
   }

   public PomWriterOptions setTemplate(File template) {
      this.template = template;
      return this;
   }

   public String[] getConfs() {
      return this.confs;
   }

   public PomWriterOptions setConfs(String[] confs) {
      this.confs = confs;
      return this;
   }

   public String getLicenseHeader() {
      return this.licenseHeader;
   }

   public PomWriterOptions setLicenseHeader(String licenseHeader) {
      this.licenseHeader = licenseHeader;
      if (this.licenseHeader != null) {
         this.licenseHeader = this.licenseHeader.trim();
      }

      return this;
   }

   public ConfigurationScopeMapping getMapping() {
      return this.mapping;
   }

   public PomWriterOptions setMapping(ConfigurationScopeMapping mapping) {
      this.mapping = mapping;
      return this;
   }

   public boolean isPrintIvyInfo() {
      return this.printIvyInfo;
   }

   public PomWriterOptions setPrintIvyInfo(boolean printIvyInfo) {
      this.printIvyInfo = printIvyInfo;
      return this;
   }

   public List getExtraDependencies() {
      return this.extraDependencies;
   }

   public PomWriterOptions setExtraDependencies(List extraDependencies) {
      this.extraDependencies = extraDependencies;
      return this;
   }

   public String getArtifactName() {
      return this.artifactName;
   }

   public PomWriterOptions setArtifactName(String artifactName) {
      this.artifactName = artifactName;
      return this;
   }

   public String getArtifactPackaging() {
      return this.artifactPackaging;
   }

   public PomWriterOptions setArtifactPackaging(String artifactPackaging) {
      this.artifactPackaging = artifactPackaging;
      return this;
   }

   public String getDescription() {
      return this.description;
   }

   public PomWriterOptions setDescription(String description) {
      this.description = description;
      return this;
   }

   public static class ConfigurationScopeMapping {
      private Map scopes;

      public ConfigurationScopeMapping(Map scopesMapping) {
         this.scopes = new LinkedHashMap(scopesMapping);
      }

      public String getScope(String[] confs) {
         for(String conf : confs) {
            if (this.scopes.containsKey(conf)) {
               return (String)this.scopes.get(conf);
            }
         }

         return null;
      }

      public boolean isOptional(String[] confs) {
         return this.getScope(confs) == null;
      }
   }

   public static class ExtraDependency {
      private String group;
      private String artifact;
      private String version;
      private String scope;
      private String type;
      private String classifier;
      private boolean optional;

      public ExtraDependency(String group, String artifact, String version, String scope, String type, String classifier, boolean optional) {
         this.group = group;
         this.artifact = artifact;
         this.version = version;
         this.scope = scope;
         this.type = type;
         this.classifier = classifier;
         this.optional = optional;
      }

      public String getGroup() {
         return this.group;
      }

      public String getArtifact() {
         return this.artifact;
      }

      public String getVersion() {
         return this.version;
      }

      public String getScope() {
         return this.scope;
      }

      public String getType() {
         return this.type;
      }

      public String getClassifier() {
         return this.classifier;
      }

      public boolean isOptional() {
         return this.optional;
      }
   }
}
