package org.snakeyaml.engine.v2.exceptions;

import java.util.Optional;

public class ParserException extends MarkedYamlEngineException {
   public ParserException(String context, Optional contextMark, String problem, Optional problemMark) {
      super(context, contextMark, problem, problemMark, (Throwable)null);
   }

   public ParserException(String problem, Optional problemMark) {
      super((String)null, Optional.empty(), problem, problemMark, (Throwable)null);
   }
}
