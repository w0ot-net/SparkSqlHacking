package breeze.linalg;

import breeze.generic.UFunc;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.collection.immutable.IndexedSeq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006\u001f\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u0013\u0019><\bK]5pe&$\u00180\u0011:h'>\u0014HO\u0003\u0002\u0006\r\u00051A.\u001b8bY\u001eT\u0011aB\u0001\u0007EJ,WM_3\u0014\u0005\u0001I\u0001C\u0001\u0006\u000e\u001b\u0005Y!\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u00059Y!AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005\u0011\u0002C\u0001\u0006\u0014\u0013\t!2B\u0001\u0003V]&$\u0018AH1sON|'\u000f^)vCNLG+\u001a8t_J<\u0016\u000e\u001e5Pe\u0012,'/\u001b8h+\u00119Re\u000f$\u0015\u0007ai\u0004\n\u0005\u0003\u001a;\rrcB\u0001\u000e\u001c\u001b\u0005!\u0011B\u0001\u000f\u0005\u0003\u001d\t'oZ:peRL!AH\u0010\u0003\t%k\u0007\u000f\\\u0005\u0003A\u0005\u0012Q!\u0016$v]\u000eT!A\t\u0004\u0002\u000f\u001d,g.\u001a:jGB\u0011A%\n\u0007\u0001\t\u00151#A1\u0001(\u0005\u0005\t\u0016C\u0001\u0015,!\tQ\u0011&\u0003\u0002+\u0017\t9aj\u001c;iS:<\u0007C\u0001\u0006-\u0013\ti3BA\u0002B]f\u00042aL\u001c;\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024!\u00051AH]8pizJ\u0011\u0001D\u0005\u0003m-\tq\u0001]1dW\u0006<W-\u0003\u00029s\tQ\u0011J\u001c3fq\u0016$7+Z9\u000b\u0005YZ\u0001C\u0001\u0013<\t\u0015a$A1\u0001(\u0005\u0005I\u0005\"\u0002 \u0003\u0001\by\u0014AA9u!\u0011Q\u0001i\t\"\n\u0005\u0005[!\u0001\u0005\u0013mKN\u001cHeY8m_:$C.Z:t!\u0011Q2IO#\n\u0005\u0011#!aC)vCNLG+\u001a8t_J\u0004\"\u0001\n$\u0005\u000b\u001d\u0013!\u0019A\u0014\u0003\u0003YCQ!\u0013\u0002A\u0004)\u000b1a\u001c:e!\ry3*R\u0005\u0003\u0019f\u0012\u0001b\u0014:eKJLgn\u001a"
)
public interface LowPriorityArgSort {
   // $FF: synthetic method
   static UFunc.UImpl argsortQuasiTensorWithOrdering$(final LowPriorityArgSort $this, final .less.colon.less qt, final Ordering ord) {
      return $this.argsortQuasiTensorWithOrdering(qt, ord);
   }

   default UFunc.UImpl argsortQuasiTensorWithOrdering(final .less.colon.less qt, final Ordering ord) {
      return new UFunc.UImpl(qt, ord) {
         private final .less.colon.less qt$2;
         private final Ordering ord$2;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public IndexedSeq apply(final Object q) {
            return (IndexedSeq)((QuasiTensor)this.qt$2.apply(q)).keysIterator().toIndexedSeq().sorted(this.ord$2.on((x$1) -> ((QuasiTensor)this.qt$2.apply(q)).apply(x$1)));
         }

         public {
            this.qt$2 = qt$2;
            this.ord$2 = ord$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final LowPriorityArgSort $this) {
   }
}
