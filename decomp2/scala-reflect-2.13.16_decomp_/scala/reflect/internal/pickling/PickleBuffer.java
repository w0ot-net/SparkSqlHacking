package scala.reflect.internal.pickling;

import scala.Function0;
import scala.Predef;
import scala.Tuple2;
import scala.Array.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d\u0001B\u000f\u001f\u0001\u001dB\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006I!\f\u0005\tg\u0001\u0011\t\u0011)A\u0005i!Aq\u0007\u0001B\u0001B\u0003%A\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0004@\u0001\u0001\u0007I\u0011\u0001!\t\u000f\u0005\u0003\u0001\u0019!C\u0001\u0005\"1\u0001\n\u0001Q!\n5Bq!\u0013\u0001A\u0002\u0013\u0005!\nC\u0004L\u0001\u0001\u0007I\u0011\u0001'\t\r9\u0003\u0001\u0015)\u00035\u0011\u001dy\u0005\u00011A\u0005\u0002)Cq\u0001\u0015\u0001A\u0002\u0013\u0005\u0011\u000b\u0003\u0004T\u0001\u0001\u0006K\u0001\u000e\u0005\u0006)\u0002!I!\u0016\u0005\u00069\u0002!\t!\u0018\u0005\u0006A\u0002!\t!\u0019\u0005\u0006I\u0002!\t!\u001a\u0005\u0006Q\u0002!\t!\u001b\u0005\u0006]\u0002!\ta\u001c\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006m\u0002!\ta\u001e\u0005\u0006q\u0002!\ta\u001e\u0005\u0006s\u0002!\tA\u001f\u0005\u0006w\u0002!\t\u0001 \u0005\u0007\u007f\u0002!\t!!\u0001\t\u000f\u0005]\u0001\u0001\"\u0001\u0002\u001a!9\u0011q\t\u0001\u0005\u0002\u0005%\u0003bBA.\u0001\u0011\u0005\u0011Q\f\u0002\r!&\u001c7\u000e\\3Ck\u001a4WM\u001d\u0006\u0003?\u0001\n\u0001\u0002]5dW2Lgn\u001a\u0006\u0003C\t\n\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003G\u0011\nqA]3gY\u0016\u001cGOC\u0001&\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0015\u0011\u0005%RS\"\u0001\u0013\n\u0005-\"#AB!osJ+g-\u0001\u0003eCR\f\u0007cA\u0015/a%\u0011q\u0006\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003SEJ!A\r\u0013\u0003\t\tKH/Z\u0001\u0005MJ|W\u000e\u0005\u0002*k%\u0011a\u0007\n\u0002\u0004\u0013:$\u0018A\u0001;p\u0003\u0019a\u0014N\\5u}Q!!\bP\u001f?!\tY\u0004!D\u0001\u001f\u0011\u0015aC\u00011\u0001.\u0011\u0015\u0019D\u00011\u00015\u0011\u00159D\u00011\u00015\u0003\u0015\u0011\u0017\u0010^3t+\u0005i\u0013!\u00032zi\u0016\u001cx\fJ3r)\t\u0019e\t\u0005\u0002*\t&\u0011Q\t\n\u0002\u0005+:LG\u000fC\u0004H\r\u0005\u0005\t\u0019A\u0017\u0002\u0007a$\u0013'\u0001\u0004csR,7\u000fI\u0001\ne\u0016\fG-\u00138eKb,\u0012\u0001N\u0001\u000ee\u0016\fG-\u00138eKb|F%Z9\u0015\u0005\rk\u0005bB$\n\u0003\u0003\u0005\r\u0001N\u0001\u000be\u0016\fG-\u00138eKb\u0004\u0013AC<sSR,\u0017J\u001c3fq\u0006qqO]5uK&sG-\u001a=`I\u0015\fHCA\"S\u0011\u001d9E\"!AA\u0002Q\n1b\u001e:ji\u0016Le\u000eZ3yA\u00051qM]8x)>$\"a\u0011,\t\u000b]s\u0001\u0019\u0001\u001b\u0002\u001dQ\f'oZ3u\u0007\u0006\u0004\u0018mY5us\"\u0012a\"\u0017\t\u0003SiK!a\u0017\u0013\u0003\r%tG.\u001b8f\u00039)gn];sK\u000e\u000b\u0007/Y2jif$\"a\u00110\t\u000b}{\u0001\u0019\u0001\u001b\u0002\u0011\r\f\u0007/Y2jif\f\u0011b\u001e:ji\u0016\u0014\u0015\u0010^3\u0015\u0005\r\u0013\u0007\"B2\u0011\u0001\u0004!\u0014!\u00012\u0002\u0011]\u0014\u0018\u000e^3OCR$\"a\u00114\t\u000b\u001d\f\u0002\u0019\u0001\u001b\u0002\u0003a\fAb\u001e:ji\u0016duN\\4OCR$\"a\u00116\t\u000b\u001d\u0014\u0002\u0019A6\u0011\u0005%b\u0017BA7%\u0005\u0011auN\\4\u0002\u0011A\fGo\u00195OCR$2a\u00119s\u0011\u0015\t8\u00031\u00015\u0003\r\u0001xn\u001d\u0005\u0006ON\u0001\r\u0001N\u0001\noJLG/\u001a'p]\u001e$\"aQ;\t\u000b\u001d$\u0002\u0019A6\u0002\u0011I,\u0017\r\u001a\"zi\u0016$\u0012\u0001N\u0001\be\u0016\fGMT1u\u0003-\u0011X-\u00193M_:<g*\u0019;\u0015\u0003-\f\u0001B]3bI2{gn\u001a\u000b\u0003WvDQA \rA\u0002Q\n1\u0001\\3o\u00031!x.\u00138eKb,GmU3r+\t\t\u0019\u0001\u0005\u0004\u0002\u0006\u0005-\u0011\u0011\u0003\b\u0004S\u0005\u001d\u0011bAA\u0005I\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0007\u0003\u001f\u0011!\"\u00138eKb,GmU3r\u0015\r\tI\u0001\n\t\u0006S\u0005MA'L\u0005\u0004\u0003+!#A\u0002+va2,''A\u0003v]RLG.\u0006\u0003\u0002\u001c\u0005\u001dBCBA\u000f\u0003s\ti\u0004\u0005\u0004\u0002\u0006\u0005}\u00111E\u0005\u0005\u0003C\tyA\u0001\u0003MSN$\b\u0003BA\u0013\u0003Oa\u0001\u0001B\u0004\u0002*i\u0011\r!a\u000b\u0003\u0003Q\u000bB!!\f\u00024A\u0019\u0011&a\f\n\u0007\u0005EBEA\u0004O_RD\u0017N\\4\u0011\u0007%\n)$C\u0002\u00028\u0011\u00121!\u00118z\u0011\u0019\tYD\u0007a\u0001i\u0005\u0019QM\u001c3\t\u000f\u0005}\"\u00041\u0001\u0002B\u0005\u0011q\u000e\u001d\t\u0006S\u0005\r\u00131E\u0005\u0004\u0003\u000b\"#!\u0003$v]\u000e$\u0018n\u001c81\u0003\u0015!\u0018.\\3t+\u0011\tY%!\u0015\u0015\r\u00055\u00131KA,!\u0019\t)!a\b\u0002PA!\u0011QEA)\t\u001d\tIc\u0007b\u0001\u0003WAa!!\u0016\u001c\u0001\u0004!\u0014!\u00018\t\u000f\u0005}2\u00041\u0001\u0002ZA)\u0011&a\u0011\u0002P\u0005Y1M]3bi\u0016Le\u000eZ3y+\t\ty\u0006E\u0002*]Q\u0002"
)
public class PickleBuffer {
   private final byte[] data;
   private byte[] bytes;
   private int readIndex;
   private int writeIndex;

