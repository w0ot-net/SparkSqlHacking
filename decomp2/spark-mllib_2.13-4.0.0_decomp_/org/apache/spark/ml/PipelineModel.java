package org.apache.spark.ml;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uh\u0001B\u000f\u001f\u0001\u001dB\u0001\"\u000f\u0001\u0003\u0006\u0004%\tE\u000f\u0005\t#\u0002\u0011\t\u0011)A\u0005w!A1\u000b\u0001BC\u0002\u0013\u0005A\u000b\u0003\u0005^\u0001\t\u0005\t\u0015!\u0003V\u0011\u0019y\u0006\u0001\"\u0001\u001fA\"1q\f\u0001C\u0001=\u0015DQa\u001c\u0001\u0005BADq!a\f\u0001\t\u0003\n\t\u0004C\u0004\u0002J\u0001!\t%a\u0013\t\u000f\u0005}\u0003\u0001\"\u0011\u0002b\u001d9\u0011\u0011\u000f\u0010\t\u0002\u0005MdAB\u000f\u001f\u0011\u0003\t)\b\u0003\u0004`\u0019\u0011\u0005\u0011q\u0012\u0005\b\u0003#cA\u0011IAJ\u0011\u001d\ti\n\u0004C!\u0003?3q!a*\r\u00011\tI\u000b\u0003\u0006\u0002,B\u0011)\u0019!C\u0001\u0003[C\u0011\"a,\u0011\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\r}\u0003B\u0011AAY\u0011\u001d\tI\f\u0005C!\u0003wCq!!2\u0011\t#\n9\r\u0003\b\u0002LB\u0001\n1!A\u0001\n\u0013\ti-!5\u0007\r\u0005MG\u0002BAk\u0011\u0019yv\u0003\"\u0001\u0002X\"I\u00111\\\fC\u0002\u0013%\u0011Q\u001c\u0005\t\u0003S<\u0002\u0015!\u0003\u0002`\"9\u0011QT\f\u0005B\u0005-\b\"CAx\u0019\u0005\u0005I\u0011BAy\u00055\u0001\u0016\u000e]3mS:,Wj\u001c3fY*\u0011q\u0004I\u0001\u0003[2T!!\t\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\r\"\u0013AB1qC\u000eDWMC\u0001&\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0001&L\u001a\u0011\u0007%RC&D\u0001\u001f\u0013\tYcDA\u0003N_\u0012,G\u000e\u0005\u0002*\u0001A\u0011a&M\u0007\u0002_)\u0011\u0001GH\u0001\u0005kRLG.\u0003\u00023_\tQQ\nT,sSR\f'\r\\3\u0011\u0005Q:T\"A\u001b\u000b\u0005Y\u0002\u0013\u0001C5oi\u0016\u0014h.\u00197\n\u0005a*$a\u0002'pO\u001eLgnZ\u0001\u0004k&$W#A\u001e\u0011\u0005q*eBA\u001fD!\tq\u0014)D\u0001@\u0015\t\u0001e%\u0001\u0004=e>|GO\u0010\u0006\u0002\u0005\u0006)1oY1mC&\u0011A)Q\u0001\u0007!J,G-\u001a4\n\u0005\u0019;%AB*ue&twM\u0003\u0002E\u0003\"\u001a\u0011!S(\u0011\u0005)kU\"A&\u000b\u00051\u0003\u0013AC1o]>$\u0018\r^5p]&\u0011aj\u0013\u0002\u0006'&t7-Z\u0011\u0002!\u0006)\u0011G\f\u001b/a\u0005!Q/\u001b3!Q\r\u0011\u0011jT\u0001\u0007gR\fw-Z:\u0016\u0003U\u00032AV,Z\u001b\u0005\t\u0015B\u0001-B\u0005\u0015\t%O]1z!\tI#,\u0003\u0002\\=\tYAK]1og\u001a|'/\\3sQ\r\u0019\u0011jT\u0001\bgR\fw-Z:!Q\r!\u0011jT\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00071\n7\rC\u0003:\u000b\u0001\u00071\bK\u0002b\u0013>CQaU\u0003A\u0002UC3aY%P)\racm\u001a\u0005\u0006s\u0019\u0001\ra\u000f\u0005\u0006'\u001a\u0001\r\u0001\u001b\t\u0004S6LV\"\u00016\u000b\u0005AZ'\"\u00017\u0002\t)\fg/Y\u0005\u0003]*\u0014A\u0001T5ti\u0006IAO]1og\u001a|'/\u001c\u000b\u0004c\u0006\u0015\u0001C\u0001:\u0000\u001d\t\u0019HP\u0004\u0002uu:\u0011Q/\u001f\b\u0003mbt!AP<\n\u0003\u0015J!a\t\u0013\n\u0005\u0005\u0012\u0013BA>!\u0003\r\u0019\u0018\u000f\\\u0005\u0003{z\fq\u0001]1dW\u0006<WM\u0003\u0002|A%!\u0011\u0011AA\u0002\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0002~}\"9\u0011qA\u0004A\u0002\u0005%\u0011a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003\u0017\t9\u0002\u0005\u0004\u0002\u000e\u0005=\u00111C\u0007\u0002}&\u0019\u0011\u0011\u0003@\u0003\u000f\u0011\u000bG/Y:fiB!\u0011QCA\f\u0019\u0001!A\"!\u0007\u0002\u0006\u0005\u0005\t\u0011!B\u0001\u00037\u00111a\u0018\u00134#\u0011\ti\"a\t\u0011\u0007Y\u000by\"C\u0002\u0002\"\u0005\u0013qAT8uQ&tw\rE\u0002W\u0003KI1!a\nB\u0005\r\te.\u001f\u0015\u0005\u000f%\u000bY#\t\u0002\u0002.\u0005)!G\f\u0019/a\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u00024\u0005}\u0002\u0003BA\u001b\u0003wi!!a\u000e\u000b\u0007\u0005eb0A\u0003usB,7/\u0003\u0003\u0002>\u0005]\"AC*ueV\u001cG\u000fV=qK\"9\u0011\u0011\t\u0005A\u0002\u0005M\u0012AB:dQ\u0016l\u0017\r\u000b\u0003\t\u0013\u0006\u0015\u0013EAA$\u0003\u0015\tdF\r\u00181\u0003\u0011\u0019w\u000e]=\u0015\u00071\ni\u0005C\u0004\u0002P%\u0001\r!!\u0015\u0002\u000b\u0015DHO]1\u0011\t\u0005M\u0013\u0011L\u0007\u0003\u0003+R1!a\u0016\u001f\u0003\u0015\u0001\u0018M]1n\u0013\u0011\tY&!\u0016\u0003\u0011A\u000b'/Y7NCBD3!C%P\u0003\u00159(/\u001b;f+\t\t\u0019\u0007E\u0002/\u0003KJ1!a\u001a0\u0005!iEj\u0016:ji\u0016\u0014\b\u0006\u0002\u0006J\u0003W\n#!!\u001c\u0002\u000bErcG\f\u0019)\t\u0001I\u0015QI\u0001\u000e!&\u0004X\r\\5oK6{G-\u001a7\u0011\u0005%b1c\u0002\u0007\u0002x\u0005u\u00141\u0011\t\u0004-\u0006e\u0014bAA>\u0003\n1\u0011I\\=SK\u001a\u0004BALA@Y%\u0019\u0011\u0011Q\u0018\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002\u0006\u0006-UBAAD\u0015\r\tIi[\u0001\u0003S>LA!!$\u0002\b\na1+\u001a:jC2L'0\u00192mKR\u0011\u00111O\u0001\u0005e\u0016\fG-\u0006\u0002\u0002\u0016B!a&a&-\u0013\r\tIj\f\u0002\t\u001b2\u0013V-\u00193fe\"\"a\"SA6\u0003\u0011aw.\u00193\u0015\u00071\n\t\u000b\u0003\u0004\u0002$>\u0001\raO\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u0010\u0013\u0006-$a\u0005)ja\u0016d\u0017N\\3N_\u0012,Gn\u0016:ji\u0016\u00148c\u0001\t\u0002d\u0005A\u0011N\\:uC:\u001cW-F\u0001-\u0003%Ign\u001d;b]\u000e,\u0007\u0005\u0006\u0003\u00024\u0006]\u0006cAA[!5\tA\u0002\u0003\u0004\u0002,N\u0001\r\u0001L\u0001\u0005g\u00064X\r\u0006\u0003\u0002>\u0006\r\u0007c\u0001,\u0002@&\u0019\u0011\u0011Y!\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003G#\u0002\u0019A\u001e\u0002\u0011M\fg/Z%na2$B!!0\u0002J\"1\u00111U\u000bA\u0002m\n!b];qKJ$3/\u0019<f)\u0011\ti,a4\t\r\u0005\rf\u00031\u0001<\u0013\u0011\tI,!\u001a\u0003'AK\u0007/\u001a7j]\u0016lu\u000eZ3m%\u0016\fG-\u001a:\u0014\u0007]\t)\n\u0006\u0002\u0002ZB\u0019\u0011QW\f\u0002\u0013\rd\u0017m]:OC6,WCAAp!\u0011\t\t/a:\u000e\u0005\u0005\r(bAAsW\u0006!A.\u00198h\u0013\r1\u00151]\u0001\u000bG2\f7o\u001d(b[\u0016\u0004Cc\u0001\u0017\u0002n\"1\u00111U\u000eA\u0002m\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a=\u0011\t\u0005\u0005\u0018Q_\u0005\u0005\u0003o\f\u0019O\u0001\u0004PE*,7\r\u001e\u0015\u0005\u0019%\u000bY\u0007\u000b\u0003\f\u0013\u0006-\u0004"
)
public class PipelineModel extends Model implements MLWritable {
   private final String uid;
   private final Transformer[] stages;

