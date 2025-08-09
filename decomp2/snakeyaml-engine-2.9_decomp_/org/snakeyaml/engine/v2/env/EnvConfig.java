package org.snakeyaml.engine.v2.env;

import java.util.Optional;

public interface EnvConfig {
   default Optional getValueFor(String name, String separator, String value, String environment) {
      return Optional.empty();
   }
}
