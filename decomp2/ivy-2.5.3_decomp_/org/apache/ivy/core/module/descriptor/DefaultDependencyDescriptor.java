package org.apache.ivy.core.module.descriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.matcher.MatcherHelper;
import org.apache.ivy.plugins.namespace.NameSpaceHelper;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.namespace.NamespaceTransformer;
import org.apache.ivy.util.Checks;

public class DefaultDependencyDescriptor implements DependencyDescriptor {
   private static final Pattern SELF_FALLBACK_PATTERN = Pattern.compile("@(\\+[^\\(]+)?(\\(.*\\))?");
   private static final Pattern THIS_FALLBACK_PATTERN = Pattern.compile("#(\\+[^\\(]+)?(\\(.*\\))?");
   private final ModuleRevisionId revId;
   private ModuleRevisionId dynamicRevId;
   private Map confs;
   private Map dependencyArtifacts;
   private Map includeRules;
   private Map excludeRules;
   private boolean isForce;
   private boolean isChanging;
   private ModuleRevisionId parentId;
   private boolean isTransitive;
   private Namespace namespace;
   private final ModuleDescriptor md;
   private DependencyDescriptor asSystem;
   private ModuleRevisionId sourceModule;

   public static DependencyDescriptor transformInstance(DependencyDescriptor dd, Namespace ns) {
      NamespaceTransformer t = ns.getToSystemTransformer();
      if (t.isIdentity()) {
         return dd;
      } else {
         DefaultDependencyDescriptor newdd = transformInstance(dd, t, false);
         newdd.namespace = ns;
         return newdd;
      }
   }

   public static DefaultDependencyDescriptor transformInstance(DependencyDescriptor dd, NamespaceTransformer t, boolean fromSystem) {
      ModuleRevisionId transformParentId = t.transform(dd.getParentRevisionId());
      ModuleRevisionId transformMrid = t.transform(dd.getDependencyRevisionId());
      ModuleRevisionId transformDynamicMrid = t.transform(dd.getDynamicConstraintDependencyRevisionId());
      DefaultDependencyDescriptor newdd = new DefaultDependencyDescriptor((ModuleDescriptor)null, transformMrid, transformDynamicMrid, dd.isForce(), dd.isChanging(), dd.isTransitive());
      newdd.parentId = transformParentId;
      ModuleRevisionId sourceModule = dd.getSourceModule();
      if (sourceModule != null) {
         newdd.sourceModule = t.transform(sourceModule);
      }

      String[] moduleConfs = dd.getModuleConfigurations();
      if (moduleConfs.length == 1 && "*".equals(moduleConfs[0])) {
         if (!(dd instanceof DefaultDependencyDescriptor)) {
            throw new IllegalArgumentException("dependency descriptor transformation does not support * module confs with descriptors which aren't DefaultDependencyDescriptor");
         }

         DefaultDependencyDescriptor ddd = (DefaultDependencyDescriptor)dd;
         newdd.confs = new LinkedHashMap(ddd.confs);
         newdd.setExcludeRules(new LinkedHashMap(ddd.getExcludeRules()));
         newdd.setIncludeRules(new LinkedHashMap(ddd.getIncludeRules()));
         newdd.setDependencyArtifacts(new LinkedHashMap(ddd.getDependencyArtifacts()));
      } else {
         for(String moduleConf : moduleConfs) {
            newdd.confs.put(moduleConf, new ArrayList(Arrays.asList(dd.getDependencyConfigurations(moduleConf))));
            newdd.getExcludeRules().put(moduleConf, new ArrayList(Arrays.asList(dd.getExcludeRules(moduleConf))));
            newdd.getIncludeRules().put(moduleConf, new ArrayList(Arrays.asList(dd.getIncludeRules(moduleConf))));
            newdd.getDependencyArtifacts().put(moduleConf, new ArrayList(Arrays.asList(dd.getDependencyArtifacts(moduleConf))));
         }
      }

      if (fromSystem) {
         newdd.asSystem = dd;
      }

      return newdd;
   }

