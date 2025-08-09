package org.apache.derby.impl.sql.compile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class UCode_CharStream implements CharStream {
   int charCnt;
   int[] charOffset;
   public static final boolean staticFlag = false;
   public int bufpos;
   int bufsize;
   int available;
   int tokenBegin;
   private int[] bufline;
   private int[] bufcolumn;
   private int column;
   private int line;
   private boolean prevCharIsCR;
   private boolean prevCharIsLF;
   private Reader inputStream;
   private char[] nextCharBuf;
   private char[] buffer;
   private int maxNextCharInd;
   private int nextCharInd;
   private int inBuf;

   private void ExpandBuff(boolean var1) {
      char[] var2 = new char[this.bufsize + 2048];
      int[] var3 = new int[this.bufsize + 2048];
      int[] var4 = new int[this.bufsize + 2048];
      int[] var5 = new int[this.bufsize + 2048];

      try {
         if (var1) {
            System.arraycopy(this.buffer, this.tokenBegin, var2, 0, this.bufsize - this.tokenBegin);
            System.arraycopy(this.buffer, 0, var2, this.bufsize - this.tokenBegin, this.bufpos);
            this.buffer = var2;
            System.arraycopy(this.bufline, this.tokenBegin, var3, 0, this.bufsize - this.tokenBegin);
            System.arraycopy(this.bufline, 0, var3, this.bufsize - this.tokenBegin, this.bufpos);
            this.bufline = var3;
            System.arraycopy(this.bufcolumn, this.tokenBegin, var4, 0, this.bufsize - this.tokenBegin);
            System.arraycopy(this.bufcolumn, 0, var4, this.bufsize - this.tokenBegin, this.bufpos);
            this.bufcolumn = var4;
            System.arraycopy(this.charOffset, this.tokenBegin, var5, 0, this.bufsize - this.tokenBegin);
            System.arraycopy(this.charOffset, 0, var5, this.bufsize - this.tokenBegin, this.bufpos);
            this.charOffset = var5;
            this.bufpos += this.bufsize - this.tokenBegin;
         } else {
            System.arraycopy(this.buffer, this.tokenBegin, var2, 0, this.bufsize - this.tokenBegin);
            this.buffer = var2;
            System.arraycopy(this.bufline, this.tokenBegin, var3, 0, this.bufsize - this.tokenBegin);
            this.bufline = var3;
            System.arraycopy(this.bufcolumn, this.tokenBegin, var4, 0, this.bufsize - this.tokenBegin);
            this.bufcolumn = var4;
            System.arraycopy(this.charOffset, this.tokenBegin, var5, 0, this.bufsize - this.tokenBegin);
            this.charOffset = var5;
            this.bufpos -= this.tokenBegin;
         }
      } catch (Throwable var7) {
         throw new Error(var7.getMessage());
      }

      this.available = this.bufsize += 2048;
      this.tokenBegin = 0;
   }

   private void FillBuff() throws IOException {
      if (this.maxNextCharInd == this.nextCharBuf.length) {
         this.maxNextCharInd = this.nextCharInd = 0;
      }

      try {
         int var1;
         if ((var1 = this.inputStream.read(this.nextCharBuf, this.maxNextCharInd, this.nextCharBuf.length - this.maxNextCharInd)) == -1) {
            this.inputStream.close();
            throw new IOException();
         } else {
            this.maxNextCharInd += var1;
         }
      } catch (IOException var3) {
         if (this.bufpos != 0) {
            --this.bufpos;
            this.backup(0);
         } else {
            this.bufline[this.bufpos] = this.line;
            this.bufcolumn[this.bufpos] = this.column;
         }

         if (this.tokenBegin == -1) {
            this.tokenBegin = this.bufpos;
         }

         throw var3;
      }
   }

   private char ReadChar() throws IOException {
      if (++this.nextCharInd >= this.maxNextCharInd) {
         this.FillBuff();
      }

      return this.nextCharBuf[this.nextCharInd];
   }

   public char BeginToken() throws IOException {
      if (this.inBuf > 0) {
         --this.inBuf;
         return this.buffer[this.tokenBegin = this.bufpos == this.bufsize - 1 ? (this.bufpos = 0) : ++this.bufpos];
      } else {
         this.tokenBegin = 0;
         this.bufpos = -1;
         char var1 = this.readChar();
         return var1;
      }
   }

   private void UpdateLineColumn(char var1) {
      ++this.column;
      if (this.prevCharIsLF) {
         this.prevCharIsLF = false;
         this.line += this.column = 1;
      } else if (this.prevCharIsCR) {
         this.prevCharIsCR = false;
         if (var1 == '\n') {
            this.prevCharIsLF = true;
         } else {
            this.line += this.column = 1;
         }
      }

      switch (var1) {
         case '\t':
            --this.column;
            this.column += 8 - (this.column & 7);
            break;
         case '\n':
            this.prevCharIsLF = true;
         case '\u000b':
         case '\f':
         default:
            break;
         case '\r':
            this.prevCharIsCR = true;
      }

      this.bufline[this.bufpos] = this.line;
      this.bufcolumn[this.bufpos] = this.column;
   }

   public final char readChar() throws IOException {
      if (this.inBuf > 0) {
         --this.inBuf;
         return this.buffer[this.bufpos == this.bufsize - 1 ? (this.bufpos = 0) : ++this.bufpos];
      } else {
         if (++this.bufpos == this.available) {
            if (this.available == this.bufsize) {
               if (this.tokenBegin > 2048) {
                  this.bufpos = 0;
                  this.available = this.tokenBegin;
               } else if (this.tokenBegin < 0) {
                  this.bufpos = 0;
               } else {
                  this.ExpandBuff(false);
               }
            } else if (this.available > this.tokenBegin) {
               this.available = this.bufsize;
            } else if (this.tokenBegin - this.available < 2048) {
               this.ExpandBuff(true);
            } else {
               this.available = this.tokenBegin;
            }
         }

         char var1 = this.ReadChar();
         this.UpdateLineColumn(var1);
         this.charOffset[this.bufpos] = this.charCnt++;
         return this.buffer[this.bufpos] = var1;
      }
   }

   /** @deprecated */
   @Deprecated
   public final int getColumn() {
      return this.bufcolumn[this.bufpos];
   }

   /** @deprecated */
   @Deprecated
   public final int getLine() {
      return this.bufline[this.bufpos];
   }

   public final int getEndColumn() {
      return this.bufcolumn[this.bufpos];
   }

   public final int getEndLine() {
      return this.bufline[this.bufpos];
   }

   public final int getBeginColumn() {
      return this.bufcolumn[this.tokenBegin];
   }

   public final int getBeginLine() {
      return this.bufline[this.tokenBegin];
   }

   public final int getBeginOffset() {
      return this.charOffset[this.tokenBegin];
   }

   public final int getEndOffset() {
      return this.charOffset[this.bufpos];
   }

   public final void backup(int var1) {
      this.inBuf += var1;
      if ((this.bufpos -= var1) < 0) {
         this.bufpos += this.bufsize;
      }

   }

   public UCode_CharStream(Reader var1, int var2, int var3, int var4) {
      this.bufpos = -1;
      this.column = 0;
      this.line = 1;
      this.prevCharIsCR = false;
      this.prevCharIsLF = false;
      this.maxNextCharInd = 0;
      this.nextCharInd = -1;
      this.inBuf = 0;
      this.inputStream = var1;
      this.line = var2;
      this.column = var3 - 1;
      this.available = this.bufsize = var4;
      this.buffer = new char[var4];
      this.nextCharBuf = new char[var4];
      this.bufline = new int[var4];
      this.bufcolumn = new int[var4];
      this.charOffset = new int[var4];
   }

   public UCode_CharStream(Reader var1, int var2, int var3) {
      this((Reader)var1, var2, var3, 4096);
   }

   public void ReInit(Reader var1, int var2, int var3, int var4) {
      this.inputStream = var1;
      this.line = var2;
      this.column = var3 - 1;
      if (this.buffer == null || var4 != this.buffer.length) {
         this.available = this.bufsize = var4;
         this.buffer = new char[var4];
         this.nextCharBuf = new char[var4];
         this.bufline = new int[var4];
         this.bufcolumn = new int[var4];
      }

      this.inBuf = this.maxNextCharInd = this.charCnt = this.tokenBegin = 0;
      this.nextCharInd = this.bufpos = -1;
   }

   public void ReInit(Reader var1, int var2, int var3) {
      this.ReInit((Reader)var1, var2, var3, 4096);
   }

   public UCode_CharStream(InputStream var1, int var2, int var3, int var4) {
      this((Reader)(new InputStreamReader(var1)), var2, var3, 4096);
   }

   public UCode_CharStream(InputStream var1, int var2, int var3) {
      this((InputStream)var1, var2, var3, 4096);
   }

   public void ReInit(InputStream var1, int var2, int var3, int var4) {
      this.ReInit((Reader)(new InputStreamReader(var1)), var2, var3, 4096);
   }

   public void ReInit(InputStream var1, int var2, int var3) {
      this.ReInit((InputStream)var1, var2, var3, 4096);
   }

   public final String GetImage() {
      if (this.bufpos >= this.tokenBegin) {
         return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1);
      } else {
         String var10000 = new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin);
         return var10000 + new String(this.buffer, 0, this.bufpos + 1);
      }
   }

   public final char[] GetSuffix(int var1) {
      char[] var2 = new char[var1];
      if (this.bufpos + 1 >= var1) {
         System.arraycopy(this.buffer, this.bufpos - var1 + 1, var2, 0, var1);
      } else {
         System.arraycopy(this.buffer, this.bufsize - (var1 - this.bufpos - 1), var2, 0, var1 - this.bufpos - 1);
         System.arraycopy(this.buffer, 0, var2, var1 - this.bufpos - 1, this.bufpos + 1);
      }

      return var2;
   }

   public void Done() {
      this.nextCharBuf = null;
      this.buffer = null;
      this.bufline = null;
      this.bufcolumn = null;
      this.charOffset = null;
   }

   public void adjustBeginLineColumn(int var1, int var2) {
      int var3 = this.tokenBegin;
      int var4;
      if (this.bufpos >= this.tokenBegin) {
         var4 = this.bufpos - this.tokenBegin + this.inBuf + 1;
      } else {
         var4 = this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf;
      }

      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      int var8;
      for(var8 = 0; var5 < var4; ++var5) {
         int var10000 = this.bufline[var6 = var3 % this.bufsize];
         ++var3;
         if (var10000 != this.bufline[var7 = var3 % this.bufsize]) {
            break;
         }

         this.bufline[var6] = var1;
         int var9 = var8 + this.bufcolumn[var7] - this.bufcolumn[var6];
         this.bufcolumn[var6] = var2 + var8;
         var8 = var9;
      }

      if (var5 < var4) {
         this.bufline[var6] = var1++;
         this.bufcolumn[var6] = var2 + var8;

         while(var5++ < var4) {
            int var13 = this.bufline[var6 = var3 % this.bufsize];
            ++var3;
            if (var13 != this.bufline[var3 % this.bufsize]) {
               this.bufline[var6] = var1++;
            } else {
               this.bufline[var6] = var1;
            }
         }
      }

      this.line = this.bufline[var6];
      this.column = this.bufcolumn[var6];
   }
}
