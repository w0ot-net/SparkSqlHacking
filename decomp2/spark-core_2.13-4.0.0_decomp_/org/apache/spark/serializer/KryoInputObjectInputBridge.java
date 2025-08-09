package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import java.io.FilterInputStream;
import java.io.ObjectInput;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b!\u0002\u000b\u0016\u0001]i\u0002\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u0011Q\u0002!\u0011!Q\u0001\nUBQA\u000f\u0001\u0005\u0002mBQ\u0001\u0011\u0001\u0005B\u0005CQ\u0001\u0013\u0001\u0005B%CQ!\u0014\u0001\u0005B9CQA\u0015\u0001\u0005BMCQa\u0016\u0001\u0005BaCQ\u0001\u0018\u0001\u0005BuCQ!\u001b\u0001\u0005B)DQA\u001c\u0001\u0005B)DQa\u001c\u0001\u0005BADQa\u001d\u0001\u0005BQDQa\u001d\u0001\u0005BuDa!a\u0002\u0001\t\u0003j\u0006bBA\u0005\u0001\u0011\u0005\u00131\u0002\u0005\u0007\u0003'\u0001A\u0011\t6\t\u000f\u0005U\u0001\u0001\"\u0011\u0002\u0018!9\u0011q\u0004\u0001\u0005B\u0005\u0005\"AG&ss>Le\u000e];u\u001f\nTWm\u0019;J]B,HO\u0011:jI\u001e,'B\u0001\f\u0018\u0003)\u0019XM]5bY&TXM\u001d\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sON\u0019\u0001A\b\u0014\u0011\u0005}!S\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013AA5p\u0015\u0005\u0019\u0013\u0001\u00026bm\u0006L!!\n\u0011\u0003#\u0019KG\u000e^3s\u0013:\u0004X\u000f^*ue\u0016\fW\u000e\u0005\u0002 O%\u0011\u0001\u0006\t\u0002\f\u001f\nTWm\u0019;J]B,H/\u0001\u0003lef|7\u0001\u0001\t\u0003YIj\u0011!\f\u0006\u0003S9R!a\f\u0019\u0002!\u0015\u001cx\u000e^3sS\u000e\u001cxN\u001a;xCJ,'\"A\u0019\u0002\u0007\r|W.\u0003\u00024[\t!1J]=p\u0003\u0015Ig\u000e];u!\t1\u0004(D\u00018\u0015\t\tS&\u0003\u0002:o\t)\u0011J\u001c9vi\u00061A(\u001b8jiz\"2\u0001\u0010 @!\ti\u0004!D\u0001\u0016\u0011\u0015I3\u00011\u0001,\u0011\u0015!4\u00011\u00016\u0003!\u0011X-\u00193M_:<G#\u0001\"\u0011\u0005\r3U\"\u0001#\u000b\u0003\u0015\u000bQa]2bY\u0006L!a\u0012#\u0003\t1{gnZ\u0001\te\u0016\fGm\u00115beR\t!\n\u0005\u0002D\u0017&\u0011A\n\u0012\u0002\u0005\u0007\"\f'/A\u0005sK\u0006$g\t\\8biR\tq\n\u0005\u0002D!&\u0011\u0011\u000b\u0012\u0002\u0006\r2|\u0017\r^\u0001\te\u0016\fGMQ=uKR\tA\u000b\u0005\u0002D+&\u0011a\u000b\u0012\u0002\u0005\u0005f$X-A\u0005sK\u0006$7\u000b[8siR\t\u0011\f\u0005\u0002D5&\u00111\f\u0012\u0002\u0006'\"|'\u000f^\u0001\be\u0016\fG-\u0016+G)\u0005q\u0006CA0g\u001d\t\u0001G\r\u0005\u0002b\t6\t!M\u0003\u0002dU\u00051AH]8pizJ!!\u001a#\u0002\rA\u0013X\rZ3g\u0013\t9\u0007N\u0001\u0004TiJLgn\u001a\u0006\u0003K\u0012\u000bqA]3bI&sG\u000fF\u0001l!\t\u0019E.\u0003\u0002n\t\n\u0019\u0011J\u001c;\u0002#I,\u0017\rZ+og&<g.\u001a3TQ>\u0014H/A\u0005tW&\u0004()\u001f;fgR\u00111.\u001d\u0005\u0006e2\u0001\ra[\u0001\u0002]\u0006I!/Z1e\rVdG.\u001f\u000b\u0003kb\u0004\"a\u0011<\n\u0005]$%\u0001B+oSRDQ!_\u0007A\u0002i\f\u0011A\u0019\t\u0004\u0007n$\u0016B\u0001?E\u0005\u0015\t%O]1z)\u0015)hp`A\u0002\u0011\u0015Ih\u00021\u0001{\u0011\u0019\t\tA\u0004a\u0001W\u0006\u0019qN\u001a4\t\r\u0005\u0015a\u00021\u0001l\u0003\raWM\\\u0001\te\u0016\fG\rT5oK\u0006Y!/Z1e\u0005>|G.Z1o)\t\ti\u0001E\u0002D\u0003\u001fI1!!\u0005E\u0005\u001d\u0011un\u001c7fC:\f\u0001C]3bIVs7/[4oK\u0012\u0014\u0015\u0010^3\u0002\u0015I,\u0017\r\u001a#pk\ndW\r\u0006\u0002\u0002\u001aA\u00191)a\u0007\n\u0007\u0005uAI\u0001\u0004E_V\u0014G.Z\u0001\u000be\u0016\fGm\u00142kK\u000e$HCAA\u0012!\r\u0019\u0015QE\u0005\u0004\u0003O!%AB!osJ+g\r"
)
public class KryoInputObjectInputBridge extends FilterInputStream implements ObjectInput {
   private final Kryo kryo;
   private final Input input;

   public long readLong() {
      return this.input.readLong();
   }

   public char readChar() {
      return this.input.readChar();
   }

   public float readFloat() {
      return this.input.readFloat();
   }

   public byte readByte() {
      return this.input.readByte();
   }

   public short readShort() {
      return this.input.readShort();
   }

   public String readUTF() {
      return this.input.readString();
   }

   public int readInt() {
      return this.input.readInt();
   }

   public int readUnsignedShort() {
      return this.input.readShortUnsigned();
   }

   public int skipBytes(final int n) {
      this.input.skip(n);
      return n;
   }

   public void readFully(final byte[] b) {
      this.input.read(b);
   }

   public void readFully(final byte[] b, final int off, final int len) {
      this.input.read(b, off, len);
   }

   public String readLine() {
      throw new UnsupportedOperationException("readLine");
   }

   public boolean readBoolean() {
      return this.input.readBoolean();
   }

   public int readUnsignedByte() {
      return this.input.readByteUnsigned();
   }

   public double readDouble() {
      return this.input.readDouble();
   }

   public Object readObject() {
      return this.kryo.readClassAndObject(this.input);
   }

   public KryoInputObjectInputBridge(final Kryo kryo, final Input input) {
      super(input);
      this.kryo = kryo;
      this.input = input;
   }
}
