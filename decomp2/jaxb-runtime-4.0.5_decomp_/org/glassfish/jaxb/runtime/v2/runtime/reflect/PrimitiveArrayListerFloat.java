package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;

final class PrimitiveArrayListerFloat extends Lister {
   private PrimitiveArrayListerFloat() {
   }

   static void register() {
      Lister.primitiveArrayListers.put(Float.TYPE, new PrimitiveArrayListerFloat());
   }

   public ListIterator iterator(final float[] objects, XMLSerializer context) {
      return new ListIterator() {
         int idx = 0;

         public boolean hasNext() {
            return this.idx < objects.length;
         }

         public Float next() {
            return objects[this.idx++];
         }
      };
   }

   public FloatArrayPack startPacking(Object current, Accessor acc) {
      return new FloatArrayPack();
   }

   public void addToPack(FloatArrayPack objects, Float o) {
      objects.add(o);
   }

   public void endPacking(FloatArrayPack pack, Object bean, Accessor acc) throws AccessorException {
      acc.set(bean, pack.build());
   }

   public void reset(Object o, Accessor acc) throws AccessorException {
      acc.set(o, new float[0]);
   }

   static final class FloatArrayPack {
      float[] buf = new float[16];
      int size;

      void add(Float b) {
         if (this.buf.length == this.size) {
            float[] nb = new float[this.buf.length * 2];
            System.arraycopy(this.buf, 0, nb, 0, this.buf.length);
            this.buf = nb;
         }

         if (b != null) {
            this.buf[this.size++] = b;
         }

      }

      float[] build() {
         if (this.buf.length == this.size) {
            return this.buf;
         } else {
            float[] r = new float[this.size];
            System.arraycopy(this.buf, 0, r, 0, this.size);
            return r;
         }
      }
   }
}
