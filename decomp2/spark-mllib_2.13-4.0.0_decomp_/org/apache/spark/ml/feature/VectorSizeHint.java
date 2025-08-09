package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t%a\u0001B\u0010!\u0001-B\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\t3\u0002\u0011\t\u0011)A\u0005\u0007\")1\f\u0001C\u00019\")1\f\u0001C\u0001E\"9A\r\u0001b\u0001\n\u0003)\u0007BB6\u0001A\u0003%a\rC\u0003n\u0001\u0011\u0005a\u000eC\u0003u\u0001\u0011\u0005Q\u000fC\u0003{\u0001\u0011\u00051\u0010C\u0004\u007f\u0001\t\u0007I\u0011I@\t\u0011\u0005%\u0001\u0001)A\u0005\u0003\u0003Aq!!\u0004\u0001\t\u0003\ty\u0001C\u0004\u0002\u0016\u0001!\t%a\u0006\t\u000f\u0005\u0005\u0004\u0001\"\u0003\u0002d!9\u0011Q\u0011\u0001\u0005B\u0005\u001d\u0005bBAG\u0001\u0011\u0005\u0013q\u0012\u0005\b\u0003;\u0003A\u0011IAP\u000f\u001d\tI\u000b\tE\u0001\u0003W3aa\b\u0011\t\u0002\u00055\u0006BB.\u0014\t\u0003\tY\r\u0003\u0006\u0002NN\u0011\r\u0011\"\u0001!\u0003\u001fD\u0001\"a7\u0014A\u0003%\u0011\u0011\u001b\u0005\u000b\u0003;\u001c\"\u0019!C\u0001A\u0005=\u0007\u0002CAp'\u0001\u0006I!!5\t\u0015\u0005\u00058C1A\u0005\u0002\u0001\ny\r\u0003\u0005\u0002dN\u0001\u000b\u0011BAi\u0011)\t)o\u0005b\u0001\n\u0003\u0001\u0013q\u001d\u0005\t\u0003_\u001c\u0002\u0015!\u0003\u0002j\"9\u0011\u0011_\n\u0005B\u0005M\b\"CA~'\u0005\u0005I\u0011BA\u007f\u000591Vm\u0019;peNK'0\u001a%j]RT!!\t\u0012\u0002\u000f\u0019,\u0017\r^;sK*\u00111\u0005J\u0001\u0003[2T!!\n\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dB\u0013AB1qC\u000eDWMC\u0001*\u0003\ry'oZ\u0002\u0001'\u0015\u0001A\u0006\r\u001d<!\tic&D\u0001#\u0013\ty#EA\u0006Ue\u0006t7OZ8s[\u0016\u0014\bCA\u00197\u001b\u0005\u0011$BA\u001a5\u0003\u0019\u0019\b.\u0019:fI*\u0011QGI\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003oI\u00121\u0002S1t\u0013:\u0004X\u000f^\"pYB\u0011\u0011'O\u0005\u0003uI\u0012\u0001\u0003S1t\u0011\u0006tG\r\\3J]Z\fG.\u001b3\u0011\u0005qzT\"A\u001f\u000b\u0005y\u0012\u0013\u0001B;uS2L!\u0001Q\u001f\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003\r\u0003\"\u0001R'\u000f\u0005\u0015[\u0005C\u0001$J\u001b\u00059%B\u0001%+\u0003\u0019a$o\\8u})\t!*A\u0003tG\u0006d\u0017-\u0003\u0002M\u0013\u00061\u0001K]3eK\u001aL!AT(\u0003\rM#(/\u001b8h\u0015\ta\u0015\nK\u0002\u0002#^\u0003\"AU+\u000e\u0003MS!\u0001\u0016\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002W'\n)1+\u001b8dK\u0006\n\u0001,A\u00033]Mr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002R/\u00061A(\u001b8jiz\"\"!X0\u0011\u0005y\u0003Q\"\u0001\u0011\t\u000b\u0005\u001b\u0001\u0019A\")\u0007}\u000bv\u000bK\u0002\u0004#^#\u0012!\u0018\u0015\u0004\tE;\u0016\u0001B:ju\u0016,\u0012A\u001a\t\u0003O\"l\u0011\u0001N\u0005\u0003SR\u0012\u0001\"\u00138u!\u0006\u0014\u0018-\u001c\u0015\u0004\u000bE;\u0016!B:ju\u0016\u0004\u0003f\u0001\u0004R/\u00069q-\u001a;TSj,W#A8\u0011\u0005A\fX\"A%\n\u0005IL%aA%oi\"\u001aq!U,\u0002\u000fM,GoU5{KR\u0011ao^\u0007\u0002\u0001!)\u0001\u0010\u0003a\u0001_\u0006)a/\u00197vK\"\u001a\u0001\"U,\u0002\u0017M,G/\u00138qkR\u001cu\u000e\u001c\u000b\u0003mrDQ\u0001_\u0005A\u0002\rC3!C)X\u00035A\u0017M\u001c3mK&sg/\u00197jIV\u0011\u0011\u0011\u0001\t\u0005O\u0006\r1)C\u0002\u0002\u0006Q\u0012Q\u0001U1sC6D3AC)X\u00039A\u0017M\u001c3mK&sg/\u00197jI\u0002B3aC)X\u0003A\u0019X\r\u001e%b]\u0012dW-\u00138wC2LG\rF\u0002w\u0003#AQ\u0001\u001f\u0007A\u0002\rC3\u0001D)X\u0003%!(/\u00198tM>\u0014X\u000e\u0006\u0003\u0002\u001a\u0005m\u0002\u0003BA\u000e\u0003kqA!!\b\u000209!\u0011qDA\u0016\u001d\u0011\t\t#!\u000b\u000f\t\u0005\r\u0012q\u0005\b\u0004\r\u0006\u0015\u0012\"A\u0015\n\u0005\u001dB\u0013BA\u0013'\u0013\r\ti\u0003J\u0001\u0004gFd\u0017\u0002BA\u0019\u0003g\tq\u0001]1dW\u0006<WMC\u0002\u0002.\u0011JA!a\u000e\u0002:\tIA)\u0019;b\rJ\fW.\u001a\u0006\u0005\u0003c\t\u0019\u0004C\u0004\u0002>5\u0001\r!a\u0010\u0002\u000f\u0011\fG/Y:fiB\"\u0011\u0011IA'!\u0019\t\u0019%!\u0012\u0002J5\u0011\u00111G\u0005\u0005\u0003\u000f\n\u0019DA\u0004ECR\f7/\u001a;\u0011\t\u0005-\u0013Q\n\u0007\u0001\t1\ty%a\u000f\u0002\u0002\u0003\u0005)\u0011AA)\u0005\ryF%M\t\u0005\u0003'\nI\u0006E\u0002q\u0003+J1!a\u0016J\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001]A.\u0013\r\ti&\u0013\u0002\u0004\u0003:L\bfA\u0007R/\u0006)b/\u00197jI\u0006$XmU2iK6\f\u0017I\u001c3TSj,GCBA3\u0003c\n\t\t\u0005\u0003\u0002h\u00055TBAA5\u0015\r\tYGI\u0001\nCR$(/\u001b2vi\u0016LA!a\u001c\u0002j\tq\u0011\t\u001e;sS\n,H/Z$s_V\u0004\bbBA:\u001d\u0001\u0007\u0011QO\u0001\u0007g\u000eDW-\\1\u0011\t\u0005]\u0014QP\u0007\u0003\u0003sRA!a\u001f\u00024\u0005)A/\u001f9fg&!\u0011qPA=\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003\u0007s\u0001\u0019AA3\u0003\u00159'o\\;q\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BA;\u0003\u0013Cq!a\u001d\u0010\u0001\u0004\t)\bK\u0002\u0010#^\u000bAaY8qsR\u0019a/!%\t\u000f\u0005M\u0005\u00031\u0001\u0002\u0016\u0006)Q\r\u001f;sCB\u0019q-a&\n\u0007\u0005eEG\u0001\u0005QCJ\fW.T1qQ\r\u0001\u0012kV\u0001\ti>\u001cFO]5oOR\t1\t\u000b\u0003\u0012#\u0006\r\u0016EAAS\u0003\u0015\u0019d\u0006\r\u00181Q\r\u0001\u0011kV\u0001\u000f-\u0016\u001cGo\u001c:TSj,\u0007*\u001b8u!\tq6cE\u0004\u0014\u0003_\u000b),a/\u0011\u0007A\f\t,C\u0002\u00024&\u0013a!\u00118z%\u00164\u0007\u0003\u0002\u001f\u00028vK1!!/>\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!!0\u0002H6\u0011\u0011q\u0018\u0006\u0005\u0003\u0003\f\u0019-\u0001\u0002j_*\u0011\u0011QY\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002J\u0006}&\u0001D*fe&\fG.\u001b>bE2,GCAAV\u0003Iy\u0005\u000bV%N\u0013N#\u0016jQ0J\u001dZ\u000bE*\u0013#\u0016\u0005\u0005E\u0007\u0003BAj\u00033l!!!6\u000b\t\u0005]\u00171Y\u0001\u0005Y\u0006tw-C\u0002O\u0003+\f1c\u0014)U\u00136K5\u000bV%D?&se+\u0011'J\t\u0002\nQ\"\u0012*S\u001fJ{\u0016J\u0014,B\u0019&#\u0015AD#S%>\u0013v,\u0013(W\u00032KE\tI\u0001\r'.K\u0005kX%O-\u0006c\u0015\nR\u0001\u000e'.K\u0005kX%O-\u0006c\u0015\n\u0012\u0011\u0002/M,\b\u000f]8si\u0016$\u0007*\u00198eY\u0016LeN^1mS\u0012\u001cXCAAu!\u0011\u0001\u00181^\"\n\u0007\u00055\u0018JA\u0003BeJ\f\u00170\u0001\rtkB\u0004xN\u001d;fI\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5eg\u0002\nA\u0001\\8bIR\u0019Q,!>\t\r\u0005]X\u00041\u0001D\u0003\u0011\u0001\u0018\r\u001e5)\u0007u\tv+\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0000B!\u00111\u001bB\u0001\u0013\u0011\u0011\u0019!!6\u0003\r=\u0013'.Z2uQ\r\u0019\u0012k\u0016\u0015\u0004%E;\u0006"
)
public class VectorSizeHint extends Transformer implements HasInputCol, HasHandleInvalid, DefaultParamsWritable {
   private final String uid;
   private final IntParam size;
   private final Param handleInvalid;
   private Param inputCol;

