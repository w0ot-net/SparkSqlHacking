package org.glassfish.jaxb.core.v2.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.glassfish.jaxb.core.v2.Messages;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class XmlFactory {
   private static final Logger LOGGER = Logger.getLogger(XmlFactory.class.getName());
   private static final String DISABLE_XML_SECURITY = "org.glassfish.jaxb.disableXmlSecurity";
   private static final boolean XML_SECURITY_DISABLED = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
      public Boolean run() {
         return Boolean.getBoolean("org.glassfish.jaxb.disableXmlSecurity");
      }
   });

   private XmlFactory() {
   }

   private static boolean isXMLSecurityDisabled(boolean runtimeSetting) {
      return XML_SECURITY_DISABLED || runtimeSetting;
   }

   public static SchemaFactory createSchemaFactory(String language, boolean disableSecureProcessing) throws IllegalStateException {
      try {
         SchemaFactory factory = SchemaFactory.newInstance(language);
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "SchemaFactory instance: {0}", factory);
         }

         factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(disableSecureProcessing));
         return factory;
      } catch (SAXNotSupportedException | SAXNotRecognizedException ex) {
         LOGGER.log(Level.SEVERE, (String)null, ex);
         throw new IllegalStateException(ex);
      } catch (AbstractMethodError er) {
         LOGGER.log(Level.SEVERE, (String)null, er);
         throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(), er);
      }
   }

   public static SAXParserFactory createParserFactory(boolean disableSecureProcessing) throws IllegalStateException {
      try {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "SAXParserFactory instance: {0}", factory);
         }

         factory.setNamespaceAware(true);
         factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(disableSecureProcessing));
         return factory;
      } catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException ex) {
         LOGGER.log(Level.SEVERE, (String)null, ex);
         throw new IllegalStateException(ex);
      } catch (AbstractMethodError er) {
         LOGGER.log(Level.SEVERE, (String)null, er);
         throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(), er);
      }
   }

   public static XPathFactory createXPathFactory(boolean disableSecureProcessing) throws IllegalStateException {
      try {
         XPathFactory factory = XPathFactory.newInstance();
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "XPathFactory instance: {0}", factory);
         }

         factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(disableSecureProcessing));
         return factory;
      } catch (XPathFactoryConfigurationException ex) {
         LOGGER.log(Level.SEVERE, (String)null, ex);
         throw new IllegalStateException(ex);
      } catch (AbstractMethodError er) {
         LOGGER.log(Level.SEVERE, (String)null, er);
         throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(), er);
      }
   }

   public static TransformerFactory createTransformerFactory(boolean disableSecureProcessing) throws IllegalStateException {
      try {
         TransformerFactory factory = TransformerFactory.newInstance();
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "TransformerFactory instance: {0}", factory);
         }

         factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(disableSecureProcessing));
         return factory;
      } catch (TransformerConfigurationException ex) {
         LOGGER.log(Level.SEVERE, (String)null, ex);
         throw new IllegalStateException(ex);
      } catch (AbstractMethodError er) {
         LOGGER.log(Level.SEVERE, (String)null, er);
         throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(), er);
      }
   }

   public static DocumentBuilderFactory createDocumentBuilderFactory(boolean disableSecureProcessing) throws IllegalStateException {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "DocumentBuilderFactory instance: {0}", factory);
         }

         factory.setNamespaceAware(true);
         factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", !isXMLSecurityDisabled(disableSecureProcessing));
         return factory;
      } catch (ParserConfigurationException ex) {
         LOGGER.log(Level.SEVERE, (String)null, ex);
         throw new IllegalStateException(ex);
      } catch (AbstractMethodError er) {
         LOGGER.log(Level.SEVERE, (String)null, er);
         throw new IllegalStateException(Messages.INVALID_JAXP_IMPLEMENTATION.format(), er);
      }
   }

   public static SchemaFactory allowExternalAccess(SchemaFactory sf, String value, boolean disableSecureProcessing) {
      if (isXMLSecurityDisabled(disableSecureProcessing)) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, Messages.JAXP_XML_SECURITY_DISABLED.format());
         }

         return sf;
      } else if (System.getProperty("javax.xml.accessExternalSchema") != null) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, Messages.JAXP_EXTERNAL_ACCESS_CONFIGURED.format());
         }

         return sf;
      } else {
         try {
            sf.setProperty("http://javax.xml.XMLConstants/property/accessExternalSchema", value);
            if (LOGGER.isLoggable(Level.FINE)) {
               LOGGER.log(Level.FINE, Messages.JAXP_SUPPORTED_PROPERTY.format("http://javax.xml.XMLConstants/property/accessExternalSchema"));
            }
         } catch (SAXException se) {
            if (LOGGER.isLoggable(Level.CONFIG)) {
               LOGGER.log(Level.CONFIG, Messages.JAXP_UNSUPPORTED_PROPERTY.format("http://javax.xml.XMLConstants/property/accessExternalSchema"), se);
            }
         }

         return sf;
      }
   }

   public static SchemaFactory allowExternalDTDAccess(SchemaFactory sf, String value, boolean disableSecureProcessing) {
      if (isXMLSecurityDisabled(disableSecureProcessing)) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, Messages.JAXP_XML_SECURITY_DISABLED.format());
         }

         return sf;
      } else if (System.getProperty("javax.xml.accessExternalDTD") != null) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, Messages.JAXP_EXTERNAL_ACCESS_CONFIGURED.format());
         }

         return sf;
      } else {
         try {
            sf.setProperty("http://javax.xml.XMLConstants/property/accessExternalDTD", value);
            if (LOGGER.isLoggable(Level.FINE)) {
               LOGGER.log(Level.FINE, Messages.JAXP_SUPPORTED_PROPERTY.format("http://javax.xml.XMLConstants/property/accessExternalDTD"));
            }
         } catch (SAXException se) {
            if (LOGGER.isLoggable(Level.CONFIG)) {
               LOGGER.log(Level.CONFIG, Messages.JAXP_UNSUPPORTED_PROPERTY.format("http://javax.xml.XMLConstants/property/accessExternalDTD"), se);
            }
         }

         return sf;
      }
   }
}
