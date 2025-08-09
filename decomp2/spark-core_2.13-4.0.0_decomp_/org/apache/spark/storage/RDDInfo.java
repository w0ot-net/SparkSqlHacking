package org.apache.spark.storage;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Utils$;
import scala.Enumeration;
import scala.Option;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg\u0001B\u0015+\u0001MB\u0001\u0002\u0013\u0001\u0003\u0006\u0004%\t!\u0013\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005\u0015\"Aa\n\u0001BA\u0002\u0013\u0005q\n\u0003\u0005Y\u0001\t\u0005\r\u0011\"\u0001Z\u0011!y\u0006A!A!B\u0013\u0001\u0006\u0002\u00031\u0001\u0005\u000b\u0007I\u0011A%\t\u0011\u0005\u0004!\u0011!Q\u0001\n)C\u0001B\u0019\u0001\u0003\u0002\u0004%\ta\u0019\u0005\tO\u0002\u0011\t\u0019!C\u0001Q\"A!\u000e\u0001B\u0001B\u0003&A\r\u0003\u0005l\u0001\t\u0015\r\u0011\"\u0001m\u0011!\u0001\bA!A!\u0002\u0013i\u0007\u0002C9\u0001\u0005\u000b\u0007I\u0011\u0001:\t\u0011Y\u0004!\u0011!Q\u0001\nMD\u0001b\u001e\u0001\u0003\u0006\u0004%\ta\u0014\u0005\tq\u0002\u0011\t\u0011)A\u0005!\"A\u0011\u0010\u0001BC\u0002\u0013\u0005!\u0010C\u0005\u0002\n\u0001\u0011\t\u0011)A\u0005w\"Q\u00111\u0002\u0001\u0003\u0006\u0004%\t!!\u0004\t\u0015\u0005}\u0001A!A!\u0002\u0013\ty\u0001C\u0004\u0002\"\u0001!\t!a\t\t\u0011\u0005]\u0002\u00011A\u0005\u0002%C\u0011\"!\u000f\u0001\u0001\u0004%\t!a\u000f\t\u000f\u0005}\u0002\u0001)Q\u0005\u0015\"I\u0011\u0011\t\u0001A\u0002\u0013\u0005\u00111\t\u0005\n\u0003\u0017\u0002\u0001\u0019!C\u0001\u0003\u001bB\u0001\"!\u0015\u0001A\u0003&\u0011Q\t\u0005\n\u0003'\u0002\u0001\u0019!C\u0001\u0003\u0007B\u0011\"!\u0016\u0001\u0001\u0004%\t!a\u0016\t\u0011\u0005m\u0003\u0001)Q\u0005\u0003\u000bBa!!\u0018\u0001\t\u0003a\u0007bBA0\u0001\u0011\u0005\u0013\u0011\r\u0005\b\u0003G\u0002A\u0011IA3\u000f!\tIH\u000bE\u0001Y\u0005mdaB\u0015+\u0011\u0003a\u0013Q\u0010\u0005\b\u0003C\u0019C\u0011AA@\u0011\u001d\t\ti\tC\u0001\u0003\u0007C\u0011\"!*$#\u0003%\t!a*\t\u0013\u0005m6%%A\u0005\u0002\u0005u\u0006\"CAaGE\u0005I\u0011AAb\u0005\u001d\u0011F\tR%oM>T!a\u000b\u0017\u0002\u000fM$xN]1hK*\u0011QFL\u0001\u0006gB\f'o\u001b\u0006\u0003_A\na!\u00199bG\",'\"A\u0019\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001!$\b\u0005\u00026q5\taGC\u00018\u0003\u0015\u00198-\u00197b\u0013\tIdG\u0001\u0004B]f\u0014VM\u001a\t\u0004w\r3eB\u0001\u001fB\u001d\ti\u0004)D\u0001?\u0015\ty$'\u0001\u0004=e>|GOP\u0005\u0002o%\u0011!IN\u0001\ba\u0006\u001c7.Y4f\u0013\t!UIA\u0004Pe\u0012,'/\u001a3\u000b\u0005\t3\u0004CA$\u0001\u001b\u0005Q\u0013AA5e+\u0005Q\u0005CA\u001bL\u0013\taeGA\u0002J]R\f1!\u001b3!\u0003\u0011q\u0017-\\3\u0016\u0003A\u0003\"!U+\u000f\u0005I\u001b\u0006CA\u001f7\u0013\t!f'\u0001\u0004Qe\u0016$WMZ\u0005\u0003-^\u0013aa\u0015;sS:<'B\u0001+7\u0003!q\u0017-\\3`I\u0015\fHC\u0001.^!\t)4,\u0003\u0002]m\t!QK\\5u\u0011\u001dqF!!AA\u0002A\u000b1\u0001\u001f\u00132\u0003\u0015q\u0017-\\3!\u00035qW/\u001c)beRLG/[8og\u0006qa.^7QCJ$\u0018\u000e^5p]N\u0004\u0013\u0001D:u_J\fw-\u001a'fm\u0016dW#\u00013\u0011\u0005\u001d+\u0017B\u00014+\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u0003A\u0019Ho\u001c:bO\u0016dUM^3m?\u0012*\u0017\u000f\u0006\u0002[S\"9a,CA\u0001\u0002\u0004!\u0017!D:u_J\fw-\u001a'fm\u0016d\u0007%A\u0005jg\n\u000b'O]5feV\tQ\u000e\u0005\u00026]&\u0011qN\u000e\u0002\b\u0005>|G.Z1o\u0003)I7OQ1se&,'\u000fI\u0001\na\u0006\u0014XM\u001c;JIN,\u0012a\u001d\t\u0004wQT\u0015BA;F\u0005\r\u0019V-]\u0001\u000ba\u0006\u0014XM\u001c;JIN\u0004\u0013\u0001C2bY2\u001c\u0016\u000e^3\u0002\u0013\r\fG\u000e\\*ji\u0016\u0004\u0013!B:d_B,W#A>\u0011\u0007Ubh0\u0003\u0002~m\t1q\n\u001d;j_:\u00042a`A\u0003\u001b\t\t\tAC\u0002\u0002\u00041\n1A\u001d3e\u0013\u0011\t9!!\u0001\u0003#I#Ei\u00149fe\u0006$\u0018n\u001c8TG>\u0004X-\u0001\u0004tG>\u0004X\rI\u0001\u0019_V$\b/\u001e;EKR,'/\\5oSN$\u0018n\u0019'fm\u0016dWCAA\b!\u0011\t\t\"a\u0006\u000f\u0007}\f\u0019\"\u0003\u0003\u0002\u0016\u0005\u0005\u0011A\u0005#fi\u0016\u0014X.\u001b8jgRL7\rT3wK2LA!!\u0007\u0002\u001c\t)a+\u00197vK&\u0019\u0011Q\u0004\u001c\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u001a_V$\b/\u001e;EKR,'/\\5oSN$\u0018n\u0019'fm\u0016d\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0014\r\u0006\u0015\u0012qEA\u0015\u0003W\ti#a\f\u00022\u0005M\u0012Q\u0007\u0005\u0006\u0011V\u0001\rA\u0013\u0005\u0006\u001dV\u0001\r\u0001\u0015\u0005\u0006AV\u0001\rA\u0013\u0005\u0006EV\u0001\r\u0001\u001a\u0005\u0006WV\u0001\r!\u001c\u0005\u0006cV\u0001\ra\u001d\u0005\boV\u0001\n\u00111\u0001Q\u0011\u001dIX\u0003%AA\u0002mD\u0011\"a\u0003\u0016!\u0003\u0005\r!a\u0004\u0002'9,XnQ1dQ\u0016$\u0007+\u0019:uSRLwN\\:\u0002/9,XnQ1dQ\u0016$\u0007+\u0019:uSRLwN\\:`I\u0015\fHc\u0001.\u0002>!9alFA\u0001\u0002\u0004Q\u0015\u0001\u00068v[\u000e\u000b7\r[3e!\u0006\u0014H/\u001b;j_:\u001c\b%A\u0004nK6\u001c\u0016N_3\u0016\u0005\u0005\u0015\u0003cA\u001b\u0002H%\u0019\u0011\u0011\n\u001c\u0003\t1{gnZ\u0001\f[\u0016l7+\u001b>f?\u0012*\u0017\u000fF\u0002[\u0003\u001fB\u0001B\u0018\u000e\u0002\u0002\u0003\u0007\u0011QI\u0001\t[\u0016l7+\u001b>fA\u0005AA-[:l'&TX-\u0001\u0007eSN\\7+\u001b>f?\u0012*\u0017\u000fF\u0002[\u00033B\u0001BX\u000f\u0002\u0002\u0003\u0007\u0011QI\u0001\nI&\u001c8nU5{K\u0002\n\u0001\"[:DC\u000eDW\rZ\u0001\ti>\u001cFO]5oOR\t\u0001+A\u0004d_6\u0004\u0018M]3\u0015\u0007)\u000b9\u0007\u0003\u0004\u0002j\u0005\u0002\rAR\u0001\u0005i\"\fG\u000fK\u0002\u0001\u0003[\u0002B!a\u001c\u0002v5\u0011\u0011\u0011\u000f\u0006\u0004\u0003gb\u0013AC1o]>$\u0018\r^5p]&!\u0011qOA9\u00051!UM^3m_B,'/\u00119j\u0003\u001d\u0011F\tR%oM>\u0004\"aR\u0012\u0014\u0005\r\"DCAA>\u0003\u001d1'o\\7SI\u0012$2ARAC\u0011\u001d\t\u0019!\na\u0001\u0003\u000f\u0003D!!#\u0002\u0014B)q0a#\u0002\u0010&!\u0011QRA\u0001\u0005\r\u0011F\t\u0012\t\u0005\u0003#\u000b\u0019\n\u0004\u0001\u0005\u0019\u0005U\u0015QQA\u0001\u0002\u0003\u0015\t!a&\u0003\u0007}#\u0013'\u0005\u0003\u0002\u001a\u0006}\u0005cA\u001b\u0002\u001c&\u0019\u0011Q\u0014\u001c\u0003\u000f9{G\u000f[5oOB\u0019Q'!)\n\u0007\u0005\rfGA\u0002B]f\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:TCAAUU\r\u0001\u00161V\u0016\u0003\u0003[\u0003B!a,\u000286\u0011\u0011\u0011\u0017\u0006\u0005\u0003g\u000b),A\u0005v]\u000eDWmY6fI*\u0019\u00111\u000f\u001c\n\t\u0005e\u0016\u0011\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0003(\u0006\u0002\u0002@*\u001a10a+\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u0013:+\t\t)M\u000b\u0003\u0002\u0010\u0005-\u0006"
)
public class RDDInfo implements Ordered {
   private final int id;
   private String name;
   private final int numPartitions;
   private StorageLevel storageLevel;
   private final boolean isBarrier;
   private final Seq parentIds;
   private final String callSite;
   private final Option scope;
   private final Enumeration.Value outputDeterministicLevel;
   private int numCachedPartitions;
   private long memSize;
   private long diskSize;

