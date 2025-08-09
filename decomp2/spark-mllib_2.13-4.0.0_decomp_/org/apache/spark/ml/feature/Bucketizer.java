package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.param.DoubleArrayArrayParam;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.ArrayImplicits.;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t=e\u0001B\u0015+\u0005UB\u0001B\u0016\u0001\u0003\u0006\u0004%\te\u0016\u0005\t]\u0002\u0011\t\u0011)A\u00051\")\u0001\u000f\u0001C\u0001c\")\u0001\u000f\u0001C\u0001k\"9q\u000f\u0001b\u0001\n\u0003A\bB\u0002@\u0001A\u0003%\u0011\u0010C\u0004\u0002\u0002\u0001!\t!a\u0001\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018!9\u0011\u0011\u0005\u0001\u0005\u0002\u0005\r\u0002bBA\u0015\u0001\u0011\u0005\u00111\u0006\u0005\n\u0003c\u0001!\u0019!C!\u0003gA\u0001\"!\u0011\u0001A\u0003%\u0011Q\u0007\u0005\b\u0003\u000b\u0002A\u0011AA$\u0011%\ti\u0005\u0001b\u0001\n\u0003\ty\u0005\u0003\u0005\u0002^\u0001\u0001\u000b\u0011BA)\u0011\u001d\t\t\u0007\u0001C\u0001\u0003GBq!!\u001b\u0001\t\u0003\tY\u0007C\u0004\u0002r\u0001!\t!a\u001d\t\u000f\u0005m\u0004\u0001\"\u0001\u0002~!9\u00111\u0011\u0001\u0005B\u0005\u0015\u0005bBAj\u0001\u0011%\u0011Q\u001b\u0005\b\u0003S\u0004A\u0011IAv\u0011\u001d\tI\u0010\u0001C!\u0003wDqA!\u0004\u0001\t\u0003\u0012yaB\u0004\u0003\u001a)B\tAa\u0007\u0007\r%R\u0003\u0012\u0001B\u000f\u0011\u0019\u0001(\u0004\"\u0001\u0003<!I!Q\b\u000eC\u0002\u0013\u0005!f\u0016\u0005\b\u0005\u007fQ\u0002\u0015!\u0003Y\u0011%\u0011\tE\u0007b\u0001\n\u0003Qs\u000bC\u0004\u0003Di\u0001\u000b\u0011\u0002-\t\u0013\t\u0015#D1A\u0005\u0002):\u0006b\u0002B$5\u0001\u0006I\u0001\u0017\u0005\u000b\u0005\u0013R\"\u0019!C\u0001U\t-\u0003\u0002\u0003B'5\u0001\u0006I!a\u001e\t\u0011\t=#\u0004\"\u0001+\u0005#B\u0001Ba\u0017\u001b\t\u0003Q#Q\f\u0005\t\u0005CRB\u0011\u0001\u0016\u0003d!9!Q\u000e\u000e\u0005B\t=\u0004\"\u0003B>5\u0005\u0005I\u0011\u0002B?\u0005)\u0011UoY6fi&TXM\u001d\u0006\u0003W1\nqAZ3biV\u0014XM\u0003\u0002.]\u0005\u0011Q\u000e\u001c\u0006\u0003_A\nQa\u001d9be.T!!\r\u001a\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0014aA8sO\u000e\u00011\u0003\u0003\u00017y\u0011;%*\u0014)\u0011\u0007]B$(D\u0001-\u0013\tIDFA\u0003N_\u0012,G\u000e\u0005\u0002<\u00015\t!\u0006\u0005\u0002>\u00056\taH\u0003\u0002@\u0001\u000611\u000f[1sK\u0012T!!\u0011\u0017\u0002\u000bA\f'/Y7\n\u0005\rs$\u0001\u0005%bg\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5e!\tiT)\u0003\u0002G}\tY\u0001*Y:J]B,HoQ8m!\ti\u0004*\u0003\u0002J}\ta\u0001*Y:PkR\u0004X\u000f^\"pYB\u0011QhS\u0005\u0003\u0019z\u0012A\u0002S1t\u0013:\u0004X\u000f^\"pYN\u0004\"!\u0010(\n\u0005=s$!\u0004%bg>+H\u000f];u\u0007>d7\u000f\u0005\u0002R)6\t!K\u0003\u0002TY\u0005!Q\u000f^5m\u0013\t)&KA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULG-F\u0001Y!\tI&M\u0004\u0002[AB\u00111LX\u0007\u00029*\u0011Q\fN\u0001\u0007yI|w\u000e\u001e \u000b\u0003}\u000bQa]2bY\u0006L!!\u00190\u0002\rA\u0013X\rZ3g\u0013\t\u0019GM\u0001\u0004TiJLgn\u001a\u0006\u0003CzC3!\u00014m!\t9'.D\u0001i\u0015\tIg&\u0001\u0006b]:|G/\u0019;j_:L!a\u001b5\u0003\u000bMKgnY3\"\u00035\fQ!\r\u00185]A\nA!^5eA!\u001a!A\u001a7\u0002\rqJg.\u001b;?)\tQ$\u000fC\u0003W\u0007\u0001\u0007\u0001\fK\u0002sM2D3a\u00014m)\u0005Q\u0004f\u0001\u0003gY\u000611\u000f\u001d7jiN,\u0012!\u001f\t\u0003unl\u0011\u0001Q\u0005\u0003y\u0002\u0013\u0001\u0003R8vE2,\u0017I\u001d:bsB\u000b'/Y7)\u0007\u00151G.A\u0004ta2LGo\u001d\u0011)\u0007\u00191G.A\u0005hKR\u001c\u0006\u000f\\5ugV\u0011\u0011Q\u0001\t\u0007\u0003\u000f\tI!!\u0004\u000e\u0003yK1!a\u0003_\u0005\u0015\t%O]1z!\u0011\t9!a\u0004\n\u0007\u0005EaL\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u000f\u0019d\u0017!C:fiN\u0003H.\u001b;t)\u0011\tI\"a\u0007\u000e\u0003\u0001Aq!!\b\t\u0001\u0004\t)!A\u0003wC2,X\rK\u0002\tM2\f1b]3u\u0013:\u0004X\u000f^\"pYR!\u0011\u0011DA\u0013\u0011\u0019\ti\"\u0003a\u00011\"\u001a\u0011B\u001a7\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\t\u0005e\u0011Q\u0006\u0005\u0007\u0003;Q\u0001\u0019\u0001-)\u0007)1G.A\u0007iC:$G.Z%om\u0006d\u0017\u000eZ\u000b\u0003\u0003k\u0001BA_A\u001c1&\u0019\u0011\u0011\b!\u0003\u000bA\u000b'/Y7)\t-1\u0017QH\u0011\u0003\u0003\u007f\tQA\r\u00182]A\na\u0002[1oI2,\u0017J\u001c<bY&$\u0007\u0005\u000b\u0003\rM\u0006u\u0012\u0001E:fi\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5e)\u0011\tI\"!\u0013\t\r\u0005uQ\u00021\u0001YQ\u0011ia-!\u0010\u0002\u0017M\u0004H.\u001b;t\u0003J\u0014\u0018-_\u000b\u0003\u0003#\u00022A_A*\u0013\r\t)\u0006\u0011\u0002\u0016\t>,(\r\\3BeJ\f\u00170\u0011:sCf\u0004\u0016M]1nQ\u0011qa-!\u0017\"\u0005\u0005m\u0013!\u0002\u001a/g9\u0002\u0014\u0001D:qY&$8/\u0011:sCf\u0004\u0003\u0006B\bg\u00033\nabZ3u'Bd\u0017\u000e^:BeJ\f\u00170\u0006\u0002\u0002fA1\u0011qAA\u0005\u0003\u000bAC\u0001\u00054\u0002Z\u0005q1/\u001a;Ta2LGo]!se\u0006LH\u0003BA\r\u0003[Bq!!\b\u0012\u0001\u0004\t)\u0007\u000b\u0003\u0012M\u0006e\u0013\u0001D:fi&s\u0007/\u001e;D_2\u001cH\u0003BA\r\u0003kBq!!\b\u0013\u0001\u0004\t9\bE\u0003\u0002\b\u0005%\u0001\f\u000b\u0003\u0013M\u0006e\u0013!D:fi>+H\u000f];u\u0007>d7\u000f\u0006\u0003\u0002\u001a\u0005}\u0004bBA\u000f'\u0001\u0007\u0011q\u000f\u0015\u0005'\u0019\fI&A\u0005ue\u0006t7OZ8s[R!\u0011qQAU!\u0011\tI)a)\u000f\t\u0005-\u0015Q\u0014\b\u0005\u0003\u001b\u000bIJ\u0004\u0003\u0002\u0010\u0006]e\u0002BAI\u0003+s1aWAJ\u0013\u0005\u0019\u0014BA\u00193\u0013\ty\u0003'C\u0002\u0002\u001c:\n1a]9m\u0013\u0011\ty*!)\u0002\u000fA\f7m[1hK*\u0019\u00111\u0014\u0018\n\t\u0005\u0015\u0016q\u0015\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!a(\u0002\"\"9\u00111\u0016\u000bA\u0002\u00055\u0016a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003_\u000bY\f\u0005\u0004\u00022\u0006M\u0016qW\u0007\u0003\u0003CKA!!.\u0002\"\n9A)\u0019;bg\u0016$\b\u0003BA]\u0003wc\u0001\u0001\u0002\u0007\u0002>\u0006%\u0016\u0011!A\u0001\u0006\u0003\tyLA\u0002`IE\nB!!1\u0002HB!\u0011qAAb\u0013\r\t)M\u0018\u0002\b\u001d>$\b.\u001b8h!\u0011\t9!!3\n\u0007\u0005-gLA\u0002B]fDC\u0001\u00064\u0002P\u0006\u0012\u0011\u0011[\u0001\u0006e9\u0002d\u0006M\u0001\u0010aJ,\u0007oT;uaV$h)[3mIR1\u0011q[Ar\u0003K\u0004B!!7\u0002`6\u0011\u00111\u001c\u0006\u0005\u0003;\f\t+A\u0003usB,7/\u0003\u0003\u0002b\u0006m'aC*ueV\u001cGOR5fY\u0012Daa^\u000bA\u0002\u0005\u0015\u0001BBAt+\u0001\u0007\u0001,A\u0005pkR\u0004X\u000f^\"pY\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002n\u0006M\b\u0003BAm\u0003_LA!!=\u0002\\\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005Uh\u00031\u0001\u0002n\u000611o\u00195f[\u0006D3A\u00064m\u0003\u0011\u0019w\u000e]=\u0015\u0007i\ni\u0010C\u0004\u0002\u0000^\u0001\rA!\u0001\u0002\u000b\u0015DHO]1\u0011\u0007i\u0014\u0019!C\u0002\u0003\u0006\u0001\u0013\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0005/\u0019\u0014I!\t\u0002\u0003\f\u0005)\u0011G\f\u001b/c\u0005AAo\\*ue&tw\rF\u0001YQ\u0011AbMa\u0005\"\u0005\tU\u0011!B\u001a/a9\u0002\u0004f\u0001\u0001gY\u0006Q!)^2lKRL'0\u001a:\u0011\u0005mR2c\u0002\u000e\u0003 \t\u0015\"1\u0006\t\u0005\u0003\u000f\u0011\t#C\u0002\u0003$y\u0013a!\u00118z%\u00164\u0007\u0003B)\u0003(iJ1A!\u000bS\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004BA!\f\u000385\u0011!q\u0006\u0006\u0005\u0005c\u0011\u0019$\u0001\u0002j_*\u0011!QG\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003:\t=\"\u0001D*fe&\fG.\u001b>bE2,GC\u0001B\u000e\u00031\u00196*\u0013)`\u0013:3\u0016\tT%E\u00035\u00196*\u0013)`\u0013:3\u0016\tT%EA\u0005iQI\u0015*P%~KeJV!M\u0013\u0012\u000ba\"\u0012*S\u001fJ{\u0016J\u0014,B\u0019&#\u0005%\u0001\u0007L\u000b\u0016\u0003v,\u0013(W\u00032KE)A\u0007L\u000b\u0016\u0003v,\u0013(W\u00032KE\tI\u0001\u0018gV\u0004\bo\u001c:uK\u0012D\u0015M\u001c3mK&sg/\u00197jIN,\"!a\u001e\u00021M,\b\u000f]8si\u0016$\u0007*\u00198eY\u0016LeN^1mS\u0012\u001c\b%A\u0006dQ\u0016\u001c7n\u00159mSR\u001cH\u0003\u0002B*\u00053\u0002B!a\u0002\u0003V%\u0019!q\u000b0\u0003\u000f\t{w\u000e\\3b]\"1q\u000f\na\u0001\u0003\u000b\t\u0001c\u00195fG.\u001c\u0006\u000f\\5ug\u0006\u0013(/Y=\u0015\t\tM#q\f\u0005\b\u0003\u001b*\u0003\u0019AA3\u0003Y\u0011\u0017N\\1ssN+\u0017M]2i\r>\u0014()^2lKR\u001cH\u0003CA\u0007\u0005K\u00129G!\u001b\t\r]4\u0003\u0019AA\u0003\u0011\u0019Yc\u00051\u0001\u0002\u000e!9!1\u000e\u0014A\u0002\tM\u0013aC6fKBLeN^1mS\u0012\fA\u0001\\8bIR\u0019!H!\u001d\t\r\tMt\u00051\u0001Y\u0003\u0011\u0001\u0018\r\u001e5)\t\u001d2'qO\u0011\u0003\u0005s\nQ!\r\u00187]A\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa \u0011\t\t\u0005%qQ\u0007\u0003\u0005\u0007SAA!\"\u00034\u0005!A.\u00198h\u0013\u0011\u0011IIa!\u0003\r=\u0013'.Z2uQ\u0011QbMa\u001e)\te1'q\u000f"
)
public final class Bucketizer extends Model implements HasHandleInvalid, HasInputCol, HasOutputCol, HasInputCols, HasOutputCols, DefaultParamsWritable {
   private final String uid;
   private final DoubleArrayParam splits;
   private final Param handleInvalid;
   private final DoubleArrayArrayParam splitsArray;
   private StringArrayParam outputCols;
   private StringArrayParam inputCols;
   private Param outputCol;
   private Param inputCol;

