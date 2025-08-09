package org.apache.ivy.osgi.updatesite.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class EclipseUpdateSiteParser {
   public static UpdateSite parse(InputStream in) throws IOException, SAXException {
      SiteHandler handler = new SiteHandler();

      try {
         XMLHelper.parse((InputStream)in, (URL)null, handler, (LexicalHandler)null);
      } catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }

      return handler.updatesite;
   }

   private static class SiteHandler extends DelegatingHandler {
      private static final String SITE = "site";
      private static final String URL = "url";
      private static final String PACK200 = "pack200";
      private static final String MIRRORS_URL = "mirrorsURL";
      private static final String ASSOCIATE_SITES_URL = "associateSitesURL";
      private static final String DIGEST_URL = "digestURL";
      UpdateSite updatesite;

      public SiteHandler() {
         super("site");
         this.addChild(new FeatureHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(FeatureHandler child) {
               SiteHandler.this.updatesite.addFeature(child.feature);
            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         this.updatesite = new UpdateSite();
         String url = atts.getValue("url");
         if (!StringUtils.isNullOrEmpty(url)) {
            if (!url.endsWith("/") && !url.endsWith(File.separator)) {
               url = url + "/";
            }

            try {
               this.updatesite.setUri(new URI(url));
            } catch (URISyntaxException e) {
               throw new RuntimeException("illegal url", e);
            }
         }

         String mirrorsURL = atts.getValue("mirrorsURL");
         if (!StringUtils.isNullOrEmpty(mirrorsURL)) {
            this.updatesite.setMirrorsURL(mirrorsURL);
         }

         String pack200 = atts.getValue("pack200");
         if (pack200 != null && Boolean.parseBoolean(pack200)) {
            this.updatesite.setPack200(true);
         }

         String digestURL = atts.getValue("digestURL");
         if (digestURL != null) {
            try {
               this.updatesite.setDigestUri(new URI(digestURL));
            } catch (URISyntaxException e) {
               throw new RuntimeException("illegal url", e);
            }
         }

         String associateSitesURL = atts.getValue("associateSitesURL");
         if (associateSitesURL != null) {
            this.updatesite.setAssociateSitesURL(associateSitesURL);
         }

      }
   }

   private static class FeatureHandler extends DelegatingHandler {
      private static final String FEATURE = "feature";
      private static final String VERSION = "version";
      private static final String ID = "id";
      private static final String URL = "url";
      private static final String PATCH = "patch";
      private static final String ARCH = "arch";
      private static final String NL = "nl";
      private static final String WS = "ws";
      private static final String OS = "os";
      private static final String LABEL = "label";
      private static final String TYPE = "type";
      private EclipseFeature feature;

      public FeatureHandler() {
         super("feature");
         this.addChild(new CategoryHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(CategoryHandler child) {
               FeatureHandler.this.feature.addCategory(child.name);
            }
         });
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String id = atts.getValue("id");
         String version = atts.getValue("version");
         this.feature = new EclipseFeature(id, new Version(version));
         String url = atts.getValue("url");
         if (url != null) {
            this.feature.setURL(url);
         }

         this.feature.setType(atts.getValue("type"));
         this.feature.setLabel(atts.getValue("label"));
         this.feature.setOS(atts.getValue("os"));
         this.feature.setWS(atts.getValue("ws"));
         this.feature.setNL(atts.getValue("nl"));
         this.feature.setArch(atts.getValue("arch"));
         this.feature.setPatch(atts.getValue("patch"));
      }
   }

   private static class CategoryHandler extends DelegatingHandler {
      private static final String CATEGORY = "category";
      private static final String NAME = "name";
      String name;

      public CategoryHandler() {
         super("category");
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         this.name = atts.getValue("name");
      }
   }
}
