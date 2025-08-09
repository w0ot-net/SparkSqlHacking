package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005me\u0001B\u000b\u0017\u0001\u0005B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0005\u000e\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005k!)Q\n\u0001C\u0001\u001d\")Q\n\u0001C\u0001'\")Q\u000b\u0001C\u0001-\")1\f\u0001C\u00019\")\u0011\r\u0001C\u0001E\")Q\r\u0001C\u0001M\")\u0011\u000e\u0001C\u0001U\")1\u000f\u0001C\u0001i\"1q\u000f\u0001C\u00011aDQ!\u001f\u0001\u0005BiDq!!\n\u0001\t\u0003\n9\u0003C\u0004\u0002<\u0001!\t%!\u0010\t\u000f\u0005E\u0003\u0001\"\u0011\u0002T\u001d9\u0011\u0011\f\f\t\u0002\u0005mcAB\u000b\u0017\u0011\u0003\ti\u0006\u0003\u0004N#\u0011\u0005\u00111\u0010\u0005\b\u0003{\nB\u0011IA@\u0011%\t9)EA\u0001\n\u0013\tII\u0001\u0005S\r>\u0014X.\u001e7b\u0015\t9\u0002$A\u0004gK\u0006$XO]3\u000b\u0005eQ\u0012AA7m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7\u0001A\n\u0005\u0001\tRS\u0006E\u0002$I\u0019j\u0011\u0001G\u0005\u0003Ka\u0011\u0011\"R:uS6\fGo\u001c:\u0011\u0005\u001dBS\"\u0001\f\n\u0005%2\"!\u0004*G_JlW\u000f\\1N_\u0012,G\u000e\u0005\u0002(W%\u0011AF\u0006\u0002\r%\u001a{'/\\;mC\n\u000b7/\u001a\t\u0003]Ej\u0011a\f\u0006\u0003aa\tA!\u001e;jY&\u0011!g\f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002kA\u0011ag\u0010\b\u0003ou\u0002\"\u0001O\u001e\u000e\u0003eR!A\u000f\u0011\u0002\rq\u0012xn\u001c;?\u0015\u0005a\u0014!B:dC2\f\u0017B\u0001 <\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005yZ\u0004fA\u0001D\u0013B\u0011AiR\u0007\u0002\u000b*\u0011aIG\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001%F\u0005\u0015\u0019\u0016N\\2fC\u0005Q\u0015!B\u0019/k9\u0002\u0014\u0001B;jI\u0002B3AA\"J\u0003\u0019a\u0014N\\5u}Q\u0011q\n\u0015\t\u0003O\u0001AQaM\u0002A\u0002UB3\u0001U\"JQ\r\u00191)\u0013\u000b\u0002\u001f\"\u001aAaQ%\u0002\u0015M,GOR8s[Vd\u0017\r\u0006\u0002X16\t\u0001\u0001C\u0003Z\u000b\u0001\u0007Q'A\u0003wC2,X\rK\u0002\u0006\u0007&\u000b\u0001c]3u\u0011\u0006tG\r\\3J]Z\fG.\u001b3\u0015\u0005]k\u0006\"B-\u0007\u0001\u0004)\u0004f\u0001\u0004D?\u0006\n\u0001-A\u00033]Mr\u0003'\u0001\btKR4U-\u0019;ve\u0016\u001c8i\u001c7\u0015\u0005]\u001b\u0007\"B-\b\u0001\u0004)\u0004fA\u0004D\u0013\u0006Y1/\u001a;MC\n,GnQ8m)\t9v\rC\u0003Z\u0011\u0001\u0007Q\u0007K\u0002\t\u0007&\u000b!c]3u\r>\u00148-Z%oI\u0016DH*\u00192fYR\u0011qk\u001b\u0005\u00063&\u0001\r\u0001\u001c\t\u0003[:l\u0011aO\u0005\u0003_n\u0012qAQ8pY\u0016\fg\u000eK\u0002\n\u0007F\f\u0013A]\u0001\u0006e9\nd\u0006M\u0001\u001ag\u0016$8\u000b\u001e:j]\u001eLe\u000eZ3yKJ|%\u000fZ3s)f\u0004X\r\u0006\u0002Xk\")\u0011L\u0003a\u0001k!\u001a!bQ0\u0002\u0019!\f7/\u00138uKJ\u001cW\r\u001d;\u0016\u00031\f1AZ5u)\t13\u0010C\u0003}\u0019\u0001\u0007Q0A\u0004eCR\f7/\u001a;1\u0007y\fi\u0001E\u0003\u0000\u0003\u000b\tI!\u0004\u0002\u0002\u0002)\u0019\u00111\u0001\u000e\u0002\u0007M\fH.\u0003\u0003\u0002\b\u0005\u0005!a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003\u0017\ti\u0001\u0004\u0001\u0005\u0017\u0005=10!A\u0001\u0002\u000b\u0005\u0011\u0011\u0003\u0002\u0004?\u0012\n\u0014\u0003BA\n\u00033\u00012!\\A\u000b\u0013\r\t9b\u000f\u0002\b\u001d>$\b.\u001b8h!\ri\u00171D\u0005\u0004\u0003;Y$aA!os\"\"AbQA\u0011C\t\t\u0019#A\u00033]Ar\u0003'A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\tI#!\u000e\u0011\t\u0005-\u0012\u0011G\u0007\u0003\u0003[QA!a\f\u0002\u0002\u0005)A/\u001f9fg&!\u00111GA\u0017\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003oi\u0001\u0019AA\u0015\u0003\u0019\u00198\r[3nC\"\u001aQbQ%\u0002\t\r|\u0007/\u001f\u000b\u0004\u001f\u0006}\u0002bBA!\u001d\u0001\u0007\u00111I\u0001\u0006Kb$(/\u0019\t\u0005\u0003\u000b\nY%\u0004\u0002\u0002H)\u0019\u0011\u0011\n\r\u0002\u000bA\f'/Y7\n\t\u00055\u0013q\t\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001aabQ%\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u000e\u0015\u0005\u001f\r\u000b\t\u0003K\u0002\u0001\u0007&\u000b\u0001B\u0015$pe6,H.\u0019\t\u0003OE\u0019r!EA0\u0003K\nY\u0007E\u0002n\u0003CJ1!a\u0019<\u0005\u0019\te.\u001f*fMB!a&a\u001aP\u0013\r\tIg\f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\ti'a\u001e\u000e\u0005\u0005=$\u0002BA9\u0003g\n!![8\u000b\u0005\u0005U\u0014\u0001\u00026bm\u0006LA!!\u001f\u0002p\ta1+\u001a:jC2L'0\u00192mKR\u0011\u00111L\u0001\u0005Y>\fG\rF\u0002P\u0003\u0003Ca!a!\u0014\u0001\u0004)\u0014\u0001\u00029bi\"DCaE\"\u0002\"\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0012\t\u0005\u0003\u001b\u000b\u0019*\u0004\u0002\u0002\u0010*!\u0011\u0011SA:\u0003\u0011a\u0017M\\4\n\t\u0005U\u0015q\u0012\u0002\u0007\u001f\nTWm\u0019;)\tE\u0019\u0015\u0011\u0005\u0015\u0005!\r\u000b\t\u0003"
)
public class RFormula extends Estimator implements RFormulaBase, DefaultParamsWritable {
   private final String uid;
   private Param formula;
   private BooleanParam forceIndexLabel;
   private Param handleInvalid;
   private Param stringIndexerOrderType;
   private Param labelCol;
   private Param featuresCol;