   public static Enumeration.Value $lessinit$greater$default$9() {
      return RDDInfo$.MODULE$.$lessinit$greater$default$9();
   }

   public static Option $lessinit$greater$default$8() {
      return RDDInfo$.MODULE$.$lessinit$greater$default$8();
   }

   public static String $lessinit$greater$default$7() {
      return RDDInfo$.MODULE$.$lessinit$greater$default$7();
   }

   public static RDDInfo fromRdd(final RDD rdd) {
      return RDDInfo$.MODULE$.fromRdd(rdd);
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public int id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public void name_$eq(final String x$1) {
      this.name = x$1;
   }

   public int numPartitions() {
      return this.numPartitions;
   }

   public StorageLevel storageLevel() {
      return this.storageLevel;
   }

   public void storageLevel_$eq(final StorageLevel x$1) {
      this.storageLevel = x$1;
   }

   public boolean isBarrier() {
      return this.isBarrier;
   }

   public Seq parentIds() {
      return this.parentIds;
   }

   public String callSite() {
      return this.callSite;
   }

   public Option scope() {
      return this.scope;
   }

   public Enumeration.Value outputDeterministicLevel() {
      return this.outputDeterministicLevel;
   }

   public int numCachedPartitions() {
      return this.numCachedPartitions;
   }

   public void numCachedPartitions_$eq(final int x$1) {
      this.numCachedPartitions = x$1;
   }

   public long memSize() {
      return this.memSize;
   }

   public void memSize_$eq(final long x$1) {
      this.memSize = x$1;
   }

   public long diskSize() {
      return this.diskSize;
   }

   public void diskSize_$eq(final long x$1) {
      this.diskSize = x$1;
   }

   public boolean isCached() {
      return this.memSize() + this.diskSize() > 0L && this.numCachedPartitions() > 0;
   }

   public String toString() {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("RDD \"%s\" (%d) StorageLevel: %s; CachedPartitions: %d; TotalPartitions: %d; MemorySize: %s; DiskSize: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.name(), BoxesRunTime.boxToInteger(this.id()), this.storageLevel().toString(), BoxesRunTime.boxToInteger(this.numCachedPartitions()), BoxesRunTime.boxToInteger(this.numPartitions()), Utils$.MODULE$.bytesToString(this.memSize()), Utils$.MODULE$.bytesToString(this.diskSize())}));
   }

   public int compare(final RDDInfo that) {
      return this.id() - that.id();
   }

   public RDDInfo(final int id, final String name, final int numPartitions, final StorageLevel storageLevel, final boolean isBarrier, final Seq parentIds, final String callSite, final Option scope, final Enumeration.Value outputDeterministicLevel) {
      this.id = id;
      this.name = name;
      this.numPartitions = numPartitions;
      this.storageLevel = storageLevel;
      this.isBarrier = isBarrier;
      this.parentIds = parentIds;
      this.callSite = callSite;
      this.scope = scope;
      this.outputDeterministicLevel = outputDeterministicLevel;
      super();
      Ordered.$init$(this);
      this.numCachedPartitions = 0;
      this.memSize = 0L;
      this.diskSize = 0L;
   }
}
