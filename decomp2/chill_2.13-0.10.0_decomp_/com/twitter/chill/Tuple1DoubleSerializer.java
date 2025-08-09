package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001B\u0003\u0001\u0019!)a\u0005\u0001C\u0001O!)\u0011\u0006\u0001C\u0001U!)!\t\u0001C\u0001\u0007\n1B+\u001e9mKF\"u.\u001e2mKN+'/[1mSj,'O\u0003\u0002\u0007\u000f\u0005)1\r[5mY*\u0011\u0001\"C\u0001\bi^LG\u000f^3s\u0015\u0005Q\u0011aA2p[\u000e\u00011c\u0001\u0001\u000e=A\u0019aBE\u000b\u000f\u0005=\u0001R\"A\u0003\n\u0005E)\u0011a\u00029bG.\fw-Z\u0005\u0003'Q\u00111bS*fe&\fG.\u001b>fe*\u0011\u0011#\u0002\t\u0004-eYR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\rQ+\b\u000f\\32!\t1B$\u0003\u0002\u001e/\t1Ai\\;cY\u0016\u0004\"a\b\u0013\u000e\u0003\u0001R!!\t\u0012\u0002\u0005%|'\"A\u0012\u0002\t)\fg/Y\u0005\u0003K\u0001\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u0001\u0015\u0011\u0005=\u0001\u0011\u0001\u0002:fC\u0012$B!F\u00161k!)AF\u0001a\u0001[\u0005!1n]3s!\tqa&\u0003\u00020)\t!1J]=p\u0011\u0015\t$\u00011\u00013\u0003\tIg\u000e\u0005\u0002\u000fg%\u0011A\u0007\u0006\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0006m\t\u0001\raN\u0001\u0004G2\u001c\bc\u0001\u001d@+9\u0011\u0011(\u0010\t\u0003u]i\u0011a\u000f\u0006\u0003y-\ta\u0001\u0010:p_Rt\u0014B\u0001 \u0018\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0006\u00072\f7o\u001d\u0006\u0003}]\tQa\u001e:ji\u0016$B\u0001R$I\u001bB\u0011a#R\u0005\u0003\r^\u0011A!\u00168ji\")Af\u0001a\u0001[!)\u0011j\u0001a\u0001\u0015\u0006\u0019q.\u001e;\u0011\u00059Y\u0015B\u0001'\u0015\u0005\u0019yU\u000f\u001e9vi\")aj\u0001a\u0001+\u0005\u0019A/\u001e9"
)
public class Tuple1DoubleSerializer extends Serializer implements Serializable {
   public Tuple1 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple1.mcD.sp(in.readDouble());
   }

   public void write(final Kryo kser, final Output out, final Tuple1 tup) {
      out.writeDouble(tup._1$mcD$sp());
   }

   public Tuple1DoubleSerializer() {
      this.setImmutable(true);
   }
}
