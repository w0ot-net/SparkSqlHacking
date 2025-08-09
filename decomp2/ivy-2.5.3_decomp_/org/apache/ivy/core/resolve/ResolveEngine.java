package org.apache.ivy.core.resolve;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.DefaultResolutionCacheManager;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.event.download.PrepareDownloadEvent;
import org.apache.ivy.core.event.resolve.EndResolveEvent;
import org.apache.ivy.core.event.resolve.StartResolveEvent;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ConfigurationResolveReport;
import org.apache.ivy.core.report.DownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.sort.SortEngine;
import org.apache.ivy.core.sort.SortOptions;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.filter.Filter;

public class ResolveEngine {
   private ResolveEngineSettings settings;
   private EventManager eventManager;
   private SortEngine sortEngine;
   private DependencyResolver dictatorResolver;

   public ResolveEngine(ResolveEngineSettings settings, EventManager eventManager, SortEngine sortEngine) {
      this.settings = settings;
      this.eventManager = eventManager;
      this.sortEngine = sortEngine;
   }

   public DependencyResolver getDictatorResolver() {
      return this.dictatorResolver;
   }

   public void setDictatorResolver(DependencyResolver dictatorResolver) {
      this.dictatorResolver = dictatorResolver;
   }

   public ResolveReport resolve(File ivySource) throws ParseException, IOException {
      return this.resolve(ivySource.toURI().toURL());
   }

   public ResolveReport resolve(URL ivySource) throws ParseException, IOException {
      return this.resolve(ivySource, new ResolveOptions());
   }

   public ResolveReport resolve(ModuleRevisionId mrid, ResolveOptions options, boolean changing) throws ParseException, IOException {
      ResolveOptions optionsToUse = new ResolveOptions(options);
      DefaultModuleDescriptor md;
      if (options.useSpecialConfs()) {
         ResolvedModuleRevision rmr = this.findModule(mrid, new ResolveOptions(options));
         if (rmr == null) {
            Message.verbose("module not found " + mrid);
            optionsToUse.setLog("download-only");
            md = DefaultModuleDescriptor.newCallerInstance(mrid, new String[]{"default"}, options.isTransitive(), changing);
         } else {
            String[] confs = options.getConfs(rmr.getDescriptor());
            md = DefaultModuleDescriptor.newCallerInstance(ModuleRevisionId.newInstance(mrid, rmr.getId().getRevision()), confs, options.isTransitive(), changing);
         }
      } else {
         md = DefaultModuleDescriptor.newCallerInstance(mrid, options.getConfs(), options.isTransitive(), changing);
      }

      return this.resolve((ModuleDescriptor)md, optionsToUse);
   }

   public ResolveReport resolve(URL ivySource, ResolveOptions options) throws ParseException, IOException {
      URLResource res = new URLResource(ivySource);
      ModuleDescriptorParser parser = ModuleDescriptorParserRegistry.getInstance().getParser(res);
      Message.verbose("using " + parser + " to parse " + ivySource);
      ModuleDescriptor md = parser.parseDescriptor(this.settings, ivySource, options.isValidate());
      String revision = options.getRevision();
      if (revision == null && md.getResolvedModuleRevisionId().getRevision() == null) {
         revision = Ivy.getWorkingRevision();
      }

      if (revision != null) {
         md.setResolvedModuleRevisionId(ModuleRevisionId.newInstance(md.getModuleRevisionId(), revision));
      }

      return this.resolve(md, options);
   }

