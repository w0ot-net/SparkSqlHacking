package org.apache.ivy.osgi.obr.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.osgi.core.BundleArtifact;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.filter.OSGiFilter;
import org.apache.ivy.osgi.filter.OSGiFilterParser;
import org.apache.ivy.osgi.repo.BundleRepoDescriptor;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;

public class OBRXMLParser {
   public static BundleRepoDescriptor parse(URI baseUri, InputStream in) throws IOException, SAXException {
      RepositoryHandler handler = new RepositoryHandler(baseUri);

      try {
         XMLHelper.parse((InputStream)in, (URL)null, handler, (LexicalHandler)null);
      } catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }

      return handler.repo;
   }

   static class RepositoryHandler extends DelegatingHandler {
      static final String REPOSITORY = "repository";
      static final String LASTMODIFIED = "lastmodified";
      static final String NAME = "name";
      BundleRepoDescriptor repo;
      private final URI baseUri;

      public RepositoryHandler(URI baseUri) {
         super("repository");
         this.baseUri = baseUri;
         this.addChild(new ResourceHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ResourceHandler child) {
               RepositoryHandler.this.repo.addBundle(child.bundleInfo);
            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         this.repo = new BundleRepoDescriptor(this.baseUri, ExecutionEnvironmentProfileProvider.getInstance());
         this.repo.setName(atts.getValue("name"));
         this.repo.setLastModified(atts.getValue("lastmodified"));
      }
   }

   static class ResourceHandler extends DelegatingHandler {
      private static final String DEFAULT_VERSION = "1.0.0";
      static final String RESOURCE = "resource";
      static final String ID = "id";
      static final String PRESENTATION_NAME = "presentationname";
      static final String SYMBOLIC_NAME = "symbolicname";
      static final String URI = "uri";
      static final String VERSION = "version";
      BundleInfo bundleInfo;

      public ResourceHandler() {
         super("resource");
         this.setSkipOnError(true);
         this.addChild(new ResourceSourceHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ResourceSourceHandler child) {
               String uri = child.getBufferedChars().trim();
               if (!uri.endsWith(".jar")) {
                  ResourceHandler.this.log(1, "A source uri is suspect, it is not ending with .jar, it is probably a pointer to a download page. Ignoring it.");
               } else {
                  try {
                     ResourceHandler.this.bundleInfo.addArtifact(new BundleArtifact(true, new URI(uri), (String)null));
                  } catch (URISyntaxException var4) {
                     ResourceHandler.this.log(1, "Incorrect uri " + uri + ". The source of " + ResourceHandler.this.bundleInfo.getSymbolicName() + " is then ignored.");
                  }
               }
            }
         });
         this.addChild(new ResourceDescriptionHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ResourceDescriptionHandler child) {
               ResourceHandler.this.bundleInfo.setDescription(child.getBufferedChars().trim());
            }
         });
         this.addChild(new ResourceDocumentationHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ResourceDocumentationHandler child) {
               ResourceHandler.this.bundleInfo.setDocumentation(child.getBufferedChars().trim());
            }
         });
         this.addChild(new ResourceLicenseHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ResourceLicenseHandler child) {
               ResourceHandler.this.bundleInfo.setLicense(child.getBufferedChars().trim());
            }
         });
         this.addChild(new ResourceSizeHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ResourceSizeHandler child) {
               String size = child.getBufferedChars().trim();

               try {
                  ResourceHandler.this.bundleInfo.setSize(Integer.valueOf(size));
               } catch (NumberFormatException var4) {
                  ResourceHandler.this.log(1, "Invalid size for the bundle " + ResourceHandler.this.bundleInfo.getSymbolicName() + ": " + size + ". This size is then ignored.");
               }

            }
         });
         this.addChild(new CapabilityHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(CapabilityHandler child) throws SAXParseException {
               try {
                  CapabilityAdapter.adapt(ResourceHandler.this.bundleInfo, child.capability);
               } catch (ParseException e) {
                  throw new SAXParseException("Invalid capability: " + e.getMessage(), child.getLocator());
               }
            }
         });
         this.addChild(new RequireHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(RequireHandler child) throws SAXParseException {
               try {
                  RequirementAdapter.adapt(ResourceHandler.this.bundleInfo, child.requirement);
               } catch (UnsupportedFilterException e) {
                  throw new SAXParseException("Unsupported requirement filter: " + child.filter + " (" + e.getMessage() + ")", ResourceHandler.this.getLocator());
               } catch (ParseException e) {
                  throw new SAXParseException("Error in the requirement filter on the bundle: " + e.getMessage(), ResourceHandler.this.getLocator());
               }
            }
         });
         this.addChild(new ExtendHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ExtendHandler child) throws SAXParseException {
            }
         });
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String symbolicname = atts.getValue("symbolicname");
         if (symbolicname == null) {
            this.log(0, "Resource with no symbolic name, skipping it.");
            this.skip();
         } else {
            String v = this.getOptionalAttribute(atts, "version", "1.0.0");
            Version version = new Version(v);
            this.bundleInfo = new BundleInfo(symbolicname, version);
            this.bundleInfo.setPresentationName(atts.getValue("presentationname"));
            String uri = atts.getValue("uri");
            if (uri != null) {
               try {
                  this.bundleInfo.addArtifact(new BundleArtifact(false, new URI(uri), (String)null));
               } catch (URISyntaxException var7) {
                  this.log(0, "Incorrect uri " + uri + ". The resource " + symbolicname + " is then ignored.");
                  this.skip();
                  return;
               }
            }

            this.bundleInfo.setId(atts.getValue("id"));
         }
      }

      protected String getCurrentElementIdentifier() {
         return this.bundleInfo.getSymbolicName() + "/" + this.bundleInfo.getVersion();
      }
   }

   static class ResourceSourceHandler extends DelegatingHandler {
      static final String SOURCE = "source";

      public ResourceSourceHandler() {
         super("source");
         this.setBufferingChar(true);
      }
   }

   static class ResourceDescriptionHandler extends DelegatingHandler {
      static final String DESCRIPTION = "description";

      public ResourceDescriptionHandler() {
         super("description");
         this.setBufferingChar(true);
      }
   }

   static class ResourceDocumentationHandler extends DelegatingHandler {
      static final String DOCUMENTATION = "documentation";

      public ResourceDocumentationHandler() {
         super("documentation");
         this.setBufferingChar(true);
      }
   }

   static class ResourceLicenseHandler extends DelegatingHandler {
      static final String LICENSE = "license";

      public ResourceLicenseHandler() {
         super("license");
         this.setBufferingChar(true);
      }
   }

   static class ResourceSizeHandler extends DelegatingHandler {
      static final String SIZE = "size";

      public ResourceSizeHandler() {
         super("size");
         this.setBufferingChar(true);
      }
   }

   static class CapabilityHandler extends DelegatingHandler {
      static final String CAPABILITY = "capability";
      static final String NAME = "name";
      Capability capability;

      public CapabilityHandler() {
         super("capability");
         this.addChild(new CapabilityPropertyHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(CapabilityPropertyHandler child) {
               String name = child.name;
               String value = child.value;
               String type = child.type;
               CapabilityHandler.this.capability.addProperty(name, value, type);
            }
         });
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String name = this.getRequiredAttribute(atts, "name");
         this.capability = new Capability(name);
      }
   }

   static class CapabilityPropertyHandler extends DelegatingHandler {
      static final String CAPABILITY_PROPERTY = "p";
      static final String NAME = "n";
      static final String VALUE = "v";
      static final String TYPE = "t";
      String name;
      String value;
      String type;

      public CapabilityPropertyHandler() {
         super("p");
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         this.name = this.getRequiredAttribute(atts, "n");
         this.value = this.getRequiredAttribute(atts, "v");
         this.type = atts.getValue("t");
      }
   }

   static class AbstractRequirementHandler extends DelegatingHandler {
      static final String NAME = "name";
      static final String OPTIONAL = "optional";
      static final String MULTIPLE = "multiple";
      static final String FILTER = "filter";
      Requirement requirement;
      OSGiFilter filter;

      public AbstractRequirementHandler(String name) {
         super(name);
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String name = this.getRequiredAttribute(atts, "name");
         String filterText = atts.getValue("filter");
         this.filter = null;
         if (filterText != null) {
            try {
               this.filter = OSGiFilterParser.parse(filterText);
            } catch (ParseException var6) {
               throw new SAXParseException("Requirement with ill-formed filter: " + filterText, this.getLocator());
            }
         }

         Boolean optional = this.getOptionalBooleanAttribute(atts, "optional", (Boolean)null);
         Boolean multiple = this.getOptionalBooleanAttribute(atts, "multiple", (Boolean)null);
         this.requirement = new Requirement(name, this.filter);
         if (optional != null) {
            this.requirement.setOptional(optional);
         }

         if (multiple != null) {
            this.requirement.setMultiple(multiple);
         }

      }
   }

   static class RequireHandler extends AbstractRequirementHandler {
      static final String REQUIRE = "require";

      public RequireHandler() {
         super("require");
      }
   }

   static class ExtendHandler extends AbstractRequirementHandler {
      static final String EXTEND = "extend";

      public ExtendHandler() {
         super("extend");
      }
   }
}
