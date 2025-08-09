package org.apache.spark.rdd;

import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.spark.Partition;
import org.apache.spark.SerializableWritable;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e3Qa\u0003\u0007\u0001\u001dQA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tI\u0001\u0011)\u0019!C!K!Aa\u0005\u0001B\u0001B\u0003%\u0011\u0005\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003)\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u001d9\u0004A1A\u0005\u0002aBa\u0001\u0010\u0001!\u0002\u0013I\u0004\"B\u001f\u0001\t\u0003r\u0004\"B \u0001\t\u0003\u0002\u0005\"B%\u0001\t\u0003Q%a\u0004%bI>|\u0007\u000fU1si&$\u0018n\u001c8\u000b\u00055q\u0011a\u0001:eI*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xmE\u0002\u0001+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007C\u0001\u000f\u001e\u001b\u0005q\u0011B\u0001\u0010\u000f\u0005%\u0001\u0016M\u001d;ji&|g.A\u0003sI\u0012LEm\u0001\u0001\u0011\u0005Y\u0011\u0013BA\u0012\u0018\u0005\rIe\u000e^\u0001\u0006S:$W\r_\u000b\u0002C\u00051\u0011N\u001c3fq\u0002\n\u0011a\u001d\t\u0003S9j\u0011A\u000b\u0006\u0003W1\na!\\1qe\u0016$'BA\u0017\u0011\u0003\u0019A\u0017\rZ8pa&\u0011qF\u000b\u0002\u000b\u0013:\u0004X\u000f^*qY&$\u0018A\u0002\u001fj]&$h\b\u0006\u00033iU2\u0004CA\u001a\u0001\u001b\u0005a\u0001\"B\u0010\u0006\u0001\u0004\t\u0003\"\u0002\u0013\u0006\u0001\u0004\t\u0003\"B\u0014\u0006\u0001\u0004A\u0013AC5oaV$8\u000b\u001d7jiV\t\u0011\bE\u0002\u001du!J!a\u000f\b\u0003)M+'/[1mSj\f'\r\\3Xe&$\u0018M\u00197f\u0003-Ig\u000e];u'Bd\u0017\u000e\u001e\u0011\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!I\u0001\u0007KF,\u0018\r\\:\u0015\u0005\u0005#\u0005C\u0001\fC\u0013\t\u0019uCA\u0004C_>dW-\u00198\t\u000b\u0015K\u0001\u0019\u0001$\u0002\u000b=$\b.\u001a:\u0011\u0005Y9\u0015B\u0001%\u0018\u0005\r\te._\u0001\u000fO\u0016$\b+\u001b9f\u000b:4h+\u0019:t)\u0005Y\u0005\u0003\u0002'T-Zs!!T)\u0011\u00059;R\"A(\u000b\u0005A\u0003\u0013A\u0002\u001fs_>$h(\u0003\u0002S/\u00051\u0001K]3eK\u001aL!\u0001V+\u0003\u00075\u000b\u0007O\u0003\u0002S/A\u0011AjV\u0005\u00031V\u0013aa\u0015;sS:<\u0007"
)
public class HadoopPartition implements Partition {
   private final int rddId;
   private final int index;
   private final SerializableWritable inputSplit;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int index() {
      return this.index;
   }

   public SerializableWritable inputSplit() {
      return this.inputSplit;
   }

   public int hashCode() {
      return 31 * (31 + this.rddId) + this.index();
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public Map getPipeEnvVars() {
      InputSplit var3 = (InputSplit)this.inputSplit().value();
      Map var10000;
      if (var3 instanceof FileSplit var4) {
         var10000 = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("map_input_file"), var4.getPath().toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("mapreduce_map_input_file"), var4.getPath().toString())})));
      } else {
         var10000 = (Map).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      }

      Map envVars = var10000;
      return envVars;
   }

   public HadoopPartition(final int rddId, final int index, final InputSplit s) {
      this.rddId = rddId;
      this.index = index;
      Partition.$init$(this);
      this.inputSplit = new SerializableWritable(s);
   }
}
