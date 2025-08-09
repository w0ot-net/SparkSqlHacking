package org.apache.ivy.core.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessControlException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.NormalRelativeUrlResolver;
import org.apache.ivy.core.RelativeUrlResolver;
import org.apache.ivy.core.cache.CacheUtil;
import org.apache.ivy.core.cache.DefaultRepositoryCacheManager;
import org.apache.ivy.core.cache.DefaultResolutionCacheManager;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.check.CheckEngineSettings;
import org.apache.ivy.core.deliver.DeliverEngineSettings;
import org.apache.ivy.core.install.InstallEngineSettings;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.id.ModuleRules;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.core.pack.ArchivePacking;
import org.apache.ivy.core.pack.PackingRegistry;
import org.apache.ivy.core.publish.PublishEngineSettings;
import org.apache.ivy.core.repository.RepositoryManagementEngineSettings;
import org.apache.ivy.core.resolve.ResolveEngineSettings;
import org.apache.ivy.core.retrieve.RetrieveEngineSettings;
import org.apache.ivy.core.sort.SortEngineSettings;
import org.apache.ivy.osgi.core.OsgiLatestStrategy;
import org.apache.ivy.plugins.IvySettingsAware;
import org.apache.ivy.plugins.circular.CircularDependencyStrategy;
import org.apache.ivy.plugins.circular.ErrorCircularDependencyStrategy;
import org.apache.ivy.plugins.circular.IgnoreCircularDependencyStrategy;
import org.apache.ivy.plugins.circular.WarnCircularDependencyStrategy;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.conflict.LatestCompatibleConflictManager;
import org.apache.ivy.plugins.conflict.LatestConflictManager;
import org.apache.ivy.plugins.conflict.NoConflictManager;
import org.apache.ivy.plugins.conflict.StrictConflictManager;
import org.apache.ivy.plugins.latest.LatestLexicographicStrategy;
import org.apache.ivy.plugins.latest.LatestRevisionStrategy;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.plugins.latest.LatestTimeStrategy;
import org.apache.ivy.plugins.latest.WorkspaceLatestStrategy;
import org.apache.ivy.plugins.lock.CreateFileLockStrategy;
import org.apache.ivy.plugins.lock.LockStrategy;
import org.apache.ivy.plugins.lock.NIOFileLockStrategy;
import org.apache.ivy.plugins.lock.NoLockStrategy;
import org.apache.ivy.plugins.matcher.ExactOrRegexpPatternMatcher;
import org.apache.ivy.plugins.matcher.ExactPatternMatcher;
import org.apache.ivy.plugins.matcher.MapMatcher;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.matcher.RegexpPatternMatcher;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.report.LogReportOutputter;
import org.apache.ivy.plugins.report.ReportOutputter;
import org.apache.ivy.plugins.report.XmlReportOutputter;
import org.apache.ivy.plugins.resolver.AbstractWorkspaceResolver;
import org.apache.ivy.plugins.resolver.ChainResolver;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.plugins.resolver.DualResolver;
import org.apache.ivy.plugins.resolver.ResolverSettings;
import org.apache.ivy.plugins.resolver.WorkspaceChainResolver;
import org.apache.ivy.plugins.signer.SignatureGenerator;
import org.apache.ivy.plugins.trigger.Trigger;
import org.apache.ivy.plugins.version.ChainVersionMatcher;
import org.apache.ivy.plugins.version.ExactVersionMatcher;
import org.apache.ivy.plugins.version.LatestVersionMatcher;
import org.apache.ivy.plugins.version.SubVersionMatcher;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.plugins.version.VersionRangeMatcher;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.url.URLHandlerRegistry;

public class IvySettings implements SortEngineSettings, PublishEngineSettings, ParserSettings, DeliverEngineSettings, CheckEngineSettings, InstallEngineSettings, ResolverSettings, ResolveEngineSettings, RetrieveEngineSettings, RepositoryManagementEngineSettings {
   private static final long INTERRUPT_TIMEOUT = 2000L;
   private Map typeDefs;
   private Map resolversMap;
   private DependencyResolver defaultResolver;
   private DependencyResolver dictatorResolver;
   private String defaultResolverName;
   private File defaultCache;
   private String defaultBranch;
   private boolean checkUpToDate;
   private ModuleRules moduleSettings;
   private Map conflictsManager;
   private Map latestStrategies;
   private Map lockStrategies;
   private Map namespaces;
   private Map matchers;
   private Map reportOutputters;
   private Map versionMatchers;
   private Map circularDependencyStrategies;
   private Map repositoryCacheManagers;
   private Map signatureGenerators;
   private List triggers;
   private IvyVariableContainer variableContainer;
   private boolean validate;
   private LatestStrategy defaultLatestStrategy;
   private LockStrategy defaultLockStrategy;
   private ConflictManager defaultConflictManager;
   private CircularDependencyStrategy circularDependencyStrategy;
   private RepositoryCacheManager defaultRepositoryCacheManager;
   private ResolutionCacheManager resolutionCacheManager;
   private List listingIgnore;
   private boolean repositoriesConfigured;
   private boolean useRemoteConfig;
   private File defaultUserDir;
   private File baseDir;
   private List classpathURLs;
   private ClassLoader classloader;
   private Boolean debugConflictResolution;
   private boolean logNotConvertedExclusionRule;
   private VersionMatcher versionMatcher;
   private StatusManager statusManager;
   private Boolean debugLocking;
   private Boolean dumpMemoryUsage;
   private String defaultCacheIvyPattern;
   private String defaultCacheArtifactPattern;
   private boolean defaultUseOrigin;
   private String defaultResolveMode;
   private PackingRegistry packingRegistry;
   private AbstractWorkspaceResolver workspaceResolver;
   private final Map timeoutConstraints;

   public IvySettings() {
      this(new IvyVariableContainerImpl());
   }

