package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]d!B\f\u0019\u0001i\u0001\u0003\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u0011i\u0002!\u00111A\u0005\u0002mB\u0001b\u0015\u0001\u0003\u0002\u0004%\t\u0001\u0016\u0005\t5\u0002\u0011\t\u0011)Q\u0005y!A1\f\u0001BA\u0002\u0013\u0005A\f\u0003\u0005a\u0001\t\u0005\r\u0011\"\u0001b\u0011!\u0019\u0007A!A!B\u0013i\u0006\u0002\u00033\u0001\u0005\u0003\u0007I\u0011A3\t\u0011\u001d\u0004!\u00111A\u0005\u0002!D\u0001B\u001b\u0001\u0003\u0002\u0003\u0006KA\u001a\u0005\tW\u0002\u0011\t\u0011)A\u0005Y\"Aq\u000e\u0001B\u0002B\u0003-\u0001\u000f\u0003\u0005w\u0001\t\r\t\u0015a\u0003x\u0011!A\bAaA!\u0002\u0017I\b\"\u0002>\u0001\t\u0003Y\bbBA\u0007\u0001\u0011\u0005\u0013q\u0002\u0005\b\u0003K\u0001A\u0011IA\u0014\u000f)\tI\u0003GA\u0001\u0012\u0003Q\u00121\u0006\u0004\n/a\t\t\u0011#\u0001\u001b\u0003[AaA_\n\u0005\u0002\u0005\u0015\u0003\"CA$'E\u0005I\u0011AA%\u0011%\t9gEA\u0001\n\u0013\tIG\u0001\u000b[SB\u0004X\r\u001a)beRLG/[8ogJ#EI\r\u0006\u00033i\t1A\u001d3e\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<W\u0003B\u0011M!\"\u001a\"\u0001\u0001\u0012\u0011\u0007\r\"c%D\u0001\u0019\u0013\t)\u0003DA\f[SB\u0004X\r\u001a)beRLG/[8og\n\u000b7/\u001a*E\tB\u0011q\u0005\u000b\u0007\u0001\t\u0015I\u0003A1\u0001,\u0005\u000516\u0001A\t\u0003YI\u0002\"!\f\u0019\u000e\u00039R\u0011aL\u0001\u0006g\u000e\fG.Y\u0005\u0003c9\u0012qAT8uQ&tw\r\u0005\u0002.g%\u0011AG\f\u0002\u0004\u0003:L\u0018AA:d!\t9\u0004(D\u0001\u001b\u0013\tI$D\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/A\u0001g+\u0005a\u0004#B\u0017>\u007f9\u0013\u0016B\u0001 /\u0005%1UO\\2uS>t'\u0007E\u0002A\u0011.s!!\u0011$\u000f\u0005\t+U\"A\"\u000b\u0005\u0011S\u0013A\u0002\u001fs_>$h(C\u00010\u0013\t9e&A\u0004qC\u000e\\\u0017mZ3\n\u0005%S%\u0001C%uKJ\fGo\u001c:\u000b\u0005\u001ds\u0003CA\u0014M\t\u0015i\u0005A1\u0001,\u0005\u0005\t\u0005c\u0001!I\u001fB\u0011q\u0005\u0015\u0003\u0006#\u0002\u0011\ra\u000b\u0002\u0002\u0005B\u0019\u0001\t\u0013\u0014\u0002\u000b\u0019|F%Z9\u0015\u0005UC\u0006CA\u0017W\u0013\t9fF\u0001\u0003V]&$\bbB-\u0004\u0003\u0003\u0005\r\u0001P\u0001\u0004q\u0012\n\u0014A\u00014!\u0003\u0011\u0011H\rZ\u0019\u0016\u0003u\u00032a\t0L\u0013\ty\u0006DA\u0002S\t\u0012\u000b\u0001B\u001d3ec}#S-\u001d\u000b\u0003+\nDq!\u0017\u0004\u0002\u0002\u0003\u0007Q,A\u0003sI\u0012\f\u0004%\u0001\u0003sI\u0012\u0014T#\u00014\u0011\u0007\rrv*\u0001\u0005sI\u0012\u0014t\fJ3r)\t)\u0016\u000eC\u0004Z\u0013\u0005\u0005\t\u0019\u00014\u0002\u000bI$GM\r\u0011\u0002+A\u0014Xm]3sm\u0016\u001c\b+\u0019:uSRLwN\\5oOB\u0011Q&\\\u0005\u0003]:\u0012qAQ8pY\u0016\fg.\u0001\u0006fm&$WM\\2fII\u00022!\u001d;L\u001b\u0005\u0011(BA:/\u0003\u001d\u0011XM\u001a7fGRL!!\u001e:\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00134!\r\tHoT\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004cA9uM\u00051A(\u001b8jiz\"2\u0002`A\u0002\u0003\u000b\t9!!\u0003\u0002\fQ)QP`@\u0002\u0002A)1\u0005A&PM!)qn\u0004a\u0002a\")ao\u0004a\u0002o\")\u0001p\u0004a\u0002s\")Qg\u0004a\u0001m!)!h\u0004a\u0001y!)1l\u0004a\u0001;\")Am\u0004a\u0001M\"91n\u0004I\u0001\u0002\u0004a\u0017aB2p[B,H/\u001a\u000b\u0006%\u0006E\u00111\u0004\u0005\b\u0003'\u0001\u0002\u0019AA\u000b\u0003\u0005\u0019\bcA\u001c\u0002\u0018%\u0019\u0011\u0011\u0004\u000e\u0003\u0013A\u000b'\u000f^5uS>t\u0007bBA\u000f!\u0001\u0007\u0011qD\u0001\bG>tG/\u001a=u!\r9\u0014\u0011E\u0005\u0004\u0003GQ\"a\u0003+bg.\u001cuN\u001c;fqR\f\u0011c\u00197fCJ$U\r]3oI\u0016t7-[3t)\u0005)\u0016\u0001\u0006.jaB,G\rU1si&$\u0018n\u001c8t%\u0012#%\u0007\u0005\u0002$'M)1#a\f\u00026A\u0019Q&!\r\n\u0007\u0005MbF\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003o\t\t%\u0004\u0002\u0002:)!\u00111HA\u001f\u0003\tIwN\u0003\u0002\u0002@\u0005!!.\u0019<b\u0013\u0011\t\u0019%!\u000f\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005-\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0005\u0002L\u0005\u0005\u00141MA3+\t\tiEK\u0002m\u0003\u001fZ#!!\u0015\u0011\t\u0005M\u0013QL\u0007\u0003\u0003+RA!a\u0016\u0002Z\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u00037r\u0013AC1o]>$\u0018\r^5p]&!\u0011qLA+\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006\u001bV\u0011\ra\u000b\u0003\u0006#V\u0011\ra\u000b\u0003\u0006SU\u0011\raK\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003W\u0002B!!\u001c\u0002t5\u0011\u0011q\u000e\u0006\u0005\u0003c\ni$\u0001\u0003mC:<\u0017\u0002BA;\u0003_\u0012aa\u00142kK\u000e$\b"
)
public class ZippedPartitionsRDD2 extends ZippedPartitionsBaseRDD {
   private Function2 f;
   private RDD rdd1;
   private RDD rdd2;

