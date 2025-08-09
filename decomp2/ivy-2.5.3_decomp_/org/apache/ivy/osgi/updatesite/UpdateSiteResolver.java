package org.apache.ivy.osgi.updatesite;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.CacheResourceOptions;
import org.apache.ivy.core.cache.DownloadListener;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.osgi.repo.AbstractOSGiResolver;
import org.apache.ivy.osgi.repo.RepoDescriptor;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;
import org.apache.ivy.util.Message;
import org.xml.sax.SAXException;

public class UpdateSiteResolver extends AbstractOSGiResolver {
   private String url;
   private Long metadataTtl;
   private Boolean forceMetadataUpdate;
   private String logLevel;

   public void setUrl(String url) {
      this.url = url;
   }

   public void setMetadataTtl(Long metadataTtl) {
      this.metadataTtl = metadataTtl;
   }

   public void setForceMetadataUpdate(Boolean forceMetadataUpdate) {
      this.forceMetadataUpdate = forceMetadataUpdate;
   }

   public void setLogLevel(String logLevel) {
      this.logLevel = logLevel;
   }

   protected void init() {
      if (this.url == null) {
         throw new RuntimeException("Missing url");
      } else {
         CacheResourceOptions options = new CacheResourceOptions();
         if (this.metadataTtl != null) {
            options.setTtl(this.metadataTtl);
         }

         if (this.forceMetadataUpdate != null) {
            options.setForce(this.forceMetadataUpdate);
         }

         final int log;
         if (this.logLevel != null) {
            if ("debug".equalsIgnoreCase(this.logLevel)) {
               log = 4;
            } else if ("verbose".equalsIgnoreCase(this.logLevel)) {
               log = 3;
            } else if ("info".equalsIgnoreCase(this.logLevel)) {
               log = 2;
            } else if ("warn".equalsIgnoreCase(this.logLevel)) {
               log = 1;
            } else {
               if (!"error".equalsIgnoreCase(this.logLevel)) {
                  throw new RuntimeException("Unknown log level: " + this.logLevel);
               }

               log = 0;
            }
         } else {
            log = 2;
         }

         options.setListener(new DownloadListener() {
            public void startArtifactDownload(RepositoryCacheManager cache, ResolvedResource rres, Artifact artifact, ArtifactOrigin origin) {
               if (log <= 2) {
                  Message.info("\tdownloading " + rres.getResource().getName());
               }

            }

            public void needArtifact(RepositoryCacheManager cache, Artifact artifact) {
               if (log <= 3) {
                  Message.verbose("\ttrying to download " + artifact);
               }

            }

            public void endArtifactDownload(RepositoryCacheManager cache, Artifact artifact, ArtifactDownloadReport adr, File archiveFile) {
               if (log <= 3) {
                  if (adr.isDownloaded()) {
                     Message.verbose("\tdownloaded to " + archiveFile.getAbsolutePath());
                  } else {
                     Message.verbose("\tnothing to download");
                  }
               }

            }
         });
         UpdateSiteLoader loader = new UpdateSiteLoader(this.getRepositoryCacheManager(), this.getEventManager(), options, this.getTimeoutConstraint());
         loader.setLogLevel(log);

         RepoDescriptor repoDescriptor;
         try {
            repoDescriptor = loader.load(new URI(this.url));
         } catch (IOException e) {
            throw new RuntimeException("IO issue while trying to read the update site (" + e.getMessage() + ")");
         } catch (ParseException e) {
            throw new RuntimeException("Failed to parse the updatesite (" + e.getMessage() + ")", e);
         } catch (SAXException e) {
            throw new RuntimeException("Ill-formed updatesite (" + e.getMessage() + ")", e);
         } catch (URISyntaxException e) {
            throw new RuntimeException("Ill-formed url (" + e.getMessage() + ")", e);
         }

         if (repoDescriptor == null) {
            this.setRepoDescriptor(FAILING_REPO_DESCRIPTOR);
            throw new RuntimeException("No update site was found at the location: " + this.url);
         } else {
            this.setRepoDescriptor(repoDescriptor);
         }
      }
   }
}
