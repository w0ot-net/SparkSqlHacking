package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface DoubleComparator extends Comparator {
   int compare(double var1, double var3);

   default DoubleComparator reversed() {
      return DoubleComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Double ok1, Double ok2) {
      return this.compare(ok1, ok2);
   }

   default DoubleComparator thenComparing(DoubleComparator second) {
      return (DoubleComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof DoubleComparator ? this.thenComparing((DoubleComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$f8e9881b$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/doubles/DoubleComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(DD)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/doubles/DoubleComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/doubles/DoubleComparator;DD)I")) {
               DoubleComparator var10000 = (DoubleComparator)lambda.getCapturedArg(0);
               return (DoubleComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
