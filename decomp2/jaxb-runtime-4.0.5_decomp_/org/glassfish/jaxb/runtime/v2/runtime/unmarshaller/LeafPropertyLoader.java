package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.xml.sax.SAXException;

public class LeafPropertyLoader extends Loader {
   private final TransducedAccessor xacc;

   public LeafPropertyLoader(TransducedAccessor xacc) {
      super(true);
      this.xacc = xacc;
   }

   public void text(UnmarshallingContext.State state, CharSequence text) throws SAXException {
      try {
         this.xacc.parse(state.getPrev().getTarget(), text);
      } catch (AccessorException e) {
         handleGenericException(e, true);
      } catch (RuntimeException e) {
         handleParseConversionException(state, e);
      }

   }
}
