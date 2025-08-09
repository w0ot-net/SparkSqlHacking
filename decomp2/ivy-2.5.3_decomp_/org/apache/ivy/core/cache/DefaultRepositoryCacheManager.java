package org.apache.ivy.core.cache;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ArtifactRevisionId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.id.ModuleRules;
import org.apache.ivy.core.pack.PackagingManager;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.IvySettingsAware;
import org.apache.ivy.plugins.lock.LockStrategy;
import org.apache.ivy.plugins.matcher.ExactPatternMatcher;
import org.apache.ivy.plugins.matcher.MapMatcher;
import org.apache.ivy.plugins.matcher.NoMatcher;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.NameSpaceHelper;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorParser;
import org.apache.ivy.plugins.repository.ArtifactResourceResolver;
import org.apache.ivy.plugins.repository.LocalizableResource;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.ResourceDownloader;
import org.apache.ivy.plugins.repository.ResourceHelper;
import org.apache.ivy.plugins.resolver.AbstractResolver;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.HexEncoder;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.PropertiesFile;
import org.apache.ivy.util.StringUtils;

public class DefaultRepositoryCacheManager implements RepositoryCacheManager, IvySettingsAware {
   private static final String DEFAULT_ARTIFACT_PATTERN = "[organisation]/[module](/[branch])/[type]s/[artifact]-[revision](-[classifier])(.[ext])";
   private static final String DEFAULT_DATA_FILE_PATTERN = "[organisation]/[module](/[branch])/ivydata-[revision].properties";
   private static final String DEFAULT_IVY_PATTERN = "[organisation]/[module](/[branch])/ivy-[revision].xml";
   private static final int DEFAULT_MEMORY_CACHE_SIZE = 150;
   private static MessageDigest SHA_DIGEST;
   private IvySettings settings;
   private File basedir;
   private LockStrategy lockStrategy;
   private String name;
   private String ivyPattern;
   private String dataFilePattern = "[organisation]/[module](/[branch])/ivydata-[revision].properties";
   private String artifactPattern;
   private String lockStrategyName;
   private String changingPattern;
   private String changingMatcherName = "exactOrRegexp";
   private Boolean checkmodified;
   private Boolean useOrigin;
   private ModuleRules ttlRules = new ModuleRules();
   private Long defaultTTL = null;
   private ModuleDescriptorMemoryCache memoryModuleDescrCache;
   private PackagingManager packagingManager = new PackagingManager();
   private final List configuredTTLs = new ArrayList();
   private static final Pattern DURATION_PATTERN;
   private static final int MILLIS_IN_SECONDS = 1000;
   private static final int MILLIS_IN_MINUTES = 60000;
   private static final int MILLIS_IN_HOUR = 3600000;
   private static final int MILLIS_IN_DAY = 86400000;
   private static final Pattern ARTIFACT_KEY_PATTERN;

   public DefaultRepositoryCacheManager() {
   }

   public DefaultRepositoryCacheManager(String name, IvySettings settings, File basedir) {
      this.setName(name);
      this.setSettings(settings);
      this.setBasedir(basedir);
   }

   public IvySettings getSettings() {
      return this.settings;
   }

   public void setSettings(IvySettings settings) {
      this.settings = settings;
      this.packagingManager.setSettings(settings);

      for(ConfiguredTTL configuredTTL : this.configuredTTLs) {
         this.addTTL(configuredTTL.attributes, (PatternMatcher)(configuredTTL.matcher == null ? ExactPatternMatcher.INSTANCE : settings.getMatcher(configuredTTL.matcher)), configuredTTL.duration);
      }

      this.configuredTTLs.clear();
   }

   public File getIvyFileInCache(ModuleRevisionId mrid) {
      String file = IvyPatternHelper.substitute(this.getIvyPattern(), DefaultArtifact.newIvyArtifact(mrid, (Date)null));
      return new File(this.getRepositoryCacheRoot(), file);
   }

   public String getIvyPattern() {
      if (this.ivyPattern == null) {
         if (this.settings != null) {
            this.ivyPattern = this.settings.getDefaultCacheIvyPattern();
         }

         if (this.ivyPattern == null) {
            this.ivyPattern = "[organisation]/[module](/[branch])/ivy-[revision].xml";
         }
      }

      return this.ivyPattern;
   }

   public String getArtifactPattern() {
      if (this.artifactPattern == null) {
         if (this.settings != null) {
            this.artifactPattern = this.settings.getDefaultCacheArtifactPattern();
         }

         if (this.artifactPattern == null) {
            this.artifactPattern = "[organisation]/[module](/[branch])/[type]s/[artifact]-[revision](-[classifier])(.[ext])";
         }
      }

      return this.artifactPattern;
   }

   public void setArtifactPattern(String artifactPattern) {
      CacheUtil.checkCachePattern(artifactPattern);
      this.artifactPattern = artifactPattern;
   }

   public File getBasedir() {
      if (this.basedir == null) {
         this.basedir = this.settings.getDefaultRepositoryCacheBasedir();
      }

      return this.basedir;
   }

   public void setBasedir(File cache) {
      this.basedir = cache;
   }

   public long getDefaultTTL() {
      if (this.defaultTTL == null) {
         this.defaultTTL = this.parseDuration(this.settings.getVariable("ivy.cache.ttl.default"));
      }

      return this.defaultTTL;
   }

   public void setDefaultTTL(long defaultTTL) {
      this.defaultTTL = defaultTTL;
   }

   public void setDefaultTTL(String defaultTTL) {
      this.defaultTTL = this.parseDuration(defaultTTL);
   }

   public String getDataFilePattern() {
      return this.dataFilePattern;
   }

   public void setDataFilePattern(String dataFilePattern) {
      CacheUtil.checkCachePattern(dataFilePattern);
      this.dataFilePattern = dataFilePattern;
   }

