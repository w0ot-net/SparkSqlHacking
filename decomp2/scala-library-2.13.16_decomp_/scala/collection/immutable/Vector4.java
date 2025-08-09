package scala.collection.immutable;

import [[[[Ljava.lang.Object;;
import java.lang.reflect.Array;
import java.util.Arrays;
import scala.Function1;
import scala.MatchError;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t}b\u0001\u0002\u00180\rYB\u0011\u0002\u0013\u0001\u0003\u0002\u0003\u0006I!S-\t\u0013u\u0003!Q1A\u0005\u0002=r\u0006\u0002\u00032\u0001\u0005\u0003\u0005\u000b\u0011B0\t\u0013\r\u0004!Q1A\u0005\u0002=\"\u0007\u0002\u00035\u0001\u0005\u0003\u0005\u000b\u0011B3\t\u0013%\u0004!Q1A\u0005\u0002=r\u0006\u0002\u00036\u0001\u0005\u0003\u0005\u000b\u0011B0\t\u0013-\u0004!Q1A\u0005\u0002=b\u0007\u0002\u00039\u0001\u0005\u0003\u0005\u000b\u0011B7\t\u0013E\u0004!Q1A\u0005\u0002=r\u0006\u0002\u0003:\u0001\u0005\u0003\u0005\u000b\u0011B0\t\u0013M\u0004!Q1A\u0005\u0002=\"\b\u0002\u0003=\u0001\u0005\u0003\u0005\u000b\u0011B;\t\u0013e\u0004!Q1A\u0005\u0002=b\u0007\u0002\u0003>\u0001\u0005\u0003\u0005\u000b\u0011B7\t\u0013m\u0004!Q1A\u0005\u0002=\"\u0007\u0002\u0003?\u0001\u0005\u0003\u0005\u000b\u0011B3\t\u0013u\u0004!\u0011!Q\u0001\n%s\bbCA\u0001\u0001\t\u0005\t\u0015!\u0003`\u0003\u0007Aq!a\u0002\u0001\t\u0003\tI\u0001\u0003\u0005\u0002$\u0001\u0001K\u0011BA\u0013\u0011%\t9\u0005AI\u0001\n\u0013\tI\u0005C\u0005\u0002`\u0001\t\n\u0011\"\u0003\u0002b!I\u0011Q\r\u0001\u0012\u0002\u0013%\u0011q\r\u0005\n\u0003W\u0002\u0011\u0013!C\u0005\u0003CB\u0011\"!\u001c\u0001#\u0003%I!a\u001c\t\u0013\u0005M\u0004!%A\u0005\n\u0005\u0005\u0004\"CA;\u0001E\u0005I\u0011BA<\u0011%\tY\bAI\u0001\n\u0013\ty\u0007C\u0005\u0002~\u0001\t\n\u0011\"\u0003\u0002h!I\u0011q\u0010\u0001\u0012\u0002\u0013%\u0011\u0011\n\u0005\n\u0003\u0003\u0003\u0011\u0013!C\u0005\u0003CBq!a!\u0001\t\u0003\t)\tC\u0004\u0002\u000e\u0002!\t%a$\t\u000f\u0005\r\u0006\u0001\"\u0011\u0002&\"9\u0011\u0011\u0017\u0001\u0005B\u0005M\u0006bBA`\u0001\u0011\u0005\u0013\u0011\u0019\u0005\t\u0003+\u0004\u0001\u0015\"\u0005\u0002X\"9\u00111\u001d\u0001\u0005B\u0005\u0015\bbBAt\u0001\u0011\u0005\u0013Q\u001d\u0005\b\u0003S\u0004A\u0011C\u0018_\u0011!\tY\u000f\u0001C\t_\u00055\b\u0002\u0003B\u0005\u0001\u0011EqFa\u0003\t\u0011\t=\u0001\u0001)C)\u0005#A\u0001Ba\u000b\u0001A\u0013E#Q\u0006\u0002\b-\u0016\u001cGo\u001c:5\u0015\t\u0001\u0014'A\u0005j[6,H/\u00192mK*\u0011!gM\u0001\u000bG>dG.Z2uS>t'\"\u0001\u001b\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011qGP\n\u0003\u0001a\u00022!\u000f\u001e=\u001b\u0005y\u0013BA\u001e0\u0005%\u0011\u0015n\u001a,fGR|'\u000f\u0005\u0002>}1\u0001AAB \u0001\t\u000b\u0007\u0001IA\u0001B#\t\tU\t\u0005\u0002C\u00076\t1'\u0003\u0002Eg\t9aj\u001c;iS:<\u0007C\u0001\"G\u0013\t95GA\u0002B]f\f\u0001b\u00189sK\u001aL\u00070\r\t\u0003\u0015Zs!a\u0013+\u000f\u00051\u001bfBA'S\u001d\tq\u0015+D\u0001P\u0015\t\u0001V'\u0001\u0004=e>|GOP\u0005\u0002i%\u0011!gM\u0005\u0003aEJ!!V\u0018\u0002\u0019Y+7\r^8s\u0013:d\u0017N\\3\n\u0005]C&\u0001B!seFR!!V\u0018\n\u0005i[\u0016a\u00029sK\u001aL\u00070M\u0005\u00039>\u0012aAV3di>\u0014\u0018\u0001\u00027f]F*\u0012a\u0018\t\u0003\u0005\u0002L!!Y\u001a\u0003\u0007%sG/A\u0003mK:\f\u0004%A\u0004qe\u00164\u0017\u000e\u001f\u001a\u0016\u0003\u0015\u0004\"A\u00134\n\u0005\u001dD&\u0001B!seJ\n\u0001\u0002\u001d:fM&D(\u0007I\u0001\u0006Y\u0016t\u0017GM\u0001\u0007Y\u0016t\u0017G\r\u0011\u0002\u000fA\u0014XMZ5ygU\tQ\u000e\u0005\u0002K]&\u0011q\u000e\u0017\u0002\u0005\u0003J\u00148'\u0001\u0005qe\u00164\u0017\u000e_\u001a!\u0003\u0019aWM\\\u00193g\u00059A.\u001a82eM\u0002\u0013!\u00023bi\u0006$T#A;\u0011\u0005)3\u0018BA<Y\u0005\u0011\t%O\u001d\u001b\u0002\r\u0011\fG/\u0019\u001b!\u0003\u001d\u0019XO\u001a4jqN\n\u0001b];gM&D8\u0007I\u0001\bgV4g-\u001b=3\u0003!\u0019XO\u001a4jqJ\u0002\u0013\u0001C0tk\u001a4\u0017\u000e_\u0019\n\u0005}T\u0014aB:vM\u001aL\u00070M\u0001\t?2,gn\u001a;ia%\u0019\u0011Q\u0001\u001e\u0002\u000f1,gn\u001a;ia\u00051A(\u001b8jiz\"\u0002$a\u0003\u0002\u000e\u0005=\u0011\u0011CA\n\u0003+\t9\"!\u0007\u0002\u001c\u0005u\u0011qDA\u0011!\rI\u0004\u0001\u0010\u0005\u0006\u0011R\u0001\r!\u0013\u0005\u0006;R\u0001\ra\u0018\u0005\u0006GR\u0001\r!\u001a\u0005\u0006SR\u0001\ra\u0018\u0005\u0006WR\u0001\r!\u001c\u0005\u0006cR\u0001\ra\u0018\u0005\u0006gR\u0001\r!\u001e\u0005\u0006sR\u0001\r!\u001c\u0005\u0006wR\u0001\r!\u001a\u0005\u0006{R\u0001\r!\u0013\u0005\u0007\u0003\u0003!\u0002\u0019A0\u0002\t\r|\u0007/\u001f\u000b\u0019\u0003O\tI#a\u000b\u0002.\u0005=\u0012\u0011GA\u001a\u0003k\t9$!\u000f\u0002<\u0005u\u0002cA\u001d\u0001\u0003\"9!,\u0006I\u0001\u0002\u0004I\u0005bB/\u0016!\u0003\u0005\ra\u0018\u0005\bGV\u0001\n\u00111\u0001f\u0011\u001dIW\u0003%AA\u0002}Cqa[\u000b\u0011\u0002\u0003\u0007Q\u000eC\u0004r+A\u0005\t\u0019A0\t\u000fM,\u0002\u0013!a\u0001k\"9\u00110\u0006I\u0001\u0002\u0004i\u0007bB>\u0016!\u0003\u0005\r!\u001a\u0005\b\u007fV\u0001\n\u00111\u0001J\u0011!\t)!\u0006I\u0001\u0002\u0004y\u0006fA\u000b\u0002BA\u0019!)a\u0011\n\u0007\u0005\u00153G\u0001\u0004j]2Lg.Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tYEK\u0002J\u0003\u001bZ#!a\u0014\u0011\t\u0005E\u00131L\u0007\u0003\u0003'RA!!\u0016\u0002X\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u00033\u001a\u0014AC1o]>$\u0018\r^5p]&!\u0011QLA*\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t\u0019GK\u0002`\u0003\u001b\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002j)\u001aQ-!\u0014\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*TCAA9U\ri\u0017QJ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]*\"!!\u001f+\u0007U\fi%\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%s\u0005y1m\u001c9zI\u0011,g-Y;mi\u0012\n\u0004'A\bd_BLH\u0005Z3gCVdG\u000fJ\u00192\u0003\u0015\t\u0007\u000f\u001d7z)\ra\u0014q\u0011\u0005\u0007\u0003\u0013\u000b\u0003\u0019A0\u0002\u000b%tG-\u001a=)\u0007\u0005\n\t%A\u0004va\u0012\fG/\u001a3\u0016\t\u0005E\u0015q\u0013\u000b\u0007\u0003'\u000bi*a(\u0011\teZ\u0016Q\u0013\t\u0004{\u0005]EaBAME\t\u0007\u00111\u0014\u0002\u0002\u0005F\u0011A(\u0012\u0005\u0007\u0003\u0013\u0013\u0003\u0019A0\t\u000f\u0005\u0005&\u00051\u0001\u0002\u0016\u0006!Q\r\\3n\u0003!\t\u0007\u000f]3oI\u0016$W\u0003BAT\u0003[#B!!+\u00020B!\u0011hWAV!\ri\u0014Q\u0016\u0003\b\u00033\u001b#\u0019AAN\u0011\u001d\t\tk\ta\u0001\u0003W\u000b\u0011\u0002\u001d:fa\u0016tG-\u001a3\u0016\t\u0005U\u00161\u0018\u000b\u0005\u0003o\u000bi\f\u0005\u0003:7\u0006e\u0006cA\u001f\u0002<\u00129\u0011\u0011\u0014\u0013C\u0002\u0005m\u0005bBAQI\u0001\u0007\u0011\u0011X\u0001\u0004[\u0006\u0004X\u0003BAb\u0003\u0013$B!!2\u0002LB!\u0011hWAd!\ri\u0014\u0011\u001a\u0003\u0007\u00033+#\u0019\u0001!\t\u000f\u00055W\u00051\u0001\u0002P\u0006\ta\r\u0005\u0004C\u0003#d\u0014qY\u0005\u0004\u0003'\u001c$!\u0003$v]\u000e$\u0018n\u001c82\u0003\u0019\u0019H.[2faQ1\u0011\u0011\\An\u0003?\u00042!O.=\u0011\u0019\tiN\na\u0001?\u0006\u0011An\u001c\u0005\u0007\u0003C4\u0003\u0019A0\u0002\u0005!L\u0017\u0001\u0002;bS2,\"!!7\u0002\t%t\u0017\u000e^\u0001\u0011m\u0016\u001cGo\u001c:TY&\u001cWmQ8v]R\f1B^3di>\u00148\u000b\\5dKR!\u0011q\u001eB\u0003a\u0011\t\t0!?\u0011\u000b\t\u000b\u00190a>\n\u0007\u0005U8GA\u0003BeJ\f\u0017\u0010E\u0002>\u0003s$1\"a?+\u0003\u0003\u0005\tQ!\u0001\u0002~\n!q\fJ\u00197#\r\t\u0015q \t\u0004\u0005\n\u0005\u0011b\u0001B\u0002g\t1\u0011I\\=SK\u001aDaAa\u0002+\u0001\u0004y\u0016aA5eq\u00069b/Z2u_J\u001cF.[2f!J,g-\u001b=MK:<G\u000f\u001b\u000b\u0004?\n5\u0001B\u0002B\u0004W\u0001\u0007q,A\u0007qe\u0016\u0004XM\u001c3fI\u0006cG\u000eM\u000b\u0005\u0005'\u0011I\u0002\u0006\u0004\u0003\u0016\tm!q\u0005\t\u0005sm\u00139\u0002E\u0002>\u00053!q!!'-\u0005\u0004\tY\nC\u0004\u0003\u001e1\u0002\rAa\b\u0002\rA\u0014XMZ5y!\u0019\u0011\tCa\t\u0003\u00185\t\u0011'C\u0002\u0003&E\u0012A\"\u0013;fe\u0006\u0014G.Z(oG\u0016DaA!\u000b-\u0001\u0004y\u0016!A6\u0002\u0019\u0005\u0004\b/\u001a8eK\u0012\fE\u000e\u001c\u0019\u0016\t\t=\"Q\u0007\u000b\u0007\u0005c\u00119D!\u0010\u0011\teZ&1\u0007\t\u0004{\tUBaBAM[\t\u0007\u00111\u0014\u0005\b\u0005si\u0003\u0019\u0001B\u001e\u0003\u0019\u0019XO\u001a4jqB1!\u0011\u0005B\u0012\u0005gAaA!\u000b.\u0001\u0004y\u0006"
)
public final class Vector4 extends BigVector {
   private final int len1;
   private final Object[][] prefix2;
   private final int len12;
   private final Object[][][] prefix3;
   private final int len123;
   private final Object[][][][] data4;
   private final Object[][][] suffix3;
   private final Object[][] suffix2;

   public int len1() {
      return this.len1;
   }

   public Object[][] prefix2() {
      return this.prefix2;
   }

   public int len12() {
      return this.len12;
   }

   public Object[][][] prefix3() {
      return this.prefix3;
   }

   public int len123() {
      return this.len123;
   }

   public Object[][][][] data4() {
      return this.data4;
   }

   public Object[][][] suffix3() {
      return this.suffix3;
   }

   public Object[][] suffix2() {
      return this.suffix2;
   }

   private Vector4 copy(final Object[] prefix1, final int len1, final Object[][] prefix2, final int len12, final Object[][][] prefix3, final int len123, final Object[][][][] data4, final Object[][][] suffix3, final Object[][] suffix2, final Object[] suffix1, final int length0) {
      return new Vector4(prefix1, len1, prefix2, len12, prefix3, len123, data4, suffix3, suffix2, suffix1, length0);
   }

   private Object[] copy$default$1() {
      return this.prefix1();
   }

   private int copy$default$2() {
      return this.len1();
   }

   private Object[][] copy$default$3() {
      return this.prefix2();
   }

   private int copy$default$4() {
      return this.len12();
   }

   private Object[][][] copy$default$5() {
      return this.prefix3();
   }

   private int copy$default$6() {
      return this.len123();
   }

   private Object[][][][] copy$default$7() {
      return this.data4();
   }

   private Object[][][] copy$default$8() {
      return this.suffix3();
   }

   private Object[][] copy$default$9() {
      return this.suffix2();
   }

   private Object[] copy$default$10() {
      return this.suffix1();
   }

   private int copy$default$11() {
      return this.length0();
   }

   public Object apply(final int index) {
      if (index >= 0 && index < this.length0()) {
         int io = index - this.len123();
         if (io >= 0) {
            int i4 = io >>> 15;
            int i3 = io >>> 10 & 31;
            int i2 = io >>> 5 & 31;
            int i1 = io & 31;
            if (i4 < this.data4().length) {
               return this.data4()[i4][i3][i2][i1];
            } else if (i3 < this.suffix3().length) {
               return this.suffix3()[i3][i2][i1];
            } else {
               return i2 < this.suffix2().length ? this.suffix2()[i2][i1] : this.suffix1()[i1];
            }
         } else if (index >= this.len12()) {
            int io = index - this.len12();
            return this.prefix3()[io >>> 10][io >>> 5 & 31][io & 31];
         } else if (index >= this.len1()) {
            int io = index - this.len1();
            return this.prefix2()[io >>> 5][io & 31];
         } else {
            return this.prefix1()[index];
         }
      } else {
         throw this.ioob(index);
      }
   }

   public Vector updated(final int index, final Object elem) {
      if (index >= 0 && index < this.length0()) {
         if (index >= this.len123()) {
            int io = index - this.len123();
            int i4 = io >>> 15;
            int i3 = io >>> 10 & 31;
            int i2 = io >>> 5 & 31;
            int i1 = io & 31;
            if (i4 < this.data4().length) {
               VectorInline$ var149 = VectorInline$.MODULE$;
               Object[][][][] copyUpdate_a4 = this.data4();
               Object[][][][] copyUpdate_a4c = ((Object;)copyUpdate_a4).clone();
               Object[][][] copyUpdate_copyUpdate_a3c = copyUpdate_a4c[i4].clone();
               Object[][] copyUpdate_copyUpdate_copyUpdate_a2c = copyUpdate_copyUpdate_a3c[i3].clone();
               Object[] copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_copyUpdate_a2c[i2].clone();
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c[i1] = elem;
               Object[] var162 = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c;
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = null;
               copyUpdate_copyUpdate_copyUpdate_a2c[i2] = var162;
               Object[][] var161 = copyUpdate_copyUpdate_copyUpdate_a2c;
               copyUpdate_copyUpdate_copyUpdate_a2c = null;
               copyUpdate_copyUpdate_a3c[i3] = var161;
               Object[][][] var158 = copyUpdate_copyUpdate_a3c;
               copyUpdate_copyUpdate_a3c = null;
               copyUpdate_a4c[i4] = var158;
               var149 = copyUpdate_a4c;
               Object var114 = null;
               copyUpdate_a4c = null;
               Object[][][][] x$1 = var149;
               Object[] x$2 = this.copy$default$1();
               int x$3 = this.copy$default$2();
               Object[][] x$4 = this.copy$default$3();
               int x$5 = this.copy$default$4();
               Object[][][] x$6 = this.copy$default$5();
               int x$7 = this.copy$default$6();
               Object[][][] x$8 = this.copy$default$8();
               Object[][] x$9 = this.copy$default$9();
               Object[] x$10 = this.copy$default$10();
               int x$11 = this.copy$default$11();
               return new Vector4(x$2, x$3, x$4, x$5, x$6, x$7, x$1, x$8, x$9, x$10, x$11);
            } else if (i3 < this.suffix3().length) {
               VectorInline$ var147 = VectorInline$.MODULE$;
               Object[][][] copyUpdate_a3 = this.suffix3();
               Object[][][] copyUpdate_a3c = (([[[Ljava.lang.Object;)copyUpdate_a3).clone();
               Object[][] copyUpdate_copyUpdate_a2c = copyUpdate_a3c[i3].clone();
               Object[] copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_a2c[i2].clone();
               copyUpdate_copyUpdate_copyUpdate_a1c[i1] = elem;
               Object[] var160 = copyUpdate_copyUpdate_copyUpdate_a1c;
               copyUpdate_copyUpdate_copyUpdate_a1c = null;
               copyUpdate_copyUpdate_a2c[i2] = var160;
               Object[][] var157 = copyUpdate_copyUpdate_a2c;
               copyUpdate_copyUpdate_a2c = null;
               copyUpdate_a3c[i3] = var157;
               var147 = copyUpdate_a3c;
               Object var119 = null;
               copyUpdate_a3c = null;
               Object[][][] x$12 = var147;
               Object[] x$13 = this.copy$default$1();
               int x$14 = this.copy$default$2();
               Object[][] x$15 = this.copy$default$3();
               int x$16 = this.copy$default$4();
               Object[][][] x$17 = this.copy$default$5();
               int x$18 = this.copy$default$6();
               Object[][][][] x$19 = this.copy$default$7();
               Object[][] x$20 = this.copy$default$9();
               Object[] x$21 = this.copy$default$10();
               int x$22 = this.copy$default$11();
               return new Vector4(x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$12, x$20, x$21, x$22);
            } else if (i2 < this.suffix2().length) {
               VectorInline$ var145 = VectorInline$.MODULE$;
               Object[][] copyUpdate_a2 = this.suffix2();
               Object[][] copyUpdate_a2c = (([[Ljava.lang.Object;)copyUpdate_a2).clone();
               Object[] copyUpdate_copyUpdate_a1c = copyUpdate_a2c[i2].clone();
               copyUpdate_copyUpdate_a1c[i1] = elem;
               Object[] var156 = copyUpdate_copyUpdate_a1c;
               copyUpdate_copyUpdate_a1c = null;
               copyUpdate_a2c[i2] = var156;
               var145 = copyUpdate_a2c;
               Object var123 = null;
               copyUpdate_a2c = null;
               Object[][] x$23 = var145;
               Object[] x$24 = this.copy$default$1();
               int x$25 = this.copy$default$2();
               Object[][] x$26 = this.copy$default$3();
               int x$27 = this.copy$default$4();
               Object[][][] x$28 = this.copy$default$5();
               int x$29 = this.copy$default$6();
               Object[][][][] x$30 = this.copy$default$7();
               Object[][][] x$31 = this.copy$default$8();
               Object[] x$32 = this.copy$default$10();
               int x$33 = this.copy$default$11();
               return new Vector4(x$24, x$25, x$26, x$27, x$28, x$29, x$30, x$31, x$23, x$32, x$33);
            } else {
               VectorInline$ var143 = VectorInline$.MODULE$;
               Object[] copyUpdate_a1 = this.suffix1();
               Object[] copyUpdate_a1c = (([Ljava.lang.Object;)copyUpdate_a1).clone();
               copyUpdate_a1c[i1] = elem;
               var143 = copyUpdate_a1c;
               Object var126 = null;
               copyUpdate_a1c = null;
               Object[] x$34 = var143;
               Object[] x$35 = this.copy$default$1();
               int x$36 = this.copy$default$2();
               Object[][] x$37 = this.copy$default$3();
               int x$38 = this.copy$default$4();
               Object[][][] x$39 = this.copy$default$5();
               int x$40 = this.copy$default$6();
               Object[][][][] x$41 = this.copy$default$7();
               Object[][][] x$42 = this.copy$default$8();
               Object[][] x$43 = this.copy$default$9();
               int x$44 = this.copy$default$11();
               return new Vector4(x$35, x$36, x$37, x$38, x$39, x$40, x$41, x$42, x$43, x$34, x$44);
            }
         } else if (index >= this.len12()) {
            int io = index - this.len12();
            VectorInline$ var140 = VectorInline$.MODULE$;
            var140 = this.prefix3();
            int var152 = io >>> 10;
            int var154 = io >>> 5 & 31;
            int copyUpdate_idx1 = io & 31;
            int copyUpdate_idx2 = var154;
            int copyUpdate_idx3 = var152;
            Object[][][] copyUpdate_a3 = var140;
            Object[][][] copyUpdate_a3c = (([[[Ljava.lang.Object;)copyUpdate_a3).clone();
            Object[][] copyUpdate_copyUpdate_a2c = copyUpdate_a3c[copyUpdate_idx3].clone();
            Object[] copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_a2c[copyUpdate_idx2].clone();
            copyUpdate_copyUpdate_copyUpdate_a1c[copyUpdate_idx1] = elem;
            Object[] var159 = copyUpdate_copyUpdate_copyUpdate_a1c;
            copyUpdate_copyUpdate_copyUpdate_a1c = null;
            copyUpdate_copyUpdate_a2c[copyUpdate_idx2] = var159;
            Object[][] var155 = copyUpdate_copyUpdate_a2c;
            copyUpdate_copyUpdate_a2c = null;
            copyUpdate_a3c[copyUpdate_idx3] = var155;
            var140 = copyUpdate_a3c;
            Object var128 = null;
            copyUpdate_a3c = null;
            Object[][][] x$45 = var140;
            Object[] x$46 = this.copy$default$1();
            int x$47 = this.copy$default$2();
            Object[][] x$48 = this.copy$default$3();
            int x$49 = this.copy$default$4();
            int x$50 = this.copy$default$6();
            Object[][][][] x$51 = this.copy$default$7();
            Object[][][] x$52 = this.copy$default$8();
            Object[][] x$53 = this.copy$default$9();
            Object[] x$54 = this.copy$default$10();
            int x$55 = this.copy$default$11();
            return new Vector4(x$46, x$47, x$48, x$49, x$45, x$50, x$51, x$52, x$53, x$54, x$55);
         } else if (index >= this.len1()) {
            int io = index - this.len1();
            VectorInline$ var137 = VectorInline$.MODULE$;
            var137 = this.prefix2();
            int var151 = io >>> 5;
            int copyUpdate_idx1 = io & 31;
            int copyUpdate_idx2 = var151;
            Object[][] copyUpdate_a2 = var137;
            Object[][] copyUpdate_a2c = (([[Ljava.lang.Object;)copyUpdate_a2).clone();
            Object[] copyUpdate_copyUpdate_a1c = copyUpdate_a2c[copyUpdate_idx2].clone();
            copyUpdate_copyUpdate_a1c[copyUpdate_idx1] = elem;
            Object[] var153 = copyUpdate_copyUpdate_a1c;
            copyUpdate_copyUpdate_a1c = null;
            copyUpdate_a2c[copyUpdate_idx2] = var153;
            var137 = copyUpdate_a2c;
            Object var132 = null;
            copyUpdate_a2c = null;
            Object[][] x$56 = var137;
            Object[] x$57 = this.copy$default$1();
            int x$58 = this.copy$default$2();
            int x$59 = this.copy$default$4();
            Object[][][] x$60 = this.copy$default$5();
            int x$61 = this.copy$default$6();
            Object[][][][] x$62 = this.copy$default$7();
            Object[][][] x$63 = this.copy$default$8();
            Object[][] x$64 = this.copy$default$9();
            Object[] x$65 = this.copy$default$10();
            int x$66 = this.copy$default$11();
            return new Vector4(x$57, x$58, x$56, x$59, x$60, x$61, x$62, x$63, x$64, x$65, x$66);
         } else {
            VectorInline$ var10000 = VectorInline$.MODULE$;
            Object[] copyUpdate_a1c = this.prefix1().clone();
            copyUpdate_a1c[index] = elem;
            var10000 = copyUpdate_a1c;
            copyUpdate_a1c = null;
            int var10001 = this.copy$default$2();
            Object[][] var10002 = this.copy$default$3();
            int var10003 = this.copy$default$4();
            Object[][][] var10004 = this.copy$default$5();
            int var10005 = this.copy$default$6();
            Object[][][][] var10006 = this.copy$default$7();
            Object[][][] var10007 = this.copy$default$8();
            Object[][] var10008 = this.copy$default$9();
            Object[] var10009 = this.copy$default$10();
            int copy_length0 = this.copy$default$11();
            Object[] copy_suffix1 = var10009;
            Object[][] copy_suffix2 = var10008;
            Object[][][] copy_suffix3 = var10007;
            Object[][][][] copy_data4 = var10006;
            int copy_len123 = var10005;
            Object[][][] copy_prefix3 = var10004;
            int copy_len12 = var10003;
            Object[][] copy_prefix2 = var10002;
            int copy_len1 = var10001;
            Object[] copy_prefix1 = var10000;
            return new Vector4(copy_prefix1, copy_len1, copy_prefix2, copy_len12, copy_prefix3, copy_len123, copy_data4, copy_suffix3, copy_suffix2, copy_suffix1, copy_length0);
         }
      } else {
         throw this.ioob(index);
      }
   }

   public Vector appended(final Object elem) {
      if (this.suffix1().length < 32) {
         Object[] x$1 = VectorStatics$.MODULE$.copyAppend1(this.suffix1(), elem);
         int x$2 = this.length0() + 1;
         Object[] x$3 = this.prefix1();
         int x$4 = this.len1();
         Object[][] x$5 = this.prefix2();
         int x$6 = this.len12();
         Object[][][] x$7 = this.prefix3();
         int x$8 = this.len123();
         Object[][][][] x$9 = this.data4();
         Object[][][] x$10 = this.suffix3();
         Object[][] x$11 = this.suffix2();
         return new Vector4(x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$1, x$2);
      } else if (this.suffix2().length < 31) {
         Object[][] x$12 = VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1());
         VectorInline$ var61 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var61 = wrap1_a;
         wrap1_a = null;
         Object[] x$13 = var61;
         int x$14 = this.length0() + 1;
         Object[] x$15 = this.prefix1();
         int x$16 = this.len1();
         Object[][] x$17 = this.prefix2();
         int x$18 = this.len12();
         Object[][][] x$19 = this.prefix3();
         int x$20 = this.len123();
         Object[][][][] x$21 = this.data4();
         Object[][][] x$22 = this.suffix3();
         return new Vector4(x$15, x$16, x$17, x$18, x$19, x$20, x$21, x$22, x$12, x$13, x$14);
      } else if (this.suffix3().length < 31) {
         Object[][][] x$23 = VectorStatics$.MODULE$.copyAppend(this.suffix3(), VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1()));
         Object[][] x$24 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var59 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var59 = wrap1_a;
         wrap1_a = null;
         Object[] x$25 = var59;
         int x$26 = this.length0() + 1;
         Object[] x$27 = this.prefix1();
         int x$28 = this.len1();
         Object[][] x$29 = this.prefix2();
         int x$30 = this.len12();
         Object[][][] x$31 = this.prefix3();
         int x$32 = this.len123();
         Object[][][][] x$33 = this.data4();
         return new Vector4(x$27, x$28, x$29, x$30, x$31, x$32, x$33, x$23, x$24, x$25, x$26);
      } else if (this.data4().length < 30) {
         Object[][][][] x$34 = VectorStatics$.MODULE$.copyAppend(this.data4(), VectorStatics$.MODULE$.copyAppend(this.suffix3(), VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1())));
         Object[][][] x$35 = VectorStatics$.MODULE$.empty3();
         Object[][] x$36 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var10000 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10000 = wrap1_a;
         wrap1_a = null;
         Object[] x$37 = var10000;
         int x$38 = this.length0() + 1;
         Object[] x$39 = this.prefix1();
         int x$40 = this.len1();
         Object[][] x$41 = this.prefix2();
         int x$42 = this.len12();
         Object[][][] x$43 = this.prefix3();
         int x$44 = this.len123();
         return new Vector4(x$39, x$40, x$41, x$42, x$43, x$44, x$34, x$35, x$36, x$37, x$38);
      } else {
         Object[] var10002 = this.prefix1();
         int var10003 = this.len1();
         Object[][] var10004 = this.prefix2();
         int var10005 = this.len12();
         Object[][][] var10006 = this.prefix3();
         int var10007 = this.len123();
         Object[][][][] var10008 = this.data4();
         int var10009 = 983040 + this.len123();
         Object[][][][][] var10010 = VectorStatics$.MODULE$.empty5();
         VectorInline$ var10011 = VectorInline$.MODULE$;
         Object[][][] wrap4_x = VectorStatics$.MODULE$.copyAppend(this.suffix3(), VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1()));
         Object[][][][] wrap4_a = new Object[1][][][];
         wrap4_a[0] = wrap4_x;
         var10011 = wrap4_a;
         wrap4_x = null;
         wrap4_a = null;
         Object[][][] var10012 = VectorStatics$.MODULE$.empty3();
         Object[][] var10013 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var10014 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10014 = wrap1_a;
         wrap1_a = null;
         return new Vector5(var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, var10010, var10011, var10012, var10013, var10014, this.length0() + 1);
      }
   }

   public Vector prepended(final Object elem) {
      if (this.len1() < 32) {
         Object[] x$1 = VectorStatics$.MODULE$.copyPrepend1(elem, this.prefix1());
         int x$2 = this.len1() + 1;
         int x$3 = this.len12() + 1;
         int x$4 = this.len123() + 1;
         int x$5 = this.length0() + 1;
         Object[][] x$6 = this.prefix2();
         Object[][][] x$7 = this.prefix3();
         Object[][][][] x$8 = this.data4();
         Object[][][] x$9 = this.suffix3();
         Object[][] x$10 = this.suffix2();
         Object[] x$11 = this.suffix1();
         return new Vector4(x$1, x$2, x$6, x$3, x$7, x$4, x$8, x$9, x$10, x$11, x$5);
      } else if (this.len12() < 1024) {
         VectorInline$ var61 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var61 = wrap1_a;
         wrap1_a = null;
         Object[] x$12 = var61;
         Object[][] x$14 = VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2());
         int x$15 = this.len12() + 1;
         int x$16 = this.len123() + 1;
         int x$17 = this.length0() + 1;
         Object[][][] x$18 = this.prefix3();
         Object[][][][] x$19 = this.data4();
         Object[][][] x$20 = this.suffix3();
         Object[][] x$21 = this.suffix2();
         Object[] x$22 = this.suffix1();
         int copy_len1 = 1;
         return new Vector4(x$12, copy_len1, x$14, x$15, x$18, x$16, x$19, x$20, x$21, x$22, x$17);
      } else if (this.len123() < 32768) {
         VectorInline$ var59 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var59 = wrap1_a;
         wrap1_a = null;
         Object[] x$23 = var59;
         Object[][] x$25 = VectorStatics$.MODULE$.empty2();
         Object[][][] x$27 = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2()), this.prefix3());
         int x$28 = this.len123() + 1;
         int x$29 = this.length0() + 1;
         Object[][][][] x$30 = this.data4();
         Object[][][] x$31 = this.suffix3();
         Object[][] x$32 = this.suffix2();
         Object[] x$33 = this.suffix1();
         byte copy_len12 = 1;
         int copy_len1 = 1;
         return new Vector4(x$23, copy_len1, x$25, copy_len12, x$27, x$28, x$30, x$31, x$32, x$33, x$29);
      } else if (this.data4().length < 30) {
         VectorInline$ var10000 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10000 = wrap1_a;
         wrap1_a = null;
         Object[] x$34 = var10000;
         Object[][] x$36 = VectorStatics$.MODULE$.empty2();
         Object[][][] x$38 = VectorStatics$.MODULE$.empty3();
         Object[][][][] x$40 = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2()), this.prefix3()), this.data4());
         int x$41 = this.length0() + 1;
         Object[][][] x$42 = this.suffix3();
         Object[][] x$43 = this.suffix2();
         Object[] x$44 = this.suffix1();
         byte copy_len123 = 1;
         byte copy_len12 = 1;
         int copy_len1 = 1;
         return new Vector4(x$34, copy_len1, x$36, copy_len12, x$38, copy_len123, x$40, x$42, x$43, x$44, x$41);
      } else {
         VectorInline$ var10002 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10002 = wrap1_a;
         wrap1_a = null;
         Object[][] var10004 = VectorStatics$.MODULE$.empty2();
         Object[][][] var10006 = VectorStatics$.MODULE$.empty3();
         VectorInline$ var10008 = VectorInline$.MODULE$;
         Object[][][] wrap4_x = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2()), this.prefix3());
         Object[][][][] wrap4_a = new Object[1][][][];
         wrap4_a[0] = wrap4_x;
         var10008 = wrap4_a;
         wrap4_x = null;
         wrap4_a = null;
         return new Vector5(var10002, 1, var10004, 1, var10006, 1, var10008, this.len123() + 1, VectorStatics$.MODULE$.empty5(), this.data4(), this.suffix3(), this.suffix2(), this.suffix1(), this.length0() + 1);
      }
   }

   public Vector map(final Function1 f) {
      VectorStatics$ var10000 = VectorStatics$.MODULE$;
      Object[] mapElems1_a = this.prefix1();
      int mapElems1_i = 0;

      while(true) {
         if (mapElems1_i >= mapElems1_a.length) {
            var10000 = mapElems1_a;
            break;
         }

         Object mapElems1_v1 = mapElems1_a[mapElems1_i];
         Object mapElems1_v2 = f.apply(mapElems1_v1);
         if (mapElems1_v1 != mapElems1_v2) {
            Object[] mapElems1_mapElems1Rest_ac = new Object[mapElems1_a.length];
            if (mapElems1_i > 0) {
               System.arraycopy(mapElems1_a, 0, mapElems1_mapElems1Rest_ac, 0, mapElems1_i);
            }

            mapElems1_mapElems1Rest_ac[mapElems1_i] = mapElems1_v2;

            for(int mapElems1_mapElems1Rest_i = mapElems1_i + 1; mapElems1_mapElems1Rest_i < mapElems1_a.length; ++mapElems1_mapElems1Rest_i) {
               mapElems1_mapElems1Rest_ac[mapElems1_mapElems1Rest_i] = f.apply(mapElems1_a[mapElems1_mapElems1Rest_i]);
            }

            var10000 = mapElems1_mapElems1Rest_ac;
            mapElems1_mapElems1Rest_ac = null;
            break;
         }

         ++mapElems1_i;
      }

      mapElems1_a = null;
      Object var93 = null;
      Object var94 = null;
      Object var96 = null;
      Object[] x$1 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][] mapElems_a = this.prefix2();
      byte mapElems_n = 2;
      VectorStatics$ mapElems_this = var10000;
      if (mapElems_n == 1) {
         int mapElems_mapElems1_i = 0;

         while(true) {
            if (mapElems_mapElems1_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_mapElems1_v1 = mapElems_a[mapElems_mapElems1_i];
            Object mapElems_mapElems1_v2 = f.apply(mapElems_mapElems1_v1);
            if (mapElems_mapElems1_v1 != mapElems_mapElems1_v2) {
               Object[] mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElems_a.length];
               if (mapElems_mapElems1_i > 0) {
                  System.arraycopy(mapElems_a, 0, mapElems_mapElems1_mapElems1Rest_ac, 0, mapElems_mapElems1_i);
               }

               mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_i] = mapElems_mapElems1_v2;

               for(int mapElems_mapElems1_mapElems1Rest_i = mapElems_mapElems1_i + 1; mapElems_mapElems1_mapElems1Rest_i < mapElems_a.length; ++mapElems_mapElems1_mapElems1Rest_i) {
                  mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElems_a[mapElems_mapElems1_mapElems1Rest_i]);
               }

               var10000 = mapElems_mapElems1_mapElems1Rest_ac;
               mapElems_mapElems1_mapElems1Rest_ac = null;
               break;
            }

            ++mapElems_mapElems1_i;
         }

         Object var101 = null;
         Object var103 = null;
         Object var106 = null;
      } else {
         int mapElems_i = 0;

         while(true) {
            if (mapElems_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_v1 = mapElems_a[mapElems_i];
            Object[] mapElems_v2 = mapElems_this.mapElems(mapElems_n - 1, (Object[])mapElems_v1, f);
            if (mapElems_v1 != mapElems_v2) {
               Object[] mapElemsRest_ac = Array.newInstance(mapElems_a.getClass().getComponentType(), mapElems_a.length);
               if (mapElems_i > 0) {
                  System.arraycopy(mapElems_a, 0, mapElemsRest_ac, 0, mapElems_i);
               }

               mapElemsRest_ac[mapElems_i] = mapElems_v2;

               for(int mapElemsRest_i = mapElems_i + 1; mapElemsRest_i < mapElems_a.length; ++mapElemsRest_i) {
                  int var10002 = mapElems_n - 1;
                  Object[] mapElemsRest_mapElems_a = mapElems_a[mapElemsRest_i];
                  int mapElemsRest_mapElems_n = var10002;
                  Object[] var187;
                  if (mapElemsRest_mapElems_n != 1) {
                     int mapElemsRest_mapElems_i = 0;

                     while(true) {
                        if (mapElemsRest_mapElems_i >= mapElemsRest_mapElems_a.length) {
                           var187 = mapElemsRest_mapElems_a;
                           break;
                        }

                        Object mapElemsRest_mapElems_v1 = mapElemsRest_mapElems_a[mapElemsRest_mapElems_i];
                        Object[] mapElemsRest_mapElems_v2 = mapElems_this.mapElems(mapElemsRest_mapElems_n - 1, mapElemsRest_mapElems_v1, f);
                        if (mapElemsRest_mapElems_v1 != mapElemsRest_mapElems_v2) {
                           var187 = mapElems_this.mapElemsRest(mapElemsRest_mapElems_n, mapElemsRest_mapElems_a, f, mapElemsRest_mapElems_i, mapElemsRest_mapElems_v2);
                           break;
                        }

                        ++mapElemsRest_mapElems_i;
                     }
                  } else {
                     int mapElemsRest_mapElems_mapElems1_i = 0;

                     while(true) {
                        if (mapElemsRest_mapElems_mapElems1_i >= mapElemsRest_mapElems_a.length) {
                           var187 = mapElemsRest_mapElems_a;
                           break;
                        }

                        Object mapElemsRest_mapElems_mapElems1_v1 = mapElemsRest_mapElems_a[mapElemsRest_mapElems_mapElems1_i];
                        Object mapElemsRest_mapElems_mapElems1_v2 = f.apply(mapElemsRest_mapElems_mapElems1_v1);
                        if (mapElemsRest_mapElems_mapElems1_v1 != mapElemsRest_mapElems_mapElems1_v2) {
                           Object[] mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElemsRest_mapElems_a.length];
                           if (mapElemsRest_mapElems_mapElems1_i > 0) {
                              System.arraycopy(mapElemsRest_mapElems_a, 0, mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac, 0, mapElemsRest_mapElems_mapElems1_i);
                           }

                           mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac[mapElemsRest_mapElems_mapElems1_i] = mapElemsRest_mapElems_mapElems1_v2;

                           for(int mapElemsRest_mapElems_mapElems1_mapElems1Rest_i = mapElemsRest_mapElems_mapElems1_i + 1; mapElemsRest_mapElems_mapElems1_mapElems1Rest_i < mapElemsRest_mapElems_a.length; ++mapElemsRest_mapElems_mapElems1_mapElems1Rest_i) {
                              mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac[mapElemsRest_mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElemsRest_mapElems_a[mapElemsRest_mapElems_mapElems1_mapElems1Rest_i]);
                           }

                           var187 = mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac;
                           mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac = null;
                           break;
                        }

                        ++mapElemsRest_mapElems_mapElems1_i;
                     }

                     Object var164 = null;
                     Object var167 = null;
                     Object var171 = null;
                  }

                  mapElemsRest_mapElems_a = null;
                  Object var160 = null;
                  Object var162 = null;
                  Object var165 = null;
                  Object var168 = null;
                  Object var172 = null;
                  mapElemsRest_ac[mapElemsRest_i] = var187;
               }

               var10000 = mapElemsRest_ac;
               mapElemsRest_ac = null;
               Object var159 = null;
               Object var161 = null;
               Object var163 = null;
               Object var166 = null;
               Object var169 = null;
               Object var173 = null;
               break;
            }

            ++mapElems_i;
         }
      }

      Object var97 = null;
      mapElems_a = null;
      Object var99 = null;
      Object var100 = null;
      Object var102 = null;
      Object var104 = null;
      Object var107 = null;
      Object[][] x$2 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][][] mapElems_a = this.prefix3();
      byte mapElems_n = 3;
      VectorStatics$ mapElems_this = var10000;
      if (mapElems_n == 1) {
         int mapElems_mapElems1_i = 0;

         while(true) {
            if (mapElems_mapElems1_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_mapElems1_v1 = mapElems_a[mapElems_mapElems1_i];
            Object mapElems_mapElems1_v2 = f.apply(mapElems_mapElems1_v1);
            if (mapElems_mapElems1_v1 != mapElems_mapElems1_v2) {
               Object[] mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElems_a.length];
               if (mapElems_mapElems1_i > 0) {
                  System.arraycopy(mapElems_a, 0, mapElems_mapElems1_mapElems1Rest_ac, 0, mapElems_mapElems1_i);
               }

               mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_i] = mapElems_mapElems1_v2;

               for(int mapElems_mapElems1_mapElems1Rest_i = mapElems_mapElems1_i + 1; mapElems_mapElems1_mapElems1Rest_i < mapElems_a.length; ++mapElems_mapElems1_mapElems1Rest_i) {
                  mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElems_a[mapElems_mapElems1_mapElems1Rest_i]);
               }

               var10000 = mapElems_mapElems1_mapElems1Rest_ac;
               mapElems_mapElems1_mapElems1Rest_ac = null;
               break;
            }

            ++mapElems_mapElems1_i;
         }

         Object var112 = null;
         Object var114 = null;
         Object var117 = null;
      } else {
         int mapElems_i = 0;

         while(true) {
            if (mapElems_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_v1 = mapElems_a[mapElems_i];
            Object[] mapElems_v2 = mapElems_this.mapElems(mapElems_n - 1, (Object[])mapElems_v1, f);
            if (mapElems_v1 != mapElems_v2) {
               var10000 = mapElems_this.mapElemsRest(mapElems_n, mapElems_a, f, mapElems_i, mapElems_v2);
               break;
            }

            ++mapElems_i;
         }
      }

      Object var108 = null;
      mapElems_a = null;
      Object var110 = null;
      Object var111 = null;
      Object var113 = null;
      Object var115 = null;
      Object var118 = null;
      Object[][][] x$3 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][][][] mapElems_a = this.data4();
      byte mapElems_n = 4;
      VectorStatics$ mapElems_this = var10000;
      if (mapElems_n == 1) {
         int mapElems_mapElems1_i = 0;

         while(true) {
            if (mapElems_mapElems1_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_mapElems1_v1 = mapElems_a[mapElems_mapElems1_i];
            Object mapElems_mapElems1_v2 = f.apply(mapElems_mapElems1_v1);
            if (mapElems_mapElems1_v1 != mapElems_mapElems1_v2) {
               Object[] mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElems_a.length];
               if (mapElems_mapElems1_i > 0) {
                  System.arraycopy(mapElems_a, 0, mapElems_mapElems1_mapElems1Rest_ac, 0, mapElems_mapElems1_i);
               }

               mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_i] = mapElems_mapElems1_v2;

               for(int mapElems_mapElems1_mapElems1Rest_i = mapElems_mapElems1_i + 1; mapElems_mapElems1_mapElems1Rest_i < mapElems_a.length; ++mapElems_mapElems1_mapElems1Rest_i) {
                  mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElems_a[mapElems_mapElems1_mapElems1Rest_i]);
               }

               var10000 = mapElems_mapElems1_mapElems1Rest_ac;
               mapElems_mapElems1_mapElems1Rest_ac = null;
               break;
            }

            ++mapElems_mapElems1_i;
         }

         Object var123 = null;
         Object var125 = null;
         Object var128 = null;
      } else {
         int mapElems_i = 0;

         while(true) {
            if (mapElems_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_v1 = mapElems_a[mapElems_i];
            Object[] mapElems_v2 = mapElems_this.mapElems(mapElems_n - 1, (Object[])mapElems_v1, f);
            if (mapElems_v1 != mapElems_v2) {
               var10000 = mapElems_this.mapElemsRest(mapElems_n, mapElems_a, f, mapElems_i, mapElems_v2);
               break;
            }

            ++mapElems_i;
         }
      }

      Object var119 = null;
      mapElems_a = null;
      Object var121 = null;
      Object var122 = null;
      Object var124 = null;
      Object var126 = null;
      Object var129 = null;
      Object[][][][] x$4 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][][] mapElems_a = this.suffix3();
      byte mapElems_n = 3;
      VectorStatics$ mapElems_this = var10000;
      if (mapElems_n == 1) {
         int mapElems_mapElems1_i = 0;

         while(true) {
            if (mapElems_mapElems1_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_mapElems1_v1 = mapElems_a[mapElems_mapElems1_i];
            Object mapElems_mapElems1_v2 = f.apply(mapElems_mapElems1_v1);
            if (mapElems_mapElems1_v1 != mapElems_mapElems1_v2) {
               Object[] mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElems_a.length];
               if (mapElems_mapElems1_i > 0) {
                  System.arraycopy(mapElems_a, 0, mapElems_mapElems1_mapElems1Rest_ac, 0, mapElems_mapElems1_i);
               }

               mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_i] = mapElems_mapElems1_v2;

               for(int mapElems_mapElems1_mapElems1Rest_i = mapElems_mapElems1_i + 1; mapElems_mapElems1_mapElems1Rest_i < mapElems_a.length; ++mapElems_mapElems1_mapElems1Rest_i) {
                  mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElems_a[mapElems_mapElems1_mapElems1Rest_i]);
               }

               var10000 = mapElems_mapElems1_mapElems1Rest_ac;
               mapElems_mapElems1_mapElems1Rest_ac = null;
               break;
            }

            ++mapElems_mapElems1_i;
         }

         Object var134 = null;
         Object var136 = null;
         Object var139 = null;
      } else {
         int mapElems_i = 0;

         while(true) {
            if (mapElems_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_v1 = mapElems_a[mapElems_i];
            Object[] mapElems_v2 = mapElems_this.mapElems(mapElems_n - 1, (Object[])mapElems_v1, f);
            if (mapElems_v1 != mapElems_v2) {
               var10000 = mapElems_this.mapElemsRest(mapElems_n, mapElems_a, f, mapElems_i, mapElems_v2);
               break;
            }

            ++mapElems_i;
         }
      }

      Object var130 = null;
      mapElems_a = null;
      Object var132 = null;
      Object var133 = null;
      Object var135 = null;
      Object var137 = null;
      Object var140 = null;
      Object[][][] x$5 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][] mapElems_a = this.suffix2();
      byte mapElems_n = 2;
      VectorStatics$ mapElems_this = var10000;
      if (mapElems_n == 1) {
         int mapElems_mapElems1_i = 0;

         while(true) {
            if (mapElems_mapElems1_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_mapElems1_v1 = mapElems_a[mapElems_mapElems1_i];
            Object mapElems_mapElems1_v2 = f.apply(mapElems_mapElems1_v1);
            if (mapElems_mapElems1_v1 != mapElems_mapElems1_v2) {
               Object[] mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElems_a.length];
               if (mapElems_mapElems1_i > 0) {
                  System.arraycopy(mapElems_a, 0, mapElems_mapElems1_mapElems1Rest_ac, 0, mapElems_mapElems1_i);
               }

               mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_i] = mapElems_mapElems1_v2;

               for(int mapElems_mapElems1_mapElems1Rest_i = mapElems_mapElems1_i + 1; mapElems_mapElems1_mapElems1Rest_i < mapElems_a.length; ++mapElems_mapElems1_mapElems1Rest_i) {
                  mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElems_a[mapElems_mapElems1_mapElems1Rest_i]);
               }

               var10000 = mapElems_mapElems1_mapElems1Rest_ac;
               mapElems_mapElems1_mapElems1Rest_ac = null;
               break;
            }

            ++mapElems_mapElems1_i;
         }

         Object var145 = null;
         Object var147 = null;
         Object var150 = null;
      } else {
         int mapElems_i = 0;

         while(true) {
            if (mapElems_i >= mapElems_a.length) {
               var10000 = mapElems_a;
               break;
            }

            Object mapElems_v1 = mapElems_a[mapElems_i];
            Object[] mapElems_v2 = mapElems_this.mapElems(mapElems_n - 1, (Object[])mapElems_v1, f);
            if (mapElems_v1 != mapElems_v2) {
               var10000 = mapElems_this.mapElemsRest(mapElems_n, mapElems_a, f, mapElems_i, mapElems_v2);
               break;
            }

            ++mapElems_i;
         }
      }

      Object var141 = null;
      mapElems_a = null;
      Object var143 = null;
      Object var144 = null;
      Object var146 = null;
      Object var148 = null;
      Object var151 = null;
      Object[][] x$6 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[] mapElems1_a = this.suffix1();
      int mapElems1_i = 0;

      while(true) {
         if (mapElems1_i >= mapElems1_a.length) {
            var10000 = mapElems1_a;
            break;
         }

         Object mapElems1_v1 = mapElems1_a[mapElems1_i];
         Object mapElems1_v2 = f.apply(mapElems1_v1);
         if (mapElems1_v1 != mapElems1_v2) {
            Object[] mapElems1_mapElems1Rest_ac = new Object[mapElems1_a.length];
            if (mapElems1_i > 0) {
               System.arraycopy(mapElems1_a, 0, mapElems1_mapElems1Rest_ac, 0, mapElems1_i);
            }

            mapElems1_mapElems1Rest_ac[mapElems1_i] = mapElems1_v2;

            for(int mapElems1_mapElems1Rest_i = mapElems1_i + 1; mapElems1_mapElems1Rest_i < mapElems1_a.length; ++mapElems1_mapElems1Rest_i) {
               mapElems1_mapElems1Rest_ac[mapElems1_mapElems1Rest_i] = f.apply(mapElems1_a[mapElems1_mapElems1Rest_i]);
            }

            var10000 = mapElems1_mapElems1Rest_ac;
            mapElems1_mapElems1Rest_ac = null;
            break;
         }

         ++mapElems1_i;
      }

      mapElems1_a = null;
      Object var153 = null;
      Object var154 = null;
      Object var156 = null;
      Object[] x$7 = var10000;
      int x$8 = this.copy$default$2();
      int x$9 = this.copy$default$4();
      int x$10 = this.copy$default$6();
      int x$11 = this.copy$default$11();
      return new Vector4(x$1, x$8, x$2, x$9, x$3, x$10, x$4, x$5, x$6, x$7, x$11);
   }

   public Vector slice0(final int lo, final int hi) {
      VectorSliceBuilder b = new VectorSliceBuilder(lo, hi);
      b.consider(1, this.prefix1());
      b.consider(2, this.prefix2());
      b.consider(3, this.prefix3());
      b.consider(4, this.data4());
      b.consider(3, this.suffix3());
      b.consider(2, this.suffix2());
      b.consider(1, this.suffix1());
      return b.result();
   }

   public Vector tail() {
      if (this.len1() > 1) {
         VectorInline$ var10000 = VectorInline$.MODULE$;
         Object[] copyTail_a = this.prefix1();
         var10000 = Arrays.copyOfRange(copyTail_a, 1, copyTail_a.length);
         copyTail_a = null;
         Object[] x$1 = var10000;
         int x$2 = this.len1() - 1;
         int x$3 = this.len12() - 1;
         int x$4 = this.len123() - 1;
         int x$5 = this.length0() - 1;
         Object[][] x$6 = this.prefix2();
         Object[][][] x$7 = this.prefix3();
         Object[][][][] x$8 = this.data4();
         Object[][][] x$9 = this.suffix3();
         Object[][] x$10 = this.suffix2();
         Object[] x$11 = this.suffix1();
         return new Vector4(x$1, x$2, x$6, x$3, x$7, x$4, x$8, x$9, x$10, x$11, x$5);
      } else {
         return this.slice0(1, this.length0());
      }
   }

   public Vector init() {
      if (this.suffix1().length > 1) {
         VectorInline$ var10000 = VectorInline$.MODULE$;
         Object[] copyInit_a = this.suffix1();
         var10000 = Arrays.copyOfRange(copyInit_a, 0, copyInit_a.length - 1);
         copyInit_a = null;
         Object[] x$1 = var10000;
         int x$2 = this.length0() - 1;
         Object[] x$3 = this.prefix1();
         int x$4 = this.len1();
         Object[][] x$5 = this.prefix2();
         int x$6 = this.len12();
         Object[][][] x$7 = this.prefix3();
         int x$8 = this.len123();
         Object[][][][] x$9 = this.data4();
         Object[][][] x$10 = this.suffix3();
         Object[][] x$11 = this.suffix2();
         return new Vector4(x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$1, x$2);
      } else {
         return this.slice0(0, this.length0() - 1);
      }
   }

   public int vectorSliceCount() {
      return 7;
   }

   public Object[] vectorSlice(final int idx) {
      switch (idx) {
         case 0:
            return this.prefix1();
         case 1:
            return this.prefix2();
         case 2:
            return this.prefix3();
         case 3:
            return this.data4();
         case 4:
            return this.suffix3();
         case 5:
            return this.suffix2();
         case 6:
            return this.suffix1();
         default:
            throw new MatchError(idx);
      }
   }

   public int vectorSlicePrefixLength(final int idx) {
      switch (idx) {
         case 0:
            return this.len1();
         case 1:
            return this.len12();
         case 2:
            return this.len123();
         case 3:
            return this.len123() + this.data4().length * '耀';
         case 4:
            return this.len123() + this.data4().length * '耀' + this.suffix3().length * 1024;
         case 5:
            return this.length0() - this.suffix1().length;
         case 6:
            return this.length0();
         default:
            throw new MatchError(idx);
      }
   }

   public Vector prependedAll0(final IterableOnce prefix, final int k) {
      Object[] var3 = VectorStatics$.MODULE$.prepend1IfSpace(this.prefix1(), prefix);
      if (var3 == null) {
         return super.prependedAll0(prefix, k);
      } else {
         int diff = var3.length - this.prefix1().length;
         int x$2 = this.len1() + diff;
         int x$3 = this.len12() + diff;
         int x$4 = this.len123() + diff;
         int x$5 = this.length0() + diff;
         Object[][] x$6 = this.prefix2();
         Object[][][] x$7 = this.prefix3();
         Object[][][][] x$8 = this.data4();
         Object[][][] x$9 = this.suffix3();
         Object[][] x$10 = this.suffix2();
         Object[] x$11 = this.suffix1();
         return new Vector4(var3, x$2, x$6, x$3, x$7, x$4, x$8, x$9, x$10, x$11, x$5);
      }
   }

   public Vector appendedAll0(final IterableOnce suffix, final int k) {
      Object[] suffix1b = VectorStatics$.MODULE$.append1IfSpace(this.suffix1(), suffix);
      if (suffix1b != null) {
         int x$2 = this.length0() - this.suffix1().length + suffix1b.length;
         Object[] x$3 = this.prefix1();
         int x$4 = this.len1();
         Object[][] x$5 = this.prefix2();
         int x$6 = this.len12();
         Object[][][] x$7 = this.prefix3();
         int x$8 = this.len123();
         Object[][][][] x$9 = this.data4();
         Object[][][] x$10 = this.suffix3();
         Object[][] x$11 = this.suffix2();
         return new Vector4(x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, suffix1b, x$2);
      } else {
         return super.appendedAll0(suffix, k);
      }
   }

   public Vector4(final Object[] _prefix1, final int len1, final Object[][] prefix2, final int len12, final Object[][][] prefix3, final int len123, final Object[][][][] data4, final Object[][][] suffix3, final Object[][] suffix2, final Object[] _suffix1, final int _length0) {
      super(_prefix1, _suffix1, _length0);
      this.len1 = len1;
      this.prefix2 = prefix2;
      this.len12 = len12;
      this.prefix3 = prefix3;
      this.len123 = len123;
      this.data4 = data4;
      this.suffix3 = suffix3;
      this.suffix2 = suffix2;
   }
}
