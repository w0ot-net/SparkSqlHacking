package org.apache.ivy.osgi.updatesite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.ivy.core.cache.CacheResourceOptions;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.osgi.core.ExecutionEnvironmentProfileProvider;
import org.apache.ivy.osgi.p2.P2ArtifactParser;
import org.apache.ivy.osgi.p2.P2CompositeParser;
import org.apache.ivy.osgi.p2.P2Descriptor;
import org.apache.ivy.osgi.p2.P2MetadataParser;
import org.apache.ivy.osgi.p2.XMLInputParser;
import org.apache.ivy.osgi.repo.RepoDescriptor;
import org.apache.ivy.osgi.updatesite.xml.EclipseFeature;
import org.apache.ivy.osgi.updatesite.xml.EclipseUpdateSiteParser;
import org.apache.ivy.osgi.updatesite.xml.FeatureParser;
import org.apache.ivy.osgi.updatesite.xml.UpdateSite;
import org.apache.ivy.osgi.updatesite.xml.UpdateSiteDigestParser;
import org.apache.ivy.plugins.repository.url.URLRepository;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.util.Message;
import org.xml.sax.SAXException;

public class UpdateSiteLoader {
   private final RepositoryCacheManager repositoryCacheManager;
   private final URLRepository urlRepository = new URLRepository();
   private final CacheResourceOptions options;
   private final TimeoutConstraint timeoutConstraint;
   private int logLevel = 2;

   public UpdateSiteLoader(RepositoryCacheManager repositoryCacheManager, EventManager eventManager, CacheResourceOptions options, TimeoutConstraint timeoutConstraint) {
      this.repositoryCacheManager = repositoryCacheManager;
      this.options = options;
      this.timeoutConstraint = timeoutConstraint;
      if (eventManager != null) {
         this.urlRepository.addTransferListener(eventManager);
      }

   }

   public void setLogLevel(int logLevel) {
      this.logLevel = logLevel;
   }

   public RepoDescriptor load(URI repoUri) throws IOException, ParseException, SAXException {
      if (!repoUri.toString().endsWith("/")) {
         try {
            repoUri = new URI(repoUri.toString() + "/");
         } catch (URISyntaxException var4) {
            throw new RuntimeException("Cannot make an uri for the repo");
         }
      }

      Message.info("Loading the update site " + repoUri);
      RepoDescriptor repo = this.loadP2(repoUri);
      if (repo != null) {
         return repo;
      } else {
         Message.verbose("\tNo P2 artifacts, falling back on the old fashioned updatesite");
         UpdateSite site = this.loadSite(repoUri);
         if (site == null) {
            return null;
         } else {
            repo = this.loadFromDigest(site);
            return (RepoDescriptor)(repo != null ? repo : this.loadFromSite(site));
         }
      }
   }

   private P2Descriptor loadP2(URI repoUri) throws IOException, ParseException, SAXException {
      P2Descriptor p2Descriptor = new P2Descriptor(repoUri, ExecutionEnvironmentProfileProvider.getInstance());
      p2Descriptor.setLogLevel(this.logLevel);
      if (!this.populateP2Descriptor(repoUri, p2Descriptor)) {
         return null;
      } else {
         p2Descriptor.finish();
         return p2Descriptor;
      }
   }

   private boolean populateP2Descriptor(URI repoUri, P2Descriptor p2Descriptor) throws IOException, ParseException, SAXException {
      Message.verbose("Loading P2 repository " + repoUri);
      boolean contentExists = this.readContent(repoUri, p2Descriptor);
      boolean artifactExists = this.readArtifacts(repoUri, p2Descriptor);
      return artifactExists || contentExists;
   }

   private boolean readContent(URI repoUri, P2Descriptor p2Descriptor) throws IOException, ParseException, SAXException {
      boolean contentExists = this.readCompositeContent(repoUri, "compositeContent", p2Descriptor);
      if (!contentExists) {
         P2MetadataParser metadataParser = new P2MetadataParser(p2Descriptor);
         metadataParser.setLogLevel(this.logLevel);
         contentExists = this.readJarOrXml(repoUri, "content", metadataParser);
      }

      return contentExists;
   }

