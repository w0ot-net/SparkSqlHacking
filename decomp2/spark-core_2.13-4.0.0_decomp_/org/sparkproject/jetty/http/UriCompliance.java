package org.sparkproject.jetty.http;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UriCompliance implements ComplianceViolation.Mode {
   protected static final Logger LOG = LoggerFactory.getLogger(UriCompliance.class);
   public static final UriCompliance DEFAULT;
   public static final UriCompliance LEGACY;
   public static final UriCompliance RFC3986;
   public static final UriCompliance RFC3986_UNAMBIGUOUS;
   public static final UriCompliance UNSAFE;
   /** @deprecated */
   @Deprecated
   public static final UriCompliance SAFE;
   /** @deprecated */
   @Deprecated
   public static final UriCompliance STRICT;
   private static final AtomicInteger __custom;
   private static final List KNOWN_MODES;
   private final String _name;
   private final Set _allowed;

   public static UriCompliance valueOf(String name) {
      for(UriCompliance compliance : KNOWN_MODES) {
         if (compliance.getName().equals(name)) {
            return compliance;
         }
      }

      LOG.warn("Unknown UriCompliance mode {}", name);
      return null;
   }

   public static UriCompliance from(Set violations) {
      return new UriCompliance("CUSTOM" + __custom.getAndIncrement(), violations);
   }

   public static UriCompliance from(String spec) {
      String[] elements = spec.split("\\s*,\\s*");
      Set<Violation> violations;
      switch (elements[0]) {
         case "0":
            violations = EnumSet.noneOf(Violation.class);
            break;
         case "*":
            violations = EnumSet.allOf(Violation.class);
            break;
         default:
            UriCompliance mode = valueOf(elements[0]);
            violations = (Set<Violation>)(mode == null ? EnumSet.noneOf(Violation.class) : copyOf(mode.getAllowed()));
      }

      for(int i = 1; i < elements.length; ++i) {
         String element = elements[i];
         boolean exclude = element.startsWith("-");
         if (exclude) {
            element = element.substring(1);
         }

         if (!element.equals("NON_CANONICAL_AMBIGUOUS_PATHS")) {
            Violation section = UriCompliance.Violation.valueOf(element);
            if (exclude) {
               violations.remove(section);
            } else {
               violations.add(section);
            }
         }
      }

      UriCompliance compliance = new UriCompliance("CUSTOM" + __custom.getAndIncrement(), violations);
      if (LOG.isDebugEnabled()) {
         LOG.debug("UriCompliance from {}->{}", spec, compliance);
      }

      return compliance;
   }

   public UriCompliance(String name, Set violations) {
      Objects.requireNonNull(violations);
      this._name = name;
      this._allowed = Collections.unmodifiableSet((Set)(violations.isEmpty() ? EnumSet.noneOf(Violation.class) : copyOf(violations)));
   }

   public boolean allows(ComplianceViolation violation) {
      return violation instanceof Violation && this._allowed.contains(violation);
   }

   public String getName() {
      return this._name;
   }

   public Set getAllowed() {
      return this._allowed;
   }

   public Set getKnown() {
      return EnumSet.allOf(Violation.class);
   }

   public UriCompliance with(String name, Violation... violations) {
      Set<Violation> union = (Set<Violation>)(this._allowed.isEmpty() ? EnumSet.noneOf(Violation.class) : copyOf(this._allowed));
      union.addAll(copyOf(violations));
      return new UriCompliance(name, union);
   }

   public UriCompliance without(String name, Violation... violations) {
      Set<Violation> remainder = (Set<Violation>)(this._allowed.isEmpty() ? EnumSet.noneOf(Violation.class) : copyOf(this._allowed));
      remainder.removeAll(copyOf(violations));
      return new UriCompliance(name, remainder);
   }

   public String toString() {
      return String.format("%s%s", this._name, this._allowed);
   }

   private static Set copyOf(Violation[] violations) {
      return violations != null && violations.length != 0 ? EnumSet.copyOf(Arrays.asList(violations)) : EnumSet.noneOf(Violation.class);
   }

   private static Set copyOf(Set violations) {
      return violations != null && !violations.isEmpty() ? EnumSet.copyOf(violations) : EnumSet.noneOf(Violation.class);
   }

   public static String checkUriCompliance(UriCompliance compliance, HttpURI uri) {
      for(Violation violation : UriCompliance.Violation.values()) {
         if (uri.hasViolation(violation) && (compliance == null || !compliance.allows(violation))) {
            return violation.getDescription();
         }
      }

      return null;
   }

   static {
      DEFAULT = new UriCompliance("DEFAULT", EnumSet.of(UriCompliance.Violation.AMBIGUOUS_PATH_SEPARATOR, UriCompliance.Violation.AMBIGUOUS_PATH_ENCODING));
      LEGACY = new UriCompliance("LEGACY", EnumSet.of(UriCompliance.Violation.AMBIGUOUS_PATH_SEGMENT, UriCompliance.Violation.AMBIGUOUS_PATH_SEPARATOR, UriCompliance.Violation.AMBIGUOUS_PATH_ENCODING, UriCompliance.Violation.AMBIGUOUS_EMPTY_SEGMENT, UriCompliance.Violation.UTF16_ENCODINGS));
      RFC3986 = new UriCompliance("RFC3986", EnumSet.allOf(Violation.class));
      RFC3986_UNAMBIGUOUS = new UriCompliance("RFC3986_UNAMBIGUOUS", EnumSet.noneOf(Violation.class));
      UNSAFE = new UriCompliance("UNSAFE", EnumSet.allOf(Violation.class));
      SAFE = new UriCompliance("SAFE", DEFAULT.getAllowed());
      STRICT = new UriCompliance("STRICT", RFC3986.getAllowed());
      __custom = new AtomicInteger();
      KNOWN_MODES = List.of(DEFAULT, LEGACY, RFC3986, RFC3986_UNAMBIGUOUS, UNSAFE, SAFE, STRICT);
   }

   public static enum Violation implements ComplianceViolation {
      AMBIGUOUS_PATH_SEGMENT("https://tools.ietf.org/html/rfc3986#section-3.3", "Ambiguous URI path segment"),
      AMBIGUOUS_EMPTY_SEGMENT("https://tools.ietf.org/html/rfc3986#section-3.3", "Ambiguous URI empty segment"),
      AMBIGUOUS_PATH_SEPARATOR("https://tools.ietf.org/html/rfc3986#section-3.3", "Ambiguous URI path separator"),
      AMBIGUOUS_PATH_PARAMETER("https://tools.ietf.org/html/rfc3986#section-3.3", "Ambiguous URI path parameter"),
      AMBIGUOUS_PATH_ENCODING("https://tools.ietf.org/html/rfc3986#section-3.3", "Ambiguous URI path encoding"),
      UTF16_ENCODINGS("https://www.w3.org/International/iri-edit/draft-duerst-iri.html#anchor29", "UTF16 encoding");

      private final String _url;
      private final String _description;

      private Violation(String url, String description) {
         this._url = url;
         this._description = description;
      }

      public String getName() {
         return this.name();
      }

      public String getURL() {
         return this._url;
      }

      public String getDescription() {
         return this._description;
      }

      // $FF: synthetic method
      private static Violation[] $values() {
         return new Violation[]{AMBIGUOUS_PATH_SEGMENT, AMBIGUOUS_EMPTY_SEGMENT, AMBIGUOUS_PATH_SEPARATOR, AMBIGUOUS_PATH_PARAMETER, AMBIGUOUS_PATH_ENCODING, UTF16_ENCODINGS};
      }
   }
}
