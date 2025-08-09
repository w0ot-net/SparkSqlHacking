package org.apache.ivy.core.resolve;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.event.resolve.EndResolveDependencyEvent;
import org.apache.ivy.core.event.resolve.StartResolveDependencyEvent;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DependencyArtifactDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.IncludeRule;
import org.apache.ivy.core.module.descriptor.MDArtifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ArtifactRevisionId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.matcher.MatcherHelper;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.FilterHelper;

public class IvyNode implements Comparable {
   private static final Pattern FALLBACK_CONF_PATTERN = Pattern.compile("(.+)\\((.*)\\)");
   private ResolveData data;
   private ResolveEngineSettings settings;
   private IvyNodeCallers callers;
   private IvyNodeEviction eviction;
   private IvyNode root;
   private ModuleRevisionId id;
   private Map dds = new HashMap();
   private ModuleDescriptor md;
   private ResolvedModuleRevision module;
   private Exception problem = null;
   private boolean downloaded = false;
   private boolean searched = false;
   private Collection confsToFetch = new HashSet();
   private Collection fetchedConfigurations = new HashSet();
   private Collection loadedRootModuleConfs = new HashSet();
   private IvyNodeUsage usage = new IvyNodeUsage(this);
   private Map mergedUsages = new LinkedHashMap();

   public IvyNode(ResolveData data, IvyNode parent, DependencyDescriptor dd) {
      this.id = dd.getDependencyRevisionId();
      this.dds.put(parent, dd);
      this.root = parent.getRoot();
      this.init(data);
   }

   public IvyNode(ResolveData data, ModuleDescriptor md) {
      this.id = md.getModuleRevisionId();
      this.md = md;
      this.root = this;
      this.init(data);
   }

   private void init(ResolveData data) {
      this.data = data;
      this.settings = data.getSettings();
      this.eviction = new IvyNodeEviction(this);
      this.callers = new IvyNodeCallers(this);
   }

   public boolean loadData(String rootModuleConf, IvyNode parent, String parentConf, String conf, boolean shouldBePublic, IvyNodeUsage usage) {
      Message.debug("loadData of " + this.toString() + " of rootConf=" + rootModuleConf);
      if (!this.isRoot() && this.data.getReport() != null) {
         this.data.getReport().addDependency(this);
      }

      boolean loaded = false;
      if (this.hasProblem()) {
         Message.debug("Node has problem.  Skip loading");
      } else if (this.isEvicted(rootModuleConf)) {
         Message.debug(rootModuleConf + " is evicted.  Skip loading");
      } else if (!this.hasConfigurationsToLoad() && this.isRootModuleConfLoaded(rootModuleConf)) {
         Message.debug(rootModuleConf + " is loaded and no conf to load.  Skip loading");
      } else {
         this.markRootModuleConfLoaded(rootModuleConf);
         if (this.md == null) {
            ResolveEngine engine = IvyContext.getContext().getIvy().getResolveEngine();
            DependencyResolver resolver = engine.getDictatorResolver();
            if (resolver == null) {
               resolver = this.data.getSettings().getResolver(this.getId());
            }

            if (resolver == null) {
               Message.error("no resolver found for " + this.getModuleId() + ": check your configuration");
               this.problem = new RuntimeException("no resolver found for " + this.getModuleId() + ": check your configuration");
               return false;
            }

            try {
               Message.debug("\tusing " + resolver + " to resolve " + this.getId());
               DependencyDescriptor dependencyDescriptor = this.getDependencyDescriptor(parent);
               long start = System.currentTimeMillis();
               ModuleRevisionId requestedRevisionId = dependencyDescriptor.getDependencyRevisionId();
               this.data.getEventManager().fireIvyEvent(new StartResolveDependencyEvent(resolver, dependencyDescriptor, requestedRevisionId));
               this.module = resolver.getDependency(dependencyDescriptor, this.data);
               this.data.getEventManager().fireIvyEvent(new EndResolveDependencyEvent(resolver, dependencyDescriptor, requestedRevisionId, this.module, System.currentTimeMillis() - start));
               if (this.module == null) {
                  Message.warn("\tmodule not found: " + this.getId());
                  resolver.reportFailure();
                  this.problem = new RuntimeException("not found");
                  return false;
               }

               this.module.getResolver().getRepositoryCacheManager().saveResolvers(this.module.getDescriptor(), this.module.getResolver().getName(), this.module.getArtifactResolver().getName());
               if (this.settings.logModuleWhenFound() && "default".equals(this.getData().getOptions().getLog())) {
                  Message.info("\tfound " + this.module.getId() + " in " + this.module.getResolver().getName());
               } else {
                  Message.verbose("\tfound " + this.module.getId() + " in " + this.module.getResolver().getName());
               }

               if (this.settings.getVersionMatcher().isDynamic(this.getId()) && this.settings.getVersionMatcher().isDynamic(this.module.getId())) {
                  Message.error("impossible to resolve dynamic revision for " + this.getId() + ": check your configuration and make sure revision is part of your pattern");
                  this.problem = new RuntimeException("impossible to resolve dynamic revision");
                  return false;
               }

               if (!this.getId().equals(this.module.getId())) {
                  IvyNode resolved = this.data.getNode(this.module.getId());
                  if (resolved != null) {
                     this.md = this.module.getDescriptor();
                     if (!this.handleConfiguration(loaded, rootModuleConf, parent, parentConf, conf, shouldBePublic, usage)) {
                        return false;
                     }

                     this.moveToRealNode(rootModuleConf, parent, parentConf, conf, shouldBePublic, resolved);
                     return true;
                  }

                  String log = "\t[" + this.module.getId().getRevision() + "] " + this.getId();
                  if (!this.settings.getVersionMatcher().isDynamic(this.getId())) {
                     log = log + " (forced)";
                  }

                  if (this.settings.logResolvedRevision() && "default".equals(this.getData().getOptions().getLog())) {
                     Message.info(log);
                  } else {
                     Message.verbose(log);
                  }
               }

               this.downloaded = this.module.getReport().isDownloaded();
               this.searched = this.module.getReport().isSearched();
               loaded = true;
               this.md = this.module.getDescriptor();
               this.confsToFetch.remove("*");
               this.updateConfsToFetch(Arrays.asList(this.resolveSpecialConfigurations(this.getRequiredConfigurations(parent, parentConf))));
            } catch (ResolveProcessException e) {
               throw e;
            } catch (Exception e) {
               this.problem = e;
               Message.debug("Unexpected error: " + this.problem.getMessage(), this.problem);
               return false;
            }
         } else {
            loaded = true;
         }
      }

      this.handleConfiguration(loaded, rootModuleConf, parent, parentConf, conf, shouldBePublic, usage);
      if (this.hasProblem()) {
         Message.debug("problem : " + this.problem.getMessage());
         return false;
      } else {
         DependencyDescriptor dd = this.getDependencyDescriptor(parent);
         if (dd != null) {
            usage.addUsage(rootModuleConf, dd, parentConf);
         }

         return loaded;
      }
   }

