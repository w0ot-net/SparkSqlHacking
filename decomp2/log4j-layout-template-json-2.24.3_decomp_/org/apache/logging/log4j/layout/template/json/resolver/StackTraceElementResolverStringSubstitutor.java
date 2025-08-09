package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Objects;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;

final class StackTraceElementResolverStringSubstitutor implements TemplateResolverStringSubstitutor {
   private final StrSubstitutor substitutor;

   StackTraceElementResolverStringSubstitutor(final StrSubstitutor substitutor) {
      this.substitutor = (StrSubstitutor)Objects.requireNonNull(substitutor, "substitutor");
   }

   public StrSubstitutor getInternalSubstitutor() {
      return this.substitutor;
   }

   public boolean isStable() {
      return true;
   }

   public String replace(final StackTraceElement ignored, final String source) {
      return this.substitutor.replace((LogEvent)null, source);
   }
}
