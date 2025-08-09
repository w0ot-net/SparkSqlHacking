package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.Attribute$;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.NumericAttribute$;
import org.apache.spark.ml.attribute.UnresolvedAttribute$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tUc\u0001\u0002\u0010 \u0001)B\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0005\u0012\u0005\t7\u0002\u0011\t\u0011)A\u0005\u000b\")Q\f\u0001C\u0001=\")Q\f\u0001C\u0001I\")a\r\u0001C\u0001O\")\u0001\u000f\u0001C\u0001c\")A\u000f\u0001C\u0001k\"9!\u0010\u0001b\u0001\n\u0003Z\bbBA\u0002\u0001\u0001\u0006I\u0001 \u0005\b\u0003\u000f\u0001A\u0011IA\u0005\u0011\u001d\t9\u0006\u0001C!\u00033Bq!!\u001c\u0001\t\u0003\ny\u0007C\u0004\u0002\u0002\u0002!\t%a!\b\u000f\u00055u\u0004#\u0001\u0002\u0010\u001a1ad\bE\u0001\u0003#Ca!X\b\u0005\u0002\u0005=\u0006\"CAY\u001f\t\u0007I\u0011A\u0010E\u0011\u001d\t\u0019l\u0004Q\u0001\n\u0015C\u0011\"!.\u0010\u0005\u0004%\ta\b#\t\u000f\u0005]v\u0002)A\u0005\u000b\"I\u0011\u0011X\bC\u0002\u0013\u0005q\u0004\u0012\u0005\b\u0003w{\u0001\u0015!\u0003F\u0011)\til\u0004b\u0001\n\u0003y\u0012q\u0018\u0005\b\u0003\u0003|\u0001\u0015!\u0003l\u0011!\t\u0019m\u0004C\u0001?\u0005\u0015\u0007\u0002CAz\u001f\u0011\u0005q$!>\t\u000f\t\u001dq\u0002\"\u0011\u0003\n!A!QC\b\u0005\u0002}\u00119\u0002C\u0005\u0003B=\t\t\u0011\"\u0003\u0003D\tya+Z2u_J\f5o]3nE2,'O\u0003\u0002!C\u00059a-Z1ukJ,'B\u0001\u0012$\u0003\tiGN\u0003\u0002%K\u0005)1\u000f]1sW*\u0011aeJ\u0001\u0007CB\f7\r[3\u000b\u0003!\n1a\u001c:h\u0007\u0001\u0019b\u0001A\u00160oij\u0004C\u0001\u0017.\u001b\u0005\t\u0013B\u0001\u0018\"\u0005-!&/\u00198tM>\u0014X.\u001a:\u0011\u0005A*T\"A\u0019\u000b\u0005I\u001a\u0014AB:iCJ,GM\u0003\u00025C\u0005)\u0001/\u0019:b[&\u0011a'\r\u0002\r\u0011\u0006\u001c\u0018J\u001c9vi\u000e{Gn\u001d\t\u0003aaJ!!O\u0019\u0003\u0019!\u000b7oT;uaV$8i\u001c7\u0011\u0005AZ\u0014B\u0001\u001f2\u0005AA\u0015m\u001d%b]\u0012dW-\u00138wC2LG\r\u0005\u0002?\u00036\tqH\u0003\u0002AC\u0005!Q\u000f^5m\u0013\t\u0011uHA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULG-F\u0001F!\t1uJ\u0004\u0002H\u001bB\u0011\u0001jS\u0007\u0002\u0013*\u0011!*K\u0001\u0007yI|w\u000e\u001e \u000b\u00031\u000bQa]2bY\u0006L!AT&\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0016K\u0001\u0004TiJLgn\u001a\u0006\u0003\u001d.C3!A*Z!\t!v+D\u0001V\u0015\t16%\u0001\u0006b]:|G/\u0019;j_:L!\u0001W+\u0003\u000bMKgnY3\"\u0003i\u000bQ!\r\u00185]A\nA!^5eA!\u001a!aU-\u0002\rqJg.\u001b;?)\ty\u0016\r\u0005\u0002a\u00015\tq\u0004C\u0003D\u0007\u0001\u0007Q\tK\u0002b'fC3aA*Z)\u0005y\u0006f\u0001\u0003T3\u0006a1/\u001a;J]B,HoQ8mgR\u0011\u0001.[\u0007\u0002\u0001!)!.\u0002a\u0001W\u0006)a/\u00197vKB\u0019A.\\#\u000e\u0003-K!A\\&\u0003\u000b\u0005\u0013(/Y=)\u0007\u0015\u0019\u0016,\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000e\u0006\u0002ie\")!N\u0002a\u0001\u000b\"\u001aaaU-\u0002!M,G\u000fS1oI2,\u0017J\u001c<bY&$GC\u00015w\u0011\u0015Qw\u00011\u0001FQ\r91\u000b_\u0011\u0002s\u0006)!G\f\u001b/a\u0005i\u0001.\u00198eY\u0016LeN^1mS\u0012,\u0012\u0001 \t\u0004{z,U\"A\u001a\n\u0005}\u001c$!\u0002)be\u0006l\u0007f\u0001\u0005Tq\u0006q\u0001.\u00198eY\u0016LeN^1mS\u0012\u0004\u0003fA\u0005Tq\u0006IAO]1og\u001a|'/\u001c\u000b\u0005\u0003\u0017\ti\u0003\u0005\u0003\u0002\u000e\u0005\u001db\u0002BA\b\u0003CqA!!\u0005\u0002\u001e9!\u00111CA\u000e\u001d\u0011\t)\"!\u0007\u000f\u0007!\u000b9\"C\u0001)\u0013\t1s%\u0003\u0002%K%\u0019\u0011qD\u0012\u0002\u0007M\fH.\u0003\u0003\u0002$\u0005\u0015\u0012a\u00029bG.\fw-\u001a\u0006\u0004\u0003?\u0019\u0013\u0002BA\u0015\u0003W\u0011\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005\r\u0012Q\u0005\u0005\b\u0003_Q\u0001\u0019AA\u0019\u0003\u001d!\u0017\r^1tKR\u0004D!a\r\u0002@A1\u0011QGA\u001c\u0003wi!!!\n\n\t\u0005e\u0012Q\u0005\u0002\b\t\u0006$\u0018m]3u!\u0011\ti$a\u0010\r\u0001\u0011a\u0011\u0011IA\u0017\u0003\u0003\u0005\tQ!\u0001\u0002D\t\u0019q\fJ\u0019\u0012\t\u0005\u0015\u00131\n\t\u0004Y\u0006\u001d\u0013bAA%\u0017\n9aj\u001c;iS:<\u0007c\u00017\u0002N%\u0019\u0011qJ&\u0003\u0007\u0005s\u0017\u0010\u000b\u0003\u000b'\u0006M\u0013EAA+\u0003\u0015\u0011d\u0006\r\u00181\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BA.\u0003O\u0002B!!\u0018\u0002d5\u0011\u0011q\f\u0006\u0005\u0003C\n)#A\u0003usB,7/\u0003\u0003\u0002f\u0005}#AC*ueV\u001cG\u000fV=qK\"9\u0011\u0011N\u0006A\u0002\u0005m\u0013AB:dQ\u0016l\u0017\rK\u0002\f'f\u000bAaY8qsR\u0019q,!\u001d\t\u000f\u0005MD\u00021\u0001\u0002v\u0005)Q\r\u001f;sCB\u0019Q0a\u001e\n\u0007\u0005e4G\u0001\u0005QCJ\fW.T1qQ\u0011a1+! \"\u0005\u0005}\u0014!B\u0019/i9\n\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0015CC!D*\u0002\b\u0006\u0012\u0011\u0011R\u0001\u0006g9\u0002d\u0006\r\u0015\u0004\u0001MK\u0016a\u0004,fGR|'/Q:tK6\u0014G.\u001a:\u0011\u0005\u0001|1cB\b\u0002\u0014\u0006e\u0015q\u0014\t\u0004Y\u0006U\u0015bAAL\u0017\n1\u0011I\\=SK\u001a\u0004BAPAN?&\u0019\u0011QT \u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011\u0011UAV\u001b\t\t\u0019K\u0003\u0003\u0002&\u0006\u001d\u0016AA5p\u0015\t\tI+\u0001\u0003kCZ\f\u0017\u0002BAW\u0003G\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a$\u0002\u0019M[\u0015\nU0J\u001dZ\u000bE*\u0013#\u0002\u001bM[\u0015\nU0J\u001dZ\u000bE*\u0013#!\u00035)%KU(S?&se+\u0011'J\t\u0006qQI\u0015*P%~KeJV!M\u0013\u0012\u0003\u0013\u0001D&F\u000bB{\u0016J\u0014,B\u0019&#\u0015!D&F\u000bB{\u0016J\u0014,B\u0019&#\u0005%A\ftkB\u0004xN\u001d;fI\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5egV\t1.\u0001\rtkB\u0004xN\u001d;fI\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5eg\u0002\nAdZ3u-\u0016\u001cGo\u001c:MK:<G\u000f[:Ge>lg)\u001b:tiJ{w\u000f\u0006\u0004\u0002H\u0006M\u0017q\u001c\t\u0007\r\u0006%W)!4\n\u0007\u0005-\u0017KA\u0002NCB\u00042\u0001\\Ah\u0013\r\t\tn\u0013\u0002\u0004\u0013:$\bbBA\u00183\u0001\u0007\u0011Q\u001b\u0019\u0005\u0003/\fY\u000e\u0005\u0004\u00026\u0005]\u0012\u0011\u001c\t\u0005\u0003{\tY\u000e\u0002\u0007\u0002^\u0006M\u0017\u0011!A\u0001\u0006\u0003\t\u0019EA\u0002`IIBq!!9\u001a\u0001\u0004\t\u0019/A\u0004d_2,XN\\:\u0011\u000b\u0005\u0015\u0018Q^#\u000f\t\u0005\u001d\u00181\u001e\b\u0004\u0011\u0006%\u0018\"\u0001'\n\u0007\u0005\r2*\u0003\u0003\u0002p\u0006E(aA*fc*\u0019\u00111E&\u0002\u0015\u001d,G\u000fT3oORD7\u000f\u0006\u0005\u0002H\u0006](1\u0001B\u0003\u0011\u001d\tyC\u0007a\u0001\u0003s\u0004D!a?\u0002\u0000B1\u0011QGA\u001c\u0003{\u0004B!!\u0010\u0002\u0000\u0012a!\u0011AA|\u0003\u0003\u0005\tQ!\u0001\u0002D\t\u0019q\fJ\u001a\t\u000f\u0005\u0005(\u00041\u0001\u0002d\")!P\u0007a\u0001\u000b\u0006!An\\1e)\ry&1\u0002\u0005\u0007\u0005\u001bY\u0002\u0019A#\u0002\tA\fG\u000f\u001b\u0015\u00057M\u0013\t\"\t\u0002\u0003\u0014\u0005)\u0011G\f\u001c/a\u0005A\u0011m]:f[\ndW\r\u0006\u0004\u0003\u001a\tE\"q\u0007\u000b\u0005\u00057\u00119\u0003\u0005\u0003\u0003\u001e\t\rRB\u0001B\u0010\u0015\r\u0011\t#I\u0001\u0007Y&t\u0017\r\\4\n\t\t\u0015\"q\u0004\u0002\u0007-\u0016\u001cGo\u001c:\t\u000f\t%B\u00041\u0001\u0003,\u0005\u0011aO\u001e\t\u0006Y\n5\u00121J\u0005\u0004\u0005_Y%A\u0003\u001fsKB,\u0017\r^3e}!9!1\u0007\u000fA\u0002\tU\u0012a\u00027f]\u001e$\bn\u001d\t\u0005Y6\fi\rC\u0004\u0003:q\u0001\rAa\u000f\u0002\u0017-,W\r]%om\u0006d\u0017\u000e\u001a\t\u0004Y\nu\u0012b\u0001B \u0017\n9!i\\8mK\u0006t\u0017\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B#!\u0011\u00119E!\u0014\u000e\u0005\t%#\u0002\u0002B&\u0003O\u000bA\u0001\\1oO&!!q\nB%\u0005\u0019y%M[3di\"\"qb\u0015B\tQ\u0011q1K!\u0005"
)
public class VectorAssembler extends Transformer implements HasInputCols, HasOutputCol, HasHandleInvalid, DefaultParamsWritable {
   private final String uid;
   private final Param handleInvalid;
   private Param outputCol;
   private StringArrayParam inputCols;

