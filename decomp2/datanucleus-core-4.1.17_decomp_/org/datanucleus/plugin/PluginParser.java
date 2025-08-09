package org.datanucleus.plugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.jar.Manifest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

class PluginParser {
   static DocumentBuilderFactory dbFactory = null;

   public static Bundle parseManifest(Manifest mf, URL fileUrl) {
      Bundle bundle = null;

      try {
         String symbolicName = getBundleSymbolicName(mf, (String)null);
         String bundleVersion = getBundleVersion(mf, (String)null);
         String bundleName = getBundleName(mf, (String)null);
         String bundleVendor = getBundleVendor(mf, (String)null);
         bundle = new Bundle(symbolicName, bundleName, bundleVendor, bundleVersion, fileUrl);
         bundle.setRequireBundle(getRequireBundle(mf));
         return bundle;
      } catch (NucleusException ne) {
         NucleusLogger.GENERAL.warn("Plugin at URL=" + fileUrl + " failed to parse so is being ignored", ne);
         return null;
      }
   }

   private static List getRequireBundle(Manifest mf) {
      String str = mf.getMainAttributes().getValue("Require-Bundle");
      if (str != null && str.length() >= 1) {
         Parser p = new Parser(str);
         List<Bundle.BundleDescription> requiredBundle = new ArrayList();
         String bundleSymbolicName = p.parseSymbolicName();

         while(bundleSymbolicName != null) {
            Bundle.BundleDescription bd = new Bundle.BundleDescription();
            bd.setBundleSymbolicName(bundleSymbolicName);
            bd.setParameters(p.parseParameters());
            bundleSymbolicName = p.parseSymbolicName();
            requiredBundle.add(bd);
         }

         return requiredBundle;
      } else {
         return Collections.EMPTY_LIST;
      }
   }

   private static List parseExtensionPoints(Element rootElement, Bundle plugin, ClassLoaderResolver clr) {
      List<ExtensionPoint> extensionPoints = new ArrayList();

      try {
         NodeList elements = rootElement.getElementsByTagName("extension-point");

         for(int i = 0; i < elements.getLength(); ++i) {
            Element element = (Element)elements.item(i);
            String id = element.getAttribute("id").trim();
            String name = element.getAttribute("name");
            String schema = element.getAttribute("schema");
            extensionPoints.add(new ExtensionPoint(id, name, clr.getResource(schema, (ClassLoader)null), plugin));
         }

         return extensionPoints;
      } catch (NucleusException ex) {
         throw ex;
      }
   }

   private static List parseExtensions(Element rootElement, Bundle plugin, ClassLoaderResolver clr) {
      List<Extension> extensions = new ArrayList();

      try {
         NodeList elements = rootElement.getElementsByTagName("extension");

         for(int i = 0; i < elements.getLength(); ++i) {
            Element element = (Element)elements.item(i);
            Extension ex = new Extension(element.getAttribute("point"), plugin);
            NodeList elms = element.getChildNodes();
            extensions.add(ex);

            for(int e = 0; e < elms.getLength(); ++e) {
               if (elms.item(e) instanceof Element) {
                  ex.addConfigurationElement(parseConfigurationElement(ex, (Element)elms.item(e), (ConfigurationElement)null));
               }
            }
         }

         return extensions;
      } catch (NucleusException ex) {
         throw ex;
      }
   }

   private static String getBundleSymbolicName(Manifest mf, String defaultValue) {
      if (mf == null) {
         return defaultValue;
      } else {
         String name = mf.getMainAttributes().getValue("Bundle-SymbolicName");
         if (name == null) {
            return defaultValue;
         } else {
            StringTokenizer token = new StringTokenizer(name, ";");
            return token.nextToken().trim();
         }
      }
   }

   private static String getBundleName(Manifest mf, String defaultValue) {
      if (mf == null) {
         return defaultValue;
      } else {
         String name = mf.getMainAttributes().getValue("Bundle-Name");
         return name == null ? defaultValue : name;
      }
   }

   private static String getBundleVendor(Manifest mf, String defaultValue) {
      if (mf == null) {
         return defaultValue;
      } else {
         String vendor = mf.getMainAttributes().getValue("Bundle-Vendor");
         return vendor == null ? defaultValue : vendor;
      }
   }

