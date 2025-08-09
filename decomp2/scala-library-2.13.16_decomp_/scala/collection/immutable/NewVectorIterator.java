package scala.collection.immutable;

import java.lang.reflect.Array;
import scala.Array$;
import scala.MatchError;
import scala.collection.AbstractIterator;
import scala.collection.IterableOnce$;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001\u0002\u0011\"\r!B\u0001B\u0011\u0001\u0003\u0002\u0003\u0006Ia\u0011\u0005\t\u000f\u0002\u0011\t\u0011)Q\u0005\u0011\"A1\n\u0001B\u0001B\u0003%\u0001\nC\u0003M\u0001\u0011\u0005Q\n\u0003\u0004S\u0001\u0001\u0006Ka\u0015\u0005\nG\u0002\u0001\r\u0011!Q!\n\u0011D\u0011b\u001a\u0001A\u0002\u0003\u0005\u000b\u0015\u00025\t\u0013-\u0004\u0001\u0019!A!B\u0013a\u0007\"C8\u0001\u0001\u0004\u0005\t\u0015)\u0003q\u0011%\u0019\b\u00011A\u0001B\u0003&A\u000f\u0003\u0004x\u0001\u0001\u0006K\u0001\u0013\u0005\rq\u0002!\t\u0011!B\u0001\u0002\u0003\u0006K\u0001\u0013\u0005\u0007s\u0002\u0001\u000b\u0015\u0002%\t\u0019i\u0004A\u0011!A\u0003\u0002\u0003\u0005\u000b\u0015\u0002%\t\rm\u0004\u0001\u0015)\u0003I\u0011\u0019a\b\u0001)Q\u0005\u0011\"1Q\u0010\u0001Q!\n!CaA \u0001!B\u0013A\u0005BB@\u0001\t\u0003\n\t\u0001C\u0004\u0002\f\u0001!\t!!\u0004\t\u000f\u0005]\u0001\u0001\"\u0001\u0002\u001a!A\u00111\u0004\u0001!\n\u0013\ti\u0002\u0003\u0005\u0002&\u0001\u0001K\u0011BA\u000f\u0011!\t9\u0003\u0001Q\u0005\n\u0005%\u0002\u0002CA\u001a\u0001\u0001&I!!\u000e\t\u000f\u0005m\u0002\u0001\"\u0011\u0002>!9\u0011\u0011\n\u0001\u0005B\u0005-\u0003bBA(\u0001\u0011\u0005\u0013\u0011\u000b\u0005\b\u00037\u0002A\u0011IA/\u0011\u001d\tY\b\u0001C!\u0003{B\u0001\"a \u0001\t#\t\u0013\u0011\u0011\u0002\u0012\u001d\u0016<h+Z2u_JLE/\u001a:bi>\u0014(B\u0001\u0012$\u0003%IW.\\;uC\ndWM\u0003\u0002%K\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u0019\nQa]2bY\u0006\u001c\u0001!\u0006\u0002*aM\u0019\u0001A\u000b\u001e\u0011\u0007-bc&D\u0001$\u0013\ti3E\u0001\tBEN$(/Y2u\u0013R,'/\u0019;peB\u0011q\u0006\r\u0007\u0001\t\u0015\t\u0004A1\u00013\u0005\u0005\t\u0015CA\u001a8!\t!T'D\u0001&\u0013\t1TEA\u0004O_RD\u0017N\\4\u0011\u0005QB\u0014BA\u001d&\u0005\r\te.\u001f\t\u0003w\u0001k\u0011\u0001\u0010\u0006\u0003{y\nA\u0001\\1oO*\tq(\u0001\u0003kCZ\f\u0017BA!=\u0005%\u0019En\u001c8fC\ndW-A\u0001w!\r!UIL\u0007\u0002C%\u0011a)\t\u0002\u0007-\u0016\u001cGo\u001c:\u0002\u0017Q|G/\u00197MK:<G\u000f\u001b\t\u0003i%K!AS\u0013\u0003\u0007%sG/\u0001\u0006tY&\u001cWmQ8v]R\fa\u0001P5oSRtD\u0003\u0002(P!F\u00032\u0001\u0012\u0001/\u0011\u0015\u0011E\u00011\u0001D\u0011\u00159E\u00011\u0001I\u0011\u0015YE\u00011\u0001I\u0003\t\t\u0017\u0007\u0005\u0002UA:\u0011QK\u0018\b\u0003-vs!a\u0016/\u000f\u0005a[V\"A-\u000b\u0005i;\u0013A\u0002\u001fs_>$h(C\u0001'\u0013\t!S%\u0003\u0002#G%\u0011q,I\u0001\r-\u0016\u001cGo\u001c:J]2Lg.Z\u0005\u0003C\n\u0014A!\u0011:sc)\u0011q,I\u0001\u0003CJ\u0002\"\u0001V3\n\u0005\u0019\u0014'\u0001B!seJ\n!!Y\u001a\u0011\u0005QK\u0017B\u00016c\u0005\u0011\t%O]\u001a\u0002\u0005\u0005$\u0004C\u0001+n\u0013\tq'M\u0001\u0003BeJ$\u0014AA16!\t!\u0016/\u0003\u0002sE\n!\u0011I\u001d:6\u0003\t\tg\u0007\u0005\u0002Uk&\u0011aO\u0019\u0002\u0005\u0003J\u0014h'A\u0003bc1,g.\u0001\u0019tG\u0006d\u0017\rJ2pY2,7\r^5p]\u0012JW.\\;uC\ndW\r\n(foZ+7\r^8s\u0013R,'/\u0019;pe\u0012\"\u0013.M\u0001\u0007_2$\u0007k\\:\u0002eM\u001c\u0017\r\\1%G>dG.Z2uS>tG%[7nkR\f'\r\\3%\u001d\u0016<h+Z2u_JLE/\u001a:bi>\u0014H\u0005\n7f]F\n\u0001b\u001d7jG\u0016LE\r_\u0001\tg2L7-\u001a#j[\u0006Q1\u000f\\5dKN#\u0018M\u001d;\u0002\u0011Md\u0017nY3F]\u0012\f\u0011b\u001b8po:\u001c\u0016N_3\u0016\u0003!C3aEA\u0003!\r!\u0014qA\u0005\u0004\u0003\u0013)#AB5oY&tW-A\u0004iCNtU\r\u001f;\u0016\u0005\u0005=\u0001c\u0001\u001b\u0002\u0012%\u0019\u00111C\u0013\u0003\u000f\t{w\u000e\\3b]\"\u001aA#!\u0002\u0002\t9,\u0007\u0010\u001e\u000b\u0002]\u0005a\u0011\r\u001a<b]\u000e,7\u000b\\5dKR\u0011\u0011q\u0004\t\u0004i\u0005\u0005\u0012bAA\u0012K\t!QK\\5u\u0003\u001d\tGM^1oG\u0016\f\u0001\"\u00193wC:\u001cW-\u0011\u000b\u0007\u0003?\tY#a\f\t\r\u00055\u0002\u00041\u0001I\u0003\tIw\u000e\u0003\u0004\u00022a\u0001\r\u0001S\u0001\u0004q>\u0014\u0018\u0001B:fi\u0006#b!a\b\u00028\u0005e\u0002BBA\u00173\u0001\u0007\u0001\n\u0003\u0004\u00022e\u0001\r\u0001S\u0001\u0005IJ|\u0007\u000f\u0006\u0003\u0002@\u0005\u0015\u0003\u0003B\u0016\u0002B9J1!a\u0011$\u0005!IE/\u001a:bi>\u0014\bBBA$5\u0001\u0007\u0001*A\u0001o\u0003\u0011!\u0018m[3\u0015\t\u0005}\u0012Q\n\u0005\u0007\u0003\u000fZ\u0002\u0019\u0001%\u0002\u000bMd\u0017nY3\u0015\r\u0005}\u00121KA,\u0011\u0019\t)\u0006\ba\u0001\u0011\u0006!aM]8n\u0011\u0019\tI\u0006\ba\u0001\u0011\u0006)QO\u001c;jY\u0006Y1m\u001c9z)>\f%O]1z+\u0011\ty&!\u001c\u0015\u000f!\u000b\t'a\u001d\u0002x!9\u00111M\u000fA\u0002\u0005\u0015\u0014A\u0001=t!\u0015!\u0014qMA6\u0013\r\tI'\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004_\u00055DaBA8;\t\u0007\u0011\u0011\u000f\u0002\u0002\u0005F\u0011af\u000e\u0005\u0007\u0003kj\u0002\u0019\u0001%\u0002\u000bM$\u0018M\u001d;\t\r\u0005eT\u00041\u0001I\u0003\raWM\\\u0001\ti>4Vm\u0019;peV\t1)A\u0003ta2LG\u000fF\u0002O\u0003\u0007Ca!!\" \u0001\u0004A\u0015AA1u\u0001"
)
public final class NewVectorIterator extends AbstractIterator implements Cloneable {
   private final Vector v;
   private int totalLength;
   private final int sliceCount;
   private Object[] a1;
   private Object[][] a2;
   private Object[][][] a3;
   private Object[][][][] a4;
   private Object[][][][][] a5;
   private Object[][][][][][] a6;
   private int a1len;
   public int scala$collection$immutable$NewVectorIterator$$i1;
   private int oldPos;
   public int scala$collection$immutable$NewVectorIterator$$len1;
   private int sliceIdx;
   private int sliceDim;
   private int sliceStart;
   private int sliceEnd;

