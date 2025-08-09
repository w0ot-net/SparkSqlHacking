package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001B\u0003\u0001\u0019!)a\u0005\u0001C\u0001O!)\u0011\u0006\u0001C\u0001U!)!\t\u0001C\u0001\u0007\naB+\u001e9mKJ\"u.\u001e2mK\u0012{WO\u00197f'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0004\b\u0003\u0015\u0019\u0007.\u001b7m\u0015\tA\u0011\"A\u0004uo&$H/\u001a:\u000b\u0003)\t1aY8n\u0007\u0001\u00192\u0001A\u0007\u001f!\rq!#\u0006\b\u0003\u001fAi\u0011!B\u0005\u0003#\u0015\tq\u0001]1dW\u0006<W-\u0003\u0002\u0014)\tY1jU3sS\u0006d\u0017N_3s\u0015\t\tR\u0001\u0005\u0003\u00173mYR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\rQ+\b\u000f\\33!\t1B$\u0003\u0002\u001e/\t1Ai\\;cY\u0016\u0004\"a\b\u0013\u000e\u0003\u0001R!!\t\u0012\u0002\u0005%|'\"A\u0012\u0002\t)\fg/Y\u0005\u0003K\u0001\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u0001\u0015\u0011\u0005=\u0001\u0011\u0001\u0002:fC\u0012$B!F\u00161k!)AF\u0001a\u0001[\u0005!1n]3s!\tqa&\u0003\u00020)\t!1J]=p\u0011\u0015\t$\u00011\u00013\u0003\tIg\u000e\u0005\u0002\u000fg%\u0011A\u0007\u0006\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0006m\t\u0001\raN\u0001\u0004G2\u001c\bc\u0001\u001d@+9\u0011\u0011(\u0010\t\u0003u]i\u0011a\u000f\u0006\u0003y-\ta\u0001\u0010:p_Rt\u0014B\u0001 \u0018\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0006\u00072\f7o\u001d\u0006\u0003}]\tQa\u001e:ji\u0016$B\u0001R$I\u001bB\u0011a#R\u0005\u0003\r^\u0011A!\u00168ji\")Af\u0001a\u0001[!)\u0011j\u0001a\u0001\u0015\u0006\u0019q.\u001e;\u0011\u00059Y\u0015B\u0001'\u0015\u0005\u0019yU\u000f\u001e9vi\")aj\u0001a\u0001+\u0005\u0019A/\u001e9"
)
public class Tuple2DoubleDoubleSerializer extends Serializer implements Serializable {
   public Tuple2 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple2.mcDD.sp(in.readDouble(), in.readDouble());
   }

   public void write(final Kryo kser, final Output out, final Tuple2 tup) {
      out.writeDouble(tup._1$mcD$sp());
      out.writeDouble(tup._2$mcD$sp());
   }

   public Tuple2DoubleDoubleSerializer() {
      this.setImmutable(true);
   }
}
