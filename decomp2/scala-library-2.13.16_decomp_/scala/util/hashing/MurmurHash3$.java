package scala.util.hashing;

import scala.Function1;
import scala.Function2;
import scala.Product;
import scala.collection.IndexedSeq;
import scala.collection.IterableOnce;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

public final class MurmurHash3$ extends MurmurHash3 {
   public static final MurmurHash3$ MODULE$ = new MurmurHash3$();
   private static final int seqSeed = "Seq".hashCode();
   private static final int mapSeed = "Map".hashCode();
   private static final int setSeed = "Set".hashCode();
   private static final int emptyMapHash;

   static {
      emptyMapHash = MODULE$.unorderedHash(Nil$.MODULE$, MODULE$.mapSeed());
   }

   public final int arraySeed() {
      return 1007110753;
   }

   public final int stringSeed() {
      return -137723950;
   }

   public final int productSeed() {
      return -889275714;
   }

   public final int symmetricSeed() {
      return -1248659538;
   }

   public final int traversableSeed() {
      return -415593707;
   }

   public final int seqSeed() {
      return seqSeed;
   }

   public final int mapSeed() {
      return mapSeed;
   }

   public final int setSeed() {
      return setSeed;
   }

   public int arrayHash(final Object a) {
      return this.arrayHash(a, 1007110753);
   }

   public int bytesHash(final byte[] data) {
      return this.bytesHash(data, 1007110753);
   }

   public int orderedHash(final IterableOnce xs) {
      return this.orderedHash(xs, -1248659538);
   }

   public int productHash(final Product x) {
      return this.productHash(x, -889275714, false);
   }

   public int stringHash(final String x) {
      return this.stringHash(x, -137723950);
   }

   public int unorderedHash(final IterableOnce xs) {
      return this.unorderedHash(xs, -415593707);
   }

   public int rangeHash(final int start, final int step, final int last) {
      return this.rangeHash(start, step, last, this.seqSeed());
   }

   public int arraySeqHash(final Object a) {
      return this.arrayHash(a, this.seqSeed());
   }

   public int tuple2Hash(final Object x, final Object y) {
      return this.tuple2Hash(Statics.anyHash(x), Statics.anyHash(y), -889275714);
   }

   public int seqHash(final Seq xs) {
      if (xs instanceof IndexedSeq) {
         IndexedSeq var2 = (IndexedSeq)xs;
         return this.indexedSeqHash(var2, this.seqSeed());
      } else if (xs instanceof List) {
         List var3 = (List)xs;
         return this.listHash(var3, this.seqSeed());
      } else {
         return this.orderedHash(xs, this.seqSeed());
      }
   }

