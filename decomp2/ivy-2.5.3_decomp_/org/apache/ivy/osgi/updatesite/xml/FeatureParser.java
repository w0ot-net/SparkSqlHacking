package org.apache.ivy.osgi.updatesite.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class FeatureParser {
   public static EclipseFeature parse(InputStream in) throws IOException, SAXException {
      FeatureHandler handler = new FeatureHandler();

      try {
         XMLHelper.parse((InputStream)in, (URL)null, handler, (LexicalHandler)null);
      } catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }

      return handler.feature;
   }

   static class FeatureHandler extends DelegatingHandler {
      private static final String FEATURE = "feature";
      private static final String COLOCATION_AFFINITY = "colocation-affinity";
      private static final String PRIMARY = "primary";
      private static final String EXCLUSIVE = "exclusive";
      private static final String PLUGIN = "plugin";
      private static final String APPLICATION = "application";
      private static final String ARCH = "arch";
      private static final String NL = "nl";
      private static final String WS = "ws";
      private static final String OS = "os";
      private static final String VERSION = "version";
      private static final String ID = "id";
      private static final String PROVIDER_NAME = "provider-name";
      private static final String LABEL = "label";
      private static final String IMAGE = "image";
      EclipseFeature feature;

      public FeatureHandler() {
         super("feature");
         this.addChild(new DescriptionHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(DescriptionHandler child) {
               FeatureHandler.this.feature.setDescription(child.getBufferedChars().trim());
            }
         });
         this.addChild(new LicenseHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(LicenseHandler child) {
               FeatureHandler.this.feature.setLicense(child.getBufferedChars().trim());
            }
         });
         this.addChild(new CopyrightHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(CopyrightHandler child) {
               FeatureHandler.this.feature.setCopyright(child.getBufferedChars().trim());
            }
         });
         this.addChild(new PluginHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(PluginHandler child) {
               FeatureHandler.this.feature.addPlugin(child.plugin);
            }
         });
         this.addChild(new RequiresHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(RequiresHandler child) {
               for(Require require : child.requires) {
                  FeatureHandler.this.feature.addRequire(require);
               }

            }
         });
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String id = atts.getValue("id");
         String version = atts.getValue("version");
         this.feature = new EclipseFeature(id, new Version(version));
         this.feature.setOS(atts.getValue("os"));
         this.feature.setWS(atts.getValue("ws"));
         this.feature.setNL(atts.getValue("nl"));
         this.feature.setArch(atts.getValue("arch"));
         this.feature.setApplication(atts.getValue("application"));
         this.feature.setPlugin(atts.getValue("plugin"));
         this.feature.setExclusive(Boolean.valueOf(atts.getValue("exclusive")));
         this.feature.setPrimary(Boolean.valueOf(atts.getValue("primary")));
         this.feature.setColocationAffinity(atts.getValue("colocation-affinity"));
         this.feature.setProviderName(atts.getValue("provider-name"));
         this.feature.setLabel(atts.getValue("label"));
         this.feature.setImage(atts.getValue("image"));
      }
   }

   private static class PluginHandler extends DelegatingHandler {
      private static final String PLUGIN = "plugin";
      private static final String FILTER = "filter";
      private static final String FRAGMENT = "fragment";
      private static final String UNPACK = "unpack";
      private static final String VERSION = "version";
      private static final String ID = "id";
      private EclipsePlugin plugin;

      public PluginHandler() {
         super("plugin");
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         this.plugin = new EclipsePlugin();
         String id = atts.getValue("id");
         String version = atts.getValue("version");
         this.plugin.setId(id);
         this.plugin.setVersion(new Version(version));
         this.plugin.setUnpack(Boolean.valueOf(atts.getValue("unpack")));
         this.plugin.setFragment(atts.getValue("fragment"));
         this.plugin.setFilter(atts.getValue("filter"));
      }
   }

   private static class DescriptionHandler extends DelegatingHandler {
      private static final String DESCRIPTION = "description";

      public DescriptionHandler() {
         super("description");
         this.setBufferingChar(true);
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
      }
   }

   private static class LicenseHandler extends DelegatingHandler {
      private static final String LICENSE = "license";

      public LicenseHandler() {
         super("license");
         this.setBufferingChar(true);
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
      }
   }

   private static class CopyrightHandler extends DelegatingHandler {
      private static final String COPYRIGHT = "copyright";

      public CopyrightHandler() {
         super("copyright");
         this.setBufferingChar(true);
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
      }
   }

   static class RequiresHandler extends DelegatingHandler {
      private static final String REQUIRES = "requires";
      List requires = new ArrayList();

      public RequiresHandler() {
         super("requires");
         this.addChild(new ImportHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ImportHandler child) {
               RequiresHandler.this.requires.add(child.require);
            }
         });
      }
   }

   private static class ImportHandler extends DelegatingHandler {
      Require require;
      private static final String IMPORT = "import";
      private static final String FILTER = "filter";
      private static final String MATCH = "match";
      private static final String VERSION = "version";
      private static final String PLUGIN = "plugin";
      private static final String FEATURE = "feature";

      public ImportHandler() {
         super("import");
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         this.require = new Require();
         String version = atts.getValue("version");
         this.require.setFeature(atts.getValue("feature"));
         this.require.setPlugin(atts.getValue("plugin"));
         this.require.setVersion(new Version(version));
         this.require.setMatch(atts.getValue("match"));
         this.require.setFilter(atts.getValue("filter"));
      }
   }
}
