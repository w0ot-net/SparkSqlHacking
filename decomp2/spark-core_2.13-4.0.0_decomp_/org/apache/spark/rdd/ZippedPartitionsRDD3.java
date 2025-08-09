package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import scala.Function3;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}e!B\u000e\u001d\u0001y!\u0003\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u0011y\u0002!\u00111A\u0005\u0002}B\u0001b\u0017\u0001\u0003\u0002\u0004%\t\u0001\u0018\u0005\tE\u0002\u0011\t\u0011)Q\u0005\u0001\"A1\r\u0001BA\u0002\u0013\u0005A\r\u0003\u0005i\u0001\t\u0005\r\u0011\"\u0001j\u0011!Y\u0007A!A!B\u0013)\u0007\u0002\u00037\u0001\u0005\u0003\u0007I\u0011A7\t\u0011=\u0004!\u00111A\u0005\u0002AD\u0001B\u001d\u0001\u0003\u0002\u0003\u0006KA\u001c\u0005\tg\u0002\u0011\t\u0019!C\u0001i\"Aa\u000f\u0001BA\u0002\u0013\u0005q\u000f\u0003\u0005z\u0001\t\u0005\t\u0015)\u0003v\u0011!Q\bA!A!\u0002\u0013Y\b\u0002\u0003@\u0001\u0005\u0007\u0005\u000b1B@\t\u0015\u0005-\u0001AaA!\u0002\u0017\ti\u0001\u0003\u0006\u0002\u0010\u0001\u0011\u0019\u0011)A\u0006\u0003#A!\"a\u0005\u0001\u0005\u0007\u0005\u000b1BA\u000b\u0011\u001d\t9\u0002\u0001C\u0001\u00033Aq!a\r\u0001\t\u0003\n)\u0004C\u0004\u0002L\u0001!\t%!\u0014\b\u0015\u0005=C$!A\t\u0002y\t\tFB\u0005\u001c9\u0005\u0005\t\u0012\u0001\u0010\u0002T!9\u0011qC\f\u0005\u0002\u0005-\u0004\"CA7/E\u0005I\u0011AA8\u0011%\tyiFA\u0001\n\u0013\t\tJ\u0001\u000b[SB\u0004X\r\u001a)beRLG/[8ogJ#Ei\r\u0006\u0003;y\t1A\u001d3e\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<W#B\u0013Q)bc3C\u0001\u0001'!\r9\u0003FK\u0007\u00029%\u0011\u0011\u0006\b\u0002\u00185&\u0004\b/\u001a3QCJ$\u0018\u000e^5p]N\u0014\u0015m]3S\t\u0012\u0003\"a\u000b\u0017\r\u0001\u0011)Q\u0006\u0001b\u0001_\t\tak\u0001\u0001\u0012\u0005A2\u0004CA\u00195\u001b\u0005\u0011$\"A\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0012$a\u0002(pi\"Lgn\u001a\t\u0003c]J!\u0001\u000f\u001a\u0003\u0007\u0005s\u00170\u0001\u0002tGB\u00111\bP\u0007\u0002=%\u0011QH\b\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0002MV\t\u0001\t\u0005\u00042\u0003\u000e\u0013fKW\u0005\u0003\u0005J\u0012\u0011BR;oGRLwN\\\u001a\u0011\u0007\u0011cuJ\u0004\u0002F\u0015:\u0011a)S\u0007\u0002\u000f*\u0011\u0001JL\u0001\u0007yI|w\u000e\u001e \n\u0003MJ!a\u0013\u001a\u0002\u000fA\f7m[1hK&\u0011QJ\u0014\u0002\t\u0013R,'/\u0019;pe*\u00111J\r\t\u0003WA#Q!\u0015\u0001C\u0002=\u0012\u0011!\u0011\t\u0004\t2\u001b\u0006CA\u0016U\t\u0015)\u0006A1\u00010\u0005\u0005\u0011\u0005c\u0001#M/B\u00111\u0006\u0017\u0003\u00063\u0002\u0011\ra\f\u0002\u0002\u0007B\u0019A\t\u0014\u0016\u0002\u000b\u0019|F%Z9\u0015\u0005u\u0003\u0007CA\u0019_\u0013\ty&G\u0001\u0003V]&$\bbB1\u0004\u0003\u0003\u0005\r\u0001Q\u0001\u0004q\u0012\n\u0014A\u00014!\u0003\u0011\u0011H\rZ\u0019\u0016\u0003\u0015\u00042a\n4P\u0013\t9GDA\u0002S\t\u0012\u000b\u0001B\u001d3ec}#S-\u001d\u000b\u0003;*Dq!\u0019\u0004\u0002\u0002\u0003\u0007Q-A\u0003sI\u0012\f\u0004%\u0001\u0003sI\u0012\u0014T#\u00018\u0011\u0007\u001d27+\u0001\u0005sI\u0012\u0014t\fJ3r)\ti\u0016\u000fC\u0004b\u0013\u0005\u0005\t\u0019\u00018\u0002\u000bI$GM\r\u0011\u0002\tI$GmM\u000b\u0002kB\u0019qEZ,\u0002\u0011I$GmM0%KF$\"!\u0018=\t\u000f\u0005d\u0011\u0011!a\u0001k\u0006)!\u000f\u001a34A\u0005)\u0002O]3tKJ4Xm\u001d)beRLG/[8oS:<\u0007CA\u0019}\u0013\ti(GA\u0004C_>dW-\u00198\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u0003\u0002\u0002\u0005\u001dq*\u0004\u0002\u0002\u0004)\u0019\u0011Q\u0001\u001a\u0002\u000fI,g\r\\3di&!\u0011\u0011BA\u0002\u0005!\u0019E.Y:t)\u0006<\u0017AC3wS\u0012,gnY3%mA)\u0011\u0011AA\u0004'\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\u000b\u0005\u0005\u0011qA,\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\bE\u0003\u0002\u0002\u0005\u001d!&\u0001\u0004=S:LGO\u0010\u000b\u000f\u00037\t9#!\u000b\u0002,\u00055\u0012qFA\u0019))\ti\"a\b\u0002\"\u0005\r\u0012Q\u0005\t\u0007O\u0001y5k\u0016\u0016\t\u000by\u001c\u00029A@\t\u000f\u0005-1\u0003q\u0001\u0002\u000e!9\u0011qB\nA\u0004\u0005E\u0001bBA\n'\u0001\u000f\u0011Q\u0003\u0005\u0006sM\u0001\rA\u000f\u0005\u0006}M\u0001\r\u0001\u0011\u0005\u0006GN\u0001\r!\u001a\u0005\u0006YN\u0001\rA\u001c\u0005\u0006gN\u0001\r!\u001e\u0005\buN\u0001\n\u00111\u0001|\u0003\u001d\u0019w.\u001c9vi\u0016$RAWA\u001c\u0003\u0003Bq!!\u000f\u0015\u0001\u0004\tY$A\u0001t!\rY\u0014QH\u0005\u0004\u0003\u007fq\"!\u0003)beRLG/[8o\u0011\u001d\t\u0019\u0005\u0006a\u0001\u0003\u000b\nqaY8oi\u0016DH\u000fE\u0002<\u0003\u000fJ1!!\u0013\u001f\u0005-!\u0016m]6D_:$X\r\u001f;\u0002#\rdW-\u0019:EKB,g\u000eZ3oG&,7\u000fF\u0001^\u0003QQ\u0016\u000e\u001d9fIB\u000b'\u000f^5uS>t7O\u0015#EgA\u0011qeF\n\u0006/\u0005U\u00131\f\t\u0004c\u0005]\u0013bAA-e\t1\u0011I\\=SK\u001a\u0004B!!\u0018\u0002h5\u0011\u0011q\f\u0006\u0005\u0003C\n\u0019'\u0001\u0002j_*\u0011\u0011QM\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002j\u0005}#\u0001D*fe&\fG.\u001b>bE2,GCAA)\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%mUQ\u0011\u0011OAD\u0003\u0013\u000bY)!$\u0016\u0005\u0005M$fA>\u0002v-\u0012\u0011q\u000f\t\u0005\u0003s\n\u0019)\u0004\u0002\u0002|)!\u0011QPA@\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0002J\n!\"\u00198o_R\fG/[8o\u0013\u0011\t))a\u001f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003R3\t\u0007q\u0006B\u0003V3\t\u0007q\u0006B\u0003Z3\t\u0007q\u0006B\u0003.3\t\u0007q&\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0014B!\u0011QSAN\u001b\t\t9J\u0003\u0003\u0002\u001a\u0006\r\u0014\u0001\u00027b]\u001eLA!!(\u0002\u0018\n1qJ\u00196fGR\u0004"
)
public class ZippedPartitionsRDD3 extends ZippedPartitionsBaseRDD {
   private Function3 f;
   private RDD rdd1;
   private RDD rdd2;
   private RDD rdd3;