   public static RFormula load(final String path) {
      return RFormula$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RFormula$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getFormula() {
      return RFormulaBase.getFormula$(this);
   }

   public boolean getForceIndexLabel() {
      return RFormulaBase.getForceIndexLabel$(this);
   }

   public String getStringIndexerOrderType() {
      return RFormulaBase.getStringIndexerOrderType$(this);
   }

   public boolean hasLabelCol(final StructType schema) {
      return RFormulaBase.hasLabelCol$(this, schema);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public Param formula() {
      return this.formula;
   }

   public BooleanParam forceIndexLabel() {
      return this.forceIndexLabel;
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public final Param stringIndexerOrderType() {
      return this.stringIndexerOrderType;
   }

   public void org$apache$spark$ml$feature$RFormulaBase$_setter_$formula_$eq(final Param x$1) {
      this.formula = x$1;
   }

   public void org$apache$spark$ml$feature$RFormulaBase$_setter_$forceIndexLabel_$eq(final BooleanParam x$1) {
      this.forceIndexLabel = x$1;
   }

   public void org$apache$spark$ml$feature$RFormulaBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public final void org$apache$spark$ml$feature$RFormulaBase$_setter_$stringIndexerOrderType_$eq(final Param x$1) {
      this.stringIndexerOrderType = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public RFormula setFormula(final String value) {
      return (RFormula)this.set(this.formula(), value);
   }

   public RFormula setHandleInvalid(final String value) {
      return (RFormula)this.set(this.handleInvalid(), value);
   }

   public RFormula setFeaturesCol(final String value) {
      return (RFormula)this.set(this.featuresCol(), value);
   }

   public RFormula setLabelCol(final String value) {
      return (RFormula)this.set(this.labelCol(), value);
   }

   public RFormula setForceIndexLabel(final boolean value) {
      return (RFormula)this.set(this.forceIndexLabel(), BoxesRunTime.boxToBoolean(value));
   }

   public RFormula setStringIndexerOrderType(final String value) {
      return (RFormula)this.set(this.stringIndexerOrderType(), value);
   }

   public boolean hasIntercept() {
      .MODULE$.require(this.isDefined(this.formula()), () -> "Formula must be defined first.");
      return RFormulaParser$.MODULE$.parse((String)this.$(this.formula())).hasIntercept();
   }

   public RFormulaModel fit(final Dataset dataset) {
      LazyRef firstRow$lzy = new LazyRef();
      this.transformSchema(dataset.schema(), true);
      .MODULE$.require(this.isDefined(this.formula()), () -> "Formula must be defined first.");
      ParsedRFormula parsedFormula = RFormulaParser$.MODULE$.parse((String)this.$(this.formula()));
      ResolvedRFormula resolvedFormula = parsedFormula.resolve(dataset.schema());
      ArrayBuffer encoderStages = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ArrayBuffer oneHotEncodeColumns = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Map prefixesToRewrite = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ArrayBuffer tempColumns = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Seq terms = (Seq)((SeqOps)((SeqOps)resolvedFormula.terms().flatten(.MODULE$.$conforms())).distinct()).sorted(scala.math.Ordering.String..MODULE$);
      scala.collection.immutable.Map indexed = ((IterableOnceOps)((IterableOps)terms.zipWithIndex()).map((x0$1) -> {
         if (x0$1 != null) {
            String term = (String)x0$1._1();
            int i = x0$1._2$mcI$sp();
            StructField termField = SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), term);
            DataType var14 = termField.dataType();
            if (var14 instanceof StringType) {
               String indexCol = tmpColumn$1("stridx", tempColumns);
               encoderStages.$plus$eq((new StringIndexer()).setInputCol(term).setOutputCol(indexCol).setStringOrderType((String)this.$(this.stringIndexerOrderType())).setHandleInvalid((String)this.$(this.handleInvalid())));
               prefixesToRewrite.update(indexCol + "_", term + "_");
               return new Tuple2(term, indexCol);
            } else if (var14 instanceof VectorUDT) {
               AttributeGroup group = AttributeGroup$.MODULE$.fromStructField(termField);
               int size = group.size() < 0 ? ((Vector)firstRow$1(firstRow$lzy, dataset, terms).getAs(i)).size() : group.size();
               encoderStages.$plus$eq((new VectorSizeHint(this.uid())).setHandleInvalid("optimistic").setInputCol(term).setSize(size));
               return new Tuple2(term, term);
            } else {
               return new Tuple2(term, term);
            }
         } else {
            throw new MatchError(x0$1);
         }
      })).toMap(scala..less.colon.less..MODULE$.refl());
      BooleanRef keepReferenceCategory = BooleanRef.create(false);
      Seq encodedTerms = (Seq)resolvedFormula.terms().map((x0$2) -> {
         if (x0$2 != null) {
            SeqOps var11 = scala.package..MODULE$.Seq().unapplySeq(x0$2);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var11) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11), 1) == 0) {
               label59: {
                  String term = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var11), 0);
                  DataType var10000 = SchemaUtils$.MODULE$.getSchemaFieldType(dataset.schema(), term);
                  StringType var13 = org.apache.spark.sql.types.StringType..MODULE$;
                  if (var10000 == null) {
                     if (var13 != null) {
                        break label59;
                     }
                  } else if (!var10000.equals(var13)) {
                     break label59;
                  }

                  String encodedCol = tmpColumn$1("onehot", tempColumns);
                  if (!this.hasIntercept() && !keepReferenceCategory.elem) {
                     encoderStages.$plus$eq((new OneHotEncoder(this.uid())).setInputCols((String[])((Object[])(new String[]{(String)indexed.apply(term)}))).setOutputCols((String[])((Object[])(new String[]{encodedCol}))).setDropLast(false));
                     keepReferenceCategory.elem = true;
                     BoxedUnit var18 = BoxedUnit.UNIT;
                  } else {
                     oneHotEncodeColumns.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(indexed.apply(term)), encodedCol));
                  }

