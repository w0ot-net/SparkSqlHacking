package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import jakarta.xml.bind.JAXBElement;
import java.util.Collection;
import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.xml.sax.SAXException;

public class XsiNilLoader extends ProxyLoader {
   private final Loader defaultLoader;

   public XsiNilLoader(Loader defaultLoader) {
      this.defaultLoader = defaultLoader;

      assert defaultLoader != null;

   }

   protected Loader selectLoader(UnmarshallingContext.State state, TagName ea) throws SAXException {
      int idx = ea.atts.getIndex("http://www.w3.org/2001/XMLSchema-instance", "nil");
      if (idx != -1) {
         Boolean b = DatatypeConverterImpl._parseBoolean(ea.atts.getValue(idx));
         if (b != null && b) {
            this.onNil(state);
            boolean hasOtherAttributes = ea.atts.getLength() - 1 > 0;
            if (!hasOtherAttributes || !(state.getPrev().getTarget() instanceof JAXBElement)) {
               return Discarder.INSTANCE;
            }
         }
      }

      return this.defaultLoader;
   }

   public Collection getExpectedChildElements() {
      return this.defaultLoader.getExpectedChildElements();
   }

   public Collection getExpectedAttributes() {
      return this.defaultLoader.getExpectedAttributes();
   }

   protected void onNil(UnmarshallingContext.State state) throws SAXException {
   }

   public static final class Single extends XsiNilLoader {
      private final Accessor acc;

      public Single(Loader l, Accessor acc) {
         super(l);
         this.acc = acc;
      }

      protected void onNil(UnmarshallingContext.State state) throws SAXException {
         try {
            this.acc.set(state.getPrev().getTarget(), (Object)null);
            state.getPrev().setNil(true);
         } catch (AccessorException e) {
            handleGenericException(e, true);
         }

      }
   }

   public static final class Array extends XsiNilLoader {
      public Array(Loader core) {
         super(core);
      }

      protected void onNil(UnmarshallingContext.State state) {
         state.setTarget((Object)null);
      }
   }
}