   public static Bucketizer load(final String path) {
      return Bucketizer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Bucketizer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
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

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public String uid() {
      return this.uid;
   }

   public DoubleArrayParam splits() {
      return this.splits;
   }

   public double[] getSplits() {
      return (double[])this.$(this.splits());
   }

   public Bucketizer setSplits(final double[] value) {
      return (Bucketizer)this.set(this.splits(), value);
   }

   public Bucketizer setInputCol(final String value) {
      return (Bucketizer)this.set(this.inputCol(), value);
   }

   public Bucketizer setOutputCol(final String value) {
      return (Bucketizer)this.set(this.outputCol(), value);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public Bucketizer setHandleInvalid(final String value) {
      return (Bucketizer)this.set(this.handleInvalid(), value);
   }

   public DoubleArrayArrayParam splitsArray() {
      return this.splitsArray;
   }

   public double[][] getSplitsArray() {
      return (double[][])this.$(this.splitsArray());
   }

   public Bucketizer setSplitsArray(final double[][] value) {
      return (Bucketizer)this.set(this.splitsArray(), value);
   }

   public Bucketizer setInputCols(final String[] value) {
      return (Bucketizer)this.set(this.inputCols(), value);
   }

   public Bucketizer setOutputCols(final String[] value) {
      return (Bucketizer)this.set(this.outputCols(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType transformedSchema = this.transformSchema(dataset.schema());
      Tuple2 var6 = this.isSet(this.inputCols()) ? new Tuple2(.MODULE$.SparkArrayOps(this.$(this.inputCols())).toImmutableArraySeq(), .MODULE$.SparkArrayOps(this.$(this.outputCols())).toImmutableArraySeq()) : new Tuple2(new scala.collection.immutable..colon.colon((String)this.$(this.inputCol()), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon((String)this.$(this.outputCol()), scala.collection.immutable.Nil..MODULE$));
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         Seq inputColumns;
         Seq outputColumns;
         Tuple2 var23;
         label52: {
            label56: {
               Seq inputColumns = (Seq)var6._1();
               Seq outputColumns = (Seq)var6._2();
               Tuple2 var5 = new Tuple2(inputColumns, outputColumns);
               inputColumns = (Seq)var5._1();
               outputColumns = (Seq)var5._2();
               String var10000 = this.getHandleInvalid();
               String var13 = Bucketizer$.MODULE$.SKIP_INVALID();
               if (var10000 == null) {
                  if (var13 == null) {
                     break label56;
                  }
               } else if (var10000.equals(var13)) {
                  break label56;
               }

               boolean var24;
               Dataset var10002;
               label44: {
                  label43: {
                     var23 = new Tuple2;
                     var10002 = dataset.toDF();
                     String var10003 = this.getHandleInvalid();
                     String var14 = Bucketizer$.MODULE$.KEEP_INVALID();
                     if (var10003 == null) {
                        if (var14 == null) {
                           break label43;
                        }
                     } else if (var10003.equals(var14)) {
                        break label43;
                     }

                     var24 = false;
                     break label44;
                  }

                  var24 = true;
               }

               var23.<init>(var10002, BoxesRunTime.boxToBoolean(var24));
               break label52;
            }

            var23 = new Tuple2(dataset.na().drop(inputColumns).toDF(), BoxesRunTime.boxToBoolean(false));
         }

         Tuple2 var12 = var23;
         if (var12 != null) {
            Dataset filteredDataset = (Dataset)var12._1();
            boolean keepInvalid = var12._2$mcZ$sp();
            Tuple2 var11 = new Tuple2(filteredDataset, BoxesRunTime.boxToBoolean(keepInvalid));
            Dataset filteredDataset = (Dataset)var11._1();
            boolean keepInvalid = var11._2$mcZ$sp();
            Seq seqOfSplits = (Seq)(this.isSet(this.inputCols()) ? .MODULE$.SparkArrayOps(this.$(this.splitsArray())).toImmutableArraySeq() : new scala.collection.immutable..colon.colon((double[])this.$(this.splits()), scala.collection.immutable.Nil..MODULE$));
            Seq bucketizers = (Seq)((IterableOps)seqOfSplits.zipWithIndex()).map((x0$1) -> {
               if (x0$1 != null) {
                  double[] splits = (double[])x0$1._1();
                  int idx = x0$1._2$mcI$sp();
                  return org.apache.spark.sql.functions..MODULE$.udf((JFunction1.mcDD.sp)(feature) -> Bucketizer$.MODULE$.binarySearchForBuckets(splits, feature, keepInvalid), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double()).withName("bucketizer_" + idx);
               } else {
                  throw new MatchError(x0$1);
               }
            });
            Seq newCols = (Seq)((IterableOps)inputColumns.zipWithIndex()).map((x0$2) -> {
               if (x0$2 != null) {
                  String inputCol = (String)x0$2._1();
                  int idx = x0$2._2$mcI$sp();
                  return ((UserDefinedFunction)bucketizers.apply(idx)).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{filteredDataset.apply(inputCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$)})));
               } else {
                  throw new MatchError(x0$2);
               }
            });
            Seq metadata = (Seq)outputColumns.map((col) -> transformedSchema.apply(col).metadata());
            return filteredDataset.withColumns(outputColumns, newCols, metadata);
         } else {
            throw new MatchError(var12);
         }
      }
   }

   private StructField prepOutputField(final double[] splits, final String outputCol) {
      Object qual$1 = scala.Predef..MODULE$.doubleArrayOps(splits);
      int x$1 = 2;
      int x$2 = scala.collection.ArrayOps..MODULE$.sliding$default$2$extension(qual$1);
      String[] buckets = (String[])scala.collection.ArrayOps..MODULE$.sliding$extension(qual$1, 2, x$2).map((bucket) -> scala.Predef..MODULE$.wrapDoubleArray(bucket).mkString(", ")).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
      Some x$3 = new Some(outputCol);
      Some x$4 = new Some(BoxesRunTime.boxToBoolean(true));
      Some x$5 = new Some(buckets);
      Option x$6 = NominalAttribute$.MODULE$.$lessinit$greater$default$2();
      Option x$7 = NominalAttribute$.MODULE$.$lessinit$greater$default$4();
      NominalAttribute attr = new NominalAttribute(x$3, x$6, x$4, x$7, x$5);
      return attr.toStructField();
   }

   public StructType transformSchema(final StructType schema) {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(this, new scala.collection.immutable..colon.colon(this.outputCol(), new scala.collection.immutable..colon.colon(this.splits(), scala.collection.immutable.Nil..MODULE$)), new scala.collection.immutable..colon.colon(this.outputCols(), new scala.collection.immutable..colon.colon(this.splitsArray(), scala.collection.immutable.Nil..MODULE$)));
      if (!this.isSet(this.inputCols())) {
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.inputCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
         return SchemaUtils$.MODULE$.appendColumn(schema, this.prepOutputField((double[])this.$(this.splits()), (String)this.$(this.outputCol())));
      } else {
         scala.Predef..MODULE$.require(this.getInputCols().length == this.getOutputCols().length && this.getInputCols().length == this.getSplitsArray().length, () -> "Bucketizer " + this + " has mismatched Params for multi-column transform. Params (inputCols, outputCols, splitsArray) should have equal lengths, but they have different lengths: (" + this.getInputCols().length + ", " + this.getOutputCols().length + ", " + this.getSplitsArray().length + ").");
         ObjectRef transformedSchema = ObjectRef.create(schema);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(this.$(this.inputCols())), scala.Predef..MODULE$.wrapRefArray(this.$(this.outputCols())))))), (x0$1) -> {
            $anonfun$transformSchema$2(this, transformedSchema, x0$1);
            return BoxedUnit.UNIT;
         });
         return (StructType)transformedSchema.elem;
      }
   }

   public Bucketizer copy(final ParamMap extra) {
      return (Bucketizer)((Model)this.defaultCopy(extra)).setParent(this.parent());
   }

   public String toString() {
      String var10000 = this.uid();
      return "Bucketizer: uid=" + var10000 + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "") + this.get(this.outputCols()).map((c) -> ", numOutputCols=" + c.length).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$splits$1(final double[] splits) {
      return Bucketizer$.MODULE$.checkSplits(splits);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$splitsArray$1(final double[][] splitsArray) {
      return Bucketizer$.MODULE$.checkSplitsArray(splitsArray);
   }

   // $FF: synthetic method
   public static final void $anonfun$transformSchema$2(final Bucketizer $this, final ObjectRef transformedSchema$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var5 = (Tuple2)x0$1._1();
         int idx = x0$1._2$mcI$sp();
         if (var5 != null) {
            String inputCol = (String)var5._1();
            String outputCol = (String)var5._2();
            SchemaUtils$.MODULE$.checkNumericType((StructType)transformedSchema$2.elem, inputCol, SchemaUtils$.MODULE$.checkNumericType$default$3());
            transformedSchema$2.elem = SchemaUtils$.MODULE$.appendColumn((StructType)transformedSchema$2.elem, $this.prepOutputField(((double[][])$this.$($this.splitsArray()))[idx], outputCol));
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   public Bucketizer(final String uid) {
      this.uid = uid;
      HasHandleInvalid.$init$(this);
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCols.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.splits = new DoubleArrayParam(this, "splits", "Split points for mapping continuous features into buckets. With n+1 splits, there are n buckets. A bucket defined by splits x,y holds values in the range [x,y) except the last bucket, which also includes y. The splits should be of length >= 3 and strictly increasing. Values at -inf, inf must be explicitly provided to cover all Double values; otherwise, values outside the splits specified will be treated as errors.", (splits) -> BoxesRunTime.boxToBoolean($anonfun$splits$1(splits)));
      this.handleInvalid = new Param(this, "handleInvalid", "how to handle invalid entries containing NaN values. Values outside the splits will always be treated as errorsOptions are skip (filter out rows with invalid values), error (throw an error), or keep (keep invalid values in a special additional bucket).", ParamValidators$.MODULE$.inArray((Object)Bucketizer$.MODULE$.supportedHandleInvalids()), scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.setDefault(this.handleInvalid(), Bucketizer$.MODULE$.ERROR_INVALID());
      this.splitsArray = new DoubleArrayArrayParam(this, "splitsArray", "The array of split points for mapping continuous features into buckets for multiple columns. For each input column, with n+1 splits, there are n buckets. A bucket defined by splits x,y holds values in the range [x,y) except the last bucket, which also includes y. The splits should be of length >= 3 and strictly increasing. Values at -inf, inf must be explicitly provided to cover all Double values; otherwise, values outside the splits specified will be treated as errors.", (splitsArray) -> BoxesRunTime.boxToBoolean($anonfun$splitsArray$1(splitsArray)));
      Statics.releaseFence();
   }

   public Bucketizer() {
      this(Identifiable$.MODULE$.randomUID("bucketizer"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
