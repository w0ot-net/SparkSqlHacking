package org.apache.ivy.plugins.repository.url;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.plugins.repository.RepositoryCopyProgressListener;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.url.ApacheURLLister;

public class URLRepository extends AbstractRepository {
   private RepositoryCopyProgressListener progress = new RepositoryCopyProgressListener(this);
   private final Map resourcesCache = new HashMap();
   private ApacheURLLister lister = new ApacheURLLister();

   public URLRepository() {
   }

   public URLRepository(TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
   }

   public Resource getResource(String source) throws IOException {
      Resource res = (Resource)this.resourcesCache.get(source);
      if (res == null) {
         res = new URLResource(new URL(source), this.getTimeoutConstraint());
         this.resourcesCache.put(source, res);
      }

      return res;
   }

   public void get(String source, File destination) throws IOException {
      this.fireTransferInitiated(this.getResource(source), 5);

      try {
         Resource res = this.getResource(source);
         long totalLength = res.getContentLength();
         if (totalLength > 0L) {
            this.progress.setTotalLength(totalLength);
         }

         FileUtil.copy((URL)(new URL(source)), (File)destination, this.progress, this.getTimeoutConstraint());
      } catch (RuntimeException | IOException ex) {
         this.fireTransferError(ex);
         throw ex;
      } finally {
         this.progress.setTotalLength((Long)null);
      }

   }

   public void put(File source, String destination, boolean overwrite) throws IOException {
      if (!overwrite && this.getResource(destination).exists()) {
         throw new IOException("destination file exists and overwrite == false");
      } else {
         this.fireTransferInitiated(this.getResource(destination), 6);

         try {
            long totalLength = source.length();
            if (totalLength > 0L) {
               this.progress.setTotalLength(totalLength);
            }

            FileUtil.copy((File)source, (URL)(new URL(destination)), this.progress, this.getTimeoutConstraint());
         } catch (RuntimeException | IOException ex) {
            this.fireTransferError(ex);
            throw ex;
         } finally {
            this.progress.setTotalLength((Long)null);
         }

      }
   }

   public List list(String parent) throws IOException {
      if (parent.startsWith("http")) {
         List<URL> urls = this.lister.listAll(new URL(parent));
         if (urls != null) {
            List<String> ret = new ArrayList(urls.size());

            for(URL url : urls) {
               ret.add(url.toExternalForm());
            }

            return ret;
         }
      } else if (parent.startsWith("file")) {
         String path;
         try {
            URI uri = new URI(parent);
            if (uri.isOpaque()) {
               path = uri.getSchemeSpecificPart();
            } else {
               path = uri.getPath();
            }
         } catch (URISyntaxException e) {
            throw new IOException("Couldn't list content of '" + parent + "'", e);
         }

         File file = new File(path);
         if (file.exists() && file.isDirectory()) {
            String[] files = file.list();
            List<String> ret = new ArrayList(files.length);
            URL context = path.endsWith("/") ? new URL(parent) : new URL(parent + "/");

            for(String fileName : files) {
               ret.add((new URL(context, fileName)).toExternalForm());
            }

            return ret;
         }

         return Collections.emptyList();
      }

      return null;
   }
}
