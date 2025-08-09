package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.MLFormatRegister;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g\u0001B\u000f\u001f\t%BQ!\u000f\u0001\u0005\u0002iBQ!\u0010\u0001\u0005ByBQA\u0013\u0001\u0005By2Aa\u0013\u0001E\u0019\"A\u0011\f\u0002BK\u0002\u0013\u0005!\f\u0003\u0005_\t\tE\t\u0015!\u0003\\\u0011!yFA!f\u0001\n\u0003\u0001\u0007\u0002C4\u0005\u0005#\u0005\u000b\u0011B1\t\u000be\"A\u0011\u00015\t\u000f5$\u0011\u0011!C\u0001]\"9\u0011\u000fBI\u0001\n\u0003\u0011\bbB?\u0005#\u0003%\tA \u0005\n\u0003\u0003!\u0011\u0011!C!\u0003\u0007A\u0011\"a\u0005\u0005\u0003\u0003%\t!!\u0006\t\u0013\u0005uA!!A\u0005\u0002\u0005}\u0001\"CA\u0016\t\u0005\u0005I\u0011IA\u0017\u0011%\tY\u0004BA\u0001\n\u0003\ti\u0004C\u0005\u0002H\u0011\t\t\u0011\"\u0011\u0002J!I\u0011Q\n\u0003\u0002\u0002\u0013\u0005\u0013q\n\u0005\n\u0003#\"\u0011\u0011!C!\u0003'B\u0011\"!\u0016\u0005\u0003\u0003%\t%a\u0016\b\u0013\u0005m\u0003!!A\t\n\u0005uc\u0001C&\u0001\u0003\u0003EI!a\u0018\t\re:B\u0011AA<\u0011%\t\tfFA\u0001\n\u000b\n\u0019\u0006C\u0005\u0002z]\t\t\u0011\"!\u0002|!I\u0011\u0011Q\f\u0002\u0002\u0013\u0005\u00151\u0011\u0005\b\u0003+\u0003A\u0011IAL\u0005}\u0001V*\u0014'MS:,\u0017M\u001d*fOJ,7o]5p]6{G-\u001a7Xe&$XM\u001d\u0006\u0003?\u0001\n!B]3he\u0016\u001c8/[8o\u0015\t\t#%\u0001\u0002nY*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001Q\u0003G\u000e\t\u0003W9j\u0011\u0001\f\u0006\u0002[\u0005)1oY1mC&\u0011q\u0006\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\"T\"\u0001\u001a\u000b\u0005M\u0002\u0013\u0001B;uS2L!!\u000e\u001a\u0003\u001d5cuK]5uKJ4uN]7biB\u0011\u0011gN\u0005\u0003qI\u0012\u0001#\u0014'G_Jl\u0017\r\u001e*fO&\u001cH/\u001a:\u0002\rqJg.\u001b;?)\u0005Y\u0004C\u0001\u001f\u0001\u001b\u0005q\u0012A\u00024pe6\fG\u000fF\u0001@!\t\u0001uI\u0004\u0002B\u000bB\u0011!\tL\u0007\u0002\u0007*\u0011A\tK\u0001\u0007yI|w\u000e\u001e \n\u0005\u0019c\u0013A\u0002)sK\u0012,g-\u0003\u0002I\u0013\n11\u000b\u001e:j]\u001eT!A\u0012\u0017\u0002\u0013M$\u0018mZ3OC6,'\u0001\u0002#bi\u0006\u001cB\u0001\u0002\u0016N!B\u00111FT\u0005\u0003\u001f2\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002R-:\u0011!\u000b\u0016\b\u0003\u0005NK\u0011!L\u0005\u0003+2\nq\u0001]1dW\u0006<W-\u0003\u0002X1\na1+\u001a:jC2L'0\u00192mK*\u0011Q\u000bL\u0001\nS:$XM]2faR,\u0012a\u0017\t\u0003WqK!!\u0018\u0017\u0003\r\u0011{WO\u00197f\u0003)Ig\u000e^3sG\u0016\u0004H\u000fI\u0001\rG>,gMZ5dS\u0016tGo]\u000b\u0002CB\u0011!-Z\u0007\u0002G*\u0011A\rI\u0001\u0007Y&t\u0017\r\\4\n\u0005\u0019\u001c'A\u0002,fGR|'/A\u0007d_\u00164g-[2jK:$8\u000f\t\u000b\u0004S.d\u0007C\u00016\u0005\u001b\u0005\u0001\u0001\"B-\n\u0001\u0004Y\u0006\"B0\n\u0001\u0004\t\u0017\u0001B2paf$2![8q\u0011\u001dI&\u0002%AA\u0002mCqa\u0018\u0006\u0011\u0002\u0003\u0007\u0011-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003MT#a\u0017;,\u0003U\u0004\"A^>\u000e\u0003]T!\u0001_=\u0002\u0013Ut7\r[3dW\u0016$'B\u0001>-\u0003)\tgN\\8uCRLwN\\\u0005\u0003y^\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012a \u0016\u0003CR\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0003!\u0011\t9!!\u0005\u000e\u0005\u0005%!\u0002BA\u0006\u0003\u001b\tA\u0001\\1oO*\u0011\u0011qB\u0001\u0005U\u00064\u0018-C\u0002I\u0003\u0013\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u0006\u0011\u0007-\nI\"C\u0002\u0002\u001c1\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\t\u0002(A\u00191&a\t\n\u0007\u0005\u0015BFA\u0002B]fD\u0011\"!\u000b\u0010\u0003\u0003\u0005\r!a\u0006\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0003\u0005\u0004\u00022\u0005]\u0012\u0011E\u0007\u0003\u0003gQ1!!\u000e-\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003s\t\u0019D\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA \u0003\u000b\u00022aKA!\u0013\r\t\u0019\u0005\f\u0002\b\u0005>|G.Z1o\u0011%\tI#EA\u0001\u0002\u0004\t\t#\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0003\u0003\u0017B\u0011\"!\u000b\u0013\u0003\u0003\u0005\r!a\u0006\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0006\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0002\u0002\r\u0015\fX/\u00197t)\u0011\ty$!\u0017\t\u0013\u0005%R#!AA\u0002\u0005\u0005\u0012\u0001\u0002#bi\u0006\u0004\"A[\f\u0014\u000b]\t\t'!\u001c\u0011\u000f\u0005\r\u0014\u0011N.bS6\u0011\u0011Q\r\u0006\u0004\u0003Ob\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003W\n)GA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\u001c\u0002v5\u0011\u0011\u0011\u000f\u0006\u0005\u0003g\ni!\u0001\u0002j_&\u0019q+!\u001d\u0015\u0005\u0005u\u0013!B1qa2LH#B5\u0002~\u0005}\u0004\"B-\u001b\u0001\u0004Y\u0006\"B0\u001b\u0001\u0004\t\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u000b\u000b\t\nE\u0003,\u0003\u000f\u000bY)C\u0002\u0002\n2\u0012aa\u00149uS>t\u0007#B\u0016\u0002\u000en\u000b\u0017bAAHY\t1A+\u001e9mKJB\u0001\"a%\u001c\u0003\u0003\u0005\r![\u0001\u0004q\u0012\u0002\u0014!B<sSR,GCCAM\u0003?\u000b\u0019+a-\u0002DB\u00191&a'\n\u0007\u0005uEF\u0001\u0003V]&$\bBBAQ9\u0001\u0007q(\u0001\u0003qCRD\u0007bBAS9\u0001\u0007\u0011qU\u0001\rgB\f'o[*fgNLwN\u001c\t\u0005\u0003S\u000by+\u0004\u0002\u0002,*\u0019\u0011Q\u0016\u0012\u0002\u0007M\fH.\u0003\u0003\u00022\u0006-&\u0001D*qCJ\\7+Z:tS>t\u0007bBA[9\u0001\u0007\u0011qW\u0001\n_B$\u0018n\u001c8NCB\u0004b!!/\u0002@~zTBAA^\u0015\u0011\ti,a\r\u0002\u000f5,H/\u00192mK&!\u0011\u0011YA^\u0005\ri\u0015\r\u001d\u0005\b\u0003\u000bd\u0002\u0019AAd\u0003\u0015\u0019H/Y4f!\u0011\tI-a3\u000e\u0003\u0001J1!!4!\u00055\u0001\u0016\u000e]3mS:,7\u000b^1hK\u0002"
)
public class PMMLLinearRegressionModelWriter implements MLFormatRegister {
   private volatile Data$ Data$module;

