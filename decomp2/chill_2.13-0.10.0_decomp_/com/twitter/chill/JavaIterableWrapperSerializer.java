package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.reflect.Method;
import scala.MatchError;
import scala.collection.JavaConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a\u0001B\u0006\r\tMAQa\r\u0001\u0005\u0002QBQA\u000e\u0001\u0005B]BQ\u0001\u0014\u0001\u0005B5;Qa\u001b\u0007\t\n14Qa\u0003\u0007\t\n5DQaM\u0003\u0005\u0002EDqA]\u0003C\u0002\u0013\u00051\u000f\u0003\u0004z\u000b\u0001\u0006I\u0001\u001e\u0005\t\u007f\u0016\u0011\r\u0011\"\u0003\u0002\u0002!A\u0011QC\u0003!\u0002\u0013\t\u0019AA\u000fKCZ\f\u0017\n^3sC\ndWm\u0016:baB,'oU3sS\u0006d\u0017N_3s\u0015\tia\"A\u0003dQ&dGN\u0003\u0002\u0010!\u00059Ao^5ui\u0016\u0014(\"A\t\u0002\u0007\r|Wn\u0001\u0001\u0014\u0005\u0001!\u0002cA\u000b\u001a99\u0011acF\u0007\u0002\u0019%\u0011\u0001\u0004D\u0001\ba\u0006\u001c7.Y4f\u0013\tQ2DA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\r\ra\tir\u0005E\u0002\u001fG\u0015j\u0011a\b\u0006\u0003A\u0005\nA\u0001\\1oO*\t!%\u0001\u0003kCZ\f\u0017B\u0001\u0013 \u0005!IE/\u001a:bE2,\u0007C\u0001\u0014(\u0019\u0001!\u0011\u0002\u000b\u0001\u0002\u0002\u0003\u0005)\u0011A\u0015\u0003\u0007}#\u0013'\u0005\u0002+aA\u00111FL\u0007\u0002Y)\tQ&A\u0003tG\u0006d\u0017-\u0003\u00020Y\t9aj\u001c;iS:<\u0007CA\u00162\u0013\t\u0011DFA\u0002B]f\fa\u0001P5oSRtD#A\u001b\u0011\u0005Y\u0001\u0011!B<sSR,G\u0003\u0002\u001d<\u0001\u0016\u0003\"aK\u001d\n\u0005ib#\u0001B+oSRDQ\u0001\u0010\u0002A\u0002u\nAa\u001b:z_B\u0011QCP\u0005\u0003\u007fm\u0011Aa\u0013:z_\")\u0011I\u0001a\u0001\u0005\u0006\u0019q.\u001e;\u0011\u0005U\u0019\u0015B\u0001#\u001c\u0005\u0019yU\u000f\u001e9vi\")aI\u0001a\u0001\u000f\u0006\u0019qN\u001961\u0005!S\u0005c\u0001\u0010$\u0013B\u0011aE\u0013\u0003\n\u0017\u0016\u000b\t\u0011!A\u0003\u0002%\u00121a\u0018\u00133\u0003\u0011\u0011X-\u00193\u0015\t9\u001bF+\u0017\u0019\u0003\u001fF\u00032AH\u0012Q!\t1\u0013\u000bB\u0005S\u0007\u0005\u0005\t\u0011!B\u0001S\t\u0019q\f\n\u001b\t\u000bq\u001a\u0001\u0019A\u001f\t\u000bU\u001b\u0001\u0019\u0001,\u0002\u0005%t\u0007CA\u000bX\u0013\tA6DA\u0003J]B,H\u000fC\u0003[\u0007\u0001\u00071,A\u0002dYj\u00042\u0001X2g\u001d\ti\u0016\r\u0005\u0002_Y5\tqL\u0003\u0002a%\u00051AH]8pizJ!A\u0019\u0017\u0002\rA\u0013X\rZ3g\u0013\t!WMA\u0003DY\u0006\u001c8O\u0003\u0002cYA\u0012q-\u001b\t\u0004=\rB\u0007C\u0001\u0014j\t%Q\u0017,!A\u0001\u0002\u000b\u0005\u0011FA\u0002`IM\nQDS1wC&#XM]1cY\u0016<&/\u00199qKJ\u001cVM]5bY&TXM\u001d\t\u0003-\u0015\u0019\"!\u00028\u0011\u0005-z\u0017B\u00019-\u0005\u0019\te.\u001f*fMR\tA.\u0001\u0007xe\u0006\u0004\b/\u001a:DY\u0006\u001c8/F\u0001ua\t)x\u000fE\u0002]GZ\u0004\"AJ<\u0005\u0013aD\u0011\u0011!A\u0001\u0006\u0003Q(aA0%k\u0005iqO]1qa\u0016\u00148\t\\1tg\u0002\n\"AK>\u0011\u0007y\u0019C\u0010\u0005\u0002,{&\u0011a\u0010\f\u0002\u0004\u0013:$\u0018aE;oI\u0016\u0014H._5oO6+G\u000f[8e\u001fB$XCAA\u0002!\u0015Y\u0013QAA\u0005\u0013\r\t9\u0001\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005-\u0011\u0011C\u0007\u0003\u0003\u001bQ1!a\u0004 \u0003\u001d\u0011XM\u001a7fGRLA!a\u0005\u0002\u000e\t1Q*\u001a;i_\u0012\fA#\u001e8eKJd\u00170\u001b8h\u001b\u0016$\bn\u001c3PaR\u0004\u0003"
)
public class JavaIterableWrapperSerializer extends Serializer {
   public static Class wrapperClass() {
      return JavaIterableWrapperSerializer$.MODULE$.wrapperClass();
   }

   public void write(final Kryo kryo, final Output out, final Iterable obj) {
      label18: {
         Class var10000 = obj.getClass();
         Class var4 = JavaIterableWrapperSerializer$.MODULE$.wrapperClass();
         if (var10000 == null) {
            if (var4 != null) {
               break label18;
            }
         } else if (!var10000.equals(var4)) {
            break label18;
         }

         if (JavaIterableWrapperSerializer$.MODULE$.com$twitter$chill$JavaIterableWrapperSerializer$$underlyingMethodOpt().isDefined()) {
            kryo.writeClassAndObject(out, ((Method)JavaIterableWrapperSerializer$.MODULE$.com$twitter$chill$JavaIterableWrapperSerializer$$underlyingMethodOpt().get()).invoke(obj));
            return;
         }
      }

      kryo.writeClassAndObject(out, obj);
   }

   public Iterable read(final Kryo kryo, final Input in, final Class clz) {
      Object var5 = kryo.readClassAndObject(in);
      Iterable var4;
      if (var5 instanceof scala.collection.Iterable) {
         scala.collection.Iterable var6 = (scala.collection.Iterable)var5;
         var4 = (Iterable).MODULE$.asJavaIterableConverter(var6).asJava();
      } else {
         if (!(var5 instanceof Iterable)) {
            throw new MatchError(var5);
         }

         Iterable var7 = (Iterable)var5;
         var4 = var7;
      }

      return var4;
   }
}
