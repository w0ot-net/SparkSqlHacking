package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function1;
import scala.MatchError;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.ReusableBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g\u0001B\u0012%\u0005-BQA\u0012\u0001\u0005\u0002\u001dC\u0011\"\u0013\u0001A\u0002\u0003\u0005\u000b\u0015\u0002&\t\u0013i\u0003\u0001\u0019!A!B\u0013Y\u0006\"\u00030\u0001\u0001\u0004\u0005\t\u0015)\u0003`\u0011%\u0011\u0007\u00011A\u0001B\u0003&1\rC\u0005g\u0001\u0001\u0007\t\u0011)Q\u0005O\"1!\u000e\u0001Q!\n-DAB\u001c\u0001\u0005\u0002\u0003\u0015\t\u0011!Q!\n=DAB\u001d\u0001\u0005\u0002\u0003\u0015\t\u0011!Q!\n=DAb\u001d\u0001\u0005\u0002\u0003\u0015\t\u0011!Q!\n=Da\u0001\u001e\u0001!B\u0013)\bB\u0002=\u0001A\u0003&q\u000e\u0003\u0004z\u0001\u0001&iA\u001f\u0005\b\u0003\u0013\u0001A\u0011IA\u0006\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u0017Aq!!\u0005\u0001\t\u0003\t\u0019\u0002C\u0004\u0002\u0018\u0001!\t!a\u0005\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e!A\u0011q\u0004\u0001\u0005\u0002\u0011\n\t\u0003\u0003\u0005\u0002*\u0001!\t\u0001JA\u0016\u0011!\tI\u0003\u0001C\u0001I\u0005E\u0002\u0002CA\"\u0001\u0011\u0005a%!\u0012\t\u0011\u0005=\u0003\u0001)C\u0005\u0003;Aq!!\u0015\u0001\t\u0003\t\u0019\u0006\u0003\u0005\u0002X\u0001\u0001K\u0011BA-\u0011!\ty\u0006\u0001Q\u0005\n\u0005\u0005\u0004\u0002CA9\u0001\u0001&I!a\u001d\t\u000f\u0005e\u0004\u0001\"\u0011\u0002|!A\u0011q\u0011\u0001!\n\u0013\ti\u0002\u0003\u0005\u0002\n\u0002\u0001K\u0011BAF\u0011!\t\t\n\u0001Q\u0005\n\u0005M\u0005bBAO\u0001\u0011\u0005\u0011q\u0014\u0005\b\u0003C\u0003A\u0011IAR\u0011!\t)\f\u0001C\u0001I\u0005]&!\u0004,fGR|'OQ;jY\u0012,'O\u0003\u0002&M\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003O!\n!bY8mY\u0016\u001cG/[8o\u0015\u0005I\u0013!B:dC2\f7\u0001A\u000b\u0003Ye\u001a2\u0001A\u00172!\tqs&D\u0001)\u0013\t\u0001\u0004F\u0001\u0004B]f\u0014VM\u001a\t\u0005eU:$)D\u00014\u0015\t!d%A\u0004nkR\f'\r\\3\n\u0005Y\u001a$a\u0004*fkN\f'\r\\3Ck&dG-\u001a:\u0011\u0005aJD\u0002\u0001\u0003\u0006u\u0001\u0011\ra\u000f\u0002\u0002\u0003F\u0011Ah\u0010\t\u0003]uJ!A\u0010\u0015\u0003\u000f9{G\u000f[5oOB\u0011a\u0006Q\u0005\u0003\u0003\"\u00121!\u00118z!\r\u0019EiN\u0007\u0002I%\u0011Q\t\n\u0002\u0007-\u0016\u001cGo\u001c:\u0002\rqJg.\u001b;?)\u0005A\u0005cA\"\u0001o\u0005\u0011\u0011M\u000e\t\u0003\u0017^s!\u0001T+\u000f\u00055#fB\u0001(T\u001d\ty%+D\u0001Q\u0015\t\t&&\u0001\u0004=e>|GOP\u0005\u0002S%\u0011q\u0005K\u0005\u0003K\u0019J!A\u0016\u0013\u0002\u0019Y+7\r^8s\u0013:d\u0017N\\3\n\u0005aK&\u0001B!seZR!A\u0016\u0013\u0002\u0005\u0005,\u0004CA&]\u0013\ti\u0016L\u0001\u0003BeJ,\u0014AA15!\tY\u0005-\u0003\u0002b3\n!\u0011I\u001d:5\u0003\t\t7\u0007\u0005\u0002LI&\u0011Q-\u0017\u0002\u0005\u0003J\u00148'\u0001\u0002beA\u00111\n[\u0005\u0003Sf\u0013A!\u0011:se\u0005\u0011\u0011-\r\t\u0003\u00172L!!\\-\u0003\t\u0005\u0013(/M\u0001/g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$\u0013.\\7vi\u0006\u0014G.\u001a\u0013WK\u000e$xN\u001d\"vS2$WM\u001d\u0013%Y\u0016t\u0017\u0007\u0005\u0002/a&\u0011\u0011\u000f\u000b\u0002\u0004\u0013:$\u0018!M:dC2\fGeY8mY\u0016\u001cG/[8oI%lW.\u001e;bE2,GEV3di>\u0014()^5mI\u0016\u0014H\u0005\n7f]J+7\u000f^\u00011g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$\u0013.\\7vi\u0006\u0014G.\u001a\u0013WK\u000e$xN\u001d\"vS2$WM\u001d\u0013%_\u001a47/\u001a;\u0002)A\u0014XMZ5y\u0013N\u0014\u0016n\u001a5u\u00032LwM\\3e!\tqc/\u0003\u0002xQ\t9!i\\8mK\u0006t\u0017!\u00023faRD\u0017AB:fi2+g\u000e\u0006\u0002|}B\u0011a\u0006`\u0005\u0003{\"\u0012A!\u00168ji\")q0\u0004a\u0001_\u0006\t\u0011\u000eK\u0002\u000e\u0003\u0007\u00012ALA\u0003\u0013\r\t9\u0001\u000b\u0002\u0007S:d\u0017N\\3\u0002\u0013-twn\u001e8TSj,W#A8\u0002\tML'0\u001a\u0015\u0004\u001f\u0005\r\u0011aB5t\u000b6\u0004H/_\u000b\u0002k\"\u001a\u0001#a\u0001\u0002\u00119|g.R7qifD3!EA\u0002\u0003\u0015\u0019G.Z1s)\u0005Y\u0018AC5oSR\u001c\u0006/\u0019:tKR)10a\t\u0002&!1\u0011QB\nA\u0002=Da!a\n\u0014\u0001\u00049\u0014\u0001B3mK6\f\u0001\"\u001b8ji\u001a\u0013x.\u001c\u000b\u0004w\u00065\u0002BBA\u0018)\u0001\u00071.A\u0004qe\u00164\u0017\u000e_\u0019\u0015\t\u0005M\u0012QG\u0007\u0002\u0001!9\u0011qG\u000bA\u0002\u0005e\u0012!\u0001<1\t\u0005m\u0012q\b\t\u0005\u0007\u0012\u000bi\u0004E\u00029\u0003\u007f!1\"!\u0011\u00026\u0005\u0005\t\u0011!B\u0001w\t!q\fJ\u0019:\u0003\u001d\tG.[4o)>$b!a\r\u0002H\u0005-\u0003BBA%-\u0001\u0007q.\u0001\u0004cK\u001a|'/\u001a\u0005\u0007\u0003\u001b2\u0002\u0019\u0001\"\u0002\u0013\tLwMV3di>\u0014\u0018a\u00047fMR\fE.[4o!J,g-\u001b=\u0002\r\u0005$Gm\u00148f)\u0011\t\u0019$!\u0016\t\r\u0005\u001d\u0002\u00041\u00018\u0003\u001d\tG\rZ!seF\"2a_A.\u0011\u0019\ti&\u0007a\u0001W\u0006!A-\u0019;b\u0003\u001d\tG\rZ!se:#Ra_A2\u0003[Bq!!\u001a\u001b\u0001\u0004\t9'A\u0003tY&\u001cW\r\u0005\u0003/\u0003Sj\u0013bAA6Q\t)\u0011I\u001d:bs\"1\u0011q\u000e\u000eA\u0002=\f1\u0001Z5n\u0003%\tG\r\u001a,fGR|'\u000f\u0006\u0003\u00024\u0005U\u0004BBA<7\u0001\u0007!)\u0001\u0002yg\u00061\u0011\r\u001a3BY2$B!a\r\u0002~!9\u0011q\u000f\u000fA\u0002\u0005}\u0004#BAA\u0003\u0007;T\"\u0001\u0014\n\u0007\u0005\u0015eE\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-A\u0004bIZ\fgnY3\u0002\u0011\u0005$g/\u00198dK:#2a_AG\u0011\u0019\tyI\ba\u0001_\u0006\ta.\u0001\u0005bIZ\fgnY32)\u0015Y\u0018QSAM\u0011\u0019\t9j\ba\u0001_\u0006\u0019\u0011\u000e\u001a=\t\r\u0005mu\u00041\u0001p\u0003\rAxN]\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u0003\t\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003K\u0003B!a*\u00020:!\u0011\u0011VAV!\ty\u0005&C\u0002\u0002.\"\na\u0001\u0015:fI\u00164\u0017\u0002BAY\u0003g\u0013aa\u0015;sS:<'bAAWQ\u00059q-\u001a;ECR\fWCAA]!\u0015q\u0013\u0011NA^a\u0011\ti,!1\u0011\u000b9\nI'a0\u0011\u0007a\n\t\r\u0002\u0006\u0002D\n\n\t\u0011!A\u0003\u0002m\u0012Aa\u0018\u00133m\u0001"
)
public final class VectorBuilder implements ReusableBuilder {
   private Object[][][][][][] a6;
   private Object[][][][][] a5;
   private Object[][][][] a4;
   private Object[][][] a3;
   private Object[][] a2;
   private Object[] a1 = new Object[32];
   public int scala$collection$immutable$VectorBuilder$$len1 = 0;
   public int scala$collection$immutable$VectorBuilder$$lenRest = 0;
   public int scala$collection$immutable$VectorBuilder$$offset = 0;
   private boolean prefixIsRightAligned = false;
   private int depth = 1;

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   private final void setLen(final int i) {
      this.scala$collection$immutable$VectorBuilder$$len1 = i & 31;
      this.scala$collection$immutable$VectorBuilder$$lenRest = i - this.scala$collection$immutable$VectorBuilder$$len1;
   }