   private void moveToRealNode(String rootModuleConf, IvyNode parent, String parentConf, String conf, boolean shouldBePublic, IvyNode resolved) {
      if (resolved.md == null) {
         resolved.md = this.md;
      }

      if (resolved.module == null) {
         resolved.module = this.module;
      }

      resolved.downloaded |= this.module.getReport().isDownloaded();
      resolved.searched |= this.module.getReport().isSearched();
      resolved.dds.putAll(this.dds);
      resolved.updateDataFrom(this, rootModuleConf, true);
      resolved.loadData(rootModuleConf, parent, parentConf, conf, shouldBePublic, this.usage);
      resolved.usage.updateDataFrom(this.getAllUsages(), rootModuleConf);
      this.usage = resolved.usage;
      this.data.replaceNode(this.getId(), resolved, rootModuleConf);
      if (this.settings.logResolvedRevision() && "default".equals(this.getData().getOptions().getLog())) {
         Message.info("\t[" + this.module.getId().getRevision() + "] " + this.getId());
      } else {
         Message.verbose("\t[" + this.module.getId().getRevision() + "] " + this.getId());
      }

   }

   public Collection getDependencies(String rootModuleConf, String[] confs, String requestedConf) {
      if (this.md == null) {
         throw new IllegalStateException("impossible to get dependencies when data has not been loaded");
      } else {
         if (Arrays.asList(confs).contains("*")) {
            if (this.isRoot()) {
               confs = this.md.getConfigurationsNames();
            } else {
               confs = this.md.getPublicConfigurationsNames();
            }
         }

         Collection<IvyNode> deps = new HashSet();

         for(String conf : confs) {
            deps.addAll(this.getDependencies(rootModuleConf, conf, requestedConf));
         }

         return deps;
      }
   }

