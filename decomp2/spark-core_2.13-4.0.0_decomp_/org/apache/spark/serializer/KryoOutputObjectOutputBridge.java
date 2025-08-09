package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import java.io.FilterOutputStream;
import java.io.ObjectOutput;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b!B\n\u0015\u0001Ya\u0002\u0002\u0003\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u0011M\u0002!\u0011!Q\u0001\nQBQ!\u000f\u0001\u0005\u0002iBQa\u0010\u0001\u0005B\u0001CQ\u0001\u0014\u0001\u0005B5CQa\u0017\u0001\u0005BqCQ!\u0019\u0001\u0005B\tDQ\u0001\u001a\u0001\u0005B\u0015DQA\u001b\u0001\u0005B-DQ!\u001c\u0001\u0005B9DQa\u001d\u0001\u0005BQDQa\u001d\u0001\u0005B]DQa\u001d\u0001\u0005B}Dq!a\u0003\u0001\t\u0003\ni\u0001C\u0004\u0002\u0012\u0001!\t%a\u0005\t\u000f\u0005]\u0001\u0001\"\u0011\u0002\u001a!9\u00111\u0005\u0001\u0005B\u0005\u0015\u0002bBA\u0015\u0001\u0011\u0005\u00131\u0006\u0002\u001d\u0017JLxnT;uaV$xJ\u00196fGR|U\u000f\u001e9vi\n\u0013\u0018\u000eZ4f\u0015\t)b#\u0001\u0006tKJL\u0017\r\\5{KJT!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'oZ\n\u0004\u0001u)\u0003C\u0001\u0010$\u001b\u0005y\"B\u0001\u0011\"\u0003\tIwNC\u0001#\u0003\u0011Q\u0017M^1\n\u0005\u0011z\"A\u0005$jYR,'oT;uaV$8\u000b\u001e:fC6\u0004\"A\b\u0014\n\u0005\u001dz\"\u0001D(cU\u0016\u001cGoT;uaV$\u0018\u0001B6ss>\u001c\u0001\u0001\u0005\u0002,c5\tAF\u0003\u0002)[)\u0011afL\u0001\u0011KN|G/\u001a:jGN|g\r^<be\u0016T\u0011\u0001M\u0001\u0004G>l\u0017B\u0001\u001a-\u0005\u0011Y%/_8\u0002\r=,H\u000f];u!\t)t'D\u00017\u0015\t\u0001C&\u0003\u00029m\t1q*\u001e;qkR\fa\u0001P5oSRtDcA\u001e>}A\u0011A\bA\u0007\u0002)!)\u0001f\u0001a\u0001U!)1g\u0001a\u0001i\u0005QqO]5uK\u001acw.\u0019;\u0015\u0005\u0005;\u0005C\u0001\"F\u001b\u0005\u0019%\"\u0001#\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001b%\u0001B+oSRDQ\u0001\u0013\u0003A\u0002%\u000b\u0011A\u001e\t\u0003\u0005*K!aS\"\u0003\u000b\u0019cw.\u0019;\u0002\u0015]\u0014\u0018\u000e^3DQ\u0006\u00148\u000f\u0006\u0002B\u001d\")q*\u0002a\u0001!\u0006\t1\u000f\u0005\u0002R1:\u0011!K\u0016\t\u0003'\u000ek\u0011\u0001\u0016\u0006\u0003+&\na\u0001\u0010:p_Rt\u0014BA,D\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011L\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005]\u001b\u0015aC<sSR,Gi\\;cY\u0016$\"!Q/\t\u000b!3\u0001\u0019\u00010\u0011\u0005\t{\u0016B\u00011D\u0005\u0019!u.\u001e2mK\u0006AqO]5uKV#f\t\u0006\u0002BG\")qj\u0002a\u0001!\u0006QqO]5uKNCwN\u001d;\u0015\u0005\u00053\u0007\"\u0002%\t\u0001\u00049\u0007C\u0001\"i\u0013\tI7IA\u0002J]R\f\u0001b\u001e:ji\u0016Le\u000e\u001e\u000b\u0003\u00032DQ\u0001S\u0005A\u0002\u001d\fAb\u001e:ji\u0016\u0014un\u001c7fC:$\"!Q8\t\u000b!S\u0001\u0019\u00019\u0011\u0005\t\u000b\u0018B\u0001:D\u0005\u001d\u0011un\u001c7fC:\fQa\u001e:ji\u0016$\"!Q;\t\u000bY\\\u0001\u0019A4\u0002\u0003\t$\"!\u0011=\t\u000bYd\u0001\u0019A=\u0011\u0007\tSH0\u0003\u0002|\u0007\n)\u0011I\u001d:bsB\u0011!)`\u0005\u0003}\u000e\u0013AAQ=uKR9\u0011)!\u0001\u0002\u0004\u0005\u001d\u0001\"\u0002<\u000e\u0001\u0004I\bBBA\u0003\u001b\u0001\u0007q-A\u0002pM\u001aDa!!\u0003\u000e\u0001\u00049\u0017a\u00017f]\u0006QqO]5uK\nKH/Z:\u0015\u0007\u0005\u000by\u0001C\u0003P\u001d\u0001\u0007\u0001+A\u0005xe&$Xm\u00115beR\u0019\u0011)!\u0006\t\u000b!{\u0001\u0019A4\u0002\u0013]\u0014\u0018\u000e^3M_:<GcA!\u0002\u001c!1\u0001\n\u0005a\u0001\u0003;\u00012AQA\u0010\u0013\r\t\tc\u0011\u0002\u0005\u0019>tw-A\u0005xe&$XMQ=uKR\u0019\u0011)a\n\t\u000b!\u000b\u0002\u0019A4\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0004\u0003\u00065\u0002bBA\u0018%\u0001\u0007\u0011\u0011G\u0001\u0004_\nT\u0007c\u0001\"\u00024%\u0019\u0011QG\"\u0003\r\u0005s\u0017PU3g\u0001"
)
public class KryoOutputObjectOutputBridge extends FilterOutputStream implements ObjectOutput {
   private final Kryo kryo;
   private final Output output;

   public void writeFloat(final float v) {
      this.output.writeFloat(v);
   }

   public void writeChars(final String s) {
      throw new UnsupportedOperationException("writeChars");
   }

   public void writeDouble(final double v) {
      this.output.writeDouble(v);
   }

   public void writeUTF(final String s) {
      this.output.writeString(s);
   }

   public void writeShort(final int v) {
      this.output.writeShort(v);
   }

   public void writeInt(final int v) {
      this.output.writeInt(v);
   }

   public void writeBoolean(final boolean v) {
      this.output.writeBoolean(v);
   }

   public void write(final int b) {
      this.output.write(b);
   }

   public void write(final byte[] b) {
      this.output.write(b);
   }

   public void write(final byte[] b, final int off, final int len) {
      this.output.write(b, off, len);
   }

   public void writeBytes(final String s) {
      this.output.writeString(s);
   }

   public void writeChar(final int v) {
      this.output.writeChar((char)v);
   }

   public void writeLong(final long v) {
      this.output.writeLong(v);
   }

   public void writeByte(final int v) {
      this.output.writeByte(v);
   }

   public void writeObject(final Object obj) {
      this.kryo.writeClassAndObject(this.output, obj);
   }

   public KryoOutputObjectOutputBridge(final Kryo kryo, final Output output) {
      super(output);
      this.kryo = kryo;
      this.output = output;
   }
}
