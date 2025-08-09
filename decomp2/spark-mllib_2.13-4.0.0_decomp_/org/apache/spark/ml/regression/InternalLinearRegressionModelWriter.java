package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLFormatRegister;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005h\u0001\u0002\u0011\"\t1BQ\u0001\u0010\u0001\u0005\u0002uBQ\u0001\u0011\u0001\u0005B\u0005CQ!\u0014\u0001\u0005B\u00053AA\u0014\u0001E\u001f\"AA\f\u0002BK\u0002\u0013\u0005Q\f\u0003\u0005b\t\tE\t\u0015!\u0003_\u0011!\u0011GA!f\u0001\n\u0003\u0019\u0007\u0002\u00036\u0005\u0005#\u0005\u000b\u0011\u00023\t\u0011-$!Q3A\u0005\u0002uC\u0001\u0002\u001c\u0003\u0003\u0012\u0003\u0006IA\u0018\u0005\u0006y\u0011!\t!\u001c\u0005\bg\u0012\t\t\u0011\"\u0001u\u0011\u001dAH!%A\u0005\u0002eD\u0011\"!\u0003\u0005#\u0003%\t!a\u0003\t\u0011\u0005=A!%A\u0005\u0002eD\u0011\"!\u0005\u0005\u0003\u0003%\t%a\u0005\t\u0013\u0005\rB!!A\u0005\u0002\u0005\u0015\u0002\"CA\u0017\t\u0005\u0005I\u0011AA\u0018\u0011%\tY\u0004BA\u0001\n\u0003\ni\u0004C\u0005\u0002L\u0011\t\t\u0011\"\u0001\u0002N!I\u0011q\u000b\u0003\u0002\u0002\u0013\u0005\u0013\u0011\f\u0005\n\u0003;\"\u0011\u0011!C!\u0003?B\u0011\"!\u0019\u0005\u0003\u0003%\t%a\u0019\t\u0013\u0005\u0015D!!A\u0005B\u0005\u001dt!CA6\u0001\u0005\u0005\t\u0012BA7\r!q\u0005!!A\t\n\u0005=\u0004B\u0002\u001f\u001b\t\u0003\t9\tC\u0005\u0002bi\t\t\u0011\"\u0012\u0002d!I\u0011\u0011\u0012\u000e\u0002\u0002\u0013\u0005\u00151\u0012\u0005\n\u0003'S\u0012\u0011!CA\u0003+Cq!a*\u0001\t\u0003\nIKA\u0012J]R,'O\\1m\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:lu\u000eZ3m/JLG/\u001a:\u000b\u0005\t\u001a\u0013A\u0003:fOJ,7o]5p]*\u0011A%J\u0001\u0003[2T!AJ\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005!J\u0013AB1qC\u000eDWMC\u0001+\u0003\ry'oZ\u0002\u0001'\u0011\u0001QfM\u001d\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\r\u0005s\u0017PU3g!\t!t'D\u00016\u0015\t14%\u0001\u0003vi&d\u0017B\u0001\u001d6\u00059iEj\u0016:ji\u0016\u0014hi\u001c:nCR\u0004\"\u0001\u000e\u001e\n\u0005m*$\u0001E'M\r>\u0014X.\u0019;SK\u001eL7\u000f^3s\u0003\u0019a\u0014N\\5u}Q\ta\b\u0005\u0002@\u00015\t\u0011%\u0001\u0004g_Jl\u0017\r\u001e\u000b\u0002\u0005B\u00111I\u0013\b\u0003\t\"\u0003\"!R\u0018\u000e\u0003\u0019S!aR\u0016\u0002\rq\u0012xn\u001c;?\u0013\tIu&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00172\u0013aa\u0015;sS:<'BA%0\u0003%\u0019H/Y4f\u001d\u0006lWM\u0001\u0003ECR\f7\u0003\u0002\u0003.!N\u0003\"AL)\n\u0005I{#a\u0002)s_\u0012,8\r\u001e\t\u0003)fs!!V,\u000f\u0005\u00153\u0016\"\u0001\u0019\n\u0005a{\u0013a\u00029bG.\fw-Z\u0005\u00035n\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001W\u0018\u0002\u0013%tG/\u001a:dKB$X#\u00010\u0011\u00059z\u0016B\u000110\u0005\u0019!u.\u001e2mK\u0006Q\u0011N\u001c;fe\u000e,\u0007\u000f\u001e\u0011\u0002\u0019\r|WM\u001a4jG&,g\u000e^:\u0016\u0003\u0011\u0004\"!\u001a5\u000e\u0003\u0019T!aZ\u0012\u0002\r1Lg.\u00197h\u0013\tIgM\u0001\u0004WK\u000e$xN]\u0001\u000eG>,gMZ5dS\u0016tGo\u001d\u0011\u0002\u000bM\u001c\u0017\r\\3\u0002\rM\u001c\u0017\r\\3!)\u0011q\u0007/\u001d:\u0011\u0005=$Q\"\u0001\u0001\t\u000bq[\u0001\u0019\u00010\t\u000b\t\\\u0001\u0019\u00013\t\u000b-\\\u0001\u0019\u00010\u0002\t\r|\u0007/\u001f\u000b\u0005]V4x\u000fC\u0004]\u0019A\u0005\t\u0019\u00010\t\u000f\td\u0001\u0013!a\u0001I\"91\u000e\u0004I\u0001\u0002\u0004q\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002u*\u0012al_\u0016\u0002yB\u0019Q0!\u0002\u000e\u0003yT1a`A\u0001\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0004=\n!\"\u00198o_R\fG/[8o\u0013\r\t9A \u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003\u001bQ#\u0001Z>\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0006\u0011\t\u0005]\u0011\u0011E\u0007\u0003\u00033QA!a\u0007\u0002\u001e\u0005!A.\u00198h\u0015\t\ty\"\u0001\u0003kCZ\f\u0017bA&\u0002\u001a\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011q\u0005\t\u0004]\u0005%\u0012bAA\u0016_\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011GA\u001c!\rq\u00131G\u0005\u0004\u0003ky#aA!os\"I\u0011\u0011\b\n\u0002\u0002\u0003\u0007\u0011qE\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005}\u0002CBA!\u0003\u000f\n\t$\u0004\u0002\u0002D)\u0019\u0011QI\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002J\u0005\r#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0014\u0002VA\u0019a&!\u0015\n\u0007\u0005MsFA\u0004C_>dW-\u00198\t\u0013\u0005eB#!AA\u0002\u0005E\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0006\u0002\\!I\u0011\u0011H\u000b\u0002\u0002\u0003\u0007\u0011qE\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011qE\u0001\ti>\u001cFO]5oOR\u0011\u0011QC\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005=\u0013\u0011\u000e\u0005\n\u0003sA\u0012\u0011!a\u0001\u0003c\tA\u0001R1uCB\u0011qNG\n\u00065\u0005E\u0014Q\u0010\t\t\u0003g\nIH\u00183_]6\u0011\u0011Q\u000f\u0006\u0004\u0003oz\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003w\n)HA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a \u0002\u00066\u0011\u0011\u0011\u0011\u0006\u0005\u0003\u0007\u000bi\"\u0001\u0002j_&\u0019!,!!\u0015\u0005\u00055\u0014!B1qa2LHc\u00028\u0002\u000e\u0006=\u0015\u0011\u0013\u0005\u00069v\u0001\rA\u0018\u0005\u0006Ev\u0001\r\u0001\u001a\u0005\u0006Wv\u0001\rAX\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9*a)\u0011\u000b9\nI*!(\n\u0007\u0005muF\u0001\u0004PaRLwN\u001c\t\u0007]\u0005}e\f\u001a0\n\u0007\u0005\u0005vF\u0001\u0004UkBdWm\r\u0005\t\u0003Ks\u0012\u0011!a\u0001]\u0006\u0019\u0001\u0010\n\u0019\u0002\u000b]\u0014\u0018\u000e^3\u0015\u0015\u0005-\u0016\u0011WA[\u0003\u000b\f)\u000eE\u0002/\u0003[K1!a,0\u0005\u0011)f.\u001b;\t\r\u0005Mv\u00041\u0001C\u0003\u0011\u0001\u0018\r\u001e5\t\u000f\u0005]v\u00041\u0001\u0002:\u0006a1\u000f]1sWN+7o]5p]B!\u00111XAa\u001b\t\tiLC\u0002\u0002@\u0016\n1a]9m\u0013\u0011\t\u0019-!0\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\t\u000f\u0005\u001dw\u00041\u0001\u0002J\u0006Iq\u000e\u001d;j_:l\u0015\r\u001d\t\u0007\u0003\u0017\f\tN\u0011\"\u000e\u0005\u00055'\u0002BAh\u0003\u0007\nq!\\;uC\ndW-\u0003\u0003\u0002T\u00065'aA'ba\"9\u0011q[\u0010A\u0002\u0005e\u0017!B:uC\u001e,\u0007\u0003BAn\u0003;l\u0011aI\u0005\u0004\u0003?\u001c#!\u0004)ja\u0016d\u0017N\\3Ti\u0006<W\r"
)
public class InternalLinearRegressionModelWriter implements MLFormatRegister {
   private volatile Data$ Data$module;

