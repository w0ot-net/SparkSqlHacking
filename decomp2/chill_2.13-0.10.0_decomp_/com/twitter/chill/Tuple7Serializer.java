package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4A\u0001B\u0003\u0001\u0019!)\u0011\t\u0001C\u0001\u0005\")A\t\u0001C\u0001\u000b\")Q\u000b\u0001C\u0001-\n\u0001B+\u001e9mK^\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!\u0006\u0005\u000e=!Zc&\r\u001b8'\r\u0001a\"\u000f\t\u0004\u001fM1bB\u0001\t\u0012\u001b\u0005)\u0011B\u0001\n\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001F\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003%\u0015\u0001\u0012b\u0006\u000e\u001dO)j\u0003g\r\u001c\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a\u0001V;qY\u0016<\u0004CA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0011\u0002\"a\u0006\u0012\n\u0005\rB\"a\u0002(pi\"Lgn\u001a\t\u0003/\u0015J!A\n\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001eQ\u0011)\u0011\u0006\u0001b\u0001A\t\t!\t\u0005\u0002\u001eW\u0011)A\u0006\u0001b\u0001A\t\t1\t\u0005\u0002\u001e]\u0011)q\u0006\u0001b\u0001A\t\tA\t\u0005\u0002\u001ec\u0011)!\u0007\u0001b\u0001A\t\tQ\t\u0005\u0002\u001ei\u0011)Q\u0007\u0001b\u0001A\t\ta\t\u0005\u0002\u001eo\u0011)\u0001\b\u0001b\u0001A\t\tq\t\u0005\u0002;\u007f5\t1H\u0003\u0002={\u0005\u0011\u0011n\u001c\u0006\u0002}\u0005!!.\u0019<b\u0013\t\u00015H\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0007BI\u0001\u0003\u0001\u000f(U5\u00024GN\u0001\u0006oJLG/\u001a\u000b\u0005\r&s5\u000b\u0005\u0002\u0018\u000f&\u0011\u0001\n\u0007\u0002\u0005+:LG\u000fC\u0003K\u0005\u0001\u00071*\u0001\u0003lg\u0016\u0014\bCA\bM\u0013\tiUC\u0001\u0003Lef|\u0007\"B(\u0003\u0001\u0004\u0001\u0016aA8viB\u0011q\"U\u0005\u0003%V\u0011aaT;uaV$\b\"\u0002+\u0003\u0001\u00041\u0012aA8cU\u0006!!/Z1e)\u00111r\u000bW/\t\u000b)\u001b\u0001\u0019A&\t\u000be\u001b\u0001\u0019\u0001.\u0002\u0005%t\u0007CA\b\\\u0013\taVCA\u0003J]B,H\u000fC\u0003_\u0007\u0001\u0007q,A\u0002dYN\u00042\u0001Y4\u0017\u001d\t\tW\r\u0005\u0002c15\t1M\u0003\u0002e\u0017\u00051AH]8pizJ!A\u001a\r\u0002\rA\u0013X\rZ3g\u0013\tA\u0017NA\u0003DY\u0006\u001c8O\u0003\u0002g1\u0001"
)
public class Tuple7Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple7 obj) {
      kser.writeClassAndObject(out, obj._1());
      out.flush();
      kser.writeClassAndObject(out, obj._2());
      out.flush();
      kser.writeClassAndObject(out, obj._3());
      out.flush();
      kser.writeClassAndObject(out, obj._4());
      out.flush();
      kser.writeClassAndObject(out, obj._5());
      out.flush();
      kser.writeClassAndObject(out, obj._6());
      out.flush();
      kser.writeClassAndObject(out, obj._7());
      out.flush();
   }

   public Tuple7 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple7(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple7Serializer() {
      this.setImmutable(true);
   }
}
