package org.apache.ivy.core.module.descriptor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.id.ModuleRules;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.matcher.MapMatcher;
import org.apache.ivy.plugins.matcher.MatcherHelper;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.NameSpaceHelper;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.namespace.NamespaceTransformer;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorParser;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorWriter;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Message;

public class DefaultModuleDescriptor implements ModuleDescriptor {
   private ModuleRevisionId revId;
   private ModuleRevisionId resolvedRevId;
   private String status;
   private Date publicationDate;
   private Date resolvedPublicationDate;
   private List dependencies;
   private Map configurations;
   private Map artifactsByConf;
   private Collection artifacts;
   private boolean isDefault;
   private ModuleRules conflictManagers;
   private ModuleRules dependencyDescriptorMediators;
   private List licenses;
   private String homePage;
   private String description;
   private long lastModified;
   private Namespace namespace;
   private String defaultConf;
   private String defaultConfMapping;
   private boolean mappingOverride;
   private ModuleDescriptorParser parser;
   private Resource resource;
   private List excludeRules;
   private Artifact metadataArtifact;
   private List inheritedDescriptors;
   private Map extraAttributesNamespaces;
   private List extraInfos;

   public static DefaultModuleDescriptor newDefaultInstance(ModuleRevisionId mrid) {
      return newDefaultInstance(mrid, (DependencyArtifactDescriptor[])null);
   }

   public static DefaultModuleDescriptor newCallerInstance(ModuleRevisionId mrid, String[] confs, boolean transitive, boolean changing) {
      DefaultModuleDescriptor moduleDescriptor = new DefaultModuleDescriptor(ModuleRevisionId.newInstance(mrid.getOrganisation(), mrid.getName() + "-caller", "working"), "integration", (Date)null, true);

      for(String conf : confs) {
         moduleDescriptor.addConfiguration(new Configuration(conf));
      }

      moduleDescriptor.setLastModified(System.currentTimeMillis());
      DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(moduleDescriptor, mrid, true, changing, transitive);

      for(String conf : confs) {
         dd.addDependencyConfiguration(conf, conf);
      }

      moduleDescriptor.addDependency(dd);
      return moduleDescriptor;
   }

   public static DefaultModuleDescriptor newCallerInstance(ModuleRevisionId[] mrids, boolean transitive, boolean changing) {
      DefaultModuleDescriptor moduleDescriptor = new DefaultModuleDescriptor(ModuleRevisionId.newInstance("caller", "all-caller", "working"), "integration", (Date)null, true);
      moduleDescriptor.addConfiguration(new Configuration("default"));
      moduleDescriptor.setLastModified(System.currentTimeMillis());

      for(ModuleRevisionId mrid : mrids) {
         DefaultDependencyDescriptor dd = new DefaultDependencyDescriptor(moduleDescriptor, mrid, true, changing, transitive);
         dd.addDependencyConfiguration("default", "*");
         moduleDescriptor.addDependency(dd);
      }

      return moduleDescriptor;
   }

   public static DefaultModuleDescriptor newDefaultInstance(ModuleRevisionId mrid, DependencyArtifactDescriptor[] artifacts) {
      DefaultModuleDescriptor moduleDescriptor = new DefaultModuleDescriptor(mrid, "release", (Date)null, true);
      moduleDescriptor.addConfiguration(new Configuration("default"));
      if (artifacts != null && artifacts.length > 0) {
         for(DependencyArtifactDescriptor artifact : artifacts) {
            moduleDescriptor.addArtifact("default", new MDArtifact(moduleDescriptor, artifact.getName(), artifact.getType(), artifact.getExt(), artifact.getUrl(), artifact.getQualifiedExtraAttributes()));
         }
      } else {
         moduleDescriptor.addArtifact("default", new MDArtifact(moduleDescriptor, mrid.getName(), "jar", "jar"));
      }

      moduleDescriptor.setLastModified(System.currentTimeMillis());
      return moduleDescriptor;
   }

   public static DefaultModuleDescriptor newBasicInstance(ModuleRevisionId mrid, Date publicationDate) {
      DefaultModuleDescriptor moduleDescriptor = new DefaultModuleDescriptor(mrid, "release", publicationDate, false);
      moduleDescriptor.addConfiguration(new Configuration("default"));
      moduleDescriptor.addArtifact("default", new MDArtifact(moduleDescriptor, mrid.getName(), "jar", "jar"));
      return moduleDescriptor;
   }