   public static VectorSizeHint load(final String path) {
      return VectorSizeHint$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VectorSizeHint$.MODULE$.read();
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

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
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

   public IntParam size() {
      return this.size;
   }

   public int getSize() {
      return BoxesRunTime.unboxToInt(this.getOrDefault(this.size()));
   }

   public VectorSizeHint setSize(final int value) {
      return (VectorSizeHint)this.set(this.size(), BoxesRunTime.boxToInteger(value));
   }

   public VectorSizeHint setInputCol(final String value) {
      return (VectorSizeHint)this.set(this.inputCol(), value);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public VectorSizeHint setHandleInvalid(final String value) {
      return (VectorSizeHint)this.set(this.handleInvalid(), value);
   }

   public Dataset transform(final Dataset dataset) {
      String localInputCol;
      int localSize;
      String localHandleInvalid;
      AttributeGroup newGroup;
      label69: {
         localInputCol = this.getInputCol();
         localSize = this.getSize();
         localHandleInvalid = this.getHandleInvalid();
         AttributeGroup group = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), localInputCol));
         newGroup = this.validateSchemaAndSize(dataset.schema(), group);
         String var8 = VectorSizeHint$.MODULE$.OPTIMISTIC_INVALID();
         if (localHandleInvalid == null) {
            if (var8 != null) {
               break label69;
            }
         } else if (!localHandleInvalid.equals(var8)) {
            break label69;
         }

         if (group.size() == localSize) {
            return dataset.toDF();
         }
      }

