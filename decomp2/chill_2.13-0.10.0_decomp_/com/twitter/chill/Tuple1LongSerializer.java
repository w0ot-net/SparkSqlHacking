package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001B\u0003\u0001\u0019!)a\u0005\u0001C\u0001O!)\u0011\u0006\u0001C\u0001U!)!\t\u0001C\u0001\u0007\n!B+\u001e9mKFbuN\\4TKJL\u0017\r\\5{KJT!AB\u0004\u0002\u000b\rD\u0017\u000e\u001c7\u000b\u0005!I\u0011a\u0002;xSR$XM\u001d\u0006\u0002\u0015\u0005\u00191m\\7\u0004\u0001M\u0019\u0001!\u0004\u0010\u0011\u00079\u0011RC\u0004\u0002\u0010!5\tQ!\u0003\u0002\u0012\u000b\u00059\u0001/Y2lC\u001e,\u0017BA\n\u0015\u0005-Y5+\u001a:jC2L'0\u001a:\u000b\u0005E)\u0001c\u0001\f\u001a75\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004UkBdW-\r\t\u0003-qI!!H\f\u0003\t1{gn\u001a\t\u0003?\u0011j\u0011\u0001\t\u0006\u0003C\t\n!![8\u000b\u0003\r\nAA[1wC&\u0011Q\u0005\t\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003!\u0002\"a\u0004\u0001\u0002\tI,\u0017\r\u001a\u000b\u0005+-\u0002T\u0007C\u0003-\u0005\u0001\u0007Q&\u0001\u0003lg\u0016\u0014\bC\u0001\b/\u0013\tyCC\u0001\u0003Lef|\u0007\"B\u0019\u0003\u0001\u0004\u0011\u0014AA5o!\tq1'\u0003\u00025)\t)\u0011J\u001c9vi\")aG\u0001a\u0001o\u0005\u00191\r\\:\u0011\u0007azTC\u0004\u0002:{A\u0011!hF\u0007\u0002w)\u0011AhC\u0001\u0007yI|w\u000e\u001e \n\u0005y:\u0012A\u0002)sK\u0012,g-\u0003\u0002A\u0003\n)1\t\\1tg*\u0011ahF\u0001\u0006oJLG/\u001a\u000b\u0005\t\u001eCU\n\u0005\u0002\u0017\u000b&\u0011ai\u0006\u0002\u0005+:LG\u000fC\u0003-\u0007\u0001\u0007Q\u0006C\u0003J\u0007\u0001\u0007!*A\u0002pkR\u0004\"AD&\n\u00051#\"AB(viB,H\u000fC\u0003O\u0007\u0001\u0007Q#A\u0002ukB\u0004"
)
public class Tuple1LongSerializer extends Serializer implements Serializable {
   public Tuple1 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple1.mcJ.sp(in.readLong());
   }

   public void write(final Kryo kser, final Output out, final Tuple1 tup) {
      out.writeLong(tup._1$mcJ$sp());
   }

   public Tuple1LongSerializer() {
      this.setImmutable(true);
   }
}
