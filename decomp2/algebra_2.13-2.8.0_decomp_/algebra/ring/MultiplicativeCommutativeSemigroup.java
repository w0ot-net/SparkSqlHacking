package algebra.ring;

import cats.kernel.CommutativeSemigroup;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0011\u0005ciB\u0003P\u0015!\u0005\u0001KB\u0003\n\u0015!\u0005\u0011\u000bC\u0003b\t\u0011\u0005!\rC\u0003d\t\u0011\u0015A\rC\u0003F\t\u0011\u0015q\u000eC\u0004x\t\u0005\u0005I\u0011\u0002=\u0003E5+H\u000e^5qY&\u001c\u0017\r^5wK\u000e{W.\\;uCRLg/Z*f[&<'o\\;q\u0015\tYA\"\u0001\u0003sS:<'\"A\u0007\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001QC\u0001\t\u001e'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0004\u0003:L\bc\u0001\r\u001a75\t!\"\u0003\u0002\u001b\u0015\t9R*\u001e7uSBd\u0017nY1uSZ,7+Z7jOJ|W\u000f\u001d\t\u00039ua\u0001\u0001B\u0005\u001f\u0001\u0001\u0006\t\u0011!b\u0001?\t\t\u0011)\u0005\u0002!#A\u0011!#I\u0005\u0003EM\u0011qAT8uQ&tw\r\u000b\u0004\u001eI\u001d\ndg\u000f\t\u0003%\u0015J!AJ\n\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G!J3F\u000b\b\u0003%%J!AK\n\u0002\u0007%sG/\r\u0003%YA\"bBA\u00171\u001b\u0005q#BA\u0018\u000f\u0003\u0019a$o\\8u}%\tA#M\u0003$eM*DG\u0004\u0002\u0013g%\u0011AgE\u0001\u0005\u0019>tw-\r\u0003%YA\"\u0012'B\u00128qiJdB\u0001\n9\u0013\tI4#A\u0003GY>\fG/\r\u0003%YA\"\u0012'B\u0012={}rdB\u0001\n>\u0013\tq4#\u0001\u0004E_V\u0014G.Z\u0019\u0005I1\u0002D#\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0005B\u0011!cQ\u0005\u0003\tN\u0011A!\u00168ji\u0006qQ.\u001e7uSBd\u0017nY1uSZ,W#A$\u0011\u0007!c5D\u0004\u0002J\u00156\tA\"\u0003\u0002L\u0019\u00059\u0001/Y2lC\u001e,\u0017BA'O\u0005Q\u0019u.\\7vi\u0006$\u0018N^3TK6LwM]8va*\u00111\nD\u0001#\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u0007>lW.\u001e;bi&4XmU3nS\u001e\u0014x.\u001e9\u0011\u0005a!1\u0003\u0002\u0003S+f\u0003\"AE*\n\u0005Q\u001b\"AB!osJ+g\rE\u0002\u0019-bK!a\u0016\u0006\u0003A5+H\u000e^5qY&\u001c\u0017\r^5wKN+W.[4s_V\u0004h)\u001e8di&|gn\u001d\t\u00031\u0001\u0001\"AW0\u000e\u0003mS!\u0001X/\u0002\u0005%|'\"\u00010\u0002\t)\fg/Y\u0005\u0003An\u0013AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u0001)\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005\u0015DGC\u00014j!\rA\u0002a\u001a\t\u00039!$QA\b\u0004C\u0002}AQA\u001b\u0004A\u0004\u0019\f!!\u001a<)\u0005\u0019a\u0007C\u0001\nn\u0013\tq7C\u0001\u0004j]2Lg.Z\u000b\u0003aN$\"!\u001d;\u0011\u0007!c%\u000f\u0005\u0002\u001dg\u0012)ad\u0002b\u0001?!)!n\u0002a\u0002kB\u0019\u0001\u0004\u0001:)\u0005\u001da\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#A=\u0011\u0005ilX\"A>\u000b\u0005ql\u0016\u0001\u00027b]\u001eL!A`>\u0003\r=\u0013'.Z2u\u0001"
)
public interface MultiplicativeCommutativeSemigroup extends MultiplicativeSemigroup {
   static MultiplicativeCommutativeSemigroup apply(final MultiplicativeCommutativeSemigroup ev) {
      return MultiplicativeCommutativeSemigroup$.MODULE$.apply(ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeCommutativeSemigroup$.MODULE$.isMultiplicativeCommutative(ev);
   }

   // $FF: synthetic method
   static CommutativeSemigroup multiplicative$(final MultiplicativeCommutativeSemigroup $this) {
      return $this.multiplicative();
   }

   default CommutativeSemigroup multiplicative() {
      return new CommutativeSemigroup() {
         // $FF: synthetic field
         private final MultiplicativeCommutativeSemigroup $outer;

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

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.times(x, y);
         }

         public {
            if (MultiplicativeCommutativeSemigroup.this == null) {
               throw null;
            } else {
               this.$outer = MultiplicativeCommutativeSemigroup.this;
               Semigroup.$init$(this);
               CommutativeSemigroup.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static CommutativeSemigroup multiplicative$mcD$sp$(final MultiplicativeCommutativeSemigroup $this) {
      return $this.multiplicative$mcD$sp();
   }

   default CommutativeSemigroup multiplicative$mcD$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeSemigroup multiplicative$mcF$sp$(final MultiplicativeCommutativeSemigroup $this) {
      return $this.multiplicative$mcF$sp();
   }

   default CommutativeSemigroup multiplicative$mcF$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeSemigroup multiplicative$mcI$sp$(final MultiplicativeCommutativeSemigroup $this) {
      return $this.multiplicative$mcI$sp();
   }

   default CommutativeSemigroup multiplicative$mcI$sp() {
      return this.multiplicative();
   }

   // $FF: synthetic method
   static CommutativeSemigroup multiplicative$mcJ$sp$(final MultiplicativeCommutativeSemigroup $this) {
      return $this.multiplicative$mcJ$sp();
   }

   default CommutativeSemigroup multiplicative$mcJ$sp() {
      return this.multiplicative();
   }

   static void $init$(final MultiplicativeCommutativeSemigroup $this) {
   }
}
