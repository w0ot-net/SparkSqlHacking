package org.apache.ivy.core.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.plugins.circular.CircularDependencyStrategy;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.plugins.lock.LockStrategy;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.Configurator;
import org.apache.ivy.util.FileResolver;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.XMLHelper;
import org.apache.ivy.util.url.CredentialsStore;
import org.apache.ivy.util.url.URLHandlerRegistry;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlSettingsParser extends DefaultHandler {
   private Configurator configurator;
   private List configuratorTags = Arrays.asList("resolvers", "namespaces", "parsers", "latest-strategies", "conflict-managers", "outputters", "version-matchers", "statuses", "circular-dependency-strategies", "triggers", "lock-strategies", "caches", "signers", "timeout-constraints");
   private IvySettings ivy;
   private String defaultResolver;
   private String defaultCM;
   private String defaultLatest;
   private String defaultCacheManager;
   private String defaultCircular;
   private String defaultLock;
   private String currentConfiguratorTag;
   private URL settings;
   private boolean deprecatedMessagePrinted = false;

   public XmlSettingsParser(IvySettings ivy) {
      this.ivy = ivy;
   }

   public void parse(URL settings) throws ParseException, IOException {
      this.configurator = new Configurator();
      this.configurator.setFileResolver(new FileResolver() {
         public File resolveFile(String path, String filename) {
            return Checks.checkAbsolute(path, filename);
         }
      });

      for(Map.Entry entry : this.ivy.getTypeDefs().entrySet()) {
         this.configurator.typeDef((String)entry.getKey(), (Class)entry.getValue());
      }

      this.doParse(settings);
   }

   private void doParse(URL settingsUrl) throws IOException, ParseException {
      this.settings = settingsUrl;

      try {
         XMLHelper.parse(settingsUrl, (URL)null, this);
         this.ivy.validate();
      } catch (IOException e) {
         throw e;
      } catch (Exception e) {
         ParseException pe = new ParseException("failed to load settings from " + settingsUrl + ": " + e.getMessage(), 0);
         pe.initCause(e);
         throw pe;
      }
   }

   private void parse(Configurator configurator, URL configuration) throws IOException, ParseException {
      this.configurator = configurator;
      this.doParse(configuration);
   }

   public void startElement(String uri, String localName, String qName, Attributes att) throws SAXException {
      Map<String, String> attributes = new HashMap();

      for(int i = 0; i < att.getLength(); ++i) {
         attributes.put(att.getQName(i), this.ivy.substitute(att.getValue(i)));
      }

      try {
         if ("ivyconf".equals(qName)) {
            this.deprecatedMessagePrinted = true;
            Message.deprecated("'ivyconf' element is deprecated, use 'ivysettings' instead (" + this.settings + ")");
         }

         if (this.configurator.getCurrent() != null) {
            this.inConfiguratorStarted(qName, attributes);
         } else if ("classpath".equals(qName)) {
            this.classpathStarted(attributes);
         } else if ("typedef".equals(qName)) {
            this.typedefStarted(attributes);
         } else if ("property".equals(qName)) {
            this.propertyStarted(attributes);
         } else if ("properties".equals(qName)) {
            this.propertiesStarted(attributes);
         } else if ("include".equals(qName)) {
            this.includeStarted(attributes);
         } else if (!"settings".equals(qName) && !"conf".equals(qName)) {
            if ("caches".equals(qName)) {
               this.cachesStarted(qName, attributes);
            } else if ("version-matchers".equals(qName)) {
               this.versionMatchersStarted(qName, attributes);
            } else if ("statuses".equals(qName)) {
               this.statusesStarted(qName, attributes);
            } else if (this.configuratorTags.contains(qName)) {
               this.anyConfiguratorStarted(qName);
            } else if ("macrodef".equals(qName)) {
               this.macrodefStarted(qName, attributes);
            } else if ("module".equals(qName)) {
               this.moduleStarted(attributes);
            } else if ("credentials".equals(qName)) {
               this.credentialsStarted(attributes);
            }
         } else {
            this.settingsStarted(qName, attributes);
         }

      } catch (ParseException ex) {
         throw new SAXException("problem in config file: " + ex.getMessage(), ex);
      } catch (IOException ex) {
         throw new SAXException("io problem while parsing config file: " + ex.getMessage(), ex);
      }
   }

   private void credentialsStarted(Map attributes) {
      String realm = (String)attributes.remove("realm");
      String host = (String)attributes.remove("host");
      String userName = (String)attributes.remove("username");
      String passwd = (String)attributes.remove("passwd");
      CredentialsStore.INSTANCE.addCredentials(realm, host, userName, passwd);
   }

   private void moduleStarted(Map attributes) {
      attributes.put("module", attributes.remove("name"));
      String resolver = (String)attributes.remove("resolver");
      String branch = (String)attributes.remove("branch");
      String cm = (String)attributes.remove("conflict-manager");
      String resolveMode = (String)attributes.remove("resolveMode");
      String matcher = (String)attributes.remove("matcher");
      matcher = matcher == null ? "exactOrRegexp" : matcher;
      this.ivy.addModuleConfiguration(attributes, this.ivy.getMatcher(matcher), resolver, branch, cm, resolveMode);
   }

   private void macrodefStarted(String qName, Map attributes) {
      this.currentConfiguratorTag = qName;
      Configurator.MacroDef macrodef = this.configurator.startMacroDef((String)attributes.get("name"));
      macrodef.addAttribute("name", (String)null);
   }

   private void anyConfiguratorStarted(String qName) {
      this.currentConfiguratorTag = qName;
      this.configurator.setRoot(this.ivy);
   }

   private void statusesStarted(String qName, Map attributes) {
      this.currentConfiguratorTag = qName;
      StatusManager m = new StatusManager();
      String defaultStatus = (String)attributes.get("default");
      if (defaultStatus != null) {
         m.setDefaultStatus(defaultStatus);
      }

      this.ivy.setStatusManager(m);
      this.configurator.setRoot(m);
   }

   private void versionMatchersStarted(String qName, Map attributes) {
      this.anyConfiguratorStarted(qName);
      if ("true".equals(attributes.get("usedefaults"))) {
         this.ivy.configureDefaultVersionMatcher();
      }

   }

   private void cachesStarted(String qName, Map attributes) {
      this.anyConfiguratorStarted(qName);
      this.defaultLock = (String)attributes.get("lockStrategy");
      this.defaultCacheManager = (String)attributes.get("default");
      String cache = (String)attributes.get("defaultCacheDir");
      if (cache != null) {
         this.ivy.setDefaultCache(Checks.checkAbsolute(cache, "defaultCacheDir"));
      }

      String up2d = (String)attributes.get("checkUpToDate");
      if (up2d != null) {
         Message.deprecated("'checkUpToDate' is deprecated, use the 'overwriteMode' on the 'ivy:retrieve' task instead (" + this.settings + ")");
         this.ivy.setCheckUpToDate(Boolean.valueOf(up2d));
      }

      String resolutionDir = (String)attributes.get("resolutionCacheDir");
      if (resolutionDir != null) {
         this.ivy.setDefaultResolutionCacheBasedir(resolutionDir);
      }

      String useOrigin = (String)attributes.get("useOrigin");
      if (useOrigin != null) {
         this.ivy.setDefaultUseOrigin(Boolean.valueOf(useOrigin));
      }

      String cacheIvyPattern = (String)attributes.get("ivyPattern");
      if (cacheIvyPattern != null) {
         this.ivy.setDefaultCacheIvyPattern(cacheIvyPattern);
      }

      String cacheArtPattern = (String)attributes.get("artifactPattern");
      if (cacheArtPattern != null) {
         this.ivy.setDefaultCacheArtifactPattern(cacheArtPattern);
      }

      String repositoryDir = (String)attributes.get("repositoryCacheDir");
      if (repositoryDir != null) {
         this.ivy.setDefaultRepositoryCacheBasedir(repositoryDir);
      }

   }

   private void settingsStarted(String qName, Map attributes) {
      if ("conf".equals(qName) && !this.deprecatedMessagePrinted) {
         Message.deprecated("'conf' is deprecated, use 'settings' instead (" + this.settings + ")");
      }

      String cache = (String)attributes.get("defaultCache");
      if (cache != null) {
         Message.deprecated("'defaultCache' is deprecated, use 'caches[@defaultCacheDir]' instead (" + this.settings + ")");
         this.ivy.setDefaultCache(Checks.checkAbsolute(cache, "defaultCache"));
      }

      String defaultBranch = (String)attributes.get("defaultBranch");
      if (defaultBranch != null) {
         this.ivy.setDefaultBranch(defaultBranch);
      }

      String defaultResolveMode = (String)attributes.get("defaultResolveMode");
      if (defaultResolveMode != null) {
         this.ivy.setDefaultResolveMode(defaultResolveMode);
      }

      String validate = (String)attributes.get("validate");
      if (validate != null) {
         this.ivy.setValidate(Boolean.valueOf(validate));
      }

      String up2d = (String)attributes.get("checkUpToDate");
      if (up2d != null) {
         Message.deprecated("'checkUpToDate' is deprecated, use the 'overwriteMode' on the 'ivy:retrieve' task instead (" + this.settings + ")");
         this.ivy.setCheckUpToDate(Boolean.valueOf(up2d));
      }

      String useRemoteConfig = (String)attributes.get("useRemoteConfig");
      if (useRemoteConfig != null) {
         this.ivy.setUseRemoteConfig(Boolean.valueOf(useRemoteConfig));
      }

      String cacheIvyPattern = (String)attributes.get("cacheIvyPattern");
      if (cacheIvyPattern != null) {
         Message.deprecated("'cacheIvyPattern' is deprecated, use 'caches[@ivyPattern]' instead (" + this.settings + ")");
         this.ivy.setDefaultCacheIvyPattern(cacheIvyPattern);
      }

      String cacheArtPattern = (String)attributes.get("cacheArtifactPattern");
      if (cacheArtPattern != null) {
         Message.deprecated("'cacheArtifactPattern' is deprecated, use 'caches[@artifactPattern]' instead (" + this.settings + ")");
         this.ivy.setDefaultCacheArtifactPattern(cacheArtPattern);
      }

      this.defaultResolver = (String)attributes.get("defaultResolver");
      this.defaultCM = (String)attributes.get("defaultConflictManager");
      this.defaultLatest = (String)attributes.get("defaultLatestStrategy");
      this.defaultCircular = (String)attributes.get("circularDependencyStrategy");
      String requestMethod = (String)attributes.get("httpRequestMethod");
      if ("head".equalsIgnoreCase(requestMethod)) {
         URLHandlerRegistry.getHttp().setRequestMethod(2);
      } else if ("get".equalsIgnoreCase(requestMethod)) {
         URLHandlerRegistry.getHttp().setRequestMethod(1);
      } else if (!StringUtils.isNullOrEmpty(requestMethod)) {
         throw new IllegalArgumentException("Invalid httpRequestMethod specified, must be one of {'HEAD', 'GET'}");
      }

   }

   private void includeStarted(Map attributes) throws IOException, ParseException {
      IvyVariableContainer variables = this.ivy.getVariableContainer();
      this.ivy.setVariableContainer(new IvyVariableContainerWrapper(variables));
      boolean optionalInclude = "true".equals(attributes.get("optional"));

      try {
         String propFilePath = (String)attributes.get("file");
         URL settingsURL = null;
         if (propFilePath == null) {
            propFilePath = (String)attributes.get("url");
            if (propFilePath == null) {
               throw new IllegalArgumentException("bad include tag: specify file or url to include");
            }

            try {
               try {
                  settingsURL = new URL(propFilePath);
               } catch (MalformedURLException var14) {
                  settingsURL = new URL(this.settings, propFilePath);
               }
            } catch (IOException ioe) {
               if (optionalInclude) {
                  Message.verbose("Skipping inclusion of optional URL " + propFilePath + " due to IOException - " + ioe.getMessage());
                  return;
               }

               throw ioe;
            }

            Message.verbose("including url: " + settingsURL.toString());
            this.ivy.setSettingsVariables(settingsURL);
         } else {
            try {
               settingsURL = this.urlFromFileAttribute(propFilePath);
               Message.verbose("including file: " + settingsURL);
               if ("file".equals(settingsURL.getProtocol())) {
                  try {
                     File settingsFile = new File(new URI(settingsURL.toExternalForm()));
                     if (optionalInclude && !settingsFile.exists()) {
                        return;
                     }

                     this.ivy.setSettingsVariables(Checks.checkAbsolute(settingsFile, "settings include path"));
                  } catch (URISyntaxException var16) {
                     this.ivy.setSettingsVariables(Checks.checkAbsolute(settingsURL.getPath(), "settings include path"));
                  }
               } else {
                  this.ivy.setSettingsVariables(settingsURL);
               }
            } catch (IOException ioe) {
               if (!optionalInclude) {
                  throw ioe;
               }

               Message.verbose("Skipping inclusion of optional file " + propFilePath + " due to IOException - " + ioe.getMessage());
               return;
            }
         }

         try {
            (new XmlSettingsParser(this.ivy)).parse(this.configurator, settingsURL);
         } catch (IOException ioe) {
            if (!optionalInclude) {
               throw ioe;
            } else {
               Message.verbose("Skipping inclusion of optional settings URL " + settingsURL + " due to IOException - " + ioe.getMessage());
            }
         }
      } finally {
         this.ivy.setVariableContainer(variables);
      }
   }

   private URL urlFromFileAttribute(String filePath) throws IOException {
      try {
         return new URL(filePath);
      } catch (MalformedURLException var5) {
         File incFile = new File(filePath);
         if (incFile.isAbsolute()) {
            if (!incFile.exists()) {
               throw new FileNotFoundException(incFile.getAbsolutePath());
            } else {
               return incFile.toURI().toURL();
            }
         } else if ("file".equals(this.settings.getProtocol())) {
            try {
               File settingsFile = new File(new URI(this.settings.toExternalForm()));
               if (!settingsFile.exists()) {
                  throw new FileNotFoundException(settingsFile.getAbsolutePath());
               } else {
                  return (new File(settingsFile.getParentFile(), filePath)).toURI().toURL();
               }
            } catch (URISyntaxException var4) {
               return new URL(this.settings, filePath);
            }
         } else {
            return new URL(this.settings, filePath);
         }
      }
   }

   private void propertiesStarted(Map attributes) throws IOException {
      String propFilePath = (String)attributes.get("file");
      String environmentPrefix = (String)attributes.get("environment");
      if (propFilePath != null) {
         String overrideStr = (String)attributes.get("override");
         boolean override = overrideStr == null || Boolean.valueOf(overrideStr);
         Message.verbose("loading properties: " + propFilePath);

         try {
            URL fileUrl = this.urlFromFileAttribute(propFilePath);
            this.ivy.loadProperties(fileUrl, override);
         } catch (FileNotFoundException var7) {
            Message.verbose("Unable to find property file: " + propFilePath);
         }
      } else {
         if (environmentPrefix == null) {
            throw new IllegalArgumentException("Didn't find a 'file' or 'environment' attribute on the 'properties' element");
         }

         this.ivy.getVariableContainer().setEnvironmentPrefix(environmentPrefix);
      }

   }

   private void propertyStarted(Map attributes) {
      String name = (String)attributes.get("name");
      String value = (String)attributes.get("value");
      String override = (String)attributes.get("override");
      String isSetVar = (String)attributes.get("ifset");
      String unlessSetVar = (String)attributes.get("unlessset");
      if (name == null) {
         throw new IllegalArgumentException("missing attribute name on property tag");
      } else if (value == null) {
         throw new IllegalArgumentException("missing attribute value on property tag");
      } else {
         this.ivy.setVariable(name, value, override == null || Boolean.valueOf(override), isSetVar, unlessSetVar);
      }
   }

   private void typedefStarted(Map attributes) {
      String name = (String)attributes.get("name");
      String className = (String)attributes.get("classname");
      Class<?> clazz = this.ivy.typeDef(name, className);
      this.configurator.typeDef(name, clazz);
   }

   private void classpathStarted(Map attributes) throws IOException {
      String urlStr = (String)attributes.get("url");
      URL url = null;
      if (urlStr == null) {
         String file = (String)attributes.get("file");
         if (file == null) {
            throw new IllegalArgumentException("either url or file should be given for classpath element");
         }

         url = this.urlFromFileAttribute(file);
      } else {
         url = new URL(urlStr);
      }

      this.ivy.addClasspathURL(url);
   }

   private void inConfiguratorStarted(String qName, Map attributes) {
      if ("macrodef".equals(this.currentConfiguratorTag) && this.configurator.getTypeDef(qName) != null) {
         String name = (String)attributes.get("name");
         if (name == null) {
            attributes.put("name", "@{name}");
         } else if (name.contains("@{name}")) {
            attributes.put("name", name);
         } else {
            attributes.put("name", "@{name}-" + name);
         }
      }

      if (attributes.get("ref") != null) {
         if (attributes.size() != 1) {
            throw new IllegalArgumentException("ref attribute should be the only one ! found " + attributes.size() + " in " + qName);
         }

         String name = (String)attributes.get("ref");
         Object child = null;
         if (!"resolvers".equals(this.currentConfiguratorTag) && !"resolver".equals(qName)) {
            if ("latest-strategies".equals(this.currentConfiguratorTag)) {
               child = this.ivy.getLatestStrategy(name);
               if (child == null) {
                  throw new IllegalArgumentException("unknown latest strategy " + name + ": latest strategy should be defined before being referenced");
               }
            } else if ("conflict-managers".equals(this.currentConfiguratorTag)) {
               child = this.ivy.getConflictManager(name);
               if (child == null) {
                  throw new IllegalArgumentException("unknown conflict manager " + name + ": conflict manager should be defined before being referenced");
               }
            }
         } else {
            child = this.ivy.getResolver(name);
            if (child == null) {
               throw new IllegalArgumentException("unknown resolver " + name + ": resolver should be defined before being referenced");
            }
         }

         if (child == null) {
            throw new IllegalArgumentException("bad reference " + name);
         }

         this.configurator.addChild(qName, child);
      } else {
         this.configurator.startCreateChild(qName);

         for(Map.Entry attribute : attributes.entrySet()) {
            this.configurator.setAttribute((String)attribute.getKey(), (String)attribute.getValue());
         }
      }

   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (this.configurator.getCurrent() != null) {
         if (this.configuratorTags.contains(qName) && this.configurator.getDepth() == 1) {
            this.configurator.clear();
            this.currentConfiguratorTag = null;
         } else if ("macrodef".equals(qName) && this.configurator.getDepth() == 1) {
            this.configurator.endMacroDef();
            this.currentConfiguratorTag = null;
         } else {
            this.configurator.endCreateChild();
         }
      }

   }

   public void endDocument() throws SAXException {
      if (this.defaultResolver != null) {
         this.ivy.setDefaultResolver(this.ivy.substitute(this.defaultResolver));
      }

      if (this.defaultCM != null) {
         ConflictManager conflictManager = this.ivy.getConflictManager(this.ivy.substitute(this.defaultCM));
         if (conflictManager == null) {
            throw new IllegalArgumentException("unknown conflict manager " + this.ivy.substitute(this.defaultCM));
         }

         this.ivy.setDefaultConflictManager(conflictManager);
      }

      if (this.defaultLatest != null) {
         LatestStrategy latestStrategy = this.ivy.getLatestStrategy(this.ivy.substitute(this.defaultLatest));
         if (latestStrategy == null) {
            throw new IllegalArgumentException("unknown latest strategy " + this.ivy.substitute(this.defaultLatest));
         }

         this.ivy.setDefaultLatestStrategy(latestStrategy);
      }

      if (this.defaultCacheManager != null) {
         RepositoryCacheManager cache = this.ivy.getRepositoryCacheManager(this.ivy.substitute(this.defaultCacheManager));
         if (cache == null) {
            throw new IllegalArgumentException("unknown cache manager " + this.ivy.substitute(this.defaultCacheManager));
         }

         this.ivy.setDefaultRepositoryCacheManager(cache);
      }

      if (this.defaultCircular != null) {
         CircularDependencyStrategy strategy = this.ivy.getCircularDependencyStrategy(this.ivy.substitute(this.defaultCircular));
         if (strategy == null) {
            throw new IllegalArgumentException("unknown circular dependency strategy " + this.ivy.substitute(this.defaultCircular));
         }

         this.ivy.setCircularDependencyStrategy(strategy);
      }

      if (this.defaultLock != null) {
         LockStrategy strategy = this.ivy.getLockStrategy(this.ivy.substitute(this.defaultLock));
         if (strategy == null) {
            throw new IllegalArgumentException("unknown lock strategy " + this.ivy.substitute(this.defaultLock));
         }

         this.ivy.setDefaultLockStrategy(strategy);
      }

   }

   private static final class IvyVariableContainerWrapper implements IvyVariableContainer {
      private static final Collection SETTINGS_VARIABLES = Arrays.asList("ivy.settings.dir", "ivy.settings.url", "ivy.settings.file", "ivy.conf.dir", "ivy.conf.url", "ivy.conf.file");
      private final IvyVariableContainer variables;
      private Map localVariables;

      private IvyVariableContainerWrapper(IvyVariableContainer variables) {
         this.localVariables = new HashMap();
         this.variables = variables;
      }

      public void setVariable(String varName, String value, boolean overwrite) {
         if (SETTINGS_VARIABLES.contains(varName)) {
            if (!this.localVariables.containsKey(varName) || overwrite) {
               this.localVariables.put(varName, value);
            }
         } else {
            this.variables.setVariable(varName, value, overwrite);
         }

      }

      public void setEnvironmentPrefix(String prefix) {
         this.variables.setEnvironmentPrefix(prefix);
      }

      public String getVariable(String name) {
         return this.localVariables.containsKey(name) ? (String)this.localVariables.get(name) : this.variables.getVariable(name);
      }

      public Object clone() {
         throw new UnsupportedOperationException();
      }
   }
}
