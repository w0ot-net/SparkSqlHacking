package org.apache.spark.graphx.util;

import org.apache.spark.SparkContext;
import org.apache.spark.graphx.Graph;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StorageLevel.;
import org.apache.spark.util.PeriodicCheckpointer;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u000514Q!\u0003\u0006\u0001\u001dQA\u0011\"\r\u0001\u0003\u0002\u0003\u0006IAM\u001b\t\u0013Y\u0002!\u0011!Q\u0001\n]Z\u0004\"\u0002\u001f\u0001\t\u0003i\u0004\"\u0002\"\u0001\t#\u001a\u0005\"B%\u0001\t#R\u0005\"B(\u0001\t#\u0002\u0006\"\u0002*\u0001\t#\u001a\u0006\"B+\u0001\t#2&!\u0007)fe&|G-[2He\u0006\u0004\bn\u00115fG.\u0004x.\u001b8uKJT!a\u0003\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001b9\taa\u001a:ba\"D(BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0016\u0007U\tsf\u0005\u0002\u0001-A\u0019q#G\u000e\u000e\u0003aQ!a\u0003\b\n\u0005iA\"\u0001\u0006)fe&|G-[2DQ\u0016\u001c7\u000e]8j]R,'\u000f\u0005\u0003\u001d;}qS\"\u0001\u0007\n\u0005ya!!B$sCBD\u0007C\u0001\u0011\"\u0019\u0001!QA\t\u0001C\u0002\u0011\u0012!A\u0016#\u0004\u0001E\u0011Qe\u000b\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\b\u001d>$\b.\u001b8h!\t1C&\u0003\u0002.O\t\u0019\u0011I\\=\u0011\u0005\u0001zC!\u0002\u0019\u0001\u0005\u0004!#AA#E\u0003I\u0019\u0007.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197\u0011\u0005\u0019\u001a\u0014B\u0001\u001b(\u0005\rIe\u000e^\u0005\u0003ce\t!a]2\u0011\u0005aJT\"\u0001\b\n\u0005ir!\u0001D*qCJ\\7i\u001c8uKb$\u0018B\u0001\u001c\u001a\u0003\u0019a\u0014N\\5u}Q\u0019a\bQ!\u0011\t}\u0002qDL\u0007\u0002\u0015!)\u0011g\u0001a\u0001e!)ag\u0001a\u0001o\u0005Q1\r[3dWB|\u0017N\u001c;\u0015\u0005\u0011;\u0005C\u0001\u0014F\u0013\t1uE\u0001\u0003V]&$\b\"\u0002%\u0005\u0001\u0004Y\u0012\u0001\u00023bi\u0006\fa\"[:DQ\u0016\u001c7\u000e]8j]R,G\r\u0006\u0002L\u001dB\u0011a\u0005T\u0005\u0003\u001b\u001e\u0012qAQ8pY\u0016\fg\u000eC\u0003I\u000b\u0001\u00071$A\u0004qKJ\u001c\u0018n\u001d;\u0015\u0005\u0011\u000b\u0006\"\u0002%\u0007\u0001\u0004Y\u0012!C;oa\u0016\u00148/[:u)\t!E\u000bC\u0003I\u000f\u0001\u00071$\u0001\nhKR\u001c\u0005.Z2la>Lg\u000e\u001e$jY\u0016\u001cHCA,l!\rA\u0006m\u0019\b\u00033zs!AW/\u000e\u0003mS!\u0001X\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013BA0(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u00192\u0003\u0011%#XM]1cY\u0016T!aX\u0014\u0011\u0005\u0011DgBA3g!\tQv%\u0003\u0002hO\u00051\u0001K]3eK\u001aL!!\u001b6\u0003\rM#(/\u001b8h\u0015\t9w\u0005C\u0003I\u0011\u0001\u00071\u0004"
)
public class PeriodicGraphCheckpointer extends PeriodicCheckpointer {
   public void checkpoint(final Graph data) {
      data.checkpoint();
   }

   public boolean isCheckpointed(final Graph data) {
      return data.isCheckpointed();
   }

   public void persist(final Graph data) {
      label29: {
         label28: {
            StorageLevel var10000 = data.vertices().getStorageLevel();
            StorageLevel var2 = .MODULE$.NONE();
            if (var10000 == null) {
               if (var2 == null) {
                  break label28;
               }
            } else if (var10000.equals(var2)) {
               break label28;
            }

            BoxedUnit var4 = BoxedUnit.UNIT;
            break label29;
         }

         data.vertices().cache();
      }

      label21: {
         StorageLevel var5 = data.edges().getStorageLevel();
         StorageLevel var3 = .MODULE$.NONE();
         if (var5 == null) {
            if (var3 == null) {
               break label21;
            }
         } else if (var5.equals(var3)) {
            break label21;
         }

         return;
      }

      data.edges().cache();
   }

   public void unpersist(final Graph data) {
      data.unpersist(data.unpersist$default$1());
   }

   public Iterable getCheckpointFiles(final Graph data) {
      return data.getCheckpointFiles();
   }

   public PeriodicGraphCheckpointer(final int checkpointInterval, final SparkContext sc) {
      super(checkpointInterval, sc);
   }
}
