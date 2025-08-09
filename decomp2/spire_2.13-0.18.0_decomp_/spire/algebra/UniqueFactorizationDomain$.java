package spire.algebra;

import spire.math.Integral;
import spire.math.SafeLong$;

public final class UniqueFactorizationDomain$ {
   public static final UniqueFactorizationDomain$ MODULE$ = new UniqueFactorizationDomain$();

   public UniqueFactorizationDomain apply(final UniqueFactorizationDomain ev) {
      return ev;
   }

   public UniqueFactorizationDomain uniqueFactorizationDomainFromIntegral(final Integral A) {
      return new UniqueFactorizationDomain(A) {
         private final Integral A$1;

         public boolean isPrime$mcB$sp(final byte a) {
            return UniqueFactorizationDomain.isPrime$mcB$sp$(this, a);
         }

         public boolean isPrime$mcI$sp(final int a) {
            return UniqueFactorizationDomain.isPrime$mcI$sp$(this, a);
         }

         public boolean isPrime$mcJ$sp(final long a) {
            return UniqueFactorizationDomain.isPrime$mcJ$sp$(this, a);
         }

         public boolean isPrime$mcS$sp(final short a) {
            return UniqueFactorizationDomain.isPrime$mcS$sp$(this, a);
         }

         public UniqueFactorizationDomain.Decomposition factor$mcB$sp(final byte a) {
            return UniqueFactorizationDomain.factor$mcB$sp$(this, a);
         }

         public UniqueFactorizationDomain.Decomposition factor$mcI$sp(final int a) {
            return UniqueFactorizationDomain.factor$mcI$sp$(this, a);
         }

         public UniqueFactorizationDomain.Decomposition factor$mcJ$sp(final long a) {
            return UniqueFactorizationDomain.factor$mcJ$sp$(this, a);
         }

         public UniqueFactorizationDomain.Decomposition factor$mcS$sp(final short a) {
            return UniqueFactorizationDomain.factor$mcS$sp$(this, a);
         }

         public boolean isPrime(final Object a) {
            return SafeLong$.MODULE$.apply(this.A$1.toBigInt(a)).isPrime();
         }

         public UniqueFactorizationDomain.Decomposition factor(final Object a) {
            return new UniqueFactorizationDomain.WrapDecomposition(SafeLong$.MODULE$.apply(this.A$1.toBigInt(a)).factor(), this.A$1);
         }

         public {
            this.A$1 = A$1;
         }
      };
   }

   private UniqueFactorizationDomain$() {
   }
}
