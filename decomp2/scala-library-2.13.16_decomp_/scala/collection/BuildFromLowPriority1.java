package scala.collection;

import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\r\u0011\u0004C\u0003X\u0001\u0011\r\u0001LA\u000bCk&dGM\u0012:p[2{w\u000f\u0015:j_JLG/_\u0019\u000b\u0005\u00199\u0011AC2pY2,7\r^5p]*\t\u0001\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001Yq\u0002\u0005\u0002\r\u001b5\tq!\u0003\u0002\u000f\u000f\t1\u0011I\\=SK\u001a\u0004\"\u0001E\t\u000e\u0003\u0015I!AE\u0003\u0003+\t+\u0018\u000e\u001c3Ge>lGj\\<Qe&|'/\u001b;ze\u00051A%\u001b8ji\u0012\"\u0012!\u0006\t\u0003\u0019YI!aF\u0004\u0003\tUs\u0017\u000e^\u0001\u0016EVLG\u000e\u001a$s_6\u001cvN\u001d;fIN+Go\u00149t+\u0011Q2eP\"\u0015\u0005mI\u0005#\u0002\t\u001d=\t+\u0015BA\u000f\u0006\u0005%\u0011U/\u001b7e\rJ|WNE\u0002 C\u00053A\u0001\t\u0001\u0001=\taAH]3gS:,W.\u001a8u}A\u0019!e\t \r\u0001\u0011)AE\u0001b\u0001K\t\u00111iQ\u000b\u0003MA\n\"a\n\u0016\u0011\u00051A\u0013BA\u0015\b\u0005\u001dqu\u000e\u001e5j]\u001e\u00142a\u000b\u00177\r\u0011\u0001\u0003\u0001\u0001\u0016\u0011\u0007Ais&\u0003\u0002/\u000b\tI1k\u001c:uK\u0012\u001cV\r\u001e\t\u0003EA\"Q!M\u0012C\u0002I\u0012\u0011\u0001W\t\u0003OM\u0002\"\u0001\u0004\u001b\n\u0005U:!aA!osB\u0012q\u0007\u0010\t\u0006!az#hO\u0005\u0003s\u0015\u0011AbU8si\u0016$7+\u001a;PaN\u0004\"AI\u0012\u0011\u0005\tbD!C\u001f$\u0003\u0003\u0005\tQ!\u00013\u0005\u0011yF%M\u0019\u0011\u0005\tzD!\u0002!\u0003\u0005\u0004\u0011$AA!1!\r\u0001RF\u0010\t\u0003E\r#Q\u0001\u0012\u0002C\u0002I\u0012\u0011!\u0011\n\u0004\r\u001eCe\u0001\u0002\u0011\u0001\u0001\u0015\u00032AI\u0012C!\r\u0001RF\u0011\u0005\b\u0015\n\t\t\u0011q\u0001L\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004\u0019R\u0013eBA'S\u001d\tq\u0015+D\u0001P\u0015\t\u0001\u0016\"\u0001\u0004=e>|GOP\u0005\u0002\u0011%\u00111kB\u0001\ba\u0006\u001c7.Y4f\u0013\t)fK\u0001\u0005Pe\u0012,'/\u001b8h\u0015\t\u0019v!\u0001\u000egC2d'-Y2l'R\u0014\u0018N\\4DC:\u0014U/\u001b7e\rJ|W.\u0006\u0002ZIV\t!\fE\u0003\u00119m\u001bW\r\u0005\u0002]A:\u0011QL\u0018\t\u0003\u001d\u001eI!aX\u0004\u0002\rA\u0013X\rZ3g\u0013\t\t'M\u0001\u0004TiJLgn\u001a\u0006\u0003?\u001e\u0001\"A\t3\u0005\u000b\u0011\u001b!\u0019\u0001\u001a\u0011\u0007\u0019L7-D\u0001h\u0015\tAW!A\u0005j[6,H/\u00192mK&\u0011!n\u001a\u0002\u000b\u0013:$W\r_3e'\u0016\f\b"
)
public interface BuildFromLowPriority1 extends BuildFromLowPriority2 {
   // $FF: synthetic method
   static BuildFrom buildFromSortedSetOps$(final BuildFromLowPriority1 $this, final Ordering evidence$3) {
      return $this.buildFromSortedSetOps(evidence$3);
   }

   default BuildFrom buildFromSortedSetOps(final Ordering evidence$3) {
      return new BuildFrom(evidence$3) {
         private final Ordering evidence$3$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final SortedSet from) {
            return from.sortedIterableFactory().newBuilder(this.evidence$3$1);
         }

         public SortedSet fromSpecific(final SortedSet from, final IterableOnce it) {
            return (SortedSet)from.sortedIterableFactory().from(it, this.evidence$3$1);
         }

         public {
            this.evidence$3$1 = evidence$3$1;
         }
      };
   }

   // $FF: synthetic method
   static BuildFrom fallbackStringCanBuildFrom$(final BuildFromLowPriority1 $this) {
      return $this.fallbackStringCanBuildFrom();
   }

   default BuildFrom fallbackStringCanBuildFrom() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public scala.collection.immutable.IndexedSeq fromSpecific(final String from, final IterableOnce it) {
            return scala.collection.immutable.IndexedSeq$.MODULE$.from(it);
         }

         public Builder newBuilder(final String from) {
            return scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
         }
      };
   }

   static void $init$(final BuildFromLowPriority1 $this) {
   }
}