   public int mapHash(final Map xs) {
      if (xs.isEmpty()) {
         return this.emptyMapHash();
      } else {
         class accum$1 implements Function2 {
            private int a = 0;
            private int b = 0;
            private int n = 0;
            private int c = 1;

            public boolean apply$mcZDD$sp(final double v1, final double v2) {
               return Function2.apply$mcZDD$sp$(this, v1, v2);
            }

            public double apply$mcDDD$sp(final double v1, final double v2) {
               return Function2.apply$mcDDD$sp$(this, v1, v2);
            }

            public float apply$mcFDD$sp(final double v1, final double v2) {
               return Function2.apply$mcFDD$sp$(this, v1, v2);
            }

            public int apply$mcIDD$sp(final double v1, final double v2) {
               return Function2.apply$mcIDD$sp$(this, v1, v2);
            }

            public long apply$mcJDD$sp(final double v1, final double v2) {
               return Function2.apply$mcJDD$sp$(this, v1, v2);
            }

            public void apply$mcVDD$sp(final double v1, final double v2) {
               Function2.apply$mcVDD$sp$(this, v1, v2);
            }

            public boolean apply$mcZDI$sp(final double v1, final int v2) {
               return Function2.apply$mcZDI$sp$(this, v1, v2);
            }

            public double apply$mcDDI$sp(final double v1, final int v2) {
               return Function2.apply$mcDDI$sp$(this, v1, v2);
            }

            public float apply$mcFDI$sp(final double v1, final int v2) {
               return Function2.apply$mcFDI$sp$(this, v1, v2);
            }

            public int apply$mcIDI$sp(final double v1, final int v2) {
               return Function2.apply$mcIDI$sp$(this, v1, v2);
            }

            public long apply$mcJDI$sp(final double v1, final int v2) {
               return Function2.apply$mcJDI$sp$(this, v1, v2);
            }

            public void apply$mcVDI$sp(final double v1, final int v2) {
               Function2.apply$mcVDI$sp$(this, v1, v2);
            }

            public boolean apply$mcZDJ$sp(final double v1, final long v2) {
               return Function2.apply$mcZDJ$sp$(this, v1, v2);
            }

            public double apply$mcDDJ$sp(final double v1, final long v2) {
               return Function2.apply$mcDDJ$sp$(this, v1, v2);
            }

            public float apply$mcFDJ$sp(final double v1, final long v2) {
               return Function2.apply$mcFDJ$sp$(this, v1, v2);
            }

            public int apply$mcIDJ$sp(final double v1, final long v2) {
               return Function2.apply$mcIDJ$sp$(this, v1, v2);
            }

            public long apply$mcJDJ$sp(final double v1, final long v2) {
               return Function2.apply$mcJDJ$sp$(this, v1, v2);
            }

            public void apply$mcVDJ$sp(final double v1, final long v2) {
               Function2.apply$mcVDJ$sp$(this, v1, v2);
            }

            public boolean apply$mcZID$sp(final int v1, final double v2) {
               return Function2.apply$mcZID$sp$(this, v1, v2);
            }

            public double apply$mcDID$sp(final int v1, final double v2) {
               return Function2.apply$mcDID$sp$(this, v1, v2);
            }

            public float apply$mcFID$sp(final int v1, final double v2) {
               return Function2.apply$mcFID$sp$(this, v1, v2);
            }

            public int apply$mcIID$sp(final int v1, final double v2) {
               return Function2.apply$mcIID$sp$(this, v1, v2);
            }

            public long apply$mcJID$sp(final int v1, final double v2) {
               return Function2.apply$mcJID$sp$(this, v1, v2);
            }

            public void apply$mcVID$sp(final int v1, final double v2) {
               Function2.apply$mcVID$sp$(this, v1, v2);
            }

            public boolean apply$mcZII$sp(final int v1, final int v2) {
               return Function2.apply$mcZII$sp$(this, v1, v2);
            }

            public double apply$mcDII$sp(final int v1, final int v2) {
               return Function2.apply$mcDII$sp$(this, v1, v2);
            }

            public float apply$mcFII$sp(final int v1, final int v2) {
               return Function2.apply$mcFII$sp$(this, v1, v2);
            }

            public int apply$mcIII$sp(final int v1, final int v2) {
               return Function2.apply$mcIII$sp$(this, v1, v2);
            }

            public long apply$mcJII$sp(final int v1, final int v2) {
               return Function2.apply$mcJII$sp$(this, v1, v2);
            }

            public void apply$mcVII$sp(final int v1, final int v2) {
               Function2.apply$mcVII$sp$(this, v1, v2);
            }

            public boolean apply$mcZIJ$sp(final int v1, final long v2) {
               return Function2.apply$mcZIJ$sp$(this, v1, v2);
            }

            public double apply$mcDIJ$sp(final int v1, final long v2) {
               return Function2.apply$mcDIJ$sp$(this, v1, v2);
            }

            public float apply$mcFIJ$sp(final int v1, final long v2) {
               return Function2.apply$mcFIJ$sp$(this, v1, v2);
            }

            public int apply$mcIIJ$sp(final int v1, final long v2) {
               return Function2.apply$mcIIJ$sp$(this, v1, v2);
            }

            public long apply$mcJIJ$sp(final int v1, final long v2) {
               return Function2.apply$mcJIJ$sp$(this, v1, v2);
            }

            public void apply$mcVIJ$sp(final int v1, final long v2) {
               Function2.apply$mcVIJ$sp$(this, v1, v2);
            }

            public boolean apply$mcZJD$sp(final long v1, final double v2) {
               return Function2.apply$mcZJD$sp$(this, v1, v2);
            }

            public double apply$mcDJD$sp(final long v1, final double v2) {
               return Function2.apply$mcDJD$sp$(this, v1, v2);
            }

            public float apply$mcFJD$sp(final long v1, final double v2) {
               return Function2.apply$mcFJD$sp$(this, v1, v2);
            }

            public int apply$mcIJD$sp(final long v1, final double v2) {
               return Function2.apply$mcIJD$sp$(this, v1, v2);
            }

            public long apply$mcJJD$sp(final long v1, final double v2) {
               return Function2.apply$mcJJD$sp$(this, v1, v2);
            }

            public void apply$mcVJD$sp(final long v1, final double v2) {
               Function2.apply$mcVJD$sp$(this, v1, v2);
            }

            public boolean apply$mcZJI$sp(final long v1, final int v2) {
               return Function2.apply$mcZJI$sp$(this, v1, v2);
            }

            public double apply$mcDJI$sp(final long v1, final int v2) {
               return Function2.apply$mcDJI$sp$(this, v1, v2);
            }

            public float apply$mcFJI$sp(final long v1, final int v2) {
               return Function2.apply$mcFJI$sp$(this, v1, v2);
            }

            public int apply$mcIJI$sp(final long v1, final int v2) {
               return Function2.apply$mcIJI$sp$(this, v1, v2);
            }

            public long apply$mcJJI$sp(final long v1, final int v2) {
               return Function2.apply$mcJJI$sp$(this, v1, v2);
            }

            public void apply$mcVJI$sp(final long v1, final int v2) {
               Function2.apply$mcVJI$sp$(this, v1, v2);
            }

            public boolean apply$mcZJJ$sp(final long v1, final long v2) {
               return Function2.apply$mcZJJ$sp$(this, v1, v2);
            }

            public double apply$mcDJJ$sp(final long v1, final long v2) {
               return Function2.apply$mcDJJ$sp$(this, v1, v2);
            }

            public float apply$mcFJJ$sp(final long v1, final long v2) {
               return Function2.apply$mcFJJ$sp$(this, v1, v2);
            }

            public int apply$mcIJJ$sp(final long v1, final long v2) {
               return Function2.apply$mcIJJ$sp$(this, v1, v2);
            }

            public long apply$mcJJJ$sp(final long v1, final long v2) {
               return Function2.apply$mcJJJ$sp$(this, v1, v2);
            }

            public void apply$mcVJJ$sp(final long v1, final long v2) {
               Function2.apply$mcVJJ$sp$(this, v1, v2);
            }

            public Function1 curried() {
               return Function2.curried$(this);
            }

            public Function1 tupled() {
               return Function2.tupled$(this);
            }

            public String toString() {
               return Function2.toString$(this);
            }

            public int a() {
               return this.a;
            }

            public void a_$eq(final int x$1) {
               this.a = x$1;
            }

            public int b() {
               return this.b;
            }

            public void b_$eq(final int x$1) {
               this.b = x$1;
            }

            public int n() {
               return this.n;
            }

            public void n_$eq(final int x$1) {
               this.n = x$1;
            }

            public int c() {
               return this.c;
            }

            public void c_$eq(final int x$1) {
               this.c = x$1;
            }

            public void apply(final Object k, final Object v) {
               int h = MurmurHash3$.MODULE$.tuple2Hash(k, v);
               this.a_$eq(this.a() + h);
               this.b_$eq(this.b() ^ h);
               this.c_$eq(this.c() * (h | 1));
               this.n_$eq(this.n() + 1);
            }

            public accum$1() {
            }
         }

         accum$1 accum = new accum$1();
         int h = this.mapSeed();
         xs.foreachEntry(accum);
         h = this.mix(h, accum.a());
         h = this.mix(h, accum.b());
         h = this.mixLast(h, accum.c());
         int finalizeHash_length = accum.n();
         return ((MurmurHash3)this).scala$util$hashing$MurmurHash3$$avalanche(h ^ finalizeHash_length);
      }
   }

