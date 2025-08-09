package scala.collection.immutable;

import java.util.Arrays;
import scala.MatchError;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c\u0001B\u000b\u0017\ruA\u0001B\t\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\tM\u0001\u0011\t\u0011)A\u0005G!)q\u0005\u0001C\u0001Q!aQ\u0006\u0001C\u0001\u0002\u000b\u0005\t\u0011)A\u0005]!1!\u0007\u0001Q!\n\rBaa\r\u0001!B\u0013\u0019\u0003B\u0002\u001b\u0001A\u0003&1\u0005\u0003\u00076\u0001\u0011\u0005\tQ!A\u0001B\u0013%a\u0007\u0003\u0007>\u0001\u0011\u0005\tQ!A\u0001B\u0013%a\bC\u0003B\u0001\u0011\u0005!\t\u0003\u0004T\u0001\u0001&I\u0001\u0016\u0005\u0007;\u0002\u0001K\u0011\u00020\t\u000b\u0015\u0004A\u0011\u00014\t\rI\u0004\u0001\u0015\"\u0003t\u0011\u0019Y\b\u0001)C\u0005y\"A\u0011\u0011\u0002\u0001!\n\u0013\tY\u0001\u0003\u0005\u0002\u001c\u0001\u0001K\u0011BA\u000f\u0011!\t\t\u0003\u0001Q\u0005\n\u0005\r\u0002bBA\u0014\u0001\u0011\u0005\u0013\u0011\u0006\u0005\t\u0003\u0003\u0002A\u0011\u0001\f\u0002D\t\u0011b+Z2u_J\u001cF.[2f\u0005VLG\u000eZ3s\u0015\t9\u0002$A\u0005j[6,H/\u00192mK*\u0011\u0011DG\u0001\u000bG>dG.Z2uS>t'\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\b\t\u0003?\u0001j\u0011AG\u0005\u0003Ci\u0011a!\u00118z%\u00164\u0017A\u00017p!\tyB%\u0003\u0002&5\t\u0019\u0011J\u001c;\u0002\u0005!L\u0017A\u0002\u001fj]&$h\bF\u0002*W1\u0002\"A\u000b\u0001\u000e\u0003YAQAI\u0002A\u0002\rBQAJ\u0002A\u0002\r\nQg]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%S6lW\u000f^1cY\u0016$c+Z2u_J\u001cF.[2f\u0005VLG\u000eZ3sI\u0011\u001aH.[2fgB\u0019qdL\u0019\n\u0005AR\"!B!se\u0006L\bcA\u00100=\u0005\u0019A.\u001a8\u0002\u0007A|7/\u0001\u0004nCb$\u0015.\\\u00019g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$\u0013.\\7vi\u0006\u0014G.\u001a\u0013WK\u000e$xN]*mS\u000e,')^5mI\u0016\u0014H\u0005\n9sK\u001aL\u00070\u00133y)\t\u0019s\u0007C\u00039\u0011\u0001\u00071%A\u0001oQ\tA!\b\u0005\u0002 w%\u0011AH\u0007\u0002\u0007S:d\u0017N\\3\u0002qM\u001c\u0017\r\\1%G>dG.Z2uS>tG%[7nkR\f'\r\\3%-\u0016\u001cGo\u001c:TY&\u001cWMQ;jY\u0012,'\u000f\n\u0013tk\u001a4\u0017\u000e_%eqR\u00111e\u0010\u0005\u0006q%\u0001\ra\t\u0015\u0003\u0013i\n\u0001bY8og&$WM]\u000b\u0003\u00076#2\u0001R$I!\tyR)\u0003\u0002G5\t!QK\\5u\u0011\u0015A$\u00021\u0001$\u0011\u0015I%\u00021\u0001K\u0003\u0005\t\u0007cA\u00100\u0017B\u0011A*\u0014\u0007\u0001\t\u0015q%B1\u0001P\u0005\u0005!\u0016C\u0001)\u001f!\ty\u0012+\u0003\u0002S5\t9aj\u001c;iS:<\u0017\u0001C1eINc\u0017nY3\u0016\u0005USF#\u0002#W/nc\u0006\"\u0002\u001d\f\u0001\u0004\u0019\u0003\"B%\f\u0001\u0004A\u0006cA\u001003B\u0011AJ\u0017\u0003\u0006\u001d.\u0011\ra\u0014\u0005\u0006E-\u0001\ra\t\u0005\u0006M-\u0001\raI\u0001\u0004C\u0012$WCA0e)\r!\u0005-\u0019\u0005\u0006q1\u0001\ra\t\u0005\u0006\u00132\u0001\rA\u0019\t\u0004?=\u001a\u0007C\u0001'e\t\u0015qEB1\u0001P\u0003\u0019\u0011Xm];miV\u0011q\r\u001c\u000b\u0002QB\u0019!&[6\n\u0005)4\"A\u0002,fGR|'\u000f\u0005\u0002MY\u0012)Q.\u0004b\u0001]\n\t\u0011)\u0005\u0002Q_B\u0011q\u0004]\u0005\u0003cj\u00111!\u00118z\u0003!\u0001(/\u001a4jq>\u0013XC\u0001;x)\r)\b0\u001f\t\u0004?=2\bC\u0001'x\t\u0015qeB1\u0001P\u0011\u0015Ad\u00021\u0001$\u0011\u0015Ie\u00021\u0001vQ\tq!(\u0001\u0005tk\u001a4\u0017\u000e_(s+\ri\u0018\u0011\u0001\u000b\u0006}\u0006\r\u0011Q\u0001\t\u0004?=z\bc\u0001'\u0002\u0002\u0011)aj\u0004b\u0001\u001f\")\u0001h\u0004a\u0001G!)\u0011j\u0004a\u0001}\"\u0012qBO\u0001\u0007I\u0006$\u0018m\u0014:\u0016\t\u00055\u00111\u0003\u000b\u0007\u0003\u001f\t)\"a\u0006\u0011\t}y\u0013\u0011\u0003\t\u0004\u0019\u0006MA!\u0002(\u0011\u0005\u0004y\u0005\"\u0002\u001d\u0011\u0001\u0004\u0019\u0003BB%\u0011\u0001\u0004\ty\u0001\u000b\u0002\u0011u\u0005i!-\u00197b]\u000e,\u0007K]3gSb$2\u0001RA\u0010\u0011\u0015A\u0014\u00031\u0001$\u00035\u0011\u0017\r\\1oG\u0016\u001cVO\u001a4jqR\u0019A)!\n\t\u000ba\u0012\u0002\u0019A\u0012\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u000b\u0011\t\u00055\u00121\b\b\u0005\u0003_\t9\u0004E\u0002\u00022ii!!a\r\u000b\u0007\u0005UB$\u0001\u0004=e>|GOP\u0005\u0004\u0003sQ\u0012A\u0002)sK\u0012,g-\u0003\u0003\u0002>\u0005}\"AB*ue&twMC\u0002\u0002:i\t\u0011bZ3u'2L7-Z:\u0016\u00039\u0002"
)
public final class VectorSliceBuilder {
   private final int lo;
   private final int hi;
   public final Object[][] scala$collection$immutable$VectorSliceBuilder$$slices;
   private int len;
   private int pos;
   private int maxDim;

