package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.IterableOnceExtensionMethods.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005E3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003F\u0001\u0011\raI\u0001\rM_^\u0004&/[8DC:$&/\u0019<feN,g+\u00197vKNT!AB\u0004\u0002\u000fM,\b\u000f]8si*\u0011\u0001\"C\u0001\u0007Y&t\u0017\r\\4\u000b\u0003)\taA\u0019:fKj,7\u0001A\n\u0004\u00015\u0019\u0002C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\r\u0005\u0002\u0015+5\tQ!\u0003\u0002\u0017\u000b\tIBj\\<Qe&|7)\u00198Ue\u00064XM]:f-\u0006dW/Z:3\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0004\u0005\u0002\u000f5%\u00111d\u0004\u0002\u0005+:LG/\u0001\fdC:$&/\u0019<feN,GK]1wKJ\u001c\u0018M\u00197f+\rqr\bJ\u000b\u0002?A!A\u0003\t\u0012?\u0013\t\tSAA\tDC:$&/\u0019<feN,g+\u00197vKN\u0004\"a\t\u0013\r\u0001\u0011)QE\u0001b\u0001M\t\t\u0001,\u0005\u0002(UA\u0011a\u0002K\u0005\u0003S=\u0011qAT8uQ&tw\rE\u0002,wyr!\u0001\f\u001d\u000f\u00055*dB\u0001\u00184\u001d\ty#'D\u00011\u0015\t\t4\"\u0001\u0004=e>|GOP\u0005\u0002!%\u0011AgD\u0001\u000bG>dG.Z2uS>t\u0017B\u0001\u001c8\u0003\u0019\u0019w.\u001c9bi*\u0011AgD\u0005\u0003si\nq\u0001]1dW\u0006<WM\u0003\u00027o%\u0011A(\u0010\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0006\u0003si\u0002\"aI \u0005\u000b\u0001\u0013!\u0019A!\u0003\u0003Y\u000b\"a\n\"\u0011\u00059\u0019\u0015B\u0001#\u0010\u0005\r\te._\u0001\u0014G\u0006tGK]1wKJ\u001cX-\u0013;fe\u0006$xN]\u000b\u0003\u000fB+\u0012\u0001\u0013\t\u0005)\u0001Ju\nE\u0002K\u0019>s!AL&\n\u0005ez\u0011BA'O\u0005!IE/\u001a:bi>\u0014(BA\u001d\u0010!\t\u0019\u0003\u000bB\u0003A\u0007\t\u0007\u0011\t"
)
public interface LowPrioCanTraverseValues extends LowPrioCanTraverseValues2 {
   // $FF: synthetic method
   static CanTraverseValues canTraverseTraversable$(final LowPrioCanTraverseValues $this) {
      return $this.canTraverseTraversable();
   }

   default CanTraverseValues canTraverseTraversable() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final IterableOnce from, final CanTraverseValues.ValuesVisitor fn) {
            .MODULE$.foreach$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(from), (v) -> {
               $anonfun$traverse$1(fn, v);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         public boolean isTraversableAgain(final IterableOnce from) {
            return from instanceof Iterable;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$1(final CanTraverseValues.ValuesVisitor fn$2, final Object v) {
            fn$2.visit(v);
         }

         public {
            CanTraverseValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseValues canTraverseIterator$(final LowPrioCanTraverseValues $this) {
      return $this.canTraverseIterator();
   }

   default CanTraverseValues canTraverseIterator() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public CanTraverseValues.ValuesVisitor traverse(final Iterator from, final CanTraverseValues.ValuesVisitor fn) {
            from.foreach((v) -> {
               $anonfun$traverse$2(fn, v);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         public boolean isTraversableAgain(final Iterator from) {
            return from instanceof Iterable;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$2(final CanTraverseValues.ValuesVisitor fn$3, final Object v) {
            fn$3.visit(v);
         }

         public {
            CanTraverseValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final LowPrioCanTraverseValues $this) {
   }
}