   public String shortName() {
      return MLFormatRegister.shortName$(this);
   }

   private Data$ Data() {
      if (this.Data$module == null) {
         this.Data$lzycompute$2();
      }

      return this.Data$module;
   }

   public String format() {
      return "pmml";
   }

   public String stageName() {
      return "org.apache.spark.ml.regression.LinearRegressionModel";
   }

   public void write(final String path, final SparkSession sparkSession, final Map optionMap, final PipelineStage stage) {
      SparkContext sc = sparkSession.sparkContext();
      LinearRegressionModel instance = (LinearRegressionModel)stage;
      org.apache.spark.mllib.regression.LinearRegressionModel oldModel = new org.apache.spark.mllib.regression.LinearRegressionModel(VectorImplicits$.MODULE$.mlVectorToMLlibVector(instance.coefficients()), instance.intercept());
      oldModel.toPMML(sc, path);
   }

   private final void Data$lzycompute$2() {
      synchronized(this){}

      try {
         if (this.Data$module == null) {
            this.Data$module = new Data$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public PMMLLinearRegressionModelWriter() {
      MLFormatRegister.$init$(this);
   }

   private class Data implements Product, Serializable {
      private final double intercept;
      private final Vector coefficients;
      // $FF: synthetic field
      public final PMMLLinearRegressionModelWriter $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double intercept() {
         return this.intercept;
      }

      public Vector coefficients() {
         return this.coefficients;
      }

      public Data copy(final double intercept, final Vector coefficients) {
         return this.org$apache$spark$ml$regression$PMMLLinearRegressionModelWriter$Data$$$outer().new Data(intercept, coefficients);
      }

      public double copy$default$1() {
         return this.intercept();
      }

      public Vector copy$default$2() {
         return this.coefficients();
      }

      public String productPrefix() {
         return "Data";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToDouble(this.intercept());
            }
            case 1 -> {
               return this.coefficients();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Data;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "intercept";
            }
            case 1 -> {
               return "coefficients";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.intercept()));
         var1 = Statics.mix(var1, Statics.anyHash(this.coefficients()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label56: {
               if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$regression$PMMLLinearRegressionModelWriter$Data$$$outer() == this.org$apache$spark$ml$regression$PMMLLinearRegressionModelWriter$Data$$$outer()) {
                  Data var4 = (Data)x$1;
                  if (this.intercept() == var4.intercept()) {
                     label46: {
                        Vector var10000 = this.coefficients();
                        Vector var5 = var4.coefficients();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label46;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label46;
                        }

                        if (var4.canEqual(this)) {
                           break label56;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      // $FF: synthetic method
      public PMMLLinearRegressionModelWriter org$apache$spark$ml$regression$PMMLLinearRegressionModelWriter$Data$$$outer() {
         return this.$outer;
      }

      public Data(final double intercept, final Vector coefficients) {
         this.intercept = intercept;
         this.coefficients = coefficients;
         if (PMMLLinearRegressionModelWriter.this == null) {
            throw null;
         } else {
            this.$outer = PMMLLinearRegressionModelWriter.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class Data$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final PMMLLinearRegressionModelWriter $outer;

      public final String toString() {
         return "Data";
      }

      public Data apply(final double intercept, final Vector coefficients) {
         return this.$outer.new Data(intercept, coefficients);
      }

      public Option unapply(final Data x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.intercept()), x$0.coefficients())));
      }

      public Data$() {
         if (PMMLLinearRegressionModelWriter.this == null) {
            throw null;
         } else {
            this.$outer = PMMLLinearRegressionModelWriter.this;
            super();
         }
      }
   }
}
