package org.apache.spark.storage.memory;

import java.io.Serializable;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mg\u0001B\u000f\u001f\t&B\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t3\u0002\u0011\t\u0012)A\u0005#\"A!\f\u0001BK\u0002\u0013\u00051\f\u0003\u0005b\u0001\tE\t\u0015!\u0003]\u0011!\u0011\u0007A!f\u0001\n\u0003\u0019\u0007\u0002\u00036\u0001\u0005#\u0005\u000b\u0011\u00023\t\u000b-\u0004A\u0011\u00017\t\u000bE\u0004A\u0011\u0001:\t\u000fY\u0004\u0011\u0011!C\u0001o\"I\u0011\u0011\u0001\u0001\u0012\u0002\u0013\u0005\u00111\u0001\u0005\n\u0003;\u0001\u0011\u0013!C\u0001\u0003?A\u0011\"a\n\u0001#\u0003%\t!!\u000b\t\u0013\u0005E\u0002!!A\u0005B\u0005M\u0002\"CA#\u0001\u0005\u0005I\u0011AA$\u0011%\ty\u0005AA\u0001\n\u0003\t\t\u0006C\u0005\u0002X\u0001\t\t\u0011\"\u0011\u0002Z!I\u0011q\r\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u000e\u0005\n\u0003g\u0002\u0011\u0011!C!\u0003kB\u0011\"!\u001f\u0001\u0003\u0003%\t%a\u001f\t\u0013\u0005u\u0004!!A\u0005B\u0005}\u0004\"CAA\u0001\u0005\u0005I\u0011IAB\u000f%\t9IHA\u0001\u0012\u0013\tII\u0002\u0005\u001e=\u0005\u0005\t\u0012BAF\u0011\u0019Yw\u0003\"\u0001\u0002\u0016\"I\u0011QP\f\u0002\u0002\u0013\u0015\u0013q\u0010\u0005\n\u0003/;\u0012\u0011!CA\u00033C\u0011\"a+\u0018\u0003\u0003%\t)!,\t\u0013\u0005%w#!A\u0005\n\u0005-'!F*fe&\fG.\u001b>fI6+Wn\u001c:z\u000b:$(/\u001f\u0006\u0003?\u0001\na!\\3n_JL(BA\u0011#\u0003\u001d\u0019Ho\u001c:bO\u0016T!a\t\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u00152\u0013AB1qC\u000eDWMC\u0001(\u0003\ry'oZ\u0002\u0001+\tQsgE\u0003\u0001WE\u00025\t\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TF\u0001\u0004B]f\u0014VM\u001a\t\u0004eM*T\"\u0001\u0010\n\u0005Qr\"aC'f[>\u0014\u00180\u00128uef\u0004\"AN\u001c\r\u0001\u0011)\u0001\b\u0001b\u0001s\t\tA+\u0005\u0002;{A\u0011AfO\u0005\u0003y5\u0012qAT8uQ&tw\r\u0005\u0002-}%\u0011q(\f\u0002\u0004\u0003:L\bC\u0001\u0017B\u0013\t\u0011UFA\u0004Qe>$Wo\u0019;\u0011\u0005\u0011ceBA#K\u001d\t1\u0015*D\u0001H\u0015\tA\u0005&\u0001\u0004=e>|GOP\u0005\u0002]%\u00111*L\u0001\ba\u0006\u001c7.Y4f\u0013\tieJ\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002L[\u00051!-\u001e4gKJ,\u0012!\u0015\t\u0003%^k\u0011a\u0015\u0006\u0003)V\u000b!![8\u000b\u0005Y\u0013\u0013\u0001B;uS2L!\u0001W*\u0003#\rCWO\\6fI\nKH/\u001a\"vM\u001a,'/A\u0004ck\u001a4WM\u001d\u0011\u0002\u00155,Wn\u001c:z\u001b>$W-F\u0001]!\tiv,D\u0001_\u0015\ty\"%\u0003\u0002a=\nQQ*Z7peflu\u000eZ3\u0002\u00175,Wn\u001c:z\u001b>$W\rI\u0001\tG2\f7o\u001d+bOV\tA\rE\u0002fQVj\u0011A\u001a\u0006\u0003O6\nqA]3gY\u0016\u001cG/\u0003\u0002jM\nA1\t\\1tgR\u000bw-A\u0005dY\u0006\u001c8\u000fV1hA\u00051A(\u001b8jiz\"B!\u001c8paB\u0019!\u0007A\u001b\t\u000b=;\u0001\u0019A)\t\u000bi;\u0001\u0019\u0001/\t\u000b\t<\u0001\u0019\u00013\u0002\tML'0Z\u000b\u0002gB\u0011A\u0006^\u0005\u0003k6\u0012A\u0001T8oO\u0006!1m\u001c9z+\tA8\u0010\u0006\u0003zyvt\bc\u0001\u001a\u0001uB\u0011ag\u001f\u0003\u0006q%\u0011\r!\u000f\u0005\b\u001f&\u0001\n\u00111\u0001R\u0011\u001dQ\u0016\u0002%AA\u0002qCqAY\u0005\u0011\u0002\u0003\u0007q\u0010E\u0002fQj\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0002\u0006\u0005mQCAA\u0004U\r\t\u0016\u0011B\u0016\u0003\u0003\u0017\u0001B!!\u0004\u0002\u00185\u0011\u0011q\u0002\u0006\u0005\u0003#\t\u0019\"A\u0005v]\u000eDWmY6fI*\u0019\u0011QC\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u001a\u0005=!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)\u0001H\u0003b\u0001s\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T\u0003BA\u0011\u0003K)\"!a\t+\u0007q\u000bI\u0001B\u00039\u0017\t\u0007\u0011(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005-\u0012qF\u000b\u0003\u0003[Q3\u0001ZA\u0005\t\u0015ADB1\u0001:\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0007\t\u0005\u0003o\t\t%\u0004\u0002\u0002:)!\u00111HA\u001f\u0003\u0011a\u0017M\\4\u000b\u0005\u0005}\u0012\u0001\u00026bm\u0006LA!a\u0011\u0002:\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0013\u0011\u00071\nY%C\u0002\u0002N5\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2!PA*\u0011%\t)fDA\u0001\u0002\u0004\tI%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00037\u0002R!!\u0018\u0002duj!!a\u0018\u000b\u0007\u0005\u0005T&\u0001\u0006d_2dWm\u0019;j_:LA!!\u001a\u0002`\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY'!\u001d\u0011\u00071\ni'C\u0002\u0002p5\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002VE\t\t\u00111\u0001>\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005U\u0012q\u000f\u0005\n\u0003+\u0012\u0012\u0011!a\u0001\u0003\u0013\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0013\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003k\ta!Z9vC2\u001cH\u0003BA6\u0003\u000bC\u0001\"!\u0016\u0016\u0003\u0003\u0005\r!P\u0001\u0016'\u0016\u0014\u0018.\u00197ju\u0016$W*Z7pef,e\u000e\u001e:z!\t\u0011tc\u0005\u0003\u0018W\u00055\u0005\u0003BAH\u0003'k!!!%\u000b\u0007Q\u000bi$C\u0002N\u0003##\"!!#\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005m\u0015\u0011\u0015\u000b\t\u0003;\u000b\u0019+!*\u0002(B!!\u0007AAP!\r1\u0014\u0011\u0015\u0003\u0006qi\u0011\r!\u000f\u0005\u0006\u001fj\u0001\r!\u0015\u0005\u00065j\u0001\r\u0001\u0018\u0005\u0007Ej\u0001\r!!+\u0011\t\u0015D\u0017qT\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\ty+!1\u0015\t\u0005E\u00161\u0019\t\u0006Y\u0005M\u0016qW\u0005\u0004\u0003kk#AB(qi&|g\u000eE\u0004-\u0003s\u000bF,!0\n\u0007\u0005mVF\u0001\u0004UkBdWm\r\t\u0005K\"\fy\fE\u00027\u0003\u0003$Q\u0001O\u000eC\u0002eB\u0011\"!2\u001c\u0003\u0003\u0005\r!a2\u0002\u0007a$\u0003\u0007\u0005\u00033\u0001\u0005}\u0016\u0001D<sSR,'+\u001a9mC\u000e,GCAAg!\u0011\t9$a4\n\t\u0005E\u0017\u0011\b\u0002\u0007\u001f\nTWm\u0019;"
)
public class SerializedMemoryEntry implements MemoryEntry, Product, Serializable {
   private final ChunkedByteBuffer buffer;
   private final MemoryMode memoryMode;
   private final ClassTag classTag;

