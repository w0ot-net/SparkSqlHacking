package org.apache.ivy.osgi.updatesite.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class UpdateSite {
   private URI uri;
   private String mirrorsURL;
   private boolean pack200;
   private URI digestUri;
   private List features = new ArrayList();

   public void setUri(URI uri) {
      this.uri = uri;
   }

   public URI getUri() {
      return this.uri;
   }

   public void setMirrorsURL(String mirrorsURL) {
      this.mirrorsURL = mirrorsURL;
   }

   public void setPack200(boolean pack200) {
      this.pack200 = pack200;
   }

   public void setDigestUri(URI digestUri) {
      this.digestUri = digestUri;
   }

   public URI getDigestUri() {
      return this.digestUri;
   }

   public void addFeature(EclipseFeature feature) {
      this.features.add(feature);
   }

   public List getFeatures() {
      return this.features;
   }

   public void setAssociateSitesURL(String associateSitesURL) {
   }
}