   public int knownSize() {
      return this.scala$collection$immutable$VectorBuilder$$len1 + this.scala$collection$immutable$VectorBuilder$$lenRest - this.scala$collection$immutable$VectorBuilder$$offset;
   }

   public int size() {
      return this.knownSize();
   }

   public boolean isEmpty() {
      return this.knownSize() == 0;
   }

   public boolean nonEmpty() {
      return this.knownSize() != 0;
   }

   public void clear() {
      this.a6 = null;
      this.a5 = null;
      this.a4 = null;
      this.a3 = null;
      this.a2 = null;
      this.a1 = new Object[32];
      this.scala$collection$immutable$VectorBuilder$$len1 = 0;
      this.scala$collection$immutable$VectorBuilder$$lenRest = 0;
      this.scala$collection$immutable$VectorBuilder$$offset = 0;
      this.prefixIsRightAligned = false;
      this.depth = 1;
   }

   public void initSparse(final int size, final Object elem) {
      this.scala$collection$immutable$VectorBuilder$$len1 = size & 31;
      this.scala$collection$immutable$VectorBuilder$$lenRest = size - this.scala$collection$immutable$VectorBuilder$$len1;
      Arrays.fill(this.a1, elem);
      if (size > 32) {
         this.a2 = new Object[32][];
         Arrays.fill(this.a2, this.a1);
         if (size > 1024) {
            this.a3 = new Object[32][][];
            Arrays.fill(this.a3, this.a2);
            if (size > 32768) {
               this.a4 = new Object[32][][][];
               Arrays.fill(this.a4, this.a3);
               if (size > 1048576) {
                  this.a5 = new Object[32][][][][];
                  Arrays.fill(this.a5, this.a4);
                  if (size > 33554432) {
                     this.a6 = new Object[64][][][][][];
                     Arrays.fill(this.a6, this.a5);
                     this.depth = 6;
                  } else {
                     this.depth = 5;
                  }
               } else {
                  this.depth = 4;
               }
            } else {
               this.depth = 3;
            }
         } else {
            this.depth = 2;
         }
      } else {
         this.depth = 1;
      }
   }

   public void initFrom(final Object[] prefix1) {
      this.depth = 1;
      int setLen_i = prefix1.length;
      this.scala$collection$immutable$VectorBuilder$$len1 = setLen_i & 31;
      this.scala$collection$immutable$VectorBuilder$$lenRest = setLen_i - this.scala$collection$immutable$VectorBuilder$$len1;
      VectorInline$ var10001 = VectorInline$.MODULE$;
      byte copyOrUse_end = 32;
      int copyOrUse_start = 0;
      this.a1 = copyOrUse_start == 0 && copyOrUse_end == prefix1.length ? prefix1 : Arrays.copyOfRange(prefix1, copyOrUse_start, copyOrUse_end);
      if (this.scala$collection$immutable$VectorBuilder$$len1 == 0 && this.scala$collection$immutable$VectorBuilder$$lenRest > 0) {
         this.scala$collection$immutable$VectorBuilder$$len1 = 32;
         this.scala$collection$immutable$VectorBuilder$$lenRest -= 32;
      }
   }

