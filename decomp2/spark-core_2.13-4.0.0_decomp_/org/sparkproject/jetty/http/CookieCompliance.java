package org.sparkproject.jetty.http;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieCompliance implements ComplianceViolation.Mode {
   private static final Logger LOG = LoggerFactory.getLogger(CookieCompliance.class);
   public static final CookieCompliance RFC6265;
   public static final CookieCompliance RFC6265_STRICT;
   public static final CookieCompliance RFC6265_LEGACY;
   public static final CookieCompliance RFC2965_LEGACY;
   public static final CookieCompliance RFC2965;
   private static final List KNOWN_MODES;
   private static final AtomicInteger __custom;
   private final String _name;
   private final Set _violations;

   public static CookieCompliance valueOf(String name) {
      for(CookieCompliance compliance : KNOWN_MODES) {
         if (compliance.getName().equals(name)) {
            return compliance;
         }
      }

      return null;
   }

   public static CookieCompliance from(String spec) {
      CookieCompliance compliance = valueOf(spec);
      if (compliance == null) {
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
               CookieCompliance mode = valueOf(elements[0]);
               if (mode == null) {
                  throw new IllegalArgumentException("Unknown base mode: " + elements[0]);
               }

               violations = mode.getAllowed().isEmpty() ? EnumSet.noneOf(Violation.class) : EnumSet.copyOf(mode.getAllowed());
         }

         for(int i = 1; i < elements.length; ++i) {
            String element = elements[i];
            boolean exclude = element.startsWith("-");
            if (exclude) {
               element = element.substring(1);
            }

            Violation section = CookieCompliance.Violation.valueOf(element);
            if (exclude) {
               violations.remove(section);
            } else {
               violations.add(section);
            }
         }

         compliance = new CookieCompliance("CUSTOM" + __custom.getAndIncrement(), violations);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("CookieCompliance from {}->{}", spec, compliance);
      }

      return compliance;
   }

   public CookieCompliance(String name, Set violations) {
      this._name = name;
      this._violations = Collections.unmodifiableSet(EnumSet.copyOf((Collection)Objects.requireNonNull(violations)));
   }

   public boolean allows(ComplianceViolation violation) {
      return this._violations.contains(violation);
   }

   public String getName() {
      return this._name;
   }

   public Set getKnown() {
      return EnumSet.allOf(Violation.class);
   }

   public Set getAllowed() {
      return this._violations;
   }

   public boolean compliesWith(CookieCompliance mode) {
      return this == mode || this.getAllowed().containsAll(mode.getAllowed());
   }

   public String toString() {
      return String.format("%s@%x%s", this._name, this.hashCode(), this._violations);
   }

   static {
      RFC6265 = new CookieCompliance("RFC6265", EnumSet.of(CookieCompliance.Violation.INVALID_COOKIES, CookieCompliance.Violation.OPTIONAL_WHITE_SPACE, CookieCompliance.Violation.SPACE_IN_VALUES));
      RFC6265_STRICT = new CookieCompliance("RFC6265_STRICT", EnumSet.noneOf(Violation.class));
      RFC6265_LEGACY = new CookieCompliance("RFC6265_LEGACY", EnumSet.of(CookieCompliance.Violation.ATTRIBUTES, CookieCompliance.Violation.BAD_QUOTES, CookieCompliance.Violation.ESCAPE_IN_QUOTES, CookieCompliance.Violation.INVALID_COOKIES, CookieCompliance.Violation.OPTIONAL_WHITE_SPACE, CookieCompliance.Violation.SPECIAL_CHARS_IN_QUOTES, CookieCompliance.Violation.SPACE_IN_VALUES));
      RFC2965_LEGACY = new CookieCompliance("RFC2965_LEGACY", EnumSet.allOf(Violation.class));
      RFC2965 = new CookieCompliance("RFC2965", EnumSet.complementOf(EnumSet.of(CookieCompliance.Violation.BAD_QUOTES, CookieCompliance.Violation.COMMA_NOT_VALID_OCTET, CookieCompliance.Violation.RESERVED_NAMES_NOT_DOLLAR_PREFIXED)));
      KNOWN_MODES = Arrays.asList(RFC6265, RFC6265_STRICT, RFC6265_LEGACY, RFC2965, RFC2965_LEGACY);
      __custom = new AtomicInteger();
   }

   public static enum Violation implements ComplianceViolation {
      /** @deprecated */
      @Deprecated
      COMMA_NOT_VALID_OCTET("https://tools.ietf.org/html/rfc6265#section-4.2.1", "Comma not valid as cookie-octet or separator"),
      COMMA_SEPARATOR("https://www.rfc-editor.org/rfc/rfc2965.html", "Comma cookie separator"),
      /** @deprecated */
      @Deprecated
      RESERVED_NAMES_NOT_DOLLAR_PREFIXED("https://tools.ietf.org/html/rfc6265#section-4.2.1", "Reserved name no longer use '$' prefix"),
      SPECIAL_CHARS_IN_QUOTES("https://www.rfc-editor.org/rfc/rfc6265#section-4.2.1", "Special character cannot be quoted"),
      ESCAPE_IN_QUOTES("https://www.rfc-editor.org/rfc/rfc2616#section-2.2", "Escaped character in quotes"),
      BAD_QUOTES("https://www.rfc-editor.org/rfc/rfc2616#section-2.2", "Bad quotes"),
      INVALID_COOKIES("https://tools.ietf.org/html/rfc6265", "Invalid cookie"),
      ATTRIBUTES("https://www.rfc-editor.org/rfc/rfc6265#section-4.2.1", "Cookie attribute"),
      ATTRIBUTE_VALUES("https://www.rfc-editor.org/rfc/rfc6265#section-4.2.1", "Cookie attribute value"),
      OPTIONAL_WHITE_SPACE("https://www.rfc-editor.org/rfc/rfc6265#section-5.2", "White space around name/value"),
      SPACE_IN_VALUES("https://www.rfc-editor.org/rfc/rfc6265#section-5.2", "Space in value");

      private final String url;
      private final String description;

      private Violation(String url, String description) {
         this.url = url;
         this.description = description;
      }

      public String getName() {
         return this.name();
      }

      public String getURL() {
         return this.url;
      }

      public String getDescription() {
         return this.description;
      }

      // $FF: synthetic method
      private static Violation[] $values() {
         return new Violation[]{COMMA_NOT_VALID_OCTET, COMMA_SEPARATOR, RESERVED_NAMES_NOT_DOLLAR_PREFIXED, SPECIAL_CHARS_IN_QUOTES, ESCAPE_IN_QUOTES, BAD_QUOTES, INVALID_COOKIES, ATTRIBUTES, ATTRIBUTE_VALUES, OPTIONAL_WHITE_SPACE, SPACE_IN_VALUES};
      }
   }
}
