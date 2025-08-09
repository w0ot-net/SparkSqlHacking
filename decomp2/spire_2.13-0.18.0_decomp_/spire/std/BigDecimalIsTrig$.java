package spire.std;

import java.io.Serializable;
import java.math.MathContext;
import scala.package.;
import scala.runtime.ModuleSerializationProxy;

public final class BigDecimalIsTrig$ implements Serializable {
   public static final BigDecimalIsTrig$ MODULE$ = new BigDecimalIsTrig$();

   public MathContext $lessinit$greater$default$1() {
      return .MODULE$.BigDecimal().defaultMathContext();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BigDecimalIsTrig$.class);
   }

   private BigDecimalIsTrig$() {
   }
}
