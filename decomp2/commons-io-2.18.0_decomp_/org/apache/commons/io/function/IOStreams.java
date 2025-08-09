package org.apache.commons.io.function;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOIndexedException;

final class IOStreams {
   static final Object NONE = new Object();

   static void forAll(Stream stream, IOConsumer action) throws IOExceptionList {
      forAll(stream, action, (i, e) -> e);
   }

   static void forAll(Stream stream, IOConsumer action, BiFunction exSupplier) throws IOExceptionList {
      IOStream.adapt(stream).forAll(action, IOIndexedException::new);
   }

   static void forEach(Stream stream, IOConsumer action) throws IOException {
      IOConsumer<T> actualAction = toIOConsumer(action);
      of(stream).forEach((e) -> Erase.accept(actualAction, e));
   }

   static Stream of(Iterable values) {
      return values == null ? Stream.empty() : StreamSupport.stream(values.spliterator(), false);
   }

   static Stream of(Stream stream) {
      return stream == null ? Stream.empty() : stream;
   }

   @SafeVarargs
   static Stream of(Object... values) {
      return values == null ? Stream.empty() : Stream.of(values);
   }

   static IOConsumer toIOConsumer(IOConsumer action) {
      return action != null ? action : IOConsumer.noop();
   }

   private IOStreams() {
   }
}
