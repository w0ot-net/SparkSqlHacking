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

public final class HttpCompliance implements ComplianceViolation.Mode {
   protected static final Logger LOG = LoggerFactory.getLogger(HttpCompliance.class);
   public static final String VIOLATIONS_ATTR = "org.sparkproject.jetty.http.compliance.violations";
   public static final HttpCompliance RFC7230 = new HttpCompliance("RFC7230", EnumSet.noneOf(Violation.class));
   public static final HttpCompliance RFC2616;
   public static final HttpCompliance LEGACY;
   public static final HttpCompliance RFC2616_LEGACY;
   public static final HttpCompliance RFC7230_LEGACY;
   private static final List KNOWN_MODES;
   private static final AtomicInteger __custom;
   private final String _name;
   private final Set _violations;

   public static HttpCompliance valueOf(String name) {
      for(HttpCompliance compliance : KNOWN_MODES) {
         if (compliance.getName().equals(name)) {
            return compliance;
         }
      }

      LOG.warn("Unknown HttpCompliance mode {}", name);
      return null;
   }

   public static HttpCompliance from(String spec) {
      String[] elements = spec.split("\\s*,\\s*");
      Set<Violation> sections;
      switch (elements[0]) {
         case "0":
            sections = EnumSet.noneOf(Violation.class);
            break;
         case "*":
            sections = EnumSet.allOf(Violation.class);
            break;
         default:
            HttpCompliance mode = valueOf(elements[0]);
            sections = (Set<Violation>)(mode == null ? EnumSet.noneOf(Violation.class) : copyOf(mode.getAllowed()));
      }

      for(int i = 1; i < elements.length; ++i) {
         String element = elements[i];
         boolean exclude = element.startsWith("-");
         if (exclude) {
            element = element.substring(1);
         }

         Violation section = HttpCompliance.Violation.valueOf(element);
         if (exclude) {
            sections.remove(section);
         } else {
            sections.add(section);
         }
      }

      return new HttpCompliance("CUSTOM" + __custom.getAndIncrement(), sections);
   }

   private HttpCompliance(String name, Set violations) {
      Objects.requireNonNull(violations);
      this._name = name;
      this._violations = Collections.unmodifiableSet((Set)(violations.isEmpty() ? EnumSet.noneOf(Violation.class) : copyOf(violations)));
   }

   public boolean allows(ComplianceViolation violation) {
      return violation instanceof Violation && this._violations.contains(violation);
   }

   public String getName() {
      return this._name;
   }

   public Set getAllowed() {
      return this._violations;
   }

   public Set getKnown() {
      return EnumSet.allOf(Violation.class);
   }

   public HttpCompliance with(String name, Violation... violations) {
      Set<Violation> union = (Set<Violation>)(this._violations.isEmpty() ? EnumSet.noneOf(Violation.class) : copyOf(this._violations));
      union.addAll(copyOf(violations));
      return new HttpCompliance(name, union);
   }

   public HttpCompliance without(String name, Violation... violations) {
      Set<Violation> remainder = (Set<Violation>)(this._violations.isEmpty() ? EnumSet.noneOf(Violation.class) : copyOf(this._violations));
      remainder.removeAll(copyOf(violations));
      return new HttpCompliance(name, remainder);
   }

   public String toString() {
      return String.format("%s%s", this._name, this._violations);
   }

   private static Set copyOf(Violation[] violations) {
      return violations != null && violations.length != 0 ? EnumSet.copyOf(Arrays.asList(violations)) : EnumSet.noneOf(Violation.class);
   }

   private static Set copyOf(Set violations) {
      return violations != null && !violations.isEmpty() ? EnumSet.copyOf(violations) : EnumSet.noneOf(Violation.class);
   }

   static {
      RFC2616 = new HttpCompliance("RFC2616", EnumSet.of(HttpCompliance.Violation.HTTP_0_9, HttpCompliance.Violation.MULTILINE_FIELD_VALUE, HttpCompliance.Violation.MISMATCHED_AUTHORITY));
      LEGACY = new HttpCompliance("LEGACY", EnumSet.complementOf(EnumSet.of(HttpCompliance.Violation.CASE_INSENSITIVE_METHOD)));
      RFC2616_LEGACY = RFC2616.with("RFC2616_LEGACY", HttpCompliance.Violation.CASE_INSENSITIVE_METHOD, HttpCompliance.Violation.NO_COLON_AFTER_FIELD_NAME, HttpCompliance.Violation.TRANSFER_ENCODING_WITH_CONTENT_LENGTH, HttpCompliance.Violation.MULTIPLE_CONTENT_LENGTHS);
      RFC7230_LEGACY = RFC7230.with("RFC7230_LEGACY", HttpCompliance.Violation.CASE_INSENSITIVE_METHOD);
      KNOWN_MODES = Arrays.asList(RFC7230, RFC2616, LEGACY, RFC2616_LEGACY, RFC7230_LEGACY);
      __custom = new AtomicInteger();
   }

   public static enum Violation implements ComplianceViolation {
      CASE_SENSITIVE_FIELD_NAME("https://tools.ietf.org/html/rfc7230#section-3.2", "Field name is case-insensitive"),
      CASE_INSENSITIVE_METHOD("https://tools.ietf.org/html/rfc7230#section-3.1.1", "Method is case-sensitive"),
      HTTP_0_9("https://tools.ietf.org/html/rfc7230#appendix-A.2", "HTTP/0.9 not supported"),
      MULTILINE_FIELD_VALUE("https://tools.ietf.org/html/rfc7230#section-3.2.4", "Line Folding not supported"),
      MULTIPLE_CONTENT_LENGTHS("https://tools.ietf.org/html/rfc7230#section-3.3.2", "Multiple Content-Lengths"),
      TRANSFER_ENCODING_WITH_CONTENT_LENGTH("https://tools.ietf.org/html/rfc7230#section-3.3.1", "Transfer-Encoding and Content-Length"),
      WHITESPACE_AFTER_FIELD_NAME("https://tools.ietf.org/html/rfc7230#section-3.2.4", "Whitespace not allowed after field name"),
      NO_COLON_AFTER_FIELD_NAME("https://tools.ietf.org/html/rfc7230#section-3.2", "Fields must have a Colon"),
      DUPLICATE_HOST_HEADERS("https://www.rfc-editor.org/rfc/rfc7230#section-5.4", "Duplicate Host Header"),
      UNSAFE_HOST_HEADER("https://www.rfc-editor.org/rfc/rfc7230#section-2.7.1", "Invalid Authority"),
      MISMATCHED_AUTHORITY("https://www.rfc-editor.org/rfc/rfc7230#section-5.4", "Mismatched Authority");

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
         return new Violation[]{CASE_SENSITIVE_FIELD_NAME, CASE_INSENSITIVE_METHOD, HTTP_0_9, MULTILINE_FIELD_VALUE, MULTIPLE_CONTENT_LENGTHS, TRANSFER_ENCODING_WITH_CONTENT_LENGTH, WHITESPACE_AFTER_FIELD_NAME, NO_COLON_AFTER_FIELD_NAME, DUPLICATE_HOST_HEADERS, UNSAFE_HOST_HEADER, MISMATCHED_AUTHORITY};
      }
   }
}
