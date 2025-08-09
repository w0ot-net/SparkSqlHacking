package org.apache.spark.api.java;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.rdd.RDD;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Option;
import scala.collection.ArrayOps.;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005d\u0001B\u0014)\u0001MB\u0001\u0002\u0013\u0001\u0003\u0006\u0004%\t!\u0013\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005\u0015\"A\u0001\u000b\u0001BC\u0002\u0013\r\u0011\u000b\u0003\u0005Y\u0001\t\u0005\t\u0015!\u0003S\u0011\u0015I\u0006\u0001\"\u0001[\u0011\u0015q\u0006\u0001\"\u0011`\u0011\u0015\t\u0007\u0001\"\u0001c\u0011\u0015\u0019\u0007\u0001\"\u0001e\u0011\u0015i\u0007\u0001\"\u0001o\u0011\u00159\b\u0001\"\u0001y\u0011\u0015I\b\u0001\"\u0001c\u0011\u0015I\b\u0001\"\u0001{\u0011\u0019\t\t\u0001\u0001C\u0001E\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\b\u0001\u0011\u0005\u0011\u0011\u0003\u0005\b\u0003_\u0001A\u0011AA\u0019\u0011\u001d\ty\u0003\u0001C\u0001\u0003kAq!!\u0010\u0001\t\u0003\ty\u0004C\u0004\u0002D\u0001!\t!!\u0012\t\u000f\u0005\r\u0003\u0001\"\u0001\u0002V!9\u0011Q\r\u0001\u0005\u0002\u0005\u001d\u0004bBA3\u0001\u0011\u0005\u0011Q\u000f\u0005\b\u0003w\u0002A\u0011AA?\u0011\u001d\t\u0019\t\u0001C\u0001\u0003\u000bCq!!#\u0001\t\u0003\tY\tC\u0004\u0002\n\u0002!\t!a$\t\u000f\u0005%\u0005\u0001\"\u0001\u0002\u0016\"9\u0011Q\u0015\u0001\u0005B\u0005\u001d\u0006bBA`\u0001\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u000f\u0004A\u0011AAe\u000f\u001d\ti\u000e\u000bE\u0001\u0003?4aa\n\u0015\t\u0002\u0005\u0005\bBB-!\t\u0003\t)\u0010C\u0004\u0002x\u0002\"\u0019!!?\t\u000f\t=\u0001\u0005b\u0001\u0003\u0012!A!q\u0004\u0011\u0005\u0002)\u0012\t\u0003\u0003\u0005\u0003@\u0001\"\tA\u000bB!\u0011%\u00119\u0006IA\u0001\n\u0013\u0011IFA\u0004KCZ\f'\u000b\u0012#\u000b\u0005%R\u0013\u0001\u00026bm\u0006T!a\u000b\u0017\u0002\u0007\u0005\u0004\u0018N\u0003\u0002.]\u0005)1\u000f]1sW*\u0011q\u0006M\u0001\u0007CB\f7\r[3\u000b\u0003E\n1a\u001c:h\u0007\u0001)\"\u0001N\u001e\u0014\u0005\u0001)\u0004\u0003\u0002\u001c8s\u001dk\u0011\u0001K\u0005\u0003q!\u00121#\u00112tiJ\f7\r\u001e&bm\u0006\u0014F\t\u0012'jW\u0016\u0004\"AO\u001e\r\u0001\u0011)A\b\u0001b\u0001{\t\tA+\u0005\u0002?\tB\u0011qHQ\u0007\u0002\u0001*\t\u0011)A\u0003tG\u0006d\u0017-\u0003\u0002D\u0001\n9aj\u001c;iS:<\u0007CA F\u0013\t1\u0005IA\u0002B]f\u00042A\u000e\u0001:\u0003\r\u0011H\rZ\u000b\u0002\u0015B\u00191*T\u001d\u000e\u00031S!\u0001\u0013\u0017\n\u00059c%a\u0001*E\t\u0006!!\u000f\u001a3!\u0003!\u0019G.Y:t)\u0006<W#\u0001*\u0011\u0007M3\u0016(D\u0001U\u0015\t)\u0006)A\u0004sK\u001adWm\u0019;\n\u0005]#&\u0001C\"mCN\u001cH+Y4\u0002\u0013\rd\u0017m]:UC\u001e\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002\\;R\u0011q\t\u0018\u0005\u0006!\u0016\u0001\u001dA\u0015\u0005\u0006\u0011\u0016\u0001\rAS\u0001\boJ\f\u0007O\u0015#E)\t9\u0005\rC\u0003I\r\u0001\u0007!*A\u0003dC\u000eDW\rF\u0001H\u0003\u001d\u0001XM]:jgR$\"aR3\t\u000b\u0019D\u0001\u0019A4\u0002\u00119,w\u000fT3wK2\u0004\"\u0001[6\u000e\u0003%T!A\u001b\u0017\u0002\u000fM$xN]1hK&\u0011A.\u001b\u0002\r'R|'/Y4f\u0019\u00164X\r\\\u0001\u000eo&$\bNU3t_V\u00148-Z:\u0015\u0005\u001d{\u0007\"\u00029\n\u0001\u0004\t\u0018A\u0001:q!\t\u0011X/D\u0001t\u0015\t!H&\u0001\u0005sKN|WO]2f\u0013\t18OA\bSKN|WO]2f!J|g-\u001b7f\u0003I9W\r\u001e*fg>,(oY3Qe>4\u0017\u000e\\3\u0015\u0003E\f\u0011\"\u001e8qKJ\u001c\u0018n\u001d;\u0015\u0005\u001d[\b\"\u0002?\r\u0001\u0004i\u0018\u0001\u00032m_\u000e\\\u0017N\\4\u0011\u0005}r\u0018BA@A\u0005\u001d\u0011un\u001c7fC:\f\u0001\u0002Z5ti&t7\r\u001e\u000b\u0004\u000f\u0006\u0015\u0001bBA\u0004\u001d\u0001\u0007\u0011\u0011B\u0001\u000e]Vl\u0007+\u0019:uSRLwN\\:\u0011\u0007}\nY!C\u0002\u0002\u000e\u0001\u00131!\u00138u\u0003\u00191\u0017\u000e\u001c;feR\u0019q)a\u0005\t\u000f\u0005Uq\u00021\u0001\u0002\u0018\u0005\ta\rE\u0004\u0002\u001a\u0005}\u0011(a\t\u000e\u0005\u0005m!bAA\u000fQ\u0005Aa-\u001e8di&|g.\u0003\u0003\u0002\"\u0005m!\u0001\u0003$v]\u000e$\u0018n\u001c8\u0011\t\u0005\u0015\u0012QF\u0007\u0003\u0003OQA!!\u000b\u0002,\u0005!A.\u00198h\u0015\u0005I\u0013bA@\u0002(\u0005A1m\\1mKN\u001cW\rF\u0002H\u0003gAq!a\u0002\u0011\u0001\u0004\tI\u0001F\u0003H\u0003o\tI\u0004C\u0004\u0002\bE\u0001\r!!\u0003\t\r\u0005m\u0012\u00031\u0001~\u0003\u001d\u0019\b.\u001e4gY\u0016\f1B]3qCJ$\u0018\u000e^5p]R\u0019q)!\u0011\t\u000f\u0005\u001d!\u00031\u0001\u0002\n\u000511/Y7qY\u0016$RaRA$\u0003\u0017Ba!!\u0013\u0014\u0001\u0004i\u0018aD<ji\"\u0014V\r\u001d7bG\u0016lWM\u001c;\t\u000f\u000553\u00031\u0001\u0002P\u0005AaM]1di&|g\u000eE\u0002@\u0003#J1!a\u0015A\u0005\u0019!u.\u001e2mKR9q)a\u0016\u0002Z\u0005m\u0003BBA%)\u0001\u0007Q\u0010C\u0004\u0002NQ\u0001\r!a\u0014\t\u000f\u0005uC\u00031\u0001\u0002`\u0005!1/Z3e!\ry\u0014\u0011M\u0005\u0004\u0003G\u0002%\u0001\u0002'p]\u001e\f1B]1oI>l7\u000b\u001d7jiR!\u0011\u0011NA8!\u0011y\u00141N$\n\u0007\u00055\u0004IA\u0003BeJ\f\u0017\u0010C\u0004\u0002rU\u0001\r!a\u001d\u0002\u000f],\u0017n\u001a5ugB)q(a\u001b\u0002PQ1\u0011\u0011NA<\u0003sBq!!\u001d\u0017\u0001\u0004\t\u0019\bC\u0004\u0002^Y\u0001\r!a\u0018\u0002\u000bUt\u0017n\u001c8\u0015\u0007\u001d\u000by\b\u0003\u0004\u0002\u0002^\u0001\raR\u0001\u0006_RDWM]\u0001\rS:$XM]:fGRLwN\u001c\u000b\u0004\u000f\u0006\u001d\u0005BBAA1\u0001\u0007q)\u0001\u0005tk\n$(/Y2u)\r9\u0015Q\u0012\u0005\u0007\u0003\u0003K\u0002\u0019A$\u0015\u000b\u001d\u000b\t*a%\t\r\u0005\u0005%\u00041\u0001H\u0011\u001d\t9A\u0007a\u0001\u0003\u0013!RaRAL\u00033Ca!!!\u001c\u0001\u00049\u0005bBAN7\u0001\u0007\u0011QT\u0001\u0002aB!\u0011qTAQ\u001b\u0005a\u0013bAARY\tY\u0001+\u0019:uSRLwN\\3s\u0003!!xn\u0015;sS:<GCAAU!\u0011\tY+!/\u000f\t\u00055\u0016Q\u0017\t\u0004\u0003_\u0003UBAAY\u0015\r\t\u0019LM\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005]\u0006)\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003w\u000biL\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003o\u0003\u0015aB:fi:\u000bW.\u001a\u000b\u0004\u000f\u0006\r\u0007bBAc;\u0001\u0007\u0011\u0011V\u0001\u0005]\u0006lW-\u0001\u0004t_J$()_\u000b\u0005\u0003\u0017\f\u0019\u000eF\u0004H\u0003\u001b\f9.a7\t\u000f\u0005Ua\u00041\u0001\u0002PB9\u0011\u0011DA\u0010s\u0005E\u0007c\u0001\u001e\u0002T\u00121\u0011Q\u001b\u0010C\u0002u\u0012\u0011a\u0015\u0005\u0007\u00033t\u0002\u0019A?\u0002\u0013\u0005\u001c8-\u001a8eS:<\u0007bBA\u0004=\u0001\u0007\u0011\u0011B\u0001\b\u0015\u00064\u0018M\u0015#E!\t1\u0004eE\u0003!\u0003G\fI\u000fE\u0002@\u0003KL1!a:A\u0005\u0019\te.\u001f*fMB!\u00111^Ay\u001b\t\tiO\u0003\u0003\u0002p\u0006-\u0012AA5p\u0013\u0011\t\u00190!<\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005}\u0017a\u00024s_6\u0014F\tR\u000b\u0005\u0003w\u0014\u0019\u0001\u0006\u0003\u0002~\n-A\u0003BA\u0000\u0005\u000b\u0001BA\u000e\u0001\u0003\u0002A\u0019!Ha\u0001\u0005\u000bq\u0012#\u0019A\u001f\t\u0013\t\u001d!%!AA\u0004\t%\u0011AC3wS\u0012,gnY3%cA!1K\u0016B\u0001\u0011\u0019A%\u00051\u0001\u0003\u000eA!1*\u0014B\u0001\u0003\u0015!xN\u0015#E+\u0011\u0011\u0019B!\u0007\u0015\t\tU!1\u0004\t\u0005\u00176\u00139\u0002E\u0002;\u00053!Q\u0001P\u0012C\u0002uBa\u0001S\u0012A\u0002\tu\u0001\u0003\u0002\u001c\u0001\u0005/\tqB]3bIJ#EI\u0012:p[\u001aKG.\u001a\u000b\t\u0005G\u0011iCa\u000e\u0003<A!a\u0007\u0001B\u0013!\u0015y\u00141\u000eB\u0014!\ry$\u0011F\u0005\u0004\u0005W\u0001%\u0001\u0002\"zi\u0016DqAa\f%\u0001\u0004\u0011\t$\u0001\u0002tGB\u0019aGa\r\n\u0007\tU\u0002F\u0001\tKCZ\f7\u000b]1sW\u000e{g\u000e^3yi\"9!\u0011\b\u0013A\u0002\u0005%\u0016\u0001\u00034jY\u0016t\u0017-\\3\t\u000f\tuB\u00051\u0001\u0002\n\u0005Y\u0001/\u0019:bY2,G.[:n\u0003Y\u0011X-\u00193S\t\u00123%o\\7J]B,Ho\u0015;sK\u0006lG\u0003\u0003B\u0012\u0005\u0007\u0012YE!\u0016\t\u000f\t=R\u00051\u0001\u0003FA!\u0011q\u0014B$\u0013\r\u0011I\u0005\f\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\b\u0005\u001b*\u0003\u0019\u0001B(\u0003\tIg\u000e\u0005\u0003\u0002l\nE\u0013\u0002\u0002B*\u0003[\u00141\"\u00138qkR\u001cFO]3b[\"9!QH\u0013A\u0002\u0005%\u0011\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B.!\u0011\t)C!\u0018\n\t\t}\u0013q\u0005\u0002\u0007\u001f\nTWm\u0019;"
)
public class JavaRDD extends AbstractJavaRDDLike {
   private final RDD rdd;
   private final ClassTag classTag;