   public int knownSize() {
      return this.scala$collection$immutable$NewVectorIterator$$len1 - this.scala$collection$immutable$NewVectorIterator$$i1;
   }

   public boolean hasNext() {
      return this.scala$collection$immutable$NewVectorIterator$$len1 > this.scala$collection$immutable$NewVectorIterator$$i1;
   }

   public Object next() {
      if (this.scala$collection$immutable$NewVectorIterator$$i1 == this.a1len) {
         this.advance();
      }

      Object r = this.a1[this.scala$collection$immutable$NewVectorIterator$$i1];
      ++this.scala$collection$immutable$NewVectorIterator$$i1;
      return r;
   }

   private void advanceSlice() {
      if (this.scala$collection$immutable$NewVectorIterator$$len1 <= this.scala$collection$immutable$NewVectorIterator$$i1) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      ++this.sliceIdx;

      Object[] slice;
      for(slice = this.v.vectorSlice(this.sliceIdx); slice.length == 0; slice = this.v.vectorSlice(this.sliceIdx)) {
         ++this.sliceIdx;
      }

      this.sliceStart = this.sliceEnd;
      VectorInline$ var10001 = VectorInline$.MODULE$;
      int vectorSliceDim_idx = this.sliceIdx;
      int vectorSliceDim_c = this.sliceCount / 2;
      this.sliceDim = vectorSliceDim_c + 1 - Math.abs(vectorSliceDim_idx - vectorSliceDim_c);
      int var2 = this.sliceDim;
      switch (var2) {
         case 1:
            this.a1 = slice;
            break;
         case 2:
            this.a2 = slice;
            break;
         case 3:
            this.a3 = slice;
            break;
         case 4:
            this.a4 = slice;
            break;
         case 5:
            this.a5 = slice;
            break;
         case 6:
            this.a6 = slice;
            break;
         default:
            throw new MatchError(var2);
      }

      this.sliceEnd = this.sliceStart + slice.length * (1 << 5 * (this.sliceDim - 1));
      if (this.sliceEnd > this.totalLength) {
         this.sliceEnd = this.totalLength;
      }

      if (this.sliceDim > 1) {
         this.oldPos = (1 << 5 * this.sliceDim) - 1;
      }
   }