   public ResolveReport resolve(ModuleDescriptor md, ResolveOptions options) throws ParseException, IOException {
      DependencyResolver oldDictator = this.getDictatorResolver();
      IvyContext context = IvyContext.getContext();

      ResolveReport var37;
      try {
         String[] confs = options.getConfs(md);
         options.setConfs(confs);
         if (options.getResolveId() == null) {
            options.setResolveId(ResolveOptions.getDefaultResolveId(md));
         }

         this.eventManager.fireIvyEvent(new StartResolveEvent(md, confs));
         long start = System.currentTimeMillis();
         if ("default".equals(options.getLog())) {
            Message.info(":: resolving dependencies :: " + md.getResolvedModuleRevisionId() + (options.isTransitive() ? "" : " [not transitive]"));
            Message.info("\tconfs: " + Arrays.asList(confs));
         } else {
            Message.verbose(":: resolving dependencies :: " + md.getResolvedModuleRevisionId() + (options.isTransitive() ? "" : " [not transitive]"));
            Message.verbose("\tconfs: " + Arrays.asList(confs));
         }

         Message.verbose("\tvalidate = " + options.isValidate());
         Message.verbose("\trefresh = " + options.isRefresh());
         ResolveReport report = new ResolveReport(md, options.getResolveId());
         ResolveData data = new ResolveData(this, options);
         context.setResolveData(data);
         IvyNode[] dependencies = this.getDependencies(md, options, report);
         report.setDependencies(Arrays.asList(dependencies), options.getArtifactFilter());
         if (options.getCheckIfChanged()) {
            report.checkIfChanged();
         }

         ResolutionCacheManager cacheManager = this.settings.getResolutionCacheManager();
         cacheManager.saveResolvedModuleDescriptor(md);
         File ivyPropertiesInCache = cacheManager.getResolvedIvyPropertiesInCache(md.getResolvedModuleRevisionId());
         if (cacheManager instanceof DefaultResolutionCacheManager) {
            ((DefaultResolutionCacheManager)cacheManager).assertInsideCache(ivyPropertiesInCache);
         }

         Properties props = new Properties();
         if (dependencies.length > 0) {
            Map<ModuleId, ModuleRevisionId> forcedRevisions = new HashMap();

            for(IvyNode dependency : dependencies) {
               if (dependency.getModuleRevision() != null && dependency.getModuleRevision().isForce()) {
                  forcedRevisions.put(dependency.getModuleId(), dependency.getResolvedId());
               }
            }

            IvyNode root = dependencies[0].getRoot();
            Map<ModuleId, IvyNode> topLevelDeps = new HashMap();

            for(IvyNode dependency : dependencies) {
               if (!dependency.hasProblem()) {
                  DependencyDescriptor dd = dependency.getDependencyDescriptor(root);
                  if (dd != null) {
                     ModuleId orgMod = dependency.getModuleId();
                     topLevelDeps.put(orgMod, dependency);
                  }
               }
            }

            for(IvyNode dependency : dependencies) {
               if (!dependency.hasProblem() && !dependency.isCompletelyEvicted()) {
                  DependencyDescriptor dd = dependency.getDependencyDescriptor(root);
                  if (dd == null) {
                     ModuleId mid = dependency.getModuleId();
                     IvyNode tlDep = (IvyNode)topLevelDeps.get(mid);
                     if (tlDep != null) {
                        dd = tlDep.getDependencyDescriptor(root);
                     }
                  }

                  if (dd != null) {
                     ModuleRevisionId depResolvedId = dependency.getResolvedId();
                     ModuleDescriptor depDescriptor = dependency.getDescriptor();
                     ModuleRevisionId depRevisionId = dd.getDependencyRevisionId();
                     ModuleRevisionId forcedRevisionId = (ModuleRevisionId)forcedRevisions.get(dependency.getModuleId());
                     if (dependency.getModuleRevision() != null && dependency.getModuleRevision().isForce() && !depResolvedId.equals(depRevisionId) && !this.settings.getVersionMatcher().isDynamic(depRevisionId)) {
                        depResolvedId = depRevisionId;
                        depDescriptor = null;
                     }

                     if (depResolvedId == null) {
                        throw new NullPointerException("getResolvedId() is null for " + dependency.toString());
                     }

                     if (depRevisionId == null) {
                        throw new NullPointerException("getDependencyRevisionId() is null for " + dd.toString());
                     }

                     String rev = depResolvedId.getRevision();
                     String forcedRev = forcedRevisionId == null ? rev : forcedRevisionId.getRevision();
                     String status = depDescriptor == null ? "?" : depDescriptor.getStatus();
                     Message.debug("storing dependency " + depResolvedId + " in props");
                     props.put(depRevisionId.encodeToString(), rev + " " + status + " " + forcedRev + " " + depResolvedId.getBranch());
                  }
               }
            }
         }

         FileOutputStream out = new FileOutputStream(ivyPropertiesInCache);
         props.store(out, md.getResolvedModuleRevisionId() + " resolved revisions");
         out.close();
         Message.verbose("\tresolved ivy file produced in cache");
         report.setResolveTime(System.currentTimeMillis() - start);
         if (options.isDownload()) {
            Message.verbose(":: downloading artifacts ::");
            DownloadOptions downloadOptions = new DownloadOptions();
            downloadOptions.setLog(options.getLog());
            this.downloadArtifacts(report, options.getArtifactFilter(), downloadOptions);
         }

         if (options.isOutputReport()) {
            this.outputReport(report, cacheManager, options);
         }

         Message.verbose("\tresolve done (" + report.getResolveTime() + "ms resolve - " + report.getDownloadTime() + "ms download)");
         Message.sumupProblems();
         this.eventManager.fireIvyEvent(new EndResolveEvent(md, confs, report));
         var37 = report;
      } catch (RuntimeException ex) {
         Message.debug((Throwable)ex);
         Message.error(ex.getMessage());
         Message.sumupProblems();
         throw ex;
      } finally {
         context.setResolveData((ResolveData)null);
         this.setDictatorResolver(oldDictator);
      }

      return var37;
   }

