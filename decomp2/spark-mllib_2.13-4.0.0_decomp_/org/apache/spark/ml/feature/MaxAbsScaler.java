package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001B\b\u0011\u0001mA\u0001\"\f\u0001\u0003\u0006\u0004%\tE\f\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005_!)q\t\u0001C\u0001\u0011\")q\t\u0001C\u0001\u001b\")q\n\u0001C\u0001!\")Q\u000b\u0001C\u0001-\")\u0011\f\u0001C!5\")\u0011\u000f\u0001C!e\")A\u0010\u0001C!{\u001e9\u0011\u0011\u0003\t\t\u0002\u0005MaAB\b\u0011\u0011\u0003\t)\u0002\u0003\u0004H\u0017\u0011\u0005\u00111\u0007\u0005\b\u0003kYA\u0011IA\u001c\u0011%\tydCA\u0001\n\u0013\t\tE\u0001\u0007NCb\f%m]*dC2,'O\u0003\u0002\u0012%\u00059a-Z1ukJ,'BA\n\u0015\u0003\tiGN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u000f%OA\u0019QD\b\u0011\u000e\u0003II!a\b\n\u0003\u0013\u0015\u001bH/[7bi>\u0014\bCA\u0011#\u001b\u0005\u0001\u0012BA\u0012\u0011\u0005Ei\u0015\r_!cgN\u001b\u0017\r\\3s\u001b>$W\r\u001c\t\u0003C\u0015J!A\n\t\u0003%5\u000b\u00070\u00112t'\u000e\fG.\u001a:QCJ\fWn\u001d\t\u0003Q-j\u0011!\u000b\u0006\u0003UI\tA!\u001e;jY&\u0011A&\u000b\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002_A\u0011\u0001'\u000f\b\u0003c]\u0002\"AM\u001b\u000e\u0003MR!\u0001\u000e\u000e\u0002\rq\u0012xn\u001c;?\u0015\u00051\u0014!B:dC2\f\u0017B\u0001\u001d6\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005a*\u0004fA\u0001>\u0007B\u0011a(Q\u0007\u0002\u007f)\u0011\u0001\tF\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\"@\u0005\u0015\u0019\u0016N\\2fC\u0005!\u0015!\u0002\u001a/a9\u0002\u0014\u0001B;jI\u0002B3AA\u001fD\u0003\u0019a\u0014N\\5u}Q\u0011\u0011J\u0013\t\u0003C\u0001AQ!L\u0002A\u0002=B3AS\u001fDQ\r\u0019Qh\u0011\u000b\u0002\u0013\"\u001aA!P\"\u0002\u0017M,G/\u00138qkR\u001cu\u000e\u001c\u000b\u0003#Jk\u0011\u0001\u0001\u0005\u0006'\u0016\u0001\raL\u0001\u0006m\u0006dW/\u001a\u0015\u0004\u000bu\u001a\u0015\u0001D:fi>+H\u000f];u\u0007>dGCA)X\u0011\u0015\u0019f\u00011\u00010Q\r1QhQ\u0001\u0004M&$HC\u0001\u0011\\\u0011\u0015av\u00011\u0001^\u0003\u001d!\u0017\r^1tKR\u0004$A\u00184\u0011\u0007}\u0013G-D\u0001a\u0015\t\tG#A\u0002tc2L!a\u00191\u0003\u000f\u0011\u000bG/Y:fiB\u0011QM\u001a\u0007\u0001\t%97,!A\u0001\u0002\u000b\u0005\u0001NA\u0002`IE\n\"![7\u0011\u0005)\\W\"A\u001b\n\u00051,$a\u0002(pi\"Lgn\u001a\t\u0003U:L!a\\\u001b\u0003\u0007\u0005s\u0017\u0010K\u0002\b{\r\u000bq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0003gf\u0004\"\u0001^<\u000e\u0003UT!A\u001e1\u0002\u000bQL\b/Z:\n\u0005a,(AC*ueV\u001cG\u000fV=qK\")!\u0010\u0003a\u0001g\u000611o\u00195f[\u0006D3\u0001C\u001fD\u0003\u0011\u0019w\u000e]=\u0015\u0005%s\bBB@\n\u0001\u0004\t\t!A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002\u0004\u0005%QBAA\u0003\u0015\r\t9AE\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003\u0017\t)A\u0001\u0005QCJ\fW.T1qQ\rIQh\u0011\u0015\u0004\u0001u\u001a\u0015\u0001D'bq\u0006\u00137oU2bY\u0016\u0014\bCA\u0011\f'\u001dY\u0011qCA\u000f\u0003G\u00012A[A\r\u0013\r\tY\"\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\t!\ny\"S\u0005\u0004\u0003CI#!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7OU3bI\u0006\u0014G.\u001a\t\u0005\u0003K\ty#\u0004\u0002\u0002()!\u0011\u0011FA\u0016\u0003\tIwN\u0003\u0002\u0002.\u0005!!.\u0019<b\u0013\u0011\t\t$a\n\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005M\u0011\u0001\u00027pC\u0012$2!SA\u001d\u0011\u0019\tY$\u0004a\u0001_\u0005!\u0001/\u0019;iQ\riQhQ\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0007\u0002B!!\u0012\u0002L5\u0011\u0011q\t\u0006\u0005\u0003\u0013\nY#\u0001\u0003mC:<\u0017\u0002BA'\u0003\u000f\u0012aa\u00142kK\u000e$\bfA\u0006>\u0007\"\u001a!\"P\""
)
public class MaxAbsScaler extends Estimator implements MaxAbsScalerParams, DefaultParamsWritable {
   private final String uid;
   private Param outputCol;
   private Param inputCol;

