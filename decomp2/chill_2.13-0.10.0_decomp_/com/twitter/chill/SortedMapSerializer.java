package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.immutable.SortedMap;
import scala.collection.immutable.SortedMap.;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005q3A!\u0002\u0004\u0001\u001b!)\u0001\u0007\u0001C\u0001c\u0015!1\u0007\u0001\u0001\u0018\u0011\u0015!\u0004\u0001\"\u00016\u0011\u00159\u0005\u0001\"\u0001I\u0005M\u0019vN\u001d;fI6\u000b\u0007oU3sS\u0006d\u0017N_3s\u0015\t9\u0001\"A\u0003dQ&dGN\u0003\u0002\n\u0015\u00059Ao^5ui\u0016\u0014(\"A\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016\u00079\u0019cf\u0005\u0002\u0001\u001fA\u0019\u0001\u0003F\f\u000f\u0005E\u0011R\"\u0001\u0004\n\u0005M1\u0011a\u00029bG.\fw-Z\u0005\u0003+Y\u00111bS*fe&\fG.\u001b>fe*\u00111C\u0002\t\u00051}\tS&D\u0001\u001a\u0015\tQ2$A\u0005j[6,H/\u00192mK*\u0011A$H\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001J\"!C*peR,G-T1q!\t\u00113\u0005\u0004\u0001\u0005\u000b\u0011\u0002!\u0019A\u0013\u0003\u0003\u0005\u000b\"A\n\u0016\u0011\u0005\u001dBS\"A\u000f\n\u0005%j\"a\u0002(pi\"Lgn\u001a\t\u0003O-J!\u0001L\u000f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002#]\u0011)q\u0006\u0001b\u0001K\t\t!)\u0001\u0004=S:LGO\u0010\u000b\u0002eA!\u0011\u0003A\u0011.\u0005\u0005i\u0015!B<sSR,G\u0003\u0002\u001c:}\r\u0003\"aJ\u001c\n\u0005aj\"\u0001B+oSRDQAO\u0002A\u0002m\nAa[:feB\u0011\u0001\u0003P\u0005\u0003{Y\u0011Aa\u0013:z_\")qh\u0001a\u0001\u0001\u0006\u0019q.\u001e;\u0011\u0005A\t\u0015B\u0001\"\u0017\u0005\u0019yU\u000f\u001e9vi\")Ai\u0001a\u0001\u000b\u0006\u0019Q.\u00199\u0011\u0005\u0019\u0013Q\"\u0001\u0001\u0002\tI,\u0017\r\u001a\u000b\u0005\u000b&Su\nC\u0003;\t\u0001\u00071\bC\u0003L\t\u0001\u0007A*\u0001\u0002j]B\u0011\u0001#T\u0005\u0003\u001dZ\u0011Q!\u00138qkRDQ\u0001\u0015\u0003A\u0002E\u000b1a\u00197t!\r\u0011\u0016,\u0012\b\u0003'^\u0003\"\u0001V\u000f\u000e\u0003US!A\u0016\u0007\u0002\rq\u0012xn\u001c;?\u0013\tAV$\u0001\u0004Qe\u0016$WMZ\u0005\u00035n\u0013Qa\u00117bgNT!\u0001W\u000f"
)
public class SortedMapSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final SortedMap map) {
      out.writeInt(map.size(), true);
      kser.writeClassAndObject(out, map.ordering());
      map.foreach((t) -> {
         $anonfun$write$1(kser, out, t);
         return BoxedUnit.UNIT;
      });
   }

   public SortedMap read(final Kryo kser, final Input in, final Class cls) {
      int size = in.readInt(true);
      Ordering ordering = (Ordering)kser.readClassAndObject(in);
      int idx = 0;
      Builder builder = .MODULE$.newBuilder(ordering);
      builder.sizeHint(size);

      while(idx < size) {
         Tuple2 item = (Tuple2)kser.readClassAndObject(in);
         builder.$plus$eq(item);
         ++idx;
      }

      return (SortedMap)builder.result();
   }

   // $FF: synthetic method
   public static final void $anonfun$write$1(final Kryo kser$1, final Output out$1, final Tuple2 t) {
      kser$1.writeClassAndObject(out$1, t);
      out$1.flush();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
