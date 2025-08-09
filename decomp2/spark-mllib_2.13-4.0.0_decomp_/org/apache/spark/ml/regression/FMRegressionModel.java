package org.apache.spark.ml.regression;

import java.io.IOException;
import java.io.Serializable;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.Identifiable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tuf\u0001B\u001e=\u0001\u001dC\u0001\u0002\u0018\u0001\u0003\u0006\u0004%\t%\u0018\u0005\ti\u0002\u0011\t\u0011)A\u0005=\"Aa\u000f\u0001BC\u0002\u0013\u0005q\u000f\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003y\u0011%y\bA!b\u0001\n\u0003\t\t\u0001C\u0005\u0002\u0006\u0001\u0011\t\u0011)A\u0005\u0019\"Q\u0011\u0011\u0002\u0001\u0003\u0006\u0004%\t!a\u0003\t\u0015\u0005U\u0001A!A!\u0002\u0013\ti\u0001\u0003\u0005\u0002\u001a\u0001!\t\u0001PA\u000e\u0011!\tI\u0002\u0001C\u0001}\u00055\u0002\"CA\u0018\u0001\t\u0007I\u0011IA\u0019\u0011!\tY\u0004\u0001Q\u0001\n\u0005M\u0002bBA \u0001\u0011\u0005\u0013\u0011\t\u0005\b\u0003\u0013\u0002A\u0011IA&\u0011\u001d\ty\u0006\u0001C!\u0003CBq!a\u001b\u0001\t\u0003\nigB\u0004\u0002rqB\t!a\u001d\u0007\rmb\u0004\u0012AA;\u0011\u001d\tIB\u0005C\u0001\u0003'Cq!!&\u0013\t\u0003\n9\nC\u0004\u0002\"J!\t%a)\u0007\u000f\u0005-&\u0003\u0001\n\u0002.\"I\u00111\u0018\f\u0003\u0002\u0003\u0006IA\u0015\u0005\b\u000331B\u0011AA_\r\u0019\t)M\u0006#\u0002H\"Aa/\u0007BK\u0002\u0013\u0005q\u000f\u0003\u0005~3\tE\t\u0015!\u0003y\u0011%y\u0018D!f\u0001\n\u0003\t\t\u0001C\u0005\u0002\u0006e\u0011\t\u0012)A\u0005\u0019\"Q\u0011\u0011B\r\u0003\u0016\u0004%\t!a\u0003\t\u0015\u0005U\u0011D!E!\u0002\u0013\ti\u0001C\u0004\u0002\u001ae!\t!a8\t\u0013\u0005%\u0013$!A\u0005\u0002\u0005-\b\"CAz3E\u0005I\u0011AA{\u0011%\u0011I!GI\u0001\n\u0003\u0011Y\u0001C\u0005\u0003\u0010e\t\n\u0011\"\u0001\u0003\u0012!I!QC\r\u0002\u0002\u0013\u0005#q\u0003\u0005\n\u0005GI\u0012\u0011!C\u0001\u0003cA\u0011B!\n\u001a\u0003\u0003%\tAa\n\t\u0013\tM\u0012$!A\u0005B\tU\u0002\"\u0003B\"3\u0005\u0005I\u0011\u0001B#\u0011%\u0011y%GA\u0001\n\u0003\u0012\t\u0006C\u0005\u0003Ve\t\t\u0011\"\u0011\u0003X!I\u00111N\r\u0002\u0002\u0013\u0005#\u0011\f\u0005\n\u00057J\u0012\u0011!C!\u0005;:\u0011B!\u0019\u0017\u0003\u0003EIAa\u0019\u0007\u0013\u0005\u0015g#!A\t\n\t\u0015\u0004bBA\r_\u0011\u0005!1\u000f\u0005\n\u0003Wz\u0013\u0011!C#\u00053B\u0011B!\u001e0\u0003\u0003%\tIa\u001e\t\u0013\t}t&!A\u0005\u0002\n\u0005\u0005b\u0002BJ-\u0011E#Q\u0013\u0004\u0007\u0005?\u0013BA!)\t\u000f\u0005eQ\u0007\"\u0001\u0003$\"I!qU\u001bC\u0002\u0013%!q\u0003\u0005\t\u0005S+\u0004\u0015!\u0003\u0003\u001a!9\u0011\u0011U\u001b\u0005B\t-\u0006\"\u0003BX%\u0005\u0005I\u0011\u0002BY\u0005E1UJU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\u0006\u0003{y\n!B]3he\u0016\u001c8/[8o\u0015\ty\u0004)\u0001\u0002nY*\u0011\u0011IQ\u0001\u0006gB\f'o\u001b\u0006\u0003\u0007\u0012\u000ba!\u00199bG\",'\"A#\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001A5K\u0016\t\u0005\u0013*c%+D\u0001=\u0013\tYEHA\bSK\u001e\u0014Xm]:j_:lu\u000eZ3m!\ti\u0005+D\u0001O\u0015\tye(\u0001\u0004mS:\fGnZ\u0005\u0003#:\u0013aAV3di>\u0014\bCA%\u0001!\tIE+\u0003\u0002Vy\t\tb)\u0014*fOJ,7o]8s!\u0006\u0014\u0018-\\:\u0011\u0005]SV\"\u0001-\u000b\u0005es\u0014\u0001B;uS2L!a\u0017-\u0003\u00155cuK]5uC\ndW-A\u0002vS\u0012,\u0012A\u0018\t\u0003?\"t!\u0001\u00194\u0011\u0005\u0005$W\"\u00012\u000b\u0005\r4\u0015A\u0002\u001fs_>$hHC\u0001f\u0003\u0015\u00198-\u00197b\u0013\t9G-\u0001\u0004Qe\u0016$WMZ\u0005\u0003S*\u0014aa\u0015;sS:<'BA4eQ\r\tAN\u001d\t\u0003[Bl\u0011A\u001c\u0006\u0003_\u0002\u000b!\"\u00198o_R\fG/[8o\u0013\t\thNA\u0003TS:\u001cW-I\u0001t\u0003\u0015\u0019d\u0006\r\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\ta'/A\u0005j]R,'oY3qiV\t\u0001\u0010\u0005\u0002zu6\tA-\u0003\u0002|I\n1Ai\\;cY\u0016D3a\u00017s\u0003)Ig\u000e^3sG\u0016\u0004H\u000f\t\u0015\u0004\t1\u0014\u0018A\u00027j]\u0016\f'/F\u0001MQ\r)AN]\u0001\bY&tW-\u0019:!Q\r1AN]\u0001\bM\u0006\u001cGo\u001c:t+\t\ti\u0001E\u0002N\u0003\u001fI1!!\u0005O\u0005\u0019i\u0015\r\u001e:jq\"\u001aq\u0001\u001c:\u0002\u0011\u0019\f7\r^8sg\u0002B3\u0001\u00037s\u0003\u0019a\u0014N\\5u}QI!+!\b\u0002\"\u0005\u0015\u0012\u0011\u0006\u0005\u00069&\u0001\rA\u0018\u0015\u0005\u0003;a'\u000fC\u0003w\u0013\u0001\u0007\u0001\u0010\u000b\u0003\u0002\"1\u0014\b\"B@\n\u0001\u0004a\u0005\u0006BA\u0013YJDq!!\u0003\n\u0001\u0004\ti\u0001\u000b\u0003\u0002*1\u0014H#\u0001*\u0002\u00179,XNR3biV\u0014Xm]\u000b\u0003\u0003g\u00012!_A\u001b\u0013\r\t9\u0004\u001a\u0002\u0004\u0013:$\bfA\u0006me\u0006aa.^7GK\u0006$XO]3tA!\u001aA\u0002\u001c:\u0002\u000fA\u0014X\rZ5diR\u0019\u00010a\u0011\t\r\u0005\u0015S\u00021\u0001M\u0003!1W-\u0019;ve\u0016\u001c\bfA\u0007me\u0006!1m\u001c9z)\r\u0011\u0016Q\n\u0005\b\u0003\u001fr\u0001\u0019AA)\u0003\u0015)\u0007\u0010\u001e:b!\u0011\t\u0019&!\u0017\u000e\u0005\u0005U#bAA,}\u0005)\u0001/\u0019:b[&!\u00111LA+\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bf\u0001\bme\u0006)qO]5uKV\u0011\u00111\r\t\u0004/\u0006\u0015\u0014bAA41\nAQ\nT,sSR,'\u000fK\u0002\u0010YJ\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002=\"\u001a\u0001\u0001\u001c:\u0002#\u0019k%+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002J%M9!#a\u001e\u0002~\u0005\r\u0005cA=\u0002z%\u0019\u00111\u00103\u0003\r\u0005s\u0017PU3g!\u00119\u0016q\u0010*\n\u0007\u0005\u0005\u0005L\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!!\"\u0002\u00106\u0011\u0011q\u0011\u0006\u0005\u0003\u0013\u000bY)\u0001\u0002j_*\u0011\u0011QR\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0012\u0006\u001d%\u0001D*fe&\fG.\u001b>bE2,GCAA:\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005e\u0005\u0003B,\u0002\u001cJK1!!(Y\u0005!iEJU3bI\u0016\u0014\bf\u0001\u000bme\u0006!An\\1e)\r\u0011\u0016Q\u0015\u0005\u0007\u0003O+\u0002\u0019\u00010\u0002\tA\fG\u000f\u001b\u0015\u0004+1\u0014(a\u0006$N%\u0016<'/Z:tS>tWj\u001c3fY^\u0013\u0018\u000e^3s'\u00151\u00121MAX!\u0011\t\t,a.\u000e\u0005\u0005M&bAA[\u0001\u0006A\u0011N\u001c;fe:\fG.\u0003\u0003\u0002:\u0006M&a\u0002'pO\u001eLgnZ\u0001\tS:\u001cH/\u00198dKR!\u0011qXAb!\r\t\tMF\u0007\u0002%!1\u00111\u0018\rA\u0002I\u0013A\u0001R1uCN9\u0011$a\u001e\u0002J\u0006=\u0007cA=\u0002L&\u0019\u0011Q\u001a3\u0003\u000fA\u0013x\u000eZ;diB!\u0011\u0011[An\u001d\u0011\t\u0019.a6\u000f\u0007\u0005\f).C\u0001f\u0013\r\tI\u000eZ\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t*!8\u000b\u0007\u0005eG\r\u0006\u0005\u0002b\u0006\u0015\u0018q]Au!\r\t\u0019/G\u0007\u0002-!)a\u000f\ta\u0001q\")q\u0010\ta\u0001\u0019\"9\u0011\u0011\u0002\u0011A\u0002\u00055A\u0003CAq\u0003[\fy/!=\t\u000fY\f\u0003\u0013!a\u0001q\"9q0\tI\u0001\u0002\u0004a\u0005\"CA\u0005CA\u0005\t\u0019AA\u0007\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a>+\u0007a\fIp\u000b\u0002\u0002|B!\u0011Q B\u0003\u001b\t\tyP\u0003\u0003\u0003\u0002\t\r\u0011!C;oG\",7m[3e\u0015\tyG-\u0003\u0003\u0003\b\u0005}(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TC\u0001B\u0007U\ra\u0015\u0011`\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\u0011\u0019B\u000b\u0003\u0002\u000e\u0005e\u0018!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003\u001aA!!1\u0004B\u0011\u001b\t\u0011iB\u0003\u0003\u0003 \u0005-\u0015\u0001\u00027b]\u001eL1!\u001bB\u000f\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$BA!\u000b\u00030A\u0019\u0011Pa\u000b\n\u0007\t5BMA\u0002B]fD\u0011B!\r(\u0003\u0003\u0005\r!a\r\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u00119\u0004\u0005\u0004\u0003:\t}\"\u0011F\u0007\u0003\u0005wQ1A!\u0010e\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005\u0003\u0012YD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002B$\u0005\u001b\u00022!\u001fB%\u0013\r\u0011Y\u0005\u001a\u0002\b\u0005>|G.Z1o\u0011%\u0011\t$KA\u0001\u0002\u0004\u0011I#\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002B\r\u0005'B\u0011B!\r+\u0003\u0003\u0005\r!a\r\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\r\u0015\u0005\te\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0003H\t}\u0003\"\u0003B\u0019[\u0005\u0005\t\u0019\u0001B\u0015\u0003\u0011!\u0015\r^1\u0011\u0007\u0005\rxfE\u00030\u0005O\n\u0019\t\u0005\u0006\u0003j\t=\u0004\u0010TA\u0007\u0003Cl!Aa\u001b\u000b\u0007\t5D-A\u0004sk:$\u0018.\\3\n\t\tE$1\u000e\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cDC\u0001B2\u0003\u0015\t\u0007\u000f\u001d7z)!\t\tO!\u001f\u0003|\tu\u0004\"\u0002<3\u0001\u0004A\b\"B@3\u0001\u0004a\u0005bBA\u0005e\u0001\u0007\u0011QB\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\u0019Ia$\u0011\u000be\u0014)I!#\n\u0007\t\u001dEM\u0001\u0004PaRLwN\u001c\t\bs\n-\u0005\u0010TA\u0007\u0013\r\u0011i\t\u001a\u0002\u0007)V\u0004H.Z\u001a\t\u0013\tE5'!AA\u0002\u0005\u0005\u0018a\u0001=%a\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003\u0018\nu\u0005cA=\u0003\u001a&\u0019!1\u00143\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003O#\u0004\u0019\u00010\u0003/\u0019k%+Z4sKN\u001c\u0018n\u001c8N_\u0012,GNU3bI\u0016\u00148cA\u001b\u0002\u001aR\u0011!Q\u0015\t\u0004\u0003\u0003,\u0014!C2mCN\u001ch*Y7f\u0003)\u0019G.Y:t\u001d\u0006lW\r\t\u000b\u0004%\n5\u0006BBATs\u0001\u0007a,\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00034B!!1\u0004B[\u0013\u0011\u00119L!\b\u0003\r=\u0013'.Z2uQ\r\u0011BN\u001d\u0015\u0004#1\u0014\b"
)
public class FMRegressionModel extends RegressionModel implements FMRegressorParams, MLWritable {
   private final String uid;
   private final double intercept;
   private final Vector linear;
   private final Matrix factors;
   private final int numFeatures;
   private IntParam factorSize;
   private BooleanParam fitLinear;
   private DoubleParam miniBatchFraction;
   private DoubleParam initStd;
   private Param solver;
   private Param weightCol;
   private DoubleParam regParam;
   private BooleanParam fitIntercept;
   private LongParam seed;
   private DoubleParam tol;
   private DoubleParam stepSize;
   private IntParam maxIter;

