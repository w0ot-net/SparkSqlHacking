package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.Objects;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;

public final class EventResolverStringSubstitutor implements TemplateResolverStringSubstitutor {
   private final StrSubstitutor substitutor;

   public EventResolverStringSubstitutor(final StrSubstitutor substitutor) {
      this.substitutor = (StrSubstitutor)Objects.requireNonNull(substitutor, "substitutor");
   }

   public StrSubstitutor getInternalSubstitutor() {
      return this.substitutor;
   }

   public boolean isStable() {
      return false;
   }

   public String replace(final LogEvent logEvent, final String source) {
      return this.substitutor.replace(logEvent, source);
   }
}
