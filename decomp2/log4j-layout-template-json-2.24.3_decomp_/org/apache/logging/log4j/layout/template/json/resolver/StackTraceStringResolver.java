package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.logging.log4j.layout.template.json.util.CharSequencePointer;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.layout.template.json.util.Recycler;
import org.apache.logging.log4j.layout.template.json.util.RecyclerFactory;
import org.apache.logging.log4j.layout.template.json.util.TruncatingBufferedPrintWriter;

final class StackTraceStringResolver implements StackTraceResolver {
   private final Recycler srcWriterRecycler;
   private final Recycler dstWriterRecycler;
   private final Recycler sequencePointerRecycler;
   private final boolean truncationEnabled;
   private final String truncationSuffix;
   private final List truncationPointMatcherStrings;
   private final List groupedTruncationPointMatcherRegexes;

   StackTraceStringResolver(final EventResolverContext context, final String truncationSuffix, final List truncationPointMatcherStrings, final List truncationPointMatcherRegexes) {
      Supplier<TruncatingBufferedPrintWriter> writerSupplier = () -> TruncatingBufferedPrintWriter.ofCapacity(context.getMaxStringByteCount());
      RecyclerFactory recyclerFactory = context.getRecyclerFactory();
      this.srcWriterRecycler = recyclerFactory.create(writerSupplier, TruncatingBufferedPrintWriter::close);
      this.dstWriterRecycler = recyclerFactory.create(writerSupplier, TruncatingBufferedPrintWriter::close);
      this.sequencePointerRecycler = recyclerFactory.create(CharSequencePointer::new);
      this.truncationEnabled = !truncationPointMatcherStrings.isEmpty() || !truncationPointMatcherRegexes.isEmpty();
      this.truncationSuffix = truncationSuffix;
      this.truncationPointMatcherStrings = truncationPointMatcherStrings;
      this.groupedTruncationPointMatcherRegexes = groupTruncationPointMatcherRegexes(truncationPointMatcherRegexes);
   }

   private static List groupTruncationPointMatcherRegexes(final List regexes) {
      return (List)regexes.stream().map((regex) -> Pattern.compile(".*?" + regex + "(.*)", 32)).collect(Collectors.toList());
   }

   public void resolve(final Throwable throwable, final JsonWriter jsonWriter) {
      TruncatingBufferedPrintWriter srcWriter = (TruncatingBufferedPrintWriter)this.srcWriterRecycler.acquire();

      try {
         throwable.printStackTrace(srcWriter);
         Objects.requireNonNull(jsonWriter);
         this.truncate(srcWriter, jsonWriter::writeString);
      } finally {
         this.srcWriterRecycler.release(srcWriter);
      }

   }

   private void truncate(final TruncatingBufferedPrintWriter srcWriter, final Consumer effectiveWriterConsumer) {
      if (!this.truncationEnabled) {
         effectiveWriterConsumer.accept(srcWriter);
      } else {
         TruncatingBufferedPrintWriter dstWriter = (TruncatingBufferedPrintWriter)this.dstWriterRecycler.acquire();

         try {
            CharSequencePointer sequencePointer = (CharSequencePointer)this.sequencePointerRecycler.acquire();

            try {
               this.truncate(srcWriter, dstWriter, sequencePointer);
            } finally {
               this.sequencePointerRecycler.release(sequencePointer);
            }

            effectiveWriterConsumer.accept(dstWriter);
         } finally {
            this.dstWriterRecycler.release(dstWriter);
         }

      }
   }

   private void truncate(final TruncatingBufferedPrintWriter srcWriter, final TruncatingBufferedPrintWriter dstWriter, final CharSequencePointer sequencePointer) {
      int startIndex = 0;

      while(true) {
         int labeledLineStartIndex = findLabeledLineStartIndex(srcWriter, startIndex);
         int endIndex = labeledLineStartIndex >= 0 ? labeledLineStartIndex : srcWriter.length();
         int truncationPointIndex = this.findTruncationPointIndex(srcWriter, startIndex, endIndex, sequencePointer);
         if (truncationPointIndex > 0) {
            dstWriter.append(srcWriter, startIndex, truncationPointIndex);
            dstWriter.append(System.lineSeparator());
            dstWriter.append(this.truncationSuffix);
         } else {
            dstWriter.append(srcWriter, startIndex, endIndex);
         }

         if (labeledLineStartIndex <= 0) {
            return;
         }

         dstWriter.append(System.lineSeparator());
         startIndex = labeledLineStartIndex;

         while(true) {
            char c = srcWriter.charAt(startIndex++);
            dstWriter.append(c);
            if (c == ':') {
               break;
            }
         }
      }
   }

