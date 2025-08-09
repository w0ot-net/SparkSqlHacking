package org.apache.spark.api.java;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.partial.PartialResult;
import org.apache.spark.rdd.DoubleRDDFunctions;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD$;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.StatCounter;
import org.apache.spark.util.Utils$;
import org.sparkproject.guava.collect.Ordering;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t}c\u0001\u0002\u001b6\u0001\u0001C\u0001\"\u0014\u0001\u0003\u0006\u0004%\tA\u0014\u0005\t5\u0002\u0011\t\u0011)A\u0005\u001f\")1\f\u0001C\u00019\"9a\f\u0001b\u0001\n\u0003z\u0006B\u00024\u0001A\u0003%\u0001\rC\u0004S\u0001\t\u0007I\u0011I4\t\r%\u0004\u0001\u0015!\u0003i\u0011\u0015Q\u0007\u0001\"\u0011l\u0011\u0015i\u0007\u0001\"\u0001o\u0011\u0015y\u0007\u0001\"\u0001q\u0011\u0015I\b\u0001\"\u0001o\u0011\u0015I\b\u0001\"\u0001{\u0011\u001d\t\t\u0001\u0001C!\u0003\u0007Aa!!\u0002\u0001\t\u0003q\u0007bBA\u0003\u0001\u0011\u0005\u0011q\u0001\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!a\u000b\u0001\t\u0003\t\t\u0004C\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005}\u0002\u0001\"\u0001\u0002B!9\u0011q\b\u0001\u0005\u0002\u0005\u001d\u0003bBA \u0001\u0011\u0005\u0011Q\n\u0005\b\u0003;\u0002A\u0011AA0\u0011\u001d\ti\u0006\u0001C\u0001\u0003SBq!!\u001f\u0001\t\u0003\tY\bC\u0004\u0002\u0000\u0001!\t!!!\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\u0004!9\u0011q\u0011\u0001\u0005\u0002\u0005\r\u0001bBAE\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003\u0017\u0003A\u0011AAG\u0011\u001d\tY\n\u0001C\u0001\u0003\u0007Aq!!(\u0001\t\u0003\t\u0019\u0001C\u0004\u0002 \u0002!\t!a\u0001\t\u000f\u0005\u0005\u0006\u0001\"\u0001\u0002\u0004!9\u00111\u0015\u0001\u0005\u0002\u0005\r\u0001bBAS\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003s\u0003A\u0011AA\u0002\u0011\u001d\ti\f\u0001C\u0001\u0003\u007fCq!!0\u0001\t\u0003\tY\u000eC\u0004\u0002`\u0002!\t!!9\t\u000f\u0005}\u0007\u0001\"\u0001\u0002h\"9\u00111\u001e\u0001\u0005\u0002\u00055\bbBAv\u0001\u0011\u0005!\u0011\u0001\u0005\b\u0003W\u0004A\u0011\u0001B\u0004\u0011\u001d\u0011\t\u0002\u0001C\u0001\u0005'9qAa\f6\u0011\u0003\u0011\tD\u0002\u00045k!\u0005!1\u0007\u0005\u00077>\"\tAa\u0012\t\u000f\t%s\u0006\"\u0001\u0003L!9!qJ\u0018\u0005\u0004\tE\u0003\"\u0003B+_\u0005\u0005I\u0011\u0002B,\u00055Q\u0015M^1E_V\u0014G.\u001a*E\t*\u0011agN\u0001\u0005U\u00064\u0018M\u0003\u00029s\u0005\u0019\u0011\r]5\u000b\u0005iZ\u0014!B:qCJ\\'B\u0001\u001f>\u0003\u0019\t\u0007/Y2iK*\ta(A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u0003B!!iQ#M\u001b\u0005)\u0014B\u0001#6\u0005M\t%m\u001d;sC\u000e$(*\u0019<b%\u0012#E*[6f!\t1%*D\u0001H\u0015\tA\u0015*\u0001\u0003mC:<'\"\u0001\u001c\n\u0005-;%A\u0002#pk\ndW\r\u0005\u0002C\u0001\u0005!1O\u001d3e+\u0005y\u0005c\u0001)T+6\t\u0011K\u0003\u0002Ss\u0005\u0019!\u000f\u001a3\n\u0005Q\u000b&a\u0001*E\tB\u0011a+W\u0007\u0002/*\t\u0001,A\u0003tG\u0006d\u0017-\u0003\u0002L/\u0006)1O\u001d3eA\u00051A(\u001b8jiz\"\"\u0001T/\t\u000b5\u001b\u0001\u0019A(\u0002\u0011\rd\u0017m]:UC\u001e,\u0012\u0001\u0019\t\u0004C\u0012,U\"\u00012\u000b\u0005\r<\u0016a\u0002:fM2,7\r^\u0005\u0003K\n\u0014\u0001b\u00117bgN$\u0016mZ\u0001\nG2\f7o\u001d+bO\u0002*\u0012\u0001\u001b\t\u0004!N+\u0015\u0001\u0002:eI\u0002\nqa\u001e:baJ#E\t\u0006\u0002MY\")!\u000b\u0003a\u0001Q\u0006)1-Y2iKR\tA*A\u0004qKJ\u001c\u0018n\u001d;\u0015\u00051\u000b\b\"\u0002:\u000b\u0001\u0004\u0019\u0018\u0001\u00038fo2+g/\u001a7\u0011\u0005Q<X\"A;\u000b\u0005YL\u0014aB:u_J\fw-Z\u0005\u0003qV\u0014Ab\u0015;pe\u0006<W\rT3wK2\f\u0011\"\u001e8qKJ\u001c\u0018n\u001d;\u0015\u00051[\b\"\u0002?\r\u0001\u0004i\u0018\u0001\u00032m_\u000e\\\u0017N\\4\u0011\u0005Ys\u0018BA@X\u0005\u001d\u0011un\u001c7fC:\fQAZ5sgR$\u0012!R\u0001\tI&\u001cH/\u001b8diR\u0019A*!\u0003\t\u000f\u0005-q\u00021\u0001\u0002\u000e\u0005ia.^7QCJ$\u0018\u000e^5p]N\u00042AVA\b\u0013\r\t\tb\u0016\u0002\u0004\u0013:$\u0018A\u00024jYR,'\u000fF\u0002M\u0003/Aq!!\u0007\u0011\u0001\u0004\tY\"A\u0001g!\u001d\ti\"a\tF\u0003Oi!!a\b\u000b\u0007\u0005\u0005R'\u0001\u0005gk:\u001cG/[8o\u0013\u0011\t)#a\b\u0003\u0011\u0019+hn\u0019;j_:\u00042ARA\u0015\u0013\tyx)\u0001\u0005d_\u0006dWm]2f)\ra\u0015q\u0006\u0005\b\u0003\u0017\t\u0002\u0019AA\u0007)\u0015a\u00151GA\u001b\u0011\u001d\tYA\u0005a\u0001\u0003\u001bAa!a\u000e\u0013\u0001\u0004i\u0018aB:ik\u001a4G.Z\u0001\fe\u0016\u0004\u0018M\u001d;ji&|g\u000eF\u0002M\u0003{Aq!a\u0003\u0014\u0001\u0004\ti!\u0001\u0005tk\n$(/Y2u)\ra\u00151\t\u0005\u0007\u0003\u000b\"\u0002\u0019\u0001'\u0002\u000b=$\b.\u001a:\u0015\u000b1\u000bI%a\u0013\t\r\u0005\u0015S\u00031\u0001M\u0011\u001d\tY!\u0006a\u0001\u0003\u001b!R\u0001TA(\u0003#Ba!!\u0012\u0017\u0001\u0004a\u0005bBA*-\u0001\u0007\u0011QK\u0001\u0002aB!\u0011qKA-\u001b\u0005I\u0014bAA.s\tY\u0001+\u0019:uSRLwN\\3s\u0003\u0019\u0019\u0018-\u001c9mKR)A*!\u0019\u0002f!1\u00111M\fA\u0002u\fqb^5uQJ+\u0007\u000f\\1dK6,g\u000e\u001e\u0005\u0007\u0003O:\u0002\u0019A#\u0002\u0011\u0019\u0014\u0018m\u0019;j_:$r\u0001TA6\u0003[\ny\u0007\u0003\u0004\u0002da\u0001\r! \u0005\u0007\u0003OB\u0002\u0019A#\t\u000f\u0005E\u0004\u00041\u0001\u0002t\u0005!1/Z3e!\r1\u0016QO\u0005\u0004\u0003o:&\u0001\u0002'p]\u001e\fQ!\u001e8j_:$2\u0001TA?\u0011\u0019\t)%\u0007a\u0001\u0019\u0006a\u0011N\u001c;feN,7\r^5p]R\u0019A*a!\t\r\u0005\u0015#\u00041\u0001M\u0003\r\u0019X/\\\u0001\u0004[&t\u0017aA7bq\u0006)1\u000f^1ugR\u0011\u0011q\u0012\t\u0005\u0003#\u000b9*\u0004\u0002\u0002\u0014*\u0019\u0011QS\u001d\u0002\tU$\u0018\u000e\\\u0005\u0005\u00033\u000b\u0019JA\u0006Ti\u0006$8i\\;oi\u0016\u0014\u0018\u0001B7fC:\f\u0001B^1sS\u0006t7-Z\u0001\u0006gR$WM^\u0001\fg\u0006l\u0007\u000f\\3Ti\u0012,g/\u0001\btC6\u0004H.\u001a,be&\fgnY3\u0002\u0011A|\u0007o\u0015;eKZDS\u0001JAU\u0003k\u0003B!a+\u000226\u0011\u0011Q\u0016\u0006\u0004\u0003_K\u0014AC1o]>$\u0018\r^5p]&!\u00111WAW\u0005\u0015\u0019\u0016N\\2fC\t\t9,A\u00033]Er\u0003'A\u0006q_B4\u0016M]5b]\u000e,\u0007&B\u0013\u0002*\u0006U\u0016AC7fC:\f\u0005\u000f\u001d:pqR1\u0011\u0011YAj\u0003/\u0004b!a1\u0002J\u00065WBAAc\u0015\r\t9-O\u0001\ba\u0006\u0014H/[1m\u0013\u0011\tY-!2\u0003\u001bA\u000b'\u000f^5bYJ+7/\u001e7u!\u0011\t\u0019-a4\n\t\u0005E\u0017Q\u0019\u0002\u000e\u0005>,h\u000eZ3e\t>,(\r\\3\t\u000f\u0005Ug\u00051\u0001\u0002t\u00059A/[7f_V$\bBBAmM\u0001\u0007Q)\u0001\u0006d_:4\u0017\u000eZ3oG\u0016$B!!1\u0002^\"9\u0011Q[\u0014A\u0002\u0005M\u0014!C:v[\u0006\u0003\bO]8y)\u0019\t\t-a9\u0002f\"9\u0011Q\u001b\u0015A\u0002\u0005M\u0004BBAmQ\u0001\u0007Q\t\u0006\u0003\u0002B\u0006%\bbBAkS\u0001\u0007\u00111O\u0001\nQ&\u001cHo\\4sC6$B!a<\u0002~B9a+!=\u0002v\u0006m\u0018bAAz/\n1A+\u001e9mKJ\u0002BAVA|+&\u0019\u0011\u0011`,\u0003\u000b\u0005\u0013(/Y=\u0011\u000bY\u000b90a\u001d\t\u000f\u0005}(\u00061\u0001\u0002\u000e\u0005Y!-^2lKR\u001cu.\u001e8u)\u0011\tYPa\u0001\t\u000f\t\u00151\u00061\u0001\u0002v\u00069!-^2lKR\u001cHCBA~\u0005\u0013\u0011i\u0001C\u0004\u0003\u00061\u0002\rAa\u0003\u0011\tY\u000b90\u0012\u0005\u0007\u0005\u001fa\u0003\u0019A?\u0002\u0017\u00154XM\u001c\"vG.,Go]\u0001\bg\u0016$h*Y7f)\ra%Q\u0003\u0005\b\u0005/i\u0003\u0019\u0001B\r\u0003\u0011q\u0017-\\3\u0011\t\tm!\u0011\u0006\b\u0005\u0005;\u0011)\u0003E\u0002\u0003 ]k!A!\t\u000b\u0007\t\rr(\u0001\u0004=e>|GOP\u0005\u0004\u0005O9\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0003,\t5\"AB*ue&twMC\u0002\u0003(]\u000bQBS1wC\u0012{WO\u00197f%\u0012#\u0005C\u0001\"0'\u0015y#Q\u0007B\u001e!\r1&qG\u0005\u0004\u0005s9&AB!osJ+g\r\u0005\u0003\u0003>\t\rSB\u0001B \u0015\r\u0011\t%S\u0001\u0003S>LAA!\u0012\u0003@\ta1+\u001a:jC2L'0\u00192mKR\u0011!\u0011G\u0001\bMJ|WN\u0015#E)\ra%Q\n\u0005\u0006%F\u0002\raT\u0001\u0006i>\u0014F\t\u0012\u000b\u0004\u001f\nM\u0003\"\u0002*3\u0001\u0004a\u0015\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B-!\r1%1L\u0005\u0004\u0005;:%AB(cU\u0016\u001cG\u000f"
)
public class JavaDoubleRDD extends AbstractJavaRDDLike {
   private final RDD srdd;
   private final ClassTag classTag;
   private final RDD rdd;

