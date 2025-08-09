package org.apache.commons.io.function;

import java.io.Closeable;
import java.io.IOException;
import java.util.stream.BaseStream;

public interface IOBaseStream extends Closeable {
   default BaseStream asBaseStream() {
      return new UncheckedIOBaseStream(this);
   }

   default void close() {
      this.unwrap().close();
   }

   default boolean isParallel() {
      return this.unwrap().isParallel();
   }

   default IOIterator iterator() {
      return IOIteratorAdapter.adapt(this.unwrap().iterator());
   }

   default IOBaseStream onClose(IORunnable closeHandler) throws IOException {
      return this.wrap(this.unwrap().onClose(() -> Erase.run(closeHandler)));
   }

   default IOBaseStream parallel() {
      return this.isParallel() ? this : this.wrap(this.unwrap().parallel());
   }

   default IOBaseStream sequential() {
      return this.isParallel() ? this.wrap(this.unwrap().sequential()) : this;
   }

   default IOSpliterator spliterator() {
      return IOSpliteratorAdapter.adapt(this.unwrap().spliterator());
   }

   default IOBaseStream unordered() {
      return this.wrap(this.unwrap().unordered());
   }

   BaseStream unwrap();

   IOBaseStream wrap(BaseStream var1);
}
