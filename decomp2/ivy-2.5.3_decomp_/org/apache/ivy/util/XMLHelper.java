package org.apache.ivy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import org.apache.ivy.util.url.URLHandlerRegistry;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public abstract class XMLHelper {
   static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
   static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
   static final String XERCES_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
   static final String XML_NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
   static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
   private static final String XML_ACCESS_EXTERNAL_SCHEMA = "http://javax.xml.XMLConstants/property/accessExternalSchema";
   private static final String XML_ACCESS_EXTERNAL_DTD = "http://javax.xml.XMLConstants/property/accessExternalDTD";
   public static final String ALLOW_DOCTYPE_PROCESSING = "ivy.xml.allow-doctype-processing";
   public static final String EXTERNAL_RESOURCES = "ivy.xml.external-resources";
   private static final InputSource EMPTY_INPUT_SOURCE = new InputSource(new StringReader(""));

   private static SAXParser newSAXParser(URL schema, InputStream schemaStream, boolean allowXmlDoctypeProcessing, ExternalResources externalResources) throws ParserConfigurationException, SAXException {
      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      parserFactory.setNamespaceAware(true);
      parserFactory.setValidating(schema != null);
      configureSafeFeatures(parserFactory, allowXmlDoctypeProcessing, externalResources);
      SAXParser parser = parserFactory.newSAXParser();
      if (schema != null) {
         try {
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", schemaStream);
         } catch (SAXNotRecognizedException ex) {
            Message.warn("problem while setting JAXP validating property on SAXParser... XML validation will not be done", ex);
            parserFactory.setValidating(false);
            parser = parserFactory.newSAXParser();
         }
      }

      XMLReader reader = parser.getXMLReader();
      trySetFeature(reader, "http://xml.org/sax/features/namespace-prefixes", true);
      trySetProperty(reader, "http://javax.xml.XMLConstants/property/accessExternalSchema", externalResources.getAllowedProtocols());
      trySetProperty(reader, "http://javax.xml.XMLConstants/property/accessExternalDTD", externalResources.getAllowedProtocols());
      return parser;
   }

   public static String toSystemId(URL url) {
      try {
         return (new URI(url.toExternalForm())).toASCIIString();
      } catch (URISyntaxException var2) {
         return url.toExternalForm();
      }
   }

   public static void parse(URL xmlURL, URL schema, DefaultHandler handler) throws SAXException, IOException, ParserConfigurationException {
      parse((URL)xmlURL, schema, handler, (LexicalHandler)null);
   }

   public static void parse(URL xmlURL, URL schema, DefaultHandler handler, LexicalHandler lHandler) throws SAXException, IOException, ParserConfigurationException {
      parse(xmlURL, schema, handler, lHandler, XMLHelper.ExternalResources.fromSystemProperty());
   }

   public static void parse(URL xmlURL, URL schema, DefaultHandler handler, LexicalHandler lHandler, ExternalResources externalResources) throws SAXException, IOException, ParserConfigurationException {
      InputStream xmlStream = URLHandlerRegistry.getDefault().openStream(xmlURL);
      Throwable var6 = null;

      try {
         InputSource inSrc = new InputSource(xmlStream);
         inSrc.setSystemId(toSystemId(xmlURL));
         parse(inSrc, schema, handler, lHandler, externalResources);
      } catch (Throwable var15) {
         var6 = var15;
         throw var15;
      } finally {
         if (xmlStream != null) {
            if (var6 != null) {
               try {
                  xmlStream.close();
               } catch (Throwable var14) {
                  var6.addSuppressed(var14);
               }
            } else {
               xmlStream.close();
            }
         }

      }

   }

   public static void parse(InputStream xmlStream, URL schema, DefaultHandler handler, LexicalHandler lHandler) throws SAXException, IOException, ParserConfigurationException {
      parse(xmlStream, schema, handler, lHandler, XMLHelper.ExternalResources.fromSystemProperty());
   }

   public static void parse(InputStream xmlStream, URL schema, DefaultHandler handler, LexicalHandler lHandler, ExternalResources externalResources) throws SAXException, IOException, ParserConfigurationException {
      parse(new InputSource(xmlStream), schema, handler, lHandler);
   }

   public static void parse(InputSource xmlStream, URL schema, DefaultHandler handler, LexicalHandler lHandler) throws SAXException, IOException, ParserConfigurationException {
      parse(xmlStream, schema, handler, lHandler, XMLHelper.ExternalResources.fromSystemProperty());
   }

   public static void parse(InputSource xmlStream, URL schema, DefaultHandler handler, LexicalHandler lHandler, boolean loadExternalDtds) throws SAXException, IOException, ParserConfigurationException {
      parse(xmlStream, schema, handler, lHandler, loadExternalDtds ? XMLHelper.ExternalResources.LOCAL_ONLY : XMLHelper.ExternalResources.PROHIBIT);
   }

   public static void parse(InputSource xmlStream, URL schema, DefaultHandler handler, LexicalHandler lHandler, ExternalResources externalResources) throws SAXException, IOException, ParserConfigurationException {
      InputStream schemaStream = null;

      try {
         if (schema != null) {
            schemaStream = URLHandlerRegistry.getDefault().openStream(schema);
         }

         SAXParser parser = newSAXParser(schema, schemaStream, isXmlDoctypeProcessingAllowed(), externalResources);
         if (lHandler != null) {
            try {
               parser.setProperty("http://xml.org/sax/properties/lexical-handler", lHandler);
            } catch (SAXException ex) {
               Message.warn("problem while setting the lexical handler property on SAXParser", ex);
            }
         }

         DefaultHandler h = (DefaultHandler)(externalResources == XMLHelper.ExternalResources.IGNORE ? new NoopEntityResolverDefaultHandler(handler) : handler);
         parser.parse(xmlStream, h);
      } finally {
         if (schemaStream != null) {
            try {
               schemaStream.close();
            } catch (IOException var14) {
            }
         }

      }

   }

   public static boolean canUseSchemaValidation() {
      return true;
   }

   public static String escape(String text) {
      if (text == null) {
         return null;
      } else {
         StringBuilder result = new StringBuilder(text.length());

         for(char ch : text.toCharArray()) {
            switch (ch) {
               case '"':
                  result.append("&quot;");
                  break;
               case '&':
                  result.append("&amp;");
                  break;
               case '\'':
                  result.append("&apos;");
                  break;
               case '<':
                  result.append("&lt;");
                  break;
               default:
                  result.append(ch);
            }
         }

         return result.toString();
      }
   }

   public static Document parseToDom(InputSource source, EntityResolver entityResolver) throws IOException, SAXException {
      return parseToDom(source, entityResolver, isXmlDoctypeProcessingAllowed(), XMLHelper.ExternalResources.fromSystemProperty());
   }

   public static Document parseToDom(InputSource source, EntityResolver entityResolver, boolean allowXmlDoctypeProcessing, ExternalResources externalResources) throws IOException, SAXException {
      DocumentBuilder docBuilder = getDocBuilder(entityResolver, allowXmlDoctypeProcessing, externalResources);
      return docBuilder.parse(source);
   }

   public static DocumentBuilder getDocBuilder(EntityResolver entityResolver) {
      return getDocBuilder(entityResolver, isXmlDoctypeProcessingAllowed(), XMLHelper.ExternalResources.fromSystemProperty());
   }

   public static DocumentBuilder getDocBuilder(EntityResolver entityResolver, boolean allowXmlDoctypeProcessing, ExternalResources externalResources) {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setValidating(false);
         configureSafeFeatures(factory, allowXmlDoctypeProcessing, externalResources);
         DocumentBuilder docBuilder = factory.newDocumentBuilder();
         if (externalResources == XMLHelper.ExternalResources.IGNORE) {
            entityResolver = new NoopEntityResolver(entityResolver);
         }

         if (entityResolver != null) {
            docBuilder.setEntityResolver(entityResolver);
         }

         return docBuilder;
      } catch (ParserConfigurationException e) {
         throw new RuntimeException(e);
      }
   }

   public static Transformer getTransformer(Source source) throws TransformerConfigurationException {
      TransformerFactory factory = getTransformerFactory();
      return factory.newTransformer(source);
   }

   public static TransformerHandler getTransformerHandler() throws TransformerConfigurationException {
      SAXTransformerFactory factory = getTransformerFactory();
      return factory.newTransformerHandler();
   }

   public static boolean isXmlDoctypeProcessingAllowed() {
      return "true".equals(System.getProperty("ivy.xml.allow-doctype-processing"));
   }

   private XMLHelper() {
   }

   private static SAXTransformerFactory getTransformerFactory() {
      TransformerFactory factory = SAXTransformerFactory.newInstance();
      configureSafeFeatures(factory);
      return (SAXTransformerFactory)factory;
   }

   private static void configureSafeFeatures(DocumentBuilderFactory factory, boolean allowXmlDoctypeProcessing, ExternalResources externalResources) {
      String DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
      trySetFeature(factory, "http://apache.org/xml/features/disallow-doctype-decl", !allowXmlDoctypeProcessing);
      String FEATURE_SECURE_PROCESSING = "http://javax.xml.XMLConstants/feature/secure-processing";
      trySetFeature(factory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
      String ALLOW_EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
      trySetFeature(factory, "http://xml.org/sax/features/external-general-entities", false);
      String ALLOW_EXTERNAL_PARAM_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
      trySetFeature(factory, "http://xml.org/sax/features/external-parameter-entities", false);
      String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
      trySetFeature(factory, "http://apache.org/xml/features/nonvalidating/load-external-dtd", externalResources != XMLHelper.ExternalResources.PROHIBIT);

      try {
         factory.setXIncludeAware(false);
      } catch (Exception var10) {
      }

      try {
         factory.setExpandEntityReferences(false);
      } catch (Exception var9) {
      }

   }

   private static void configureSafeFeatures(SAXParserFactory factory, boolean allowXmlDoctypeProcessing, ExternalResources externalResources) {
      String DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
      trySetFeature(factory, "http://apache.org/xml/features/disallow-doctype-decl", !allowXmlDoctypeProcessing);
      String FEATURE_SECURE_PROCESSING = "http://javax.xml.XMLConstants/feature/secure-processing";
      trySetFeature(factory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
      boolean allowEntities = externalResources == XMLHelper.ExternalResources.LOCAL_ONLY || externalResources == XMLHelper.ExternalResources.ALL;
      String ALLOW_EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
      trySetFeature(factory, "http://xml.org/sax/features/external-general-entities", allowEntities);
      String ALLOW_EXTERNAL_PARAM_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
      trySetFeature(factory, "http://xml.org/sax/features/external-parameter-entities", allowEntities);
      String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
      trySetFeature(factory, "http://apache.org/xml/features/nonvalidating/load-external-dtd", externalResources != XMLHelper.ExternalResources.PROHIBIT);

      try {
         factory.setXIncludeAware(false);
      } catch (Exception var10) {
      }

   }

   private static void configureSafeFeatures(TransformerFactory factory) {
      trySetAttribute(factory, "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
      trySetAttribute(factory, "http://javax.xml.XMLConstants/property/accessExternalSchema", "");
      trySetAttribute(factory, "http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
   }

   private static boolean isFeatureSupported(SAXParserFactory factory, String feature) {
      try {
         factory.getFeature(feature);
         return true;
      } catch (ParserConfigurationException var3) {
         return false;
      } catch (SAXNotRecognizedException var4) {
         return false;
      } catch (SAXNotSupportedException var5) {
         return false;
      }
   }

   private static boolean isFeatureSupported(DocumentBuilderFactory factory, String feature) {
      try {
         factory.getFeature(feature);
         return true;
      } catch (ParserConfigurationException var3) {
         return false;
      }
   }

   private static boolean isFeatureSupported(XMLReader reader, String feature) {
      try {
         reader.getFeature(feature);
         return true;
      } catch (SAXException var3) {
         return false;
      }
   }

   private static boolean isAttributeSupported(TransformerFactory factory, String attribute) {
      try {
         factory.getAttribute(attribute);
         return true;
      } catch (IllegalArgumentException var3) {
         return false;
      }
   }

   private static boolean isPropertySupported(XMLReader reader, String property) {
      try {
         reader.getProperty(property);
         return true;
      } catch (SAXException var3) {
         return false;
      }
   }

   private static boolean trySetFeature(DocumentBuilderFactory factory, String feature, boolean val) {
      if (!isFeatureSupported(factory, feature)) {
         return false;
      } else {
         try {
            factory.setFeature(feature, val);
            return true;
         } catch (ParserConfigurationException e) {
            Message.warn("Failed to set feature " + feature + " on DocumentBuilderFactory", e);
            return false;
         }
      }
   }

   private static boolean trySetFeature(SAXParserFactory factory, String feature, boolean val) {
      if (!isFeatureSupported(factory, feature)) {
         return false;
      } else {
         try {
            factory.setFeature(feature, val);
            return true;
         } catch (ParserConfigurationException e) {
            Message.warn("Failed to set feature " + feature + " on SAXParserFactory", e);
            return false;
         } catch (SAXNotRecognizedException e) {
            Message.warn("Failed to set feature " + feature + " on SAXParserFactory", e);
            return false;
         } catch (SAXNotSupportedException e) {
            Message.warn("Failed to set feature " + feature + " on SAXParserFactory", e);
            return false;
         }
      }
   }

   private static boolean trySetFeature(XMLReader reader, String feature, boolean val) {
      if (!isFeatureSupported(reader, feature)) {
         return false;
      } else {
         try {
            reader.setFeature(feature, val);
            return true;
         } catch (SAXException e) {
            Message.warn("Failed to set feature " + feature + " on XMLReader", e);
            return false;
         }
      }
   }

   private static boolean trySetAttribute(TransformerFactory factory, String attribute, String val) {
      if (!isAttributeSupported(factory, attribute)) {
         return false;
      } else {
         try {
            factory.setAttribute(attribute, val);
            return true;
         } catch (IllegalArgumentException e) {
            Message.warn("Failed to set attribute " + attribute + " on TransformerFactory", e);
            return false;
         }
      }
   }

   private static boolean trySetProperty(XMLReader reader, String property, Object val) {
      if (!isPropertySupported(reader, property)) {
         return false;
      } else {
         try {
            reader.setProperty(property, val);
            return true;
         } catch (SAXException e) {
            Message.warn("Failed to set property " + property + " on XMLReader", e);
            return false;
         }
      }
   }

   public static enum ExternalResources {
      PROHIBIT(""),
      IGNORE("all"),
      LOCAL_ONLY("file, jar:file"),
      ALL("all");

      private final String allowedProtocols;

      private ExternalResources(String allowedProtocols) {
         this.allowedProtocols = allowedProtocols;
      }

      private String getAllowedProtocols() {
         return this.allowedProtocols;
      }

      public static ExternalResources fromSystemProperty() {
         String val = System.getProperty("ivy.xml.external-resources");
         if (val != null) {
            if (val.equalsIgnoreCase("ignore")) {
               return IGNORE;
            }

            if (val.equalsIgnoreCase("all")) {
               return ALL;
            }

            if (val.equalsIgnoreCase("local-only") || val.equalsIgnoreCase("local_only")) {
               return LOCAL_ONLY;
            }
         }

         return PROHIBIT;
      }
   }

   private static class NoopEntityResolver implements EntityResolver {
      private EntityResolver wrapped;

      private NoopEntityResolver(EntityResolver wrapped) {
         this.wrapped = wrapped;
      }

      public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
         if (this.wrapped != null) {
            InputSource s = this.wrapped.resolveEntity(publicId, systemId);
            if (s != null) {
               return s;
            }
         }

         return XMLHelper.EMPTY_INPUT_SOURCE;
      }
   }

   private static class NoopEntityResolverDefaultHandler extends DefaultHandler {
      private DefaultHandler wrapped;

      private NoopEntityResolverDefaultHandler(DefaultHandler wrapped) {
         this.wrapped = wrapped;
      }

      public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
         if (this.wrapped != null) {
            InputSource s = this.wrapped.resolveEntity(publicId, systemId);
            if (s != null) {
               return s;
            }
         }

         return XMLHelper.EMPTY_INPUT_SOURCE;
      }

      public void notationDecl(String name, String publicId, String systemId) throws SAXException {
         this.wrapped.notationDecl(name, publicId, systemId);
      }

      public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
         this.wrapped.unparsedEntityDecl(name, publicId, systemId, notationName);
      }

      public void setDocumentLocator(Locator locator) {
         this.wrapped.setDocumentLocator(locator);
      }

      public void startDocument() throws SAXException {
         this.wrapped.startDocument();
      }

      public void endDocument() throws SAXException {
         this.wrapped.endDocument();
      }

      public void startPrefixMapping(String prefix, String uri) throws SAXException {
         this.wrapped.startPrefixMapping(prefix, uri);
      }

      public void endPrefixMapping(String prefix) throws SAXException {
         this.wrapped.endPrefixMapping(prefix);
      }

      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
         this.wrapped.startElement(uri, localName, qName, attributes);
      }

      public void endElement(String uri, String localName, String qName) throws SAXException {
         this.wrapped.endElement(uri, localName, qName);
      }

      public void characters(char[] ch, int start, int length) throws SAXException {
         this.wrapped.characters(ch, start, length);
      }

      public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
         this.wrapped.ignorableWhitespace(ch, start, length);
      }

      public void processingInstruction(String target, String data) throws SAXException {
         this.wrapped.processingInstruction(target, data);
      }

      public void skippedEntity(String name) throws SAXException {
         this.wrapped.skippedEntity(name);
      }

      public void warning(SAXParseException e) throws SAXException {
         this.wrapped.warning(e);
      }

      public void error(SAXParseException e) throws SAXException {
         this.wrapped.error(e);
      }

      public void fatalError(SAXParseException e) throws SAXException {
         this.wrapped.fatalError(e);
      }
   }
}