   public static ModuleDescriptor transformInstance(ModuleDescriptor md, Namespace ns) {
      NamespaceTransformer t = ns.getToSystemTransformer();
      if (t.isIdentity()) {
         return md;
      } else {
         DefaultModuleDescriptor nmd = new DefaultModuleDescriptor(md.getParser(), md.getResource());
         nmd.revId = t.transform(md.getModuleRevisionId());
         nmd.resolvedRevId = t.transform(md.getResolvedModuleRevisionId());
         nmd.status = md.getStatus();
         nmd.publicationDate = md.getPublicationDate();
         nmd.resolvedPublicationDate = md.getResolvedPublicationDate();

         for(ExtendsDescriptor ed : md.getInheritedDescriptors()) {
            ModuleDescriptor parentMd = ed.getParentMd();
            DefaultModuleDescriptor parentNmd = new DefaultModuleDescriptor(parentMd.getParser(), parentMd.getResource());
            parentNmd.revId = t.transform(parentMd.getModuleRevisionId());
            parentNmd.resolvedRevId = t.transform(parentMd.getResolvedModuleRevisionId());
            parentNmd.status = parentMd.getStatus();
            parentNmd.publicationDate = parentMd.getPublicationDate();
            parentNmd.resolvedPublicationDate = parentMd.getResolvedPublicationDate();
            nmd.inheritedDescriptors.add(new DefaultExtendsDescriptor(parentNmd, ed.getLocation(), ed.getExtendsTypes()));
         }

         for(DependencyDescriptor dd : md.getDependencies()) {
            nmd.dependencies.add(NameSpaceHelper.toSystem(dd, ns));
         }

         for(Configuration conf : md.getConfigurations()) {
            String confName = conf.getName();
            nmd.configurations.put(confName, conf);

            for(Artifact art : md.getArtifacts(confName)) {
               nmd.addArtifact(confName, NameSpaceHelper.transform(art, t));
            }
         }

         nmd.setDefault(md.isDefault());
         if (md instanceof DefaultModuleDescriptor) {
            DefaultModuleDescriptor dmd = (DefaultModuleDescriptor)md;
            nmd.conflictManagers = dmd.conflictManagers.clone();
            nmd.dependencyDescriptorMediators = dmd.dependencyDescriptorMediators.clone();
         } else {
            Message.warn("transformed module descriptor is not a default module descriptor: impossible to copy conflict manager and version mediation configuration: " + md);
         }

         nmd.licenses.addAll(Arrays.asList(md.getLicenses()));
         nmd.homePage = md.getHomePage();
         nmd.description = md.getDescription();
         nmd.lastModified = md.getLastModified();
         nmd.extraAttributesNamespaces = md.getExtraAttributesNamespaces();
         nmd.extraInfos = md.getExtraInfos();
         nmd.namespace = ns;
         return nmd;
      }
   }

   public DefaultModuleDescriptor(ModuleRevisionId id, String status, Date pubDate) {
      this(id, status, pubDate, false);
   }

   public DefaultModuleDescriptor(ModuleRevisionId id, String status, Date pubDate, boolean isDefault) {
      this.status = StatusManager.getCurrent().getDefaultStatus();
      this.dependencies = new ArrayList();
      this.configurations = new LinkedHashMap();
      this.artifactsByConf = new HashMap();
      this.artifacts = new LinkedHashSet();
      this.isDefault = false;
      this.conflictManagers = new ModuleRules();
      this.dependencyDescriptorMediators = new ModuleRules();
      this.licenses = new ArrayList();
      this.description = "";
      this.lastModified = 0L;
      this.excludeRules = new ArrayList();
      this.inheritedDescriptors = new ArrayList();
      this.extraAttributesNamespaces = new LinkedHashMap();
      this.extraInfos = new ArrayList();
      if (id == null) {
         throw new NullPointerException("null module revision id not allowed");
      } else if (status == null) {
         throw new NullPointerException("null status not allowed");
      } else {
         this.revId = id;
         this.resolvedRevId = id;
         this.status = status;
         this.publicationDate = pubDate;
         this.resolvedPublicationDate = this.publicationDate == null ? new Date() : this.publicationDate;
         this.isDefault = isDefault;
         this.parser = XmlModuleDescriptorParser.getInstance();
      }
   }

