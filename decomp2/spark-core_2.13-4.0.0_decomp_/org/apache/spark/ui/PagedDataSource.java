package org.apache.spark.ui;

import scala.MatchError;
import scala.Tuple2;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\r3aa\u0002\u0005\u0002\u0002)\u0001\u0002\u0002\u0003\r\u0001\u0005\u000b\u0007I\u0011\u0001\u000e\t\u0011y\u0001!\u0011!Q\u0001\nmAQa\b\u0001\u0005\u0002\u0001BQa\f\u0001\u0007\u0012iAQ\u0001\r\u0001\u0007\u0012EBQ\u0001\u0010\u0001\u0005\u0002u\u0012q\u0002U1hK\u0012$\u0015\r^1T_V\u00148-\u001a\u0006\u0003\u0013)\t!!^5\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e,\"!E\u0013\u0014\u0005\u0001\u0011\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g-\u0001\u0005qC\u001e,7+\u001b>f\u0007\u0001)\u0012a\u0007\t\u0003'qI!!\b\u000b\u0003\u0007%sG/A\u0005qC\u001e,7+\u001b>fA\u00051A(\u001b8jiz\"\"!\t\u0018\u0011\u0007\t\u00021%D\u0001\t!\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u0002!\u0019A\u0014\u0003\u0003Q\u000b\"\u0001K\u0016\u0011\u0005MI\u0013B\u0001\u0016\u0015\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0005\u0017\n\u00055\"\"aA!os\")\u0001d\u0001a\u00017\u0005AA-\u0019;b'&TX-A\u0005tY&\u001cW\rR1uCR\u0019!\u0007\u000f\u001e\u0011\u0007M24%D\u00015\u0015\t)D#\u0001\u0006d_2dWm\u0019;j_:L!a\u000e\u001b\u0003\u0007M+\u0017\u000fC\u0003:\u000b\u0001\u00071$\u0001\u0003ge>l\u0007\"B\u001e\u0006\u0001\u0004Y\u0012A\u0001;p\u0003!\u0001\u0018mZ3ECR\fGC\u0001 B!\r\u0011shI\u0005\u0003\u0001\"\u0011\u0001\u0002U1hK\u0012\u000bG/\u0019\u0005\u0006\u0005\u001a\u0001\raG\u0001\u0005a\u0006<W\r"
)
public abstract class PagedDataSource {
   private final int pageSize;

   public int pageSize() {
      return this.pageSize;
   }

   public abstract int dataSize();

   public abstract Seq sliceData(final int from, final int to);

   public PageData pageData(final int page) {
      int pageTableSize = this.pageSize() <= 0 ? this.dataSize() : this.pageSize();
      int totalPages = (this.dataSize() + pageTableSize - 1) / pageTableSize;
      int pageToShow = page <= 0 ? 1 : (page > totalPages ? totalPages : page);
      Tuple2.mcII.sp var7 = new Tuple2.mcII.sp((pageToShow - 1) * this.pageSize(), .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.dataSize()), pageToShow * pageTableSize));
      if (var7 != null) {
         int from = ((Tuple2)var7)._1$mcI$sp();
         int to = ((Tuple2)var7)._2$mcI$sp();
         Tuple2.mcII.sp var6 = new Tuple2.mcII.sp(from, to);
         int from = ((Tuple2)var6)._1$mcI$sp();
         int to = ((Tuple2)var6)._2$mcI$sp();
         return new PageData(totalPages, this.sliceData(from, to));
      } else {
         throw new MatchError(var7);
      }
   }

   public PagedDataSource(final int pageSize) {
      this.pageSize = pageSize;
   }
}
