package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054A\u0001B\u0003\u0001\u0019!)\u0001\b\u0001C\u0001s!)1\b\u0001C\u0001y!)A\n\u0001C\u0001\u001b\n\u0001B+\u001e9mKR\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!F\u0003\u000e=!ZcfE\u0002\u0001\u001dA\u00022aD\n\u0017\u001d\t\u0001\u0012#D\u0001\u0006\u0013\t\u0011R!A\u0004qC\u000e\\\u0017mZ3\n\u0005Q)\"aC&TKJL\u0017\r\\5{KJT!AE\u0003\u0011\r]QBd\n\u0016.\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"A\u0002+va2,G\u0007\u0005\u0002\u001e=1\u0001A!B\u0010\u0001\u0005\u0004\u0001#!A!\u0012\u0005\u0005\"\u0003CA\f#\u0013\t\u0019\u0003DA\u0004O_RD\u0017N\\4\u0011\u0005])\u0013B\u0001\u0014\u0019\u0005\r\te.\u001f\t\u0003;!\"Q!\u000b\u0001C\u0002\u0001\u0012\u0011A\u0011\t\u0003;-\"Q\u0001\f\u0001C\u0002\u0001\u0012\u0011a\u0011\t\u0003;9\"Qa\f\u0001C\u0002\u0001\u0012\u0011\u0001\u0012\t\u0003cYj\u0011A\r\u0006\u0003gQ\n!![8\u000b\u0003U\nAA[1wC&\u0011qG\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\u0002b\u0001\u0005\u0001\u001dO)j\u0013!B<sSR,G\u0003B\u001fA\u000b*\u0003\"a\u0006 \n\u0005}B\"\u0001B+oSRDQ!\u0011\u0002A\u0002\t\u000bAa[:feB\u0011qbQ\u0005\u0003\tV\u0011Aa\u0013:z_\")aI\u0001a\u0001\u000f\u0006\u0019q.\u001e;\u0011\u0005=A\u0015BA%\u0016\u0005\u0019yU\u000f\u001e9vi\")1J\u0001a\u0001-\u0005\u0019qN\u00196\u0002\tI,\u0017\r\u001a\u000b\u0005-9{E\u000bC\u0003B\u0007\u0001\u0007!\tC\u0003Q\u0007\u0001\u0007\u0011+\u0001\u0002j]B\u0011qBU\u0005\u0003'V\u0011Q!\u00138qkRDQ!V\u0002A\u0002Y\u000b1a\u00197t!\r9fL\u0006\b\u00031r\u0003\"!\u0017\r\u000e\u0003iS!aW\u0006\u0002\rq\u0012xn\u001c;?\u0013\ti\u0006$\u0001\u0004Qe\u0016$WMZ\u0005\u0003?\u0002\u0014Qa\u00117bgNT!!\u0018\r"
)
public class Tuple4Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple4 obj) {
      kser.writeClassAndObject(out, obj._1());
      out.flush();
      kser.writeClassAndObject(out, obj._2());
      out.flush();
      kser.writeClassAndObject(out, obj._3());
      out.flush();
      kser.writeClassAndObject(out, obj._4());
      out.flush();
   }

   public Tuple4 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple4(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple4Serializer() {
      this.setImmutable(true);
   }
}
