package org.apache.spark.streaming;

import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.rdd.RDD;
import scala.Function4;
import scala.Option;
import scala.Product;
import scala.Option.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\re!\u0002\u0015*\u0001&\n\u0004\u0002\u00030\u0001\u0005+\u0007I\u0011A0\t\u00115\u0004!\u0011#Q\u0001\n\u0001DQA\u001c\u0001\u0005\u0002=DqA\u001d\u0001A\u0002\u0013%1\u000fC\u0004y\u0001\u0001\u0007I\u0011B=\t\r}\u0004\u0001\u0015)\u0003u\u0011%\tI\u0001\u0001a\u0001\n\u0013\tY\u0001C\u0005\u0002 \u0001\u0001\r\u0011\"\u0003\u0002\"!A\u0011Q\u0005\u0001!B\u0013\ti\u0001C\u0005\u0002*\u0001\u0001\r\u0011\"\u0003\u0002,!I\u00111\u0007\u0001A\u0002\u0013%\u0011Q\u0007\u0005\t\u0003s\u0001\u0001\u0015)\u0003\u0002.!9\u0011Q\b\u0001\u0005B\u0005}\u0002bBA\u001f\u0001\u0011\u0005\u0013Q\t\u0005\b\u00037\u0002A\u0011IA/\u0011\u0019\u0011\b\u0001\"\u0011\u0002h!9\u00111\u000e\u0001\u0005B\u00055\u0004\u0002CA:\u0001\u0011\u0005\u0011&!\u001e\t\u0011\u0005]\u0004\u0001\"\u0001*\u0003sB\u0001\"! \u0001\t\u0003I\u0013q\u0010\u0005\t\u0003\u0007\u0003A\u0011A\u0015\u0002\u0006\"I\u0011\u0011\u0012\u0001\u0002\u0002\u0013\u0005\u00111\u0012\u0005\n\u0003W\u0003\u0011\u0013!C\u0001\u0003[C\u0011\"!4\u0001\u0003\u0003%\t%a4\t\u0013\u0005}\u0007!!A\u0005\u0002\u0005\u0005\b\"CAr\u0001\u0005\u0005I\u0011AAs\u0011%\tI\u000fAA\u0001\n\u0003\nY\u000fC\u0005\u0002z\u0002\t\t\u0011\"\u0001\u0002|\"I!Q\u0001\u0001\u0002\u0002\u0013\u0005#q\u0001\u0005\n\u0005\u0017\u0001\u0011\u0011!C!\u0005\u001bA\u0011Ba\u0004\u0001\u0003\u0003%\tE!\u0005\t\u0013\tM\u0001!!A\u0005B\tUqA\u0003B\rS\u0005\u0005\t\u0012A\u0015\u0003\u001c\u0019I\u0001&KA\u0001\u0012\u0003I#Q\u0004\u0005\u0007]\n\"\tAa\f\t\u0013\t=!%!A\u0005F\tE\u0001\"\u0003B\u0019E\u0005\u0005I\u0011\u0011B\u001a\u0011%\u0011\u0019FIA\u0001\n\u0003\u0013)\u0006C\u0005\u0003z\t\n\t\u0011\"\u0003\u0003|\ti1\u000b^1uKN\u0003XmY%na2T!AK\u0016\u0002\u0013M$(/Z1nS:<'B\u0001\u0017.\u0003\u0015\u0019\b/\u0019:l\u0015\tqs&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002a\u0005\u0019qN]4\u0016\u000bIJtIS'\u0014\t\u0001\u0019tJ\u0015\t\u0007iU:d)\u0013'\u000e\u0003%J!AN\u0015\u0003\u0013M#\u0018\r^3Ta\u0016\u001c\u0007C\u0001\u001d:\u0019\u0001!QA\u000f\u0001C\u0002q\u0012\u0011aS\u0002\u0001#\ti4\t\u0005\u0002?\u00036\tqHC\u0001A\u0003\u0015\u00198-\u00197b\u0013\t\u0011uHA\u0004O_RD\u0017N\\4\u0011\u0005y\"\u0015BA#@\u0005\r\te.\u001f\t\u0003q\u001d#Q\u0001\u0013\u0001C\u0002q\u0012\u0011A\u0016\t\u0003q)#Qa\u0013\u0001C\u0002q\u0012\u0011a\u0015\t\u0003q5#QA\u0014\u0001C\u0002q\u0012\u0011\u0001\u0016\t\u0003}AK!!U \u0003\u000fA\u0013x\u000eZ;diB\u00111k\u0017\b\u0003)fs!!\u0016-\u000e\u0003YS!aV\u001e\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0015B\u0001.@\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001X/\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005i{\u0014\u0001\u00034v]\u000e$\u0018n\u001c8\u0016\u0003\u0001\u0004rAP1do\u0019LG.\u0003\u0002c\u007f\tIa)\u001e8di&|g\u000e\u000e\t\u0003i\u0011L!!Z\u0015\u0003\tQKW.\u001a\t\u0004}\u001d4\u0015B\u00015@\u0005\u0019y\u0005\u000f^5p]B\u0019AG[%\n\u0005-L#!B*uCR,\u0007c\u0001 h\u0019\u0006Ia-\u001e8di&|g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005A\f\bC\u0002\u001b\u0001o\u0019KE\nC\u0003_\u0007\u0001\u0007\u0001-A\u0006qCJ$\u0018\u000e^5p]\u0016\u0014X#\u0001;\u0011\u0005U4X\"A\u0016\n\u0005]\\#a\u0003)beRLG/[8oKJ\fq\u0002]1si&$\u0018n\u001c8fe~#S-\u001d\u000b\u0003uv\u0004\"AP>\n\u0005q|$\u0001B+oSRDqA`\u0003\u0002\u0002\u0003\u0007A/A\u0002yIE\nA\u0002]1si&$\u0018n\u001c8fe\u0002B3ABA\u0002!\rq\u0014QA\u0005\u0004\u0003\u000fy$\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u001f%t\u0017\u000e^5bYN#\u0018\r^3S\t\u0012+\"!!\u0004\u0011\r\u0005=\u0011QCA\r\u001b\t\t\tBC\u0002\u0002\u0014-\n1A\u001d3e\u0013\u0011\t9\"!\u0005\u0003\u0007I#E\tE\u0003?\u000379\u0014*C\u0002\u0002\u001e}\u0012a\u0001V;qY\u0016\u0014\u0014aE5oSRL\u0017\r\\*uCR,'\u000b\u0012#`I\u0015\fHc\u0001>\u0002$!Aa\u0010CA\u0001\u0002\u0004\ti!\u0001\tj]&$\u0018.\u00197Ti\u0006$XM\u0015#EA!\u001a\u0011\"a\u0001\u0002\u001fQLW.Z8vi&sG/\u001a:wC2,\"!!\f\u0011\u0007Q\ny#C\u0002\u00022%\u0012\u0001\u0002R;sCRLwN\\\u0001\u0014i&lWm\\;u\u0013:$XM\u001d<bY~#S-\u001d\u000b\u0004u\u0006]\u0002\u0002\u0003@\f\u0003\u0003\u0005\r!!\f\u0002!QLW.Z8vi&sG/\u001a:wC2\u0004\u0003f\u0001\u0007\u0002\u0004\u0005a\u0011N\\5uS\u0006d7\u000b^1uKR!\u0011\u0011IA\"\u001b\u0005\u0001\u0001bBA\n\u001b\u0001\u0007\u0011Q\u0002\u000b\u0005\u0003\u0003\n9\u0005C\u0004\u0002J9\u0001\r!a\u0013\u0002\u0017)\fg/\u0019)bSJ\u0014F\t\u0012\t\u0007\u0003\u001b\n9fN%\u000e\u0005\u0005=#\u0002BA)\u0003'\nAA[1wC*\u0019\u0011QK\u0016\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002Z\u0005=#a\u0003&bm\u0006\u0004\u0016-\u001b:S\t\u0012\u000bQB\\;n!\u0006\u0014H/\u001b;j_:\u001cH\u0003BA!\u0003?Bq!a\u0017\u0010\u0001\u0004\t\t\u0007E\u0002?\u0003GJ1!!\u001a@\u0005\rIe\u000e\u001e\u000b\u0005\u0003\u0003\nI\u0007C\u0003s!\u0001\u0007A/A\u0004uS6,w.\u001e;\u0015\t\u0005\u0005\u0013q\u000e\u0005\b\u0003c\n\u0002\u0019AA\u0017\u0003!Ig\u000e^3sm\u0006d\u0017aC4fi\u001a+hn\u0019;j_:$\u0012\u0001Y\u0001\u0013O\u0016$\u0018J\\5uS\u0006d7\u000b^1uKJ#E\t\u0006\u0002\u0002|A!ahZA\u0007\u000399W\r\u001e)beRLG/[8oKJ$\"!!!\u0011\u0007y:G/\u0001\nhKR$\u0016.\\3pkRLe\u000e^3sm\u0006dGCAAD!\u0011qt-!\f\u0002\t\r|\u0007/_\u000b\u000b\u0003\u001b\u000b\u0019*a&\u0002\u001c\u0006}E\u0003BAH\u0003C\u0003\"\u0002\u000e\u0001\u0002\u0012\u0006U\u0015\u0011TAO!\rA\u00141\u0013\u0003\u0006uY\u0011\r\u0001\u0010\t\u0004q\u0005]E!\u0002%\u0017\u0005\u0004a\u0004c\u0001\u001d\u0002\u001c\u0012)1J\u0006b\u0001yA\u0019\u0001(a(\u0005\u000b93\"\u0019\u0001\u001f\t\u0011y3\u0002\u0013!a\u0001\u0003G\u00032BP1d\u0003#\u000b)+a*\u0002*B!ahZAK!\u0011!$.!'\u0011\ty:\u0017QT\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+)\ty+!2\u0002H\u0006%\u00171Z\u000b\u0003\u0003cS3\u0001YAZW\t\t)\f\u0005\u0003\u00028\u0006\u0005WBAA]\u0015\u0011\tY,!0\u0002\u0013Ut7\r[3dW\u0016$'bAA`\u007f\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\r\u0017\u0011\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002\u001e\u0018\u0005\u0004aD!\u0002%\u0018\u0005\u0004aD!B&\u0018\u0005\u0004aD!\u0002(\u0018\u0005\u0004a\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002RB!\u00111[An\u001b\t\t)N\u0003\u0003\u0002X\u0006e\u0017\u0001\u00027b]\u001eT!!!\u0015\n\t\u0005u\u0017Q\u001b\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u0005\u0014A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004\u0007\u0006\u001d\b\u0002\u0003@\u001b\u0003\u0003\u0005\r!!\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!<\u0011\u000b\u0005=\u0018Q_\"\u000e\u0005\u0005E(bAAz\u007f\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0018\u0011\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002~\n\r\u0001c\u0001 \u0002\u0000&\u0019!\u0011A \u0003\u000f\t{w\u000e\\3b]\"9a\u0010HA\u0001\u0002\u0004\u0019\u0015A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!5\u0003\n!Aa0HA\u0001\u0002\u0004\t\t'\u0001\u0005iCND7i\u001c3f)\t\t\t'\u0001\u0005u_N#(/\u001b8h)\t\t\t.\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003{\u00149\u0002C\u0004\u007fA\u0005\u0005\t\u0019A\"\u0002\u001bM#\u0018\r^3Ta\u0016\u001c\u0017*\u001c9m!\t!$eE\u0003#\u0005?\u0011)\u0003E\u0002?\u0005CI1Aa\t@\u0005\u0019\te.\u001f*fMB!!q\u0005B\u0017\u001b\t\u0011IC\u0003\u0003\u0003,\u0005e\u0017AA5p\u0013\ra&\u0011\u0006\u000b\u0003\u00057\tQ!\u00199qYf,\"B!\u000e\u0003<\t}\"1\tB$)\u0011\u00119D!\u0013\u0011\u0015Q\u0002!\u0011\bB\u001f\u0005\u0003\u0012)\u0005E\u00029\u0005w!QAO\u0013C\u0002q\u00022\u0001\u000fB \t\u0015AUE1\u0001=!\rA$1\t\u0003\u0006\u0017\u0016\u0012\r\u0001\u0010\t\u0004q\t\u001dC!\u0002(&\u0005\u0004a\u0004B\u00020&\u0001\u0004\u0011Y\u0005E\u0006?C\u000e\u0014ID!\u0014\u0003P\tE\u0003\u0003\u0002 h\u0005{\u0001B\u0001\u000e6\u0003BA!ah\u001aB#\u0003\u001d)h.\u00199qYf,\"Ba\u0016\u0003`\t\u0015$1\u000eB9)\u0011\u0011IFa\u001d\u0011\ty:'1\f\t\f}\u0005\u001c'Q\fB1\u0005O\u0012i\u0007E\u00029\u0005?\"QA\u000f\u0014C\u0002q\u0002BAP4\u0003dA\u0019\u0001H!\u001a\u0005\u000b!3#\u0019\u0001\u001f\u0011\tQR'\u0011\u000e\t\u0004q\t-D!B&'\u0005\u0004a\u0004\u0003\u0002 h\u0005_\u00022\u0001\u000fB9\t\u0015qeE1\u0001=\u0011%\u0011)HJA\u0001\u0002\u0004\u00119(A\u0002yIA\u0002\"\u0002\u000e\u0001\u0003^\t\r$\u0011\u000eB8\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011i\b\u0005\u0003\u0002T\n}\u0014\u0002\u0002BA\u0003+\u0014aa\u00142kK\u000e$\b"
)
public class StateSpecImpl extends StateSpec implements Product {
   private final Function4 function;
   private volatile Partitioner partitioner;
   private volatile RDD initialStateRDD;
   private volatile Duration timeoutInterval;

