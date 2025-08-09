package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class MediaTypes {
   public static final MediaType WADL_TYPE = MediaType.valueOf("application/vnd.sun.wadl+xml");
   public static final Comparator PARTIAL_ORDER_COMPARATOR = new Comparator() {
      private int rank(MediaType type) {
         return (type.isWildcardType() ? 1 : 0) << 1 | (type.isWildcardSubtype() ? 1 : 0);
      }

      public int compare(MediaType typeA, MediaType typeB) {
         return this.rank(typeA) - this.rank(typeB);
      }
   };
   public static final Comparator MEDIA_TYPE_LIST_COMPARATOR = new Comparator() {
      public int compare(List o1, List o2) {
         return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(this.getLeastSpecific(o1), this.getLeastSpecific(o2));
      }

      private MediaType getLeastSpecific(List l) {
         return (MediaType)l.get(l.size() - 1);
      }
   };
   public static final List WILDCARD_TYPE_SINGLETON_LIST;
   public static final AcceptableMediaType WILDCARD_ACCEPTABLE_TYPE;
   public static final QualitySourceMediaType WILDCARD_QS_TYPE;
   public static final List WILDCARD_QS_TYPE_SINGLETON_LIST;
   private static final Map WILDCARD_SUBTYPE_CACHE;
   private static final Predicate QUALITY_PARAM_FILTERING_PREDICATE;

   private MediaTypes() {
      throw new AssertionError("Instantiation not allowed.");
   }

   public static boolean typeEqual(MediaType m1, MediaType m2) {
      if (m1 != null && m2 != null) {
         return m1.getSubtype().equalsIgnoreCase(m2.getSubtype()) && m1.getType().equalsIgnoreCase(m2.getType());
      } else {
         return false;
      }
   }

   public static boolean intersect(List ml1, List ml2) {
      for(MediaType m1 : ml1) {
         for(MediaType m2 : ml2) {
            if (typeEqual(m1, m2)) {
               return true;
            }
         }
      }

      return false;
   }

   public static MediaType mostSpecific(MediaType m1, MediaType m2) {
      if (m1.isWildcardType() && !m2.isWildcardType()) {
         return m2;
      } else if (m1.isWildcardSubtype() && !m2.isWildcardSubtype()) {
         return m2;
      } else {
         return m2.getParameters().size() > m1.getParameters().size() ? m2 : m1;
      }
   }

   public static List createFrom(Consumes annotation) {
      return annotation == null ? WILDCARD_TYPE_SINGLETON_LIST : createFrom(annotation.value());
   }

   public static List createFrom(Produces annotation) {
      return annotation == null ? WILDCARD_TYPE_SINGLETON_LIST : createFrom(annotation.value());
   }

   public static List createFrom(String[] mediaTypes) {
      List<MediaType> result = new ArrayList();

      try {
         for(String mediaType : mediaTypes) {
            HttpHeaderReader.readMediaTypes(result, mediaType);
         }
      } catch (ParseException ex) {
         throw new IllegalArgumentException(ex);
      }

      Collections.sort(result, PARTIAL_ORDER_COMPARATOR);
      return Collections.unmodifiableList(result);
   }

   public static List createQualitySourceMediaTypes(Produces mime) {
      return (List)(mime != null && mime.value().length != 0 ? new ArrayList(createQualitySourceMediaTypes(mime.value())) : WILDCARD_QS_TYPE_SINGLETON_LIST);
   }

   public static List createQualitySourceMediaTypes(String[] mediaTypes) {
      try {
         return HttpHeaderReader.readQualitySourceMediaType(mediaTypes);
      } catch (ParseException ex) {
         throw new IllegalArgumentException(ex);
      }
   }

   public static int getQuality(MediaType mt) {
      String qParam = (String)mt.getParameters().get("q");
      return readQualityFactor(qParam);
   }

   private static int readQualityFactor(String qParam) throws IllegalArgumentException {
      if (qParam == null) {
         return 1000;
      } else {
         try {
            return HttpHeaderReader.readQualityFactor(qParam);
         } catch (ParseException ex) {
            throw new IllegalArgumentException(ex);
         }
      }
   }

   public static MediaType stripQualityParams(MediaType mediaType) {
      Map<String, String> oldParameters = mediaType.getParameters();
      return !oldParameters.isEmpty() && (oldParameters.containsKey("qs") || oldParameters.containsKey("q")) ? new MediaType(mediaType.getType(), mediaType.getSubtype(), (Map)oldParameters.entrySet().stream().filter((entry) -> QUALITY_PARAM_FILTERING_PREDICATE.test(entry.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))) : mediaType;
   }

   public static MediaType getTypeWildCart(MediaType mediaType) {
      MediaType mt = (MediaType)WILDCARD_SUBTYPE_CACHE.get(mediaType.getType());
      if (mt == null) {
         mt = new MediaType(mediaType.getType(), "*");
      }

      return mt;
   }

   public static String convertToString(Iterable mediaTypes) {
      StringBuilder sb = new StringBuilder();
      boolean isFirst = true;

      for(MediaType mediaType : mediaTypes) {
         if (!isFirst) {
            sb.append(", ");
         } else {
            isFirst = false;
         }

         sb.append("\"").append(mediaType.toString()).append("\"");
      }

      return sb.toString();
   }

   public static boolean isWildcard(MediaType mediaType) {
      return mediaType.isWildcardType() || mediaType.isWildcardSubtype();
   }

   static {
      WILDCARD_TYPE_SINGLETON_LIST = Collections.singletonList(MediaType.WILDCARD_TYPE);
      WILDCARD_ACCEPTABLE_TYPE = new AcceptableMediaType("*", "*");
      WILDCARD_QS_TYPE = new QualitySourceMediaType("*", "*");
      WILDCARD_QS_TYPE_SINGLETON_LIST = Collections.singletonList(WILDCARD_QS_TYPE);
      WILDCARD_SUBTYPE_CACHE = new HashMap() {
         private static final long serialVersionUID = 3109256773218160485L;

         {
            this.put("application", new MediaType("application", "*"));
            this.put("multipart", new MediaType("multipart", "*"));
            this.put("text", new MediaType("text", "*"));
         }
      };
      QUALITY_PARAM_FILTERING_PREDICATE = (input) -> !"qs".equals(input) && !"q".equals(input);
   }
}
