package org.apache.ivy.osgi.updatesite;

import java.net.URI;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.repo.EditableRepoDescriptor;
import org.apache.ivy.osgi.updatesite.xml.EclipseFeature;
import org.apache.ivy.osgi.updatesite.xml.EclipsePlugin;

public class UpdateSiteDescriptor extends EditableRepoDescriptor {
   public UpdateSiteDescriptor(URI baseUri, ExecutionEnvironmentProfileProvider profileProvider) {
      super(baseUri, profileProvider);
   }

   public void addFeature(EclipseFeature feature) {
      this.addBundle(PluginAdapter.featureAsBundle(this.getBaseUri(), feature));

      for(EclipsePlugin plugin : feature.getPlugins()) {
         this.addBundle(PluginAdapter.pluginAsBundle(this.getBaseUri(), plugin));
      }

   }
}