   private static String getBundleVersion(Manifest mf, String defaultValue) {
      if (mf == null) {
         return defaultValue;
      } else {
         String version = mf.getMainAttributes().getValue("Bundle-Version");
         return version == null ? defaultValue : version;
      }
   }

   public static List[] parsePluginElements(DocumentBuilder db, PluginRegistry mgr, URL fileUrl, Bundle plugin, ClassLoaderResolver clr) {
      List extensionPoints = Collections.EMPTY_LIST;
      List extensions = Collections.EMPTY_LIST;
      InputStream is = null;

      try {
         is = fileUrl.openStream();
         Element rootElement = db.parse(new InputSource(new InputStreamReader(is))).getDocumentElement();
         if (NucleusLogger.GENERAL.isDebugEnabled()) {
            NucleusLogger.GENERAL.debug(Localiser.msg("024003", fileUrl.toString()));
         }

         extensionPoints = parseExtensionPoints(rootElement, plugin, clr);
         if (NucleusLogger.GENERAL.isDebugEnabled()) {
            NucleusLogger.GENERAL.debug(Localiser.msg("024004", fileUrl.toString()));
         }

         extensions = parseExtensions(rootElement, plugin, clr);
      } catch (NucleusException ex) {
         throw ex;
      } catch (Exception var19) {
         NucleusLogger.GENERAL.error(Localiser.msg("024000", fileUrl.getFile()));
      } finally {
         if (is != null) {
            try {
               is.close();
            } catch (Exception var17) {
            }
         }

      }

      return new List[]{extensionPoints, extensions};
   }

   public static DocumentBuilder getDocumentBuilder() {
      try {
         if (dbFactory == null) {
            dbFactory = DocumentBuilderFactory.newInstance();
         }

         return dbFactory.newDocumentBuilder();
      } catch (ParserConfigurationException e1) {
         throw new NucleusException(Localiser.msg("024016", e1.getMessage()));
      }
   }

   public static ConfigurationElement parseConfigurationElement(Extension ex, Element element, ConfigurationElement parent) {
      ConfigurationElement confElm = new ConfigurationElement(ex, element.getNodeName(), parent);
      NamedNodeMap attributes = element.getAttributes();

      for(int i = 0; i < attributes.getLength(); ++i) {
         Node attribute = attributes.item(i);
         confElm.putAttribute(attribute.getNodeName(), attribute.getNodeValue());
      }

      NodeList elements = element.getChildNodes();

      for(int i = 0; i < elements.getLength(); ++i) {
         if (elements.item(i) instanceof Element) {
            Element elm = (Element)elements.item(i);
            ConfigurationElement child = parseConfigurationElement(ex, elm, confElm);
            confElm.addConfigurationElement(child);
         } else if (elements.item(i) instanceof Text) {
            confElm.setText(elements.item(i).getNodeValue());
         }
      }

      return confElm;
   }

   public static Bundle.BundleVersionRange parseVersionRange(String interval) {
      Parser p = new Parser(interval);
      Bundle.BundleVersionRange versionRange = new Bundle.BundleVersionRange();
      if (p.parseChar('[')) {
         versionRange.floor_inclusive = true;
      } else if (p.parseChar('(')) {
         versionRange.floor_inclusive = false;
      }

      versionRange.floor = new Bundle.BundleVersion();
      versionRange.floor.major = p.parseIntegerLiteral().intValue();
      if (p.parseChar('.')) {
         versionRange.floor.minor = p.parseIntegerLiteral().intValue();
      }

      if (p.parseChar('.')) {
         versionRange.floor.micro = p.parseIntegerLiteral().intValue();
      }

      if (p.parseChar('.')) {
         versionRange.floor.qualifier = p.parseIdentifier();
      }

      if (p.parseChar(',')) {
         versionRange.ceiling = new Bundle.BundleVersion();
         versionRange.ceiling.major = p.parseIntegerLiteral().intValue();
         if (p.parseChar('.')) {
            versionRange.ceiling.minor = p.parseIntegerLiteral().intValue();
         }

         if (p.parseChar('.')) {
            versionRange.ceiling.micro = p.parseIntegerLiteral().intValue();
         }

         if (p.parseChar('.')) {
            versionRange.ceiling.qualifier = p.parseIdentifier();
         }

         if (p.parseChar(']')) {
            versionRange.ceiling_inclusive = true;
         } else if (p.parseChar(')')) {
            versionRange.ceiling_inclusive = false;
         }
      }

      return versionRange;
   }

