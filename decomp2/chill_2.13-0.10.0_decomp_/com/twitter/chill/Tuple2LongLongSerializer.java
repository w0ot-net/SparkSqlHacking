package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001B\u0003\u0001\u0019!)a\u0005\u0001C\u0001O!)\u0011\u0006\u0001C\u0001U!)!\t\u0001C\u0001\u0007\nAB+\u001e9mKJbuN\\4M_:<7+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011!B2iS2d'B\u0001\u0005\n\u0003\u001d!x/\u001b;uKJT\u0011AC\u0001\u0004G>l7\u0001A\n\u0004\u00015q\u0002c\u0001\b\u0013+9\u0011q\u0002E\u0007\u0002\u000b%\u0011\u0011#B\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019BCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\t\u0006!\u00111\u0012dG\u000e\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a\u0001V;qY\u0016\u0014\u0004C\u0001\f\u001d\u0013\tirC\u0001\u0003M_:<\u0007CA\u0010%\u001b\u0005\u0001#BA\u0011#\u0003\tIwNC\u0001$\u0003\u0011Q\u0017M^1\n\u0005\u0015\u0002#\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001)!\ty\u0001!\u0001\u0003sK\u0006$G\u0003B\u000b,aUBQ\u0001\f\u0002A\u00025\nAa[:feB\u0011aBL\u0005\u0003_Q\u0011Aa\u0013:z_\")\u0011G\u0001a\u0001e\u0005\u0011\u0011N\u001c\t\u0003\u001dMJ!\u0001\u000e\u000b\u0003\u000b%s\u0007/\u001e;\t\u000bY\u0012\u0001\u0019A\u001c\u0002\u0007\rd7\u000fE\u00029\u007fUq!!O\u001f\u0011\u0005i:R\"A\u001e\u000b\u0005qZ\u0011A\u0002\u001fs_>$h(\u0003\u0002?/\u00051\u0001K]3eK\u001aL!\u0001Q!\u0003\u000b\rc\u0017m]:\u000b\u0005y:\u0012!B<sSR,G\u0003\u0002#H\u00116\u0003\"AF#\n\u0005\u0019;\"\u0001B+oSRDQ\u0001L\u0002A\u00025BQ!S\u0002A\u0002)\u000b1a\\;u!\tq1*\u0003\u0002M)\t1q*\u001e;qkRDQAT\u0002A\u0002U\t1\u0001^;q\u0001"
)
public class Tuple2LongLongSerializer extends Serializer implements Serializable {
   public Tuple2 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple2.mcJJ.sp(in.readLong(), in.readLong());
   }

   public void write(final Kryo kser, final Output out, final Tuple2 tup) {
      out.writeLong(tup._1$mcJ$sp());
      out.writeLong(tup._2$mcJ$sp());
   }

   public Tuple2LongLongSerializer() {
      this.setImmutable(true);
   }
}
