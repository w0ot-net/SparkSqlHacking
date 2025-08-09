package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d\u0001\u0002\u0013&\u00051B\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\tw\u0001\u0011\t\u0011)A\u0005q!1A\b\u0001C\tOuBAB\u0011\u0001\u0005\u0002\u0003\u0015\t\u00111A\u0005\n\rCq\u0001\u0012\u0001A\u0002\u0013%Q\tC\u0005L\u0001\t\u0005\t\u0011)Q\u0005q!)A\n\u0001C\u0001\u001b\")a\n\u0001C\u0001\u001f\")A\u000b\u0001C\u0001+\")\u0001\f\u0001C\u00013\u001e)!,\nE\u00017\u001a)A%\nE\u00019\")A\b\u0004C\u0001M\"9q\r\u0004b\u0001\n\u001b\u0019\u0005B\u00025\rA\u00035\u0001\bC\u0004n\u0019\t\u0007IQB\"\t\r9d\u0001\u0015!\u00049\u00111\u0001H\u0002\"A\u0001\u0006\u0003\u0015\r\u0011\"\u0004D\u0011%\tHB!A\u0001B\u00035\u0001\bC\u0004t\u0019\t\u0007IQB\"\t\rQd\u0001\u0015!\u00049\u0011\u001d1HB1A\u0005\u000e\rCaa\u001e\u0007!\u0002\u001bA\u0004bB=\r\u0005\u0004%ia\u0011\u0005\u0007u2\u0001\u000bQ\u0002\u001d\t\u000bqdAQB?\t\u000f\u0005\u001dA\u0002\"\u0004\u0002\n!9\u0011\u0011\u0003\u0007\u0005\u000e\u0005M\u0001bBA\u000e\u0019\u00115\u0011Q\u0004\u0005\b\u0003SaA\u0011AA\u0016\u0011\u001d\ti\u0003\u0004C\u0001\u0003_Aq!!\u000e\r\t\u0003\t9\u0004C\u0004\u0002>1!\t!a\u0010\t\u000f\u0005\rC\u0002\"\u0001\u0002F!I\u0011\u0011\u000b\u0007\u0012\u0002\u0013\u0005\u00111\u000b\u0002\t/\u0016dG.N\u00193C*\u0011aeJ\u0001\u0004e:<'B\u0001\u0015*\u0003\u0019\u0011\u0018M\u001c3p[*\t!&A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001i\u0003C\u0001\u00180\u001b\u00059\u0013B\u0001\u0019(\u0005EIe\u000e\u001e\"bg\u0016$w)\u001a8fe\u0006$xN]\u0001\u0006gR\fG/\u001a\t\u0004gYBT\"\u0001\u001b\u000b\u0003U\nQa]2bY\u0006L!a\u000e\u001b\u0003\u000b\u0005\u0013(/Y=\u0011\u0005MJ\u0014B\u0001\u001e5\u0005\rIe\u000e^\u0001\u0003SB\na\u0001P5oSRtDc\u0001 A\u0003B\u0011q\bA\u0007\u0002K!)\u0011g\u0001a\u0001e!)1h\u0001a\u0001q\u0005a2\u000f]5sK\u0012\u0012\u0018M\u001c3p[\u0012\u0012hn\u001a\u0013XK2dW'\r\u001abI\u0011JW#\u0001\u001d\u0002\u000b%|F%Z9\u0015\u0005\u0019K\u0005CA\u001aH\u0013\tAEG\u0001\u0003V]&$\bb\u0002&\u0006\u0003\u0003\u0005\r\u0001O\u0001\u0004q\u0012\n\u0014!H:qSJ,GE]1oI>lGE\u001d8hI]+G\u000e\\\u001b2e\u0005$C%\u001b\u0011\u0002\u0011\r|\u0007/_%oSR,\u0012AP\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002!B\u00191GN)\u0011\u0005M\u0012\u0016BA*5\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u0005\u00193\u0006\"B,\n\u0001\u0004\u0001\u0016!\u00022zi\u0016\u001c\u0018a\u00028fqRLe\u000e\u001e\u000b\u0002q\u0005Aq+\u001a7mkE\u0012\u0014\r\u0005\u0002@\u0019M\u0019A\"\u00181\u0011\u0005Mr\u0016BA05\u0005\u0019\te.\u001f*fMB!a&\u0019 d\u0013\t\u0011wE\u0001\nHK:,'/\u0019;pe\u000e{W\u000e]1oS>t\u0007\u0003B\u001aeeaJ!!\u001a\u001b\u0003\rQ+\b\u000f\\33)\u0005Y\u0016!A&\u0002\u0005-\u0003\u0003FA\bk!\t\u00194.\u0003\u0002mi\t1\u0011N\u001c7j]\u0016\f\u0011AU\u0001\u0003%\u0002B#!\u00056\u0002=M\u0004\u0018N]3%e\u0006tGm\\7%e:<GeV3mYV\n$'\u0019\u0013%%~\u000b\u0014aH:qSJ,GE]1oI>lGE\u001d8hI]+G\u000e\\\u001b2e\u0005$CEU02A!\u00121C[\u0001\u0006\u0005f#ViU\u0001\u0007\u0005f#Vi\u0015\u0011)\u0005UQ\u0017AA'2\u0003\ri\u0015\u0007\t\u0015\u0003/)\f!!\u0014\u001a\u0002\u00075\u0013\u0004\u0005\u000b\u0002\u001aU\u00069Q.\u0019;1a>\u001cH\u0003\u0002\u001d\u007f\u0003\u0003AQa \u000eA\u0002a\n\u0011\u0001\u001e\u0005\u0007\u0003\u0007Q\u0002\u0019\u0001\u001d\u0002\u0003YD#A\u00076\u0002\u000f5\fG\u000f\r8fOR)\u0001(a\u0003\u0002\u000e!)qp\u0007a\u0001q!1\u00111A\u000eA\u0002aB#a\u00076\u0002\u000f5\fGo\r8fOR)\u0001(!\u0006\u0002\u0018!)q\u0010\ba\u0001q!1\u00111\u0001\u000fA\u0002aB#\u0001\b6\u0002\u000f5\fG\u000f\u000e8fOR9\u0001(a\b\u0002\"\u0005\u0015\u0002\"B@\u001e\u0001\u0004A\u0004BBA\u0012;\u0001\u0007\u0001(A\u0001c\u0011\u0019\t\u0019!\ba\u0001q!\u0012QD[\u0001\u000be\u0006tGm\\7TK\u0016$G#A2\u0002\u0011\u0019\u0014x.\\*fK\u0012$2APA\u0019\u0011\u0019\t\u0019d\ba\u0001G\u0006!1/Z3e\u0003%1'o\\7BeJ\f\u0017\u0010F\u0002?\u0003sAa!a\u000f!\u0001\u0004\u0011\u0014aA1se\u0006IaM]8n\u0005f$Xm\u001d\u000b\u0004}\u0005\u0005\u0003\"B,\"\u0001\u0004\u0001\u0016\u0001\u00034s_6$\u0016.\\3\u0015\u0007y\n9\u0005C\u0005\u0002J\t\u0002\n\u00111\u0001\u0002L\u0005!A/[7f!\r\u0019\u0014QJ\u0005\u0004\u0003\u001f\"$\u0001\u0002'p]\u001e\f!C\u001a:p[RKW.\u001a\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q\u000b\u0016\u0005\u0003\u0017\n9f\u000b\u0002\u0002ZA!\u00111LA3\u001b\t\tiF\u0003\u0003\u0002`\u0005\u0005\u0014!C;oG\",7m[3e\u0015\r\t\u0019\u0007N\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA4\u0003;\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0001"
)
public final class Well512a extends IntBasedGenerator {
   private final int[] state;
   private int spire$random$rng$Well512a$$i;

