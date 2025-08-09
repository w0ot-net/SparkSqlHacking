package org.apache.spark.rdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.util.Utils$;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005y4Q\u0001D\u0007\u0001\u001fUA\u0001\u0002\t\u0001\u0003\u0006\u0004%\tA\t\u0005\tq\u0001\u0011\t\u0011)A\u0005G!AA\t\u0001BC\u0002\u0013\u0005S\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003G\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001d!\u0006\u00011A\u0005\u0002UCq!\u0017\u0001A\u0002\u0013\u0005!\f\u0003\u0004a\u0001\u0001\u0006KA\u0016\u0005\u0006C\u0002!\tE\u0019\u0005\u0006G\u0002!\t\u0005\u001a\u0005\u0006U\u0002!Ia\u001b\u0002\"!\u0006\u0014H/\u001b;j_:,'/Q<be\u0016,f.[8o%\u0012#\u0005+\u0019:uSRLwN\u001c\u0006\u0003\u001d=\t1A\u001d3e\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7c\u0001\u0001\u00179A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\u0004\"!\b\u0010\u000e\u0003=I!aH\b\u0003\u0013A\u000b'\u000f^5uS>t\u0017\u0001\u0002:eIN\u001c\u0001!F\u0001$!\r!Cf\f\b\u0003K)r!AJ\u0015\u000e\u0003\u001dR!\u0001K\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0012BA\u0016\u0019\u0003\u001d\u0001\u0018mY6bO\u0016L!!\f\u0018\u0003\u0007M+\u0017O\u0003\u0002,1A\u0012\u0001G\u000e\t\u0004cI\"T\"A\u0007\n\u0005Mj!a\u0001*E\tB\u0011QG\u000e\u0007\u0001\t%9$!!A\u0001\u0002\u000b\u0005QHA\u0002`IE\nQA\u001d3eg\u0002B#A\u0001\u001e\u0011\u0005]Y\u0014B\u0001\u001f\u0019\u0005%!(/\u00198tS\u0016tG/\u0005\u0002?\u0003B\u0011qcP\u0005\u0003\u0001b\u0011qAT8uQ&tw\r\u0005\u0002\u0018\u0005&\u00111\t\u0007\u0002\u0004\u0003:L\u0018!B5oI\u0016DX#\u0001$\u0011\u0005]9\u0015B\u0001%\u0019\u0005\rIe\u000e^\u0001\u0007S:$W\r\u001f\u0011\u0002\rqJg.\u001b;?)\raUj\u0015\t\u0003c\u0001AQ\u0001I\u0003A\u00029\u00032\u0001\n\u0017Pa\t\u0001&\u000bE\u00022eE\u0003\"!\u000e*\u0005\u0013]j\u0015\u0011!A\u0001\u0006\u0003i\u0004\"\u0002#\u0006\u0001\u00041\u0015a\u00029be\u0016tGo]\u000b\u0002-B\u0019qc\u0016\u000f\n\u0005aC\"!B!se\u0006L\u0018a\u00039be\u0016tGo]0%KF$\"a\u00170\u0011\u0005]a\u0016BA/\u0019\u0005\u0011)f.\u001b;\t\u000f};\u0011\u0011!a\u0001-\u0006\u0019\u0001\u0010J\u0019\u0002\u0011A\f'/\u001a8ug\u0002\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\r\u00061Q-];bYN$\"!\u001a5\u0011\u0005]1\u0017BA4\u0019\u0005\u001d\u0011un\u001c7fC:DQ!\u001b\u0006A\u0002\u0005\u000bQa\u001c;iKJ\f1b\u001e:ji\u0016|%M[3diR\u00111\f\u001c\u0005\u0006[.\u0001\rA\\\u0001\u0004_>\u001c\bCA8u\u001b\u0005\u0001(BA9s\u0003\tIwNC\u0001t\u0003\u0011Q\u0017M^1\n\u0005U\u0004(AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6D3aC<~!\r9\u0002P_\u0005\u0003sb\u0011a\u0001\u001e5s_^\u001c\bCA8|\u0013\ta\bOA\u0006J\u001f\u0016C8-\u001a9uS>t7%\u0001>"
)
public class PartitionerAwareUnionRDDPartition implements Partition {
   private final transient Seq rdds;
   private final int index;
   private Partition[] parents;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public Seq rdds() {
      return this.rdds;
   }

   public int index() {
      return this.index;
   }

   public Partition[] parents() {
      return this.parents;
   }

   public void parents_$eq(final Partition[] x$1) {
      this.parents = x$1;
   }

   public int hashCode() {
      return this.index();
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.parents_$eq((Partition[])((IterableOnceOps)this.rdds().map((x$2) -> x$2.partitions()[this.index()])).toArray(.MODULE$.apply(Partition.class)));
         oos.defaultWriteObject();
      });
   }

   public PartitionerAwareUnionRDDPartition(final Seq rdds, final int index) {
      this.rdds = rdds;
      this.index = index;
      Partition.$init$(this);
      this.parents = (Partition[])((IterableOnceOps)rdds.map((x$1) -> x$1.partitions()[this.index()])).toArray(.MODULE$.apply(Partition.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
