package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.DefaultTransducedAccessor;

public final class TransducedAccessor_field_Short extends DefaultTransducedAccessor {
   public String print(Object o) {
      return DatatypeConverterImpl._printShort(((Bean)o).f_short);
   }

   public void parse(Object o, CharSequence lexical) {
      ((Bean)o).f_short = DatatypeConverterImpl._parseShort(lexical);
   }

   public boolean hasValue(Object o) {
      return true;
   }
}
