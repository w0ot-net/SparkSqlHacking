package spire.syntax;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015b\u0001B\r\u001b\u0005}A\u0001B\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Q!)A\u0006\u0001C\u0001[!)\u0011\u0007\u0001C\u0001e!)q\f\u0001C\u0001A\")!\u000e\u0001C\u0001W\")Q\u000f\u0001C\u0001m\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003[\u0001A\u0011AA\u0018\u0011%\t\u0019\u0005AA\u0001\n\u0003\n)\u0005C\u0005\u0002H\u0001\t\t\u0011\"\u0011\u0002J\u001dI\u0011q\n\u000e\u0002\u0002#\u0005\u0011\u0011\u000b\u0004\t3i\t\t\u0011#\u0001\u0002T!1AF\u0004C\u0001\u00037Bq!!\u0018\u000f\t\u000b\ty\u0006C\u0004\u0002z9!)!a\u001f\t\u000f\u0005Me\u0002\"\u0002\u0002\u0016\"9\u0011Q\u0016\b\u0005\u0006\u0005=\u0006bBAd\u001d\u0011\u0015\u0011\u0011\u001a\u0005\b\u0003CtAQAAr\u0011\u001d\tYP\u0004C\u0003\u0003{D\u0011B!\u0006\u000f\u0003\u0003%)Aa\u0006\t\u0013\tma\"!A\u0005\u0006\tu!A\u0005'ji\u0016\u0014\u0018\r\\%oi>\u0013H-\u001a:PaNT!a\u0007\u000f\u0002\rMLh\u000e^1y\u0015\u0005i\u0012!B:qSJ,7\u0001A\n\u0003\u0001\u0001\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z-\u0006d\u0017a\u00017igV\t\u0001\u0006\u0005\u0002\"S%\u0011!F\t\u0002\u0004\u0013:$\u0018\u0001\u00027ig\u0002\na\u0001P5oSRtDC\u0001\u00181!\ty\u0003!D\u0001\u001b\u0011\u001513\u00011\u0001)\u0003\u0015!C.Z:t+\t\u0019D\n\u0006\u00025;R\u0019Q\u0007O+\u0011\u0005\u00052\u0014BA\u001c#\u0005\u001d\u0011un\u001c7fC:DQ!\u000f\u0003A\u0004i\n!!\u001a<\u0011\u0007m:%J\u0004\u0002=\t:\u0011QH\u0011\b\u0003}\u0005k\u0011a\u0010\u0006\u0003\u0001z\ta\u0001\u0010:p_Rt\u0014\"A\u000f\n\u0005\rc\u0012aB1mO\u0016\u0014'/Y\u0005\u0003\u000b\u001a\u000bq\u0001]1dW\u0006<WM\u0003\u0002D9%\u0011\u0001*\u0013\u0002\u0006\u001fJ$WM\u001d\u0006\u0003\u000b\u001a\u0003\"a\u0013'\r\u0001\u0011)Q\n\u0002b\u0001\u001d\n\t\u0011)\u0005\u0002P%B\u0011\u0011\u0005U\u0005\u0003#\n\u0012qAT8uQ&tw\r\u0005\u0002\"'&\u0011AK\t\u0002\u0004\u0003:L\b\"\u0002,\u0005\u0001\b9\u0016!A2\u0011\u0007a[&*D\u0001Z\u0015\tQF$\u0001\u0003nCRD\u0017B\u0001/Z\u00055\u0019uN\u001c<feR\f'\r\\3U_\")a\f\u0002a\u0001\u0015\u0006\u0019!\u000f[:\u0002\u0011\u0011bWm]:%KF,\"!\u00194\u0015\u0005\tLGcA\u001bdO\")\u0011(\u0002a\u0002IB\u00191hR3\u0011\u0005-3G!B'\u0006\u0005\u0004q\u0005\"\u0002,\u0006\u0001\bA\u0007c\u0001-\\K\")a,\u0002a\u0001K\u0006AAe\u001a:fCR,'/\u0006\u0002mcR\u0011Q\u000e\u001e\u000b\u0004k9\u0014\b\"B\u001d\u0007\u0001\by\u0007cA\u001eHaB\u00111*\u001d\u0003\u0006\u001b\u001a\u0011\rA\u0014\u0005\u0006-\u001a\u0001\u001da\u001d\t\u00041n\u0003\b\"\u00020\u0007\u0001\u0004\u0001\u0018a\u0003\u0013he\u0016\fG/\u001a:%KF,\"a\u001e?\u0015\u0005a|HcA\u001bz{\")\u0011h\u0002a\u0002uB\u00191hR>\u0011\u0005-cH!B'\b\u0005\u0004q\u0005\"\u0002,\b\u0001\bq\bc\u0001-\\w\")al\u0002a\u0001w\u0006\u00191-\u001c9\u0016\t\u0005\u0015\u0011q\u0002\u000b\u0005\u0003\u000f\t)\u0002F\u0003)\u0003\u0013\t\t\u0002\u0003\u0004:\u0011\u0001\u000f\u00111\u0002\t\u0005w\u001d\u000bi\u0001E\u0002L\u0003\u001f!Q!\u0014\u0005C\u00029CaA\u0016\u0005A\u0004\u0005M\u0001\u0003\u0002-\\\u0003\u001bAaA\u0018\u0005A\u0002\u00055\u0011aA7j]V!\u00111DA\u0011)\u0011\ti\"a\u000b\u0015\r\u0005}\u00111EA\u0014!\rY\u0015\u0011\u0005\u0003\u0006\u001b&\u0011\rA\u0014\u0005\u0007s%\u0001\u001d!!\n\u0011\tm:\u0015q\u0004\u0005\u0007-&\u0001\u001d!!\u000b\u0011\ta[\u0016q\u0004\u0005\u0007=&\u0001\r!a\b\u0002\u00075\f\u00070\u0006\u0003\u00022\u0005]B\u0003BA\u001a\u0003\u0003\"b!!\u000e\u0002:\u0005u\u0002cA&\u00028\u0011)QJ\u0003b\u0001\u001d\"1\u0011H\u0003a\u0002\u0003w\u0001BaO$\u00026!1aK\u0003a\u0002\u0003\u007f\u0001B\u0001W.\u00026!1aL\u0003a\u0001\u0003k\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002Q\u00051Q-];bYN$2!NA&\u0011!\ti\u0005DA\u0001\u0002\u0004\u0011\u0016a\u0001=%c\u0005\u0011B*\u001b;fe\u0006d\u0017J\u001c;Pe\u0012,'o\u00149t!\tycbE\u0002\u000f\u0003+\u00022!IA,\u0013\r\tIF\t\u0002\u0007\u0003:L(+\u001a4\u0015\u0005\u0005E\u0013a\u0004\u0013mKN\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u0005\u0005\u0014Q\u000e\u000b\u0005\u0003G\n)\b\u0006\u0003\u0002f\u0005MD#B\u001b\u0002h\u0005=\u0004BB\u001d\u0011\u0001\b\tI\u0007\u0005\u0003<\u000f\u0006-\u0004cA&\u0002n\u0011)Q\n\u0005b\u0001\u001d\"1a\u000b\u0005a\u0002\u0003c\u0002B\u0001W.\u0002l!1a\f\u0005a\u0001\u0003WBa!a\u001e\u0011\u0001\u0004q\u0013!\u0002\u0013uQ&\u001c\u0018A\u0005\u0013mKN\u001cH%Z9%Kb$XM\\:j_:,B!! \u0002\nR!\u0011qPAI)\u0011\t\t)a$\u0015\u000bU\n\u0019)a#\t\re\n\u00029AAC!\u0011Yt)a\"\u0011\u0007-\u000bI\tB\u0003N#\t\u0007a\n\u0003\u0004W#\u0001\u000f\u0011Q\u0012\t\u00051n\u000b9\t\u0003\u0004_#\u0001\u0007\u0011q\u0011\u0005\u0007\u0003o\n\u0002\u0019\u0001\u0018\u0002%\u0011:'/Z1uKJ$S\r\u001f;f]NLwN\\\u000b\u0005\u0003/\u000b\u0019\u000b\u0006\u0003\u0002\u001a\u0006-F\u0003BAN\u0003S#R!NAO\u0003KCa!\u000f\nA\u0004\u0005}\u0005\u0003B\u001eH\u0003C\u00032aSAR\t\u0015i%C1\u0001O\u0011\u00191&\u0003q\u0001\u0002(B!\u0001lWAQ\u0011\u0019q&\u00031\u0001\u0002\"\"1\u0011q\u000f\nA\u00029\nQ\u0003J4sK\u0006$XM\u001d\u0013fc\u0012*\u0007\u0010^3og&|g.\u0006\u0003\u00022\u0006uF\u0003BAZ\u0003\u000b$B!!.\u0002DR)Q'a.\u0002@\"1\u0011h\u0005a\u0002\u0003s\u0003BaO$\u0002<B\u00191*!0\u0005\u000b5\u001b\"\u0019\u0001(\t\rY\u001b\u00029AAa!\u0011A6,a/\t\ry\u001b\u0002\u0019AA^\u0011\u0019\t9h\u0005a\u0001]\u0005i1-\u001c9%Kb$XM\\:j_:,B!a3\u0002XR!\u0011QZAp)\u0011\ty-!8\u0015\u000b!\n\t.!7\t\re\"\u00029AAj!\u0011Yt)!6\u0011\u0007-\u000b9\u000eB\u0003N)\t\u0007a\n\u0003\u0004W)\u0001\u000f\u00111\u001c\t\u00051n\u000b)\u000e\u0003\u0004_)\u0001\u0007\u0011Q\u001b\u0005\u0007\u0003o\"\u0002\u0019\u0001\u0018\u0002\u001b5Lg\u000eJ3yi\u0016t7/[8o+\u0011\t)/!<\u0015\t\u0005\u001d\u0018\u0011 \u000b\u0005\u0003S\f9\u0010\u0006\u0004\u0002l\u0006=\u00181\u001f\t\u0004\u0017\u00065H!B'\u0016\u0005\u0004q\u0005BB\u001d\u0016\u0001\b\t\t\u0010\u0005\u0003<\u000f\u0006-\bB\u0002,\u0016\u0001\b\t)\u0010\u0005\u0003Y7\u0006-\bB\u00020\u0016\u0001\u0004\tY\u000f\u0003\u0004\u0002xU\u0001\rAL\u0001\u000e[\u0006DH%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u0005}(q\u0001\u000b\u0005\u0005\u0003\u0011\u0019\u0002\u0006\u0003\u0003\u0004\tEAC\u0002B\u0003\u0005\u0013\u0011i\u0001E\u0002L\u0005\u000f!Q!\u0014\fC\u00029Ca!\u000f\fA\u0004\t-\u0001\u0003B\u001eH\u0005\u000bAaA\u0016\fA\u0004\t=\u0001\u0003\u0002-\\\u0005\u000bAaA\u0018\fA\u0002\t\u0015\u0001BBA<-\u0001\u0007a&\u0001\niCND7i\u001c3fI\u0015DH/\u001a8tS>tG\u0003BA#\u00053Aa!a\u001e\u0018\u0001\u0004q\u0013\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\u0011\u0011yBa\t\u0015\u0007U\u0012\t\u0003\u0003\u0005\u0002Na\t\t\u00111\u0001S\u0011\u0019\t9\b\u0007a\u0001]\u0001"
)
public final class LiteralIntOrderOps {
   private final int lhs;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return LiteralIntOrderOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return LiteralIntOrderOps$.MODULE$.hashCode$extension($this);
   }

   public static Object max$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.max$extension($this, rhs, ev, c);
   }

   public static Object min$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.min$extension($this, rhs, ev, c);
   }

   public static int cmp$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.cmp$extension($this, rhs, ev, c);
   }

   public static boolean $greater$eq$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$greater$eq$extension($this, rhs, ev, c);
   }

   public static boolean $greater$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$greater$extension($this, rhs, ev, c);
   }

   public static boolean $less$eq$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$less$eq$extension($this, rhs, ev, c);
   }

   public static boolean $less$extension(final int $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$less$extension($this, rhs, ev, c);
   }

   public int lhs() {
      return this.lhs;
   }

   public boolean $less(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$less$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $less$eq(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$less$eq$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $greater(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$greater$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $greater$eq(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.$greater$eq$extension(this.lhs(), rhs, ev, c);
   }

   public int cmp(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.cmp$extension(this.lhs(), rhs, ev, c);
   }

   public Object min(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.min$extension(this.lhs(), rhs, ev, c);
   }

   public Object max(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralIntOrderOps$.MODULE$.max$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralIntOrderOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralIntOrderOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralIntOrderOps(final int lhs) {
      this.lhs = lhs;
   }
}
