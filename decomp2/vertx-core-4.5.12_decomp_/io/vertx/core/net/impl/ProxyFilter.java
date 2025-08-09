package io.vertx.core.net.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface ProxyFilter extends Predicate {
   ProxyFilter DEFAULT_PROXY_FILTER = (so) -> !so.isDomainSocket();

   static ProxyFilter nonProxyHosts(List nonProxyHosts) {
      List<Object> filterElts = (List)nonProxyHosts.stream().map((nonProxyHost) -> {
         if (nonProxyHost.contains("*")) {
            String pattern = nonProxyHost.replaceAll("\\.", "\\.").replaceAll("\\*", ".*");
            return Pattern.compile(pattern);
         } else {
            return nonProxyHost;
         }
      }).collect(Collectors.toList());
      return (so) -> {
         if (so.isDomainSocket()) {
            return false;
         } else {
            String host = so.host();

            for(Object filterElt : filterElts) {
               if (filterElt instanceof Pattern) {
                  if (((Pattern)filterElt).matcher(host).matches()) {
                     return false;
                  }
               } else if (filterElt.equals(host)) {
                  return false;
               }
            }

            return true;
         }
      };
   }
}
