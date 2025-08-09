package org.apache.spark.ml.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.UUID;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamMap$;
import org.apache.spark.ml.util.Summary;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%g\u0001\u0002\u0012$\u00019B\u0001b\u0012\u0001\u0003\u0002\u0003\u0006I\u0001\u0013\u0005\t5\u0002\u0011\t\u0011)A\u00057\"1q\f\u0001C\u0001G\u0001Dq\u0001\u001b\u0001C\u0002\u0013\u0005\u0011\u000e\u0003\u0004|\u0001\u0001\u0006IA\u001b\u0005\b{\u0002\u0011\r\u0011\"\u0005\u007f\u0011\u0019y\b\u0001)A\u00057\"I\u0011\u0011\u0001\u0001C\u0002\u0013\u0005\u00111\u0001\u0005\t\u0003G\u0001\u0001\u0015!\u0003\u0002\u0006!Y\u0011q\u0006\u0001\t\u0006\u0004%\taIA\u0019\u0011-\t\t\u0005\u0001EC\u0002\u0013\u00051%a\u0011\t\u0017\u0005-\u0003\u0001#b\u0001\n\u0003\u0019\u0013Q\n\u0005\u000b\u0003+\u0002\u0001R1A\u0005\n\u0005]\u0003BCA0\u0001!\u0015\r\u0011\"\u0001\u0002b!Y\u0011q\u000e\u0001\t\u0006\u0004%\t!JA9\u0011)\tI\b\u0001EC\u0002\u0013\u0005\u0011\u0011\r\u0005\u000b\u0003{\u0002\u0001R1A\u0005\u0002\u0005\u0005\u0004BCAA\u0001!\u0015\r\u0011\"\u0001\u0002b!Q\u0011Q\u0011\u0001\t\u0006\u0004%\t!!\u0019\t\u000f\u0005%\u0005\u0001\"\u0003\u0002\f\"9\u00111\u0013\u0001\u0005\n\u0005-\u0005bBAK\u0001\u0011%\u00111\u0012\u0005\b\u0003/\u0003A\u0011BAF\u0011-\tI\n\u0001EC\u0002\u0013\u00051%a\u0001\t\u0017\u0005m\u0005\u0001#b\u0001\n\u0003\u0019\u00131\u0001\u0005\f\u0003;\u0003\u0001R1A\u0005\u0002\r\n\u0019\u0001C\u0006\u0002 \u0002A)\u0019!C\u0001G\u0005\r\u0001bBAQ\u0001\u0011\u0005\u00111\u0015\u0005\b\u0003C\u0003A\u0011AAT\u0011)\ty\u000b\u0001EC\u0002\u0013\u0005\u0011\u0011\u0017\u0005\u000b\u0003w\u0003\u0001R1A\u0005\u0002\u0005E\u0006BCA`\u0001!\u0015\r\u0011\"\u0001\u00022\"Q\u00111\u0019\u0001\t\u0006\u0004%\t!!-\u0003E\u001d+g.\u001a:bY&TX\r\u001a'j]\u0016\f'OU3he\u0016\u001c8/[8o'VlW.\u0019:z\u0015\t!S%\u0001\u0006sK\u001e\u0014Xm]:j_:T!AJ\u0014\u0002\u00055d'B\u0001\u0015*\u0003\u0015\u0019\b/\u0019:l\u0015\tQ3&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002Y\u0005\u0019qN]4\u0004\u0001M!\u0001aL\u001b<!\t\u00014'D\u00012\u0015\u0005\u0011\u0014!B:dC2\f\u0017B\u0001\u001b2\u0005\u0019\te.\u001f*fMB\u0011a'O\u0007\u0002o)\u0011\u0001(J\u0001\u0005kRLG.\u0003\u0002;o\t91+^7nCJL\bC\u0001\u001fE\u001d\ti$I\u0004\u0002?\u00036\tqH\u0003\u0002A[\u00051AH]8pizJ\u0011AM\u0005\u0003\u0007F\nq\u0001]1dW\u0006<W-\u0003\u0002F\r\na1+\u001a:jC2L'0\u00192mK*\u00111)M\u0001\bI\u0006$\u0018m]3ua\tI\u0015\u000bE\u0002K\u001b>k\u0011a\u0013\u0006\u0003\u0019\u001e\n1a]9m\u0013\tq5JA\u0004ECR\f7/\u001a;\u0011\u0005A\u000bF\u0002\u0001\u0003\n%\u0006\t\t\u0011!A\u0003\u0002M\u00131a\u0018\u00136#\t!v\u000b\u0005\u00021+&\u0011a+\r\u0002\b\u001d>$\b.\u001b8h!\t\u0001\u0004,\u0003\u0002Zc\t\u0019\u0011I\\=\u0002\u0013=\u0014\u0018nZ'pI\u0016d\u0007C\u0001/^\u001b\u0005\u0019\u0013B\u00010$\u0005\u0001:UM\\3sC2L'0\u001a3MS:,\u0017M\u001d*fOJ,7o]5p]6{G-\u001a7\u0002\rqJg.\u001b;?)\r\t'm\u001a\t\u00039\u0002AQaR\u0002A\u0002\r\u0004$\u0001\u001a4\u0011\u0007)kU\r\u0005\u0002QM\u0012I!KYA\u0001\u0002\u0003\u0015\ta\u0015\u0005\u00065\u000e\u0001\raW\u0001\u000eaJ,G-[2uS>t7i\u001c7\u0016\u0003)\u0004\"a[8\u000f\u00051l\u0007C\u0001 2\u0013\tq\u0017'\u0001\u0004Qe\u0016$WMZ\u0005\u0003aF\u0014aa\u0015;sS:<'B\u000182Q\r!1/\u001f\t\u0003i^l\u0011!\u001e\u0006\u0003m\u001e\n!\"\u00198o_R\fG/[8o\u0013\tAXOA\u0003TS:\u001cW-I\u0001{\u0003\u0015\u0011d\u0006\r\u00181\u00039\u0001(/\u001a3jGRLwN\\\"pY\u0002B3!B:z\u0003\u0015iw\u000eZ3m+\u0005Y\u0016AB7pI\u0016d\u0007%A\u0006qe\u0016$\u0017n\u0019;j_:\u001cXCAA\u0003!\u0011\t9!a\u0007\u000f\t\u0005%\u0011\u0011\u0004\b\u0005\u0003\u0017\t9B\u0004\u0003\u0002\u000e\u0005Ua\u0002BA\b\u0003'q1APA\t\u0013\u0005a\u0013B\u0001\u0016,\u0013\tA\u0013&\u0003\u0002MO%\u00111iS\u0005\u0005\u0003;\tyBA\u0005ECR\fgI]1nK*\u00111i\u0013\u0015\u0004\u0011ML\u0018\u0001\u00049sK\u0012L7\r^5p]N\u0004\u0003fA\u0005ts\"\u001a\u0011\"!\u000b\u0011\u0007A\nY#C\u0002\u0002.E\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u0015\u0019\fW.\u001b7z\u0019&t7.\u0006\u0002\u00024A!\u0011QGA\u001e\u001d\ra\u0016qG\u0005\u0004\u0003s\u0019\u0013aG$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3beJ+wM]3tg&|g.\u0003\u0003\u0002>\u0005}\"!\u0004$b[&d\u00170\u00118e\u0019&t7NC\u0002\u0002:\r\naAZ1nS2LXCAA#!\u0011\t)$a\u0012\n\t\u0005%\u0013q\b\u0002\u0007\r\u0006l\u0017\u000e\\=\u0002\t1Lgn[\u000b\u0003\u0003\u001f\u0002B!!\u000e\u0002R%!\u00111KA \u0005\u0011a\u0015N\\6\u0002\u0015\u001dd'oU;n[\u0006\u0014\u00180\u0006\u0002\u0002ZA\u0019!*a\u0017\n\u0007\u0005u3JA\u0002S_^\fAB\\;n\u0013:\u001cH/\u00198dKN,\"!a\u0019\u0011\u0007A\n)'C\u0002\u0002hE\u0012A\u0001T8oO\"\"ab]A6C\t\ti'A\u00033]Ir\u0003'\u0001\u0007gK\u0006$XO]3OC6,7/\u0006\u0002\u0002tA!\u0001'!\u001ek\u0013\r\t9(\r\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0005e\u0006t7\u000eK\u0002\u0011gf\f\u0001\u0003Z3he\u0016,7o\u00144Ge\u0016,Gm\\7)\u0007E\u0019\u00180A\fsKNLG-^1m\t\u0016<'/Z3PM\u001a\u0013X-\u001a3p[\"\u001a!c]=\u00027I,7/\u001b3vC2$Um\u001a:fK>3gI]3fI>lg*\u001e7mQ\r\u00192/_\u0001\u0006Y\u0006\u0014W\r\\\u000b\u0003\u0003\u001b\u00032ASAH\u0013\r\t\tj\u0013\u0002\u0007\u0007>dW/\u001c8\u0002\u0015A\u0014X\rZ5di&|g.\u0001\u0004xK&<\u0007\u000e^\u0001\u0007_\u001a47/\u001a;\u0002#\u0011,g/[1oG\u0016\u0014Vm]5ek\u0006d7/\u0001\tqK\u0006\u00148o\u001c8SKNLG-^1mg\u0006\u0001ro\u001c:lS:<'+Z:jIV\fGn]\u0001\u0012e\u0016\u001c\bo\u001c8tKJ+7/\u001b3vC2\u001c\u0018!\u0003:fg&$W/\u00197t)\t\t)\u0001K\u0002\u001dgf$B!!\u0002\u0002*\"1\u00111V\u000fA\u0002)\fQB]3tS\u0012,\u0018\r\\:UsB,\u0007fA\u000fts\u0006aa.\u001e7m\t\u00164\u0018.\u00198dKV\u0011\u00111\u0017\t\u0004a\u0005U\u0016bAA\\c\t1Ai\\;cY\u0016D3AH:z\u0003!!WM^5b]\u000e,\u0007fA\u0010ts\u0006QA-[:qKJ\u001c\u0018n\u001c8)\u0007\u0001\u001a\u00180A\u0002bS\u000eD3!I:zQ\r\u00011/\u001f"
)
public class GeneralizedLinearRegressionSummary implements Summary, Serializable {
   private GeneralizedLinearRegression.FamilyAndLink familyLink;
   private GeneralizedLinearRegression.Family family;
   private GeneralizedLinearRegression.Link link;
   private Row glrSummary;
   private long numInstances;
   private String[] featureNames;
   private long rank;
   private long degreesOfFreedom;
   private long residualDegreeOfFreedom;
   private long residualDegreeOfFreedomNull;
   private Dataset devianceResiduals;
   private Dataset pearsonResiduals;
   private Dataset workingResiduals;
   private Dataset responseResiduals;
   private double nullDeviance;
   private double deviance;
   private double dispersion;
   private double aic;
   private final Dataset dataset;
   private GeneralizedLinearRegressionModel origModel;
   private final String predictionCol;
   private final GeneralizedLinearRegressionModel model;
   private final transient Dataset predictions;
   private volatile int bitmap$0;

