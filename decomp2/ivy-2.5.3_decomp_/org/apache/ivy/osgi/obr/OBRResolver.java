package org.apache.ivy.osgi.obr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.ivy.core.cache.CacheResourceOptions;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.osgi.obr.xml.OBRXMLParser;
import org.apache.ivy.osgi.repo.AbstractOSGiResolver;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.xml.sax.SAXException;

public class OBRResolver extends AbstractOSGiResolver {
   private String repoXmlURL;
   private String repoXmlFile;
   private Long metadataTtl;
   private Boolean forceMetadataUpdate;

   public void setRepoXmlFile(String repositoryXmlFile) {
      this.repoXmlFile = repositoryXmlFile;
   }

   public void setRepoXmlURL(String repositoryXmlURL) {
      this.repoXmlURL = repositoryXmlURL;
   }

   public void setMetadataTtl(Long metadataTtl) {
      this.metadataTtl = metadataTtl;
   }

   public void setForceMetadataUpdate(Boolean forceMetadataUpdate) {
      this.forceMetadataUpdate = forceMetadataUpdate;
   }

   protected void init() {
      if (this.repoXmlFile != null && this.repoXmlURL != null) {
         throw new RuntimeException("The OBR repository resolver " + this.getName() + " couldn't be configured: repoXmlFile and repoXmlUrl cannot be set both");
      } else {
         if (this.repoXmlFile != null) {
            File f = new File(this.repoXmlFile);
            this.loadRepoFromFile(f.getParentFile().toURI(), f, this.repoXmlFile);
         } else {
            if (this.repoXmlURL == null) {
               throw new RuntimeException("The OBR repository resolver " + this.getName() + " couldn't be configured: repoXmlFile or repoXmlUrl is missing");
            }

            URL url;
            try {
               url = new URL(this.repoXmlURL);
            } catch (MalformedURLException var11) {
               throw new RuntimeException("The OBR repository resolver " + this.getName() + " couldn't be configured: repoXmlURL '" + this.repoXmlURL + "' is not an URL");
            }

            EventManager eventManager = this.getEventManager();

            ArtifactDownloadReport report;
            try {
               if (eventManager != null) {
                  this.getRepository().addTransferListener(eventManager);
               }

               Resource obrResource = new URLResource(url, this.getTimeoutConstraint());
               CacheResourceOptions options = new CacheResourceOptions();
               if (this.metadataTtl != null) {
                  options.setTtl(this.metadataTtl);
               }

               if (this.forceMetadataUpdate != null) {
                  options.setForce(this.forceMetadataUpdate);
               }

               report = this.getRepositoryCacheManager().downloadRepositoryResource(obrResource, "obr", "obr", "xml", options, this.getRepository());
            } finally {
               if (eventManager != null) {
                  this.getRepository().removeTransferListener(eventManager);
               }

            }

            URI baseURI;
            try {
               baseURI = new URI(this.repoXmlURL);
            } catch (URISyntaxException var10) {
               throw new RuntimeException("illegal uri");
            }

            this.loadRepoFromFile(baseURI, report.getLocalFile(), this.repoXmlURL);
         }

      }
   }

   private void loadRepoFromFile(URI baseUri, File repoFile, String sourceLocation) {
      FileInputStream in;
      try {
         in = new FileInputStream(repoFile);
      } catch (FileNotFoundException var9) {
         throw new RuntimeException("The OBR repository resolver " + this.getName() + " couldn't be configured: the file " + sourceLocation + " was not found");
      }

      try {
         this.setRepoDescriptor(OBRXMLParser.parse(baseUri, in));
      } catch (IOException e) {
         throw new RuntimeException("The OBR repository resolver " + this.getName() + " couldn't be configured: the file " + sourceLocation + " could not be read (" + e.getMessage() + ")", e);
      } catch (SAXException e) {
         throw new RuntimeException("The OBR repository resolver " + this.getName() + " couldn't be configured: the file " + sourceLocation + " has incorrect XML (" + e.getMessage() + ")", e);
      }

      try {
         in.close();
      } catch (IOException var6) {
      }

   }
}
