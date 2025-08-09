package org.apache.spark;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Writable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.Utils$;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00194A!\u0003\u0006\u0001#!A\u0011\u0005\u0001BA\u0002\u0013\u0005!\u0005\u0003\u00053\u0001\t\u0005\r\u0011\"\u00014\u0011!I\u0004A!A!B\u0013\u0019\u0003\"\u0002 \u0001\t\u0003y\u0004\"B\"\u0001\t\u0003\u0011\u0003\"\u0002#\u0001\t\u0003*\u0005\"B)\u0001\t\u0013\u0011\u0006\"\u0002-\u0001\t\u0013I&\u0001F*fe&\fG.\u001b>bE2,wK]5uC\ndWM\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h\u0007\u0001)\"AE\u0013\u0014\u0007\u0001\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035}i\u0011a\u0007\u0006\u00039u\t!![8\u000b\u0003y\tAA[1wC&\u0011\u0001e\u0007\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0002iV\t1\u0005\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u00049#!\u0001+\u0012\u0005!Z\u0003C\u0001\u000b*\u0013\tQSCA\u0004O_RD\u0017N\\4\u0011\u00051\u0002T\"A\u0017\u000b\u0005qq#BA\u0018\r\u0003\u0019A\u0017\rZ8pa&\u0011\u0011'\f\u0002\t/JLG/\u00192mK\u0006)Ao\u0018\u0013fcR\u0011Ag\u000e\t\u0003)UJ!AN\u000b\u0003\tUs\u0017\u000e\u001e\u0005\bq\t\t\t\u00111\u0001$\u0003\rAH%M\u0001\u0003i\u0002B#aA\u001e\u0011\u0005Qa\u0014BA\u001f\u0016\u0005%!(/\u00198tS\u0016tG/\u0001\u0004=S:LGO\u0010\u000b\u0003\u0001\n\u00032!\u0011\u0001$\u001b\u0005Q\u0001\"B\u0011\u0005\u0001\u0004\u0019\u0013!\u0002<bYV,\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0019\u0003\"a\u0012(\u000f\u0005!c\u0005CA%\u0016\u001b\u0005Q%BA&\u0011\u0003\u0019a$o\\8u}%\u0011Q*F\u0001\u0007!J,G-\u001a4\n\u0005=\u0003&AB*ue&twM\u0003\u0002N+\u0005YqO]5uK>\u0013'.Z2u)\t!4\u000bC\u0003U\u000f\u0001\u0007Q+A\u0002pkR\u0004\"A\u0007,\n\u0005][\"AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6\f!B]3bI>\u0013'.Z2u)\t!$\fC\u0003\\\u0011\u0001\u0007A,\u0001\u0002j]B\u0011!$X\u0005\u0003=n\u0011\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1nQ\t\u0001\u0001\r\u0005\u0002bI6\t!M\u0003\u0002d\u0015\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0015\u0014'\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class SerializableWritable implements Serializable {
   private transient Writable t;

   public Writable t() {
      return this.t;
   }

   public void t_$eq(final Writable x$1) {
      this.t = x$1;
   }

   public Writable value() {
      return this.t();
   }

   public String toString() {
      return this.t().toString();
   }

   private void writeObject(final ObjectOutputStream out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         out.defaultWriteObject();
         (new ObjectWritable(this.t())).write(out);
      });
   }

   private void readObject(final ObjectInputStream in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         in.defaultReadObject();
         ObjectWritable ow = new ObjectWritable();
         ow.setConf(new Configuration(false));
         ow.readFields(in);
         this.t_$eq((Writable)ow.get());
      });
   }

   public SerializableWritable(final Writable t) {
      this.t = t;
      super();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