   public void outputReport(ResolveReport report, ResolutionCacheManager cacheMgr, ResolveOptions options) throws IOException {
      if ("default".equals(options.getLog())) {
         Message.info(":: resolution report :: resolve " + report.getResolveTime() + "ms :: artifacts dl " + report.getDownloadTime() + "ms");
      } else {
         Message.verbose(":: resolution report :: resolve " + report.getResolveTime() + "ms :: artifacts dl " + report.getDownloadTime() + "ms");
      }

      report.setProblemMessages(Message.getProblems());
      report.output(this.settings.getReportOutputters(), cacheMgr, options);
   }

   public void downloadArtifacts(ResolveReport report, Filter artifactFilter, DownloadOptions options) {
      long start = System.currentTimeMillis();
      this.eventManager.fireIvyEvent(new PrepareDownloadEvent((Artifact[])report.getArtifacts().toArray(new Artifact[report.getArtifacts().size()])));
      long totalSize = 0L;

      for(IvyNode dependency : report.getDependencies()) {
         this.checkInterrupted();
         if (!dependency.isCompletelyEvicted() && !dependency.hasProblem() && dependency.getModuleRevision() != null) {
            DependencyResolver resolver = dependency.getModuleRevision().getArtifactResolver();
            Artifact[] selectedArtifacts = dependency.getSelectedArtifacts(artifactFilter);
            DownloadReport dReport = resolver.download(selectedArtifacts, options);

            for(ArtifactDownloadReport adr : dReport.getArtifactsReports()) {
               if (adr.getDownloadStatus() == DownloadStatus.FAILED) {
                  if (adr.getArtifact().getExtraAttribute("ivy:merged") != null) {
                     Message.warn("\tmerged artifact not found: " + adr.getArtifact() + ". It was required in " + adr.getArtifact().getExtraAttribute("ivy:merged"));
                  } else {
                     Message.warn("\t" + adr);
                     resolver.reportFailure(adr.getArtifact());
                  }
               } else if (adr.getDownloadStatus() == DownloadStatus.SUCCESSFUL) {
                  totalSize += adr.getSize();
               }
            }

            for(String dconf : dependency.getRootModuleConfigurations()) {
               if (!dependency.isEvicted(dconf) && !dependency.isBlacklisted(dconf)) {
                  report.getConfigurationReport(dconf).addDependency(dependency, dReport);
               } else {
                  report.getConfigurationReport(dconf).addDependency(dependency);
               }
            }
         }
      }

      report.setDownloadTime(System.currentTimeMillis() - start);
      report.setDownloadSize(totalSize);
   }

   public ArtifactDownloadReport download(Artifact artifact, DownloadOptions options) {
      DependencyResolver resolver = this.settings.getResolver(artifact.getModuleRevisionId());
      DownloadReport r = resolver.download(new Artifact[]{artifact}, options);
      return r.getArtifactReport(artifact);
   }