                  prefixesToRewrite.update(encodedCol + "_", term + "_");
                  return encodedCol;
               }
            }
         }

         if (x0$2 != null) {
            SeqOps var15 = scala.package..MODULE$.Seq().unapplySeq(x0$2);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var15) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var15)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var15), 1) == 0) {
               String term = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var15), 0);
               return term;
            }
         }

         String interactionCol = tmpColumn$1("interaction", tempColumns);
         encoderStages.$plus$eq((new Interaction()).setInputCols((String[])((IterableOnceOps)x0$2.map(indexed)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))).setOutputCol(interactionCol));
         prefixesToRewrite.update(interactionCol + "_", "");
         return interactionCol;
      });
      if (oneHotEncodeColumns.nonEmpty()) {
         Tuple2 var15 = scala.collection.ArrayOps..MODULE$.unzip$extension(.MODULE$.refArrayOps(oneHotEncodeColumns.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), .MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.apply(String.class));
         if (var15 == null) {
            throw new MatchError(var15);
         }

         String[] inputCols = (String[])var15._1();
         String[] outputCols = (String[])var15._2();
         Tuple2 var14 = new Tuple2(inputCols, outputCols);
         String[] inputCols = (String[])var14._1();
         String[] outputCols = (String[])var14._2();
         encoderStages.$plus$eq((new OneHotEncoder(this.uid())).setInputCols(inputCols).setOutputCols(outputCols).setDropLast(true));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      label31: {
         label30: {
            encoderStages.$plus$eq((new VectorAssembler(this.uid())).setInputCols((String[])encodedTerms.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))).setOutputCol((String)this.$(this.featuresCol())).setHandleInvalid((String)this.$(this.handleInvalid())));
            encoderStages.$plus$eq(new VectorAttributeRewriter((String)this.$(this.featuresCol()), prefixesToRewrite.toMap(scala..less.colon.less..MODULE$.refl())));
            encoderStages.$plus$eq(new ColumnPruner(tempColumns.toSet()));
            if (scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])dataset.schema().fieldNames()), resolvedFormula.label())) {
               DataType var22 = SchemaUtils$.MODULE$.getSchemaFieldType(dataset.schema(), resolvedFormula.label());
               StringType var20 = org.apache.spark.sql.types.StringType..MODULE$;
               if (var22 == null) {
                  if (var20 == null) {
                     break label30;
                  }
               } else if (var22.equals(var20)) {
                  break label30;
               }
            }

            if (!BoxesRunTime.unboxToBoolean(this.$(this.forceIndexLabel()))) {
               BoxedUnit var23 = BoxedUnit.UNIT;
               break label31;
            }
         }

         encoderStages.$plus$eq((new StringIndexer()).setInputCol(resolvedFormula.label()).setOutputCol((String)this.$(this.labelCol())).setHandleInvalid((String)this.$(this.handleInvalid())));
      }

      PipelineModel pipelineModel = (new Pipeline(this.uid())).setStages((PipelineStage[])encoderStages.toArray(scala.reflect.ClassTag..MODULE$.apply(PipelineStage.class))).fit(dataset);
      return (RFormulaModel)this.copyValues((new RFormulaModel(this.uid(), resolvedFormula, pipelineModel)).setParent(this), this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      .MODULE$.require(!this.hasLabelCol(schema) || !BoxesRunTime.unboxToBoolean(this.$(this.forceIndexLabel())), () -> "If label column already exists, forceIndexLabel can not be set with true.");
      return this.hasLabelCol(schema) ? new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), new StructField((String)this.$(this.featuresCol()), new VectorUDT(), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class))) : new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), new StructField((String)this.$(this.featuresCol()), new VectorUDT(), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class))), new StructField((String)this.$(this.labelCol()), org.apache.spark.sql.types.DoubleType..MODULE$, true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public RFormula copy(final ParamMap extra) {
      return (RFormula)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "RFormula: uid=" + var10000 + this.get(this.formula()).map((f) -> ", formula = " + f).getOrElse(() -> "");
   }

   private static final String tmpColumn$1(final String category, final ArrayBuffer tempColumns$1) {
      String col = Identifiable$.MODULE$.randomUID(category);
      tempColumns$1.$plus$eq(col);
      return col;
   }

   // $FF: synthetic method
   private static final Row firstRow$lzycompute$1(final LazyRef firstRow$lzy$1, final Dataset dataset$1, final Seq terms$1) {
      synchronized(firstRow$lzy$1){}

      Row var4;
      try {
         var4 = firstRow$lzy$1.initialized() ? (Row)firstRow$lzy$1.value() : (Row)firstRow$lzy$1.initialize(dataset$1.select((Seq)terms$1.map((colName) -> org.apache.spark.sql.functions..MODULE$.col(colName))).first());
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private static final Row firstRow$1(final LazyRef firstRow$lzy$1, final Dataset dataset$1, final Seq terms$1) {
      return firstRow$lzy$1.initialized() ? (Row)firstRow$lzy$1.value() : firstRow$lzycompute$1(firstRow$lzy$1, dataset$1, terms$1);
   }

   public RFormula(final String uid) {
      this.uid = uid;
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasHandleInvalid.$init$(this);
      RFormulaBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public RFormula() {
      this(Identifiable$.MODULE$.randomUID("rFormula"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
