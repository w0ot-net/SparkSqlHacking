package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.math.BigDecimal;
import scala.math.BigDecimal.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3A\u0001B\u0003\u0005\u0019!)Q\u0004\u0001C\u0001=!)\u0001\u0005\u0001C!C!)\u0011\b\u0001C!u\t!\")[4EK\u000eLW.\u00197TKJL\u0017\r\\5{KJT!AB\u0004\u0002\u000b\rD\u0017\u000e\u001c7\u000b\u0005!I\u0011a\u0002;xSR$XM\u001d\u0006\u0002\u0015\u0005\u00191m\\7\u0004\u0001M\u0011\u0001!\u0004\t\u0004\u001dI)bBA\b\u0011\u001b\u0005)\u0011BA\t\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0005\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003#\u0015\u0001\"AF\u000e\u000e\u0003]Q!\u0001G\r\u0002\t5\fG\u000f\u001b\u0006\u00025\u0005)1oY1mC&\u0011Ad\u0006\u0002\u000b\u0005&<G)Z2j[\u0006d\u0017A\u0002\u001fj]&$h\bF\u0001 !\ty\u0001!\u0001\u0003sK\u0006$G\u0003B\u000b#O1BQa\t\u0002A\u0002\u0011\nAa\u001b:z_B\u0011a\"J\u0005\u0003MQ\u0011Aa\u0013:z_\")\u0001F\u0001a\u0001S\u0005)\u0011N\u001c9viB\u0011aBK\u0005\u0003WQ\u0011Q!\u00138qkRDQ!\f\u0002A\u00029\n1a\u00197t!\ryc'\u0006\b\u0003aQ\u0002\"!M\r\u000e\u0003IR!aM\u0006\u0002\rq\u0012xn\u001c;?\u0013\t)\u0014$\u0001\u0004Qe\u0016$WMZ\u0005\u0003oa\u0012Qa\u00117bgNT!!N\r\u0002\u000b]\u0014\u0018\u000e^3\u0015\tmz\u0004)\u0012\t\u0003yuj\u0011!G\u0005\u0003}e\u0011A!\u00168ji\")1e\u0001a\u0001I!)\u0011i\u0001a\u0001\u0005\u00061q.\u001e;qkR\u0004\"AD\"\n\u0005\u0011#\"AB(viB,H\u000fC\u0003G\u0007\u0001\u0007Q#A\u0002pE*\u0004"
)
public class BigDecimalSerializer extends Serializer {
   public BigDecimal read(final Kryo kryo, final Input input, final Class cls) {
      java.math.BigDecimal jBigDec = (java.math.BigDecimal)kryo.readClassAndObject(input);
      return .MODULE$.apply(jBigDec);
   }

   public void write(final Kryo kryo, final Output output, final BigDecimal obj) {
      kryo.writeClassAndObject(output, obj.bigDecimal());
   }
}