   public Collection getDependencies(String rootModuleConf, String conf, String requestedConf) {
      if (this.md == null) {
         throw new IllegalStateException("impossible to get dependencies when data has not been loaded");
      } else {
         Map<ModuleRevisionId, IvyNode> dependencies = new LinkedHashMap();

         for(DependencyDescriptor dependencyDescriptor : this.md.getDependencies()) {
            DependencyDescriptor dd = this.data.mediate(dependencyDescriptor);
            String[] dependencyConfigurations = dd.getDependencyConfigurations(conf, requestedConf);
            if (dependencyConfigurations.length != 0) {
               ModuleRevisionId requestedDependencyRevisionId = dd.getDependencyRevisionId();
               if (this.isDependencyModuleExcluded(dd, rootModuleConf, requestedDependencyRevisionId, conf)) {
                  Message.verbose("excluding " + dd + " in " + conf);
               } else {
                  IvyNode depNode = (IvyNode)dependencies.get(requestedDependencyRevisionId);
                  if (depNode == null) {
                     depNode = this.data.getNode(requestedDependencyRevisionId);
                  }

                  if (depNode == null) {
                     depNode = new IvyNode(this.data, this, dd);
                  } else {
                     depNode.addDependencyDescriptor(this, dd);
                     if (depNode.hasProblem()) {
                     }
                  }

                  String[] confsArray = depNode.resolveSpecialConfigurations(dependencyConfigurations);
                  Collection<String> confs = Arrays.asList(confsArray);
                  depNode.updateConfsToFetch(confs);
                  depNode.addRootModuleConfigurations(depNode.usage, rootModuleConf, confsArray);
                  depNode.usage.setRequiredConfs(this, conf, confs);
                  depNode.addCaller(rootModuleConf, this, conf, requestedConf, dependencyConfigurations, dd);
                  dependencies.put(requestedDependencyRevisionId, depNode);
               }
            }
         }

         return dependencies.values();
      }
   }

   private void addDependencyDescriptor(IvyNode parent, DependencyDescriptor dd) {
      this.dds.put(parent, dd);
   }

   public DependencyDescriptor getDependencyDescriptor(IvyNode parent) {
      return (DependencyDescriptor)this.dds.get(parent);
   }

   private boolean isDependencyModuleExcluded(DependencyDescriptor dd, String rootModuleConf, ModuleRevisionId dependencyRevisionId, String conf) {
      Artifact a = DefaultArtifact.newIvyArtifact(dependencyRevisionId, (Date)null);
      if (!this.isRoot()) {
         return this.callers.doesCallersExclude(rootModuleConf, a);
      } else {
         Boolean exclude = this.doesExclude(this.md, rootModuleConf, new String[]{rootModuleConf}, dd, a, new ArrayDeque());
         return exclude != null && exclude;
      }
   }

   Boolean doesExclude(ModuleDescriptor md, String rootModuleConf, String[] moduleConfs, DependencyDescriptor dd, Artifact artifact, Deque callersStack) {
      if (this.directlyExcludes(md, moduleConfs, dd, artifact)) {
         return Boolean.TRUE;
      } else {
         IvyNode c = this.getData().getNode(md.getModuleRevisionId());
         if (c != null) {
            return callersStack.contains(c) ? null : c.doesCallersExclude(rootModuleConf, artifact, callersStack);
         } else {
            return Boolean.FALSE;
         }
      }
   }

   public boolean directlyExcludes(ModuleDescriptor md, String[] moduleConfs, DependencyDescriptor dd, Artifact artifact) {
      return dd != null && dd.doesExclude(moduleConfs, artifact.getId().getArtifactId()) || md.doesExclude(moduleConfs, artifact.getId().getArtifactId());
   }

   public boolean hasConfigurationsToLoad() {
      return !this.confsToFetch.isEmpty();
   }

   private boolean markRootModuleConfLoaded(String rootModuleConf) {
      return this.loadedRootModuleConfs.add(rootModuleConf);
   }

   private boolean isRootModuleConfLoaded(String rootModuleConf) {
      return this.loadedRootModuleConfs.contains(rootModuleConf);
   }

   private boolean handleConfiguration(boolean loaded, String rootModuleConf, IvyNode parent, String parentConf, String conf, boolean shouldBePublic, IvyNodeUsage usage) {
      if (this.md != null) {
         String[] confs = this.getRealConfs(conf);
         this.addRootModuleConfigurations(usage, rootModuleConf, confs);

         for(String realConf : confs) {
            Configuration c = this.md.getConfiguration(realConf);
            if (c == null) {
               this.confsToFetch.remove(conf);
               if (this.isConfRequiredByMergedUsageOnly(rootModuleConf, conf)) {
                  Message.verbose("configuration required by evicted revision is not available in selected revision. skipping " + conf + " in " + this);
               } else if (!conf.equals(realConf)) {
                  this.problem = new RuntimeException("configuration not found in " + this + ": '" + conf + "'. Missing configuration: '" + realConf + "'. It was required from " + parent + " " + parentConf);
               } else {
                  this.problem = new RuntimeException("configuration not found in " + this + ": '" + realConf + "'. It was required from " + parent + " " + parentConf);
               }

               return false;
            }

            if (shouldBePublic && !this.isRoot() && !Configuration.Visibility.PUBLIC.equals(c.getVisibility())) {
               this.confsToFetch.remove(conf);
               if (this.isConfRequiredByMergedUsageOnly(rootModuleConf, conf)) {
                  Message.verbose("configuration required by evicted revision is not visible in selected revision. skipping " + conf + " in " + this);
               } else {
                  this.problem = new RuntimeException("configuration not public in " + this + ": '" + c + "'. It was required from " + parent + " " + parentConf);
               }

               return false;
            }
         }

         if (loaded) {
            this.fetchedConfigurations.add(conf);
            this.confsToFetch.removeAll(Arrays.asList(confs));
            this.confsToFetch.remove(conf);
         }
      }

      return true;
   }

