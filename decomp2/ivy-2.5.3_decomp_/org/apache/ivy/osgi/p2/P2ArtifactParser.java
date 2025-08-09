package org.apache.ivy.osgi.p2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.osgi.filter.OSGiFilter;
import org.apache.ivy.osgi.filter.OSGiFilterParser;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;

public class P2ArtifactParser implements XMLInputParser {
   private final P2Descriptor p2Descriptor;
   private final String repoUrl;

   public P2ArtifactParser(P2Descriptor p2Descriptor, String repoUrl) {
      this.p2Descriptor = p2Descriptor;
      this.repoUrl = repoUrl;
   }

   public void parse(InputStream in) throws IOException, ParseException, SAXException {
      RepositoryHandler handler = new RepositoryHandler(this.p2Descriptor, this.repoUrl);

      try {
         XMLHelper.parse((InputStream)in, (URL)null, handler, (LexicalHandler)null);
      } catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }
   }

   private static class RepositoryHandler extends DelegatingHandler {
      private static final String REPOSITORY = "repository";
      private Map artifactPatterns = new LinkedHashMap();

      public RepositoryHandler(P2Descriptor p2Descriptor, String repoUrl) {
         super("repository");
         this.addChild(new MappingsHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(MappingsHandler child) {
               for(Map.Entry entry : child.outputByFilter.entrySet()) {
                  OSGiFilter filter;
                  try {
                     filter = OSGiFilterParser.parse((String)entry.getKey());
                  } catch (ParseException var6) {
                     throw new IllegalStateException();
                  }

                  RepositoryHandler.this.artifactPatterns.put(filter, entry.getValue());
               }

            }
         });
         this.addChild(new ArtifactsHandler(p2Descriptor, this.artifactPatterns, repoUrl), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ArtifactsHandler child) {
            }
         });
      }
   }

   private static class MappingsHandler extends DelegatingHandler {
      private static final String MAPPINGS = "mappings";
      private static final String SIZE = "size";
      Map outputByFilter;

      public MappingsHandler() {
         super("mappings");
         this.addChild(new RuleHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(RuleHandler child) {
               MappingsHandler.this.outputByFilter.put(child.filter, child.output);
            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         int size = Integer.parseInt(atts.getValue("size"));
         this.outputByFilter = new LinkedHashMap(size);
      }
   }

   private static class RuleHandler extends DelegatingHandler {
      private static final String RULE = "rule";
      private static final String FILTER = "filter";
      private static final String OUTPUT = "output";
      private String filter;
      private String output;

      public RuleHandler() {
         super("rule");
      }

      protected void handleAttributes(Attributes atts) {
         this.filter = atts.getValue("filter");
         this.output = atts.getValue("output");
      }
   }

   private static class ArtifactsHandler extends DelegatingHandler {
      private static final String ARTIFACTS = "artifacts";

      public ArtifactsHandler(final P2Descriptor p2Descriptor, final Map artifactPatterns, final String repoUrl) {
         super("artifacts");
         this.addChild(new ArtifactHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ArtifactHandler child) throws SAXParseException {
               String url = this.getPattern(child.p2Artifact, child.properties);
               if (url != null) {
                  url = url.replaceAll("\\$\\{repoUrl\\}", repoUrl);
                  url = url.replaceAll("\\$\\{id\\}", child.p2Artifact.getId());
                  url = url.replaceAll("\\$\\{version\\}", child.p2Artifact.getVersion().toString());

                  URI uri;
                  try {
                     uri = (new URL(url)).toURI();
                  } catch (URISyntaxException | MalformedURLException e) {
                     throw new SAXParseException("Incorrect artifact url '" + url + "' (" + ((Exception)e).getMessage() + ")", ArtifactsHandler.this.getLocator(), e);
                  }

                  p2Descriptor.addArtifactUrl(child.p2Artifact.getClassifier(), child.p2Artifact.getId(), child.p2Artifact.getVersion(), uri, (String)child.properties.get("format"));
               }

            }

            private String getPattern(P2Artifact p2Artifact, Map properties) {
               Map<String, String> props = new HashMap(properties);
               props.put("classifier", p2Artifact.getClassifier());

               for(Map.Entry pattern : artifactPatterns.entrySet()) {
                  if (((OSGiFilter)pattern.getKey()).eval(props)) {
                     return (String)pattern.getValue();
                  }
               }

               return null;
            }
         });
      }
   }

   private static class ArtifactHandler extends DelegatingHandler {
      private static final String ARTIFACT = "artifact";
      private static final String CLASSIFIER = "classifier";
      private static final String ID = "id";
      private static final String VERSION = "version";
      private P2Artifact p2Artifact;
      private Map properties;

      public ArtifactHandler() {
         super("artifact");
         this.addChild(new PropertiesParser.PropertiesHandler(new String[0]), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(PropertiesParser.PropertiesHandler child) {
               ArtifactHandler.this.properties = child.properties;
            }
         });
      }

      protected void handleAttributes(Attributes atts) throws SAXException {
         String id = atts.getValue("id");
         Version version = new Version(atts.getValue("version"));
         String classifier = atts.getValue("classifier");
         this.p2Artifact = new P2Artifact(id, version, classifier);
      }
   }
}
