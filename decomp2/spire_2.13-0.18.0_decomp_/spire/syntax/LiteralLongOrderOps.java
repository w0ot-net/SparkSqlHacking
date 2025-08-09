package spire.syntax;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005\t-b\u0001B\r\u001b\u0005}A\u0001B\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Q!)A\u0006\u0001C\u0001[!)\u0011\u0007\u0001C\u0001e!)q\f\u0001C\u0001A\")!\u000e\u0001C\u0001W\")Q\u000f\u0001C\u0001m\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\u000f\u0001\u0011\u0005\u0011q\u0004\u0005\b\u0003g\u0001A\u0011AA\u001b\u0011%\tI\u0005AA\u0001\n\u0003\nY\u0005C\u0005\u0002N\u0001\t\t\u0011\"\u0011\u0002P\u001dI\u0011Q\u000b\u000e\u0002\u0002#\u0005\u0011q\u000b\u0004\t3i\t\t\u0011#\u0001\u0002Z!1AF\u0004C\u0001\u0003CBq!a\u0019\u000f\t\u000b\t)\u0007C\u0004\u0002\u00009!)!!!\t\u000f\u0005ee\u0002\"\u0002\u0002\u001c\"9\u00111\u0017\b\u0005\u0006\u0005U\u0006bBAg\u001d\u0011\u0015\u0011q\u001a\u0005\b\u0003OtAQAAu\u0011\u001d\u0011\tA\u0004C\u0003\u0005\u0007A\u0011Ba\u0007\u000f\u0003\u0003%)A!\b\t\u0013\t\u0005b\"!A\u0005\u0006\t\r\"a\u0005'ji\u0016\u0014\u0018\r\u001c'p]\u001e|%\u000fZ3s\u001fB\u001c(BA\u000e\u001d\u0003\u0019\u0019\u0018P\u001c;bq*\tQ$A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001\u0001\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osZ\u000bG.A\u0002mQN,\u0012\u0001\u000b\t\u0003C%J!A\u000b\u0012\u0003\t1{gnZ\u0001\u0005Y\"\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003]A\u0002\"a\f\u0001\u000e\u0003iAQAJ\u0002A\u0002!\nQ\u0001\n7fgN,\"a\r'\u0015\u0005QjFcA\u001b9+B\u0011\u0011EN\u0005\u0003o\t\u0012qAQ8pY\u0016\fg\u000eC\u0003:\t\u0001\u000f!(\u0001\u0002fmB\u00191h\u0012&\u000f\u0005q\"eBA\u001fC\u001d\tq\u0014)D\u0001@\u0015\t\u0001e$\u0001\u0004=e>|GOP\u0005\u0002;%\u00111\tH\u0001\bC2<WM\u0019:b\u0013\t)e)A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\rc\u0012B\u0001%J\u0005\u0015y%\u000fZ3s\u0015\t)e\t\u0005\u0002L\u00192\u0001A!B'\u0005\u0005\u0004q%!A!\u0012\u0005=\u0013\u0006CA\u0011Q\u0013\t\t&EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005\u001a\u0016B\u0001+#\u0005\r\te.\u001f\u0005\u0006-\u0012\u0001\u001daV\u0001\u0002GB\u0019\u0001l\u0017&\u000e\u0003eS!A\u0017\u000f\u0002\t5\fG\u000f[\u0005\u00039f\u0013QbQ8om\u0016\u0014H/\u00192mKR{\u0007\"\u00020\u0005\u0001\u0004Q\u0015a\u0001:ig\u0006AA\u0005\\3tg\u0012*\u0017/\u0006\u0002bMR\u0011!-\u001b\u000b\u0004k\r<\u0007\"B\u001d\u0006\u0001\b!\u0007cA\u001eHKB\u00111J\u001a\u0003\u0006\u001b\u0016\u0011\rA\u0014\u0005\u0006-\u0016\u0001\u001d\u0001\u001b\t\u00041n+\u0007\"\u00020\u0006\u0001\u0004)\u0017\u0001\u0003\u0013he\u0016\fG/\u001a:\u0016\u00051\fHCA7u)\r)dN\u001d\u0005\u0006s\u0019\u0001\u001da\u001c\t\u0004w\u001d\u0003\bCA&r\t\u0015ieA1\u0001O\u0011\u00151f\u0001q\u0001t!\rA6\f\u001d\u0005\u0006=\u001a\u0001\r\u0001]\u0001\fI\u001d\u0014X-\u0019;fe\u0012*\u0017/\u0006\u0002xyR\u0011\u0001p \u000b\u0004kel\b\"B\u001d\b\u0001\bQ\bcA\u001eHwB\u00111\n \u0003\u0006\u001b\u001e\u0011\rA\u0014\u0005\u0006-\u001e\u0001\u001dA \t\u00041n[\b\"\u00020\b\u0001\u0004Y\u0018aA2naV!\u0011QAA\u000b)\u0011\t9!a\u0007\u0015\r\u0005%\u0011qBA\f!\r\t\u00131B\u0005\u0004\u0003\u001b\u0011#aA%oi\"1\u0011\b\u0003a\u0002\u0003#\u0001BaO$\u0002\u0014A\u00191*!\u0006\u0005\u000b5C!\u0019\u0001(\t\rYC\u00019AA\r!\u0011A6,a\u0005\t\ryC\u0001\u0019AA\n\u0003\ri\u0017N\\\u000b\u0005\u0003C\t9\u0003\u0006\u0003\u0002$\u0005EBCBA\u0013\u0003S\ti\u0003E\u0002L\u0003O!Q!T\u0005C\u00029Ca!O\u0005A\u0004\u0005-\u0002\u0003B\u001eH\u0003KAaAV\u0005A\u0004\u0005=\u0002\u0003\u0002-\\\u0003KAaAX\u0005A\u0002\u0005\u0015\u0012aA7bqV!\u0011qGA\u001f)\u0011\tI$a\u0012\u0015\r\u0005m\u0012qHA\"!\rY\u0015Q\b\u0003\u0006\u001b*\u0011\rA\u0014\u0005\u0007s)\u0001\u001d!!\u0011\u0011\tm:\u00151\b\u0005\u0007-*\u0001\u001d!!\u0012\u0011\ta[\u00161\b\u0005\u0007=*\u0001\r!a\u000f\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0003\u0002\r\u0015\fX/\u00197t)\r)\u0014\u0011\u000b\u0005\t\u0003'b\u0011\u0011!a\u0001%\u0006\u0019\u0001\u0010J\u0019\u0002'1KG/\u001a:bY2{gnZ(sI\u0016\u0014x\n]:\u0011\u0005=r1c\u0001\b\u0002\\A\u0019\u0011%!\u0018\n\u0007\u0005}#E\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003/\nq\u0002\n7fgN$S\r\u001f;f]NLwN\\\u000b\u0005\u0003O\n\u0019\b\u0006\u0003\u0002j\u0005mD\u0003BA6\u0003s\"R!NA7\u0003kBa!\u000f\tA\u0004\u0005=\u0004\u0003B\u001eH\u0003c\u00022aSA:\t\u0015i\u0005C1\u0001O\u0011\u00191\u0006\u0003q\u0001\u0002xA!\u0001lWA9\u0011\u0019q\u0006\u00031\u0001\u0002r!1\u0011Q\u0010\tA\u00029\nQ\u0001\n;iSN\f!\u0003\n7fgN$S-\u001d\u0013fqR,gn]5p]V!\u00111QAH)\u0011\t))a&\u0015\t\u0005\u001d\u0015Q\u0013\u000b\u0006k\u0005%\u0015\u0011\u0013\u0005\u0007sE\u0001\u001d!a#\u0011\tm:\u0015Q\u0012\t\u0004\u0017\u0006=E!B'\u0012\u0005\u0004q\u0005B\u0002,\u0012\u0001\b\t\u0019\n\u0005\u0003Y7\u00065\u0005B\u00020\u0012\u0001\u0004\ti\t\u0003\u0004\u0002~E\u0001\rAL\u0001\u0013I\u001d\u0014X-\u0019;fe\u0012*\u0007\u0010^3og&|g.\u0006\u0003\u0002\u001e\u0006%F\u0003BAP\u0003c#B!!)\u00020R)Q'a)\u0002,\"1\u0011H\u0005a\u0002\u0003K\u0003BaO$\u0002(B\u00191*!+\u0005\u000b5\u0013\"\u0019\u0001(\t\rY\u0013\u00029AAW!\u0011A6,a*\t\ry\u0013\u0002\u0019AAT\u0011\u0019\tiH\u0005a\u0001]\u0005)Be\u001a:fCR,'\u000fJ3rI\u0015DH/\u001a8tS>tW\u0003BA\\\u0003\u0007$B!!/\u0002LR!\u00111XAe)\u0015)\u0014QXAc\u0011\u0019I4\u0003q\u0001\u0002@B!1hRAa!\rY\u00151\u0019\u0003\u0006\u001bN\u0011\rA\u0014\u0005\u0007-N\u0001\u001d!a2\u0011\ta[\u0016\u0011\u0019\u0005\u0007=N\u0001\r!!1\t\r\u0005u4\u00031\u0001/\u00035\u0019W\u000e\u001d\u0013fqR,gn]5p]V!\u0011\u0011[Ao)\u0011\t\u0019.!:\u0015\t\u0005U\u00171\u001d\u000b\u0007\u0003\u0013\t9.a8\t\re\"\u00029AAm!\u0011Yt)a7\u0011\u0007-\u000bi\u000eB\u0003N)\t\u0007a\n\u0003\u0004W)\u0001\u000f\u0011\u0011\u001d\t\u00051n\u000bY\u000e\u0003\u0004_)\u0001\u0007\u00111\u001c\u0005\u0007\u0003{\"\u0002\u0019\u0001\u0018\u0002\u001b5Lg\u000eJ3yi\u0016t7/[8o+\u0011\tY/a=\u0015\t\u00055\u0018q \u000b\u0005\u0003_\fi\u0010\u0006\u0004\u0002r\u0006U\u0018\u0011 \t\u0004\u0017\u0006MH!B'\u0016\u0005\u0004q\u0005BB\u001d\u0016\u0001\b\t9\u0010\u0005\u0003<\u000f\u0006E\bB\u0002,\u0016\u0001\b\tY\u0010\u0005\u0003Y7\u0006E\bB\u00020\u0016\u0001\u0004\t\t\u0010\u0003\u0004\u0002~U\u0001\rAL\u0001\u000e[\u0006DH%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\t\u0015!Q\u0002\u000b\u0005\u0005\u000f\u0011I\u0002\u0006\u0003\u0003\n\t]AC\u0002B\u0006\u0005\u001f\u0011\u0019\u0002E\u0002L\u0005\u001b!Q!\u0014\fC\u00029Ca!\u000f\fA\u0004\tE\u0001\u0003B\u001eH\u0005\u0017AaA\u0016\fA\u0004\tU\u0001\u0003\u0002-\\\u0005\u0017AaA\u0018\fA\u0002\t-\u0001BBA?-\u0001\u0007a&\u0001\niCND7i\u001c3fI\u0015DH/\u001a8tS>tG\u0003BA&\u0005?Aa!! \u0018\u0001\u0004q\u0013\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\u0011\u0011)C!\u000b\u0015\u0007U\u00129\u0003\u0003\u0005\u0002Ta\t\t\u00111\u0001S\u0011\u0019\ti\b\u0007a\u0001]\u0001"
)
public final class LiteralLongOrderOps {
   private final long lhs;

