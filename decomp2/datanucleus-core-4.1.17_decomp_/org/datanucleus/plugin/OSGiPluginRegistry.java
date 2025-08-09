package org.datanucleus.plugin;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class OSGiPluginRegistry implements PluginRegistry {
   private static final String DATANUCLEUS_PKG = "org.datanucleus";
   Map extensionPointsByUniqueId = new HashMap();
   Map registeredPluginByPluginId = new HashMap();
   ExtensionPoint[] extensionPoints = new ExtensionPoint[0];

   public OSGiPluginRegistry(ClassLoaderResolver clr) {
   }

   public ExtensionPoint getExtensionPoint(String id) {
      return (ExtensionPoint)this.extensionPointsByUniqueId.get(id);
   }

   public ExtensionPoint[] getExtensionPoints() {
      return this.extensionPoints;
   }

   public void registerExtensionPoints() {
      this.registerExtensions();
   }

   public void registerExtensions() {
      if (this.extensionPoints.length <= 0) {
         List registeringExtensions = new ArrayList();
         org.osgi.framework.Bundle bdl = FrameworkUtil.getBundle(this.getClass());
         BundleContext ctx = bdl.getBundleContext();
         if (ctx == null) {
            NucleusLogger.GENERAL.error("Bundle " + bdl.getSymbolicName() + " is in state " + bdl.getState() + " and has NULL context, so cannot register it properly!");
         }

         DocumentBuilder docBuilder = OSGiBundleParser.getDocumentBuilder();
         org.osgi.framework.Bundle[] osgiBundles = ctx.getBundles();

         for(org.osgi.framework.Bundle osgiBundle : osgiBundles) {
            URL pluginURL = osgiBundle.getEntry("plugin.xml");
            if (pluginURL != null) {
               Bundle bundle = this.registerBundle(osgiBundle);
               if (bundle != null) {
                  List[] elements = OSGiBundleParser.parsePluginElements(docBuilder, this, pluginURL, bundle, osgiBundle);
                  this.registerExtensionPointsForPluginInternal(elements[0], false);
                  registeringExtensions.addAll(elements[1]);
               }
            }
         }

         this.extensionPoints = (ExtensionPoint[])this.extensionPointsByUniqueId.values().toArray(new ExtensionPoint[this.extensionPointsByUniqueId.values().size()]);

         for(int i = 0; i < registeringExtensions.size(); ++i) {
            Extension extension = (Extension)registeringExtensions.get(i);
            ExtensionPoint exPoint = this.getExtensionPoint(extension.getExtensionPointId());
            if (exPoint == null) {
               if (extension.getPlugin() != null && extension.getPlugin().getSymbolicName() != null && extension.getPlugin().getSymbolicName().startsWith("org.datanucleus")) {
                  NucleusLogger.GENERAL.warn(Localiser.msg("024002", extension.getExtensionPointId(), extension.getPlugin().getSymbolicName(), extension.getPlugin().getManifestLocation()));
               }
            } else {
               extension.setExtensionPoint(exPoint);
               exPoint.addExtension(extension);
            }
         }

      }
   }

   protected void registerExtensionPointsForPluginInternal(List extPoints, boolean updateExtensionPointsArray) {
      for(ExtensionPoint exPoint : extPoints) {
         this.extensionPointsByUniqueId.put(exPoint.getUniqueId(), exPoint);
      }

      if (updateExtensionPointsArray) {
         this.extensionPoints = (ExtensionPoint[])this.extensionPointsByUniqueId.values().toArray(new ExtensionPoint[this.extensionPointsByUniqueId.values().size()]);
      }

   }

   private Bundle registerBundle(org.osgi.framework.Bundle osgiBundle) {
      Bundle bundle = OSGiBundleParser.parseManifest(osgiBundle);
      if (bundle == null) {
         return null;
      } else {
         if (this.registeredPluginByPluginId.get(bundle.getSymbolicName()) == null) {
            if (NucleusLogger.GENERAL.isDebugEnabled()) {
               NucleusLogger.GENERAL.debug("Registering bundle " + bundle.getSymbolicName() + " version " + bundle.getVersion() + " at URL " + bundle.getManifestLocation() + ".");
            }

            this.registeredPluginByPluginId.put(bundle.getSymbolicName(), bundle);
         }

         return bundle;
      }
   }

   public Object createExecutableExtension(ConfigurationElement confElm, String name, Class[] argsClass, Object[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
      String symbolicName = confElm.getExtension().getPlugin().getSymbolicName();
      String attribute = confElm.getAttribute(name);
      org.osgi.framework.Bundle osgiBundle = this.getOsgiBundle(symbolicName);
      Class cls = osgiBundle.loadClass(attribute);
      Constructor constructor = cls.getConstructor(argsClass);

      try {
         return constructor.newInstance(args);
      } catch (InstantiationException e1) {
         NucleusLogger.GENERAL.error(e1.getMessage(), e1);
         throw e1;
      } catch (IllegalAccessException e2) {
         NucleusLogger.GENERAL.error(e2.getMessage(), e2);
         throw e2;
      } catch (IllegalArgumentException e3) {
         NucleusLogger.GENERAL.error(e3.getMessage(), e3);
         throw e3;
      } catch (InvocationTargetException e4) {
         NucleusLogger.GENERAL.error(e4.getMessage(), e4);
         throw e4;
      }
   }

   public Class loadClass(String pluginId, String className) throws ClassNotFoundException {
      return this.getOsgiBundle(pluginId).loadClass(className);
   }

   public URL resolveURLAsFileURL(URL url) throws IOException {
      return null;
   }

   public void resolveConstraints() {
   }

   public Bundle[] getBundles() {
      return (Bundle[])this.registeredPluginByPluginId.values().toArray(new Bundle[this.registeredPluginByPluginId.values().size()]);
   }

   private org.osgi.framework.Bundle getOsgiBundle(String symbolicName) {
      BundleContext ctx = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
      org.osgi.framework.Bundle[] osgiBundles = ctx.getBundles();

      for(org.osgi.framework.Bundle osgiBundle : osgiBundles) {
         if (symbolicName.equals(osgiBundle.getSymbolicName())) {
            return osgiBundle;
         }
      }

      return null;
   }

   protected static class ExtensionSorter implements Comparator, Serializable {
      private static final long serialVersionUID = -264321551131696022L;

      public int compare(Extension o1, Extension o2) {
         String name1 = o1.getPlugin().getSymbolicName();
         String name2 = o2.getPlugin().getSymbolicName();
         if (name1.startsWith("org.datanucleus") && !name2.startsWith("org.datanucleus")) {
            return -1;
         } else {
            return !name1.startsWith("org.datanucleus") && name2.startsWith("org.datanucleus") ? 1 : name1.compareTo(name2);
         }
      }
   }
}
