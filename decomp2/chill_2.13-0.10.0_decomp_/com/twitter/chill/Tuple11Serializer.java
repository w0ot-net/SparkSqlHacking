package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple11;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4A\u0001B\u0003\u0001\u0019!)Q\n\u0001C\u0001\u001d\")\u0001\u000b\u0001C\u0001#\")\u0011\r\u0001C\u0001E\n\tB+\u001e9mKF\n4+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011!B2iS2d'B\u0001\u0005\n\u0003\u001d!x/\u001b;uKJT\u0011AC\u0001\u0004G>l7\u0001A\u000b\r\u001byA3FL\u00195oij\u0004iQ\n\u0004\u00019)\u0005cA\b\u0014-9\u0011\u0001#E\u0007\u0002\u000b%\u0011!#B\u0001\ba\u0006\u001c7.Y4f\u0013\t!RCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\n\u0006!59\"\u0004H\u0014+[A\u001ad'\u000f\u001f@\u00056\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002DA\u0004UkBdW-M\u0019\u0011\u0005uqB\u0002\u0001\u0003\u0006?\u0001\u0011\r\u0001\t\u0002\u0002\u0003F\u0011\u0011\u0005\n\t\u0003/\tJ!a\t\r\u0003\u000f9{G\u000f[5oOB\u0011q#J\u0005\u0003Ma\u00111!\u00118z!\ti\u0002\u0006B\u0003*\u0001\t\u0007\u0001EA\u0001C!\ti2\u0006B\u0003-\u0001\t\u0007\u0001EA\u0001D!\tib\u0006B\u00030\u0001\t\u0007\u0001EA\u0001E!\ti\u0012\u0007B\u00033\u0001\t\u0007\u0001EA\u0001F!\tiB\u0007B\u00036\u0001\t\u0007\u0001EA\u0001G!\tir\u0007B\u00039\u0001\t\u0007\u0001EA\u0001H!\ti\"\bB\u0003<\u0001\t\u0007\u0001EA\u0001I!\tiR\bB\u0003?\u0001\t\u0007\u0001EA\u0001J!\ti\u0002\tB\u0003B\u0001\t\u0007\u0001EA\u0001K!\ti2\tB\u0003E\u0001\t\u0007\u0001EA\u0001L!\t15*D\u0001H\u0015\tA\u0015*\u0001\u0002j_*\t!*\u0001\u0003kCZ\f\u0017B\u0001'H\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tq\nE\u0007\u0011\u0001q9#&\f\u00194mebtHQ\u0001\u0006oJLG/\u001a\u000b\u0005%VSv\f\u0005\u0002\u0018'&\u0011A\u000b\u0007\u0002\u0005+:LG\u000fC\u0003W\u0005\u0001\u0007q+\u0001\u0003lg\u0016\u0014\bCA\bY\u0013\tIVC\u0001\u0003Lef|\u0007\"B.\u0003\u0001\u0004a\u0016aA8viB\u0011q\"X\u0005\u0003=V\u0011aaT;uaV$\b\"\u00021\u0003\u0001\u00041\u0012aA8cU\u0006!!/Z1e)\u001112\rZ5\t\u000bY\u001b\u0001\u0019A,\t\u000b\u0015\u001c\u0001\u0019\u00014\u0002\u0005%t\u0007CA\bh\u0013\tAWCA\u0003J]B,H\u000fC\u0003k\u0007\u0001\u00071.A\u0002dYN\u00042\u0001\\:\u0017\u001d\ti\u0017\u000f\u0005\u0002o15\tqN\u0003\u0002q\u0017\u00051AH]8pizJ!A\u001d\r\u0002\rA\u0013X\rZ3g\u0013\t!XOA\u0003DY\u0006\u001c8O\u0003\u0002s1\u0001"
)
public class Tuple11Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple11 obj) {
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
      kser.writeClassAndObject(out, obj._8());
      out.flush();
      kser.writeClassAndObject(out, obj._9());
      out.flush();
      kser.writeClassAndObject(out, obj._10());
      out.flush();
      kser.writeClassAndObject(out, obj._11());
      out.flush();
   }

   public Tuple11 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple11(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple11Serializer() {
      this.setImmutable(true);
   }
}