   public static FMRegressionModel load(final String path) {
      return FMRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return FMRegressionModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final int getFactorSize() {
      return FactorizationMachinesParams.getFactorSize$(this);
   }

   public final boolean getFitLinear() {
      return FactorizationMachinesParams.getFitLinear$(this);
   }

   public final double getMiniBatchFraction() {
      return FactorizationMachinesParams.getMiniBatchFraction$(this);
   }

   public final double getInitStd() {
      return FactorizationMachinesParams.getInitStd$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final IntParam factorSize() {
      return this.factorSize;
   }

   public final BooleanParam fitLinear() {
      return this.fitLinear;
   }

   public final DoubleParam miniBatchFraction() {
      return this.miniBatchFraction;
   }

   public final DoubleParam initStd() {
      return this.initStd;
   }

   public final Param solver() {
      return this.solver;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$factorSize_$eq(final IntParam x$1) {
      this.factorSize = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$fitLinear_$eq(final BooleanParam x$1) {
      this.fitLinear = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$miniBatchFraction_$eq(final DoubleParam x$1) {
      this.miniBatchFraction = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$initStd_$eq(final DoubleParam x$1) {
      this.initStd = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
   }

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public DoubleParam stepSize() {
      return this.stepSize;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public double intercept() {
      return this.intercept;
   }

   public Vector linear() {
      return this.linear;
   }

   public Matrix factors() {
      return this.factors;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public double predict(final Vector features) {
      return FactorizationMachines$.MODULE$.getRawPrediction(features, this.intercept(), this.linear(), this.factors());
   }

   public FMRegressionModel copy(final ParamMap extra) {
      return (FMRegressionModel)this.copyValues(new FMRegressionModel(this.uid(), this.intercept(), this.linear(), this.factors()), extra);
   }

   public MLWriter write() {
      return new FMRegressionModelWriter(this);
   }

   public String toString() {
      String var10000 = Identifiable.toString$(this);
      return "FMRegressionModel: uid=" + var10000 + ", numFeatures=" + this.numFeatures() + ", factorSize=" + this.$(this.factorSize()) + ", fitLinear=" + this.$(this.fitLinear()) + ", fitIntercept=" + this.$(this.fitIntercept());
   }

   public FMRegressionModel(final String uid, final double intercept, final Vector linear, final Matrix factors) {
      this.uid = uid;
      this.intercept = intercept;
      this.linear = linear;
      this.factors = factors;
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasTol.$init$(this);
      HasSolver.$init$(this);
      HasSeed.$init$(this);
      HasFitIntercept.$init$(this);
      HasRegParam.$init$(this);
      HasWeightCol.$init$(this);
      FactorizationMachinesParams.$init$(this);
      MLWritable.$init$(this);
      this.numFeatures = linear.size();
      Statics.releaseFence();
   }

   public FMRegressionModel() {
      this("", Double.NaN, .MODULE$.empty(), org.apache.spark.ml.linalg.Matrices..MODULE$.empty());
   }

   public static class FMRegressionModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final FMRegressionModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.intercept(), this.instance.linear(), this.instance.factors());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(FMRegressionModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.regression.FMRegressionModel.FMRegressionModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.regression.FMRegressionModel.FMRegressionModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public FMRegressionModelWriter(final FMRegressionModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final double intercept;
         private final Vector linear;
         private final Matrix factors;
         // $FF: synthetic field
         public final FMRegressionModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public double intercept() {
            return this.intercept;
         }

         public Vector linear() {
            return this.linear;
         }

         public Matrix factors() {
            return this.factors;
         }

         public Data copy(final double intercept, final Vector linear, final Matrix factors) {
            return this.org$apache$spark$ml$regression$FMRegressionModel$FMRegressionModelWriter$Data$$$outer().new Data(intercept, linear, factors);
         }

         public double copy$default$1() {
            return this.intercept();
         }

         public Vector copy$default$2() {
            return this.linear();
         }

         public Matrix copy$default$3() {
            return this.factors();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 3;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return BoxesRunTime.boxToDouble(this.intercept());
               }
               case 1 -> {
                  return this.linear();
               }
               case 2 -> {
                  return this.factors();
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
                  return "intercept";
               }
               case 1 -> {
                  return "linear";
               }
               case 2 -> {
                  return "factors";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, Statics.doubleHash(this.intercept()));
            var1 = Statics.mix(var1, Statics.anyHash(this.linear()));
            var1 = Statics.mix(var1, Statics.anyHash(this.factors()));
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var8;
            if (this != x$1) {
               label64: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$regression$FMRegressionModel$FMRegressionModelWriter$Data$$$outer() == this.org$apache$spark$ml$regression$FMRegressionModel$FMRegressionModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.intercept() == var4.intercept()) {
                        label54: {
                           Vector var10000 = this.linear();
                           Vector var5 = var4.linear();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label54;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label54;
                           }

                           Matrix var7 = this.factors();
                           Matrix var6 = var4.factors();
                           if (var7 == null) {
                              if (var6 != null) {
                                 break label54;
                              }
                           } else if (!var7.equals(var6)) {
                              break label54;
                           }

                           if (var4.canEqual(this)) {
                              break label64;
                           }
                        }
                     }
                  }

                  var8 = false;
                  return var8;
               }
            }

            var8 = true;
            return var8;
         }

         // $FF: synthetic method
         public FMRegressionModelWriter org$apache$spark$ml$regression$FMRegressionModel$FMRegressionModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final double intercept, final Vector linear, final Matrix factors) {
            this.intercept = intercept;
            this.linear = linear;
            this.factors = factors;
            if (FMRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = FMRegressionModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final FMRegressionModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final double intercept, final Vector linear, final Matrix factors) {
            return this.$outer.new Data(intercept, linear, factors);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.intercept()), x$0.linear(), x$0.factors())));
         }

