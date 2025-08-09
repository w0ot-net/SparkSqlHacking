package org.apache.jute;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.TreeMap;

public class ToStringOutputArchive implements OutputArchive {
   private PrintStream stream;
   private boolean isFirst = true;
   private long dataSize;

   private void throwExceptionOnError(String tag) throws IOException {
      if (this.stream.checkError()) {
         throw new IOException("Error serializing " + tag);
      }
   }

   private void printCommaUnlessFirst() {
      if (!this.isFirst) {
         this.stream.print(",");
         ++this.dataSize;
      }

      this.isFirst = false;
   }

   public ToStringOutputArchive(OutputStream out) throws UnsupportedEncodingException {
      this.stream = new PrintStream(out, true, "UTF-8");
   }

   public void writeByte(byte b, String tag) throws IOException {
      this.writeLong((long)b, tag);
   }

   public void writeBool(boolean b, String tag) throws IOException {
      this.printCommaUnlessFirst();
      String val = b ? "T" : "F";
      this.stream.print(val);
      ++this.dataSize;
      this.throwExceptionOnError(tag);
   }

   public void writeInt(int i, String tag) throws IOException {
      this.writeLong((long)i, tag);
   }

   public void writeLong(long l, String tag) throws IOException {
      this.printCommaUnlessFirst();
      String strValue = String.valueOf(l);
      this.stream.print(strValue);
      this.dataSize += (long)strValue.length();
      this.throwExceptionOnError(tag);
   }

   public void writeFloat(float f, String tag) throws IOException {
      this.writeDouble((double)f, tag);
   }

   public void writeDouble(double d, String tag) throws IOException {
      this.printCommaUnlessFirst();
      String strValue = String.valueOf(d);
      this.stream.print(strValue);
      this.dataSize += (long)strValue.length();
      this.throwExceptionOnError(tag);
   }

   public void writeString(String s, String tag) throws IOException {
      this.printCommaUnlessFirst();
      String strValue = escapeString(s);
      this.stream.print(strValue);
      this.dataSize += (long)strValue.length();
      this.throwExceptionOnError(tag);
   }

   public void writeBuffer(byte[] buf, String tag) throws IOException {
      this.printCommaUnlessFirst();
      String strValue = escapeBuffer(buf);
      this.stream.print(strValue);
      this.dataSize += (long)strValue.length();
      this.throwExceptionOnError(tag);
   }

   public void writeRecord(Record r, String tag) throws IOException {
      if (r != null) {
         r.serialize(this, tag);
      }
   }

   public void startRecord(Record r, String tag) throws IOException {
      if (tag != null && !"".equals(tag)) {
         this.printCommaUnlessFirst();
         this.stream.print("s{");
         this.dataSize += 2L;
         this.isFirst = true;
      }

   }

   public void endRecord(Record r, String tag) throws IOException {
      if (tag != null && !"".equals(tag)) {
         this.stream.print("}");
         ++this.dataSize;
         this.isFirst = false;
      } else {
         this.stream.print("\n");
         ++this.dataSize;
         this.isFirst = true;
      }

   }

   public void startVector(List v, String tag) throws IOException {
      this.printCommaUnlessFirst();
      this.stream.print("v{");
      this.dataSize += 2L;
      this.isFirst = true;
   }

   public void endVector(List v, String tag) throws IOException {
      this.stream.print("}");
      ++this.dataSize;
      this.isFirst = false;
   }

   public void startMap(TreeMap v, String tag) throws IOException {
      this.printCommaUnlessFirst();
      this.stream.print("m{");
      this.dataSize += 2L;
      this.isFirst = true;
   }

   public void endMap(TreeMap v, String tag) throws IOException {
      this.stream.print("}");
      ++this.dataSize;
      this.isFirst = false;
   }

   public long getDataSize() {
      return this.dataSize;
   }

   private static String escapeString(String s) {
      if (s == null) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder(s.length() + 1);
         sb.append('\'');
         int len = s.length();

         for(int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            switch (c) {
               case '\u0000':
                  sb.append("%00");
                  break;
               case '\n':
                  sb.append("%0A");
                  break;
               case '\r':
                  sb.append("%0D");
                  break;
               case '%':
                  sb.append("%25");
                  break;
               case ',':
                  sb.append("%2C");
                  break;
               case '}':
                  sb.append("%7D");
                  break;
               default:
                  sb.append(c);
            }
         }

         return sb.toString();
      }
   }

   private static String escapeBuffer(byte[] barr) {
      if (barr != null && barr.length != 0) {
         StringBuilder sb = new StringBuilder(barr.length + 1);
         sb.append('#');

         for(byte b : barr) {
            sb.append(Integer.toHexString(b));
         }

         return sb.toString();
      } else {
         return "";
      }
   }
}
