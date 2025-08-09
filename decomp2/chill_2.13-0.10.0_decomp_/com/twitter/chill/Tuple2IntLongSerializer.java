package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3A\u0001B\u0003\u0001\u0019!)\u0011\u0006\u0001C\u0001U!)A\u0006\u0001C\u0001[!)Q\t\u0001C\u0001\r\n9B+\u001e9mKJJe\u000e\u001e'p]\u001e\u001cVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001aE\u0002\u0001\u001b\u0005\u00022A\u0004\n\u0016\u001d\ty\u0001#D\u0001\u0006\u0013\t\tR!A\u0004qC\u000e\\\u0017mZ3\n\u0005M!\"aC&TKJL\u0017\r\\5{KJT!!E\u0003\u0011\tYI2DH\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1A+\u001e9mKJ\u0002\"A\u0006\u000f\n\u0005u9\"aA%oiB\u0011acH\u0005\u0003A]\u0011A\u0001T8oOB\u0011!eJ\u0007\u0002G)\u0011A%J\u0001\u0003S>T\u0011AJ\u0001\u0005U\u00064\u0018-\u0003\u0002)G\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012a\u000b\t\u0003\u001f\u0001\tAA]3bIR!QCL\u001a9\u0011\u0015y#\u00011\u00011\u0003\u0011Y7/\u001a:\u0011\u00059\t\u0014B\u0001\u001a\u0015\u0005\u0011Y%/_8\t\u000bQ\u0012\u0001\u0019A\u001b\u0002\u0005%t\u0007C\u0001\b7\u0013\t9DCA\u0003J]B,H\u000fC\u0003:\u0005\u0001\u0007!(A\u0002dYN\u00042a\u000f\"\u0016\u001d\ta\u0004\t\u0005\u0002>/5\taH\u0003\u0002@\u0017\u00051AH]8pizJ!!Q\f\u0002\rA\u0013X\rZ3g\u0013\t\u0019EIA\u0003DY\u0006\u001c8O\u0003\u0002B/\u0005)qO]5uKR!qIS&Q!\t1\u0002*\u0003\u0002J/\t!QK\\5u\u0011\u0015y3\u00011\u00011\u0011\u0015a5\u00011\u0001N\u0003\ryW\u000f\u001e\t\u0003\u001d9K!a\u0014\u000b\u0003\r=+H\u000f];u\u0011\u0015\t6\u00011\u0001\u0016\u0003\r!X\u000f\u001d"
)
public class Tuple2IntLongSerializer extends Serializer implements Serializable {
   public Tuple2 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple2.mcIJ.sp(in.readInt(), in.readLong());
   }

   public void write(final Kryo kser, final Output out, final Tuple2 tup) {
      out.writeInt(tup._1$mcI$sp());
      out.writeLong(tup._2$mcJ$sp());
   }

   public Tuple2IntLongSerializer() {
      this.setImmutable(true);
   }
}
