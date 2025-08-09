package org.apache.ivy.osgi.repo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.url.URLRepository;
import org.apache.ivy.plugins.repository.url.URLResource;

public class RelativeURLRepository extends URLRepository {
   private final URL baseUrl;
   private Map resourcesCache;

   public RelativeURLRepository() {
      this.resourcesCache = new HashMap();
      this.baseUrl = null;
   }

   /** @deprecated */
   @Deprecated
   public RelativeURLRepository(URL baseUrl) {
      this(baseUrl, (TimeoutConstraint)null);
   }

   public RelativeURLRepository(URL baseUrl, TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
      this.resourcesCache = new HashMap();
      this.baseUrl = baseUrl;
   }

   public Resource getResource(String source) throws IOException {
      source = encode(source);
      Resource res = (Resource)this.resourcesCache.get(source);
      if (res == null) {
         URI uri;
         try {
            uri = new URI(source);
         } catch (URISyntaxException var5) {
            uri = null;
         }

         if (uri != null && !uri.isAbsolute()) {
            res = new URLResource(new URL(this.baseUrl + source), this.getTimeoutConstraint());
         } else {
            res = new URLResource(new URL(source), this.getTimeoutConstraint());
         }

         this.resourcesCache.put(source, res);
      }

      return res;
   }

   private static String encode(String source) {
      return source.trim().replaceAll(" ", "%20");
   }
}