   public int scala$collection$immutable$VectorSliceBuilder$$prefixIdx(final int n) {
      return n - 1;
   }

   public int scala$collection$immutable$VectorSliceBuilder$$suffixIdx(final int n) {
      return 11 - n;
   }

   public void consider(final int n, final Object[] a) {
      int count = a.length * (1 << 5 * (n - 1));
      int lo0 = Math.max(this.lo - this.pos, 0);
      int hi0 = Math.min(this.hi - this.pos, count);
      if (hi0 > lo0) {
         this.addSlice(n, a, lo0, hi0);
         this.len += hi0 - lo0;
      }

      this.pos += count;
   }

   private void addSlice(final int n, final Object[] a, final int lo, final int hi) {
      while(n != 1) {
         int bitsN = 5 * (n - 1);
         int widthN = 1 << bitsN;
         int loN = lo >>> bitsN;
         int hiN = hi >>> bitsN;
         int loRest = lo & widthN - 1;
         int hiRest = hi & widthN - 1;
         if (loRest == 0) {
            if (hiRest == 0) {
               VectorInline$ var19 = VectorInline$.MODULE$;
               this.add(n, loN == 0 && hiN == a.length ? a : Arrays.copyOfRange(a, loN, hiN));
               return;
            }

            if (hiN > loN) {
               VectorInline$ var18 = VectorInline$.MODULE$;
               this.add(n, loN == 0 && hiN == a.length ? a : Arrays.copyOfRange(a, loN, hiN));
            }

            int var14 = n - 1;
            Object[] var16 = a[hiN];
            hi = hiRest;
            lo = 0;
            a = var16;
            n = var14;
         } else if (hiN == loN) {
            int var13 = n - 1;
            Object[] var15 = a[loN];
            hi = hiRest;
            lo = loRest;
            a = var15;
            n = var13;
         } else {
            this.addSlice(n - 1, a[loN], loRest, widthN);
            if (hiRest == 0) {
               if (hiN <= loN + 1) {
                  return;
               }

               VectorInline$ var17 = VectorInline$.MODULE$;
               int copyOrUse_start = loN + 1;
               this.add(n, copyOrUse_start == 0 && hiN == a.length ? a : Arrays.copyOfRange(a, copyOrUse_start, hiN));
               return;
            }

            if (hiN > loN + 1) {
               VectorInline$ var10002 = VectorInline$.MODULE$;
               int copyOrUse_start = loN + 1;
               this.add(n, copyOrUse_start == 0 && hiN == a.length ? a : Arrays.copyOfRange(a, copyOrUse_start, hiN));
            }

            int var10000 = n - 1;
            Object[] var10001 = a[hiN];
            hi = hiRest;
            lo = 0;
            a = var10001;
            n = var10000;
         }
      }

      VectorInline$ var20 = VectorInline$.MODULE$;
      this.add(1, lo == 0 && hi == a.length ? a : Arrays.copyOfRange(a, lo, hi));
   }

