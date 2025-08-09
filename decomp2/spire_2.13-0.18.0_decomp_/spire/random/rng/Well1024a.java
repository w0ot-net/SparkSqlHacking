package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c\u0001\u0002\u0013&\u00051B\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\tw\u0001\u0011\t\u0011)A\u0005q!1A\b\u0001C\tOuBAB\u0011\u0001\u0005\u0002\u0003\u0015\t\u00111A\u0005\n\rCq\u0001\u0012\u0001A\u0002\u0013%Q\tC\u0005L\u0001\t\u0005\t\u0011)Q\u0005q!)A\n\u0001C\u0001\u001b\")a\n\u0001C\u0001\u001f\")A\u000b\u0001C\u0001+\")\u0001\f\u0001C\u00013\u001e)!,\nE\u00017\u001a)A%\nE\u00019\")A\b\u0004C\u0001M\"9q\r\u0004b\u0001\n\u001b\u0019\u0005B\u00025\rA\u00035\u0001\bC\u0004n\u0019\t\u0007IQB\"\t\r9d\u0001\u0015!\u00049\u00111\u0001H\u0002\"A\u0001\u0006\u0003\u0015\r\u0011\"\u0004D\u0011%\tHB!A\u0001B\u00035\u0001\bC\u0004t\u0019\t\u0007IQB\"\t\rQd\u0001\u0015!\u00049\u0011\u001d1HB1A\u0005\u000e\rCaa\u001e\u0007!\u0002\u001bA\u0004bB=\r\u0005\u0004%ia\u0011\u0005\u0007u2\u0001\u000bQ\u0002\u001d\t\u000fqd!\u0019!C\u0007\u0007\"1Q\u0010\u0004Q\u0001\u000eaBaa \u0007\u0005\u000e\u0005\u0005\u0001bBA\u0007\u0019\u00115\u0011q\u0002\u0005\b\u0003/aA\u0011AA\r\u0011\u001d\tY\u0002\u0004C\u0001\u0003;Aq!a\t\r\t\u0003\t)\u0003C\u0004\u0002,1!\t!!\f\t\u000f\u0005EB\u0002\"\u0001\u00024!I\u0011q\b\u0007\u0012\u0002\u0013\u0005\u0011\u0011\t\u0002\n/\u0016dG.\r\u00193i\u0005T!AJ\u0014\u0002\u0007ItwM\u0003\u0002)S\u00051!/\u00198e_6T\u0011AK\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001Q\u0006\u0005\u0002/_5\tq%\u0003\u00021O\t\t\u0012J\u001c;CCN,GmR3oKJ\fGo\u001c:\u0002\u000bM$\u0018\r^3\u0011\u0007M2\u0004(D\u00015\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0005\u0015\t%O]1z!\t\u0019\u0014(\u0003\u0002;i\t\u0019\u0011J\u001c;\u0002\u0005%\u0004\u0014A\u0002\u001fj]&$h\bF\u0002?\u0001\u0006\u0003\"a\u0010\u0001\u000e\u0003\u0015BQ!M\u0002A\u0002IBQaO\u0002A\u0002a\nQd\u001d9je\u0016$#/\u00198e_6$#O\\4%/\u0016dG.\r\u00193i\u0005$C%[\u000b\u0002q\u0005)\u0011n\u0018\u0013fcR\u0011a)\u0013\t\u0003g\u001dK!\u0001\u0013\u001b\u0003\tUs\u0017\u000e\u001e\u0005\b\u0015\u0016\t\t\u00111\u00019\u0003\rAH%M\u0001\u001fgBL'/\u001a\u0013sC:$w.\u001c\u0013s]\u001e$s+\u001a7mcA\u0012D'\u0019\u0013%S\u0002\n\u0001bY8qs&s\u0017\u000e^\u000b\u0002}\u0005aq-\u001a;TK\u0016$')\u001f;fgV\t\u0001\u000bE\u00024mE\u0003\"a\r*\n\u0005M#$\u0001\u0002\"zi\u0016\fAb]3u'\u0016,GMQ=uKN$\"A\u0012,\t\u000b]K\u0001\u0019\u0001)\u0002\u000b\tLH/Z:\u0002\u000f9,\u0007\u0010^%oiR\t\u0001(A\u0005XK2d\u0017\u0007\r\u001a5CB\u0011q\bD\n\u0004\u0019u\u0003\u0007CA\u001a_\u0013\tyFG\u0001\u0004B]f\u0014VM\u001a\t\u0005]\u0005t4-\u0003\u0002cO\t\u0011r)\u001a8fe\u0006$xN]\"p[B\fg.[8o!\u0011\u0019DM\r\u001d\n\u0005\u0015$$A\u0002+va2,'\u0007F\u0001\\\u0003\u0005Y\u0015AA&!Q\ty!\u000e\u0005\u00024W&\u0011A\u000e\u000e\u0002\u0007S:d\u0017N\\3\u0002\u0003I\u000b!A\u0015\u0011)\u0005EQ\u0017aH:qSJ,GE]1oI>lGE\u001d8hI]+G\u000e\\\u00191eQ\nG\u0005\n*`c\u0005\u00013\u000f]5sK\u0012\u0012\u0018M\u001c3p[\u0012\u0012hn\u001a\u0013XK2d\u0017\u0007\r\u001a5C\u0012\"#kX\u0019!Q\t\u0019\".A\u0003C3R+5+\u0001\u0004C3R+5\u000b\t\u0015\u0003+)\f!!T\u0019\u0002\u00075\u000b\u0004\u0005\u000b\u0002\u0018U\u0006\u0011QJM\u0001\u0004\u001bJ\u0002\u0003FA\rk\u0003\ti5'A\u0002Ng\u0001B#a\u00076\u0002\u000f5\fG\u000f\r9pgR)\u0001(a\u0001\u0002\b!1\u0011Q\u0001\u000fA\u0002a\n\u0011\u0001\u001e\u0005\u0007\u0003\u0013a\u0002\u0019\u0001\u001d\u0002\u0003YD#\u0001\b6\u0002\u000f5\fG\u000f\r8fOR)\u0001(!\u0005\u0002\u0014!1\u0011QA\u000fA\u0002aBa!!\u0003\u001e\u0001\u0004A\u0004FA\u000fk\u0003)\u0011\u0018M\u001c3p[N+W\r\u001a\u000b\u0002G\u0006AaM]8n'\u0016,G\rF\u0002?\u0003?Aa!!\t \u0001\u0004\u0019\u0017\u0001B:fK\u0012\f\u0011B\u001a:p[\u0006\u0013(/Y=\u0015\u0007y\n9\u0003\u0003\u0004\u0002*\u0001\u0002\rAM\u0001\u0004CJ\u0014\u0018!\u00034s_6\u0014\u0015\u0010^3t)\rq\u0014q\u0006\u0005\u0006/\u0006\u0002\r\u0001U\u0001\tMJ|W\u000eV5nKR\u0019a(!\u000e\t\u0013\u0005]\"\u0005%AA\u0002\u0005e\u0012\u0001\u0002;j[\u0016\u00042aMA\u001e\u0013\r\ti\u0004\u000e\u0002\u0005\u0019>tw-\u0001\nge>lG+[7fI\u0011,g-Y;mi\u0012\nTCAA\"U\u0011\tI$!\u0012,\u0005\u0005\u001d\u0003\u0003BA%\u0003'j!!a\u0013\u000b\t\u00055\u0013qJ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u00155\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003+\nYEA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public final class Well1024a extends IntBasedGenerator {
   private final int[] state;
   private int spire$random$rng$Well1024a$$i;

   public static long fromTime$default$1() {
      return Well1024a$.MODULE$.fromTime$default$1();
   }

   public static Well1024a fromTime(final long time) {
      return Well1024a$.MODULE$.fromTime(time);
   }

   public static Well1024a fromBytes(final byte[] bytes) {
      return Well1024a$.MODULE$.fromBytes(bytes);
   }

   public static Well1024a fromArray(final int[] arr) {
      return Well1024a$.MODULE$.fromArray(arr);
   }

   public static Well1024a fromSeed(final Tuple2 seed) {
      return Well1024a$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return Well1024a$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Well1024a$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Well1024a$.MODULE$.apply();
   }

   public int spire$random$rng$Well1024a$$i() {
      return this.spire$random$rng$Well1024a$$i;
   }

   private void i_$eq(final int x$1) {
      this.spire$random$rng$Well1024a$$i = x$1;
   }

   public Well1024a copyInit() {
      return new Well1024a((int[])this.state.clone(), this.spire$random$rng$Well1024a$$i());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[Well1024a$.MODULE$.spire$random$rng$Well1024a$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < Well1024a$.MODULE$.spire$random$rng$Well1024a$$R(); ++index$macro$1) {
         bb.putInt(this.state[index$macro$1]);
      }

      bb.putInt(this.spire$random$rng$Well1024a$$i());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < Well1024a$.MODULE$.spire$random$rng$Well1024a$$BYTES() ? Arrays.copyOf(bytes, Well1024a$.MODULE$.spire$random$rng$Well1024a$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < Well1024a$.MODULE$.spire$random$rng$Well1024a$$R(); ++index$macro$1) {
         this.state[index$macro$1] = bb.getInt();
      }

      this.i_$eq(bb.getInt());
   }

   public int nextInt() {
      int z0 = this.state[this.map$1(Well1024a$.MODULE$.spire$random$rng$Well1024a$$R_1())];
      int z1 = this.state[this.spire$random$rng$Well1024a$$i()] ^ Well1024a$.MODULE$.spire$random$rng$Well1024a$$mat0pos(8, this.state[this.map$1(Well1024a$.MODULE$.spire$random$rng$Well1024a$$M1())]);
      int z2 = Well1024a$.MODULE$.spire$random$rng$Well1024a$$mat0neg(-19, this.state[this.map$1(Well1024a$.MODULE$.spire$random$rng$Well1024a$$M2())]) ^ Well1024a$.MODULE$.spire$random$rng$Well1024a$$mat0neg(-14, this.state[this.map$1(Well1024a$.MODULE$.spire$random$rng$Well1024a$$M3())]);
      this.state[this.spire$random$rng$Well1024a$$i()] = z1 ^ z2;
      this.state[this.map$1(Well1024a$.MODULE$.spire$random$rng$Well1024a$$R_1())] = Well1024a$.MODULE$.spire$random$rng$Well1024a$$mat0neg(-11, z0) ^ Well1024a$.MODULE$.spire$random$rng$Well1024a$$mat0neg(-7, z1) ^ Well1024a$.MODULE$.spire$random$rng$Well1024a$$mat0neg(-13, z2);
      this.i_$eq(this.map$1(Well1024a$.MODULE$.spire$random$rng$Well1024a$$R_1()));
      return this.state[this.spire$random$rng$Well1024a$$i()];
   }

   private final int map$1(final int r) {
      return this.spire$random$rng$Well1024a$$i() + r & Well1024a$.MODULE$.spire$random$rng$Well1024a$$R_1();
   }

   public Well1024a(final int[] state, final int i0) {
      this.state = state;
      this.spire$random$rng$Well1024a$$i = i0;
   }
}
