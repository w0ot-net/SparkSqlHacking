package org.snakeyaml.engine.v2.exceptions;

import java.util.Optional;

public class DuplicateKeyException extends ConstructorException {
   public DuplicateKeyException(Optional contextMark, Object key, Optional problemMark) {
      super("while constructing a mapping", contextMark, "found duplicate key " + key.toString(), problemMark);
   }
}