   public static boolean equals$extension(final long $this, final Object x$1) {
      return LiteralLongOrderOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final long $this) {
      return LiteralLongOrderOps$.MODULE$.hashCode$extension($this);
   }

   public static Object max$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.max$extension($this, rhs, ev, c);
   }

   public static Object min$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.min$extension($this, rhs, ev, c);
   }

   public static int cmp$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.cmp$extension($this, rhs, ev, c);
   }

   public static boolean $greater$eq$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$greater$eq$extension($this, rhs, ev, c);
   }

   public static boolean $greater$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$greater$extension($this, rhs, ev, c);
   }

   public static boolean $less$eq$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$less$eq$extension($this, rhs, ev, c);
   }

   public static boolean $less$extension(final long $this, final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$less$extension($this, rhs, ev, c);
   }

   public long lhs() {
      return this.lhs;
   }

   public boolean $less(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$less$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $less$eq(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$less$eq$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $greater(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$greater$extension(this.lhs(), rhs, ev, c);
   }

   public boolean $greater$eq(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.$greater$eq$extension(this.lhs(), rhs, ev, c);
   }

   public int cmp(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.cmp$extension(this.lhs(), rhs, ev, c);
   }

   public Object min(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.min$extension(this.lhs(), rhs, ev, c);
   }

   public Object max(final Object rhs, final Order ev, final ConvertableTo c) {
      return LiteralLongOrderOps$.MODULE$.max$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralLongOrderOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralLongOrderOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralLongOrderOps(final long lhs) {
      this.lhs = lhs;
   }
}
