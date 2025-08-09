package org.apache.ivy.osgi.repo;

import java.net.URI;
import java.text.ParseException;
import java.util.Iterator;
import org.apache.ivy.osgi.core.BundleArtifact;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.core.ManifestParser;
import org.apache.ivy.util.Message;

public class BundleRepoDescriptor extends EditableRepoDescriptor {
   private String name;
   private String lastModified;

   public BundleRepoDescriptor(URI baseUri, ExecutionEnvironmentProfileProvider profileProvider) {
      super(baseUri, profileProvider);
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setLastModified(String lastModified) {
      this.lastModified = lastModified;
   }

   public String getLastModified() {
      return this.lastModified;
   }

   public void populate(Iterator it) {
      while(it.hasNext()) {
         ManifestAndLocation manifestAndLocation = (ManifestAndLocation)it.next();

         try {
            BundleInfo bundleInfo = ManifestParser.parseManifest(manifestAndLocation.getManifest());
            bundleInfo.addArtifact(new BundleArtifact(false, manifestAndLocation.getUri(), (String)null));
            this.addBundle(bundleInfo);
         } catch (ParseException e) {
            Message.error("Rejected " + manifestAndLocation.getUri() + ": " + e.getMessage());
         }
      }

   }
}
