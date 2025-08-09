package org.snakeyaml.engine.v2.exceptions;

import java.util.Objects;
import java.util.Optional;

public class ComposerException extends MarkedYamlEngineException {
   public ComposerException(String context, Optional contextMark, String problem, Optional problemMark) {
      super(context, contextMark, problem, problemMark);
      Objects.requireNonNull(context);
   }

   public ComposerException(String problem, Optional problemMark) {
      super("", Optional.empty(), problem, problemMark);
   }
}
