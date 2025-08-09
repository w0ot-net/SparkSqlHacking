package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface LongComparator extends Comparator {
   int compare(long var1, long var3);

   default LongComparator reversed() {
      return LongComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Long ok1, Long ok2) {
      return this.compare(ok1, ok2);
   }

   default LongComparator thenComparing(LongComparator second) {
      return (LongComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof LongComparator ? this.thenComparing((LongComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$3d6e68ef$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/longs/LongComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(JJ)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/longs/LongComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/longs/LongComparator;JJ)I")) {
               LongComparator var10000 = (LongComparator)lambda.getCapturedArg(0);
               return (LongComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
