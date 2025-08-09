package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface BooleanComparator extends Comparator {
   int compare(boolean var1, boolean var2);

   default BooleanComparator reversed() {
      return BooleanComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Boolean ok1, Boolean ok2) {
      return this.compare(ok1, ok2);
   }

   default BooleanComparator thenComparing(BooleanComparator second) {
      return (BooleanComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof BooleanComparator ? this.thenComparing((BooleanComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$e8be742d$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/booleans/BooleanComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(ZZ)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/booleans/BooleanComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/booleans/BooleanComparator;ZZ)I")) {
               BooleanComparator var10000 = (BooleanComparator)lambda.getCapturedArg(0);
               return (BooleanComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