   private DefaultDependencyDescriptor(DefaultDependencyDescriptor dd, ModuleRevisionId revision) {
      this.confs = new LinkedHashMap();
      this.isTransitive = true;
      this.namespace = null;
      this.asSystem = this;
      Checks.checkNotNull(dd, "dd");
      Checks.checkNotNull(revision, "revision");
      if (!revision.getModuleId().equals(dd.getDependencyId())) {
         throw new IllegalArgumentException("new ModuleRevisionId MUST have the same ModuleId as original one. original = " + dd.getDependencyId() + " new = " + revision.getModuleId());
      } else {
         this.md = dd.md;
         this.parentId = dd.parentId;
         this.revId = revision;
         this.dynamicRevId = dd.dynamicRevId;
         this.isForce = dd.isForce;
         this.isChanging = dd.isChanging;
         this.isTransitive = dd.isTransitive;
         this.namespace = dd.namespace;
         this.confs.putAll(dd.confs);
         this.excludeRules = dd.excludeRules == null ? null : new LinkedHashMap(dd.excludeRules);
         this.includeRules = dd.includeRules == null ? null : new LinkedHashMap(dd.includeRules);
         this.dependencyArtifacts = dd.dependencyArtifacts == null ? null : new LinkedHashMap(dd.dependencyArtifacts);
         this.sourceModule = dd.sourceModule;
      }
   }

   public DefaultDependencyDescriptor(ModuleDescriptor md, ModuleRevisionId mrid, boolean force, boolean changing, boolean transitive) {
      this(md, mrid, mrid, force, changing, transitive);
   }

   public DefaultDependencyDescriptor(ModuleRevisionId mrid, boolean force) {
      this(mrid, force, false);
   }

   public DefaultDependencyDescriptor(ModuleRevisionId mrid, boolean force, boolean changing) {
      this((ModuleDescriptor)null, mrid, mrid, force, changing, true);
   }

   public DefaultDependencyDescriptor(ModuleDescriptor md, ModuleRevisionId mrid, ModuleRevisionId dynamicConstraint, boolean force, boolean changing, boolean transitive) {
      this.confs = new LinkedHashMap();
      this.isTransitive = true;
      this.namespace = null;
      this.asSystem = this;
      Checks.checkNotNull(mrid, "mrid");
      Checks.checkNotNull(dynamicConstraint, "dynamicConstraint");
      this.md = md;
      this.revId = mrid;
      this.dynamicRevId = dynamicConstraint;
      this.isForce = force;
      this.isChanging = changing;
      this.isTransitive = transitive;
      this.sourceModule = md == null ? null : md.getModuleRevisionId();
   }

   public ModuleId getDependencyId() {
      return this.getDependencyRevisionId().getModuleId();
   }

   public ModuleDescriptor getModuleDescriptor() {
      return this.md;
   }

   public ModuleRevisionId getDependencyRevisionId() {
      return this.revId;
   }

   public ModuleRevisionId getDynamicConstraintDependencyRevisionId() {
      return this.dynamicRevId;
   }

   public String[] getModuleConfigurations() {
      return (String[])this.confs.keySet().toArray(new String[this.confs.keySet().size()]);
   }

   public String[] getDependencyConfigurations(String moduleConfiguration) {
      return this.getDependencyConfigurations(moduleConfiguration, moduleConfiguration);
   }

