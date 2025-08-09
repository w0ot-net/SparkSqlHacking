package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.http.BasicBuilder;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.http.Interceptor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ImpersonatorInterceptor implements Interceptor {
   public static final String IMPERSONATE_USER = "Impersonate-User";
   public static final String NAME = "IMPERSONATOR";
   private final RequestConfig requestConfig;

   public ImpersonatorInterceptor(RequestConfig requestConfig) {
      this.requestConfig = requestConfig;
   }

   public void before(BasicBuilder builder, HttpRequest request, Interceptor.RequestTags tags) {
      RequestConfig config = (RequestConfig)Optional.ofNullable((RequestConfig)tags.getTag(RequestConfig.class)).orElse(this.requestConfig);
      if (Utils.isNotNullOrEmpty(config.getImpersonateUsername())) {
         builder.header("Impersonate-User", config.getImpersonateUsername());
         String[] impersonateGroups = config.getImpersonateGroups();
         if (Utils.isNotNullOrEmpty(impersonateGroups)) {
            for(String group : impersonateGroups) {
               builder.header("Impersonate-Group", group);
            }
         }

         Map<String, List<String>> impersonateExtras = config.getImpersonateExtras();
         if (Utils.isNotNullOrEmpty(impersonateExtras)) {
            for(Object key : impersonateExtras.keySet()) {
               List<String> values = (List)impersonateExtras.get(key);
               if (values != null) {
                  for(String value : values) {
                     builder.header("Impersonate-Extra-" + key, value);
                  }
               }
            }
         }
      }

   }
}