   public static long fromTime$default$1() {
      return Well512a$.MODULE$.fromTime$default$1();
   }

   public static Well512a fromTime(final long time) {
      return Well512a$.MODULE$.fromTime(time);
   }

   public static Well512a fromBytes(final byte[] bytes) {
      return Well512a$.MODULE$.fromBytes(bytes);
   }

   public static Well512a fromArray(final int[] arr) {
      return Well512a$.MODULE$.fromArray(arr);
   }

   public static Well512a fromSeed(final Tuple2 seed) {
      return Well512a$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return Well512a$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Well512a$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Well512a$.MODULE$.apply();
   }

   public int spire$random$rng$Well512a$$i() {
      return this.spire$random$rng$Well512a$$i;
   }

   private void i_$eq(final int x$1) {
      this.spire$random$rng$Well512a$$i = x$1;
   }

   public Well512a copyInit() {
      return new Well512a((int[])this.state.clone(), this.spire$random$rng$Well512a$$i());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[Well512a$.MODULE$.spire$random$rng$Well512a$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < Well512a$.MODULE$.spire$random$rng$Well512a$$R(); ++index$macro$1) {
         bb.putInt(this.state[index$macro$1]);
      }

      bb.putInt(this.spire$random$rng$Well512a$$i());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < Well512a$.MODULE$.spire$random$rng$Well512a$$BYTES() ? Arrays.copyOf(bytes, Well512a$.MODULE$.spire$random$rng$Well512a$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < Well512a$.MODULE$.spire$random$rng$Well512a$$R(); ++index$macro$1) {
         this.state[index$macro$1] = bb.getInt();
      }

      this.i_$eq(bb.getInt());
   }

   public int nextInt() {
      int z0 = this.state[this.map$1(Well512a$.MODULE$.spire$random$rng$Well512a$$R_1())];
      int z1 = Well512a$.MODULE$.spire$random$rng$Well512a$$mat0neg(-16, this.state[this.spire$random$rng$Well512a$$i()]) ^ Well512a$.MODULE$.spire$random$rng$Well512a$$mat0neg(-15, this.state[this.map$1(Well512a$.MODULE$.spire$random$rng$Well512a$$M1())]);
      int z2 = Well512a$.MODULE$.spire$random$rng$Well512a$$mat0pos(11, this.state[this.map$1(Well512a$.MODULE$.spire$random$rng$Well512a$$M2())]);
      this.state[this.spire$random$rng$Well512a$$i()] = z1 ^ z2;
      this.state[this.map$1(Well512a$.MODULE$.spire$random$rng$Well512a$$R_1())] = Well512a$.MODULE$.spire$random$rng$Well512a$$mat0neg(-2, z0) ^ Well512a$.MODULE$.spire$random$rng$Well512a$$mat0neg(-18, z1) ^ Well512a$.MODULE$.spire$random$rng$Well512a$$mat3neg(-28, z2) ^ Well512a$.MODULE$.spire$random$rng$Well512a$$mat4neg(-5, -633066204, this.state[this.spire$random$rng$Well512a$$i()]);
      this.i_$eq(this.map$1(Well512a$.MODULE$.spire$random$rng$Well512a$$R_1()));
      return this.state[this.spire$random$rng$Well512a$$i()];
   }

   private final int map$1(final int r) {
      return this.spire$random$rng$Well512a$$i() + r & Well512a$.MODULE$.spire$random$rng$Well512a$$R_1();
   }

   public Well512a(final int[] state, final int i0) {
      this.state = state;
      this.spire$random$rng$Well512a$$i = i0;
   }
}