   public static MaxAbsScaler load(final String path) {
      return MaxAbsScaler$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MaxAbsScaler$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return MaxAbsScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public MaxAbsScaler setInputCol(final String value) {
      return (MaxAbsScaler)this.set(this.inputCol(), value);
   }

   public MaxAbsScaler setOutputCol(final String value) {
      return (MaxAbsScaler)this.set(this.outputCol(), value);
   }

   public MaxAbsScalerModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Row var4 = (Row)dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{"max", "min"}))).summary(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))).as("summary")}))).select("summary.max", .MODULE$.wrapRefArray((Object[])(new String[]{"summary.min"}))).first();
      if (var4 != null) {
         Some var5 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var4);
         if (!var5.isEmpty() && var5.get() != null && ((SeqOps)var5.get()).lengthCompare(2) == 0) {
            Object max = ((SeqOps)var5.get()).apply(0);
            Object min = ((SeqOps)var5.get()).apply(1);
            if (max instanceof Vector) {
               Vector var8 = (Vector)max;
               if (min instanceof Vector) {
                  Vector var9 = (Vector)min;
                  Tuple2 var3 = new Tuple2(var8, var9);
                  Vector max = (Vector)var3._1();
                  Vector min = (Vector)var3._2();
                  double[] maxAbs = (double[])scala.Array..MODULE$.tabulate(max.size(), (JFunction1.mcDI.sp)(i) -> scala.math.package..MODULE$.max(scala.math.package..MODULE$.abs(min.apply(i)), scala.math.package..MODULE$.abs(max.apply(i))), scala.reflect.ClassTag..MODULE$.Double());
                  return (MaxAbsScalerModel)this.copyValues((new MaxAbsScalerModel(this.uid(), org.apache.spark.ml.linalg.Vectors..MODULE$.dense(maxAbs).compressed())).setParent(this), this.copyValues$default$2());
               }
            }
         }
      }

      throw new MatchError(var4);
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public MaxAbsScaler copy(final ParamMap extra) {
      return (MaxAbsScaler)this.defaultCopy(extra);
   }

   public MaxAbsScaler(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      MaxAbsScalerParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public MaxAbsScaler() {
      this(Identifiable$.MODULE$.randomUID("maxAbsScal"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
