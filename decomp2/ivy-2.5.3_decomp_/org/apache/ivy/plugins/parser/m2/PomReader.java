package org.apache.ivy.plugins.parser.m2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.descriptor.License;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.XMLHelper;
import org.apache.ivy.util.url.URLHandlerRegistry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class PomReader {
   private static final String PROFILES_ELEMENT = "profiles";
   private static final String PACKAGING = "packaging";
   private static final String DEPENDENCY = "dependency";
   private static final String DEPENDENCIES = "dependencies";
   private static final String DEPENDENCY_MGT = "dependencyManagement";
   private static final String PROJECT = "project";
   private static final String MODEL = "model";
   private static final String GROUP_ID = "groupId";
   private static final String ARTIFACT_ID = "artifactId";
   private static final String VERSION = "version";
   private static final String DESCRIPTION = "description";
   private static final String HOMEPAGE = "url";
   private static final String LICENSES = "licenses";
   private static final String LICENSE = "license";
   private static final String LICENSE_NAME = "name";
   private static final String LICENSE_URL = "url";
   private static final String PARENT = "parent";
   private static final String SCOPE = "scope";
   private static final String CLASSIFIER = "classifier";
   private static final String OPTIONAL = "optional";
   private static final String EXCLUSIONS = "exclusions";
   private static final String EXCLUSION = "exclusion";
   private static final String DISTRIBUTION_MGT = "distributionManagement";
   private static final String RELOCATION = "relocation";
   private static final String PROPERTIES = "properties";
   private static final String PLUGINS = "plugins";
   private static final String PLUGIN = "plugin";
   private static final String TYPE = "type";
   private static final String PROFILE = "profile";
   private final Map properties = new HashMap();
   private final Element projectElement;
   private final Element parentElement;

   public PomReader(URL descriptorURL, Resource res) throws IOException, SAXException {
      InputStream stream = new AddDTDFilterInputStream(URLHandlerRegistry.getDefault().openStream(descriptorURL));
      InputSource source = new InputSource(stream);
      source.setSystemId(XMLHelper.toSystemId(descriptorURL));

      try {
         Document pomDomDoc = XMLHelper.parseToDom(source, new EntityResolver() {
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
               return systemId != null && systemId.endsWith("m2-entities.ent") ? new InputSource(PomReader.class.getResourceAsStream("m2-entities.ent")) : null;
            }
         }, true, XMLHelper.ExternalResources.IGNORE);
         this.projectElement = pomDomDoc.getDocumentElement();
         if (!"project".equals(this.projectElement.getNodeName()) && !"model".equals(this.projectElement.getNodeName())) {
            throw new SAXParseException("project must be the root tag", res.getName(), res.getName(), 0, 0);
         }

         this.parentElement = getFirstChildElement(this.projectElement, "parent");
      } finally {
         try {
            stream.close();
         } catch (IOException var11) {
         }

      }

      for(Map.Entry envEntry : System.getenv().entrySet()) {
         this.setProperty("env." + (String)envEntry.getKey(), (String)envEntry.getValue());
      }

      Properties sysProps = System.getProperties();

      for(String sysProp : sysProps.stringPropertyNames()) {
         this.setProperty(sysProp, sysProps.getProperty(sysProp));
      }

   }

   public boolean hasParent() {
      return this.parentElement != null;
   }

   public void setProperty(String prop, String val) {
      if (!this.properties.containsKey(prop) && val != null) {
         this.properties.put(prop, val);
      }

   }

   public String getGroupId() {
      String groupId = getFirstChildText(this.projectElement, "groupId");
      if (groupId == null) {
         groupId = getFirstChildText(this.parentElement, "groupId");
      }

      return this.replaceProps(groupId);
   }

   public String getParentGroupId() {
      String groupId = getFirstChildText(this.parentElement, "groupId");
      if (groupId == null) {
         groupId = getFirstChildText(this.projectElement, "groupId");
      }

      return this.replaceProps(groupId);
   }

   public String getArtifactId() {
      String val = getFirstChildText(this.projectElement, "artifactId");
      if (val == null) {
         val = getFirstChildText(this.parentElement, "artifactId");
      }

      return this.replaceProps(val);
   }

   public String getParentArtifactId() {
      String val = getFirstChildText(this.parentElement, "artifactId");
      if (val == null) {
         val = getFirstChildText(this.projectElement, "artifactId");
      }

      return this.replaceProps(val);
   }

   public String getVersion() {
      String val = getFirstChildText(this.projectElement, "version");
      if (val == null) {
         val = getFirstChildText(this.parentElement, "version");
      }

      return this.replaceProps(val);
   }

   public String getParentVersion() {
      String val = getFirstChildText(this.parentElement, "version");
      if (val == null) {
         val = getFirstChildText(this.projectElement, "version");
      }

      return this.replaceProps(val);
   }

   public String getPackaging() {
      String val = getFirstChildText(this.projectElement, "packaging");
      return val == null ? "jar" : this.replaceProps(val);
   }

   public String getHomePage() {
      String val = getFirstChildText(this.projectElement, "url");
      if (val == null) {
         val = "";
      }

      return val;
   }

   public String getDescription() {
      String val = getFirstChildText(this.projectElement, "description");
      if (val == null) {
         val = "";
      }

      return val.trim();
   }

   public License[] getLicenses() {
      Element licenses = getFirstChildElement(this.projectElement, "licenses");
      if (licenses == null) {
         return new License[0];
      } else {
         licenses.normalize();
         List<License> lics = new ArrayList();

         for(Element license : getAllChilds(licenses)) {
            if ("license".equals(license.getNodeName())) {
               String name = getFirstChildText(license, "name");
               String url = getFirstChildText(license, "url");
               if (name != null || url != null) {
                  if (name == null) {
                     name = "Unknown License";
                  }

                  lics.add(new License(name, url));
               }
            }
         }

         return (License[])lics.toArray(new License[lics.size()]);
      }
   }

   public ModuleRevisionId getRelocation() {
      Element distrMgt = getFirstChildElement(this.projectElement, "distributionManagement");
      Element relocation = getFirstChildElement(distrMgt, "relocation");
      if (relocation == null) {
         return null;
      } else {
         String relocGroupId = getFirstChildText(relocation, "groupId");
         if (relocGroupId == null) {
            relocGroupId = this.getGroupId();
         }

         String relocArtId = getFirstChildText(relocation, "artifactId");
         if (relocArtId == null) {
            relocArtId = this.getArtifactId();
         }

         String relocVersion = getFirstChildText(relocation, "version");
         if (relocVersion == null) {
            relocVersion = this.getVersion();
         }

         return ModuleRevisionId.newInstance(relocGroupId, relocArtId, relocVersion);
      }
   }

   public List getDependencies() {
      return this.getDependencies(this.projectElement);
   }

   private List getDependencies(Element parent) {
      Element dependenciesElement = getFirstChildElement(parent, "dependencies");
      if (dependenciesElement == null) {
         return Collections.emptyList();
      } else {
         List<PomDependencyData> dependencies = new LinkedList();
         NodeList children = dependenciesElement.getChildNodes();
         int i = 0;

         for(int sz = children.getLength(); i < sz; ++i) {
            Node node = children.item(i);
            if (node instanceof Element && "dependency".equals(node.getNodeName())) {
               dependencies.add(new PomDependencyData((Element)node));
            }
         }

         return dependencies;
      }
   }

   public List getDependencyMgt() {
      return this.getDependencyMgt(this.projectElement);
   }

   private List getDependencyMgt(Element parent) {
      Element dependenciesElement = getFirstChildElement(getFirstChildElement(parent, "dependencyManagement"), "dependencies");
      if (dependenciesElement == null) {
         return Collections.emptyList();
      } else {
         List<PomDependencyMgt> dependencies = new LinkedList();
         NodeList children = dependenciesElement.getChildNodes();
         int i = 0;

         for(int sz = children.getLength(); i < sz; ++i) {
            Node node = children.item(i);
            if (node instanceof Element && "dependency".equals(node.getNodeName())) {
               dependencies.add(new PomDependencyMgtElement((Element)node));
            }
         }

         return dependencies;
      }
   }

   public List getProfiles() {
      Element profilesElement = getFirstChildElement(this.projectElement, "profiles");
      if (profilesElement == null) {
         return Collections.emptyList();
      } else {
         List<PomProfileElement> result = new LinkedList();
         NodeList children = profilesElement.getChildNodes();
         int i = 0;

         for(int sz = children.getLength(); i < sz; ++i) {
            Node node = children.item(i);
            if (node instanceof Element && "profile".equals(node.getNodeName())) {
               result.add(new PomProfileElement((Element)node));
            }
         }

         return result;
      }
   }

   public List getPlugins() {
      return this.getPlugins(this.projectElement);
   }

   private List getPlugins(Element parent) {
      Element buildElement = getFirstChildElement(parent, "build");
      Element pluginsElement = getFirstChildElement(buildElement, "plugins");
      if (pluginsElement == null) {
         return Collections.emptyList();
      } else {
         NodeList children = pluginsElement.getChildNodes();
         List<PomPluginElement> plugins = new LinkedList();

         for(int i = 0; i < children.getLength(); ++i) {
            Node node = children.item(i);
            if (node instanceof Element && "plugin".equals(node.getNodeName())) {
               plugins.add(new PomPluginElement((Element)node));
            }
         }

         return plugins;
      }
   }

   private static Map getProperties(Element parent) {
      Element propsEl = getFirstChildElement(parent, "properties");
      if (propsEl == null) {
         return Collections.emptyMap();
      } else {
         propsEl.normalize();
         Map<String, String> props = new HashMap();

         for(Element prop : getAllChilds(propsEl)) {
            props.put(prop.getNodeName(), getTextContent(prop));
         }

         return props;
      }
   }

   public Map getPomProperties() {
      return new HashMap(getProperties(this.projectElement));
   }

   private String replaceProps(String val) {
      return val == null ? null : IvyPatternHelper.substituteVariables(val, this.properties).trim();
   }

   private static String getTextContent(Element param0) {
      // $FF: Couldn't be decompiled
   }

   private static String getFirstChildText(Element parentElem, String name) {
      Element node = getFirstChildElement(parentElem, name);
      return node != null ? getTextContent(node) : null;
   }

   private static Element getFirstChildElement(Element parentElem, String name) {
      if (parentElem == null) {
         return null;
      } else {
         NodeList childs = parentElem.getChildNodes();

         for(int i = 0; i < childs.getLength(); ++i) {
            Node node = childs.item(i);
            if (node instanceof Element && name.equals(node.getNodeName())) {
               return (Element)node;
            }
         }

         return null;
      }
   }

   private static List getAllChilds(Element parent) {
      List<Element> r = new LinkedList();
      if (parent != null) {
         NodeList childs = parent.getChildNodes();

         for(int i = 0; i < childs.getLength(); ++i) {
            Node node = childs.item(i);
            if (node instanceof Element) {
               r.add((Element)node);
            }
         }
      }

      return r;
   }

   public class PomDependencyMgtElement implements PomDependencyMgt {
      private final Element depElement;

      public PomDependencyMgtElement(PomDependencyMgtElement copyFrom) {
         this((Element)copyFrom.depElement);
      }

      PomDependencyMgtElement(Element depElement) {
         this.depElement = depElement;
      }

      public String getGroupId() {
         String val = PomReader.getFirstChildText(this.depElement, "groupId");
         return PomReader.this.replaceProps(val);
      }

      public String getArtifactId() {
         String val = PomReader.getFirstChildText(this.depElement, "artifactId");
         return PomReader.this.replaceProps(val);
      }

      public String getVersion() {
         String val = PomReader.getFirstChildText(this.depElement, "version");
         return PomReader.this.replaceProps(val);
      }

      public String getScope() {
         String val = PomReader.getFirstChildText(this.depElement, "scope");
         return PomReader.this.replaceProps(val);
      }

      public List getExcludedModules() {
         Element exclusionsElement = PomReader.getFirstChildElement(this.depElement, "exclusions");
         if (exclusionsElement == null) {
            return Collections.emptyList();
         } else {
            List<ModuleId> exclusions = new LinkedList();
            NodeList children = exclusionsElement.getChildNodes();
            int i = 0;

            for(int sz = children.getLength(); i < sz; ++i) {
               Node node = children.item(i);
               if (node instanceof Element && "exclusion".equals(node.getNodeName())) {
                  String groupId = PomReader.getFirstChildText((Element)node, "groupId");
                  String artifactId = PomReader.getFirstChildText((Element)node, "artifactId");
                  if (groupId != null && artifactId != null) {
                     exclusions.add(ModuleId.newInstance(groupId, artifactId));
                  }
               }
            }

            return exclusions;
         }
      }
   }

   public class PomPluginElement implements PomDependencyMgt {
      private Element pluginElement;

      PomPluginElement(Element pluginElement) {
         this.pluginElement = pluginElement;
      }

      public String getGroupId() {
         String val = PomReader.getFirstChildText(this.pluginElement, "groupId");
         return PomReader.this.replaceProps(val);
      }

      public String getArtifactId() {
         String val = PomReader.getFirstChildText(this.pluginElement, "artifactId");
         return PomReader.this.replaceProps(val);
      }

      public String getVersion() {
         String val = PomReader.getFirstChildText(this.pluginElement, "version");
         return PomReader.this.replaceProps(val);
      }

      public String getScope() {
         return null;
      }

      public List getExcludedModules() {
         return Collections.emptyList();
      }
   }

   public class PomDependencyData extends PomDependencyMgtElement {
      private final Element depElement;

      public PomDependencyData(PomDependencyData copyFrom) {
         this((Element)copyFrom.depElement);
      }

      PomDependencyData(Element depElement) {
         super((Element)depElement);
         this.depElement = depElement;
      }

      public String getScope() {
         String val = PomReader.getFirstChildText(this.depElement, "scope");
         return this.emptyIsNull(PomReader.this.replaceProps(val));
      }

      public String getClassifier() {
         String val = PomReader.getFirstChildText(this.depElement, "classifier");
         return this.emptyIsNull(PomReader.this.replaceProps(val));
      }

      public String getType() {
         String val = PomReader.getFirstChildText(this.depElement, "type");
         return this.emptyIsNull(PomReader.this.replaceProps(val));
      }

      public boolean isOptional() {
         return Boolean.parseBoolean(PomReader.getFirstChildText(this.depElement, "optional"));
      }

      private String emptyIsNull(String val) {
         if (val == null) {
            return null;
         } else {
            return val.equals("") ? null : val;
         }
      }
   }

   public class PomProfileElement {
      private static final String VALUE = "value";
      private static final String NAME = "name";
      private static final String PROPERTY = "property";
      private static final String ID_ELEMENT = "id";
      private static final String ACTIVATION_ELEMENT = "activation";
      private static final String ACTIVE_BY_DEFAULT_ELEMENT = "activeByDefault";
      private static final String OS = "os";
      private static final String FAMILY = "family";
      private static final String VERSION = "version";
      private static final String ARCH = "arch";
      private static final String FILE = "file";
      private static final String MISSING = "missing";
      private static final String EXISTS = "exists";
      private static final String JDK = "jdk";
      private final Element profileElement;

      PomProfileElement(Element profileElement) {
         this.profileElement = profileElement;
      }

      public String getId() {
         return PomReader.getFirstChildText(this.profileElement, "id");
      }

      public boolean isActive() {
         return this.isActiveByDefault() || this.isActivatedByProperty() || this.isActiveByOS() || this.isActiveByJDK() || this.isActiveByFile();
      }

      public boolean isActiveByDefault() {
         Element activation = PomReader.getFirstChildElement(this.profileElement, "activation");
         return Boolean.parseBoolean(PomReader.getFirstChildText(activation, "activeByDefault"));
      }

      public boolean isActiveByOS() {
         Element activation = PomReader.getFirstChildElement(this.profileElement, "activation");
         if (activation == null) {
            return false;
         } else {
            Element osActivation = PomReader.getFirstChildElement(activation, "os");
            if (osActivation == null) {
               return false;
            } else {
               String actualOS = System.getProperty("os.name");
               String expectedOSName = PomReader.getFirstChildText(osActivation, "name");
               if (expectedOSName != null && !actualOS.equals(expectedOSName.trim())) {
                  return false;
               } else {
                  String expectedOSFamily = PomReader.getFirstChildText(osActivation, "family");
                  if (expectedOSFamily != null && !actualOS.contains(expectedOSFamily.trim())) {
                     return false;
                  } else {
                     String expectedOSArch = PomReader.getFirstChildText(osActivation, "arch");
                     if (expectedOSArch != null && !System.getProperty("os.arch").equals(expectedOSArch.trim())) {
                        return false;
                     } else {
                        String expectedOSVersion = PomReader.getFirstChildText(osActivation, "version");
                        if (expectedOSVersion != null && !System.getProperty("os.version").equals(expectedOSVersion.trim())) {
                           return false;
                        } else {
                           return expectedOSName != null || expectedOSFamily != null || expectedOSArch != null || expectedOSVersion != null;
                        }
                     }
                  }
               }
            }
         }
      }

      public boolean isActiveByJDK() {
         Element activation = PomReader.getFirstChildElement(this.profileElement, "activation");
         if (activation == null) {
            return false;
         } else {
            String expectedJDKRange = PomReader.getFirstChildText(activation, "jdk");
            if (expectedJDKRange == null) {
               return false;
            } else {
               boolean negate = expectedJDKRange.trim().startsWith("!");
               String nonNegatedRange = negate ? expectedJDKRange.substring(1).trim() : expectedJDKRange.trim();
               boolean javaVersionInRange = MavenVersionRangeParser.currentJavaVersionInRange(nonNegatedRange);
               return javaVersionInRange ^ negate;
            }
         }
      }

      public boolean isActiveByFile() {
         Element activation = PomReader.getFirstChildElement(this.profileElement, "activation");
         if (activation == null) {
            return false;
         } else {
            Element fileActivation = PomReader.getFirstChildElement(activation, "file");
            if (fileActivation == null) {
               return false;
            } else {
               String expectedMissing = PomReader.getFirstChildText(fileActivation, "missing");
               if (expectedMissing != null && (new File(expectedMissing.trim())).exists()) {
                  return false;
               } else {
                  String expectedExists = PomReader.getFirstChildText(fileActivation, "exists");
                  if (expectedExists != null && !(new File(expectedExists.trim())).exists()) {
                     return false;
                  } else {
                     return expectedMissing != null || expectedExists != null;
                  }
               }
            }
         }
      }

      public boolean isActivatedByProperty() {
         Element activation = PomReader.getFirstChildElement(this.profileElement, "activation");
         Element propertyActivation = PomReader.getFirstChildElement(activation, "property");
         String propertyName = PomReader.getFirstChildText(propertyActivation, "name");
         if (propertyName != null && !"".equals(propertyName)) {
            boolean negate = propertyName.charAt(0) == '!';
            if (negate) {
               propertyName = propertyName.substring(1);
            }

            if ("".equals(propertyName)) {
               return false;
            } else {
               String propertyValue = PomReader.getFirstChildText(propertyActivation, "value");
               boolean matched;
               if (propertyValue != null && !"".equals(propertyValue)) {
                  matched = propertyValue.equals(PomReader.this.properties.get(propertyName));
               } else {
                  matched = PomReader.this.properties.containsKey(propertyName);
               }

               return matched ^ negate;
            }
         } else {
            return false;
         }
      }

      public List getDependencies() {
         return PomReader.this.getDependencies(this.profileElement);
      }

      public List getDependencyMgt() {
         return PomReader.this.getDependencyMgt(this.profileElement);
      }

      public List getPlugins() {
         return PomReader.this.getPlugins(this.profileElement);
      }

      public Map getProfileProperties() {
         return PomReader.getProperties(this.profileElement);
      }
   }

   private static final class AddDTDFilterInputStream extends FilterInputStream {
      private static final int MARK = 10000;
      private static final String DOCTYPE = "<!DOCTYPE project SYSTEM \"m2-entities.ent\">\n";
      private int count;
      private byte[] prefix;

      private AddDTDFilterInputStream(InputStream in) throws IOException {
         super(new BufferedInputStream(in));
         this.prefix = "<!DOCTYPE project SYSTEM \"m2-entities.ent\">\n".getBytes();
         this.in.mark(10000);
         int byte1 = this.in.read();
         int byte2 = this.in.read();
         int byte3 = this.in.read();
         if (byte1 == 239 && byte2 == 187 && byte3 == 191) {
            this.in.mark(10000);
         } else {
            this.in.reset();
         }

         int bytesToSkip = 0;
         LineNumberReader reader = new LineNumberReader(new InputStreamReader(this.in, StandardCharsets.UTF_8), 100);
         String firstLine = reader.readLine();
         if (firstLine != null) {
            String trimmed = firstLine.trim();
            if (trimmed.startsWith("<?xml ")) {
               int endIndex = trimmed.indexOf("?>");
               String xmlDecl = trimmed.substring(0, endIndex + 2);
               this.prefix = (xmlDecl + "\n" + "<!DOCTYPE project SYSTEM \"m2-entities.ent\">\n").getBytes();
               bytesToSkip = xmlDecl.getBytes().length;
            }
         } else {
            this.prefix = new byte[0];
         }

         this.in.reset();

         for(int i = 0; i < bytesToSkip; ++i) {
            this.in.read();
         }

      }

      public int read() throws IOException {
         return this.count < this.prefix.length ? this.prefix[this.count++] : super.read();
      }

      public int read(byte[] b, int off, int len) throws IOException {
         if (b == null) {
            throw new NullPointerException();
         } else if (off >= 0 && off <= b.length && len >= 0 && off + len <= b.length && off + len >= 0) {
            if (len == 0) {
               return 0;
            } else {
               int nbrBytesCopied = 0;
               if (this.count < this.prefix.length) {
                  int nbrBytesFromPrefix = Math.min(this.prefix.length - this.count, len);
                  System.arraycopy(this.prefix, this.count, b, off, nbrBytesFromPrefix);
                  nbrBytesCopied = nbrBytesFromPrefix;
               }

               if (nbrBytesCopied < len) {
                  nbrBytesCopied += this.in.read(b, off + nbrBytesCopied, len - nbrBytesCopied);
               }

               this.count += nbrBytesCopied;
               return nbrBytesCopied;
            }
         } else {
            throw new IndexOutOfBoundsException();
         }
      }
   }
}