   public VectorBuilder initFrom(final Vector v) {
      int var2 = v.vectorSliceCount();
      switch (var2) {
         case 0:
            break;
         case 1:
            Vector1 v1 = (Vector1)v;
            this.depth = 1;
            int setLen_i = v1.prefix1().length;
            this.scala$collection$immutable$VectorBuilder$$len1 = setLen_i & 31;
            this.scala$collection$immutable$VectorBuilder$$lenRest = setLen_i - this.scala$collection$immutable$VectorBuilder$$len1;
            VectorInline$ var68 = VectorInline$.MODULE$;
            var68 = v1.prefix1();
            byte copyOrUse_end = 32;
            byte copyOrUse_start = 0;
            Object[] copyOrUse_a = var68;
            var68 = copyOrUse_start == 0 && copyOrUse_end == copyOrUse_a.length ? copyOrUse_a : Arrays.copyOfRange(copyOrUse_a, copyOrUse_start, copyOrUse_end);
            copyOrUse_a = null;
            this.a1 = var68;
            break;
         case 3:
            Vector2 v2 = (Vector2)v;
            Object[][] d2 = v2.data2();
            VectorInline$ var65 = VectorInline$.MODULE$;
            var65 = v2.suffix1();
            byte copyOrUse_end = 32;
            byte copyOrUse_start = 0;
            Object[] copyOrUse_a = var65;
            var65 = copyOrUse_start == 0 && copyOrUse_end == copyOrUse_a.length ? copyOrUse_a : Arrays.copyOfRange(copyOrUse_a, copyOrUse_start, copyOrUse_end);
            copyOrUse_a = null;
            this.a1 = var65;
            this.depth = 2;
            this.scala$collection$immutable$VectorBuilder$$offset = 32 - v2.len1();
            int setLen_i = v2.length0() + this.scala$collection$immutable$VectorBuilder$$offset;
            this.scala$collection$immutable$VectorBuilder$$len1 = setLen_i & 31;
            this.scala$collection$immutable$VectorBuilder$$lenRest = setLen_i - this.scala$collection$immutable$VectorBuilder$$len1;
            this.a2 = new Object[32][];
            this.a2[0] = v2.prefix1();
            System.arraycopy(d2, 0, this.a2, 1, d2.length);
            this.a2[d2.length + 1] = this.a1;
            break;
         case 5:
            Vector3 v3 = (Vector3)v;
            Object[][][] d3 = v3.data3();
            Object[][] s2 = v3.suffix2();
            VectorInline$ var62 = VectorInline$.MODULE$;
            var62 = v3.suffix1();
            byte copyOrUse_end = 32;
            byte copyOrUse_start = 0;
            Object[] copyOrUse_a = var62;
            var62 = copyOrUse_start == 0 && copyOrUse_end == copyOrUse_a.length ? copyOrUse_a : Arrays.copyOfRange(copyOrUse_a, copyOrUse_start, copyOrUse_end);
            copyOrUse_a = null;
            this.a1 = var62;
            this.depth = 3;
            this.scala$collection$immutable$VectorBuilder$$offset = 1024 - v3.len12();
            int setLen_i = v3.length0() + this.scala$collection$immutable$VectorBuilder$$offset;
            this.scala$collection$immutable$VectorBuilder$$len1 = setLen_i & 31;
            this.scala$collection$immutable$VectorBuilder$$lenRest = setLen_i - this.scala$collection$immutable$VectorBuilder$$len1;
            this.a3 = new Object[32][][];
            this.a3[0] = VectorStatics$.MODULE$.copyPrepend(v3.prefix1(), v3.prefix2());
            System.arraycopy(d3, 0, this.a3, 1, d3.length);
            this.a2 = Arrays.copyOf(s2, 32);
            this.a3[d3.length + 1] = this.a2;
            this.a2[s2.length] = this.a1;
            break;
         case 7:
            Vector4 v4 = (Vector4)v;
            Object[][][][] d4 = v4.data4();
            Object[][][] s3 = v4.suffix3();
            Object[][] s2 = v4.suffix2();
            VectorInline$ var59 = VectorInline$.MODULE$;
            var59 = v4.suffix1();
            byte copyOrUse_end = 32;
            byte copyOrUse_start = 0;
            Object[] copyOrUse_a = var59;
            var59 = copyOrUse_start == 0 && copyOrUse_end == copyOrUse_a.length ? copyOrUse_a : Arrays.copyOfRange(copyOrUse_a, copyOrUse_start, copyOrUse_end);
            copyOrUse_a = null;
            this.a1 = var59;
            this.depth = 4;
            this.scala$collection$immutable$VectorBuilder$$offset = '耀' - v4.len123();
            int setLen_i = v4.length0() + this.scala$collection$immutable$VectorBuilder$$offset;
            this.scala$collection$immutable$VectorBuilder$$len1 = setLen_i & 31;
            this.scala$collection$immutable$VectorBuilder$$lenRest = setLen_i - this.scala$collection$immutable$VectorBuilder$$len1;
            this.a4 = new Object[32][][][];
            this.a4[0] = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(v4.prefix1(), v4.prefix2()), v4.prefix3());
            System.arraycopy(d4, 0, this.a4, 1, d4.length);
            this.a3 = Arrays.copyOf(s3, 32);
            this.a2 = Arrays.copyOf(s2, 32);
            this.a4[d4.length + 1] = this.a3;
            this.a3[s3.length] = this.a2;
            this.a2[s2.length] = this.a1;
            break;
         case 9:
            Vector5 v5 = (Vector5)v;
            Object[][][][][] d5 = v5.data5();
            Object[][][][] s4 = v5.suffix4();
            Object[][][] s3 = v5.suffix3();
            Object[][] s2 = v5.suffix2();
            VectorInline$ var56 = VectorInline$.MODULE$;
            var56 = v5.suffix1();
            byte copyOrUse_end = 32;
            byte copyOrUse_start = 0;
            Object[] copyOrUse_a = var56;
            var56 = copyOrUse_start == 0 && copyOrUse_end == copyOrUse_a.length ? copyOrUse_a : Arrays.copyOfRange(copyOrUse_a, copyOrUse_start, copyOrUse_end);
            copyOrUse_a = null;
            this.a1 = var56;
            this.depth = 5;
            this.scala$collection$immutable$VectorBuilder$$offset = 1048576 - v5.len1234();
            int setLen_i = v5.length0() + this.scala$collection$immutable$VectorBuilder$$offset;
            this.scala$collection$immutable$VectorBuilder$$len1 = setLen_i & 31;
            this.scala$collection$immutable$VectorBuilder$$lenRest = setLen_i - this.scala$collection$immutable$VectorBuilder$$len1;
            this.a5 = new Object[32][][][][];
            this.a5[0] = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(v5.prefix1(), v5.prefix2()), v5.prefix3()), v5.prefix4());
            System.arraycopy(d5, 0, this.a5, 1, d5.length);
            this.a4 = Arrays.copyOf(s4, 32);
            this.a3 = Arrays.copyOf(s3, 32);
            this.a2 = Arrays.copyOf(s2, 32);
            this.a5[d5.length + 1] = this.a4;
            this.a4[s4.length] = this.a3;
            this.a3[s3.length] = this.a2;
            this.a2[s2.length] = this.a1;
            break;
         case 11:
            Vector6 v6 = (Vector6)v;
            Object[][][][][][] d6 = v6.data6();
            Object[][][][][] s5 = v6.suffix5();
            Object[][][][] s4 = v6.suffix4();
            Object[][][] s3 = v6.suffix3();
            Object[][] s2 = v6.suffix2();
            VectorInline$ var10001 = VectorInline$.MODULE$;
            var10001 = v6.suffix1();
            byte copyOrUse_end = 32;
            byte copyOrUse_start = 0;
            Object[] copyOrUse_a = var10001;
            var10001 = copyOrUse_start == 0 && copyOrUse_end == copyOrUse_a.length ? copyOrUse_a : Arrays.copyOfRange(copyOrUse_a, copyOrUse_start, copyOrUse_end);
            copyOrUse_a = null;
            this.a1 = var10001;
            this.depth = 6;
            this.scala$collection$immutable$VectorBuilder$$offset = 33554432 - v6.len12345();
            int setLen_i = v6.length0() + this.scala$collection$immutable$VectorBuilder$$offset;
            this.scala$collection$immutable$VectorBuilder$$len1 = setLen_i & 31;
            this.scala$collection$immutable$VectorBuilder$$lenRest = setLen_i - this.scala$collection$immutable$VectorBuilder$$len1;
            this.a6 = new Object[64][][][][][];
            this.a6[0] = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(v6.prefix1(), v6.prefix2()), v6.prefix3()), v6.prefix4()), v6.prefix5());
            System.arraycopy(d6, 0, this.a6, 1, d6.length);
            this.a5 = Arrays.copyOf(s5, 32);
            this.a4 = Arrays.copyOf(s4, 32);
            this.a3 = Arrays.copyOf(s3, 32);
            this.a2 = Arrays.copyOf(s2, 32);
            this.a6[d6.length + 1] = this.a5;
            this.a5[s5.length] = this.a4;
            this.a4[s4.length] = this.a3;
            this.a3[s3.length] = this.a2;
            this.a2[s2.length] = this.a1;
            break;
         default:
            throw new MatchError(var2);
      }

      if (this.scala$collection$immutable$VectorBuilder$$len1 == 0 && this.scala$collection$immutable$VectorBuilder$$lenRest > 0) {
         this.scala$collection$immutable$VectorBuilder$$len1 = 32;
         this.scala$collection$immutable$VectorBuilder$$lenRest -= 32;
      }

      return this;
   }

   public VectorBuilder alignTo(final int before, final Vector bigVector) {
      if (this.scala$collection$immutable$VectorBuilder$$len1 == 0 && this.scala$collection$immutable$VectorBuilder$$lenRest == 0) {
         int var10000;
         int var10001;
         if (Vector0$.MODULE$.equals(bigVector)) {
            var10000 = 0;
            var10001 = 1;
         } else if (bigVector instanceof Vector1) {
            var10000 = 0;
            var10001 = 1;
         } else if (bigVector instanceof Vector2) {
            var10000 = ((Vector2)bigVector).len1();
            var10001 = 32;
         } else if (bigVector instanceof Vector3) {
            var10000 = ((Vector3)bigVector).len12();
            var10001 = 1024;
         } else if (bigVector instanceof Vector4) {
            var10000 = ((Vector4)bigVector).len123();
            var10001 = 32768;
         } else if (bigVector instanceof Vector5) {
            var10000 = ((Vector5)bigVector).len1234();
            var10001 = 1048576;
         } else {
            if (!(bigVector instanceof Vector6)) {
               throw new MatchError(bigVector);
            }

            var10000 = ((Vector6)bigVector).len12345();
            var10001 = 33554432;
         }

         int var5 = var10001;
         int var4 = var10000;
         if (var5 == 1) {
            return this;
         } else {
            int overallPrefixLength = (before + var4) % var5;
            this.scala$collection$immutable$VectorBuilder$$offset = (var5 - overallPrefixLength) % var5;
            this.advanceN(this.scala$collection$immutable$VectorBuilder$$offset & -32);
            this.scala$collection$immutable$VectorBuilder$$len1 = this.scala$collection$immutable$VectorBuilder$$offset & 31;
            this.prefixIsRightAligned = true;
            return this;
         }
      } else {
         throw new UnsupportedOperationException("A non-empty VectorBuilder cannot be aligned retrospectively. Please call .reset() or use a new VectorBuilder.");
      }
   }

   private void leftAlignPrefix() {
      Object[] a = null;
      Object[] aParent = null;
      if (this.depth >= 6) {
         a = this.a6;
         int i = this.scala$collection$immutable$VectorBuilder$$offset >>> 25;
         if (i > 0) {
            System.arraycopy(a, i, a, 0, 64 - i);
         }

         int shrinkOffsetIfToLarge$1_width = 33554432;
         int shrinkOffsetIfToLarge$1_newOffset = this.scala$collection$immutable$VectorBuilder$$offset % shrinkOffsetIfToLarge$1_width;
         this.scala$collection$immutable$VectorBuilder$$lenRest -= this.scala$collection$immutable$VectorBuilder$$offset - shrinkOffsetIfToLarge$1_newOffset;
         this.scala$collection$immutable$VectorBuilder$$offset = shrinkOffsetIfToLarge$1_newOffset;
         if (this.scala$collection$immutable$VectorBuilder$$lenRest >>> 25 == 0) {
            this.depth = 5;
         }

         aParent = a;
         a = (Object[])a[0];
      }

      if (this.depth >= 5) {
         if (a == null) {
            a = this.a5;
         }

         int i = this.scala$collection$immutable$VectorBuilder$$offset >>> 20 & 31;
         if (this.depth == 5) {
            if (i > 0) {
               System.arraycopy(a, i, a, 0, 32 - i);
            }

            this.a5 = a;
            int shrinkOffsetIfToLarge$1_width = 1048576;
            int shrinkOffsetIfToLarge$1_newOffset = this.scala$collection$immutable$VectorBuilder$$offset % shrinkOffsetIfToLarge$1_width;
            this.scala$collection$immutable$VectorBuilder$$lenRest -= this.scala$collection$immutable$VectorBuilder$$offset - shrinkOffsetIfToLarge$1_newOffset;
            this.scala$collection$immutable$VectorBuilder$$offset = shrinkOffsetIfToLarge$1_newOffset;
            if (this.scala$collection$immutable$VectorBuilder$$lenRest >>> 20 == 0) {
               this.depth = 4;
            }
         } else {
            if (i > 0) {
               a = Arrays.copyOfRange(a, i, 32);
            }

            aParent[0] = a;
         }

         aParent = a;
         a = a[0];
      }

      if (this.depth >= 4) {
         if (a == null) {
            a = this.a4;
         }

         int i = this.scala$collection$immutable$VectorBuilder$$offset >>> 15 & 31;
         if (this.depth == 4) {
            if (i > 0) {
               System.arraycopy(a, i, a, 0, 32 - i);
            }

            this.a4 = a;
            int shrinkOffsetIfToLarge$1_width = 32768;
            int shrinkOffsetIfToLarge$1_newOffset = this.scala$collection$immutable$VectorBuilder$$offset % shrinkOffsetIfToLarge$1_width;
            this.scala$collection$immutable$VectorBuilder$$lenRest -= this.scala$collection$immutable$VectorBuilder$$offset - shrinkOffsetIfToLarge$1_newOffset;
            this.scala$collection$immutable$VectorBuilder$$offset = shrinkOffsetIfToLarge$1_newOffset;
            if (this.scala$collection$immutable$VectorBuilder$$lenRest >>> 15 == 0) {
               this.depth = 3;
            }
         } else {
            if (i > 0) {
               a = Arrays.copyOfRange(a, i, 32);
            }

            aParent[0] = a;
         }

         aParent = a;
         a = a[0];
      }

      if (this.depth >= 3) {
         if (a == null) {
            a = this.a3;
         }

         int i = this.scala$collection$immutable$VectorBuilder$$offset >>> 10 & 31;
         if (this.depth == 3) {
            if (i > 0) {
               System.arraycopy(a, i, a, 0, 32 - i);
            }

            this.a3 = a;
            int shrinkOffsetIfToLarge$1_width = 1024;
            int shrinkOffsetIfToLarge$1_newOffset = this.scala$collection$immutable$VectorBuilder$$offset % shrinkOffsetIfToLarge$1_width;
            this.scala$collection$immutable$VectorBuilder$$lenRest -= this.scala$collection$immutable$VectorBuilder$$offset - shrinkOffsetIfToLarge$1_newOffset;
            this.scala$collection$immutable$VectorBuilder$$offset = shrinkOffsetIfToLarge$1_newOffset;
            if (this.scala$collection$immutable$VectorBuilder$$lenRest >>> 10 == 0) {
               this.depth = 2;
            }
         } else {
            if (i > 0) {
               a = Arrays.copyOfRange(a, i, 32);
            }

            aParent[0] = a;
         }

         aParent = a;
         a = a[0];
      }

      if (this.depth >= 2) {
         if (a == null) {
            a = this.a2;
         }

         int i = this.scala$collection$immutable$VectorBuilder$$offset >>> 5 & 31;
         if (this.depth == 2) {
            if (i > 0) {
               System.arraycopy(a, i, a, 0, 32 - i);
            }

            this.a2 = a;
            int shrinkOffsetIfToLarge$1_width = 32;
            int shrinkOffsetIfToLarge$1_newOffset = this.scala$collection$immutable$VectorBuilder$$offset % shrinkOffsetIfToLarge$1_width;
            this.scala$collection$immutable$VectorBuilder$$lenRest -= this.scala$collection$immutable$VectorBuilder$$offset - shrinkOffsetIfToLarge$1_newOffset;
            this.scala$collection$immutable$VectorBuilder$$offset = shrinkOffsetIfToLarge$1_newOffset;
            if (this.scala$collection$immutable$VectorBuilder$$lenRest >>> 5 == 0) {
               this.depth = 1;
            }
         } else {
            if (i > 0) {
               a = Arrays.copyOfRange(a, i, 32);
            }

            aParent[0] = a;
         }

         aParent = a;
         a = a[0];
      }

      if (this.depth >= 1) {
         if (a == null) {
            a = this.a1;
         }

         int i = this.scala$collection$immutable$VectorBuilder$$offset & 31;
         if (this.depth == 1) {
            if (i > 0) {
               System.arraycopy(a, i, a, 0, 32 - i);
            }

            this.a1 = a;
            this.scala$collection$immutable$VectorBuilder$$len1 -= this.scala$collection$immutable$VectorBuilder$$offset;
            this.scala$collection$immutable$VectorBuilder$$offset = 0;
         } else {
            if (i > 0) {
               a = Arrays.copyOfRange(a, i, 32);
            }

            aParent[0] = a;
         }
      }

      this.prefixIsRightAligned = false;
   }

   public VectorBuilder addOne(final Object elem) {
      if (this.scala$collection$immutable$VectorBuilder$$len1 == 32) {
         this.advance();
      }

      this.a1[this.scala$collection$immutable$VectorBuilder$$len1] = elem;
      ++this.scala$collection$immutable$VectorBuilder$$len1;
      return this;
   }

   private void addArr1(final Object[] data) {
      int dl = data.length;
      if (dl > 0) {
         if (this.scala$collection$immutable$VectorBuilder$$len1 == 32) {
            this.advance();
         }

         int copy1 = Math.min(32 - this.scala$collection$immutable$VectorBuilder$$len1, dl);
         int copy2 = dl - copy1;
         System.arraycopy(data, 0, this.a1, this.scala$collection$immutable$VectorBuilder$$len1, copy1);
         this.scala$collection$immutable$VectorBuilder$$len1 += copy1;
         if (copy2 > 0) {
            this.advance();
            System.arraycopy(data, copy1, this.a1, 0, copy2);
            this.scala$collection$immutable$VectorBuilder$$len1 += copy2;
         }
      }
   }

   private void addArrN(final Object[] slice, final int dim) {
      if (slice.length != 0) {
         if (this.scala$collection$immutable$VectorBuilder$$len1 == 32) {
            this.advance();
         }

         int sl = slice.length;
         switch (dim) {
            case 2:
               int copy1 = Math.min(1024 - this.scala$collection$immutable$VectorBuilder$$lenRest >>> 5 & 31, sl);
               int copy2 = sl - copy1;
               int destPos = this.scala$collection$immutable$VectorBuilder$$lenRest >>> 5 & 31;
               System.arraycopy(slice, 0, this.a2, destPos, copy1);
               this.advanceN(32 * copy1);
               if (copy2 > 0) {
                  System.arraycopy(slice, copy1, this.a2, 0, copy2);
                  this.advanceN(32 * copy2);
                  return;
               }

               return;
            case 3:
               if (this.scala$collection$immutable$VectorBuilder$$lenRest % 1024 == 0) {
                  int copy1 = Math.min('耀' - this.scala$collection$immutable$VectorBuilder$$lenRest >>> 10 & 31, sl);
                  int copy2 = sl - copy1;
                  int destPos = this.scala$collection$immutable$VectorBuilder$$lenRest >>> 10 & 31;
                  System.arraycopy(slice, 0, this.a3, destPos, copy1);
                  this.advanceN(1024 * copy1);
                  if (copy2 > 0) {
                     System.arraycopy(slice, copy1, this.a3, 0, copy2);
                     this.advanceN(1024 * copy2);
                     return;
                  }

                  return;
               }

               for(Object var25 : slice) {
                  $anonfun$addArrN$1(this, var25);
               }

               return;
            case 4:
               if (this.scala$collection$immutable$VectorBuilder$$lenRest % '耀' == 0) {
                  int copy1 = Math.min(1048576 - this.scala$collection$immutable$VectorBuilder$$lenRest >>> 15 & 31, sl);
                  int copy2 = sl - copy1;
                  int destPos = this.scala$collection$immutable$VectorBuilder$$lenRest >>> 15 & 31;
                  System.arraycopy(slice, 0, this.a4, destPos, copy1);
                  this.advanceN('耀' * copy1);
                  if (copy2 > 0) {
                     System.arraycopy(slice, copy1, this.a4, 0, copy2);
                     this.advanceN('耀' * copy2);
                     return;
                  }

                  return;
               }

               for(Object var26 : slice) {
                  $anonfun$addArrN$2(this, var26);
               }

               return;
            case 5:
               if (this.scala$collection$immutable$VectorBuilder$$lenRest % 1048576 == 0) {
                  int copy1 = Math.min(33554432 - this.scala$collection$immutable$VectorBuilder$$lenRest >>> 20 & 31, sl);
                  int copy2 = sl - copy1;
                  int destPos = this.scala$collection$immutable$VectorBuilder$$lenRest >>> 20 & 31;
                  System.arraycopy(slice, 0, this.a5, destPos, copy1);
                  this.advanceN(1048576 * copy1);
                  if (copy2 > 0) {
                     System.arraycopy(slice, copy1, this.a5, 0, copy2);
                     this.advanceN(1048576 * copy2);
                     return;
                  }

                  return;
               }

               for(Object var27 : slice) {
                  $anonfun$addArrN$3(this, var27);
               }

               return;
            case 6:
               if (this.scala$collection$immutable$VectorBuilder$$lenRest % 33554432 == 0) {
                  int destPos = this.scala$collection$immutable$VectorBuilder$$lenRest >>> 25;
                  if (destPos + sl > 64) {
                     throw new IllegalArgumentException("exceeding 2^31 elements");
                  }

                  System.arraycopy(slice, 0, this.a6, destPos, sl);
                  this.advanceN(33554432 * sl);
                  return;
               }

               for(Object var28 : slice) {
                  $anonfun$addArrN$4(this, var28);
               }

               return;
            default:
               throw new MatchError(BoxesRunTime.boxToInteger(dim));
         }
      }
   }

   private VectorBuilder addVector(final Vector xs) {
      int sliceCount = xs.vectorSliceCount();

      for(int sliceIdx = 0; sliceIdx < sliceCount; ++sliceIdx) {
         Object[] slice = xs.vectorSlice(sliceIdx);
         VectorInline$ var10000 = VectorInline$.MODULE$;
         int vectorSliceDim_c = sliceCount / 2;
         int var5 = vectorSliceDim_c + 1 - Math.abs(sliceIdx - vectorSliceDim_c);
         switch (var5) {
            case 1:
               this.addArr1(slice);
               continue;
         }

         if (this.scala$collection$immutable$VectorBuilder$$len1 != 32 && this.scala$collection$immutable$VectorBuilder$$len1 != 0) {
            VectorStatics$ var16 = VectorStatics$.MODULE$;
            int var10001 = var5 - 2;
            Function1 foreachRec_f = (data) -> {
               $anonfun$addVector$1(this, data);
               return BoxedUnit.UNIT;
            };
            int foreachRec_level = var10001;
            VectorStatics$ foreachRec_this = var16;
            int foreachRec_i = 0;
            int foreachRec_len = slice.length;
            if (foreachRec_level == 0) {
               while(foreachRec_i < foreachRec_len) {
                  Object[] var13 = slice[foreachRec_i];
                  $anonfun$addVector$1(this, var13);
                  ++foreachRec_i;
               }
            } else {
               for(int foreachRec_l = foreachRec_level - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                  foreachRec_this.foreachRec(foreachRec_l, slice[foreachRec_i], foreachRec_f);
               }
            }

            Object var14 = null;
            foreachRec_f = null;
         } else {
            this.addArrN(slice, var5);
         }
      }

      return this;
   }

   public VectorBuilder addAll(final IterableOnce xs) {
      if (xs instanceof Vector) {
         Vector var2 = (Vector)xs;
         return this.scala$collection$immutable$VectorBuilder$$len1 == 0 && this.scala$collection$immutable$VectorBuilder$$lenRest == 0 && !this.prefixIsRightAligned ? this.initFrom(var2) : this.addVector(var2);
      } else {
         return (VectorBuilder)Growable.addAll$(this, xs);
      }
   }

   private void advance() {
      int idx = this.scala$collection$immutable$VectorBuilder$$lenRest + 32;
      int xor = idx ^ this.scala$collection$immutable$VectorBuilder$$lenRest;
      this.scala$collection$immutable$VectorBuilder$$lenRest = idx;
      this.scala$collection$immutable$VectorBuilder$$len1 = 0;
      this.advance1(idx, xor);
   }

   private void advanceN(final int n) {
      if (n > 0) {
         int idx = this.scala$collection$immutable$VectorBuilder$$lenRest + n;
         int xor = idx ^ this.scala$collection$immutable$VectorBuilder$$lenRest;
         this.scala$collection$immutable$VectorBuilder$$lenRest = idx;
         this.scala$collection$immutable$VectorBuilder$$len1 = 0;
         this.advance1(idx, xor);
      }
   }

   private void advance1(final int idx, final int xor) {
      if (xor <= 0) {
         throw new IllegalArgumentException((new StringBuilder(50)).append("advance1(").append(idx).append(", ").append(xor).append("): a1=").append(this.a1).append(", a2=").append(this.a2).append(", a3=").append(this.a3).append(", a4=").append(this.a4).append(", a5=").append(this.a5).append(", a6=").append(this.a6).append(", depth=").append(this.depth).toString());
      } else if (xor < 1024) {
         if (this.depth <= 1) {
            this.a2 = new Object[32][];
            this.a2[0] = this.a1;
            this.depth = 2;
         }

         this.a1 = new Object[32];
         this.a2[idx >>> 5 & 31] = this.a1;
      } else if (xor < 32768) {
         if (this.depth <= 2) {
            this.a3 = new Object[32][][];
            this.a3[0] = this.a2;
            this.depth = 3;
         }

         this.a1 = new Object[32];
         this.a2 = new Object[32][];
         this.a2[idx >>> 5 & 31] = this.a1;
         this.a3[idx >>> 10 & 31] = this.a2;
      } else if (xor < 1048576) {
         if (this.depth <= 3) {
            this.a4 = new Object[32][][][];
            this.a4[0] = this.a3;
            this.depth = 4;
         }

         this.a1 = new Object[32];
         this.a2 = new Object[32][];
         this.a3 = new Object[32][][];
         this.a2[idx >>> 5 & 31] = this.a1;
         this.a3[idx >>> 10 & 31] = this.a2;
         this.a4[idx >>> 15 & 31] = this.a3;
      } else if (xor < 33554432) {
         if (this.depth <= 4) {
            this.a5 = new Object[32][][][][];
            this.a5[0] = this.a4;
            this.depth = 5;
         }

         this.a1 = new Object[32];
         this.a2 = new Object[32][];
         this.a3 = new Object[32][][];
         this.a4 = new Object[32][][][];
         this.a2[idx >>> 5 & 31] = this.a1;
         this.a3[idx >>> 10 & 31] = this.a2;
         this.a4[idx >>> 15 & 31] = this.a3;
         this.a5[idx >>> 20 & 31] = this.a4;
      } else {
         if (this.depth <= 5) {
            this.a6 = new Object[64][][][][][];
            this.a6[0] = this.a5;
            this.depth = 6;
         }

         this.a1 = new Object[32];
         this.a2 = new Object[32][];
         this.a3 = new Object[32][][];
         this.a4 = new Object[32][][][];
         this.a5 = new Object[32][][][][];
         this.a2[idx >>> 5 & 31] = this.a1;
         this.a3[idx >>> 10 & 31] = this.a2;
         this.a4[idx >>> 15 & 31] = this.a3;
         this.a5[idx >>> 20 & 31] = this.a4;
         this.a6[idx >>> 25] = this.a5;
      }
   }

   public Vector result() {
      if (this.prefixIsRightAligned) {
         this.leftAlignPrefix();
      }

      int len = this.scala$collection$immutable$VectorBuilder$$len1 + this.scala$collection$immutable$VectorBuilder$$lenRest;
      int realLen = len - this.scala$collection$immutable$VectorBuilder$$offset;
      if (realLen == 0) {
         return Vector$.MODULE$.empty();
      } else if (len < 0) {
         throw new IndexOutOfBoundsException((new StringBuilder(33)).append("Vector cannot have negative size ").append(len).toString());
      } else if (len <= 32) {
         VectorInline$ var10002 = VectorInline$.MODULE$;
         Object[] copyIfDifferentSize_a = this.a1;
         var10002 = copyIfDifferentSize_a.length == realLen ? copyIfDifferentSize_a : Arrays.copyOf(copyIfDifferentSize_a, realLen);
         copyIfDifferentSize_a = null;
         return new Vector1(var10002);
      } else if (len <= 1024) {
         int i1 = len - 1 & 31;
         int i2 = len - 1 >>> 5;
         Object[][] data = Arrays.copyOfRange(this.a2, 1, i2);
         Object[] prefix1 = this.a2[0];
         VectorInline$ var140 = VectorInline$.MODULE$;
         var140 = this.a2[i2];
         int copyIfDifferentSize_len = i1 + 1;
         Object[] copyIfDifferentSize_a = var140;
         var140 = copyIfDifferentSize_a.length == copyIfDifferentSize_len ? copyIfDifferentSize_a : Arrays.copyOf(copyIfDifferentSize_a, copyIfDifferentSize_len);
         copyIfDifferentSize_a = null;
         Object[] suffix1 = var140;
         return new Vector2(prefix1, 32 - this.scala$collection$immutable$VectorBuilder$$offset, data, suffix1, realLen);
      } else if (len <= 32768) {
         int i1 = len - 1 & 31;
         int i2 = len - 1 >>> 5 & 31;
         int i3 = len - 1 >>> 10;
         Object[][][] data = Arrays.copyOfRange(this.a3, 1, i3);
         VectorInline$ var135 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a3[0];
         var135 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][] prefix2 = var135;
         Object[] prefix1 = this.a3[0][0];
         Object[][] suffix2 = Arrays.copyOf(this.a3[i3], i2);
         var135 = VectorInline$.MODULE$;
         var135 = this.a3[i3][i2];
         int copyIfDifferentSize_len = i1 + 1;
         Object[] copyIfDifferentSize_a = var135;
         var135 = copyIfDifferentSize_a.length == copyIfDifferentSize_len ? copyIfDifferentSize_a : Arrays.copyOf(copyIfDifferentSize_a, copyIfDifferentSize_len);
         copyIfDifferentSize_a = null;
         Object[] suffix1 = var135;
         int len1 = prefix1.length;
         int len12 = len1 + prefix2.length * 32;
         return new Vector3(prefix1, len1, prefix2, len12, data, suffix2, suffix1, realLen);
      } else if (len <= 1048576) {
         int i1 = len - 1 & 31;
         int i2 = len - 1 >>> 5 & 31;
         int i3 = len - 1 >>> 10 & 31;
         int i4 = len - 1 >>> 15;
         Object[][][][] data = Arrays.copyOfRange(this.a4, 1, i4);
         VectorInline$ var128 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a4[0];
         var128 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][][] prefix3 = var128;
         var128 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a4[0][0];
         var128 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][] prefix2 = var128;
         Object[] prefix1 = this.a4[0][0][0];
         Object[][][] suffix3 = Arrays.copyOf(this.a4[i4], i3);
         Object[][] suffix2 = Arrays.copyOf(this.a4[i4][i3], i2);
         var128 = VectorInline$.MODULE$;
         var128 = this.a4[i4][i3][i2];
         int copyIfDifferentSize_len = i1 + 1;
         Object[] copyIfDifferentSize_a = var128;
         var128 = copyIfDifferentSize_a.length == copyIfDifferentSize_len ? copyIfDifferentSize_a : Arrays.copyOf(copyIfDifferentSize_a, copyIfDifferentSize_len);
         copyIfDifferentSize_a = null;
         Object[] suffix1 = var128;
         int len1 = prefix1.length;
         int len12 = len1 + prefix2.length * 32;
         int len123 = len12 + prefix3.length * 1024;
         return new Vector4(prefix1, len1, prefix2, len12, prefix3, len123, data, suffix3, suffix2, suffix1, realLen);
      } else if (len <= 33554432) {
         int i1 = len - 1 & 31;
         int i2 = len - 1 >>> 5 & 31;
         int i3 = len - 1 >>> 10 & 31;
         int i4 = len - 1 >>> 15 & 31;
         int i5 = len - 1 >>> 20;
         Object[][][][][] data = Arrays.copyOfRange(this.a5, 1, i5);
         VectorInline$ var119 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a5[0];
         var119 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][][][] prefix4 = var119;
         var119 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a5[0][0];
         var119 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][][] prefix3 = var119;
         var119 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a5[0][0][0];
         var119 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][] prefix2 = var119;
         Object[] prefix1 = this.a5[0][0][0][0];
         Object[][][][] suffix4 = Arrays.copyOf(this.a5[i5], i4);
         Object[][][] suffix3 = Arrays.copyOf(this.a5[i5][i4], i3);
         Object[][] suffix2 = Arrays.copyOf(this.a5[i5][i4][i3], i2);
         var119 = VectorInline$.MODULE$;
         var119 = this.a5[i5][i4][i3][i2];
         int copyIfDifferentSize_len = i1 + 1;
         Object[] copyIfDifferentSize_a = var119;
         var119 = copyIfDifferentSize_a.length == copyIfDifferentSize_len ? copyIfDifferentSize_a : Arrays.copyOf(copyIfDifferentSize_a, copyIfDifferentSize_len);
         copyIfDifferentSize_a = null;
         Object[] suffix1 = var119;
         int len1 = prefix1.length;
         int len12 = len1 + prefix2.length * 32;
         int len123 = len12 + prefix3.length * 1024;
         int len1234 = len123 + prefix4.length * '耀';
         return new Vector5(prefix1, len1, prefix2, len12, prefix3, len123, prefix4, len1234, data, suffix4, suffix3, suffix2, suffix1, realLen);
      } else {
         int i1 = len - 1 & 31;
         int i2 = len - 1 >>> 5 & 31;
         int i3 = len - 1 >>> 10 & 31;
         int i4 = len - 1 >>> 15 & 31;
         int i5 = len - 1 >>> 20 & 31;
         int i6 = len - 1 >>> 25;
         Object[][][][][][] data = Arrays.copyOfRange(this.a6, 1, i6);
         VectorInline$ var10000 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a6[0];
         var10000 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][][][][] prefix5 = var10000;
         var10000 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a6[0][0];
         var10000 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][][][] prefix4 = var10000;
         var10000 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a6[0][0][0];
         var10000 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][][] prefix3 = var10000;
         var10000 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.a6[0][0][0][0];
         var10000 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[][] prefix2 = var10000;
         Object[] prefix1 = this.a6[0][0][0][0][0];
         Object[][][][][] suffix5 = Arrays.copyOf(this.a6[i6], i5);
         Object[][][][] suffix4 = Arrays.copyOf(this.a6[i6][i5], i4);
         Object[][][] suffix3 = Arrays.copyOf(this.a6[i6][i5][i4], i3);
         Object[][] suffix2 = Arrays.copyOf(this.a6[i6][i5][i4][i3], i2);
         var10000 = VectorInline$.MODULE$;
         var10000 = this.a6[i6][i5][i4][i3][i2];
         int copyIfDifferentSize_len = i1 + 1;
         Object[] copyIfDifferentSize_a = var10000;
         var10000 = copyIfDifferentSize_a.length == copyIfDifferentSize_len ? copyIfDifferentSize_a : Arrays.copyOf(copyIfDifferentSize_a, copyIfDifferentSize_len);
         copyIfDifferentSize_a = null;
         Object[] suffix1 = var10000;
         int len1 = prefix1.length;
         int len12 = len1 + prefix2.length * 32;
         int len123 = len12 + prefix3.length * 1024;
         int len1234 = len123 + prefix4.length * '耀';
         int len12345 = len1234 + prefix5.length * 1048576;
         return new Vector6(prefix1, len1, prefix2, len12, prefix3, len123, prefix4, len1234, prefix5, len12345, data, suffix5, suffix4, suffix3, suffix2, suffix1, realLen);
      }
   }

   public String toString() {
      return (new StringBuilder(47)).append("VectorBuilder(len1=").append(this.scala$collection$immutable$VectorBuilder$$len1).append(", lenRest=").append(this.scala$collection$immutable$VectorBuilder$$lenRest).append(", offset=").append(this.scala$collection$immutable$VectorBuilder$$offset).append(", depth=").append(this.depth).append(")").toString();
   }

   public Object[] getData() {
      return new Object[][]{this.a1, this.a2, this.a3, this.a4, this.a5, this.a6};
   }

   private final void shrinkOffsetIfToLarge$1(final int width) {
      int newOffset = this.scala$collection$immutable$VectorBuilder$$offset % width;
      this.scala$collection$immutable$VectorBuilder$$lenRest -= this.scala$collection$immutable$VectorBuilder$$offset - newOffset;
      this.scala$collection$immutable$VectorBuilder$$offset = newOffset;
   }

   // $FF: synthetic method
   public static final void $anonfun$addArrN$1(final VectorBuilder $this, final Object e) {
      $this.addArrN(e, 2);
   }

   // $FF: synthetic method
   public static final void $anonfun$addArrN$2(final VectorBuilder $this, final Object e) {
      $this.addArrN(e, 3);
   }

   // $FF: synthetic method
   public static final void $anonfun$addArrN$3(final VectorBuilder $this, final Object e) {
      $this.addArrN(e, 4);
   }

   // $FF: synthetic method
   public static final void $anonfun$addArrN$4(final VectorBuilder $this, final Object e) {
      $this.addArrN(e, 5);
   }

   // $FF: synthetic method
   public static final void $anonfun$addVector$1(final VectorBuilder $this, final Object[] data) {
      $this.addArr1(data);
   }

   // $FF: synthetic method
   public static final Object $anonfun$addArrN$1$adapted(final VectorBuilder $this, final Object e) {
      $anonfun$addArrN$1($this, e);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$addArrN$2$adapted(final VectorBuilder $this, final Object e) {
      $anonfun$addArrN$2($this, e);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$addArrN$3$adapted(final VectorBuilder $this, final Object e) {
      $anonfun$addArrN$3($this, e);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$addArrN$4$adapted(final VectorBuilder $this, final Object e) {
      $anonfun$addArrN$4($this, e);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
