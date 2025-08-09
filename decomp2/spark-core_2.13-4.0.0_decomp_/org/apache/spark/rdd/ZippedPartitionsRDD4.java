package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import scala.Function4;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg!B\u0010!\u0001\tB\u0003\u0002C\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u0011\t\u0003!\u00111A\u0005\u0002\rC\u0001b\u0019\u0001\u0003\u0002\u0004%\t\u0001\u001a\u0005\tU\u0002\u0011\t\u0011)Q\u0005\t\"A1\u000e\u0001BA\u0002\u0013\u0005A\u000e\u0003\u0005q\u0001\t\u0005\r\u0011\"\u0001r\u0011!\u0019\bA!A!B\u0013i\u0007\u0002\u0003;\u0001\u0005\u0003\u0007I\u0011A;\t\u0011]\u0004!\u00111A\u0005\u0002aD\u0001B\u001f\u0001\u0003\u0002\u0003\u0006KA\u001e\u0005\tw\u0002\u0011\t\u0019!C\u0001y\"Aa\u0010\u0001BA\u0002\u0013\u0005q\u0010C\u0005\u0002\u0004\u0001\u0011\t\u0011)Q\u0005{\"Q\u0011Q\u0001\u0001\u0003\u0002\u0004%\t!a\u0002\t\u0015\u0005-\u0001A!a\u0001\n\u0003\ti\u0001\u0003\u0006\u0002\u0012\u0001\u0011\t\u0011)Q\u0005\u0003\u0013A!\"a\u0005\u0001\u0005\u0003\u0005\u000b\u0011BA\u000b\u0011)\tY\u0002\u0001B\u0002B\u0003-\u0011Q\u0004\u0005\u000b\u0003S\u0001!1!Q\u0001\f\u0005-\u0002BCA\u0017\u0001\t\r\t\u0015a\u0003\u00020!Q\u0011\u0011\u0007\u0001\u0003\u0004\u0003\u0006Y!a\r\t\u0015\u0005U\u0002AaA!\u0002\u0017\t9\u0004C\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005e\u0003\u0001\"\u0011\u0002\\!9\u0011\u0011\u000f\u0001\u0005B\u0005MtACA;A\u0005\u0005\t\u0012\u0001\u0012\u0002x\u0019Iq\u0004IA\u0001\u0012\u0003\u0011\u0013\u0011\u0010\u0005\b\u0003sYB\u0011AAI\u0011%\t\u0019jGI\u0001\n\u0003\t)\nC\u0005\u00028n\t\t\u0011\"\u0003\u0002:\n!\",\u001b9qK\u0012\u0004\u0016M\u001d;ji&|gn\u001d*E\tRR!!\t\u0012\u0002\u0007I$GM\u0003\u0002$I\u0005)1\u000f]1sW*\u0011QEJ\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\n1a\u001c:h+\u0019IC\u000b\u0017/aaM\u0011\u0001A\u000b\t\u0004W1rS\"\u0001\u0011\n\u00055\u0002#a\u0006.jaB,G\rU1si&$\u0018n\u001c8t\u0005\u0006\u001cXM\u0015#E!\ty\u0003\u0007\u0004\u0001\u0005\u000bE\u0002!\u0019A\u001a\u0003\u0003Y\u001b\u0001!\u0005\u00025uA\u0011Q\u0007O\u0007\u0002m)\tq'A\u0003tG\u0006d\u0017-\u0003\u0002:m\t9aj\u001c;iS:<\u0007CA\u001b<\u0013\tadGA\u0002B]f\f!a]2\u0011\u0005}\u0002U\"\u0001\u0012\n\u0005\u0005\u0013#\u0001D*qCJ\\7i\u001c8uKb$\u0018!\u00014\u0016\u0003\u0011\u0003r!N#H-js&-\u0003\u0002Gm\tIa)\u001e8di&|g\u000e\u000e\t\u0004\u0011B\u001bfBA%O\u001d\tQU*D\u0001L\u0015\ta%'\u0001\u0004=e>|GOP\u0005\u0002o%\u0011qJN\u0001\ba\u0006\u001c7.Y4f\u0013\t\t&K\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tye\u0007\u0005\u00020)\u0012)Q\u000b\u0001b\u0001g\t\t\u0011\tE\u0002I!^\u0003\"a\f-\u0005\u000be\u0003!\u0019A\u001a\u0003\u0003\t\u00032\u0001\u0013)\\!\tyC\fB\u0003^\u0001\t\u00071GA\u0001D!\rA\u0005k\u0018\t\u0003_\u0001$Q!\u0019\u0001C\u0002M\u0012\u0011\u0001\u0012\t\u0004\u0011Bs\u0013!\u00024`I\u0015\fHCA3i!\t)d-\u0003\u0002hm\t!QK\\5u\u0011\u001dI7!!AA\u0002\u0011\u000b1\u0001\u001f\u00132\u0003\t1\u0007%\u0001\u0003sI\u0012\fT#A7\u0011\u0007-r7+\u0003\u0002pA\t\u0019!\u000b\u0012#\u0002\u0011I$G-M0%KF$\"!\u001a:\t\u000f%4\u0011\u0011!a\u0001[\u0006)!\u000f\u001a32A\u0005!!\u000f\u001a33+\u00051\bcA\u0016o/\u0006A!\u000f\u001a33?\u0012*\u0017\u000f\u0006\u0002fs\"9\u0011.CA\u0001\u0002\u00041\u0018!\u0002:eIJ\u0002\u0013\u0001\u0002:eIN*\u0012! \t\u0004W9\\\u0016\u0001\u0003:eINzF%Z9\u0015\u0007\u0015\f\t\u0001C\u0004j\u0019\u0005\u0005\t\u0019A?\u0002\u000bI$Gm\r\u0011\u0002\tI$G\rN\u000b\u0003\u0003\u0013\u00012a\u000b8`\u0003!\u0011H\r\u001a\u001b`I\u0015\fHcA3\u0002\u0010!A\u0011nDA\u0001\u0002\u0004\tI!A\u0003sI\u0012$\u0004%A\u000bqe\u0016\u001cXM\u001d<fgB\u000b'\u000f^5uS>t\u0017N\\4\u0011\u0007U\n9\"C\u0002\u0002\u001aY\u0012qAQ8pY\u0016\fg.\u0001\u0006fm&$WM\\2fIe\u0002R!a\b\u0002&Mk!!!\t\u000b\u0007\u0005\rb'A\u0004sK\u001adWm\u0019;\n\t\u0005\u001d\u0012\u0011\u0005\u0002\t\u00072\f7o\u001d+bO\u0006YQM^5eK:\u001cW\rJ\u00191!\u0015\ty\"!\nX\u0003-)g/\u001b3f]\u000e,G%M\u0019\u0011\u000b\u0005}\u0011QE.\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0006\u0003?\t)cX\u0001\fKZLG-\u001a8dK\u0012\n4\u0007E\u0003\u0002 \u0005\u0015b&\u0001\u0004=S:LGO\u0010\u000b\u0011\u0003{\tY%!\u0014\u0002P\u0005E\u00131KA+\u0003/\"B\"a\u0010\u0002B\u0005\r\u0013QIA$\u0003\u0013\u0002ra\u000b\u0001T/n{f\u0006C\u0004\u0002\u001c]\u0001\u001d!!\b\t\u000f\u0005%r\u0003q\u0001\u0002,!9\u0011QF\fA\u0004\u0005=\u0002bBA\u0019/\u0001\u000f\u00111\u0007\u0005\b\u0003k9\u00029AA\u001c\u0011\u0015it\u00031\u0001?\u0011\u0015\u0011u\u00031\u0001E\u0011\u0015Yw\u00031\u0001n\u0011\u0015!x\u00031\u0001w\u0011\u0015Yx\u00031\u0001~\u0011\u001d\t)a\u0006a\u0001\u0003\u0013A\u0011\"a\u0005\u0018!\u0003\u0005\r!!\u0006\u0002\u000f\r|W\u000e];uKR)!-!\u0018\u0002h!9\u0011q\f\rA\u0002\u0005\u0005\u0014!A:\u0011\u0007}\n\u0019'C\u0002\u0002f\t\u0012\u0011\u0002U1si&$\u0018n\u001c8\t\u000f\u0005%\u0004\u00041\u0001\u0002l\u000591m\u001c8uKb$\bcA \u0002n%\u0019\u0011q\u000e\u0012\u0003\u0017Q\u000b7o[\"p]R,\u0007\u0010^\u0001\u0012G2,\u0017M\u001d#fa\u0016tG-\u001a8dS\u0016\u001cH#A3\u0002)iK\u0007\u000f]3e!\u0006\u0014H/\u001b;j_:\u001c(\u000b\u0012#5!\tY3dE\u0003\u001c\u0003w\n\t\tE\u00026\u0003{J1!a 7\u0005\u0019\te.\u001f*fMB!\u00111QAG\u001b\t\t)I\u0003\u0003\u0002\b\u0006%\u0015AA5p\u0015\t\tY)\u0001\u0003kCZ\f\u0017\u0002BAH\u0003\u000b\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a\u001e\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00138+1\t9*!,\u00020\u0006E\u00161WA[+\t\tIJ\u000b\u0003\u0002\u0016\u0005m5FAAO!\u0011\ty*!+\u000e\u0005\u0005\u0005&\u0002BAR\u0003K\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u001df'\u0001\u0006b]:|G/\u0019;j_:LA!a+\u0002\"\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bUk\"\u0019A\u001a\u0005\u000bek\"\u0019A\u001a\u0005\u000buk\"\u0019A\u001a\u0005\u000b\u0005l\"\u0019A\u001a\u0005\u000bEj\"\u0019A\u001a\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\u0006\u0003BA_\u0003\u0007l!!a0\u000b\t\u0005\u0005\u0017\u0011R\u0001\u0005Y\u0006tw-\u0003\u0003\u0002F\u0006}&AB(cU\u0016\u001cG\u000f"
)
public class ZippedPartitionsRDD4 extends ZippedPartitionsBaseRDD {
   private Function4 f;
   private RDD rdd1;
   private RDD rdd2;
   private RDD rdd3;
   private RDD rdd4;