   public ArtifactOrigin locate(Artifact artifact) {
      DependencyResolver resolver = this.settings.getResolver(artifact.getModuleRevisionId());
      return resolver.locate(artifact);
   }

   public ArtifactDownloadReport download(ArtifactOrigin origin, DownloadOptions options) {
      DependencyResolver resolver = this.settings.getResolver(origin.getArtifact().getModuleRevisionId());
      return resolver.download(origin, options);
   }

   public IvyNode[] getDependencies(URL ivySource, ResolveOptions options) throws ParseException, IOException {
      return this.getDependencies(ModuleDescriptorParserRegistry.getInstance().parseDescriptor(this.settings, ivySource, options.isValidate()), options, (ResolveReport)null);
   }

   public IvyNode[] getDependencies(ModuleDescriptor md, ResolveOptions options, ResolveReport report) {
      if (md == null) {
         throw new NullPointerException("module descriptor must not be null");
      } else {
         String[] confs = options.getConfs(md);
         Collection<String> missingConfs = new ArrayList();

         for(String conf : confs) {
            if (conf == null) {
               throw new NullPointerException("null conf not allowed: confs where: " + Arrays.asList(confs));
            }

            if (md.getConfiguration(conf) == null) {
               missingConfs.add(" '" + conf + "' ");
            }
         }

         if (!missingConfs.isEmpty()) {
            throw new IllegalArgumentException("requested configuration" + (missingConfs.size() > 1 ? "s" : "") + " not found in " + md.getModuleRevisionId() + ": " + missingConfs);
         } else {
            IvyContext context = IvyContext.pushNewCopyContext();

            IvyNode[] var33;
            try {
               options.setConfs(confs);
               Date reportDate = new Date();
               ResolveData data = context.getResolveData();
               if (data == null) {
                  data = new ResolveData(this, options);
                  context.setResolveData(data);
               }

               IvyNode rootNode = new IvyNode(data, md);

               for(String conf : confs) {
                  Message.verbose("resolving dependencies for configuration '" + conf + "'");
                  ConfigurationResolveReport confReport = null;
                  if (report != null) {
                     confReport = report.getConfigurationReport(conf);
                     if (confReport == null) {
                        confReport = new ConfigurationResolveReport(this, md, conf, reportDate, options);
                        report.addReport(conf, confReport);
                     }
                  }

                  data.setReport(confReport);
                  VisitNode root = new VisitNode(data, rootNode, (VisitNode)null, conf, (String)null);
                  root.setRequestedConf(conf);
                  rootNode.updateConfsToFetch(Collections.singleton(conf));
                  boolean fetched = false;

                  while(!fetched) {
                     try {
                        this.fetchDependencies(root, conf, new HashSet(), false);
                        fetched = true;
                     } catch (RestartResolveProcess restart) {
                        Message.verbose("====================================================");
                        Message.verbose("=           RESTARTING RESOLVE PROCESS");
                        Message.verbose("= " + restart.getMessage());
                        Message.verbose("====================================================");
                     }
                  }

                  for(IvyNode dep : data.getNodes()) {
                     dep.clean();
                  }
               }

               Collection<IvyNode> nodes = data.getNodes();
               Collection<IvyNode> dependencies = new LinkedHashSet(nodes.size());

               for(IvyNode node : nodes) {
                  if (node != null && !node.isRoot() && !node.isCompletelyBlacklisted()) {
                     dependencies.add(node);
                  }
               }

               List<IvyNode> sortedDependencies = this.sortEngine.sortNodes(dependencies, SortOptions.SILENT);
               Collections.reverse(sortedDependencies);
               this.handleTransitiveEviction(md, confs, data, sortedDependencies);
               var33 = (IvyNode[])dependencies.toArray(new IvyNode[dependencies.size()]);
            } finally {
               IvyContext.popContext();
            }

            return var33;
         }
      }
   }

