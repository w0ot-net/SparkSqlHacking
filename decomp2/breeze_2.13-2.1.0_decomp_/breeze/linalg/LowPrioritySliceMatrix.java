package breeze.linalg;

import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanSlice2;
import breeze.math.Semiring;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.collection.immutable.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mf!\u0003\u0005\n!\u0003\r\tADAW\u0011\u0015)\u0002\u0001\"\u0001\u0017\u0011\u0015Q\u0002\u0001b\u0001\u001c\u0011\u0015Q\u0006\u0001b\u0001\\\u0011\u0015Y\u0007\u0001b\u0001m\u0011\u001d\ti\u0001\u0001C\u0002\u0003\u001fAq!a\u000b\u0001\t\u0007\ti\u0003C\u0004\u0002x\u0001!\u0019!!\u001f\u0003-1{w\u000f\u0015:j_JLG/_*mS\u000e,W*\u0019;sSbT!AC\u0006\u0002\r1Lg.\u00197h\u0015\u0005a\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001y\u0001C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002/A\u0011\u0001\u0003G\u0005\u00033E\u0011A!\u00168ji\u0006!2-\u00198TY&\u001cWmV3je\u0012\u0014vn^:`'6+B\u0001H\u00154mQ\u0019QD\u0013*\u0011\ry\t3\u0005O$$\u001b\u0005y\"B\u0001\u0011\n\u0003\u001d\u0019X\u000f\u001d9peRL!AI\u0010\u0003\u0013\r\u000bgn\u00157jG\u0016\u0014\u0004#\u0002\u0013&OI*T\"A\u0005\n\u0005\u0019J!aC*mS\u000e,W*\u0019;sSb\u0004\"\u0001K\u0015\r\u0001\u0011)!F\u0001b\u0001W\t\u00111*M\t\u0003Y=\u0002\"\u0001E\u0017\n\u00059\n\"a\u0002(pi\"Lgn\u001a\t\u0003!AJ!!M\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002)g\u0011)AG\u0001b\u0001W\t\u00111J\r\t\u0003QY\"Qa\u000e\u0002C\u0002-\u0012\u0011A\u0016\t\u0004s\u0005#eB\u0001\u001e@\u001d\tYd(D\u0001=\u0015\tiT\"\u0001\u0004=e>|GOP\u0005\u0002%%\u0011\u0001)E\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00115IA\u0002TKFT!\u0001Q\t\u0011\u0005A)\u0015B\u0001$\u0012\u0005\rIe\u000e\u001e\b\u0003s!K!!S\"\u0002\u0019\u0011\u001aw\u000e\\8oI\r|Gn\u001c8\t\u000f-\u0013\u0011\u0011!a\u0002\u0019\u0006YQM^5eK:\u001cW\rJ\u00194!\ri\u0005+N\u0007\u0002\u001d*\u0011qjC\u0001\u0005[\u0006$\b.\u0003\u0002R\u001d\nA1+Z7je&tw\rC\u0004T\u0005\u0005\u0005\t9\u0001+\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000e\t\u0004+b+T\"\u0001,\u000b\u0005]\u000b\u0012a\u0002:fM2,7\r^\u0005\u00033Z\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u0015G\u0006t7\u000b\\5dK^+\u0017N\u001d3D_2\u001cxlU'\u0016\tq\u0003'\r\u001a\u000b\u0004;\u0016D\u0007C\u0002\u0010\"=\u001eCd\fE\u0003%K}\u000b7\r\u0005\u0002)A\u0012)!f\u0001b\u0001WA\u0011\u0001F\u0019\u0003\u0006i\r\u0011\ra\u000b\t\u0003Q\u0011$QaN\u0002C\u0002-BqAZ\u0002\u0002\u0002\u0003\u000fq-A\u0006fm&$WM\\2fIE*\u0004cA'QG\"9\u0011nAA\u0001\u0002\bQ\u0017aC3wS\u0012,gnY3%cY\u00022!\u0016-d\u0003UA\u0017M\u001c3i_2$7)\u00198NCB\u0014vn^:`'6+B!\\<zwV\ta\u000e\u0005\u0004peVd\u0018q\u0001\b\u0003=AL!!]\u0010\u0002\u001f\r\u000bgnQ8mY\u0006\u00048/Z!ySNL!a\u001d;\u0003\u0011!\u000bg\u000e\u001a%pY\u0012T!!]\u0010\u0011\u000b\u0011*c\u000f\u001f>\u0011\u0005!:H!\u0002\u0016\u0005\u0005\u0004Y\u0003C\u0001\u0015z\t\u0015!DA1\u0001,!\tA3\u0010B\u00038\t\t\u00071FD\u0002~\u0003\u0003q!\u0001\n@\n\u0005}L\u0011\u0001B!ySNLA!a\u0001\u0002\u0006\u0005\u0011q\f\r\u0006\u0003\u007f&\u0001B\u0001JA\u0005u&\u0019\u00111B\u0005\u0003\rY+7\r^8s\u0003UA\u0017M\u001c3i_2$7)\u00198NCB\u001cu\u000e\\:`'6+\u0002\"!\u0005\u0002\u001a\u0005u\u0011\u0011E\u000b\u0003\u0003'\u0001\u0002b\u001c:\u0002\u0016\u0005\r\u0012\u0011\u0006\t\tI\u0015\n9\"a\u0007\u0002 A\u0019\u0001&!\u0007\u0005\u000b)*!\u0019A\u0016\u0011\u0007!\ni\u0002B\u00035\u000b\t\u00071\u0006E\u0002)\u0003C!QaN\u0003C\u0002-r1!`A\u0013\u0013\u0011\t9#!\u0002\u0002\u0005}\u000b\u0004#\u0002\u0013\u0002\n\u0005}\u0011aG2b]\u000e{G\u000e\\1qg\u0016\u0014vn^:`'2L7-Z'biJL\u00070\u0006\u0006\u00020\u0005m\u0012qHA\"\u0003\u0013\"\"\"!\r\u0002V\u0005m\u0013\u0011MA4!1q\u00121GA\u001cy\u0006\u0015\u0013qIA'\u0013\r\t)d\b\u0002\u0010\u0007\u0006t7i\u001c7mCB\u001cX-\u0011=jgBAA%JA\u001d\u0003{\t\t\u0005E\u0002)\u0003w!QA\u000b\u0004C\u0002-\u00022\u0001KA \t\u0015!dA1\u0001,!\rA\u00131\t\u0003\u0006o\u0019\u0011\ra\u000b\t\u0006I\u0005%\u0011\u0011\t\t\u0004Q\u0005%CABA&\r\t\u00071FA\u0001S!\u0015!\u0013qJA*\u0013\r\t\t&\u0003\u0002\n)J\fgn\u001d9pg\u0016\u0004R\u0001JA\u0005\u0003\u000fB\u0011\"a\u0016\u0007\u0003\u0003\u0005\u001d!!\u0017\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0005\u001bB\u000b\t\u0005C\u0005\u0002^\u0019\t\t\u0011q\u0001\u0002`\u0005YQM^5eK:\u001cW\rJ\u00199!\u0011)\u0006,!\u0011\t\u0013\u0005\rd!!AA\u0004\u0005\u0015\u0014aC3wS\u0012,gnY3%ce\u0002B!\u0016-\u0002H!I\u0011\u0011\u000e\u0004\u0002\u0002\u0003\u000f\u00111N\u0001\fKZLG-\u001a8dK\u0012\u0012\u0004\u0007\u0005\u0004\u0002n\u0005M\u0014qI\u0007\u0003\u0003_R1!!\u001d\f\u0003\u001d\u0019Ho\u001c:bO\u0016LA!!\u001e\u0002p\t!!,\u001a:p\u0003m\u0019\u0017M\\\"pY2\f\u0007o]3D_2\u001cxl\u00157jG\u0016l\u0015\r\u001e:jqVQ\u00111PAB\u0003\u000f\u000bY)!%\u0015\u0015\u0005u\u0014QSAN\u0003C\u000b9\u000bE\u0007\u001f\u0003g\ty(a\t\u0002\u000e\u0006=\u00151\u0013\t\tI\u0015\n\t)!\"\u0002\nB\u0019\u0001&a!\u0005\u000b):!\u0019A\u0016\u0011\u0007!\n9\tB\u00035\u000f\t\u00071\u0006E\u0002)\u0003\u0017#QaN\u0004C\u0002-\u0002R\u0001JA\u0005\u0003\u0013\u00032\u0001KAI\t\u0019\tYe\u0002b\u0001WA)A%!\u0003\u0002\u0010\"I\u0011qS\u0004\u0002\u0002\u0003\u000f\u0011\u0011T\u0001\fKZLG-\u001a8dK\u0012\u0012\u0014\u0007\u0005\u0003N!\u0006%\u0005\"CAO\u000f\u0005\u0005\t9AAP\u0003-)g/\u001b3f]\u000e,GE\r\u001a\u0011\tUC\u0016\u0011\u0012\u0005\n\u0003G;\u0011\u0011!a\u0002\u0003K\u000b1\"\u001a<jI\u0016t7-\u001a\u00133gA!Q\u000bWAH\u0011%\tIkBA\u0001\u0002\b\tY+A\u0006fm&$WM\\2fII\"\u0004CBA7\u0003g\nyID\u0002%\u0003_K1!!-\n\u0003-\u0019F.[2f\u001b\u0006$(/\u001b="
)
public interface LowPrioritySliceMatrix {
   // $FF: synthetic method
   static CanSlice2 canSliceWeirdRows_SM$(final LowPrioritySliceMatrix $this, final Semiring evidence$13, final ClassTag evidence$14) {
      return $this.canSliceWeirdRows_SM(evidence$13, evidence$14);
   }

