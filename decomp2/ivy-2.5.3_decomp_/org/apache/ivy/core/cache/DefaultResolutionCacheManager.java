package org.apache.ivy.core.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;
import java.util.Properties;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.RelativeUrlResolver;
import org.apache.ivy.core.module.descriptor.ExtendsDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.IvySettingsAware;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorParser;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.FileUtil;

public class DefaultResolutionCacheManager implements ResolutionCacheManager, IvySettingsAware {
   private static final String DEFAULT_CACHE_RESOLVED_IVY_PATTERN = "resolved-[organisation]-[module]-[revision].xml";
   private static final String DEFAULT_CACHE_RESOLVED_IVY_PROPERTIES_PATTERN = "resolved-[organisation]-[module]-[revision].properties";
   private String resolvedIvyPattern = "resolved-[organisation]-[module]-[revision].xml";
   private String resolvedIvyPropertiesPattern = "resolved-[organisation]-[module]-[revision].properties";
   private File basedir;
   private String name = "resolution-cache";
   private IvySettings settings;

   public DefaultResolutionCacheManager() {
   }

   public DefaultResolutionCacheManager(File basedir) {
      this.setBasedir(basedir);
   }

   public void setSettings(IvySettings settings) {
      this.settings = settings;
   }

   public File getResolutionCacheRoot() {
      if (this.basedir == null) {
         if (this.settings == null) {
            throw new IllegalStateException("The 'basedir' or 'IvySettings' has not been set on the ResolutionCacheManager");
         }

         this.basedir = this.settings.getDefaultResolutionCacheBasedir();
      }

      return this.basedir;
   }

   public File getBasedir() {
      return this.basedir;
   }

   public void setBasedir(File basedir) {
      this.basedir = basedir;
   }

   public String getResolvedIvyPattern() {
      return this.resolvedIvyPattern;
   }

   public void setResolvedIvyPattern(String cacheResolvedIvyPattern) {
      this.resolvedIvyPattern = cacheResolvedIvyPattern;
   }

   public String getResolvedIvyPropertiesPattern() {
      return this.resolvedIvyPropertiesPattern;
   }