   public static boolean $lessinit$greater$default$7() {
      return ZippedPartitionsRDD4$.MODULE$.$lessinit$greater$default$7();
   }

   public Function4 f() {
      return this.f;
   }

   public void f_$eq(final Function4 x$1) {
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

   public RDD rdd4() {
      return this.rdd4;
   }

   public void rdd4_$eq(final RDD x$1) {
      this.rdd4 = x$1;
   }

   public Iterator compute(final Partition s, final TaskContext context) {
      Seq partitions = ((ZippedPartitionsPartition)s).partitions();
      return (Iterator)this.f().apply(this.rdd1().iterator((Partition)partitions.apply(0), context), this.rdd2().iterator((Partition)partitions.apply(1), context), this.rdd3().iterator((Partition)partitions.apply(2), context), this.rdd4().iterator((Partition)partitions.apply(3), context));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdd1_$eq((RDD)null);
      this.rdd2_$eq((RDD)null);
      this.rdd3_$eq((RDD)null);
      this.rdd4_$eq((RDD)null);
      this.f_$eq((Function4)null);
   }

   public ZippedPartitionsRDD4(final SparkContext sc, final Function4 f, final RDD rdd1, final RDD rdd2, final RDD rdd3, final RDD rdd4, final boolean preservesPartitioning, final ClassTag evidence$9, final ClassTag evidence$10, final ClassTag evidence$11, final ClassTag evidence$12, final ClassTag evidence$13) {
      this.f = f;
      this.rdd1 = rdd1;
      this.rdd2 = rdd2;
      this.rdd3 = rdd3;
      this.rdd4 = rdd4;
      super(sc, new .colon.colon(rdd1, new .colon.colon(rdd2, new .colon.colon(rdd3, new .colon.colon(rdd4, scala.collection.immutable.Nil..MODULE$)))), preservesPartitioning, evidence$13);
   }
}