   public DefaultModuleDescriptor(ModuleDescriptorParser parser, Resource res) {
      this.status = StatusManager.getCurrent().getDefaultStatus();
      this.dependencies = new ArrayList();
      this.configurations = new LinkedHashMap();
      this.artifactsByConf = new HashMap();
      this.artifacts = new LinkedHashSet();
      this.isDefault = false;
      this.conflictManagers = new ModuleRules();
      this.dependencyDescriptorMediators = new ModuleRules();
      this.licenses = new ArrayList();
      this.description = "";
      this.lastModified = 0L;
      this.excludeRules = new ArrayList();
      this.inheritedDescriptors = new ArrayList();
      this.extraAttributesNamespaces = new LinkedHashMap();
      this.extraInfos = new ArrayList();
      this.parser = parser;
      this.resource = res;
   }

   public Artifact getMetadataArtifact() {
      if (this.metadataArtifact == null) {
         this.metadataArtifact = DefaultArtifact.newIvyArtifact(this.resolvedRevId, this.resolvedPublicationDate);
      }

      return this.metadataArtifact;
   }

   public void setModuleArtifact(Artifact moduleArtifact) {
      this.metadataArtifact = moduleArtifact;
   }

   public boolean isDefault() {
      return this.isDefault;
   }

   public void setPublicationDate(Date publicationDate) {
      this.publicationDate = publicationDate;
      if (this.resolvedPublicationDate == null) {
         this.resolvedPublicationDate = publicationDate == null ? new Date() : publicationDate;
      }

   }

   public Date getPublicationDate() {
      return this.publicationDate;
   }

   public void setResolvedPublicationDate(Date publicationDate) {
      if (publicationDate == null) {
         throw new NullPointerException("null publication date not allowed");
      } else {
         this.resolvedPublicationDate = publicationDate;
      }
   }

   public Date getResolvedPublicationDate() {
      return this.resolvedPublicationDate;
   }

   public String getRevision() {
      return this.getResolvedModuleRevisionId().getRevision();
   }

   public void setModuleRevisionId(ModuleRevisionId revId) {
      if (revId == null) {
         throw new NullPointerException("null module revision id not allowed");
      } else {
         this.revId = revId;
         if (this.resolvedRevId == null) {
            this.resolvedRevId = revId;
         }

      }
   }

