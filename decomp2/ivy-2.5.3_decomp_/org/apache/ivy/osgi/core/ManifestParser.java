package org.apache.ivy.osgi.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.osgi.util.VersionRange;
import org.apache.ivy.util.StringUtils;

public class ManifestParser {
   private static final String EXPORT_PACKAGE = "Export-Package";
   private static final String IMPORT_PACKAGE = "Import-Package";
   private static final String EXPORT_SERVICE = "Export-Service";
   private static final String IMPORT_SERVICE = "Import-Service";
   private static final String REQUIRE_BUNDLE = "Require-Bundle";
   private static final String BUNDLE_VERSION = "Bundle-Version";
   private static final String BUNDLE_NAME = "Bundle-Name";
   private static final String BUNDLE_DESCRIPTION = "Bundle-Description";
   private static final String BUNDLE_SYMBOLIC_NAME = "Bundle-SymbolicName";
   private static final String BUNDLE_MANIFEST_VERSION = "Bundle-ManifestVersion";
   private static final String BUNDLE_REQUIRED_EXECUTION_ENVIRONMENT = "Bundle-RequiredExecutionEnvironment";
   private static final String BUNDLE_CLASSPATH = "Bundle-ClassPath";
   private static final String ECLIPSE_SOURCE_BUNDLE = "Eclipse-SourceBundle";
   private static final String ATTR_RESOLUTION = "resolution";
   private static final String ATTR_VERSION = "version";
   private static final String ATTR_BUNDLE_VERSION = "bundle-version";
   private static final String ATTR_USE = "use";

   public static BundleInfo parseJarManifest(InputStream jarStream) throws IOException, ParseException {
      JarInputStream jis = new JarInputStream(jarStream);
      Manifest manifest = jis.getManifest();
      jis.close();
      return manifest == null ? null : parseManifest(manifest);
   }

   public static BundleInfo parseManifest(File manifestFile) throws IOException, ParseException {
      FileInputStream fis = new FileInputStream(manifestFile);
      Throwable var2 = null;

      BundleInfo var3;
      try {
         var3 = parseManifest((InputStream)fis);
      } catch (Throwable var12) {
         var2 = var12;
         throw var12;
      } finally {
         if (fis != null) {
            if (var2 != null) {
               try {
                  fis.close();
               } catch (Throwable var11) {
                  var2.addSuppressed(var11);
               }
            } else {
               fis.close();
            }
         }

      }

      return var3;
   }

   public static BundleInfo parseManifest(String manifest) throws IOException, ParseException {
      ByteArrayInputStream bais = new ByteArrayInputStream(manifest.getBytes(StandardCharsets.UTF_8));
      BundleInfo parseManifest = parseManifest((InputStream)bais);
      bais.close();
      return parseManifest;
   }

   public static BundleInfo parseManifest(InputStream manifestStream) throws IOException, ParseException {
      return parseManifest(new Manifest(manifestStream));
   }

