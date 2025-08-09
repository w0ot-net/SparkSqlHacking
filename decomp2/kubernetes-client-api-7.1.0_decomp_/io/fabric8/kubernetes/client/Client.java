package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.APIGroup;
import io.fabric8.kubernetes.api.model.APIGroupList;
import io.fabric8.kubernetes.api.model.APIResourceList;
import io.fabric8.kubernetes.api.model.APIVersions;
import io.fabric8.kubernetes.api.model.RootPaths;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.http.HttpClient;
import java.io.Closeable;
import java.net.URL;

public interface Client extends Closeable {
   boolean supports(Class var1);

   boolean supports(String var1, String var2);

   boolean hasApiGroup(String var1, boolean var2);

   Client adapt(Class var1);

   URL getMasterUrl();

   String getApiVersion();

   String getNamespace();

   RootPaths rootPaths();

   /** @deprecated */
   @Deprecated
   boolean supportsApiPath(String var1);

   void close();

   APIVersions getAPIVersions();

   APIGroupList getApiGroups();

   APIGroup getApiGroup(String var1);

   APIResourceList getApiResources(String var1);

   MixedOperation resources(Class var1, Class var2, Class var3);

   default MixedOperation resources(Class resourceType, Class listClass) {
      return this.resources(resourceType, listClass, (Class)null);
   }

   Client newClient(RequestConfig var1);

   HttpClient getHttpClient();

   Config getConfiguration();

   default String raw(String uri) {
      return this.raw(uri, "GET", (Object)null);
   }

   String raw(String var1, String var2, Object var3);
}
