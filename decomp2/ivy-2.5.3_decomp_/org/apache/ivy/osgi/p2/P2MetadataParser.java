package org.apache.ivy.osgi.p2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.osgi.core.BundleCapability;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.BundleRequirement;
import org.apache.ivy.osgi.core.ExportPackage;
import org.apache.ivy.osgi.core.ManifestParser;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.osgi.util.VersionRange;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;

public class P2MetadataParser implements XMLInputParser {
   private final P2Descriptor p2Descriptor;
   private int logLevel = 2;

   public P2MetadataParser(P2Descriptor p2Descriptor) {
      this.p2Descriptor = p2Descriptor;
   }

   public void setLogLevel(int logLevel) {
      this.logLevel = logLevel;
   }

   public void parse(InputStream in) throws IOException, ParseException, SAXException {
      RepositoryHandler handler = new RepositoryHandler(this.p2Descriptor);

      try {
         XMLHelper.parse((InputStream)in, (URL)null, handler, (LexicalHandler)null);
      } catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }
   }

   private static String namespace2Type(String namespace) {
      if (namespace == null) {
         return null;
      } else if (namespace.equals("java.package")) {
         return "package";
      } else {
         return namespace.equals("osgi.bundle") ? "bundle" : null;
      }
   }

   private class RepositoryHandler extends DelegatingHandler {
      private static final String REPOSITORY = "repository";

      public RepositoryHandler(final P2Descriptor p2Descriptor) {
         super("repository");
         this.addChild(P2MetadataParser.this.new UnitsHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(UnitsHandler child) {
               for(BundleInfo bundle : child.bundles) {
                  p2Descriptor.addBundle(bundle);
               }

            }
         });
         this.addChild(P2MetadataParser.this.new ReferencesHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ReferencesHandler child) {
            }
         });
      }
   }

   private class ReferencesHandler extends DelegatingHandler {
      private static final String REFERENCES = "references";
      private static final String SIZE = "size";
      List repositoryUris;

      public ReferencesHandler() {
         super("references");
         this.addChild(P2MetadataParser.this.new RepositoryReferenceHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(RepositoryReferenceHandler child) {
               ReferencesHandler.this.repositoryUris.add(child.uri);
            }
         });
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         int size = Integer.parseInt(atts.getValue("size"));
         this.repositoryUris = new ArrayList(size);
      }
   }

   private class RepositoryReferenceHandler extends DelegatingHandler {
      private static final String REPOSITORY = "repository";
      private static final String URI = "uri";
      private static final String URL = "url";
      URI uri;

      public RepositoryReferenceHandler() {
         super("repository");
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String uriAtt = atts.getValue("uri");
         String urlAtt = atts.getValue("url");
         if (uriAtt != null) {
            try {
               this.uri = new URI(uriAtt);
            } catch (URISyntaxException e) {
               throw new SAXParseException("Invalid uri attribute " + uriAtt + "(" + e.getMessage() + ")", this.getLocator());
            }
         }

         if (this.uri != null && urlAtt != null) {
            try {
               this.uri = new URI(urlAtt);
            } catch (URISyntaxException e) {
               throw new SAXParseException("Invalid url attribute " + urlAtt + "(" + e.getMessage() + ")", this.getLocator());
            }
         }

      }
   }

   private class UnitsHandler extends DelegatingHandler {
      private static final String UNITS = "units";
      private static final String SIZE = "size";
      List bundles;

      public UnitsHandler() {
         super("units");
         this.addChild(P2MetadataParser.this.new UnitHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(UnitHandler child) {
               if (child.bundleInfo != null && !child.bundleInfo.getCapabilities().isEmpty()) {
                  UnitsHandler.this.bundles.add(child.bundleInfo);
               }

            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         int size = Integer.parseInt(atts.getValue("size"));
         this.bundles = new ArrayList(size);
      }
   }

   class UnitHandler extends DelegatingHandler {
      private static final String CATEGORY_PROPERTY = "org.eclipse.equinox.p2.type.category";
      private static final String UNIT = "unit";
      private static final String ID = "id";
      private static final String VERSION = "version";
      BundleInfo bundleInfo;

      public UnitHandler() {
         super("unit");
         this.addChild(new PropertiesParser.PropertiesHandler(new String[]{"org.eclipse.equinox.p2.type.category"}), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(PropertiesParser.PropertiesHandler child) {
               String category = (String)child.properties.get("org.eclipse.equinox.p2.type.category");
               if (category != null && Boolean.valueOf(category)) {
                  child.getParent().skip();
                  UnitHandler.this.bundleInfo = null;
               }

            }
         });
         this.addChild(P2MetadataParser.this.new ProvidesHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ProvidesHandler child) {
               if ("source".equals(child.eclipseType)) {
                  UnitHandler.this.bundleInfo.setSource(true);
                  String symbolicName = UnitHandler.this.bundleInfo.getSymbolicName();
                  if (symbolicName.endsWith(".source")) {
                     UnitHandler.this.bundleInfo.setSymbolicNameTarget(symbolicName.substring(0, symbolicName.length() - 7));
                     UnitHandler.this.bundleInfo.setVersionTarget(UnitHandler.this.bundleInfo.getVersion());
                  }
               }

               for(BundleCapability capability : child.capabilities) {
                  UnitHandler.this.bundleInfo.addCapability(capability);
               }

            }
         });
         this.addChild(new FilterHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(FilterHandler child) {
            }
         });
         this.addChild(P2MetadataParser.this.new RequiresHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(RequiresHandler child) {
               for(BundleRequirement requirement : child.requirements) {
                  UnitHandler.this.bundleInfo.addRequirement(requirement);
               }

            }
         });
         this.addChild(P2MetadataParser.this.new HostRequirementsHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(HostRequirementsHandler child) {
            }
         });
         this.addChild(P2MetadataParser.this.new MetaRequirementsHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(MetaRequirementsHandler child) {
            }
         });
         this.addChild(P2MetadataParser.this.new ArtifactsHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ArtifactsHandler child) {
            }
         });
         this.addChild(P2MetadataParser.this.new TouchpointDataHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(TouchpointDataHandler child) throws SAXParseException {
               if (child.zipped != null) {
                  UnitHandler.this.bundleInfo.setHasInnerClasspath(child.zipped);
               }

               if (UnitHandler.this.bundleInfo.isSource()) {
                  if (child.manifest != null) {
                     String manifest = ManifestParser.formatLines(child.manifest.trim());

                     BundleInfo embeddedInfo;
                     try {
                        embeddedInfo = ManifestParser.parseManifest(manifest);
                     } catch (IOException e) {
                        if (P2MetadataParser.this.logLevel >= 3) {
                           Message.verbose("The Manifest of the source bundle " + UnitHandler.this.bundleInfo.getSymbolicName() + " could not be parsed", e);
                        }

                        return;
                     } catch (ParseException e) {
                        if (P2MetadataParser.this.logLevel >= 3) {
                           Message.verbose("The Manifest of the source bundle " + UnitHandler.this.bundleInfo.getSymbolicName() + " is ill formed", e);
                        }

                        return;
                     }

                     if (!embeddedInfo.isSource()) {
                        if (P2MetadataParser.this.logLevel >= 3) {
                           Message.verbose("The Manifest of the source bundle " + UnitHandler.this.bundleInfo.getSymbolicName() + " is not declaring being a source.");
                        }

                        return;
                     }

                     String symbolicNameTarget = embeddedInfo.getSymbolicNameTarget();
                     if (symbolicNameTarget == null) {
                        if (P2MetadataParser.this.logLevel >= 3) {
                           Message.verbose("The Manifest of the source bundle " + UnitHandler.this.bundleInfo.getSymbolicName() + " is not declaring a target symbolic name.");
                        }

                        return;
                     }

                     Version versionTarget = embeddedInfo.getVersionTarget();
                     if (versionTarget == null) {
                        if (P2MetadataParser.this.logLevel >= 3) {
                           Message.verbose("The Manifest of the source bundle " + UnitHandler.this.bundleInfo.getSymbolicName() + " is not declaring a target version.");
                        }

                        return;
                     }

                     UnitHandler.this.bundleInfo.setSymbolicNameTarget(symbolicNameTarget);
                     UnitHandler.this.bundleInfo.setVersionTarget(versionTarget);
                  }

               }
            }
         });
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String id = atts.getValue("id");
         String version = atts.getValue("version");
         this.bundleInfo = new BundleInfo(id, new Version(version));
      }
   }

   private static class FilterHandler extends DelegatingHandler {
      private static final String FILTER = "filter";

      public FilterHandler() {
         super("filter");
         this.setBufferingChar(true);
      }
   }

   private class ProvidesHandler extends DelegatingHandler {
      private static final String PROVIDES = "provides";
      private static final String SIZE = "size";
      List capabilities;
      String eclipseType;

      public ProvidesHandler() {
         super("provides");
         this.addChild(new ProvidedHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ProvidedHandler child) {
               if (child.namespace.equals("org.eclipse.equinox.p2.eclipse.type")) {
                  ProvidesHandler.this.eclipseType = child.name;
               } else {
                  String type = P2MetadataParser.namespace2Type(child.namespace);
                  if (type == null) {
                     if (P2MetadataParser.this.logLevel >= 4) {
                        Message.debug("Unsupported provided capability " + child.namespace + " " + child.name + " " + child.version);
                     }

                     return;
                  }

                  BundleCapability capability;
                  if ("package".equals(type)) {
                     capability = new ExportPackage(child.name, child.version);
                  } else {
                     capability = new BundleCapability(type, child.name, child.version);
                  }

                  ProvidesHandler.this.capabilities.add(capability);
               }

            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         int size = Integer.parseInt(atts.getValue("size"));
         this.capabilities = new ArrayList(size);
      }
   }

   private static class ProvidedHandler extends DelegatingHandler {
      private static final String PROVIDED = "provided";
      private static final String NAMESPACE = "namespace";
      private static final String NAME = "name";
      private static final String VERSION = "version";
      String namespace;
      String name;
      Version version;

      public ProvidedHandler() {
         super("provided");
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         this.namespace = atts.getValue("namespace");
         this.name = atts.getValue("name");
         this.version = new Version(atts.getValue("version"));
      }
   }

   abstract class AbstractRequirementHandler extends DelegatingHandler {
      private static final String SIZE = "size";
      List requirements;

      public AbstractRequirementHandler(String name) {
         super(name);
         this.addChild(P2MetadataParser.this.new RequiredHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(RequiredHandler child) {
               String name = child.name;
               VersionRange range = child.range;
               String type = P2MetadataParser.namespace2Type(child.namespace);
               if (type == null) {
                  if (P2MetadataParser.this.logLevel >= 4) {
                     Message.debug("Unsupported required capability " + child.namespace + " " + name + " " + range);
                  }
               } else {
                  String resolution = child.optional ? "optional" : null;
                  AbstractRequirementHandler.this.requirements.add(new BundleRequirement(type, name, range, resolution));
               }

            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         int size = Integer.parseInt(atts.getValue("size"));
         this.requirements = new ArrayList(size);
      }
   }

   private class RequiresHandler extends AbstractRequirementHandler {
      private static final String REQUIRES = "requires";

      public RequiresHandler() {
         super("requires");
      }
   }

   private class RequiredHandler extends DelegatingHandler {
      private static final String REQUIRED = "required";
      private static final String NAMESPACE = "namespace";
      private static final String NAME = "name";
      private static final String RANGE = "range";
      private static final String OPTIONAL = "optional";
      String namespace;
      String name;
      VersionRange range;
      boolean optional;

      public RequiredHandler() {
         super("required");
      }

      protected void handleAttributes(Attributes atts) throws SAXParseException {
         this.namespace = atts.getValue("namespace");
         this.name = atts.getValue("name");

         try {
            this.range = new VersionRange(atts.getValue("range"));
         } catch (ParseException e) {
            throw new RuntimeException(e);
         }

         this.optional = this.getOptionalBooleanAttribute(atts, "optional", Boolean.FALSE);
      }
   }

   private class HostRequirementsHandler extends AbstractRequirementHandler {
      private static final String HOST_REQUIREMENTS = "hostRequirements";

      public HostRequirementsHandler() {
         super("hostRequirements");
      }
   }

   private class MetaRequirementsHandler extends AbstractRequirementHandler {
      private static final String META_REQUIREMENTS = "metaRequirements";

      public MetaRequirementsHandler() {
         super("metaRequirements");
      }
   }

   private class ArtifactsHandler extends DelegatingHandler {
      private static final String ARTIFACTS = "artifacts";
      private static final String SIZE = "size";
      List artifacts;

      public ArtifactsHandler() {
         super("artifacts");
         this.addChild(P2MetadataParser.this.new ArtifactHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ArtifactHandler child) {
               ArtifactsHandler.this.artifacts.add(child.artifact);
            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         int size = Integer.parseInt(atts.getValue("size"));
         this.artifacts = new ArrayList(size);
      }
   }

   private class ArtifactHandler extends DelegatingHandler {
      private static final String ARTIFACT = "artifact";
      private static final String ID = "id";
      private static final String VERSION = "version";
      private static final String CLASSIFIER = "classifier";
      P2Artifact artifact;

      public ArtifactHandler() {
         super("artifact");
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String id = atts.getValue("id");
         String version = atts.getValue("version");
         String classifier = atts.getValue("classifier");
         this.artifact = new P2Artifact(id, new Version(version), classifier);
      }
   }

   private class TouchpointDataHandler extends DelegatingHandler {
      private static final String TOUCHPOINTDATA = "touchpointData";
      String manifest;
      Boolean zipped;

      public TouchpointDataHandler() {
         super("touchpointData");
         this.addChild(P2MetadataParser.this.new InstructionsHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(InstructionsHandler child) {
               TouchpointDataHandler.this.manifest = child.manifest;
               TouchpointDataHandler.this.zipped = child.zipped;
            }
         });
      }

      protected void handleAttributes(Attributes atts) {
      }
   }

   private class InstructionsHandler extends DelegatingHandler {
      private static final String INSTRUCTIONS = "instructions";
      String manifest;
      Boolean zipped;

      public InstructionsHandler() {
         super("instructions");
         this.addChild(P2MetadataParser.this.new InstructionHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(InstructionHandler child) {
               InstructionsHandler.this.manifest = null;
               InstructionsHandler.this.zipped = null;
               String buffer = child.getBufferedChars().trim();
               if ("manifest".equals(child.key)) {
                  InstructionsHandler.this.manifest = buffer;
               } else if ("zipped".equals(child.key) && buffer.length() != 0) {
                  InstructionsHandler.this.zipped = Boolean.valueOf(buffer);
               }

            }
         });
      }

      protected void handleAttributes(Attributes atts) {
      }
   }

   private class InstructionHandler extends DelegatingHandler {
      private static final String INSTRUCTION = "instruction";
      private static final String KEY = "key";
      String key;

      public InstructionHandler() {
         super("instruction");
         this.setBufferingChar(true);
      }

      protected void handleAttributes(Attributes atts) {
         this.key = atts.getValue("key");
      }
   }
}