   public void setResolvedIvyPropertiesPattern(String cacheResolvedIvyPropertiesPattern) {
      this.resolvedIvyPropertiesPattern = cacheResolvedIvyPropertiesPattern;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public File getResolvedIvyFileInCache(ModuleRevisionId mrid) {
      String file = IvyPatternHelper.substitute(this.getResolvedIvyPattern(), mrid.getOrganisation(), mrid.getName(), mrid.getRevision(), "ivy", "ivy", "xml");
      return new File(this.getResolutionCacheRoot(), file);
   }

   public File getResolvedIvyPropertiesInCache(ModuleRevisionId mrid) {
      String file = IvyPatternHelper.substitute(this.getResolvedIvyPropertiesPattern(), mrid.getOrganisation(), mrid.getName(), mrid.getRevision(), "ivy", "ivy", "xml");
      return new File(this.getResolutionCacheRoot(), file);
   }

   public File getConfigurationResolveReportInCache(String resolveId, String conf) {
      return new File(this.getResolutionCacheRoot(), resolveId + "-" + conf + ".xml");
   }

   public File[] getConfigurationResolveReportsInCache(String resolveId) {
      final String prefix = resolveId + "-";
      String suffix = ".xml";
      return this.getResolutionCacheRoot().listFiles(new FilenameFilter() {
         public boolean accept(File dir, String name) {
            return name.startsWith(prefix) && name.endsWith(".xml");
         }
      });
   }

   public ModuleDescriptor getResolvedModuleDescriptor(ModuleRevisionId mrid) throws ParseException, IOException {
      File ivyFile = this.getResolvedIvyFileInCache(mrid);
      if (!ivyFile.exists()) {
         throw new IllegalStateException("Ivy file not found in cache for " + mrid + "!");
      } else {
         Properties paths = new Properties();
         File parentsFile = this.getResolvedIvyPropertiesInCache(ModuleRevisionId.newInstance(mrid, mrid.getRevision() + "-parents"));
         if (parentsFile.exists()) {
            FileInputStream in = new FileInputStream(parentsFile);
            paths.load(in);
            in.close();
         }

         ParserSettings pSettings = new CacheParserSettings(this.settings, paths);
         URL ivyFileURL = ivyFile.toURI().toURL();
         return this.getModuleDescriptorParser(ivyFile).parseDescriptor(pSettings, ivyFileURL, false);
      }
   }

   protected ModuleDescriptorParser getModuleDescriptorParser(File moduleDescriptorFile) {
      return XmlModuleDescriptorParser.getInstance();
   }

   public void saveResolvedModuleDescriptor(ModuleDescriptor md) throws ParseException, IOException {
      ModuleRevisionId mrevId = md.getResolvedModuleRevisionId();
      File ivyFileInCache = this.getResolvedIvyFileInCache(mrevId);
      this.assertInsideCache(ivyFileInCache);
      md.toIvyFile(ivyFileInCache);
      Properties paths = new Properties();
      this.saveLocalParents(mrevId, md, ivyFileInCache, paths);
      if (!paths.isEmpty()) {
         File parentsFile = this.getResolvedIvyPropertiesInCache(ModuleRevisionId.newInstance(mrevId, mrevId.getRevision() + "-parents"));
         this.assertInsideCache(parentsFile);
         FileOutputStream out = new FileOutputStream(parentsFile);
         paths.store(out, (String)null);
         out.close();
      }

   }

   public final void assertInsideCache(File fileInCache) {
      if (!FileUtil.isLeadingPath(this.getResolutionCacheRoot(), fileInCache)) {
         throw new IllegalArgumentException(fileInCache + " is outside of the cache");
      }
   }

   private void saveLocalParents(ModuleRevisionId baseMrevId, ModuleDescriptor md, File mdFile, Properties paths) throws ParseException, IOException {
      for(ExtendsDescriptor parent : md.getInheritedDescriptors()) {
         if (parent.isLocal()) {
            ModuleDescriptor parentMd = parent.getParentMd();
            ModuleRevisionId pRevId = ModuleRevisionId.newInstance(baseMrevId, baseMrevId.getRevision() + "-parent." + paths.size());
            File parentFile = this.getResolvedIvyFileInCache(pRevId);
            this.assertInsideCache(parentFile);
            parentMd.toIvyFile(parentFile);
            paths.setProperty(mdFile.getName() + "|" + parent.getLocation(), parentFile.getAbsolutePath());
            this.saveLocalParents(baseMrevId, parentMd, parentFile, paths);
         }
      }

   }

   public String toString() {
      return this.name;
   }

   public void clean() {
      FileUtil.forceDelete(this.getResolutionCacheRoot());
   }

   private static class CacheParserSettings implements ParserSettings {
      private ParserSettings delegate;
      private Map parentPaths;

      public CacheParserSettings(ParserSettings delegate, Map parentPaths) {
         this.delegate = delegate;
         this.parentPaths = parentPaths;
      }

      public String substitute(String value) {
         return this.delegate.substitute(value);
      }

      public Map substitute(Map strings) {
         return this.delegate.substitute(strings);
      }

      public ResolutionCacheManager getResolutionCacheManager() {
         return this.delegate.getResolutionCacheManager();
      }

      public ConflictManager getConflictManager(String name) {
         return this.delegate.getConflictManager(name);
      }

      public PatternMatcher getMatcher(String matcherName) {
         return this.delegate.getMatcher(matcherName);
      }

      public Namespace getNamespace(String namespace) {
         return this.delegate.getNamespace(namespace);
      }

      public StatusManager getStatusManager() {
         return this.delegate.getStatusManager();
      }

      public RelativeUrlResolver getRelativeUrlResolver() {
         return new MapURLResolver(this.parentPaths, this.delegate.getRelativeUrlResolver());
      }

      public DependencyResolver getResolver(ModuleRevisionId mRevId) {
         return this.delegate.getResolver(mRevId);
      }

      public File resolveFile(String filename) {
         return this.delegate.resolveFile(filename);
      }

      public String getDefaultBranch(ModuleId moduleId) {
         return this.delegate.getDefaultBranch(moduleId);
      }

      public Namespace getContextNamespace() {
         return this.delegate.getContextNamespace();
      }

      public String getVariable(String value) {
         return this.delegate.getVariable(value);
      }

      public TimeoutConstraint getTimeoutConstraint(String name) {
         return this.delegate.getTimeoutConstraint(name);
      }
   }

   private static final class MapURLResolver extends RelativeUrlResolver {
      private Map paths;
      private RelativeUrlResolver delegate;

      private MapURLResolver(Map paths, RelativeUrlResolver delegate) {
         this.paths = paths;
         this.delegate = delegate;
      }

      public URL getURL(URL context, String url) throws MalformedURLException {
         String path = context.getPath();
         if (path.contains("/")) {
            String file = path.substring(path.lastIndexOf(47) + 1);
            if (this.paths.containsKey(file + "|" + url)) {
               File result = new File(this.paths.get(file + "|" + url).toString());
               return result.toURI().toURL();
            }
         }

         return this.delegate.getURL(context, url);
      }
   }
}
