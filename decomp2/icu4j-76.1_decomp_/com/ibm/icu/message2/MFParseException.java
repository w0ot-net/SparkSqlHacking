package com.ibm.icu.message2;

import java.text.ParseException;

/** @deprecated */
@Deprecated
public class MFParseException extends ParseException {
   private static final long serialVersionUID = -7634219305388292407L;

   public MFParseException(String message, int errorOffset) {
      super(message, errorOffset);
   }

   public String getMessage() {
      return super.getMessage();
   }
}