         public Data$() {
            if (FMRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = FMRegressionModelWriter.this;
               super();
            }
         }
      }
   }

   private static class FMRegressionModelReader extends MLReader {
      private final String className = FMRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      public FMRegressionModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().format("parquet").load(dataPath);
         Row var7 = (Row)data.select("intercept", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"linear", "factors"}))).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(3) == 0) {
               Object intercept = ((SeqOps)var8.get()).apply(0);
               Object linear = ((SeqOps)var8.get()).apply(1);
               Object factors = ((SeqOps)var8.get()).apply(2);
               if (intercept instanceof Double) {
                  double var12 = BoxesRunTime.unboxToDouble(intercept);
                  if (linear instanceof Vector) {
                     Vector var14 = (Vector)linear;
                     if (factors instanceof Matrix) {
                        Matrix var15 = (Matrix)factors;
                        Tuple3 var6 = new Tuple3(BoxesRunTime.boxToDouble(var12), var14, var15);
                        double intercept = BoxesRunTime.unboxToDouble(var6._1());
                        Vector linear = (Vector)var6._2();
                        Matrix factors = (Matrix)var6._3();
                        FMRegressionModel model = new FMRegressionModel(metadata.uid(), intercept, linear, factors);
                        metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                        return model;
                     }
                  }
               }
            }
         }

         throw new MatchError(var7);
      }

      public FMRegressionModelReader() {
      }
   }
}
