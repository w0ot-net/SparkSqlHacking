package com.ibm.icu.impl.data;

import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.text.UTF16;
import java.io.IOException;

public class TokenIterator {
   private ResourceReader reader;
   private String line;
   private StringBuffer buf;
   private boolean done;
   private int pos;
   private int lastpos;

   public TokenIterator(ResourceReader r) {
      this.reader = r;
      this.line = null;
      this.done = false;
      this.buf = new StringBuffer();
      this.pos = this.lastpos = -1;
   }

   public String next() throws IOException {
      if (this.done) {
         return null;
      } else {
         while(true) {
            if (this.line == null) {
               this.line = this.reader.readLineSkippingComments();
               if (this.line == null) {
                  this.done = true;
                  return null;
               }

               this.pos = 0;
            }

            this.buf.setLength(0);
            this.lastpos = this.pos;
            this.pos = this.nextToken(this.pos);
            if (this.pos >= 0) {
               return this.buf.toString();
            }

            this.line = null;
         }
      }
   }

   public int getLineNumber() {
      return this.reader.getLineNumber();
   }

   public String describePosition() {
      return this.reader.describePosition() + ':' + (this.lastpos + 1);
   }

   private int nextToken(int position) {
      position = PatternProps.skipWhiteSpace(this.line, position);
      if (position == this.line.length()) {
         return -1;
      } else {
         int startpos = position;
         char c = this.line.charAt(position++);
         char quote = 0;
         switch (c) {
            case '"':
            case '\'':
               quote = c;
               break;
            case '#':
               return -1;
            default:
               this.buf.append(c);
         }

         while(position < this.line.length()) {
            c = this.line.charAt(position);
            if (c != '\\') {
               if (quote != 0 && c == quote || quote == 0 && PatternProps.isWhiteSpace(c)) {
                  ++position;
                  return position;
               }

               if (quote == 0 && c == '#') {
                  return position;
               }

               this.buf.append(c);
               ++position;
            } else {
               int cpAndLength = Utility.unescapeAndLengthAt(this.line, position + 1);
               if (cpAndLength < 0) {
                  throw new RuntimeException("Invalid escape at " + this.reader.describePosition() + ':' + position);
               }

               UTF16.append(this.buf, Utility.cpFromCodePointAndLength(cpAndLength));
               position += 1 + Utility.lengthFromCodePointAndLength(cpAndLength);
            }
         }

         if (quote != 0) {
            throw new RuntimeException("Unterminated quote at " + this.reader.describePosition() + ':' + startpos);
         } else {
            return position;
         }
      }
   }
}
