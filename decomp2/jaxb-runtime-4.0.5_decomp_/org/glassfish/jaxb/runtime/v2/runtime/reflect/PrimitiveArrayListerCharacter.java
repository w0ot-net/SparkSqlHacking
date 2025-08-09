package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;

final class PrimitiveArrayListerCharacter extends Lister {
   private PrimitiveArrayListerCharacter() {
   }

   static void register() {
      primitiveArrayListers.put(Character.TYPE, new PrimitiveArrayListerCharacter());
   }

   public ListIterator iterator(final char[] objects, XMLSerializer context) {
      return new ListIterator() {
         int idx = 0;

         public boolean hasNext() {
            return this.idx < objects.length;
         }

         public Character next() {
            return objects[this.idx++];
         }
      };
   }

   public CharacterArrayPack startPacking(Object current, Accessor acc) {
      return new CharacterArrayPack();
   }

   public void addToPack(CharacterArrayPack objects, Character o) {
      objects.add(o);
   }

   public void endPacking(CharacterArrayPack pack, Object bean, Accessor acc) throws AccessorException {
      acc.set(bean, pack.build());
   }

   public void reset(Object o, Accessor acc) throws AccessorException {
      acc.set(o, new char[0]);
   }

   static final class CharacterArrayPack {
      char[] buf = new char[16];
      int size;

      void add(Character b) {
         if (this.buf.length == this.size) {
            char[] nb = new char[this.buf.length * 2];
            System.arraycopy(this.buf, 0, nb, 0, this.buf.length);
            this.buf = nb;
         }

         if (b != null) {
            this.buf[this.size++] = b;
         }

      }

      char[] build() {
         if (this.buf.length == this.size) {
            return this.buf;
         } else {
            char[] r = new char[this.size];
            System.arraycopy(this.buf, 0, r, 0, this.size);
            return r;
         }
      }
   }
}
