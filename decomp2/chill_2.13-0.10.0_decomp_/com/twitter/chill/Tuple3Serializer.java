package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3A\u0001B\u0003\u0001\u0019!)Q\u0007\u0001C\u0001m!)\u0001\b\u0001C\u0001s!)\u0011\n\u0001C\u0001\u0015\n\u0001B+\u001e9mKN\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!\u0006\u0003\u000e=!Z3c\u0001\u0001\u000f[A\u0019qb\u0005\f\u000f\u0005A\tR\"A\u0003\n\u0005I)\u0011a\u00029bG.\fw-Z\u0005\u0003)U\u00111bS*fe&\fG.\u001b>fe*\u0011!#\u0002\t\u0006/iarEK\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1A+\u001e9mKN\u0002\"!\b\u0010\r\u0001\u0011)q\u0004\u0001b\u0001A\t\t\u0011)\u0005\u0002\"IA\u0011qCI\u0005\u0003Ga\u0011qAT8uQ&tw\r\u0005\u0002\u0018K%\u0011a\u0005\u0007\u0002\u0004\u0003:L\bCA\u000f)\t\u0015I\u0003A1\u0001!\u0005\u0005\u0011\u0005CA\u000f,\t\u0015a\u0003A1\u0001!\u0005\u0005\u0019\u0005C\u0001\u00184\u001b\u0005y#B\u0001\u00192\u0003\tIwNC\u00013\u0003\u0011Q\u0017M^1\n\u0005Qz#\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u00018!\u0015\u0001\u0002\u0001H\u0014+\u0003\u00159(/\u001b;f)\u0011QTHQ$\u0011\u0005]Y\u0014B\u0001\u001f\u0019\u0005\u0011)f.\u001b;\t\u000by\u0012\u0001\u0019A \u0002\t-\u001cXM\u001d\t\u0003\u001f\u0001K!!Q\u000b\u0003\t-\u0013\u0018p\u001c\u0005\u0006\u0007\n\u0001\r\u0001R\u0001\u0004_V$\bCA\bF\u0013\t1UC\u0001\u0004PkR\u0004X\u000f\u001e\u0005\u0006\u0011\n\u0001\rAF\u0001\u0004_\nT\u0017\u0001\u0002:fC\u0012$BAF&M#\")ah\u0001a\u0001\u007f!)Qj\u0001a\u0001\u001d\u0006\u0011\u0011N\u001c\t\u0003\u001f=K!\u0001U\u000b\u0003\u000b%s\u0007/\u001e;\t\u000bI\u001b\u0001\u0019A*\u0002\u0007\rd7\u000fE\u0002U7Zq!!V-\u0011\u0005YCR\"A,\u000b\u0005a[\u0011A\u0002\u001fs_>$h(\u0003\u0002[1\u00051\u0001K]3eK\u001aL!\u0001X/\u0003\u000b\rc\u0017m]:\u000b\u0005iC\u0002"
)
public class Tuple3Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple3 obj) {
      kser.writeClassAndObject(out, obj._1());
      out.flush();
      kser.writeClassAndObject(out, obj._2());
      out.flush();
      kser.writeClassAndObject(out, obj._3());
      out.flush();
   }

   public Tuple3 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple3(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple3Serializer() {
      this.setImmutable(true);
   }
}