   public String[] getDependencyConfigurations(String moduleConfiguration, String requestedConfiguration) {
      if (this.md != null) {
         Configuration c = this.md.getConfiguration(moduleConfiguration);
         if (c instanceof ConfigurationIntersection) {
            ConfigurationIntersection intersection = (ConfigurationIntersection)c;
            Set<String> intersectedDepConfs = new HashSet();

            for(String intersect : intersection.getIntersectedConfigurationNames()) {
               Collection<String> depConfs = this.getDependencyConfigurationsIncludingExtending(intersect, requestedConfiguration);
               if (intersectedDepConfs.isEmpty()) {
                  intersectedDepConfs.addAll(depConfs);
               } else if (intersectedDepConfs.contains("*")) {
                  intersectedDepConfs.remove("*");
                  intersectedDepConfs.addAll(depConfs);
               } else if (!depConfs.contains("*")) {
                  Set<String> intersectedDepConfsCopy = intersectedDepConfs;
                  intersectedDepConfs = new HashSet();

                  for(String intersectedDepConf : intersectedDepConfsCopy) {
                     if (depConfs.contains(intersectedDepConf)) {
                        intersectedDepConfs.add(intersectedDepConf);
                     }
                  }
               }
            }

            List<String> confsList = (List)this.confs.get(moduleConfiguration);
            if (confsList != null) {
               intersectedDepConfs.addAll(confsList);
            }

            if (intersectedDepConfs.isEmpty()) {
               List<String> defConfs = (List)this.confs.get("*");
               if (defConfs != null) {
                  for(String mappedConf : defConfs) {
                     if (mappedConf != null && mappedConf.startsWith("@+")) {
                        return new String[]{moduleConfiguration + mappedConf.substring(1)};
                     }

                     if (mappedConf != null && mappedConf.equals("@")) {
                        return new String[]{moduleConfiguration};
                     }
                  }
               }
            }

            return (String[])intersectedDepConfs.toArray(new String[intersectedDepConfs.size()]);
         }

         if (c instanceof ConfigurationGroup) {
            ConfigurationGroup group = (ConfigurationGroup)c;
            Set<String> groupDepConfs = new HashSet();

            for(String member : group.getMembersConfigurationNames()) {
               Collection<String> depConfs = this.getDependencyConfigurationsIncludingExtending(member, requestedConfiguration);
               groupDepConfs.addAll(depConfs);
            }

            return (String[])groupDepConfs.toArray(new String[groupDepConfs.size()]);
         }
      }

      List<String> confsList = (List)this.confs.get(moduleConfiguration);
      if (confsList == null) {
         confsList = (List)this.confs.get("%");
      }

      List<String> defConfs = (List)this.confs.get("*");
      Collection<String> ret = new LinkedHashSet();
      if (confsList != null) {
         ret.addAll(confsList);
      }

      if (defConfs != null) {
         ret.addAll(defConfs);
         List<String> excludedConfs = (List)this.confs.get("!" + moduleConfiguration);
         if (excludedConfs != null) {
            ret.removeAll(excludedConfs);
         }
      }

      Collection<String> replacedRet = new LinkedHashSet();

      for(String c : ret) {
         String replacedConf = replaceSelfFallbackPattern(c, moduleConfiguration);
         if (replacedConf == null) {
            replacedConf = replaceThisFallbackPattern(c, requestedConfiguration);
         }

         if (replacedConf != null) {
            c = replacedConf;
         }

         replacedRet.add(c);
      }

      if (replacedRet.remove("*")) {
         StringBuilder r = new StringBuilder("*");

         for(String c : replacedRet) {
            if (c.startsWith("!")) {
               r.append(c);
            }
         }

         return new String[]{r.toString()};
      } else {
         return (String[])replacedRet.toArray(new String[replacedRet.size()]);
      }
   }

   private Collection getDependencyConfigurationsIncludingExtending(String conf, String requestedConfiguration) {
      Set<String> allDepConfs = new LinkedHashSet(Arrays.asList(this.getDependencyConfigurations(conf, requestedConfiguration)));

      for(Configuration extendingConf : Configuration.findConfigurationExtending(conf, this.md.getConfigurations())) {
         allDepConfs.addAll(Arrays.asList(this.getDependencyConfigurations(extendingConf.getName(), requestedConfiguration)));
      }

      return allDepConfs;
   }

   protected static String replaceSelfFallbackPattern(String conf, String moduleConfiguration) {
      return replaceFallbackConfigurationPattern(SELF_FALLBACK_PATTERN, conf, moduleConfiguration);
   }

   protected static String replaceThisFallbackPattern(String conf, String requestedConfiguration) {
      return replaceFallbackConfigurationPattern(THIS_FALLBACK_PATTERN, conf, requestedConfiguration);
   }

   protected static String replaceFallbackConfigurationPattern(Pattern pattern, String conf, String moduleConfiguration) {
      Matcher matcher = pattern.matcher(conf);
      if (matcher.matches()) {
         String mappedConf = moduleConfiguration;
         if (matcher.group(1) != null) {
            mappedConf = moduleConfiguration + matcher.group(1);
         }

         if (matcher.group(2) != null) {
            mappedConf = mappedConf + matcher.group(2);
         }

         return mappedConf;
      } else {
         return null;
      }
   }

