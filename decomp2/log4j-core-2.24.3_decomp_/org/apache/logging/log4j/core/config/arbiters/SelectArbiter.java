package org.apache.logging.log4j.core.config.arbiters;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;

@Plugin(
   name = "Select",
   category = "Core",
   elementType = "Arbiter",
   deferChildren = true,
   printObject = true
)
public class SelectArbiter {
   public Arbiter evaluateConditions(final List conditions) {
      Optional<Arbiter> opt = conditions.stream().filter((c) -> c instanceof DefaultArbiter).reduce((a, b) -> {
         throw new IllegalStateException("Multiple elements: " + a + ", " + b);
      });

      for(Arbiter condition : conditions) {
         if (!(condition instanceof DefaultArbiter) && condition.isCondition()) {
            return condition;
         }
      }

      return (Arbiter)opt.orElse((Object)null);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      public Builder asBuilder() {
         return this;
      }

      public SelectArbiter build() {
         return new SelectArbiter();
      }
   }
}
