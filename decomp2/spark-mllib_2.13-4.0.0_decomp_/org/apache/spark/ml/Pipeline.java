package org.apache.spark.ml;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t-e\u0001\u0002\u0015*\u0001IB\u0001\u0002\u0011\u0001\u0003\u0006\u0004%\t%\u0011\u0005\t1\u0002\u0011\t\u0011)A\u0005\u0005\")!\f\u0001C\u00017\")!\f\u0001C\u0001A\"9!\r\u0001b\u0001\n\u0003\u0019\u0007B\u0002;\u0001A\u0003%A\rC\u0003w\u0001\u0011\u0005q\u000fC\u0004\u0002\u000e\u0001!\t!a\u0004\t\u000f\u0005M\u0001\u0001\"\u0011\u0002\u0016!9\u0011Q\b\u0001\u0005B\u0005}\u0002bBA'\u0001\u0011\u0005\u0013q\n\u0005\b\u0003G\u0002A\u0011IA3\u000f\u001d\t)(\u000bE\u0001\u0003o2a\u0001K\u0015\t\u0002\u0005e\u0004B\u0002.\u000f\t\u0003\t9\nC\u0004\u0002\u001a:!\t%a'\t\u000f\u0005\u0015f\u0002\"\u0011\u0002(\u001a9\u0011q\u0016\b\u0001\u001d\u0005E\u0006BCAZ%\t\u0015\r\u0011\"\u0001\u00026\"I\u0011q\u0017\n\u0003\u0002\u0003\u0006I\u0001\u0018\u0005\u00075J!\t!!/\t\u000f\u0005\u0005'\u0003\"\u0011\u0002D\"9\u0011Q\u001a\n\u0005R\u0005=\u0007BDAj%A\u0005\u0019\u0011!A\u0005\n\u0005U\u0017\u0011\u001c\u0004\u0007\u00037tA!!8\t\riKB\u0011AAp\u0011%\t\u0019/\u0007b\u0001\n\u0013\t)\u000f\u0003\u0005\u0002rf\u0001\u000b\u0011BAt\u0011\u001d\t)+\u0007C!\u0003g<\u0001\"a>\u000f\u0011\u0003I\u0013\u0011 \u0004\t\u0003wt\u0001\u0012A\u0015\u0002~\"1!l\bC\u0001\u0003\u007fDqA!\u0001 \t\u0003\u0011\u0019\u0001C\u0004\u0002N~!\tAa\u0002\t\u000f\u00055w\u0004\"\u0001\u00036!9\u0011QU\u0010\u0005\u0002\t\u0015\u0003bBAS?\u0011\u0005!1\f\u0005\b\u0005GzB\u0011\u0001B3\u0011%\u0011iHDA\u0001\n\u0013\u0011yH\u0001\u0005QSB,G.\u001b8f\u0015\tQ3&\u0001\u0002nY*\u0011A&L\u0001\u0006gB\f'o\u001b\u0006\u0003]=\na!\u00199bG\",'\"\u0001\u0019\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0019$\bE\u00025k]j\u0011!K\u0005\u0003m%\u0012\u0011\"R:uS6\fGo\u001c:\u0011\u0005QB\u0014BA\u001d*\u00055\u0001\u0016\u000e]3mS:,Wj\u001c3fYB\u00111HP\u0007\u0002y)\u0011Q(K\u0001\u0005kRLG.\u0003\u0002@y\tQQ\nT,sSR\f'\r\\3\u0002\u0007ULG-F\u0001C!\t\u0019EJ\u0004\u0002E\u0015B\u0011Q\tS\u0007\u0002\r*\u0011q)M\u0001\u0007yI|w\u000e\u001e \u000b\u0003%\u000bQa]2bY\u0006L!a\u0013%\u0002\rA\u0013X\rZ3g\u0013\tieJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0017\"C3!\u0001)W!\t\tF+D\u0001S\u0015\t\u00196&\u0001\u0006b]:|G/\u0019;j_:L!!\u0016*\u0003\u000bMKgnY3\"\u0003]\u000bQ!\r\u00185]A\nA!^5eA!\u001a!\u0001\u0015,\u0002\rqJg.\u001b;?)\taV\f\u0005\u00025\u0001!)\u0001i\u0001a\u0001\u0005\"\u001aQ\f\u0015,)\u0007\r\u0001f\u000bF\u0001]Q\r!\u0001KV\u0001\u0007gR\fw-Z:\u0016\u0003\u0011\u00042!\u001a5k\u001b\u00051'BA4*\u0003\u0015\u0001\u0018M]1n\u0013\tIgMA\u0003QCJ\fW\u000eE\u0002lY:l\u0011\u0001S\u0005\u0003[\"\u0013Q!\u0011:sCf\u0004\"\u0001N8\n\u0005AL#!\u0004)ja\u0016d\u0017N\\3Ti\u0006<W\rK\u0002\u0006!J\f\u0013a]\u0001\u0006c9\u0012d\u0006M\u0001\bgR\fw-Z:!Q\r1\u0001K]\u0001\ng\u0016$8\u000b^1hKN$\"\u0001_=\u000e\u0003\u0001AQA_\u0004A\u0002m\fQA^1mk\u0016\u0004$\u0001`@\u0011\u0007-dW\u0010\u0005\u0002\u007f\u007f2\u0001AaCA\u0001s\u0006\u0005\t\u0011!B\u0001\u0003\u0007\u00111a\u0018\u00132#\r\t)A\u001c\t\u0004W\u0006\u001d\u0011bAA\u0005\u0011\n9aj\u001c;iS:<\u0007fA\u0004Qe\u0006Iq-\u001a;Ti\u0006<Wm]\u000b\u0002U\"\u001a\u0001\u0002\u0015:\u0002\u0007\u0019LG\u000fF\u00028\u0003/Aq!!\u0007\n\u0001\u0004\tY\"A\u0004eCR\f7/\u001a;1\t\u0005u\u00111\u0006\t\u0007\u0003?\t)#!\u000b\u000e\u0005\u0005\u0005\"bAA\u0012W\u0005\u00191/\u001d7\n\t\u0005\u001d\u0012\u0011\u0005\u0002\b\t\u0006$\u0018m]3u!\rq\u00181\u0006\u0003\r\u0003[\t9\"!A\u0001\u0002\u000b\u0005\u0011q\u0006\u0002\u0004?\u0012\u0012\u0014\u0003BA\u0003\u0003c\u00012a[A\u001a\u0013\r\t)\u0004\u0013\u0002\u0004\u0003:L\b\u0006B\u0005Q\u0003s\t#!a\u000f\u0002\u000bIr\u0003G\f\u0019\u0002\t\r|\u0007/\u001f\u000b\u00049\u0006\u0005\u0003bBA\"\u0015\u0001\u0007\u0011QI\u0001\u0006Kb$(/\u0019\t\u0004K\u0006\u001d\u0013bAA%M\nA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u000b!Z\u000bq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003#\ni\u0006\u0005\u0003\u0002T\u0005eSBAA+\u0015\u0011\t9&!\t\u0002\u000bQL\b/Z:\n\t\u0005m\u0013Q\u000b\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA0\u0017\u0001\u0007\u0011\u0011K\u0001\u0007g\u000eDW-\\1)\u0007-\u0001&/A\u0003xe&$X-\u0006\u0002\u0002hA\u00191(!\u001b\n\u0007\u0005-DH\u0001\u0005N\u0019^\u0013\u0018\u000e^3sQ\u0011a\u0001+a\u001c\"\u0005\u0005E\u0014!B\u0019/m9\u0002\u0004f\u0001\u0001Qe\u0006A\u0001+\u001b9fY&tW\r\u0005\u00025\u001dM9a\"a\u001f\u0002\u0002\u0006\u001d\u0005cA6\u0002~%\u0019\u0011q\u0010%\u0003\r\u0005s\u0017PU3g!\u0011Y\u00141\u0011/\n\u0007\u0005\u0015EH\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!!#\u0002\u00146\u0011\u00111\u0012\u0006\u0005\u0003\u001b\u000by)\u0001\u0002j_*\u0011\u0011\u0011S\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0016\u0006-%\u0001D*fe&\fG.\u001b>bE2,GCAA<\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005u\u0005\u0003B\u001e\u0002 rK1!!)=\u0005!iEJU3bI\u0016\u0014\b\u0006\u0002\tQ\u0003_\nA\u0001\\8bIR\u0019A,!+\t\r\u0005-\u0016\u00031\u0001C\u0003\u0011\u0001\u0018\r\u001e5)\tE\u0001\u0016q\u000e\u0002\u000f!&\u0004X\r\\5oK^\u0013\u0018\u000e^3s'\r\u0011\u0012qM\u0001\tS:\u001cH/\u00198dKV\tA,A\u0005j]N$\u0018M\\2fAQ!\u00111XA`!\r\tiLE\u0007\u0002\u001d!1\u00111W\u000bA\u0002q\u000bAa]1wKR!\u0011QYAf!\rY\u0017qY\u0005\u0004\u0003\u0013D%\u0001B+oSRDa!a+\u0017\u0001\u0004\u0011\u0015\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\u0005\u0015\u0017\u0011\u001b\u0005\u0007\u0003W;\u0002\u0019\u0001\"\u0002\u0015M,\b/\u001a:%g\u00064X\r\u0006\u0003\u0002F\u0006]\u0007BBAV1\u0001\u0007!)\u0003\u0003\u0002B\u0006%$A\u0004)ja\u0016d\u0017N\\3SK\u0006$WM]\n\u00043\u0005uECAAq!\r\ti,G\u0001\nG2\f7o\u001d(b[\u0016,\"!a:\u0011\t\u0005%\u0018q^\u0007\u0003\u0003WTA!!<\u0002\u0010\u0006!A.\u00198h\u0013\ri\u00151^\u0001\u000bG2\f7o\u001d(b[\u0016\u0004Cc\u0001/\u0002v\"1\u00111V\u000fA\u0002\t\u000bqb\u00155be\u0016$'+Z1e/JLG/\u001a\t\u0004\u0003{{\"aD*iCJ,GMU3bI^\u0013\u0018\u000e^3\u0014\u0007}\tY\b\u0006\u0002\u0002z\u0006qa/\u00197jI\u0006$Xm\u0015;bO\u0016\u001cH\u0003BAc\u0005\u000bAQAY\u0011A\u0002)$\"\"!2\u0003\n\tE!1\u0003B\u0010\u0011\u001d\t\u0019L\ta\u0001\u0005\u0017\u00012!\u001aB\u0007\u0013\r\u0011yA\u001a\u0002\u0007!\u0006\u0014\u0018-\\:\t\u000b\t\u0014\u0003\u0019\u00016\t\u000f\tU!\u00051\u0001\u0003\u0018\u0005\u00111o\u0019\t\u0005\u00053\u0011Y\"D\u0001,\u0013\r\u0011ib\u000b\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\u0007\u0003W\u0013\u0003\u0019\u0001\")\u0017\t\u0012\u0019C!\u000b\u0003,\t=\"\u0011\u0007\t\u0004W\n\u0015\u0012b\u0001B\u0014\u0011\nQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012!QF\u0001\u001fkN,\u0007e]1wK&k\u0007\u000f\u001c\u0011xSRD\u0007e\u00159be.\u001cVm]:j_:\fQa]5oG\u0016\f#Aa\r\u0002\u000bQr\u0003G\f\u0019\u0015\u0015\u0005\u0015'q\u0007B\u001d\u0005w\u0011\u0019\u0005C\u0004\u00024\u000e\u0002\rAa\u0003\t\u000b\t\u001c\u0003\u0019\u00016\t\r1\u001a\u0003\u0019\u0001B\u001f!\u0011\tyBa\u0010\n\t\t\u0005\u0013\u0011\u0005\u0002\r'B\f'o[*fgNLwN\u001c\u0005\u0007\u0003W\u001b\u0003\u0019\u0001\"\u0015\u0011\t\u001d#Q\nB)\u0005'\u0002Ra\u001bB%\u0005*L1Aa\u0013I\u0005\u0019!V\u000f\u001d7fe!1!q\n\u0013A\u0002\t\u000b\u0011#\u001a=qK\u000e$X\rZ\"mCN\u001ch*Y7f\u0011\u001d\u0011)\u0002\na\u0001\u0005/Aa!a+%\u0001\u0004\u0011\u0005f\u0003\u0013\u0003$\t%\"q\u000bB\u0018\u0005c\t#A!\u0017\u00025U\u001cX\r\t7pC\u0012\u0004s/\u001b;iAM\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0015\u0011\t\u001d#Q\fB0\u0005CBaAa\u0014&\u0001\u0004\u0011\u0005B\u0002\u0017&\u0001\u0004\u0011i\u0004\u0003\u0004\u0002,\u0016\u0002\rAQ\u0001\rO\u0016$8\u000b^1hKB\u000bG\u000f\u001b\u000b\n\u0005\n\u001d$1\u000eB;\u0005sBaA!\u001b'\u0001\u0004\u0011\u0015\u0001C:uC\u001e,W+\u001b3\t\u000f\t5d\u00051\u0001\u0003p\u0005A1\u000f^1hK&#\u0007\u0010E\u0002l\u0005cJ1Aa\u001dI\u0005\rIe\u000e\u001e\u0005\b\u0005o2\u0003\u0019\u0001B8\u0003%qW/\\*uC\u001e,7\u000f\u0003\u0004\u0003|\u0019\u0002\rAQ\u0001\ngR\fw-Z:ESJ\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!!\u0011\t\u0005%(1Q\u0005\u0005\u0005\u000b\u000bYO\u0001\u0004PE*,7\r\u001e\u0015\u0005\u001dA\u000by\u0007\u000b\u0003\u000e!\u0006=\u0004"
)
public class Pipeline extends Estimator implements MLWritable {
   private final String uid;
   private final Param stages;

