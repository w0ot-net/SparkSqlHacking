package org.apache.spark.serializer;

import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4Q!\u0004\b\u0001!YA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\tK\u0001\u0011\t\u0011)A\u0005M!AA\u0006\u0001B\u0001B\u0003%Q\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00047\u0001\t\u0007I\u0011B\u001c\t\rm\u0002\u0001\u0015!\u00039\u0011\u001da\u0004\u00011A\u0005\nuBqA\u0010\u0001A\u0002\u0013%q\b\u0003\u0004F\u0001\u0001\u0006KA\n\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006C\u0002!\t\u0001\u0019\u0002\u0018\u0015\u00064\u0018mU3sS\u0006d\u0017N_1uS>t7\u000b\u001e:fC6T!a\u0004\t\u0002\u0015M,'/[1mSj,'O\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h'\t\u0001q\u0003\u0005\u0002\u001935\ta\"\u0003\u0002\u001b\u001d\t\u00192+\u001a:jC2L'0\u0019;j_:\u001cFO]3b[\u0006\u0019q.\u001e;\u0004\u0001A\u0011adI\u0007\u0002?)\u0011\u0001%I\u0001\u0003S>T\u0011AI\u0001\u0005U\u00064\u0018-\u0003\u0002%?\taq*\u001e;qkR\u001cFO]3b[\u0006a1m\\;oi\u0016\u0014(+Z:fiB\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t\u0019\u0011J\u001c;\u0002\u001d\u0015DHO]1EK\n,x-\u00138g_B\u0011qEL\u0005\u0003_!\u0012qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0005eM\"T\u0007\u0005\u0002\u0019\u0001!)1\u0004\u0002a\u0001;!)Q\u0005\u0002a\u0001M!)A\u0006\u0002a\u0001[\u00051qN\u00196PkR,\u0012\u0001\u000f\t\u0003=eJ!AO\u0010\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\b_\nTw*\u001e;!\u0003\u001d\u0019w.\u001e8uKJ,\u0012AJ\u0001\fG>,h\u000e^3s?\u0012*\u0017\u000f\u0006\u0002A\u0007B\u0011q%Q\u0005\u0003\u0005\"\u0012A!\u00168ji\"9A\tCA\u0001\u0002\u00041\u0013a\u0001=%c\u0005A1m\\;oi\u0016\u0014\b%A\u0006xe&$Xm\u00142kK\u000e$XC\u0001%U)\tIU\f\u0006\u0002\u0018\u0015\"91JCA\u0001\u0002\ba\u0015AC3wS\u0012,gnY3%cA\u0019Q\n\u0015*\u000e\u00039S!a\u0014\u0015\u0002\u000fI,g\r\\3di&\u0011\u0011K\u0014\u0002\t\u00072\f7o\u001d+bOB\u00111\u000b\u0016\u0007\u0001\t\u0015)&B1\u0001W\u0005\u0005!\u0016CA,[!\t9\u0003,\u0003\u0002ZQ\t9aj\u001c;iS:<\u0007CA\u0014\\\u0013\ta\u0006FA\u0002B]fDQA\u0018\u0006A\u0002I\u000b\u0011\u0001^\u0001\u0006M2,8\u000f\u001b\u000b\u0002\u0001\u0006)1\r\\8tK\u0002"
)
public class JavaSerializationStream extends SerializationStream {
   private final int counterReset;
   private final boolean extraDebugInfo;
   private final ObjectOutputStream objOut;
   private int counter;

   private ObjectOutputStream objOut() {
      return this.objOut;
   }

   private int counter() {
      return this.counter;
   }

   private void counter_$eq(final int x$1) {
      this.counter = x$1;
   }

   public SerializationStream writeObject(final Object t, final ClassTag evidence$1) {
      try {
         this.objOut().writeObject(t);
      } catch (Throwable var7) {
         if (var7 instanceof NotSerializableException var6) {
            if (this.extraDebugInfo) {
               throw SerializationDebugger$.MODULE$.improveException(t, var6);
            }
         }

         throw var7;
      }

      this.counter_$eq(this.counter() + 1);
      if (this.counterReset > 0 && this.counter() >= this.counterReset) {
         this.objOut().reset();
         this.counter_$eq(0);
      }

      return this;
   }

   public void flush() {
      this.objOut().flush();
   }

   public void close() {
      this.objOut().close();
   }

   public JavaSerializationStream(final OutputStream out, final int counterReset, final boolean extraDebugInfo) {
      this.counterReset = counterReset;
      this.extraDebugInfo = extraDebugInfo;
      this.objOut = new ObjectOutputStream(out);
      this.counter = 0;
   }
}
