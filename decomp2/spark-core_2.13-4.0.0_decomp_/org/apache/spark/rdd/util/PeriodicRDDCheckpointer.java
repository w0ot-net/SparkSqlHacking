package org.apache.spark.rdd.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StorageLevel.;
import org.apache.spark.util.PeriodicCheckpointer;
import scala.Predef;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4Qa\u0003\u0007\u0001!YA\u0011\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u001b\t\u0013U\u0002!\u0011!Q\u0001\nYR\u0004\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u000b\t\u0003A\u0011A\"\t\u000b\t\u0003A\u0011A%\t\u000b1\u0003A\u0011K'\t\u000bM\u0003A\u0011\u000b+\t\u000be\u0003A\u0011\u000b.\t\u000bq\u0003A\u0011K/\t\u000b}\u0003A\u0011\u000b1\u0003/A+'/[8eS\u000e\u0014F\tR\"iK\u000e\\\u0007o\\5oi\u0016\u0014(BA\u0007\u000f\u0003\u0011)H/\u001b7\u000b\u0005=\u0001\u0012a\u0001:eI*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014x-\u0006\u0002\u0018GM\u0011\u0001\u0001\u0007\t\u00043miR\"\u0001\u000e\u000b\u00055\u0001\u0012B\u0001\u000f\u001b\u0005Q\u0001VM]5pI&\u001c7\t[3dWB|\u0017N\u001c;feB\u0019adH\u0011\u000e\u00039I!\u0001\t\b\u0003\u0007I#E\t\u0005\u0002#G1\u0001A!\u0002\u0013\u0001\u0005\u00041#!\u0001+\u0004\u0001E\u0011q%\f\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\b\u001d>$\b.\u001b8h!\tAc&\u0003\u00020S\t\u0019\u0011I\\=\u0002%\rDWmY6q_&tG/\u00138uKJ4\u0018\r\u001c\t\u0003QIJ!aM\u0015\u0003\u0007%sG/\u0003\u000217\u0005\u00111o\u0019\t\u0003oaj\u0011\u0001E\u0005\u0003sA\u0011Ab\u00159be.\u001cuN\u001c;fqRL!!N\u000e\u0002\u0019M$xN]1hK2+g/\u001a7\u0011\u0005u\u0002U\"\u0001 \u000b\u0005}\u0002\u0012aB:u_J\fw-Z\u0005\u0003\u0003z\u0012Ab\u0015;pe\u0006<W\rT3wK2\fa\u0001P5oSRtD\u0003\u0002#G\u000f\"\u00032!\u0012\u0001\"\u001b\u0005a\u0001\"\u0002\u0019\u0005\u0001\u0004\t\u0004\"B\u001b\u0005\u0001\u00041\u0004\"B\u001e\u0005\u0001\u0004aDc\u0001#K\u0017\")\u0001'\u0002a\u0001c!)Q'\u0002a\u0001m\u0005Q1\r[3dWB|\u0017N\u001c;\u0015\u00059\u000b\u0006C\u0001\u0015P\u0013\t\u0001\u0016F\u0001\u0003V]&$\b\"\u0002*\u0007\u0001\u0004i\u0012\u0001\u00023bi\u0006\fa\"[:DQ\u0016\u001c7\u000e]8j]R,G\r\u0006\u0002V1B\u0011\u0001FV\u0005\u0003/&\u0012qAQ8pY\u0016\fg\u000eC\u0003S\u000f\u0001\u0007Q$A\u0004qKJ\u001c\u0018n\u001d;\u0015\u00059[\u0006\"\u0002*\t\u0001\u0004i\u0012!C;oa\u0016\u00148/[:u)\tqe\fC\u0003S\u0013\u0001\u0007Q$\u0001\nhKR\u001c\u0005.Z2la>Lg\u000e\u001e$jY\u0016\u001cHCA1v!\r\u0011'.\u001c\b\u0003G\"t!\u0001Z4\u000e\u0003\u0015T!AZ\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0013BA5*\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u001b7\u0003\u0011%#XM]1cY\u0016T!![\u0015\u0011\u00059\u0014hBA8q!\t!\u0017&\u0003\u0002rS\u00051\u0001K]3eK\u001aL!a\u001d;\u0003\rM#(/\u001b8h\u0015\t\t\u0018\u0006C\u0003S\u0015\u0001\u0007Q\u0004"
)
public class PeriodicRDDCheckpointer extends PeriodicCheckpointer {
   private final StorageLevel storageLevel;

   public void checkpoint(final RDD data) {
      data.checkpoint();
   }

   public boolean isCheckpointed(final RDD data) {
      return data.isCheckpointed();
   }

   public void persist(final RDD data) {
      label14: {
         StorageLevel var10000 = data.getStorageLevel();
         StorageLevel var2 = .MODULE$.NONE();
         if (var10000 == null) {
            if (var2 == null) {
               break label14;
            }
         } else if (var10000.equals(var2)) {
            break label14;
         }

         return;
      }

      data.persist(this.storageLevel);
   }

   public void unpersist(final RDD data) {
      data.unpersist(data.unpersist$default$1());
   }

   public Iterable getCheckpointFiles(final RDD data) {
      return scala.Option..MODULE$.option2Iterable(data.getCheckpointFile().map((x) -> x));
   }

   public PeriodicRDDCheckpointer(final int checkpointInterval, final SparkContext sc, final StorageLevel storageLevel) {
      Predef var10000;
      boolean var10001;
      label17: {
         label16: {
            this.storageLevel = storageLevel;
            super(checkpointInterval, sc);
            var10000 = scala.Predef..MODULE$;
            StorageLevel var4 = .MODULE$.NONE();
            if (storageLevel == null) {
               if (var4 != null) {
                  break label16;
               }
            } else if (!storageLevel.equals(var4)) {
               break label16;
            }

            var10001 = false;
            break label17;
         }

         var10001 = true;
      }

      var10000.require(var10001);
   }

   public PeriodicRDDCheckpointer(final int checkpointInterval, final SparkContext sc) {
      this(checkpointInterval, sc, .MODULE$.MEMORY_ONLY());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
