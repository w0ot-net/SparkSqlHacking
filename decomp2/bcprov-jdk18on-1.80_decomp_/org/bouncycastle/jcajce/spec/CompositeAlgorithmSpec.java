package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeAlgorithmSpec implements AlgorithmParameterSpec {
   private final List algorithmNames;
   private final List parameterSpecs;

   public CompositeAlgorithmSpec(Builder var1) {
      this.algorithmNames = Collections.unmodifiableList(new ArrayList(var1.algorithmNames));
      this.parameterSpecs = Collections.unmodifiableList(new ArrayList(var1.parameterSpecs));
   }

   public List getAlgorithmNames() {
      return this.algorithmNames;
   }

   public List getParameterSpecs() {
      return this.parameterSpecs;
   }

   public static class Builder {
      private List algorithmNames = new ArrayList();
      private List parameterSpecs = new ArrayList();

      public Builder add(String var1) {
         return this.add(var1, (AlgorithmParameterSpec)null);
      }

      public Builder add(String var1, AlgorithmParameterSpec var2) {
         if (!this.algorithmNames.contains(var1)) {
            this.algorithmNames.add(var1);
            this.parameterSpecs.add(var2);
            return this;
         } else {
            throw new IllegalStateException("cannot build with the same algorithm name added");
         }
      }

      public CompositeAlgorithmSpec build() {
         if (this.algorithmNames.isEmpty()) {
            throw new IllegalStateException("cannot call build with no algorithm names added");
         } else {
            return new CompositeAlgorithmSpec(this);
         }
      }
   }
}