   public static Option unapply(final StateSpecImpl x$0) {
      return StateSpecImpl$.MODULE$.unapply(x$0);
   }

   public static StateSpecImpl apply(final Function4 function) {
      return StateSpecImpl$.MODULE$.apply(function);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Function4 function() {
      return this.function;
   }

   private Partitioner partitioner() {
      return this.partitioner;
   }

   private void partitioner_$eq(final Partitioner x$1) {
      this.partitioner = x$1;
   }

   private RDD initialStateRDD() {
      return this.initialStateRDD;
   }

   private void initialStateRDD_$eq(final RDD x$1) {
      this.initialStateRDD = x$1;
   }

   private Duration timeoutInterval() {
      return this.timeoutInterval;
   }

   private void timeoutInterval_$eq(final Duration x$1) {
      this.timeoutInterval = x$1;
   }

   public StateSpecImpl initialState(final RDD rdd) {
      this.initialStateRDD_$eq(rdd);
      return this;
   }

   public StateSpecImpl initialState(final JavaPairRDD javaPairRDD) {
      this.initialStateRDD_$eq(javaPairRDD.rdd());
      return this;
   }

   public StateSpecImpl numPartitions(final int numPartitions) {
      this.partitioner(new HashPartitioner(numPartitions));
      return this;
   }

   public StateSpecImpl partitioner(final Partitioner partitioner) {
      this.partitioner_$eq(partitioner);
      return this;
   }

   public StateSpecImpl timeout(final Duration interval) {
      this.timeoutInterval_$eq(interval);
      return this;
   }

   public Function4 getFunction() {
      return this.function();
   }

   public Option getInitialStateRDD() {
      return .MODULE$.apply(this.initialStateRDD());
   }

   public Option getPartitioner() {
      return .MODULE$.apply(this.partitioner());
   }

   public Option getTimeoutInterval() {
      return .MODULE$.apply(this.timeoutInterval());
   }

   public StateSpecImpl copy(final Function4 function) {
      return new StateSpecImpl(function);
   }

   public Function4 copy$default$1() {
      return this.function();
   }

   public String productPrefix() {
      return "StateSpecImpl";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.function();
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
      return x$1 instanceof StateSpecImpl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "function";
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
         label47: {
            if (x$1 instanceof StateSpecImpl) {
               label40: {
                  StateSpecImpl var4 = (StateSpecImpl)x$1;
                  Function4 var10000 = this.function();
                  Function4 var5 = var4.function();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public StateSpecImpl(final Function4 function) {
      this.function = function;
      Product.$init$(this);
      scala.Predef..MODULE$.require(function != null);
      this.partitioner = null;
      this.initialStateRDD = null;
      this.timeoutInterval = null;
   }
}
