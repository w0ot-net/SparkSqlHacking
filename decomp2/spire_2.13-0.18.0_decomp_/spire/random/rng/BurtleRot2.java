package spire.random.rng;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3A\u0001D\u0007\u0003)!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\u001b\u0011!\t\u0003A!A!\u0002\u0013Q\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u000e\t\u000b\r\u0002A\u0011\u0001\u0013\t\u000b)\u0002A\u0011C\u0016\t\u000b=\u0002A\u0011\u0001\u0019\b\u000bEj\u0001\u0012\u0001\u001a\u0007\u000b1i\u0001\u0012A\u001a\t\u000b\rJA\u0011A\u001c\t\u000baJA\u0011A\u001d\u0003\u0015\t+(\u000f\u001e7f%>$(G\u0003\u0002\u000f\u001f\u0005\u0019!O\\4\u000b\u0005A\t\u0012A\u0002:b]\u0012|WNC\u0001\u0013\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001A\u000b\u0011\u0005Y9R\"A\u0007\n\u0005ai!a\u0003\"veRdWMU8ugI\n!aX1\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\u0007%sG/\u0001\u0002`E\u0006\u0011qlY\u0001\u0003?\u0012\fa\u0001P5oSRtD#B\u0013'O!J\u0003C\u0001\f\u0001\u0011\u0015IR\u00011\u0001\u001b\u0011\u0015\u0001S\u00011\u0001\u001b\u0011\u0015\tS\u00011\u0001\u001b\u0011\u0015\u0011S\u00011\u0001\u001b\u0003\u001d\tGM^1oG\u0016,\u0012\u0001\f\t\u000375J!A\f\u000f\u0003\tUs\u0017\u000e^\u0001\tG>\u0004\u00180\u00138jiV\tQ%\u0001\u0006CkJ$H.\u001a*piJ\u0002\"AF\u0005\u0014\u0005%!\u0004c\u0001\f6K%\u0011a'\u0004\u0002\u0010\u0005V\u0014H\u000f\\3D_6\u0004\u0018M\\5p]R\t!'\u0001\u0004de\u0016\fG/\u001a\u000b\u0006Kibd\b\u0011\u0005\u0006w-\u0001\rAG\u0001\u0002C\")Qh\u0003a\u00015\u0005\t!\rC\u0003@\u0017\u0001\u0007!$A\u0001d\u0011\u0015\t5\u00021\u0001\u001b\u0003\u0005!\u0007"
)
public final class BurtleRot2 extends BurtleRot32 {
   public static BurtleRot2 create(final int a, final int b, final int c, final int d) {
      return BurtleRot2$.MODULE$.create(a, b, c, d);
   }

   public static long fromTime$default$1() {
      return BurtleRot2$.MODULE$.fromTime$default$1();
   }

   public static BurtleRot32 fromTime(final long time) {
      return BurtleRot2$.MODULE$.fromTime(time);
   }

   public static BurtleRot32 fromSeed(final int[] ints) {
      return BurtleRot2$.MODULE$.fromSeed(ints);
   }

   public static BurtleRot32 fromBytes(final byte[] bytes) {
      return BurtleRot2$.MODULE$.fromBytes(bytes);
   }

   public static int[] randomSeed() {
      return BurtleRot2$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return BurtleRot2$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return BurtleRot2$.MODULE$.apply();
   }

   public void advance() {
      int e = this.a() - Integer.rotateLeft(this.b(), 27);
      this.a_$eq(this.b() ^ Integer.rotateLeft(this.c(), 17));
      this.b_$eq(this.c() + this.d());
      this.c_$eq(this.d() + e);
      this.d_$eq(e + this.a());
   }

   public BurtleRot2 copyInit() {
      return new BurtleRot2(this.a(), this.b(), this.c(), this.d());
   }

   public BurtleRot2(final int _a, final int _b, final int _c, final int _d) {
      super(_a, _b, _c, _d);
   }
}
