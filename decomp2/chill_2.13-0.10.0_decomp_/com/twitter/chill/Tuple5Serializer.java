package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114A\u0001B\u0003\u0001\u0019!)1\b\u0001C\u0001y!)a\b\u0001C\u0001\u007f!)q\n\u0001C\u0001!\n\u0001B+\u001e9mKV\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!\u0006\u0004\u000e=!Zc&M\n\u0004\u00019\u0019\u0004cA\b\u0014-9\u0011\u0001#E\u0007\u0002\u000b%\u0011!#B\u0001\ba\u0006\u001c7.Y4f\u0013\t!RCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\n\u0006!\u001d9\"\u0004H\u0014+[Aj\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007)V\u0004H.Z\u001b\u0011\u0005uqB\u0002\u0001\u0003\u0006?\u0001\u0011\r\u0001\t\u0002\u0002\u0003F\u0011\u0011\u0005\n\t\u0003/\tJ!a\t\r\u0003\u000f9{G\u000f[5oOB\u0011q#J\u0005\u0003Ma\u00111!\u00118z!\ti\u0002\u0006B\u0003*\u0001\t\u0007\u0001EA\u0001C!\ti2\u0006B\u0003-\u0001\t\u0007\u0001EA\u0001D!\tib\u0006B\u00030\u0001\t\u0007\u0001EA\u0001E!\ti\u0012\u0007B\u00033\u0001\t\u0007\u0001EA\u0001F!\t!\u0014(D\u00016\u0015\t1t'\u0001\u0002j_*\t\u0001(\u0001\u0003kCZ\f\u0017B\u0001\u001e6\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tQ\bE\u0004\u0011\u0001q9#&\f\u0019\u0002\u000b]\u0014\u0018\u000e^3\u0015\t\u0001\u001b\u0005*\u0014\t\u0003/\u0005K!A\u0011\r\u0003\tUs\u0017\u000e\u001e\u0005\u0006\t\n\u0001\r!R\u0001\u0005WN,'\u000f\u0005\u0002\u0010\r&\u0011q)\u0006\u0002\u0005\u0017JLx\u000eC\u0003J\u0005\u0001\u0007!*A\u0002pkR\u0004\"aD&\n\u00051+\"AB(viB,H\u000fC\u0003O\u0005\u0001\u0007a#A\u0002pE*\fAA]3bIR!a#\u0015*X\u0011\u0015!5\u00011\u0001F\u0011\u0015\u00196\u00011\u0001U\u0003\tIg\u000e\u0005\u0002\u0010+&\u0011a+\u0006\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u00061\u000e\u0001\r!W\u0001\u0004G2\u001c\bc\u0001.b-9\u00111l\u0018\t\u00039bi\u0011!\u0018\u0006\u0003=.\ta\u0001\u0010:p_Rt\u0014B\u00011\u0019\u0003\u0019\u0001&/\u001a3fM&\u0011!m\u0019\u0002\u0006\u00072\f7o\u001d\u0006\u0003Ab\u0001"
)
public class Tuple5Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple5 obj) {
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
   }

   public Tuple5 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple5(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple5Serializer() {
      this.setImmutable(true);
   }
}