   public String[] getDependencyConfigurations(String[] moduleConfigurations) {
      Set<String> confs = new LinkedHashSet();

      for(String moduleConfiguration : moduleConfigurations) {
         confs.addAll(Arrays.asList(this.getDependencyConfigurations(moduleConfiguration)));
      }

      return confs.contains("*") ? new String[]{"*"} : (String[])confs.toArray(new String[confs.size()]);
   }

   public DependencyArtifactDescriptor[] getDependencyArtifacts(String moduleConfiguration) {
      Collection<DependencyArtifactDescriptor> artifacts = this.getCollectionForConfiguration(moduleConfiguration, this.dependencyArtifacts);
      return (DependencyArtifactDescriptor[])artifacts.toArray(new DependencyArtifactDescriptor[artifacts.size()]);
   }

   public IncludeRule[] getIncludeRules(String moduleConfiguration) {
      Collection<IncludeRule> rules = this.getCollectionForConfiguration(moduleConfiguration, this.includeRules);
      return (IncludeRule[])rules.toArray(new IncludeRule[rules.size()]);
   }

   public ExcludeRule[] getExcludeRules(String moduleConfiguration) {
      Collection<ExcludeRule> rules = this.getCollectionForConfiguration(moduleConfiguration, this.excludeRules);
      return (ExcludeRule[])rules.toArray(new ExcludeRule[rules.size()]);
   }

   private Set getCollectionForConfiguration(String moduleConfiguration, Map collectionMap) {
      if (collectionMap != null && !collectionMap.isEmpty()) {
         Collection<T> artifacts = (Collection)collectionMap.get(moduleConfiguration);
         Collection<T> defArtifacts = (Collection)collectionMap.get("*");
         Set<T> ret = new LinkedHashSet();
         if (artifacts != null) {
            ret.addAll(artifacts);
         }

         if (defArtifacts != null) {
            ret.addAll(defArtifacts);
         }

         return ret;
      } else {
         return Collections.emptySet();
      }
   }

   public DependencyArtifactDescriptor[] getDependencyArtifacts(String[] moduleConfigurations) {
      Set<DependencyArtifactDescriptor> artifacts = new LinkedHashSet();

      for(String moduleConfiguration : moduleConfigurations) {
         artifacts.addAll(Arrays.asList(this.getDependencyArtifacts(moduleConfiguration)));
      }

      return (DependencyArtifactDescriptor[])artifacts.toArray(new DependencyArtifactDescriptor[artifacts.size()]);
   }

   public IncludeRule[] getIncludeRules(String[] moduleConfigurations) {
      Set<IncludeRule> rules = new LinkedHashSet();

      for(String moduleConfiguration : moduleConfigurations) {
         rules.addAll(Arrays.asList(this.getIncludeRules(moduleConfiguration)));
      }

      return (IncludeRule[])rules.toArray(new IncludeRule[rules.size()]);
   }

   public ExcludeRule[] getExcludeRules(String[] moduleConfigurations) {
      Set<ExcludeRule> rules = new LinkedHashSet();

      for(String moduleConfiguration : moduleConfigurations) {
         rules.addAll(Arrays.asList(this.getExcludeRules(moduleConfiguration)));
      }

      return (ExcludeRule[])rules.toArray(new ExcludeRule[rules.size()]);
   }

   public DependencyArtifactDescriptor[] getAllDependencyArtifacts() {
      if (this.dependencyArtifacts == null) {
         return new DependencyArtifactDescriptor[0];
      } else {
         Set<DependencyArtifactDescriptor> ret = this.mergeAll(this.dependencyArtifacts);
         return (DependencyArtifactDescriptor[])ret.toArray(new DependencyArtifactDescriptor[ret.size()]);
      }
   }

   public IncludeRule[] getAllIncludeRules() {
      if (this.includeRules == null) {
         return new IncludeRule[0];
      } else {
         Set<IncludeRule> ret = this.mergeAll(this.includeRules);
         return (IncludeRule[])ret.toArray(new IncludeRule[ret.size()]);
      }
   }

   public ExcludeRule[] getAllExcludeRules() {
      if (this.excludeRules == null) {
         return new ExcludeRule[0];
      } else {
         Set<ExcludeRule> ret = this.mergeAll(this.excludeRules);
         return (ExcludeRule[])ret.toArray(new ExcludeRule[ret.size()]);
      }
   }

