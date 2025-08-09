package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;

final class PrimitiveArrayListerDouble extends Lister {
   private PrimitiveArrayListerDouble() {
   }

   static void register() {
      Lister.primitiveArrayListers.put(Double.TYPE, new PrimitiveArrayListerDouble());
   }

   public ListIterator iterator(final double[] objects, XMLSerializer context) {
      return new ListIterator() {
         int idx = 0;

         public boolean hasNext() {
            return this.idx < objects.length;
         }

         public Double next() {
            return objects[this.idx++];
         }
      };
   }

   public DoubleArrayPack startPacking(Object current, Accessor acc) {
      return new DoubleArrayPack();
   }

   public void addToPack(DoubleArrayPack objects, Double o) {
      objects.add(o);
   }

   public void endPacking(DoubleArrayPack pack, Object bean, Accessor acc) throws AccessorException {
      acc.set(bean, pack.build());
   }

   public void reset(Object o, Accessor acc) throws AccessorException {
      acc.set(o, new double[0]);
   }

   static final class DoubleArrayPack {
      double[] buf = new double[16];
      int size;

      void add(Double b) {
         if (this.buf.length == this.size) {
            double[] nb = new double[this.buf.length * 2];
            System.arraycopy(this.buf, 0, nb, 0, this.buf.length);
            this.buf = nb;
         }

         if (b != null) {
            this.buf[this.size++] = b;
         }

      }

      double[] build() {
         if (this.buf.length == this.size) {
            return this.buf;
         } else {
            double[] r = new double[this.size];
            System.arraycopy(this.buf, 0, r, 0, this.size);
            return r;
         }
      }
   }
}
