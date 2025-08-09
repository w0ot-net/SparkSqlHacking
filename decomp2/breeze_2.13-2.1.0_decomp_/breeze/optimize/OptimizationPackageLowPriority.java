package breeze.optimize;

import breeze.math.MutableEnumeratedCoordinateField;
import breeze.math.MutableFiniteCoordinateField;
import breeze.util.Implicits$;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005daB\u0005\u000b!\u0003\r\tc\u0004\u0005\u00065\u0001!\ta\u0007\u0004\u0005?\u0001\u0001\u0001\u0005\u0003\u0005\\\u0005\t\u0005\t\u0015a\u0003]\u0011!A'A!A!\u0002\u0017I\u0007\"B8\u0003\t\u0003\u0001\b\"\u0002<\u0003\t\u00039\bbBA\u0005\u0005\u0011\u0005\u00131\u0002\u0005\b\u0003s\u0001A1AA\u001e\u0005yy\u0005\u000f^5nSj\fG/[8o!\u0006\u001c7.Y4f\u0019><\bK]5pe&$\u0018P\u0003\u0002\f\u0019\u0005Aq\u000e\u001d;j[&TXMC\u0001\u000e\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"a\u0006\r\u000e\u0003)I!!\u0007\u0006\u0003?=\u0003H/[7ju\u0006$\u0018n\u001c8QC\u000e\\\u0017mZ3M_^\u0004&/[8sSRL('\u0001\u0004%S:LG\u000f\n\u000b\u00029A\u0011\u0011#H\u0005\u0003=I\u0011A!\u00168ji\nABJ\u0011$H'6Kg.[7ju\u0006$\u0018n\u001c8QC\u000e\\\u0017mZ3\u0016\t\u0005:\u0013gY\n\u0004\u0005A\u0011\u0003#B\f$KA\u001a\u0014B\u0001\u0013\u000b\u0005mIE/\u001a:bE2,w\n\u001d;j[&T\u0018\r^5p]B\u000b7m[1hKB\u0011ae\n\u0007\u0001\t\u0015A#A1\u0001*\u0005\t!e)\u0005\u0002+[A\u0011\u0011cK\u0005\u0003YI\u0011qAT8uQ&tw\r\u0005\u0002\u0012]%\u0011qF\u0005\u0002\u0004\u0003:L\bC\u0001\u00142\t\u0015\u0011$A1\u0001*\u0005\u00191Vm\u0019;peB\u0012AG\u0010\t\u0006ka\u00024\b\u0016\b\u0003/YJ!a\u000e\u0006\u0002'\u0019K'o\u001d;Pe\u0012,'/T5oS6L'0\u001a:\n\u0005eR$!B*uCR,'BA\u001c\u000b!\ta\u0004K\u0004\u0002>\u001dB\u0011aE\u0010\u0003\n\u007f\u0001\t\t\u0011!A\u0003\u0002\t\u0013qaX\u0019/if\u0004X-\u0003\u0002B\u0015\t\u0019b)\u001b:ti>\u0013H-\u001a:NS:LW.\u001b>feF\u0011!f\u0011\n\u0004\t\"[e\u0001B#\u0001\u0001\r\u0013A\u0002\u0010:fM&tW-\\3oizR!a\u0012\b\u0002\rq\u0012xn\u001c;?!\r9\u0012\nM\u0005\u0003\u0015*\u0011Q\u0001\u0014\"G\u000fN\u0003\"!\u0005'\n\u00055\u0013\"!C*j]\u001edW\r^8o\u0013\ty\u0005)\u0001\td_:4XM]4f]\u000e,7\t[3dW&\u0011\u0011K\u0015\u0002\u0005\u0013:4w.\u0003\u0002Tu\t\u00012i\u001c8wKJ<WM\\2f\u0007\",7m\u001b\t\u0004+b\u0003dBA\fW\u0013\t9&\"A\u0003M\u0005\u001a;5+\u0003\u0002Z5\nI\u0012\t\u001d9s_bLW.\u0019;f\u0013:4XM]:f\u0011\u0016\u001c8/[1o\u0015\t9&\"A\u0003ta\u0006\u001cW\rE\u0003^AB\u0012W-D\u0001_\u0015\tyF\"\u0001\u0003nCRD\u0017BA1_\u0005\u0001jU\u000f^1cY\u0016,e.^7fe\u0006$X\rZ\"p_J$\u0017N\\1uK\u001aKW\r\u001c3\u0011\u0005\u0019\u001aG!\u00023\u0003\u0005\u0004I#!A%\u0011\u0005E1\u0017BA4\u0013\u0005\u0019!u.\u001e2mK\u0006\u0011AM\u001a\t\u0005#),C.\u0003\u0002l%\t\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\t\u0004/5\u0004\u0014B\u00018\u000b\u00051!\u0015N\u001a4Gk:\u001cG/[8o\u0003\u0019a\u0014N\\5u}Q\t\u0011\u000fF\u0002siV\u0004Ra\u001d\u0002&a\tl\u0011\u0001\u0001\u0005\u00067\u0016\u0001\u001d\u0001\u0018\u0005\u0006Q\u0016\u0001\u001d![\u0001\t[&t\u0017.\\5{KR!\u0001\u0007\u001f>}\u0011\u0015Ih\u00011\u0001&\u0003\t1g\u000eC\u0003|\r\u0001\u0007\u0001'\u0001\u0003j]&$\b\"B?\u0007\u0001\u0004q\u0018aB8qi&|gn\u001d\t\u0005#}\f\u0019!C\u0002\u0002\u0002I\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?!\r9\u0012QA\u0005\u0004\u0003\u000fQ!AE(qi&l\u0017N_1uS>tw\n\u001d;j_:\f!\"\u001b;fe\u0006$\u0018n\u001c8t)!\ti!a\r\u00026\u0005]\u0002CBA\b\u00037\t\tC\u0004\u0003\u0002\u0012\u0005]a\u0002BA\n\u0003+i\u0011AR\u0005\u0002'%\u0019\u0011\u0011\u0004\n\u0002\u000fA\f7m[1hK&!\u0011QDA\u0010\u0005!IE/\u001a:bi>\u0014(bAA\r%A\"\u00111EA\u0016!\u0019)\u0004\bMA\u0013)B\u0019\u0011q\u0005)\u000f\u0007\u0005%b\nE\u0002'\u0003W!!b\u0010\u0001\u0002\u0002\u0003\u0005)\u0011AA\u0017#\rQ\u0013q\u0006\n\u0005\u0003cA5JB\u0003F\u0001\u0001\ty\u0003C\u0003z\u000f\u0001\u0007Q\u0005C\u0003|\u000f\u0001\u0007\u0001\u0007C\u0003~\u000f\u0001\u0007a0\u0001\rmE\u001a<7/T5oS6L'0\u0019;j_:\u0004\u0016mY6bO\u0016,\u0002\"!\u0010\u0002D\u0005-\u0013q\t\u000b\u0007\u0003\u007f\ti%!\u0016\u0011\u0011M\u0014\u0011\u0011IA#\u0003\u0013\u00022AJA\"\t\u0015A\u0003B1\u0001*!\r1\u0013q\t\u0003\u0006e!\u0011\r!\u000b\t\u0004M\u0005-C!\u00023\t\u0005\u0004I\u0003BB.\t\u0001\b\ty\u0005\u0005\u0005^\u0003#\n)%!\u0013f\u0013\r\t\u0019F\u0018\u0002\u001d\u001bV$\u0018M\u00197f\r&t\u0017\u000e^3D_>\u0014H-\u001b8bi\u00164\u0015.\u001a7e\u0011\u0019A\u0007\u0002q\u0001\u0002XA1\u0011C[A!\u00033\u0002BaF7\u0002F%\u001a\u0001!!\u0018\u000b\u0007\u0005}#\"A\nPaRLW.\u001b>bi&|g\u000eU1dW\u0006<W\r"
)
public interface OptimizationPackageLowPriority extends OptimizationPackageLowPriority2 {
   // $FF: synthetic method
   static LBFGSMinimizationPackage lbfgsMinimizationPackage$(final OptimizationPackageLowPriority $this, final MutableFiniteCoordinateField space, final .less.colon.less df) {
      return $this.lbfgsMinimizationPackage(space, df);
   }

