package org.apache.ivy.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.core.sort.SortOptions;
import org.apache.ivy.plugins.matcher.MapMatcher;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.util.Message;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

public class IvyBuildList extends IvyTask {
   public static final String DESCRIPTOR_REQUIRED = "required";
   private List buildFileSets = new ArrayList();
   private String reference;
   private boolean haltOnError = true;
   private String onMissingDescriptor = "head";
   private boolean reverse = false;
   private String ivyFilePath;
   private String root = "*";
   private List roots = new ArrayList();
   private boolean excludeRoot = false;
   private String leaf = "*";
   private List leafs = new ArrayList();
   private String delimiter = ",";
   private boolean excludeLeaf = false;
   private boolean onlydirectdep = false;
   private String restartFrom = "*";

   public void addFileset(FileSet buildFiles) {
      this.buildFileSets.add(buildFiles);
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

   public String getRoot() {
      return this.root;
   }

   public void setRoot(String root) {
      this.root = root;
   }

   public BuildListModule createRoot() {
      BuildListModule root = new BuildListModule();
      this.roots.add(root);
      return root;
   }

   public boolean isExcludeRoot() {
      return this.excludeRoot;
   }

   public void setExcludeRoot(boolean root) {
      this.excludeRoot = root;
   }

   public String getLeaf() {
      return this.leaf;
   }

   public void setLeaf(String leaf) {
      this.leaf = leaf;
   }

   public BuildListModule createLeaf() {
      BuildListModule leaf = new BuildListModule();
      this.leafs.add(leaf);
      return leaf;
   }

   public boolean isExcludeLeaf() {
      return this.excludeLeaf;
   }

   public void setExcludeLeaf(boolean excludeLeaf) {
      this.excludeLeaf = excludeLeaf;
   }

   public String getDelimiter() {
      return this.delimiter;
   }

   public void setDelimiter(String delimiter) {
      this.delimiter = delimiter;
   }

   public boolean getOnlydirectdep() {
      return this.onlydirectdep;
   }

   public void setOnlydirectdep(boolean onlydirectdep) {
      this.onlydirectdep = onlydirectdep;
   }

   public void doExecute() throws BuildException {
      if (this.reference == null) {
         throw new BuildException("reference should be provided in ivy build list");
      } else if (this.buildFileSets.isEmpty()) {
         throw new BuildException("at least one nested fileset should be provided in ivy build list");
      } else {
         Ivy ivy = this.getIvyInstance();
         IvySettings settings = ivy.getSettings();
         this.ivyFilePath = this.getProperty(this.ivyFilePath, settings, "ivy.buildlist.ivyfilepath");
         Path path = new Path(this.getProject());
         Map<ModuleDescriptor, File> buildFiles = new HashMap();
         List<File> independent = new ArrayList();
         List<File> noDescriptor = new ArrayList();
         Collection<ModuleDescriptor> mds = new ArrayList();
         Set<MapMatcher> rootModules = this.convert(this.roots, this.root, settings);
         Set<MapMatcher> leafModules = this.convert(this.leafs, this.leaf, settings);
         Set<MapMatcher> restartFromModules = this.convert(Collections.emptyList(), this.restartFrom, settings);

         for(FileSet fs : this.buildFileSets) {
            DirectoryScanner ds = fs.getDirectoryScanner(this.getProject());

            for(String build : ds.getIncludedFiles()) {
               File buildFile = new File(ds.getBasedir(), build);
               File ivyFile = this.getIvyFileFor(buildFile);
               if (!ivyFile.exists()) {
                  this.onMissingDescriptor(buildFile, ivyFile, noDescriptor);
               } else {
                  try {
                     ModuleDescriptor md = ModuleDescriptorParserRegistry.getInstance().parseDescriptor(settings, ivyFile.toURI().toURL(), this.doValidate(settings));
                     buildFiles.put(md, buildFile);
                     mds.add(md);
                     Message.debug("Add " + md.getModuleRevisionId().getModuleId());
                  } catch (Exception ex) {
                     if (this.haltOnError) {
                        throw new BuildException("impossible to parse ivy file for " + buildFile + ": ivyfile=" + ivyFile + " exception=" + ex, ex);
                     }

                     Message.warn("impossible to parse ivy file for " + buildFile + ": ivyfile=" + ivyFile + " exception=" + ex.getMessage());
                     Message.info("\t=> adding it at the beginning of the path");
                     independent.add(buildFile);
                  }
               }
            }
         }

         List<ModuleDescriptor> leafModuleDescriptors = this.findModuleDescriptors(mds, leafModules, "leaf");
         List<ModuleDescriptor> rootModuleDescriptors = this.findModuleDescriptors(mds, rootModules, "root");
         List<ModuleDescriptor> restartFromModuleDescriptors = this.findModuleDescriptors(mds, restartFromModules, "restartFrom");
         if (!rootModuleDescriptors.isEmpty()) {
            Message.info("Filtering modules based on roots [" + this.extractModuleNames(rootModules) + "]");
            mds = this.filterModulesFromRoot(mds, rootModuleDescriptors);
         }

         if (!leafModuleDescriptors.isEmpty()) {
            Message.info("Filtering modules based on leafs [" + this.extractModuleNames(leafModules) + "]");
            mds = this.filterModulesFromLeaf(mds, leafModuleDescriptors);
         }

         List<ModuleDescriptor> sortedModules = ivy.sortModuleDescriptors(mds, SortOptions.DEFAULT);
         if (!"tail".equals(this.onMissingDescriptor)) {
            for(File buildFile : noDescriptor) {
               this.addBuildFile(path, buildFile);
            }
         }

         for(File buildFile : independent) {
            this.addBuildFile(path, buildFile);
         }

         if (this.isReverse()) {
            Collections.reverse(sortedModules);
         }

         if (!restartFromModuleDescriptors.isEmpty()) {
            boolean foundRestartFrom = false;
            List<ModuleDescriptor> keptModules = new ArrayList();
            ModuleDescriptor restartFromModuleDescriptor = (ModuleDescriptor)restartFromModuleDescriptors.get(0);

            for(ModuleDescriptor md : sortedModules) {
               if (md.equals(restartFromModuleDescriptor)) {
                  foundRestartFrom = true;
               }

               if (foundRestartFrom) {
                  keptModules.add(md);
               }
            }

            sortedModules = keptModules;
         }

         StringBuilder order = new StringBuilder();

         for(ModuleDescriptor md : sortedModules) {
            if (order.length() > 0) {
               order.append(", ");
            }

            order.append(md.getModuleRevisionId().getModuleId());
            this.addBuildFile(path, (File)buildFiles.get(md));
         }

         if ("tail".equals(this.onMissingDescriptor)) {
            for(File buildFile : noDescriptor) {
               this.addBuildFile(path, buildFile);
            }
         }

         this.getProject().addReference(this.getReference(), path);
         this.getProject().setProperty("ivy.sorted.modules", order.toString());
      }
   }

   private Set convert(List modulesList, String modulesString, IvySettings settings) {
      Set<MapMatcher> result = new LinkedHashSet();

      for(BuildListModule module : modulesList) {
         File ivyFile = module.getFile();
         if (ivyFile == null) {
            String org = module.getOrganisation();
            String name = module.getModule();
            String rev = module.getRevision();
            String branch = module.getBranch();
            Map<String, String> attributes = new HashMap();
            attributes.put("organisation", org == null ? "*" : org);
            attributes.put("module", name == null ? "*" : name);
            attributes.put("module", rev == null ? "*" : rev);
            attributes.put("module", branch == null ? "*" : branch);
            result.add(new MapMatcher(attributes, settings.getMatcher("exact")));
         } else {
            try {
               ModuleDescriptor md = ModuleDescriptorParserRegistry.getInstance().parseDescriptor(settings, ivyFile.toURI().toURL(), this.doValidate(settings));
               Map<String, String> attributes = new HashMap();
               attributes.putAll(md.getModuleRevisionId().getAttributes());
               attributes.put("resource", md.getResource().getName());
               result.add(new MapMatcher(attributes, settings.getMatcher("exact")));
            } catch (Exception e) {
               throw new BuildException(e);
            }
         }
      }

      if (!"*".equals(modulesString)) {
         StringTokenizer st = new StringTokenizer(modulesString, this.getDelimiter());

         while(st.hasMoreTokens()) {
            Map<String, String> attributes = new HashMap();
            attributes.put("module", st.nextToken());
            result.add(new MapMatcher(attributes, settings.getMatcher("exact")));
         }
      }

      return result;
   }

   private void onMissingDescriptor(File buildFile, File ivyFile, List noDescriptor) {
      switch (this.onMissingDescriptor) {
         case "fail":
            throw new BuildException("a module has no module descriptor and onMissingDescriptor=fail. Build file: " + buildFile + ". Expected descriptor: " + ivyFile);
         case "skip":
            Message.debug("skipping " + buildFile + ": descriptor " + ivyFile + " doesn't exist");
            break;
         case "warn":
            Message.warn("a module has no module descriptor. Build file: " + buildFile + ". Expected descriptor: " + ivyFile);
         default:
            Message.verbose(String.format("no descriptor for %s: descriptor=%s: adding it at the %s of the path", buildFile, ivyFile, "tail".equals(this.onMissingDescriptor) ? "tail" : "head"));
            Message.verbose("\t(change onMissingDescriptor if you want to take another action");
            noDescriptor.add(buildFile);
      }

   }

   private List findModuleDescriptors(Collection mds, Set matchers, String kind) {
      List<ModuleDescriptor> result = new ArrayList();
      Set<MapMatcher> missingMatchers = new HashSet(matchers);

      for(ModuleDescriptor md : mds) {
         Map<String, String> attributes = new HashMap();
         attributes.putAll(md.getAttributes());
         attributes.put("resource", md.getResource().getName());

         for(MapMatcher matcher : matchers) {
            if (matcher.matches(attributes)) {
               missingMatchers.remove(matcher);
               result.add(md);
            }
         }
      }

      if (!missingMatchers.isEmpty()) {
         throw new BuildException("unable to find " + kind + " module(s) " + this.extractModuleNames(missingMatchers) + " in build fileset");
      } else {
         return result;
      }
   }

   private String extractModuleNames(Set matchers) {
      StringBuilder result = new StringBuilder();
      String sep = "";

      for(MapMatcher matcher : matchers) {
         result.append(sep);
         Map<String, String> attributes = matcher.getAttributes();
         String organisation = (String)attributes.get("organisation");
         if (organisation != null && !"*".equals(organisation)) {
            result.append(organisation);
            result.append('#');
         }

         result.append((String)attributes.get("module"));
         sep = ", ";
      }

      return result.toString();
   }

   private Collection filterModulesFromRoot(Collection mds, List rootmds) {
      Map<ModuleId, ModuleDescriptor> moduleIdMap = new HashMap();

      for(ModuleDescriptor md : mds) {
         moduleIdMap.put(md.getModuleRevisionId().getModuleId(), md);
      }

      Set<ModuleDescriptor> toKeep = new LinkedHashSet();

      for(ModuleDescriptor rootmd : rootmds) {
         this.processFilterNodeFromRoot(rootmd, toKeep, moduleIdMap);
         if (this.excludeRoot) {
            Message.verbose("Excluded module " + rootmd.getModuleRevisionId().getModuleId().getName());
         } else {
            toKeep.add(rootmd);
         }
      }

      for(ModuleDescriptor md : toKeep) {
         Message.verbose("Kept module " + md.getModuleRevisionId().getModuleId().getName());
      }

      return toKeep;
   }

   private void processFilterNodeFromRoot(ModuleDescriptor node, Set toKeep, Map moduleIdMap) {
      for(DependencyDescriptor dep : node.getDependencies()) {
         ModuleId id = dep.getDependencyId();
         ModuleDescriptor md = (ModuleDescriptor)moduleIdMap.get(id);
         if (md != null && !toKeep.contains(md)) {
            toKeep.add(md);
            if (!this.getOnlydirectdep()) {
               this.processFilterNodeFromRoot(md, toKeep, moduleIdMap);
            }
         }
      }

   }

   private Collection filterModulesFromLeaf(Collection mds, List leafmds) {
      Map<ModuleId, ModuleDescriptor> moduleIdMap = new HashMap();

      for(ModuleDescriptor md : mds) {
         moduleIdMap.put(md.getModuleRevisionId().getModuleId(), md);
      }

      Set<ModuleDescriptor> toKeep = new LinkedHashSet();

      for(ModuleDescriptor leafmd : leafmds) {
         if (this.excludeLeaf) {
            Message.verbose("Excluded module " + leafmd.getModuleRevisionId().getModuleId().getName());
         } else {
            toKeep.add(leafmd);
         }

         this.processFilterNodeFromLeaf(leafmd, toKeep, moduleIdMap);
      }

      for(ModuleDescriptor md : toKeep) {
         Message.verbose("Kept module " + md.getModuleRevisionId().getModuleId().getName());
      }

      return toKeep;
   }

   private void processFilterNodeFromLeaf(ModuleDescriptor node, Set toKeep, Map moduleIdMap) {
      for(ModuleDescriptor md : moduleIdMap.values()) {
         for(DependencyDescriptor dep : md.getDependencies()) {
            if (node.getModuleRevisionId().getModuleId().equals(dep.getDependencyId()) && !toKeep.contains(md)) {
               toKeep.add(md);
               if (!this.getOnlydirectdep()) {
                  this.processFilterNodeFromLeaf(md, toKeep, moduleIdMap);
               }
            }
         }
      }

   }

   private void addBuildFile(Path path, File buildFile) {
      FileSet fs = new FileSet();
      fs.setFile(buildFile);
      path.addFileset(fs);
   }

   private File getIvyFileFor(File buildFile) {
      return new File(buildFile.getParentFile(), this.ivyFilePath);
   }

   public boolean isHaltonerror() {
      return this.haltOnError;
   }

   public void setHaltonerror(boolean haltOnError) {
      this.haltOnError = haltOnError;
   }

   public String getIvyfilepath() {
      return this.ivyFilePath;
   }

   public void setIvyfilepath(String ivyFilePath) {
      this.ivyFilePath = ivyFilePath;
   }

   public String getOnMissingDescriptor() {
      return this.onMissingDescriptor;
   }

   public void setOnMissingDescriptor(String onMissingDescriptor) {
      this.onMissingDescriptor = onMissingDescriptor;
   }

   /** @deprecated */
   @Deprecated
   public boolean isSkipbuildwithoutivy() {
      return "skip".equals(this.onMissingDescriptor);
   }

   /** @deprecated */
   @Deprecated
   public void setSkipbuildwithoutivy(boolean skipBuildFilesWithoutIvy) {
      Message.deprecated("skipbuildwithoutivy is deprecated, use onMissingDescriptor instead.");
      this.onMissingDescriptor = skipBuildFilesWithoutIvy ? "skip" : "fail";
   }

   public boolean isReverse() {
      return this.reverse;
   }

   public void setReverse(boolean reverse) {
      this.reverse = reverse;
   }

   public String getRestartFrom() {
      return this.restartFrom;
   }

   public void setRestartFrom(String restartFrom) {
      this.restartFrom = restartFrom;
   }

   public static final class OnMissingDescriptor {
      public static final String HEAD = "head";
      public static final String TAIL = "tail";
      public static final String SKIP = "skip";
      public static final String FAIL = "fail";
      public static final String WARN = "warn";

      private OnMissingDescriptor() {
      }
   }

   public static final class BuildListModule {
      private String organisation;
      private String module;
      private String revision;
      private String branch;
      private File file;

      public String getOrganisation() {
         return this.organisation;
      }

      public void setOrganisation(String organisation) {
         this.organisation = organisation;
      }

      public String getModule() {
         return this.module;
      }

      public void setModule(String module) {
         this.module = module;
      }

      public String getRevision() {
         return this.revision;
      }

      public void setRevision(String revision) {
         this.revision = revision;
      }

      public String getBranch() {
         return this.branch;
      }

      public void setBranch(String branch) {
         this.branch = branch;
      }

      public File getFile() {
         return this.file;
      }

      public void setFile(File file) {
         this.file = file;
      }
   }
}
