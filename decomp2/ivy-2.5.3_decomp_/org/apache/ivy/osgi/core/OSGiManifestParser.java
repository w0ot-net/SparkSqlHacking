package org.apache.ivy.osgi.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.jar.Manifest;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.parser.xml.XmlModuleDescriptorWriter;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.util.StringUtils;

public class OSGiManifestParser implements ModuleDescriptorParser {
   private static final OSGiManifestParser INSTANCE = new OSGiManifestParser();
   private ExecutionEnvironmentProfileProvider profileProvider = ExecutionEnvironmentProfileProvider.getInstance();

   public static OSGiManifestParser getInstance() {
      return INSTANCE;
   }

   public void add(ExecutionEnvironmentProfileProvider pp) {
      this.profileProvider = pp;
   }

   public boolean accept(Resource res) {
      return res != null && !StringUtils.isNullOrEmpty(res.getName()) && res.getName().toUpperCase(Locale.US).endsWith("MANIFEST.MF");
   }

   public ModuleDescriptor parseDescriptor(ParserSettings ivySettings, URL descriptorURL, Resource res, boolean validate) throws ParseException, IOException {
      InputStream resourceStream = res.openStream();
      Throwable var7 = null;

      Manifest manifest;
      try {
         manifest = new Manifest(resourceStream);
      } catch (Throwable var18) {
         var7 = var18;
         throw var18;
      } finally {
         if (resourceStream != null) {
            if (var7 != null) {
               try {
                  resourceStream.close();
               } catch (Throwable var16) {
                  var7.addSuppressed(var16);
               }
            } else {
               resourceStream.close();
            }
         }

      }

      BundleInfo bundleInfo = ManifestParser.parseManifest(manifest);

      try {
         bundleInfo.addArtifact(new BundleArtifact(false, descriptorURL.toURI(), (String)null));
      } catch (URISyntaxException e) {
         throw new RuntimeException("Unsupported repository, resources names are not uris", e);
      }

      return BundleInfoAdapter.toModuleDescriptor(this, (URI)null, bundleInfo, manifest, this.profileProvider);
   }

   public void toIvyFile(InputStream is, Resource res, File destFile, ModuleDescriptor md) throws ParseException, IOException {
      try {
         XmlModuleDescriptorWriter.write(md, destFile);
      } finally {
         if (is != null) {
            is.close();
         }

      }

   }

   public ModuleDescriptor parseDescriptor(ParserSettings ivySettings, URL descriptorURL, boolean validate) throws ParseException, IOException {
      URLResource resource = new URLResource(descriptorURL);
      return this.parseDescriptor(ivySettings, descriptorURL, resource, validate);
   }

   public String getType() {
      return "manifest";
   }

   public Artifact getMetadataArtifact(ModuleRevisionId mrid, Resource res) {
      return DefaultArtifact.newIvyArtifact(mrid, new Date(res.getLastModified()));
   }

   public String toString() {
      return "manifest parser";
   }
}
