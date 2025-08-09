package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import jakarta.xml.bind.JAXBException;
import org.glassfish.jaxb.core.WhiteSpaceProcessor;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.xml.sax.SAXException;

public final class ListTransducedAccessorImpl extends DefaultTransducedAccessor {
   private final Transducer xducer;
   private final Lister lister;
   private final Accessor acc;

   public ListTransducedAccessorImpl(Transducer xducer, Accessor acc, Lister lister) {
      this.xducer = xducer;
      this.lister = lister;
      this.acc = acc;
   }

   public boolean useNamespace() {
      return this.xducer.useNamespace();
   }

   public void declareNamespace(Object bean, XMLSerializer w) throws AccessorException, SAXException {
      ListT list = (ListT)this.acc.get(bean);
      if (list != null) {
         ListIterator<ItemT> itr = this.lister.iterator(list, w);

         while(itr.hasNext()) {
            try {
               ItemT item = (ItemT)itr.next();
               if (item != null) {
                  this.xducer.declareNamespace(item, w);
               }
            } catch (JAXBException e) {
               w.reportError((String)null, e);
            }
         }
      }

   }

   public String print(Object o) throws AccessorException, SAXException {
      ListT list = (ListT)this.acc.get(o);
      if (list == null) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder();
         XMLSerializer w = XMLSerializer.getInstance();
         ListIterator<ItemT> itr = this.lister.iterator(list, w);

         while(itr.hasNext()) {
            try {
               ItemT item = (ItemT)itr.next();
               if (item != null) {
                  if (buf.length() > 0) {
                     buf.append(' ');
                  }

                  buf.append(this.xducer.print(item));
               }
            } catch (JAXBException e) {
               w.reportError((String)null, e);
            }
         }

         return buf.toString();
      }
   }

   private void processValue(Object bean, CharSequence s) throws AccessorException, SAXException {
      PackT pack = (PackT)this.lister.startPacking(bean, this.acc);
      int idx = 0;
      int len = s.length();

      while(true) {
         int p;
         for(p = idx; p < len && !WhiteSpaceProcessor.isWhiteSpace(s.charAt(p)); ++p) {
         }

         CharSequence token = s.subSequence(idx, p);
         if (!token.equals("")) {
            this.lister.addToPack(pack, this.xducer.parse(token));
         }

         if (p == len) {
            break;
         }

         while(p < len && WhiteSpaceProcessor.isWhiteSpace(s.charAt(p))) {
            ++p;
         }

         if (p == len) {
            break;
         }

         idx = p;
      }

      this.lister.endPacking(pack, bean, this.acc);
   }

   public void parse(Object bean, CharSequence lexical) throws AccessorException, SAXException {
      this.processValue(bean, lexical);
   }

   public boolean hasValue(Object bean) throws AccessorException {
      return this.acc.get(bean) != null;
   }
}
