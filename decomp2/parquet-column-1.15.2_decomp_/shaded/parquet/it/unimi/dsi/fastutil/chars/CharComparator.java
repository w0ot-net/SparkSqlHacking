package shaded.parquet.it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface CharComparator extends Comparator {
   int compare(char var1, char var2);

   default CharComparator reversed() {
      return CharComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Character ok1, Character ok2) {
      return this.compare(ok1, ok2);
   }

   default CharComparator thenComparing(CharComparator second) {
      return (CharComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof CharComparator ? this.thenComparing((CharComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$2b1ecd07$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/chars/CharComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(CC)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/chars/CharComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/chars/CharComparator;CC)I")) {
               CharComparator var10000 = (CharComparator)lambda.getCapturedArg(0);
               return (CharComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
