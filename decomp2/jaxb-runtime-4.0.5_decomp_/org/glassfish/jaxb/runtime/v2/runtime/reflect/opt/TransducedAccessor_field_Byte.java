package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.DefaultTransducedAccessor;

public final class TransducedAccessor_field_Byte extends DefaultTransducedAccessor {
   public String print(Object o) {
      return DatatypeConverterImpl._printByte(((Bean)o).f_byte);
   }

   public void parse(Object o, CharSequence lexical) {
      ((Bean)o).f_byte = DatatypeConverterImpl._parseByte(lexical);
   }

   public boolean hasValue(Object o) {
      return true;
   }
}
