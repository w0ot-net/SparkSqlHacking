package breeze.numerics.constants;

import breeze.numerics.package$pow$powDoubleDoubleImpl$;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static double Pi;
   private static double π;
   private static double E;
   private static double GoldenRatio;
   private static double eulerMascheroni;
   private static double γ;
   private static double Mu0;
   private static double Epsilon0;
   private static double Alpha;
   private static double NA;
   private static double R;
   private static double k;
   private static double c;
   private static double h;
   private static double hBar;
   private static double e;
   private static double Wien;
   private static double sigma;
   private static final double MagneticConstant;
   private static final double ElectricConstant;
   private static final double FineStructureConstant;
   private static final double GravitationConstant;
   private static final double StandardAccelerationOfGravity;
   private static final double g;
   private static final double AvogadroNumber;
   private static final double MolarGasConstant;
   private static final double BoltzmannConstant;
   private static final double LightSpeed;
   private static final double PlanckConstant;
   private static final double ElementaryCharge;
   private static final double ElectronMass;
   private static final double ProtonMass;
   private static final double NeutronMass;
   private static final double RydbergConstant;
   private static final double WienDisplacementLawConstant;
   private static final double StefanBoltzmannConstant;
   private static final double yotta;
   private static final double zetta;
   private static final double exa;
   private static final double peta;
   private static final double tera;
   private static final double giga;
   private static final double mega;
   private static final double kilo;
   private static final double hecto;
   private static final double deca;
   private static final double deci;
   private static final double centi;
   private static final double milli;
   private static final double micro;
   private static final double nano;
   private static final double pico;
   private static final double femto;
   private static final double atto;
   private static final double zepto;
   private static final double yocto;
   private static final double kibi;
   private static final double mebi;
   private static final double gibi;
   private static final double tebi;
   private static final double pebi;
   private static final double exbi;
   private static final double zebi;
   private static final double yobi;
   private static volatile int bitmap$0;

   static {
      MagneticConstant = 4.0E-7 * MODULE$.Pi();
      ElectricConstant = 8.854187817E-12;
      FineStructureConstant = 0.0072973525698;
      GravitationConstant = 6.67384E-11;
      StandardAccelerationOfGravity = 9.80665;
      g = MODULE$.StandardAccelerationOfGravity();
      AvogadroNumber = 6.02214129E23;
      MolarGasConstant = 8.3144621;
      BoltzmannConstant = 1.3806488E-23;
      LightSpeed = 2.99792458E8;
      PlanckConstant = 6.62606957E-34;
      ElementaryCharge = 1.602176565E-19;
      ElectronMass = 9.10938291E-31;
      ProtonMass = 1.672621777E-27;
      NeutronMass = 1.674927351E-27;
      RydbergConstant = 1.0973731568539E7;
      WienDisplacementLawConstant = 0.0028977721;
      StefanBoltzmannConstant = 5.670373E-8;
      yotta = 1.0E24;
      zetta = 1.0E21;
      exa = 1.0E18;
      peta = 1.0E15;
      tera = 1.0E12;
      giga = (double)1.0E9F;
      mega = (double)1000000.0F;
      kilo = (double)1000.0F;
      hecto = (double)100.0F;
      deca = (double)10.0F;
      deci = 0.1;
      centi = 0.01;
      milli = 0.001;
      micro = 1.0E-6;
      nano = 1.0E-9;
      pico = 1.0E-12;
      femto = 1.0E-15;
      atto = 1.0E-18;
      zepto = 1.0E-21;
      yocto = 1.0E-24;
      kibi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)10.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      mebi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)20.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      gibi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)30.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      tebi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)40.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      pebi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)50.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      exbi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)60.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      zebi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)70.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
      yobi = breeze.numerics.package.pow$.MODULE$.apply$mDDDc$sp((double)2.0F, (double)80.0F, package$pow$powDoubleDoubleImpl$.MODULE$);
   }

   private double Pi$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 1) == 0) {
            Pi = Math.PI;
            bitmap$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return Pi;
   }

   public double Pi() {
      return (bitmap$0 & 1) == 0 ? this.Pi$lzycompute() : Pi;
   }

   private double π$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 2) == 0) {
            π = this.Pi();
            bitmap$0 |= 2;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return π;
   }

   public double π() {
      return (bitmap$0 & 2) == 0 ? this.π$lzycompute() : π;
   }

   private double E$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 4) == 0) {
            E = Math.E;
            bitmap$0 |= 4;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return E;
   }

   public double E() {
      return (bitmap$0 & 4) == 0 ? this.E$lzycompute() : E;
   }

   private double GoldenRatio$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 8) == 0) {
            GoldenRatio = (Math.sqrt((double)5.0F) + (double)1.0F) / (double)2.0F;
            bitmap$0 |= 8;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return GoldenRatio;
   }

   public double GoldenRatio() {
      return (bitmap$0 & 8) == 0 ? this.GoldenRatio$lzycompute() : GoldenRatio;
   }

   private double eulerMascheroni$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 16) == 0) {
            eulerMascheroni = 0.5772156649015329;
            bitmap$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return eulerMascheroni;
   }

   public double eulerMascheroni() {
      return (bitmap$0 & 16) == 0 ? this.eulerMascheroni$lzycompute() : eulerMascheroni;
   }

   private double γ$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 32) == 0) {
            γ = this.eulerMascheroni();
            bitmap$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return γ;
   }

   public double γ() {
      return (bitmap$0 & 32) == 0 ? this.γ$lzycompute() : γ;
   }

   public double MagneticConstant() {
      return MagneticConstant;
   }

   private double Mu0$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 64) == 0) {
            Mu0 = this.MagneticConstant();
            bitmap$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return Mu0;
   }

   public double Mu0() {
      return (bitmap$0 & 64) == 0 ? this.Mu0$lzycompute() : Mu0;
   }

   public double ElectricConstant() {
      return ElectricConstant;
   }

   private double Epsilon0$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 128) == 0) {
            Epsilon0 = this.ElectricConstant();
            bitmap$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return Epsilon0;
   }

   public double Epsilon0() {
      return (bitmap$0 & 128) == 0 ? this.Epsilon0$lzycompute() : Epsilon0;
   }

   public double FineStructureConstant() {
      return FineStructureConstant;
   }

   private double Alpha$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 256) == 0) {
            Alpha = this.FineStructureConstant();
            bitmap$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return Alpha;
   }

   public double Alpha() {
      return (bitmap$0 & 256) == 0 ? this.Alpha$lzycompute() : Alpha;
   }

   public double GravitationConstant() {
      return GravitationConstant;
   }

   public double StandardAccelerationOfGravity() {
      return StandardAccelerationOfGravity;
   }

   public double g() {
      return g;
   }

   public double AvogadroNumber() {
      return AvogadroNumber;
   }

   private double NA$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 512) == 0) {
            NA = this.AvogadroNumber();
            bitmap$0 |= 512;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return NA;
   }

   public double NA() {
      return (bitmap$0 & 512) == 0 ? this.NA$lzycompute() : NA;
   }

   public double MolarGasConstant() {
      return MolarGasConstant;
   }

   private double R$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 1024) == 0) {
            R = this.MolarGasConstant();
            bitmap$0 |= 1024;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return R;
   }

   public double R() {
      return (bitmap$0 & 1024) == 0 ? this.R$lzycompute() : R;
   }

   public double BoltzmannConstant() {
      return BoltzmannConstant;
   }

   private double k$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 2048) == 0) {
            k = this.BoltzmannConstant();
            bitmap$0 |= 2048;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return k;
   }

   public double k() {
      return (bitmap$0 & 2048) == 0 ? this.k$lzycompute() : k;
   }

   public double LightSpeed() {
      return LightSpeed;
   }

   private double c$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 4096) == 0) {
            c = this.LightSpeed();
            bitmap$0 |= 4096;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return c;
   }

   public double c() {
      return (bitmap$0 & 4096) == 0 ? this.c$lzycompute() : c;
   }

   public double PlanckConstant() {
      return PlanckConstant;
   }

   private double h$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 8192) == 0) {
            h = this.PlanckConstant();
            bitmap$0 |= 8192;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return h;
   }

   public double h() {
      return (bitmap$0 & 8192) == 0 ? this.h$lzycompute() : h;
   }

   private double hBar$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 16384) == 0) {
            hBar = this.PlanckConstant() / (double)2.0F / this.Pi();
            bitmap$0 |= 16384;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return hBar;
   }

   public double hBar() {
      return (bitmap$0 & 16384) == 0 ? this.hBar$lzycompute() : hBar;
   }

   public double ElementaryCharge() {
      return ElementaryCharge;
   }

   private double e$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & '耀') == 0) {
            e = this.ElementaryCharge();
            bitmap$0 |= 32768;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return e;
   }

   public double e() {
      return (bitmap$0 & '耀') == 0 ? this.e$lzycompute() : e;
   }

   public double ElectronMass() {
      return ElectronMass;
   }

   public double ProtonMass() {
      return ProtonMass;
   }

   public double NeutronMass() {
      return NeutronMass;
   }

   public double RydbergConstant() {
      return RydbergConstant;
   }

   public double WienDisplacementLawConstant() {
      return WienDisplacementLawConstant;
   }

   private double Wien$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 65536) == 0) {
            Wien = this.WienDisplacementLawConstant();
            bitmap$0 |= 65536;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return Wien;
   }

   public double Wien() {
      return (bitmap$0 & 65536) == 0 ? this.Wien$lzycompute() : Wien;
   }

   public double StefanBoltzmannConstant() {
      return StefanBoltzmannConstant;
   }

   private double sigma$lzycompute() {
      synchronized(this){}

      try {
         if ((bitmap$0 & 131072) == 0) {
            sigma = this.StefanBoltzmannConstant();
            bitmap$0 |= 131072;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return sigma;
   }

   public double sigma() {
      return (bitmap$0 & 131072) == 0 ? this.sigma$lzycompute() : sigma;
   }

   public double yotta() {
      return yotta;
   }

   public double zetta() {
      return zetta;
   }

   public double exa() {
      return exa;
   }

   public double peta() {
      return peta;
   }

   public double tera() {
      return tera;
   }

   public double giga() {
      return giga;
   }

   public double mega() {
      return mega;
   }

   public double kilo() {
      return kilo;
   }

   public double hecto() {
      return hecto;
   }

   public double deca() {
      return deca;
   }

   public double deci() {
      return deci;
   }

   public double centi() {
      return centi;
   }

   public double milli() {
      return milli;
   }

   public double micro() {
      return micro;
   }

   public double nano() {
      return nano;
   }

   public double pico() {
      return pico;
   }

   public double femto() {
      return femto;
   }

   public double atto() {
      return atto;
   }

   public double zepto() {
      return zepto;
   }

   public double yocto() {
      return yocto;
   }

   public double kibi() {
      return kibi;
   }

   public double mebi() {
      return mebi;
   }

   public double gibi() {
      return gibi;
   }

   public double tebi() {
      return tebi;
   }

   public double pebi() {
      return pebi;
   }

   public double exbi() {
      return exbi;
   }

   public double zebi() {
      return zebi;
   }

   public double yobi() {
      return yobi;
   }

   private package$() {
   }
}
