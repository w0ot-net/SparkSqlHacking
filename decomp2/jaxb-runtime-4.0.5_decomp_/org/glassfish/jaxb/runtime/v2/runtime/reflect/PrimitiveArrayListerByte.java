package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;

final class PrimitiveArrayListerByte extends Lister {
   private PrimitiveArrayListerByte() {
   }

   static void register() {
      primitiveArrayListers.put(Byte.TYPE, new PrimitiveArrayListerByte());
   }

   public ListIterator iterator(final byte[] objects, XMLSerializer context) {
      return new ListIterator() {
         int idx = 0;

         public boolean hasNext() {
            return this.idx < objects.length;
         }

         public Byte next() {
            return objects[this.idx++];
         }
      };
   }

   public ByteArrayPack startPacking(Object current, Accessor acc) {
      return new ByteArrayPack();
   }

   public void addToPack(ByteArrayPack objects, Byte o) {
      objects.add(o);
   }

   public void endPacking(ByteArrayPack pack, Object bean, Accessor acc) throws AccessorException {
      acc.set(bean, pack.build());
   }

   public void reset(Object o, Accessor acc) throws AccessorException {
      acc.set(o, new byte[0]);
   }

   static final class ByteArrayPack {
      byte[] buf = new byte[16];
      int size;

      void add(Byte b) {
         if (this.buf.length == this.size) {
            byte[] nb = new byte[this.buf.length * 2];
            System.arraycopy(this.buf, 0, nb, 0, this.buf.length);
            this.buf = nb;
         }

         if (b != null) {
            this.buf[this.size++] = b;
         }

      }

      byte[] build() {
         if (this.buf.length == this.size) {
            return this.buf;
         } else {
            byte[] r = new byte[this.size];
            System.arraycopy(this.buf, 0, r, 0, this.size);
            return r;
         }
      }
   }
}