   private void advance() {
      int pos = this.scala$collection$immutable$NewVectorIterator$$i1 - this.scala$collection$immutable$NewVectorIterator$$len1 + this.totalLength;
      if (pos == this.sliceEnd) {
         this.advanceSlice();
      }

      if (this.sliceDim > 1) {
         int io = pos - this.sliceStart;
         int xor = this.oldPos ^ io;
         this.advanceA(io, xor);
         this.oldPos = io;
      }

      this.scala$collection$immutable$NewVectorIterator$$len1 -= this.scala$collection$immutable$NewVectorIterator$$i1;
      this.a1len = Math.min(this.a1.length, this.scala$collection$immutable$NewVectorIterator$$len1);
      this.scala$collection$immutable$NewVectorIterator$$i1 = 0;
   }

   private void advanceA(final int io, final int xor) {
      if (xor < 1024) {
         this.a1 = this.a2[io >>> 5 & 31];
      } else if (xor < 32768) {
         this.a2 = this.a3[io >>> 10 & 31];
         this.a1 = this.a2[0];
      } else if (xor < 1048576) {
         this.a3 = this.a4[io >>> 15 & 31];
         this.a2 = this.a3[0];
         this.a1 = this.a2[0];
      } else if (xor < 33554432) {
         this.a4 = this.a5[io >>> 20 & 31];
         this.a3 = this.a4[0];
         this.a2 = this.a3[0];
         this.a1 = this.a2[0];
      } else {
         this.a5 = this.a6[io >>> 25];
         this.a4 = this.a5[0];
         this.a3 = this.a4[0];
         this.a2 = this.a3[0];
         this.a1 = this.a2[0];
      }
   }

   private void setA(final int io, final int xor) {
      if (xor < 1024) {
         this.a1 = this.a2[io >>> 5 & 31];
      } else if (xor < 32768) {
         this.a2 = this.a3[io >>> 10 & 31];
         this.a1 = this.a2[io >>> 5 & 31];
      } else if (xor < 1048576) {
         this.a3 = this.a4[io >>> 15 & 31];
         this.a2 = this.a3[io >>> 10 & 31];
         this.a1 = this.a2[io >>> 5 & 31];
      } else if (xor < 33554432) {
         this.a4 = this.a5[io >>> 20 & 31];
         this.a3 = this.a4[io >>> 15 & 31];
         this.a2 = this.a3[io >>> 10 & 31];
         this.a1 = this.a2[io >>> 5 & 31];
      } else {
         this.a5 = this.a6[io >>> 25];
         this.a4 = this.a5[io >>> 20 & 31];
         this.a3 = this.a4[io >>> 15 & 31];
         this.a2 = this.a3[io >>> 10 & 31];
         this.a1 = this.a2[io >>> 5 & 31];
      }
   }

