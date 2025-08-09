package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.Coordinator;

final class AdaptedAccessor extends Accessor {
   private final Accessor core;
   private final Class adapter;
   private XmlAdapter staticAdapter;

   AdaptedAccessor(Class targetType, Accessor extThis, Class adapter) {
      super(targetType);
      this.core = extThis;
      this.adapter = adapter;
   }

   public boolean isAdapted() {
      return true;
   }

   public Object get(Object bean) throws AccessorException {
      InMemValueT v = (InMemValueT)this.core.get(bean);
      XmlAdapter<OnWireValueT, InMemValueT> a = this.getAdapter();

      try {
         return a.marshal(v);
      } catch (Exception e) {
         throw new AccessorException(e);
      }
   }

   public void set(Object bean, Object o) throws AccessorException {
      XmlAdapter<OnWireValueT, InMemValueT> a = this.getAdapter();

      try {
         this.core.set(bean, o == null ? null : a.unmarshal(o));
      } catch (Exception e) {
         throw new AccessorException(e);
      }
   }

   public Object getUnadapted(Object bean) throws AccessorException {
      return this.core.getUnadapted(bean);
   }

   public void setUnadapted(Object bean, Object value) throws AccessorException {
      this.core.setUnadapted(bean, value);
   }

   private XmlAdapter getAdapter() {
      Coordinator coordinator = Coordinator._getInstance();
      if (coordinator != null) {
         return coordinator.getAdapter(this.adapter);
      } else {
         synchronized(this) {
            if (this.staticAdapter == null) {
               this.staticAdapter = (XmlAdapter)ClassFactory.create(this.adapter);
            }
         }

         return this.staticAdapter;
      }
   }
}