   public static RDD toRDD(final JavaRDD rdd) {
      return JavaRDD$.MODULE$.toRDD(rdd);
   }

   public static JavaRDD fromRDD(final RDD rdd, final ClassTag evidence$1) {
      return JavaRDD$.MODULE$.fromRDD(rdd, evidence$1);
   }

   public RDD rdd() {
      return this.rdd;
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   public JavaRDD wrapRDD(final RDD rdd) {
      return JavaRDD$.MODULE$.fromRDD(rdd, this.classTag());
   }

   public JavaRDD cache() {
      return this.wrapRDD(this.rdd().cache());
   }

   public JavaRDD persist(final StorageLevel newLevel) {
      return this.wrapRDD(this.rdd().persist(newLevel));
   }

   public JavaRDD withResources(final ResourceProfile rp) {
      return this.wrapRDD(this.rdd().withResources(rp));
   }

   public ResourceProfile getResourceProfile() {
      return this.rdd().getResourceProfile();
   }

   public JavaRDD unpersist() {
      return this.wrapRDD(this.rdd().unpersist(this.rdd().unpersist$default$1()));
   }

   public JavaRDD unpersist(final boolean blocking) {
      return this.wrapRDD(this.rdd().unpersist(blocking));
   }

   public JavaRDD distinct() {
      return this.wrapRDD(this.rdd().distinct());
   }

   public JavaRDD distinct(final int numPartitions) {
      Ordering x$2 = this.rdd().distinct$default$2(numPartitions);
      return this.wrapRDD(this.rdd().distinct(numPartitions, x$2));
   }

   public JavaRDD filter(final Function f) {
      return this.wrapRDD(this.rdd().filter((x) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(f, x))));
   }