   private String getDefaultConf(String conf) {
      Matcher m = FALLBACK_CONF_PATTERN.matcher(conf);
      return m.matches() ? m.group(2) : conf;
   }

   private String getMainConf(String conf) {
      Matcher m = FALLBACK_CONF_PATTERN.matcher(conf);
      return m.matches() ? m.group(1) : null;
   }

   public void updateConfsToFetch(Collection confs) {
      this.confsToFetch.addAll(confs);
      this.confsToFetch.removeAll(this.fetchedConfigurations);
   }

   private String[] resolveSpecialConfigurations(String[] dependencyConfigurations) {
      if (dependencyConfigurations.length == 1 && dependencyConfigurations[0].startsWith("*") && this.isLoaded()) {
         String conf = dependencyConfigurations[0];
         if ("*".equals(conf)) {
            return this.getDescriptor().getPublicConfigurationsNames();
         } else {
            List<String> exclusions = Arrays.asList(conf.substring(2).split("\\!"));
            List<String> ret = new ArrayList(Arrays.asList(this.getDescriptor().getPublicConfigurationsNames()));
            ret.removeAll(exclusions);
            return (String[])ret.toArray(new String[ret.size()]);
         }
      } else {
         return dependencyConfigurations;
      }
   }

   public String[] getRequiredConfigurations(IvyNode in, String inConf) {
      Collection<String> req = new LinkedHashSet();
      this.addAllIfNotNull(req, this.usage.getRequiredConfigurations(in, inConf));

      for(IvyNodeUsage usage : this.mergedUsages.values()) {
         this.addAllIfNotNull(req, usage.getRequiredConfigurations(in, inConf));
      }

      return (String[])req.toArray(new String[req.size()]);
   }

   private void addAllIfNotNull(Collection into, Collection col) {
      if (col != null) {
         into.addAll(col);
      }

   }

   public String[] getRequiredConfigurations() {
      Collection<String> required = new ArrayList(this.confsToFetch.size() + this.fetchedConfigurations.size());
      required.addAll(this.fetchedConfigurations);
      required.addAll(this.confsToFetch);
      return (String[])required.toArray(new String[required.size()]);
   }

   public Configuration getConfiguration(String conf) {
      if (this.md == null) {
         throw new IllegalStateException("impossible to get configuration when data has not been loaded");
      } else {
         String defaultConf = this.getDefaultConf(conf);
         conf = this.getMainConf(conf);
         Configuration configuration = this.md.getConfiguration(conf);
         if (configuration == null) {
            configuration = this.md.getConfiguration(defaultConf);
         }

         return configuration;
      }
   }

   public String[] getConfigurations(String rootModuleConf) {
      Set<String> depConfs = new LinkedHashSet();
      this.addAllIfNotNull(depConfs, this.usage.getConfigurations(rootModuleConf));

      for(IvyNodeUsage usage : this.mergedUsages.values()) {
         this.addAllIfNotNull(depConfs, usage.getConfigurations(rootModuleConf));
      }

      return (String[])depConfs.toArray(new String[depConfs.size()]);
   }

   protected boolean isConfRequiredByMergedUsageOnly(String rootModuleConf, String conf) {
      Set<String> confs = this.usage.getConfigurations(rootModuleConf);
      return confs == null || !confs.contains(conf);
   }

   /** @deprecated */
   @Deprecated
   public void discardConf(String rootModuleConf, String conf) {
      Set<String> depConfs = this.usage.addAndGetConfigurations(rootModuleConf);
      if (this.md == null) {
         depConfs.remove(conf);
      } else {
         Configuration c = this.md.getConfiguration(conf);
         if (conf == null) {
            Message.warn("unknown configuration in " + this.getId() + ": " + conf);
         } else {
            for(String ext : c.getExtends()) {
               this.discardConf(rootModuleConf, ext);
            }

            depConfs.remove(c.getName());
         }
      }

   }

   private void addRootModuleConfigurations(IvyNodeUsage usage, String rootModuleConf, String[] dependencyConfs) {
      if (this.md != null) {
         for(String dependencyConf : dependencyConfs) {
            Configuration conf = this.md.getConfiguration(dependencyConf);
            if (conf != null) {
               this.addRootModuleConfigurations(usage, rootModuleConf, conf.getExtends());
            }
         }
      }

      Collections.addAll(usage.addAndGetConfigurations(rootModuleConf), dependencyConfs);
   }

   public String[] getRootModuleConfigurations() {
      Set<String> confs = this.getRootModuleConfigurationsSet();
      return (String[])confs.toArray(new String[confs.size()]);
   }

