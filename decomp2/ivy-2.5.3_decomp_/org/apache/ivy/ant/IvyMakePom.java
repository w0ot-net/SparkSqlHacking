package org.apache.ivy.ant;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.plugins.parser.m2.PomModuleDescriptorWriter;
import org.apache.ivy.plugins.parser.m2.PomWriterOptions;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorParser;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;

public class IvyMakePom extends IvyTask {
   private String artifactName;
   private String artifactPackaging;
   private File pomFile = null;
   private File headerFile = null;
   private File templateFile = null;
   private boolean printIvyInfo = true;
   private String conf;
   private File ivyFile = null;
   private String description;
   private List mappings = new ArrayList();
   private List dependencies = new ArrayList();

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

   public File getHeaderFile() {
      return this.headerFile;
   }

   public void setHeaderFile(File headerFile) {
      this.headerFile = headerFile;
   }

   public File getTemplateFile() {
      return this.templateFile;
   }

   public void setTemplateFile(File templateFile) {
      this.templateFile = templateFile;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isPrintIvyInfo() {
      return this.printIvyInfo;
   }

   public void setPrintIvyInfo(boolean printIvyInfo) {
      this.printIvyInfo = printIvyInfo;
   }

   public String getConf() {
      return this.conf;
   }

   public void setConf(String conf) {
      this.conf = conf;
   }

   public String getArtifactName() {
      return this.artifactName;
   }

   public void setArtifactName(String artifactName) {
      this.artifactName = artifactName;
   }

   public String getArtifactPackaging() {
      return this.artifactPackaging;
   }

   public void setArtifactPackaging(String artifactPackaging) {
      this.artifactPackaging = artifactPackaging;
   }

   public Mapping createMapping() {
      Mapping mapping = new Mapping();
      this.mappings.add(mapping);
      return mapping;
   }

   public Dependency createDependency() {
      Dependency dependency = new Dependency();
      this.dependencies.add(dependency);
      return dependency;
   }

   public void doExecute() throws BuildException {
      try {
         if (this.ivyFile == null) {
            throw new BuildException("source ivy file is required for makepom task");
         } else if (this.pomFile == null) {
            throw new BuildException("destination pom file is required for makepom task");
         } else {
            ModuleDescriptor md = XmlModuleDescriptorParser.getInstance().parseDescriptor(this.getSettings(), this.ivyFile.toURI().toURL(), false);
            PomModuleDescriptorWriter.write(md, this.pomFile, this.getPomWriterOptions());
         }
      } catch (MalformedURLException e) {
         throw new BuildException("unable to convert given ivy file to url: " + this.ivyFile + ": " + e, e);
      } catch (ParseException e) {
         this.log(e.getMessage(), 0);
         throw new BuildException("syntax errors in ivy file " + this.ivyFile + ": " + e, e);
      } catch (Exception e) {
         throw new BuildException("impossible convert given ivy file to pom file: " + e + " from=" + this.ivyFile + " to=" + this.pomFile, e);
      }
   }

   private PomWriterOptions getPomWriterOptions() throws IOException {
      PomWriterOptions options = new PomWriterOptions();
      options.setConfs(StringUtils.splitToArray(this.conf)).setArtifactName(this.getArtifactName()).setArtifactPackaging(this.getArtifactPackaging()).setPrintIvyInfo(this.isPrintIvyInfo()).setDescription(this.getDescription()).setExtraDependencies(this.getDependencies()).setTemplate(this.getTemplateFile());
      if (!this.mappings.isEmpty()) {
         options.setMapping(new PomWriterOptions.ConfigurationScopeMapping(this.getMappingsMap()));
      }

      if (this.headerFile != null) {
         options.setLicenseHeader(FileUtil.readEntirely(this.getHeaderFile()));
      }

      return options;
   }

   private Map getMappingsMap() {
      Map<String, String> mappingsMap = new LinkedHashMap();

      for(Mapping mapping : this.mappings) {
         for(String mappingConf : StringUtils.splitToArray(mapping.getConf())) {
            if (!mappingsMap.containsKey(mappingConf)) {
               mappingsMap.put(mappingConf, mapping.getScope());
            }
         }
      }

      return mappingsMap;
   }

   private List getDependencies() {
      List<PomWriterOptions.ExtraDependency> result = new ArrayList();

      for(Dependency dependency : this.dependencies) {
         result.add(new PomWriterOptions.ExtraDependency(dependency.getGroup(), dependency.getArtifact(), dependency.getVersion(), dependency.getScope(), dependency.getType(), dependency.getClassifier(), dependency.getOptional()));
      }

      return result;
   }

   public class Mapping {
      private String conf;
      private String scope;

      public String getConf() {
         return this.conf;
      }

      public void setConf(String conf) {
         this.conf = conf;
      }

      public String getScope() {
         return this.scope;
      }

      public void setScope(String scope) {
         this.scope = scope;
      }
   }

   public class Dependency {
      private String group = null;
      private String artifact = null;
      private String version = null;
      private String scope = null;
      private String type = null;
      private String classifier = null;
      private boolean optional = false;

      public String getGroup() {
         return this.group;
      }

      public void setGroup(String group) {
         this.group = group;
      }

      public String getArtifact() {
         return this.artifact;
      }

      public void setArtifact(String artifact) {
         this.artifact = artifact;
      }

      public String getVersion() {
         return this.version;
      }

      public void setVersion(String version) {
         this.version = version;
      }

      public String getScope() {
         return this.scope;
      }

      public void setScope(String scope) {
         this.scope = scope;
      }

      public String getType() {
         return this.type;
      }

      public void setType(String type) {
         this.type = type;
      }

      public String getClassifier() {
         return this.classifier;
      }

      public void setClassifier(String classifier) {
         this.classifier = classifier;
      }

      public boolean getOptional() {
         return this.optional;
      }

      public void setOptional(boolean optional) {
         this.optional = optional;
      }
   }
}
