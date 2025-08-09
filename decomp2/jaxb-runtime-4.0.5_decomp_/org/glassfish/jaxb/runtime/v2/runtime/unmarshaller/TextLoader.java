package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.xml.sax.SAXException;

public class TextLoader extends Loader {
   private final Transducer xducer;

   public TextLoader(Transducer xducer) {
      super(true);
      this.xducer = xducer;
   }

   public void text(UnmarshallingContext.State state, CharSequence text) throws SAXException {
      try {
         state.setTarget(this.xducer.parse(text));
      } catch (AccessorException e) {
         handleGenericException(e, true);
      } catch (RuntimeException e) {
         handleParseConversionException(state, e);
      }

   }
}
