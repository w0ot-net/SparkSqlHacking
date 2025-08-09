package org.apache.spark.streaming.api.python;

import java.util.ArrayList;
import java.util.Queue;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaDStream$;
import org.apache.spark.streaming.dstream.DStream;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dtA\u0002\n\u0014\u0011\u00039rD\u0002\u0004\"'!\u0005qC\t\u0005\u0006c\u0005!\ta\r\u0005\u0006i\u0005!\t!\u000e\u0005\u0006}\u0005!\ta\u0010\u0005\u0006%\u0006!\ta\u0015\u0005\u0006K\u0006!\tA\u001a\u0005\bk\u0006\t\t\u0011\"\u0003w\r\u0019\t3#!\u0001\u0014{\"Q\u0011\u0011\u0002\u0005\u0003\u0002\u0003\u0006I!a\u0003\t\u00119C!\u0011!Q\u0001\n=Ca!\r\u0005\u0005\u0002\u0005\u0015\u0002\"CA\u001b\u0011\t\u0007I\u0011AA\u001c\u0011!\ty\u0004\u0003Q\u0001\n\u0005e\u0002bBA!\u0011\u0011\u0005\u00131\t\u0005\b\u0003+BA\u0011IA,\u0011%\t\t\u0007\u0003b\u0001\n\u0003\t\u0019\u0007C\u0004\u0002f!\u0001\u000b\u0011\u0002\"\u0002\u001bAKH\u000f[8o\tN#(/Z1n\u0015\t!R#\u0001\u0004qsRDwN\u001c\u0006\u0003-]\t1!\u00199j\u0015\tA\u0012$A\u0005tiJ,\u0017-\\5oO*\u0011!dG\u0001\u0006gB\f'o\u001b\u0006\u00039u\ta!\u00199bG\",'\"\u0001\u0010\u0002\u0007=\u0014x\r\u0005\u0002!\u00035\t1CA\u0007QsRDwN\u001c#TiJ,\u0017-\\\n\u0004\u0003\rJ\u0003C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#AB!osJ+g\r\u0005\u0002+_5\t1F\u0003\u0002-[\u0005\u0011\u0011n\u001c\u0006\u0002]\u0005!!.\u0019<b\u0013\t\u00014F\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGOP\u0002\u0001)\u0005y\u0012A\u0005:fO&\u001cH/\u001a:TKJL\u0017\r\\5{KJ$\"AN\u001d\u0011\u0005\u0011:\u0014B\u0001\u001d&\u0005\u0011)f.\u001b;\t\u000bi\u001a\u0001\u0019A\u001e\u0002\u0007M,'\u000f\u0005\u0002!y%\u0011Qh\u0005\u0002\"!f$\bn\u001c8Ue\u0006t7OZ8s[\u001a+hn\u0019;j_:\u001cVM]5bY&TXM]\u0001\u000fG\u0006dGNR8sK\u0006\u001c\u0007N\u0015#E)\r1\u0004)\u0014\u0005\u0006\u0003\u0012\u0001\rAQ\u0001\tU\u0012\u001cHO]3b[B\u00191)R$\u000e\u0003\u0011S!AL\u000b\n\u0005\u0019#%a\u0003&bm\u0006$5\u000b\u001e:fC6\u00042\u0001\n%K\u0013\tIUEA\u0003BeJ\f\u0017\u0010\u0005\u0002%\u0017&\u0011A*\n\u0002\u0005\u0005f$X\rC\u0003O\t\u0001\u0007q*A\u0003qMVt7\r\u0005\u0002!!&\u0011\u0011k\u0005\u0002\u0018!f$\bn\u001c8Ue\u0006t7OZ8s[\u001a+hn\u0019;j_:\f!\u0002^8S\t\u0012\u000bV/Z;f)\t!\u0006\rE\u0002V1jk\u0011A\u0016\u0006\u0003/6\nA!\u001e;jY&\u0011\u0011L\u0016\u0002\u0006#V,W/\u001a\t\u00047z;U\"\u0001/\u000b\u00059j&B\u0001\f\u001a\u0013\tyFLA\u0004KCZ\f'\u000b\u0012#\t\u000b\u0005,\u0001\u0019\u00012\u0002\tI$Gm\u001d\t\u0004+\u000eT\u0016B\u00013W\u0005%\t%O]1z\u0019&\u001cH/A\u0015ti>\u00048\u000b\u001e:fC6LgnZ\"p]R,\u0007\u0010^%g!f$\bn\u001c8Qe>\u001cWm]:Jg\u0012+\u0017\r\u001a\u000b\u0003m\u001dDQ\u0001\u001b\u0004A\u0002%\f\u0011!\u001a\t\u0003UJt!a\u001b9\u000f\u00051|W\"A7\u000b\u00059\u0014\u0014A\u0002\u001fs_>$h(C\u0001'\u0013\t\tX%A\u0004qC\u000e\\\u0017mZ3\n\u0005M$(!\u0003+ie><\u0018M\u00197f\u0015\t\tX%\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001x!\tA80D\u0001z\u0015\tQX&\u0001\u0003mC:<\u0017B\u0001?z\u0005\u0019y%M[3diN\u0011\u0001B \t\u0005\u007f\u0006\u0015q)\u0004\u0002\u0002\u0002)\u0019\u00111A\f\u0002\u000f\u0011\u001cHO]3b[&!\u0011qAA\u0001\u0005\u001d!5\u000b\u001e:fC6\fa\u0001]1sK:$\b\u0007BA\u0007\u0003'\u0001Ra`A\u0003\u0003\u001f\u0001B!!\u0005\u0002\u00141\u0001AaCA\u000b\u0013\u0005\u0005\t\u0011!B\u0001\u0003/\u00111a\u0018\u00139#\u0011\tI\"a\b\u0011\u0007\u0011\nY\"C\u0002\u0002\u001e\u0015\u0012qAT8uQ&tw\rE\u0002%\u0003CI1!a\t&\u0005\r\te.\u001f\u000b\u0007\u0003O\tI#a\r\u0011\u0005\u0001B\u0001bBA\u0005\u0017\u0001\u0007\u00111\u0006\u0019\u0005\u0003[\t\t\u0004E\u0003\u0000\u0003\u000b\ty\u0003\u0005\u0003\u0002\u0012\u0005EB\u0001DA\u000b\u0003S\t\t\u0011!A\u0003\u0002\u0005]\u0001\"\u0002(\f\u0001\u0004y\u0015\u0001\u00024v]\u000e,\"!!\u000f\u0011\u0007\u0001\nY$C\u0002\u0002>M\u0011\u0011\u0003\u0016:b]N4wN]7Gk:\u001cG/[8o\u0003\u00151WO\\2!\u00031!W\r]3oI\u0016t7-[3t+\t\t)\u0005E\u0003k\u0003\u000f\nY%C\u0002\u0002JQ\u0014A\u0001T5tiB\"\u0011QJA)!\u0015y\u0018QAA(!\u0011\t\t\"!\u0015\u0005\u0017\u0005Mc\"!A\u0001\u0002\u000b\u0005\u0011q\u0003\u0002\u0004?\u0012J\u0014!D:mS\u0012,G)\u001e:bi&|g.\u0006\u0002\u0002ZA!\u00111LA/\u001b\u00059\u0012bAA0/\tAA)\u001e:bi&|g.A\u0007bg*\u000bg/\u0019#TiJ,\u0017-\\\u000b\u0002\u0005\u0006q\u0011m\u001d&bm\u0006$5\u000b\u001e:fC6\u0004\u0003"
)
public abstract class PythonDStream extends DStream {
   private final DStream parent;
   private final TransformFunction func;
   private final JavaDStream asJavaDStream;

   public static void stopStreamingContextIfPythonProcessIsDead(final Throwable e) {
      PythonDStream$.MODULE$.stopStreamingContextIfPythonProcessIsDead(e);
   }

   public static Queue toRDDQueue(final ArrayList rdds) {
      return PythonDStream$.MODULE$.toRDDQueue(rdds);
   }

   public static void callForeachRDD(final JavaDStream jdstream, final PythonTransformFunction pfunc) {
      PythonDStream$.MODULE$.callForeachRDD(jdstream, pfunc);
   }

   public static void registerSerializer(final PythonTransformFunctionSerializer ser) {
      PythonDStream$.MODULE$.registerSerializer(ser);
   }

   public TransformFunction func() {
      return this.func;
   }

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public JavaDStream asJavaDStream() {
      return this.asJavaDStream;
   }

   public PythonDStream(final DStream parent, final PythonTransformFunction pfunc) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
      this.parent = parent;
      this.func = new TransformFunction(pfunc);
      this.asJavaDStream = JavaDStream$.MODULE$.fromDStream(this, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }
}
