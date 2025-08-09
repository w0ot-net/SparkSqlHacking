package org.apache.logging.log4j.core.lookup;

import java.util.Map;
import java.util.Properties;

public final class RuntimeStrSubstitutor extends StrSubstitutor {
   public RuntimeStrSubstitutor() {
   }

   public RuntimeStrSubstitutor(final Map valueMap) {
      super(valueMap);
   }

   public RuntimeStrSubstitutor(final Properties properties) {
      super(properties);
   }

   public RuntimeStrSubstitutor(final StrLookup lookup) {
      super(lookup);
   }

   public RuntimeStrSubstitutor(final StrSubstitutor other) {
      super(other);
   }

   public String toString() {
      return "RuntimeStrSubstitutor{" + super.toString() + "}";
   }
}
