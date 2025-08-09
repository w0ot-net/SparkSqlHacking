package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Array.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EbA\u0002\u000b\u0016\u0003\u00039R\u0004\u0003\u0005\u0017\u0001\t\u0015\r\u0011\"\u00033\u0011!\u0011\u0005A!A!\u0002\u0013\u0019\u0004\u0002C$\u0001\u0005\u0007\u0005\u000b1\u0002%\t\u000b9\u0003A\u0011A(\t\u000fQ\u0003\u0001\u0019!C\t+\"9a\f\u0001a\u0001\n#y\u0006BB3\u0001A\u0003&a\u000bC\u0004g\u0001\u0001\u0007I\u0011B4\t\u000f9\u0004\u0001\u0019!C\u0005_\"1\u0011\u000f\u0001Q!\n!DQA\u001d\u0001\u0005\u0002MDQa\u001e\u0001\u0005\u0006aDQ!\u001f\u0001\u0007\u0012iDQa\u001f\u0001\u0005\u0002\u001dDQ\u0001 \u0001\u0005\u0002u<\u0001\"a\u0003\u0016\u0011\u00039\u0012Q\u0002\u0004\b)UA\taFA\b\u0011\u0019q\u0015\u0003\"\u0001\u0002 !I\u0011\u0011E\t\u0002\u0002\u0013%\u00111\u0005\u0002\u0012%\u0012#5\t[3dWB|\u0017N\u001c;ECR\f'B\u0001\f\u0018\u0003\r\u0011H\r\u001a\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sOV\u0011a$O\n\u0004\u0001})\u0003C\u0001\u0011$\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#AB!osJ+g\r\u0005\u0002'_9\u0011q%\f\b\u0003Q1j\u0011!\u000b\u0006\u0003U-\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002E%\u0011a&I\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0014G\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002/CU\t1\u0007E\u00025k]j\u0011!F\u0005\u0003mU\u00111A\u0015#E!\tA\u0014\b\u0004\u0001\u0005\u000bi\u0002!\u0019A\u001e\u0003\u0003Q\u000b\"\u0001P \u0011\u0005\u0001j\u0014B\u0001 \"\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\t!\n\u0005\u0005\u000b#aA!os\u0006!!\u000f\u001a3!Q\t\u0011A\t\u0005\u0002!\u000b&\u0011a)\t\u0002\niJ\fgn]5f]R\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rIEjN\u0007\u0002\u0015*\u00111*I\u0001\be\u00164G.Z2u\u0013\ti%J\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q\u0011\u0001k\u0015\u000b\u0003#J\u00032\u0001\u000e\u00018\u0011\u00159E\u0001q\u0001I\u0011\u00151B\u00011\u00014\u0003\u001d\u0019\u0007o\u0015;bi\u0016,\u0012A\u0016\t\u0003/js!\u0001\u000e-\n\u0005e+\u0012aD\"iK\u000e\\\u0007o\\5oiN#\u0018\r^3\n\u0005mc&!\u0002,bYV,\u0017BA/\"\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002\u0017\r\u00048\u000b^1uK~#S-\u001d\u000b\u0003A\u000e\u0004\"\u0001I1\n\u0005\t\f#\u0001B+oSRDq\u0001\u001a\u0004\u0002\u0002\u0003\u0007a+A\u0002yIE\n\u0001b\u00199Ti\u0006$X\rI\u0001\u0006GB\u0014F\tR\u000b\u0002QB\u0019\u0001%[6\n\u0005)\f#AB(qi&|g\u000eE\u00025Y^J!!\\\u000b\u0003\u001b\rCWmY6q_&tGO\u0015#E\u0003%\u0019\u0007O\u0015#E?\u0012*\u0017\u000f\u0006\u0002aa\"9A-CA\u0001\u0002\u0004A\u0017AB2q%\u0012#\u0005%\u0001\bjg\u000eCWmY6q_&tG/\u001a3\u0016\u0003Q\u0004\"\u0001I;\n\u0005Y\f#a\u0002\"p_2,\u0017M\\\u0001\u000bG\",7m\u001b9pS:$H#\u00011\u0002\u0019\u0011|7\t[3dWB|\u0017N\u001c;\u0015\u0003-\fQb\u00195fG.\u0004x.\u001b8u%\u0012#\u0015!D4fiB\u000b'\u000f^5uS>t7/F\u0001\u007f!\u0011\u0001s0a\u0001\n\u0007\u0005\u0005\u0011EA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002\u0006\u0005\u001dQ\"A\f\n\u0007\u0005%qCA\u0005QCJ$\u0018\u000e^5p]\u0006\t\"\u000b\u0012#DQ\u0016\u001c7\u000e]8j]R$\u0015\r^1\u0011\u0005Q\n2\u0003B\t \u0003#\u0001B!a\u0005\u0002\u001e5\u0011\u0011Q\u0003\u0006\u0005\u0003/\tI\"\u0001\u0002j_*\u0011\u00111D\u0001\u0005U\u00064\u0018-C\u00021\u0003+!\"!!\u0004\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\u0002\u0003BA\u0014\u0003[i!!!\u000b\u000b\t\u0005-\u0012\u0011D\u0001\u0005Y\u0006tw-\u0003\u0003\u00020\u0005%\"AB(cU\u0016\u001cG\u000f"
)
public abstract class RDDCheckpointData implements Serializable {
   private final transient RDD rdd;
   private Enumeration.Value cpState;
   private Option cpRDD;