   public Set getRootModuleConfigurationsSet() {
      Set<String> confs = new LinkedHashSet();
      this.addAllIfNotNull(confs, this.usage.getRootModuleConfigurations());

      for(IvyNodeUsage usage : this.mergedUsages.values()) {
         this.addAllIfNotNull(confs, usage.getRootModuleConfigurations());
      }

      return confs;
   }

   public String[] getConfsToFetch() {
      return (String[])this.confsToFetch.toArray(new String[this.confsToFetch.size()]);
   }

   public String[] getRealConfs(String conf) {
      if (this.md == null) {
         return new String[]{conf};
      } else {
         String defaultConf = this.getDefaultConf(conf);
         conf = this.getMainConf(conf);
         if (this.md.getConfiguration(conf) == null || Configuration.Visibility.PRIVATE.equals(this.md.getConfiguration(conf).getVisibility())) {
            if ("".equals(defaultConf)) {
               return new String[0];
            }

            conf = defaultConf;
         }

         if (conf.charAt(0) == '*') {
            return this.resolveSpecialConfigurations(new String[]{conf});
         } else {
            return conf.contains(",") ? StringUtils.splitToArray(conf) : new String[]{conf};
         }
      }
   }

   private Collection findPath(ModuleId from) {
      return this.findPath(from, this, new LinkedList());
   }

   private Collection findPath(ModuleId from, IvyNode node, List path) {
      IvyNode parent = node.getDirectCallerFor(from);
      if (parent == null) {
         throw new IllegalArgumentException("no path from " + from + " to " + this.getId() + " found");
      } else if (path.contains(parent)) {
         path.add(0, parent);
         Message.verbose("circular dependency found while looking for the path for another one: was looking for " + from + " as a caller of " + path.get(path.size() - 1));
         return path;
      } else {
         path.add(0, parent);
         return (Collection)(parent.getId().getModuleId().equals(from) ? path : this.findPath(from, parent, path));
      }
   }

   private void updateDataFrom(IvyNode node, String rootModuleConf, boolean real) {
      this.callers.updateFrom(node.callers, rootModuleConf, real);
      if (real) {
         this.usage.updateDataFrom(node.getAllUsages(), rootModuleConf);
      } else {
         IvyNodeUsage mergedUsage = (IvyNodeUsage)this.mergedUsages.get(node.getId());
         if (mergedUsage == null) {
            mergedUsage = new IvyNodeUsage(node);
            this.mergedUsages.put(node.getId(), mergedUsage);
         }

         mergedUsage.updateDataFrom(node.getAllUsages(), rootModuleConf);
      }

      this.updateConfsToFetch(node.fetchedConfigurations);
      this.updateConfsToFetch(node.confsToFetch);
   }

   private Collection getAllUsages() {
      Collection<IvyNodeUsage> usages = new ArrayList();
      usages.add(this.usage);
      usages.addAll(this.mergedUsages.values());
      return usages;
   }

   public Artifact[] getAllArtifacts() {
      Set<Artifact> ret = new HashSet();

      for(String rootModuleConf : this.getRootModuleConfigurationsSet()) {
         ret.addAll(Arrays.asList(this.getArtifacts(rootModuleConf)));
      }

      return (Artifact[])ret.toArray(new Artifact[ret.size()]);
   }

   public Artifact[] getSelectedArtifacts(Filter artifactFilter) {
      Collection<Artifact> ret = new HashSet();

      for(String rootModuleConf : this.getRootModuleConfigurationsSet()) {
         if (!this.isEvicted(rootModuleConf) && !this.isBlacklisted(rootModuleConf)) {
            ret.addAll(Arrays.asList(this.getArtifacts(rootModuleConf)));
         }
      }

      ret = FilterHelper.filter(ret, artifactFilter);
      return (Artifact[])ret.toArray(new Artifact[ret.size()]);
   }

