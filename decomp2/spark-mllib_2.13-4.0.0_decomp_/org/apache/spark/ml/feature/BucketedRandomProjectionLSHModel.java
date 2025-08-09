package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tee\u0001B\u001b7\u0001\u0005C\u0001B\u0013\u0001\u0003\u0006\u0004%\te\u0013\u0005\t3\u0002\u0011\t\u0011)A\u0005\u0019\"I!\f\u0001BC\u0002\u0013\u0005\u0001h\u0017\u0005\tE\u0002\u0011\t\u0011)A\u00059\"11\r\u0001C\u0001q\u0011Daa\u0019\u0001\u0005\u0002a:\u0007BB2\u0001\t\u0003A\u0004\u000e\u0003\u0004l\u0001\u0011\u0005\u0001h\u001d\u0005\u0006i\u0002!\t%\u001e\u0005\b\u0003\u000b\u0001A\u0011IA\u0004\u0011!\ti\u0001\u0001C)q\u0005=\u0001\u0002CA\u000e\u0001\u0011E\u0003(!\b\t\u0011\u0005=\u0002\u0001\"\u00159\u0003cAq!!\u000f\u0001\t\u0003\nY\u0004C\u0004\u0002P\u0001!\t%!\u0015\t\u000f\u0005\u0005\u0004\u0001\"\u0011\u0002d\u001d9\u0011Q\u000e\u001c\t\u0002\u0005=dAB\u001b7\u0011\u0003\t\t\b\u0003\u0004d%\u0011\u0005\u0011q\u0012\u0005\b\u0003#\u0013B\u0011IAJ\u0011\u001d\tiJ\u0005C!\u0003?3q!a*\u0013\u0001I\tI\u000bC\u0005\u0002,Z\u0011\t\u0011)A\u0005\r\"11M\u0006C\u0001\u0003[3a!!.\u0017\t\u0006]\u0006\u0002C6\u001a\u0005+\u0007I\u0011A.\t\u0013\u0005=\u0017D!E!\u0002\u0013a\u0006BB2\u001a\t\u0003\t\t\u000eC\u0005\u0002:e\t\t\u0011\"\u0001\u0002Z\"I\u0011Q\\\r\u0012\u0002\u0013\u0005\u0011q\u001c\u0005\n\u0003gL\u0012\u0011!C!\u0003kD\u0011B!\u0001\u001a\u0003\u0003%\tAa\u0001\t\u0013\t-\u0011$!A\u0005\u0002\t5\u0001\"\u0003B\r3\u0005\u0005I\u0011\tB\u000e\u0011%\u0011I#GA\u0001\n\u0003\u0011Y\u0003C\u0005\u00036e\t\t\u0011\"\u0011\u00038!I!1H\r\u0002\u0002\u0013\u0005#Q\b\u0005\n\u0003CJ\u0012\u0011!C!\u0005\u007fA\u0011B!\u0011\u001a\u0003\u0003%\tEa\u0011\b\u0013\t\u001dc#!A\t\n\t%c!CA[-\u0005\u0005\t\u0012\u0002B&\u0011\u0019\u0019\u0017\u0006\"\u0001\u0003Z!I\u0011\u0011M\u0015\u0002\u0002\u0013\u0015#q\b\u0005\n\u00057J\u0013\u0011!CA\u0005;B\u0011B!\u0019*\u0003\u0003%\tIa\u0019\t\u000f\t=d\u0003\"\u0015\u0003r\u00191!1\u0010\n\u0005\u0005{BaaY\u0018\u0005\u0002\t}\u0004\"\u0003BB_\t\u0007I\u0011BA{\u0011!\u0011)i\fQ\u0001\n\u0005]\bbBAO_\u0011\u0005#q\u0011\u0005\n\u0005\u0017\u0013\u0012\u0011!C\u0005\u0005\u001b\u0013\u0001EQ;dW\u0016$X\r\u001a*b]\u0012|W\u000e\u0015:pU\u0016\u001cG/[8o\u0019NCUj\u001c3fY*\u0011q\u0007O\u0001\bM\u0016\fG/\u001e:f\u0015\tI$(\u0001\u0002nY*\u00111\bP\u0001\u0006gB\f'o\u001b\u0006\u0003{y\na!\u00199bG\",'\"A \u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0011u\tE\u0002D\t\u001ak\u0011AN\u0005\u0003\u000bZ\u0012\u0001\u0002T*I\u001b>$W\r\u001c\t\u0003\u0007\u0002\u0001\"a\u0011%\n\u0005%3$!\t\"vG.,G/\u001a3SC:$w.\u001c)s_*,7\r^5p]2\u001b\u0006\nU1sC6\u001c\u0018aA;jIV\tA\n\u0005\u0002N-:\u0011a\n\u0016\t\u0003\u001fJk\u0011\u0001\u0015\u0006\u0003#\u0002\u000ba\u0001\u0010:p_Rt$\"A*\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0013\u0016A\u0002)sK\u0012,g-\u0003\u0002X1\n11\u000b\u001e:j]\u001eT!!\u0016*\u0002\tULG\rI\u0001\u000be\u0006tG-T1ue&DX#\u0001/\u0011\u0005u\u0003W\"\u00010\u000b\u0005}C\u0014A\u00027j]\u0006dw-\u0003\u0002b=\n1Q*\u0019;sSb\f1B]1oI6\u000bGO]5yA\u00051A(\u001b8jiz\"2AR3g\u0011\u0015QU\u00011\u0001M\u0011\u0015QV\u00011\u0001])\u00051Ec\u0001$jU\")!j\u0002a\u0001\u0019\")1n\u0002a\u0001Y\u0006y!/\u00198e+:LGOV3di>\u00148\u000fE\u0002n]Bl\u0011AU\u0005\u0003_J\u0013Q!\u0011:sCf\u0004\"!X9\n\u0005It&A\u0002,fGR|'/F\u0001m\u0003-\u0019X\r^%oaV$8i\u001c7\u0015\u0005Y<X\"\u0001\u0001\t\u000baL\u0001\u0019\u0001'\u0002\u000bY\fG.^3)\t%Q\u0018\u0011\u0001\t\u0003wzl\u0011\u0001 \u0006\u0003{j\n!\"\u00198o_R\fG/[8o\u0013\tyHPA\u0003TS:\u001cW-\t\u0002\u0002\u0004\u0005)!G\f\u001b/a\u0005a1/\u001a;PkR\u0004X\u000f^\"pYR\u0019a/!\u0003\t\u000baT\u0001\u0019\u0001')\t)Q\u0018\u0011A\u0001\rQ\u0006\u001c\bNR;oGRLwN\u001c\u000b\u0004Y\u0006E\u0001BBA\n\u0017\u0001\u0007\u0001/A\u0003fY\u0016l7\u000f\u000b\u0003\fu\u0006]\u0011EAA\r\u0003\u0015\u0011d&\r\u00181\u0003-YW-\u001f#jgR\fgnY3\u0015\r\u0005}\u0011QEA\u0015!\ri\u0017\u0011E\u0005\u0004\u0003G\u0011&A\u0002#pk\ndW\r\u0003\u0004\u0002(1\u0001\r\u0001]\u0001\u0002q\"1\u00111\u0006\u0007A\u0002A\f\u0011!\u001f\u0015\u0005\u0019i\f9\"\u0001\u0007iCNDG)[:uC:\u001cW\r\u0006\u0004\u0002 \u0005M\u0012Q\u0007\u0005\u0007\u0003Oi\u0001\u0019\u00017\t\r\u0005-R\u00021\u0001mQ\u0011i!0a\u0006\u0002\t\r|\u0007/\u001f\u000b\u0004\r\u0006u\u0002bBA \u001d\u0001\u0007\u0011\u0011I\u0001\u0006Kb$(/\u0019\t\u0005\u0003\u0007\nI%\u0004\u0002\u0002F)\u0019\u0011q\t\u001d\u0002\u000bA\f'/Y7\n\t\u0005-\u0013Q\t\u0002\t!\u0006\u0014\u0018-\\'ba\"\"aB_A\f\u0003\u00159(/\u001b;f+\t\t\u0019\u0006\u0005\u0003\u0002V\u0005mSBAA,\u0015\r\tI\u0006O\u0001\u0005kRLG.\u0003\u0003\u0002^\u0005]#\u0001C'M/JLG/\u001a:)\t=Q\u0018qC\u0001\ti>\u001cFO]5oOR\tA\n\u000b\u0003\u0011u\u0006\u001d\u0014EAA5\u0003\u0015\u0019d\u0006\r\u00181Q\u0011\u0001!0a\u0006\u0002A\t+8m[3uK\u0012\u0014\u0016M\u001c3p[B\u0013xN[3di&|g\u000eT*I\u001b>$W\r\u001c\t\u0003\u0007J\u0019rAEA:\u0003s\ny\bE\u0002n\u0003kJ1!a\u001eS\u0005\u0019\te.\u001f*fMB)\u0011QKA>\r&!\u0011QPA,\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0003\u0003\u000bY)\u0004\u0002\u0002\u0004*!\u0011QQAD\u0003\tIwN\u0003\u0002\u0002\n\u0006!!.\u0019<b\u0013\u0011\ti)a!\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005=\u0014\u0001\u0002:fC\u0012,\"!!&\u0011\u000b\u0005U\u0013q\u0013$\n\t\u0005e\u0015q\u000b\u0002\t\u001b2\u0013V-\u00193fe\"\"AC_A\f\u0003\u0011aw.\u00193\u0015\u0007\u0019\u000b\t\u000b\u0003\u0004\u0002$V\u0001\r\u0001T\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u0016u\u0006]!A\n\"vG.,G/\u001a3SC:$w.\u001c)s_*,7\r^5p]2\u001b\u0006*T8eK2<&/\u001b;feN\u0019a#a\u0015\u0002\u0011%t7\u000f^1oG\u0016$B!a,\u00024B\u0019\u0011\u0011\u0017\f\u000e\u0003IAa!a+\u0019\u0001\u00041%\u0001\u0002#bi\u0006\u001cr!GA:\u0003s\u000by\fE\u0002n\u0003wK1!!0S\u0005\u001d\u0001&o\u001c3vGR\u0004B!!1\u0002L:!\u00111YAd\u001d\ry\u0015QY\u0005\u0002'&\u0019\u0011\u0011\u001a*\u0002\u000fA\f7m[1hK&!\u0011QRAg\u0015\r\tIMU\u0001\u0011e\u0006tG-\u00168jiZ+7\r^8sg\u0002\"B!a5\u0002XB\u0019\u0011Q[\r\u000e\u0003YAQa\u001b\u000fA\u0002q#B!a5\u0002\\\"91.\bI\u0001\u0002\u0004a\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003CT3\u0001XArW\t\t)\u000f\u0005\u0003\u0002h\u0006=XBAAu\u0015\u0011\tY/!<\u0002\u0013Ut7\r[3dW\u0016$'BA?S\u0013\u0011\t\t0!;\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003o\u0004B!!?\u0002\u00006\u0011\u00111 \u0006\u0005\u0003{\f9)\u0001\u0003mC:<\u0017bA,\u0002|\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011!Q\u0001\t\u0004[\n\u001d\u0011b\u0001B\u0005%\n\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!!q\u0002B\u000b!\ri'\u0011C\u0005\u0004\u0005'\u0011&aA!os\"I!qC\u0011\u0002\u0002\u0003\u0007!QA\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\tu\u0001C\u0002B\u0010\u0005K\u0011y!\u0004\u0002\u0003\")\u0019!1\u0005*\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003(\t\u0005\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BA!\f\u00034A\u0019QNa\f\n\u0007\tE\"KA\u0004C_>dW-\u00198\t\u0013\t]1%!AA\u0002\t=\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a>\u0003:!I!q\u0003\u0013\u0002\u0002\u0003\u0007!QA\u0001\tQ\u0006\u001c\bnQ8eKR\u0011!Q\u0001\u000b\u0003\u0003o\fa!Z9vC2\u001cH\u0003\u0002B\u0017\u0005\u000bB\u0011Ba\u0006(\u0003\u0003\u0005\rAa\u0004\u0002\t\u0011\u000bG/\u0019\t\u0004\u0003+L3#B\u0015\u0003N\u0005}\u0004c\u0002B(\u0005+b\u00161[\u0007\u0003\u0005#R1Aa\u0015S\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\u0016\u0003R\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0015\u0005\t%\u0013!B1qa2LH\u0003BAj\u0005?BQa\u001b\u0017A\u0002q\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003f\t-\u0004\u0003B7\u0003hqK1A!\u001bS\u0005\u0019y\u0005\u000f^5p]\"I!QN\u0017\u0002\u0002\u0003\u0007\u00111[\u0001\u0004q\u0012\u0002\u0014\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\tM$\u0011\u0010\t\u0004[\nU\u0014b\u0001B<%\n!QK\\5u\u0011\u0019\t\u0019K\fa\u0001\u0019\n1#)^2lKR,GMU1oI>l\u0007K]8kK\u000e$\u0018n\u001c8M'\"ku\u000eZ3m%\u0016\fG-\u001a:\u0014\u0007=\n)\n\u0006\u0002\u0003\u0002B\u0019\u0011\u0011W\u0018\u0002\u0013\rd\u0017m]:OC6,\u0017AC2mCN\u001ch*Y7fAQ\u0019aI!#\t\r\u0005\r6\u00071\u0001M\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011y\t\u0005\u0003\u0002z\nE\u0015\u0002\u0002BJ\u0003w\u0014aa\u00142kK\u000e$\b\u0006\u0002\n{\u0003/AC!\u0005>\u0002\u0018\u0001"
)
public class BucketedRandomProjectionLSHModel extends LSHModel implements BucketedRandomProjectionLSHParams {
   private final String uid;
   private final Matrix randMatrix;
   private DoubleParam bucketLength;