   default LBFGSMinimizationPackage lbfgsMinimizationPackage(final MutableFiniteCoordinateField space, final .less.colon.less df) {
      return new LBFGSMinimizationPackage(space, df);
   }

   static void $init$(final OptimizationPackageLowPriority $this) {
   }

   public class LBFGSMinimizationPackage implements IterableOptimizationPackage {
      private final MutableEnumeratedCoordinateField space;
      private final .less.colon.less df;
      // $FF: synthetic field
      public final OptimizationPackageLowPriority $outer;

      public Object minimize(final Object fn, final Object init, final Seq options) {
         return ((FirstOrderMinimizer.State)Implicits$.MODULE$.scEnrichIterator(this.iterations(fn, init, options)).last()).x();
      }

      public Iterator iterations(final Object fn, final Object init, final Seq options) {
         return ((FirstOrderMinimizer.OptParams)options.foldLeft(new FirstOrderMinimizer.OptParams(FirstOrderMinimizer.OptParams$.MODULE$.apply$default$1(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$2(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$3(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$4(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$5(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$6(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$7(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$8()), (a, b) -> (FirstOrderMinimizer.OptParams)b.apply(a))).iterations((DiffFunction)(new CachedDiffFunction((DiffFunction)this.df.apply(fn), this.space.copy())), init, (MutableEnumeratedCoordinateField)this.space);
      }

      // $FF: synthetic method
      public OptimizationPackageLowPriority breeze$optimize$OptimizationPackageLowPriority$LBFGSMinimizationPackage$$$outer() {
         return this.$outer;
      }

      public LBFGSMinimizationPackage(final MutableEnumeratedCoordinateField space, final .less.colon.less df) {
         this.space = space;
         this.df = df;
         if (OptimizationPackageLowPriority.this == null) {
            throw null;
         } else {
            this.$outer = OptimizationPackageLowPriority.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
