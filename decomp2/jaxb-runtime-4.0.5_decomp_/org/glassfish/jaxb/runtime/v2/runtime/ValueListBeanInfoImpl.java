package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.FinalArrayList;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.WhiteSpaceProcessor;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfo;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

final class ValueListBeanInfoImpl extends JaxBeanInfo {
   private final Class itemType;
   private final Transducer xducer;
   private final Loader loader = new Loader(true) {
      public void text(UnmarshallingContext.State state, CharSequence text) throws SAXException {
         List<Object> r = new FinalArrayList();
         int idx = 0;
         int len = text.length();

         while(true) {
            int p;
            while(true) {
               for(p = idx; p < len && !WhiteSpaceProcessor.isWhiteSpace(text.charAt(p)); ++p) {
               }

               CharSequence token = text.subSequence(idx, p);
               if (token.equals("")) {
                  break;
               }

               try {
                  r.add(ValueListBeanInfoImpl.this.xducer.parse(token));
                  break;
               } catch (AccessorException e) {
                  handleGenericException(e, true);
               }
            }

            if (p == len) {
               break;
            }

            while(p < len && WhiteSpaceProcessor.isWhiteSpace(text.charAt(p))) {
               ++p;
            }

            if (p == len) {
               break;
            }

            idx = p;
         }

         state.setTarget(ValueListBeanInfoImpl.this.toArray(r));
      }
   };

   public ValueListBeanInfoImpl(JAXBContextImpl owner, Class arrayType) throws JAXBException {
      super(owner, (RuntimeTypeInfo)null, arrayType, false, true, false);
      this.itemType = this.jaxbType.getComponentType();
      this.xducer = owner.getBeanInfo(arrayType.getComponentType(), true).getTransducer();

      assert this.xducer != null;

   }

   private Object toArray(List list) {
      int len = list.size();
      Object array = Array.newInstance(this.itemType, len);

      for(int i = 0; i < len; ++i) {
         Array.set(array, i, list.get(i));
      }

      return array;
   }

   public void serializeBody(Object array, XMLSerializer target) throws SAXException, IOException, XMLStreamException {
      int len = Array.getLength(array);

      for(int i = 0; i < len; ++i) {
         Object item = Array.get(array, i);

         try {
            this.xducer.writeText(target, item, "arrayItem");
         } catch (AccessorException e) {
            target.reportError("arrayItem", e);
         }
      }

   }

   public void serializeURIs(Object array, XMLSerializer target) throws SAXException {
      if (this.xducer.useNamespace()) {
         int len = Array.getLength(array);

         for(int i = 0; i < len; ++i) {
            Object item = Array.get(array, i);

            try {
               this.xducer.declareNamespace(item, target);
            } catch (AccessorException e) {
               target.reportError("arrayItem", e);
            }
         }
      }

   }

   public String getElementNamespaceURI(Object array) {
      throw new UnsupportedOperationException();
   }

   public String getElementLocalName(Object array) {
      throw new UnsupportedOperationException();
   }

   public Object createInstance(UnmarshallingContext context) {
      throw new UnsupportedOperationException();
   }

   public boolean reset(Object array, UnmarshallingContext context) {
      return false;
   }

   public String getId(Object array, XMLSerializer target) {
      return null;
   }

   public void serializeAttributes(Object array, XMLSerializer target) {
   }

   public void serializeRoot(Object array, XMLSerializer target) throws SAXException {
      target.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(array.getClass().getName()), (ValidationEventLocator)null, (Throwable)null));
   }

   public Transducer getTransducer() {
      return null;
   }

   public Loader getLoader(JAXBContextImpl context, boolean typeSubstitutionCapable) {
      return this.loader;
   }
}
