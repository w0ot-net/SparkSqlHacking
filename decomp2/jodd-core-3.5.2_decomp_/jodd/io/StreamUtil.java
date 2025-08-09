package jodd.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import jodd.JoddCore;

public class StreamUtil {
   public static void close(InputStream in) {
      if (in != null) {
         try {
            in.close();
         } catch (IOException var2) {
         }
      }

   }

   public static void close(OutputStream out) {
      if (out != null) {
         try {
            out.flush();
         } catch (IOException var3) {
         }

         try {
            out.close();
         } catch (IOException var2) {
         }
      }

   }

   public static void close(Reader in) {
      if (in != null) {
         try {
            in.close();
         } catch (IOException var2) {
         }
      }

   }

   public static void close(Writer out) {
      if (out != null) {
         try {
            out.flush();
         } catch (IOException var3) {
         }

         try {
            out.close();
         } catch (IOException var2) {
         }
      }

   }

   public static int copy(InputStream input, OutputStream output) throws IOException {
      byte[] buffer = new byte[JoddCore.ioBufferSize];
      int count = 0;

      while(true) {
         int read = input.read(buffer, 0, JoddCore.ioBufferSize);
         if (read == -1) {
            return count;
         }

         output.write(buffer, 0, read);
         count += read;
      }
   }

   public static int copy(InputStream input, OutputStream output, int byteCount) throws IOException {
      int bufferSize = byteCount > JoddCore.ioBufferSize ? JoddCore.ioBufferSize : byteCount;
      byte[] buffer = new byte[bufferSize];
      int count = 0;

      while(byteCount > 0) {
         int read;
         if (byteCount < bufferSize) {
            read = input.read(buffer, 0, byteCount);
         } else {
            read = input.read(buffer, 0, bufferSize);
         }

         if (read == -1) {
            break;
         }

         byteCount -= read;
         count += read;
         output.write(buffer, 0, read);
      }

      return count;
   }

   public static void copy(InputStream input, Writer output) throws IOException {
      copy(input, output, JoddCore.encoding);
   }

   public static void copy(InputStream input, Writer output, int byteCount) throws IOException {
      copy(input, output, JoddCore.encoding, byteCount);
   }

   public static void copy(InputStream input, Writer output, String encoding) throws IOException {
      copy((Reader)(new InputStreamReader(input, encoding)), (Writer)output);
   }

   public static void copy(InputStream input, Writer output, String encoding, int byteCount) throws IOException {
      copy((Reader)(new InputStreamReader(input, encoding)), (Writer)output, byteCount);
   }

   public static int copy(Reader input, Writer output) throws IOException {
      char[] buffer = new char[JoddCore.ioBufferSize];

      int count;
      int read;
      for(count = 0; (read = input.read(buffer, 0, JoddCore.ioBufferSize)) >= 0; count += read) {
         output.write(buffer, 0, read);
      }

      output.flush();
      return count;
   }

   public static int copy(Reader input, Writer output, int charCount) throws IOException {
      int bufferSize = charCount > JoddCore.ioBufferSize ? JoddCore.ioBufferSize : charCount;
      char[] buffer = new char[bufferSize];
      int count = 0;

      while(charCount > 0) {
         int read;
         if (charCount < bufferSize) {
            read = input.read(buffer, 0, charCount);
         } else {
            read = input.read(buffer, 0, bufferSize);
         }

         if (read == -1) {
            break;
         }

         charCount -= read;
         count += read;
         output.write(buffer, 0, read);
      }

      return count;
   }

   public static void copy(Reader input, OutputStream output) throws IOException {
      copy(input, output, JoddCore.encoding);
   }

   public static void copy(Reader input, OutputStream output, int charCount) throws IOException {
      copy(input, output, JoddCore.encoding, charCount);
   }

   public static void copy(Reader input, OutputStream output, String encoding) throws IOException {
      Writer out = new OutputStreamWriter(output, encoding);
      copy(input, out);
      out.flush();
   }

   public static void copy(Reader input, OutputStream output, String encoding, int charCount) throws IOException {
      Writer out = new OutputStreamWriter(output, encoding);
      copy(input, out, charCount);
      out.flush();
   }

