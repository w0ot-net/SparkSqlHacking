package org.snakeyaml.engine.v2.exceptions;

import java.util.Optional;

public class ConstructorException extends MarkedYamlEngineException {
   public ConstructorException(String context, Optional contextMark, String problem, Optional problemMark, Throwable cause) {
      super(context, contextMark, problem, problemMark, cause);
   }

   public ConstructorException(String context, Optional contextMark, String problem, Optional problemMark) {
      this(context, contextMark, problem, problemMark, (Throwable)null);
   }
}