   public static class Parser {
      private final String input;
      protected final CharacterIterator ci;

      public Parser(String input) {
         this.input = input;
         this.ci = new StringCharacterIterator(input);
      }

      public String getInput() {
         return this.input;
      }

      public int getIndex() {
         return this.ci.getIndex();
      }

      public int skipWS() {
         int startIdx = this.ci.getIndex();

         for(char c = this.ci.current(); Character.isWhitespace(c) || c == '\t' || c == '\f' || c == '\n' || c == '\r' || c == '\t' || c == '\f' || c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' '; c = this.ci.next()) {
         }

         return startIdx;
      }

      public boolean parseEOS() {
         this.skipWS();
         return this.ci.current() == '\uffff';
      }

      public boolean parseChar(char c) {
         this.skipWS();
         if (this.ci.current() == c) {
            this.ci.next();
            return true;
         } else {
            return false;
         }
      }

      public boolean parseChar(char c, char unlessFollowedBy) {
         int savedIdx = this.skipWS();
         if (this.ci.current() == c && this.ci.next() != unlessFollowedBy) {
            return true;
         } else {
            this.ci.setIndex(savedIdx);
            return false;
         }
      }

      public BigInteger parseIntegerLiteral() {
         int savedIdx = this.skipWS();
         StringBuilder digits = new StringBuilder();
         char c = this.ci.current();
         int radix;
         if (c == '0') {
            c = this.ci.next();
            if (c != 'x' && c != 'X') {
               if (this.isOctDigit(c)) {
                  radix = 8;

                  do {
                     digits.append(c);
                     c = this.ci.next();
                  } while(this.isOctDigit(c));
               } else {
                  radix = 10;
                  digits.append('0');
               }
            } else {
               radix = 16;

               for(c = this.ci.next(); this.isHexDigit(c); c = this.ci.next()) {
                  digits.append(c);
               }
            }
         } else {
            for(radix = 10; this.isDecDigit(c); c = this.ci.next()) {
               digits.append(c);
            }
         }

         if (digits.length() == 0) {
            this.ci.setIndex(savedIdx);
            return null;
         } else {
            if (c == 'l' || c == 'L') {
               this.ci.next();
            }

            return new BigInteger(digits.toString(), radix);
         }
      }

      public boolean parseString(String s) {
         int savedIdx = this.skipWS();
         int len = s.length();
         char c = this.ci.current();

         for(int i = 0; i < len; ++i) {
            if (c != s.charAt(i)) {
               this.ci.setIndex(savedIdx);
               return false;
            }

            c = this.ci.next();
         }

         return true;
      }

      public boolean parseStringIgnoreCase(String s) {
         String lowerCasedString = s.toLowerCase();
         int savedIdx = this.skipWS();
         int len = lowerCasedString.length();
         char c = this.ci.current();

         for(int i = 0; i < len; ++i) {
            if (Character.toLowerCase(c) != lowerCasedString.charAt(i)) {
               this.ci.setIndex(savedIdx);
               return false;
            }

            c = this.ci.next();
         }

         return true;
      }

      public String parseIdentifier() {
         this.skipWS();
         char c = this.ci.current();
         if (!Character.isJavaIdentifierStart(c)) {
            return null;
         } else {
            StringBuilder id = new StringBuilder();
            id.append(c);

            while(Character.isJavaIdentifierPart(c = this.ci.next()) || c == '-') {
               id.append(c);
            }

            return id.toString();
         }
      }

      public String parseInterval() {
         this.skipWS();
         char c = this.ci.current();

         StringBuilder id;
         for(id = new StringBuilder(); c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '.' || c == '_' || c == '-' || c == '[' || c == ']' || c == '(' || c == ')'; c = this.ci.next()) {
            id.append(c);
         }

         return id.toString();
      }

