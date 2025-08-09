package org.apache.spark.ml.util;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.ServiceLoader;
import org.apache.spark.SparkException;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.Utils.;
import scala.MatchError;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005a4A!\u0003\u0006\u0001+!A\u0001\u0005\u0001B\u0001B\u0003%\u0011\u0005C\u0003&\u0001\u0011\u0005a\u0005C\u0004*\u0001\u0001\u0007I\u0011\u0002\u0016\t\u000fa\u0002\u0001\u0019!C\u0005s!1\u0001\t\u0001Q!\n-BQ!\u0011\u0001\u0005\u0002\tCQA\u0014\u0001\u0005R=CQ!\u001b\u0001\u0005B)\u0014qbR3oKJ\fG.\u0014'Xe&$XM\u001d\u0006\u0003\u00171\tA!\u001e;jY*\u0011QBD\u0001\u0003[2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\u0002\u0001'\r\u0001aC\u0007\t\u0003/ai\u0011AC\u0005\u00033)\u0011\u0001\"\u0014'Xe&$XM\u001d\t\u00037yi\u0011\u0001\b\u0006\u0003;9\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003?q\u0011q\u0001T8hO&tw-A\u0003ti\u0006<W\r\u0005\u0002#G5\tA\"\u0003\u0002%\u0019\ti\u0001+\u001b9fY&tWm\u0015;bO\u0016\fa\u0001P5oSRtDCA\u0014)!\t9\u0002\u0001C\u0003!\u0005\u0001\u0007\u0011%\u0001\u0004t_V\u00148-Z\u000b\u0002WA\u0011A&\u000e\b\u0003[M\u0002\"AL\u0019\u000e\u0003=R!\u0001\r\u000b\u0002\rq\u0012xn\u001c;?\u0015\u0005\u0011\u0014!B:dC2\f\u0017B\u0001\u001b2\u0003\u0019\u0001&/\u001a3fM&\u0011ag\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Q\n\u0014AC:pkJ\u001cWm\u0018\u0013fcR\u0011!H\u0010\t\u0003wqj\u0011!M\u0005\u0003{E\u0012A!\u00168ji\"9q\bBA\u0001\u0002\u0004Y\u0013a\u0001=%c\u000591o\\;sG\u0016\u0004\u0013A\u00024pe6\fG\u000f\u0006\u0002D\t6\t\u0001\u0001C\u0003*\r\u0001\u00071\u0006K\u0002\u0007\r2\u0003\"a\u0012&\u000e\u0003!S!!\u0013\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002L\u0011\n)1+\u001b8dK\u0006\nQ*A\u00033]Qr\u0003'\u0001\u0005tCZ,\u0017*\u001c9m)\tQ\u0004\u000bC\u0003R\u000f\u0001\u00071&\u0001\u0003qCRD\u0007fA\u0004G\u0019\"\u001aq\u0001V0\u0011\u0007m*v+\u0003\u0002Wc\t1A\u000f\u001b:poN\u0004\"\u0001W/\u000e\u0003eS!AW.\u0002\u0005%|'\"\u0001/\u0002\t)\fg/Y\u0005\u0003=f\u00131\"S(Fq\u000e,\u0007\u000f^5p]\u0006\n\u0001-\u0001 JM\u0002\"\b.\u001a\u0011j]B,H\u000f\t9bi\"\u0004\u0013\r\u001c:fC\u0012L\b%\u001a=jgR\u001c\bEY;uA=4XM]<sSR,\u0007%[:!]>$\b%\u001a8bE2,GM\f\u0015\u0004\u000f\t<\u0007cA\u001eVGB\u0011A-Z\u0007\u0002\u001d%\u0011aM\u0004\u0002\u000f'B\f'o[#yG\u0016\u0004H/[8oC\u0005A\u0017\u0001P%gA5,H\u000e^5qY\u0016\u00043o\\;sG\u0016\u001c\bEZ8sA\u0005\u0004s-\u001b<f]\u0002\u001a\bn\u001c:uA9\fW.\u001a\u0011g_Jl\u0017\r\u001e\u0011be\u0016\u0004cm\\;oI:\nqa]3tg&|g\u000e\u0006\u0002DW\")A\u000e\u0003a\u0001[\u0006a1\u000f]1sWN+7o]5p]B\u0011a.]\u0007\u0002_*\u0011\u0001OD\u0001\u0004gFd\u0017B\u0001:p\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8oQ\t\u0001A\u000f\u0005\u0002Hk&\u0011a\u000f\u0013\u0002\t+:\u001cH/\u00192mK\"\u001a\u0001A\u0012'"
)
public class GeneralMLWriter extends MLWriter {
   private final PipelineStage stage;
   private String source;

   private String source() {
      return this.source;
   }

   private void source_$eq(final String x$1) {
      this.source = x$1;
   }

   public GeneralMLWriter format(final String source) {
      this.source_$eq(source);
      return this;
   }

   public void saveImpl(final String path) throws IOException, SparkException {
      String stageName;
      label36: {
         ClassLoader loader = .MODULE$.getContextOrSparkClassLoader();
         ServiceLoader serviceLoader = ServiceLoader.load(MLFormatRegister.class, loader);
         stageName = this.stage.getClass().getName();
         String var10000 = this.source();
         String targetName = var10000 + "+" + stageName;
         List formats = scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(serviceLoader).asScala().toList();
         List shortNames = formats.map((x$1) -> x$1.shortName());
         List var11 = formats.filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$saveImpl$2(targetName, x$2)));
         Class var21;
         if (scala.collection.immutable.Nil..MODULE$.equals(var11)) {
            Try var12 = scala.util.Try..MODULE$.apply(() -> loader.loadClass(this.source()));
            if (!(var12 instanceof Success)) {
               if (var12 instanceof Failure) {
                  Failure var15 = (Failure)var12;
                  Throwable error = var15.exception();
                  throw new SparkException("Could not load requested format " + this.source() + " for " + stageName + " (" + targetName + ") had " + formats + "supporting " + shortNames, error);
               }

               throw new MatchError(var12);
            }

            Success var13 = (Success)var12;
            Class writer = (Class)var13.value();
            var21 = writer;
         } else {
            if (!(var11 instanceof scala.collection.immutable..colon.colon)) {
               break label36;
            }

            scala.collection.immutable..colon.colon var17 = (scala.collection.immutable..colon.colon)var11;
            MLFormatRegister head = (MLFormatRegister)var17.head();
            List var19 = var17.next$access$1();
            if (!scala.collection.immutable.Nil..MODULE$.equals(var19)) {
               break label36;
            }

            var21 = head.getClass();
         }

         Class writerCls = var21;
         if (MLWriterFormat.class.isAssignableFrom(writerCls)) {
            MLWriterFormat writer = (MLWriterFormat)writerCls.getConstructor().newInstance();
            writer.write(path, this.sparkSession(), this.optionMap(), this.stage);
            return;
         }

         throw new SparkException("ML source " + this.source() + " is not a valid MLWriterFormat");
      }

      String var10002 = this.source();
      throw new SparkException("Multiple writers found for " + var10002 + "+" + stageName + ", try using the class name of the writer");
   }

   public GeneralMLWriter session(final SparkSession sparkSession) {
      return (GeneralMLWriter)super.session(sparkSession);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$saveImpl$2(final String targetName$1, final MLFormatRegister x$2) {
      return x$2.shortName().equalsIgnoreCase(targetName$1);
   }

   public GeneralMLWriter(final PipelineStage stage) {
      this.stage = stage;
      this.source = "internal";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
