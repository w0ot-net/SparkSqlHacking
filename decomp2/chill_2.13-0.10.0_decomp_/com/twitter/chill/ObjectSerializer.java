package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.control.Exception.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b\u0001\u0002\u0006\f\u0001IAQA\u000b\u0001\u0005\u0002-Bq!\f\u0001C\u0002\u0013\u0005a\u0006\u0003\u0004G\u0001\u0001\u0006Ia\f\u0005\u0006\u0015\u0002!\te\u0013\u0005\u00067\u0002!\t\u0002\u0018\u0005\u0006I\u0002!\t\"\u001a\u0005\u0006Y\u0002!\t%\u001c\u0005\u0006m\u0002!\ta\u001e\u0005\b\u0003\u0007\u0001A\u0011CA\u0003\u0005Ay%M[3diN+'/[1mSj,'O\u0003\u0002\r\u001b\u0005)1\r[5mY*\u0011abD\u0001\bi^LG\u000f^3s\u0015\u0005\u0001\u0012aA2p[\u000e\u0001QCA\n\u001f'\t\u0001A\u0003E\u0002\u00163qq!AF\f\u000e\u0003-I!\u0001G\u0006\u0002\u000fA\f7m[1hK&\u0011!d\u0007\u0002\f\u0017N+'/[1mSj,'O\u0003\u0002\u0019\u0017A\u0011QD\b\u0007\u0001\t\u0015y\u0002A1\u0001!\u0005\u0005!\u0016CA\u0011(!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\t\u0015\n\u0005%\u001a#aA!os\u00061A(\u001b8jiz\"\u0012\u0001\f\t\u0004-\u0001a\u0012!C2bG\",Gm\u00142k+\u0005y\u0003\u0003\u0002\u00196o\u001dk\u0011!\r\u0006\u0003eM\nq!\\;uC\ndWM\u0003\u00025G\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Y\n$aA'baB\u0012\u0001\b\u0012\t\u0004s\u0001\u001beB\u0001\u001e?!\tY4%D\u0001=\u0015\ti\u0014#\u0001\u0004=e>|GOP\u0005\u0003\u007f\r\na\u0001\u0015:fI\u00164\u0017BA!C\u0005\u0015\u0019E.Y:t\u0015\ty4\u0005\u0005\u0002\u001e\t\u0012IQiAA\u0001\u0002\u0003\u0015\t\u0001\t\u0002\u0004?\u0012\n\u0014AC2bG\",Gm\u00142kAA\u0019!\u0005\u0013\u000f\n\u0005%\u001b#AB(qi&|g.A\u0003xe&$X\r\u0006\u0003M\u001fRK\u0006C\u0001\u0012N\u0013\tq5E\u0001\u0003V]&$\b\"\u0002)\u0005\u0001\u0004\t\u0016\u0001B6tKJ\u0004\"!\u0006*\n\u0005M[\"\u0001B&ss>DQ!\u0016\u0003A\u0002Y\u000b1a\\;u!\t)r+\u0003\u0002Y7\t1q*\u001e;qkRDQA\u0017\u0003A\u0002q\t1a\u001c2k\u0003=\u0019'/Z1uKNKgn\u001a7fi>tGCA$^\u0011\u0015qV\u00011\u0001`\u0003\r\u0019Gn\u001d\u0019\u0003A\n\u00042!\u000f!b!\ti\"\rB\u0005d;\u0006\u0005\t\u0011!B\u0001A\t\u0019q\fJ\u001a\u0002\u0015\r\f7\r[3e%\u0016\fG\r\u0006\u0002HM\")aL\u0002a\u0001OB\u0012\u0001N\u001b\t\u0004s\u0001K\u0007CA\u000fk\t%Yg-!A\u0001\u0002\u000b\u0005\u0001EA\u0002`IQ\nAA]3bIR!AD\\8u\u0011\u0015\u0001v\u00011\u0001R\u0011\u0015\u0001x\u00011\u0001r\u0003\tIg\u000e\u0005\u0002\u0016e&\u00111o\u0007\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0006=\u001e\u0001\r!\u001e\t\u0004s\u0001c\u0012aB1dG\u0016\u0004Ho\u001d\u000b\u0003qn\u0004\"AI=\n\u0005i\u001c#a\u0002\"p_2,\u0017M\u001c\u0005\u0006=\"\u0001\r\u0001 \u0019\u0003{~\u00042!\u000f!\u007f!\tir\u0010\u0002\u0006\u0002\u0002m\f\t\u0011!A\u0003\u0002\u0001\u00121a\u0018\u00136\u0003-iw\u000eZ;mK\u001aKW\r\u001c3\u0015\t\u0005\u001d\u0011Q\u0004\t\u0005E!\u000bI\u0001\u0005\u0003\u0002\f\u0005eQBAA\u0007\u0015\u0011\ty!!\u0005\u0002\u000fI,g\r\\3di*!\u00111CA\u000b\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0011\u0001\u00026bm\u0006LA!a\u0007\u0002\u000e\t)a)[3mI\"9\u0011qD\u0005A\u0002\u0005\u0005\u0012!B6mCN\u001c\b\u0007BA\u0012\u0003O\u0001B!\u000f!\u0002&A\u0019Q$a\n\u0005\u0017\u0005%\u0012QDA\u0001\u0002\u0003\u0015\t\u0001\t\u0002\u0004?\u00122\u0004"
)
public class ObjectSerializer extends Serializer {
   private final Map cachedObj;

   public Map cachedObj() {
      return this.cachedObj;
   }

   public void write(final Kryo kser, final Output out, final Object obj) {
   }

   public Option createSingleton(final Class cls) {
      return this.moduleField(cls).map((x$1) -> x$1.get((Object)null));
   }

   public Option cachedRead(final Class cls) {
      synchronized(this.cachedObj()){}

      Option var3;
      try {
         var3 = (Option)this.cachedObj().getOrElseUpdate(cls, () -> this.createSingleton(cls));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   public Object read(final Kryo kser, final Input in, final Class cls) {
      return this.cachedRead(cls).get();
   }

   public boolean accepts(final Class cls) {
      return this.cachedRead(cls).isDefined();
   }

   public Option moduleField(final Class klass) {
      return (new Some(klass)).filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$moduleField$1(x$2))).flatMap((k) -> .MODULE$.allCatch().opt(() -> k.getDeclaredField("MODULE$")));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$moduleField$1(final Class x$2) {
      return scala.collection.StringOps..MODULE$.last$extension(scala.Predef..MODULE$.augmentString(x$2.getName())) == '$';
   }

   public ObjectSerializer() {
      this.cachedObj = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
