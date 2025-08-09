package org.apache.http.conn.util;

import java.net.IDN;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

@Contract(
   threading = ThreadingBehavior.SAFE
)
public final class PublicSuffixMatcher {
   private final Map rules;
   private final Map exceptions;

   public PublicSuffixMatcher(Collection rules, Collection exceptions) {
      this(DomainType.UNKNOWN, rules, exceptions);
   }

   public PublicSuffixMatcher(DomainType domainType, Collection rules, Collection exceptions) {
      Args.notNull(domainType, "Domain type");
      Args.notNull(rules, "Domain suffix rules");
      this.rules = new ConcurrentHashMap(rules.size());

      for(String rule : rules) {
         this.rules.put(rule, domainType);
      }

      this.exceptions = new ConcurrentHashMap();
      if (exceptions != null) {
         for(String exception : exceptions) {
            this.exceptions.put(exception, domainType);
         }
      }

   }

   public PublicSuffixMatcher(Collection lists) {
      Args.notNull(lists, "Domain suffix lists");
      this.rules = new ConcurrentHashMap();
      this.exceptions = new ConcurrentHashMap();

      for(PublicSuffixList list : lists) {
         DomainType domainType = list.getType();

         for(String rule : list.getRules()) {
            this.rules.put(rule, domainType);
         }

         List<String> exceptions = list.getExceptions();
         if (exceptions != null) {
            for(String exception : exceptions) {
               this.exceptions.put(exception, domainType);
            }
         }
      }

   }

   private static DomainType findEntry(Map map, String rule) {
      return map == null ? null : (DomainType)map.get(rule);
   }

   private static boolean match(DomainType domainType, DomainType expectedType) {
      return domainType != null && (expectedType == null || domainType.equals(expectedType));
   }

   public String getDomainRoot(String domain) {
      return this.getDomainRoot(domain, (DomainType)null);
   }

   public String getDomainRoot(String domain, DomainType expectedType) {
      if (domain == null) {
         return null;
      } else if (domain.startsWith(".")) {
         return null;
      } else {
         String normalized = DnsUtils.normalize(domain);
         String segment = normalized;

         String result;
         String nextSegment;
         for(result = null; segment != null; segment = nextSegment) {
            String key = IDN.toUnicode(segment);
            DomainType exceptionRule = findEntry(this.exceptions, key);
            if (match(exceptionRule, expectedType)) {
               return segment;
            }

            DomainType domainRule = findEntry(this.rules, key);
            if (match(domainRule, expectedType)) {
               if (domainRule == DomainType.PRIVATE) {
                  return segment;
               }

               return result;
            }

            int nextdot = segment.indexOf(46);
            nextSegment = nextdot != -1 ? segment.substring(nextdot + 1) : null;
            if (nextSegment != null) {
               DomainType wildcardDomainRule = findEntry(this.rules, "*." + IDN.toUnicode(nextSegment));
               if (match(wildcardDomainRule, expectedType)) {
                  if (wildcardDomainRule == DomainType.PRIVATE) {
                     return segment;
                  }

                  return result;
               }
            }

            result = segment;
         }

         if (expectedType != null && expectedType != DomainType.UNKNOWN) {
            return null;
         } else {
            return result;
         }
      }
   }

   public boolean matches(String domain) {
      return this.matches(domain, (DomainType)null);
   }

   public boolean matches(String domain, DomainType expectedType) {
      if (domain == null) {
         return false;
      } else {
         String domainRoot = this.getDomainRoot(domain.startsWith(".") ? domain.substring(1) : domain, expectedType);
         return domainRoot == null;
      }
   }
}