   public static BucketedRandomProjectionLSHModel load(final String path) {
      return BucketedRandomProjectionLSHModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return BucketedRandomProjectionLSHModel$.MODULE$.read();
   }

   public final double getBucketLength() {
      return BucketedRandomProjectionLSHParams.getBucketLength$(this);
   }

   public DoubleParam bucketLength() {
      return this.bucketLength;
   }

   public void org$apache$spark$ml$feature$BucketedRandomProjectionLSHParams$_setter_$bucketLength_$eq(final DoubleParam x$1) {
      this.bucketLength = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Matrix randMatrix() {
      return this.randMatrix;
   }

   public Vector[] randUnitVectors() {
      return (Vector[])this.randMatrix().rowIter().toArray(.MODULE$.apply(Vector.class));
   }

   public BucketedRandomProjectionLSHModel setInputCol(final String value) {
      return (BucketedRandomProjectionLSHModel)Params.set$(this, (Param)this.inputCol(), value);
   }

   public BucketedRandomProjectionLSHModel setOutputCol(final String value) {
      return (BucketedRandomProjectionLSHModel)Params.set$(this, (Param)this.outputCol(), value);
   }

   public Vector[] hashFunction(final Vector elems) {
      DenseVector hashVec = new DenseVector((double[])scala.Array..MODULE$.ofDim(this.randMatrix().numRows(), .MODULE$.Double()));
      org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F / BoxesRunTime.unboxToDouble(this.$(this.bucketLength())), this.randMatrix(), elems, (double)0.0F, hashVec);
      return (Vector[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(hashVec.values()), (h) -> $anonfun$hashFunction$1(BoxesRunTime.unboxToDouble(h)), .MODULE$.apply(Vector.class));
   }

   public double keyDistance(final Vector x, final Vector y) {
      return Math.sqrt(org.apache.spark.ml.linalg.Vectors..MODULE$.sqdist(x, y));
   }

   public double hashDistance(final Vector[] x, final Vector[] y) {
      double distance = Double.MAX_VALUE;

      for(int i = 0; i < x.length; ++i) {
         double[] vx = x[i].toArray();
         double[] vy = y[i].toArray();
         int j = 0;

         double d;
         for(d = (double)0.0F; j < vx.length && d < distance; ++j) {
            double diff = vx[j] - vy[j];
            d += diff * diff;
         }

         if (d == (double)0) {
            return (double)0.0F;
         }

         if (d < distance) {
            distance = d;
         }
      }

      return distance;
   }

   public BucketedRandomProjectionLSHModel copy(final ParamMap extra) {
      BucketedRandomProjectionLSHModel copied = (BucketedRandomProjectionLSHModel)(new BucketedRandomProjectionLSHModel(this.uid(), this.randMatrix())).setParent(this.parent());
      return (BucketedRandomProjectionLSHModel)this.copyValues(copied, extra);
   }

   public MLWriter write() {
      return new BucketedRandomProjectionLSHModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "BucketedRandomProjectionLSHModel: uid=" + var10000 + ", numHashTables=" + this.$(this.numHashTables());
   }

   // $FF: synthetic method
   public static final Vector $anonfun$hashFunction$1(final double h) {
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(scala.runtime.RichDouble..MODULE$.floor$extension(scala.Predef..MODULE$.doubleWrapper(h)), scala.collection.immutable.Nil..MODULE$);
   }

   public BucketedRandomProjectionLSHModel(final String uid, final Matrix randMatrix) {
      this.uid = uid;
      this.randMatrix = randMatrix;
      BucketedRandomProjectionLSHParams.$init$(this);
      Statics.releaseFence();
   }

   public BucketedRandomProjectionLSHModel() {
      this("", (Matrix)org.apache.spark.ml.linalg.Matrices..MODULE$.empty());
   }

   public BucketedRandomProjectionLSHModel(final String uid, final Vector[] randUnitVectors) {
      this(uid, org.apache.spark.ml.linalg.Matrices..MODULE$.fromVectors(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(randUnitVectors).toImmutableArraySeq()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class BucketedRandomProjectionLSHModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final BucketedRandomProjectionLSHModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.randMatrix());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(BucketedRandomProjectionLSHModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.BucketedRandomProjectionLSHModel.BucketedRandomProjectionLSHModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.BucketedRandomProjectionLSHModel.BucketedRandomProjectionLSHModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
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

      public BucketedRandomProjectionLSHModelWriter(final BucketedRandomProjectionLSHModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Matrix randUnitVectors;
         // $FF: synthetic field
         public final BucketedRandomProjectionLSHModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Matrix randUnitVectors() {
            return this.randUnitVectors;
         }

         public Data copy(final Matrix randUnitVectors) {
            return this.org$apache$spark$ml$feature$BucketedRandomProjectionLSHModel$BucketedRandomProjectionLSHModelWriter$Data$$$outer().new Data(randUnitVectors);
         }

         public Matrix copy$default$1() {
            return this.randUnitVectors();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.randUnitVectors();
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
                  return "randUnitVectors";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label52: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$BucketedRandomProjectionLSHModel$BucketedRandomProjectionLSHModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$BucketedRandomProjectionLSHModel$BucketedRandomProjectionLSHModelWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Matrix var10000 = this.randUnitVectors();
                        Matrix var5 = var4.randUnitVectors();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label42;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label42;
                        }

                        if (var4.canEqual(this)) {
                           break label52;
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
         public BucketedRandomProjectionLSHModelWriter org$apache$spark$ml$feature$BucketedRandomProjectionLSHModel$BucketedRandomProjectionLSHModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Matrix randUnitVectors) {
            this.randUnitVectors = randUnitVectors;
            if (BucketedRandomProjectionLSHModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = BucketedRandomProjectionLSHModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final BucketedRandomProjectionLSHModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Matrix randUnitVectors) {
            return this.$outer.new Data(randUnitVectors);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.randUnitVectors()));
         }

         public Data$() {
            if (BucketedRandomProjectionLSHModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = BucketedRandomProjectionLSHModelWriter.this;
               super();
            }
         }
      }
   }

   private static class BucketedRandomProjectionLSHModelReader extends MLReader {
      private final String className = BucketedRandomProjectionLSHModel.class.getName();

      private String className() {
         return this.className;
      }

      public BucketedRandomProjectionLSHModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().parquet(dataPath);
         Row var7 = (Row)MLUtils$.MODULE$.convertMatrixColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"randUnitVectors"}))).select("randUnitVectors", scala.collection.immutable.Nil..MODULE$).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(1) == 0) {
               Object randMatrix = ((SeqOps)var8.get()).apply(0);
               if (randMatrix instanceof Matrix) {
                  Matrix var10 = (Matrix)randMatrix;
                  BucketedRandomProjectionLSHModel model = new BucketedRandomProjectionLSHModel(metadata.uid(), var10);
                  metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                  return model;
               }
            }
         }

         throw new MatchError(var7);
      }

      public BucketedRandomProjectionLSHModelReader() {
      }
   }
}