   public static Pipeline load(final String path) {
      return Pipeline$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Pipeline$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public Param stages() {
      return this.stages;
   }

   public Pipeline setStages(final PipelineStage[] value) {
      this.set(this.stages(), value);
      return this;
   }

   public PipelineStage[] getStages() {
      return (PipelineStage[])((PipelineStage[])this.$(this.stages())).clone();
   }

   public PipelineModel fit(final Dataset dataset) {
      return (PipelineModel)Instrumentation$.MODULE$.instrumented((instr) -> (PipelineModel)instr.withFitEvent(this, dataset, () -> {
            this.transformSchema(dataset.schema(), true);
            PipelineStage[] theStages = (PipelineStage[])this.$(this.stages());
            IntRef indexOfLastEstimator = IntRef.create(-1);
            .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(theStages)).zipWithIndex().foreach((x0$1) -> {
               $anonfun$fit$3(indexOfLastEstimator, x0$1);
               return BoxedUnit.UNIT;
            });
            ObjectRef curDataset = ObjectRef.create(dataset);
            ListBuffer transformers = scala.collection.mutable.ListBuffer..MODULE$.empty();
            .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(theStages)).zipWithIndex().foreach((x0$2) -> {
               if (x0$2 != null) {
                  PipelineStage stage = (PipelineStage)x0$2._1();
                  int index = x0$2._2$mcI$sp();
                  if (index <= indexOfLastEstimator.elem) {
                     Object var10000;
                     if (stage instanceof Estimator) {
                        Estimator var12 = (Estimator)stage;
                        var10000 = instr.withFitEvent(var12, (Dataset)curDataset.elem, () -> var12.fit((Dataset)curDataset.elem));
                     } else {
                        if (!(stage instanceof Transformer)) {
                           throw new IllegalArgumentException("Does not support stage " + stage + " of type " + stage.getClass());
                        }

                        Transformer var13 = (Transformer)stage;
                        var10000 = var13;
                     }

                     Transformer transformer = (Transformer)var10000;
                     if (index < indexOfLastEstimator.elem) {
                        curDataset.elem = instr.withTransformEvent(transformer, (Dataset)curDataset.elem, () -> transformer.transform((Dataset)curDataset.elem));
                     }

                     return (ListBuffer)transformers.$plus$eq(transformer);
                  } else {
                     return (ListBuffer)transformers.$plus$eq((Transformer)stage);
                  }
               } else {
                  throw new MatchError(x0$2);
               }
            });
            return (PipelineModel)(new PipelineModel(this.uid(), (Transformer[])transformers.toArray(scala.reflect.ClassTag..MODULE$.apply(Transformer.class)))).setParent(this);
         }));
   }

   public Pipeline copy(final ParamMap extra) {
      ParamMap map = this.extractParamMap(extra);
      PipelineStage[] newStages = (PipelineStage[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(map.apply(this.stages())), (x$1) -> x$1.copy(extra), scala.reflect.ClassTag..MODULE$.apply(PipelineStage.class));
      return (new Pipeline(this.uid())).setStages(newStages);
   }

   public StructType transformSchema(final StructType schema) {
      PipelineStage[] theStages = (PipelineStage[])this.$(this.stages());
      scala.Predef..MODULE$.require(scala.Predef..MODULE$.wrapRefArray(theStages).toSet().size() == theStages.length, () -> "Cannot have duplicate components in a pipeline.");
      return (StructType).MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps(theStages), schema, (cur, stage) -> stage.transformSchema(cur));
   }

   public MLWriter write() {
      return new PipelineWriter(this);
   }

   // $FF: synthetic method
   public static final void $anonfun$fit$3(final IntRef indexOfLastEstimator$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         PipelineStage stage = (PipelineStage)x0$1._1();
         int index = x0$1._2$mcI$sp();
         if (stage instanceof Estimator) {
            indexOfLastEstimator$1.elem = index;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }

         BoxedUnit var9 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public Pipeline(final String uid) {
      this.uid = uid;
      MLWritable.$init$(this);
      this.stages = new Param(this, "stages", "stages of the pipeline", scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(PipelineStage.class)));
   }

   public Pipeline() {
      this(Identifiable$.MODULE$.randomUID("pipeline"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class PipelineWriter extends MLWriter {
      private final Pipeline instance;

      // $FF: synthetic method
      private void super$save(final String path) {
         super.save(path);
      }

      public Pipeline instance() {
         return this.instance;
      }

      public void save(final String path) {
         Instrumentation$.MODULE$.instrumented((x$2) -> {
            $anonfun$save$1(this, path, x$2);
            return BoxedUnit.UNIT;
         });
      }

      public void saveImpl(final String path) {
         Pipeline.SharedReadWrite$.MODULE$.saveImpl(this.instance(), this.instance().getStages(), (SparkSession)this.sparkSession(), path);
      }

      // $FF: synthetic method
      public static final void $anonfun$save$1(final PipelineWriter $this, final String path$1, final Instrumentation x$2) {
         x$2.withSaveInstanceEvent($this, path$1, (JFunction0.mcV.sp)() -> $this.super$save(path$1));
      }

      public PipelineWriter(final Pipeline instance) {
         this.instance = instance;
         Pipeline.SharedReadWrite$.MODULE$.validateStages(instance.getStages());
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class PipelineReader extends MLReader {
      private final String className = Pipeline.class.getName();

      private String className() {
         return this.className;
      }

      public Pipeline load(final String path) {
         return (Pipeline)Instrumentation$.MODULE$.instrumented((x$3) -> (Pipeline)x$3.withLoadInstanceEvent(this, path, () -> {
               Tuple2 var4 = Pipeline.SharedReadWrite$.MODULE$.load(this.className(), this.sparkSession(), path);
               if (var4 != null) {
                  String uid = (String)var4._1();
                  PipelineStage[] stages = (PipelineStage[])var4._2();
                  if (uid != null && stages != null) {
                     Tuple2 var3 = new Tuple2(uid, stages);
                     String uid = (String)var3._1();
                     PipelineStage[] stagesx = (PipelineStage[])var3._2();
                     return (new Pipeline(uid)).setStages(stagesx);
                  }
               }

               throw new MatchError(var4);
            }));
      }

      public PipelineReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SharedReadWrite$ {
      public static final SharedReadWrite$ MODULE$ = new SharedReadWrite$();

      public void validateStages(final PipelineStage[] stages) {
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(stages), (x0$1) -> {
            $anonfun$validateStages$1(x0$1);
            return BoxedUnit.UNIT;
         });
      }

      /** @deprecated */
      public void saveImpl(final Params instance, final PipelineStage[] stages, final SparkContext sc, final String path) {
         this.saveImpl(instance, stages, org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate(), path);
      }

      public void saveImpl(final Params instance, final PipelineStage[] stages, final SparkSession spark, final String path) {
         Instrumentation$.MODULE$.instrumented((instr) -> {
            $anonfun$saveImpl$1(stages, instance, path, spark, instr);
            return BoxedUnit.UNIT;
         });
      }

      /** @deprecated */
      public Tuple2 load(final String expectedClassName, final SparkContext sc, final String path) {
         return this.load(expectedClassName, org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate(), path);
      }

      public Tuple2 load(final String expectedClassName, final SparkSession spark, final String path) {
         return (Tuple2)Instrumentation$.MODULE$.instrumented((instr) -> {
            DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, spark, expectedClassName);
            DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
            String stagesDir = (new Path(path, "stages")).toString();
            String[] stageUids = (String[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.params()), "stageUids")), format, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.classType(String.class), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
            PipelineStage[] stages = (PipelineStage[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])stageUids))), (x0$1) -> {
               if (x0$1 != null) {
                  String stageUid = (String)x0$1._1();
                  int idx = x0$1._2$mcI$sp();
                  String stagePath = MODULE$.getStagePath(stageUid, idx, stageUids.length, stagesDir);
                  MLReader reader = DefaultParamsReader$.MODULE$.loadParamsInstanceReader(stagePath, spark);
                  return (PipelineStage)instr.withLoadInstanceEvent(reader, stagePath, () -> (PipelineStage)reader.load(stagePath));
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(PipelineStage.class));
            return new Tuple2(metadata.uid(), stages);
         });
      }

      public String getStagePath(final String stageUid, final int stageIdx, final int numStages, final String stagesDir) {
         int stageIdxDigits = Integer.toString(numStages).length();
         String idxFormat = "%0" + stageIdxDigits + "d";
         String var10000 = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString(idxFormat), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(stageIdx)}));
         String stageDir = var10000 + "_" + stageUid;
         return (new Path(stagesDir, stageDir)).toString();
      }

      // $FF: synthetic method
      public static final void $anonfun$validateStages$1(final PipelineStage x0$1) {
         if (x0$1 instanceof MLWritable) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            String var10002 = x0$1.uid();
            throw new UnsupportedOperationException("Pipeline write will fail on this Pipeline because it contains a stage which does not implement Writable. Non-Writable stage: " + var10002 + " of type " + x0$1.getClass());
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$saveImpl$4(final PipelineStage[] stages$1, final String stagesDir$1, final Instrumentation instr$2, final Tuple2 x0$1) {
         if (x0$1 != null) {
            PipelineStage stage = (PipelineStage)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            MLWriter writer = ((MLWritable)stage).write();
            String stagePath = MODULE$.getStagePath(stage.uid(), idx, stages$1.length, stagesDir$1);
            instr$2.withSaveInstanceEvent(writer, stagePath, (JFunction0.mcV.sp)() -> writer.save(stagePath));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$saveImpl$1(final PipelineStage[] stages$1, final Params instance$1, final String path$3, final SparkSession spark$1, final Instrumentation instr) {
         String[] stageUids = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(stages$1), (x$5) -> x$5.uid(), scala.reflect.ClassTag..MODULE$.apply(String.class));
         List jsonParams = new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("stageUids"), org.json4s.jackson.JsonMethods..MODULE$.parse(org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.seq2jvalue(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(stageUids).toImmutableArraySeq(), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())), org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), scala.collection.immutable.Nil..MODULE$);
         DefaultParamsWriter$.MODULE$.saveMetadata(instance$1, path$3, (SparkSession)spark$1, scala.None..MODULE$, new Some(org.json4s.JsonDSL..MODULE$.list2jvalue(jsonParams)));
         String stagesDir = (new Path(path$3, "stages")).toString();
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(stages$1))), (x0$1) -> {
            $anonfun$saveImpl$4(stages$1, stagesDir, instr, x0$1);
            return BoxedUnit.UNIT;
         });
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