   private void add(final int n, final Object[] a) {
      int var10000;
      if (n <= this.maxDim) {
         var10000 = 11 - n;
      } else {
         this.maxDim = n;
         var10000 = n - 1;
      }

      int idx = var10000;
      this.scala$collection$immutable$VectorSliceBuilder$$slices[idx] = a;
   }

   public Vector result() {
      if (this.len <= 32) {
         if (this.len == 0) {
            return Vector0$.MODULE$;
         } else {
            Object[] prefix1 = this.scala$collection$immutable$VectorSliceBuilder$$slices[1 - 1];
            int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = 1;
            Object[] suffix1 = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n];
            Object[] var216;
            if (prefix1 != null) {
               if (suffix1 != null) {
                  VectorInline$ var215 = VectorInline$.MODULE$;
                  Object[] concatArrays_dest = Arrays.copyOf(prefix1, prefix1.length + suffix1.length);
                  System.arraycopy(suffix1, 0, concatArrays_dest, prefix1.length, suffix1.length);
                  var216 = concatArrays_dest;
                  concatArrays_dest = null;
               } else {
                  var216 = prefix1;
               }
            } else if (suffix1 != null) {
               var216 = suffix1;
            } else {
               Object[][] prefix2 = this.scala$collection$immutable$VectorSliceBuilder$$slices[2 - 1];
               if (prefix2 != null) {
                  var216 = prefix2[0];
               } else {
                  int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = 2;
                  var216 = ((Object[][])this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n])[0];
               }
            }

            Object[] a = var216;
            return new Vector1(a);
         }
      } else {
         this.balancePrefix(1);
         this.balanceSuffix(1);
         int resultDim = this.maxDim;
         if (resultDim < 6) {
            Object[] pre = this.scala$collection$immutable$VectorSliceBuilder$$slices[this.maxDim - 1];
            int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = this.maxDim;
            Object[] suf = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n];
            if (pre != null && suf != null) {
               if (pre.length + suf.length <= 30) {
                  Object[][] var10000 = this.scala$collection$immutable$VectorSliceBuilder$$slices;
                  int var10001 = this.maxDim - 1;
                  VectorInline$ var10002 = VectorInline$.MODULE$;
                  Object[] concatArrays_dest = Arrays.copyOf(pre, pre.length + suf.length);
                  System.arraycopy(suf, 0, concatArrays_dest, pre.length, suf.length);
                  Object var134 = null;
                  var10000[var10001] = concatArrays_dest;
                  int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = this.maxDim;
                  this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n] = null;
               } else {
                  ++resultDim;
               }
            } else if ((pre != null ? pre : suf).length > 30) {
               ++resultDim;
            }
         }

         Object[] prefix1 = this.scala$collection$immutable$VectorSliceBuilder$$slices[1 - 1];
         int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = 1;
         Object[] suffix1 = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n];
         int len1 = prefix1.length;
         switch (resultDim) {
            case 2:
               Object[][] dataOr_a = VectorStatics$.MODULE$.empty2();
               int dataOr_n = 2;
               Object[] dataOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[dataOr_n - 1];
               Object var214;
               if (dataOr_p != null) {
                  var214 = dataOr_p;
               } else {
                  Object[] dataOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - dataOr_n];
                  var214 = dataOr_s != null ? dataOr_s : dataOr_a;
               }

               dataOr_a = null;
               dataOr_p = null;
               Object var137 = null;
               Object[][] data2 = var214;
               return new Vector2(prefix1, len1, data2, suffix1, this.len);
            case 3:
               Object[][] prefixOr_a = VectorStatics$.MODULE$.empty2();
               int prefixOr_n = 2;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               Object var211 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][] prefix2 = var211;
               Object[][][] dataOr_a = VectorStatics$.MODULE$.empty3();
               int dataOr_n = 3;
               Object[] dataOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[dataOr_n - 1];
               if (dataOr_p != null) {
                  var211 = dataOr_p;
               } else {
                  Object[] dataOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - dataOr_n];
                  var211 = dataOr_s != null ? dataOr_s : dataOr_a;
               }

               dataOr_a = null;
               dataOr_p = null;
               Object var142 = null;
               Object[][][] data3 = var211;
               Object[][] suffixOr_a = VectorStatics$.MODULE$.empty2();
               int suffixOr_n = 2;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var211 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][] suffix2 = var211;
               int len12 = len1 + prefix2.length * 32;
               return new Vector3(prefix1, len1, prefix2, len12, data3, suffix2, suffix1, this.len);
            case 4:
               Object[][] prefixOr_a = VectorStatics$.MODULE$.empty2();
               int prefixOr_n = 2;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               Object var206 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][] prefix2 = var206;
               Object[][][] prefixOr_a = VectorStatics$.MODULE$.empty3();
               int prefixOr_n = 3;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               var206 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][][] prefix3 = var206;
               Object[][][][] dataOr_a = VectorStatics$.MODULE$.empty4();
               int dataOr_n = 4;
               Object[] dataOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[dataOr_n - 1];
               if (dataOr_p != null) {
                  var206 = dataOr_p;
               } else {
                  Object[] dataOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - dataOr_n];
                  var206 = dataOr_s != null ? dataOr_s : dataOr_a;
               }

               dataOr_a = null;
               dataOr_p = null;
               Object var151 = null;
               Object[][][][] data4 = var206;
               Object[][][] suffixOr_a = VectorStatics$.MODULE$.empty3();
               int suffixOr_n = 3;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var206 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][][] suffix3 = var206;
               Object[][] suffixOr_a = VectorStatics$.MODULE$.empty2();
               int suffixOr_n = 2;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var206 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][] suffix2 = var206;
               int len12 = len1 + prefix2.length * 32;
               int len123 = len12 + prefix3.length * 1024;
               return new Vector4(prefix1, len1, prefix2, len12, prefix3, len123, data4, suffix3, suffix2, suffix1, this.len);
            case 5:
               Object[][] prefixOr_a = VectorStatics$.MODULE$.empty2();
               int prefixOr_n = 2;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               Object var199 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][] prefix2 = var199;
               Object[][][] prefixOr_a = VectorStatics$.MODULE$.empty3();
               int prefixOr_n = 3;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               var199 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][][] prefix3 = var199;
               Object[][][][] prefixOr_a = VectorStatics$.MODULE$.empty4();
               int prefixOr_n = 4;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               var199 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][][][] prefix4 = var199;
               Object[][][][][] dataOr_a = VectorStatics$.MODULE$.empty5();
               int dataOr_n = 5;
               Object[] dataOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[dataOr_n - 1];
               if (dataOr_p != null) {
                  var199 = dataOr_p;
               } else {
                  Object[] dataOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - dataOr_n];
                  var199 = dataOr_s != null ? dataOr_s : dataOr_a;
               }

               dataOr_a = null;
               dataOr_p = null;
               Object var164 = null;
               Object[][][][][] data5 = var199;
               Object[][][][] suffixOr_a = VectorStatics$.MODULE$.empty4();
               int suffixOr_n = 4;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var199 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][][][] suffix4 = var199;
               Object[][][] suffixOr_a = VectorStatics$.MODULE$.empty3();
               int suffixOr_n = 3;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var199 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][][] suffix3 = var199;
               Object[][] suffixOr_a = VectorStatics$.MODULE$.empty2();
               int suffixOr_n = 2;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var199 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][] suffix2 = var199;
               int len12 = len1 + prefix2.length * 32;
               int len123 = len12 + prefix3.length * 1024;
               int len1234 = len123 + prefix4.length * '耀';
               return new Vector5(prefix1, len1, prefix2, len12, prefix3, len123, prefix4, len1234, data5, suffix4, suffix3, suffix2, suffix1, this.len);
            case 6:
               Object[][] prefixOr_a = VectorStatics$.MODULE$.empty2();
               int prefixOr_n = 2;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               Object var190 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][] prefix2 = var190;
               Object[][][] prefixOr_a = VectorStatics$.MODULE$.empty3();
               int prefixOr_n = 3;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               var190 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][][] prefix3 = var190;
               Object[][][][] prefixOr_a = VectorStatics$.MODULE$.empty4();
               int prefixOr_n = 4;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               var190 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][][][] prefix4 = var190;
               Object[][][][][] prefixOr_a = VectorStatics$.MODULE$.empty5();
               int prefixOr_n = 5;
               Object[] prefixOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[prefixOr_n - 1];
               var190 = prefixOr_p != null ? prefixOr_p : prefixOr_a;
               prefixOr_a = null;
               prefixOr_p = null;
               Object[][][][][] prefix5 = var190;
               Object[][][][][][] dataOr_a = VectorStatics$.MODULE$.empty6();
               int dataOr_n = 6;
               Object[] dataOr_p = this.scala$collection$immutable$VectorSliceBuilder$$slices[dataOr_n - 1];
               if (dataOr_p != null) {
                  var190 = dataOr_p;
               } else {
                  Object[] dataOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - dataOr_n];
                  var190 = dataOr_s != null ? dataOr_s : dataOr_a;
               }

               dataOr_a = null;
               dataOr_p = null;
               Object var181 = null;
               Object[][][][][][] data6 = var190;
               Object[][][][][] suffixOr_a = VectorStatics$.MODULE$.empty5();
               int suffixOr_n = 5;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var190 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][][][][] suffix5 = var190;
               Object[][][][] suffixOr_a = VectorStatics$.MODULE$.empty4();
               int suffixOr_n = 4;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var190 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][][][] suffix4 = var190;
               Object[][][] suffixOr_a = VectorStatics$.MODULE$.empty3();
               int suffixOr_n = 3;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var190 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][][] suffix3 = var190;
               Object[][] suffixOr_a = VectorStatics$.MODULE$.empty2();
               int suffixOr_n = 2;
               Object[] suffixOr_s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - suffixOr_n];
               var190 = suffixOr_s != null ? suffixOr_s : suffixOr_a;
               suffixOr_a = null;
               suffixOr_s = null;
               Object[][] suffix2 = var190;
               int len12 = len1 + prefix2.length * 32;
               int len123 = len12 + prefix3.length * 1024;
               int len1234 = len123 + prefix4.length * '耀';
               int len12345 = len1234 + prefix5.length * 1048576;
               return new Vector6(prefix1, len1, prefix2, len12, prefix3, len123, prefix4, len1234, prefix5, len12345, data6, suffix5, suffix4, suffix3, suffix2, suffix1, this.len);
            default:
               throw new MatchError(resultDim);
         }
      }
   }

   private Object[] prefixOr(final int n, final Object[] a) {
      Object[] p = this.scala$collection$immutable$VectorSliceBuilder$$slices[n - 1];
      return p != null ? p : a;
   }

   private Object[] suffixOr(final int n, final Object[] a) {
      Object[] s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - n];
      return s != null ? s : a;
   }

   private Object[] dataOr(final int n, final Object[] a) {
      Object[] p = this.scala$collection$immutable$VectorSliceBuilder$$slices[n - 1];
      if (p != null) {
         return p;
      } else {
         Object[] s = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - n];
         return s != null ? s : a;
      }
   }

   private void balancePrefix(final int n) {
      if (this.scala$collection$immutable$VectorSliceBuilder$$slices[n - 1] == null) {
         if (n == this.maxDim) {
            this.scala$collection$immutable$VectorSliceBuilder$$slices[n - 1] = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - n];
            this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - n] = null;
         } else {
            this.balancePrefix(n + 1);
            Object[][] preN1 = this.scala$collection$immutable$VectorSliceBuilder$$slices[n + 1 - 1];
            this.scala$collection$immutable$VectorSliceBuilder$$slices[n - 1] = preN1[0];
            if (preN1.length == 1) {
               this.scala$collection$immutable$VectorSliceBuilder$$slices[n + 1 - 1] = null;
               if (this.maxDim == n + 1) {
                  int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = n + 1;
                  if (this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n] == null) {
                     this.maxDim = n;
                     return;
                  }
               }

            } else {
               this.scala$collection$immutable$VectorSliceBuilder$$slices[n + 1 - 1] = Arrays.copyOfRange(preN1, 1, preN1.length);
            }
         }
      }
   }

   private void balanceSuffix(final int n) {
      if (this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - n] == null) {
         if (n == this.maxDim) {
            this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - n] = this.scala$collection$immutable$VectorSliceBuilder$$slices[n - 1];
            this.scala$collection$immutable$VectorSliceBuilder$$slices[n - 1] = null;
         } else {
            this.balanceSuffix(n + 1);
            int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = n + 1;
            Object[][] sufN1 = this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n];
            this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - n] = sufN1[sufN1.length - 1];
            if (sufN1.length == 1) {
               int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = n + 1;
               this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n] = null;
               if (this.maxDim == n + 1 && this.scala$collection$immutable$VectorSliceBuilder$$slices[n + 1 - 1] == null) {
                  this.maxDim = n;
               }
            } else {
               int scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n = n + 1;
               this.scala$collection$immutable$VectorSliceBuilder$$slices[11 - scala$collection$immutable$VectorSliceBuilder$$suffixIdx_n] = Arrays.copyOfRange(sufN1, 0, sufN1.length - 1);
            }
         }
      }
   }

   public String toString() {
      return (new StringBuilder(49)).append("VectorSliceBuilder(lo=").append(this.lo).append(", hi=").append(this.hi).append(", len=").append(this.len).append(", pos=").append(this.pos).append(", maxDim=").append(this.maxDim).append(")").toString();
   }

   public Object[][] getSlices() {
      return this.scala$collection$immutable$VectorSliceBuilder$$slices;
   }

   public VectorSliceBuilder(final int lo, final int hi) {
      this.lo = lo;
      this.hi = hi;
      this.scala$collection$immutable$VectorSliceBuilder$$slices = new Object[11][];
      this.len = 0;
      this.pos = 0;
      this.maxDim = 0;
   }
}
