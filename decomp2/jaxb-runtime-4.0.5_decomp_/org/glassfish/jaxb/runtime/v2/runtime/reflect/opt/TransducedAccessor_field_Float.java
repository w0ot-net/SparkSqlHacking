package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.DefaultTransducedAccessor;

public final class TransducedAccessor_field_Float extends DefaultTransducedAccessor {
   public String print(Object o) {
      return DatatypeConverterImpl._printFloat(((Bean)o).f_float);
   }

   public void parse(Object o, CharSequence lexical) {
      ((Bean)o).f_float = DatatypeConverterImpl._parseFloat(lexical);
   }

   public boolean hasValue(Object o) {
      return true;
   }
}