   public static boolean $lessinit$greater$default$5() {
      return ZippedPartitionsRDD2$.MODULE$.$lessinit$greater$default$5();
   }

   public Function2 f() {
      return this.f;
   }

   public void f_$eq(final Function2 x$1) {
      this.f = x$1;
   }

   public RDD rdd1() {
      return this.rdd1;
   }

   public void rdd1_$eq(final RDD x$1) {
      this.rdd1 = x$1;
   }

   public RDD rdd2() {
      return this.rdd2;
   }

   public void rdd2_$eq(final RDD x$1) {
      this.rdd2 = x$1;
   }

   public Iterator compute(final Partition s, final TaskContext context) {
      Seq partitions = ((ZippedPartitionsPartition)s).partitions();
      return (Iterator)this.f().apply(this.rdd1().iterator((Partition)partitions.apply(0), context), this.rdd2().iterator((Partition)partitions.apply(1), context));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdd1_$eq((RDD)null);
      this.rdd2_$eq((RDD)null);
      this.f_$eq((Function2)null);
   }

   public ZippedPartitionsRDD2(final SparkContext sc, final Function2 f, final RDD rdd1, final RDD rdd2, final boolean preservesPartitioning, final ClassTag evidence$2, final ClassTag evidence$3, final ClassTag evidence$4) {
      this.f = f;
      this.rdd1 = rdd1;
      this.rdd2 = rdd2;
      super(sc, new .colon.colon(rdd1, new .colon.colon(rdd2, scala.collection.immutable.Nil..MODULE$)), preservesPartitioning, evidence$4);
   }
}
