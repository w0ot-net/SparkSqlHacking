package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001\u0002\u0014(\u00059B\u0001b\r\u0001\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\t{\u0001\u0011\t\u0011)A\u0005u!1a\b\u0001C\tS}Bq\u0001\u0012\u0001A\u0002\u0013%Q\tC\u0004G\u0001\u0001\u0007I\u0011B$\t\r5\u0003\u0001\u0015)\u0003;\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u00151\u0006\u0001\"\u0001X\u0011\u0015Q\u0006\u0001\"\u0001\\\u000f\u0015av\u0005#\u0001^\r\u00151s\u0005#\u0001_\u0011\u0015qD\u0002\"\u0001i\u0011\u001dIGB1A\u0005\n\u0015CaA\u001b\u0007!\u0002\u0013Q\u0004bB8\r\u0005\u0004%I!\u0012\u0005\u0007a2\u0001\u000b\u0011\u0002\u001e\t\u000fId!\u0019!C\u0005\u000b\"11\u000f\u0004Q\u0001\niBq!\u001e\u0007C\u0002\u0013%Q\t\u0003\u0004w\u0019\u0001\u0006IA\u000f\u0005\bq2\u0011\r\u0011\"\u0004F\u0011\u0019IH\u0002)A\u0007u!91\u0010\u0004b\u0001\n\u001b)\u0005B\u0002?\rA\u00035!\bC\u0004\u007f\u0019\t\u0007IQB#\t\r}d\u0001\u0015!\u0004;\u0011\u001d\t\u0019\u0001\u0004C\u0007\u0003\u000bAq!!\u0005\r\t\u001b\t\u0019\u0002C\u0004\u0002\u001c1!i!!\b\t\u000f\u0005\rB\u0002\"\u0004\u0002&!9\u0011Q\u0006\u0007\u0005\u0002\u0005=\u0002bBA\u0019\u0019\u0011\u0005\u00111\u0007\u0005\b\u0003saA\u0011AA\u001e\u0011\u001d\t\t\u0005\u0004C\u0001\u0003\u0007Bq!a\u0012\r\t\u0003\tI\u0005C\u0005\u0002V1\t\n\u0011\"\u0001\u0002X\tQq+\u001a7mceJ4gN2\u000b\u0005!J\u0013a\u0001:oO*\u0011!fK\u0001\u0007e\u0006tGm\\7\u000b\u00031\nQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001_A\u0011\u0001'M\u0007\u0002S%\u0011!'\u000b\u0002\u0012\u0013:$()Y:fI\u001e+g.\u001a:bi>\u0014\u0018!B:uCR,\u0007cA\u001b9u5\taGC\u00018\u0003\u0015\u00198-\u00197b\u0013\tIdGA\u0003BeJ\f\u0017\u0010\u0005\u00026w%\u0011AH\u000e\u0002\u0004\u0013:$\u0018AA51\u0003\u0019a\u0014N\\5u}Q\u0019\u0001IQ\"\u0011\u0005\u0005\u0003Q\"A\u0014\t\u000bM\u001a\u0001\u0019\u0001\u001b\t\u000bu\u001a\u0001\u0019\u0001\u001e\u0002\u0003%,\u0012AO\u0001\u0006S~#S-\u001d\u000b\u0003\u0011.\u0003\"!N%\n\u0005)3$\u0001B+oSRDq\u0001T\u0003\u0002\u0002\u0003\u0007!(A\u0002yIE\n!!\u001b\u0011\u0002\u0011\r|\u0007/_%oSR,\u0012\u0001Q\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002%B\u0019Q\u0007O*\u0011\u0005U\"\u0016BA+7\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u0005!C\u0006\"B-\n\u0001\u0004\u0011\u0016!\u00022zi\u0016\u001c\u0018a\u00028fqRLe\u000e\u001e\u000b\u0002u\u0005Qq+\u001a7mceJ4gN2\u0011\u0005\u0005c1c\u0001\u0007`EB\u0011Q\u0007Y\u0005\u0003CZ\u0012a!\u00118z%\u00164\u0007\u0003\u0002\u0019d\u0001\u0016L!\u0001Z\u0015\u0003%\u001d+g.\u001a:bi>\u00148i\\7qC:LwN\u001c\t\u0005k\u0019$$(\u0003\u0002hm\t1A+\u001e9mKJ\"\u0012!X\u0001\n+B\u0004XM]'bg.\f!\"\u00169qKJl\u0015m]6!Q\tyA\u000e\u0005\u00026[&\u0011aN\u000e\u0002\u0007S:d\u0017N\\3\u0002\u00131{w/\u001a:NCN\\\u0017A\u0003'po\u0016\u0014X*Y:lA!\u0012\u0011\u0003\\\u0001\b)\u0016l\u0007/\u001a:C\u0003!!V-\u001c9fe\n\u0003\u0003FA\nm\u0003\u001d!V-\u001c9fe\u000e\u000b\u0001\u0002V3na\u0016\u00148\t\t\u0015\u0003+1\f\u0011aS\u0001\u0003\u0017\u0002B#a\u00067\u0002\u0003I\u000b!A\u0015\u0011)\u0005ea\u0017!\u0002\"Z)\u0016\u001b\u0016A\u0002\"Z)\u0016\u001b\u0006\u0005\u000b\u0002\u001cY\u00069Q.\u0019;1a>\u001cH#\u0002\u001e\u0002\b\u0005-\u0001BBA\u00059\u0001\u0007!(A\u0001u\u0011\u0019\ti\u0001\ba\u0001u\u0005\ta\u000f\u000b\u0002\u001dY\u00069Q.\u0019;1]\u0016<G#\u0002\u001e\u0002\u0016\u0005]\u0001BBA\u0005;\u0001\u0007!\b\u0003\u0004\u0002\u000eu\u0001\rA\u000f\u0015\u0003;1\fA!\\1ucQ\u0019!(a\b\t\r\u00055a\u00041\u0001;Q\tqB.A\u0004nCR\u001c\u0004o\\:\u0015\u000bi\n9#!\u000b\t\r\u0005%q\u00041\u0001;\u0011\u0019\tia\ba\u0001u!\u0012q\u0004\\\u0001\u000be\u0006tGm\\7TK\u0016$G#A3\u0002\u0011\u0019\u0014x.\\*fK\u0012$2\u0001QA\u001b\u0011\u0019\t9$\ta\u0001K\u0006!1/Z3e\u0003%1'o\\7BeJ\f\u0017\u0010F\u0002A\u0003{Aa!a\u0010#\u0001\u0004!\u0014aA1se\u0006IaM]8n\u0005f$Xm\u001d\u000b\u0004\u0001\u0006\u0015\u0003\"B-$\u0001\u0004\u0011\u0016\u0001\u00034s_6$\u0016.\\3\u0015\u0007\u0001\u000bY\u0005C\u0005\u0002N\u0011\u0002\n\u00111\u0001\u0002P\u0005!A/[7f!\r)\u0014\u0011K\u0005\u0004\u0003'2$\u0001\u0002'p]\u001e\f!C\u001a:p[RKW.\u001a\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\f\u0016\u0005\u0003\u001f\nYf\u000b\u0002\u0002^A!\u0011qLA5\u001b\t\t\tG\u0003\u0003\u0002d\u0005\u0015\u0014!C;oG\",7m[3e\u0015\r\t9GN\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA6\u0003C\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0001"
)
public final class Well19937c extends IntBasedGenerator {
   private final int[] state;
   private int i;

