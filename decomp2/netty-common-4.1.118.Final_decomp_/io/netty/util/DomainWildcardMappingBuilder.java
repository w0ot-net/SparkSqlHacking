package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import java.util.LinkedHashMap;
import java.util.Map;

public class DomainWildcardMappingBuilder {
   private final Object defaultValue;
   private final Map map;

   public DomainWildcardMappingBuilder(Object defaultValue) {
      this(4, defaultValue);
   }

   public DomainWildcardMappingBuilder(int initialCapacity, Object defaultValue) {
      this.defaultValue = ObjectUtil.checkNotNull(defaultValue, "defaultValue");
      this.map = new LinkedHashMap(initialCapacity);
   }

   public DomainWildcardMappingBuilder add(String hostname, Object output) {
      this.map.put(this.normalizeHostName(hostname), ObjectUtil.checkNotNull(output, "output"));
      return this;
   }

   private String normalizeHostName(String hostname) {
      ObjectUtil.checkNotNull(hostname, "hostname");
      if (!hostname.isEmpty() && hostname.charAt(0) != '.') {
         hostname = DomainWildcardMappingBuilder.ImmutableDomainWildcardMapping.normalize((String)ObjectUtil.checkNotNull(hostname, "hostname"));
         if (hostname.charAt(0) == '*') {
            if (hostname.length() >= 3 && hostname.charAt(1) == '.') {
               return hostname.substring(1);
            } else {
               throw new IllegalArgumentException("Wildcard Hostname '" + hostname + "'not valid");
            }
         } else {
            return hostname;
         }
      } else {
         throw new IllegalArgumentException("Hostname '" + hostname + "' not valid");
      }
   }

   public Mapping build() {
      return new ImmutableDomainWildcardMapping(this.defaultValue, this.map);
   }

   private static final class ImmutableDomainWildcardMapping implements Mapping {
      private static final String REPR_HEADER = "ImmutableDomainWildcardMapping(default: ";
      private static final String REPR_MAP_OPENING = ", map: ";
      private static final String REPR_MAP_CLOSING = ")";
      private final Object defaultValue;
      private final Map map;

      ImmutableDomainWildcardMapping(Object defaultValue, Map map) {
         this.defaultValue = defaultValue;
         this.map = new LinkedHashMap(map);
      }

      public Object map(String hostname) {
         if (hostname != null) {
            hostname = normalize(hostname);
            V value = (V)this.map.get(hostname);
            if (value != null) {
               return value;
            }

            int idx = hostname.indexOf(46);
            if (idx != -1) {
               value = (V)this.map.get(hostname.substring(idx));
               if (value != null) {
                  return value;
               }
            }
         }

         return this.defaultValue;
      }

      static String normalize(String hostname) {
         return DomainNameMapping.normalizeHostname(hostname);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("ImmutableDomainWildcardMapping(default: ").append(this.defaultValue).append(", map: ").append('{');

         for(Map.Entry entry : this.map.entrySet()) {
            String hostname = (String)entry.getKey();
            if (hostname.charAt(0) == '.') {
               hostname = '*' + hostname;
            }

            sb.append(hostname).append('=').append(entry.getValue()).append(", ");
         }

         sb.setLength(sb.length() - 2);
         return sb.append('}').append(")").toString();
      }
   }
}