   public static VectorAssembler load(final String path) {
      return VectorAssembler$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VectorAssembler$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public VectorAssembler setInputCols(final String[] value) {
      return (VectorAssembler)this.set(this.inputCols(), value);
   }

   public VectorAssembler setOutputCol(final String value) {
      return (VectorAssembler)this.set(this.outputCol(), value);
   }

   public VectorAssembler setHandleInvalid(final String value) {
      return (VectorAssembler)this.set(this.handleInvalid(), value);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public Dataset transform(final Dataset dataset) {
      Tuple2[] inputColsWithField;
      int[] lengths;
      Metadata metadata;
      Dataset var28;
      label65: {
         label68: {
            this.transformSchema(dataset.schema(), true);
            StructType schema = dataset.schema();
            inputColsWithField = (Tuple2[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.$(this.inputCols())), (c) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(c), SchemaUtils$.MODULE$.getSchemaField(schema, c)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            String[] vectorCols = (String[]).MODULE$.collect$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColsWithField), new Serializable() {
               private static final long serialVersionUID = 0L;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  if (x1 != null) {
                     String c = (String)x1._1();
                     StructField field = (StructField)x1._2();
                     if (field.dataType() instanceof VectorUDT) {
                        return c;
                     }
                  }

                  return default.apply(x1);
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  if (x1 != null) {
                     StructField field = (StructField)x1._2();
                     if (field.dataType() instanceof VectorUDT) {
                        return true;
                     }
                  }

                  return false;
               }
            }, scala.reflect.ClassTag..MODULE$.apply(String.class));
            Map vectorColsLengths = VectorAssembler$.MODULE$.getLengths(dataset, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(vectorCols).toImmutableArraySeq(), (String)this.$(this.handleInvalid()));
            Seq[] featureAttributesMap = (Seq[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColsWithField), (x0$1) -> {
               if (x0$1 != null) {
                  String c = (String)x0$1._1();
                  StructField field = (StructField)x0$1._2();
                  DataType var9 = field.dataType();
                  if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(var9)) {
                     Attribute attribute = Attribute$.MODULE$.fromStructField(field);
                     return UnresolvedAttribute$.MODULE$.equals(attribute) ? new scala.collection.immutable..colon.colon(NumericAttribute$.MODULE$.defaultAttr().withName(c), scala.collection.immutable.Nil..MODULE$) : new scala.collection.immutable..colon.colon(attribute.withName(c), scala.collection.immutable.Nil..MODULE$);
                  } else if (var9 instanceof NumericType ? true : org.apache.spark.sql.types.BooleanType..MODULE$.equals(var9)) {
                     return new scala.collection.immutable..colon.colon(NumericAttribute$.MODULE$.defaultAttr().withName(c), scala.collection.immutable.Nil..MODULE$);
                  } else if (var9 instanceof VectorUDT) {
                     AttributeGroup attributeGroup = AttributeGroup$.MODULE$.fromStructField(field);
                     return (Seq)(attributeGroup.attributes().isDefined() ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(attributeGroup.attributes().get()))).toImmutableArraySeq().map((x0$2) -> {
                        if (x0$2 != null) {
                           Attribute attr = (Attribute)x0$2._1();
                           int i = x0$2._2$mcI$sp();
                           return attr.name().isDefined() ? attr.withName(c + "_" + attr.name().get()) : attr.withName(c + "_" + i);
                        } else {
                           throw new MatchError(x0$2);
                        }
                     }) : scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), BoxesRunTime.unboxToInt(vectorColsLengths.apply(c))).map((i) -> $anonfun$transform$4(c, BoxesRunTime.unboxToInt(i))));
                  } else {
                     throw new SparkException("VectorAssembler does not support the " + var9 + " type");
                  }
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(Seq.class));
            Attribute[] featureAttributes = (Attribute[]).MODULE$.flatten$extension(scala.Predef..MODULE$.refArrayOps((Object[])featureAttributesMap), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.apply(Attribute.class));
            lengths = (int[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])featureAttributesMap), (a) -> BoxesRunTime.boxToInteger($anonfun$transform$5(a)), scala.reflect.ClassTag..MODULE$.Int());
            metadata = (new AttributeGroup((String)this.$(this.outputCol()), featureAttributes)).toMetadata();
            String var13 = (String)this.$(this.handleInvalid());
            String var10000 = VectorAssembler$.MODULE$.SKIP_INVALID();
            if (var10000 == null) {
               if (var13 == null) {
                  break label68;
               }
            } else if (var10000.equals(var13)) {
               break label68;
            }

            label57: {
               label69: {
                  var10000 = VectorAssembler$.MODULE$.KEEP_INVALID();
                  if (var10000 == null) {
                     if (var13 == null) {
                        break label69;
                     }
                  } else if (var10000.equals(var13)) {
                     break label69;
                  }

                  label50: {
                     var10000 = VectorAssembler$.MODULE$.ERROR_INVALID();
                     if (var10000 == null) {
                        if (var13 == null) {
                           break label50;
                        }
                     } else if (var10000.equals(var13)) {
                        break label50;
                     }

                     var27 = false;
                     break label57;
                  }

                  var27 = true;
                  break label57;
               }

               var27 = true;
            }

            if (!var27) {
               throw new MatchError(var13);
            }

            var28 = dataset;
            break label65;
         }

         var28 = dataset.na().drop((String[])this.$(this.inputCols()));
      }

      Dataset filteredDataset;
      label42: {
         label41: {
            filteredDataset = var28;
            Object var29 = this.$(this.handleInvalid());
            String var18 = VectorAssembler$.MODULE$.KEEP_INVALID();
            if (var29 == null) {
               if (var18 == null) {
                  break label41;
               }
            } else if (var29.equals(var18)) {
               break label41;
            }

            var30 = false;
            break label42;
         }

         var30 = true;
      }

      boolean keepInvalid = var30;
      functions var31 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (r) -> VectorAssembler$.MODULE$.assemble(lengths, keepInvalid, r.toSeq());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorAssembler.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorAssembler.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.sql.Row").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction assembleFunc = var31.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).asNondeterministic();
      Column[] args = (Column[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColsWithField), (x0$3) -> {
         if (x0$3 != null) {
            String c = (String)x0$3._1();
            StructField field = (StructField)x0$3._2();
            DataType var9 = field.dataType();
            if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(var9)) {
               return dataset.apply(c);
            } else if (var9 instanceof VectorUDT) {
               return dataset.apply(c);
            } else if (var9 instanceof NumericType ? true : org.apache.spark.sql.types.BooleanType..MODULE$.equals(var9)) {
               return dataset.apply(c).cast(org.apache.spark.sql.types.DoubleType..MODULE$).as(c + "_double_" + this.uid());
            } else {
               throw new MatchError(var9);
            }
         } else {
            throw new MatchError(x0$3);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
      return filteredDataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col("*"), assembleFunc.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.struct(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(args).toImmutableArraySeq())}))).as((String)this.$(this.outputCol()), metadata)})));
   }

   public StructType transformSchema(final StructType schema) {
      String[] inputColNames = (String[])this.$(this.inputCols());
      String outputColName = (String)this.$(this.outputCol());
      String[] incorrectColumns = (String[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColNames), (name) -> {
         DataType var4 = SchemaUtils$.MODULE$.getSchemaFieldType(schema, name);
         if (var4 instanceof NumericType ? true : org.apache.spark.sql.types.BooleanType..MODULE$.equals(var4)) {
            return scala.None..MODULE$;
         } else if (var4 instanceof VectorUDT) {
            return scala.None..MODULE$;
         } else {
            String var10002 = var4.catalogString();
            return new Some("Data type " + var10002 + " of column " + name + " is not supported.");
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class));
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])incorrectColumns))) {
         throw new IllegalArgumentException(scala.Predef..MODULE$.wrapRefArray((Object[])incorrectColumns).mkString("\n"));
      } else if (.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fieldNames()), outputColName)) {
         throw new IllegalArgumentException("Output column " + outputColName + " already exists.");
      } else {
         return new StructType((StructField[]).MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), new StructField(outputColName, new VectorUDT(), true, org.apache.spark.sql.types.StructField..MODULE$.$lessinit$greater$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
      }
   }

   public VectorAssembler copy(final ParamMap extra) {
      return (VectorAssembler)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "VectorAssembler: uid=" + var10000 + ", handleInvalid=" + this.$(this.handleInvalid()) + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final NumericAttribute $anonfun$transform$4(final String c$1, final int i) {
      return NumericAttribute$.MODULE$.defaultAttr().withName(c$1 + "_" + i);
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$5(final Seq a) {
      return a.length();
   }

   public VectorAssembler(final String uid) {
      this.uid = uid;
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasHandleInvalid.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.handleInvalid = new Param(this, "handleInvalid", scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Param for how to handle invalid data (NULL and NaN values). Options are 'skip' (filter out\n      |rows with invalid data), 'error' (throw an error), or 'keep' (return relevant number of NaN\n      |in the output). Column lengths are taken from the size of ML Attribute Group, which can be\n      |set using `VectorSizeHint` in a pipeline before `VectorAssembler`. Column lengths can also\n      |be inferred from first rows of the data since it is safe to do so but only in case of 'error'\n      |or 'skip'.")).replaceAll("\n", " "), ParamValidators$.MODULE$.inArray((Object)VectorAssembler$.MODULE$.supportedHandleInvalids()), scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.setDefault(this.handleInvalid(), VectorAssembler$.MODULE$.ERROR_INVALID());
      Statics.releaseFence();
   }

   public VectorAssembler() {
      this(Identifiable$.MODULE$.randomUID("vecAssembler"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
