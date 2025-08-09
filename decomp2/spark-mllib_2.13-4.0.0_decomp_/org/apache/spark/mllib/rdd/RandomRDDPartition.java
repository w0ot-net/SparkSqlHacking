package org.apache.spark.mllib.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.mllib.random.RandomDataGenerator;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513QAC\u0006\u0001\u001bUA\u0001\"\t\u0001\u0003\u0006\u0004%\te\t\u0005\tO\u0001\u0011\t\u0011)A\u0005I!A\u0001\u0006\u0001BC\u0002\u0013\u00051\u0005\u0003\u0005*\u0001\t\u0005\t\u0015!\u0003%\u0011!Q\u0003A!b\u0001\n\u0003Y\u0003\u0002C\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u0011y\u0002!Q1A\u0005\u0002}B\u0001b\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\u0006\t\u0002!\t!\u0012\u0002\u0013%\u0006tGm\\7S\t\u0012\u0003\u0016M\u001d;ji&|gN\u0003\u0002\r\u001b\u0005\u0019!\u000f\u001a3\u000b\u00059y\u0011!B7mY&\u0014'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0016\u0005Y!4c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0010\u000e\u0003=I!\u0001I\b\u0003\u0013A\u000b'\u000f^5uS>t\u0017!B5oI\u0016D8\u0001A\u000b\u0002IA\u0011\u0001$J\u0005\u0003Me\u00111!\u00138u\u0003\u0019Ig\u000eZ3yA\u0005!1/\u001b>f\u0003\u0015\u0019\u0018N_3!\u0003%9WM\\3sCR|'/F\u0001-!\ri\u0003GM\u0007\u0002])\u0011q&D\u0001\u0007e\u0006tGm\\7\n\u0005Er#a\u0005*b]\u0012|W\u000eR1uC\u001e+g.\u001a:bi>\u0014\bCA\u001a5\u0019\u0001!Q!\u000e\u0001C\u0002Y\u0012\u0011\u0001V\t\u0003oi\u0002\"\u0001\u0007\u001d\n\u0005eJ\"a\u0002(pi\"Lgn\u001a\t\u00031mJ!\u0001P\r\u0003\u0007\u0005s\u00170\u0001\u0006hK:,'/\u0019;pe\u0002\nAa]3fIV\t\u0001\t\u0005\u0002\u0019\u0003&\u0011!)\u0007\u0002\u0005\u0019>tw-A\u0003tK\u0016$\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006\r\"K%j\u0013\t\u0004\u000f\u0002\u0011T\"A\u0006\t\u000b\u0005J\u0001\u0019\u0001\u0013\t\u000b!J\u0001\u0019\u0001\u0013\t\u000b)J\u0001\u0019\u0001\u0017\t\u000byJ\u0001\u0019\u0001!"
)
public class RandomRDDPartition implements Partition {
   private final int index;
   private final int size;
   private final RandomDataGenerator generator;
   private final long seed;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int hashCode() {
      return Partition.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public int index() {
      return this.index;
   }

   public int size() {
      return this.size;
   }

   public RandomDataGenerator generator() {
      return this.generator;
   }

   public long seed() {
      return this.seed;
   }

   public RandomRDDPartition(final int index, final int size, final RandomDataGenerator generator, final long seed) {
      this.index = index;
      this.size = size;
      this.generator = generator;
      this.seed = seed;
      Partition.$init$(this);
      .MODULE$.require(size >= 0, () -> "Non-negative partition size required.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