   public static boolean $lessinit$greater$default$6() {
      return ZippedPartitionsRDD3$.MODULE$.$lessinit$greater$default$6();
   }

   public Function3 f() {
      return this.f;
   }

   public void f_$eq(final Function3 x$1) {
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

   public RDD rdd3() {
      return this.rdd3;
   }

   public void rdd3_$eq(final RDD x$1) {
      this.rdd3 = x$1;
   }

   public Iterator compute(final Partition s, final TaskContext context) {
      Seq partitions = ((ZippedPartitionsPartition)s).partitions();
      return (Iterator)this.f().apply(this.rdd1().iterator((Partition)partitions.apply(0), context), this.rdd2().iterator((Partition)partitions.apply(1), context), this.rdd3().iterator((Partition)partitions.apply(2), context));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdd1_$eq((RDD)null);
      this.rdd2_$eq((RDD)null);
      this.rdd3_$eq((RDD)null);
      this.f_$eq((Function3)null);
   }

   public ZippedPartitionsRDD3(final SparkContext sc, final Function3 f, final RDD rdd1, final RDD rdd2, final RDD rdd3, final boolean preservesPartitioning, final ClassTag evidence$5, final ClassTag evidence$6, final ClassTag evidence$7, final ClassTag evidence$8) {
      this.f = f;
      this.rdd1 = rdd1;
      this.rdd2 = rdd2;
      this.rdd3 = rdd3;
      super(sc, new .colon.colon(rdd1, new .colon.colon(rdd2, new .colon.colon(rdd3, scala.collection.immutable.Nil..MODULE$))), preservesPartitioning, evidence$8);
   }
}
