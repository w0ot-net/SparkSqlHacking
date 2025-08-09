package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.reflect.ScalaSignature;
import scala.runtime.VolatileByteRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3A\u0001B\u0003\u0001\u0019!)Q\u0004\u0001C\u0001=!)\u0001\u0005\u0001C\u0001C!)!\u0007\u0001C\u0001g\tIbk\u001c7bi&dWMQ=uKJ+gmU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0014\u0005\u0001i\u0001c\u0001\b\u0013+9\u0011q\u0002E\u0007\u0002\u000b%\u0011\u0011#B\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019BCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\t\u0006!\t12$D\u0001\u0018\u0015\tA\u0012$A\u0004sk:$\u0018.\\3\u000b\u0003i\tQa]2bY\u0006L!\u0001H\f\u0003\u001fY{G.\u0019;jY\u0016\u0014\u0015\u0010^3SK\u001a\fa\u0001P5oSRtD#A\u0010\u0011\u0005=\u0001\u0011!B<sSR,G\u0003\u0002\u0012'WA\u0002\"a\t\u0013\u000e\u0003eI!!J\r\u0003\tUs\u0017\u000e\u001e\u0005\u0006O\t\u0001\r\u0001K\u0001\u0005WN,'\u000f\u0005\u0002\u000fS%\u0011!\u0006\u0006\u0002\u0005\u0017JLx\u000eC\u0003-\u0005\u0001\u0007Q&A\u0002pkR\u0004\"A\u0004\u0018\n\u0005=\"\"AB(viB,H\u000fC\u00032\u0005\u0001\u0007Q#\u0001\u0003ji\u0016l\u0017\u0001\u0002:fC\u0012$B!\u0006\u001b6u!)qe\u0001a\u0001Q!)ag\u0001a\u0001o\u0005\u0011\u0011N\u001c\t\u0003\u001daJ!!\u000f\u000b\u0003\u000b%s\u0007/\u001e;\t\u000bm\u001a\u0001\u0019\u0001\u001f\u0002\u0007\rd7\u000fE\u0002>\tVq!A\u0010\"\u0011\u0005}JR\"\u0001!\u000b\u0005\u0005[\u0011A\u0002\u001fs_>$h(\u0003\u0002D3\u00051\u0001K]3eK\u001aL!!\u0012$\u0003\u000b\rc\u0017m]:\u000b\u0005\rK\u0002"
)
public class VolatileByteRefSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final VolatileByteRef item) {
      out.writeByte(item.elem);
   }

   public VolatileByteRef read(final Kryo kser, final Input in, final Class cls) {
      return new VolatileByteRef(in.readByte());
   }
}