   public static PipelineModel load(final String path) {
      return PipelineModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return PipelineModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public Transformer[] stages() {
      return this.stages;
   }

   public Dataset transform(final Dataset dataset) {
      return (Dataset)Instrumentation$.MODULE$.instrumented((instr) -> instr.withTransformEvent(this, dataset, () -> {
            this.transformSchema(dataset.schema(), true);
            return (Dataset).MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps(this.stages()), dataset.toDF(), (cur, transformer) -> instr.withTransformEvent(transformer, cur, () -> transformer.transform(cur)));
         }));
   }

   public StructType transformSchema(final StructType schema) {
      return (StructType).MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps(this.stages()), schema, (cur, transformer) -> transformer.transformSchema(cur));
   }

   public PipelineModel copy(final ParamMap extra) {
      return (PipelineModel)(new PipelineModel(this.uid(), (Transformer[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.stages()), (x$6) -> x$6.copy(extra), scala.reflect.ClassTag..MODULE$.apply(Transformer.class)))).setParent(this.parent());
   }

   public MLWriter write() {
      return new PipelineModelWriter(this);
   }

   public PipelineModel(final String uid, final Transformer[] stages) {
      this.uid = uid;
      this.stages = stages;
      MLWritable.$init$(this);
   }

   public PipelineModel(final String uid, final List stages) {
      this(uid, (Transformer[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(stages).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(Transformer.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class PipelineModelWriter extends MLWriter {
      private final PipelineModel instance;

      // $FF: synthetic method
      private void super$save(final String path) {
         super.save(path);
      }

      public PipelineModel instance() {
         return this.instance;
      }

      public void save(final String path) {
         Instrumentation$.MODULE$.instrumented((x$7) -> {
            $anonfun$save$3(this, path, x$7);
            return BoxedUnit.UNIT;
         });
      }

      public void saveImpl(final String path) {
         Pipeline.SharedReadWrite$.MODULE$.saveImpl(this.instance(), this.instance().stages(), (SparkSession)this.sparkSession(), path);
      }

      // $FF: synthetic method
      public static final void $anonfun$save$3(final PipelineModelWriter $this, final String path$5, final Instrumentation x$7) {
         x$7.withSaveInstanceEvent($this, path$5, (JFunction0.mcV.sp)() -> $this.super$save(path$5));
      }

      public PipelineModelWriter(final PipelineModel instance) {
         this.instance = instance;
         Pipeline.SharedReadWrite$.MODULE$.validateStages(instance.stages());
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class PipelineModelReader extends MLReader {
      private final String className = PipelineModel.class.getName();

      private String className() {
         return this.className;
      }

      public PipelineModel load(final String path) {
         return (PipelineModel)Instrumentation$.MODULE$.instrumented((x$8) -> (PipelineModel)x$8.withLoadInstanceEvent(this, path, () -> {
               Tuple2 var4 = Pipeline.SharedReadWrite$.MODULE$.load(this.className(), this.sparkSession(), path);
               if (var4 != null) {
                  String uid = (String)var4._1();
                  PipelineStage[] stages = (PipelineStage[])var4._2();
                  if (uid != null && stages != null) {
                     Tuple2 var3 = new Tuple2(uid, stages);
                     String uid = (String)var3._1();
                     PipelineStage[] stagesx = (PipelineStage[])var3._2();
                     Transformer[] transformers = (Transformer[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(stagesx), (x0$1) -> {
                        if (x0$1 instanceof Transformer var3) {
                           return var3;
                        } else {
                           String var10002 = x0$1.uid();
                           throw new RuntimeException("PipelineModel.read loaded a stage but found it was not a Transformer.  Bad stage " + var10002 + " of type " + x0$1.getClass());
                        }
                     }, scala.reflect.ClassTag..MODULE$.apply(Transformer.class));
                     return new PipelineModel(uid, transformers);
                  }
               }

               throw new MatchError(var4);
            }));
      }

      public PipelineModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