   public JavaRDD coalesce(final int numPartitions) {
      JavaRDD$ var10000 = JavaRDD$.MODULE$;
      boolean x$2 = this.rdd().coalesce$default$2();
      Option x$3 = this.rdd().coalesce$default$3();
      Ordering x$4 = this.rdd().coalesce$default$4(numPartitions, x$2, x$3);
      return var10000.fromRDD(this.rdd().coalesce(numPartitions, x$2, x$3, x$4), this.classTag());
   }

   public JavaRDD coalesce(final int numPartitions, final boolean shuffle) {
      JavaRDD$ var10000 = JavaRDD$.MODULE$;
      Option x$3 = this.rdd().coalesce$default$3();
      Ordering x$4 = this.rdd().coalesce$default$4(numPartitions, shuffle, x$3);
      return var10000.fromRDD(this.rdd().coalesce(numPartitions, shuffle, x$3, x$4), this.classTag());
   }

   public JavaRDD repartition(final int numPartitions) {
      JavaRDD$ var10000 = JavaRDD$.MODULE$;
      Ordering x$2 = this.rdd().repartition$default$2(numPartitions);
      return var10000.fromRDD(this.rdd().repartition(numPartitions, x$2), this.classTag());
   }

   public JavaRDD sample(final boolean withReplacement, final double fraction) {
      return this.sample(withReplacement, fraction, Utils$.MODULE$.random().nextLong());
   }

