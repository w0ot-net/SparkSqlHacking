package org.apache.spark.streaming;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3Q!\u0002\u0004\u0001\r9A\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\t9\u0001\u0011\t\u0011)A\u0005;!)1\u0005\u0001C\u0001I!)\u0011\u0006\u0001C!U\tYrJ\u00196fGRLe\u000e];u'R\u0014X-Y7XSRDGj\\1eKJT!a\u0002\u0005\u0002\u0013M$(/Z1nS:<'BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0014\u0005\u0001y\u0001C\u0001\t\u0016\u001b\u0005\t\"B\u0001\n\u0014\u0003\tIwNC\u0001\u0015\u0003\u0011Q\u0017M^1\n\u0005Y\t\"!E(cU\u0016\u001cG/\u00138qkR\u001cFO]3b[\u0006aq,\u001b8qkR\u001cFO]3b[\u000e\u0001\u0001C\u0001\t\u001b\u0013\tY\u0012CA\u0006J]B,Ho\u0015;sK\u0006l\u0017A\u00027pC\u0012,'\u000f\u0005\u0002\u001fC5\tqD\u0003\u0002!'\u0005!A.\u00198h\u0013\t\u0011sDA\u0006DY\u0006\u001c8\u000fT8bI\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u0002&O!\u0002\"A\n\u0001\u000e\u0003\u0019AQaF\u0002A\u0002eAQ\u0001H\u0002A\u0002u\tAB]3t_24Xm\u00117bgN$\"aK#1\u00051Z\u0004cA\u00177s9\u0011a\u0006\u000e\t\u0003_Ij\u0011\u0001\r\u0006\u0003ca\ta\u0001\u0010:p_Rt$\"A\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0012\u0014A\u0002)sK\u0012,g-\u0003\u00028q\t)1\t\\1tg*\u0011QG\r\t\u0003umb\u0001\u0001B\u0005=\t\u0005\u0005\t\u0011!B\u0001{\t\u0019q\fJ\u0019\u0012\u0005y\u0012\u0005CA A\u001b\u0005\u0011\u0014BA!3\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aP\"\n\u0005\u0011\u0013$aA!os\")a\t\u0002a\u0001\u000f\u0006!A-Z:d!\t\u0001\u0002*\u0003\u0002J#\t\trJ\u00196fGR\u001cFO]3b[\u000ec\u0017m]:"
)
public class ObjectInputStreamWithLoader extends ObjectInputStream {
   private final ClassLoader loader;

   public Class resolveClass(final ObjectStreamClass desc) {
      try {
         return Class.forName(desc.getName(), false, this.loader);
      } catch (Exception var3) {
         return super.resolveClass(desc);
      }
   }

   public ObjectInputStreamWithLoader(final InputStream _inputStream, final ClassLoader loader) {
      super(_inputStream);
      this.loader = loader;
   }
}
