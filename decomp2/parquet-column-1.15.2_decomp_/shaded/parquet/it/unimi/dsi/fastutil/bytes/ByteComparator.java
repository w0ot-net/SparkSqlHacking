package shaded.parquet.it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;

@FunctionalInterface
public interface ByteComparator extends Comparator {
   int compare(byte var1, byte var2);

   default ByteComparator reversed() {
      return ByteComparators.oppositeComparator(this);
   }

   /** @deprecated */
   @Deprecated
   default int compare(Byte ok1, Byte ok2) {
      return this.compare(ok1, ok2);
   }

   default ByteComparator thenComparing(ByteComparator second) {
      return (ByteComparator)((Serializable)((k1, k2) -> {
         int comp = this.compare(k1, k2);
         return comp == 0 ? second.compare(k1, k2) : comp;
      }));
   }

   default Comparator thenComparing(Comparator second) {
      return (Comparator)(second instanceof ByteComparator ? this.thenComparing((ByteComparator)second) : super.thenComparing(second));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$thenComparing$6e387fbf$1":
            if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("shaded/parquet/it/unimi/dsi/fastutil/bytes/ByteComparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(BB)I") && lambda.getImplClass().equals("shaded/parquet/it/unimi/dsi/fastutil/bytes/ByteComparator") && lambda.getImplMethodSignature().equals("(Lit/unimi/dsi/fastutil/bytes/ByteComparator;BB)I")) {
               ByteComparator var10000 = (ByteComparator)lambda.getCapturedArg(0);
               return (ByteComparator)(k1, k2) -> {
                  int comp = this.compare(k1, k2);
                  return comp == 0 ? second.compare(k1, k2) : comp;
               };
            }
         default:
            throw new IllegalArgumentException("Invalid lambda deserialization");
      }
   }
}