   public byte[] bytes() {
      return this.bytes;
   }

   public void bytes_$eq(final byte[] x$1) {
      this.bytes = x$1;
   }

   public int readIndex() {
      return this.readIndex;
   }

   public void readIndex_$eq(final int x$1) {
      this.readIndex = x$1;
   }

   public int writeIndex() {
      return this.writeIndex;
   }

   public void writeIndex_$eq(final int x$1) {
      this.writeIndex = x$1;
   }

   private void growTo(final int targetCapacity) {
      byte[] bytes1 = new byte[targetCapacity];
      .MODULE$.copy(this.bytes(), 0, bytes1, 0, this.writeIndex());
      this.bytes_$eq(bytes1);
   }

   public void ensureCapacity(final int capacity) {
      if (this.bytes().length < this.writeIndex() + capacity) {
         int newCapacity;
         for(newCapacity = this.bytes().length; newCapacity < this.writeIndex() + capacity; newCapacity <<= 1) {
         }

         byte[] growTo_bytes1 = new byte[newCapacity];
         .MODULE$.copy(this.bytes(), 0, growTo_bytes1, 0, this.writeIndex());
         this.bytes_$eq(growTo_bytes1);
      }
   }

   public void writeByte(final int b) {
      if (this.writeIndex() == this.bytes().length) {
         byte[] growTo_bytes1 = new byte[this.bytes().length << 1];
         .MODULE$.copy(this.bytes(), 0, growTo_bytes1, 0, this.writeIndex());
         this.bytes_$eq(growTo_bytes1);
         Object var3 = null;
      }

      this.bytes()[this.writeIndex()] = (byte)b;
      this.writeIndex_$eq(this.writeIndex() + 1);
   }

   public void writeNat(final int x) {
      this.writeLongNat((long)x & 4294967295L);
   }

   public void writeLongNat(final long x) {
      long y = x >>> 7;
      if (y != 0L) {
         this.writeNatPrefix$1(y);
      }

      this.writeByte((int)(x & 127L));
   }

   public void patchNat(final int pos, final int x) {
      this.bytes()[pos] = (byte)(x & 127);
      int y = x >>> 7;
      if (y != 0) {
         this.patchNatPrefix$1(y, pos);
      }
   }