      public String parseName() {
         int savedIdx = this.skipWS();
         String id;
         if ((id = this.parseIdentifier()) == null) {
            return null;
         } else {
            StringBuilder qn = new StringBuilder(id);

            while(this.parseChar('.')) {
               if ((id = this.parseIdentifier()) == null) {
                  this.ci.setIndex(savedIdx);
                  return null;
               }

               qn.append('.').append(id);
            }

            return qn.toString();
         }
      }

      private final boolean isDecDigit(char c) {
         return c >= '0' && c <= '9';
      }

      private final boolean isOctDigit(char c) {
         return c >= '0' && c <= '7';
      }

      private final boolean isHexDigit(char c) {
         return c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F';
      }

      public boolean nextIsSingleQuote() {
         this.skipWS();
         return this.ci.current() == '\'';
      }

      public boolean nextIsDot() {
         return this.ci.current() == '.';
      }

      public boolean nextIsComma() {
         return this.ci.current() == ',';
      }

      public boolean nextIsSemiColon() {
         return this.ci.current() == ';';
      }

      public String parseStringLiteral() {
         this.skipWS();
         char quote = this.ci.current();
         if (quote != '"' && quote != '\'') {
            return null;
         } else {
            StringBuilder lit;
            char c;
            for(lit = new StringBuilder(); (c = this.ci.next()) != quote; lit.append(c)) {
               if (c == '\uffff') {
                  throw new NucleusUserException("Invalid string literal: " + this.input);
               }

               if (c == '\\') {
                  c = this.parseEscapedCharacter();
               }
            }

            this.ci.next();
            return lit.toString();
         }
      }

      private char parseEscapedCharacter() {
         char c;
         if (this.isOctDigit(c = this.ci.next())) {
            int i = c - 48;
            if (this.isOctDigit(c = this.ci.next())) {
               i = i * 8 + (c - 48);
               if (this.isOctDigit(c = this.ci.next())) {
                  i = i * 8 + (c - 48);
               } else {
                  this.ci.previous();
               }
            } else {
               this.ci.previous();
            }

            if (i > 255) {
               throw new NucleusUserException("Invalid character escape: '\\" + Integer.toOctalString(i) + "'");
            } else {
               return (char)i;
            }
         } else {
            switch (c) {
               case '"':
                  return '"';
               case '\'':
                  return '\'';
               case '\\':
                  return '\\';
               case 'b':
                  return '\b';
               case 'f':
                  return '\f';
               case 'n':
                  return '\n';
               case 'r':
                  return '\r';
               case 't':
                  return '\t';
               default:
                  throw new NucleusUserException("Invalid character escape: '\\" + c + "'");
            }
         }
      }

      public String remaining() {
         StringBuilder sb = new StringBuilder();

         for(char c = this.ci.current(); c != '\uffff'; c = this.ci.next()) {
            sb.append(c);
         }

         return sb.toString();
      }

      public String toString() {
         return this.input;
      }

      public Map parseParameters() {
         this.skipWS();
         Map paramaters = new HashMap();

         while(this.nextIsSemiColon()) {
            this.parseChar(';');
            this.skipWS();
            String name = this.parseName();
            this.skipWS();
            if (!this.parseString(":=") && !this.parseString("=")) {
               throw new NucleusUserException("Expected := or = symbols but found \"" + this.remaining() + "\" at position " + this.getIndex() + " of text \"" + this.input + "\"");
            }

            String argument = this.parseStringLiteral();
            if (argument == null) {
               argument = this.parseIdentifier();
            }

            if (argument == null) {
               argument = this.parseInterval();
            }

            paramaters.put(name, argument);
            this.skipWS();
         }

         return paramaters;
      }

      public String parseSymbolicName() {
         if (this.nextIsComma()) {
            this.parseChar(',');
         }

         String name = this.parseName();
         if (name == null && !this.parseEOS()) {
            throw new NucleusUserException("Invalid characters found \"" + this.remaining() + "\" at position " + this.getIndex() + " of text \"" + this.input + "\"");
         } else {
            return name;
         }
      }
   }
}
