package cats.kernel.instances;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Hash;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003C\u0001\u0011\r1I\u0001\u0007NCBLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q)R\"A\u0003\n\u0005Y)!!D'ba&s7\u000f^1oG\u0016\u001c\u0018'\u0001\u0004%S:LG\u000f\n\u000b\u00023A\u0011aBG\u0005\u00037=\u0011A!\u00168ji\u000692-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012D\u0015m\u001d5G_Jl\u0015\r]\u000b\u0004=ARDcA\u0010=\u007fA\u0019\u0001%I\u0012\u000e\u0003\u001dI!AI\u0004\u0003\t!\u000b7\u000f\u001b\t\u0005I-r\u0013H\u0004\u0002&SA\u0011aeD\u0007\u0002O)\u0011\u0001fC\u0001\u0007yI|w\u000e\u001e \n\u0005)z\u0011A\u0002)sK\u0012,g-\u0003\u0002-[\t\u0019Q*\u00199\u000b\u0005)z\u0001CA\u00181\u0019\u0001!Q!\r\u0002C\u0002I\u0012\u0011aS\t\u0003gY\u0002\"A\u0004\u001b\n\u0005Uz!a\u0002(pi\"Lgn\u001a\t\u0003\u001d]J!\u0001O\b\u0003\u0007\u0005s\u0017\u0010\u0005\u00020u\u0011)1H\u0001b\u0001e\t\ta\u000bC\u0004>\u0005\u0005\u0005\t9\u0001 \u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002!C9Bq\u0001\u0011\u0002\u0002\u0002\u0003\u000f\u0011)\u0001\u0006fm&$WM\\2fII\u00022\u0001I\u0011:\u0003\u0011\u001a\u0017\r^:LKJtW\r\\*uI\u000e{W.\\;uCRLg/Z'p]>LGMR8s\u001b\u0006\u0004Xc\u0001#K\u0019R\u0011Q)\u0014\t\u0004A\u0019C\u0015BA$\b\u0005E\u0019u.\\7vi\u0006$\u0018N^3N_:|\u0017\u000e\u001a\t\u0005I-J5\n\u0005\u00020\u0015\u0012)\u0011g\u0001b\u0001eA\u0011q\u0006\u0014\u0003\u0006w\r\u0011\rA\r\u0005\b\u001d\u000e\t\t\u0011q\u0001P\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004AA[\u0015BA)\b\u0005Q\u0019u.\\7vi\u0006$\u0018N^3TK6LwM]8va\"\u0012\u0001a\u0015\t\u0003)ns!!\u0016-\u000f\u0005\u00012\u0016BA,\b\u0003\u0019\u0019w.\u001c9bi&\u0011\u0011LW\u0001\u0015g\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2\u000b\u0005];\u0011B\u0001/^\u0005I\u001aX\u000f\u001d9sKN\u001cXK\\;tK\u0012LU\u000e]8si^\u000b'O\\5oO\u001a{'oU2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001c'BA-[\u0001"
)
public interface MapInstances extends MapInstances1 {
   // $FF: synthetic method
   static Hash catsKernelStdHashForMap$(final MapInstances $this, final Hash evidence$1, final Hash evidence$2) {
      return $this.catsKernelStdHashForMap(evidence$1, evidence$2);
   }

   default Hash catsKernelStdHashForMap(final Hash evidence$1, final Hash evidence$2) {
      return new MapHash(evidence$2);
   }

   // $FF: synthetic method
   static CommutativeMonoid catsKernelStdCommutativeMonoidForMap$(final MapInstances $this, final CommutativeSemigroup evidence$3) {
      return $this.catsKernelStdCommutativeMonoidForMap(evidence$3);
   }

   default CommutativeMonoid catsKernelStdCommutativeMonoidForMap(final CommutativeSemigroup evidence$3) {
      return new CommutativeMonoid(evidence$3) {
         public CommutativeMonoid reverse() {
            return CommutativeMonoid.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
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

         public {
            CommutativeSemigroup.$init$(this);
            CommutativeMonoid.$init$(this);
         }
      };
   }

   static void $init$(final MapInstances $this) {
   }
}