   public static byte[] readAvailableBytes(InputStream in) throws IOException {
      int l = in.available();
      byte[] byteArray = new byte[l];

      int i;
      int j;
      for(i = 0; i < l && (j = in.read(byteArray, i, l - i)) >= 0; i += j) {
      }

      if (i < l) {
         throw new IOException("Failed to completely read input stream");
      } else {
         return byteArray;
      }
   }

   public static byte[] readBytes(InputStream input) throws IOException {
      FastByteArrayOutputStream output = new FastByteArrayOutputStream();
      copy((InputStream)input, (OutputStream)output);
      return output.toByteArray();
   }

   public static byte[] readBytes(InputStream input, int byteCount) throws IOException {
      FastByteArrayOutputStream output = new FastByteArrayOutputStream();
      copy((InputStream)input, (OutputStream)output, byteCount);
      return output.toByteArray();
   }

   public static byte[] readBytes(Reader input) throws IOException {
      FastByteArrayOutputStream output = new FastByteArrayOutputStream();
      copy((Reader)input, (OutputStream)output);
      return output.toByteArray();
   }

   public static byte[] readBytes(Reader input, int byteCount) throws IOException {
      FastByteArrayOutputStream output = new FastByteArrayOutputStream();
      copy((Reader)input, (OutputStream)output, byteCount);
      return output.toByteArray();
   }

   public static byte[] readBytes(Reader input, String encoding) throws IOException {
      FastByteArrayOutputStream output = new FastByteArrayOutputStream();
      copy((Reader)input, (OutputStream)output, encoding);
      return output.toByteArray();
   }

   public static byte[] readBytes(Reader input, String encoding, int byteCount) throws IOException {
      FastByteArrayOutputStream output = new FastByteArrayOutputStream();
      copy((Reader)input, (OutputStream)output, encoding, byteCount);
      return output.toByteArray();
   }

   public static char[] readChars(InputStream input) throws IOException {
      FastCharArrayWriter output = new FastCharArrayWriter();
      copy((InputStream)input, (Writer)output);
      return output.toCharArray();
   }

   public static char[] readChars(InputStream input, int charCount) throws IOException {
      FastCharArrayWriter output = new FastCharArrayWriter();
      copy((InputStream)input, (Writer)output, charCount);
      return output.toCharArray();
   }

   public static char[] readChars(InputStream input, String encoding) throws IOException {
      FastCharArrayWriter output = new FastCharArrayWriter();
      copy((InputStream)input, (Writer)output, encoding);
      return output.toCharArray();
   }

   public static char[] readChars(InputStream input, String encoding, int charCount) throws IOException {
      FastCharArrayWriter output = new FastCharArrayWriter();
      copy((InputStream)input, (Writer)output, encoding, charCount);
      return output.toCharArray();
   }

   public static char[] readChars(Reader input) throws IOException {
      FastCharArrayWriter output = new FastCharArrayWriter();
      copy((Reader)input, (Writer)output);
      return output.toCharArray();
   }

   public static char[] readChars(Reader input, int charCount) throws IOException {
      FastCharArrayWriter output = new FastCharArrayWriter();
      copy((Reader)input, (Writer)output, charCount);
      return output.toCharArray();
   }

   public static boolean compare(InputStream input1, InputStream input2) throws IOException {
      if (!(input1 instanceof BufferedInputStream)) {
         input1 = new BufferedInputStream(input1);
      }

      if (!(input2 instanceof BufferedInputStream)) {
         input2 = new BufferedInputStream(input2);
      }

      for(int ch = input1.read(); ch != -1; ch = input1.read()) {
         int ch2 = input2.read();
         if (ch != ch2) {
            return false;
         }
      }

      int ch2 = input2.read();
      return ch2 == -1;
   }

   public static boolean compare(Reader input1, Reader input2) throws IOException {
      if (!(input1 instanceof BufferedReader)) {
         input1 = new BufferedReader(input1);
      }

      if (!(input2 instanceof BufferedReader)) {
         input2 = new BufferedReader(input2);
      }

      for(int ch = input1.read(); ch != -1; ch = input1.read()) {
         int ch2 = input2.read();
         if (ch != ch2) {
            return false;
         }
      }

      int ch2 = input2.read();
      return ch2 == -1;
   }
}
