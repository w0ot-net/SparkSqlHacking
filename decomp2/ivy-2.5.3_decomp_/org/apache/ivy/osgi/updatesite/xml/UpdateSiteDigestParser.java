package org.apache.ivy.osgi.updatesite.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.updatesite.UpdateSiteDescriptor;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class UpdateSiteDigestParser {
   public static UpdateSiteDescriptor parse(InputStream in, UpdateSite site) throws IOException, SAXException {
      DigestHandler handler = new DigestHandler(site);

      try {
         XMLHelper.parse((InputStream)in, (URL)null, handler, (LexicalHandler)null);
      } catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }

      return handler.repoDescriptor;
   }

   static class DigestHandler extends DelegatingHandler {
      private static final String DIGEST = "digest";
      UpdateSiteDescriptor repoDescriptor;

      public DigestHandler(UpdateSite site) {
         super("digest");
         this.repoDescriptor = new UpdateSiteDescriptor(site.getUri(), ExecutionEnvironmentProfileProvider.getInstance());
         this.addChild(new FeatureParser.FeatureHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(FeatureParser.FeatureHandler child) {
               DigestHandler.this.repoDescriptor.addFeature(child.feature);
            }
         });
      }
   }
}
