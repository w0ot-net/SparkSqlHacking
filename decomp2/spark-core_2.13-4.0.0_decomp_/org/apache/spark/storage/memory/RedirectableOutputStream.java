package org.apache.spark.storage.memory;

import java.io.OutputStream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153Q!\u0003\u0006\u0001\u0019QAQ!\b\u0001\u0005\u0002}A\u0011B\t\u0001A\u0002\u0003\u0005\u000b\u0015B\u000b\t\u000b\r\u0002A\u0011\u0001\u0013\t\u000b5\u0002A\u0011\t\u0018\t\u000b5\u0002A\u0011\t\u001b\t\u000b5\u0002A\u0011\t\u001f\t\u000b\t\u0003A\u0011I\"\t\u000b\u0011\u0003A\u0011I\"\u00031I+G-\u001b:fGR\f'\r\\3PkR\u0004X\u000f^*ue\u0016\fWN\u0003\u0002\f\u0019\u00051Q.Z7pefT!!\u0004\b\u0002\u000fM$xN]1hK*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0005\u0002\u0001+A\u0011acG\u0007\u0002/)\u0011\u0001$G\u0001\u0003S>T\u0011AG\u0001\u0005U\u00064\u0018-\u0003\u0002\u001d/\taq*\u001e;qkR\u001cFO]3b[\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001!!\t\t\u0003!D\u0001\u000b\u0003\ty7/A\btKR|U\u000f\u001e9viN#(/Z1n)\t)3\u0006\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0003V]&$\b\"\u0002\u0017\u0004\u0001\u0004)\u0012!A:\u0002\u000b]\u0014\u0018\u000e^3\u0015\u0005\u0015z\u0003\"\u0002\u0019\u0005\u0001\u0004\t\u0014!\u00012\u0011\u0005\u0019\u0012\u0014BA\u001a(\u0005\rIe\u000e\u001e\u000b\u0003KUBQ\u0001M\u0003A\u0002Y\u00022AJ\u001c:\u0013\tAtEA\u0003BeJ\f\u0017\u0010\u0005\u0002'u%\u00111h\n\u0002\u0005\u0005f$X\r\u0006\u0003&{y\u0002\u0005\"\u0002\u0019\u0007\u0001\u00041\u0004\"B \u0007\u0001\u0004\t\u0014aA8gM\")\u0011I\u0002a\u0001c\u0005\u0019A.\u001a8\u0002\u000b\u0019dWo\u001d5\u0015\u0003\u0015\nQa\u00197pg\u0016\u0004"
)
public class RedirectableOutputStream extends OutputStream {
   private OutputStream os;

   public void setOutputStream(final OutputStream s) {
      this.os = s;
   }

   public void write(final int b) {
      this.os.write(b);
   }

   public void write(final byte[] b) {
      this.os.write(b);
   }

   public void write(final byte[] b, final int off, final int len) {
      this.os.write(b, off, len);
   }

   public void flush() {
      this.os.flush();
   }

   public void close() {
      this.os.close();
   }
}
