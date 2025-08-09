package org.apache.spark.mllib.tree.configuration;

import java.io.Serializable;
import org.apache.spark.mllib.tree.impurity.Gini$;
import org.apache.spark.mllib.tree.impurity.Variance$;
import scala.Enumeration;
import scala.MatchError;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class Strategy$ implements Serializable {
   public static final Strategy$ MODULE$ = new Strategy$();

   public int $lessinit$greater$default$4() {
      return 2;
   }

   public int $lessinit$greater$default$5() {
      return 32;
   }

   public Enumeration.Value $lessinit$greater$default$6() {
      return QuantileStrategy$.MODULE$.Sort();
   }

   public Map $lessinit$greater$default$7() {
      return (Map).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   public int $lessinit$greater$default$8() {
      return 1;
   }

   public double $lessinit$greater$default$9() {
      return (double)0.0F;
   }

   public int $lessinit$greater$default$10() {
      return 256;
   }

   public double $lessinit$greater$default$11() {
      return (double)1.0F;
   }

   public boolean $lessinit$greater$default$12() {
      return false;
   }

   public int $lessinit$greater$default$13() {
      return 10;
   }

   public double $lessinit$greater$default$14() {
      return (double)0.0F;
   }

   public boolean $lessinit$greater$default$15() {
      return false;
   }

   public Strategy defaultStrategy(final String algo) {
      return this.defaultStrategy(Algo$.MODULE$.fromString(algo));
   }

   public Strategy defaultStrategy(final Enumeration.Value algo) {
      Enumeration.Value var10000 = Algo$.MODULE$.Classification();
      if (var10000 == null) {
         if (algo == null) {
            return new Strategy(Algo$.MODULE$.Classification(), Gini$.MODULE$, 10, 2, this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8(), this.$lessinit$greater$default$9(), this.$lessinit$greater$default$10(), this.$lessinit$greater$default$11(), this.$lessinit$greater$default$12(), this.$lessinit$greater$default$13(), this.$lessinit$greater$default$14(), this.$lessinit$greater$default$15());
         }
      } else if (var10000.equals(algo)) {
         return new Strategy(Algo$.MODULE$.Classification(), Gini$.MODULE$, 10, 2, this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8(), this.$lessinit$greater$default$9(), this.$lessinit$greater$default$10(), this.$lessinit$greater$default$11(), this.$lessinit$greater$default$12(), this.$lessinit$greater$default$13(), this.$lessinit$greater$default$14(), this.$lessinit$greater$default$15());
      }

      var10000 = Algo$.MODULE$.Regression();
      if (var10000 == null) {
         if (algo == null) {
            return new Strategy(Algo$.MODULE$.Regression(), Variance$.MODULE$, 10, 0, this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8(), this.$lessinit$greater$default$9(), this.$lessinit$greater$default$10(), this.$lessinit$greater$default$11(), this.$lessinit$greater$default$12(), this.$lessinit$greater$default$13(), this.$lessinit$greater$default$14(), this.$lessinit$greater$default$15());
         }
      } else if (var10000.equals(algo)) {
         return new Strategy(Algo$.MODULE$.Regression(), Variance$.MODULE$, 10, 0, this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8(), this.$lessinit$greater$default$9(), this.$lessinit$greater$default$10(), this.$lessinit$greater$default$11(), this.$lessinit$greater$default$12(), this.$lessinit$greater$default$13(), this.$lessinit$greater$default$14(), this.$lessinit$greater$default$15());
      }

      throw new MatchError(algo);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Strategy$.class);
   }

   private Strategy$() {
   }
}
