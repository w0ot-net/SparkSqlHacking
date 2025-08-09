package org.glassfish.jersey.server.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.PathSegment;
import jakarta.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.message.internal.LanguageTag;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.uri.UriComponent;
import org.glassfish.jersey.uri.UriComponent.Type;

@PreMatching
@Priority(3000)
public final class UriConnegFilter implements ContainerRequestFilter {
   protected final Map mediaTypeMappings;
   protected final Map languageMappings;

   public UriConnegFilter(@Context Configuration rc) {
      this(extractMediaTypeMappings(rc.getProperty("jersey.config.server.mediaTypeMappings")), extractLanguageMappings(rc.getProperty("jersey.config.server.languageMappings")));
   }

   public UriConnegFilter(Map mediaTypeMappings, Map languageMappings) {
      if (mediaTypeMappings == null) {
         mediaTypeMappings = Collections.emptyMap();
      }

      if (languageMappings == null) {
         languageMappings = Collections.emptyMap();
      }

      this.mediaTypeMappings = mediaTypeMappings;
      this.languageMappings = languageMappings;
   }

   public void filter(ContainerRequestContext rc) throws IOException {
      UriInfo uriInfo = rc.getUriInfo();
      String path = uriInfo.getRequestUri().getRawPath();
      if (path.indexOf(46) != -1) {
         List<PathSegment> l = uriInfo.getPathSegments(false);
         if (!l.isEmpty()) {
            PathSegment segment = null;

            for(int i = l.size() - 1; i >= 0; --i) {
               segment = (PathSegment)l.get(i);
               if (segment.getPath().length() > 0) {
                  break;
               }
            }

            if (segment != null) {
               int length = path.length();
               String[] suffixes = segment.getPath().split("\\.");

               for(int i = suffixes.length - 1; i >= 1; --i) {
                  String suffix = suffixes[i];
                  if (suffix.length() != 0) {
                     MediaType accept = (MediaType)this.mediaTypeMappings.get(suffix);
                     if (accept != null) {
                        rc.getHeaders().putSingle("Accept", accept.toString());
                        int index = path.lastIndexOf('.' + suffix);
                        path = (new StringBuilder(path)).delete(index, index + suffix.length() + 1).toString();
                        suffixes[i] = "";
                        break;
                     }
                  }
               }

               for(int i = suffixes.length - 1; i >= 1; --i) {
                  String suffix = suffixes[i];
                  if (suffix.length() != 0) {
                     String acceptLanguage = (String)this.languageMappings.get(suffix);
                     if (acceptLanguage != null) {
                        rc.getHeaders().putSingle("Accept-Language", acceptLanguage);
                        int index = path.lastIndexOf('.' + suffix);
                        path = (new StringBuilder(path)).delete(index, index + suffix.length() + 1).toString();
                        suffixes[i] = "";
                        break;
                     }
                  }
               }

               if (length != path.length()) {
                  rc.setRequestUri(uriInfo.getRequestUriBuilder().replacePath(path).build(new Object[0]));
               }

            }
         }
      }
   }

   private static Map extractMediaTypeMappings(Object mappings) {
      return parseAndValidateMappings("jersey.config.server.mediaTypeMappings", mappings, new TypeParser() {
         public MediaType valueOf(String value) {
            return MediaType.valueOf(value);
         }
      });
   }

   private static Map extractLanguageMappings(Object mappings) {
      return parseAndValidateMappings("jersey.config.server.languageMappings", mappings, new TypeParser() {
         public String valueOf(String value) {
            return LanguageTag.valueOf(value).toString();
         }
      });
   }

   private static Map parseAndValidateMappings(String property, Object mappings, TypeParser parser) {
      if (mappings == null) {
         return Collections.emptyMap();
      } else if (mappings instanceof Map) {
         return (Map)mappings;
      } else {
         HashMap<String, T> mappingsMap = new HashMap();
         if (mappings instanceof String) {
            parseMappings(property, (String)mappings, mappingsMap, parser);
         } else {
            if (!(mappings instanceof String[])) {
               throw new IllegalArgumentException(LocalizationMessages.INVALID_MAPPING_TYPE(property));
            }

            String[] mappingsArray = (String[])mappings;

            for(String aMappingsArray : mappingsArray) {
               parseMappings(property, aMappingsArray, mappingsMap, parser);
            }
         }

         encodeKeys(mappingsMap);
         return mappingsMap;
      }
   }

   private static void parseMappings(String property, String mappings, Map mappingsMap, TypeParser parser) {
      if (mappings != null) {
         String[] records = mappings.split(",");

         for(String record : records) {
            String[] mapping = record.split(":");
            if (mapping.length != 2) {
               throw new IllegalArgumentException(LocalizationMessages.INVALID_MAPPING_FORMAT(property, mappings));
            }

            String trimmedSegment = mapping[0].trim();
            String trimmedValue = mapping[1].trim();
            if (trimmedSegment.length() == 0) {
               throw new IllegalArgumentException(LocalizationMessages.INVALID_MAPPING_KEY_EMPTY(property, record));
            }

            if (trimmedValue.length() == 0) {
               throw new IllegalArgumentException(LocalizationMessages.INVALID_MAPPING_VALUE_EMPTY(property, record));
            }

            mappingsMap.put(trimmedSegment, parser.valueOf(trimmedValue));
         }

      }
   }

   private static void encodeKeys(Map map) {
      Map<String, T> tempMap = new HashMap();

      for(Map.Entry entry : map.entrySet()) {
         tempMap.put(UriComponent.contextualEncode((String)entry.getKey(), Type.PATH_SEGMENT), entry.getValue());
      }

      map.clear();
      map.putAll(tempMap);
   }

   private interface TypeParser {
      Object valueOf(String var1);
   }
}
