package org.jline.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Iterator;
import java.util.ListIterator;

public interface History extends Iterable {
   void attach(LineReader var1);

   void load() throws IOException;

   void save() throws IOException;

   void write(Path var1, boolean var2) throws IOException;

   void append(Path var1, boolean var2) throws IOException;

   void read(Path var1, boolean var2) throws IOException;

   void purge() throws IOException;

   int size();

   default boolean isEmpty() {
      return this.size() == 0;
   }

   int index();

   int first();

   int last();

   String get(int var1);

   default void add(String line) {
      this.add(Instant.now(), line);
   }

   void add(Instant var1, String var2);

   default boolean isPersistable(Entry entry) {
      return true;
   }

   ListIterator iterator(int var1);

   default ListIterator iterator() {
      return this.iterator(this.first());
   }

   default Iterator reverseIterator() {
      return this.reverseIterator(this.last());
   }

   default Iterator reverseIterator(final int index) {
      return new Iterator() {
         private final ListIterator it = History.this.iterator(index + 1);

         public boolean hasNext() {
            return this.it.hasPrevious();
         }

         public Entry next() {
            return (Entry)this.it.previous();
         }

         public void remove() {
            this.it.remove();
            History.this.resetIndex();
         }
      };
   }

   String current();

   boolean previous();

   boolean next();

   boolean moveToFirst();

   boolean moveToLast();

   boolean moveTo(int var1);

   void moveToEnd();

   void resetIndex();

   public interface Entry {
      int index();

      Instant time();

      String line();
   }
}
