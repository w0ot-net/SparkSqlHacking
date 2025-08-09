package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOIndexedException;

@FunctionalInterface
public interface IOConsumer {
   IOConsumer NOOP_IO_CONSUMER = (t) -> {
   };

   static void forAll(IOConsumer action, Iterable iterable) throws IOExceptionList {
      IOStreams.forAll(IOStreams.of(iterable), action);
   }

   static void forAll(IOConsumer action, Stream stream) throws IOExceptionList {
      IOStreams.forAll(stream, action, IOIndexedException::new);
   }

   @SafeVarargs
   static void forAll(IOConsumer action, Object... array) throws IOExceptionList {
      IOStreams.forAll(IOStreams.of(array), action);
   }

   static void forEach(Iterable iterable, IOConsumer action) throws IOException {
      IOStreams.forEach(IOStreams.of(iterable), action);
   }

   static void forEach(Stream stream, IOConsumer action) throws IOException {
      IOStreams.forEach(stream, action);
   }

   static void forEach(Object[] array, IOConsumer action) throws IOException {
      IOStreams.forEach(IOStreams.of(array), action);
   }

   static IOConsumer noop() {
      return NOOP_IO_CONSUMER;
   }

   void accept(Object var1) throws IOException;

   default IOConsumer andThen(IOConsumer after) {
      Objects.requireNonNull(after, "after");
      return (t) -> {
         this.accept(t);
         after.accept(t);
      };
   }

   default Consumer asConsumer() {
      return (t) -> Uncheck.accept(this, t);
   }
}
