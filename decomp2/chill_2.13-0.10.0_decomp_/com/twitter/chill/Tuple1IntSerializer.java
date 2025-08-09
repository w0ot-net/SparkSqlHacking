package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001B\u0003\u0001\u0019!)a\u0005\u0001C\u0001O!)\u0011\u0006\u0001C\u0001U!)!\t\u0001C\u0001\u0007\n\u0019B+\u001e9mKFJe\u000e^*fe&\fG.\u001b>fe*\u0011aaB\u0001\u0006G\"LG\u000e\u001c\u0006\u0003\u0011%\tq\u0001^<jiR,'OC\u0001\u000b\u0003\r\u0019w.\\\u0002\u0001'\r\u0001QB\b\t\u0004\u001dI)bBA\b\u0011\u001b\u0005)\u0011BA\t\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0005\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003#\u0015\u00012AF\r\u001c\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"A\u0002+va2,\u0017\u0007\u0005\u0002\u00179%\u0011Qd\u0006\u0002\u0004\u0013:$\bCA\u0010%\u001b\u0005\u0001#BA\u0011#\u0003\tIwNC\u0001$\u0003\u0011Q\u0017M^1\n\u0005\u0015\u0002#\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001)!\ty\u0001!\u0001\u0003sK\u0006$G\u0003B\u000b,aUBQ\u0001\f\u0002A\u00025\nAa[:feB\u0011aBL\u0005\u0003_Q\u0011Aa\u0013:z_\")\u0011G\u0001a\u0001e\u0005\u0011\u0011N\u001c\t\u0003\u001dMJ!\u0001\u000e\u000b\u0003\u000b%s\u0007/\u001e;\t\u000bY\u0012\u0001\u0019A\u001c\u0002\u0007\rd7\u000fE\u00029\u007fUq!!O\u001f\u0011\u0005i:R\"A\u001e\u000b\u0005qZ\u0011A\u0002\u001fs_>$h(\u0003\u0002?/\u00051\u0001K]3eK\u001aL!\u0001Q!\u0003\u000b\rc\u0017m]:\u000b\u0005y:\u0012!B<sSR,G\u0003\u0002#H\u00116\u0003\"AF#\n\u0005\u0019;\"\u0001B+oSRDQ\u0001L\u0002A\u00025BQ!S\u0002A\u0002)\u000b1a\\;u!\tq1*\u0003\u0002M)\t1q*\u001e;qkRDQAT\u0002A\u0002U\t1\u0001^;q\u0001"
)
public class Tuple1IntSerializer extends Serializer implements Serializable {
   public Tuple1 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple1.mcI.sp(in.readInt());
   }

   public void write(final Kryo kser, final Output out, final Tuple1 tup) {
      out.writeInt(tup._1$mcI$sp());
   }

   public Tuple1IntSerializer() {
      this.setImmutable(true);
   }
}
