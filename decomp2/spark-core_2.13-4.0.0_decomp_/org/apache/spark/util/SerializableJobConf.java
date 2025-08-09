package org.apache.spark.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.mapred.JobConf;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005I3Qa\u0002\u0005\u0001\u0015AA\u0001\u0002\n\u0001\u0003\u0002\u0004%\t!\n\u0005\t]\u0001\u0011\t\u0019!C\u0001_!AQ\u0007\u0001B\u0001B\u0003&a\u0005C\u0003;\u0001\u0011\u00051\bC\u0003@\u0001\u0011%\u0001\tC\u0003L\u0001\u0011%AJA\nTKJL\u0017\r\\5{C\ndWMS8c\u0007>tgM\u0003\u0002\n\u0015\u0005!Q\u000f^5m\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7c\u0001\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u0004\"\u0001G\u0011\u000f\u0005eybB\u0001\u000e\u001f\u001b\u0005Y\"B\u0001\u000f\u001e\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u000b\n\u0005\u0001\u001a\u0012a\u00029bG.\fw-Z\u0005\u0003E\r\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001I\n\u0002\u000bY\fG.^3\u0016\u0003\u0019\u0002\"a\n\u0017\u000e\u0003!R!!\u000b\u0016\u0002\r5\f\u0007O]3e\u0015\tYC\"\u0001\u0004iC\u0012|w\u000e]\u0005\u0003[!\u0012qAS8c\u0007>tg-A\u0005wC2,Xm\u0018\u0013fcR\u0011\u0001g\r\t\u0003%EJ!AM\n\u0003\tUs\u0017\u000e\u001e\u0005\bi\t\t\t\u00111\u0001'\u0003\rAH%M\u0001\u0007m\u0006dW/\u001a\u0011)\u0005\r9\u0004C\u0001\n9\u0013\tI4CA\u0005ue\u0006t7/[3oi\u00061A(\u001b8jiz\"\"\u0001\u0010 \u0011\u0005u\u0002Q\"\u0001\u0005\t\u000b\u0011\"\u0001\u0019\u0001\u0014\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0003a\u0005CQAQ\u0003A\u0002\r\u000b1a\\;u!\t!\u0015*D\u0001F\u0015\t1u)\u0001\u0002j_*\t\u0001*\u0001\u0003kCZ\f\u0017B\u0001&F\u0005Iy%M[3di>+H\u000f];u'R\u0014X-Y7\u0002\u0015I,\u0017\rZ(cU\u0016\u001cG\u000f\u0006\u00021\u001b\")aJ\u0002a\u0001\u001f\u0006\u0011\u0011N\u001c\t\u0003\tBK!!U#\u0003#=\u0013'.Z2u\u0013:\u0004X\u000f^*ue\u0016\fW\u000e"
)
public class SerializableJobConf implements Serializable {
   private transient JobConf value;

   public JobConf value() {
      return this.value;
   }

   public void value_$eq(final JobConf x$1) {
      this.value = x$1;
   }

   private void writeObject(final ObjectOutputStream out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         out.defaultWriteObject();
         this.value().write(out);
      });
   }

   private void readObject(final ObjectInputStream in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.value_$eq(new JobConf(false));
         this.value().readFields(in);
      });
   }

   public SerializableJobConf(final JobConf value) {
      this.value = value;
      super();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
