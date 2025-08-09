package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;

final class PrimitiveArrayListerShort extends Lister {
   private PrimitiveArrayListerShort() {
   }

   static void register() {
      Lister.primitiveArrayListers.put(Short.TYPE, new PrimitiveArrayListerShort());
   }

   public ListIterator iterator(final short[] objects, XMLSerializer context) {
      return new ListIterator() {
         int idx = 0;

         public boolean hasNext() {
            return this.idx < objects.length;
         }

         public Short next() {
            return objects[this.idx++];
         }
      };
   }

   public ShortArrayPack startPacking(Object current, Accessor acc) {
      return new ShortArrayPack();
   }

   public void addToPack(ShortArrayPack objects, Short o) {
      objects.add(o);
   }

   public void endPacking(ShortArrayPack pack, Object bean, Accessor acc) throws AccessorException {
      acc.set(bean, pack.build());
   }

   public void reset(Object o, Accessor acc) throws AccessorException {
      acc.set(o, new short[0]);
   }

   static final class ShortArrayPack {
      short[] buf = new short[16];
      int size;

      void add(Short b) {
         if (this.buf.length == this.size) {
            short[] nb = new short[this.buf.length * 2];
            System.arraycopy(this.buf, 0, nb, 0, this.buf.length);
            this.buf = nb;
         }

         if (b != null) {
            this.buf[this.size++] = b;
         }

      }

      short[] build() {
         if (this.buf.length == this.size) {
            return this.buf;
         } else {
            short[] r = new short[this.size];
            System.arraycopy(this.buf, 0, r, 0, this.size);
            return r;
         }
      }
   }
}
