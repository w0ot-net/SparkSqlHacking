package scala.collection.parallel.mutable;

import scala.reflect.ScalaSignature;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005A2\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0004\u0005\u0006'\u0001!\t!\u0006\u0005\u00063\u0001!\tB\u0007\u0005\u0006M\u00011\tb\n\u0005\u0006U\u00011\tb\u000b\u0002\r'&TX-T1q+RLGn\u001d\u0006\u0003\u000f!\tq!\\;uC\ndWM\u0003\u0002\n\u0015\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\f\u0019\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00035\tQa]2bY\u0006\u001c\"\u0001A\b\u0011\u0005A\tR\"\u0001\u0007\n\u0005Ia!AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u00051\u0002C\u0001\t\u0018\u0013\tABB\u0001\u0003V]&$\u0018\u0001D2bY\u000etU/\\#mK6\u001cH#B\u000e\u001fA\t\"\u0003C\u0001\t\u001d\u0013\tiBBA\u0002J]RDQa\b\u0002A\u0002m\tAA\u001a:p[\")\u0011E\u0001a\u00017\u0005)QO\u001c;jY\")1E\u0001a\u00017\u0005YA/\u00192mK2+gn\u001a;i\u0011\u0015)#\u00011\u0001\u001c\u0003E\u0019\u0018N_3NCB\u0014UoY6fiNK'0Z\u0001\u000bG>,h\u000e^#mK6\u001cHcA\u000e)S!)qd\u0001a\u00017!)\u0011e\u0001a\u00017\u0005\u00012m\\;oi\n+8m[3u'&TXm\u001d\u000b\u000471r\u0003\"B\u0017\u0005\u0001\u0004Y\u0012A\u00034s_6\u0014UoY6fi\")q\u0006\u0002a\u00017\u0005YQO\u001c;jY\n+8m[3u\u0001"
)
public interface SizeMapUtils {
   // $FF: synthetic method
   static int calcNumElems$(final SizeMapUtils $this, final int from, final int until, final int tableLength, final int sizeMapBucketSize) {
      return $this.calcNumElems(from, until, tableLength, sizeMapBucketSize);
   }

   default int calcNumElems(final int from, final int until, final int tableLength, final int sizeMapBucketSize) {
      int fbindex = from / sizeMapBucketSize;
      int lbindex = until / sizeMapBucketSize;
      if (fbindex == lbindex) {
         return this.countElems(from, until);
      } else {
         int fbuntil = .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper((fbindex + 1) * sizeMapBucketSize), tableLength);
         int fbcount = this.countElems(from, fbuntil);
         int lbstart = lbindex * sizeMapBucketSize;
         int lbcount = this.countElems(lbstart, until);
         int inbetween = this.countBucketSizes(fbindex + 1, lbindex);
         return fbcount + inbetween + lbcount;
      }
   }

   int countElems(final int from, final int until);

   int countBucketSizes(final int fromBucket, final int untilBucket);

   static void $init$(final SizeMapUtils $this) {
   }
}