   public Artifact[] getArtifacts(String rootModuleConf) {
      String[] confs = this.getConfigurations(rootModuleConf);
      if (confs != null && confs.length != 0) {
         if (this.md == null) {
            throw new IllegalStateException("impossible to get artifacts when data has not been loaded. IvyNode = " + this);
         } else {
            Set<Artifact> artifacts = new HashSet();
            Set<DependencyArtifactDescriptor> dependencyArtifacts = this.usage.getDependencyArtifactsSet(rootModuleConf);
            if (this.md.isDefault() && dependencyArtifacts != null && !dependencyArtifacts.isEmpty()) {
               this.addArtifactsFromOwnUsage(artifacts, dependencyArtifacts);
               this.addArtifactsFromMergedUsage(rootModuleConf, artifacts);
            } else {
               Set<IncludeRule> includes = new LinkedHashSet();
               this.addAllIfNotNull(includes, this.usage.getDependencyIncludesSet(rootModuleConf));

               for(IvyNodeUsage usage : this.mergedUsages.values()) {
                  this.addAllIfNotNull(includes, usage.getDependencyIncludesSet(rootModuleConf));
               }

               if ((dependencyArtifacts == null || dependencyArtifacts.isEmpty()) && includes.isEmpty()) {
                  for(String conf : confs) {
                     artifacts.addAll(Arrays.asList(this.md.getArtifacts(conf)));
                  }
               } else {
                  Map<ArtifactId, Artifact> allArtifacts = new HashMap();

                  for(String conf : confs) {
                     for(Artifact art : this.md.getArtifacts(conf)) {
                        allArtifacts.put(art.getId().getArtifactId(), art);
                     }
                  }

                  if (dependencyArtifacts != null) {
                     this.addArtifactsFromOwnUsage(artifacts, dependencyArtifacts);
                  }

                  this.addArtifactsFromMergedUsage(rootModuleConf, artifacts);
                  Iterator<IncludeRule> it = includes.iterator();

                  while(it.hasNext()) {
                     IncludeRule dad = (IncludeRule)it.next();
                     Collection<Artifact> arts = findArtifactsMatching(dad, allArtifacts);
                     if (arts.isEmpty()) {
                        Message.error("a required artifact is not listed by module descriptor: " + dad.getId());
                        it.remove();
                     } else {
                        Message.debug(this + " in " + rootModuleConf + ": including " + arts);
                        artifacts.addAll(arts);
                     }
                  }
               }
            }

            Iterator<Artifact> iter = artifacts.iterator();
            Set<ArtifactRevisionId> artifactRevisionsSeen = new HashSet();

            while(iter.hasNext()) {
               Artifact artifact = (Artifact)iter.next();
               boolean excluded = this.callers.doesCallersExclude(rootModuleConf, artifact);
               if (excluded) {
                  Message.debug(this + " in " + rootModuleConf + ": excluding " + artifact);
                  iter.remove();
               }

               if (!artifactRevisionsSeen.add(artifact.getId())) {
                  Message.debug(this + " in " + rootModuleConf + ": skipping duplicate " + artifact);
                  iter.remove();
               }
            }

            return (Artifact[])artifacts.toArray(new Artifact[artifacts.size()]);
         }
      } else {
         return new Artifact[0];
      }
   }

   private void addArtifactsFromOwnUsage(Set artifacts, Set dependencyArtifacts) {
      for(DependencyArtifactDescriptor dad : dependencyArtifacts) {
         artifacts.add(new MDArtifact(this.md, dad.getName(), dad.getType(), dad.getExt(), dad.getUrl(), dad.getQualifiedExtraAttributes()));
      }

   }

   private void addArtifactsFromMergedUsage(String rootModuleConf, Set artifacts) {
      for(IvyNodeUsage usage : this.mergedUsages.values()) {
         Set<DependencyArtifactDescriptor> mergedDependencyArtifacts = usage.getDependencyArtifactsSet(rootModuleConf);
         if (mergedDependencyArtifacts != null) {
            for(DependencyArtifactDescriptor dad : mergedDependencyArtifacts) {
               Map<String, String> extraAttributes = new HashMap(dad.getQualifiedExtraAttributes());
               MDArtifact artifact = new MDArtifact(this.md, dad.getName(), dad.getType(), dad.getExt(), dad.getUrl(), extraAttributes);
               if (!artifacts.contains(artifact)) {
                  extraAttributes.put("ivy:merged", dad.getDependencyDescriptor().getParentRevisionId() + " -> " + usage.getNode().getId());
                  artifacts.add(artifact);
               }
            }
         }
      }

   }

   private static Collection findArtifactsMatching(IncludeRule rule, Map allArtifacts) {
      Collection<Artifact> ret = new ArrayList();

      for(Map.Entry entry : allArtifacts.entrySet()) {
         if (MatcherHelper.matches(rule.getMatcher(), rule.getId(), (ArtifactId)entry.getKey())) {
            ret.add(entry.getValue());
         }
      }

      return ret;
   }

   public boolean hasProblem() {
      return this.problem != null;
   }

   public Exception getProblem() {
      return this.problem;
   }

   public String getProblemMessage() {
      return StringUtils.getErrorMessage(this.problem);
   }

   public boolean isDownloaded() {
      return this.downloaded;
   }

   public boolean isSearched() {
      return this.searched;
   }

   public boolean isLoaded() {
      return this.md != null;
   }

   public boolean isFetched(String conf) {
      return this.fetchedConfigurations.contains(conf);
   }

   public IvyNode findNode(ModuleRevisionId mrid) {
      return this.data.getNode(mrid);
   }

   boolean isRoot() {
      return this.root == this;
   }

   public IvyNode getRoot() {
      return this.root;
   }

