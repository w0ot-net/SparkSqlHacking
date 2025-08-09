package org.datanucleus.plugin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassLoaderResolverImpl;
import org.datanucleus.exceptions.ClassNotResolvedException;

public class PluginManager {
   private PluginRegistry registry;

   public PluginManager(String registryClassName, ClassLoaderResolver clr, Properties props) {
      String bundleCheckAction = "EXCEPTION";
      if (props.containsKey("bundle-check-action")) {
         bundleCheckAction = props.getProperty("bundle-check-action");
      }

      String allowUserBundles = props.getProperty("allow-user-bundles");
      boolean userBundles = allowUserBundles != null ? Boolean.valueOf(allowUserBundles) : true;
      this.registry = PluginRegistryFactory.newPluginRegistry(registryClassName, bundleCheckAction, userBundles, clr);
      this.registry.registerExtensionPoints();
      this.registry.registerExtensions();
      String validateStr = props.containsKey("validate-plugins") ? props.getProperty("validate-plugins") : "false";
      if (validateStr.equalsIgnoreCase("true")) {
         this.registry.resolveConstraints();
      }

   }

   public String getRegistryClassName() {
      return this.registry.getClass().getName();
   }

   public ExtensionPoint getExtensionPoint(String id) {
      return this.registry.getExtensionPoint(id);
   }

   public ConfigurationElement getConfigurationElementForExtension(String extensionPointName, String discrimAttrName, String discrimAttrValue) {
      return this.getConfigurationElementForExtension(extensionPointName, discrimAttrName != null ? new String[]{discrimAttrName} : new String[0], discrimAttrValue != null ? new String[]{discrimAttrValue} : new String[0]);
   }

   public ConfigurationElement[] getConfigurationElementsForExtension(String extensionPointName, String discrimAttrName, String discrimAttrValue) {
      List<ConfigurationElement> elems = this.getConfigurationElementsForExtension(extensionPointName, discrimAttrName != null ? new String[]{discrimAttrName} : new String[0], discrimAttrValue != null ? new String[]{discrimAttrValue} : new String[0]);
      return !elems.isEmpty() ? (ConfigurationElement[])elems.toArray(new ConfigurationElement[elems.size()]) : null;
   }

   public ConfigurationElement getConfigurationElementForExtension(String extensionPointName, String[] discrimAttrName, String[] discrimAttrValue) {
      List matchingConfigElements = this.getConfigurationElementsForExtension(extensionPointName, discrimAttrName, discrimAttrValue);
      return !matchingConfigElements.isEmpty() ? (ConfigurationElement)matchingConfigElements.get(0) : null;
   }

   private List getConfigurationElementsForExtension(String extensionPointName, String[] discrimAttrName, String[] discrimAttrValue) {
      List matchingConfigElements = new LinkedList();
      ExtensionPoint extensionPoint = this.getExtensionPoint(extensionPointName);
      if (extensionPoint != null) {
         Extension[] ex = extensionPoint.getExtensions();

         for(int i = 0; i < ex.length; ++i) {
            ConfigurationElement[] confElm = ex[i].getConfigurationElements();

            for(int j = 0; j < confElm.length; ++j) {
               boolean equals = true;

               for(int k = 0; k < discrimAttrName.length; ++k) {
                  if (discrimAttrValue[k] == null) {
                     if (confElm[j].getAttribute(discrimAttrName[k]) != null) {
                        equals = false;
                        break;
                     }
                  } else {
                     if (confElm[j].getAttribute(discrimAttrName[k]) == null) {
                        equals = false;
                        break;
                     }

                     if (!confElm[j].getAttribute(discrimAttrName[k]).equalsIgnoreCase(discrimAttrValue[k])) {
                        equals = false;
                        break;
                     }
                  }
               }

               if (equals) {
                  matchingConfigElements.add(confElm[j]);
               }
            }
         }
      }

      Collections.sort(matchingConfigElements, new ConfigurationElementPriorityComparator());
      return matchingConfigElements;
   }

   public String getAttributeValueForExtension(String extensionPoint, String discrimAttrName, String discrimAttrValue, String attributeName) {
      ConfigurationElement elem = this.getConfigurationElementForExtension(extensionPoint, discrimAttrName, discrimAttrValue);
      return elem != null ? elem.getAttribute(attributeName) : null;
   }

