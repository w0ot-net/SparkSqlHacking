package org.apache.curator.shaded.com.google.common.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Ascii;
import org.apache.curator.shaded.com.google.common.base.Optional;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Splitter;
import org.apache.curator.shaded.com.google.common.collect.AbstractIterator;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Streams;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.MustBeClosed;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class CharSource {
   protected CharSource() {
   }

   public ByteSource asByteSource(Charset charset) {
      return new AsByteSource(charset);
   }

   public abstract Reader openStream() throws IOException;

   public BufferedReader openBufferedStream() throws IOException {
      Reader reader = this.openStream();
      return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
   }

   @MustBeClosed
   public Stream lines() throws IOException {
      BufferedReader reader = this.openBufferedStream();
      return (Stream)reader.lines().onClose(() -> {
         try {
            reader.close();
         } catch (IOException e) {
            throw new UncheckedIOException(e);
         }
      });
   }

   public Optional lengthIfKnown() {
      return Optional.absent();
   }

   public long length() throws IOException {
      Optional<Long> lengthIfKnown = this.lengthIfKnown();
      if (lengthIfKnown.isPresent()) {
         return (Long)lengthIfKnown.get();
      } else {
         Closer closer = Closer.create();

         long var4;
         try {
            Reader reader = (Reader)closer.register(this.openStream());
            var4 = this.countBySkipping(reader);
         } catch (Throwable e) {
            throw closer.rethrow(e);
         } finally {
            closer.close();
         }

         return var4;
      }
   }

   private long countBySkipping(Reader reader) throws IOException {
      long count;
      long read;
      for(count = 0L; (read = reader.skip(Long.MAX_VALUE)) != 0L; count += read) {
      }

      return count;
   }

   @CanIgnoreReturnValue
   public long copyTo(Appendable appendable) throws IOException {
      Preconditions.checkNotNull(appendable);
      Closer closer = Closer.create();

      long var4;
      try {
         Reader reader = (Reader)closer.register(this.openStream());
         var4 = CharStreams.copy(reader, appendable);
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var4;
   }

   @CanIgnoreReturnValue
   public long copyTo(CharSink sink) throws IOException {
      Preconditions.checkNotNull(sink);
      Closer closer = Closer.create();

      long var5;
      try {
         Reader reader = (Reader)closer.register(this.openStream());
         Writer writer = (Writer)closer.register(sink.openStream());
         var5 = CharStreams.copy(reader, writer);
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var5;
   }

   public String read() throws IOException {
      Closer closer = Closer.create();

      String var3;
      try {
         Reader reader = (Reader)closer.register(this.openStream());
         var3 = CharStreams.toString(reader);
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var3;
   }

   @CheckForNull
   public String readFirstLine() throws IOException {
      Closer closer = Closer.create();

      String var3;
      try {
         BufferedReader reader = (BufferedReader)closer.register(this.openBufferedStream());
         var3 = reader.readLine();
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var3;
   }

   public ImmutableList readLines() throws IOException {
      Closer closer = Closer.create();

      ImmutableList var5;
      try {
         BufferedReader reader = (BufferedReader)closer.register(this.openBufferedStream());
         List<String> result = Lists.newArrayList();

         String line;
         while((line = reader.readLine()) != null) {
            result.add(line);
         }

         var5 = ImmutableList.copyOf((Collection)result);
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var5;
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object readLines(LineProcessor processor) throws IOException {
      Preconditions.checkNotNull(processor);
      Closer closer = Closer.create();

      Object var4;
      try {
         Reader reader = (Reader)closer.register(this.openStream());
         var4 = CharStreams.readLines(reader, processor);
      } catch (Throwable e) {
         throw closer.rethrow(e);
      } finally {
         closer.close();
      }

      return var4;
   }

   public void forEachLine(Consumer action) throws IOException {
      try {
         Stream<String> lines = this.lines();

         try {
            lines.forEachOrdered(action);
         } catch (Throwable var6) {
            if (lines != null) {
               try {
                  lines.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (lines != null) {
            lines.close();
         }

      } catch (UncheckedIOException e) {
         throw e.getCause();
      }
   }

   public boolean isEmpty() throws IOException {
      Optional<Long> lengthIfKnown = this.lengthIfKnown();
      if (lengthIfKnown.isPresent()) {
         return (Long)lengthIfKnown.get() == 0L;
      } else {
         Closer closer = Closer.create();

         boolean var4;
         try {
            Reader reader = (Reader)closer.register(this.openStream());
            var4 = reader.read() == -1;
         } catch (Throwable e) {
            throw closer.rethrow(e);
         } finally {
            closer.close();
         }

         return var4;
      }
   }

   public static CharSource concat(Iterable sources) {
      return new ConcatenatedCharSource(sources);
   }

   public static CharSource concat(Iterator sources) {
      return concat((Iterable)ImmutableList.copyOf(sources));
   }

   public static CharSource concat(CharSource... sources) {
      return concat((Iterable)ImmutableList.copyOf((Object[])sources));
   }

   public static CharSource wrap(CharSequence charSequence) {
      return (CharSource)(charSequence instanceof String ? new StringCharSource((String)charSequence) : new CharSequenceCharSource(charSequence));
   }

   public static CharSource empty() {
      return CharSource.EmptyCharSource.INSTANCE;
   }

   private final class AsByteSource extends ByteSource {
      final Charset charset;

      AsByteSource(Charset charset) {
         this.charset = (Charset)Preconditions.checkNotNull(charset);
      }

      public CharSource asCharSource(Charset charset) {
         return charset.equals(this.charset) ? CharSource.this : super.asCharSource(charset);
      }

      public InputStream openStream() throws IOException {
         return new ReaderInputStream(CharSource.this.openStream(), this.charset, 8192);
      }

      public String toString() {
         return CharSource.this.toString() + ".asByteSource(" + this.charset + ")";
      }
   }

   private static class CharSequenceCharSource extends CharSource {
      private static final Splitter LINE_SPLITTER = Splitter.onPattern("\r\n|\n|\r");
      protected final CharSequence seq;

      protected CharSequenceCharSource(CharSequence seq) {
         this.seq = (CharSequence)Preconditions.checkNotNull(seq);
      }

      public Reader openStream() {
         return new CharSequenceReader(this.seq);
      }

      public String read() {
         return this.seq.toString();
      }

      public boolean isEmpty() {
         return this.seq.length() == 0;
      }

      public long length() {
         return (long)this.seq.length();
      }

      public Optional lengthIfKnown() {
         return Optional.of((long)this.seq.length());
      }

      private Iterator linesIterator() {
         return new AbstractIterator() {
            Iterator lines;

            {
               this.lines = CharSource.CharSequenceCharSource.LINE_SPLITTER.split(CharSequenceCharSource.this.seq).iterator();
            }

            @CheckForNull
            protected String computeNext() {
               if (this.lines.hasNext()) {
                  String next = (String)this.lines.next();
                  if (this.lines.hasNext() || !next.isEmpty()) {
                     return next;
                  }
               }

               return (String)this.endOfData();
            }
         };
      }

      public Stream lines() {
         return Streams.stream(this.linesIterator());
      }

      @CheckForNull
      public String readFirstLine() {
         Iterator<String> lines = this.linesIterator();
         return lines.hasNext() ? (String)lines.next() : null;
      }

      public ImmutableList readLines() {
         return ImmutableList.copyOf(this.linesIterator());
      }

      @ParametricNullness
      public Object readLines(LineProcessor processor) throws IOException {
         Iterator<String> lines = this.linesIterator();

         while(lines.hasNext() && processor.processLine((String)lines.next())) {
         }

         return processor.getResult();
      }

      public String toString() {
         return "CharSource.wrap(" + Ascii.truncate(this.seq, 30, "...") + ")";
      }
   }

   private static class StringCharSource extends CharSequenceCharSource {
      protected StringCharSource(String seq) {
         super(seq);
      }

      public Reader openStream() {
         return new StringReader((String)this.seq);
      }

      public long copyTo(Appendable appendable) throws IOException {
         appendable.append(this.seq);
         return (long)this.seq.length();
      }

      public long copyTo(CharSink sink) throws IOException {
         Preconditions.checkNotNull(sink);
         Closer closer = Closer.create();

         long var4;
         try {
            Writer writer = (Writer)closer.register(sink.openStream());
            writer.write((String)this.seq);
            var4 = (long)this.seq.length();
         } catch (Throwable e) {
            throw closer.rethrow(e);
         } finally {
            closer.close();
         }

         return var4;
      }
   }

   private static final class EmptyCharSource extends StringCharSource {
      private static final EmptyCharSource INSTANCE = new EmptyCharSource();

      private EmptyCharSource() {
         super("");
      }

      public String toString() {
         return "CharSource.empty()";
      }
   }

   private static final class ConcatenatedCharSource extends CharSource {
      private final Iterable sources;

      ConcatenatedCharSource(Iterable sources) {
         this.sources = (Iterable)Preconditions.checkNotNull(sources);
      }

      public Reader openStream() throws IOException {
         return new MultiReader(this.sources.iterator());
      }

      public boolean isEmpty() throws IOException {
         for(CharSource source : this.sources) {
            if (!source.isEmpty()) {
               return false;
            }
         }

         return true;
      }

      public Optional lengthIfKnown() {
         long result = 0L;

         for(CharSource source : this.sources) {
            Optional<Long> lengthIfKnown = source.lengthIfKnown();
            if (!lengthIfKnown.isPresent()) {
               return Optional.absent();
            }

            result += (Long)lengthIfKnown.get();
         }

         return Optional.of(result);
      }

      public long length() throws IOException {
         long result = 0L;

         for(CharSource source : this.sources) {
            result += source.length();
         }

         return result;
      }

      public String toString() {
         return "CharSource.concat(" + this.sources + ")";
      }
   }
}
