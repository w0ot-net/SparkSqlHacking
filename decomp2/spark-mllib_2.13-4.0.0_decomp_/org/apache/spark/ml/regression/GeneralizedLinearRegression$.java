package org.apache.spark.ml.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Set;
import scala.runtime.ModuleSerializationProxy;

public final class GeneralizedLinearRegression$ implements DefaultParamsReadable, Serializable {
   public static final GeneralizedLinearRegression$ MODULE$ = new GeneralizedLinearRegression$();
   private static Set supportedFamilyAndLinkPairs;
   private static String[] supportedFamilyNames;
   private static String[] supportedLinkNames;
   private static final String IRLS;
   private static final String[] supportedSolvers;
   private static final double epsilon;
   private static volatile byte bitmap$0;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      IRLS = "irls";
      supportedSolvers = (String[])((Object[])(new String[]{MODULE$.IRLS()}));
      epsilon = 1.0E-16;
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public GeneralizedLinearRegression load(final String path) {
      return (GeneralizedLinearRegression)MLReadable.load$(this, path);
   }

   private Set supportedFamilyAndLinkPairs$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            supportedFamilyAndLinkPairs = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Gaussian$.MODULE$), GeneralizedLinearRegression.Identity$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Gaussian$.MODULE$), GeneralizedLinearRegression.Log$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Gaussian$.MODULE$), GeneralizedLinearRegression.Inverse$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Binomial$.MODULE$), GeneralizedLinearRegression.Logit$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Binomial$.MODULE$), GeneralizedLinearRegression.Probit$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Binomial$.MODULE$), GeneralizedLinearRegression.CLogLog$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Poisson$.MODULE$), GeneralizedLinearRegression.Log$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Poisson$.MODULE$), GeneralizedLinearRegression.Identity$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Poisson$.MODULE$), GeneralizedLinearRegression.Sqrt$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Gamma$.MODULE$), GeneralizedLinearRegression.Inverse$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Gamma$.MODULE$), GeneralizedLinearRegression.Identity$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(GeneralizedLinearRegression.Gamma$.MODULE$), GeneralizedLinearRegression.Log$.MODULE$)})));
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return supportedFamilyAndLinkPairs;
   }

   public Set supportedFamilyAndLinkPairs() {
      return (byte)(bitmap$0 & 1) == 0 ? this.supportedFamilyAndLinkPairs$lzycompute() : supportedFamilyAndLinkPairs;
   }

   public String IRLS() {
      return IRLS;
   }

   public String[] supportedSolvers() {
      return supportedSolvers;
   }

   private String[] supportedFamilyNames$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            supportedFamilyNames = (String[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps(((IterableOnceOps)this.supportedFamilyAndLinkPairs().map((x$1) -> ((GeneralizedLinearRegression.Family)x$1._1()).name())).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))), "tweedie", scala.reflect.ClassTag..MODULE$.apply(String.class));
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return supportedFamilyNames;
   }

   public String[] supportedFamilyNames() {
      return (byte)(bitmap$0 & 2) == 0 ? this.supportedFamilyNames$lzycompute() : supportedFamilyNames;
   }

   private String[] supportedLinkNames$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            supportedLinkNames = (String[])((IterableOnceOps)this.supportedFamilyAndLinkPairs().map((x$2) -> ((GeneralizedLinearRegression.Link)x$2._2()).name())).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return supportedLinkNames;
   }

   public String[] supportedLinkNames() {
      return (byte)(bitmap$0 & 4) == 0 ? this.supportedLinkNames$lzycompute() : supportedLinkNames;
   }

   public double epsilon() {
      return epsilon;
   }

   public double ylogy(final double y, final double mu) {
      return y == (double)0 ? (double)0.0F : y * scala.math.package..MODULE$.log(y / mu);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GeneralizedLinearRegression$.class);
   }

   private GeneralizedLinearRegression$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
