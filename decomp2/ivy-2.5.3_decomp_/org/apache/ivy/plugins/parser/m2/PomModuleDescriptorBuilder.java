package org.apache.ivy.plugins.parser.m2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DefaultDependencyArtifactDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultExcludeRule;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ExtraInfoHolder;
import org.apache.ivy.core.module.descriptor.License;
import org.apache.ivy.core.module.descriptor.MDArtifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.descriptor.OverrideDependencyDescriptorMediator;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.matcher.ExactPatternMatcher;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public class PomModuleDescriptorBuilder {
   private static final String IVY_XML_MAVEN_NAMESPACE_URI = "http://ant.apache.org/ivy/maven";
   private static final int DEPENDENCY_MANAGEMENT_KEY_PARTS_COUNT = 4;
   public static final Configuration[] MAVEN2_CONFIGURATIONS;
   static final Map MAVEN2_CONF_MAPPING;
   private static final String DEPENDENCY_MANAGEMENT = "m:dependency.management";
   private static final String PROPERTIES = "m:properties";
   private static final String EXTRA_INFO_DELIMITER = "__";
   private static final Collection JAR_PACKAGINGS;
   private final PomModuleDescriptor ivyModuleDescriptor;
   private ModuleRevisionId mrid;
   private DefaultArtifact mainArtifact;
   private ParserSettings parserSettings;
   private static final String WRONG_NUMBER_OF_PARTS_MSG = "what seemed to be a dependency management extra info exclusion had the wrong number of parts (should have 2) ";

   public PomModuleDescriptorBuilder(ModuleDescriptorParser parser, Resource res, ParserSettings ivySettings) {
      this.ivyModuleDescriptor = new PomModuleDescriptor(parser, res);
      this.ivyModuleDescriptor.setResolvedPublicationDate(new Date(res.getLastModified()));

      for(Configuration m2conf : MAVEN2_CONFIGURATIONS) {
         this.ivyModuleDescriptor.addConfiguration(m2conf);
      }

      this.ivyModuleDescriptor.setMappingOverride(true);
      this.ivyModuleDescriptor.addExtraAttributeNamespace("m", "http://ant.apache.org/ivy/maven");
      this.parserSettings = ivySettings;
   }

   public ModuleDescriptor getModuleDescriptor() {
      return this.ivyModuleDescriptor;
   }

   public void setModuleRevId(String groupId, String artifactId, String version) {
      this.mrid = ModuleRevisionId.newInstance(groupId, artifactId, version);
      this.ivyModuleDescriptor.setModuleRevisionId(this.mrid);
      if (version != null && !version.endsWith("SNAPSHOT")) {
         this.ivyModuleDescriptor.setStatus("release");
      } else {
         this.ivyModuleDescriptor.setStatus("integration");
      }

   }

   public void setHomePage(String homePage) {
      this.ivyModuleDescriptor.setHomePage(homePage);
   }

   public void setDescription(String description) {
      this.ivyModuleDescriptor.setDescription(description);
   }

   public void setLicenses(License[] licenses) {
      for(License license : licenses) {
         this.ivyModuleDescriptor.addLicense(license);
      }

   }

   public void addMainArtifact(String artifactId, String packaging) {
      if ("pom".equals(packaging)) {
         DependencyResolver resolver = this.parserSettings.getResolver(this.mrid);
         if (resolver != null) {
            DefaultArtifact artifact = new DefaultArtifact(this.mrid, new Date(), artifactId, "jar", "jar");
            ArtifactOrigin artifactOrigin = resolver.locate(artifact);
            if (!ArtifactOrigin.isUnknown(artifactOrigin)) {
               this.mainArtifact = artifact;
               this.ivyModuleDescriptor.addArtifact("master", this.mainArtifact);
            }
         }

      } else {
         String ext;
         if (JAR_PACKAGINGS.contains(packaging)) {
            ext = "jar";
         } else if ("pear".equals(packaging)) {
            ext = "phar";
         } else {
            ext = packaging;
         }

         this.mainArtifact = new DefaultArtifact(this.mrid, new Date(), artifactId, packaging, ext);
         this.ivyModuleDescriptor.addArtifact("master", this.mainArtifact);
      }
   }

   public void addDependency(Resource res, PomReader.PomDependencyData dep) {
      String scope = dep.getScope();
      if (!StringUtils.isNullOrEmpty(scope) && !MAVEN2_CONF_MAPPING.containsKey(scope)) {
         scope = "compile";
      }

      String version = dep.getVersion();
      if (StringUtils.isNullOrEmpty(version)) {
         version = this.getDefaultVersion(dep);
      }

      ModuleRevisionId moduleRevId = ModuleRevisionId.newInstance(dep.getGroupId(), dep.getArtifactId(), version);
      ModuleRevisionId mRevId = this.ivyModuleDescriptor.getModuleRevisionId();
      if (mRevId == null || !mRevId.getModuleId().equals(moduleRevId.getModuleId())) {
         List<ModuleId> excluded = dep.getExcludedModules();
         if (excluded.isEmpty()) {
            excluded = getDependencyMgtExclusions(this.ivyModuleDescriptor, dep.getGroupId(), dep.getArtifactId());
         }

         boolean excludeAllTransitiveDeps = shouldExcludeAllTransitiveDeps(excluded);
         DependencyDescriptor existing = (DependencyDescriptor)this.ivyModuleDescriptor.depDescriptors.get(moduleRevId);
         String[] existingConfigurations = existing == null ? new String[0] : existing.getModuleConfigurations();
         DefaultDependencyDescriptor dd = (DefaultDependencyDescriptor)(existing != null && existing instanceof DefaultDependencyDescriptor ? (DefaultDependencyDescriptor)existing : new PomDependencyDescriptor(dep, this.ivyModuleDescriptor, moduleRevId, !excludeAllTransitiveDeps));
         if (StringUtils.isNullOrEmpty(scope)) {
            scope = this.getDefaultScope(dep);
         }

         ConfMapper mapping = (ConfMapper)MAVEN2_CONF_MAPPING.get(scope);
         mapping.addMappingConfs(dd, dep.isOptional());
         Map<String, String> extraAtt = new HashMap();
         String optionalizedScope = dep.isOptional() ? "optional" : scope;
         if (this.isNonDefaultArtifact(dep)) {
            if (existing != null && existing.getAllDependencyArtifacts().length == 0) {
               String moduleConfiguration = existingConfigurations.length == 1 ? existingConfigurations[0] : optionalizedScope;
               dd.addDependencyArtifact(moduleConfiguration, this.createDefaultArtifact(dd));
            }

            String type = "jar";
            if (dep.getType() != null) {
               type = dep.getType();
            }

            String ext = type;
            if ("test-jar".equals(type)) {
               ext = "jar";
               extraAtt.put("m:classifier", "tests");
            } else if (JAR_PACKAGINGS.contains(type)) {
               ext = "jar";
            }

            if (dep.getClassifier() != null) {
               extraAtt.put("m:classifier", dep.getClassifier());
            }

            DefaultDependencyArtifactDescriptor depArtifact = new DefaultDependencyArtifactDescriptor(dd, dd.getDependencyId().getName(), type, ext, (URL)null, extraAtt);
            depArtifact.addConfiguration(optionalizedScope);
            dd.addDependencyArtifact(optionalizedScope, depArtifact);
         } else if (existing != null) {
            dd.addDependencyArtifact(optionalizedScope, this.createDefaultArtifact(dd));
         }

         for(ModuleId excludedModule : excluded) {
            if (!"*".equals(excludedModule.getOrganisation()) || !"*".equals(excludedModule.getName())) {
               for(String conf : dd.getModuleConfigurations()) {
                  dd.addExcludeRule(conf, new DefaultExcludeRule(new ArtifactId(excludedModule, "*", "*", "*"), ExactPatternMatcher.INSTANCE, (Map)null));
               }
            }
         }

         if (existing != dd) {
            this.ivyModuleDescriptor.addDependency(dd);
         }

      }
   }

   private boolean isNonDefaultArtifact(PomReader.PomDependencyData dep) {
      return dep.getClassifier() != null || dep.getType() != null && !"jar".equals(dep.getType());
   }

   private DefaultDependencyArtifactDescriptor createDefaultArtifact(DefaultDependencyDescriptor dd) {
      return new DefaultDependencyArtifactDescriptor(dd, dd.getDependencyId().getName(), "jar", "jar", (URL)null, (Map)null);
   }

   private static boolean shouldExcludeAllTransitiveDeps(List exclusions) {
      if (exclusions != null && !exclusions.isEmpty()) {
         for(ModuleId exclusion : exclusions) {
            if (exclusion != null && "*".equals(exclusion.getOrganisation()) && "*".equals(exclusion.getName())) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void addDependency(DependencyDescriptor descriptor) {
      ModuleId dependencyId = descriptor.getDependencyId();
      ModuleRevisionId mRevId = this.ivyModuleDescriptor.getModuleRevisionId();
      if (mRevId == null || !mRevId.getModuleId().equals(dependencyId)) {
         this.ivyModuleDescriptor.addDependency(descriptor);
      }
   }

   public void addDependencyMgt(PomDependencyMgt dep) {
      this.ivyModuleDescriptor.addDependencyManagement(dep);
      String key = getDependencyMgtExtraInfoKeyForVersion(dep.getGroupId(), dep.getArtifactId());
      this.overwriteExtraInfoIfExists(key, dep.getVersion());
      if (dep.getScope() != null) {
         String scopeKey = getDependencyMgtExtraInfoKeyForScope(dep.getGroupId(), dep.getArtifactId());
         this.overwriteExtraInfoIfExists(scopeKey, dep.getScope());
      }

      if (!dep.getExcludedModules().isEmpty()) {
         String exclusionPrefix = getDependencyMgtExtraInfoPrefixForExclusion(dep.getGroupId(), dep.getArtifactId());
         int index = 0;

         for(ModuleId excludedModule : dep.getExcludedModules()) {
            this.overwriteExtraInfoIfExists(exclusionPrefix + index, excludedModule.getOrganisation() + "__" + excludedModule.getName());
            ++index;
         }
      }

      this.ivyModuleDescriptor.addDependencyDescriptorMediator(ModuleId.newInstance(dep.getGroupId(), dep.getArtifactId()), ExactPatternMatcher.INSTANCE, new OverrideDependencyDescriptorMediator((String)null, dep.getVersion()));
   }

   public void addPlugin(PomDependencyMgt plugin) {
      String pluginValue = plugin.getGroupId() + "__" + plugin.getArtifactId() + "__" + plugin.getVersion();
      ExtraInfoHolder extraInfoByTagName = this.ivyModuleDescriptor.getExtraInfoByTagName("m:maven.plugins");
      if (extraInfoByTagName == null) {
         extraInfoByTagName = new ExtraInfoHolder();
         extraInfoByTagName.setName("m:maven.plugins");
         this.ivyModuleDescriptor.addExtraInfo(extraInfoByTagName);
      }

      String pluginExtraInfo = extraInfoByTagName.getContent();
      if (pluginExtraInfo == null) {
         pluginExtraInfo = pluginValue;
      } else {
         pluginExtraInfo = pluginExtraInfo + "|" + pluginValue;
      }

      extraInfoByTagName.setContent(pluginExtraInfo);
   }

   public static List getPlugins(ModuleDescriptor md) {
      List<PomDependencyMgt> result = new ArrayList();
      String plugins = md.getExtraInfoContentByTagName("m:maven.plugins");
      if (plugins == null) {
         return new ArrayList();
      } else {
         for(String plugin : plugins.split("\\|")) {
            String[] parts = plugin.split("__");
            result.add(new PomPluginElement(parts[0], parts[1], parts[2]));
         }

         return result;
      }
   }

   private String getDefaultVersion(PomReader.PomDependencyData dep) {
      ModuleId moduleId = ModuleId.newInstance(dep.getGroupId(), dep.getArtifactId());
      if (this.ivyModuleDescriptor.getDependencyManagementMap().containsKey(moduleId)) {
         return ((PomDependencyMgt)this.ivyModuleDescriptor.getDependencyManagementMap().get(moduleId)).getVersion();
      } else {
         String key = getDependencyMgtExtraInfoKeyForVersion(dep.getGroupId(), dep.getArtifactId());
         return this.ivyModuleDescriptor.getExtraInfoContentByTagName(key);
      }
   }

   private String getDefaultScope(PomReader.PomDependencyData dep) {
      ModuleId moduleId = ModuleId.newInstance(dep.getGroupId(), dep.getArtifactId());
      String result;
      if (this.ivyModuleDescriptor.getDependencyManagementMap().containsKey(moduleId)) {
         result = ((PomDependencyMgt)this.ivyModuleDescriptor.getDependencyManagementMap().get(moduleId)).getScope();
      } else {
         String key = getDependencyMgtExtraInfoKeyForScope(dep.getGroupId(), dep.getArtifactId());
         result = this.ivyModuleDescriptor.getExtraInfoContentByTagName(key);
      }

      if (result == null || !MAVEN2_CONF_MAPPING.containsKey(result)) {
         result = "compile";
      }

      return result;
   }

   private static String getDependencyMgtExtraInfoKeyForVersion(String groupId, String artifactId) {
      return "m:dependency.management__" + groupId + "__" + artifactId + "__" + "version";
   }

   private static String getDependencyMgtExtraInfoKeyForScope(String groupId, String artifactId) {
      return "m:dependency.management__" + groupId + "__" + artifactId + "__" + "scope";
   }

   private static String getPropertyExtraInfoKey(String propertyName) {
      return "m:properties__" + propertyName;
   }

   private static String getDependencyMgtExtraInfoPrefixForExclusion(String groupId, String artifactId) {
      return "m:dependency.management__" + groupId + "__" + artifactId + "__" + "exclusion_";
   }

   private static List getDependencyMgtExclusions(ModuleDescriptor descriptor, String groupId, String artifactId) {
      if (descriptor instanceof PomModuleDescriptor) {
         PomDependencyMgt dependencyMgt = (PomDependencyMgt)((PomModuleDescriptor)descriptor).getDependencyManagementMap().get(ModuleId.newInstance(groupId, artifactId));
         if (dependencyMgt != null) {
            return dependencyMgt.getExcludedModules();
         }
      }

      String exclusionPrefix = getDependencyMgtExtraInfoPrefixForExclusion(groupId, artifactId);
      List<ModuleId> exclusionIds = new LinkedList();

      for(ExtraInfoHolder extraInfoHolder : descriptor.getExtraInfos()) {
         String key = extraInfoHolder.getName();
         if (key.startsWith(exclusionPrefix)) {
            String fullExclusion = extraInfoHolder.getContent();
            String[] exclusionParts = fullExclusion.split("__");
            if (exclusionParts.length != 2) {
               Message.error("what seemed to be a dependency management extra info exclusion had the wrong number of parts (should have 2) " + exclusionParts.length + " : " + fullExclusion);
            } else {
               exclusionIds.add(ModuleId.newInstance(exclusionParts[0], exclusionParts[1]));
            }
         }
      }

      return exclusionIds;
   }

   public static Map getDependencyManagementMap(ModuleDescriptor md) {
      Map<ModuleId, String> ret = new LinkedHashMap();
      if (md instanceof PomModuleDescriptor) {
         for(Map.Entry e : ((PomModuleDescriptor)md).getDependencyManagementMap().entrySet()) {
            PomDependencyMgt dependencyMgt = (PomDependencyMgt)e.getValue();
            ret.put(e.getKey(), dependencyMgt.getVersion());
         }
      } else {
         for(ExtraInfoHolder extraInfoHolder : md.getExtraInfos()) {
            String key = extraInfoHolder.getName();
            if (key.startsWith("m:dependency.management")) {
               String[] parts = key.split("__");
               if (parts.length != 4) {
                  Message.warn("what seem to be a dependency management extra info doesn't match expected pattern: " + key);
               } else {
                  ret.put(ModuleId.newInstance(parts[1], parts[2]), extraInfoHolder.getContent());
               }
            }
         }
      }

      return ret;
   }

   public static List getDependencyManagements(ModuleDescriptor md) {
      List<PomDependencyMgt> result = new ArrayList();
      if (md instanceof PomModuleDescriptor) {
         result.addAll(((PomModuleDescriptor)md).getDependencyManagementMap().values());
      } else {
         for(ExtraInfoHolder extraInfoHolder : md.getExtraInfos()) {
            String key = extraInfoHolder.getName();
            if (key.startsWith("m:dependency.management")) {
               String[] parts = key.split("__");
               if (parts.length != 4) {
                  Message.warn("what seem to be a dependency management extra info doesn't match expected pattern: " + key);
               } else {
                  String versionKey = "m:dependency.management__" + parts[1] + "__" + parts[2] + "__" + "version";
                  String scopeKey = "m:dependency.management__" + parts[1] + "__" + parts[2] + "__" + "scope";
                  String version = md.getExtraInfoContentByTagName(versionKey);
                  String scope = md.getExtraInfoContentByTagName(scopeKey);
                  List<ModuleId> exclusions = getDependencyMgtExclusions(md, parts[1], parts[2]);
                  result.add(new DefaultPomDependencyMgt(parts[1], parts[2], version, scope, exclusions));
               }
            }
         }
      }

      return result;
   }

   /** @deprecated */
   @Deprecated
   public void addExtraInfos(Map extraAttributes) {
      for(Map.Entry entry : extraAttributes.entrySet()) {
         this.addExtraInfo((String)entry.getKey(), (String)entry.getValue());
      }

   }

   private void addExtraInfo(String key, String value) {
      if (this.ivyModuleDescriptor.getExtraInfoByTagName(key) == null) {
         this.ivyModuleDescriptor.getExtraInfos().add(new ExtraInfoHolder(key, value));
      }

   }

   private void overwriteExtraInfoIfExists(String key, String value) {
      boolean found = false;

      for(ExtraInfoHolder extraInfoHolder : this.ivyModuleDescriptor.getExtraInfos()) {
         if (extraInfoHolder.getName().equals(key)) {
            extraInfoHolder.setContent(value);
            found = true;
         }
      }

      if (!found) {
         this.ivyModuleDescriptor.getExtraInfos().add(new ExtraInfoHolder(key, value));
      }

   }

   public void addExtraInfos(List extraInfosHolder) {
      for(ExtraInfoHolder extraInfoHolder : extraInfosHolder) {
         this.addExtraInfo(extraInfoHolder.getName(), extraInfoHolder.getContent());
      }

   }

   /** @deprecated */
   @Deprecated
   public static Map extractPomProperties(Map extraInfo) {
      Map<String, String> r = new HashMap();

      for(Map.Entry extraInfoEntry : extraInfo.entrySet()) {
         if (((String)extraInfoEntry.getKey()).startsWith("m:properties")) {
            String prop = ((String)extraInfoEntry.getKey()).substring("m:properties".length() + "__".length());
            r.put(prop, extraInfoEntry.getValue());
         }
      }

      return r;
   }

   public static Map extractPomProperties(List extraInfos) {
      Map<String, String> r = new HashMap();

      for(ExtraInfoHolder extraInfoHolder : extraInfos) {
         if (extraInfoHolder.getName().startsWith("m:properties")) {
            String prop = extraInfoHolder.getName().substring("m:properties".length() + "__".length());
            r.put(prop, extraInfoHolder.getContent());
         }
      }

      return r;
   }

   public void addProperty(String propertyName, String value) {
      this.addExtraInfo(getPropertyExtraInfoKey(propertyName), value);
   }

   public Artifact getMainArtifact() {
      return this.mainArtifact;
   }

   public Artifact getSourceArtifact() {
      return new MDArtifact(this.ivyModuleDescriptor, this.mrid.getName(), "source", "jar", (URL)null, Collections.singletonMap("m:classifier", "sources"));
   }

   public Artifact getSrcArtifact() {
      return new MDArtifact(this.ivyModuleDescriptor, this.mrid.getName(), "source", "jar", (URL)null, Collections.singletonMap("m:classifier", "src"));
   }

   public Artifact getJavadocArtifact() {
      return new MDArtifact(this.ivyModuleDescriptor, this.mrid.getName(), "javadoc", "jar", (URL)null, Collections.singletonMap("m:classifier", "javadoc"));
   }

   public void addSourceArtifact() {
      this.ivyModuleDescriptor.addArtifact("sources", this.getSourceArtifact());
   }

   public void addSrcArtifact() {
      this.ivyModuleDescriptor.addArtifact("sources", this.getSrcArtifact());
   }

   public void addJavadocArtifact() {
      this.ivyModuleDescriptor.addArtifact("javadoc", this.getJavadocArtifact());
   }

   static {
      MAVEN2_CONFIGURATIONS = new Configuration[]{new Configuration("default", Configuration.Visibility.PUBLIC, "runtime dependencies and master artifact can be used with this conf", new String[]{"runtime", "master"}, true, (String)null), new Configuration("master", Configuration.Visibility.PUBLIC, "contains only the artifact published by this module itself, with no transitive dependencies", new String[0], true, (String)null), new Configuration("compile", Configuration.Visibility.PUBLIC, "this is the default scope, used if none is specified. Compile dependencies are available in all classpaths.", new String[0], true, (String)null), new Configuration("provided", Configuration.Visibility.PUBLIC, "this is much like compile, but indicates you expect the JDK or a container to provide it. It is only available on the compilation classpath, and is not transitive.", new String[0], true, (String)null), new Configuration("runtime", Configuration.Visibility.PUBLIC, "this scope indicates that the dependency is not required for compilation, but is for execution. It is in the runtime and test classpaths, but not the compile classpath.", new String[]{"compile"}, true, (String)null), new Configuration("test", Configuration.Visibility.PUBLIC, "this scope indicates that the dependency is not required for normal use of the application, and is only available for the test compilation and execution phases.", new String[]{"runtime"}, true, (String)null), new Configuration("system", Configuration.Visibility.PUBLIC, "this scope is similar to provided except that you have to provide the JAR which contains it explicitly. The artifact is always available and is not looked up in a repository.", new String[0], true, (String)null), new Configuration("sources", Configuration.Visibility.PUBLIC, "this configuration contains the source artifact of this module, if any.", new String[0], true, (String)null), new Configuration("javadoc", Configuration.Visibility.PUBLIC, "this configuration contains the javadoc artifact of this module, if any.", new String[0], true, (String)null), new Configuration("optional", Configuration.Visibility.PUBLIC, "contains all optional dependencies", new String[0], true, (String)null)};
      MAVEN2_CONF_MAPPING = new HashMap();
      JAR_PACKAGINGS = Arrays.asList("ejb", "bundle", "maven-plugin", "eclipse-plugin", "jbi-component", "jbi-shared-library", "orbit", "hk2-jar");
      MAVEN2_CONF_MAPPING.put("compile", new ConfMapper() {
         public void addMappingConfs(DefaultDependencyDescriptor dd, boolean isOptional) {
            if (isOptional) {
               dd.addDependencyConfiguration("optional", "compile(*)");
               dd.addDependencyConfiguration("optional", "master(*)");
            } else {
               dd.addDependencyConfiguration("compile", "compile(*)");
               dd.addDependencyConfiguration("compile", "master(*)");
               dd.addDependencyConfiguration("runtime", "runtime(*)");
            }

         }
      });
      MAVEN2_CONF_MAPPING.put("provided", new ConfMapper() {
         public void addMappingConfs(DefaultDependencyDescriptor dd, boolean isOptional) {
            if (isOptional) {
               dd.addDependencyConfiguration("optional", "compile(*)");
               dd.addDependencyConfiguration("optional", "provided(*)");
               dd.addDependencyConfiguration("optional", "runtime(*)");
               dd.addDependencyConfiguration("optional", "master(*)");
            } else {
               dd.addDependencyConfiguration("provided", "compile(*)");
               dd.addDependencyConfiguration("provided", "provided(*)");
               dd.addDependencyConfiguration("provided", "runtime(*)");
               dd.addDependencyConfiguration("provided", "master(*)");
            }

         }
      });
      MAVEN2_CONF_MAPPING.put("runtime", new ConfMapper() {
         public void addMappingConfs(DefaultDependencyDescriptor dd, boolean isOptional) {
            if (isOptional) {
               dd.addDependencyConfiguration("optional", "compile(*)");
               dd.addDependencyConfiguration("optional", "provided(*)");
               dd.addDependencyConfiguration("optional", "master(*)");
            } else {
               dd.addDependencyConfiguration("runtime", "compile(*)");
               dd.addDependencyConfiguration("runtime", "runtime(*)");
               dd.addDependencyConfiguration("runtime", "master(*)");
            }

         }
      });
      MAVEN2_CONF_MAPPING.put("test", new ConfMapper() {
         public void addMappingConfs(DefaultDependencyDescriptor dd, boolean isOptional) {
            dd.addDependencyConfiguration("test", "runtime(*)");
            dd.addDependencyConfiguration("test", "master(*)");
         }
      });
      MAVEN2_CONF_MAPPING.put("system", new ConfMapper() {
         public void addMappingConfs(DefaultDependencyDescriptor dd, boolean isOptional) {
            dd.addDependencyConfiguration("system", "master(*)");
         }
      });
   }

   private static class PomPluginElement implements PomDependencyMgt {
      private String groupId;
      private String artifactId;
      private String version;

      public PomPluginElement(String groupId, String artifactId, String version) {
         this.groupId = groupId;
         this.artifactId = artifactId;
         this.version = version;
      }

      public String getGroupId() {
         return this.groupId;
      }

      public String getArtifactId() {
         return this.artifactId;
      }

      public String getVersion() {
         return this.version;
      }

      public String getScope() {
         return null;
      }

      public List getExcludedModules() {
         return Collections.emptyList();
      }
   }

   public static class PomDependencyDescriptor extends DefaultDependencyDescriptor {
      private final PomReader.PomDependencyData pomDependencyData;

      private PomDependencyDescriptor(PomReader.PomDependencyData pomDependencyData, ModuleDescriptor moduleDescriptor, ModuleRevisionId revisionId, boolean transitive) {
         super(moduleDescriptor, revisionId, true, false, transitive);
         this.pomDependencyData = pomDependencyData;
      }

      public PomReader.PomDependencyData getPomDependencyData() {
         return this.pomDependencyData;
      }
   }

   public static class PomModuleDescriptor extends DefaultModuleDescriptor {
      private final Map dependencyManagementMap = new LinkedHashMap();
      private final Map depDescriptors = new HashMap();

      public PomModuleDescriptor(ModuleDescriptorParser parser, Resource res) {
         super(parser, res);
      }

      public void addDependencyManagement(PomDependencyMgt dependencyMgt) {
         this.dependencyManagementMap.put(ModuleId.newInstance(dependencyMgt.getGroupId(), dependencyMgt.getArtifactId()), dependencyMgt);
      }

      public Map getDependencyManagementMap() {
         return this.dependencyManagementMap;
      }

      public void addDependency(DependencyDescriptor dependency) {
         super.addDependency(dependency);
         this.depDescriptors.put(dependency.getDependencyRevisionId(), dependency);
      }
   }

   interface ConfMapper {
      void addMappingConfs(DefaultDependencyDescriptor var1, boolean var2);
   }
}
