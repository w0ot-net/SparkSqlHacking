package org.apache.ivy.osgi.updatesite;

import java.net.URI;
import org.apache.ivy.osgi.core.BundleArtifact;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.BundleRequirement;
import org.apache.ivy.osgi.updatesite.xml.EclipseFeature;
import org.apache.ivy.osgi.updatesite.xml.EclipsePlugin;
import org.apache.ivy.osgi.updatesite.xml.Require;
import org.apache.ivy.osgi.util.VersionRange;

public class PluginAdapter {
   public static BundleInfo featureAsBundle(URI baseUri, EclipseFeature feature) {
      BundleInfo b = new BundleInfo(feature.getId(), feature.getVersion());
      URI uri;
      if (feature.getUrl() == null) {
         uri = baseUri.resolve("features/" + feature.getId() + '_' + feature.getVersion() + ".jar");
      } else {
         uri = baseUri.resolve(feature.getUrl());
      }

      b.addArtifact(new BundleArtifact(false, uri, (String)null));
      b.setDescription(feature.getDescription());
      b.setLicense(feature.getLicense());

      for(EclipsePlugin plugin : feature.getPlugins()) {
         BundleRequirement r = new BundleRequirement("bundle", plugin.getId(), new VersionRange(plugin.getVersion()), (String)null);
         b.addRequirement(r);
      }

      for(Require require : feature.getRequires()) {
         String id;
         if (require.getPlugin() != null) {
            id = require.getPlugin();
         } else {
            id = require.getFeature();
         }

         if (!require.getMatch().equals("greaterOrEqual")) {
            throw new IllegalStateException("unsupported match " + require.getMatch());
         }

         VersionRange range = new VersionRange(require.getVersion());
         BundleRequirement r = new BundleRequirement("bundle", id, range, (String)null);
         b.addRequirement(r);
      }

      return b;
   }

   public static BundleInfo pluginAsBundle(URI baseUri, EclipsePlugin plugin) {
      BundleInfo b = new BundleInfo(plugin.getId(), plugin.getVersion());
      URI uri = baseUri.resolve("plugins/" + plugin.getId() + '_' + plugin.getVersion() + ".jar");
      b.addArtifact(new BundleArtifact(false, uri, (String)null));
      return b;
   }
}
