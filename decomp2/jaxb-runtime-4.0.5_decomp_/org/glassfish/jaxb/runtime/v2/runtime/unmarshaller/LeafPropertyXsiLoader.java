package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import java.util.Collection;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.v2.runtime.ClassBeanInfoImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public final class LeafPropertyXsiLoader extends Loader {
   private final Loader defaultLoader;
   private final TransducedAccessor xacc;
   private final Accessor acc;

   public LeafPropertyXsiLoader(Loader defaultLoader, TransducedAccessor xacc, Accessor acc) {
      this.defaultLoader = defaultLoader;
      this.expectText = true;
      this.xacc = xacc;
      this.acc = acc;
   }

   public void startElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
      Loader loader = this.selectLoader(state, ea);
      state.setLoader(loader);
      loader.startElement(state, ea);
   }

   protected Loader selectLoader(UnmarshallingContext.State state, TagName ea) throws SAXException {
      UnmarshallingContext context = state.getContext();
      JaxBeanInfo beanInfo = null;
      Attributes atts = ea.atts;
      int idx = atts.getIndex("http://www.w3.org/2001/XMLSchema-instance", "type");
      if (idx >= 0) {
         String value = atts.getValue(idx);
         QName type = DatatypeConverterImpl._parseQName(value, context);
         if (type == null) {
            return this.defaultLoader;
         } else {
            beanInfo = context.getJAXBContext().getGlobalType(type);
            if (beanInfo == null) {
               return this.defaultLoader;
            } else {
               ClassBeanInfoImpl cbii;
               try {
                  cbii = (ClassBeanInfoImpl)beanInfo;
               } catch (ClassCastException var11) {
                  return this.defaultLoader;
               }

               return (Loader)(null == cbii.getTransducer() ? this.defaultLoader : new LeafPropertyLoader(new TransducedAccessor.CompositeTransducedAccessorImpl(state.getContext().getJAXBContext(), cbii.getTransducer(), this.acc)));
            }
         }
      } else {
         return this.defaultLoader;
      }
   }

   public Collection getExpectedChildElements() {
      return this.defaultLoader.getExpectedChildElements();
   }

   public Collection getExpectedAttributes() {
      return this.defaultLoader.getExpectedAttributes();
   }
}