   public IvySettings(IvyVariableContainer variableContainer) {
      this.typeDefs = new HashMap();
      this.resolversMap = new HashMap();
      this.dictatorResolver = null;
      this.defaultBranch = null;
      this.checkUpToDate = true;
      this.moduleSettings = new ModuleRules();
      this.conflictsManager = new HashMap();
      this.latestStrategies = new HashMap();
      this.lockStrategies = new HashMap();
      this.namespaces = new HashMap();
      this.matchers = new HashMap();
      this.reportOutputters = new HashMap();
      this.versionMatchers = new HashMap();
      this.circularDependencyStrategies = new HashMap();
      this.repositoryCacheManagers = new HashMap();
      this.signatureGenerators = new HashMap();
      this.triggers = new ArrayList();
      this.variableContainer = new IvyVariableContainerImpl();
      this.validate = true;
      this.defaultLatestStrategy = null;
      this.defaultLockStrategy = null;
      this.defaultConflictManager = null;
      this.circularDependencyStrategy = null;
      this.defaultRepositoryCacheManager = null;
      this.resolutionCacheManager = null;
      this.listingIgnore = new ArrayList();
      this.useRemoteConfig = false;
      this.baseDir = (new File(".")).getAbsoluteFile();
      this.classpathURLs = new ArrayList();
      this.defaultResolveMode = "default";
      this.packingRegistry = new PackingRegistry();
      this.timeoutConstraints = new HashMap();
      this.setVariableContainer(variableContainer);
      this.setVariable("ivy.default.settings.dir", this.getDefaultSettingsDir(), true);
      this.setVariable("ivy.basedir", this.getBaseDir().getAbsolutePath());
      this.setDeprecatedVariable("ivy.default.conf.dir", "ivy.default.settings.dir");
      String ivyTypeDefs = System.getProperty("ivy.typedef.files");
      if (ivyTypeDefs != null) {
         for(String file : StringUtils.splitToArray(ivyTypeDefs)) {
            try {
               this.typeDefs((InputStream)(new FileInputStream(Checks.checkAbsolute(file, "ivy.typedef.files"))), true);
            } catch (FileNotFoundException var11) {
               Message.warn("typedefs file not found: " + file);
            } catch (IOException e) {
               Message.warn("problem with typedef file: " + file, e);
            }
         }
      } else {
         try {
            this.typeDefs(getSettingsURL("typedef.properties").openStream(), true);
         } catch (IOException e) {
            Message.warn("impossible to load default type defs", e);
         }
      }

      LatestLexicographicStrategy latestLexicographicStrategy = new LatestLexicographicStrategy();
      LatestRevisionStrategy latestRevisionStrategy = new LatestRevisionStrategy();
      LatestTimeStrategy latestTimeStrategy = new LatestTimeStrategy();
      OsgiLatestStrategy osgiLatestStrategy = new OsgiLatestStrategy();
      this.addLatestStrategy("latest-revision", latestRevisionStrategy);
      this.addLatestStrategy("latest-lexico", latestLexicographicStrategy);
      this.addLatestStrategy("latest-time", latestTimeStrategy);
      this.addLatestStrategy("latest-osgi", osgiLatestStrategy);
      this.addLockStrategy("no-lock", new NoLockStrategy());
      this.addLockStrategy("artifact-lock", new CreateFileLockStrategy(this.debugLocking()));
      this.addLockStrategy("artifact-lock-nio", new NIOFileLockStrategy(this.debugLocking()));
      this.addConflictManager("latest-revision", new LatestConflictManager("latest-revision", latestRevisionStrategy));
      this.addConflictManager("latest-compatible", new LatestCompatibleConflictManager("latest-compatible", latestRevisionStrategy));
      this.addConflictManager("latest-time", new LatestConflictManager("latest-time", latestTimeStrategy));
      this.addConflictManager("all", new NoConflictManager());
      this.addConflictManager("strict", new StrictConflictManager());
      this.addMatcher(ExactPatternMatcher.INSTANCE);
      this.addMatcher(RegexpPatternMatcher.INSTANCE);
      this.addMatcher(ExactOrRegexpPatternMatcher.INSTANCE);

      try {
         Class<? extends PatternMatcher> globClazz = IvySettings.class.getClassLoader().loadClass("org.apache.ivy.plugins.matcher.GlobPatternMatcher");
         Field instanceField = globClazz.getField("INSTANCE");
         this.addMatcher((PatternMatcher)instanceField.get((Object)null));
      } catch (Exception e) {
         Message.info("impossible to define glob matcher: org.apache.ivy.plugins.matcher.GlobPatternMatcher was not found", e);
      }

      this.addReportOutputter(new LogReportOutputter());
      this.addReportOutputter(new XmlReportOutputter());
      this.configureDefaultCircularDependencyStrategies();
      this.listingIgnore.add(".cvsignore");
      this.listingIgnore.add("CVS");
      this.listingIgnore.add(".svn");
      this.listingIgnore.add("maven-metadata.xml");
      this.listingIgnore.add("maven-metadata.xml.md5");
      this.listingIgnore.add("maven-metadata.xml.sha1");
      this.addSystemProperties();
   }

   private synchronized void addSystemProperties() {
      try {
         this.addAllVariables((Map)System.getProperties().clone());
      } catch (AccessControlException ex) {
         Message.verbose("access denied to getting all system properties: they won't be available as Ivy variables.\nset " + ex.getPermission() + " permission if you want to access them");
      }

   }

   public synchronized void configureRepositories(boolean remote) {
      if (!this.repositoriesConfigured) {
         Properties props = new Properties();
         boolean configured = false;
         if (this.useRemoteConfig && remote) {
            try {
               URL url = new URL("https://ant.apache.org/ivy/repository.properties");
               Message.verbose("configuring repositories with " + url);
               props.load(URLHandlerRegistry.getDefault().openStream(url));
               configured = true;
            } catch (Exception ex) {
               Message.verbose("unable to use remote repository configuration", ex);
               props = new Properties();
            }
         }

         if (!configured) {
            InputStream repositoryPropsStream = null;

            try {
               repositoryPropsStream = getSettingsURL("repository.properties").openStream();
               props.load(repositoryPropsStream);
            } catch (IOException e) {
               Message.error("unable to use internal repository configuration", e);
               if (repositoryPropsStream != null) {
                  try {
                     repositoryPropsStream.close();
                  } catch (Exception var7) {
                  }
               }
            }
         }

         this.addAllVariables(props, false);
         this.repositoriesConfigured = true;
      }

   }

   public synchronized void typeDefs(InputStream stream) throws IOException {
      this.typeDefs(stream, false);
   }

   public synchronized void typeDefs(InputStream stream, boolean silentFail) throws IOException {
      try {
         Properties p = new Properties();
         p.load(stream);
         this.typeDefs(p, silentFail);
      } finally {
         stream.close();
      }

   }

   public synchronized void typeDefs(Properties p) {
      this.typeDefs(p, false);
   }

   public synchronized void typeDefs(Properties p, boolean silentFail) {
      for(Map.Entry entry : p.entrySet()) {
         this.typeDef(entry.getKey().toString(), entry.getValue().toString(), silentFail);
      }

   }

