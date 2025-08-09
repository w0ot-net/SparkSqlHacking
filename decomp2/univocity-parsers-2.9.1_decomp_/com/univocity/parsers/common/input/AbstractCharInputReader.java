package com.univocity.parsers.common.input;

import com.univocity.parsers.common.Format;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractCharInputReader implements CharInputReader {
   private final ExpandingCharAppender tmp;
   private boolean lineSeparatorDetected;
   private final boolean detectLineSeparator;
   private List inputAnalysisProcesses;
   private char lineSeparator1;
   private char lineSeparator2;
   private final char normalizedLineSeparator;
   private long lineCount;
   private long charCount;
   private int recordStart;
   final int whitespaceRangeStart;
   private boolean skipping;
   private boolean commentProcessing;
   protected final boolean closeOnStop;
   public int i;
   private char ch;
   public char[] buffer;
   public int length;
   private boolean incrementLineCount;
   private boolean normalizeLineEndings;

   public AbstractCharInputReader(char normalizedLineSeparator, int whitespaceRangeStart, boolean closeOnStop) {
      this((char[])null, normalizedLineSeparator, whitespaceRangeStart, closeOnStop);
   }

   public AbstractCharInputReader(char[] lineSeparator, char normalizedLineSeparator, int whitespaceRangeStart, boolean closeOnStop) {
      this.skipping = false;
      this.commentProcessing = false;
      this.length = -1;
      this.normalizeLineEndings = true;
      this.whitespaceRangeStart = whitespaceRangeStart;
      this.tmp = new ExpandingCharAppender(4096, (String)null, whitespaceRangeStart);
      if (lineSeparator == null) {
         this.detectLineSeparator = true;
         this.submitLineSeparatorDetector();
         this.lineSeparator1 = 0;
         this.lineSeparator2 = 0;
      } else {
         this.setLineSeparator(lineSeparator);
         this.detectLineSeparator = false;
      }

      this.normalizedLineSeparator = normalizedLineSeparator;
      this.closeOnStop = closeOnStop;
   }

   private void submitLineSeparatorDetector() {
      if (this.detectLineSeparator && !this.lineSeparatorDetected) {
         this.addInputAnalysisProcess(new LineSeparatorDetector() {
            protected void apply(char separator1, char separator2) {
               if (separator1 != 0) {
                  AbstractCharInputReader.this.lineSeparatorDetected = true;
                  AbstractCharInputReader.this.lineSeparator1 = separator1;
                  AbstractCharInputReader.this.lineSeparator2 = separator2;
               } else {
                  AbstractCharInputReader.this.setLineSeparator(Format.getSystemLineSeparator());
               }

            }
         });
      }

   }

   private void setLineSeparator(char[] lineSeparator) {
      if (lineSeparator != null && lineSeparator.length != 0) {
         if (lineSeparator.length > 2) {
            throw new IllegalArgumentException("Invalid line separator. Up to 2 characters are expected. Got " + lineSeparator.length + " characters.");
         } else {
            this.lineSeparator1 = lineSeparator[0];
            this.lineSeparator2 = lineSeparator.length == 2 ? lineSeparator[1] : 0;
         }
      } else {
         throw new IllegalArgumentException("Invalid line separator. Expected 1 to 2 characters");
      }
   }

   protected abstract void setReader(Reader var1);

   protected abstract void reloadBuffer();

   protected final void unwrapInputStream(BomInput.BytesProcessedNotification notification) {
      InputStream inputStream = notification.input;
      String encoding = notification.encoding;
      if (encoding != null) {
         try {
            this.start(new InputStreamReader(inputStream, encoding));
         } catch (Exception e) {
            throw new IllegalStateException(e);
         }
      } else {
         this.length = -1;
         this.start(new InputStreamReader(inputStream), false);
      }

   }

   private void start(Reader reader, boolean resetTmp) {
      if (resetTmp) {
         this.tmp.reset();
      }

      this.stop();
      this.setReader(reader);
      this.lineCount = 0L;
      this.lineSeparatorDetected = false;
      this.submitLineSeparatorDetector();
      this.updateBuffer();
      if (this.length > 0 && this.buffer[0] == '\ufeff') {
         ++this.i;
      }

   }

   public final void start(Reader reader) {
      this.start(reader, true);
   }

   private void updateBuffer() {
      if (!this.commentProcessing && this.length - this.recordStart > 0 && this.buffer != null && !this.skipping) {
         this.tmp.append(this.buffer, this.recordStart, this.length - this.recordStart);
      }

      this.recordStart = 0;
      this.reloadBuffer();
      this.charCount += (long)this.i;
      this.i = 0;
      if (this.length == -1) {
         this.stop();
         this.incrementLineCount = true;
      }

      if (this.inputAnalysisProcesses != null) {
         if (this.length > 0 && this.length <= 4) {
            int tmpLength = this.length;
            char[] tmp = Arrays.copyOfRange(this.buffer, 0, this.length + 1);
            List<InputAnalysisProcess> processes = this.inputAnalysisProcesses;
            this.inputAnalysisProcesses = null;
            this.reloadBuffer();
            this.inputAnalysisProcesses = processes;
            if (this.length != -1) {
               char[] newBuffer = new char[tmpLength + this.buffer.length];
               System.arraycopy(tmp, 0, newBuffer, 0, tmpLength);
               System.arraycopy(this.buffer, 0, newBuffer, tmpLength, this.length);
               this.buffer = newBuffer;
               this.length += tmpLength;
            } else {
               this.buffer = tmp;
               this.length = tmpLength;
            }
         }

         try {
            for(InputAnalysisProcess process : this.inputAnalysisProcesses) {
               process.execute(this.buffer, this.length);
            }
         } finally {
            if (this.length > 4) {
               this.inputAnalysisProcesses = null;
            }

         }
      }

   }

   public final void addInputAnalysisProcess(InputAnalysisProcess inputAnalysisProcess) {
      if (inputAnalysisProcess != null) {
         if (this.inputAnalysisProcesses == null) {
            this.inputAnalysisProcesses = new ArrayList();
         }

         this.inputAnalysisProcesses.add(inputAnalysisProcess);
      }
   }

   private void throwEOFException() {
      if (this.incrementLineCount) {
         ++this.lineCount;
      }

      this.ch = 0;
      throw new EOFException();
   }

   public final char nextChar() {
      if (this.length == -1) {
         this.throwEOFException();
      }

      this.ch = this.buffer[this.i++];
      if (this.i >= this.length) {
         this.updateBuffer();
      }

      if (this.lineSeparator1 == this.ch && (this.lineSeparator2 == 0 || this.length != -1 && this.lineSeparator2 == this.buffer[this.i])) {
         ++this.lineCount;
         if (this.normalizeLineEndings) {
            this.ch = this.normalizedLineSeparator;
            if (this.lineSeparator2 == 0) {
               return this.ch;
            }

            if (++this.i >= this.length) {
               if (this.length != -1) {
                  this.updateBuffer();
               } else {
                  this.throwEOFException();
               }
            }
         }
      }

      return this.ch;
   }

   public final char getChar() {
      return this.ch;
   }

   public final long lineCount() {
      return this.lineCount;
   }

   public final void skipLines(long lines) {
      if (lines < 1L) {
         this.skipping = false;
      } else {
         this.skipping = true;
         long expectedLineCount = this.lineCount + lines;

         try {
            while(true) {
               this.nextChar();
               if (this.lineCount >= expectedLineCount) {
                  this.skipping = false;
                  break;
               }
            }
         } catch (EOFException var6) {
            this.skipping = false;
         }

      }
   }

   public String readComment() {
      long expectedLineCount = this.lineCount + 1L;
      this.commentProcessing = true;
      this.tmp.reset();

      String var4;
      try {
         while(true) {
            char ch = this.nextChar();
            if (ch <= ' ' && this.whitespaceRangeStart < ch) {
               ch = this.skipWhitespace(ch, this.normalizedLineSeparator, this.normalizedLineSeparator);
            }

            this.tmp.appendUntil(ch, this, this.normalizedLineSeparator, this.normalizedLineSeparator);
            if (this.lineCount >= expectedLineCount) {
               this.tmp.updateWhitespace();
               var4 = this.tmp.getAndReset();
               return var4;
            }

            this.tmp.appendIgnoringWhitespace(this.nextChar());
         }
      } catch (EOFException var8) {
         this.tmp.updateWhitespace();
         var4 = this.tmp.getAndReset();
      } finally {
         this.commentProcessing = false;
      }

      return var4;
   }

   public final long charCount() {
      return this.charCount + (long)this.i;
   }

   public final void enableNormalizeLineEndings(boolean normalizeLineEndings) {
      this.normalizeLineEndings = normalizeLineEndings;
   }

   public char[] getLineSeparator() {
      return this.lineSeparator2 != 0 ? new char[]{this.lineSeparator1, this.lineSeparator2} : new char[]{this.lineSeparator1};
   }

   public final char skipWhitespace(char ch, char stopChar1, char stopChar2) {
      while(ch <= ' ' && ch != stopChar1 && ch != this.normalizedLineSeparator && ch != stopChar2 && this.whitespaceRangeStart < ch) {
         ch = this.nextChar();
      }

      return ch;
   }

   public final int currentParsedContentLength() {
      return this.i - this.recordStart + this.tmp.length();
   }

   public final String currentParsedContent() {
      if (this.tmp.length() == 0) {
         return this.i > this.recordStart ? new String(this.buffer, this.recordStart, this.i - this.recordStart) : null;
      } else {
         if (this.i > this.recordStart) {
            this.tmp.append(this.buffer, this.recordStart, this.i - this.recordStart);
         }

         return this.tmp.getAndReset();
      }
   }

   public final int lastIndexOf(char ch) {
      if (this.tmp.length() == 0) {
         if (this.i > this.recordStart) {
            int x = this.i - 1;

            for(int c = 0; x >= this.recordStart; ++c) {
               if (this.buffer[x] == ch) {
                  return this.recordStart + c;
               }

               --x;
            }
         }

         return -1;
      } else {
         if (this.i > this.recordStart) {
            int x = this.i - 1;

            for(int c = 0; x >= this.recordStart; ++c) {
               if (this.buffer[x] == ch) {
                  return this.tmp.length() + this.recordStart + c;
               }

               --x;
            }
         }

         return this.tmp.lastIndexOf(ch);
      }
   }

   public final void markRecordStart() {
      this.tmp.reset();
      this.recordStart = this.i % this.length;
   }

   public final boolean skipString(char ch, char stop) {
      if (this.i == 0) {
         return false;
      } else {
         int i;
         for(i = this.i; ch != stop; ch = this.buffer[i++]) {
            if (i >= this.length) {
               return false;
            }

            if (this.lineSeparator1 == ch && (this.lineSeparator2 == 0 || this.lineSeparator2 == this.buffer[i])) {
               break;
            }
         }

         this.i = i - 1;
         this.nextChar();
         return true;
      }
   }

   public final String getString(char ch, char stop, boolean trim, String nullValue, int maxLength) {
      if (this.i == 0) {
         return null;
      } else {
         int i;
         for(i = this.i; ch != stop; ch = this.buffer[i++]) {
            if (i >= this.length) {
               return null;
            }

            if (this.lineSeparator1 == ch && (this.lineSeparator2 == 0 || this.lineSeparator2 == this.buffer[i])) {
               break;
            }
         }

         int pos = this.i - 1;
         int len = i - this.i;
         if (maxLength != -1 && len > maxLength) {
            return null;
         } else {
            this.i = i - 1;
            if (trim) {
               for(int var10 = i - 2; this.buffer[var10] <= ' ' && this.whitespaceRangeStart < this.buffer[var10]; --var10) {
                  --len;
               }
            }

            String out;
            if (len <= 0) {
               out = nullValue;
            } else {
               out = new String(this.buffer, pos, len);
            }

            this.nextChar();
            return out;
         }
      }
   }

   public final String getQuotedString(char quote, char escape, char escapeEscape, int maxLength, char stop1, char stop2, boolean keepQuotes, boolean keepEscape, boolean trimLeading, boolean trimTrailing) {
      if (this.i == 0) {
         return null;
      } else {
         int i = this.i;

         while(i < this.length) {
            this.ch = this.buffer[i];
            if (this.ch != quote) {
               if (this.ch == escape && !keepEscape) {
                  if (i + 1 < this.length) {
                     char next = this.buffer[i + 1];
                     if (next == quote || next == escapeEscape) {
                        return null;
                     }
                  }
               } else if (this.lineSeparator1 == this.ch && this.normalizeLineEndings && (this.lineSeparator2 == 0 || i + 1 < this.length && this.lineSeparator2 == this.buffer[i + 1])) {
                  return null;
               }

               ++i;
            } else {
               if (this.buffer[i - 1] != escape) {
                  if (i + 1 < this.length) {
                     char next = this.buffer[i + 1];
                     if (next == stop1 || next == stop2) {
                        next = (char)this.i;
                        int len = i - this.i;
                        if (maxLength != -1 && len > maxLength) {
                           return null;
                        }

                        if (keepQuotes) {
                           --next;
                           len += 2;
                        } else {
                           if (trimTrailing) {
                              while(len > 0 && this.buffer[next + len - 1] <= ' ') {
                                 --len;
                              }
                           }

                           if (trimLeading) {
                              while(len > 0 && this.buffer[next] <= ' ') {
                                 ++next;
                                 --len;
                              }
                           }
                        }

                        this.i = i + 1;
                        String out;
                        if (len <= 0) {
                           out = "";
                        } else {
                           out = new String(this.buffer, next, len);
                        }

                        if (this.i >= this.length) {
                           this.updateBuffer();
                        }

                        return out;
                     }
                  }

                  return null;
               }

               if (!keepEscape) {
                  return null;
               }

               ++i;
            }
         }

         return null;
      }
   }

   public final boolean skipQuotedString(char quote, char escape, char stop1, char stop2) {
      if (this.i == 0) {
         return false;
      } else {
         int i = this.i;

         while(i < this.length) {
            this.ch = this.buffer[i];
            if (this.ch != quote) {
               if (this.lineSeparator1 == this.ch && this.normalizeLineEndings && (this.lineSeparator2 == 0 || i + 1 < this.length && this.lineSeparator2 == this.buffer[i + 1])) {
                  return false;
               }

               ++i;
            } else {
               if (this.buffer[i - 1] != escape) {
                  if (i + 1 < this.length) {
                     char next = this.buffer[i + 1];
                     if (next == stop1 || next == stop2) {
                        this.i = i + 1;
                        if (this.i >= this.length) {
                           this.updateBuffer();
                        }

                        return true;
                     }
                  }

                  return false;
               }

               ++i;
            }
         }

         return false;
      }
   }
}