   private void handleTransitiveEviction(ModuleDescriptor md, String[] confs, ResolveData data, List sortedDependencies) {
      for(IvyNode node : sortedDependencies) {
         if (!node.isCompletelyEvicted()) {
            for(String conf : confs) {
               IvyNodeCallers.Caller[] callers = node.getCallers(conf);
               if (this.settings.debugConflictResolution()) {
                  Message.debug("checking if " + node.getId() + " is transitively evicted in " + conf);
               }

               boolean allEvicted = callers.length > 0;

               for(IvyNodeCallers.Caller caller : callers) {
                  if (caller.getModuleRevisionId().equals(md.getModuleRevisionId())) {
                     allEvicted = false;
                     break;
                  }

                  IvyNode callerNode = data.getNode(caller.getModuleRevisionId());
                  if (callerNode == null) {
                     Message.warn("ivy internal error: no node found for " + caller.getModuleRevisionId() + ": looked in " + data.getNodeIds() + " and root module id was " + md.getModuleRevisionId());
                  } else {
                     if (!callerNode.isEvicted(conf)) {
                        allEvicted = false;
                        break;
                     }

                     if (this.settings.debugConflictResolution()) {
                        Message.debug("caller " + callerNode.getId() + " of " + node.getId() + " is evicted");
                     }
                  }
               }

               if (allEvicted) {
                  Message.verbose("all callers are evicted for " + node + ": evicting too");
                  node.markEvicted(conf, (IvyNode)null, (ConflictManager)null, (Collection)null);
               } else if (this.settings.debugConflictResolution()) {
                  Message.debug(node.getId() + " isn't transitively evicted, at least one caller was not evicted");
               }
            }
         }
      }

   }

   private void fetchDependencies(VisitNode node, String conf, Set fetchedSet, boolean shouldBePublic) {
      this.checkInterrupted();
      long start = System.currentTimeMillis();
      if (node.getParent() != null) {
         Message.verbose("== resolving dependencies " + node.getParent().getId() + "->" + node.getId() + " [" + node.getParentConf() + "->" + conf + "]");
      } else {
         Message.verbose("== resolving dependencies for " + node.getId() + " [" + conf + "]");
      }

      ResolveData data = node.getNode().getData();
      VisitNode parentVisitNode = data.getCurrentVisitNode();
      data.setCurrentVisitNode(node);
      DependencyDescriptor dd = node.getDependencyDescriptor();
      VersionMatcher versionMatcher = node.getNode().getData().getSettings().getVersionMatcher();
      if (dd != null && (node.getRoot() != node.getParent() || !versionMatcher.isDynamic(dd.getDependencyRevisionId()))) {
         this.resolveConflict(node, conf);
      }

      if (node.loadData(conf, shouldBePublic)) {
         this.resolveConflict(node, conf);
         if (!node.isEvicted() && !node.isCircular()) {
            for(String rconf : node.getRealConfs(conf)) {
               this.doFetchDependencies(node, rconf, fetchedSet);
            }
         }
      } else if (!node.hasProblem() && !node.isEvicted() && !node.isCircular()) {
         for(String rconf : node.getRealConfs(conf)) {
            this.doFetchDependencies(node, rconf, fetchedSet);
         }
      }

      if (node.isEvicted()) {
         IvyNodeEviction.EvictionData ed = node.getEvictedData();
         if (ed.getSelected() != null) {
            for(IvyNode selected : ed.getSelected()) {
               if (!selected.isLoaded()) {
                  selected.updateConfsToFetch(Collections.singleton(conf));
               } else {
                  this.fetchDependencies(node.gotoNode(selected), conf, fetchedSet, true);
               }
            }
         }
      }

      if (this.settings.debugConflictResolution()) {
         Message.debug(node.getId() + " => dependencies resolved in " + conf + " (" + (System.currentTimeMillis() - start) + "ms)");
      }

      data.setCurrentVisitNode(parentVisitNode);
   }