   public static BundleInfo parseManifest(Manifest manifest) throws ParseException {
      Attributes mainAttributes = manifest.getMainAttributes();
      String symbolicName = (new ManifestHeaderValue(mainAttributes.getValue("Bundle-SymbolicName"))).getSingleValue();
      if (symbolicName == null) {
         throw new ParseException("No Bundle-SymbolicName in the manifest", 0);
      } else {
         String description = (new ManifestHeaderValue(mainAttributes.getValue("Bundle-Description"))).getSingleValue();
         if (description == null) {
            description = (new ManifestHeaderValue(mainAttributes.getValue("Bundle-Description"))).getSingleValue();
         }

         String vBundle = (new ManifestHeaderValue(mainAttributes.getValue("Bundle-Version"))).getSingleValue();

         Version version;
         try {
            version = versionOf(vBundle);
         } catch (NumberFormatException e) {
            throw new ParseException("The Bundle-Version has an incorrect version: " + vBundle + " (" + e.getMessage() + ")", 0);
         }

         BundleInfo bundleInfo = new BundleInfo(symbolicName, version);
         bundleInfo.setDescription(description);
         List<String> environments = (new ManifestHeaderValue(mainAttributes.getValue("Bundle-RequiredExecutionEnvironment"))).getValues();
         bundleInfo.setExecutionEnvironments(environments);
         parseRequirement(bundleInfo, mainAttributes, "Require-Bundle", "bundle", "bundle-version");
         parseRequirement(bundleInfo, mainAttributes, "Import-Package", "package", "version");
         parseRequirement(bundleInfo, mainAttributes, "Import-Service", "service", "version");
         ManifestHeaderValue exportElements = new ManifestHeaderValue(mainAttributes.getValue("Export-Package"));

         for(ManifestHeaderElement exportElement : exportElements.getElements()) {
            String vExport = (String)exportElement.getAttributes().get("version");
            Version v = null;

            try {
               v = versionOf(vExport);
            } catch (NumberFormatException e) {
               throw new ParseException("The Export-Package has an incorrect version: " + vExport + " (" + e.getMessage() + ")", 0);
            }

            for(String name : exportElement.getValues()) {
               ExportPackage export = new ExportPackage(name, v);
               String uses = (String)exportElement.getDirectives().get("use");
               if (uses != null) {
                  for(String use : StringUtils.splitToArray(uses)) {
                     export.addUse(use);
                  }
               }

               bundleInfo.addCapability(export);
            }
         }

         parseCapability(bundleInfo, mainAttributes, "Export-Service", "service");
         String eclipseSourceBundle = mainAttributes.getValue("Eclipse-SourceBundle");
         if (eclipseSourceBundle != null) {
            bundleInfo.setSource(true);
            ManifestHeaderValue eclipseSourceBundleValue = new ManifestHeaderValue(eclipseSourceBundle);
            ManifestHeaderElement element = (ManifestHeaderElement)eclipseSourceBundleValue.getElements().iterator().next();
            String symbolicNameTarget = (String)element.getValues().iterator().next();
            bundleInfo.setSymbolicNameTarget(symbolicNameTarget);
            String v = (String)element.getAttributes().get("version");
            if (v != null) {
               bundleInfo.setVersionTarget(new Version(v));
            }
         }

         String bundleClasspath = mainAttributes.getValue("Bundle-ClassPath");
         if (bundleClasspath != null) {
            ManifestHeaderValue bundleClasspathValue = new ManifestHeaderValue(bundleClasspath);
            bundleInfo.setClasspath(bundleClasspathValue.getValues());
            bundleInfo.setHasInnerClasspath(true);
         }

         return bundleInfo;
      }
   }

   private static void parseRequirement(BundleInfo bundleInfo, Attributes mainAttributes, String headerName, String type, String versionAttr) throws ParseException {
      ManifestHeaderValue elements = new ManifestHeaderValue(mainAttributes.getValue(headerName));

      for(ManifestHeaderElement element : elements.getElements()) {
         String resolution = (String)element.getDirectives().get("resolution");
         String attVersion = (String)element.getAttributes().get(versionAttr);
         VersionRange version = null;

         try {
            version = versionRangeOf(attVersion);
         } catch (ParseException e) {
            throw new ParseException("The " + headerName + " has an incorrect version: " + attVersion + " (" + e.getMessage() + ")", 0);
         }

         for(String name : element.getValues()) {
            bundleInfo.addRequirement(new BundleRequirement(type, name, version, resolution));
         }
      }

   }

   private static void parseCapability(BundleInfo bundleInfo, Attributes mainAttributes, String headerName, String type) throws ParseException {
      ManifestHeaderValue elements = new ManifestHeaderValue(mainAttributes.getValue(headerName));

      for(ManifestHeaderElement element : elements.getElements()) {
         String attVersion = (String)element.getAttributes().get("version");
         Version version = null;

         try {
            version = versionOf(attVersion);
         } catch (NumberFormatException e) {
            throw new ParseException("The " + headerName + " has an incorrect version: " + attVersion + " (" + e.getMessage() + ")", 0);
         }

         for(String name : element.getValues()) {
            BundleCapability export = new BundleCapability(type, name, version);
            bundleInfo.addCapability(export);
         }
      }

   }

   private static VersionRange versionRangeOf(String v) throws ParseException {
      return v == null ? null : new VersionRange(v);
   }

   private static Version versionOf(String v) {
      return v == null ? null : new Version(v);
   }

   public static String formatLines(String manifest) {
      StringBuilder buffer = new StringBuilder(manifest.length());

      for(String line : manifest.split("\n")) {
         if (line.length() <= 72) {
            buffer.append(line);
            buffer.append('\n');
         } else {
            buffer.append(line, 0, 72);
            buffer.append("\n ");

            int end;
            for(int n = 72; n <= line.length() - 1; n = end) {
               end = n + 71;
               if (end > line.length()) {
                  end = line.length();
               }

               buffer.append(line, n, end);
               buffer.append('\n');
               if (end != line.length()) {
                  buffer.append(' ');
               }
            }
         }
      }

      return buffer.toString();
   }
}
