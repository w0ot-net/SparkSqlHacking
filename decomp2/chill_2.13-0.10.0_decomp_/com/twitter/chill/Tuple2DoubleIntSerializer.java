package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3A\u0001B\u0003\u0001\u0019!)\u0011\u0006\u0001C\u0001U!)A\u0006\u0001C\u0001[!)Q\t\u0001C\u0001\r\nIB+\u001e9mKJ\"u.\u001e2mK&sGoU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u0001i\u0011\u0005E\u0002\u000f%Uq!a\u0004\t\u000e\u0003\u0015I!!E\u0003\u0002\u000fA\f7m[1hK&\u00111\u0003\u0006\u0002\f\u0017N+'/[1mSj,'O\u0003\u0002\u0012\u000bA!a#G\u000e\u001f\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"A\u0002+va2,'\u0007\u0005\u0002\u00179%\u0011Qd\u0006\u0002\u0007\t>,(\r\\3\u0011\u0005Yy\u0012B\u0001\u0011\u0018\u0005\rIe\u000e\u001e\t\u0003E\u001dj\u0011a\t\u0006\u0003I\u0015\n!![8\u000b\u0003\u0019\nAA[1wC&\u0011\u0001f\t\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u0002\"a\u0004\u0001\u0002\tI,\u0017\r\u001a\u000b\u0005+9\u001a\u0004\bC\u00030\u0005\u0001\u0007\u0001'\u0001\u0003lg\u0016\u0014\bC\u0001\b2\u0013\t\u0011DC\u0001\u0003Lef|\u0007\"\u0002\u001b\u0003\u0001\u0004)\u0014AA5o!\tqa'\u0003\u00028)\t)\u0011J\u001c9vi\")\u0011H\u0001a\u0001u\u0005\u00191\r\\:\u0011\u0007m\u0012UC\u0004\u0002=\u0001B\u0011QhF\u0007\u0002})\u0011qhC\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005;\u0012A\u0002)sK\u0012,g-\u0003\u0002D\t\n)1\t\\1tg*\u0011\u0011iF\u0001\u0006oJLG/\u001a\u000b\u0005\u000f*[\u0005\u000b\u0005\u0002\u0017\u0011&\u0011\u0011j\u0006\u0002\u0005+:LG\u000fC\u00030\u0007\u0001\u0007\u0001\u0007C\u0003M\u0007\u0001\u0007Q*A\u0002pkR\u0004\"A\u0004(\n\u0005=#\"AB(viB,H\u000fC\u0003R\u0007\u0001\u0007Q#A\u0002ukB\u0004"
)
public class Tuple2DoubleIntSerializer extends Serializer implements Serializable {
   public Tuple2 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple2.mcDI.sp(in.readDouble(), in.readInt());
   }

   public void write(final Kryo kser, final Output out, final Tuple2 tup) {
      out.writeDouble(tup._1$mcD$sp());
      out.writeInt(tup._2$mcI$sp());
   }

   public Tuple2DoubleIntSerializer() {
      this.setImmutable(true);
   }
}
