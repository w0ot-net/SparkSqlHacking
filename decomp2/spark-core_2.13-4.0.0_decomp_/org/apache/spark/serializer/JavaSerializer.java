package org.apache.spark.serializer;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.Utils$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00054A!\u0004\b\u0001/!AA\u0005\u0001B\u0001B\u0003%Q\u0005C\u0003*\u0001\u0011\u0005!\u0006C\u0004.\u0001\u0001\u0007I\u0011\u0002\u0018\t\u000fU\u0002\u0001\u0019!C\u0005m!1A\b\u0001Q!\n=Bq!\u0010\u0001A\u0002\u0013%a\bC\u0004C\u0001\u0001\u0007I\u0011B\"\t\r\u0015\u0003\u0001\u0015)\u0003@\u0011\u0015I\u0003\u0001\"\u0005G\u0011\u00159\u0005\u0001\"\u0011I\u0011\u0015a\u0005\u0001\"\u0011N\u0011\u0015\u0019\u0006\u0001\"\u0011U\u00059Q\u0015M^1TKJL\u0017\r\\5{KJT!a\u0004\t\u0002\u0015M,'/[1mSj,'O\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h\u0007\u0001\u00192\u0001\u0001\r\u001d!\tI\"$D\u0001\u000f\u0013\tYbB\u0001\u0006TKJL\u0017\r\\5{KJ\u0004\"!\b\u0012\u000e\u0003yQ!a\b\u0011\u0002\u0005%|'\"A\u0011\u0002\t)\fg/Y\u0005\u0003Gy\u0011a\"\u0012=uKJt\u0017\r\\5{C\ndW-\u0001\u0003d_:4\u0007C\u0001\u0014(\u001b\u0005\u0001\u0012B\u0001\u0015\u0011\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0003W1\u0002\"!\u0007\u0001\t\u000b\u0011\u0012\u0001\u0019A\u0013\u0002\u0019\r|WO\u001c;feJ+7/\u001a;\u0016\u0003=\u0002\"\u0001M\u001a\u000e\u0003ER\u0011AM\u0001\u0006g\u000e\fG.Y\u0005\u0003iE\u00121!\u00138u\u0003A\u0019w.\u001e8uKJ\u0014Vm]3u?\u0012*\u0017\u000f\u0006\u00028uA\u0011\u0001\u0007O\u0005\u0003sE\u0012A!\u00168ji\"91\bBA\u0001\u0002\u0004y\u0013a\u0001=%c\u0005i1m\\;oi\u0016\u0014(+Z:fi\u0002\na\"\u001a=ue\u0006$UMY;h\u0013:4w.F\u0001@!\t\u0001\u0004)\u0003\u0002Bc\t9!i\\8mK\u0006t\u0017AE3yiJ\fG)\u001a2vO&sgm\\0%KF$\"a\u000e#\t\u000fm:\u0011\u0011!a\u0001\u007f\u0005yQ\r\u001f;sC\u0012+'-^4J]\u001a|\u0007\u0005F\u0001,\u0003-qWm^%ogR\fgnY3\u0015\u0003%\u0003\"!\u0007&\n\u0005-s!AE*fe&\fG.\u001b>fe&s7\u000f^1oG\u0016\fQb\u001e:ji\u0016,\u0005\u0010^3s]\u0006dGCA\u001cO\u0011\u0015y5\u00021\u0001Q\u0003\ryW\u000f\u001e\t\u0003;EK!A\u0015\u0010\u0003\u0019=\u0013'.Z2u\u001fV$\b/\u001e;\u0002\u0019I,\u0017\rZ#yi\u0016\u0014h.\u00197\u0015\u0005]*\u0006\"\u0002,\r\u0001\u00049\u0016AA5o!\ti\u0002,\u0003\u0002Z=\tYqJ\u00196fGRLe\u000e];uQ\t\u00011\f\u0005\u0002]?6\tQL\u0003\u0002_!\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0001l&\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class JavaSerializer extends Serializer implements Externalizable {
   private int counterReset;
   private boolean extraDebugInfo;

   private int counterReset() {
      return this.counterReset;
   }

   private void counterReset_$eq(final int x$1) {
      this.counterReset = x$1;
   }

   private boolean extraDebugInfo() {
      return this.extraDebugInfo;
   }

   private void extraDebugInfo_$eq(final boolean x$1) {
      this.extraDebugInfo = x$1;
   }

   public SerializerInstance newInstance() {
      ClassLoader classLoader = (ClassLoader)this.defaultClassLoader().getOrElse(() -> Thread.currentThread().getContextClassLoader());
      return new JavaSerializerInstance(this.counterReset(), this.extraDebugInfo(), classLoader);
   }

   public void writeExternal(final ObjectOutput out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         out.writeInt(this.counterReset());
         out.writeBoolean(this.extraDebugInfo());
      });
   }

   public void readExternal(final ObjectInput in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.counterReset_$eq(in.readInt());
         this.extraDebugInfo_$eq(in.readBoolean());
      });
   }

   public JavaSerializer(final SparkConf conf) {
      this.counterReset = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.SERIALIZER_OBJECT_STREAM_RESET()));
      this.extraDebugInfo = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.SERIALIZER_EXTRA_DEBUG_INFO()));
   }

   public JavaSerializer() {
      this(new SparkConf());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
