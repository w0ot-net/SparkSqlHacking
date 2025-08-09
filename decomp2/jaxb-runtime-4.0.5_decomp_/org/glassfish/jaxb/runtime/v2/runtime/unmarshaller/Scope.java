package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Lister;
import org.xml.sax.SAXException;

public final class Scope {
   public final UnmarshallingContext context;
   private Object bean;
   private Accessor acc;
   private Object pack;
   private Lister lister;

   Scope(UnmarshallingContext context) {
      this.context = context;
   }

   public boolean hasStarted() {
      return this.bean != null;
   }

   public void reset() {
      if (this.bean == null) {
         assert this.clean();

      } else {
         this.bean = null;
         this.acc = null;
         this.pack = null;
         this.lister = null;
      }
   }

   public void finish() throws AccessorException {
      if (this.hasStarted()) {
         this.lister.endPacking(this.pack, this.bean, this.acc);
         this.reset();
      }

      assert this.clean();

   }

   private boolean clean() {
      return this.bean == null && this.acc == null && this.pack == null && this.lister == null;
   }

   public void add(Accessor acc, Lister lister, Object value) throws SAXException {
      try {
         if (!this.hasStarted()) {
            this.bean = this.context.getCurrentState().getTarget();
            this.acc = acc;
            this.lister = lister;
            this.pack = lister.startPacking(this.bean, acc);
         }

         lister.addToPack(this.pack, value);
      } catch (AccessorException e) {
         Loader.handleGenericException(e, true);
         this.lister = Lister.getErrorInstance();
         this.acc = Accessor.getErrorInstance();
      }

   }

   public void start(Accessor acc, Lister lister) throws SAXException {
      try {
         if (!this.hasStarted()) {
            this.bean = this.context.getCurrentState().getTarget();
            this.acc = acc;
            this.lister = lister;
            this.pack = lister.startPacking(this.bean, acc);
         }
      } catch (AccessorException e) {
         Loader.handleGenericException(e, true);
         this.lister = Lister.getErrorInstance();
         this.acc = Accessor.getErrorInstance();
      }

   }
}