   public synchronized void load(File settingsFile) throws ParseException, IOException {
      Message.info(":: loading settings :: file = " + settingsFile);
      long start = System.currentTimeMillis();
      this.setSettingsVariables(settingsFile);
      if (this.getVariable("ivy.default.ivy.user.dir") != null) {
         this.setDefaultIvyUserDir(Checks.checkAbsolute(this.getVariable("ivy.default.ivy.user.dir"), "ivy.default.ivy.user.dir"));
      } else {
         this.getDefaultIvyUserDir();
      }

      this.loadDefaultProperties();

      try {
         (new XmlSettingsParser(this)).parse(settingsFile.toURI().toURL());
      } catch (MalformedURLException e) {
         throw new IllegalArgumentException("given file cannot be transformed to url: " + settingsFile, e);
      }

      this.setVariable("ivy.default.ivy.user.dir", this.getDefaultIvyUserDir().getAbsolutePath(), false);
      Message.verbose("settings loaded (" + (System.currentTimeMillis() - start) + "ms)");
      this.dumpSettings();
   }

   public synchronized void load(URL settingsURL) throws ParseException, IOException {
      Message.info(":: loading settings :: url = " + settingsURL);
      long start = System.currentTimeMillis();
      this.setSettingsVariables(settingsURL);
      if (this.getVariable("ivy.default.ivy.user.dir") != null) {
         this.setDefaultIvyUserDir(Checks.checkAbsolute(this.getVariable("ivy.default.ivy.user.dir"), "ivy.default.ivy.user.dir"));
      } else {
         this.getDefaultIvyUserDir();
      }

      this.loadDefaultProperties();
      (new XmlSettingsParser(this)).parse(settingsURL);
      this.setVariable("ivy.default.ivy.user.dir", this.getDefaultIvyUserDir().getAbsolutePath(), false);
      Message.verbose("settings loaded (" + (System.currentTimeMillis() - start) + "ms)");
      this.dumpSettings();
   }

   public synchronized void defaultInit() throws IOException {
      if (this.getVariable("ivy.default.ivy.user.dir") != null) {
         this.setDefaultIvyUserDir(Checks.checkAbsolute(this.getVariable("ivy.default.ivy.user.dir"), "ivy.default.ivy.user.dir"));
      } else {
         this.getDefaultIvyUserDir();
      }

      this.getDefaultCache();
      this.loadDefaultProperties();
      this.setVariable("ivy.default.ivy.user.dir", this.getDefaultIvyUserDir().getAbsolutePath(), false);
      this.dumpSettings();
   }

   public synchronized void loadDefault() throws ParseException, IOException {
      this.load(getDefaultSettingsURL());
   }

   public synchronized void loadDefault14() throws ParseException, IOException {
      this.load(getDefault14SettingsURL());
   }

   private void loadDefaultProperties() throws IOException {
      this.loadProperties(getDefaultPropertiesURL(), false);
   }

   public static URL getDefaultPropertiesURL() {
      return getSettingsURL("ivy.properties");
   }

   public static URL getDefaultSettingsURL() {
      return getSettingsURL("ivysettings.xml");
   }

   public static URL getDefault14SettingsURL() {
      return getSettingsURL("ivysettings-1.4.xml");
   }

   private String getDefaultSettingsDir() {
      String ivysettingsLocation = getDefaultSettingsURL().toExternalForm();
      return ivysettingsLocation.substring(0, ivysettingsLocation.length() - "ivysettings.xml".length() - 1);
   }

   private static URL getSettingsURL(String file) {
      return XmlSettingsParser.class.getResource(file);
   }

   public synchronized void setSettingsVariables(File settingsFile) {
      try {
         this.setVariable("ivy.settings.dir", (new File(settingsFile.getAbsolutePath())).getParent());
         this.setDeprecatedVariable("ivy.conf.dir", "ivy.settings.dir");
         this.setVariable("ivy.settings.file", settingsFile.getAbsolutePath());
         this.setDeprecatedVariable("ivy.conf.file", "ivy.settings.file");
         this.setVariable("ivy.settings.url", settingsFile.toURI().toURL().toExternalForm());
         this.setDeprecatedVariable("ivy.conf.url", "ivy.settings.url");
         this.setVariable("ivy.settings.dir.url", (new File(settingsFile.getAbsolutePath())).getParentFile().toURI().toURL().toExternalForm());
      } catch (MalformedURLException e) {
         throw new IllegalArgumentException("given file cannot be transformed to url: " + settingsFile, e);
      }
   }

   private void setDeprecatedVariable(String deprecatedKey, String newKey) {
      this.setVariable(deprecatedKey, this.getVariable(newKey));
   }

   public synchronized void setSettingsVariables(URL settingsURL) {
      String settingsURLStr = settingsURL.toExternalForm();
      this.setVariable("ivy.settings.url", settingsURLStr);
      this.setDeprecatedVariable("ivy.conf.url", "ivy.settings.url");
      int slashIndex = settingsURLStr.lastIndexOf(47);
      if (slashIndex != -1) {
         String dirUrl = settingsURLStr.substring(0, slashIndex);
         this.setVariable("ivy.settings.dir", dirUrl);
         this.setVariable("ivy.settings.dir.url", dirUrl);
         this.setDeprecatedVariable("ivy.conf.dir", "ivy.settings.dir");
      } else {
         Message.warn("settings url does not contain any slash (/): ivy.settings.dir variable not set");
      }

   }

   private void dumpSettings() {
      Message.verbose("\tdefault cache: " + this.getDefaultCache());
      Message.verbose("\tdefault resolver: " + this.getDefaultResolver());
      Message.debug("\tdefault latest strategy: " + this.getDefaultLatestStrategy());
      Message.debug("\tdefault conflict manager: " + this.getDefaultConflictManager());
      Message.debug("\tcircular dependency strategy: " + this.getCircularDependencyStrategy());
      Message.debug("\tvalidate: " + this.doValidate());
      Message.debug("\tcheck up2date: " + this.isCheckUpToDate());
      if (!this.classpathURLs.isEmpty()) {
         Message.verbose("\t-- " + this.classpathURLs.size() + " custom classpath urls:");

         for(URL url : this.classpathURLs) {
            Message.debug("\t\t" + url);
         }
      }

      Message.verbose("\t-- " + this.resolversMap.size() + " resolvers:");

      for(DependencyResolver resolver : this.resolversMap.values()) {
         resolver.dumpSettings();
      }

      Message.debug("\tmodule settings:");
      this.moduleSettings.dump("\t\t");
   }

