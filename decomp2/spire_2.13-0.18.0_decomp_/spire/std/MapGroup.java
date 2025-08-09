package spire.std;

import cats.kernel.Group;
import java.lang.invoke.SerializedLambda;
import scala..less.colon.less.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3A!\u0002\u0004\u0001\u0017!A!\t\u0001BC\u0002\u0013\r3\tC\u0005F\u0001\t\u0005\t\u0015!\u0003E\r\")q\t\u0001C\u0001\u0011\")A\n\u0001C\u0001\u001b\nAQ*\u00199He>,\bO\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001!F\u0002\r'\u0001\u001aB\u0001A\u0007#uA!abD\t \u001b\u00051\u0011B\u0001\t\u0007\u0005%i\u0015\r]'p]>LG\r\u0005\u0002\u0013'1\u0001A!\u0002\u000b\u0001\u0005\u0004)\"!A&\u0012\u0005Ya\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"a\u0002(pi\"Lgn\u001a\t\u0003/uI!A\b\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0013A\u0011)\u0011\u0005\u0001b\u0001+\t\ta\u000bE\u0002$_Ir!\u0001\n\u0017\u000f\u0005\u0015RcB\u0001\u0014*\u001b\u00059#B\u0001\u0015\u000b\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002,\u0011\u00059\u0011\r\\4fEJ\f\u0017BA\u0017/\u0003\u001d\u0001\u0018mY6bO\u0016T!a\u000b\u0005\n\u0005A\n$!B$s_V\u0004(BA\u0017/!\u0011\u0019t'E\u0010\u000f\u0005Q*\u0004C\u0001\u0014\u0019\u0013\t1\u0004$\u0001\u0004Qe\u0016$WMZ\u0005\u0003qe\u00121!T1q\u0015\t1\u0004\u0004\u0005\u0002<\u007f9\u0011AH\u0010\b\u0003MuJ\u0011!G\u0005\u0003[aI!\u0001Q!\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u00055B\u0012AB:dC2\f'/F\u0001E!\r\u0019sfH\u0001\bg\u000e\fG.\u0019:!\u0013\t\u0011u\"\u0001\u0004=S:LGO\u0010\u000b\u0002\u0013R\u0011!j\u0013\t\u0005\u001d\u0001\tr\u0004C\u0003C\u0007\u0001\u000fA)A\u0004j]Z,'o]3\u0015\u0005Ir\u0005\"B(\u0005\u0001\u0004\u0011\u0014!\u0001=)\t\u0001\tF+\u0016\t\u0003/IK!a\u0015\r\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$\u0001\u0001"
)
public class MapGroup extends MapMonoid implements Group {
   private static final long serialVersionUID = 0L;

   public double inverse$mcD$sp(final double a) {
      return Group.inverse$mcD$sp$(this, a);
   }

   public float inverse$mcF$sp(final float a) {
      return Group.inverse$mcF$sp$(this, a);
   }

   public int inverse$mcI$sp(final int a) {
      return Group.inverse$mcI$sp$(this, a);
   }

   public long inverse$mcJ$sp(final long a) {
      return Group.inverse$mcJ$sp$(this, a);
   }

   public Object remove(final Object a, final Object b) {
      return Group.remove$(this, a, b);
   }

   public double remove$mcD$sp(final double a, final double b) {
      return Group.remove$mcD$sp$(this, a, b);
   }

   public float remove$mcF$sp(final float a, final float b) {
      return Group.remove$mcF$sp$(this, a, b);
   }

   public int remove$mcI$sp(final int a, final int b) {
      return Group.remove$mcI$sp$(this, a, b);
   }

   public long remove$mcJ$sp(final long a, final long b) {
      return Group.remove$mcJ$sp$(this, a, b);
   }

   public Object combineN(final Object a, final int n) {
      return Group.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Group.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Group.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Group.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Group.combineN$mcJ$sp$(this, a, n);
   }

   public Group scalar() {
      return (Group)super.scalar();
   }

   public Map inverse(final Map x) {
      return x.view().mapValues((a) -> this.scalar().inverse(a)).toMap(.MODULE$.refl());
   }

   public MapGroup(final Group scalar) {
      super(scalar);
      Group.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
