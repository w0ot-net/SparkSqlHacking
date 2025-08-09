package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTranspose;
import breeze.math.MutableInnerProductVectorSpace;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015v!B\f\u0019\u0011\u0003ib!B\u0010\u0019\u0011\u0003\u0001\u0003\"B\u0017\u0002\t\u0003q\u0003\"B\u0018\u0002\t\u0003\u0001\u0004bB>\u0002#\u0003%\t\u0001 \u0005\n\u0003/\t\u0011\u0013!C\u0001\u00033A\u0011\"!\t\u0002#\u0003%\t!a\t\t\u0013\u0005=\u0012!%A\u0005\u0002\u0005EbABA\u001f\u0003\u001d\ty\u0004\u0003\u0006\u0002H!\u0011)\u0019!C\u0001\u0003\u0013B\u0011\"a\u0013\t\u0005\u0003\u0005\u000b\u0011\u00024\t\r5BA\u0011AA'\u0011\u001d\t)\u0006\u0003C\u0001\u0003/B\u0011\"!\u0018\t\u0003\u0003%\t%a\u0018\t\u0013\u0005\u0005\u0004\"!A\u0005B\u0005\rt!CA5\u0003\u0005\u0005\t\u0012BA6\r%\ti$AA\u0001\u0012\u0013\ti\u0007\u0003\u0004.!\u0011\u0005\u0011q\u000e\u0005\b\u0003c\u0002BQAA:\u0011%\ti\bEA\u0001\n\u000b\ty\bC\u0005\u0002\u0004B\t\t\u0011\"\u0002\u0002\u0006\"I\u0011\u0011N\u0001\u0002\u0002\u0013-\u0011Q\u0012\u0005\n\u0003#\u000b\u0011\u0011!C\u0005\u0003'\u000bA\u0001T*N%*\u0011\u0011DG\u0001\u0007Y&t\u0017\r\\4\u000b\u0003m\taA\u0019:fKj,7\u0001\u0001\t\u0003=\u0005i\u0011\u0001\u0007\u0002\u0005\u0019Nk%kE\u0002\u0002C\u001d\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007C\u0001\u0015,\u001b\u0005I#B\u0001\u0016\u001b\u0003\u0011)H/\u001b7\n\u00051J#aE*fe&\fG.\u001b>bE2,Gj\\4hS:<\u0017A\u0002\u001fj]&$h\bF\u0001\u001e\u0003\u0015\u0019x\u000e\u001c<f+\u0011\td*W\u001b\u0015\u000fIJ7.\\8rmR)1G\u0010)\\=B\u0011A'\u000e\u0007\u0001\t\u001514A1\u00018\u0005\u00051\u0016C\u0001\u001d<!\t\u0011\u0013(\u0003\u0002;G\t9aj\u001c;iS:<\u0007C\u0001\u0012=\u0013\ti4EA\u0002B]fDQaP\u0002A\u0004\u0001\u000ba!\\;mi63\u0006#B!H\u001bN\u001adB\u0001\"F\u001b\u0005\u0019%B\u0001#\u0019\u0003%y\u0007/\u001a:bi>\u00148/\u0003\u0002G\u0007\u0006Yq\n]'vY6\u000bGO]5y\u0013\tA\u0015JA\u0003J[Bd''\u0003\u0002K\u0017\n)QKR;oG*\u0011AJG\u0001\bO\u0016tWM]5d!\t!d\nB\u0003P\u0007\t\u0007qGA\u0001N\u0011\u0015\t6\u0001q\u0001S\u0003\u0019!(/\u00198t\u0003B!1KV'Y\u001b\u0005!&BA+\u0019\u0003\u001d\u0019X\u000f\u001d9peRL!a\u0016+\u0003\u0019\r\u000bg\u000e\u0016:b]N\u0004xn]3\u0011\u0005QJF!\u0002.\u0004\u0005\u00049$AA'U\u0011\u0015a6\u0001q\u0001^\u0003\u001diW\u000f\u001c;N)Z\u0003R!Q$YgMBQaX\u0002A\u0004\u0001\fa![:qC\u000e,\u0007\u0003B1eg\u0019l\u0011A\u0019\u0006\u0003Gj\tA!\\1uQ&\u0011QM\u0019\u0002\u001f\u001bV$\u0018M\u00197f\u0013:tWM\u001d)s_\u0012,8\r\u001e,fGR|'o\u00159bG\u0016\u0004\"AI4\n\u0005!\u001c#A\u0002#pk\ndW\rC\u0003k\u0007\u0001\u0007Q*A\u0001B\u0011\u0015a7\u00011\u00014\u0003\u0005\u0011\u0007b\u00028\u0004!\u0003\u0005\rAZ\u0001\u000fe\u0016<W\u000f\\1sSj\fG/[8o\u0011\u001d\u00018\u0001%AA\u0002\u0019\f\u0011\u0002^8mKJ\fgnY3\t\u000fI\u001c\u0001\u0013!a\u0001g\u00069Q.\u0019=Ji\u0016\u0014\bC\u0001\u0012u\u0013\t)8EA\u0002J]RDqa^\u0002\u0011\u0002\u0003\u0007\u00010A\u0003rk&,G\u000f\u0005\u0002#s&\u0011!p\t\u0002\b\u0005>|G.Z1o\u0003=\u0019x\u000e\u001c<fI\u0011,g-Y;mi\u0012\u001aTcB?\u0002\u0012\u0005M\u0011QC\u000b\u0002}*\u0012am`\u0016\u0003\u0003\u0003\u0001B!a\u0001\u0002\u000e5\u0011\u0011Q\u0001\u0006\u0005\u0003\u000f\tI!A\u0005v]\u000eDWmY6fI*\u0019\u00111B\u0012\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0010\u0005\u0015!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)q\n\u0002b\u0001o\u0011)!\f\u0002b\u0001o\u0011)a\u0007\u0002b\u0001o\u0005y1o\u001c7wK\u0012\"WMZ1vYR$C'F\u0004~\u00037\ti\"a\b\u0005\u000b=+!\u0019A\u001c\u0005\u000bi+!\u0019A\u001c\u0005\u000bY*!\u0019A\u001c\u0002\u001fM|GN^3%I\u00164\u0017-\u001e7uIU*\u0002\"!\n\u0002*\u0005-\u0012QF\u000b\u0003\u0003OQ#a]@\u0005\u000b=3!\u0019A\u001c\u0005\u000bi3!\u0019A\u001c\u0005\u000bY2!\u0019A\u001c\u0002\u001fM|GN^3%I\u00164\u0017-\u001e7uIY*\u0002\"a\r\u00028\u0005e\u00121H\u000b\u0003\u0003kQ#\u0001_@\u0005\u000b=;!\u0019A\u001c\u0005\u000bi;!\u0019A\u001c\u0005\u000bY:!\u0019A\u001c\u0003\u000fM\u000bg-\u001a#jmN\u0019\u0001\"!\u0011\u0011\u0007\t\n\u0019%C\u0002\u0002F\r\u0012a!\u00118z-\u0006d\u0017aA0`qV\ta-\u0001\u0003`?b\u0004C\u0003BA(\u0003'\u00022!!\u0015\t\u001b\u0005\t\u0001BBA$\u0017\u0001\u0007a-\u0001\u0006%I&4H%]7be.$2AZA-\u0011\u0019\tY\u0006\u0004a\u0001M\u0006\t\u00110\u0001\u0005iCND7i\u001c3f)\u0005\u0019\u0018AB3rk\u0006d7\u000fF\u0002y\u0003KB\u0001\"a\u001a\u000f\u0003\u0003\u0005\raO\u0001\u0004q\u0012\n\u0014aB*bM\u0016$\u0015N\u001e\t\u0004\u0003#\u00022C\u0001\t\")\t\tY'\u0001\u000b%I&4H%]7be.$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003k\nI\bF\u0002g\u0003oBa!a\u0017\u0013\u0001\u00041\u0007bBA>%\u0001\u0007\u0011qJ\u0001\u0006IQD\u0017n]\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002`\u0005\u0005\u0005bBA>'\u0001\u0007\u0011qJ\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$B!a\"\u0002\fR\u0019\u00010!#\t\u0011\u0005\u001dD#!AA\u0002mBq!a\u001f\u0015\u0001\u0004\ty\u0005\u0006\u0003\u0002P\u0005=\u0005BBA$+\u0001\u0007a-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0016B!\u0011qSAQ\u001b\t\tIJ\u0003\u0003\u0002\u001c\u0006u\u0015\u0001\u00027b]\u001eT!!a(\u0002\t)\fg/Y\u0005\u0005\u0003G\u000bIJ\u0001\u0004PE*,7\r\u001e"
)
public final class LSMR {
   public static boolean solve$default$6() {
      return LSMR$.MODULE$.solve$default$6();
   }

