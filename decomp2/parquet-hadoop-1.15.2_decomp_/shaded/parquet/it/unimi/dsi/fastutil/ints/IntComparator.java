package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface IntComparator extends Comparator {
   int compare(int var1, int var2);

   default IntComparator reversed() {
      return IntComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Integer ok1, Integer ok2) {
      return this.compare(ok1, ok2);
   }

   default IntComparator thenComparing(IntComparator second) {
      return (IntComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof IntComparator ? this.thenComparing((IntComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$931d6fed$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/ints/IntComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(II)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/ints/IntComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/ints/IntComparator;II)I")) {
               IntComparator var10000 = (IntComparator)lambda.getCapturedArg(0);
               return (IntComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
