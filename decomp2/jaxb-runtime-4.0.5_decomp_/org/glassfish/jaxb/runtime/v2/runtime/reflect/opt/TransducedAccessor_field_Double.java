package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.DefaultTransducedAccessor;

public final class TransducedAccessor_field_Double extends DefaultTransducedAccessor {
   public String print(Object o) {
      return DatatypeConverterImpl._printDouble(((Bean)o).f_double);
   }

   public void parse(Object o, CharSequence lexical) {
      ((Bean)o).f_double = DatatypeConverterImpl._parseDouble(lexical);
   }

   public boolean hasValue(Object o) {
      return true;
   }
}
