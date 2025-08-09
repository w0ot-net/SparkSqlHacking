package com.univocity.parsers.common;

import com.univocity.parsers.common.input.AbstractCharInputReader;
import com.univocity.parsers.common.input.CharInputReader;
import com.univocity.parsers.common.input.DefaultCharInputReader;
import com.univocity.parsers.common.input.EOFException;
import com.univocity.parsers.common.input.InputAnalysisProcess;
import com.univocity.parsers.common.input.LookaheadCharInputReader;
import com.univocity.parsers.common.iterators.RecordIterator;
import com.univocity.parsers.common.iterators.RowIterator;
import com.univocity.parsers.common.processor.core.NoopProcessor;
import com.univocity.parsers.common.processor.core.Processor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.common.record.RecordMetaData;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractParser {
   protected final CommonParserSettings settings;
   protected final ParserOutput output;
   private final long recordsToRead;
   protected final char comment;
   private final LineReader lineReader = new LineReader();
   protected ParsingContext context;
   protected Processor processor;
   protected CharInputReader input;
   protected char ch;
   private final ProcessorErrorHandler errorHandler;
   private final long rowsToSkip;
   protected final Map comments;
   protected String lastComment;
   private final boolean collectComments;
   private final int errorContentLength;
   private boolean extractingHeaders = false;
   private final boolean extractHeaders;
   protected final int whitespaceRangeStart;
   protected boolean ignoreTrailingWhitespace;
   protected boolean ignoreLeadingWhitespace;
   private final boolean processComments;

   public AbstractParser(CommonParserSettings settings) {
      settings.autoConfigure();
      this.settings = settings;
      this.errorContentLength = settings.getErrorContentLength();
      this.ignoreTrailingWhitespace = settings.getIgnoreTrailingWhitespaces();
      this.ignoreLeadingWhitespace = settings.getIgnoreLeadingWhitespaces();
      this.output = new ParserOutput(this, settings);
      this.processor = settings.getProcessor();
      this.recordsToRead = settings.getNumberOfRecordsToRead();
      this.comment = settings.getFormat().getComment();
      this.errorHandler = settings.getProcessorErrorHandler();
      this.rowsToSkip = settings.getNumberOfRowsToSkip();
      this.collectComments = settings.isCommentCollectionEnabled();
      this.comments = (Map)(this.collectComments ? new TreeMap() : Collections.emptyMap());
      this.extractHeaders = settings.isHeaderExtractionEnabled();
      this.whitespaceRangeStart = settings.getWhitespaceRangeStart();
      this.processComments = settings.isCommentProcessingEnabled();
   }

   protected void processComment() {
      if (this.collectComments) {
         long line = this.input.lineCount();
         String comment = this.input.readComment();
         if (comment != null) {
            this.lastComment = comment;
            this.comments.put(line, this.lastComment);
         }
      } else {
         try {
            this.input.skipLines(1L);
         } catch (IllegalArgumentException var4) {
         }
      }

   }

   public final void parse(Reader reader) {
      this.beginParsing(reader);

      try {
         while(!this.context.isStopped()) {
            this.input.markRecordStart();
            this.ch = this.input.nextChar();
            if (this.processComments && this.inComment()) {
               this.processComment();
            } else {
               if (this.output.pendingRecords.isEmpty()) {
                  this.parseRecord();
               }

               String[] row = this.output.rowParsed();
               if (row != null) {
                  if (this.recordsToRead >= 0L && this.context.currentRecord() >= this.recordsToRead) {
                     this.context.stop();
                     if (this.recordsToRead == 0L) {
                        this.stopParsing();
                        return;
                     }
                  }

                  if (this.processor != NoopProcessor.instance) {
                     this.rowProcessed(row);
                  }
               }
            }
         }

         this.stopParsing();
      } catch (EOFException var15) {
         try {
            this.handleEOF();

            while(!this.output.pendingRecords.isEmpty()) {
               this.handleEOF();
            }
         } finally {
            this.stopParsing();
         }
      } catch (Throwable var16) {
         Throwable ex = var16;

         try {
            ex = this.handleException(ex);
         } finally {
            this.stopParsing(ex);
         }
      }

   }

   protected abstract void parseRecord();

   protected boolean consumeValueOnEOF() {
      return false;
   }

   private String[] handleEOF() {
      String[] row = null;

      try {
         boolean consumeValueOnEOF = this.consumeValueOnEOF();
         if (this.output.column != 0 || consumeValueOnEOF && !this.context.isStopped()) {
            if (this.output.appender.length() <= 0 && !consumeValueOnEOF) {
               if (this.input.currentParsedContentLength() > 0) {
                  this.output.emptyParsed();
               }
            } else {
               this.output.valueParsed();
            }

            row = this.output.rowParsed();
         } else if (this.output.appender.length() <= 0 && this.input.currentParsedContentLength() <= 0) {
            if (!this.output.pendingRecords.isEmpty()) {
               row = (String[])this.output.pendingRecords.poll();
            }
         } else {
            if (this.output.appender.length() == 0) {
               this.output.emptyParsed();
            } else {
               this.output.valueParsed();
            }

            row = this.output.rowParsed();
         }
      } catch (Throwable e) {
         throw this.handleException(e);
      }

      if (row != null && this.processor != NoopProcessor.instance) {
         this.rowProcessed(row);
      }

      return row;
   }

   public final void beginParsing(Reader reader) {
      this.output.reset();
      if (reader instanceof LineReader) {
         this.input = new DefaultCharInputReader(this.settings.getFormat().getLineSeparator(), this.settings.getFormat().getNormalizedNewline(), this.settings.getInputBufferSize(), this.whitespaceRangeStart, true);
      } else {
         this.input = this.settings.newCharInputReader(this.whitespaceRangeStart);
      }

      this.input.enableNormalizeLineEndings(true);
      this.context = this.createParsingContext();
      if (this.processor instanceof DefaultConversionProcessor) {
         DefaultConversionProcessor conversionProcessor = (DefaultConversionProcessor)this.processor;
         conversionProcessor.errorHandler = this.errorHandler;
         conversionProcessor.context = this.context;
      }

      if (this.input instanceof AbstractCharInputReader) {
         AbstractCharInputReader inputReader = (AbstractCharInputReader)this.input;
         inputReader.addInputAnalysisProcess(this.getInputAnalysisProcess());

         for(InputAnalysisProcess p : this.settings.getInputAnalysisProcesses()) {
            inputReader.addInputAnalysisProcess(p);
         }
      }

      try {
         this.input.start(reader);
      } catch (Throwable t) {
         throw this.handleException(t);
      }

      this.input.skipLines(this.rowsToSkip);
      this.initialize();
      this.processor.processStarted(this.context);
   }

   void extractHeadersIfRequired() {
      while(this.extractHeaders && this.output.parsedHeaders == null && !this.context.isStopped() && !this.extractingHeaders) {
         Processor userProvidedProcessor = this.processor;

         try {
            this.processor = NoopProcessor.instance;
            this.extractingHeaders = true;
            this.parseNext();
         } finally {
            this.extractingHeaders = false;
            this.processor = userProvidedProcessor;
         }
      }

   }

   protected ParsingContext createParsingContext() {
      DefaultParsingContext out = new DefaultParsingContext(this, this.errorContentLength);
      out.stopped = false;
      return out;
   }

   protected void initialize() {
   }

   protected InputAnalysisProcess getInputAnalysisProcess() {
      return null;
   }

   private String getParsedContent(CharSequence tmp) {
      return "Parsed content: " + AbstractException.restrictContent(this.errorContentLength, tmp);
   }

   private TextParsingException handleException(Throwable ex) {
      if (this.context != null) {
         this.context.stop();
      }

      if (ex instanceof DataProcessingException) {
         DataProcessingException error = (DataProcessingException)ex;
         error.restrictContent(this.errorContentLength);
         error.setContext(this.context);
         throw error;
      } else {
         String message = ex.getClass().getName() + " - " + ex.getMessage();
         char[] chars = this.output.appender.getChars();
         if (chars != null) {
            int length = this.output.appender.length();
            if (length > chars.length) {
               message = "Length of parsed input (" + length + ") exceeds the maximum number of characters defined in" + " your parser settings (" + this.settings.getMaxCharsPerColumn() + "). ";
               length = chars.length;
            }

            String tmp = new String(chars);
            if (tmp.contains("\n") || tmp.contains("\r")) {
               tmp = ArgumentUtils.displayLineSeparators(tmp, true);
               String lineSeparator = ArgumentUtils.displayLineSeparators(this.settings.getFormat().getLineSeparatorString(), false);
               message = message + "\nIdentified line separator characters in the parsed content. This may be the cause of the error. The line separator in your parser settings is set to '" + lineSeparator + "'. " + this.getParsedContent(tmp);
            }

            int nullCharacterCount = 0;
            int maxLength = length > 1073741823 ? 1073741822 : length;
            StringBuilder s = new StringBuilder(maxLength);

            for(int i = 0; i < maxLength; ++i) {
               if (chars[i] == 0) {
                  s.append('\\');
                  s.append('0');
                  ++nullCharacterCount;
               } else {
                  s.append(chars[i]);
               }
            }

            tmp = s.toString();
            if (nullCharacterCount > 0) {
               message = message + "\nIdentified " + nullCharacterCount + " null characters ('\u0000') on parsed content. This may " + "indicate the data is corrupt or its encoding is invalid. Parsed content:\n\t" + this.getParsedContent(tmp);
            }
         }

         if (ex instanceof ArrayIndexOutOfBoundsException) {
            try {
               int index = Integer.parseInt(ex.getMessage());
               if (index == this.settings.getMaxCharsPerColumn()) {
                  message = message + "\nHint: Number of characters processed may have exceeded limit of " + index + " characters per column. Use settings.setMaxCharsPerColumn(int) to define the maximum number of characters a column can have";
               }

               if (index == this.settings.getMaxColumns()) {
                  message = message + "\nHint: Number of columns processed may have exceeded limit of " + index + " columns. Use settings.setMaxColumns(int) to define the maximum number of columns your input can have";
               }

               message = message + "\nEnsure your configuration is correct, with delimiters, quotes and escape sequences that match the input format you are trying to parse";
            } catch (Throwable var11) {
            }
         }

         try {
            if (!message.isEmpty()) {
               message = message + "\n";
            }

            message = message + "Parser Configuration: " + this.settings.toString();
         } catch (Exception var10) {
         }

         if (this.errorContentLength == 0) {
            this.output.appender.reset();
         }

         TextParsingException out = new TextParsingException(this.context, message, ex);
         out.setErrorContentLength(this.errorContentLength);
         return out;
      }
   }

   private void stopParsing(Throwable error) {
      if (error != null) {
         try {
            this.stopParsing();
         } catch (Throwable var3) {
         }

         if (error instanceof DataProcessingException) {
            DataProcessingException ex = (DataProcessingException)error;
            ex.setContext(this.context);
            throw ex;
         } else if (error instanceof RuntimeException) {
            throw (RuntimeException)error;
         } else if (error instanceof Error) {
            throw (Error)error;
         } else {
            throw new IllegalStateException(error.getMessage(), error);
         }
      } else {
         this.stopParsing();
      }
   }

   public final void stopParsing() {
      try {
         this.ch = 0;

         try {
            if (this.context != null) {
               this.context.stop();
            }
         } finally {
            try {
               if (this.processor != null) {
                  this.processor.processEnded(this.context);
               }
            } finally {
               if (this.output != null) {
                  this.output.appender.reset();
               }

               if (this.input != null) {
                  this.input.stop();
               }

            }

         }

      } catch (Throwable error) {
         throw this.handleException(error);
      }
   }

   private List beginParseAll(boolean validateReader, Reader reader, int expectedRowCount) {
      if (reader == null) {
         if (validateReader) {
            throw new IllegalStateException("Input reader must not be null");
         }

         if (this.context == null) {
            throw new IllegalStateException("Input not defined. Please call method 'beginParsing()' with a valid input.");
         }

         if (this.context.isStopped()) {
            return Collections.emptyList();
         }
      }

      List<T> out = new ArrayList(expectedRowCount <= 0 ? 10000 : expectedRowCount);
      if (reader != null) {
         this.beginParsing(reader);
      }

      return out;
   }

   public List parseAll(int expectedRowCount) {
      return this.internalParseAll(false, (Reader)null, expectedRowCount);
   }

   public List parseAll() {
      return this.internalParseAll(false, (Reader)null, -1);
   }

   public List parseAllRecords(int expectedRowCount) {
      return this.internalParseAllRecords(false, (Reader)null, expectedRowCount);
   }

   public List parseAllRecords() {
      return this.internalParseAllRecords(false, (Reader)null, -1);
   }

   public final List parseAll(Reader reader) {
      return this.parseAll((Reader)reader, 0);
   }

   public final List parseAll(Reader reader, int expectedRowCount) {
      return this.internalParseAll(true, reader, expectedRowCount);
   }

   private final List internalParseAll(boolean validateReader, Reader reader, int expectedRowCount) {
      List<String[]> out = this.beginParseAll(validateReader, reader, expectedRowCount);

      String[] row;
      while((row = this.parseNext()) != null) {
         out.add(row);
      }

      return out;
   }

   protected boolean inComment() {
      return this.ch == this.comment;
   }

   public final String[] parseNext() {
      try {
         while(true) {
            if (!this.context.isStopped()) {
               this.input.markRecordStart();
               this.ch = this.input.nextChar();
               if (this.processComments && this.inComment()) {
                  this.processComment();
                  continue;
               }

               if (this.output.pendingRecords.isEmpty()) {
                  this.parseRecord();
               }

               String[] row = this.output.rowParsed();
               if (row != null) {
                  if (this.recordsToRead >= 0L && this.context.currentRecord() >= this.recordsToRead) {
                     this.context.stop();
                     if (this.recordsToRead == 0L) {
                        this.stopParsing();
                        return null;
                     }
                  }

                  if (this.processor != NoopProcessor.instance) {
                     this.rowProcessed(row);
                  }

                  return row;
               }

               if (!this.extractingHeaders) {
                  continue;
               }

               return null;
            }

            if (this.output.column != 0) {
               return this.output.rowParsed();
            }

            this.stopParsing();
            return null;
         }
      } catch (EOFException var9) {
         String[] row = this.handleEOF();
         if (this.output.pendingRecords.isEmpty()) {
            this.stopParsing();
         }

         return row;
      } catch (NullPointerException ex) {
         if (this.context == null) {
            throw new IllegalStateException("Cannot parse without invoking method beginParsing(Reader) first");
         } else {
            if (this.input != null) {
               this.stopParsing();
            }

            throw new IllegalStateException("Error parsing next record.", ex);
         }
      } catch (Throwable var11) {
         Throwable ex = var11;

         try {
            ex = this.handleException(ex);
         } finally {
            this.stopParsing(ex);
         }

         return null;
      }
   }

   protected final void reloadHeaders() {
      this.output.initializeHeaders();
      if (this.context instanceof DefaultParsingContext) {
         ((DefaultParsingContext)this.context).reset();
      }

   }

   public final Record parseRecord(String line) {
      String[] values = this.parseLine(line);
      return values == null ? null : this.context.toRecord(values);
   }

   public final String[] parseLine(String line) {
      if (line != null && !line.isEmpty()) {
         this.lineReader.setLine(line);
         if (this.context != null && !this.context.isStopped()) {
            if (this.input instanceof DefaultCharInputReader) {
               ((DefaultCharInputReader)this.input).reloadBuffer();
            } else if (this.input instanceof LookaheadCharInputReader) {
               ((LookaheadCharInputReader)this.input).reloadBuffer();
            }
         } else {
            this.beginParsing((Reader)this.lineReader);
         }

         try {
            while(!this.context.isStopped()) {
               this.input.markRecordStart();
               this.ch = this.input.nextChar();
               if (this.processComments && this.inComment()) {
                  this.processComment();
                  return null;
               }

               if (this.output.pendingRecords.isEmpty()) {
                  this.parseRecord();
               }

               String[] row = this.output.rowParsed();
               if (row != null) {
                  if (this.processor != NoopProcessor.instance) {
                     this.rowProcessed(row);
                  }

                  return row;
               }
            }

            return null;
         } catch (EOFException var9) {
            return this.handleEOF();
         } catch (NullPointerException ex) {
            if (this.input != null) {
               this.stopParsing((Throwable)null);
            }

            throw new IllegalStateException("Error parsing next record.", ex);
         } catch (Throwable var11) {
            Throwable ex = var11;

            try {
               ex = this.handleException(ex);
            } finally {
               this.stopParsing(ex);
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private void rowProcessed(String[] row) {
      Internal.process(row, this.processor, this.context, this.errorHandler);
   }

   public final void parse(File file) {
      this.parse(ArgumentUtils.newReader(file));
   }

   public final void parse(File file, String encoding) {
      this.parse(ArgumentUtils.newReader(file, encoding));
   }

   public final void parse(File file, Charset encoding) {
      this.parse(ArgumentUtils.newReader(file, encoding));
   }

   public final void parse(InputStream input) {
      this.parse(ArgumentUtils.newReader(input));
   }

   public final void parse(InputStream input, String encoding) {
      this.parse(ArgumentUtils.newReader(input, encoding));
   }

   public final void parse(InputStream input, Charset encoding) {
      this.parse(ArgumentUtils.newReader(input, encoding));
   }

   public final void beginParsing(File file) {
      this.beginParsing(ArgumentUtils.newReader(file));
   }

   public final void beginParsing(File file, String encoding) {
      this.beginParsing(ArgumentUtils.newReader(file, encoding));
   }

   public final void beginParsing(File file, Charset encoding) {
      this.beginParsing(ArgumentUtils.newReader(file, encoding));
   }

   public final void beginParsing(InputStream input) {
      this.beginParsing(ArgumentUtils.newReader(input));
   }

   public final void beginParsing(InputStream input, String encoding) {
      this.beginParsing(ArgumentUtils.newReader(input, encoding));
   }

   public final void beginParsing(InputStream input, Charset encoding) {
      this.beginParsing(ArgumentUtils.newReader(input, encoding));
   }

   public final List parseAll(File file, int expectedRowCount) {
      return this.parseAll(ArgumentUtils.newReader(file), expectedRowCount);
   }

   public final List parseAll(File file, String encoding, int expectedRowCount) {
      return this.parseAll(ArgumentUtils.newReader(file, encoding), expectedRowCount);
   }

   public final List parseAll(File file, Charset encoding, int expectedRowCount) {
      return this.parseAll(ArgumentUtils.newReader(file, encoding), expectedRowCount);
   }

   public final List parseAll(InputStream input, int expectedRowCount) {
      return this.parseAll(ArgumentUtils.newReader(input), expectedRowCount);
   }

   public final List parseAll(InputStream input, String encoding, int expectedRowCount) {
      return this.parseAll(ArgumentUtils.newReader(input, encoding), expectedRowCount);
   }

   public final List parseAll(InputStream input, Charset encoding, int expectedRowCount) {
      return this.parseAll(ArgumentUtils.newReader(input, encoding), expectedRowCount);
   }

   public final List parseAll(File file) {
      return this.parseAll(ArgumentUtils.newReader(file));
   }

   public final List parseAll(File file, String encoding) {
      return this.parseAll(ArgumentUtils.newReader(file, encoding));
   }

   public final List parseAll(File file, Charset encoding) {
      return this.parseAll(ArgumentUtils.newReader(file, encoding));
   }

   public final List parseAll(InputStream input) {
      return this.parseAll(ArgumentUtils.newReader(input));
   }

   public final List parseAll(InputStream input, String encoding) {
      return this.parseAll(ArgumentUtils.newReader(input, encoding));
   }

   public final List parseAll(InputStream input, Charset encoding) {
      return this.parseAll(ArgumentUtils.newReader(input, encoding));
   }

   public final List parseAllRecords(File file, int expectedRowCount) {
      return this.parseAllRecords(ArgumentUtils.newReader(file), expectedRowCount);
   }

   public final List parseAllRecords(File file, String encoding, int expectedRowCount) {
      return this.parseAllRecords(ArgumentUtils.newReader(file, encoding), expectedRowCount);
   }

   public final List parseAllRecords(File file, Charset encoding, int expectedRowCount) {
      return this.parseAllRecords(ArgumentUtils.newReader(file, encoding), expectedRowCount);
   }

   public final List parseAllRecords(InputStream input, int expectedRowCount) {
      return this.parseAllRecords(ArgumentUtils.newReader(input), expectedRowCount);
   }

   public final List parseAllRecords(InputStream input, String encoding, int expectedRowCount) {
      return this.parseAllRecords(ArgumentUtils.newReader(input, encoding), expectedRowCount);
   }

   public final List parseAllRecords(InputStream input, Charset encoding, int expectedRowCount) {
      return this.parseAllRecords(ArgumentUtils.newReader(input, encoding), expectedRowCount);
   }

   public final List parseAllRecords(File file) {
      return this.parseAllRecords(ArgumentUtils.newReader(file));
   }

   public final List parseAllRecords(File file, String encoding) {
      return this.parseAllRecords(ArgumentUtils.newReader(file, encoding));
   }

   public final List parseAllRecords(File file, Charset encoding) {
      return this.parseAllRecords(ArgumentUtils.newReader(file, encoding));
   }

   public final List parseAllRecords(InputStream input) {
      return this.parseAllRecords(ArgumentUtils.newReader(input));
   }

   public final List parseAllRecords(InputStream input, String encoding) {
      return this.parseAllRecords(ArgumentUtils.newReader(input, encoding));
   }

   public final List parseAllRecords(InputStream input, Charset encoding) {
      return this.parseAllRecords(ArgumentUtils.newReader(input, encoding));
   }

   public final List parseAllRecords(Reader reader, int expectedRowCount) {
      return this.internalParseAllRecords(true, reader, expectedRowCount);
   }

   private List internalParseAllRecords(boolean validateReader, Reader reader, int expectedRowCount) {
      List<Record> out = this.beginParseAll(validateReader, reader, expectedRowCount);
      if (this.context.isStopped()) {
         return out;
      } else {
         Record record;
         while((record = this.parseNextRecord()) != null) {
            out.add(record);
         }

         return out;
      }
   }

   public final List parseAllRecords(Reader reader) {
      return this.parseAllRecords((Reader)reader, 0);
   }

   public final Record parseNextRecord() {
      String[] row = this.parseNext();
      return row != null ? this.context.toRecord(row) : null;
   }

   final Map getComments() {
      return this.comments;
   }

   final String getLastComment() {
      return this.lastComment;
   }

   final String[] getParsedHeaders() {
      this.extractHeadersIfRequired();
      return this.output.parsedHeaders;
   }

   public final ParsingContext getContext() {
      return this.context;
   }

   public final RecordMetaData getRecordMetadata() {
      if (this.context == null) {
         throw new IllegalStateException("Record metadata not available. The parser has not been started.");
      } else {
         return this.context.recordMetaData();
      }
   }

   public final IterableResult iterate(File input, String encoding) {
      return this.iterate(input, Charset.forName(encoding));
   }

   public final IterableResult iterate(File input, Charset encoding) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterate(File input) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterate(Reader input) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterate(InputStream input, String encoding) {
      return this.iterate(input, Charset.forName(encoding));
   }

   public final IterableResult iterate(InputStream input, Charset encoding) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterate(InputStream input) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterateRecords(File input, String encoding) {
      return this.iterateRecords(input, Charset.forName(encoding));
   }

   public final IterableResult iterateRecords(File input, Charset encoding) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterateRecords(File input) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterateRecords(Reader input) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterateRecords(InputStream input, String encoding) {
      return this.iterateRecords(input, Charset.forName(encoding));
   }

   public final IterableResult iterateRecords(InputStream input, Charset encoding) {
      // $FF: Couldn't be decompiled
   }

   public final IterableResult iterateRecords(InputStream input) {
      // $FF: Couldn't be decompiled
   }
}