   private boolean readArtifacts(URI repoUri, P2Descriptor p2Descriptor) throws IOException, ParseException, SAXException {
      boolean artifactExists = this.readCompositeArtifact(repoUri, "compositeArtifacts", p2Descriptor);
      if (!artifactExists) {
         artifactExists = this.readJarOrXml(repoUri, "artifacts", new P2ArtifactParser(p2Descriptor, repoUri.toURL().toExternalForm()));
      }

      return artifactExists;
   }

   private boolean readCompositeContent(URI repoUri, String name, P2Descriptor p2Descriptor) throws IOException, ParseException, SAXException {
      P2CompositeParser p2CompositeParser = new P2CompositeParser();
      boolean exist = this.readJarOrXml(repoUri, name, p2CompositeParser);
      if (exist) {
         for(String childLocation : p2CompositeParser.getChildLocations()) {
            if (!childLocation.endsWith("/")) {
               childLocation = childLocation + "/";
            }

            URI childUri = repoUri.resolve(childLocation);
            this.readContent(childUri, p2Descriptor);
         }
      }

      return exist;
   }

   private boolean readCompositeArtifact(URI repoUri, String name, P2Descriptor p2Descriptor) throws IOException, ParseException, SAXException {
      P2CompositeParser p2CompositeParser = new P2CompositeParser();
      boolean exist = this.readJarOrXml(repoUri, name, p2CompositeParser);
      if (exist) {
         for(String childLocation : p2CompositeParser.getChildLocations()) {
            if (!childLocation.endsWith("/")) {
               childLocation = childLocation + "/";
            }

            URI childUri = repoUri.resolve(childLocation);
            this.readArtifacts(childUri, p2Descriptor);
         }
      }

      return exist;
   }

   private boolean readJarOrXml(URI repoUri, String baseName, XMLInputParser reader) throws IOException, ParseException, SAXException {
      InputStream readIn = null;
      URL contentUrl = repoUri.resolve(baseName + ".jar").toURL();
      URLResource res = new URLResource(contentUrl, this.timeoutConstraint);
      ArtifactDownloadReport report = this.repositoryCacheManager.downloadRepositoryResource(res, baseName, baseName, "jar", this.options, this.urlRepository);
      if (report.getDownloadStatus() == DownloadStatus.FAILED) {
         contentUrl = repoUri.resolve(baseName + ".xml").toURL();
         res = new URLResource(contentUrl, this.timeoutConstraint);
         report = this.repositoryCacheManager.downloadRepositoryResource(res, baseName, baseName, "xml", this.options, this.urlRepository);
         if (report.getDownloadStatus() == DownloadStatus.FAILED) {
            return false;
         }

         readIn = new FileInputStream(report.getLocalFile());
      } else {
         InputStream in = new FileInputStream(report.getLocalFile());

         try {
            readIn = this.findEntry(in, baseName + ".xml");
            if (readIn == null) {
               in.close();
               return false;
            }
         } catch (IOException e) {
            in.close();
            throw e;
         }
      }

      try {
         reader.parse(readIn);
      } finally {
         readIn.close();
      }

      return true;
   }

   private UpdateSite loadSite(URI repoUri) throws IOException, SAXException {
      URI siteUri = this.normalizeSiteUri(repoUri, (URI)null);
      URL u = siteUri.resolve("site.xml").toURL();
      URLResource res = new URLResource(u, this.timeoutConstraint);
      ArtifactDownloadReport report = this.repositoryCacheManager.downloadRepositoryResource(res, "site", "updatesite", "xml", this.options, this.urlRepository);
      if (report.getDownloadStatus() == DownloadStatus.FAILED) {
         return null;
      } else {
         InputStream in = new FileInputStream(report.getLocalFile());
         Throwable var7 = null;

         UpdateSite var9;
         try {
            UpdateSite site = EclipseUpdateSiteParser.parse(in);
            site.setUri(this.normalizeSiteUri(site.getUri(), siteUri));
            var9 = site;
         } catch (Throwable var18) {
            var7 = var18;
            throw var18;
         } finally {
            if (in != null) {
               if (var7 != null) {
                  try {
                     in.close();
                  } catch (Throwable var17) {
                     var7.addSuppressed(var17);
                  }
               } else {
                  in.close();
               }
            }

         }

         return var9;
      }
   }

