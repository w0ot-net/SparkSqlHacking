package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.DefaultTransducedAccessor;

public final class TransducedAccessor_method_Short extends DefaultTransducedAccessor {
   public String print(Object o) {
      return DatatypeConverterImpl._printShort(((Bean)o).get_short());
   }

   public void parse(Object o, CharSequence lexical) {
      ((Bean)o).set_short(DatatypeConverterImpl._parseShort(lexical));
   }

   public boolean hasValue(Object o) {
      return true;
   }
}