   default CanSlice2 canSliceWeirdRows_SM(final Semiring evidence$13, final ClassTag evidence$14) {
      return new CanSlice2(evidence$13, evidence$14) {
         private final Semiring evidence$13$1;
         private final ClassTag evidence$14$1;

         public SliceMatrix apply(final SliceMatrix from, final Seq rows, final .colon.colon ignored) {
            return new SliceMatrix(from.tensor(), (IndexedSeq)SliceUtils$.MODULE$.mapRowSeq(rows, from.rows()).map(from.slice1()), from.slice2(), this.evidence$13$1, this.evidence$14$1);
         }

         public {
            this.evidence$13$1 = evidence$13$1;
            this.evidence$14$1 = evidence$14$1;
         }
      };
   }

   // $FF: synthetic method
   static CanSlice2 canSliceWeirdCols_SM$(final LowPrioritySliceMatrix $this, final Semiring evidence$15, final ClassTag evidence$16) {
      return $this.canSliceWeirdCols_SM(evidence$15, evidence$16);
   }

   default CanSlice2 canSliceWeirdCols_SM(final Semiring evidence$15, final ClassTag evidence$16) {
      return new CanSlice2(evidence$15, evidence$16) {
         private final Semiring evidence$15$1;
         private final ClassTag evidence$16$1;

         public SliceMatrix apply(final SliceMatrix from, final .colon.colon ignored, final Seq cols) {
            return new SliceMatrix(from.tensor(), from.slice1(), (IndexedSeq)SliceUtils$.MODULE$.mapColumnSeq(cols, from.cols()).map(from.slice2()), this.evidence$15$1, this.evidence$16$1);
         }

         public {
            this.evidence$15$1 = evidence$15$1;
            this.evidence$16$1 = evidence$16$1;
         }
      };
   }

