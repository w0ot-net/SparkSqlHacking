package breeze.integrate.quasimontecarlo;

import breeze.stats.distributions.HasInverseCdf;
import scala.Function1;
import scala.collection.immutable.Seq;

public final class package$ implements ProvidesTransformedQuasiMonteCarlo {
   public static final package$ MODULE$ = new package$();
   private static volatile ProvidesTransformedQuasiMonteCarlo.DistributionRandomVariableSpec$ DistributionRandomVariableSpec$module;
   private static volatile ProvidesTransformedQuasiMonteCarlo.RejectionSampledGammaQuasiRandomVariable$ RejectionSampledGammaQuasiRandomVariable$module;
   private static volatile ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaLeq1$ GammaQuasiRandomVariableSpecAlphaLeq1$module;
   private static volatile ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaGeq1$ GammaQuasiRandomVariableSpecAlphaGeq1$module;

   static {
      ProvidesTransformedQuasiMonteCarlo.$init$(MODULE$);
   }

   public double quasiMonteCarloIntegrate(final Function1 f, final Seq variables, final long numSamples) {
      return ProvidesTransformedQuasiMonteCarlo.quasiMonteCarloIntegrate$(this, f, variables, numSamples);
   }

   public ProvidesTransformedQuasiMonteCarlo.DistributionRandomVariableSpec$ DistributionRandomVariableSpec() {
      if (DistributionRandomVariableSpec$module == null) {
         this.DistributionRandomVariableSpec$lzycompute$1();
      }

      return DistributionRandomVariableSpec$module;
   }

   public ProvidesTransformedQuasiMonteCarlo.RejectionSampledGammaQuasiRandomVariable$ RejectionSampledGammaQuasiRandomVariable() {
      if (RejectionSampledGammaQuasiRandomVariable$module == null) {
         this.RejectionSampledGammaQuasiRandomVariable$lzycompute$1();
      }

      return RejectionSampledGammaQuasiRandomVariable$module;
   }

   public ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaLeq1$ GammaQuasiRandomVariableSpecAlphaLeq1() {
      if (GammaQuasiRandomVariableSpecAlphaLeq1$module == null) {
         this.GammaQuasiRandomVariableSpecAlphaLeq1$lzycompute$1();
      }

      return GammaQuasiRandomVariableSpecAlphaLeq1$module;
   }

   public ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaGeq1$ GammaQuasiRandomVariableSpecAlphaGeq1() {
      if (GammaQuasiRandomVariableSpecAlphaGeq1$module == null) {
         this.GammaQuasiRandomVariableSpecAlphaGeq1$lzycompute$1();
      }

      return GammaQuasiRandomVariableSpecAlphaGeq1$module;
   }

   public HasInverseCdf ToQuasi(final HasInverseCdf icdfProvider) {
      return icdfProvider;
   }

   private final void DistributionRandomVariableSpec$lzycompute$1() {
      synchronized(this){}

      try {
         if (DistributionRandomVariableSpec$module == null) {
            DistributionRandomVariableSpec$module = new ProvidesTransformedQuasiMonteCarlo.DistributionRandomVariableSpec$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void RejectionSampledGammaQuasiRandomVariable$lzycompute$1() {
      synchronized(this){}

      try {
         if (RejectionSampledGammaQuasiRandomVariable$module == null) {
            RejectionSampledGammaQuasiRandomVariable$module = new ProvidesTransformedQuasiMonteCarlo.RejectionSampledGammaQuasiRandomVariable$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void GammaQuasiRandomVariableSpecAlphaLeq1$lzycompute$1() {
      synchronized(this){}

      try {
         if (GammaQuasiRandomVariableSpecAlphaLeq1$module == null) {
            GammaQuasiRandomVariableSpecAlphaLeq1$module = new ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaLeq1$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void GammaQuasiRandomVariableSpecAlphaGeq1$lzycompute$1() {
      synchronized(this){}

      try {
         if (GammaQuasiRandomVariableSpecAlphaGeq1$module == null) {
            GammaQuasiRandomVariableSpecAlphaGeq1$module = new ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaGeq1$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private package$() {
   }
}