   public void setResolvedModuleRevisionId(ModuleRevisionId revId) {
      this.resolvedRevId = revId;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public void addInheritedDescriptor(ExtendsDescriptor descriptor) {
      this.inheritedDescriptors.add(descriptor);
   }

   public void addDependency(DependencyDescriptor dependency) {
      this.dependencies.add(dependency);
   }

   public void addConfiguration(Configuration conf) {
      this.configurations.put(conf.getName(), conf);
   }

   public void addArtifact(String conf, Artifact artifact) {
      Configuration c = this.getConfiguration(conf);
      if (c == null) {
         throw new IllegalArgumentException("Cannot add artifact '" + artifact.getId().getArtifactId().getShortDescription() + "' to configuration '" + conf + "' of module " + this.revId + " because this configuration doesn't exist!");
      } else {
         if (c instanceof ConfigurationGroup) {
            ConfigurationGroup group = (ConfigurationGroup)c;

            for(String member : group.getMembersConfigurationNames()) {
               this.addArtifact(member, artifact);
            }
         } else {
            Collection<Artifact> artifacts = (Collection)this.artifactsByConf.get(conf);
            if (artifacts == null) {
               artifacts = new ArrayList();
               this.artifactsByConf.put(conf, artifacts);
            }

            artifacts.add(artifact);
            this.artifacts.add(artifact);
         }

      }
   }

   public ModuleRevisionId getModuleRevisionId() {
      return this.revId;
   }

   public ModuleRevisionId getResolvedModuleRevisionId() {
      return this.resolvedRevId;
   }

   public String getStatus() {
      return this.status;
   }

   public ExtendsDescriptor[] getInheritedDescriptors() {
      return (ExtendsDescriptor[])this.inheritedDescriptors.toArray(new ExtendsDescriptor[this.inheritedDescriptors.size()]);
   }

   public Configuration[] getConfigurations() {
      return (Configuration[])this.configurations.values().toArray(new Configuration[this.configurations.size()]);
   }

   public String[] getConfigurationsNames() {
      return (String[])this.configurations.keySet().toArray(new String[this.configurations.size()]);
   }

   public String[] getPublicConfigurationsNames() {
      List<String> ret = new ArrayList();

      for(Configuration conf : this.configurations.values()) {
         if (Configuration.Visibility.PUBLIC.equals(conf.getVisibility())) {
            ret.add(conf.getName());
         }
      }

      return (String[])ret.toArray(new String[ret.size()]);
   }

   public Configuration getConfiguration(String confName) {
      Configuration configuration = (Configuration)this.configurations.get(confName);
      if (configuration == null && confName != null) {
         Matcher m = Pattern.compile("\\*\\[([^=]+)\\=([^\\]]+)\\]").matcher(confName);
         if (m.matches()) {
            String attName = m.group(1);
            String attValue = m.group(2);
            Map<String, Configuration> members = new LinkedHashMap();

            for(Configuration conf : this.configurations.values()) {
               if (attValue.equals(conf.getAttribute(attName))) {
                  members.put(conf.getName(), conf);
               }
            }

            return new ConfigurationGroup(confName, members);
         } else {
            String[] confs = confName.split("\\+");
            if (confs.length <= 1) {
               return null;
            } else {
               Map<String, Configuration> intersectedConfs = new LinkedHashMap();

               for(String conf : confs) {
                  Configuration c = (Configuration)this.configurations.get(conf);
                  if (c == null) {
                     Message.verbose("missing configuration '" + conf + "' from intersection " + confName + " in " + this);
                     return null;
                  }

                  intersectedConfs.put(conf, c);
               }

               return new ConfigurationIntersection(confName, intersectedConfs);
            }
         }
      } else {
         return configuration;
      }
   }

   public Artifact[] getArtifacts(String conf) {
      Configuration c = this.getConfiguration(conf);
      if (c == null) {
         return new Artifact[0];
      } else {
         Collection<Artifact> artifacts = (Collection)this.artifactsByConf.get(conf);
         if (c instanceof ConfigurationIntersection) {
            ConfigurationIntersection intersection = (ConfigurationIntersection)c;
            Set<Artifact> intersectedArtifacts = new LinkedHashSet();

            for(String intersect : intersection.getIntersectedConfigurationNames()) {
               Collection<Artifact> arts = this.getArtifactsIncludingExtending(intersect);
               if (intersectedArtifacts.isEmpty()) {
                  intersectedArtifacts.addAll(arts);
               } else {
                  intersectedArtifacts.retainAll(arts);
               }
            }

            if (artifacts != null) {
               intersectedArtifacts.addAll(artifacts);
            }

            return (Artifact[])intersectedArtifacts.toArray(new Artifact[intersectedArtifacts.size()]);
         } else if (!(c instanceof ConfigurationGroup)) {
            return artifacts == null ? new Artifact[0] : (Artifact[])artifacts.toArray(new Artifact[artifacts.size()]);
         } else {
            ConfigurationGroup group = (ConfigurationGroup)c;
            Set<Artifact> groupArtifacts = new LinkedHashSet();

            for(String member : group.getMembersConfigurationNames()) {
               groupArtifacts.addAll(this.getArtifactsIncludingExtending(member));
            }

            if (artifacts != null) {
               groupArtifacts.addAll(artifacts);
            }

            return (Artifact[])groupArtifacts.toArray(new Artifact[groupArtifacts.size()]);
         }
      }
   }

   private Collection getArtifactsIncludingExtending(String conf) {
      Set<Artifact> artifacts = new LinkedHashSet();
      Collection<Artifact> arts = (Collection)this.artifactsByConf.get(conf);
      if (arts != null) {
         artifacts.addAll(arts);
      }

      for(Configuration extendingConf : Configuration.findConfigurationExtending(conf, this.getConfigurations())) {
         arts = (Collection)this.artifactsByConf.get(extendingConf.getName());
         if (arts != null) {
            artifacts.addAll(arts);
         }
      }

      return artifacts;
   }

   public Artifact[] getAllArtifacts() {
      return (Artifact[])this.artifacts.toArray(new Artifact[this.artifacts.size()]);
   }

   public DependencyDescriptor[] getDependencies() {
      return (DependencyDescriptor[])this.dependencies.toArray(new DependencyDescriptor[this.dependencies.size()]);
   }

   public boolean dependsOn(VersionMatcher matcher, ModuleDescriptor md) {
      for(DependencyDescriptor dd : this.dependencies) {
         if (dd.getDependencyId().equals(md.getModuleRevisionId().getModuleId())) {
            if (md.getResolvedModuleRevisionId().getRevision() == null) {
               return true;
            }

            if (matcher.accept(dd.getDependencyRevisionId(), md)) {
               return true;
            }
         }
      }

      return false;
   }

   public void toIvyFile(File destFile) throws ParseException, IOException {
      if (this.parser != null && this.resource != null) {
         this.parser.toIvyFile(this.resource.openStream(), this.resource, destFile, this);
      } else {
         XmlModuleDescriptorWriter.write(this, destFile);
      }

   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.revId == null ? 0 : this.revId.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof DefaultModuleDescriptor)) {
         return false;
      } else {
         DefaultModuleDescriptor other = (DefaultModuleDescriptor)obj;
         return this.revId == null ? other.revId == null : this.revId.equals(other.revId);
      }
   }

   public String toString() {
      return "module: " + this.revId + " status=" + this.status + " publication=" + this.publicationDate + " configurations=" + this.configurations + " artifacts=" + this.artifactsByConf + " dependencies=" + this.dependencies;
   }

   public void setDefault(boolean b) {
      this.isDefault = b;
   }

   public void addConflictManager(ModuleId moduleId, PatternMatcher matcher, ConflictManager manager) {
      this.conflictManagers.defineRule(new MapMatcher(moduleId.getAttributes(), matcher), manager);
   }

   public ConflictManager getConflictManager(ModuleId moduleId) {
      return (ConflictManager)this.conflictManagers.getRule(moduleId);
   }

   public void addDependencyDescriptorMediator(ModuleId moduleId, PatternMatcher matcher, DependencyDescriptorMediator ddm) {
      this.dependencyDescriptorMediators.defineRule(new MapMatcher(moduleId.getAttributes(), matcher), ddm);
   }

   public DependencyDescriptor mediate(DependencyDescriptor dd) {
      for(DependencyDescriptorMediator mediator : this.dependencyDescriptorMediators.getRules(dd.getDependencyId())) {
         dd = mediator.mediate(dd);
      }

      return dd;
   }

   public ModuleRules getAllDependencyDescriptorMediators() {
      return this.dependencyDescriptorMediators.clone();
   }

   public void addLicense(License license) {
      this.licenses.add(license);
   }

   public License[] getLicenses() {
      return (License[])this.licenses.toArray(new License[this.licenses.size()]);
   }

   public String getHomePage() {
      return this.homePage;
   }

   public void setHomePage(String homePage) {
      this.homePage = homePage;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public long getLastModified() {
      return this.lastModified;
   }

   public void setLastModified(long lastModified) {
      this.lastModified = lastModified;
   }

   public Namespace getNamespace() {
      return this.namespace;
   }

   public boolean isNamespaceUseful() {
      for(DependencyDescriptor dd : this.dependencies) {
         if (dd.getAllExcludeRules().length > 0) {
            return true;
         }
      }

      return false;
   }

   public void setNamespace(Namespace ns) {
      this.namespace = ns;
   }

   public void check() {
      Stack<String> confs = new Stack();

      for(Configuration conf : this.configurations.values()) {
         for(String ext : conf.getExtends()) {
            confs.push(conf.getName());
            this.checkConf(confs, ext.trim());
            confs.pop();
         }
      }

   }

   private void checkConf(Stack confs, String confName) {
      int index = confs.indexOf(confName);
      if (index != -1) {
         StringBuilder cycle;
         for(cycle = new StringBuilder(); index < confs.size(); ++index) {
            cycle.append((String)confs.get(index)).append(" => ");
         }

         cycle.append(confName);
         throw new IllegalStateException("illegal cycle detected in configuration extension: " + cycle);
      } else {
         Configuration conf = this.getConfiguration(confName);
         if (conf == null) {
            throw new IllegalStateException("unknown configuration '" + confName + "'. It is extended by " + (String)confs.get(confs.size() - 1));
         } else {
            for(String ext : conf.getExtends()) {
               confs.push(conf.getName());
               this.checkConf(confs, ext.trim());
               confs.pop();
            }

         }
      }
   }

   public String getDefaultConf() {
      return this.defaultConf;
   }

   public void setDefaultConf(String defaultConf) {
      this.defaultConf = defaultConf;
   }

   public String getDefaultConfMapping() {
      return this.defaultConfMapping;
   }

   public void setDefaultConfMapping(String defaultConfMapping) {
      this.defaultConfMapping = defaultConfMapping;
   }

   public void setMappingOverride(boolean override) {
      this.mappingOverride = override;
   }

   public boolean isMappingOverride() {
      return this.mappingOverride;
   }

   public String getAttribute(String attName) {
      return this.resolvedRevId.getAttribute(attName);
   }

   public Map getAttributes() {
      return this.resolvedRevId.getAttributes();
   }

   public String getExtraAttribute(String attName) {
      return this.resolvedRevId.getExtraAttribute(attName);
   }

   public Map getExtraAttributes() {
      return this.resolvedRevId.getExtraAttributes();
   }

   public Map getQualifiedExtraAttributes() {
      return this.resolvedRevId.getQualifiedExtraAttributes();
   }

   public ModuleDescriptorParser getParser() {
      return this.parser;
   }

   public Resource getResource() {
      return this.resource;
   }

   public void addExcludeRule(ExcludeRule rule) {
      this.excludeRules.add(rule);
   }

   public boolean canExclude() {
      return !this.excludeRules.isEmpty();
   }

   public boolean doesExclude(String[] moduleConfigurations, ArtifactId artifactId) {
      if (this.namespace != null) {
         artifactId = NameSpaceHelper.transform(artifactId, this.namespace.getFromSystemTransformer());
      }

      for(ExcludeRule rule : this.getExcludeRules(moduleConfigurations)) {
         if (MatcherHelper.matches(rule.getMatcher(), rule.getId(), artifactId)) {
            return true;
         }
      }

      return false;
   }

   public ExcludeRule[] getAllExcludeRules() {
      return (ExcludeRule[])this.excludeRules.toArray(new ExcludeRule[this.excludeRules.size()]);
   }

   public ExcludeRule[] getExcludeRules(String[] moduleConfigurations) {
      Set<ExcludeRule> rules = new LinkedHashSet();

      for(ExcludeRule rule : this.excludeRules) {
         String[] ruleConfs = rule.getConfigurations();
         if (this.containsAny(ruleConfs, moduleConfigurations)) {
            rules.add(rule);
         }
      }

      return (ExcludeRule[])rules.toArray(new ExcludeRule[rules.size()]);
   }

   private boolean containsAny(String[] arr1, String[] arr2) {
      return (new ArrayList(Arrays.asList(arr1))).removeAll(Arrays.asList(arr2));
   }

   public Map getExtraAttributesNamespaces() {
      return this.extraAttributesNamespaces;
   }

   public void addExtraAttributeNamespace(String prefix, String namespace) {
      this.extraAttributesNamespaces.put(prefix, namespace);
   }

   /** @deprecated */
   @Deprecated
   public void addExtraInfo(String infoKey, String value) {
      this.extraInfos.add(new ExtraInfoHolder(infoKey, value));
   }

   /** @deprecated */
   @Deprecated
   public Map getExtraInfo() {
      Map<String, String> map = new HashMap();

      for(ExtraInfoHolder extraInfo : this.extraInfos) {
         this.populateExtraInfoMap(map, extraInfo);
      }

      return map;
   }

   private void populateExtraInfoMap(Map map, ExtraInfoHolder extraInfo) {
      map.put(extraInfo.getName(), extraInfo.getContent());

      for(ExtraInfoHolder nested : extraInfo.getNestedExtraInfoHolder()) {
         this.populateExtraInfoMap(map, nested);
      }

   }

   public List getExtraInfos() {
      return this.extraInfos;
   }

   public void addExtraInfo(ExtraInfoHolder extraInfo) {
      this.extraInfos.add(extraInfo);
   }

   public String getExtraInfoContentByTagName(String tagName) {
      ExtraInfoHolder extraInfoByTagName = this.getExtraInfoByTagName(tagName);
      return extraInfoByTagName != null ? extraInfoByTagName.getContent() : null;
   }

   public ExtraInfoHolder getExtraInfoByTagName(String tagName) {
      for(ExtraInfoHolder extraInfoHolder : this.extraInfos) {
         if (extraInfoHolder.getName().equals(tagName)) {
            return extraInfoHolder;
         }
      }

      return null;
   }
}