   public String shortName() {
      return MLFormatRegister.shortName$(this);
   }

   private Data$ Data() {
      if (this.Data$module == null) {
         this.Data$lzycompute$1();
      }

      return this.Data$module;
   }

   public String format() {
      return "internal";
   }

   public String stageName() {
      return "org.apache.spark.ml.regression.LinearRegressionModel";
   }

   public void write(final String path, final SparkSession sparkSession, final Map optionMap, final PipelineStage stage) {
      LinearRegressionModel instance = (LinearRegressionModel)stage;
      SparkContext sc = sparkSession.sparkContext();
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, sparkSession);
      Data data = new Data(instance.intercept(), instance.coefficients(), instance.scale());
      String dataPath = (new Path(path, "data")).toString();
      .colon.colon var10001 = new .colon.colon(data, scala.collection.immutable.Nil..MODULE$);
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(InternalLinearRegressionModelWriter.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.regression.InternalLinearRegressionModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.regression.InternalLinearRegressionModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator1$1() {
         }
      }

      sparkSession.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
   }

   private final void Data$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Data$module == null) {
            this.Data$module = new Data$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public InternalLinearRegressionModelWriter() {
      MLFormatRegister.$init$(this);
   }

   private class Data implements Product, Serializable {
      private final double intercept;
      private final Vector coefficients;
      private final double scale;
      // $FF: synthetic field
      public final InternalLinearRegressionModelWriter $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double intercept() {
         return this.intercept;
      }

      public Vector coefficients() {
         return this.coefficients;
      }

      public double scale() {
         return this.scale;
      }

      public Data copy(final double intercept, final Vector coefficients, final double scale) {
         return this.org$apache$spark$ml$regression$InternalLinearRegressionModelWriter$Data$$$outer().new Data(intercept, coefficients, scale);
      }

      public double copy$default$1() {
         return this.intercept();
      }

      public Vector copy$default$2() {
         return this.coefficients();
      }

      public double copy$default$3() {
         return this.scale();
      }

      public String productPrefix() {
         return "Data";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToDouble(this.intercept());
            }
            case 1 -> {
               return this.coefficients();
            }
            case 2 -> {
               return BoxesRunTime.boxToDouble(this.scale());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
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
            case 2 -> {
               return "scale";
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
         var1 = Statics.mix(var1, Statics.doubleHash(this.scale()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label60: {
               if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$regression$InternalLinearRegressionModelWriter$Data$$$outer() == this.org$apache$spark$ml$regression$InternalLinearRegressionModelWriter$Data$$$outer()) {
                  Data var4 = (Data)x$1;
                  if (this.intercept() == var4.intercept() && this.scale() == var4.scale()) {
                     label50: {
                        Vector var10000 = this.coefficients();
                        Vector var5 = var4.coefficients();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label50;
                        }

                        if (var4.canEqual(this)) {
                           break label60;
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
      public InternalLinearRegressionModelWriter org$apache$spark$ml$regression$InternalLinearRegressionModelWriter$Data$$$outer() {
         return this.$outer;
      }

      public Data(final double intercept, final Vector coefficients, final double scale) {
         this.intercept = intercept;
         this.coefficients = coefficients;
         this.scale = scale;
         if (InternalLinearRegressionModelWriter.this == null) {
            throw null;
         } else {
            this.$outer = InternalLinearRegressionModelWriter.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class Data$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final InternalLinearRegressionModelWriter $outer;

      public final String toString() {
         return "Data";
      }

      public Data apply(final double intercept, final Vector coefficients, final double scale) {
         return this.$outer.new Data(intercept, coefficients, scale);
      }

      public Option unapply(final Data x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.intercept()), x$0.coefficients(), BoxesRunTime.boxToDouble(x$0.scale()))));
      }

      public Data$() {
         if (InternalLinearRegressionModelWriter.this == null) {
            throw null;
         } else {
            this.$outer = InternalLinearRegressionModelWriter.this;
            super();
         }
      }
   }
}
