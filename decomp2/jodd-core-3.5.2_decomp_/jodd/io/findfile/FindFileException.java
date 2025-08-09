package jodd.io.findfile;

import jodd.exception.UncheckedException;

public class FindFileException extends UncheckedException {
   public FindFileException(Throwable t) {
      super(t);
   }

   public FindFileException(String message) {
      super(message);
   }

   public FindFileException(String message, Throwable t) {
      super(message, t);
   }
}
