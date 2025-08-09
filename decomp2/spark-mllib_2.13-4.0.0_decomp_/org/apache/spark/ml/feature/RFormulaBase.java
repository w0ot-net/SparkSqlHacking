package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.sql.types.StructType;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=4\u0001BC\u0006\u0011\u0002\u0007\u00051\"\u0006\u0005\u0006U\u0001!\t\u0001\f\u0005\ba\u0001\u0011\r\u0011\"\u00012\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001di\u0005A1A\u0005\u00029CQ!\u0016\u0001\u0005\u0002YCqa\u0017\u0001C\u0002\u0013\u0005\u0013\u0007C\u0004`\u0001\t\u0007IQA\u0019\t\u000b\u0005\u0004A\u0011A&\t\u000b\r\u0004A\u0011\u00033\u0003\u0019I3uN]7vY\u0006\u0014\u0015m]3\u000b\u00051i\u0011a\u00024fCR,(/\u001a\u0006\u0003\u001d=\t!!\u001c7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001cR\u0001\u0001\f\u001dI\u001d\u0002\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\u000f#\u001b\u0005q\"BA\u0010!\u0003\u0019\u0019\b.\u0019:fI*\u0011\u0011%D\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003Gy\u0011a\u0002S1t\r\u0016\fG/\u001e:fg\u000e{G\u000e\u0005\u0002\u001eK%\u0011aE\b\u0002\f\u0011\u0006\u001cH*\u00192fY\u000e{G\u000e\u0005\u0002\u001eQ%\u0011\u0011F\b\u0002\u0011\u0011\u0006\u001c\b*\u00198eY\u0016LeN^1mS\u0012\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002[A\u0011qCL\u0005\u0003_a\u0011A!\u00168ji\u00069am\u001c:nk2\fW#\u0001\u001a\u0011\u0007M\"d'D\u0001!\u0013\t)\u0004EA\u0003QCJ\fW\u000e\u0005\u00028}9\u0011\u0001\b\u0010\t\u0003sai\u0011A\u000f\u0006\u0003w-\na\u0001\u0010:p_Rt\u0014BA\u001f\u0019\u0003\u0019\u0001&/\u001a3fM&\u0011q\b\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005uB\u0002f\u0001\u0002C\u0011B\u00111IR\u0007\u0002\t*\u0011QiD\u0001\u000bC:tw\u000e^1uS>t\u0017BA$E\u0005\u0015\u0019\u0016N\\2fC\u0005I\u0015!B\u0019/k9\u0002\u0014AC4fi\u001a{'/\\;mCV\ta\u0007K\u0002\u0004\u0005\"\u000bqBZ8sG\u0016Le\u000eZ3y\u0019\u0006\u0014W\r\\\u000b\u0002\u001fB\u00111\u0007U\u0005\u0003#\u0002\u0012ABQ8pY\u0016\fg\u000eU1sC6D3\u0001\u0002\"TC\u0005!\u0016!\u0002\u001a/c9\u0002\u0014AE4fi\u001a{'oY3J]\u0012,\u0007\u0010T1cK2,\u0012a\u0016\t\u0003/aK!!\u0017\r\u0003\u000f\t{w\u000e\\3b]\"\u001aQAQ*\u0002\u001b!\fg\u000e\u001a7f\u0013:4\u0018\r\\5eQ\r1!)X\u0011\u0002=\u0006)!GL\u001a/a\u000512\u000f\u001e:j]\u001eLe\u000eZ3yKJ|%\u000fZ3s)f\u0004X\rK\u0002\b\u0005v\u000b\u0011dZ3u'R\u0014\u0018N\\4J]\u0012,\u00070\u001a:Pe\u0012,'\u000fV=qK\"\u001a\u0001BQ/\u0002\u0017!\f7\u000fT1cK2\u001cu\u000e\u001c\u000b\u0003/\u0016DQAZ\u0005A\u0002\u001d\faa]2iK6\f\u0007C\u00015n\u001b\u0005I'B\u00016l\u0003\u0015!\u0018\u0010]3t\u0015\taw\"A\u0002tc2L!A\\5\u0003\u0015M#(/^2u)f\u0004X\r"
)
public interface RFormulaBase extends HasFeaturesCol, HasLabelCol, HasHandleInvalid {
   void org$apache$spark$ml$feature$RFormulaBase$_setter_$formula_$eq(final Param x$1);

   void org$apache$spark$ml$feature$RFormulaBase$_setter_$forceIndexLabel_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$feature$RFormulaBase$_setter_$handleInvalid_$eq(final Param x$1);

   void org$apache$spark$ml$feature$RFormulaBase$_setter_$stringIndexerOrderType_$eq(final Param x$1);

   Param formula();

   // $FF: synthetic method
   static String getFormula$(final RFormulaBase $this) {
      return $this.getFormula();
   }

   default String getFormula() {
      return (String)this.$(this.formula());
   }

   BooleanParam forceIndexLabel();

   // $FF: synthetic method
   static boolean getForceIndexLabel$(final RFormulaBase $this) {
      return $this.getForceIndexLabel();
   }

   default boolean getForceIndexLabel() {
      return BoxesRunTime.unboxToBoolean(this.$(this.forceIndexLabel()));
   }

   Param handleInvalid();

   Param stringIndexerOrderType();

   // $FF: synthetic method
   static String getStringIndexerOrderType$(final RFormulaBase $this) {
      return $this.getStringIndexerOrderType();
   }

   default String getStringIndexerOrderType() {
      return (String)this.$(this.stringIndexerOrderType());
   }

   // $FF: synthetic method
   static boolean hasLabelCol$(final RFormulaBase $this, final StructType schema) {
      return $this.hasLabelCol(schema);
   }

   default boolean hasLabelCol(final StructType schema) {
      return ((SeqOps)schema.map((x$1) -> x$1.name())).contains(this.$(this.labelCol()));
   }

   static void $init$(final RFormulaBase $this) {
      $this.org$apache$spark$ml$feature$RFormulaBase$_setter_$formula_$eq(new Param($this, "formula", "R model formula", .MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$RFormulaBase$_setter_$forceIndexLabel_$eq(new BooleanParam($this, "forceIndexLabel", "Force to index label whether it is numeric or string"));
      $this.org$apache$spark$ml$feature$RFormulaBase$_setter_$handleInvalid_$eq(new Param($this, "handleInvalid", "How to handle invalid data (unseen or NULL values) in features and label column of string type. Options are 'skip' (filter out rows with invalid data), error (throw an error), or 'keep' (put invalid data in a special additional bucket, at index numLabels).", ParamValidators$.MODULE$.inArray((Object)StringIndexer$.MODULE$.supportedHandleInvalids()), .MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$RFormulaBase$_setter_$stringIndexerOrderType_$eq(new Param($this, "stringIndexerOrderType", "How to order categories of a string FEATURE column used by StringIndexer. The last category after ordering is dropped when encoding strings. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])StringIndexer$.MODULE$.supportedStringOrderType()).mkString(", ") + ". The default value is 'frequencyDesc'. When the ordering is set to 'alphabetDesc', RFormula drops the same category as R when encoding strings.", ParamValidators$.MODULE$.inArray((Object)StringIndexer$.MODULE$.supportedStringOrderType()), .MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.forceIndexLabel().$minus$greater(BoxesRunTime.boxToBoolean(false)), $this.handleInvalid().$minus$greater(StringIndexer$.MODULE$.ERROR_INVALID()), $this.stringIndexerOrderType().$minus$greater(StringIndexer$.MODULE$.frequencyDesc())}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
