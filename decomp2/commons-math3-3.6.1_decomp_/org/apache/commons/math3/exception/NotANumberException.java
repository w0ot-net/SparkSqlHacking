package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NotANumberException extends MathIllegalNumberException {
   private static final long serialVersionUID = 20120906L;

   public NotANumberException() {
      super(LocalizedFormats.NAN_NOT_ALLOWED, Double.NaN);
   }
}
