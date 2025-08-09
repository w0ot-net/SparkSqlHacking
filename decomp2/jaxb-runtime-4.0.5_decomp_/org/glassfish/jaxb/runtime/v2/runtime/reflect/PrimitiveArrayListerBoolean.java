package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;

final class PrimitiveArrayListerBoolean extends Lister {
   private PrimitiveArrayListerBoolean() {
   }

   static void register() {
      Lister.primitiveArrayListers.put(Boolean.TYPE, new PrimitiveArrayListerBoolean());
   }

   public ListIterator iterator(final boolean[] objects, XMLSerializer context) {
      return new ListIterator() {
         int idx = 0;

         public boolean hasNext() {
            return this.idx < objects.length;
         }

         public Boolean next() {
            return objects[this.idx++];
         }
      };
   }

   public BooleanArrayPack startPacking(Object current, Accessor acc) {
      return new BooleanArrayPack();
   }

   public void addToPack(BooleanArrayPack objects, Boolean o) {
      objects.add(o);
   }

   public void endPacking(BooleanArrayPack pack, Object bean, Accessor acc) throws AccessorException {
      acc.set(bean, pack.build());
   }

   public void reset(Object o, Accessor acc) throws AccessorException {
      acc.set(o, new boolean[0]);
   }

   static final class BooleanArrayPack {
      boolean[] buf = new boolean[16];
      int size;

      void add(Boolean b) {
         if (this.buf.length == this.size) {
            boolean[] nb = new boolean[this.buf.length * 2];
            System.arraycopy(this.buf, 0, nb, 0, this.buf.length);
            this.buf = nb;
         }

         if (b != null) {
            this.buf[this.size++] = b;
         }

      }

      boolean[] build() {
         if (this.buf.length == this.size) {
            return this.buf;
         } else {
            boolean[] r = new boolean[this.size];
            System.arraycopy(this.buf, 0, r, 0, this.size);
            return r;
         }
      }
   }
}