   public String predictionCol() {
      return this.predictionCol;
   }

   public GeneralizedLinearRegressionModel model() {
      return this.model;
   }

   public Dataset predictions() {
      return this.predictions;
   }

   private GeneralizedLinearRegression.FamilyAndLink familyLink$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1) == 0) {
            this.familyLink = GeneralizedLinearRegression.FamilyAndLink$.MODULE$.apply(this.model());
            this.bitmap$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.familyLink;
   }

   public GeneralizedLinearRegression.FamilyAndLink familyLink() {
      return (this.bitmap$0 & 1) == 0 ? this.familyLink$lzycompute() : this.familyLink;
   }

   private GeneralizedLinearRegression.Family family$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2) == 0) {
            this.family = this.familyLink().family();
            this.bitmap$0 |= 2;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.family;
   }

   public GeneralizedLinearRegression.Family family() {
      return (this.bitmap$0 & 2) == 0 ? this.family$lzycompute() : this.family;
   }

   private GeneralizedLinearRegression.Link link$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4) == 0) {
            this.link = this.familyLink().link();
            this.bitmap$0 |= 4;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.link;
   }

   public GeneralizedLinearRegression.Link link() {
      return (this.bitmap$0 & 4) == 0 ? this.link$lzycompute() : this.link;
   }

   private Row glrSummary$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8) == 0) {
            Column devCol;
            Column var14;
            label133: {
               label132: {
                  label131: {
                     UserDefinedFunction devUDF = .MODULE$.udf((label, pred, weight) -> BoxesRunTime.boxToDouble($anonfun$glrSummary$1(this, BoxesRunTime.unboxToDouble(label), BoxesRunTime.unboxToDouble(pred), BoxesRunTime.unboxToDouble(weight))), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
                     devCol = .MODULE$.sum(devUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label(), this.prediction(), this.weight()}))));
                     String var10001 = this.model().getFamily().toLowerCase(Locale.ROOT);
                     String var5 = GeneralizedLinearRegression.Binomial$.MODULE$.name();
                     if (var10001 == null) {
                        if (var5 == null) {
                           break label131;
                        }
                     } else if (var10001.equals(var5)) {
                        break label131;
                     }

                     var10001 = this.model().getFamily().toLowerCase(Locale.ROOT);
                     String var6 = GeneralizedLinearRegression.Poisson$.MODULE$.name();
                     if (var10001 == null) {
                        if (var6 != null) {
                           break label132;
                        }
                     } else if (!var10001.equals(var6)) {
                        break label132;
                     }
                  }

                  var14 = .MODULE$.lit(BoxesRunTime.boxToDouble(Double.NaN));
                  break label133;
               }

               UserDefinedFunction rssUDF = .MODULE$.udf((label, pred, weight) -> BoxesRunTime.boxToDouble($anonfun$glrSummary$2(this, BoxesRunTime.unboxToDouble(label), BoxesRunTime.unboxToDouble(pred), BoxesRunTime.unboxToDouble(weight))), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
               var14 = .MODULE$.sum(rssUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label(), this.prediction(), this.weight()}))));
            }

            Column rssCol;
            label120: {
               label147: {
                  rssCol = var14;
                  if (this.model().getFitIntercept()) {
                     if (!this.model().hasOffsetCol()) {
                        break label147;
                     }

                     if (this.model().hasOffsetCol()) {
                        label146: {
                           GeneralizedLinearRegression.Family var15 = this.family();
                           GeneralizedLinearRegression.Gaussian$ var9 = GeneralizedLinearRegression.Gaussian$.MODULE$;
                           if (var15 == null) {
                              if (var9 != null) {
                                 break label146;
                              }
                           } else if (!var15.equals(var9)) {
                              break label146;
                           }

                           GeneralizedLinearRegression.Link var16 = this.link();
                           GeneralizedLinearRegression.Identity$ var10 = GeneralizedLinearRegression.Identity$.MODULE$;
                           if (var16 == null) {
                              if (var10 == null) {
                                 break label147;
                              }
                           } else if (var16.equals(var10)) {
                              break label147;
                           }
                        }
                     }
                  }

                  var14 = .MODULE$.lit(BoxesRunTime.boxToDouble(Double.NaN));
                  break label120;
               }

               var14 = .MODULE$.sum(this.label().$minus(this.offset()).$times(this.weight())).$div(.MODULE$.sum(this.weight()));
            }

            Column avgCol = var14;
            this.glrSummary = (Row)this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.count(this.label()), .MODULE$.sum(this.weight()), devCol, rssCol, avgCol}))).head();
            this.bitmap$0 |= 8;
         }
      } catch (Throwable var12) {
         throw var12;
      }

      return this.glrSummary;
   }

   private Row glrSummary() {
      return (this.bitmap$0 & 8) == 0 ? this.glrSummary$lzycompute() : this.glrSummary;
   }

   private long numInstances$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16) == 0) {
            this.numInstances = this.glrSummary().getLong(0);
            this.bitmap$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numInstances;
   }

   public long numInstances() {
      return (this.bitmap$0 & 16) == 0 ? this.numInstances$lzycompute() : this.numInstances;
   }

   private String[] featureNames$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 32) == 0) {
            Option featureAttrs = AttributeGroup$.MODULE$.fromStructField(this.dataset.schema().apply(this.model().getFeaturesCol())).attributes();
            this.featureNames = featureAttrs.isDefined() ? (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(featureAttrs.get()), (x$3) -> (String)x$3.name().get(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : (String[])scala.Array..MODULE$.tabulate(this.origModel.numFeatures(), (x) -> $anonfun$featureNames$2(this, BoxesRunTime.unboxToInt(x)), scala.reflect.ClassTag..MODULE$.apply(String.class));
            this.bitmap$0 |= 32;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      this.origModel = null;
      return this.featureNames;
   }

   public String[] featureNames() {
      return (this.bitmap$0 & 32) == 0 ? this.featureNames$lzycompute() : this.featureNames;
   }

   private long rank$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 64) == 0) {
            this.rank = this.model().getFitIntercept() ? (long)(this.model().coefficients().size() + 1) : (long)this.model().coefficients().size();
            this.bitmap$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rank;
   }

   public long rank() {
      return (this.bitmap$0 & 64) == 0 ? this.rank$lzycompute() : this.rank;
   }

   private long degreesOfFreedom$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 128) == 0) {
            this.degreesOfFreedom = this.numInstances() - this.rank();
            this.bitmap$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.degreesOfFreedom;
   }

   public long degreesOfFreedom() {
      return (this.bitmap$0 & 128) == 0 ? this.degreesOfFreedom$lzycompute() : this.degreesOfFreedom;
   }

   private long residualDegreeOfFreedom$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 256) == 0) {
            this.residualDegreeOfFreedom = this.degreesOfFreedom();
            this.bitmap$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.residualDegreeOfFreedom;
   }

   public long residualDegreeOfFreedom() {
      return (this.bitmap$0 & 256) == 0 ? this.residualDegreeOfFreedom$lzycompute() : this.residualDegreeOfFreedom;
   }

   private long residualDegreeOfFreedomNull$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 512) == 0) {
            this.residualDegreeOfFreedomNull = this.model().getFitIntercept() ? this.numInstances() - 1L : this.numInstances();
            this.bitmap$0 |= 512;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.residualDegreeOfFreedomNull;
   }

   public long residualDegreeOfFreedomNull() {
      return (this.bitmap$0 & 512) == 0 ? this.residualDegreeOfFreedomNull$lzycompute() : this.residualDegreeOfFreedomNull;
   }

   private Column label() {
      return .MODULE$.col(this.model().getLabelCol()).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
   }

   private Column prediction() {
      return .MODULE$.col(this.predictionCol());
   }

   private Column weight() {
      return !this.model().hasWeightCol() ? .MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F)) : .MODULE$.col(this.model().getWeightCol());
   }

   private Column offset() {
      return !this.model().hasOffsetCol() ? .MODULE$.lit(BoxesRunTime.boxToDouble((double)0.0F)) : .MODULE$.col(this.model().getOffsetCol()).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
   }

   private Dataset devianceResiduals$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1024) == 0) {
            UserDefinedFunction drUDF = .MODULE$.udf((y, mu, weight) -> BoxesRunTime.boxToDouble($anonfun$devianceResiduals$1(this, BoxesRunTime.unboxToDouble(y), BoxesRunTime.unboxToDouble(mu), BoxesRunTime.unboxToDouble(weight))), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
            this.devianceResiduals = this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{drUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label(), this.prediction(), this.weight()}))).as("devianceResiduals")})));
            this.bitmap$0 |= 1024;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.devianceResiduals;
   }

   public Dataset devianceResiduals() {
      return (this.bitmap$0 & 1024) == 0 ? this.devianceResiduals$lzycompute() : this.devianceResiduals;
   }

   private Dataset pearsonResiduals$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2048) == 0) {
            UserDefinedFunction prUDF = .MODULE$.udf((JFunction1.mcDD.sp)(mu) -> this.family().variance(mu), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
            this.pearsonResiduals = this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label().minus(this.prediction()).multiply(.MODULE$.sqrt(this.weight())).divide(.MODULE$.sqrt(prUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.prediction()}))))).as("pearsonResiduals")})));
            this.bitmap$0 |= 2048;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.pearsonResiduals;
   }

   public Dataset pearsonResiduals() {
      return (this.bitmap$0 & 2048) == 0 ? this.pearsonResiduals$lzycompute() : this.pearsonResiduals;
   }

   private Dataset workingResiduals$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4096) == 0) {
            UserDefinedFunction wrUDF = .MODULE$.udf((JFunction2.mcDDD.sp)(y, mu) -> (y - mu) * this.link().deriv(mu), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
            this.workingResiduals = this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{wrUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label(), this.prediction()}))).as("workingResiduals")})));
            this.bitmap$0 |= 4096;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.workingResiduals;
   }

   public Dataset workingResiduals() {
      return (this.bitmap$0 & 4096) == 0 ? this.workingResiduals$lzycompute() : this.workingResiduals;
   }

   private Dataset responseResiduals$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8192) == 0) {
            this.responseResiduals = this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label().minus(this.prediction()).as("responseResiduals")})));
            this.bitmap$0 |= 8192;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.responseResiduals;
   }

   public Dataset responseResiduals() {
      return (this.bitmap$0 & 8192) == 0 ? this.responseResiduals$lzycompute() : this.responseResiduals;
   }

   public Dataset residuals() {
      return this.devianceResiduals();
   }

   public Dataset residuals(final String residualsType) {
      switch (residualsType == null ? 0 : residualsType.hashCode()) {
         case -694166804:
            if ("pearson".equals(residualsType)) {
               return this.pearsonResiduals();
            }
            break;
         case -340323263:
            if ("response".equals(residualsType)) {
               return this.responseResiduals();
            }
            break;
         case 1109141059:
            if ("deviance".equals(residualsType)) {
               return this.devianceResiduals();
            }
            break;
         case 1525164849:
            if ("working".equals(residualsType)) {
               return this.workingResiduals();
            }
      }

      throw new UnsupportedOperationException("The residuals type " + residualsType + " is not supported by Generalized Linear Regression.");
   }

   private double nullDeviance$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16384) == 0) {
            double var15;
            if (!this.model().getFitIntercept()) {
               var15 = (double)0.0F;
            } else {
               label140: {
                  if (this.model().hasOffsetCol()) {
                     label141: {
                        if (this.model().hasOffsetCol()) {
                           label138: {
                              GeneralizedLinearRegression.Family var10001 = this.family();
                              GeneralizedLinearRegression.Gaussian$ var4 = GeneralizedLinearRegression.Gaussian$.MODULE$;
                              if (var10001 == null) {
                                 if (var4 != null) {
                                    break label138;
                                 }
                              } else if (!var10001.equals(var4)) {
                                 break label138;
                              }

                              GeneralizedLinearRegression.Link var14 = this.link();
                              GeneralizedLinearRegression.Identity$ var5 = GeneralizedLinearRegression.Identity$.MODULE$;
                              if (var14 == null) {
                                 if (var5 == null) {
                                    break label141;
                                 }
                              } else if (var14.equals(var5)) {
                                 break label141;
                              }
                           }
                        }

                        String featureNull;
                        ParamMap paramMap;
                        label104: {
                           label103: {
                              featureNull = "feature_" + UUID.randomUUID().toString();
                              paramMap = this.model().extractParamMap();
                              paramMap.put(this.model().featuresCol(), featureNull);
                              String var16 = this.family().name();
                              String var8 = "tweedie";
                              if (var16 == null) {
                                 if (var8 != null) {
                                    break label103;
                                 }
                              } else if (!var16.equals(var8)) {
                                 break label103;
                              }

                              BoxedUnit var17 = BoxedUnit.UNIT;
                              break label104;
                           }

                           paramMap.remove(this.model().variancePower());
                        }

                        functions var18 = .MODULE$;
                        Function0 var10002 = () -> org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(0);
                        JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                        JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GeneralizedLinearRegressionSummary.class.getClassLoader());
                        UserDefinedFunction emptyVectorUDF = var18.udf(var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator1$3()));
                        var15 = ((GeneralizedLinearRegressionModel)this.model().parent().fit(this.dataset.withColumn(featureNull, emptyVectorUDF.apply(scala.collection.immutable.Nil..MODULE$)), paramMap)).intercept();
                        break label140;
                     }
                  }

                  var15 = this.link().link(this.glrSummary().getDouble(4));
               }
            }

            double intercept = var15;
            this.nullDeviance = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label(), this.offset(), this.weight()}))).rdd().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$nullDeviance$2(this, intercept, x0$1)), scala.reflect.ClassTag..MODULE$.Double())).sum();
            this.bitmap$0 |= 16384;
         }
      } catch (Throwable var13) {
         throw var13;
      }

      return this.nullDeviance;
   }

   public double nullDeviance() {
      return (this.bitmap$0 & 16384) == 0 ? this.nullDeviance$lzycompute() : this.nullDeviance;
   }

   private double deviance$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & '耀') == 0) {
            this.deviance = this.glrSummary().getDouble(2);
            this.bitmap$0 |= 32768;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.deviance;
   }

   public double deviance() {
      return (this.bitmap$0 & '耀') == 0 ? this.deviance$lzycompute() : this.deviance;
   }

   private double dispersion$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 65536) == 0) {
            double var9;
            label69: {
               label79: {
                  String var10001 = this.model().getFamily().toLowerCase(Locale.ROOT);
                  String var2 = GeneralizedLinearRegression.Binomial$.MODULE$.name();
                  if (var10001 == null) {
                     if (var2 == null) {
                        break label79;
                     }
                  } else if (var10001.equals(var2)) {
                     break label79;
                  }

                  var10001 = this.model().getFamily().toLowerCase(Locale.ROOT);
                  String var3 = GeneralizedLinearRegression.Poisson$.MODULE$.name();
                  if (var10001 == null) {
                     if (var3 == null) {
                        break label79;
                     }
                  } else if (var10001.equals(var3)) {
                     break label79;
                  }

                  double rss = this.glrSummary().getDouble(3);
                  var9 = rss / (double)this.degreesOfFreedom();
                  break label69;
               }

               var9 = (double)1.0F;
            }

            this.dispersion = var9;
            this.bitmap$0 |= 65536;
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.dispersion;
   }

   public double dispersion() {
      return (this.bitmap$0 & 65536) == 0 ? this.dispersion$lzycompute() : this.dispersion;
   }

   private double aic$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 131072) == 0) {
            double weightSum = this.glrSummary().getDouble(1);
            RDD t = this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.label(), this.prediction(), this.weight()}))).rdd().map((x0$1) -> {
               if (x0$1 != null) {
                  Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                  if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
                     Object label = ((SeqOps)var3.get()).apply(0);
                     Object pred = ((SeqOps)var3.get()).apply(1);
                     Object weight = ((SeqOps)var3.get()).apply(2);
                     if (label instanceof Double) {
                        double var7 = BoxesRunTime.unboxToDouble(label);
                        if (pred instanceof Double) {
                           double var9 = BoxesRunTime.unboxToDouble(pred);
                           if (weight instanceof Double) {
                              double var11 = BoxesRunTime.unboxToDouble(weight);
                              return new Tuple3(BoxesRunTime.boxToDouble(var7), BoxesRunTime.boxToDouble(var9), BoxesRunTime.boxToDouble(var11));
                           }
                        }
                     }
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
            this.aic = this.family().aic(t, this.deviance(), (double)this.numInstances(), weightSum) + (double)(2L * this.rank());
            this.bitmap$0 |= 131072;
         }
      } catch (Throwable var6) {
         throw var6;
      }

      return this.aic;
   }

   public double aic() {
      return (this.bitmap$0 & 131072) == 0 ? this.aic$lzycompute() : this.aic;
   }

   // $FF: synthetic method
   public static final double $anonfun$glrSummary$1(final GeneralizedLinearRegressionSummary $this, final double label, final double pred, final double weight) {
      return $this.family().deviance(label, pred, weight);
   }

   // $FF: synthetic method
   public static final double $anonfun$glrSummary$2(final GeneralizedLinearRegressionSummary $this, final double label, final double pred, final double weight) {
      return (label - pred) * (label - pred) * weight / $this.family().variance(pred);
   }

   // $FF: synthetic method
   public static final String $anonfun$featureNames$2(final GeneralizedLinearRegressionSummary $this, final int x) {
      String var10000 = $this.model().getFeaturesCol();
      return var10000 + "_" + x;
   }

   // $FF: synthetic method
   public static final double $anonfun$devianceResiduals$1(final GeneralizedLinearRegressionSummary $this, final double y, final double mu, final double weight) {
      double r = scala.math.package..MODULE$.sqrt(scala.math.package..MODULE$.max($this.family().deviance(y, mu, weight), (double)0.0F));
      return y > mu ? r : (double)-1.0F * r;
   }

   // $FF: synthetic method
   public static final double $anonfun$nullDeviance$2(final GeneralizedLinearRegressionSummary $this, final double intercept$1, final Row x0$1) {
      if (x0$1 != null) {
         Some var7 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
         if (!var7.isEmpty() && var7.get() != null && ((SeqOps)var7.get()).lengthCompare(3) == 0) {
            Object y = ((SeqOps)var7.get()).apply(0);
            Object offset = ((SeqOps)var7.get()).apply(1);
            Object weight = ((SeqOps)var7.get()).apply(2);
            if (y instanceof Double) {
               double var11 = BoxesRunTime.unboxToDouble(y);
               if (offset instanceof Double) {
                  double var13 = BoxesRunTime.unboxToDouble(offset);
                  if (weight instanceof Double) {
                     double var15 = BoxesRunTime.unboxToDouble(weight);
                     return $this.family().deviance(var11, $this.link().unlink(intercept$1 + var13), var15);
                  }
               }
            }
         }
      }

      throw new MatchError(x0$1);
   }

   public GeneralizedLinearRegressionSummary(final Dataset dataset, final GeneralizedLinearRegressionModel origModel) {
      this.dataset = dataset;
      this.origModel = origModel;
      super();
      this.predictionCol = origModel.isDefined(origModel.predictionCol()) && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(origModel.getPredictionCol())) ? origModel.getPredictionCol() : "prediction_" + UUID.randomUUID().toString();
      this.model = (GeneralizedLinearRegressionModel)origModel.copy(ParamMap$.MODULE$.empty()).setPredictionCol(this.predictionCol());
      this.predictions = this.model().transform(dataset);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class $typecreator1$3 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
      }
   }
}