   public int emptyMapHash() {
      return emptyMapHash;
   }

   public int setHash(final Set xs) {
      return this.unorderedHash(xs, this.setSeed());
   }

   public MurmurHash3.ArrayHashing arrayHashing() {
      return new MurmurHash3.ArrayHashing();
   }

   public Hashing bytesHashing() {
      return new Hashing() {
         public int hash(final byte[] data) {
            return MurmurHash3$.MODULE$.bytesHash(data, 1007110753);
         }
      };
   }

   public Hashing orderedHashing() {
      return new Hashing() {
         public int hash(final IterableOnce xs) {
            return MurmurHash3$.MODULE$.orderedHash(xs, -1248659538);
         }
      };
   }

   public Hashing productHashing() {
      return new Hashing() {
         public int hash(final Product x) {
            return MurmurHash3$.MODULE$.productHash(x);
         }
      };
   }

   public Hashing stringHashing() {
      return new Hashing() {
         public int hash(final String x) {
            return MurmurHash3$.MODULE$.stringHash(x, -137723950);
         }
      };
   }

   public Hashing unorderedHashing() {
      return new Hashing() {
         public int hash(final IterableOnce xs) {
            return MurmurHash3$.MODULE$.unorderedHash(xs, -415593707);
         }
      };
   }

