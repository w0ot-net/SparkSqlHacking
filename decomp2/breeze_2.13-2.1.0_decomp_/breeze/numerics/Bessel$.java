package breeze.numerics;

public final class Bessel$ {
   public static final Bessel$ MODULE$ = new Bessel$();
   private static final double[] breeze$numerics$Bessel$$i0p = new double[]{0.9999999999999997, 0.2466405579426905, 0.01478980363444585, 3.82699355994036E-4, 5.395676869878828E-6, 4.700912200921704E-8, 2.733894920915608E-10, 1.115830108455192E-12, 3.301093025084127E-15, 7.209167098020555E-18, 1.166898488777214E-20, 1.378948246502109E-23, 1.124884061857506E-26, 5.498556929587117E-30};
   private static final double[] breeze$numerics$Bessel$$i0q = new double[]{0.4463598170691436, 0.001702205745042606, 2.792125684538934E-6, 2.369902034785866E-9, 8.965900179621208E-13};
   private static final double[] breeze$numerics$Bessel$$i0pp = new double[]{0.119227374812067, 0.1947452015979746, 0.07629241821600588, 0.00847490358080155, 2.023821945835647E-4};
   private static final double[] breeze$numerics$Bessel$$i0qq = new double[]{0.2962898424533095, 0.4866115913196384, 0.1938352806477617, 0.02261671093400046, 6.450448095075585E-4, 1.52983578240045E-6};
   private static final double[] breeze$numerics$Bessel$$A_i1 = new double[]{2.7779141127610464E-18, -2.111421214358166E-17, 1.5536319577362005E-16, -1.1055969477353862E-15, 7.600684294735408E-15, -5.042185504727912E-14, 3.223793365945575E-13, -1.9839743977649436E-12, 1.1736186298890901E-11, -6.663489723502027E-11, 3.625590281552117E-10, -1.8872497517228294E-9, 9.381537386495773E-9, -4.445059128796328E-8, 2.0032947535521353E-7, -8.568720264695455E-7, 3.4702513081376785E-6, -1.3273163656039436E-5, 4.781565107550054E-5, -1.6176081582589674E-4, 5.122859561685758E-4, -0.0015135724506312532, 0.004156422944312888, -0.010564084894626197, 0.024726449030626516, -0.05294598120809499, 0.1026436586898471, -0.17641651835783406, 0.25258718644363365};
   private static final double[] breeze$numerics$Bessel$$B_i1 = new double[]{7.517296310842105E-18, 4.414348323071708E-18, -4.6503053684893586E-17, -3.209525921993424E-17, 2.96262899764595E-16, 3.3082023109209285E-16, -1.8803547755107825E-15, -3.8144030724370075E-15, 1.0420276984128802E-14, 4.272440016711951E-14, -2.1015418427726643E-14, -4.0835511110921974E-13, -7.198551776245908E-13, 2.0356285441470896E-12, 1.4125807436613782E-11, 3.2526035830154884E-11, -1.8974958123505413E-11, -5.589743462196584E-10, -3.835380385964237E-9, -2.6314688468895196E-8, -2.512236237870209E-7, -3.882564808877691E-6, -1.1058893876262371E-4, -0.009761097491361469, 0.7785762350182801};

   public double breeze$numerics$Bessel$$chbevl(final double x, final double[] coef, final int N) {
      double b0 = (double)0.0F;
      double b1 = (double)0.0F;
      double b2 = (double)0.0F;
      int p = 0;
      int i = N - 1;
      b0 = coef[p];
      ++p;
      b1 = (double)0.0F;

      for(int var16 = N - 1; var16 > 0; --var16) {
         b2 = b1;
         b1 = b0;
         b0 = x * b0 - b2 + coef[p];
         ++p;
      }

      return (double)0.5F * (b0 - b2);
   }

   public double[] breeze$numerics$Bessel$$i0p() {
      return breeze$numerics$Bessel$$i0p;
   }

   public double[] breeze$numerics$Bessel$$i0q() {
      return breeze$numerics$Bessel$$i0q;
   }

   public double[] breeze$numerics$Bessel$$i0pp() {
      return breeze$numerics$Bessel$$i0pp;
   }

   public double[] breeze$numerics$Bessel$$i0qq() {
      return breeze$numerics$Bessel$$i0qq;
   }

   public double[] breeze$numerics$Bessel$$A_i1() {
      return breeze$numerics$Bessel$$A_i1;
   }

   public double[] breeze$numerics$Bessel$$B_i1() {
      return breeze$numerics$Bessel$$B_i1;
   }

   private Bessel$() {
   }
}