   private RDD rdd() {
      return this.rdd;
   }

   public Enumeration.Value cpState() {
      return this.cpState;
   }

   public void cpState_$eq(final Enumeration.Value x$1) {
      this.cpState = x$1;
   }

   private Option cpRDD() {
      return this.cpRDD;
   }

   private void cpRDD_$eq(final Option x$1) {
      this.cpRDD = x$1;
   }

   public boolean isCheckpointed() {
      synchronized(RDDCheckpointData$.MODULE$){}

      boolean var2;
      try {
         boolean var6;
         label47: {
            label46: {
               Enumeration.Value var10000 = this.cpState();
               Enumeration.Value var3 = CheckpointState$.MODULE$.Checkpointed();
               if (var10000 == null) {
                  if (var3 == null) {
                     break label46;
                  }
               } else if (var10000.equals(var3)) {
                  break label46;
               }

               var6 = false;
               break label47;
            }

            var6 = true;
         }

         var2 = var6;
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   public final void checkpoint() {
      synchronized(RDDCheckpointData$.MODULE$){}

      try {
         Enumeration.Value var10000 = this.cpState();
         Enumeration.Value var2 = CheckpointState$.MODULE$.Initialized();
         if (var10000 == null) {
            if (var2 != null) {
               return;
            }
         } else if (!var10000.equals(var2)) {
            return;
         }

         this.cpState_$eq(CheckpointState$.MODULE$.CheckpointingInProgress());
      } catch (Throwable var10) {
         throw var10;
      }

      CheckpointRDD newRDD = this.doCheckpoint();
      synchronized(RDDCheckpointData$.MODULE$){}

      try {
         this.cpRDD_$eq(new Some(newRDD));
         this.cpState_$eq(CheckpointState$.MODULE$.Checkpointed());
         this.rdd().markCheckpointed();
      } catch (Throwable var9) {
         throw var9;
      }

   }

   public abstract CheckpointRDD doCheckpoint();

   public Option checkpointRDD() {
      synchronized(RDDCheckpointData$.MODULE$){}

      Option var2;
      try {
         var2 = this.cpRDD();
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public Partition[] getPartitions() {
      synchronized(RDDCheckpointData$.MODULE$){}

      Partition[] var2;
      try {
         var2 = (Partition[])this.cpRDD().map((x$1) -> x$1.partitions()).getOrElse(() -> (Partition[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Partition.class)));
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public RDDCheckpointData(final RDD rdd, final ClassTag evidence$1) {
      this.rdd = rdd;
      this.cpState = CheckpointState$.MODULE$.Initialized();
      this.cpRDD = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
