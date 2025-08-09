package com.univocity.parsers.common;

import com.univocity.parsers.common.fields.ExcludeFieldNameSelector;
import com.univocity.parsers.common.fields.FieldIndexSelector;
import com.univocity.parsers.common.fields.FieldNameSelector;
import com.univocity.parsers.common.fields.FieldSelector;
import com.univocity.parsers.common.input.WriterCharAppender;
import com.univocity.parsers.common.processor.RowWriterProcessor;
import com.univocity.parsers.common.processor.RowWriterProcessorSwitch;
import com.univocity.parsers.common.record.Record;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWriter {
   private final RowWriterProcessor writerProcessor;
   private Writer writer;
   private final boolean skipEmptyLines;
   protected final char comment;
   private final WriterCharAppender rowAppender;
   private final boolean isHeaderWritingEnabled;
   private Object[] outputRow;
   private int[] indexesToWrite;
   private final char[] lineSeparator;
   protected NormalizedString[] headers;
   protected long recordCount;
   protected final String nullValue;
   protected final String emptyValue;
   protected final WriterCharAppender appender;
   private final Object[] partialLine;
   private int partialLineIndex;
   private Map headerIndexes;
   private int largestRowLength;
   protected boolean writingHeaders;
   protected boolean[] headerTrimFlags;
   private NormalizedString[] dummyHeaderRow;
   protected boolean expandRows;
   private boolean usingSwitch;
   private boolean enableNewlineAfterRecord;
   protected boolean usingNullOrEmptyValue;
   protected final int whitespaceRangeStart;
   private final boolean columnReorderingEnabled;
   protected boolean ignoreLeading;
   protected boolean ignoreTrailing;
   private final CommonSettings internalSettings;
   private final int errorContentLength;

   public AbstractWriter(CommonWriterSettings settings) {
      this((Writer)null, settings);
   }

   public AbstractWriter(File file, CommonWriterSettings settings) {
      this(ArgumentUtils.newWriter(file), settings);
   }

   public AbstractWriter(File file, String encoding, CommonWriterSettings settings) {
      this(ArgumentUtils.newWriter(file, encoding), settings);
   }

   public AbstractWriter(File file, Charset encoding, CommonWriterSettings settings) {
      this(ArgumentUtils.newWriter(file, encoding), settings);
   }

   public AbstractWriter(OutputStream output, CommonWriterSettings settings) {
      this(ArgumentUtils.newWriter(output), settings);
   }

   public AbstractWriter(OutputStream output, String encoding, CommonWriterSettings settings) {
      this(ArgumentUtils.newWriter(output, encoding), settings);
   }

   public AbstractWriter(OutputStream output, Charset encoding, CommonWriterSettings settings) {
      this(ArgumentUtils.newWriter(output, encoding), settings);
   }

   public AbstractWriter(Writer writer, CommonWriterSettings settings) {
      // $FF: Couldn't be decompiled
   }

   protected void enableNewlineAfterRecord(boolean enableNewlineAfterRecord) {
      this.enableNewlineAfterRecord = enableNewlineAfterRecord;
   }

   protected abstract void initialize(CommonWriterSettings var1);

   private void updateIndexesToWrite(CommonSettings settings) {
      FieldSelector selector = settings.getFieldSelector();
      if (selector != null) {
         if (this.headers != null && this.headers.length > 0) {
            this.indexesToWrite = selector.getFieldIndexes(this.headers);
            if (this.columnReorderingEnabled) {
               int size = ArgumentUtils.removeAll(this.indexesToWrite, -1).length;
               this.outputRow = new Object[size];
            } else {
               this.outputRow = new Object[this.headers.length];
            }
         } else {
            if (selector instanceof FieldNameSelector || selector instanceof ExcludeFieldNameSelector) {
               throw new IllegalStateException("Cannot select fields by name with no headers defined");
            }

            int rowLength = this.largestRowLength;
            if (!(selector instanceof FieldIndexSelector)) {
               rowLength = settings.getMaxColumns();
            } else {
               boolean gotLengthFromSelection = false;

               for(Integer index : ((FieldIndexSelector)selector).get()) {
                  if (rowLength <= index) {
                     rowLength = index;
                     gotLengthFromSelection = true;
                  }
               }

               if (gotLengthFromSelection) {
                  ++rowLength;
               }

               if (rowLength < this.largestRowLength) {
                  rowLength = this.largestRowLength;
               }
            }

            this.indexesToWrite = selector.getFieldIndexes(new NormalizedString[rowLength]);
            if (this.columnReorderingEnabled) {
               int size = ArgumentUtils.removeAll(this.indexesToWrite, -1).length;
               this.outputRow = new Object[size];
            } else {
               this.outputRow = new Object[rowLength];
            }
         }
      } else {
         this.outputRow = null;
         this.indexesToWrite = null;
      }

   }

   public void updateFieldSelection(String... newFieldSelection) {
      if (this.headers == null) {
         throw new IllegalStateException("Cannot select fields by name. Headers not defined.");
      } else {
         this.internalSettings.selectFields(newFieldSelection);
         this.updateIndexesToWrite(this.internalSettings);
      }
   }

   public void updateFieldSelection(Integer... newFieldSelectionByIndex) {
      this.internalSettings.selectIndexes(newFieldSelectionByIndex);
      this.updateIndexesToWrite(this.internalSettings);
   }

   public void updateFieldExclusion(String... fieldsToExclude) {
      if (this.headers == null) {
         throw new IllegalStateException("Cannot de-select fields by name. Headers not defined.");
      } else {
         this.internalSettings.excludeFields(fieldsToExclude);
         this.updateIndexesToWrite(this.internalSettings);
      }
   }

   public void updateFieldExclusion(Integer... fieldIndexesToExclude) {
      this.internalSettings.excludeIndexes(fieldIndexesToExclude);
      this.updateIndexesToWrite(this.internalSettings);
   }

   private void submitRow(Object[] row) {
      if (this.largestRowLength < row.length) {
         this.largestRowLength = row.length;
      }

      if (this.writingHeaders) {
         this.headerTrimFlags = new boolean[this.headers.length];

         for(int i = 0; i < this.headers.length; ++i) {
            this.headerTrimFlags[i] = !this.headers[i].isLiteral();
         }
      }

      this.processRow(row);
   }

   protected abstract void processRow(Object[] var1);

   protected final void appendValueToRow() {
      this.rowAppender.append(this.appender);
   }

   protected final void appendToRow(char ch) {
      this.rowAppender.append(ch);
   }

   protected final void appendToRow(char[] chars) {
      this.rowAppender.append(chars);
   }

   public final void writeHeaders() {
      this.writeHeaders(NormalizedString.toArray(this.headers));
   }

   public final void writeHeaders(Collection headers) {
      if (headers != null && headers.size() > 0) {
         this.writeHeaders((String[])headers.toArray(new String[headers.size()]));
      } else {
         throw this.throwExceptionAndClose("No headers defined.");
      }
   }

   public final void writeHeaders(String... headers) {
      if (this.recordCount > 0L) {
         throw this.throwExceptionAndClose("Cannot write headers after records have been written.", (Object[])headers, (Throwable)null);
      } else if (headers != null && headers.length > 0) {
         this.writingHeaders = true;
         if (this.columnReorderingEnabled && this.outputRow != null) {
            this.fillOutputRow(headers);
            headers = (String[])Arrays.copyOf(this.outputRow, this.outputRow.length, String[].class);
         }

         this.headers = NormalizedString.toIdentifierGroupArray(headers);
         this.submitRow(headers);
         this.internalWriteRow();
         this.writingHeaders = false;
      } else {
         throw this.throwExceptionAndClose("No headers defined.", (Object[])headers, (Throwable)null);
      }
   }

   public final void processRecordsAndClose(Iterable allRecords) {
      try {
         this.processRecords(allRecords);
      } finally {
         this.close();
      }

   }

   public final void processRecordsAndClose(Object[] allRecords) {
      try {
         this.processRecords(allRecords);
      } finally {
         this.close();
      }

   }

   public final void processRecords(Iterable records) {
      for(Object record : records) {
         this.processRecord(record);
      }

   }

   public final void processRecords(Object[] records) {
      for(Object record : records) {
         this.processRecord(record);
      }

   }

   public final void processRecords(Record[] records) {
      for(Record record : records) {
         this.processRecord(record);
      }

   }

   public final void processRecord(Object... record) {
      this.processRecord((Object)record);
   }

   public final void processRecord(Record record) {
      this.processRecord((Object)(record == null ? null : record.getValues()));
   }

   public final void processRecord(Object record) {
      if (this.writerProcessor == null) {
         String recordDescription;
         if (record instanceof Object[]) {
            recordDescription = Arrays.toString(record);
         } else {
            recordDescription = String.valueOf(record);
         }

         String message = "Cannot process record '" + recordDescription + "' without a writer processor. Please define a writer processor instance in the settings or use the 'writeRow' methods.";
         this.throwExceptionAndClose(message);
      }

      Object[] row;
      try {
         if (this.usingSwitch) {
            this.dummyHeaderRow = ((RowWriterProcessorSwitch)this.writerProcessor).getHeaders(record);
            if (this.dummyHeaderRow == null) {
               this.dummyHeaderRow = this.headers;
            }

            row = this.writerProcessor.write(record, this.dummyHeaderRow, this.indexesToWrite);
         } else {
            row = this.writerProcessor.write(record, this.getRowProcessorHeaders(), this.indexesToWrite);
         }
      } catch (DataProcessingException e) {
         e.setErrorContentLength(this.errorContentLength);
         throw e;
      }

      if (row != null) {
         this.writeRow(row);
      }

   }

   private NormalizedString[] getRowProcessorHeaders() {
      return this.headers == null && this.indexesToWrite == null ? null : this.headers;
   }

   public final void writeRowsAndClose(Iterable allRows) {
      try {
         this.writeRows(allRows);
      } finally {
         this.close();
      }

   }

   public final void writeRowsAndClose(Collection allRows) {
      try {
         this.writeRows(allRows);
      } finally {
         this.close();
      }

   }

   public final void writeStringRowsAndClose(Collection allRows) {
      try {
         this.writeStringRows(allRows);
      } finally {
         this.close();
      }

   }

   public final void writeRecordsAndClose(Collection allRows) {
      try {
         this.writeRecords(allRows);
      } finally {
         this.close();
      }

   }

   public final void writeRowsAndClose(Object[][] allRows) {
      try {
         this.writeRows(allRows);
      } finally {
         this.close();
      }

   }

   public final void writeRows(Object[][] rows) {
      for(Object[] row : rows) {
         this.writeRow(row);
      }

   }

   public final void writeRows(Iterable rows) {
      for(Collection row : rows) {
         this.writeRow(row);
      }

   }

   public final void writeStringRows(Collection rows) {
      for(String[] row : rows) {
         this.writeRow(row);
      }

   }

   public final void writeRecords(Collection rows) {
      for(Record row : rows) {
         this.writeRecord(row);
      }

   }

   public final void writeStringRows(Iterable rows) {
      for(Collection row : rows) {
         this.writeRow(row.toArray());
      }

   }

   public final void writeRows(Collection rows) {
      for(Object[] row : rows) {
         this.writeRow(row);
      }

   }

   public final void writeRow(Collection row) {
      if (row != null) {
         this.writeRow(row.toArray());
      }
   }

   public final void writeRow(String[] row) {
      this.writeRow((Object[])row);
   }

   public final void writeRecord(Record row) {
      if (row == null) {
         if (!this.skipEmptyLines) {
            this.writeEmptyRow();
         }
      } else {
         if (this.recordCount == 0L && this.isHeaderWritingEnabled && this.headers == null) {
            String[] headers = row.getMetaData().headers();
            if (headers != null) {
               this.headers = NormalizedString.toArray(headers);
            }
         }

         this.writeRow((Object[])row.getValues());
      }
   }

   public final void writeRow(Object... row) {
      try {
         if (this.recordCount == 0L && this.isHeaderWritingEnabled && this.headers != null) {
            this.writeHeaders();
         }

         if (row == null || row.length == 0 && !this.expandRows) {
            if (!this.skipEmptyLines) {
               this.writeEmptyRow();
            }
         } else {
            row = this.adjustRowLength(row);
            this.submitRow(row);
            this.internalWriteRow();
         }
      } catch (Throwable ex) {
         throw this.throwExceptionAndClose("Error writing row.", row, ex);
      }
   }

   protected Object[] expand(Object[] row, int length, Integer h2) {
      if (row.length < length) {
         return Arrays.copyOf(row, length);
      } else if (h2 != null && row.length < h2) {
         return Arrays.copyOf(row, h2);
      } else {
         return length == -1 && h2 == null && row.length < this.largestRowLength ? Arrays.copyOf(row, this.largestRowLength) : row;
      }
   }

   public final void writeRow(String row) {
      try {
         this.writer.write(row);
         if (this.enableNewlineAfterRecord) {
            this.writer.write(this.lineSeparator);
         }

      } catch (Throwable ex) {
         throw this.throwExceptionAndClose("Error writing row.", row, ex);
      }
   }

   public final void writeEmptyRow() {
      try {
         if (this.enableNewlineAfterRecord) {
            this.writer.write(this.lineSeparator);
         }

      } catch (Throwable ex) {
         throw this.throwExceptionAndClose("Error writing empty row.", Arrays.toString(this.lineSeparator), ex);
      }
   }

   public final void commentRow(String comment) {
      this.writeRow(this.comment + comment);
   }

   private void fillOutputRow(Object[] row) {
      if (!this.columnReorderingEnabled && row.length > this.outputRow.length) {
         this.outputRow = Arrays.copyOf(this.outputRow, row.length);
      }

      if (this.indexesToWrite.length < row.length) {
         if (this.columnReorderingEnabled) {
            for(int i = 0; i < this.indexesToWrite.length; ++i) {
               this.outputRow[i] = row[this.indexesToWrite[i]];
            }
         } else {
            for(int i = 0; i < this.indexesToWrite.length; ++i) {
               this.outputRow[this.indexesToWrite[i]] = row[this.indexesToWrite[i]];
            }
         }
      } else {
         for(int i = 0; i < row.length && i < this.indexesToWrite.length; ++i) {
            if (this.indexesToWrite[i] != -1) {
               this.outputRow[this.indexesToWrite[i]] = row[i];
            }
         }
      }

   }

   private void internalWriteRow() {
      try {
         if (!this.skipEmptyLines || this.rowAppender.length() != 0) {
            if (this.enableNewlineAfterRecord) {
               this.rowAppender.appendNewLine();
            }

            this.rowAppender.writeCharsAndReset(this.writer);
            ++this.recordCount;
         }
      } catch (Throwable ex) {
         throw this.throwExceptionAndClose("Error writing row.", this.rowAppender.getAndReset(), ex);
      }
   }

   protected static int skipLeadingWhitespace(int whitespaceRangeStart, String element) {
      if (element.isEmpty()) {
         return 0;
      } else {
         for(int i = 0; i < element.length(); ++i) {
            char nextChar = element.charAt(i);
            if (nextChar > ' ' || whitespaceRangeStart >= nextChar) {
               return i;
            }
         }

         return element.length();
      }
   }

   public final void flush() {
      try {
         this.writer.flush();
      } catch (Throwable ex) {
         throw this.throwExceptionAndClose("Error flushing output.", this.rowAppender.getAndReset(), ex);
      }
   }

   public final void close() {
      try {
         this.headerIndexes = null;
         if (this.writer != null) {
            this.writer.close();
            this.writer = null;
         }
      } catch (Throwable ex) {
         throw new IllegalStateException("Error closing the output.", ex);
      }

      if (this.partialLineIndex != 0) {
         throw new TextWritingException("Not all values associated with the last record have been written to the output. \n\tHint: use 'writeValuesToRow()' or 'writeValuesToString()' to flush the partially written values to a row.", this.recordCount, this.getContent(Arrays.copyOf(this.partialLine, this.partialLineIndex)));
      }
   }

   private TextWritingException throwExceptionAndClose(String message) {
      return this.throwExceptionAndClose(message, (Object[])((Object[])null), (Throwable)null);
   }

   private TextWritingException throwExceptionAndClose(String message, Throwable cause) {
      return this.throwExceptionAndClose(message, (Object[])null, cause);
   }

   private TextWritingException throwExceptionAndClose(String message, String recordCharacters, Throwable cause) {
      try {
         if (cause instanceof NullPointerException && this.writer == null) {
            message = message + " No writer provided in the constructor of " + this.getClass().getName() + ". You can only use operations that write to Strings.";
         }

         throw new TextWritingException(message, this.recordCount, this.getContent((CharSequence)recordCharacters), cause);
      } finally {
         this.close();
      }
   }

   private TextWritingException throwExceptionAndClose(String message, Object[] recordValues, Throwable cause) {
      try {
         throw new TextWritingException(message, this.recordCount, this.getContent(recordValues), cause);
      } finally {
         try {
            this.close();
         } catch (Throwable var8) {
         }

      }
   }

   protected String getStringValue(Object element) {
      this.usingNullOrEmptyValue = false;
      if (element == null) {
         this.usingNullOrEmptyValue = true;
         return this.nullValue;
      } else {
         String string = String.valueOf(element);
         if (string.isEmpty()) {
            this.usingNullOrEmptyValue = true;
            return this.emptyValue;
         } else {
            if (this.ignoreLeading) {
               int start = skipLeadingWhitespace(this.whitespaceRangeStart, string);
               if (start == string.length()) {
                  this.usingNullOrEmptyValue = true;
                  return this.emptyValue;
               }
            }

            return string;
         }
      }
   }

   public final void addValues(Object... values) {
      try {
         System.arraycopy(values, 0, this.partialLine, this.partialLineIndex, values.length);
         this.partialLineIndex += values.length;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error adding values to in-memory row", values, t);
      }
   }

   public final void addStringValues(Collection values) {
      if (values != null) {
         try {
            for(String o : values) {
               this.partialLine[this.partialLineIndex++] = o;
            }
         } catch (Throwable t) {
            throw this.throwExceptionAndClose("Error adding values to in-memory row", values.toArray(), t);
         }
      }

   }

   public final void addValues(Collection values) {
      if (values != null) {
         try {
            for(Object o : values) {
               this.partialLine[this.partialLineIndex++] = o;
            }
         } catch (Throwable t) {
            throw this.throwExceptionAndClose("Error adding values to in-memory row", values.toArray(), t);
         }
      }

   }

   public final void addValue(Object value) {
      try {
         this.partialLine[this.partialLineIndex++] = value;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error adding value to in-memory row", new Object[]{value}, t);
      }
   }

   private void fillPartialLineToMatchHeaders() {
      if (this.headers != null && this.partialLineIndex < this.headers.length) {
         while(this.partialLineIndex < this.headers.length) {
            this.partialLine[this.partialLineIndex++] = null;
         }
      }

   }

   public final void writeValuesToRow() {
      this.fillPartialLineToMatchHeaders();
      this.writeRow(Arrays.copyOf(this.partialLine, this.partialLineIndex));
      this.discardValues();
   }

   public final void addValue(int index, Object value) {
      if (index >= this.partialLine.length) {
         throw this.throwExceptionAndClose("Cannot write '" + value + "' to index '" + index + "'. Maximum number of columns (" + this.partialLine.length + ") exceeded.", (Object[])(new Object[]{value}), (Throwable)null);
      } else {
         this.partialLine[index] = value;
         if (this.partialLineIndex <= index) {
            this.partialLineIndex = index + 1;
         }

      }
   }

   public final void addValue(String headerName, Object value) {
      this.addValue(this.getFieldIndex(this.headers, NormalizedString.valueOf(headerName), false), value);
   }

   private final void addValue(NormalizedString[] headersInContext, NormalizedString headerName, boolean ignoreOnMismatch, Object value) {
      int index = this.getFieldIndex(headersInContext, headerName, ignoreOnMismatch);
      if (index != -1) {
         this.addValue(index, value);
      }

   }

   private int getFieldIndex(NormalizedString[] headersInContext, NormalizedString headerName, boolean ignoreOnMismatch) {
      if (this.headerIndexes == null) {
         this.headerIndexes = new HashMap();
      }

      Map<NormalizedString, Integer> indexes = (Map)this.headerIndexes.get(headersInContext);
      if (indexes == null) {
         indexes = new HashMap();
         this.headerIndexes.put(headersInContext, indexes);
      }

      Integer index = (Integer)indexes.get(headerName);
      if (index == null) {
         if (headersInContext == null) {
            throw this.throwExceptionAndClose("Cannot calculate position of header '" + headerName + "' as no headers were defined.", (Throwable)null);
         }

         index = ArgumentUtils.indexOf(NormalizedString.toArray(headersInContext), NormalizedString.valueOf(headerName));
         if (index == -1 && !ignoreOnMismatch) {
            throw this.throwExceptionAndClose("Header '" + headerName + "' could not be found. Defined headers are: " + Arrays.toString(headersInContext) + '.', (Throwable)null);
         }

         indexes.put(headerName, index);
      }

      return index;
   }

   public final void discardValues() {
      Arrays.fill(this.partialLine, 0, this.partialLineIndex, (Object)null);
      this.partialLineIndex = 0;
   }

   public final String writeHeadersToString() {
      return this.writeHeadersToString(NormalizedString.toArray(this.headers));
   }

   public final String writeHeadersToString(Collection headers) {
      if (headers != null && headers.size() > 0) {
         return this.writeHeadersToString((String[])headers.toArray(new String[headers.size()]));
      } else {
         throw this.throwExceptionAndClose("No headers defined");
      }
   }

   public final String writeHeadersToString(String... headers) {
      if (headers != null && headers.length > 0) {
         this.writingHeaders = true;
         this.submitRow(headers);
         this.writingHeaders = false;
         this.headers = NormalizedString.toIdentifierGroupArray(headers);
         return this.internalWriteRowToString();
      } else {
         throw this.throwExceptionAndClose("No headers defined.");
      }
   }

   public final List processRecordsToString(Iterable records) {
      try {
         List<String> out = new ArrayList(1000);

         for(Object record : records) {
            out.add(this.processRecordToString(record));
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Unable process input records", t);
      }
   }

   public final List processRecordsToString(Object[] records) {
      try {
         List<String> out = new ArrayList(1000);

         for(Object record : records) {
            out.add(this.processRecordToString(record));
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Unable process input records", records, t);
      }
   }

   public final String processRecordToString(Object... record) {
      return this.processRecordToString((Object)record);
   }

   public final String processRecordToString(Record record) {
      return this.processRecordToString((Object)(record == null ? null : record.getValues()));
   }

   public final String processRecordToString(Object record) {
      if (this.writerProcessor == null) {
         throw this.throwExceptionAndClose("Cannot process record '" + record + "' without a writer processor. Please define a writer processor instance in the settings or use the 'writeRow' methods.");
      } else {
         try {
            Object[] row = this.writerProcessor.write(record, this.getRowProcessorHeaders(), this.indexesToWrite);
            return row != null ? this.writeRowToString(row) : null;
         } catch (Throwable t) {
            throw this.throwExceptionAndClose("Could not process record '" + record + "'", t);
         }
      }
   }

   public final List writeRowsToString(Object[][] rows) {
      try {
         List<String> out = new ArrayList(rows.length);

         for(Object[] row : rows) {
            String string = this.writeRowToString(row);
            if (string != null) {
               out.add(string);
            }
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error writing input rows", t);
      }
   }

   public final List writeRowsToString(Iterable rows) {
      try {
         List<String> out = new ArrayList(1000);

         for(Collection row : rows) {
            out.add(this.writeRowToString(row));
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error writing input rows", t);
      }
   }

   public final List writeStringRowsToString(Iterable rows) {
      try {
         List<String> out = new ArrayList(1000);

         for(Collection row : rows) {
            String string = this.writeRowToString(row);
            if (string != null) {
               out.add(string);
            }
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error writing input rows", t);
      }
   }

   public final List writeRowsToString(Collection rows) {
      try {
         List<String> out = new ArrayList(rows.size());

         for(Object[] row : rows) {
            out.add(this.writeRowToString(row));
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error writing input rows", t);
      }
   }

   public final List writeStringRowsToString(Collection rows) {
      try {
         List<String> out = new ArrayList(rows.size());

         for(String[] row : rows) {
            out.add(this.writeRowToString(row));
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error writing input rows", t);
      }
   }

   public final List writeRecordsToString(Collection rows) {
      try {
         List<String> out = new ArrayList(rows.size());

         for(Record row : rows) {
            out.add(this.writeRecordToString(row));
         }

         return out;
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error writing input rows", t);
      }
   }

   public final String writeRowToString(Collection row) {
      try {
         return row == null ? null : this.writeRowToString(row.toArray());
      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error writing input row ", t);
      }
   }

   public final String writeRowToString(String[] row) {
      return this.writeRowToString((Object[])row);
   }

   public final String writeRecordToString(Record row) {
      return this.writeRowToString((Object[])(row == null ? null : row.getValues()));
   }

   public final String writeRowToString(Object... row) {
      try {
         if ((row == null || row.length == 0 && !this.expandRows) && this.skipEmptyLines) {
            return null;
         } else {
            row = this.adjustRowLength(row);
            this.submitRow(row);
            return this.internalWriteRowToString();
         }
      } catch (Throwable ex) {
         throw this.throwExceptionAndClose("Error writing row.", row, ex);
      }
   }

   private Object[] adjustRowLength(Object[] row) {
      if (this.outputRow != null) {
         this.fillOutputRow(row);
         row = this.outputRow;
      } else if (this.expandRows) {
         if (this.usingSwitch) {
            row = this.expand(row, this.dummyHeaderRow == null ? -1 : this.dummyHeaderRow.length, this.headers == null ? null : this.headers.length);
            this.dummyHeaderRow = null;
         } else {
            row = this.expand(row, this.headers == null ? -1 : this.headers.length, (Integer)null);
         }
      }

      return row;
   }

   public final String commentRowToString(String comment) {
      return this.writeRowToString(this.comment + comment);
   }

   private String internalWriteRowToString() {
      if (this.skipEmptyLines && this.rowAppender.length() == 0) {
         return null;
      } else {
         String out = this.rowAppender.getAndReset();
         ++this.recordCount;
         return out;
      }
   }

   public final String writeValuesToString() {
      this.fillPartialLineToMatchHeaders();
      String out = this.writeRowToString(Arrays.copyOf(this.partialLine, this.partialLineIndex));
      this.discardValues();
      return out;
   }

   public final void processValuesToRow() {
      this.fillPartialLineToMatchHeaders();
      this.processRecord(Arrays.copyOf(this.partialLine, this.partialLineIndex));
      this.discardValues();
   }

   public final String processValuesToString() {
      this.fillPartialLineToMatchHeaders();
      String out = this.processRecordToString(Arrays.copyOf(this.partialLine, this.partialLineIndex));
      this.discardValues();
      return out;
   }

   public final long getRecordCount() {
      return this.recordCount;
   }

   private void writeValuesFromMap(Map headerMapping, Map rowData) {
      try {
         if (rowData != null && !rowData.isEmpty()) {
            this.dummyHeaderRow = this.headers;
            if (this.usingSwitch) {
               this.dummyHeaderRow = ((RowWriterProcessorSwitch)this.writerProcessor).getHeaders(headerMapping, rowData);
               if (this.dummyHeaderRow == null) {
                  this.dummyHeaderRow = this.headers;
               }
            }

            if (this.dummyHeaderRow != null) {
               if (headerMapping == null) {
                  for(Map.Entry e : rowData.entrySet()) {
                     this.addValue(this.dummyHeaderRow, NormalizedString.valueOf(e.getKey()), true, e.getValue());
                  }
               } else {
                  for(Map.Entry e : rowData.entrySet()) {
                     String header = (String)headerMapping.get(e.getKey());
                     if (header != null) {
                        this.addValue(this.dummyHeaderRow, NormalizedString.valueOf(header), true, e.getValue());
                     }
                  }
               }
            } else if (headerMapping != null) {
               this.setHeadersFromMap(headerMapping, false);
               this.writeValuesFromMap(headerMapping, rowData);
            } else {
               this.setHeadersFromMap(rowData, true);
               this.writeValuesFromMap((Map)null, rowData);
            }
         }

      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error processing data from input map", t);
      }
   }

   private void setHeadersFromMap(Map map, boolean keys) {
      this.headers = new NormalizedString[map.size()];
      int i = 0;

      for(Object header : keys ? map.keySet() : map.values()) {
         this.headers[i++] = NormalizedString.valueOf(header);
      }

   }

   public final String writeRowToString(Map rowData) {
      return this.writeRowToString((Map)null, rowData);
   }

   public final void writeRow(Map rowData) {
      this.writeRow((Map)null, rowData);
   }

   public final String writeRowToString(Map headerMapping, Map rowData) {
      this.writeValuesFromMap(headerMapping, rowData);
      return this.writeValuesToString();
   }

   public final void writeRow(Map headerMapping, Map rowData) {
      this.writeValuesFromMap(headerMapping, rowData);
      this.writeValuesToRow();
   }

   public final List writeRowsToString(Map rowData) {
      return this.writeRowsToString((Map)null, rowData);
   }

   public final void writeRows(Map rowData) {
      this.writeRows((Map)null, rowData, (List)null, false);
   }

   public final List writeRowsToString(Map headerMapping, Map rowData) {
      List<String> writtenRows = new ArrayList();
      this.writeRows(headerMapping, rowData, writtenRows, false);
      return writtenRows;
   }

   public final void writeRows(Map headerMapping, Map rowData) {
      this.writeRows(headerMapping, rowData, (List)null, false);
   }

   private void writeRows(Map headerMapping, Map rowData, List outputList, boolean useRowProcessor) {
      try {
         Iterator[] iterators = new Iterator[rowData.size()];
         Object[] keys = new Object[rowData.size()];
         Map<Object, Object> rowValues = new LinkedHashMap(rowData.size());
         if (outputList != null && this.headers == null) {
            if (headerMapping != null) {
               this.setHeadersFromMap(headerMapping, true);
            } else {
               this.setHeadersFromMap(rowData, true);
            }
         }

         if (this.recordCount == 0L && this.headers != null && this.isHeaderWritingEnabled) {
            outputList.add(this.writeHeadersToString());
         }

         int length = 0;

         for(Map.Entry rowEntry : rowData.entrySet()) {
            iterators[length] = rowEntry.getValue() == null ? null : ((Iterable)rowEntry.getValue()).iterator();
            keys[length] = rowEntry.getKey();
            rowValues.put(rowEntry.getKey(), (Object)null);
            ++length;
         }

         boolean nullsOnly;
         do {
            nullsOnly = true;

            for(int i = 0; i < length; ++i) {
               Iterator<?> iterator = iterators[i];
               boolean isNull = iterator == null || !iterator.hasNext();
               nullsOnly &= isNull;
               if (isNull) {
                  rowValues.put(keys[i], (Object)null);
               } else {
                  rowValues.put(keys[i], iterator.next());
               }
            }

            if (!nullsOnly) {
               if (outputList == null) {
                  if (useRowProcessor) {
                     this.processRecord(headerMapping, rowValues);
                  } else {
                     this.writeRow(headerMapping, rowValues);
                  }
               } else if (useRowProcessor) {
                  outputList.add(this.processRecordToString(headerMapping, rowValues));
               } else {
                  outputList.add(this.writeRowToString(headerMapping, rowValues));
               }
            }
         } while(!nullsOnly);

      } catch (Throwable t) {
         throw this.throwExceptionAndClose("Error processing input rows from map", t);
      }
   }

   public final List writeStringRowsToString(Map headerMapping, Map rowData) {
      List<String> writtenRows = new ArrayList();
      this.writeRows(headerMapping, this.wrapStringArray(rowData), writtenRows, false);
      return writtenRows;
   }

   public final List writeRecordsToString(Map headerMapping, Map rowData) {
      List<String> writtenRows = new ArrayList();
      this.writeRows(headerMapping, this.wrapRecordValues(rowData), writtenRows, false);
      return writtenRows;
   }

   public final void writeStringRows(Map headerMapping, Map rowData) {
      this.writeRows(headerMapping, this.wrapStringArray(rowData), (List)null, false);
   }

   public final void writeRecords(Map headerMapping, Map rowData) {
      this.writeRows(headerMapping, this.wrapRecordValues(rowData), (List)null, false);
   }

   public final List writeObjectRowsToString(Map headerMapping, Map rowData) {
      List<String> writtenRows = new ArrayList();
      this.writeRows(headerMapping, this.wrapObjectArray(rowData), writtenRows, false);
      return writtenRows;
   }

   public final void writeObjectRows(Map headerMapping, Map rowData) {
      this.writeRows(headerMapping, this.wrapObjectArray(rowData), (List)null, false);
   }

   private Map wrapObjectArray(Map rowData) {
      Map<K, Iterable<Object>> out = new LinkedHashMap(rowData.size());

      for(Map.Entry e : rowData.entrySet()) {
         if (e.getValue() == null) {
            out.put(e.getKey(), Collections.emptyList());
         } else {
            out.put(e.getKey(), Arrays.asList(e.getValue()));
         }
      }

      return out;
   }

   private Map wrapStringArray(Map rowData) {
      Map<K, Iterable<String>> out = new LinkedHashMap(rowData.size());

      for(Map.Entry e : rowData.entrySet()) {
         if (e.getValue() == null) {
            out.put(e.getKey(), Collections.emptyList());
         } else {
            out.put(e.getKey(), Arrays.asList(e.getValue()));
         }
      }

      return out;
   }

   private Map wrapRecordValues(Map rowData) {
      Map<K, Iterable<String>> out = new LinkedHashMap(rowData.size());

      for(Map.Entry e : rowData.entrySet()) {
         if (e.getValue() == null) {
            out.put(e.getKey(), Collections.emptyList());
         } else {
            out.put(e.getKey(), Arrays.asList(((Record)e.getValue()).getValues()));
         }
      }

      return out;
   }

   public final void writeObjectRowsAndClose(Map headerMapping, Map rowData) {
      try {
         this.writeObjectRows(headerMapping, rowData);
      } finally {
         this.close();
      }

   }

   public final void writeStringRowsAndClose(Map headerMapping, Map rowData) {
      try {
         this.writeStringRows(headerMapping, rowData);
      } finally {
         this.close();
      }

   }

   public final void writeRecordsAndClose(Map headerMapping, Map rowData) {
      try {
         this.writeRecords(headerMapping, rowData);
      } finally {
         this.close();
      }

   }

   public final void writeObjectRowsAndClose(Map rowData) {
      this.writeObjectRowsAndClose((Map)null, rowData);
   }

   public final void writeStringRowsAndClose(Map rowData) {
      this.writeStringRowsAndClose((Map)null, rowData);
   }

   public final void writeRecordsAndClose(Map rowData) {
      this.writeRecordsAndClose((Map)null, rowData);
   }

   public final void writeRowsAndClose(Map headerMapping, Map rowData) {
      try {
         this.writeRows(headerMapping, rowData);
      } finally {
         this.close();
      }

   }

   public final void writeRowsAndClose(Map rowData) {
      this.writeRowsAndClose((Map)null, rowData);
   }

   public final String processRecordToString(Map rowData) {
      return this.processRecordToString((Map)null, rowData);
   }

   public final void processRecord(Map rowData) {
      this.processRecord((Map)null, rowData);
   }

   public final String processRecordToString(Map headerMapping, Map rowData) {
      this.writeValuesFromMap(headerMapping, rowData);
      return this.processValuesToString();
   }

   public final void processRecord(Map headerMapping, Map rowData) {
      this.writeValuesFromMap(headerMapping, rowData);
      this.processValuesToRow();
   }

   public final List processRecordsToString(Map rowData) {
      return this.processRecordsToString((Map)null, rowData);
   }

   public final void processRecords(Map rowData) {
      this.writeRows((Map)null, rowData, (List)null, true);
   }

   public final List processRecordsToString(Map headerMapping, Map rowData) {
      List<String> writtenRows = new ArrayList();
      this.writeRows(headerMapping, rowData, writtenRows, true);
      return writtenRows;
   }

   public final void processRecords(Map headerMapping, Map rowData) {
      this.writeRows(headerMapping, rowData, (List)null, true);
   }

   public final List processObjectRecordsToString(Map rowData) {
      return this.processObjectRecordsToString((Map)null, rowData);
   }

   public final List processObjectRecordsToString(Map headerMapping, Map rowData) {
      List<String> writtenRows = new ArrayList();
      this.writeRows(headerMapping, this.wrapObjectArray(rowData), writtenRows, true);
      return writtenRows;
   }

   public final void processObjectRecords(Map headerMapping, Map rowData) {
      this.writeRows(headerMapping, this.wrapObjectArray(rowData), (List)null, true);
   }

   public final void processObjectRecordsAndClose(Map headerMapping, Map rowData) {
      try {
         this.processObjectRecords(headerMapping, rowData);
      } finally {
         this.close();
      }

   }

   public final void processObjectRecordsAndClose(Map rowData) {
      this.processRecordsAndClose((Map)null, this.wrapObjectArray(rowData));
   }

   public final void processRecordsAndClose(Map headerMapping, Map rowData) {
      try {
         this.processRecords(headerMapping, rowData);
      } finally {
         this.close();
      }

   }

   public final void processRecordsAndClose(Map rowData) {
      this.processRecordsAndClose((Map)null, rowData);
   }

   private Object[] getContent(Object[] tmp) {
      return AbstractException.restrictContent(this.errorContentLength, tmp);
   }

   private String getContent(CharSequence tmp) {
      return AbstractException.restrictContent(this.errorContentLength, tmp);
   }

   protected final boolean allowTrim(int fieldIndex) {
      return !this.writingHeaders || fieldIndex >= this.headerTrimFlags.length || this.headerTrimFlags[fieldIndex];
   }
}
