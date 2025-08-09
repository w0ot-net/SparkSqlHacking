package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import jakarta.xml.bind.JAXBElement;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.xml.sax.SAXException;

public class ValuePropertyLoader extends Loader {
   private final TransducedAccessor xacc;

   public ValuePropertyLoader(TransducedAccessor xacc) {
      super(true);
      this.xacc = xacc;
   }

   public void text(UnmarshallingContext.State state, CharSequence text) throws SAXException {
      try {
         this.xacc.parse(state.getTarget(), text);
      } catch (AccessorException e) {
         handleGenericException(e, true);
      } catch (RuntimeException e) {
         if (state.getPrev() != null) {
            if (!(state.getPrev().getTarget() instanceof JAXBElement)) {
               handleParseConversionException(state, e);
            }
         } else {
            handleParseConversionException(state, e);
         }
      }

   }
}