   private void doFetchDependencies(VisitNode node, String conf, Set fetchedSet) {
      Configuration c = node.getConfiguration(conf);
      if (c == null) {
         if (!node.isConfRequiredByMergedUsageOnly(conf)) {
            Message.warn("configuration not found '" + conf + "' in " + node.getResolvedId() + ": ignoring");
            if (node.getParent() != null) {
               Message.warn("it was required from " + node.getParent().getResolvedId());
            }
         }

      } else {
         boolean requestedConfSet = false;
         if (node.getRequestedConf() == null) {
            node.setRequestedConf(conf);
            requestedConfSet = true;
         }

         String[] extendedConfs = c.getExtends();
         if (extendedConfs.length > 0) {
            node.updateConfsToFetch(Arrays.asList(extendedConfs));
         }

         for(String extendedConf : extendedConfs) {
            this.fetchDependencies(node, extendedConf, fetchedSet, false);
         }

         if (!this.isDependenciesFetched(node.getNode(), conf, fetchedSet) && node.isTransitive()) {
            for(VisitNode dep : node.getDependencies(conf)) {
               dep.useRealNode();

               for(String rconf : dep.getRequiredConfigurations(node, conf)) {
                  this.fetchDependencies(dep, rconf, fetchedSet, true);
               }

               if (!dep.isEvicted() && !dep.hasProblem()) {
                  for(String fconf : dep.getConfsToFetch()) {
                     this.fetchDependencies(dep, fconf, fetchedSet, false);
                  }
               }
            }

            this.markDependenciesFetched(node.getNode(), conf, fetchedSet);
         }

         if (requestedConfSet) {
            node.setRequestedConf((String)null);
         }

      }
   }

   private boolean isDependenciesFetched(IvyNode node, String conf, Set fetchedSet) {
      String key = this.getDependenciesFetchedKey(node, conf);
      return fetchedSet.contains(key);
   }

   private void markDependenciesFetched(IvyNode node, String conf, Set fetchedSet) {
      String key = this.getDependenciesFetchedKey(node, conf);
      fetchedSet.add(key);
   }

   private String getDependenciesFetchedKey(IvyNode node, String conf) {
      ModuleRevisionId moduleRevisionId = node.getResolvedId();
      return moduleRevisionId.getOrganisation() + "|" + moduleRevisionId.getName() + "|" + moduleRevisionId.getRevision() + "|" + conf;
   }

   private void resolveConflict(VisitNode node, String conf) {
      this.resolveConflict(node, node.getParent(), conf, Collections.emptySet());
   }

