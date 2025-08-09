package org.apache.ivy.osgi.repo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.osgi.core.BundleInfoAdapter;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.url.URLRepository;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.plugins.resolver.BasicResolver;
import org.apache.ivy.plugins.resolver.util.MDResolvedResource;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.plugins.resolver.util.ResourceMDParser;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public abstract class AbstractOSGiResolver extends BasicResolver {
   private static final String CAPABILITY_EXTRA_ATTR = "osgi_bundle";
   protected static final RepoDescriptor FAILING_REPO_DESCRIPTOR = new EditableRepoDescriptor((URI)null, (ExecutionEnvironmentProfileProvider)null);
   private RepoDescriptor repoDescriptor = null;
   private URLRepository repository = new URLRepository();
   private RequirementStrategy requirementStrategy;

   public AbstractOSGiResolver() {
      this.requirementStrategy = AbstractOSGiResolver.RequirementStrategy.noambiguity;
   }

   public void setRequirementStrategy(RequirementStrategy importPackageStrategy) {
      this.requirementStrategy = importPackageStrategy;
   }

   public void setRequirementStrategy(String strategy) {
      this.setRequirementStrategy(AbstractOSGiResolver.RequirementStrategy.valueOf(strategy));
   }

   protected void setRepoDescriptor(RepoDescriptor repoDescriptor) {
      this.repoDescriptor = repoDescriptor;
   }

   public URLRepository getRepository() {
      return this.repository;
   }

   protected void ensureInit() {
      if (this.repoDescriptor == null) {
         try {
            this.init();
         } catch (Exception e) {
            this.repoDescriptor = FAILING_REPO_DESCRIPTOR;
            throw new RuntimeException("Error while loading the OSGi repo descriptor" + e.getMessage() + " (" + e.getClass().getName() + ")", e);
         }
      } else if (this.repoDescriptor == FAILING_REPO_DESCRIPTOR) {
         throw new RuntimeException("The repository " + this.getName() + " already failed to load");
      }

   }

   protected abstract void init();

   public RepoDescriptor getRepoDescriptor() {
      this.ensureInit();
      return this.repoDescriptor;
   }

   public boolean isAllownomd() {
      return false;
   }

   public ResolvedResource findIvyFileRef(DependencyDescriptor dd, ResolveData data) {
      ModuleRevisionId mrid = dd.getDependencyRevisionId();
      String osgiType = mrid.getOrganisation();
      if (osgiType == null) {
         throw new RuntimeException("Unsupported OSGi module Id: " + mrid.getModuleId());
      } else {
         String id = mrid.getName();
         Collection<ModuleDescriptor> mds = ModuleDescriptorWrapper.unwrap(this.getRepoDescriptor().findModules(osgiType, id));
         if (mds != null && !mds.isEmpty()) {
            ResolvedResource[] ret;
            if ("bundle".equals(osgiType)) {
               ret = this.findBundle(dd, data, mds);
            } else {
               ret = this.findCapability(dd, data, mds);
            }

            ResolvedResource found = this.findResource(ret, this.getDefaultRMDParser(dd.getDependencyId()), mrid, data.getDate());
            if (found == null) {
               Message.debug("\t" + this.getName() + ": no resource found for " + mrid);
            }

            return found;
         } else {
            Message.verbose("\t " + id + " not found.");
            return null;
         }
      }
   }

   public ResolvedResource[] findBundle(DependencyDescriptor dd, ResolveData data, Collection mds) {
      ResolvedResource[] ret = new ResolvedResource[mds.size()];
      int i = 0;

      for(ModuleDescriptor md : mds) {
         MetadataArtifactDownloadReport report = new MetadataArtifactDownloadReport((Artifact)null);
         report.setDownloadStatus(DownloadStatus.NO);
         report.setSearched(true);
         ResolvedModuleRevision rmr = new ResolvedModuleRevision(this, this, md, report);
         MDResolvedResource mdrr = new MDResolvedResource((Resource)null, md.getRevision(), rmr);
         ret[i++] = mdrr;
      }

      return ret;
   }

   public ResolvedResource[] findCapability(DependencyDescriptor dd, ResolveData data, Collection mds) {
      List<ResolvedResource> ret = new ArrayList(mds.size());

      for(ModuleDescriptor md : mds) {
         IvyNode node = data.getNode(md.getModuleRevisionId());
         if (node != null && node.getDescriptor() != null) {
            return new ResolvedResource[]{this.buildResolvedCapabilityMd(dd, node.getDescriptor())};
         }

         ret.add(this.buildResolvedCapabilityMd(dd, md));
      }

      return (ResolvedResource[])ret.toArray(new ResolvedResource[mds.size()]);
   }

   private MDResolvedResource buildResolvedCapabilityMd(DependencyDescriptor dd, ModuleDescriptor md) {
      String org = dd.getDependencyRevisionId().getOrganisation();
      String name = dd.getDependencyRevisionId().getName();
      String rev = md.getExtraInfoContentByTagName("_osgi_export_" + name);
      ModuleRevisionId capabilityRev = ModuleRevisionId.newInstance(org, name, rev, Collections.singletonMap("osgi_bundle", md.getModuleRevisionId().toString()));
      DefaultModuleDescriptor capabilityMd = new DefaultModuleDescriptor(capabilityRev, this.getSettings().getStatusManager().getDefaultStatus(), new Date());
      String useConf = "use_" + dd.getDependencyRevisionId().getName();
      capabilityMd.addConfiguration(BundleInfoAdapter.CONF_DEFAULT);
      capabilityMd.addConfiguration(BundleInfoAdapter.CONF_OPTIONAL);
      capabilityMd.addConfiguration(BundleInfoAdapter.CONF_TRANSITIVE_OPTIONAL);
      capabilityMd.addConfiguration(new Configuration(useConf));
      DefaultDependencyDescriptor capabilityDD = new DefaultDependencyDescriptor(md.getModuleRevisionId(), false);
      capabilityDD.addDependencyConfiguration("default", "default");
      capabilityDD.addDependencyConfiguration("optional", "optional");
      capabilityDD.addDependencyConfiguration("transitive-optional", "transitive-optional");
      capabilityDD.addDependencyConfiguration(useConf, useConf);
      capabilityMd.addDependency(capabilityDD);
      MetadataArtifactDownloadReport report = new MetadataArtifactDownloadReport((Artifact)null);
      report.setDownloadStatus(DownloadStatus.NO);
      report.setSearched(true);
      ResolvedModuleRevision rmr = new ResolvedModuleRevision(this, this, capabilityMd, report);
      return new MDResolvedResource((Resource)null, capabilityMd.getRevision(), rmr);
   }

   public ResolvedResource findResource(ResolvedResource[] rress, ResourceMDParser rmdparser, ModuleRevisionId mrid, Date date) {
      ResolvedResource found = super.findResource(rress, rmdparser, mrid, date);
      if (found == null) {
         return null;
      } else {
         String osgiType = mrid.getOrganisation();
         if (!"bundle".equals(osgiType)) {
            if (rress.length != 1) {
               Map<String, List<MDResolvedResource>> matching = new HashMap();

               for(ResolvedResource rres : rress) {
                  String name = ((MDResolvedResource)rres).getResolvedModuleRevision().getDescriptor().getExtraAttribute("osgi_bundle");
                  List<MDResolvedResource> list = (List)matching.get(name);
                  if (list == null) {
                     list = new ArrayList();
                     matching.put(name, list);
                  }

                  list.add((MDResolvedResource)rres);
               }

               if (matching.keySet().size() != 1) {
                  if (this.requirementStrategy != AbstractOSGiResolver.RequirementStrategy.first) {
                     if (this.requirementStrategy == AbstractOSGiResolver.RequirementStrategy.noambiguity) {
                        Message.error("Ambiguity for the '" + osgiType + "' requirement " + mrid.getName() + ";version=" + mrid.getRevision());

                        for(Map.Entry entry : matching.entrySet()) {
                           Message.error("\t" + (String)entry.getKey());

                           for(MDResolvedResource c : (List)entry.getValue()) {
                              Message.error("\t\t" + c.getRevision() + (found == c ? " (best match)" : ""));
                           }
                        }

                        return null;
                     }
                  } else {
                     Message.warn("Ambiguity for the '" + osgiType + "' requirement " + mrid.getName() + ";version=" + mrid.getRevision());

                     for(Map.Entry entry : matching.entrySet()) {
                        Message.warn("\t" + (String)entry.getKey());

                        for(MDResolvedResource c : (List)entry.getValue()) {
                           Message.warn("\t\t" + c.getRevision() + (found == c ? " (selected)" : ""));
                        }
                     }
                  }
               }
            }

            Message.info("'" + osgiType + "' requirement " + mrid.getName() + ";version=" + mrid.getRevision() + " satisfied by " + ((MDResolvedResource)found).getResolvedModuleRevision().getId().getName() + ";" + found.getRevision());
         }

         return found;
      }
   }

   public ResolvedResource findArtifactRef(Artifact artifact, Date date) {
      URL url = artifact.getUrl();
      if (url == null) {
         return null;
      } else {
         Message.verbose("\tusing url for " + artifact + ": " + url);
         this.logArtifactAttempt(artifact, url.toExternalForm());
         Resource resource = new URLResource(url, this.getTimeoutConstraint());
         return new ResolvedResource(resource, artifact.getModuleRevisionId().getRevision());
      }
   }

   protected void checkModuleDescriptorRevision(ModuleDescriptor systemMd, ModuleRevisionId systemMrid) {
      String osgiType = systemMrid.getOrganisation();
      if (osgiType == null || osgiType.equals("bundle")) {
         super.checkModuleDescriptorRevision(systemMd, systemMrid);
      }

   }

   protected Collection filterNames(Collection names) {
      this.getSettings().filterIgnore(names);
      return names;
   }

   protected Collection findNames(Map tokenValues, String token) {
      if ("organisation".equals(token)) {
         return this.getRepoDescriptor().getCapabilities();
      } else {
         String osgiType = (String)tokenValues.get("organisation");
         if (StringUtils.isNullOrEmpty(osgiType)) {
            return Collections.emptyList();
         } else if ("module".equals(token)) {
            return this.getRepoDescriptor().getCapabilityValues(osgiType);
         } else if (!"revision".equals(token)) {
            if ("conf".equals(token)) {
               String name = (String)tokenValues.get("module");
               if (name == null) {
                  return Collections.emptyList();
               } else if (osgiType.equals("package")) {
                  return Collections.singletonList("use_" + name);
               } else {
                  Collection<ModuleDescriptor> mds = ModuleDescriptorWrapper.unwrap(this.getRepoDescriptor().findModules(osgiType, name));
                  if (mds == null) {
                     return Collections.emptyList();
                  } else {
                     String version = (String)tokenValues.get("revision");
                     if (version == null) {
                        return Collections.emptyList();
                     } else {
                        ModuleDescriptor found = null;

                        for(ModuleDescriptor md : mds) {
                           if (md.getRevision().equals(version)) {
                              found = md;
                           }
                        }

                        if (found == null) {
                           return Collections.emptyList();
                        } else {
                           return Arrays.asList(found.getConfigurationsNames());
                        }
                     }
                  }
               }
            } else {
               return Collections.emptyList();
            }
         } else {
            String name = (String)tokenValues.get("module");
            List<String> versions = new ArrayList();
            Set<ModuleDescriptorWrapper> mds = this.getRepoDescriptor().findModules(osgiType, name);
            if (mds != null) {
               for(ModuleDescriptorWrapper md : mds) {
                  versions.add(md.getBundleInfo().getVersion().toString());
               }
            }

            return versions;
         }
      }
   }

   private void filterCapabilityValues(Set capabilityValues, Map moduleByCapabilityValue, Map tokenValues, String rev) {
      if (rev == null) {
         capabilityValues.addAll(moduleByCapabilityValue.keySet());
      } else {
         for(Map.Entry entry : moduleByCapabilityValue.entrySet()) {
            boolean moduleMatchRev = false;

            for(ModuleDescriptor md : (Set)entry.getValue()) {
               moduleMatchRev = rev.equals(md.getRevision());
               if (moduleMatchRev) {
                  break;
               }
            }

            if (moduleMatchRev) {
               capabilityValues.add(entry.getKey());
            }
         }
      }

   }

   public Map[] listTokenValues(String[] tokens, Map criteria) {
      Set<String> tokenSet = new HashSet(Arrays.asList(tokens));
      Set<Map<String, String>> listTokenValues = this.listTokenValues(tokenSet, criteria);
      return (Map[])listTokenValues.toArray(new Map[listTokenValues.size()]);
   }

   private Set listTokenValues(Set tokens, Map criteria) {
      Map<String, String> stringCriteria = new HashMap();

      for(Map.Entry entry : criteria.entrySet()) {
         Object value = entry.getValue();
         if (!(value instanceof String)) {
            return Collections.emptySet();
         }

         stringCriteria.put(entry.getKey(), (String)value);
      }

      if (tokens.isEmpty()) {
         return Collections.singleton(stringCriteria);
      } else {
         Set<String> remainingTokens = new HashSet(tokens);
         remainingTokens.remove("organisation");
         String osgiType = (String)stringCriteria.get("organisation");
         if (osgiType == null) {
            Map<String, Object> newCriteria = new HashMap(criteria);
            newCriteria.put("organisation", "bundle");
            Set<Map<String, String>> tokenValues = new HashSet(this.listTokenValues(remainingTokens, newCriteria));
            newCriteria = new HashMap(criteria);
            newCriteria.put("organisation", "package");
            tokenValues.addAll(this.listTokenValues(remainingTokens, newCriteria));
            newCriteria = new HashMap(criteria);
            newCriteria.put("organisation", "service");
            tokenValues.addAll(this.listTokenValues(remainingTokens, newCriteria));
            return tokenValues;
         } else {
            Map<String, String> values = new HashMap();
            values.put("organisation", osgiType);
            Set<String> capabilities = this.getRepoDescriptor().getCapabilityValues(osgiType);
            if (capabilities != null && !capabilities.isEmpty()) {
               remainingTokens.remove("module");
               String module = (String)stringCriteria.get("module");
               if (module == null) {
                  Set<Map<String, String>> tokenValues = new HashSet();

                  for(String name : capabilities) {
                     Map<String, Object> newCriteria = new HashMap(criteria);
                     newCriteria.put("module", name);
                     tokenValues.addAll(this.listTokenValues(remainingTokens, newCriteria));
                  }

                  return tokenValues;
               } else {
                  values.put("module", module);
                  remainingTokens.remove("revision");
                  String rev = (String)stringCriteria.get("revision");
                  if (rev == null) {
                     Set<ModuleDescriptorWrapper> mdws = this.getRepoDescriptor().findModules(osgiType, module);
                     if (mdws != null && !mdws.isEmpty()) {
                        Set<Map<String, String>> tokenValues = new HashSet();

                        for(ModuleDescriptorWrapper mdw : mdws) {
                           Map<String, Object> newCriteria = new HashMap(criteria);
                           newCriteria.put("revision", mdw.getBundleInfo().getVersion().toString());
                           tokenValues.addAll(this.listTokenValues(remainingTokens, newCriteria));
                        }

                        return tokenValues;
                     } else {
                        return Collections.emptySet();
                     }
                  } else {
                     values.put("revision", rev);
                     remainingTokens.remove("conf");
                     String conf = (String)stringCriteria.get("conf");
                     if (conf != null) {
                        values.put("conf", conf);
                        return Collections.singleton(values);
                     } else if (osgiType.equals("package")) {
                        values.put("conf", "use_" + module);
                        return Collections.singleton(values);
                     } else {
                        Set<ModuleDescriptorWrapper> bundles = this.getRepoDescriptor().findModules(osgiType, module);
                        if (bundles == null) {
                           return Collections.emptySet();
                        } else {
                           Version v = new Version(rev);
                           ModuleDescriptorWrapper found = null;

                           for(ModuleDescriptorWrapper bundle : bundles) {
                              if (bundle.getBundleInfo().getVersion().equals(v)) {
                                 found = bundle;
                              }
                           }

                           if (found == null) {
                              return Collections.emptySet();
                           } else {
                              Set<Map<String, String>> tokenValues = new HashSet();

                              for(String configuration : BundleInfoAdapter.getConfigurations(found.getBundleInfo())) {
                                 Map<String, String> newCriteria = new HashMap(stringCriteria);
                                 newCriteria.put("conf", configuration);
                                 tokenValues.add(newCriteria);
                              }

                              return tokenValues;
                           }
                        }
                     }
                  }
               }
            } else {
               return Collections.emptySet();
            }
         }
      }
   }

   protected long get(Resource resource, File dest) throws IOException {
      Message.verbose("\t" + this.getName() + ": downloading " + resource.getName());
      Message.debug("\t\tto " + dest);
      if (dest.getParentFile() != null) {
         dest.getParentFile().mkdirs();
      }

      this.getRepository().get(resource.getName(), dest);
      return dest.length();
   }

   protected Resource getResource(String source) throws IOException {
      return this.getRepository().getResource(source);
   }

   public void publish(Artifact artifact, File src, boolean overwrite) throws IOException {
      throw new UnsupportedOperationException();
   }

   public static class RequirementStrategy {
      public static RequirementStrategy first = new RequirementStrategy();
      public static RequirementStrategy noambiguity = new RequirementStrategy();

      public static RequirementStrategy valueOf(String strategy) {
         if (strategy.equals("first")) {
            return first;
         } else if (strategy.equals("noambiguity")) {
            return noambiguity;
         } else {
            throw new IllegalStateException();
         }
      }
   }
}