   public ConflictManager getConflictManager(ModuleId mid) {
      if (this.md == null) {
         throw new IllegalStateException("impossible to get conflict manager when data has not been loaded. IvyNode = " + this);
      } else {
         ConflictManager cm = this.md.getConflictManager(mid);
         return cm == null ? this.settings.getConflictManager(mid) : cm;
      }
   }

   public IvyNode getRealNode() {
      IvyNode real = this.data.getNode(this.getId());
      return real != null ? real : this;
   }

   public ModuleRevisionId getId() {
      return this.id;
   }

   public ModuleId getModuleId() {
      return this.id.getModuleId();
   }

   public ModuleDescriptor getDescriptor() {
      return this.md;
   }

   public ResolveData getData() {
      return this.data;
   }

   public ResolvedModuleRevision getModuleRevision() {
      return this.module;
   }

   public long getPublication() {
      return this.module != null ? this.module.getPublicationDate().getTime() : 0L;
   }

   public long getLastModified() {
      return this.md != null ? this.md.getLastModified() : 0L;
   }

   public ModuleRevisionId getResolvedId() {
      if (this.md != null && this.md.getResolvedModuleRevisionId().getRevision() != null) {
         return this.md.getResolvedModuleRevisionId();
      } else {
         return this.module != null ? this.module.getId() : this.getId();
      }
   }

   public void clean() {
      this.confsToFetch.clear();
   }

   boolean canExclude(String rootModuleConf) {
      for(IvyNodeCallers.Caller caller : this.getCallers(rootModuleConf)) {
         if (caller.canExclude()) {
            return true;
         }
      }

      return false;
   }

   private IvyNode getDirectCallerFor(ModuleId from) {
      return this.callers.getDirectCallerFor(from);
   }

   public IvyNodeCallers.Caller[] getCallers(String rootModuleConf) {
      return this.callers.getCallers(rootModuleConf);
   }

   public Collection getAllCallersModuleIds() {
      return this.callers.getAllCallersModuleIds();
   }

   public IvyNodeCallers.Caller[] getAllCallers() {
      return this.callers.getAllCallers();
   }

   public IvyNodeCallers.Caller[] getAllRealCallers() {
      return this.callers.getAllRealCallers();
   }

   public void addCaller(String rootModuleConf, IvyNode callerNode, String callerConf, String requestedConf, String[] dependencyConfs, DependencyDescriptor dd) {
      this.callers.addCaller(rootModuleConf, callerNode, callerConf, requestedConf, dependencyConfs, dd);
      boolean isCircular = this.callers.getAllCallersModuleIds().contains(this.getId().getModuleId());
      if (isCircular) {
         IvyContext.getContext().getCircularDependencyStrategy().handleCircularDependency(this.toMrids(this.findPath(this.getId().getModuleId()), this));
      }

   }

   public boolean doesCallersExclude(String rootModuleConf, Artifact artifact, Deque callersStack) {
      return this.callers.doesCallersExclude(rootModuleConf, artifact, callersStack);
   }

   /** @deprecated */
   @Deprecated
   public boolean doesCallersExclude(String rootModuleConf, Artifact artifact, Stack callersStack) {
      Deque<IvyNode> callersDeque = new ArrayDeque();

      for(ModuleRevisionId mrid : callersStack) {
         for(IvyNodeCallers.Caller caller : this.getCallers(rootModuleConf)) {
            if (caller.getModuleRevisionId().equals(mrid)) {
               callersDeque.add(this.data.getNode(mrid));
            }
         }
      }

      return this.callers.doesCallersExclude(rootModuleConf, artifact, callersDeque);
   }

   private ModuleRevisionId[] toMrids(Collection path, IvyNode depNode) {
      ModuleRevisionId[] ret = new ModuleRevisionId[path.size() + 1];
      int i = 0;

      for(IvyNode node : path) {
         ret[i++] = node.getId();
      }

      ret[ret.length - 1] = depNode.getId();
      return ret;
   }

   public Set getResolvedNodes(ModuleId moduleId, String rootModuleConf) {
      return this.eviction.getResolvedNodes(moduleId, rootModuleConf);
   }

   public Collection getResolvedRevisions(ModuleId moduleId, String rootModuleConf) {
      return this.eviction.getResolvedRevisions(moduleId, rootModuleConf);
   }

   public void markEvicted(IvyNodeEviction.EvictionData evictionData) {
      this.eviction.markEvicted(evictionData);
      String rootModuleConf = evictionData.getRootModuleConf();
      if (evictionData.getSelected() != null) {
         for(IvyNode selected : evictionData.getSelected()) {
            selected.updateDataFrom(this, rootModuleConf, false);
         }
      }

   }

   public Collection getAllEvictingConflictManagers() {
      return this.eviction.getAllEvictingConflictManagers();
   }

