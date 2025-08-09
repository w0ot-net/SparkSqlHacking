package org.apache.commons.io.function;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.BaseStream;

final class UncheckedIOBaseStream implements BaseStream {
   private final IOBaseStream delegate;

   UncheckedIOBaseStream(IOBaseStream delegate) {
      this.delegate = delegate;
   }

   public void close() {
      this.delegate.close();
   }

   public boolean isParallel() {
      return this.delegate.isParallel();
   }

   public Iterator iterator() {
      return this.delegate.iterator().asIterator();
   }

   public BaseStream onClose(Runnable closeHandler) {
      IOBaseStream var10000 = this.delegate;
      Objects.requireNonNull(var10000);
      return ((IOBaseStream)Uncheck.apply(var10000::onClose, (IORunnable)() -> closeHandler.run())).unwrap();
   }

   public BaseStream parallel() {
      return this.delegate.parallel().unwrap();
   }

   public BaseStream sequential() {
      return this.delegate.sequential().unwrap();
   }

   public Spliterator spliterator() {
      return this.delegate.spliterator().unwrap();
   }

   public BaseStream unordered() {
      return this.delegate.unordered().unwrap();
   }
}