   public static int solve$default$5() {
      return LSMR$.MODULE$.solve$default$5();
   }

   public static double solve$default$4() {
      return LSMR$.MODULE$.solve$default$4();
   }

   public static double solve$default$3() {
      return LSMR$.MODULE$.solve$default$3();
   }

   public static Object solve(final Object A, final Object b, final double regularization, final double tolerance, final int maxIter, final boolean quiet, final UFunc.UImpl2 multMV, final CanTranspose transA, final UFunc.UImpl2 multMTV, final MutableInnerProductVectorSpace ispace) {
      return LSMR$.MODULE$.solve(A, b, regularization, tolerance, maxIter, quiet, multMV, transA, multMTV, ispace);
   }

   private static final class SafeDiv {
      private final double __x;

      public double __x() {
         return this.__x;
      }

      public double $div$qmark(final double y) {
         return LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.__x(), y);
      }

      public int hashCode() {
         return LSMR.SafeDiv$.MODULE$.hashCode$extension(this.__x());
      }

      public boolean equals(final Object x$1) {
         return LSMR.SafeDiv$.MODULE$.equals$extension(this.__x(), x$1);
      }

      public SafeDiv(final double __x) {
         this.__x = __x;
      }
   }

   private static class SafeDiv$ {
      public static final SafeDiv$ MODULE$ = new SafeDiv$();

      public final double $div$qmark$extension(final double $this, final double y) {
         return y == (double)0 ? $this : $this / y;
      }

      public final int hashCode$extension(final double $this) {
         return Double.hashCode($this);
      }

      public final boolean equals$extension(final double $this, final Object x$1) {
         boolean var4;
         if (x$1 instanceof SafeDiv) {
            var4 = true;
         } else {
            var4 = false;
         }

         boolean var10000;
         if (var4) {
            double var6 = ((SafeDiv)x$1).__x();
            if ($this == var6) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }

      public SafeDiv$() {
      }
   }
}