   public static RDD toRDD(final JavaDoubleRDD rdd) {
      return JavaDoubleRDD$.MODULE$.toRDD(rdd);
   }

   public static JavaDoubleRDD fromRDD(final RDD rdd) {
      return JavaDoubleRDD$.MODULE$.fromRDD(rdd);
   }

   public RDD srdd() {
      return this.srdd;
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   public RDD rdd() {
      return this.rdd;
   }

   public JavaDoubleRDD wrapRDD(final RDD rdd) {
      return new JavaDoubleRDD(rdd.map((x$1) -> BoxesRunTime.boxToDouble($anonfun$wrapRDD$1(x$1)), .MODULE$.Double()));
   }

   public JavaDoubleRDD cache() {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().cache());
   }

   public JavaDoubleRDD persist(final StorageLevel newLevel) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().persist(newLevel));
   }

   public JavaDoubleRDD unpersist() {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().unpersist(this.srdd().unpersist$default$1()));
   }

   public JavaDoubleRDD unpersist(final boolean blocking) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().unpersist(blocking));
   }

   public Double first() {
      return scala.Predef..MODULE$.double2Double(BoxesRunTime.unboxToDouble(this.srdd().first()));
   }

   public JavaDoubleRDD distinct() {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().distinct());
   }

   public JavaDoubleRDD distinct(final int numPartitions) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().distinct(numPartitions, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   public JavaDoubleRDD filter(final Function f) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().filter((JFunction1.mcZD.sp)(x) -> (Boolean)f.call(scala.Predef..MODULE$.double2Double(x))));
   }

   public JavaDoubleRDD coalesce(final int numPartitions) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().coalesce(numPartitions, this.srdd().coalesce$default$2(), this.srdd().coalesce$default$3(), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   public JavaDoubleRDD coalesce(final int numPartitions, final boolean shuffle) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().coalesce(numPartitions, shuffle, this.srdd().coalesce$default$3(), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   public JavaDoubleRDD repartition(final int numPartitions) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().repartition(numPartitions, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   public JavaDoubleRDD subtract(final JavaDoubleRDD other) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().subtract(JavaDoubleRDD$.MODULE$.toRDD(other)));
   }

   public JavaDoubleRDD subtract(final JavaDoubleRDD other, final int numPartitions) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().subtract(JavaDoubleRDD$.MODULE$.toRDD(other), numPartitions));
   }

   public JavaDoubleRDD subtract(final JavaDoubleRDD other, final Partitioner p) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().subtract(JavaDoubleRDD$.MODULE$.toRDD(other), p, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   public JavaDoubleRDD sample(final boolean withReplacement, final Double fraction) {
      return this.sample(withReplacement, fraction, Utils$.MODULE$.random().nextLong());
   }

   public JavaDoubleRDD sample(final boolean withReplacement, final Double fraction, final long seed) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().sample(withReplacement, scala.Predef..MODULE$.Double2double(fraction), seed));
   }

   public JavaDoubleRDD union(final JavaDoubleRDD other) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().union(other.srdd()));
   }

   public JavaDoubleRDD intersection(final JavaDoubleRDD other) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.srdd().intersection(other.srdd()));
   }

   public Double sum() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).sum());
   }

   public Double min() {
      return (Double)this.min(Ordering.natural());
   }

   public Double max() {
      return (Double)this.max(Ordering.natural());
   }

   public StatCounter stats() {
      return RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).stats();
   }

   public Double mean() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).mean());
   }

   public Double variance() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).variance());
   }

   public Double stdev() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).stdev());
   }

   public Double sampleStdev() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).sampleStdev());
   }

   public Double sampleVariance() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).sampleVariance());
   }

   public Double popStdev() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).popStdev());
   }

   public Double popVariance() {
      return scala.Predef..MODULE$.double2Double(RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).popVariance());
   }

   public PartialResult meanApprox(final long timeout, final Double confidence) {
      return RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).meanApprox(timeout, scala.Predef..MODULE$.Double2double(confidence));
   }

   public PartialResult meanApprox(final long timeout) {
      DoubleRDDFunctions qual$1 = RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd());
      double x$2 = qual$1.meanApprox$default$2();
      return qual$1.meanApprox(timeout, x$2);
   }

   public PartialResult sumApprox(final long timeout, final Double confidence) {
      return RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).sumApprox(timeout, scala.Predef..MODULE$.Double2double(confidence));
   }

   public PartialResult sumApprox(final long timeout) {
      DoubleRDDFunctions qual$1 = RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd());
      double x$2 = qual$1.sumApprox$default$2();
      return qual$1.sumApprox(timeout, x$2);
   }

   public Tuple2 histogram(final int bucketCount) {
      Tuple2 result = RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).histogram(bucketCount);
      return new Tuple2(result._1(), result._2());
   }

   public long[] histogram(final double[] buckets) {
      return RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).histogram(buckets, false);
   }

   public long[] histogram(final Double[] buckets, final boolean evenBuckets) {
      return RDD$.MODULE$.doubleRDDToDoubleRDDFunctions(this.srdd()).histogram((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])buckets), (x$2) -> BoxesRunTime.boxToDouble($anonfun$histogram$1(x$2)), .MODULE$.Double()), evenBuckets);
   }

   public JavaDoubleRDD setName(final String name) {
      this.srdd().setName(name);
      return this;
   }

   // $FF: synthetic method
   public static final Double $anonfun$rdd$1(final double x) {
      return x;
   }

   // $FF: synthetic method
   public static final double $anonfun$wrapRDD$1(final Double x$1) {
      return x$1;
   }

   // $FF: synthetic method
   public static final double $anonfun$histogram$1(final Double x$2) {
      return scala.Predef..MODULE$.Double2double(x$2);
   }

   public JavaDoubleRDD(final RDD srdd) {
      this.srdd = srdd;
      this.classTag = (ClassTag)scala.Predef..MODULE$.implicitly(.MODULE$.apply(Double.class));
      this.rdd = srdd.map((x) -> $anonfun$rdd$1(BoxesRunTime.unboxToDouble(x)), .MODULE$.apply(Double.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
