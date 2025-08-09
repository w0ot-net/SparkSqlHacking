package javolution.io;

import java.io.IOException;
import java.io.Writer;
import javolution.lang.Reusable;
import javolution.text.Text;

public final class AppendableWriter extends Writer implements Reusable {
   private Appendable _output;
   private char[] _tmpBuffer;
   private final CharSequence _tmpBufferAsCharSequence = new CharSequence() {
      public int length() {
         return AppendableWriter.this._tmpBuffer.length;
      }

      public char charAt(int index) {
         return AppendableWriter.this._tmpBuffer[index];
      }

      public CharSequence subSequence(int start, int end) {
         throw new UnsupportedOperationException();
      }
   };

   public AppendableWriter setOutput(Appendable output) {
      if (this._output != null) {
         throw new IllegalStateException("Writer not closed or reset");
      } else {
         this._output = output;
         return this;
      }
   }

   public void write(char c) throws IOException {
      if (this._output == null) {
         throw new IOException("Writer closed");
      } else {
         this._output.append(c);
      }
   }

   public void write(int c) throws IOException {
      if (this._output == null) {
         throw new IOException("Writer closed");
      } else {
         this._output.append((char)c);
      }
   }

   public void write(char[] cbuf, int off, int len) throws IOException {
      if (this._output == null) {
         throw new IOException("Writer closed");
      } else {
         this._tmpBuffer = cbuf;
         this._output.append(this._tmpBufferAsCharSequence, off, off + len);
         this._tmpBuffer = null;
      }
   }

   public void write(String str, int off, int len) throws IOException {
      if (this._output == null) {
         throw new IOException("Writer closed");
      } else {
         if (str instanceof CharSequence) {
            this._output.append((CharSequence)str);
         } else {
            this._output.append(Text.valueOf((Object)str));
         }

      }
   }

   public void write(CharSequence csq) throws IOException {
      if (this._output == null) {
         throw new IOException("Writer closed");
      } else {
         this._output.append(csq);
      }
   }

   public void flush() {
   }

   public void close() {
      if (this._output != null) {
         this.reset();
      }

   }

   public void reset() {
      this._output = null;
      this._tmpBuffer = null;
   }
}