   private int findTruncationPointIndex(final TruncatingBufferedPrintWriter writer, final int startIndex, final int endIndex, final CharSequencePointer sequencePointer) {
      for(int i = 0; i < this.truncationPointMatcherStrings.size(); ++i) {
         String matcher = (String)this.truncationPointMatcherStrings.get(i);
         int matchIndex = findMatchingIndex(matcher, writer, startIndex, endIndex);
         if (matchIndex > 0) {
            return matchIndex + matcher.length();
         }
      }

      CharSequence sequence;
      if (startIndex == 0 && endIndex == writer.length()) {
         sequence = writer;
      } else {
         sequencePointer.reset(writer, startIndex, writer.length());
         sequence = sequencePointer;
      }

      for(int i = 0; i < this.groupedTruncationPointMatcherRegexes.size(); ++i) {
         Pattern pattern = (Pattern)this.groupedTruncationPointMatcherRegexes.get(i);
         Matcher matcher = pattern.matcher(sequence);
         boolean matched = matcher.matches();
         if (matched) {
            int lastGroup = matcher.groupCount();
            return matcher.start(lastGroup);
         }
      }

      return -1;
   }

   private static int findLabeledLineStartIndex(final CharSequence buffer, final int startIndex) {
      int bufferLength = buffer.length();
      int bufferIndex = startIndex;

      while(bufferIndex < bufferLength) {
         int lineStartIndex = findLineStartIndex(buffer, bufferIndex);
         if (lineStartIndex < 0) {
            break;
         }

         for(bufferIndex = lineStartIndex; bufferIndex < bufferLength && '\t' == buffer.charAt(bufferIndex); ++bufferIndex) {
         }

         if (bufferIndex < bufferLength - 11 && buffer.charAt(bufferIndex) == 'C' && buffer.charAt(bufferIndex + 1) == 'a' && buffer.charAt(bufferIndex + 2) == 'u' && buffer.charAt(bufferIndex + 3) == 's' && buffer.charAt(bufferIndex + 4) == 'e' && buffer.charAt(bufferIndex + 5) == 'd' && buffer.charAt(bufferIndex + 6) == ' ' && buffer.charAt(bufferIndex + 7) == 'b' && buffer.charAt(bufferIndex + 8) == 'y' && buffer.charAt(bufferIndex + 9) == ':' && buffer.charAt(bufferIndex + 10) == ' ') {
            return lineStartIndex;
         }

         if (bufferIndex < bufferLength - 12 && buffer.charAt(bufferIndex) == 'S' && buffer.charAt(bufferIndex + 1) == 'u' && buffer.charAt(bufferIndex + 2) == 'p' && buffer.charAt(bufferIndex + 3) == 'p' && buffer.charAt(bufferIndex + 4) == 'r' && buffer.charAt(bufferIndex + 5) == 'e' && buffer.charAt(bufferIndex + 6) == 's' && buffer.charAt(bufferIndex + 7) == 's' && buffer.charAt(bufferIndex + 8) == 'e' && buffer.charAt(bufferIndex + 9) == 'd' && buffer.charAt(bufferIndex + 10) == ':' && buffer.charAt(bufferIndex + 11) == ' ') {
            return lineStartIndex;
         }
      }

      return -1;
   }

   private static int findLineStartIndex(final CharSequence buffer, final int startIndex) {
      for(int bufferIndex = startIndex; bufferIndex < buffer.length(); ++bufferIndex) {
         if (buffer.charAt(bufferIndex) == '\n' && bufferIndex + 1 < buffer.length()) {
            return bufferIndex + 1;
         }
      }

      return -1;
   }

   private static int findMatchingIndex(final CharSequence matcher, final CharSequence buffer, final int bufferStartIndex, final int bufferEndIndex) {
      int effectiveBufferEndIndex = bufferEndIndex - matcher.length() + 1;

      for(int bufferIndex = bufferStartIndex; bufferIndex <= effectiveBufferEndIndex; ++bufferIndex) {
         boolean found = true;

         for(int matcherIndex = 0; matcherIndex < matcher.length(); ++matcherIndex) {
            char matcherChar = matcher.charAt(matcherIndex);
            char bufferChar = buffer.charAt(bufferIndex + matcherIndex);
            if (matcherChar != bufferChar) {
               found = false;
               break;
            }
         }

         if (found) {
            return bufferIndex;
         }
      }

      return -1;
   }
}
