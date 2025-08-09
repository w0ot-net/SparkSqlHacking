package breeze.integrate.quasimontecarlo;

import breeze.stats.distributions.HasInverseCdf;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t<Q!\u0005\n\t\u0002e1Qa\u0007\n\t\u0002qAQAJ\u0001\u0005\u0002\u001d2A\u0001K\u0001\u0004S!AQf\u0001BC\u0002\u0013\u0005a\u0006\u0003\u00058\u0007\t\u0005\t\u0015!\u00030\u0011\u001513\u0001\"\u00019\u0011\u0015a4\u0001\"\u0001>\u0011\u001d\t5!!A\u0005B\tCqAR\u0002\u0002\u0002\u0013\u0005siB\u0004Q\u0003\u0005\u0005\t\u0012A)\u0007\u000f!\n\u0011\u0011!E\u0001%\")ae\u0003C\u0001'\")Ak\u0003C\u0003+\"9\u0001lCA\u0001\n\u000bI\u0006bB.\f\u0003\u0003%)\u0001\u0018\u0005\b!\u0006\t\t\u0011b\u0001a\u0003\u001d\u0001\u0018mY6bO\u0016T!a\u0005\u000b\u0002\u001fE,\u0018m]5n_:$XmY1sY>T!!\u0006\f\u0002\u0013%tG/Z4sCR,'\"A\f\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AG\u0001\u000e\u0003I\u0011q\u0001]1dW\u0006<WmE\u0002\u0002;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007C\u0001\u000e%\u0013\t)#C\u0001\u0012Qe>4\u0018\u000eZ3t)J\fgn\u001d4pe6,G-U;bg&luN\u001c;f\u0007\u0006\u0014Hn\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003e\u0011q\u0001V8Rk\u0006\u001c\u0018n\u0005\u0002\u0004UA\u0011adK\u0005\u0003Y}\u0011a!\u00118z-\u0006d\u0017\u0001D5dI\u001a\u0004&o\u001c<jI\u0016\u0014X#A\u0018\u0011\u0005A*T\"A\u0019\u000b\u0005I\u001a\u0014!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u00025-\u0005)1\u000f^1ug&\u0011a'\r\u0002\u000e\u0011\u0006\u001c\u0018J\u001c<feN,7\t\u001a4\u0002\u001b%\u001cGM\u001a)s_ZLG-\u001a:!)\tI4\b\u0005\u0002;\u00075\t\u0011\u0001C\u0003.\r\u0001\u0007q&A\u0004u_F+\u0018m]5\u0016\u0003y\u0002\"AO \n\u0005\u0001##A\b#jgR\u0014\u0018NY;uS>t'+\u00198e_64\u0016M]5bE2,7\u000b]3d\u0003!A\u0017m\u001d5D_\u0012,G#A\"\u0011\u0005y!\u0015BA# \u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\u0005![\u0005C\u0001\u0010J\u0013\tQuDA\u0004C_>dW-\u00198\t\u000f1K\u0011\u0011!a\u0001\u001b\u0006\u0019\u0001\u0010J\u0019\u0011\u0005yq\u0015BA( \u0005\r\te._\u0001\b)>\fV/Y:j!\tQ4b\u0005\u0002\f;Q\t\u0011+A\tu_F+\u0018m]5%Kb$XM\\:j_:$\"A\u0010,\t\u000b]k\u0001\u0019A\u001d\u0002\u000b\u0011\"\b.[:\u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0003\u0005jCQa\u0016\bA\u0002e\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005u{FC\u0001%_\u0011\u001dau\"!AA\u00025CQaV\bA\u0002e\"\"!O1\t\u000b5\u0002\u0002\u0019A\u0018"
)
public final class package {
   public static HasInverseCdf ToQuasi(final HasInverseCdf icdfProvider) {
      return package$.MODULE$.ToQuasi(icdfProvider);
   }

   public static ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaGeq1$ GammaQuasiRandomVariableSpecAlphaGeq1() {
      return package$.MODULE$.GammaQuasiRandomVariableSpecAlphaGeq1();
   }

   public static ProvidesTransformedQuasiMonteCarlo.GammaQuasiRandomVariableSpecAlphaLeq1$ GammaQuasiRandomVariableSpecAlphaLeq1() {
      return package$.MODULE$.GammaQuasiRandomVariableSpecAlphaLeq1();
   }

   public static ProvidesTransformedQuasiMonteCarlo.RejectionSampledGammaQuasiRandomVariable$ RejectionSampledGammaQuasiRandomVariable() {
      return package$.MODULE$.RejectionSampledGammaQuasiRandomVariable();
   }

   public static ProvidesTransformedQuasiMonteCarlo.DistributionRandomVariableSpec$ DistributionRandomVariableSpec() {
      return package$.MODULE$.DistributionRandomVariableSpec();
   }

   public static double quasiMonteCarloIntegrate(final Function1 f, final Seq variables, final long numSamples) {
      return package$.MODULE$.quasiMonteCarloIntegrate(f, variables, numSamples);
   }

   public static final class ToQuasi {
      private final HasInverseCdf icdfProvider;

      public HasInverseCdf icdfProvider() {
         return this.icdfProvider;
      }

      public ProvidesTransformedQuasiMonteCarlo.DistributionRandomVariableSpec toQuasi() {
         return package.ToQuasi$.MODULE$.toQuasi$extension(this.icdfProvider());
      }

      public int hashCode() {
         return package.ToQuasi$.MODULE$.hashCode$extension(this.icdfProvider());
      }

      public boolean equals(final Object x$1) {
         return package.ToQuasi$.MODULE$.equals$extension(this.icdfProvider(), x$1);
      }

      public ToQuasi(final HasInverseCdf icdfProvider) {
         this.icdfProvider = icdfProvider;
      }
   }

   public static class ToQuasi$ {
      public static final ToQuasi$ MODULE$ = new ToQuasi$();

      public final ProvidesTransformedQuasiMonteCarlo.DistributionRandomVariableSpec toQuasi$extension(final HasInverseCdf $this) {
         return package$.MODULE$.new DistributionRandomVariableSpec($this);
      }

      public final int hashCode$extension(final HasInverseCdf $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final HasInverseCdf $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof ToQuasi) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var7;
         if (var3) {
            label32: {
               label31: {
                  HasInverseCdf var5 = x$1 == null ? null : ((ToQuasi)x$1).icdfProvider();
                  if ($this == null) {
                     if (var5 == null) {
                        break label31;
                     }
                  } else if ($this.equals(var5)) {
                     break label31;
                  }

                  var7 = false;
                  break label32;
               }

               var7 = true;
            }

            if (var7) {
               var7 = true;
               return var7;
            }
         }

         var7 = false;
         return var7;
      }
   }
}
