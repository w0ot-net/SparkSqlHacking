package org.datanucleus.plugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class OSGiBundleParser {
   static DocumentBuilderFactory dbFactory = null;

   public static Bundle parseManifest(org.osgi.framework.Bundle osgiBundle) {
      Dictionary<String, String> headers = osgiBundle.getHeaders();
      Bundle bundle = null;

      try {
         String symbolicName = getBundleSymbolicName(headers, (String)null);
         String bundleVersion = getBundleVersion(headers, (String)null);
         String bundleName = getBundleName(headers, (String)null);
         String bundleVendor = getBundleVendor(headers, (String)null);
         bundle = new Bundle(symbolicName, bundleName, bundleVendor, bundleVersion, (URL)null);
         bundle.setRequireBundle(getRequireBundle(headers));
         return bundle;
      } catch (NucleusException ne) {
         NucleusLogger.GENERAL.warn("Plugin at bundle " + osgiBundle.getSymbolicName() + " (" + osgiBundle.getBundleId() + ") failed to parse so is being ignored", ne);
         return null;
      }
   }

   private static List getRequireBundle(Dictionary headers) {
      String str = (String)headers.get("Require-Bundle");
      if (str != null && str.length() >= 1) {
         PluginParser.Parser p = new PluginParser.Parser(str);
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
         return Collections.emptyList();
      }
   }

   private static List parseExtensionPoints(Element rootElement, Bundle plugin, org.osgi.framework.Bundle osgiBundle) {
      List<ExtensionPoint> extensionPoints = new ArrayList();

      try {
         NodeList elements = rootElement.getElementsByTagName("extension-point");

         for(int i = 0; i < elements.getLength(); ++i) {
            Element element = (Element)elements.item(i);
            String id = element.getAttribute("id").trim();
            String name = element.getAttribute("name");
            String schema = element.getAttribute("schema");
            extensionPoints.add(new ExtensionPoint(id, name, osgiBundle.getEntry(schema), plugin));
         }

         return extensionPoints;
      } catch (NucleusException ex) {
         throw ex;
      }
   }

   private static List parseExtensions(Element rootElement, Bundle plugin, org.osgi.framework.Bundle osgiBundle) {
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
                  ex.addConfigurationElement(PluginParser.parseConfigurationElement(ex, (Element)elms.item(e), (ConfigurationElement)null));
               }
            }
         }

         return extensions;
      } catch (NucleusException ex) {
         throw ex;
      }
   }

   private static String getHeaderValue(Dictionary headers, String key, String defaultValue) {
      if (headers == null) {
         return defaultValue;
      } else {
         String name = (String)headers.get(key);
         return name == null ? defaultValue : name;
      }
   }

   private static String getBundleSymbolicName(Dictionary headers, String defaultValue) {
      String name = getHeaderValue(headers, "Bundle-SymbolicName", defaultValue);
      StringTokenizer token = new StringTokenizer(name, ";");
      return token.nextToken().trim();
   }

   private static String getBundleName(Dictionary headers, String defaultValue) {
      return getHeaderValue(headers, "Bundle-Name", defaultValue);
   }

   private static String getBundleVendor(Dictionary headers, String defaultValue) {
      return getHeaderValue(headers, "Bundle-Vendor", defaultValue);
   }

   private static String getBundleVersion(Dictionary headers, String defaultValue) {
      return getHeaderValue(headers, "Bundle-Version", defaultValue);
   }

   public static List[] parsePluginElements(DocumentBuilder db, PluginRegistry mgr, URL fileUrl, Bundle plugin, org.osgi.framework.Bundle osgiBundle) {
      List<ExtensionPoint> extensionPoints = Collections.emptyList();
      List<Extension> extensions = Collections.emptyList();
      InputStream is = null;

      try {
         is = fileUrl.openStream();
         Element rootElement = db.parse(new InputSource(new InputStreamReader(is))).getDocumentElement();
         if (NucleusLogger.GENERAL.isDebugEnabled()) {
            NucleusLogger.GENERAL.debug(Localiser.msg("024003", fileUrl.toString()));
         }

         extensionPoints = parseExtensionPoints(rootElement, plugin, osgiBundle);
         if (NucleusLogger.GENERAL.isDebugEnabled()) {
            NucleusLogger.GENERAL.debug(Localiser.msg("024004", fileUrl.toString()));
         }

         extensions = parseExtensions(rootElement, plugin, osgiBundle);
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
}
