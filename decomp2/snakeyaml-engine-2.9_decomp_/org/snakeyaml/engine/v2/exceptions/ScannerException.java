package org.snakeyaml.engine.v2.exceptions;

import java.util.Optional;

public class ScannerException extends MarkedYamlEngineException {
   public ScannerException(String context, Optional contextMark, String problem, Optional problemMark) {
      super(context, contextMark, problem, problemMark, (Throwable)null);
   }

   public ScannerException(String problem, Optional problemMark) {
      super((String)null, Optional.empty(), problem, problemMark, (Throwable)null);
   }
}
