package jodd.typeconverter;

import jodd.exception.UncheckedException;

public class TypeConversionException extends UncheckedException {
   public TypeConversionException(Throwable t) {
      super(t);
   }

   public TypeConversionException(String message) {
      super(message);
   }

   public TypeConversionException(String message, Throwable t) {
      super(message, t);
   }

   public TypeConversionException(Object value) {
      this("Conversion failed: " + value);
   }

   public TypeConversionException(Object value, Throwable t) {
      this("Conversion failed: " + value, t);
   }
}
