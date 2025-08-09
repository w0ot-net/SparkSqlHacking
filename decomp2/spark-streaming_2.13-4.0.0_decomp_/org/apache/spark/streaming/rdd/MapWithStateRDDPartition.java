package org.apache.spark.streaming.rdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Utils.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b!B\n\u0015\u0001Yq\u0002\u0002C\u0015\u0001\u0005\u000b\u0007I\u0011I\u0016\t\u0011=\u0002!\u0011!Q\u0001\n1B\u0001\u0002\r\u0001\u0003\u0002\u0004%I!\r\u0005\t\u0011\u0002\u0011\t\u0019!C\u0005\u0013\"AA\b\u0001B\u0001B\u0003&!\u0007\u0003\u0005P\u0001\t\u0005\r\u0011\"\u0003Q\u0011!A\u0006A!a\u0001\n\u0013I\u0006\u0002\u0003,\u0001\u0005\u0003\u0005\u000b\u0015B)\t\u000bm\u0003A\u0011\u0001/\t\u0011)\u0004\u0001\u0019!C\u0001)-D\u0001\u0002\u001c\u0001A\u0002\u0013\u0005A#\u001c\u0005\u0007_\u0002\u0001\u000b\u0015B\u0013\t\u0011A\u0004\u0001\u0019!C\u0001)-D\u0001\"\u001d\u0001A\u0002\u0013\u0005AC\u001d\u0005\u0007i\u0002\u0001\u000b\u0015B\u0013\t\u000bU\u0004A\u0011\t<\t\u000b]\u0004A\u0011\t=\t\u000by\u0004A\u0011B@\u000315\u000b\u0007oV5uQN#\u0018\r^3S\t\u0012\u0003\u0016M\u001d;ji&|gN\u0003\u0002\u0016-\u0005\u0019!\u000f\u001a3\u000b\u0005]A\u0012!C:ue\u0016\fW.\u001b8h\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<7c\u0001\u0001 KA\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\u0004\"AJ\u0014\u000e\u0003aI!\u0001\u000b\r\u0003\u0013A\u000b'\u000f^5uS>t\u0017!B5oI\u0016D8\u0001A\u000b\u0002YA\u0011\u0001%L\u0005\u0003]\u0005\u00121!\u00138u\u0003\u0019Ig\u000eZ3yA\u0005a\u0001O]3w'R\fG/\u001a*E\tV\t!\u0007\r\u00024uA\u0019AG\u000e\u001d\u000e\u0003UR!!\u0006\r\n\u0005]*$a\u0001*E\tB\u0011\u0011H\u000f\u0007\u0001\t%YT!!A\u0001\u0002\u000b\u0005\u0011IA\u0002`IE\nQ\u0002\u001d:fmN#\u0018\r^3S\t\u0012\u0003\u0003FA\u0003?!\t\u0001s(\u0003\u0002AC\tIAO]1og&,g\u000e^\t\u0003\u0005\u0016\u0003\"\u0001I\"\n\u0005\u0011\u000b#a\u0002(pi\"Lgn\u001a\t\u0003A\u0019K!aR\u0011\u0003\u0007\u0005s\u00170\u0001\tqe\u001648\u000b^1uKJ#Ei\u0018\u0013fcR\u0011!*\u0014\t\u0003A-K!\u0001T\u0011\u0003\tUs\u0017\u000e\u001e\u0005\b\u001d\u0012\t\t\u00111\u00013\u0003\rAH%M\u0001\u0013a\u0006\u0014H/\u001b;j_:,G\rR1uCJ#E)F\u0001Ra\t\u0011F\u000bE\u00025mM\u0003\"!\u000f+\u0005\u0013UC\u0011\u0011!A\u0001\u0006\u0003\t%aA0%e\u0005\u0019\u0002/\u0019:uSRLwN\\3e\t\u0006$\u0018M\u0015#EA!\u0012\u0001BP\u0001\u0017a\u0006\u0014H/\u001b;j_:,G\rR1uCJ#Ei\u0018\u0013fcR\u0011!J\u0017\u0005\b\u001d\u001e\t\t\u00111\u0001R\u0003\u0019a\u0014N\\5u}Q!Ql\u00181f!\tq\u0006!D\u0001\u0015\u0011\u0015I\u0013\u00021\u0001-\u0011\u0015\u0001\u0014\u00021\u0001ba\t\u0011G\rE\u00025m\r\u0004\"!\u000f3\u0005\u0013m\u0002\u0017\u0011!A\u0001\u0006\u0003\t\u0005\"B(\n\u0001\u00041\u0007GA4j!\r!d\u0007\u001b\t\u0003s%$\u0011\"V3\u0002\u0002\u0003\u0005)\u0011A!\u00027A\u0014XM^5pkN\u001cVm]:j_:\u0014F\t\u0012)beRLG/[8o+\u0005)\u0013a\b9sKZLw.^:TKN\u001c\u0018n\u001c8S\t\u0012\u0003\u0016M\u001d;ji&|gn\u0018\u0013fcR\u0011!J\u001c\u0005\b\u001d.\t\t\u00111\u0001&\u0003q\u0001(/\u001a<j_V\u001c8+Z:tS>t'\u000b\u0012#QCJ$\u0018\u000e^5p]\u0002\n1\u0004]1si&$\u0018n\u001c8fI\u0012\u000bG/\u0019*E\tB\u000b'\u000f^5uS>t\u0017a\b9beRLG/[8oK\u0012$\u0015\r^1S\t\u0012\u0003\u0016M\u001d;ji&|gn\u0018\u0013fcR\u0011!j\u001d\u0005\b\u001d:\t\t\u00111\u0001&\u0003q\u0001\u0018M\u001d;ji&|g.\u001a3ECR\f'\u000b\u0012#QCJ$\u0018\u000e^5p]\u0002\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002Y\u00051Q-];bYN$\"!\u001f?\u0011\u0005\u0001R\u0018BA>\"\u0005\u001d\u0011un\u001c7fC:DQ!`\tA\u0002\u0015\u000bQa\u001c;iKJ\f1b\u001e:ji\u0016|%M[3diR\u0019!*!\u0001\t\u000f\u0005\r!\u00031\u0001\u0002\u0006\u0005\u0019qn\\:\u0011\t\u0005\u001d\u0011\u0011C\u0007\u0003\u0003\u0013QA!a\u0003\u0002\u000e\u0005\u0011\u0011n\u001c\u0006\u0003\u0003\u001f\tAA[1wC&!\u00111CA\u0005\u0005Iy%M[3di>+H\u000f];u'R\u0014X-Y7)\u000bI\t9\"a\t\u0011\u000b\u0001\nI\"!\b\n\u0007\u0005m\u0011E\u0001\u0004uQJ|wo\u001d\t\u0005\u0003\u000f\ty\"\u0003\u0003\u0002\"\u0005%!aC%P\u000bb\u001cW\r\u001d;j_:\u001c#!!\b"
)
public class MapWithStateRDDPartition implements Partition {
   private final int index;
   private transient RDD prevStateRDD;
   private transient RDD partitionedDataRDD;
   private Partition previousSessionRDDPartition;
   private Partition partitionedDataRDDPartition;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int index() {
      return this.index;
   }

