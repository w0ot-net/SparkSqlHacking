package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001B\u0012%\u0005-B\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u0005\tu\u0001\u0011\t\u0011)A\u0005o!11\b\u0001C\tMqBq!\u0011\u0001A\u0002\u0013%!\tC\u0004D\u0001\u0001\u0007I\u0011\u0002#\t\r)\u0003\u0001\u0015)\u00038\u0011\u0015Y\u0005\u0001\"\u0001M\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0011\u00159\u0006\u0001\"\u0001Y\u000f\u0015IF\u0005#\u0001[\r\u0015\u0019C\u0005#\u0001\\\u0011\u0015YD\u0002\"\u0001f\u0011\u001d1GB1A\u0005\n\tCaa\u001a\u0007!\u0002\u00139\u0004b\u00027\r\u0005\u0004%IA\u0011\u0005\u0007[2\u0001\u000b\u0011B\u001c\t\u000f=d!\u0019!C\u0007\u0005\"1\u0001\u000f\u0004Q\u0001\u000e]BqA\u001d\u0007C\u0002\u00135!\t\u0003\u0004t\u0019\u0001\u0006ia\u000e\u0005\bk2\u0011\r\u0011\"\u0004C\u0011\u00191H\u0002)A\u0007o!)\u0001\u0010\u0004C\u0007s\"1q\u0010\u0004C\u0007\u0003\u0003Aq!!\u0003\r\t\u001b\tY\u0001C\u0004\u0002\u00121!i!a\u0005\t\u000f\u0005mA\u0002\"\u0004\u0002\u001e!9\u00111\u0007\u0007\u0005\u0002\u0005U\u0002bBA\u001c\u0019\u0011\u0005\u0011\u0011\b\u0005\b\u0003\u007faA\u0011AA!\u0011\u001d\t9\u0005\u0004C\u0001\u0003\u0013Bq!!\u0014\r\t\u0003\ty\u0005C\u0005\u0002\\1\t\n\u0011\"\u0001\u0002^\tQq+\u001a7miQ\"\u0014hN1\u000b\u0005\u00152\u0013a\u0001:oO*\u0011q\u0005K\u0001\u0007e\u0006tGm\\7\u000b\u0003%\nQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001YA\u0011QFL\u0007\u0002M%\u0011qF\n\u0002\u0012\u0013:$()Y:fI\u001e+g.\u001a:bi>\u0014\u0018!B:uCR,\u0007c\u0001\u001a6o5\t1GC\u00015\u0003\u0015\u00198-\u00197b\u0013\t14GA\u0003BeJ\f\u0017\u0010\u0005\u00023q%\u0011\u0011h\r\u0002\u0004\u0013:$\u0018AA51\u0003\u0019a\u0014N\\5u}Q\u0019Qh\u0010!\u0011\u0005y\u0002Q\"\u0001\u0013\t\u000bA\u001a\u0001\u0019A\u0019\t\u000bi\u001a\u0001\u0019A\u001c\u0002\u0003%,\u0012aN\u0001\u0006S~#S-\u001d\u000b\u0003\u000b\"\u0003\"A\r$\n\u0005\u001d\u001b$\u0001B+oSRDq!S\u0003\u0002\u0002\u0003\u0007q'A\u0002yIE\n!!\u001b\u0011\u0002\u0011\r|\u0007/_%oSR,\u0012!P\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002\u001fB\u0019!'\u000e)\u0011\u0005I\n\u0016B\u0001*4\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u0005\u0015+\u0006\"\u0002,\n\u0001\u0004y\u0015!\u00022zi\u0016\u001c\u0018a\u00028fqRLe\u000e\u001e\u000b\u0002o\u0005Qq+\u001a7miQ\"\u0014hN1\u0011\u0005yb1c\u0001\u0007]?B\u0011!'X\u0005\u0003=N\u0012a!\u00118z%\u00164\u0007\u0003B\u0017a{\tL!!\u0019\u0014\u0003%\u001d+g.\u001a:bi>\u00148i\\7qC:LwN\u001c\t\u0005e\r\ft'\u0003\u0002eg\t1A+\u001e9mKJ\"\u0012AW\u0001\n+B\u0004XM]'bg.\f!\"\u00169qKJl\u0015m]6!Q\ty\u0011\u000e\u0005\u00023U&\u00111n\r\u0002\u0007S:d\u0017N\\3\u0002\u00131{w/\u001a:NCN\\\u0017A\u0003'po\u0016\u0014X*Y:lA!\u0012\u0011#[\u0001\u0002\u0017\u0006\u00111\n\t\u0015\u0003'%\f\u0011AU\u0001\u0003%\u0002B#!F5\u0002\u000b\tKF+R*\u0002\r\tKF+R*!Q\t9\u0012.A\u0004nCR\u0004\u0004o\\:\u0015\u0007]RH\u0010C\u0003|1\u0001\u0007q'A\u0001u\u0011\u0015i\b\u00041\u00018\u0003\u00051\bF\u0001\rj\u0003\u001di\u0017\r\u001e\u0019oK\u001e$RaNA\u0002\u0003\u000bAQa_\rA\u0002]BQ!`\rA\u0002]B#!G5\u0002\t5\fG/\r\u000b\u0004o\u00055\u0001\"B?\u001b\u0001\u00049\u0004F\u0001\u000ej\u0003\u001di\u0017\r^\u001aoK\u001e$RaNA\u000b\u0003/AQa_\u000eA\u0002]BQ!`\u000eA\u0002]B#aG5\u0002\t5\fG/\u000e\u000b\fo\u0005}\u00111EA\u0014\u0003W\ty\u0003\u0003\u0004\u0002\"q\u0001\raN\u0001\u0002e\"1\u0011Q\u0005\u000fA\u0002]\n\u0011!\u0019\u0005\u0007\u0003Sa\u0002\u0019A\u001c\u0002\u0005\u0011\u001c\bBBA\u00179\u0001\u0007q'\u0001\u0002ei\")Q\u0010\ba\u0001o!\u0012A$[\u0001\u000be\u0006tGm\\7TK\u0016$G#\u00012\u0002\u0011\u0019\u0014x.\\*fK\u0012$2!PA\u001e\u0011\u0019\tiD\ba\u0001E\u0006!1/Z3e\u0003%1'o\\7BeJ\f\u0017\u0010F\u0002>\u0003\u0007Ba!!\u0012 \u0001\u0004\t\u0014aA1se\u0006IaM]8n\u0005f$Xm\u001d\u000b\u0004{\u0005-\u0003\"\u0002,!\u0001\u0004y\u0015\u0001\u00034s_6$\u0016.\\3\u0015\u0007u\n\t\u0006C\u0005\u0002T\u0005\u0002\n\u00111\u0001\u0002V\u0005!A/[7f!\r\u0011\u0014qK\u0005\u0004\u00033\u001a$\u0001\u0002'p]\u001e\f!C\u001a:p[RKW.\u001a\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\f\u0016\u0005\u0003+\n\tg\u000b\u0002\u0002dA!\u0011QMA8\u001b\t\t9G\u0003\u0003\u0002j\u0005-\u0014!C;oG\",7m[3e\u0015\r\tigM\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA9\u0003O\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0001"
)
public final class Well44497a extends IntBasedGenerator {
   private final int[] state;
   private int i;

