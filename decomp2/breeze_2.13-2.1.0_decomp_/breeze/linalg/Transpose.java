package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec\u0001B\f\u0019\u0005vA\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t\r\u0002\u0011\t\u0012)A\u0005U!)q\t\u0001C\u0001\u0011\")!\n\u0001C\u0001\u0017\"9A\nAA\u0001\n\u0003i\u0005bB*\u0001#\u0003%\t\u0001\u0016\u0005\bC\u0002\t\t\u0011\"\u0011c\u0011\u001dY\u0007!!A\u0005\u00021Dq\u0001\u001d\u0001\u0002\u0002\u0013\u0005\u0011\u000fC\u0004u\u0001\u0005\u0005I\u0011I;\t\u000fq\u0004\u0011\u0011!C\u0001{\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0017\u0001\u0011\u0011!C!\u0003\u001bA\u0011\"a\u0004\u0001\u0003\u0003%\t%!\u0005\t\u0013\u0005M\u0001!!A\u0005B\u0005Uq!CA\r1\u0005\u0005\t\u0012AA\u000e\r!9\u0002$!A\t\u0002\u0005u\u0001BB$\u0012\t\u0003\tI\u0003C\u0005\u0002\u0010E\t\t\u0011\"\u0012\u0002\u0012!I\u00111F\t\u0002\u0002\u0013\u0005\u0015Q\u0006\u0005\n\u0003s\t\u0012\u0011!CA\u0003wA\u0011\"a\u0014\u0012\u0003\u0003%I!!\u0015\u0003\u0013Q\u0013\u0018M\\:q_N,'BA\r\u001b\u0003\u0019a\u0017N\\1mO*\t1$\u0001\u0004ce\u0016,'0Z\u0002\u0001+\tqBfE\u0003\u0001?\u0015*\u0004\b\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VM\u001a\t\u0004M\u001dJS\"\u0001\r\n\u0005!B\"A\u0003(v[\u0016\u0014\u0018nY(qgB\u0019a\u0005\u0001\u0016\u0011\u0005-bC\u0002\u0001\u0003\u0007[\u0001!)\u0019\u0001\u0018\u0003\u0003Q\u000b\"a\f\u001a\u0011\u0005\u0001\u0002\u0014BA\u0019\"\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001I\u001a\n\u0005Q\n#aA!osB\u0011\u0001EN\u0005\u0003o\u0005\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002:\u0003:\u0011!h\u0010\b\u0003wyj\u0011\u0001\u0010\u0006\u0003{q\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0012\n\u0005\u0001\u000b\u0013a\u00029bG.\fw-Z\u0005\u0003\u0005\u000e\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001Q\u0011\u0002\u000b%tg.\u001a:\u0016\u0003)\na!\u001b8oKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002*\u0013\")Ai\u0001a\u0001U\u0005!!/\u001a9s+\u0005I\u0013\u0001B2paf,\"AT)\u0015\u0005=\u0013\u0006c\u0001\u0014\u0001!B\u00111&\u0015\u0003\u0006[\u0015\u0011\rA\f\u0005\b\t\u0016\u0001\n\u00111\u0001Q\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!\u00161\u0016\u0003YS#AK,,\u0003a\u0003\"!\u00170\u000e\u0003iS!a\u0017/\u0002\u0013Ut7\r[3dW\u0016$'BA/\"\u0003)\tgN\\8uCRLwN\\\u0005\u0003?j\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015icA1\u0001/\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\r\u0005\u0002eS6\tQM\u0003\u0002gO\u0006!A.\u00198h\u0015\u0005A\u0017\u0001\u00026bm\u0006L!A[3\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005i\u0007C\u0001\u0011o\u0013\ty\u0017EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u00023e\"91/CA\u0001\u0002\u0004i\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001w!\r9(PM\u0007\u0002q*\u0011\u00110I\u0001\u000bG>dG.Z2uS>t\u0017BA>y\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007y\f\u0019\u0001\u0005\u0002!\u007f&\u0019\u0011\u0011A\u0011\u0003\u000f\t{w\u000e\\3b]\"91oCA\u0001\u0002\u0004\u0011\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2aYA\u0005\u0011\u001d\u0019H\"!AA\u00025\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002[\u0006AAo\\*ue&tw\rF\u0001d\u0003\u0019)\u0017/^1mgR\u0019a0a\u0006\t\u000fM|\u0011\u0011!a\u0001e\u0005IAK]1ogB|7/\u001a\t\u0003ME\u0019B!E\u0010\u0002 A!\u0011\u0011EA\u0014\u001b\t\t\u0019CC\u0002\u0002&\u001d\f!![8\n\u0007\t\u000b\u0019\u0003\u0006\u0002\u0002\u001c\u0005)\u0011\r\u001d9msV!\u0011qFA\u001b)\u0011\t\t$a\u000e\u0011\t\u0019\u0002\u00111\u0007\t\u0004W\u0005UB!B\u0017\u0015\u0005\u0004q\u0003B\u0002#\u0015\u0001\u0004\t\u0019$A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005u\u0012q\t\u000b\u0005\u0003\u007f\tI\u0005E\u0003!\u0003\u0003\n)%C\u0002\u0002D\u0005\u0012aa\u00149uS>t\u0007cA\u0016\u0002H\u0011)Q&\u0006b\u0001]!I\u00111J\u000b\u0002\u0002\u0003\u0007\u0011QJ\u0001\u0004q\u0012\u0002\u0004\u0003\u0002\u0014\u0001\u0003\u000b\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0015\u0011\u0007\u0011\f)&C\u0002\u0002X\u0015\u0014aa\u00142kK\u000e$\b"
)
public final class Transpose implements NumericOps, Product, Serializable {
   private final Object inner;

   public static Option unapply(final Transpose x$0) {
      return Transpose$.MODULE$.unapply(x$0);
   }

   public static Transpose apply(final Object inner) {
      return Transpose$.MODULE$.apply(inner);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public Object inner() {
      return this.inner;
   }

   public Transpose repr() {
      return this;
   }

   public Transpose copy(final Object inner) {
      return new Transpose(inner);
   }

   public Object copy$default$1() {
      return this.inner();
   }

   public String productPrefix() {
      return "Transpose";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.inner();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Transpose;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "inner";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label39: {
            boolean var2;
            if (x$1 instanceof Transpose) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Transpose var4 = (Transpose)x$1;
               if (BoxesRunTime.equals(this.inner(), var4.inner())) {
                  break label39;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Transpose(final Object inner) {
      this.inner = inner;
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      Product.$init$(this);
   }
}