   public synchronized void loadProperties(URL url) throws IOException {
      this.loadProperties(url, true);
   }

   public synchronized void loadProperties(URL url, boolean overwrite) throws IOException {
      this.loadProperties(url.openStream(), overwrite);
   }

   public synchronized void loadProperties(File file) throws IOException {
      this.loadProperties(file, true);
   }

   public synchronized void loadProperties(File file, boolean overwrite) throws IOException {
      this.loadProperties((InputStream)(new FileInputStream(file)), overwrite);
   }

   private void loadProperties(InputStream stream, boolean overwrite) throws IOException {
      try {
         Properties properties = new Properties();
         properties.load(stream);
         this.addAllVariables(properties, overwrite);
      } finally {
         if (stream != null) {
            try {
               stream.close();
            } catch (IOException var9) {
            }
         }

      }

   }

   public synchronized void setVariable(String varName, String value) {
      this.setVariable(varName, value, true);
   }

   public synchronized void setVariable(String varName, String value, boolean overwrite) {
      this.setVariable(varName, value, overwrite, (String)null, (String)null);
   }

   public synchronized void setVariable(String varName, String value, boolean overwrite, String ifSetVar, String unlessSetVar) {
      if (ifSetVar != null && this.variableContainer.getVariable(ifSetVar) == null) {
         Message.verbose("Not setting '" + varName + "' to '" + value + "' since '" + ifSetVar + "' is not set.");
      } else if (unlessSetVar != null && this.variableContainer.getVariable(unlessSetVar) != null) {
         Message.verbose("Not setting '" + varName + "' to '" + value + "' since '" + unlessSetVar + "' is set.");
      } else {
         this.variableContainer.setVariable(varName, value, overwrite);
      }
   }

   public synchronized void addAllVariables(Map variables) {
      this.addAllVariables(variables, true);
   }

   public synchronized void addAllVariables(Map variables, boolean overwrite) {
      for(Map.Entry entry : variables.entrySet()) {
         Object val = entry.getValue();
         if (val == null || val instanceof String) {
            this.setVariable(entry.getKey().toString(), (String)val, overwrite);
         }
      }

   }

   public synchronized String substitute(String str) {
      return IvyPatternHelper.substituteVariables(str, this.variableContainer);
   }

   public synchronized Map substitute(Map strings) {
      Map<String, String> substituted = new LinkedHashMap();

      for(Map.Entry entry : strings.entrySet()) {
         substituted.put(entry.getKey(), this.substitute((String)entry.getValue()));
      }

      return substituted;
   }

   public synchronized IvyVariableContainer getVariables() {
      return this.variableContainer;
   }

   public synchronized Class typeDef(String name, String className) {
      return this.typeDef(name, className, false);
   }

   public synchronized Class typeDef(String name, String className, boolean silentFail) {
      Class<?> clazz = this.classForName(className, silentFail);
      if (clazz != null) {
         this.typeDefs.put(name, clazz);
      }

      return clazz;
   }

   private Class classForName(String className, boolean silentFail) {
      try {
         return this.getClassLoader().loadClass(className);
      } catch (ClassNotFoundException var4) {
         if (silentFail) {
            Message.info("impossible to define new type: class not found: " + className + " in " + this.classpathURLs + " nor Ivy classloader");
            return null;
         } else {
            throw new RuntimeException("impossible to define new type: class not found: " + className + " in " + this.classpathURLs + " nor Ivy classloader");
         }
      }
   }

   private ClassLoader getClassLoader() {
      if (this.classloader == null) {
         if (this.classpathURLs.isEmpty()) {
            this.classloader = Ivy.class.getClassLoader();
         } else {
            this.classloader = new URLClassLoader((URL[])this.classpathURLs.toArray(new URL[this.classpathURLs.size()]), Ivy.class.getClassLoader());
         }
      }

      return this.classloader;
   }

   public synchronized void addClasspathURL(URL url) {
      this.classpathURLs.add(url);
      this.classloader = null;
   }

   public synchronized Map getTypeDefs() {
      return this.typeDefs;
   }

   public synchronized Class getTypeDef(String name) {
      return (Class)this.typeDefs.get(name);
   }

   public synchronized void addConfigured(DependencyResolver resolver) {
      this.addResolver(resolver);
   }

   public synchronized void addConfigured(ModuleDescriptorParser parser) {
      ModuleDescriptorParserRegistry.getInstance().addParser(parser);
   }

   public synchronized void addConfigured(SignatureGenerator generator) {
      this.addSignatureGenerator(generator);
   }

   public synchronized void addSignatureGenerator(SignatureGenerator generator) {
      this.init(generator);
      this.signatureGenerators.put(generator.getName(), generator);
   }

   public synchronized SignatureGenerator getSignatureGenerator(String name) {
      return (SignatureGenerator)this.signatureGenerators.get(name);
   }

   public synchronized void addResolver(DependencyResolver resolver) {
      if (resolver == null) {
         throw new NullPointerException("null resolver");
      } else {
         this.init(resolver);
         this.resolversMap.put(resolver.getName(), resolver);
         if (resolver instanceof ChainResolver) {
            for(DependencyResolver dr : ((ChainResolver)resolver).getResolvers()) {
               this.addResolver(dr);
            }
         } else if (resolver instanceof DualResolver) {
            DependencyResolver ivyResolver = ((DualResolver)resolver).getIvyResolver();
            if (ivyResolver != null) {
               this.addResolver(ivyResolver);
            }

            DependencyResolver artifactResolver = ((DualResolver)resolver).getArtifactResolver();
            if (artifactResolver != null) {
               this.addResolver(artifactResolver);
            }
         }

      }
   }

   public synchronized void setDefaultCache(File cacheDirectory) {
      this.setVariable("ivy.cache.dir", cacheDirectory.getAbsolutePath(), false);
      this.defaultCache = cacheDirectory;
      if (this.defaultRepositoryCacheManager != null && "default-cache".equals(this.defaultRepositoryCacheManager.getName()) && this.defaultRepositoryCacheManager instanceof DefaultRepositoryCacheManager) {
         ((DefaultRepositoryCacheManager)this.defaultRepositoryCacheManager).setBasedir(this.defaultCache);
      }

   }

   public synchronized void setDefaultResolver(String resolverName) {
      this.checkResolverName(resolverName);
      if (resolverName != null && !resolverName.equals(this.defaultResolverName)) {
         this.defaultResolver = null;
      }

      this.defaultResolverName = resolverName;
   }

