package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;

final class PrimitiveArrayListerInteger extends Lister {
   private PrimitiveArrayListerInteger() {
   }

   static void register() {
      primitiveArrayListers.put(Integer.TYPE, new PrimitiveArrayListerInteger());
   }

   public ListIterator iterator(final int[] objects, XMLSerializer context) {
      return new ListIterator() {
         int idx = 0;

         public boolean hasNext() {
            return this.idx < objects.length;
         }

         public Integer next() {
            return objects[this.idx++];
         }
      };
   }

   public IntegerArrayPack startPacking(Object current, Accessor acc) {
      return new IntegerArrayPack();
   }

   public void addToPack(IntegerArrayPack objects, Integer o) {
      objects.add(o);
   }

   public void endPacking(IntegerArrayPack pack, Object bean, Accessor acc) throws AccessorException {
      acc.set(bean, pack.build());
   }

   public void reset(Object o, Accessor acc) throws AccessorException {
      acc.set(o, new int[0]);
   }

   static final class IntegerArrayPack {
      int[] buf = new int[16];
      int size;

      void add(Integer b) {
         if (this.buf.length == this.size) {
            int[] nb = new int[this.buf.length * 2];
            System.arraycopy(this.buf, 0, nb, 0, this.buf.length);
            this.buf = nb;
         }

         if (b != null) {
            this.buf[this.size++] = b;
         }

      }

      int[] build() {
         if (this.buf.length == this.size) {
            return this.buf;
         } else {
            int[] r = new int[this.size];
            System.arraycopy(this.buf, 0, r, 0, this.size);
            return r;
         }
      }
   }
}
