package cats.kernel.instances;

import cats.kernel.CommutativeSemigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2Aa\u0001\u0003\u0001\u0017!Aa\u0006\u0001B\u0001B\u0003-q\u0006C\u00031\u0001\u0011\u0005\u0011GA\u000fT_J$X\rZ'ba\u000e{W.\\;uCRLg/Z*f[&<'o\\;q\u0015\t)a!A\u0005j]N$\u0018M\\2fg*\u0011q\u0001C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003%\tAaY1ug\u000e\u0001Qc\u0001\u0007\u0014AM\u0019\u0001!\u0004\u0012\u0011\t9y\u0011cH\u0007\u0002\t%\u0011\u0001\u0003\u0002\u0002\u0013'>\u0014H/\u001a3NCB\u001cV-\\5he>,\b\u000f\u0005\u0002\u0013'1\u0001A!\u0002\u000b\u0001\u0005\u0004)\"!A&\u0012\u0005Ya\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"a\u0002(pi\"Lgn\u001a\t\u0003/uI!A\b\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0013A\u0011)\u0011\u0005\u0001b\u0001+\t\ta\u000bE\u0002$I\u0019j\u0011AB\u0005\u0003K\u0019\u0011AcQ8n[V$\u0018\r^5wKN+W.[4s_V\u0004\b\u0003B\u0014-#}i\u0011\u0001\u000b\u0006\u0003S)\n\u0011\"[7nkR\f'\r\\3\u000b\u0005-B\u0012AC2pY2,7\r^5p]&\u0011Q\u0006\u000b\u0002\n'>\u0014H/\u001a3NCB\f\u0011A\u0016\t\u0004G\u0011z\u0012A\u0002\u001fj]&$h\bF\u00013)\t\u0019D\u0007\u0005\u0003\u000f\u0001Ey\u0002\"\u0002\u0018\u0003\u0001\by\u0003"
)
public class SortedMapCommutativeSemigroup extends SortedMapSemigroup implements CommutativeSemigroup {
   public CommutativeSemigroup reverse() {
      return CommutativeSemigroup.reverse$(this);
   }

   public CommutativeSemigroup reverse$mcD$sp() {
      return CommutativeSemigroup.reverse$mcD$sp$(this);
   }

   public CommutativeSemigroup reverse$mcF$sp() {
      return CommutativeSemigroup.reverse$mcF$sp$(this);
   }

   public CommutativeSemigroup reverse$mcI$sp() {
      return CommutativeSemigroup.reverse$mcI$sp$(this);
   }

   public CommutativeSemigroup reverse$mcJ$sp() {
      return CommutativeSemigroup.reverse$mcJ$sp$(this);
   }

   public CommutativeSemigroup intercalate(final Object middle) {
      return CommutativeSemigroup.intercalate$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
   }

   public SortedMapCommutativeSemigroup(final CommutativeSemigroup V) {
      super(V);
      CommutativeSemigroup.$init$(this);
   }
}