   public static long fromTime$default$1() {
      return Well44497a$.MODULE$.fromTime$default$1();
   }

   public static Well44497a fromTime(final long time) {
      return Well44497a$.MODULE$.fromTime(time);
   }

   public static Well44497a fromBytes(final byte[] bytes) {
      return Well44497a$.MODULE$.fromBytes(bytes);
   }

   public static Well44497a fromArray(final int[] arr) {
      return Well44497a$.MODULE$.fromArray(arr);
   }

   public static Well44497a fromSeed(final Tuple2 seed) {
      return Well44497a$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return Well44497a$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Well44497a$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Well44497a$.MODULE$.apply();
   }

   private int i() {
      return this.i;
   }

   private void i_$eq(final int x$1) {
      this.i = x$1;
   }

   public Well44497a copyInit() {
      return new Well44497a((int[])this.state.clone(), this.i());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[Well44497a$.MODULE$.spire$random$rng$Well44497a$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < Well44497a$.MODULE$.spire$random$rng$Well44497a$$R(); ++index$macro$1) {
         bb.putInt(this.state[index$macro$1]);
      }

      bb.putInt(this.i());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < Well44497a$.MODULE$.spire$random$rng$Well44497a$$BYTES() ? Arrays.copyOf(bytes, Well44497a$.MODULE$.spire$random$rng$Well44497a$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < Well44497a$.MODULE$.spire$random$rng$Well44497a$$R(); ++index$macro$1) {
         this.state[index$macro$1] = bb.getInt();
      }

      this.i_$eq(bb.getInt());
   }

   public int nextInt() {
      int z0 = this.state[Well44497abIndexCache$.MODULE$.vrm1()[this.i()]] & Well44497a$.MODULE$.spire$random$rng$Well44497a$$LowerMask() | this.state[Well44497abIndexCache$.MODULE$.vrm2()[this.i()]] & Well44497a$.MODULE$.spire$random$rng$Well44497a$$UpperMask();
      int z1 = Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat0neg(-24, this.state[this.i()]) ^ Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat0pos(30, this.state[Well44497abIndexCache$.MODULE$.vm1()[this.i()]]);
      int z2 = Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat0neg(-10, this.state[Well44497abIndexCache$.MODULE$.vm2()[this.i()]]) ^ Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat3neg(-26, this.state[Well44497abIndexCache$.MODULE$.vm3()[this.i()]]);
      this.state[this.i()] = z1 ^ z2;
      this.state[Well44497abIndexCache$.MODULE$.vrm1()[this.i()]] = Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat1(z0) ^ Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat0pos(20, z1) ^ Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat5(9, -1221985044, -67108865, 131072, z2) ^ Well44497a$.MODULE$.spire$random$rng$Well44497a$$mat1(this.state[this.i()]);
      this.i_$eq(Well44497abIndexCache$.MODULE$.vrm1()[this.i()]);
      return this.state[this.i()];
   }

   public Well44497a(final int[] state, final int i0) {
      this.state = state;
      this.i = i0;
   }
}