   private Set mergeAll(Map artifactsMap) {
      Set<T> ret = new LinkedHashSet();

      for(Collection artifacts : artifactsMap.values()) {
         ret.addAll(artifacts);
      }

      return ret;
   }

   public void addDependencyConfiguration(String masterConf, String depConf) {
      if (this.md != null && !"*".equals(masterConf) && !"%".equals(masterConf)) {
         Configuration config;
         if (masterConf.startsWith("!")) {
            config = this.md.getConfiguration(masterConf.substring(1));
         } else {
            config = this.md.getConfiguration(masterConf);
         }

         if (config == null) {
            throw new IllegalArgumentException("Cannot add dependency '" + this.revId + "' to configuration '" + masterConf + "' of module " + this.md.getModuleRevisionId() + " because this configuration doesn't exist!");
         }

         if (config instanceof ConfigurationGroup) {
            ConfigurationGroup group = (ConfigurationGroup)config;

            for(String member : group.getMembersConfigurationNames()) {
               this.addDependencyConfiguration(member, depConf);
            }

            return;
         }
      }

      List<String> confsList = (List)this.confs.get(masterConf);
      if (confsList == null) {
         confsList = new ArrayList();
         this.confs.put(masterConf, confsList);
      }

      if (!confsList.contains(depConf)) {
         confsList.add(depConf);
      }

   }

   public void addDependencyArtifact(String masterConf, DependencyArtifactDescriptor dad) {
      this.addObjectToConfiguration(masterConf, dad, this.getDependencyArtifacts());
   }

   public void addIncludeRule(String masterConf, IncludeRule rule) {
      this.addObjectToConfiguration(masterConf, rule, this.getIncludeRules());
   }

   public void addExcludeRule(String masterConf, ExcludeRule rule) {
      this.addObjectToConfiguration(masterConf, rule, this.getExcludeRules());
   }

   private void addObjectToConfiguration(String callerConf, Object toAdd, Map confsMap) {
      Collection<T> col = (Collection)confsMap.get(callerConf);
      if (col == null) {
         col = new ArrayList();
         confsMap.put(callerConf, col);
      }

      col.add(toAdd);
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

   public boolean canExclude() {
      return this.excludeRules != null && !this.excludeRules.isEmpty();
   }

   public String toString() {
      return "dependency: " + this.revId + " " + this.confs;
   }

   public boolean isForce() {
      return this.isForce;
   }

   public ModuleRevisionId getParentRevisionId() {
      return this.md != null ? this.md.getResolvedModuleRevisionId() : this.parentId;
   }

   public boolean isChanging() {
      return this.isChanging;
   }

   public boolean isTransitive() {
      return this.isTransitive;
   }

   public Namespace getNamespace() {
      return this.namespace;
   }

   public String getAttribute(String attName) {
      return this.revId.getAttribute(attName);
   }

   public Map getAttributes() {
      return this.revId.getAttributes();
   }

   public String getExtraAttribute(String attName) {
      return this.revId.getExtraAttribute(attName);
   }

   public Map getExtraAttributes() {
      return this.revId.getExtraAttributes();
   }

   public Map getQualifiedExtraAttributes() {
      return this.revId.getQualifiedExtraAttributes();
   }

   public DependencyDescriptor asSystem() {
      return this.asSystem;
   }

   private void setDependencyArtifacts(Map dependencyArtifacts) {
      this.dependencyArtifacts = dependencyArtifacts;
   }

   private Map getDependencyArtifacts() {
      if (this.dependencyArtifacts == null) {
         this.dependencyArtifacts = new LinkedHashMap();
      }

      return this.dependencyArtifacts;
   }

   private void setIncludeRules(Map includeRules) {
      this.includeRules = includeRules;
   }

   private Map getIncludeRules() {
      if (this.includeRules == null) {
         this.includeRules = new LinkedHashMap();
      }

      return this.includeRules;
   }

   private void setExcludeRules(Map excludeRules) {
      this.excludeRules = excludeRules;
   }

   private Map getExcludeRules() {
      if (this.excludeRules == null) {
         this.excludeRules = new LinkedHashMap();
      }

      return this.excludeRules;
   }

   public ModuleRevisionId getSourceModule() {
      return this.sourceModule;
   }

   public DependencyDescriptor clone(ModuleRevisionId revision) {
      return new DefaultDependencyDescriptor(this, revision);
   }
}
