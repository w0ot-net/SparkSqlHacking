package org.apache.spark.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@DeveloperApi
@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005u3Aa\u0002\u0005\u0001#!AA\u0005\u0001BA\u0002\u0013\u0005Q\u0005\u0003\u0005/\u0001\t\u0005\r\u0011\"\u00010\u0011!)\u0004A!A!B\u00131\u0003\"\u0002\u001e\u0001\t\u0003Y\u0004\"B \u0001\t\u0013\u0001\u0005\"B&\u0001\t\u0013a%!G*fe&\fG.\u001b>bE2,7i\u001c8gS\u001e,(/\u0019;j_:T!!\u0003\u0006\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sO\u000e\u00011c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!G\u0011\u000f\u0005iybBA\u000e\u001f\u001b\u0005a\"BA\u000f\u0011\u0003\u0019a$o\\8u}%\tQ#\u0003\u0002!)\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0001C#A\u0003wC2,X-F\u0001'!\t9C&D\u0001)\u0015\tI#&\u0001\u0003d_:4'BA\u0016\r\u0003\u0019A\u0017\rZ8pa&\u0011Q\u0006\u000b\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\u0013Y\fG.^3`I\u0015\fHC\u0001\u00194!\t\u0019\u0012'\u0003\u00023)\t!QK\\5u\u0011\u001d!$!!AA\u0002\u0019\n1\u0001\u001f\u00132\u0003\u00191\u0018\r\\;fA!\u00121a\u000e\t\u0003'aJ!!\u000f\u000b\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018A\u0002\u001fj]&$h\b\u0006\u0002=}A\u0011Q\bA\u0007\u0002\u0011!)A\u0005\u0002a\u0001M\u0005YqO]5uK>\u0013'.Z2u)\t\u0001\u0014\tC\u0003C\u000b\u0001\u00071)A\u0002pkR\u0004\"\u0001R%\u000e\u0003\u0015S!AR$\u0002\u0005%|'\"\u0001%\u0002\t)\fg/Y\u0005\u0003\u0015\u0016\u0013!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\u0006Q!/Z1e\u001f\nTWm\u0019;\u0015\u0005Aj\u0005\"\u0002(\u0007\u0001\u0004y\u0015AA5o!\t!\u0005+\u0003\u0002R\u000b\n\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7)\u0005\u0001\u0019\u0006C\u0001+X\u001b\u0005)&B\u0001,\u000b\u0003)\tgN\\8uCRLwN\\\u0005\u00031V\u0013A\u0002R3wK2|\u0007/\u001a:Ba&D#\u0001\u0001.\u0011\u0005Q[\u0016B\u0001/V\u0005!)fn\u001d;bE2,\u0007"
)
public class SerializableConfiguration implements Serializable {
   private transient Configuration value;

   public Configuration value() {
      return this.value;
   }

   public void value_$eq(final Configuration x$1) {
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
         this.value_$eq(new Configuration(false));
         this.value().readFields(in);
      });
   }

   public SerializableConfiguration(final Configuration value) {
      this.value = value;
      super();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