   private URI normalizeSiteUri(URI uri, URI defaultValue) {
      if (uri == null) {
         return defaultValue;
      } else {
         String uriString = uri.toString();
         if (uriString.endsWith("site.xml")) {
            try {
               return new URI(uriString.substring(0, uriString.length() - 8));
            } catch (URISyntaxException e) {
               throw new RuntimeException("Illegal uri", e);
            }
         } else if (!uriString.endsWith("/")) {
            try {
               return new URI(uriString + "/");
            } catch (URISyntaxException e) {
               throw new RuntimeException("Illegal uri", e);
            }
         } else {
            return uri;
         }
      }
   }

   private UpdateSiteDescriptor loadFromDigest(UpdateSite site) throws IOException, SAXException {
      URI digestBaseUri = site.getDigestUri();
      if (digestBaseUri == null) {
         digestBaseUri = site.getUri();
      } else if (!digestBaseUri.isAbsolute()) {
         digestBaseUri = site.getUri().resolve(digestBaseUri);
      }

      URL digest = digestBaseUri.resolve("digest.zip").toURL();
      Message.verbose("\tReading " + digest);
      URLResource res = new URLResource(digest, this.timeoutConstraint);
      ArtifactDownloadReport report = this.repositoryCacheManager.downloadRepositoryResource(res, "digest", "digest", "zip", this.options, this.urlRepository);
      if (report.getDownloadStatus() == DownloadStatus.FAILED) {
         return null;
      } else {
         InputStream in = new FileInputStream(report.getLocalFile());
         Throwable var7 = null;

         UpdateSiteDescriptor var9;
         try {
            ZipInputStream zipped = this.findEntry(in, "digest.xml");
            if (zipped != null) {
               var9 = UpdateSiteDigestParser.parse(zipped, site);
               return var9;
            }

            var9 = null;
         } catch (Throwable var19) {
            var7 = var19;
            throw var19;
         } finally {
            if (in != null) {
               if (var7 != null) {
                  try {
                     in.close();
                  } catch (Throwable var18) {
                     var7.addSuppressed(var18);
                  }
               } else {
                  in.close();
               }
            }

         }

         return var9;
      }
   }

   private UpdateSiteDescriptor loadFromSite(UpdateSite site) throws IOException, SAXException {
      UpdateSiteDescriptor repoDescriptor = new UpdateSiteDescriptor(site.getUri(), ExecutionEnvironmentProfileProvider.getInstance());

      for(EclipseFeature feature : site.getFeatures()) {
         URL url = site.getUri().resolve(feature.getUrl()).toURL();
         URLResource res = new URLResource(url, this.timeoutConstraint);
         ArtifactDownloadReport report = this.repositoryCacheManager.downloadRepositoryResource(res, feature.getId(), "feature", "jar", this.options, this.urlRepository);
         if (report.getDownloadStatus() == DownloadStatus.FAILED) {
            return null;
         }

         InputStream in = new FileInputStream(report.getLocalFile());
         Throwable var9 = null;

         Object var11;
         try {
            ZipInputStream zipped = this.findEntry(in, "feature.xml");
            if (zipped != null) {
               EclipseFeature f = FeatureParser.parse(zipped);
               f.setURL(feature.getUrl());
               repoDescriptor.addFeature(f);
               continue;
            }

            var11 = null;
         } catch (Throwable var21) {
            var9 = var21;
            throw var21;
         } finally {
            if (in != null) {
               if (var9 != null) {
                  try {
                     in.close();
                  } catch (Throwable var20) {
                     var9.addSuppressed(var20);
                  }
               } else {
                  in.close();
               }
            }

         }

         return (UpdateSiteDescriptor)var11;
      }

      return repoDescriptor;
   }

   private ZipInputStream findEntry(InputStream in, String entryName) throws IOException {
      ZipInputStream zipped = new ZipInputStream(in);

      ZipEntry zipEntry;
      for(zipEntry = zipped.getNextEntry(); zipEntry != null && !zipEntry.getName().equals(entryName); zipEntry = zipped.getNextEntry()) {
      }

      return zipEntry == null ? null : zipped;
   }
}
