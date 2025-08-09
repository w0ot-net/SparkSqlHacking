package org.apache.ivy.plugins.parser.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.ConfigurationAware;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DefaultDependencyArtifactDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultExcludeRule;
import org.apache.ivy.core.module.descriptor.DefaultExtendsDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultIncludeRule;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyArtifactDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ExcludeRule;
import org.apache.ivy.core.module.descriptor.ExtraInfoHolder;
import org.apache.ivy.core.module.descriptor.IncludeRule;
import org.apache.ivy.core.module.descriptor.License;
import org.apache.ivy.core.module.descriptor.MDArtifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.descriptor.OverrideDependencyDescriptorMediator;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolveData;
import org.apache.ivy.core.resolve.ResolveEngine;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.resolve.ResolvedModuleRevision;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.conflict.FixedConflictManager;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.NameSpaceHelper;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.AbstractModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ModuleDescriptorParserRegistry;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.file.FileRepository;
import org.apache.ivy.plugins.repository.file.FileResource;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.DateUtil;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.XMLHelper;
import org.apache.ivy.util.extendable.ExtendableItemHelper;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class XmlModuleDescriptorParser extends AbstractModuleDescriptorParser {
   static final List DEPENDENCY_REGULAR_ATTRIBUTES = Arrays.asList("org", "name", "branch", "branchConstraint", "rev", "revConstraint", "force", "transitive", "changing", "conf");
   private static final XmlModuleDescriptorParser INSTANCE = new XmlModuleDescriptorParser();

   public static XmlModuleDescriptorParser getInstance() {
      return INSTANCE;
   }

   protected XmlModuleDescriptorParser() {
   }

   public ModuleDescriptor parseDescriptor(ParserSettings ivySettings, URL xmlURL, Resource res, boolean validate) throws ParseException, IOException {
      Parser parser = this.newParser(ivySettings);
      parser.setValidate(validate);
      parser.setResource(res);
      parser.setInput(xmlURL);
      parser.parse();
      return parser.getModuleDescriptor();
   }

   ModuleDescriptor parseDescriptor(ParserSettings ivySettings, InputStream descriptor, Resource res, boolean validate) throws ParseException {
      Parser parser = this.newParser(ivySettings);
      parser.setValidate(validate);
      parser.setResource(res);
      parser.setInput(descriptor);
      parser.parse();
      return parser.getModuleDescriptor();
   }

   protected Parser newParser(ParserSettings ivySettings) {
      return new Parser(this, ivySettings);
   }

   public boolean accept(Resource res) {
      return true;
   }

   public void toIvyFile(InputStream is, Resource res, File destFile, ModuleDescriptor md) throws IOException, ParseException {
      try {
         Namespace ns = null;
         if (md instanceof DefaultModuleDescriptor) {
            DefaultModuleDescriptor dmd = (DefaultModuleDescriptor)md;
            ns = dmd.getNamespace();
         }

         XmlModuleDescriptorUpdater.update(is, res, destFile, (new UpdateOptions()).setSettings(IvyContext.getContext().getSettings()).setStatus(md.getStatus()).setRevision(md.getResolvedModuleRevisionId().getRevision()).setPubdate(md.getResolvedPublicationDate()).setUpdateBranch(false).setNamespace(ns));
      } catch (SAXException e) {
         ParseException pe = new ParseException("exception occurred while parsing " + res, 0);
         pe.initCause(e);
         throw pe;
      } finally {
         if (is != null) {
            is.close();
         }

      }

   }

   public String toString() {
      return "ivy parser";
   }

   public static class Parser extends AbstractModuleDescriptorParser.AbstractParser {
      protected static final List ALLOWED_VERSIONS = Arrays.asList("1.0", "1.1", "1.2", "1.3", "1.4", "2.0", "2.1", "2.2", "2.3", "2.4");
      private static final String IVY_XSD_CONTENT;
      private ParserSettings settings;
      private boolean validate = true;
      private URL descriptorURL;
      private InputStream descriptorInput;
      private int state = 0;
      private PatternMatcher defaultMatcher;
      private DefaultDependencyDescriptor dd;
      private ConfigurationAware confAware;
      private MDArtifact artifact;
      private String conf;
      private boolean artifactsDeclared = false;
      private StringBuilder buffer;
      private String descriptorVersion;
      private String[] publicationsDefaultConf;
      private Stack extraInfoStack = new Stack();

      public Parser(ModuleDescriptorParser parser, ParserSettings ivySettings) {
         super(parser);
         this.settings = ivySettings;
      }

      public void setInput(InputStream descriptorInput) {
         this.descriptorInput = descriptorInput;
      }

      public void setInput(URL descriptorURL) {
         this.descriptorURL = descriptorURL;
      }

      public void setValidate(boolean validate) {
         this.validate = validate;
      }

      public void parse() throws ParseException {
         try {
            URL schemaURL = this.validate ? this.getSchemaURL() : null;
            XMLHelper.ExternalResources e = this.validate && System.getProperty("ivy.xml.external-resources") == null ? XMLHelper.ExternalResources.IGNORE : XMLHelper.ExternalResources.fromSystemProperty();
            if (this.descriptorURL != null) {
               XMLHelper.parse((URL)this.descriptorURL, schemaURL, this, (LexicalHandler)null, e);
            } else {
               XMLHelper.parse((InputStream)this.descriptorInput, schemaURL, this, (LexicalHandler)null, e);
            }

            this.checkConfigurations();
            this.replaceConfigurationWildcards();
            this.getMd().setModuleArtifact(DefaultArtifact.newIvyArtifact(this.getMd().getResolvedModuleRevisionId(), this.getMd().getPublicationDate()));
            if (!this.artifactsDeclared) {
               for(String config : this.getMd().getConfigurationsNames()) {
                  this.getMd().addArtifact(config, new MDArtifact(this.getMd(), this.getMd().getModuleRevisionId().getName(), "jar", "jar"));
               }
            }

            this.getMd().check();
         } catch (ParserConfigurationException ex) {
            throw new IllegalStateException(ex.getMessage() + " in " + this.descriptorURL, ex);
         } catch (Exception ex) {
            this.checkErrors();
            ParseException pe = new ParseException(ex.getMessage() + " in " + this.descriptorURL, 0);
            pe.initCause(ex);
            throw pe;
         }
      }

      public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
         if (isApacheOrgIvyXSDSystemId(systemId) && IVY_XSD_CONTENT != null) {
            InputSource source = new InputSource(new StringReader(IVY_XSD_CONTENT));
            return source;
         } else {
            return super.resolveEntity(publicId, systemId);
         }
      }

      private static boolean isApacheOrgIvyXSDSystemId(String systemId) {
         if (systemId == null) {
            return false;
         } else {
            return systemId.equals("http://ant.apache.org/ivy/schemas/ivy.xsd") || systemId.equals("https://ant.apache.org/ivy/schemas/ivy.xsd");
         }
      }

      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
         try {
            if (this.state == 11) {
               this.getBuffer().append("<").append(qName);

               for(int i = 0; i < attributes.getLength(); ++i) {
                  this.getBuffer().append(" ");
                  this.getBuffer().append(attributes.getQName(i));
                  this.getBuffer().append("=\"");
                  this.getBuffer().append(attributes.getValue(i));
                  this.getBuffer().append("\"");
               }

               this.getBuffer().append(">");
            } else if ("ivy-module".equals(qName)) {
               this.ivyModuleStarted(attributes);
            } else if ("info".equals(qName)) {
               this.infoStarted(attributes);
            } else if (this.state == 1 && "extends".equals(qName)) {
               this.extendsStarted(attributes);
            } else if (this.state == 1 && "license".equals(qName)) {
               this.getMd().addLicense(new License(this.settings.substitute(attributes.getValue("name")), this.settings.substitute(attributes.getValue("url"))));
            } else if (this.state == 1 && "description".equals(qName)) {
               this.getMd().setHomePage(this.settings.substitute(attributes.getValue("homepage")));
               this.state = 11;
               this.buffer = new StringBuilder();
            } else if ((this.state != 1 || !"ivyauthor".equals(qName)) && (this.state != 1 || !"repository".equals(qName))) {
               if (this.state == 12 || this.state == 1 && this.isOtherNamespace(qName)) {
                  this.buffer = new StringBuilder();
                  this.state = 12;
                  ExtraInfoHolder extraInfo = new ExtraInfoHolder();
                  extraInfo.setName(qName);

                  for(int i = 0; i < attributes.getLength(); ++i) {
                     extraInfo.getAttributes().put(attributes.getQName(i), attributes.getValue(i));
                  }

                  this.extraInfoStack.push(extraInfo);
               } else if ("configurations".equals(qName)) {
                  this.configurationStarted(attributes);
               } else if ("publications".equals(qName)) {
                  this.publicationsStarted(attributes);
               } else if ("dependencies".equals(qName)) {
                  this.dependenciesStarted(attributes);
               } else if ("conflicts".equals(qName)) {
                  if (!this.descriptorVersion.startsWith("1.")) {
                     Message.deprecated("using conflicts section is deprecated: please use hints section instead. Ivy file URL: " + this.descriptorURL);
                  }

                  this.state = 8;
                  this.checkConfigurations();
               } else if ("artifact".equals(qName)) {
                  this.artifactStarted(qName, attributes);
               } else if ("include".equals(qName) && this.state == 4) {
                  this.addIncludeRule(qName, attributes);
               } else if ("exclude".equals(qName) && this.state == 4) {
                  this.addExcludeRule(qName, attributes);
               } else if ("exclude".equals(qName) && this.state == 10) {
                  this.state = 9;
                  this.parseRule(qName, attributes);
                  this.getMd().addExcludeRule((ExcludeRule)this.confAware);
               } else if ("dependency".equals(qName)) {
                  this.dependencyStarted(attributes);
               } else if ("conf".equals(qName)) {
                  this.confStarted(attributes);
               } else if ("mapped".equals(qName)) {
                  this.dd.addDependencyConfiguration(this.conf, this.settings.substitute(attributes.getValue("name")));
               } else if ((!"conflict".equals(qName) || this.state != 10) && (!"manager".equals(qName) || this.state != 8)) {
                  if ("override".equals(qName) && this.state == 10) {
                     this.mediationOverrideStarted(attributes);
                  } else if ("include".equals(qName) && this.state == 2) {
                     this.includeConfStarted(attributes);
                  } else if (this.validate && this.state != 12 && this.state != 11) {
                     this.addError("unknown tag " + qName);
                  }
               } else {
                  this.managerStarted(attributes, this.state == 8 ? "name" : "manager");
               }
            }

         } catch (Exception ex) {
            if (ex instanceof SAXException) {
               throw (SAXException)ex;
            } else {
               throw new SAXException("Problem occurred while parsing ivy file: " + ex.getMessage(), ex);
            }
         }
      }

      protected String getDefaultParentLocation() {
         return "../ivy.xml";
      }

      protected void extendsStarted(Attributes attributes) throws ParseException {
         String parentOrganisation = this.settings.substitute(attributes.getValue("organisation"));
         String parentModule = this.settings.substitute(attributes.getValue("module"));
         String parentRevision = attributes.getValue("revision") == null ? Ivy.getWorkingRevision() : this.settings.substitute(attributes.getValue("revision"));
         String location = attributes.getValue("location") == null ? this.getDefaultParentLocation() : this.settings.substitute(attributes.getValue("location"));
         String extendType = attributes.getValue("extendType") == null ? "all" : this.settings.substitute(attributes.getValue("extendType").toLowerCase(Locale.US));
         List<String> extendTypes = Arrays.asList(extendType.split(","));
         ModuleId parentMid = new ModuleId(parentOrganisation, parentModule);
         ModuleRevisionId parentMrid = new ModuleRevisionId(parentMid, parentRevision);
         ModuleDescriptor parent = null;
         boolean local = false;

         try {
            parent = this.parseParentModuleOnFilesystem(location);
            if (parent != null) {
               ModuleId foundMid = parent.getResolvedModuleRevisionId().getModuleId();
               if (!foundMid.equals(parentMid)) {
                  Message.info("Found a parent module with unexpected ModuleRevisionId at source location " + location + "! Expected: " + parentMid + ". Found: " + foundMid + ". This parent module will be ignored.");
                  parent = null;
               }
            }

            local = parent != null;
         } catch (IOException e) {
            Message.warn("Unable to parse included ivy file " + location, e);
         }

         if (parent == null) {
            try {
               parent = this.parseOtherIvyFile(parentMrid);
            } catch (ParseException e) {
               Message.warn("Unable to parse included ivy file for " + parentMrid.toString(), e);
            }
         }

         if (parent == null) {
            throw new ParseException("Unable to parse included ivy file for " + parentMrid.toString(), 0);
         } else {
            DefaultExtendsDescriptor ed = new DefaultExtendsDescriptor(parent, location, (String[])extendTypes.toArray(new String[extendTypes.size()]), local);
            this.getMd().addInheritedDescriptor(ed);
            this.mergeWithOtherModuleDescriptor(extendTypes, parent);
         }
      }

      protected void mergeWithOtherModuleDescriptor(List extendTypes, ModuleDescriptor parent) {
         if (extendTypes.contains("all")) {
            this.mergeAll(parent);
         } else {
            if (extendTypes.contains("info")) {
               this.mergeInfo(parent);
            }

            if (extendTypes.contains("configurations")) {
               this.mergeConfigurations(parent);
            }

            if (extendTypes.contains("dependencies")) {
               this.mergeDependencies(parent.getDependencies());
            }

            if (extendTypes.contains("description")) {
               this.mergeDescription(parent.getDescription());
            }

            if (extendTypes.contains("licenses")) {
               this.mergeLicenses(parent.getLicenses());
            }

            if (extendTypes.contains("excludes")) {
               this.mergeExcludes(parent.getAllExcludeRules());
            }
         }

      }

      protected void mergeAll(ModuleDescriptor parent) {
         this.mergeInfo(parent);
         this.mergeConfigurations(parent);
         this.mergeDependencies(parent.getDependencies());
         this.mergeDescription(parent.getDescription());
         this.mergeLicenses(parent.getLicenses());
         this.mergeExcludes(parent.getAllExcludeRules());
      }

      protected void mergeInfo(ModuleDescriptor parent) {
         ModuleRevisionId parentMrid = parent.getModuleRevisionId();
         DefaultModuleDescriptor descriptor = this.getMd();
         ModuleRevisionId currentMrid = descriptor.getModuleRevisionId();
         ModuleRevisionId mergedMrid = ModuleRevisionId.newInstance(mergeValue(parentMrid.getOrganisation(), currentMrid.getOrganisation()), currentMrid.getName(), mergeValue(parentMrid.getBranch(), currentMrid.getBranch()), mergeRevisionValue(parentMrid.getRevision(), currentMrid.getRevision()), mergeValues(parentMrid.getQualifiedExtraAttributes(), currentMrid.getQualifiedExtraAttributes()));
         descriptor.setModuleRevisionId(mergedMrid);
         descriptor.setResolvedModuleRevisionId(mergedMrid);
         descriptor.setStatus(mergeValue(parent.getStatus(), descriptor.getStatus()));
         if (descriptor.getNamespace() == null && parent instanceof DefaultModuleDescriptor) {
            Namespace parentNamespace = ((DefaultModuleDescriptor)parent).getNamespace();
            descriptor.setNamespace(parentNamespace);
         }

         descriptor.getExtraInfos().addAll(parent.getExtraInfos());
      }

      private static String mergeRevisionValue(String inherited, String override) {
         return override != null && !override.equals(Ivy.getWorkingRevision()) ? override : inherited;
      }

      private static String mergeValue(String inherited, String override) {
         return override == null ? inherited : override;
      }

      private static Map mergeValues(Map inherited, Map overrides) {
         Map<String, String> dup = new LinkedHashMap(inherited.size() + overrides.size());
         dup.putAll(inherited);
         dup.putAll(overrides);
         return dup;
      }

      protected void mergeConfigurations(ModuleDescriptor parent) {
         ModuleRevisionId sourceMrid = parent.getModuleRevisionId();

         for(Configuration configuration : parent.getConfigurations()) {
            Message.debug("Merging configuration with: " + configuration.getName());
            this.getMd().addConfiguration(new Configuration(configuration, sourceMrid));
         }

         if (parent instanceof DefaultModuleDescriptor) {
            this.setDefaultConfMapping(((DefaultModuleDescriptor)parent).getDefaultConfMapping());
            this.setDefaultConf(((DefaultModuleDescriptor)parent).getDefaultConf());
            this.getMd().setMappingOverride(((DefaultModuleDescriptor)parent).isMappingOverride());
         }

      }

      protected void mergeDependencies(DependencyDescriptor[] dependencies) {
         DefaultModuleDescriptor md = this.getMd();

         for(DependencyDescriptor dependencyDescriptor : dependencies) {
            Message.debug("Merging dependency with: " + dependencyDescriptor.getDependencyRevisionId().toString());
            md.addDependency(dependencyDescriptor);
         }

      }

      protected void mergeDescription(String description) {
         String current = this.getMd().getDescription();
         if (StringUtils.isNullOrEmpty(current)) {
            this.getMd().setDescription(description);
         }

      }

      public void mergeLicenses(License[] licenses) {
         for(License license : licenses) {
            this.getMd().addLicense(license);
         }

      }

      public void mergeExcludes(ExcludeRule[] excludeRules) {
         for(ExcludeRule excludeRule : excludeRules) {
            this.getMd().addExcludeRule(excludeRule);
         }

      }

      private ModuleDescriptor parseParentModuleOnFilesystem(String location) throws IOException, ParseException {
         if (!"file".equals(this.descriptorURL.getProtocol())) {
            return null;
         } else {
            File file = new File(location);
            if (!file.isAbsolute()) {
               URL url = this.settings.getRelativeUrlResolver().getURL(this.descriptorURL, location);

               try {
                  file = new File(new URI(url.toExternalForm()));
               } catch (URISyntaxException var5) {
                  file = new File(url.getPath());
               }
            }

            file = FileUtil.normalize(file.getAbsolutePath());
            if (!file.exists()) {
               Message.verbose("Parent module doesn't exist on the filesystem: " + file.getAbsolutePath());
               return null;
            } else {
               FileResource res = new FileResource((FileRepository)null, file);
               ModuleDescriptorParser parser = ModuleDescriptorParserRegistry.getInstance().getParser(res);
               return parser.parseDescriptor(this.getSettings(), file.toURI().toURL(), res, this.isValidate());
            }
         }
      }

      protected ModuleDescriptor parseOtherIvyFile(ModuleRevisionId parentMrid) throws ParseException {
         Message.debug("Trying to parse included ivy file by asking repository for module :" + parentMrid.toString());
         DependencyDescriptor dd = new DefaultDependencyDescriptor(parentMrid, true);
         ResolveData data = IvyContext.getContext().getResolveData();
         if (data == null) {
            ResolveEngine engine = IvyContext.getContext().getIvy().getResolveEngine();
            ResolveOptions options = new ResolveOptions();
            options.setDownload(false);
            data = new ResolveData(engine, options);
         }

         DependencyResolver resolver = this.getSettings().getResolver(parentMrid);
         DependencyDescriptor var6 = NameSpaceHelper.toSystem(dd, this.getSettings().getContextNamespace());
         ResolvedModuleRevision otherModule = resolver.getDependency(var6, data);
         if (otherModule == null) {
            throw new ParseException("Unable to find " + parentMrid.toString(), 0);
         } else {
            return otherModule.getDescriptor();
         }
      }

      protected void publicationsStarted(Attributes attributes) {
         this.state = 3;
         this.artifactsDeclared = true;
         this.checkConfigurations();
         String defaultConf = this.settings.substitute(attributes.getValue("defaultconf"));
         if (defaultConf != null) {
            this.setPublicationsDefaultConf(defaultConf);
         }

      }

      protected void setPublicationsDefaultConf(String defaultConf) {
         this.publicationsDefaultConf = defaultConf == null ? null : StringUtils.splitToArray(defaultConf);
      }

      protected boolean isOtherNamespace(String qName) {
         return qName.contains(":");
      }

      protected void managerStarted(Attributes attributes, String managerAtt) {
         String org = this.settings.substitute(attributes.getValue("org"));
         if (org == null) {
            org = "*";
         }

         String mod = this.settings.substitute(attributes.getValue("module"));
         if (mod == null) {
            mod = "*";
         }

         String name = this.settings.substitute(attributes.getValue(managerAtt));
         String rev = this.settings.substitute(attributes.getValue("rev"));
         ConflictManager cm;
         if (rev != null) {
            cm = new FixedConflictManager(StringUtils.splitToArray(rev));
         } else {
            if (name == null) {
               this.addError("bad conflict manager: no manager nor rev");
               return;
            }

            cm = this.settings.getConflictManager(name);
            if (cm == null) {
               this.addError("unknown conflict manager: " + name);
               return;
            }
         }

         String matcherName = this.settings.substitute(attributes.getValue("matcher"));
         PatternMatcher matcher = matcherName == null ? this.defaultMatcher : this.settings.getMatcher(matcherName);
         if (matcher == null) {
            this.addError("unknown matcher: " + matcherName);
         } else {
            this.getMd().addConflictManager(new ModuleId(org, mod), matcher, cm);
         }
      }

      protected void mediationOverrideStarted(Attributes attributes) {
         String org = this.settings.substitute(attributes.getValue("org"));
         if (org == null) {
            org = "*";
         }

         String mod = this.settings.substitute(attributes.getValue("module"));
         if (mod == null) {
            mod = "*";
         }

         String rev = this.settings.substitute(attributes.getValue("rev"));
         String branch = this.settings.substitute(attributes.getValue("branch"));
         String matcherName = this.settings.substitute(attributes.getValue("matcher"));
         PatternMatcher matcher = matcherName == null ? this.defaultMatcher : this.settings.getMatcher(matcherName);
         if (matcher == null) {
            this.addError("unknown matcher: " + matcherName);
         } else {
            this.getMd().addDependencyDescriptorMediator(new ModuleId(org, mod), matcher, new OverrideDependencyDescriptorMediator(branch, rev));
         }
      }

      protected void includeConfStarted(Attributes attributes) throws SAXException, IOException, ParserConfigurationException, ParseException {
         URL url = this.settings.getRelativeUrlResolver().getURL(this.descriptorURL, this.settings.substitute(attributes.getValue("file")), this.settings.substitute(attributes.getValue("url")));
         if (url == null) {
            throw new SAXException("include tag must have a file or an url attribute");
         } else {
            Parser parser = new Parser(this.getModuleDescriptorParser(), this.settings);
            parser.setInput(url);
            parser.setMd(new DefaultModuleDescriptor(this.getModuleDescriptorParser(), new URLResource(url)));
            XMLHelper.parse(url, (URL)null, parser);

            for(Configuration config : parser.getModuleDescriptor().getConfigurations()) {
               this.getMd().addConfiguration(config);
            }

            if (parser.getDefaultConfMapping() != null) {
               Message.debug("setting default conf mapping from imported configurations file: " + parser.getDefaultConfMapping());
               this.setDefaultConfMapping(parser.getDefaultConfMapping());
            }

            if (parser.getDefaultConf() != null) {
               Message.debug("setting default conf from imported configurations file: " + parser.getDefaultConf());
               this.setDefaultConf(parser.getDefaultConf());
            }

            if (parser.getMd().isMappingOverride()) {
               Message.debug("enabling mapping-override from imported configurations file");
               this.getMd().setMappingOverride(true);
            }

         }
      }

      protected void confStarted(Attributes attributes) {
         String conf = this.settings.substitute(attributes.getValue("name"));
         switch (this.state) {
            case 2:
               String visibility = this.settings.substitute(attributes.getValue("visibility"));
               String ext = this.settings.substitute(attributes.getValue("extends"));
               String transitiveValue = attributes.getValue("transitive");
               boolean transitive = transitiveValue == null || Boolean.valueOf(attributes.getValue("transitive"));
               String deprecated = attributes.getValue("deprecated");
               Configuration configuration = new Configuration(conf, Configuration.Visibility.getVisibility(visibility == null ? "public" : visibility), this.settings.substitute(attributes.getValue("description")), ext == null ? null : ext.split(","), transitive, deprecated);
               ExtendableItemHelper.fillExtraAttributes(this.settings, configuration, attributes, (List)Arrays.asList("name", "visibility", "extends", "transitive", "description", "deprecated"));
               this.getMd().addConfiguration(configuration);
               break;
            case 3:
               if ("*".equals(conf)) {
                  for(String config : this.getMd().getConfigurationsNames()) {
                     this.artifact.addConfiguration(config);
                     this.getMd().addArtifact(config, this.artifact);
                  }
               } else {
                  this.artifact.addConfiguration(conf);
                  this.getMd().addArtifact(conf, this.artifact);
               }
               break;
            case 4:
               this.conf = conf;
               String mappeds = this.settings.substitute(attributes.getValue("mapped"));
               if (mappeds != null) {
                  for(String mapped : StringUtils.splitToArray(mappeds)) {
                     this.dd.addDependencyConfiguration(conf, mapped);
                  }
               }
               break;
            case 5:
            case 6:
            case 7:
               this.addConfiguration(conf);
               break;
            default:
               if (this.validate) {
                  this.addError("conf tag found in invalid tag: " + this.state);
               }
         }

      }

      protected void dependencyStarted(Attributes attributes) {
         this.state = 4;
         String org = this.settings.substitute(attributes.getValue("org"));
         if (org == null) {
            org = this.getMd().getModuleRevisionId().getOrganisation();
         }

         boolean force = Boolean.valueOf(this.settings.substitute(attributes.getValue("force")));
         boolean changing = Boolean.valueOf(this.settings.substitute(attributes.getValue("changing")));
         String transitiveValue = this.settings.substitute(attributes.getValue("transitive"));
         boolean transitive = transitiveValue == null || Boolean.valueOf(attributes.getValue("transitive"));
         String name = this.settings.substitute(attributes.getValue("name"));
         String branch = this.settings.substitute(attributes.getValue("branch"));
         String branchConstraint = this.settings.substitute(attributes.getValue("branchConstraint"));
         String rev = this.settings.substitute(attributes.getValue("rev"));
         String revConstraint = this.settings.substitute(attributes.getValue("revConstraint"));
         Map<String, String> extraAttributes = ExtendableItemHelper.getExtraAttributes(this.settings, attributes, XmlModuleDescriptorParser.DEPENDENCY_REGULAR_ATTRIBUTES);
         ModuleRevisionId revId = ModuleRevisionId.newInstance(org, name, branch, rev, extraAttributes);
         ModuleRevisionId dynamicId = null;
         if (revConstraint == null && branchConstraint == null) {
            dynamicId = ModuleRevisionId.newInstance(org, name, branch, rev, extraAttributes, false);
         } else if (branchConstraint == null) {
            dynamicId = ModuleRevisionId.newInstance(org, name, (String)null, revConstraint, extraAttributes, false);
         } else {
            dynamicId = ModuleRevisionId.newInstance(org, name, branchConstraint, revConstraint, extraAttributes);
         }

         this.dd = new DefaultDependencyDescriptor(this.getMd(), revId, dynamicId, force, changing, transitive);
         this.getMd().addDependency(this.dd);
         String confs = this.settings.substitute(attributes.getValue("conf"));
         if (confs != null && confs.length() > 0) {
            this.parseDepsConfs(confs, this.dd);
         }

      }

      protected void artifactStarted(String qName, Attributes attributes) throws MalformedURLException {
         if (this.state == 3) {
            String artName = this.settings.substitute(attributes.getValue("name"));
            if (artName == null) {
               artName = this.getMd().getModuleRevisionId().getName();
            }

            String type = this.settings.substitute(attributes.getValue("type"));
            if (type == null) {
               type = "jar";
            }

            String ext = this.settings.substitute(attributes.getValue("ext"));
            if (ext == null) {
               ext = type;
            }

            String url = this.settings.substitute(attributes.getValue("url"));
            this.artifact = new MDArtifact(this.getMd(), artName, type, ext, url == null ? null : new URL(url), ExtendableItemHelper.getExtraAttributes(this.settings, attributes, Arrays.asList("ext", "type", "name", "conf")));
            String confs = this.settings.substitute(attributes.getValue("conf"));
            if (confs != null && confs.length() > 0) {
               String[] configs = "*".equals(confs) ? this.getMd().getConfigurationsNames() : StringUtils.splitToArray(confs);

               for(String config : configs) {
                  this.artifact.addConfiguration(config);
                  this.getMd().addArtifact(config, this.artifact);
               }
            }
         } else if (this.state == 4) {
            this.addDependencyArtifacts(qName, attributes);
         } else if (this.validate) {
            this.addError("artifact tag found in invalid tag: " + this.state);
         }

      }

      protected void dependenciesStarted(Attributes attributes) {
         this.state = 10;
         String defaultConf = this.settings.substitute(attributes.getValue("defaultconf"));
         if (defaultConf != null) {
            this.setDefaultConf(defaultConf);
         }

         defaultConf = this.settings.substitute(attributes.getValue("defaultconfmapping"));
         if (defaultConf != null) {
            this.setDefaultConfMapping(defaultConf);
         }

         String confMappingOverride = this.settings.substitute(attributes.getValue("confmappingoverride"));
         if (confMappingOverride != null) {
            this.getMd().setMappingOverride(Boolean.valueOf(confMappingOverride));
         }

         this.checkConfigurations();
      }

      protected void configurationStarted(Attributes attributes) {
         this.state = 2;
         this.setDefaultConfMapping(this.settings.substitute(attributes.getValue("defaultconfmapping")));
         this.setDefaultConf(this.settings.substitute(attributes.getValue("defaultconf")));
         this.getMd().setMappingOverride(Boolean.valueOf(this.settings.substitute(attributes.getValue("confmappingoverride"))));
      }

      protected void infoStarted(Attributes attributes) {
         this.state = 1;
         String org = this.settings.substitute(attributes.getValue("organisation"));
         String module = this.settings.substitute(attributes.getValue("module"));
         String revision = this.settings.substitute(attributes.getValue("revision"));
         String branch = this.settings.substitute(attributes.getValue("branch"));
         this.getMd().setModuleRevisionId(ModuleRevisionId.newInstance(org, module, branch, revision, ExtendableItemHelper.getExtraAttributes(this.settings, attributes, Arrays.asList("organisation", "module", "revision", "status", "publication", "branch", "namespace", "default", "resolver"))));
         String namespace = this.settings.substitute(attributes.getValue("namespace"));
         if (namespace != null) {
            Namespace ns = this.settings.getNamespace(namespace);
            if (ns == null) {
               Message.warn("namespace not found for " + this.getMd().getModuleRevisionId() + ": " + namespace);
            } else {
               this.getMd().setNamespace(ns);
            }
         }

         String status = this.settings.substitute(attributes.getValue("status"));
         this.getMd().setStatus(status == null ? this.settings.getStatusManager().getDefaultStatus() : status);
         this.getMd().setDefault(Boolean.valueOf(this.settings.substitute(attributes.getValue("default"))));
         String pubDate = this.settings.substitute(attributes.getValue("publication"));
         if (pubDate != null && pubDate.length() > 0) {
            try {
               this.getMd().setPublicationDate(DateUtil.parse(pubDate));
            } catch (ParseException var10) {
               this.addError("invalid publication date format: " + pubDate);
               this.getMd().setPublicationDate(this.getDefaultPubDate());
            }
         } else {
            this.getMd().setPublicationDate(this.getDefaultPubDate());
         }

      }

      protected void ivyModuleStarted(Attributes attributes) throws SAXException {
         this.descriptorVersion = attributes.getValue("version");
         int versionIndex = ALLOWED_VERSIONS.indexOf(this.descriptorVersion);
         if (versionIndex == -1) {
            this.addError("invalid version " + this.descriptorVersion);
            throw new SAXException("invalid version " + this.descriptorVersion);
         } else {
            if (versionIndex >= ALLOWED_VERSIONS.indexOf("1.3")) {
               Message.debug("post 1.3 ivy file: using exact as default matcher");
               this.defaultMatcher = this.settings.getMatcher("exact");
            } else {
               Message.debug("pre 1.3 ivy file: using exactOrRegexp as default matcher");
               this.defaultMatcher = this.settings.getMatcher("exactOrRegexp");
            }

            for(int i = 0; i < attributes.getLength(); ++i) {
               if (attributes.getQName(i).startsWith("xmlns:")) {
                  this.getMd().addExtraAttributeNamespace(attributes.getQName(i).substring("xmlns:".length()), attributes.getValue(i));
               }
            }

         }
      }

      protected void addDependencyArtifacts(String tag, Attributes attributes) throws MalformedURLException {
         this.state = 5;
         this.parseRule(tag, attributes);
      }

      protected void addIncludeRule(String tag, Attributes attributes) throws MalformedURLException {
         this.state = 6;
         this.parseRule(tag, attributes);
      }

      protected void addExcludeRule(String tag, Attributes attributes) throws MalformedURLException {
         this.state = 7;
         this.parseRule(tag, attributes);
      }

      protected void parseRule(String tag, Attributes attributes) throws MalformedURLException {
         String name = this.settings.substitute(attributes.getValue("name"));
         if (name == null) {
            name = this.settings.substitute(attributes.getValue("artifact"));
            if (name == null) {
               name = "artifact".equals(tag) ? this.dd.getDependencyId().getName() : "*";
            }
         }

         String type = this.settings.substitute(attributes.getValue("type"));
         if (type == null) {
            type = "artifact".equals(tag) ? "jar" : "*";
         }

         String ext = this.settings.substitute(attributes.getValue("ext"));
         if (ext == null) {
            ext = type;
         }

         switch (this.state) {
            case 5:
               String url = this.settings.substitute(attributes.getValue("url"));
               Map<String, String> extraAtt = ExtendableItemHelper.getExtraAttributes(this.settings, attributes, Arrays.asList("name", "type", "ext", "url", "conf"));
               this.confAware = new DefaultDependencyArtifactDescriptor(this.dd, name, type, ext, url == null ? null : new URL(url), extraAtt);
               break;
            case 6:
               PatternMatcher matcher = this.getPatternMatcher(attributes.getValue("matcher"));
               String org = this.settings.substitute(attributes.getValue("org"));
               if (org == null) {
                  org = "*";
               }

               String module = this.settings.substitute(attributes.getValue("module"));
               if (module == null) {
                  module = "*";
               }

               ArtifactId aid = new ArtifactId(new ModuleId(org, module), name, type, ext);
               Map<String, String> extraAtt = ExtendableItemHelper.getExtraAttributes(this.settings, attributes, Arrays.asList("org", "module", "name", "type", "ext", "matcher", "conf"));
               this.confAware = new DefaultIncludeRule(aid, matcher, extraAtt);
               break;
            default:
               PatternMatcher matcher = this.getPatternMatcher(attributes.getValue("matcher"));
               String org = this.settings.substitute(attributes.getValue("org"));
               if (org == null) {
                  org = "*";
               }

               String module = this.settings.substitute(attributes.getValue("module"));
               if (module == null) {
                  module = "*";
               }

               ArtifactId aid = new ArtifactId(new ModuleId(org, module), name, type, ext);
               Map<String, String> extraAtt = ExtendableItemHelper.getExtraAttributes(this.settings, attributes, Arrays.asList("org", "module", "name", "type", "ext", "matcher", "conf"));
               this.confAware = new DefaultExcludeRule(aid, matcher, extraAtt);
         }

         String confs = this.settings.substitute(attributes.getValue("conf"));
         if (confs != null && confs.length() > 0) {
            String[] configs = "*".equals(confs) ? this.getMd().getConfigurationsNames() : StringUtils.splitToArray(confs);

            for(String config : configs) {
               this.addConfiguration(config);
            }
         }

      }

      protected void addConfiguration(String c) {
         this.confAware.addConfiguration(c);
         if (this.state != 9) {
            if (this.confAware instanceof DependencyArtifactDescriptor) {
               this.dd.addDependencyArtifact(c, (DependencyArtifactDescriptor)this.confAware);
            } else if (this.confAware instanceof IncludeRule) {
               this.dd.addIncludeRule(c, (IncludeRule)this.confAware);
            } else if (this.confAware instanceof ExcludeRule) {
               this.dd.addExcludeRule(c, (ExcludeRule)this.confAware);
            }
         }

      }

      protected PatternMatcher getPatternMatcher(String m) {
         String matcherName = this.settings.substitute(m);
         PatternMatcher matcher = matcherName == null ? this.defaultMatcher : this.settings.getMatcher(matcherName);
         if (matcher == null) {
            throw new IllegalArgumentException("unknown matcher " + matcherName);
         } else {
            return matcher;
         }
      }

      public void characters(char[] ch, int start, int length) throws SAXException {
         if (this.buffer != null) {
            this.buffer.append(ch, start, length);
         }

      }

      public void endElement(String uri, String localName, String qName) throws SAXException {
         if (this.state == 3 && "artifact".equals(qName) && this.artifact.getConfigurations().length == 0) {
            String[] configs = this.publicationsDefaultConf == null ? this.getMd().getConfigurationsNames() : this.publicationsDefaultConf;

            for(String config : configs) {
               this.artifact.addConfiguration(config);
               this.getMd().addArtifact(config, this.artifact);
            }
         } else if ("configurations".equals(qName)) {
            this.checkConfigurations();
         } else if (this.state == 5 && "artifact".equals(qName) || this.state == 6 && "include".equals(qName) || this.state == 7 && "exclude".equals(qName)) {
            this.state = 4;
            if (this.confAware.getConfigurations().length == 0) {
               for(String config : this.getMd().getConfigurationsNames()) {
                  this.addConfiguration(config);
               }
            }

            this.confAware = null;
         } else if ("exclude".equals(qName) && this.state == 9) {
            if (this.confAware.getConfigurations().length == 0) {
               for(String config : this.getMd().getConfigurationsNames()) {
                  this.addConfiguration(config);
               }
            }

            this.confAware = null;
            this.state = 10;
         } else if ("dependency".equals(qName) && this.state == 4) {
            if (this.dd.getModuleConfigurations().length == 0) {
               this.parseDepsConfs(this.getDefaultConf(), this.dd);
            }

            this.state = 10;
         } else if ("dependencies".equals(qName) && this.state == 10) {
            this.state = 0;
         } else if (this.state == 1 && "info".equals(qName)) {
            this.state = 0;
         } else if (this.state == 11 && "description".equals(qName)) {
            this.getMd().setDescription(this.buffer == null ? "" : this.buffer.toString().trim());
            this.buffer = null;
            this.state = 1;
         } else if (this.state == 12) {
            String content = this.buffer == null ? "" : this.buffer.toString();
            this.buffer = null;
            ExtraInfoHolder extraInfo = (ExtraInfoHolder)this.extraInfoStack.pop();
            extraInfo.setContent(content);
            if (this.extraInfoStack.isEmpty()) {
               this.getMd().addExtraInfo(extraInfo);
               this.state = 1;
            } else {
               ExtraInfoHolder parentHolder = (ExtraInfoHolder)this.extraInfoStack.peek();
               parentHolder.getNestedExtraInfoHolder().add(extraInfo);
            }
         } else if (this.state == 11) {
            if (this.buffer.toString().endsWith("<" + qName + ">")) {
               this.buffer.deleteCharAt(this.buffer.length() - 1);
               this.buffer.append("/>");
            } else {
               this.buffer.append("</").append(qName).append(">");
            }
         }

      }

      protected void checkConfigurations() {
         if (this.getMd().getConfigurations().length == 0) {
            this.getMd().addConfiguration(new Configuration("default"));
         }

      }

      protected void replaceConfigurationWildcards() {
         for(Configuration config : this.getMd().getConfigurations()) {
            config.replaceWildcards(this.getMd());
         }

      }

      protected ParserSettings getSettings() {
         return this.settings;
      }

      protected URL getDescriptorURL() {
         return this.descriptorURL;
      }

      protected InputStream getDescriptorInput() {
         return this.descriptorInput;
      }

      protected int getState() {
         return this.state;
      }

      protected void setState(int state) {
         this.state = state;
      }

      protected PatternMatcher getDefaultMatcher() {
         return this.defaultMatcher;
      }

      protected DefaultDependencyDescriptor getDd() {
         return this.dd;
      }

      protected void setDd(DefaultDependencyDescriptor dd) {
         this.dd = dd;
      }

      protected ConfigurationAware getConfAware() {
         return this.confAware;
      }

      protected void setConfAware(ConfigurationAware confAware) {
         this.confAware = confAware;
      }

      protected MDArtifact getArtifact() {
         return this.artifact;
      }

      protected void setArtifact(MDArtifact artifact) {
         this.artifact = artifact;
      }

      protected String getConf() {
         return this.conf;
      }

      protected void setConf(String conf) {
         this.conf = conf;
      }

      protected boolean isArtifactsDeclared() {
         return this.artifactsDeclared;
      }

      protected void setArtifactsDeclared(boolean artifactsDeclared) {
         this.artifactsDeclared = artifactsDeclared;
      }

      protected StringBuilder getBuffer() {
         return this.buffer;
      }

      protected void setBuffer(StringBuilder buffer) {
         this.buffer = buffer;
      }

      protected String getDescriptorVersion() {
         return this.descriptorVersion;
      }

      protected void setDescriptorVersion(String descriptorVersion) {
         this.descriptorVersion = descriptorVersion;
      }

      protected String[] getPublicationsDefaultConf() {
         return this.publicationsDefaultConf;
      }

      protected void setPublicationsDefaultConf(String[] publicationsDefaultConf) {
         this.publicationsDefaultConf = publicationsDefaultConf;
      }

      protected boolean isValidate() {
         return this.validate;
      }

      protected URL getSchemaURL() {
         return this.getClass().getResource("ivy.xsd");
      }

      static {
         String ivyXSDContent = null;
         InputStream is = Parser.class.getResourceAsStream("ivy.xsd");
         if (is != null) {
            StringBuilder sb = new StringBuilder();

            try {
               BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

               String line;
               for(line = null; (line = reader.readLine()) != null; sb.append(line)) {
                  if (sb.length() != 0) {
                     sb.append("\n");
                  }
               }
            } catch (UnsupportedEncodingException var13) {
               String var17 = null;
            } catch (IOException var14) {
               String var16 = null;
            } finally {
               try {
                  is.close();
               } catch (Exception var12) {
               }

            }

            ivyXSDContent = sb.length() == 0 ? null : sb.toString();
         }

         IVY_XSD_CONTENT = ivyXSDContent;
      }

      public static final class State {
         public static final int NONE = 0;
         public static final int INFO = 1;
         public static final int CONF = 2;
         public static final int PUB = 3;
         public static final int DEP = 4;
         public static final int DEP_ARTIFACT = 5;
         public static final int ARTIFACT_INCLUDE = 6;
         public static final int ARTIFACT_EXCLUDE = 7;
         public static final int CONFLICT = 8;
         public static final int EXCLUDE = 9;
         public static final int DEPS = 10;
         public static final int DESCRIPTION = 11;
         public static final int EXTRA_INFO = 12;

         private State() {
         }
      }
   }
}