   public JavaRDD sample(final boolean withReplacement, final double fraction, final long seed) {
      return this.wrapRDD(this.rdd().sample(withReplacement, fraction, seed));
   }

   public JavaRDD[] randomSplit(final double[] weights) {
      return this.randomSplit(weights, Utils$.MODULE$.random().nextLong());
   }

   public JavaRDD[] randomSplit(final double[] weights, final long seed) {
      return (JavaRDD[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.rdd().randomSplit(weights, seed)), (rdd) -> this.wrapRDD(rdd), scala.reflect.ClassTag..MODULE$.apply(JavaRDD.class));
   }

   public JavaRDD union(final JavaRDD other) {
      return this.wrapRDD(this.rdd().union(other.rdd()));
   }

   public JavaRDD intersection(final JavaRDD other) {
      return this.wrapRDD(this.rdd().intersection(other.rdd()));
   }

   public JavaRDD subtract(final JavaRDD other) {
      return this.wrapRDD(this.rdd().subtract(JavaRDD$.MODULE$.toRDD(other)));
   }

   public JavaRDD subtract(final JavaRDD other, final int numPartitions) {
      return this.wrapRDD(this.rdd().subtract(JavaRDD$.MODULE$.toRDD(other), numPartitions));
   }

   public JavaRDD subtract(final JavaRDD other, final Partitioner p) {
      RDD x$1 = JavaRDD$.MODULE$.toRDD(other);
      Ordering x$3 = this.rdd().subtract$default$3(x$1, p);
      return this.wrapRDD(this.rdd().subtract(x$1, p, x$3));
   }

   public String toString() {
      return this.rdd().toString();
   }

   public JavaRDD setName(final String name) {
      this.rdd().setName(name);
      return this;
   }

   public JavaRDD sortBy(final Function f, final boolean ascending, final int numPartitions) {
      org.sparkproject.guava.collect.Ordering ordering = org.sparkproject.guava.collect.Ordering.natural();
      ClassTag ctag = JavaSparkContext$.MODULE$.fakeClassTag();
      return this.wrapRDD(this.rdd().sortBy(fn$1(f), ascending, numPartitions, scala.math.Ordering..MODULE$.comparatorToOrdering(ordering), ctag));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$1(final Function f$1, final Object x) {
      return (Boolean)f$1.call(x);
   }

   private static final Function1 fn$1(final Function f$2) {
      return (x) -> f$2.call(x);
   }

   public JavaRDD(final RDD rdd, final ClassTag classTag) {
      this.rdd = rdd;
      this.classTag = classTag;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