   private boolean resolveConflict(VisitNode node, VisitNode ancestor, String conf, Collection toevict) {
      if (ancestor != null && node != ancestor) {
         if (this.checkConflictSolvedEvicted(node, ancestor)) {
            return true;
         } else {
            boolean debugConflictResolution = this.settings.debugConflictResolution();
            if (this.checkConflictSolvedSelected(node, ancestor)) {
               if (this.resolveConflict(node, ancestor.getParent(), conf, toevict)) {
                  IvyNodeEviction.EvictionData evictionData = node.getEvictionDataInRoot(node.getRootModuleConf(), ancestor);
                  if (evictionData != null) {
                     if (debugConflictResolution) {
                        Message.debug(node + " was previously evicted in root module conf " + node.getRootModuleConf());
                     }

                     node.markEvicted(evictionData);
                     if (debugConflictResolution) {
                        Message.debug("evicting " + node + " by " + evictionData);
                     }
                  }

                  return true;
               } else {
                  return false;
               }
            } else {
               Set<IvyNode> resolvedNodes = ancestor.getNode().getResolvedNodes(node.getModuleId(), node.getRootModuleConf());
               resolvedNodes.addAll(ancestor.getNode().getPendingConflicts(node.getRootModuleConf(), node.getModuleId()));
               Collection<IvyNode> conflicts = this.computeConflicts(node, ancestor, conf, toevict, resolvedNodes);
               ConflictManager conflictManager = null;

               for(VisitNode current : ancestor.getPath()) {
                  ModuleDescriptor descriptor = current.getNode().getDescriptor();
                  if (descriptor == null) {
                     throw new IllegalStateException("impossible to get conflict manager when data has not been loaded. IvyNode = " + current.getNode());
                  }

                  conflictManager = descriptor.getConflictManager(node.getModuleId());
                  if (conflictManager != null) {
                     break;
                  }
               }

               if (conflictManager == null) {
                  conflictManager = this.settings.getConflictManager(node.getModuleId());
               }

               Collection<IvyNode> resolved = this.resolveConflicts(node, ancestor, conflicts, conflictManager);
               if (resolved == null) {
                  if (debugConflictResolution) {
                     Message.debug("impossible to resolve conflicts for " + node + " in " + ancestor + " yet");
                     Message.debug("setting all nodes as pending conflicts for later conflict resolution: " + conflicts);
                  }

                  ancestor.getNode().setPendingConflicts(node.getModuleId(), node.getRootModuleConf(), conflicts);
                  return false;
               } else {
                  if (debugConflictResolution) {
                     Message.debug("selected revisions for " + node + " in " + ancestor + ": " + resolved);
                  }

                  if (resolved.contains(node.getNode())) {
                     resolvedNodes.removeAll(resolved);

                     for(IvyNode te : resolvedNodes) {
                        te.markEvicted(node.getRootModuleConf(), ancestor.getNode(), conflictManager, resolved);
                        if (debugConflictResolution) {
                           Message.debug("evicting " + te + " by " + te.getEvictedData(node.getRootModuleConf()));
                        }
                     }

                     ancestor.getNode().setResolvedNodes(node.getModuleId(), node.getRootModuleConf(), resolved);
                     Collection<IvyNode> evicted = new HashSet(ancestor.getNode().getEvictedNodes(node.getModuleId(), node.getRootModuleConf()));
                     evicted.removeAll(resolved);
                     evicted.addAll(resolvedNodes);
                     ancestor.getNode().setEvictedNodes(node.getModuleId(), node.getRootModuleConf(), evicted);
                     ancestor.getNode().setPendingConflicts(node.getModuleId(), node.getRootModuleConf(), Collections.emptySet());
                     return this.resolveConflict(node, ancestor.getParent(), conf, resolvedNodes);
                  } else {
                     if (resolved.isEmpty() && debugConflictResolution) {
                        Message.verbose("conflict manager '" + conflictManager + "' evicted all revisions among " + conflicts);
                     }

                     Collection<IvyNode> evicted = new HashSet(ancestor.getNode().getEvictedNodes(node.getModuleId(), node.getRootModuleConf()));
                     toevict.removeAll(resolved);
                     evicted.removeAll(resolved);
                     evicted.addAll(toevict);
                     evicted.add(node.getNode());
                     ancestor.getNode().setEvictedNodes(node.getModuleId(), node.getRootModuleConf(), evicted);
                     ancestor.getNode().setPendingConflicts(node.getModuleId(), node.getRootModuleConf(), Collections.emptySet());
                     node.markEvicted(ancestor, conflictManager, resolved);
                     if (debugConflictResolution) {
                        Message.debug("evicting " + node + " by " + node.getEvictedData());
                     }

                     Collection<IvyNode> prevResolved = ancestor.getNode().getResolvedNodes(node.getModuleId(), node.getRootModuleConf());
                     boolean solved = true;
                     if (!prevResolved.equals(resolved)) {
                        ancestor.getNode().setResolvedNodes(node.getModuleId(), node.getRootModuleConf(), resolved);

                        for(IvyNode sel : resolved) {
                           if (!prevResolved.contains(sel)) {
                              solved &= this.resolveConflict(node.gotoNode(sel), ancestor.getParent(), conf, toevict);
                           }
                        }
                     }

                     return solved;
                  }
               }
            }
         }
      } else {
         return true;
      }
   }

   private Collection resolveConflicts(VisitNode node, VisitNode ancestor, Collection conflicts, ConflictManager conflictManager) {
      if (node.getParent() != ancestor && conflictManager == this.settings.getConflictManager(node.getModuleId()) && node.getParent().getNode().getResolvedNodes(node.getModuleId(), node.getRootModuleConf()).equals(conflicts)) {
         if (this.settings.debugConflictResolution()) {
            Message.debug("no new conflicting revisions for " + node + " in " + ancestor + ": " + conflicts);
         }

         return conflicts;
      } else {
         if (this.settings.debugConflictResolution()) {
            Message.debug("found conflicting revisions for " + node + " in " + ancestor + ": " + conflicts);
         }

         return conflictManager.resolveConflicts(ancestor.getNode(), conflicts);
      }
   }

