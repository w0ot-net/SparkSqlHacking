package org.datanucleus.plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import javax.xml.parsers.DocumentBuilder;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class NonManagedPluginRegistry implements PluginRegistry {
   private static final String DATANUCLEUS_PKG = "org.datanucleus";
   private static final String PLUGIN_DIR = "/";
   private static final FilenameFilter MANIFEST_FILE_FILTER = new FilenameFilter() {
      public boolean accept(File dir, String name) {
         if (name.equalsIgnoreCase("meta-inf")) {
            return true;
         } else {
            return !dir.getName().equalsIgnoreCase("meta-inf") ? false : name.equalsIgnoreCase("manifest.mf");
         }
      }
   };
   private static final char JAR_SEPARATOR = '!';
   private final ClassLoaderResolver clr;
   Map extensionPointsByUniqueId = new HashMap();
   Map registeredPluginByPluginId = new HashMap();
   ExtensionPoint[] extensionPoints;
   private String bundleCheckType = "EXCEPTION";
   private boolean allowUserBundles = false;

   public NonManagedPluginRegistry(ClassLoaderResolver clr, String bundleCheckType, boolean allowUserBundles) {
      this.clr = clr;
      this.extensionPoints = new ExtensionPoint[0];
      this.bundleCheckType = bundleCheckType != null ? bundleCheckType : "EXCEPTION";
      this.allowUserBundles = allowUserBundles;
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

   public void registerExtensionsForPlugin(URL pluginURL, Bundle bundle) {
      DocumentBuilder docBuilder = PluginParser.getDocumentBuilder();
      List[] elements = PluginParser.parsePluginElements(docBuilder, this, pluginURL, bundle, this.clr);
      this.registerExtensionPointsForPluginInternal(elements[0], true);

      for(Extension extension : elements[1]) {
         ExtensionPoint exPoint = (ExtensionPoint)this.extensionPointsByUniqueId.get(extension.getExtensionPointId());
         if (exPoint == null) {
            NucleusLogger.GENERAL.warn(Localiser.msg("024002", extension.getExtensionPointId(), extension.getPlugin().getSymbolicName(), extension.getPlugin().getManifestLocation().toString()));
         } else {
            extension.setExtensionPoint(exPoint);
            exPoint.addExtension(extension);
         }
      }

   }

   public void registerExtensions() {
      if (this.extensionPoints.length <= 0) {
         List registeringExtensions = new ArrayList();
         DocumentBuilder docBuilder = PluginParser.getDocumentBuilder();

         try {
            Enumeration<URL> paths = this.clr.getResources("/plugin.xml", ClassConstants.NUCLEUS_CONTEXT_LOADER);

            while(paths.hasMoreElements()) {
               URL pluginURL = (URL)paths.nextElement();
               URL manifest = this.getManifestURL(pluginURL);
               if (manifest != null) {
                  Bundle bundle = this.registerBundle(manifest);
                  if (bundle != null) {
                     List[] elements = PluginParser.parsePluginElements(docBuilder, this, pluginURL, bundle, this.clr);
                     this.registerExtensionPointsForPluginInternal(elements[0], false);
                     registeringExtensions.addAll(elements[1]);
                  }
               }
            }
         } catch (IOException e) {
            throw (new NucleusException("Error loading resource", e)).setFatal();
         }

         this.extensionPoints = (ExtensionPoint[])this.extensionPointsByUniqueId.values().toArray(new ExtensionPoint[this.extensionPointsByUniqueId.values().size()]);

         for(int i = 0; i < registeringExtensions.size(); ++i) {
            Extension extension = (Extension)registeringExtensions.get(i);
            ExtensionPoint exPoint = this.getExtensionPoint(extension.getExtensionPointId());
            if (exPoint == null) {
               if (extension.getPlugin() != null && extension.getPlugin().getSymbolicName() != null && extension.getPlugin().getSymbolicName().startsWith("org.datanucleus")) {
                  NucleusLogger.GENERAL.warn(Localiser.msg("024002", extension.getExtensionPointId(), extension.getPlugin().getSymbolicName(), extension.getPlugin().getManifestLocation().toString()));
               }
            } else {
               extension.setExtensionPoint(exPoint);
               exPoint.addExtension(extension);
            }
         }

         if (this.allowUserBundles) {
            ExtensionSorter sorter = new ExtensionSorter();

            for(int i = 0; i < this.extensionPoints.length; ++i) {
               ExtensionPoint pt = this.extensionPoints[i];
               pt.sortExtensions(sorter);
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

   protected Bundle registerBundle(URL manifest) {
      if (manifest == null) {
         throw new IllegalArgumentException(Localiser.msg("024007"));
      } else {
         InputStream is = null;

         Bundle var6;
         try {
            Manifest mf = null;
            if (!manifest.getProtocol().equals("jar") && !manifest.getProtocol().equals("zip") && !manifest.getProtocol().equals("wsjar")) {
               if (!manifest.getProtocol().equals("rar") && !manifest.getProtocol().equals("war")) {
                  is = manifest.openStream();
                  mf = new Manifest(is);
                  Bundle var42 = this.registerBundle(mf, manifest);
                  return var42;
               }

               String path = StringUtils.getDecodedStringFromURLString(manifest.toExternalForm());
               int index = path.indexOf(33);
               String rarPath = path.substring(4, index);
               File file = new File(rarPath);
               URL rarUrl = file.toURI().toURL();
               String jarPath = path.substring(index + 1, path.indexOf(33, index + 1));
               JarFile rarFile = new JarFile(file);
               JarInputStream jis = new JarInputStream(rarFile.getInputStream(rarFile.getEntry(jarPath)));

               try {
                  mf = jis.getManifest();
                  if (mf == null) {
                     Object var12 = null;
                     return (Bundle)var12;
                  }
               } finally {
                  jis.close();
               }

               Bundle var52 = this.registerBundle(mf, rarUrl);
               return var52;
            }

            if (!manifest.getPath().startsWith("http://") && !manifest.getPath().startsWith("https://")) {
               int begin = 4;
               if (manifest.getProtocol().equals("wsjar")) {
                  begin = 6;
               }

               String path = StringUtils.getDecodedStringFromURLString(manifest.toExternalForm());
               int index = path.indexOf(33);
               String jarPath = path.substring(begin, index);
               if (jarPath.startsWith("file:")) {
                  jarPath = jarPath.substring(5);
               }

               File jarFile = new File(jarPath);
               mf = (new JarFile(jarFile)).getManifest();
               if (mf == null) {
                  Object var50 = null;
                  return (Bundle)var50;
               }

               Bundle var9 = this.registerBundle(mf, jarFile.toURI().toURL());
               return var9;
            }

            JarURLConnection jarConnection = (JarURLConnection)manifest.openConnection();
            URL url = jarConnection.getJarFileURL();
            mf = jarConnection.getManifest();
            if (mf != null) {
               var6 = this.registerBundle(mf, url);
               return var6;
            }

            var6 = null;
         } catch (IOException e) {
            throw (new NucleusException(Localiser.msg("024008", manifest), e)).setFatal();
         } finally {
            if (is != null) {
               try {
                  is.close();
               } catch (IOException var32) {
               }
            }

         }

         return var6;
      }
   }

   protected Bundle registerBundle(Manifest mf, URL manifest) {
      Bundle bundle = PluginParser.parseManifest(mf, manifest);
      if (bundle != null && bundle.getSymbolicName() != null) {
         if (!this.allowUserBundles && !bundle.getSymbolicName().startsWith("org.datanucleus")) {
            NucleusLogger.GENERAL.debug("Ignoring bundle " + bundle.getSymbolicName() + " since not DataNucleus, and only loading DataNucleus bundles");
            return null;
         } else {
            if (this.registeredPluginByPluginId.get(bundle.getSymbolicName()) == null) {
               if (NucleusLogger.GENERAL.isDebugEnabled()) {
                  NucleusLogger.GENERAL.debug("Registering bundle " + bundle.getSymbolicName() + " version " + bundle.getVersion() + " at URL " + bundle.getManifestLocation() + ".");
               }

               this.registeredPluginByPluginId.put(bundle.getSymbolicName(), bundle);
            } else {
               Bundle previousBundle = (Bundle)this.registeredPluginByPluginId.get(bundle.getSymbolicName());
               if (bundle.getSymbolicName().startsWith("org.datanucleus") && !bundle.getManifestLocation().toExternalForm().equals(previousBundle.getManifestLocation().toExternalForm())) {
                  String msg = Localiser.msg("024009", bundle.getSymbolicName(), bundle.getManifestLocation(), previousBundle.getManifestLocation());
                  if (this.bundleCheckType.equalsIgnoreCase("EXCEPTION")) {
                     throw new NucleusException(msg);
                  }

                  if (this.bundleCheckType.equalsIgnoreCase("LOG")) {
                     NucleusLogger.GENERAL.warn(msg);
                  }
               }
            }

            return bundle;
         }
      } else {
         return null;
      }
   }

   private URL getManifestURL(URL pluginURL) {
      if (pluginURL == null) {
         return null;
      } else if (!pluginURL.toString().startsWith("jar") && !pluginURL.toString().startsWith("zip") && !pluginURL.toString().startsWith("rar") && !pluginURL.toString().startsWith("war") && !pluginURL.toString().startsWith("wsjar")) {
         if (pluginURL.toString().startsWith("vfs")) {
            String urlStr = pluginURL.toString().replace("plugin.xml", "META-INF/MANIFEST.MF");

            try {
               return new URL(urlStr);
            } catch (MalformedURLException e) {
               NucleusLogger.GENERAL.warn(Localiser.msg("024010", urlStr), e);
               return null;
            }
         } else if (pluginURL.toString().startsWith("jndi")) {
            String urlStr = pluginURL.toString().substring(5);
            urlStr = urlStr.replaceAll("\\.jar/", ".jar!/");
            urlStr = "jar:file:" + urlStr;

            try {
               return new URL(urlStr);
            } catch (MalformedURLException e) {
               NucleusLogger.GENERAL.warn(Localiser.msg("024010", urlStr), e);
               return null;
            }
         } else if (pluginURL.toString().startsWith("code-source")) {
            String urlStr = pluginURL.toString().substring(12);
            urlStr = "jar:file:" + urlStr;

            try {
               return new URL(urlStr);
            } catch (MalformedURLException e) {
               NucleusLogger.GENERAL.warn(Localiser.msg("024010", urlStr), e);
               return null;
            }
         } else {
            try {
               File file = new File((new URI(pluginURL.toString())).getPath());
               File[] dirs = (new File(file.getParent())).listFiles(MANIFEST_FILE_FILTER);
               if (dirs != null && dirs.length > 0) {
                  File[] files = dirs[0].listFiles(MANIFEST_FILE_FILTER);
                  if (files != null && files.length > 0) {
                     try {
                        return files[0].toURI().toURL();
                     } catch (MalformedURLException e) {
                        NucleusLogger.GENERAL.warn(Localiser.msg("024011", pluginURL), e);
                        return null;
                     }
                  }
               }
            } catch (URISyntaxException use) {
               NucleusLogger.GENERAL.warn(Localiser.msg("024011", pluginURL), use);
               return null;
            }

            NucleusLogger.GENERAL.warn(Localiser.msg("024012", pluginURL));
            return null;
         }
      } else {
         return pluginURL;
      }
   }

   public Object createExecutableExtension(ConfigurationElement confElm, String name, Class[] argsClass, Object[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
      Class cls = this.clr.classForName(confElm.getAttribute(name), ClassConstants.NUCLEUS_CONTEXT_LOADER);
      Constructor constructor = cls.getConstructor(argsClass);
      return constructor.newInstance(args);
   }

   public Class loadClass(String pluginId, String className) throws ClassNotFoundException {
      return this.clr.classForName(className, ClassConstants.NUCLEUS_CONTEXT_LOADER);
   }

   public URL resolveURLAsFileURL(URL url) throws IOException {
      return url;
   }

   public void resolveConstraints() {
      for(Bundle bundle : this.registeredPluginByPluginId.values()) {
         for(Bundle.BundleDescription bd : bundle.getRequireBundle()) {
            String symbolicName = bd.getBundleSymbolicName();
            Bundle requiredBundle = (Bundle)this.registeredPluginByPluginId.get(symbolicName);
            if (requiredBundle == null) {
               if (bd.getParameter("resolution") != null && bd.getParameter("resolution").equalsIgnoreCase("optional")) {
                  NucleusLogger.GENERAL.debug(Localiser.msg("024013", bundle.getSymbolicName(), symbolicName));
               } else {
                  NucleusLogger.GENERAL.error(Localiser.msg("024014", bundle.getSymbolicName(), symbolicName));
               }
            }

            if (bd.getParameter("bundle-version") != null && requiredBundle != null && !this.isVersionInInterval(requiredBundle.getVersion(), bd.getParameter("bundle-version"))) {
               NucleusLogger.GENERAL.error(Localiser.msg("024015", bundle.getSymbolicName(), symbolicName, bd.getParameter("bundle-version"), bundle.getVersion()));
            }
         }
      }

   }

   private boolean isVersionInInterval(String version, String interval) {
      Bundle.BundleVersionRange versionRange = PluginParser.parseVersionRange(version);
      Bundle.BundleVersionRange intervalRange = PluginParser.parseVersionRange(interval);
      int compare_floor = versionRange.floor.compareTo(intervalRange.floor);
      boolean result = true;
      if (intervalRange.floor_inclusive) {
         result = compare_floor >= 0;
      } else {
         result = compare_floor > 0;
      }

      if (intervalRange.ceiling != null) {
         int compare_ceiling = versionRange.floor.compareTo(intervalRange.ceiling);
         if (intervalRange.ceiling_inclusive) {
            result = compare_ceiling <= 0;
         } else {
            result = compare_ceiling < 0;
         }
      }

      return result;
   }

   public Bundle[] getBundles() {
      return (Bundle[])this.registeredPluginByPluginId.values().toArray(new Bundle[this.registeredPluginByPluginId.values().size()]);
   }

   protected static class ExtensionSorter implements Comparator, Serializable {
      private static final long serialVersionUID = 2606866392881023620L;

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
