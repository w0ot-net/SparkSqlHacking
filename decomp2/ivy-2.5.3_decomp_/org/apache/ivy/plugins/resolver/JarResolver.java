package org.apache.ivy.plugins.resolver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;
import org.apache.ivy.core.cache.CacheResourceOptions;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.jar.JarRepository;
import org.apache.ivy.plugins.repository.url.URLRepository;
import org.apache.ivy.plugins.repository.url.URLResource;

public class JarResolver extends RepositoryResolver {
   private URL url;

   public JarResolver() {
      this.setRepository(new JarRepository(new LazyTimeoutConstraint(this)));
   }

   public String getTypeName() {
      return "jar";
   }

   public void setFile(String jarFile) {
      this.setJarFile(new File(jarFile));
   }

   public void setUrl(String jarUrl) {
      try {
         this.url = new URL(jarUrl);
      } catch (MalformedURLException e) {
         throw new RuntimeException("the jar repository " + this.getName() + " has an malformed url : " + jarUrl + " (" + e.getMessage() + ")");
      }
   }

   public JarRepository getJarRepository() {
      return (JarRepository)super.getRepository();
   }

   private void setJarFile(File jarLocalFile) {
      JarFile jar;
      try {
         jar = new JarFile(jarLocalFile);
      } catch (IOException e) {
         throw new RuntimeException("the jar repository " + this.getName() + " could not be read (" + e.getMessage() + ")", e);
      }

      this.getJarRepository().setJarFile(jar);
   }

   public void setSettings(ResolverSettings settings) {
      super.setSettings(settings);
      if (this.url != null) {
         EventManager eventManager = this.getEventManager();

         ArtifactDownloadReport report;
         try {
            if (eventManager != null) {
               this.getRepository().addTransferListener(eventManager);
            }

            Resource jarResource = new URLResource(this.url, this.getTimeoutConstraint());
            CacheResourceOptions options = new CacheResourceOptions();
            report = this.getRepositoryCacheManager().downloadRepositoryResource(jarResource, "jarrepository", "jar", "jar", options, new URLRepository());
         } finally {
            if (eventManager != null) {
               this.getRepository().removeTransferListener(eventManager);
            }

         }

         if (report.getDownloadStatus() == DownloadStatus.FAILED) {
            throw new RuntimeException("The jar file " + this.url.toExternalForm() + " could not be downloaded (" + report.getDownloadDetails() + ")");
         } else {
            this.setJarFile(report.getLocalFile());
         }
      }
   }
}