   private void checkResolverName(String resolverName) {
      if (resolverName != null && !this.resolversMap.containsKey(resolverName)) {
         throw new IllegalArgumentException("no resolver found called " + resolverName + ": check your settings");
      }
   }

   public synchronized void addModuleConfiguration(Map attributes, PatternMatcher matcher, String resolverName, String branch, String conflictManager, String resolveMode) {
      this.checkResolverName(resolverName);
      this.moduleSettings.defineRule(new MapMatcher(attributes, matcher), new ModuleSettings(resolverName, branch, conflictManager, resolveMode));
   }

   public synchronized File resolveFile(String fileName) {
      return FileUtil.resolveFile(this.baseDir, fileName);
   }

   public synchronized void setBaseDir(File baseDir) {
      this.baseDir = baseDir.getAbsoluteFile();
      this.setVariable("ivy.basedir", this.baseDir.getAbsolutePath());
      this.setVariable("basedir", this.baseDir.getAbsolutePath(), false);
   }

   public synchronized File getBaseDir() {
      return this.baseDir;
   }

   public synchronized File getDefaultIvyUserDir() {
      if (this.defaultUserDir == null) {
         if (this.getVariable("ivy.home") != null) {
            this.setDefaultIvyUserDir(Checks.checkAbsolute(this.getVariable("ivy.home"), "ivy.home"));
            Message.verbose("using ivy.default.ivy.user.dir variable for default ivy user dir: " + this.defaultUserDir);
         } else {
            this.setDefaultIvyUserDir(new File(System.getProperty("user.home"), ".ivy2"));
            Message.verbose("no default ivy user dir defined: set to " + this.defaultUserDir);
         }
      }

      return this.defaultUserDir;
   }

   public synchronized void setDefaultIvyUserDir(File defaultUserDir) {
      this.defaultUserDir = defaultUserDir;
      this.setVariable("ivy.default.ivy.user.dir", this.defaultUserDir.getAbsolutePath());
      this.setVariable("ivy.home", this.defaultUserDir.getAbsolutePath());
   }

   public synchronized File getDefaultCache() {
      if (this.defaultCache == null) {
         String cache = this.getVariable("ivy.cache.dir");
         if (cache != null) {
            this.defaultCache = Checks.checkAbsolute(cache, "ivy.cache.dir");
         } else {
            this.setDefaultCache(new File(this.getDefaultIvyUserDir(), "cache"));
            Message.verbose("no default cache defined: set to " + this.defaultCache);
         }
      }

      return this.defaultCache;
   }

   public synchronized void setDefaultRepositoryCacheBasedir(String repositoryCacheRoot) {
      this.setVariable("ivy.cache.repository", repositoryCacheRoot, true);
      if (this.defaultRepositoryCacheManager != null && "default-cache".equals(this.defaultRepositoryCacheManager.getName()) && this.defaultRepositoryCacheManager instanceof DefaultRepositoryCacheManager) {
         ((DefaultRepositoryCacheManager)this.defaultRepositoryCacheManager).setBasedir(this.getDefaultRepositoryCacheBasedir());
      }

   }

   public synchronized void setDefaultResolutionCacheBasedir(String resolutionCacheRoot) {
      this.setVariable("ivy.cache.resolution", resolutionCacheRoot, true);
      if (this.resolutionCacheManager != null && this.resolutionCacheManager instanceof DefaultResolutionCacheManager) {
         ((DefaultResolutionCacheManager)this.resolutionCacheManager).setBasedir(this.getDefaultResolutionCacheBasedir());
      }

   }

   public synchronized File getDefaultRepositoryCacheBasedir() {
      String repositoryCacheRoot = this.getVariable("ivy.cache.repository");
      return repositoryCacheRoot != null ? Checks.checkAbsolute(repositoryCacheRoot, "ivy.cache.repository") : this.getDefaultCache();
   }

   public synchronized File getDefaultResolutionCacheBasedir() {
      String resolutionCacheRoot = this.getVariable("ivy.cache.resolution");
      return resolutionCacheRoot != null ? Checks.checkAbsolute(resolutionCacheRoot, "ivy.cache.resolution") : this.getDefaultCache();
   }

   public synchronized void setDictatorResolver(DependencyResolver resolver) {
      this.dictatorResolver = resolver;
   }

   private DependencyResolver getDictatorResolver() {
      if (this.dictatorResolver == null) {
         return null;
      } else {
         if (this.workspaceResolver != null && !(this.dictatorResolver instanceof WorkspaceChainResolver)) {
            this.dictatorResolver = new WorkspaceChainResolver(this, this.dictatorResolver, this.workspaceResolver);
         }

         return this.dictatorResolver;
      }
   }

   public synchronized DependencyResolver getResolver(ModuleRevisionId mrid) {
      DependencyResolver r = this.getDictatorResolver();
      if (r != null) {
         return r;
      } else {
         String resolverName = this.getResolverName(mrid);
         return this.getResolver(resolverName);
      }
   }

   public synchronized boolean hasResolver(String resolverName) {
      return this.resolversMap.containsKey(resolverName);
   }

   public synchronized DependencyResolver getResolver(String resolverName) {
      DependencyResolver r = this.getDictatorResolver();
      if (r != null) {
         return r;
      } else {
         DependencyResolver resolver = (DependencyResolver)this.resolversMap.get(resolverName);
         if (resolver == null) {
            Message.error("unknown resolver " + resolverName);
         } else if (this.workspaceResolver != null && !(resolver instanceof WorkspaceChainResolver)) {
            resolver = new WorkspaceChainResolver(this, resolver, this.workspaceResolver);
            this.resolversMap.put(resolver.getName(), resolver);
            this.resolversMap.put(resolverName, resolver);
         }

         return resolver;
      }
   }

   public synchronized DependencyResolver getDefaultResolver() {
      DependencyResolver r = this.getDictatorResolver();
      if (r != null) {
         return r;
      } else {
         if (this.defaultResolver == null) {
            this.defaultResolver = (DependencyResolver)this.resolversMap.get(this.defaultResolverName);
         }

         if (this.workspaceResolver != null && !(this.defaultResolver instanceof WorkspaceChainResolver)) {
            this.defaultResolver = new WorkspaceChainResolver(this, this.defaultResolver, this.workspaceResolver);
         }

         return this.defaultResolver;
      }
   }

   public synchronized String getResolverName(ModuleRevisionId mrid) {
      ModuleSettings ms = (ModuleSettings)this.moduleSettings.getRule(mrid, new Filter() {
         public boolean accept(ModuleSettings o) {
            return o.getResolverName() != null;
         }
      });
      return ms == null ? this.defaultResolverName : ms.getResolverName();
   }

