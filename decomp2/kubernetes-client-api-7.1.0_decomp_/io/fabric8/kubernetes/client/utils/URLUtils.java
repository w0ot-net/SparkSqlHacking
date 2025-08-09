package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.client.http.HttpRequest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URLUtils {
   private URLUtils() {
      throw new IllegalStateException("Utility class");
   }

   public static String join(String... parts) {
      StringBuilder sb = new StringBuilder();
      String urlQueryParams = "";
      if (parts.length > 0) {
         String urlWithoutQuery = parts[0];

         try {
            URI uri = new URI(parts[0]);
            if (containsQueryParam(uri)) {
               urlQueryParams = uri.getQuery();
               urlWithoutQuery = (new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), (String)null, uri.getFragment())).toString();
            }
         } catch (URISyntaxException var6) {
         }

         sb.append(urlWithoutQuery).append("/");
      }

      StringBuilder queryParams = new StringBuilder();

      for(int i = 1; i < parts.length; ++i) {
         try {
            URI partUri = new URI(parts[i]);
            if (containsQueryParam(partUri)) {
               queryParams = getQueryParams(partUri, parts, i + 1);
               break;
            }

            sb.append(parts[i]);
         } catch (URISyntaxException var7) {
            sb.append(parts[i]);
         }

         if (i < parts.length - 1) {
            sb.append("/");
         }
      }

      appendQueryParametersFromOriginalUrl(sb, urlQueryParams, queryParams);
      String joined = sb.toString();
      return joined.replaceAll("/+", "/").replaceAll("/\\?", "?").replaceAll("/#", "#").replaceAll(":/", "://");
   }

   private static void appendQueryParametersFromOriginalUrl(StringBuilder sb, String urlQueryParams, StringBuilder queryParams) {
      if (!urlQueryParams.isEmpty()) {
         if (queryParams.length() == 0) {
            queryParams.append("?");
         } else {
            queryParams.append("&");
         }

         queryParams.append(urlQueryParams);
      }

      sb.append(queryParams);
   }

   private static StringBuilder getQueryParams(URI firstPart, String[] parts, int index) {
      StringBuilder queryParams = new StringBuilder();
      queryParams.append(firstPart.toString());

      for(int i = index; i < parts.length; ++i) {
         String param = parts[i];
         if (!param.startsWith("&")) {
            queryParams.append("&");
         }

         queryParams.append(param);
      }

      return queryParams;
   }

   private static boolean containsQueryParam(URI uri) {
      return uri.getQuery() != null;
   }

   public static String pathJoin(String... strings) {
      StringBuilder buffer = new StringBuilder();

      for(String string : strings) {
         if (string != null) {
            if (buffer.length() > 0) {
               boolean bufferEndsWithSeparator = buffer.toString().endsWith("/");
               boolean stringStartsWithSeparator = string.startsWith("/");
               if (bufferEndsWithSeparator) {
                  if (stringStartsWithSeparator) {
                     string = string.substring(1);
                  }
               } else if (!stringStartsWithSeparator) {
                  buffer.append("/");
               }
            }

            buffer.append(string);
         }
      }

      return buffer.toString();
   }

   public static boolean isValidURL(String url) {
      if (url != null) {
         try {
            new URI(url);
            return true;
         } catch (URISyntaxException var2) {
            return false;
         }
      } else {
         return false;
      }
   }

   public static String encodeToUTF(String url) {
      return HttpRequest.formURLEncode(url);
   }

   public static class URLBuilder {
      private final StringBuilder url;

      public URLBuilder(String url) {
         this.url = new StringBuilder(url);
      }

      public URLBuilder(URL url) {
         this(url.toString());
      }

      public URLBuilder addQueryParameter(String key, String value) {
         if (this.url.indexOf("?") == -1) {
            this.url.append("?");
         } else {
            this.url.append("&");
         }

         this.url.append(URLUtils.encodeToUTF(key).replaceAll("[+]", "%20")).append("=").append(URLUtils.encodeToUTF(value).replaceAll("[+]", "%20"));
         return this;
      }

      public URL build() {
         try {
            return new URL(this.toString());
         } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
         }
      }

      public String toString() {
         return this.url.toString();
      }
   }
}