   public String[] getAttributeValuesForExtension(String extensionPoint, String discrimAttrName, String discrimAttrValue, String attributeName) {
      ConfigurationElement[] elems = this.getConfigurationElementsForExtension(extensionPoint, discrimAttrName, discrimAttrValue);
      if (elems == null) {
         return null;
      } else {
         String[] attrValues = new String[elems.length];

         for(int i = 0; i < elems.length; ++i) {
            attrValues[i] = elems[i].getAttribute(attributeName);
         }

         return attrValues;
      }
   }

   public String getAttributeValueForExtension(String extensionPoint, String[] discrimAttrName, String[] discrimAttrValue, String attributeName) {
      ConfigurationElement elem = this.getConfigurationElementForExtension(extensionPoint, discrimAttrName, discrimAttrValue);
      return elem != null ? elem.getAttribute(attributeName) : null;
   }

   public Object createExecutableExtension(String extensionPoint, String discrimAttrName, String discrimAttrValue, String attributeName, Class[] argsClass, Object[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
      ConfigurationElement elem = this.getConfigurationElementForExtension(extensionPoint, discrimAttrName, discrimAttrValue);
      return elem != null ? this.registry.createExecutableExtension(elem, attributeName, argsClass, args) : null;
   }

   public Object createExecutableExtension(String extensionPoint, String[] discrimAttrName, String[] discrimAttrValue, String attributeName, Class[] argsClass, Object[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
      ConfigurationElement elem = this.getConfigurationElementForExtension(extensionPoint, discrimAttrName, discrimAttrValue);
      return elem != null ? this.registry.createExecutableExtension(elem, attributeName, argsClass, args) : null;
   }

   public Class loadClass(String pluginId, String className) throws ClassNotResolvedException {
      try {
         return this.registry.loadClass(pluginId, className);
      } catch (ClassNotFoundException ex) {
         throw new ClassNotResolvedException(ex.getMessage(), ex);
      }
   }

   public URL resolveURLAsFileURL(URL url) throws IOException {
      return this.registry.resolveURLAsFileURL(url);
   }

   public String getVersionForBundle(String bundleName) {
      Bundle[] bundles = this.registry.getBundles();
      if (bundles != null) {
         for(int i = 0; i < bundles.length; ++i) {
            Bundle bundle = bundles[i];
            if (bundle.getSymbolicName().equals(bundleName)) {
               return bundle.getVersion();
            }
         }
      }

      return null;
   }

   public static PluginManager createPluginManager(Map props, ClassLoader loader) {
      ClassLoaderResolver clr = loader != null ? new ClassLoaderResolverImpl(loader) : new ClassLoaderResolverImpl();
      if (props != null) {
         clr.registerUserClassLoader((ClassLoader)props.get("datanucleus.primaryClassLoader"));
      }

      Properties pluginProps = new Properties();
      String registryClassName = null;
      if (props != null) {
         registryClassName = (String)props.get("datanucleus.plugin.pluginRegistryClassName");
         if (props.containsKey("datanucleus.plugin.pluginRegistryBundleCheck")) {
            pluginProps.setProperty("bundle-check-action", (String)props.get("datanucleus.plugin.pluginRegistryBundleCheck"));
         }

         if (props.containsKey("datanucleus.plugin.allowUserBundles")) {
            pluginProps.setProperty("allow-user-bundles", (String)props.get("datanucleus.plugin.allowUserBundles"));
         }

         if (props.containsKey("datanucleus.plugin.validatePlugins")) {
            pluginProps.setProperty("validate-plugins", (String)props.get("datanucleus.plugin.validatePlugins"));
         }
      }

      return new PluginManager(registryClassName, clr, pluginProps);
   }

   private static final class ConfigurationElementPriorityComparator implements Comparator {
      private ConfigurationElementPriorityComparator() {
      }

      public int compare(ConfigurationElement elm1, ConfigurationElement elm2) {
         String pri1 = elm1.getAttribute("priority");
         String pri2 = elm2.getAttribute("priority");
         return (pri2 == null ? 0 : Integer.parseInt(pri2)) - (pri1 == null ? 0 : Integer.parseInt(pri1));
      }
   }
}