   public synchronized String getDefaultBranch(ModuleId moduleId) {
      ModuleSettings ms = (ModuleSettings)this.moduleSettings.getRule(moduleId, new Filter() {
         public boolean accept(ModuleSettings o) {
            return o.getBranch() != null;
         }
      });
      return ms == null ? this.getDefaultBranch() : ms.getBranch();
   }

   public synchronized String getDefaultBranch() {
      return this.defaultBranch;
   }

   public synchronized void setDefaultBranch(String defaultBranch) {
      this.defaultBranch = defaultBranch;
   }

   public synchronized ConflictManager getConflictManager(ModuleId moduleId) {
      ModuleSettings ms = (ModuleSettings)this.moduleSettings.getRule(moduleId, new Filter() {
         public boolean accept(ModuleSettings o) {
            return o.getConflictManager() != null;
         }
      });
      if (ms == null) {
         return this.getDefaultConflictManager();
      } else {
         ConflictManager cm = this.getConflictManager(ms.getConflictManager());
         if (cm == null) {
            throw new IllegalStateException("ivy badly configured: unknown conflict manager " + ms.getConflictManager());
         } else {
            return cm;
         }
      }
   }

   public synchronized String getResolveMode(ModuleId moduleId) {
      ModuleSettings ms = (ModuleSettings)this.moduleSettings.getRule(moduleId, new Filter() {
         public boolean accept(ModuleSettings o) {
            return o.getResolveMode() != null;
         }
      });
      return ms == null ? this.getDefaultResolveMode() : ms.getResolveMode();
   }

   public synchronized String getDefaultResolveMode() {
      return this.defaultResolveMode;
   }

   public synchronized void setDefaultResolveMode(String defaultResolveMode) {
      this.defaultResolveMode = defaultResolveMode;
   }

   public synchronized void addConfigured(ConflictManager cm) {
      this.addConflictManager(cm.getName(), cm);
   }

   public synchronized ConflictManager getConflictManager(String name) {
      return "default".equals(name) ? this.getDefaultConflictManager() : (ConflictManager)this.conflictsManager.get(name);
   }

   public synchronized void addConflictManager(String name, ConflictManager cm) {
      this.init(cm);
      this.conflictsManager.put(name, cm);
   }

   public synchronized void addConfigured(LatestStrategy latest) {
      this.addLatestStrategy(latest.getName(), latest);
   }

   public synchronized LatestStrategy getLatestStrategy(String name) {
      if ("default".equals(name)) {
         return this.getDefaultLatestStrategy();
      } else {
         LatestStrategy strategy = (LatestStrategy)this.latestStrategies.get(name);
         if (this.workspaceResolver != null && !(strategy instanceof WorkspaceLatestStrategy)) {
            strategy = new WorkspaceLatestStrategy(strategy);
            this.latestStrategies.put(name, strategy);
         }

         return strategy;
      }
   }

   public synchronized void addLatestStrategy(String name, LatestStrategy latest) {
      this.init(latest);
      this.latestStrategies.put(name, latest);
   }

   public synchronized void addConfigured(LockStrategy lockStrategy) {
      this.addLockStrategy(lockStrategy.getName(), lockStrategy);
   }

   public synchronized LockStrategy getLockStrategy(String name) {
      return "default".equals(name) ? this.getDefaultLockStrategy() : (LockStrategy)this.lockStrategies.get(name);
   }

   public synchronized void addLockStrategy(String name, LockStrategy lockStrategy) {
      this.init(lockStrategy);
      this.lockStrategies.put(name, lockStrategy);
   }

   public synchronized void addConfigured(Namespace ns) {
      this.addNamespace(ns);
   }

   public synchronized Namespace getNamespace(String name) {
      return "system".equals(name) ? this.getSystemNamespace() : (Namespace)this.namespaces.get(name);
   }

   public final Namespace getSystemNamespace() {
      return Namespace.SYSTEM_NAMESPACE;
   }

   public synchronized void addNamespace(Namespace ns) {
      this.init(ns);
      this.namespaces.put(ns.getName(), ns);
   }

   public void addConfigured(NamedTimeoutConstraint timeoutConstraint) {
      if (timeoutConstraint != null) {
         String name = timeoutConstraint.getName();
         StringUtils.assertNotNullNorEmpty(name, "Name of a timeout constraint cannot be null or empty string");
         this.timeoutConstraints.put(name, timeoutConstraint);
      }
   }

   public TimeoutConstraint getTimeoutConstraint(String name) {
      return (TimeoutConstraint)this.timeoutConstraints.get(name);
   }

   public synchronized void addConfigured(PatternMatcher m) {
      this.addMatcher(m);
   }

   public synchronized PatternMatcher getMatcher(String name) {
      return (PatternMatcher)this.matchers.get(name);
   }

   public synchronized void addMatcher(PatternMatcher m) {
      this.init(m);
      this.matchers.put(m.getName(), m);
   }

   public synchronized void addConfigured(RepositoryCacheManager c) {
      this.addRepositoryCacheManager(c);
   }

   public synchronized RepositoryCacheManager getRepositoryCacheManager(String name) {
      return (RepositoryCacheManager)this.repositoryCacheManagers.get(name);
   }

   public synchronized void addRepositoryCacheManager(RepositoryCacheManager c) {
      this.init(c);
      this.repositoryCacheManagers.put(c.getName(), c);
   }

   public synchronized RepositoryCacheManager[] getRepositoryCacheManagers() {
      return (RepositoryCacheManager[])this.repositoryCacheManagers.values().toArray(new RepositoryCacheManager[this.repositoryCacheManagers.size()]);
   }

   public synchronized void addConfigured(ReportOutputter outputter) {
      this.addReportOutputter(outputter);
   }

   public synchronized ReportOutputter getReportOutputter(String name) {
      return (ReportOutputter)this.reportOutputters.get(name);
   }

   public synchronized void addReportOutputter(ReportOutputter outputter) {
      this.init(outputter);
      this.reportOutputters.put(outputter.getName(), outputter);
   }

   public synchronized ReportOutputter[] getReportOutputters() {
      return (ReportOutputter[])this.reportOutputters.values().toArray(new ReportOutputter[this.reportOutputters.size()]);
   }

   public synchronized void addConfigured(VersionMatcher vmatcher) {
      this.addVersionMatcher(vmatcher);
   }

   public synchronized VersionMatcher getVersionMatcher(String name) {
      return (VersionMatcher)this.versionMatchers.get(name);
   }

