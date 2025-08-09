package shaded.parquet.it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface ShortComparator extends Comparator {
   int compare(short var1, short var2);

   default ShortComparator reversed() {
      return ShortComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Short ok1, Short ok2) {
      return this.compare(ok1, ok2);
   }

   default ShortComparator thenComparing(ShortComparator second) {
      return (ShortComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof ShortComparator ? this.thenComparing((ShortComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$953dd6d$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/shorts/ShortComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(SS)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/shorts/ShortComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/shorts/ShortComparator;SS)I")) {
               ShortComparator var10000 = (ShortComparator)lambda.getCapturedArg(0);
               return (ShortComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