      Column var20;
      label62: {
         Column vecCol;
         label73: {
            vecCol = .MODULE$.col(localInputCol);
            Column sizeCol = .MODULE$.coalesce(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.unwrap_udt(vecCol).getField("size"), .MODULE$.array_size(.MODULE$.unwrap_udt(vecCol).getField("values"))})));
            String var10000 = VectorSizeHint$.MODULE$.OPTIMISTIC_INVALID();
            if (var10000 == null) {
               if (localHandleInvalid == null) {
                  break label73;
               }
            } else if (var10000.equals(localHandleInvalid)) {
               break label73;
            }

            label74: {
               var10000 = VectorSizeHint$.MODULE$.ERROR_INVALID();
               if (var10000 == null) {
                  if (localHandleInvalid == null) {
                     break label74;
                  }
               } else if (var10000.equals(localHandleInvalid)) {
                  break label74;
               }

               var10000 = VectorSizeHint$.MODULE$.SKIP_INVALID();
               if (var10000 == null) {
                  if (localHandleInvalid != null) {
                     throw new MatchError(localHandleInvalid);
                  }
               } else if (!var10000.equals(localHandleInvalid)) {
                  throw new MatchError(localHandleInvalid);
               }

               var20 = .MODULE$.when(vecCol.isNull().unary_$bang().$amp$amp(sizeCol.$eq$eq$eq(BoxesRunTime.boxToInteger(localSize))), vecCol).otherwise(.MODULE$.lit((Object)null));
               break label62;
            }

            var20 = .MODULE$.when(vecCol.isNull(), .MODULE$.raise_error(.MODULE$.lit("Got null vector in VectorSizeHint, set `handleInvalid` to 'skip' to filter invalid rows."))).when(sizeCol.$eq$bang$eq(BoxesRunTime.boxToInteger(localSize)), .MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("VectorSizeHint Expecting a vector of size " + localSize + " but got "), sizeCol.cast(org.apache.spark.sql.types.StringType..MODULE$)}))))).otherwise(vecCol);
            break label62;
         }

         var20 = vecCol;
      }

      Column newVecCol = var20;
      Dataset res = dataset.withColumn(localInputCol, newVecCol, newGroup.toMetadata());
      String var17 = VectorSizeHint$.MODULE$.SKIP_INVALID();
      if (localHandleInvalid == null) {
         if (var17 == null) {
            return res.na().drop((String[])((Object[])(new String[]{localInputCol})));
         }
      } else if (localHandleInvalid.equals(var17)) {
         return res.na().drop((String[])((Object[])(new String[]{localInputCol})));
      }

      return res;
   }

   private AttributeGroup validateSchemaAndSize(final StructType schema, final AttributeGroup group) {
      int localSize = this.getSize();
      String localInputCol = this.getInputCol();
      DataType inputColType = SchemaUtils$.MODULE$.getSchemaFieldType(schema, this.getInputCol());
      scala.Predef..MODULE$.require(inputColType instanceof VectorUDT, () -> {
         String var10000 = this.getInputCol();
         return "Input column, " + var10000 + " must be of Vector type, got " + inputColType;
      });
      int var7 = group.size();
      if (localSize == var7) {
         return group;
      } else if (-1 == var7) {
         return new AttributeGroup(localInputCol, localSize);
      } else {
         String msg = "Trying to set size of vectors in `" + localInputCol + "` to " + localSize + " but size already set to " + group.size() + ".";
         throw new IllegalArgumentException(msg);
      }
   }

   public StructType transformSchema(final StructType schema) {
      int fieldIndex = schema.fieldIndex(this.getInputCol());
      StructField[] fields = (StructField[])schema.fields().clone();
      StructField inputField = fields[fieldIndex];
      AttributeGroup group = AttributeGroup$.MODULE$.fromStructField(inputField);
      AttributeGroup newGroup = this.validateSchemaAndSize(schema, group);
      Metadata x$1 = newGroup.toMetadata();
      String x$2 = inputField.copy$default$1();
      DataType x$3 = inputField.copy$default$2();
      boolean x$4 = inputField.copy$default$3();
      fields[fieldIndex] = inputField.copy(x$2, x$3, x$4, x$1);
      return new StructType(fields);
   }

   public VectorSizeHint copy(final ParamMap extra) {
      return (VectorSizeHint)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "VectorSizeHint: uid=" + var10000 + ", handleInvalid=" + this.$(this.handleInvalid()) + this.get(this.size()).map((i) -> $anonfun$toString$1(BoxesRunTime.unboxToInt(i))).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$1(final int i) {
      return ", size=" + i;
   }

   public VectorSizeHint(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasHandleInvalid.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.size = new IntParam(this, "size", "Size of vectors in column.", (JFunction1.mcZI.sp)(s) -> s >= 0);
      this.handleInvalid = new Param(this, "handleInvalid", "How to handle invalid vectors in inputCol. Invalid vectors include nulls and vectors with the wrong size. The options are `skip` (filter out rows with invalid vectors), `error` (throw an error) and `optimistic` (do not check the vector size, and keep all rows). `error` by default.", ParamValidators$.MODULE$.inArray((Object)VectorSizeHint$.MODULE$.supportedHandleInvalids()), scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.setDefault(this.handleInvalid(), VectorSizeHint$.MODULE$.ERROR_INVALID());
      Statics.releaseFence();
   }

   public VectorSizeHint() {
      this(Identifiable$.MODULE$.randomUID("vectSizeHint"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
