package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.DefaultTransducedAccessor;

public final class TransducedAccessor_method_Byte extends DefaultTransducedAccessor {
   public String print(Object o) {
      return DatatypeConverterImpl._printByte(((Bean)o).get_byte());
   }

   public void parse(Object o, CharSequence lexical) {
      ((Bean)o).set_byte(DatatypeConverterImpl._parseByte(lexical));
   }

   public boolean hasValue(Object o) {
      return true;
   }
}
