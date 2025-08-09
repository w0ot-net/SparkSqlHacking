package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3A\u0001B\u0003\u0001\u0019!)\u0011\u0006\u0001C\u0001U!)A\u0006\u0001C\u0001[!)Q\t\u0001C\u0001\r\nQB+\u001e9mKJbuN\\4E_V\u0014G.Z*fe&\fG.\u001b>fe*\u0011aaB\u0001\u0006G\"LG\u000e\u001c\u0006\u0003\u0011%\tq\u0001^<jiR,'OC\u0001\u000b\u0003\r\u0019w.\\\u0002\u0001'\r\u0001Q\"\t\t\u0004\u001dI)bBA\b\u0011\u001b\u0005)\u0011BA\t\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0005\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003#\u0015\u0001BAF\r\u001c=5\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004UkBdWM\r\t\u0003-qI!!H\f\u0003\t1{gn\u001a\t\u0003-}I!\u0001I\f\u0003\r\u0011{WO\u00197f!\t\u0011s%D\u0001$\u0015\t!S%\u0001\u0002j_*\ta%\u0001\u0003kCZ\f\u0017B\u0001\u0015$\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t1\u0006\u0005\u0002\u0010\u0001\u0005!!/Z1e)\u0011)bf\r\u001d\t\u000b=\u0012\u0001\u0019\u0001\u0019\u0002\t-\u001cXM\u001d\t\u0003\u001dEJ!A\r\u000b\u0003\t-\u0013\u0018p\u001c\u0005\u0006i\t\u0001\r!N\u0001\u0003S:\u0004\"A\u0004\u001c\n\u0005]\"\"!B%oaV$\b\"B\u001d\u0003\u0001\u0004Q\u0014aA2mgB\u00191HQ\u000b\u000f\u0005q\u0002\u0005CA\u001f\u0018\u001b\u0005q$BA \f\u0003\u0019a$o\\8u}%\u0011\u0011iF\u0001\u0007!J,G-\u001a4\n\u0005\r#%!B\"mCN\u001c(BA!\u0018\u0003\u00159(/\u001b;f)\u00119%j\u0013)\u0011\u0005YA\u0015BA%\u0018\u0005\u0011)f.\u001b;\t\u000b=\u001a\u0001\u0019\u0001\u0019\t\u000b1\u001b\u0001\u0019A'\u0002\u0007=,H\u000f\u0005\u0002\u000f\u001d&\u0011q\n\u0006\u0002\u0007\u001fV$\b/\u001e;\t\u000bE\u001b\u0001\u0019A\u000b\u0002\u0007Q,\b\u000f"
)
public class Tuple2LongDoubleSerializer extends Serializer implements Serializable {
   public Tuple2 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple2.mcJD.sp(in.readLong(), in.readDouble());
   }

   public void write(final Kryo kser, final Output out, final Tuple2 tup) {
      out.writeLong(tup._1$mcJ$sp());
      out.writeDouble(tup._2$mcD$sp());
   }

   public Tuple2LongDoubleSerializer() {
      this.setImmutable(true);
   }
}