   public static long fromTime$default$1() {
      return Well19937c$.MODULE$.fromTime$default$1();
   }

   public static Well19937c fromTime(final long time) {
      return Well19937c$.MODULE$.fromTime(time);
   }

   public static Well19937c fromBytes(final byte[] bytes) {
      return Well19937c$.MODULE$.fromBytes(bytes);
   }

   public static Well19937c fromArray(final int[] arr) {
      return Well19937c$.MODULE$.fromArray(arr);
   }

   public static Well19937c fromSeed(final Tuple2 seed) {
      return Well19937c$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return Well19937c$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Well19937c$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Well19937c$.MODULE$.apply();
   }

   private int i() {
      return this.i;
   }

   private void i_$eq(final int x$1) {
      this.i = x$1;
   }

   public Well19937c copyInit() {
      return new Well19937c((int[])this.state.clone(), this.i());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[Well19937c$.MODULE$.spire$random$rng$Well19937c$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < Well19937c$.MODULE$.spire$random$rng$Well19937c$$R(); ++index$macro$1) {
         bb.putInt(this.state[index$macro$1]);
      }

      bb.putInt(this.i());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < Well19937c$.MODULE$.spire$random$rng$Well19937c$$BYTES() ? Arrays.copyOf(bytes, Well19937c$.MODULE$.spire$random$rng$Well19937c$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < Well19937c$.MODULE$.spire$random$rng$Well19937c$$R(); ++index$macro$1) {
         this.state[index$macro$1] = bb.getInt();
      }

      this.i_$eq(bb.getInt());
   }

   public int nextInt() {
      int z0 = this.state[Well19937acIndexCache$.MODULE$.vrm1()[this.i()]] & Well19937c$.MODULE$.spire$random$rng$Well19937c$$LowerMask() | this.state[Well19937acIndexCache$.MODULE$.vrm2()[this.i()]] & Well19937c$.MODULE$.spire$random$rng$Well19937c$$UpperMask();
      int z1 = Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat0neg(-25, this.state[this.i()]) ^ Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat0pos(27, this.state[Well19937acIndexCache$.MODULE$.vm1()[this.i()]]);
      int z2 = Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat3pos(9, this.state[Well19937acIndexCache$.MODULE$.vm2()[this.i()]]) ^ Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat0pos(1, this.state[Well19937acIndexCache$.MODULE$.vm3()[this.i()]]);
      this.state[this.i()] = z1 ^ z2;
      this.state[Well19937acIndexCache$.MODULE$.vrm1()[this.i()]] = Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat1(z0) ^ Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat0neg(-9, z1) ^ Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat0neg(-21, z2) ^ Well19937c$.MODULE$.spire$random$rng$Well19937c$$mat0pos(21, this.state[this.i()]);
      this.i_$eq(Well19937acIndexCache$.MODULE$.vrm1()[this.i()]);
      int t0 = this.state[this.i()];
      int t1 = t0 ^ t0 << 7 & Well19937c$.MODULE$.spire$random$rng$Well19937c$$TemperB();
      int t2 = t1 ^ t1 << 15 & Well19937c$.MODULE$.spire$random$rng$Well19937c$$TemperC();
      return t2;
   }

   public Well19937c(final int[] state, final int i0) {
      this.state = state;
      this.i = i0;
   }
}