   private RDD prevStateRDD() {
      return this.prevStateRDD;
   }

   private void prevStateRDD_$eq(final RDD x$1) {
      this.prevStateRDD = x$1;
   }

   private RDD partitionedDataRDD() {
      return this.partitionedDataRDD;
   }

   private void partitionedDataRDD_$eq(final RDD x$1) {
      this.partitionedDataRDD = x$1;
   }

   public Partition previousSessionRDDPartition() {
      return this.previousSessionRDDPartition;
   }

   public void previousSessionRDDPartition_$eq(final Partition x$1) {
      this.previousSessionRDDPartition = x$1;
   }

   public Partition partitionedDataRDDPartition() {
      return this.partitionedDataRDDPartition;
   }

   public void partitionedDataRDDPartition_$eq(final Partition x$1) {
      this.partitionedDataRDDPartition = x$1;
   }

   public int hashCode() {
      return this.index();
   }

   public boolean equals(final Object other) {
      if (other instanceof MapWithStateRDDPartition var4) {
         return this.index() == var4.index();
      } else {
         return false;
      }
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      .MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.previousSessionRDDPartition_$eq(this.prevStateRDD().partitions()[this.index()]);
         this.partitionedDataRDDPartition_$eq(this.partitionedDataRDD().partitions()[this.index()]);
         oos.defaultWriteObject();
      });
   }

   public MapWithStateRDDPartition(final int index, final RDD prevStateRDD, final RDD partitionedDataRDD) {
      this.index = index;
      this.prevStateRDD = prevStateRDD;
      this.partitionedDataRDD = partitionedDataRDD;
      super();
      Partition.$init$(this);
      this.previousSessionRDDPartition = null;
      this.partitionedDataRDDPartition = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