   public Iterator drop(final int n) {
      if (n > 0) {
         int newpos = Math.min(this.scala$collection$immutable$NewVectorIterator$$i1 - this.scala$collection$immutable$NewVectorIterator$$len1 + this.totalLength + n, this.totalLength);
         if (newpos == this.totalLength) {
            this.scala$collection$immutable$NewVectorIterator$$i1 = 0;
            this.scala$collection$immutable$NewVectorIterator$$len1 = 0;
            this.a1len = 0;
         } else {
            while(newpos >= this.sliceEnd) {
               this.advanceSlice();
            }

            int io = newpos - this.sliceStart;
            if (this.sliceDim > 1) {
               int xor = this.oldPos ^ io;
               this.setA(io, xor);
               this.oldPos = io;
            }

            this.a1len = this.a1.length;
            this.scala$collection$immutable$NewVectorIterator$$i1 = io & 31;
            this.scala$collection$immutable$NewVectorIterator$$len1 = this.scala$collection$immutable$NewVectorIterator$$i1 + (this.totalLength - newpos);
            if (this.a1len > this.scala$collection$immutable$NewVectorIterator$$len1) {
               this.a1len = this.scala$collection$immutable$NewVectorIterator$$len1;
            }
         }
      }

      return this;
   }

   public Iterator take(final int n) {
      if (n < this.scala$collection$immutable$NewVectorIterator$$len1 - this.scala$collection$immutable$NewVectorIterator$$i1) {
         int trunc = this.scala$collection$immutable$NewVectorIterator$$len1 - this.scala$collection$immutable$NewVectorIterator$$i1 - Math.max(0, n);
         this.totalLength -= trunc;
         this.scala$collection$immutable$NewVectorIterator$$len1 -= trunc;
         if (this.scala$collection$immutable$NewVectorIterator$$len1 < this.a1len) {
            this.a1len = this.scala$collection$immutable$NewVectorIterator$$len1;
         }

         if (this.totalLength < this.sliceEnd) {
            this.sliceEnd = this.totalLength;
         }
      }

      return this;
   }

   public Iterator slice(final int from, final int until) {
      int _until = Math.max(until, 0);
      int var10000;
      if (from > 0) {
         this.drop(from);
         var10000 = _until - from;
      } else {
         var10000 = _until;
      }

      int n = var10000;
      return this.take(n);
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      int xsLen = Array.getLength(xs);
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      int elemsToCopyToArray_srcLen = this.scala$collection$immutable$NewVectorIterator$$len1 - this.scala$collection$immutable$NewVectorIterator$$i1;
      scala.math.package$ var10 = scala.math.package$.MODULE$;
      var10 = scala.math.package$.MODULE$;
      var10 = scala.math.package$.MODULE$;
      int total = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), xsLen - start), 0);
      int copied = 0;

      int count;
      for(boolean isBoxed = xs instanceof Object[]; copied < total; copied += count) {
         if (this.scala$collection$immutable$NewVectorIterator$$i1 == this.a1len) {
            this.advance();
         }

         count = Math.min(total - copied, this.a1.length - this.scala$collection$immutable$NewVectorIterator$$i1);
         if (isBoxed) {
            System.arraycopy(this.a1, this.scala$collection$immutable$NewVectorIterator$$i1, xs, start + copied, count);
         } else {
            Array$.MODULE$.copy(this.a1, this.scala$collection$immutable$NewVectorIterator$$i1, xs, start + copied, count);
         }

         this.scala$collection$immutable$NewVectorIterator$$i1 += count;
      }

      return total;
   }

   public Vector toVector() {
      return (Vector)this.v.slice(this.scala$collection$immutable$NewVectorIterator$$i1 - this.scala$collection$immutable$NewVectorIterator$$len1 + this.totalLength, this.totalLength);
   }

   public NewVectorIterator split(final int at) {
      NewVectorIterator it2 = (NewVectorIterator)this.clone();
      it2.take(at);
      this.drop(at);
      return it2;
   }

   public NewVectorIterator(final Vector v, final int totalLength, final int sliceCount) {
      this.v = v;
      this.totalLength = totalLength;
      this.sliceCount = sliceCount;
      super();
      this.a1 = v.prefix1();
      this.a1len = this.a1.length;
      this.scala$collection$immutable$NewVectorIterator$$i1 = 0;
      this.oldPos = 0;
      this.scala$collection$immutable$NewVectorIterator$$len1 = this.totalLength;
      this.sliceIdx = 0;
      this.sliceDim = 1;
      this.sliceStart = 0;
      this.sliceEnd = this.a1len;
   }
}
