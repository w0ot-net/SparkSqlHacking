package org.fusesource.leveldbjni.internal;

import java.util.AbstractMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.iq80.leveldb.DBIterator;

public class JniDBIterator implements DBIterator {
   private final NativeIterator iterator;

   JniDBIterator(NativeIterator iterator) {
      this.iterator = iterator;
   }

   public void close() {
      this.iterator.delete();
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   public void seek(byte[] key) {
      try {
         this.iterator.seek(key);
      } catch (NativeDB.DBException e) {
         if (e.isNotFound()) {
            throw new NoSuchElementException();
         } else {
            throw new RuntimeException(e);
         }
      }
   }

   public void seekToFirst() {
      this.iterator.seekToFirst();
   }

   public void seekToLast() {
      this.iterator.seekToLast();
   }

   public Map.Entry peekNext() {
      if (!this.iterator.isValid()) {
         throw new NoSuchElementException();
      } else {
         try {
            return new AbstractMap.SimpleImmutableEntry(this.iterator.key(), this.iterator.value());
         } catch (NativeDB.DBException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public boolean hasNext() {
      return this.iterator.isValid();
   }

   public Map.Entry next() {
      Map.Entry<byte[], byte[]> rc = this.peekNext();

      try {
         this.iterator.next();
         return rc;
      } catch (NativeDB.DBException e) {
         throw new RuntimeException(e);
      }
   }

   public boolean hasPrev() {
      if (!this.iterator.isValid()) {
         return false;
      } else {
         try {
            this.iterator.prev();

            boolean var1;
            try {
               var1 = this.iterator.isValid();
            } finally {
               if (this.iterator.isValid()) {
                  this.iterator.next();
               } else {
                  this.iterator.seekToFirst();
               }

            }

            return var1;
         } catch (NativeDB.DBException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public Map.Entry peekPrev() {
      try {
         this.iterator.prev();

         Map.Entry var1;
         try {
            var1 = this.peekNext();
         } finally {
            if (this.iterator.isValid()) {
               this.iterator.next();
            } else {
               this.iterator.seekToFirst();
            }

         }

         return var1;
      } catch (NativeDB.DBException e) {
         throw new RuntimeException(e);
      }
   }

   public Map.Entry prev() {
      Map.Entry<byte[], byte[]> rc = this.peekPrev();

      try {
         this.iterator.prev();
         return rc;
      } catch (NativeDB.DBException e) {
         throw new RuntimeException(e);
      }
   }
}
