package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.function.IOConsumer;

public class FilterCollectionWriter extends Writer {
   protected final Collection EMPTY_WRITERS = Collections.emptyList();
   protected final Collection writers;

   protected FilterCollectionWriter(Collection writers) {
      this.writers = writers == null ? this.EMPTY_WRITERS : writers;
   }

   protected FilterCollectionWriter(Writer... writers) {
      this.writers = (Collection)(writers == null ? this.EMPTY_WRITERS : Arrays.asList(writers));
   }

   public Writer append(char c) throws IOException {
      return this.forAllWriters((w) -> w.append(c));
   }

   public Writer append(CharSequence csq) throws IOException {
      return this.forAllWriters((w) -> w.append(csq));
   }

   public Writer append(CharSequence csq, int start, int end) throws IOException {
      return this.forAllWriters((w) -> w.append(csq, start, end));
   }

   public void close() throws IOException {
      this.forAllWriters(Writer::close);
   }

   public void flush() throws IOException {
      this.forAllWriters(Writer::flush);
   }

   private FilterCollectionWriter forAllWriters(IOConsumer action) throws IOExceptionList {
      IOConsumer.forAll(action, this.writers());
      return this;
   }

   public void write(char[] cbuf) throws IOException {
      this.forAllWriters((w) -> w.write(cbuf));
   }

   public void write(char[] cbuf, int off, int len) throws IOException {
      this.forAllWriters((w) -> w.write(cbuf, off, len));
   }

   public void write(int c) throws IOException {
      this.forAllWriters((w) -> w.write(c));
   }

   public void write(String str) throws IOException {
      this.forAllWriters((w) -> w.write(str));
   }

   public void write(String str, int off, int len) throws IOException {
      this.forAllWriters((w) -> w.write(str, off, len));
   }

   private Stream writers() {
      return this.writers.stream().filter(Objects::nonNull);
   }
}
