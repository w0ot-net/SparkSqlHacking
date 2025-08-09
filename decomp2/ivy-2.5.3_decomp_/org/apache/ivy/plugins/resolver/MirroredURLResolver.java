package org.apache.ivy.plugins.resolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.ivy.core.cache.CacheResourceOptions;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.osgi.repo.RelativeURLRepository;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.url.ChainedRepository;
import org.apache.ivy.plugins.repository.url.URLRepository;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.util.Message;

public class MirroredURLResolver extends RepositoryResolver {
   private URL mirrorListUrl;

   public MirroredURLResolver() {
      this.setRepository(new ChainedRepository());
   }

   public void setMirrorListUrl(URL mirrorListUrl) {
      this.mirrorListUrl = mirrorListUrl;
   }

   private void setupMirrors() {
      File mirrorListFile = this.downloadMirrorList();

      List<String> mirrorBaseUrls;
      try {
         mirrorBaseUrls = this.readMirrorList(mirrorListFile);
      } catch (IOException e) {
         throw new IllegalStateException("The mirror list could not be read from " + this.mirrorListUrl + " (" + e.getMessage() + ")");
      }

      List<Repository> repositories = new ArrayList();

      for(String baseUrl : mirrorBaseUrls) {
         URL url = null;

         try {
            url = new URL(baseUrl);
         } catch (MalformedURLException var8) {
            Message.warn("In the mirror list from " + this.mirrorListUrl + ", an incorrect url has been found and will then not be used: " + baseUrl);
         }

         if (url != null) {
            RelativeURLRepository repo = new RelativeURLRepository(url, this.getTimeoutConstraint());
            repositories.add(repo);
         }
      }

      ((ChainedRepository)this.getRepository()).setRepositories(repositories);
   }

   private File downloadMirrorList() {
      URLRepository urlRepository = new URLRepository(this.getTimeoutConstraint());
      if (this.getEventManager() != null) {
         urlRepository.addTransferListener(this.getEventManager());
      }

      URLResource mirrorResource = new URLResource(this.mirrorListUrl, this.getTimeoutConstraint());
      CacheResourceOptions options = new CacheResourceOptions();
      ArtifactDownloadReport report = this.getRepositoryCacheManager().downloadRepositoryResource(mirrorResource, "mirrorlist", "text", "txt", options, urlRepository);
      return report.getLocalFile();
   }

   private List readMirrorList(File mirrorListFile) throws IOException {
      List<String> list = new ArrayList();
      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(mirrorListFile)));
      Throwable var4 = null;

      try {
         for(String line = in.readLine(); line != null; line = in.readLine()) {
            list.add(line);
         }
      } catch (Throwable var13) {
         var4 = var13;
         throw var13;
      } finally {
         if (in != null) {
            if (var4 != null) {
               try {
                  in.close();
               } catch (Throwable var12) {
                  var4.addSuppressed(var12);
               }
            } else {
               in.close();
            }
         }

      }

      return list;
   }

   public String getTypeName() {
      return "mirroredurl";
   }

   public void validate() {
      super.validate();
      this.setupMirrors();
   }
}
