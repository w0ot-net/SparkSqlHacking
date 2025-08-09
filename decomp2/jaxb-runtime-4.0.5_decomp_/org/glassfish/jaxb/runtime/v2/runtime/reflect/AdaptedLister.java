package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.Coordinator;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.xml.sax.SAXException;

final class AdaptedLister extends Lister {
   private final Lister core;
   private final Class adapter;

   AdaptedLister(Lister core, Class adapter) {
      this.core = core;
      this.adapter = adapter;
   }

   private XmlAdapter getAdapter() {
      return Coordinator._getInstance().getAdapter(this.adapter);
   }

   public ListIterator iterator(Object prop, XMLSerializer context) {
      return new ListIteratorImpl(this.core.iterator(prop, context), context);
   }

   public Object startPacking(Object bean, Accessor accessor) throws AccessorException {
      return this.core.startPacking(bean, accessor);
   }

   public void addToPack(Object pack, Object item) throws AccessorException {
      InMemItemT r;
      try {
         r = (InMemItemT)this.getAdapter().unmarshal(item);
      } catch (Exception e) {
         throw new AccessorException(e);
      }

      this.core.addToPack(pack, r);
   }

   public void endPacking(Object pack, Object bean, Accessor accessor) throws AccessorException {
      this.core.endPacking(pack, bean, accessor);
   }

   public void reset(Object bean, Accessor accessor) throws AccessorException {
      this.core.reset(bean, accessor);
   }

   private final class ListIteratorImpl implements ListIterator {
      private final ListIterator core;
      private final XMLSerializer serializer;

      public ListIteratorImpl(ListIterator core, XMLSerializer serializer) {
         this.core = core;
         this.serializer = serializer;
      }

      public boolean hasNext() {
         return this.core.hasNext();
      }

      public Object next() throws SAXException, JAXBException {
         InMemItemT next = (InMemItemT)this.core.next();

         try {
            return AdaptedLister.this.getAdapter().marshal(next);
         } catch (Exception e) {
            this.serializer.reportError((String)null, e);
            return null;
         }
      }
   }
}