   public synchronized void addVersionMatcher(VersionMatcher vmatcher) {
      this.init(vmatcher);
      this.versionMatchers.put(vmatcher.getName(), vmatcher);
      if (this.versionMatcher == null) {
         this.versionMatcher = new ChainVersionMatcher();
         this.addVersionMatcher(new ExactVersionMatcher());
      }

      if (this.versionMatcher instanceof ChainVersionMatcher) {
         ChainVersionMatcher chain = (ChainVersionMatcher)this.versionMatcher;
         chain.add(vmatcher);
      }

   }

   public synchronized VersionMatcher[] getVersionMatchers() {
      return (VersionMatcher[])this.versionMatchers.values().toArray(new VersionMatcher[this.versionMatchers.size()]);
   }

   public synchronized VersionMatcher getVersionMatcher() {
      if (this.versionMatcher == null) {
         this.configureDefaultVersionMatcher();
      }

      return this.versionMatcher;
   }

   public synchronized void configureDefaultVersionMatcher() {
      this.addVersionMatcher(new LatestVersionMatcher());
      this.addVersionMatcher(new SubVersionMatcher());
      this.addVersionMatcher(new VersionRangeMatcher());
   }

   public synchronized CircularDependencyStrategy getCircularDependencyStrategy() {
      if (this.circularDependencyStrategy == null) {
         this.circularDependencyStrategy = this.getCircularDependencyStrategy("default");
      }

      return this.circularDependencyStrategy;
   }

   public synchronized CircularDependencyStrategy getCircularDependencyStrategy(String name) {
      if ("default".equals(name)) {
         name = "warn";
      }

      return (CircularDependencyStrategy)this.circularDependencyStrategies.get(name);
   }

   public synchronized void setCircularDependencyStrategy(CircularDependencyStrategy strategy) {
      this.circularDependencyStrategy = strategy;
   }

   public synchronized void addConfigured(CircularDependencyStrategy strategy) {
      this.addCircularDependencyStrategy(strategy);
   }

   private void addCircularDependencyStrategy(CircularDependencyStrategy strategy) {
      this.circularDependencyStrategies.put(strategy.getName(), strategy);
   }

   private void configureDefaultCircularDependencyStrategies() {
      this.addCircularDependencyStrategy(WarnCircularDependencyStrategy.getInstance());
      this.addCircularDependencyStrategy(ErrorCircularDependencyStrategy.getInstance());
      this.addCircularDependencyStrategy(IgnoreCircularDependencyStrategy.getInstance());
   }

   public synchronized StatusManager getStatusManager() {
      if (this.statusManager == null) {
         this.statusManager = StatusManager.newDefaultInstance();
      }

      return this.statusManager;
   }

   public void setStatusManager(StatusManager statusManager) {
      this.statusManager = statusManager;
   }

   public synchronized String[] getIgnorableFilenames() {
      return (String[])this.listingIgnore.toArray(new String[this.listingIgnore.size()]);
   }

   public synchronized void filterIgnore(Collection names) {
      names.removeAll(this.listingIgnore);
   }

   public synchronized boolean isCheckUpToDate() {
      return this.checkUpToDate;
   }

   public synchronized void setCheckUpToDate(boolean checkUpToDate) {
      this.checkUpToDate = checkUpToDate;
   }

   public synchronized boolean doValidate() {
      return this.validate;
   }

   public synchronized void setValidate(boolean validate) {
      this.validate = validate;
   }

   public synchronized String getVariable(String name) {
      return this.variableContainer.getVariable(name);
   }

   public synchronized boolean getVariableAsBoolean(String name, boolean valueIfUnset) {
      String var = this.getVariable(name);
      return var == null ? valueIfUnset : Boolean.valueOf(var);
   }

   public synchronized ConflictManager getDefaultConflictManager() {
      if (this.defaultConflictManager == null) {
         this.defaultConflictManager = new LatestConflictManager(this.getDefaultLatestStrategy());
         ((LatestConflictManager)this.defaultConflictManager).setSettings(this);
      }

      return this.defaultConflictManager;
   }

   public synchronized void setDefaultConflictManager(ConflictManager defaultConflictManager) {
      this.defaultConflictManager = defaultConflictManager;
   }

   public synchronized LatestStrategy getDefaultLatestStrategy() {
      if (this.defaultLatestStrategy == null) {
         this.defaultLatestStrategy = new LatestRevisionStrategy();
      }

      if (this.workspaceResolver != null && !(this.defaultLatestStrategy instanceof WorkspaceLatestStrategy)) {
         this.defaultLatestStrategy = new WorkspaceLatestStrategy(this.defaultLatestStrategy);
      }

      return this.defaultLatestStrategy;
   }

   public synchronized void setDefaultLatestStrategy(LatestStrategy defaultLatestStrategy) {
      this.defaultLatestStrategy = defaultLatestStrategy;
   }

   public synchronized LockStrategy getDefaultLockStrategy() {
      if (this.defaultLockStrategy == null) {
         this.defaultLockStrategy = new NoLockStrategy();
      }

      return this.defaultLockStrategy;
   }

   public synchronized void setDefaultLockStrategy(LockStrategy defaultLockStrategy) {
      this.defaultLockStrategy = defaultLockStrategy;
   }

   public synchronized RepositoryCacheManager getDefaultRepositoryCacheManager() {
      if (this.defaultRepositoryCacheManager == null) {
         this.defaultRepositoryCacheManager = new DefaultRepositoryCacheManager("default-cache", this, this.getDefaultRepositoryCacheBasedir());
         this.addRepositoryCacheManager(this.defaultRepositoryCacheManager);
      }

      return this.defaultRepositoryCacheManager;
   }

   public synchronized void setDefaultRepositoryCacheManager(RepositoryCacheManager cache) {
      this.defaultRepositoryCacheManager = cache;
   }

   public synchronized ResolutionCacheManager getResolutionCacheManager() {
      if (this.resolutionCacheManager == null) {
         this.resolutionCacheManager = new DefaultResolutionCacheManager(this.getDefaultResolutionCacheBasedir());
         this.init(this.resolutionCacheManager);
      }

      return this.resolutionCacheManager;
   }

   public synchronized void setResolutionCacheManager(ResolutionCacheManager resolutionCacheManager) {
      this.resolutionCacheManager = resolutionCacheManager;
   }

   public synchronized void addTrigger(Trigger trigger) {
      this.init(trigger);
      this.triggers.add(trigger);
   }

   public synchronized List getTriggers() {
      return this.triggers;
   }