   public void setIvyPattern(String ivyPattern) {
      CacheUtil.checkCachePattern(ivyPattern);
      this.ivyPattern = ivyPattern;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getChangingMatcherName() {
      return this.changingMatcherName;
   }

   public void setChangingMatcher(String changingMatcherName) {
      this.changingMatcherName = changingMatcherName;
   }

   public String getChangingPattern() {
      return this.changingPattern;
   }

   public void setChangingPattern(String changingPattern) {
      this.changingPattern = changingPattern;
   }

   public void addTTL(Map attributes, PatternMatcher matcher, long duration) {
      this.ttlRules.defineRule(new MapMatcher(attributes, matcher), duration);
   }

   public void addConfiguredTtl(Map attributes) {
      String durationValue = (String)attributes.get("duration");
      if (durationValue == null) {
         throw new IllegalArgumentException("'duration' attribute is mandatory for ttl");
      } else {
         long duration = this.parseDuration(durationValue);
         ConfiguredTTL configuredTTL = new ConfiguredTTL(duration, (String)attributes.get("matcher"), attributes);
         this.configuredTTLs.add(configuredTTL);
      }
   }

   public void setMemorySize(int size) {
      this.memoryModuleDescrCache = new ModuleDescriptorMemoryCache(size);
   }

   public ModuleDescriptorMemoryCache getMemoryCache() {
      if (this.memoryModuleDescrCache == null) {
         this.memoryModuleDescrCache = new ModuleDescriptorMemoryCache(150);
      }

      return this.memoryModuleDescrCache;
   }

   private long parseDuration(String duration) {
      if (duration == null) {
         return 0L;
      } else if ("eternal".equals(duration)) {
         return Long.MAX_VALUE;
      } else {
         Matcher m = DURATION_PATTERN.matcher(duration);
         if (m.matches()) {
            int days = this.getGroupIntValue(m, 1);
            int hours = this.getGroupIntValue(m, 2);
            int minutes = this.getGroupIntValue(m, 3);
            int seconds = this.getGroupIntValue(m, 4);
            int millis = this.getGroupIntValue(m, 5);
            return (long)(days * 86400000 + hours * 3600000 + minutes * '\uea60' + seconds * 1000 + millis);
         } else {
            throw new IllegalArgumentException("invalid duration '" + duration + "': it must match " + DURATION_PATTERN.pattern() + " or 'eternal'");
         }
      }
   }

   private int getGroupIntValue(Matcher m, int groupNumber) {
      String g = m.group(groupNumber);
      return StringUtils.isNullOrEmpty(g) ? 0 : Integer.parseInt(g);
   }

   public boolean isCheckmodified() {
      if (this.checkmodified != null) {
         return this.checkmodified;
      } else {
         return this.getSettings() != null && Boolean.parseBoolean(this.getSettings().getVariable("ivy.resolver.default.check.modified"));
      }
   }

   public void setCheckmodified(boolean check) {
      this.checkmodified = check;
   }

   public boolean isUseOrigin() {
      if (this.useOrigin != null) {
         return this.useOrigin;
      } else {
         return this.getSettings() != null && this.getSettings().isDefaultUseOrigin();
      }
   }

   public void setUseOrigin(boolean b) {
      this.useOrigin = b;
   }

   public File getArchiveFileInCache(Artifact artifact) {
      ArtifactOrigin origin = this.getSavedArtifactOrigin(artifact);
      return this.getArchiveFileInCache(artifact, origin);
   }

   public File getArchiveFileInCache(Artifact artifact, ArtifactOrigin origin) {
      File archive = new File(this.getRepositoryCacheRoot(), this.getArchivePathInCache(artifact, origin));
      if (!archive.exists() && !ArtifactOrigin.isUnknown(origin) && origin.isLocal()) {
         File original = Checks.checkAbsolute(parseArtifactOriginFilePath(origin), artifact + " origin location");
         if (original.exists()) {
            return original;
         }
      }

      return archive;
   }

   private File getArchiveFileInCache(Artifact artifact, ArtifactOrigin origin, boolean useOrigin) {
      return useOrigin && !ArtifactOrigin.isUnknown(origin) && origin.isLocal() ? Checks.checkAbsolute(parseArtifactOriginFilePath(origin), artifact + " origin location") : new File(this.getRepositoryCacheRoot(), this.getArchivePathInCache(artifact, origin));
   }

   public String getArchivePathInCache(Artifact artifact) {
      return IvyPatternHelper.substitute(this.getArtifactPattern(), artifact);
   }

   public String getArchivePathInCache(Artifact artifact, ArtifactOrigin origin) {
      return this.isOriginalMetadataArtifact(artifact) ? IvyPatternHelper.substitute(this.getIvyPattern() + ".original", artifact, origin) : IvyPatternHelper.substitute(this.getArtifactPattern(), artifact, origin);
   }

   private void saveResolver(ModuleDescriptor md, String name) {
      PropertiesFile cdf = this.getCachedDataFile(md);
      cdf.setProperty("resolver", name);
      cdf.save();
   }

   public void saveResolvers(ModuleDescriptor md, String metadataResolverName, String artifactResolverName) {
      ModuleRevisionId mrid = md.getResolvedModuleRevisionId();
      if (!this.lockMetadataArtifact(mrid)) {
         Message.error("impossible to acquire lock for " + mrid);
      } else {
         try {
            PropertiesFile cdf = this.getCachedDataFile(md);
            cdf.setProperty("resolver", metadataResolverName);
            cdf.setProperty("artifact.resolver", artifactResolverName);
            cdf.save();
         } finally {
            this.unlockMetadataArtifact(mrid);
         }

      }
   }

   private String getSavedResolverName(ModuleDescriptor md) {
      PropertiesFile cdf = this.getCachedDataFile(md);
      return cdf.getProperty("resolver");
   }

   private String getSavedArtResolverName(ModuleDescriptor md) {
      PropertiesFile cdf = this.getCachedDataFile(md);
      return cdf.getProperty("artifact.resolver");
   }

   void saveArtifactOrigin(Artifact artifact, ArtifactOrigin origin) {
      PropertiesFile cdf = this.getCachedDataFile(artifact.getModuleRevisionId());
      cdf.setProperty(this.getIsLocalKey(artifact), String.valueOf(origin.isLocal()));
      cdf.setProperty(this.getLocationKey(artifact), origin.getLocation());
      cdf.setProperty(this.getOriginalKey(artifact), this.getPrefixKey(origin.getArtifact()));
      if (origin.getLastChecked() != null) {
         cdf.setProperty(this.getLastCheckedKey(artifact), origin.getLastChecked().toString());
      }

      cdf.setProperty(this.getExistsKey(artifact), Boolean.toString(origin.isExists()));
      cdf.save();
   }

   private void removeSavedArtifactOrigin(Artifact artifact) {
      PropertiesFile cdf = this.getCachedDataFile(artifact.getModuleRevisionId());
      cdf.remove(this.getLocationKey(artifact));
      cdf.remove(this.getIsLocalKey(artifact));
      cdf.remove(this.getLastCheckedKey(artifact));
      cdf.remove(this.getOriginalKey(artifact));
      cdf.save();
   }

   public ArtifactOrigin getSavedArtifactOrigin(Artifact artifact) {
      ModuleRevisionId mrid = artifact.getModuleRevisionId();
      if (!this.lockMetadataArtifact(mrid)) {
         Message.error("impossible to acquire lock for " + mrid);
         return ArtifactOrigin.unknown(artifact);
      } else {
         ArtifactOrigin origin;
         try {
            PropertiesFile cdf = this.getCachedDataFile(artifact.getModuleRevisionId());
            String location = cdf.getProperty(this.getLocationKey(artifact));
            String local = cdf.getProperty(this.getIsLocalKey(artifact));
            String lastChecked = cdf.getProperty(this.getLastCheckedKey(artifact));
            String exists = cdf.getProperty(this.getExistsKey(artifact));
            String original = cdf.getProperty(this.getOriginalKey(artifact));
            boolean isLocal = Boolean.valueOf(local);
            if (location != null) {
               if (original != null) {
                  Matcher m = ARTIFACT_KEY_PATTERN.matcher(original);
                  if (m.matches()) {
                     String origName = m.group(1);
                     String origType = m.group(2);
                     String origExt = m.group(3);
                     ArtifactRevisionId originArtifactId = ArtifactRevisionId.newInstance(artifact.getModuleRevisionId(), origName, origType, origExt);
                     if (m.group(4).equals("" + originArtifactId.hashCode())) {
                        try {
                           artifact = new DefaultArtifact(originArtifactId, artifact.getPublicationDate(), new URL(location), true);
                        } catch (MalformedURLException e) {
                           Message.debug((Throwable)e);
                        }
                     }
                  }
               } else if (!location.endsWith("." + artifact.getExt())) {
                  String ownLocationKey = this.getLocationKey(artifact);

                  for(Map.Entry entry : cdf.entrySet()) {
                     if (entry.getValue().equals(location) && !ownLocationKey.equals(entry.getKey())) {
                        Matcher m = ARTIFACT_KEY_PATTERN.matcher((String)entry.getKey());
                        if (m.matches()) {
                           String origName = m.group(1);
                           String origType = m.group(2);
                           String origExt = m.group(3);
                           if (origType.endsWith(".original")) {
                              ArtifactRevisionId originArtifactId = ArtifactRevisionId.newInstance(artifact.getModuleRevisionId(), origName, origType, origExt);
                              if (m.group(4).equals("" + originArtifactId.hashCode())) {
                                 try {
                                    artifact = new DefaultArtifact(originArtifactId, artifact.getPublicationDate(), new URL(location), true);
                                 } catch (MalformedURLException e) {
                                    Message.debug((Throwable)e);
                                 }
                              }
                              break;
                           }
                        }
                     }
                  }
               }

               origin = new ArtifactOrigin(artifact, isLocal, location);
               if (lastChecked != null) {
                  origin.setLastChecked(Long.valueOf(lastChecked));
               }

               if (exists != null) {
                  origin.setExist(Boolean.valueOf(exists));
               }

               ArtifactOrigin var30 = origin;
               return var30;
            }

            origin = ArtifactOrigin.unknown(artifact);
         } finally {
            this.unlockMetadataArtifact(mrid);
         }

         return origin;
      }
   }

   private String getPrefixKey(Artifact artifact) {
      return String.format("artifact:%s#%s#%s#%d", artifact.getName(), artifact.getType(), artifact.getExt(), artifact.getId().hashCode());
   }

   private String getLocationKey(Artifact artifact) {
      return this.getPrefixKey(artifact) + ".location";
   }

   private String getIsLocalKey(Artifact artifact) {
      return this.getPrefixKey(artifact) + ".is-local";
   }

   private String getLastCheckedKey(Artifact artifact) {
      return this.getPrefixKey(artifact) + ".lastchecked";
   }

   private String getExistsKey(Artifact artifact) {
      return this.getPrefixKey(artifact) + ".exists";
   }

   private String getOriginalKey(Artifact artifact) {
      return this.getPrefixKey(artifact) + ".original";
   }

   private PropertiesFile getCachedDataFile(ModuleDescriptor md) {
      return this.getCachedDataFile(md.getResolvedModuleRevisionId());
   }

   private PropertiesFile getCachedDataFile(ModuleRevisionId mRevId) {
      File file = new File(this.getRepositoryCacheRoot(), IvyPatternHelper.substitute(this.getDataFilePattern(), mRevId));
      this.assertInsideCache(file);
      return new PropertiesFile(file, "ivy cached data file for " + mRevId);
   }

   private PropertiesFile getCachedDataFile(String resolverName, ModuleRevisionId mRevId) {
      File file = new File(this.getRepositoryCacheRoot(), IvyPatternHelper.substitute(this.getDataFilePattern(), mRevId) + "." + resolverName);
      this.assertInsideCache(file);
      return new PropertiesFile(file, "ivy cached data file for " + mRevId);
   }

   public ResolvedModuleRevision findModuleInCache(DependencyDescriptor dd, ModuleRevisionId requestedRevisionId, CacheMetadataOptions options, String expectedResolver) {
      if (this.isCheckmodified(dd, requestedRevisionId, options)) {
         Message.verbose("don't use cache for " + requestedRevisionId + ": checkModified=true");
         return null;
      } else if (!options.isUseCacheOnly() && this.isChanging(dd, requestedRevisionId, options)) {
         Message.verbose("don't use cache for " + requestedRevisionId + ": changing=true");
         return null;
      } else {
         return this.doFindModuleInCache(requestedRevisionId, options, expectedResolver);
      }
   }

   private ResolvedModuleRevision doFindModuleInCache(ModuleRevisionId mrid, CacheMetadataOptions options, String expectedResolver) {
      if (!this.lockMetadataArtifact(mrid)) {
         Message.error("impossible to acquire lock for " + mrid);
         return null;
      } else {
         boolean unlock = true;

         try {
            if (this.settings.getVersionMatcher().isDynamic(mrid)) {
               String resolvedRevision = this.getResolvedRevision(expectedResolver, mrid, options);
               if (resolvedRevision == null) {
                  Object var21 = null;
                  return (ResolvedModuleRevision)var21;
               }

               Message.verbose("found resolved revision in cache: " + mrid + " => " + resolvedRevision);
               this.unlockMetadataArtifact(mrid);
               mrid = ModuleRevisionId.newInstance(mrid, resolvedRevision);
               if (!this.lockMetadataArtifact(mrid)) {
                  Message.error("impossible to acquire lock for " + mrid);
                  unlock = false;
                  Object var20 = null;
                  return (ResolvedModuleRevision)var20;
               }
            }

            File ivyFile = this.getIvyFileInCache(mrid);
            if (!ivyFile.exists()) {
               Message.debug("\tno ivy file in cache for " + mrid + ": tried " + ivyFile);
               return null;
            } else {
               try {
                  ModuleDescriptorParser parser = this.getModuleDescriptorParser(ivyFile);
                  ModuleDescriptor depMD = this.getMdFromCache(parser, options, ivyFile);
                  String resolverName = this.getSavedResolverName(depMD);
                  String artResolverName = this.getSavedArtResolverName(depMD);
                  DependencyResolver resolver = this.settings.getResolver(resolverName);
                  if (resolver == null) {
                     Message.debug("\tresolver not found: " + resolverName + " => trying to use the one configured for " + mrid);
                     resolver = this.settings.getResolver(depMD.getResolvedModuleRevisionId());
                     if (resolver != null) {
                        Message.debug("\tconfigured resolver found for " + depMD.getResolvedModuleRevisionId() + ": " + resolver.getName() + ": saving this data");
                        this.saveResolver(depMD, resolver.getName());
                     }
                  }

                  DependencyResolver artResolver = this.settings.getResolver(artResolverName);
                  if (artResolver == null) {
                     artResolver = resolver;
                  }

                  if (resolver != null) {
                     Message.debug("\tfound ivy file in cache for " + mrid + " (resolved by " + resolver.getName() + "): " + ivyFile);
                     if (expectedResolver != null && !expectedResolver.equals(resolver.getName())) {
                        Message.debug("found module in cache but with a different resolver: discarding: " + mrid + "; expected resolver=" + expectedResolver + "; resolver=" + resolver.getName());
                        return null;
                     }

                     MetadataArtifactDownloadReport madr = new MetadataArtifactDownloadReport(depMD.getMetadataArtifact());
                     madr.setDownloadStatus(DownloadStatus.NO);
                     madr.setSearched(false);
                     madr.setLocalFile(ivyFile);
                     madr.setSize(ivyFile.length());
                     madr.setArtifactOrigin(this.getSavedArtifactOrigin(depMD.getMetadataArtifact()));
                     if (madr.getArtifactOrigin().isExists()) {
                        if (madr.getArtifactOrigin().isLocal() && madr.getArtifactOrigin().getArtifact().getUrl() != null) {
                           madr.setOriginalLocalFile(new File(madr.getArtifactOrigin().getArtifact().getUrl().toURI()));
                        } else {
                           madr.setOriginalLocalFile(this.getArchiveFileInCache(madr.getArtifactOrigin().getArtifact()));
                        }
                     }

                     ResolvedModuleRevision var13 = new ResolvedModuleRevision(resolver, artResolver, depMD, madr);
                     return var13;
                  }

                  Message.debug("\tresolver not found: " + resolverName + " => cannot use cached ivy file for " + mrid);
               } catch (Exception e) {
                  Message.debug("\tproblem while parsing cached ivy file for: " + mrid, e);
               }

               return null;
            }
         } finally {
            if (unlock) {
               this.unlockMetadataArtifact(mrid);
            }

         }
      }
   }

   protected ModuleDescriptorParser getModuleDescriptorParser(File moduleDescriptorFile) {
      return XmlModuleDescriptorParser.getInstance();
   }

   private ModuleDescriptor getMdFromCache(ModuleDescriptorParser mdParser, CacheMetadataOptions options, File ivyFile) throws ParseException, IOException {
      ModuleDescriptorMemoryCache cache = this.getMemoryCache();
      ModuleDescriptorProvider mdProvider = new MyModuleDescriptorProvider(mdParser, this.settings);
      return cache.get(ivyFile, this.settings, options.isValidate(), mdProvider);
   }

   private ModuleDescriptor getStaledMd(ModuleDescriptorParser mdParser, CacheMetadataOptions options, File ivyFile, ParserSettings parserSettings) throws ParseException, IOException {
      ModuleDescriptorMemoryCache cache = this.getMemoryCache();
      ModuleDescriptorProvider mdProvider = new MyModuleDescriptorProvider(mdParser, parserSettings);
      return cache.getStale(ivyFile, this.settings, options.isValidate(), mdProvider);
   }

   private String getResolvedRevision(String expectedResolver, ModuleRevisionId mrid, CacheMetadataOptions options) {
      if (!this.lockMetadataArtifact(mrid)) {
         Message.error("impossible to acquire lock for " + mrid);
         return null;
      } else {
         Object var4;
         try {
            if (!options.isForce()) {
               PropertiesFile cachedResolvedRevision;
               if (expectedResolver != null) {
                  cachedResolvedRevision = this.getCachedDataFile(expectedResolver, mrid);
               } else {
                  cachedResolvedRevision = this.getCachedDataFile(mrid);
               }

               String resolvedRevision = cachedResolvedRevision.getProperty("resolved.revision");
               if (resolvedRevision == null) {
                  Message.verbose(this.getName() + ": no cached resolved revision for " + mrid);
                  Object var14 = null;
                  return (String)var14;
               }

               String resolvedTime = cachedResolvedRevision.getProperty("resolved.time");
               if (resolvedTime == null) {
                  Message.verbose(this.getName() + ": inconsistent or old cache: no cached resolved time for " + mrid);
                  this.saveResolvedRevision(expectedResolver, mrid, resolvedRevision);
                  String var16 = resolvedRevision;
                  return var16;
               }

               if (options.isCheckTTL()) {
                  long expiration = Long.parseLong(resolvedTime) + this.getTTL(mrid);
                  if (expiration > 0L && System.currentTimeMillis() > expiration) {
                     Message.verbose(this.getName() + ": cached resolved revision expired for " + mrid);
                     Object var9 = null;
                     return (String)var9;
                  }
               }

               String var15 = resolvedRevision;
               return var15;
            }

            Message.verbose("refresh mode: no check for cached resolved revision for " + mrid);
            var4 = null;
         } finally {
            this.unlockMetadataArtifact(mrid);
         }

         return (String)var4;
      }
   }

   /** @deprecated */
   @Deprecated
   public void saveResolvedRevision(ModuleRevisionId mrid, String revision) {
      this.saveResolvedRevision((String)null, mrid, revision);
   }

   public void saveResolvedRevision(String resolverName, ModuleRevisionId mrid, String revision) {
      if (!this.lockMetadataArtifact(mrid)) {
         Message.error("impossible to acquire lock for " + mrid);
      } else {
         try {
            PropertiesFile cachedResolvedRevision;
            if (resolverName == null) {
               cachedResolvedRevision = this.getCachedDataFile(mrid);
            } else {
               cachedResolvedRevision = this.getCachedDataFile(resolverName, mrid);
            }

            cachedResolvedRevision.setProperty("resolved.time", String.valueOf(System.currentTimeMillis()));
            cachedResolvedRevision.setProperty("resolved.revision", revision);
            if (resolverName != null) {
               cachedResolvedRevision.setProperty("resolver", resolverName);
            }

            cachedResolvedRevision.save();
         } finally {
            this.unlockMetadataArtifact(mrid);
         }

      }
   }

   public long getTTL(ModuleRevisionId mrid) {
      Long ttl = (Long)this.ttlRules.getRule(mrid);
      return ttl == null ? this.getDefaultTTL() : ttl;
   }

   public String toString() {
      return this.name;
   }

   public File getRepositoryCacheRoot() {
      return this.getBasedir();
   }

   public LockStrategy getLockStrategy() {
      if (this.lockStrategy == null) {
         if (this.lockStrategyName != null) {
            this.lockStrategy = this.settings.getLockStrategy(this.lockStrategyName);
         } else {
            this.lockStrategy = this.settings.getDefaultLockStrategy();
         }
      }

      return this.lockStrategy;
   }

   public void setLockStrategy(LockStrategy lockStrategy) {
      this.lockStrategy = lockStrategy;
   }

   public void setLockStrategy(String lockStrategyName) {
      this.lockStrategyName = lockStrategyName;
   }

   public ArtifactDownloadReport download(Artifact artifact, ArtifactResourceResolver resourceResolver, ResourceDownloader resourceDownloader, CacheDownloadOptions options) {
      ArtifactDownloadReport adr = new ArtifactDownloadReport(artifact);
      boolean useOrigin = this.isUseOrigin();
      ModuleRevisionId mrid = artifact.getModuleRevisionId();
      if (!this.lockMetadataArtifact(mrid)) {
         adr.setDownloadStatus(DownloadStatus.FAILED);
         adr.setDownloadDetails("impossible to get lock for " + mrid);
         return adr;
      } else {
         ArtifactDownloadReport var21;
         try {
            DownloadListener listener = options.getListener();
            if (listener != null) {
               listener.needArtifact(this, artifact);
            }

            ArtifactOrigin origin = this.getSavedArtifactOrigin(artifact);
            File archiveFile = this.getArchiveFileInCache(artifact, origin, useOrigin);
            if (archiveFile.exists() && !options.isForce()) {
               adr.setDownloadStatus(DownloadStatus.NO);
               adr.setSize(archiveFile.length());
               adr.setArtifactOrigin(origin);
               adr.setLocalFile(archiveFile);
            } else {
               long start = System.currentTimeMillis();

               try {
                  ResolvedResource artifactRef = resourceResolver.resolve(artifact);
                  if (artifactRef != null) {
                     Resource artifactRes = artifactRef.getResource();
                     origin = new ArtifactOrigin(artifact, artifactRes.isLocal(), artifactRes.getName());
                     if (useOrigin && artifactRes.isLocal()) {
                        if (artifactRes instanceof LocalizableResource) {
                           origin.setLocation(((LocalizableResource)artifactRes).getFile().getAbsolutePath());
                        }

                        this.saveArtifactOrigin(artifact, origin);
                        archiveFile = this.getArchiveFileInCache(artifact, origin);
                        adr.setDownloadStatus(DownloadStatus.NO);
                        adr.setSize(archiveFile.length());
                        adr.setArtifactOrigin(origin);
                        adr.setLocalFile(archiveFile);
                     } else {
                        archiveFile = this.getArchiveFileInCache(artifact, origin, useOrigin);
                        if (ResourceHelper.equals(artifactRes, archiveFile)) {
                           throw new IllegalStateException("invalid settings for '" + resourceResolver + "': pointing repository to ivy cache is forbidden !");
                        }

                        this.assertInsideCache(archiveFile);
                        if (listener != null) {
                           listener.startArtifactDownload(this, artifactRef, artifact, origin);
                        }

                        resourceDownloader.download(artifact, artifactRes, archiveFile);
                        adr.setSize(archiveFile.length());
                        this.saveArtifactOrigin(artifact, origin);
                        adr.setDownloadTimeMillis(System.currentTimeMillis() - start);
                        adr.setDownloadStatus(DownloadStatus.SUCCESSFUL);
                        adr.setArtifactOrigin(origin);
                        adr.setLocalFile(archiveFile);
                     }
                  } else {
                     adr.setDownloadStatus(DownloadStatus.FAILED);
                     adr.setDownloadDetails("missing artifact");
                     adr.setDownloadTimeMillis(System.currentTimeMillis() - start);
                  }
               } catch (Exception ex) {
                  Message.debug((Throwable)ex);
                  adr.setDownloadStatus(DownloadStatus.FAILED);
                  adr.setDownloadDetails(ex.getMessage());
                  adr.setDownloadTimeMillis(System.currentTimeMillis() - start);
               }
            }

            if (adr.getDownloadStatus() != DownloadStatus.FAILED) {
               this.unpackArtifact(artifact, adr, options);
            }

            if (listener != null) {
               listener.endArtifactDownload(this, artifact, adr, archiveFile);
            }

            var21 = adr;
         } finally {
            this.unlockMetadataArtifact(mrid);
         }

         return var21;
      }
   }

   private void unpackArtifact(Artifact artifact, ArtifactDownloadReport adr, CacheDownloadOptions options) {
      Artifact unpacked = this.packagingManager.getUnpackedArtifact(artifact);
      if (unpacked != null) {
         File archiveFile = this.getArchiveFileInCache(unpacked, (ArtifactOrigin)null, false);
         if (archiveFile.exists() && !options.isForce()) {
            adr.setUnpackedLocalFile(archiveFile);
            adr.setUnpackedArtifact(unpacked);
         } else {
            Message.info("\tUnpacking " + artifact.getId());

            try {
               Artifact unpackedArtifact = this.packagingManager.unpackArtifact(artifact, adr.getLocalFile(), archiveFile);
               adr.setUnpackedLocalFile(archiveFile);
               adr.setUnpackedArtifact(unpackedArtifact);
            } catch (Exception e) {
               Message.debug((Throwable)e);
               adr.setDownloadStatus(DownloadStatus.FAILED);
               adr.setDownloadDetails("The packed artifact " + artifact.getId() + " could not be unpacked (" + e.getMessage() + ")");
            }
         }

      }
   }

   public ArtifactDownloadReport downloadRepositoryResource(Resource resource, String name, String type, String extension, CacheResourceOptions options, Repository repository) {
      String hash = this.computeResourceNameHash(resource);
      ModuleRevisionId mrid = ModuleRevisionId.newInstance("_repository_metadata_", hash, Ivy.getWorkingRevision());
      Artifact artifact = new DefaultArtifact(mrid, (Date)null, name, type, extension);
      ArtifactDownloadReport adr = new ArtifactDownloadReport(artifact);
      boolean useOrigin = this.isUseOrigin();

      ArtifactDownloadReport var25;
      try {
         DownloadListener listener = options.getListener();
         if (listener != null) {
            listener.needArtifact(this, artifact);
         }

         ArtifactOrigin savedOrigin = this.getSavedArtifactOrigin(artifact);
         File archiveFile = this.getArchiveFileInCache(artifact, savedOrigin, useOrigin);
         ArtifactOrigin origin = new ArtifactOrigin(artifact, resource.isLocal(), resource.getName());
         if (!options.isForce() && this.checkCacheUptodate(archiveFile, resource, savedOrigin, origin, options.getTtl())) {
            if (archiveFile.exists()) {
               this.saveArtifactOrigin(artifact, origin);
               adr.setDownloadStatus(DownloadStatus.NO);
               adr.setSize(archiveFile.length());
               adr.setArtifactOrigin(savedOrigin);
               adr.setLocalFile(archiveFile);
            } else {
               adr.setDownloadStatus(DownloadStatus.FAILED);
               adr.setDownloadDetails("Remote resource is known to not exist");
            }
         } else {
            long start = System.currentTimeMillis();
            origin.setLastChecked(start);

            try {
               ResolvedResource artifactRef = new ResolvedResource(resource, Ivy.getWorkingRevision());
               if (useOrigin && resource.isLocal()) {
                  this.saveArtifactOrigin(artifact, origin);
                  archiveFile = this.getArchiveFileInCache(artifact, origin);
                  adr.setDownloadStatus(DownloadStatus.NO);
                  adr.setSize(archiveFile.length());
                  adr.setArtifactOrigin(origin);
                  adr.setLocalFile(archiveFile);
               } else {
                  if (listener != null) {
                     listener.startArtifactDownload(this, artifactRef, artifact, origin);
                  }

                  this.assertInsideCache(archiveFile);
                  if (archiveFile.exists()) {
                     archiveFile.delete();
                  }

                  File part = new File(archiveFile.getAbsolutePath() + ".part");
                  repository.get(resource.getName(), part);
                  if (!part.renameTo(archiveFile)) {
                     throw new IOException("impossible to move part file to definitive one: " + part + " -> " + archiveFile);
                  }

                  adr.setSize(archiveFile.length());
                  this.saveArtifactOrigin(artifact, origin);
                  adr.setDownloadTimeMillis(System.currentTimeMillis() - start);
                  adr.setDownloadStatus(DownloadStatus.SUCCESSFUL);
                  adr.setArtifactOrigin(origin);
                  adr.setLocalFile(archiveFile);
               }
            } catch (Exception ex) {
               Message.debug((Throwable)ex);
               origin.setExist(false);
               this.saveArtifactOrigin(artifact, origin);
               adr.setDownloadStatus(DownloadStatus.FAILED);
               adr.setDownloadDetails(ex.getMessage());
               adr.setDownloadTimeMillis(System.currentTimeMillis() - start);
            }
         }

         if (listener != null) {
            listener.endArtifactDownload(this, artifact, adr, archiveFile);
         }

         var25 = adr;
      } finally {
         this.unlockMetadataArtifact(mrid);
      }

      return var25;
   }

   private String computeResourceNameHash(Resource resource) {
      return HexEncoder.encode(SHA_DIGEST.digest(resource.getName().getBytes(StandardCharsets.UTF_8)));
   }

   private boolean checkCacheUptodate(File archiveFile, Resource resource, ArtifactOrigin savedOrigin, ArtifactOrigin origin, long ttl) {
      long time = System.currentTimeMillis();
      if (savedOrigin.getLastChecked() != null && time - savedOrigin.getLastChecked() < ttl) {
         return archiveFile.exists() || !savedOrigin.isExists();
      } else if (!archiveFile.exists()) {
         return false;
      } else {
         origin.setLastChecked(time);
         return archiveFile.lastModified() >= resource.getLastModified();
      }
   }

   public void originalToCachedModuleDescriptor(DependencyResolver resolver, ResolvedResource originalMetadataRef, Artifact requestedMetadataArtifact, ResolvedModuleRevision rmr, ModuleDescriptorWriter writer) {
      ModuleDescriptor md = rmr.getDescriptor();
      Artifact originalMetadataArtifact = this.getOriginalMetadataArtifact(requestedMetadataArtifact);
      File mdFileInCache = this.getIvyFileInCache(md.getResolvedModuleRevisionId());
      ModuleRevisionId mrid = requestedMetadataArtifact.getModuleRevisionId();
      if (!this.lockMetadataArtifact(mrid)) {
         Message.warn("impossible to acquire lock for: " + mrid);
      } else {
         try {
            File originalFileInCache = this.getArchiveFileInCache(originalMetadataArtifact);
            writer.write(originalMetadataRef, md, originalFileInCache, mdFileInCache);
            this.getMemoryCache().putInCache(mdFileInCache, new ParserSettingsMonitor(this.settings), true, md);
            this.saveResolvers(md, resolver.getName(), resolver.getName());
            if (!md.isDefault()) {
               rmr.getReport().setOriginalLocalFile(originalFileInCache);
            }

            rmr.getReport().setLocalFile(mdFileInCache);
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            String metadataRef;
            if (originalMetadataRef == null) {
               metadataRef = String.valueOf(md.getResolvedModuleRevisionId());
            } else {
               metadataRef = String.valueOf(originalMetadataRef);
            }

            Message.warn("impossible to put metadata file in cache: " + metadataRef, e);
         } finally {
            this.unlockMetadataArtifact(mrid);
         }

      }
   }

   public ResolvedModuleRevision cacheModuleDescriptor(DependencyResolver resolver, final ResolvedResource mdRef, DependencyDescriptor dd, Artifact moduleArtifact, ResourceDownloader downloader, CacheMetadataOptions options) throws ParseException {
      Date cachedPublicationDate = null;
      ModuleRevisionId mrid = moduleArtifact.getModuleRevisionId();
      if (!this.lockMetadataArtifact(mrid)) {
         Message.error("impossible to acquire lock for " + mrid);
         return null;
      } else {
         BackupResourceDownloader backupDownloader = new BackupResourceDownloader(downloader);

         try {
            if (moduleArtifact.isMetadata()) {
               ResolvedModuleRevision rmr = this.doFindModuleInCache(mrid, options, (String)null);
               if (rmr != null) {
                  if (rmr.getDescriptor().isDefault() && rmr.getResolver() != resolver) {
                     Message.verbose("\t" + this.getName() + ": found revision in cache: " + mrid + " (resolved by " + rmr.getResolver().getName() + "): but it's a default one, maybe we can find a better one");
                  } else {
                     if (!this.isCheckmodified(dd, mrid, options) && !this.isChanging(dd, mrid, options)) {
                        Message.verbose("\t" + this.getName() + ": revision in cache: " + mrid);
                        rmr.getReport().setSearched(true);
                        ResolvedModuleRevision var36 = rmr;
                        return var36;
                     }

                     long repLastModified = mdRef.getLastModified();
                     long cacheLastModified = rmr.getDescriptor().getLastModified();
                     if (!rmr.getDescriptor().isDefault() && repLastModified <= cacheLastModified) {
                        Message.verbose("\t" + this.getName() + ": revision in cache (not updated): " + mrid);
                        rmr.getReport().setSearched(true);
                        ResolvedModuleRevision var44 = rmr;
                        return var44;
                     }

                     Message.verbose("\t" + this.getName() + ": revision in cache is not up to date: " + mrid);
                     if (this.isChanging(dd, mrid, options)) {
                        cachedPublicationDate = rmr.getDescriptor().getResolvedPublicationDate();
                     }
                  }
               }

               Artifact originalMetadataArtifact = this.getOriginalMetadataArtifact(moduleArtifact);
               ArtifactDownloadReport report = this.download(originalMetadataArtifact, new ArtifactResourceResolver() {
                  public ResolvedResource resolve(Artifact artifact) {
                     return mdRef;
                  }
               }, backupDownloader, (new CacheDownloadOptions()).setListener(options.getListener()).setForce(true));
               Message.verbose("\t" + report);
               if (report.getDownloadStatus() == DownloadStatus.FAILED) {
                  Message.warn("problem while downloading module descriptor: " + mdRef.getResource() + ": " + report.getDownloadDetails() + " (" + report.getDownloadTimeMillis() + "ms)");
                  Object ex = null;
                  return (ResolvedModuleRevision)ex;
               } else {
                  try {
                     ModuleDescriptorParser parser = ModuleDescriptorParserRegistry.getInstance().getParser(mdRef.getResource());
                     ParserSettings parserSettings = this.settings;
                     if (resolver instanceof AbstractResolver) {
                        parserSettings = ((AbstractResolver)resolver).getParserSettings();
                     }

                     ModuleDescriptor md = this.getStaledMd(parser, options, report.getLocalFile(), parserSettings);
                     if (md == null) {
                        throw new IllegalStateException("module descriptor parser returned a null module descriptor, which is not allowed. parser=" + parser + "; parser class=" + parser.getClass().getName() + "; module descriptor resource=" + mdRef.getResource());
                     } else {
                        Message.debug("\t" + this.getName() + ": parsed downloaded md file for " + mrid + "; parsed=" + md.getModuleRevisionId());
                        boolean deleteOldArtifacts = false;
                        if (cachedPublicationDate != null && !cachedPublicationDate.equals(md.getResolvedPublicationDate())) {
                           Message.verbose(mrid + " has changed: deleting old artifacts");
                           deleteOldArtifacts = true;
                        }

                        if (deleteOldArtifacts) {
                           for(String conf : md.getConfigurationsNames()) {
                              for(Artifact art : md.getArtifacts(conf)) {
                                 Artifact transformedArtifact = NameSpaceHelper.transform(art, options.getNamespace().getToSystemTransformer());
                                 ArtifactOrigin origin = this.getSavedArtifactOrigin(transformedArtifact);
                                 File artFile = this.getArchiveFileInCache(transformedArtifact, origin, false);
                                 if (artFile.exists()) {
                                    Message.debug("deleting " + artFile);
                                    if (!artFile.delete()) {
                                       backupDownloader.restore();
                                       Message.error("Couldn't delete outdated artifact from cache: " + artFile);
                                       Object var28 = null;
                                       return (ResolvedModuleRevision)var28;
                                    }
                                 }

                                 this.removeSavedArtifactOrigin(transformedArtifact);
                              }
                           }
                        } else if (this.isChanging(dd, mrid, options)) {
                           Message.verbose(mrid + " is changing, but has not changed: will trust cached artifacts if any");
                        }

                        MetadataArtifactDownloadReport madr = new MetadataArtifactDownloadReport(md.getMetadataArtifact());
                        madr.setSearched(true);
                        madr.setDownloadStatus(report.getDownloadStatus());
                        madr.setDownloadDetails(report.getDownloadDetails());
                        madr.setArtifactOrigin(report.getArtifactOrigin());
                        madr.setDownloadTimeMillis(report.getDownloadTimeMillis());
                        madr.setOriginalLocalFile(report.getLocalFile());
                        madr.setSize(report.getSize());
                        Artifact transformedMetadataArtifact = NameSpaceHelper.transform(md.getMetadataArtifact(), options.getNamespace().getToSystemTransformer());
                        this.saveArtifactOrigin(transformedMetadataArtifact, report.getArtifactOrigin());
                        ResolvedModuleRevision var48 = new ResolvedModuleRevision(resolver, resolver, md, madr);
                        return var48;
                     }
                  } catch (IOException ex) {
                     Message.warn("io problem while parsing ivy file: " + mdRef.getResource(), ex);
                     Object parserSettings = null;
                     return (ResolvedModuleRevision)parserSettings;
                  }
               }
            } else {
               if (this.isChanging(dd, mrid, options)) {
                  long repoLastModified = mdRef.getLastModified();
                  Artifact transformedArtifact = NameSpaceHelper.transform(moduleArtifact, options.getNamespace().getToSystemTransformer());
                  ArtifactOrigin origin = this.getSavedArtifactOrigin(transformedArtifact);
                  File artFile = this.getArchiveFileInCache(transformedArtifact, origin, false);
                  if (artFile.exists() && repoLastModified > artFile.lastModified()) {
                     Message.verbose(mrid + " has changed: deleting old artifacts");
                     Message.debug("deleting " + artFile);
                     if (!artFile.delete()) {
                        Message.error("Couldn't delete outdated artifact from cache: " + artFile);
                        Object var16 = null;
                        return (ResolvedModuleRevision)var16;
                     }

                     this.removeSavedArtifactOrigin(transformedArtifact);
                  }
               }

               Object rmr = null;
               return (ResolvedModuleRevision)rmr;
            }
         } finally {
            this.unlockMetadataArtifact(mrid);
            backupDownloader.cleanUp();
         }
      }
   }

   private boolean lockMetadataArtifact(ModuleRevisionId mrid) {
      Artifact artifact = this.getDefaultMetadataArtifact(mrid);

      try {
         return this.getLockStrategy().lockArtifact(artifact, this.getArchiveFileInCache(artifact, this.getDefaultMetadataArtifactOrigin(mrid)));
      } catch (InterruptedException var4) {
         Thread.currentThread().interrupt();
         throw new RuntimeException("operation interrupted");
      }
   }

   private void unlockMetadataArtifact(ModuleRevisionId mrid) {
      Artifact artifact = this.getDefaultMetadataArtifact(mrid);
      this.getLockStrategy().unlockArtifact(artifact, this.getArchiveFileInCache(artifact, this.getDefaultMetadataArtifactOrigin(mrid)));
   }

   private ArtifactOrigin getDefaultMetadataArtifactOrigin(ModuleRevisionId mrid) {
      String location;
      try {
         location = this.getIvyFileInCache(mrid).toURI().toURL().toExternalForm();
      } catch (MalformedURLException var4) {
         throw new RuntimeException("Failed to determine artifact origin for " + mrid);
      }

      return new ArtifactOrigin(DefaultArtifact.newIvyArtifact(mrid, (Date)null), false, location);
   }

   private Artifact getDefaultMetadataArtifact(ModuleRevisionId mrid) {
      return new DefaultArtifact(mrid, new Date(), "metadata", "metadata", "ivy", true);
   }

   public Artifact getOriginalMetadataArtifact(Artifact moduleArtifact) {
      return DefaultArtifact.cloneWithAnotherType(moduleArtifact, moduleArtifact.getType() + ".original");
   }

   private boolean isOriginalMetadataArtifact(Artifact artifact) {
      return artifact.isMetadata() && artifact.getType().endsWith(".original");
   }

   private boolean isChanging(DependencyDescriptor dd, ModuleRevisionId requestedRevisionId, CacheMetadataOptions options) {
      return dd.isChanging() || this.getChangingMatcher(options).matches(requestedRevisionId.getRevision());
   }

   private org.apache.ivy.plugins.matcher.Matcher getChangingMatcher(CacheMetadataOptions options) {
      String changingPattern = options.getChangingPattern() != null ? options.getChangingPattern() : this.changingPattern;
      if (changingPattern == null) {
         return NoMatcher.INSTANCE;
      } else {
         String changingMatcherName = options.getChangingMatcherName() != null ? options.getChangingMatcherName() : this.changingMatcherName;
         PatternMatcher matcher = this.settings.getMatcher(changingMatcherName);
         if (matcher == null) {
            throw new IllegalStateException("unknown matcher '" + changingMatcherName + "'. It is set as changing matcher in " + this);
         } else {
            return matcher.getMatcher(changingPattern);
         }
      }
   }

   private boolean isCheckmodified(DependencyDescriptor dd, ModuleRevisionId requestedRevisionId, CacheMetadataOptions options) {
      return options.isCheckmodified() == null ? this.isCheckmodified() : options.isCheckmodified();
   }

   public void clean() {
      FileUtil.forceDelete(this.getBasedir());
   }

   public void dumpSettings() {
      Message.verbose("\t" + this.getName());
      Message.debug("\t\tivyPattern: " + this.getIvyPattern());
      Message.debug("\t\tartifactPattern: " + this.getArtifactPattern());
      Message.debug("\t\tlockingStrategy: " + this.getLockStrategy().getName());
      Message.debug("\t\tchangingPattern: " + this.getChangingPattern());
      Message.debug("\t\tchangingMatcher: " + this.getChangingMatcherName());
   }

   public final void assertInsideCache(File fileInCache) {
      File root = this.getRepositoryCacheRoot();
      if (root != null && !FileUtil.isLeadingPath(root, fileInCache)) {
         throw new IllegalArgumentException(fileInCache + " is outside of the cache");
      }
   }

   private static String parseArtifactOriginFilePath(ArtifactOrigin origin) {
      if (origin != null && origin.getLocation() != null) {
         String location = origin.getLocation();
         if (!location.startsWith("file:")) {
            return location;
         } else {
            URI uri;
            try {
               uri = new URI(location);
            } catch (URISyntaxException var4) {
               return location;
            }

            if (!uri.isAbsolute()) {
               return location;
            } else {
               return uri.getScheme().equals("file") ? uri.getPath() : location;
            }
         }
      } else {
         return null;
      }
   }

   static {
      try {
         SHA_DIGEST = MessageDigest.getInstance("SHA1");
      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException("The SHA1 algorithm is not available in your classpath", e);
      }

      DURATION_PATTERN = Pattern.compile("(?:(\\d+)d)? ?(?:(\\d+)h)? ?(?:(\\d+)m)? ?(?:(\\d+)s)? ?(?:(\\d+)ms)?");
      ARTIFACT_KEY_PATTERN = Pattern.compile(".*:(.*)#(.*)#(.*)#(.*)(\\.location)?");
   }

   private class MyModuleDescriptorProvider implements ModuleDescriptorProvider {
      private final ModuleDescriptorParser mdParser;
      private final ParserSettings settings;

      public MyModuleDescriptorProvider(ModuleDescriptorParser mdParser, ParserSettings settings) {
         this.mdParser = mdParser;
         this.settings = settings;
      }

      public ModuleDescriptor provideModule(ParserSettings ivySettings, File descriptorURL, boolean validate) throws ParseException, IOException {
         return this.mdParser.parseDescriptor(this.settings, descriptorURL.toURI().toURL(), validate);
      }
   }

   private final class BackupResourceDownloader implements ResourceDownloader {
      private ResourceDownloader delegate;
      private File backup;
      private String originalPath;

      private BackupResourceDownloader(ResourceDownloader delegate) {
         this.delegate = delegate;
      }

      public void download(Artifact artifact, Resource resource, File dest) throws IOException {
         if (dest.exists()) {
            this.originalPath = dest.getAbsolutePath();
            this.backup = new File(dest.getAbsolutePath() + ".backup");
            FileUtil.copy((File)dest, (File)this.backup, (CopyProgressListener)null, true);
         }

         this.delegate.download(artifact, resource, dest);
      }

      public void restore() throws IOException {
         if (this.backup != null && this.backup.exists()) {
            File original = new File(this.originalPath);
            FileUtil.copy((File)this.backup, (File)original, (CopyProgressListener)null, true);
            this.backup.delete();
         }

      }

      public void cleanUp() {
         if (this.backup != null && this.backup.exists()) {
            this.backup.delete();
         }

      }
   }

   private static final class ConfiguredTTL {
      private static final Set attributesNotContributingToMatching = new HashSet();
      private final String matcher;
      private final long duration;
      private final Map attributes;

      private ConfiguredTTL(long duration, String matcher, Map attributes) {
         this.matcher = matcher;
         this.duration = duration;
         if (attributes == null) {
            this.attributes = Collections.emptyMap();
         } else {
            Map<String, String> attrs = new HashMap(attributes);

            for(String removable : attributesNotContributingToMatching) {
               attrs.remove(removable);
            }

            this.attributes = Collections.unmodifiableMap(attrs);
         }

      }

      static {
         attributesNotContributingToMatching.add("duration");
         attributesNotContributingToMatching.add("matcher");
      }
   }
}