   public int arrayHash$mZc$sp(final boolean[] a) {
      return this.arrayHash$mZc$sp(a, 1007110753);
   }

   public int arrayHash$mBc$sp(final byte[] a) {
      return this.arrayHash$mBc$sp(a, 1007110753);
   }

   public int arrayHash$mCc$sp(final char[] a) {
      return this.arrayHash$mCc$sp(a, 1007110753);
   }

   public int arrayHash$mDc$sp(final double[] a) {
      return this.arrayHash$mDc$sp(a, 1007110753);
   }

   public int arrayHash$mFc$sp(final float[] a) {
      return this.arrayHash$mFc$sp(a, 1007110753);
   }

   public int arrayHash$mIc$sp(final int[] a) {
      return this.arrayHash$mIc$sp(a, 1007110753);
   }

   public int arrayHash$mJc$sp(final long[] a) {
      return this.arrayHash$mJc$sp(a, 1007110753);
   }

   public int arrayHash$mSc$sp(final short[] a) {
      return this.arrayHash$mSc$sp(a, 1007110753);
   }

   public int arrayHash$mVc$sp(final BoxedUnit[] a) {
      return this.arrayHash$mVc$sp(a, 1007110753);
   }

   public int arraySeqHash$mZc$sp(final boolean[] a) {
      return this.arrayHash$mZc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mBc$sp(final byte[] a) {
      return this.arrayHash$mBc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mCc$sp(final char[] a) {
      return this.arrayHash$mCc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mDc$sp(final double[] a) {
      return this.arrayHash$mDc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mFc$sp(final float[] a) {
      return this.arrayHash$mFc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mIc$sp(final int[] a) {
      return this.arrayHash$mIc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mJc$sp(final long[] a) {
      return this.arrayHash$mJc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mSc$sp(final short[] a) {
      return this.arrayHash$mSc$sp(a, this.seqSeed());
   }

   public int arraySeqHash$mVc$sp(final BoxedUnit[] a) {
      return this.arrayHash$mVc$sp(a, this.seqSeed());
   }

   public MurmurHash3.ArrayHashing arrayHashing$mZc$sp() {
      return new MurmurHash3$ArrayHashing$mcZ$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mBc$sp() {
      return new MurmurHash3$ArrayHashing$mcB$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mCc$sp() {
      return new MurmurHash3$ArrayHashing$mcC$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mDc$sp() {
      return new MurmurHash3$ArrayHashing$mcD$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mFc$sp() {
      return new MurmurHash3$ArrayHashing$mcF$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mIc$sp() {
      return new MurmurHash3$ArrayHashing$mcI$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mJc$sp() {
      return new MurmurHash3$ArrayHashing$mcJ$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mSc$sp() {
      return new MurmurHash3$ArrayHashing$mcS$sp();
   }

   public MurmurHash3.ArrayHashing arrayHashing$mVc$sp() {
      return new MurmurHash3$ArrayHashing$mcV$sp();
   }

   private MurmurHash3$() {
   }
}