   public void writeLong(final long x) {
      long y = x >> 8;
      long z = x & 255L;
      if (-y != z >> 7) {
         this.writeLong(y);
      }

      this.writeByte((int)z);
   }

   public int readByte() {
      int x = this.bytes()[this.readIndex()];
      this.readIndex_$eq(this.readIndex() + 1);
      return x;
   }

   public int readNat() {
      return (int)this.readLongNat();
   }

   public long readLongNat() {
      long x = 0L;

      long b;
      do {
         b = (long)this.readByte();
         x = (x << 7) + (b & 127L);
      } while((b & 128L) != 0L);

      return x;
   }

   public long readLong(final int len) {
      long x = 0L;

      for(int i = 0; i < len; ++i) {
         x = (x << 8) + (long)(this.readByte() & 255);
      }

      int leading = 64 - (len << 3);
      return x << leading >> leading;
   }

   public IndexedSeq toIndexedSeq() {
      int saved = this.readIndex();
      this.readIndex_$eq(0);
      this.readNat();
      this.readNat();
      Tuple2[] result = new Tuple2[this.readNat()];
      Range var10000 = scala.collection.ArrayOps..MODULE$.indices$extension(result);
      if (var10000 == null) {
         throw null;
      } else {
         Range foreach$mVc$sp_this = var10000;
         if (!foreach$mVc$sp_this.isEmpty()) {
            int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

            while(true) {
               $anonfun$toIndexedSeq$1(this, result, foreach$mVc$sp_i);
               if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
                  break;
               }

               foreach$mVc$sp_i += foreach$mVc$sp_this.step();
            }
         }

         Object var5 = null;
         this.readIndex_$eq(saved);
         return scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(result);
      }
   }

   public List until(final int end, final Function0 op) {
      if (this.readIndex() == end) {
         return scala.collection.immutable.Nil..MODULE$;
      } else {
         Object var3 = op.apply();
         List var10000 = this.until(end, op);
         if (var10000 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10000;
            return new scala.collection.immutable..colon.colon(var3, $colon$colon_this);
         }
      }
   }

   public List times(final int n, final Function0 op) {
      if (n == 0) {
         return scala.collection.immutable.Nil..MODULE$;
      } else {
         Object var3 = op.apply();
         List var10000 = this.times(n - 1, op);
         if (var10000 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10000;
            return new scala.collection.immutable..colon.colon(var3, $colon$colon_this);
         }
      }
   }

   public int[] createIndex() {
      int[] index = new int[this.readNat()];
      RichInt var10000 = scala.runtime.RichInt..MODULE$;
      byte var2 = 0;
      int until$extension_end = index.length;
      Range var6 = scala.collection.immutable.Range..MODULE$;
      Range foreach$mVc$sp_this = new Range.Exclusive(var2, until$extension_end, 1);
      if (!foreach$mVc$sp_this.isEmpty()) {
         int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

         while(true) {
            $anonfun$createIndex$1(this, index, foreach$mVc$sp_i);
            if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
               break;
            }

            foreach$mVc$sp_i += foreach$mVc$sp_this.step();
         }
      }

      return index;
   }

   private final void writeNatPrefix$1(final long x) {
      long y = x >>> 7;
      if (y != 0L) {
         this.writeNatPrefix$1(y);
      }

      this.writeByte((int)(x & 127L | 128L));
   }

   private final void patchNatPrefix$1(final int x, final int pos$1) {
      while(true) {
         this.writeByte(0);
         .MODULE$.copy(this.bytes(), pos$1, this.bytes(), pos$1 + 1, this.writeIndex() - (pos$1 + 1));
         this.bytes()[pos$1] = (byte)(x & 127 | 128);
         int y = x >>> 7;
         if (y == 0) {
            return;
         }

         x = y;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$toIndexedSeq$1(final PickleBuffer $this, final Tuple2[] result$1, final int index) {
      int tag = $this.readNat();
      int len = $this.readNat();
      byte[] bytes = (byte[])scala.collection.ArrayOps..MODULE$.slice$extension($this.data, $this.readIndex(), len + $this.readIndex());
      $this.readIndex_$eq($this.readIndex() + len);
      Predef.ArrowAssoc var10002 = scala.Predef.ArrowAssoc..MODULE$;
      Object ArrowAssoc_self = tag;
      Object var9 = ArrowAssoc_self;
      ArrowAssoc_self = null;
      Object $minus$greater$extension_$this = var9;
      result$1[index] = new Tuple2($minus$greater$extension_$this, bytes);
   }

   // $FF: synthetic method
   public static final void $anonfun$createIndex$1(final PickleBuffer $this, final int[] index$1, final int i) {
      index$1[i] = $this.readIndex();
      $this.readByte();
      $this.readIndex_$eq($this.readNat() + $this.readIndex());
   }

   public PickleBuffer(final byte[] data, final int from, final int to) {
      this.data = data;
      this.bytes = data;
      this.readIndex = from;
      this.writeIndex = to;
   }
}