   public synchronized void addConfigured(Trigger trigger) {
      this.addTrigger(trigger);
   }

   public synchronized boolean isUseRemoteConfig() {
      return this.useRemoteConfig;
   }

   public synchronized void setUseRemoteConfig(boolean useRemoteConfig) {
      this.useRemoteConfig = useRemoteConfig;
   }

   public synchronized boolean logModulesInUse() {
      return this.getVariableAsBoolean("ivy.log.modules.in.use", true);
   }

   public synchronized boolean logModuleWhenFound() {
      return this.getVariableAsBoolean("ivy.log.module.when.found", true);
   }

   public synchronized boolean logResolvedRevision() {
      return this.getVariableAsBoolean("ivy.log.resolved.revision", true);
   }

   public synchronized boolean debugConflictResolution() {
      if (this.debugConflictResolution == null) {
         this.debugConflictResolution = this.getVariableAsBoolean("ivy.log.conflict.resolution", false);
      }

      return this.debugConflictResolution;
   }

   public synchronized boolean debugLocking() {
      if (this.debugLocking == null) {
         this.debugLocking = this.getVariableAsBoolean("ivy.log.locking", false);
      }

      return this.debugLocking;
   }

   public synchronized boolean dumpMemoryUsage() {
      if (this.dumpMemoryUsage == null) {
         this.dumpMemoryUsage = this.getVariableAsBoolean("ivy.log.memory", false);
      }

      return this.dumpMemoryUsage;
   }

   public synchronized boolean logNotConvertedExclusionRule() {
      return this.logNotConvertedExclusionRule;
   }

   public synchronized void setLogNotConvertedExclusionRule(boolean logNotConvertedExclusionRule) {
      this.logNotConvertedExclusionRule = logNotConvertedExclusionRule;
   }

   private void init(Object obj) {
      if (obj instanceof IvySettingsAware) {
         ((IvySettingsAware)obj).setSettings(this);
      } else if (obj instanceof DependencyResolver) {
         ((DependencyResolver)obj).setSettings(this);
      }

   }

   public final long getInterruptTimeout() {
      return 2000L;
   }

   public synchronized Collection getResolvers() {
      return this.resolversMap.values();
   }

   public synchronized Collection getResolverNames() {
      return this.resolversMap.keySet();
   }

   public synchronized Collection getMatcherNames() {
      return this.matchers.keySet();
   }

   public synchronized IvyVariableContainer getVariableContainer() {
      return this.variableContainer;
   }

   public synchronized void setVariableContainer(IvyVariableContainer variables) {
      this.variableContainer = variables;
   }

   public synchronized RelativeUrlResolver getRelativeUrlResolver() {
      return new NormalRelativeUrlResolver();
   }

   public synchronized void setDefaultCacheIvyPattern(String defaultCacheIvyPattern) {
      CacheUtil.checkCachePattern(defaultCacheIvyPattern);
      this.defaultCacheIvyPattern = defaultCacheIvyPattern;
   }

   public synchronized String getDefaultCacheIvyPattern() {
      return this.defaultCacheIvyPattern;
   }

   public synchronized void setDefaultCacheArtifactPattern(String defaultCacheArtifactPattern) {
      CacheUtil.checkCachePattern(defaultCacheArtifactPattern);
      this.defaultCacheArtifactPattern = defaultCacheArtifactPattern;
   }

   public synchronized String getDefaultCacheArtifactPattern() {
      return this.defaultCacheArtifactPattern;
   }

   public synchronized void setDefaultUseOrigin(boolean useOrigin) {
      this.defaultUseOrigin = useOrigin;
   }

   public synchronized boolean isDefaultUseOrigin() {
      return this.defaultUseOrigin;
   }

   public synchronized void useDeprecatedUseOrigin() {
      Message.deprecated("useOrigin option is deprecated when calling resolve, use useOrigin setting on the cache implementation instead");
      this.setDefaultUseOrigin(true);
   }

   public synchronized void validate() {
      this.validateAll(this.resolversMap.values());
      this.validateAll(this.conflictsManager.values());
      this.validateAll(this.latestStrategies.values());
      this.validateAll(this.lockStrategies.values());
      this.validateAll(this.repositoryCacheManagers.values());
      this.validateAll(this.reportOutputters.values());
      this.validateAll(this.circularDependencyStrategies.values());
      this.validateAll(this.versionMatchers.values());
      this.validateAll(this.namespaces.values());
   }

   private void validateAll(Collection values) {
      for(Object object : values) {
         if (object instanceof Validatable) {
            ((Validatable)object).validate();
         }
      }

   }

   public Namespace getContextNamespace() {
      return Namespace.SYSTEM_NAMESPACE;
   }

   public synchronized void addConfigured(ArchivePacking packing) {
      this.init(packing);
      this.packingRegistry.register(packing);
   }

   public PackingRegistry getPackingRegistry() {
      return this.packingRegistry;
   }

   public void addConfigured(AbstractWorkspaceResolver workspaceResolver) {
      this.workspaceResolver = workspaceResolver;
      if (workspaceResolver != null) {
         workspaceResolver.setSettings(this);
         DefaultRepositoryCacheManager cacheManager = new DefaultRepositoryCacheManager();
         String cacheName = "workspace-resolver-cache-" + workspaceResolver.getName();
         cacheManager.setBasedir(new File(this.getDefaultCache(), cacheName));
         cacheManager.setCheckmodified(true);
         cacheManager.setUseOrigin(true);
         cacheManager.setName(cacheName);
         this.addRepositoryCacheManager(cacheManager);
         workspaceResolver.setCache(cacheName);
      }

   }

   private static class ModuleSettings {
      private String resolverName;
      private String branch;
      private String conflictManager;
      private String resolveMode;

      public ModuleSettings(String resolver, String branchName, String conflictMgr, String resolveMode) {
         this.resolverName = resolver;
         this.branch = branchName;
         this.conflictManager = conflictMgr;
         this.resolveMode = resolveMode;
      }

      public String toString() {
         return (this.resolverName != null ? "resolver: " + this.resolverName : "") + (this.branch != null ? "branch: " + this.branch : "") + (this.conflictManager != null ? "conflictManager: " + this.conflictManager : "") + (this.resolveMode != null ? "resolveMode: " + this.resolveMode : "");
      }

      public String getBranch() {
         return this.branch;
      }

      public String getResolverName() {
         return this.resolverName;
      }

      public String getConflictManager() {
         return this.conflictManager;
      }

      public String getResolveMode() {
         return this.resolveMode;
      }
   }
}