   public static Option unapply(final SerializedMemoryEntry x$0) {
      return SerializedMemoryEntry$.MODULE$.unapply(x$0);
   }

   public static SerializedMemoryEntry apply(final ChunkedByteBuffer buffer, final MemoryMode memoryMode, final ClassTag classTag) {
      return SerializedMemoryEntry$.MODULE$.apply(buffer, memoryMode, classTag);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ChunkedByteBuffer buffer() {
      return this.buffer;
   }

   public MemoryMode memoryMode() {
      return this.memoryMode;
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   public long size() {
      return this.buffer().size();
   }

   public SerializedMemoryEntry copy(final ChunkedByteBuffer buffer, final MemoryMode memoryMode, final ClassTag classTag) {
      return new SerializedMemoryEntry(buffer, memoryMode, classTag);
   }

   public ChunkedByteBuffer copy$default$1() {
      return this.buffer();
   }

   public MemoryMode copy$default$2() {
      return this.memoryMode();
   }

   public ClassTag copy$default$3() {
      return this.classTag();
   }

   public String productPrefix() {
      return "SerializedMemoryEntry";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.buffer();
         }
         case 1 -> {
            return this.memoryMode();
         }
         case 2 -> {
            return this.classTag();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof SerializedMemoryEntry;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "buffer";
         }
         case 1 -> {
            return "memoryMode";
         }
         case 2 -> {
            return "classTag";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof SerializedMemoryEntry) {
               label56: {
                  SerializedMemoryEntry var4 = (SerializedMemoryEntry)x$1;
                  ChunkedByteBuffer var10000 = this.buffer();
                  ChunkedByteBuffer var5 = var4.buffer();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  MemoryMode var8 = this.memoryMode();
                  MemoryMode var6 = var4.memoryMode();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  ClassTag var9 = this.classTag();
                  ClassTag var7 = var4.classTag();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public SerializedMemoryEntry(final ChunkedByteBuffer buffer, final MemoryMode memoryMode, final ClassTag classTag) {
      this.buffer = buffer;
      this.memoryMode = memoryMode;
      this.classTag = classTag;
      Product.$init$(this);
   }
}