   public Collection getAllEvictingNodes() {
      return this.eviction.getAllEvictingNodes();
   }

   public Collection getAllEvictingNodesDetails() {
      return this.eviction.getAllEvictingNodesDetails();
   }

   public String[] getEvictedConfs() {
      return this.eviction.getEvictedConfs();
   }

   public IvyNodeEviction.EvictionData getEvictedData(String rootModuleConf) {
      return this.eviction.getEvictedData(rootModuleConf);
   }

   public Collection getEvictedNodes(ModuleId mid, String rootModuleConf) {
      return this.eviction.getEvictedNodes(mid, rootModuleConf);
   }

   public Collection getEvictedRevisions(ModuleId mid, String rootModuleConf) {
      return this.eviction.getEvictedRevisions(mid, rootModuleConf);
   }

   public IvyNodeEviction.EvictionData getEvictionDataInRoot(String rootModuleConf, IvyNode ancestor) {
      return this.eviction.getEvictionDataInRoot(rootModuleConf, ancestor);
   }

   public boolean isCompletelyEvicted() {
      return this.eviction.isCompletelyEvicted();
   }

   public boolean isEvicted(String rootModuleConf) {
      return this.eviction.isEvicted(rootModuleConf);
   }

   public void markEvicted(String rootModuleConf, IvyNode node, ConflictManager conflictManager, Collection resolved) {
      IvyNodeEviction.EvictionData evictionData = new IvyNodeEviction.EvictionData(rootModuleConf, node, conflictManager, resolved);
      this.markEvicted(evictionData);
   }

   public void setEvictedNodes(ModuleId moduleId, String rootModuleConf, Collection evicted) {
      this.eviction.setEvictedNodes(moduleId, rootModuleConf, evicted);
   }

   public void setResolvedNodes(ModuleId moduleId, String rootModuleConf, Collection resolved) {
      this.eviction.setResolvedNodes(moduleId, rootModuleConf, resolved);
   }

   public String toString() {
      return this.getResolvedId().toString();
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof IvyNode)) {
         return false;
      } else {
         IvyNode node = (IvyNode)obj;
         return node.getId().equals(this.getId());
      }
   }

   public int compareTo(IvyNode that) {
      return this.getModuleId().compareTo(that.getModuleId());
   }

   public int hashCode() {
      return this.getId().hashCode();
   }

   public Collection getPendingConflicts(String rootModuleConf, ModuleId mid) {
      return this.eviction.getPendingConflicts(rootModuleConf, mid);
   }

   public void setPendingConflicts(ModuleId moduleId, String rootModuleConf, Collection conflicts) {
      this.eviction.setPendingConflicts(moduleId, rootModuleConf, conflicts);
   }

   public void blacklist(IvyNodeBlacklist bdata) {
      if (this.data.getSettings().logResolvedRevision()) {
         Message.info("BLACKLISTING " + bdata);
      } else {
         Message.verbose("BLACKLISTING " + bdata);
      }

      Stack<IvyNode> callerStack = new Stack();
      callerStack.push(this);
      this.clearEvictionDataInAllCallers(bdata.getRootModuleConf(), callerStack);
      this.usage.blacklist(bdata);
      this.data.blacklist(this);
   }

   private void clearEvictionDataInAllCallers(String rootModuleConf, Stack callerStack) {
      for(IvyNodeCallers.Caller caller : ((IvyNode)callerStack.peek()).getCallers(rootModuleConf)) {
         IvyNode callerNode = this.findNode(caller.getModuleRevisionId());
         if (callerNode != null) {
            callerNode.eviction = new IvyNodeEviction(callerNode);
            if (!callerStack.contains(callerNode)) {
               callerStack.push(callerNode);
               this.clearEvictionDataInAllCallers(rootModuleConf, callerStack);
               callerStack.pop();
            }
         }
      }

   }

   public boolean isBlacklisted(String rootModuleConf) {
      return this.usage.isBlacklisted(rootModuleConf);
   }

   public boolean isCompletelyBlacklisted() {
      if (this.isRoot()) {
         return false;
      } else {
         for(String rootModuleConfiguration : this.getRootModuleConfigurations()) {
            if (!this.isBlacklisted(rootModuleConfiguration)) {
               return false;
            }
         }

         return true;
      }
   }

   public IvyNodeBlacklist getBlacklistData(String rootModuleConf) {
      return this.usage.getBlacklistData(rootModuleConf);
   }

   public IvyNodeUsage getMainUsage() {
      return this.usage;
   }

   public boolean hasAnyMergedUsageWithTransitiveDependency(String rootModuleConf) {
      if (this.mergedUsages == null) {
         return false;
      } else {
         for(IvyNodeUsage usage : this.mergedUsages.values()) {
            if (usage.hasTransitiveDepender(rootModuleConf)) {
               return true;
            }
         }

         return false;
      }
   }
}