   // $FF: synthetic method
   static CanCollapseAxis.HandHold handholdCanMapRows_SM$(final LowPrioritySliceMatrix $this) {
      return $this.handholdCanMapRows_SM();
   }

   default CanCollapseAxis.HandHold handholdCanMapRows_SM() {
      return new CanCollapseAxis.HandHold();
   }

   // $FF: synthetic method
   static CanCollapseAxis.HandHold handholdCanMapCols_SM$(final LowPrioritySliceMatrix $this) {
      return $this.handholdCanMapCols_SM();
   }

   default CanCollapseAxis.HandHold handholdCanMapCols_SM() {
      return new CanCollapseAxis.HandHold();
   }

   // $FF: synthetic method
   static CanCollapseAxis canCollapseRows_SliceMatrix$(final LowPrioritySliceMatrix $this, final Semiring evidence$17, final ClassTag evidence$18, final ClassTag evidence$19, final Zero evidence$20) {
      return $this.canCollapseRows_SliceMatrix(evidence$17, evidence$18, evidence$19, evidence$20);
   }

   default CanCollapseAxis canCollapseRows_SliceMatrix(final Semiring evidence$17, final ClassTag evidence$18, final ClassTag evidence$19, final Zero evidence$20) {
      return new CanCollapseAxis(evidence$19, evidence$20, evidence$17, evidence$18) {
         // $FF: synthetic field
         private final SliceMatrix$ $outer;
         private final ClassTag evidence$19$1;
         private final Zero evidence$20$1;
         private final Semiring evidence$17$1;
         private final ClassTag evidence$18$1;

         public Transpose apply(final SliceMatrix from, final Axis._0$ axis, final Function1 f) {
            Vector result = Vector$.MODULE$.zeros(from.cols(), this.evidence$19$1, this.evidence$20$1);
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               result.update(BoxesRunTime.boxToInteger(index$macro$2), f.apply(from.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol(this.evidence$17$1, this.evidence$18$1))));
            }

            return (Transpose)result.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()));
         }

         public {
            if (LowPrioritySliceMatrix.this == null) {
               throw null;
            } else {
               this.$outer = LowPrioritySliceMatrix.this;
               this.evidence$19$1 = evidence$19$1;
               this.evidence$20$1 = evidence$20$1;
               this.evidence$17$1 = evidence$17$1;
               this.evidence$18$1 = evidence$18$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanCollapseAxis canCollapseCols_SliceMatrix$(final LowPrioritySliceMatrix $this, final Semiring evidence$21, final ClassTag evidence$22, final ClassTag evidence$23, final Zero evidence$24) {
      return $this.canCollapseCols_SliceMatrix(evidence$21, evidence$22, evidence$23, evidence$24);
   }

   default CanCollapseAxis canCollapseCols_SliceMatrix(final Semiring evidence$21, final ClassTag evidence$22, final ClassTag evidence$23, final Zero evidence$24) {
      return new CanCollapseAxis(evidence$23, evidence$24, evidence$21, evidence$22) {
         // $FF: synthetic field
         private final SliceMatrix$ $outer;
         private final ClassTag evidence$23$1;
         private final Zero evidence$24$1;
         private final Semiring evidence$21$1;
         private final ClassTag evidence$22$1;

         public Vector apply(final SliceMatrix from, final Axis._1$ axis, final Function1 f) {
            Vector result = Vector$.MODULE$.zeros(from.rows(), this.evidence$23$1, this.evidence$24$1);
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.rows()).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> result.update(BoxesRunTime.boxToInteger(r), f.apply(((ImmutableNumericOps)from.apply(BoxesRunTime.boxToInteger(r), scala.package..MODULE$.$colon$colon(), this.$outer.canSliceRow(this.evidence$21$1, this.evidence$22$1))).t(HasOps$.MODULE$.canUntranspose()))));
            return result;
         }

         public {
            if (LowPrioritySliceMatrix.this == null) {
               throw null;
            } else {
               this.$outer = LowPrioritySliceMatrix.this;
               this.evidence$23$1 = evidence$23$1;
               this.evidence$24$1 = evidence$24$1;
               this.evidence$21$1 = evidence$21$1;
               this.evidence$22$1 = evidence$22$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final LowPrioritySliceMatrix $this) {
   }
}
