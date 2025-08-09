package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001B\u0003\u0001\u0019!)a\u0005\u0001C\u0001O!)\u0011\u0006\u0001C\u0001U!)!\t\u0001C\u0001\u0007\n1B+\u001e9mKJJe\u000e^%oiN+'/[1mSj,'O\u0003\u0002\u0007\u000f\u0005)1\r[5mY*\u0011\u0001\"C\u0001\bi^LG\u000f^3s\u0015\u0005Q\u0011aA2p[\u000e\u00011c\u0001\u0001\u000e=A\u0019aBE\u000b\u000f\u0005=\u0001R\"A\u0003\n\u0005E)\u0011a\u00029bG.\fw-Z\u0005\u0003'Q\u00111bS*fe&\fG.\u001b>fe*\u0011\u0011#\u0002\t\u0005-eY2$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019!V\u000f\u001d7feA\u0011a\u0003H\u0005\u0003;]\u00111!\u00138u!\tyB%D\u0001!\u0015\t\t#%\u0001\u0002j_*\t1%\u0001\u0003kCZ\f\u0017BA\u0013!\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t\u0001\u0006\u0005\u0002\u0010\u0001\u0005!!/Z1e)\u0011)2\u0006M\u001b\t\u000b1\u0012\u0001\u0019A\u0017\u0002\t-\u001cXM\u001d\t\u0003\u001d9J!a\f\u000b\u0003\t-\u0013\u0018p\u001c\u0005\u0006c\t\u0001\rAM\u0001\u0003S:\u0004\"AD\u001a\n\u0005Q\"\"!B%oaV$\b\"\u0002\u001c\u0003\u0001\u00049\u0014aA2mgB\u0019\u0001hP\u000b\u000f\u0005ej\u0004C\u0001\u001e\u0018\u001b\u0005Y$B\u0001\u001f\f\u0003\u0019a$o\\8u}%\u0011ahF\u0001\u0007!J,G-\u001a4\n\u0005\u0001\u000b%!B\"mCN\u001c(B\u0001 \u0018\u0003\u00159(/\u001b;f)\u0011!u\tS'\u0011\u0005Y)\u0015B\u0001$\u0018\u0005\u0011)f.\u001b;\t\u000b1\u001a\u0001\u0019A\u0017\t\u000b%\u001b\u0001\u0019\u0001&\u0002\u0007=,H\u000f\u0005\u0002\u000f\u0017&\u0011A\n\u0006\u0002\u0007\u001fV$\b/\u001e;\t\u000b9\u001b\u0001\u0019A\u000b\u0002\u0007Q,\b\u000f"
)
public class Tuple2IntIntSerializer extends Serializer implements Serializable {
   public Tuple2 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple2.mcII.sp(in.readInt(), in.readInt());
   }

   public void write(final Kryo kser, final Output out, final Tuple2 tup) {
      out.writeInt(tup._1$mcI$sp());
      out.writeInt(tup._2$mcI$sp());
   }

   public Tuple2IntIntSerializer() {
      this.setImmutable(true);
   }
}