   private Collection computeConflicts(VisitNode node, VisitNode ancestor, String conf, Collection toevict, Collection selectedNodes) {
      Collection<IvyNode> conflicts = new LinkedHashSet();
      conflicts.add(node.getNode());
      boolean evictedInSelected = selectedNodes.removeAll(toevict);
      if (evictedInSelected || selectedNodes.isEmpty() && !node.getParent().getNode().equals(ancestor.getNode())) {
         IvyContext context = IvyContext.getContext();
         ResolveData data = context.getResolveData();
         VisitNode oldVisitNode = data.getCurrentVisitNode();
         data.setCurrentVisitNode(ancestor);

         try {
            for(IvyNode dep : ancestor.getNode().getDependencies(node.getRootModuleConf(), ancestor.getNode().getConfigurations(node.getRootModuleConf()), ancestor.getRequestedConf())) {
               if (dep.getModuleId().equals(node.getModuleId())) {
                  conflicts.add(dep);
               }

               conflicts.addAll(dep.getResolvedNodes(node.getModuleId(), node.getRootModuleConf()));
            }
         } finally {
            data.setCurrentVisitNode(oldVisitNode);
         }
      } else if (selectedNodes.isEmpty()) {
         VisitNode parent = node.getParent();

         for(IvyNode parentDep : parent.getNode().getDependencies(node.getRootModuleConf(), parent.getNode().getConfigurations(node.getRootModuleConf()), parent.getRequestedConf())) {
            if (parentDep.getModuleId().equals(node.getModuleId())) {
               conflicts.add(parentDep);
            }
         }
      } else {
         conflicts.addAll(selectedNodes);
      }

      return conflicts;
   }

   private boolean checkConflictSolvedSelected(VisitNode node, VisitNode ancestor) {
      if (ancestor.getResolvedRevisions(node.getModuleId()).contains(node.getResolvedId())) {
         if (this.settings.debugConflictResolution()) {
            Message.debug("conflict resolution already done for " + node + " in " + ancestor);
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean checkConflictSolvedEvicted(VisitNode node, VisitNode ancestor) {
      if (ancestor.getEvictedRevisions(node.getModuleId()).contains(node.getResolvedId())) {
         if (this.settings.debugConflictResolution()) {
            Message.debug("conflict resolution already done for " + node + " in " + ancestor);
         }

         return true;
      } else {
         return false;
      }
   }

   public ResolvedModuleRevision findModule(ModuleRevisionId id, ResolveOptions options) {
      DependencyResolver r = this.settings.getResolver(id);
      if (r == null) {
         throw new IllegalStateException("no resolver found for " + id.getModuleId());
      } else {
         DefaultModuleDescriptor md = DefaultModuleDescriptor.newCallerInstance(id, new String[]{"*"}, false, false);
         if (options.getResolveId() == null) {
            options.setResolveId(ResolveOptions.getDefaultResolveId((ModuleDescriptor)md));
         }

         try {
            return r.getDependency(new DefaultDependencyDescriptor(id, true), new ResolveData(this, options, new ConfigurationResolveReport(this, md, "default", (Date)null, options)));
         } catch (ParseException e) {
            throw new RuntimeException("problem while parsing repository module descriptor for " + id + ": " + e, e);
         }
      }
   }

   public DependencyDescriptor mediate(DependencyDescriptor dd, ResolveOptions options) {
      if (dd == null) {
         return null;
      } else {
         String resolveMode = options.getResolveMode() == null ? this.settings.getResolveMode(dd.getDependencyId()) : options.getResolveMode();
         return "dynamic".equals(resolveMode) && !dd.getDynamicConstraintDependencyRevisionId().equals(dd.getDependencyRevisionId()) ? dd.clone(ModuleRevisionId.newInstance(dd.getDynamicConstraintDependencyRevisionId(), dd.getDynamicConstraintDependencyRevisionId().getRevision())) : dd;
      }
   }

   public EventManager getEventManager() {
      return this.eventManager;
   }

   public ResolveEngineSettings getSettings() {
      return this.settings;
   }

   public SortEngine getSortEngine() {
      return this.sortEngine;
   }

   private void checkInterrupted() {
      IvyContext.getContext().getIvy().checkInterrupted();
   }
}
