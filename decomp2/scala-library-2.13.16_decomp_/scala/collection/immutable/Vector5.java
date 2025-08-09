package scala.collection.immutable;

import [[[[[Ljava.lang.Object;;
import java.util.Arrays;
import scala.Function1;
import scala.MatchError;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tmd\u0001B\u001c9\r}B\u0011\"\u0015\u0001\u0003\u0002\u0003\u0006IA\u00152\t\u0013\u0019\u0004!Q1A\u0005\u0002a:\u0007\u0002C6\u0001\u0005\u0003\u0005\u000b\u0011\u00025\t\u00131\u0004!Q1A\u0005\u0002aj\u0007\u0002C9\u0001\u0005\u0003\u0005\u000b\u0011\u00028\t\u0013I\u0004!Q1A\u0005\u0002a:\u0007\u0002C:\u0001\u0005\u0003\u0005\u000b\u0011\u00025\t\u0013Q\u0004!Q1A\u0005\u0002a*\b\u0002C=\u0001\u0005\u0003\u0005\u000b\u0011\u0002<\t\u0013i\u0004!Q1A\u0005\u0002a:\u0007\u0002C>\u0001\u0005\u0003\u0005\u000b\u0011\u00025\t\u0013q\u0004!Q1A\u0005\u0002aj\b\"CA\u0002\u0001\t\u0005\t\u0015!\u0003\u007f\u0011)\t)\u0001\u0001BC\u0002\u0013\u0005\u0001h\u001a\u0005\n\u0003\u000f\u0001!\u0011!Q\u0001\n!D1\"!\u0003\u0001\u0005\u000b\u0007I\u0011\u0001\u001d\u0002\f!Q\u00111\u0003\u0001\u0003\u0002\u0003\u0006I!!\u0004\t\u0015\u0005U\u0001A!b\u0001\n\u0003AT\u0010C\u0005\u0002\u0018\u0001\u0011\t\u0011)A\u0005}\"Q\u0011\u0011\u0004\u0001\u0003\u0006\u0004%\t\u0001O;\t\u0013\u0005m\u0001A!A!\u0002\u00131\bBCA\u000f\u0001\t\u0015\r\u0011\"\u00019[\"I\u0011q\u0004\u0001\u0003\u0002\u0003\u0006IA\u001c\u0005\f\u0003C\u0001!\u0011!Q\u0001\nI\u000b\u0019\u0003C\u0006\u0002(\u0001\u0011\t\u0011)A\u0005Q\u0006%\u0002bBA\u0017\u0001\u0011\u0005\u0011q\u0006\u0005\t\u0003\u001f\u0002\u0001\u0015\"\u0003\u0002R!I\u0011\u0011\u0010\u0001\u0012\u0002\u0013%\u00111\u0010\u0005\n\u0003#\u0003\u0011\u0013!C\u0005\u0003'C\u0011\"a&\u0001#\u0003%I!!'\t\u0013\u0005u\u0005!%A\u0005\n\u0005M\u0005\"CAP\u0001E\u0005I\u0011BAQ\u0011%\t)\u000bAI\u0001\n\u0013\t\u0019\nC\u0005\u0002(\u0002\t\n\u0011\"\u0003\u0002*\"I\u0011Q\u0016\u0001\u0012\u0002\u0013%\u00111\u0013\u0005\n\u0003_\u0003\u0011\u0013!C\u0005\u0003cC\u0011\"!.\u0001#\u0003%I!!+\t\u0013\u0005]\u0006!%A\u0005\n\u0005\u0005\u0006\"CA]\u0001E\u0005I\u0011BAM\u0011%\tY\fAI\u0001\n\u0013\tY\bC\u0005\u0002>\u0002\t\n\u0011\"\u0003\u0002\u0014\"9\u0011q\u0018\u0001\u0005\u0002\u0005\u0005\u0007bBAe\u0001\u0011\u0005\u00131\u001a\u0005\b\u0003?\u0004A\u0011IAq\u0011\u001d\ti\u000f\u0001C!\u0003_Dq!a?\u0001\t\u0003\ni\u0010\u0003\u0005\u0003\u0012\u0001\u0001K\u0011\u0003B\n\u0011\u001d\u0011y\u0002\u0001C!\u0005CAqAa\t\u0001\t\u0003\u0012\t\u0003C\u0004\u0003&\u0001!\t\u0002O4\t\u0011\t\u001d\u0002\u0001\"\u00059\u0005SA\u0001B!\u0012\u0001\t#A$q\t\u0005\t\u0005\u0017\u0002\u0001\u0015\"\u0015\u0003N!A!q\r\u0001!\n#\u0012IGA\u0004WK\u000e$xN]\u001b\u000b\u0005eR\u0014!C5n[V$\u0018M\u00197f\u0015\tYD(\u0001\u0006d_2dWm\u0019;j_:T\u0011!P\u0001\u0006g\u000e\fG.Y\u0002\u0001+\t\u0001ui\u0005\u0002\u0001\u0003B\u0019!iQ#\u000e\u0003aJ!\u0001\u0012\u001d\u0003\u0013\tKwMV3di>\u0014\bC\u0001$H\u0019\u0001!a\u0001\u0013\u0001\u0005\u0006\u0004I%!A!\u0012\u0005)s\u0005CA&M\u001b\u0005a\u0014BA'=\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aS(\n\u0005Ac$aA!os\u0006Aq\f\u001d:fM&D\u0018\u0007\u0005\u0002T?:\u0011A+\u0018\b\u0003+rs!AV.\u000f\u0005]SV\"\u0001-\u000b\u0005es\u0014A\u0002\u001fs_>$h(C\u0001>\u0013\tYD(\u0003\u0002:u%\u0011a\fO\u0001\r-\u0016\u001cGo\u001c:J]2Lg.Z\u0005\u0003A\u0006\u0014A!\u0011:sc)\u0011a\fO\u0005\u0003G\u0012\fq\u0001\u001d:fM&D\u0018'\u0003\u0002fq\t1a+Z2u_J\fA\u0001\\3ocU\t\u0001\u000e\u0005\u0002LS&\u0011!\u000e\u0010\u0002\u0004\u0013:$\u0018!\u00027f]F\u0002\u0013a\u00029sK\u001aL\u0007PM\u000b\u0002]B\u00111k\\\u0005\u0003a\u0006\u0014A!\u0011:se\u0005A\u0001O]3gSb\u0014\u0004%A\u0003mK:\f$'\u0001\u0004mK:\f$\u0007I\u0001\baJ,g-\u001b=4+\u00051\bCA*x\u0013\tA\u0018M\u0001\u0003BeJ\u001c\u0014\u0001\u00039sK\u001aL\u0007p\r\u0011\u0002\r1,g.\r\u001a4\u0003\u001daWM\\\u00193g\u0001\nq\u0001\u001d:fM&DH'F\u0001\u007f!\t\u0019v0C\u0002\u0002\u0002\u0005\u0014A!\u0011:si\u0005A\u0001O]3gSb$\u0004%A\u0004mK:\f$g\r\u001b\u0002\u00111,g.\r\u001a4i\u0001\nQ\u0001Z1uCV*\"!!\u0004\u0011\u0007M\u000by!C\u0002\u0002\u0012\u0005\u0014A!\u0011:sk\u00051A-\u0019;bk\u0001\nqa];gM&DH'\u0001\u0005tk\u001a4\u0017\u000e\u001f\u001b!\u0003\u001d\u0019XO\u001a4jqN\n\u0001b];gM&D8\u0007I\u0001\bgV4g-\u001b=3\u0003!\u0019XO\u001a4jqJ\u0002\u0013\u0001C0tk\u001a4\u0017\u000e_\u0019\n\u0007\u0005\u00152)A\u0004tk\u001a4\u0017\u000e_\u0019\u0002\u0011}cWM\\4uQBJ1!a\u000bD\u0003\u001daWM\\4uQB\na\u0001P5oSRtDCHA\u0019\u0003g\t)$a\u000e\u0002:\u0005m\u0012QHA \u0003\u0003\n\u0019%!\u0012\u0002H\u0005%\u00131JA'!\r\u0011\u0005!\u0012\u0005\u0006#j\u0001\rA\u0015\u0005\u0006Mj\u0001\r\u0001\u001b\u0005\u0006Yj\u0001\rA\u001c\u0005\u0006ej\u0001\r\u0001\u001b\u0005\u0006ij\u0001\rA\u001e\u0005\u0006uj\u0001\r\u0001\u001b\u0005\u0006yj\u0001\rA \u0005\u0007\u0003\u000bQ\u0002\u0019\u00015\t\u000f\u0005%!\u00041\u0001\u0002\u000e!1\u0011Q\u0003\u000eA\u0002yDa!!\u0007\u001b\u0001\u00041\bBBA\u000f5\u0001\u0007a\u000e\u0003\u0004\u0002\"i\u0001\rA\u0015\u0005\u0007\u0003OQ\u0002\u0019\u00015\u0002\t\r|\u0007/\u001f\u000b\u001f\u0003'\n)&a\u0016\u0002Z\u0005m\u0013QLA0\u0003C\n\u0019'!\u001a\u0002h\u0005%\u00141NA7\u0003_\u00022A\u0011\u0001K\u0011\u001d\u00197\u0004%AA\u0002ICqAZ\u000e\u0011\u0002\u0003\u0007\u0001\u000eC\u0004m7A\u0005\t\u0019\u00018\t\u000fI\\\u0002\u0013!a\u0001Q\"9Ao\u0007I\u0001\u0002\u00041\bb\u0002>\u001c!\u0003\u0005\r\u0001\u001b\u0005\byn\u0001\n\u00111\u0001\u007f\u0011!\t)a\u0007I\u0001\u0002\u0004A\u0007\"CA\u00057A\u0005\t\u0019AA\u0007\u0011!\t)b\u0007I\u0001\u0002\u0004q\b\u0002CA\r7A\u0005\t\u0019\u0001<\t\u0011\u0005u1\u0004%AA\u00029D\u0001\"!\n\u001c!\u0003\u0005\rA\u0015\u0005\t\u0003WY\u0002\u0013!a\u0001Q\"\u001a1$a\u001d\u0011\u0007-\u000b)(C\u0002\u0002xq\u0012a!\u001b8mS:,\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003{R3AUA@W\t\t\t\t\u0005\u0003\u0002\u0004\u00065UBAAC\u0015\u0011\t9)!#\u0002\u0013Ut7\r[3dW\u0016$'bAAFy\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005=\u0015Q\u0011\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003+S3\u0001[A@\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a'+\u00079\fy(\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u00111\u0015\u0016\u0004m\u0006}\u0014AD2paf$C-\u001a4bk2$HEN\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00138+\t\tYKK\u0002\u007f\u0003\u007f\nabY8qs\u0012\"WMZ1vYR$\u0003(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001d\u0016\u0005\u0005M&\u0006BA\u0007\u0003\u007f\nqbY8qs\u0012\"WMZ1vYR$\u0013\u0007M\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132c\u0005y1m\u001c9zI\u0011,g-Y;mi\u0012\n$'A\bd_BLH\u0005Z3gCVdG\u000fJ\u00194\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE\"\u0014!B1qa2LHcA#\u0002D\"1\u0011Q\u0019\u0016A\u0002!\fQ!\u001b8eKbD3AKA:\u0003\u001d)\b\u000fZ1uK\u0012,B!!4\u0002TR1\u0011qZAm\u00037\u0004BA\u00113\u0002RB\u0019a)a5\u0005\u000f\u0005U7F1\u0001\u0002X\n\t!)\u0005\u0002F\u001d\"1\u0011QY\u0016A\u0002!Dq!!8,\u0001\u0004\t\t.\u0001\u0003fY\u0016l\u0017\u0001C1qa\u0016tG-\u001a3\u0016\t\u0005\r\u0018\u0011\u001e\u000b\u0005\u0003K\fY\u000f\u0005\u0003CI\u0006\u001d\bc\u0001$\u0002j\u00129\u0011Q\u001b\u0017C\u0002\u0005]\u0007bBAoY\u0001\u0007\u0011q]\u0001\naJ,\u0007/\u001a8eK\u0012,B!!=\u0002xR!\u00111_A}!\u0011\u0011E-!>\u0011\u0007\u0019\u000b9\u0010B\u0004\u0002V6\u0012\r!a6\t\u000f\u0005uW\u00061\u0001\u0002v\u0006\u0019Q.\u00199\u0016\t\u0005}(Q\u0001\u000b\u0005\u0005\u0003\u00119\u0001\u0005\u0003CI\n\r\u0001c\u0001$\u0003\u0006\u00111\u0011Q\u001b\u0018C\u0002%CqA!\u0003/\u0001\u0004\u0011Y!A\u0001g!\u0019Y%QB#\u0003\u0004%\u0019!q\u0002\u001f\u0003\u0013\u0019+hn\u0019;j_:\f\u0014AB:mS\u000e,\u0007\u0007\u0006\u0004\u0003\u0016\t]!1\u0004\t\u0004\u0005\u0012,\u0005B\u0002B\r_\u0001\u0007\u0001.\u0001\u0002m_\"1!QD\u0018A\u0002!\f!\u0001[5\u0002\tQ\f\u0017\u000e\\\u000b\u0003\u0005+\tA!\u001b8ji\u0006\u0001b/Z2u_J\u001cF.[2f\u0007>,h\u000e^\u0001\fm\u0016\u001cGo\u001c:TY&\u001cW\r\u0006\u0003\u0003,\t\u0005\u0003\u0007\u0002B\u0017\u0005k\u0001Ra\u0013B\u0018\u0005gI1A!\r=\u0005\u0015\t%O]1z!\r1%Q\u0007\u0003\f\u0005o\u0019\u0014\u0011!A\u0001\u0006\u0003\u0011ID\u0001\u0003`IE:\u0014c\u0001&\u0003<A\u00191J!\u0010\n\u0007\t}BH\u0001\u0004B]f\u0014VM\u001a\u0005\u0007\u0005\u0007\u001a\u0004\u0019\u00015\u0002\u0007%$\u00070A\fwK\u000e$xN]*mS\u000e,\u0007K]3gSbdUM\\4uQR\u0019\u0001N!\u0013\t\r\t\rC\u00071\u0001i\u00035\u0001(/\u001a9f]\u0012,G-\u00117maU!!q\nB+)\u0019\u0011\tFa\u0016\u0003dA!!\t\u001aB*!\r1%Q\u000b\u0003\b\u0003+,$\u0019AAl\u0011\u001d\u0011I&\u000ea\u0001\u00057\na\u0001\u001d:fM&D\bC\u0002B/\u0005?\u0012\u0019&D\u0001;\u0013\r\u0011\tG\u000f\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0005\u0007\u0005K*\u0004\u0019\u00015\u0002\u0003-\fA\"\u00199qK:$W\rZ!mYB*BAa\u001b\u0003rQ1!Q\u000eB:\u0005s\u0002BA\u00113\u0003pA\u0019aI!\u001d\u0005\u000f\u0005UgG1\u0001\u0002X\"9!Q\u000f\u001cA\u0002\t]\u0014AB:vM\u001aL\u0007\u0010\u0005\u0004\u0003^\t}#q\u000e\u0005\u0007\u0005K2\u0004\u0019\u00015"
)
public final class Vector5 extends BigVector {
   private final int len1;
   private final Object[][] prefix2;
   private final int len12;
   private final Object[][][] prefix3;
   private final int len123;
   private final Object[][][][] prefix4;
   private final int len1234;
   private final Object[][][][][] data5;
   private final Object[][][][] suffix4;
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

   public Object[][][][] prefix4() {
      return this.prefix4;
   }

   public int len1234() {
      return this.len1234;
   }

   public Object[][][][][] data5() {
      return this.data5;
   }

   public Object[][][][] suffix4() {
      return this.suffix4;
   }

   public Object[][][] suffix3() {
      return this.suffix3;
   }

   public Object[][] suffix2() {
      return this.suffix2;
   }

   private Vector5 copy(final Object[] prefix1, final int len1, final Object[][] prefix2, final int len12, final Object[][][] prefix3, final int len123, final Object[][][][] prefix4, final int len1234, final Object[][][][][] data5, final Object[][][][] suffix4, final Object[][][] suffix3, final Object[][] suffix2, final Object[] suffix1, final int length0) {
      return new Vector5(prefix1, len1, prefix2, len12, prefix3, len123, prefix4, len1234, data5, suffix4, suffix3, suffix2, suffix1, length0);
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
      return this.prefix4();
   }

   private int copy$default$8() {
      return this.len1234();
   }

   private Object[][][][][] copy$default$9() {
      return this.data5();
   }

   private Object[][][][] copy$default$10() {
      return this.suffix4();
   }

   private Object[][][] copy$default$11() {
      return this.suffix3();
   }

   private Object[][] copy$default$12() {
      return this.suffix2();
   }

   private Object[] copy$default$13() {
      return this.suffix1();
   }

   private int copy$default$14() {
      return this.length0();
   }

   public Object apply(final int index) {
      if (index >= 0 && index < this.length0()) {
         int io = index - this.len1234();
         if (io >= 0) {
            int i5 = io >>> 20;
            int i4 = io >>> 15 & 31;
            int i3 = io >>> 10 & 31;
            int i2 = io >>> 5 & 31;
            int i1 = io & 31;
            if (i5 < this.data5().length) {
               return this.data5()[i5][i4][i3][i2][i1];
            } else if (i4 < this.suffix4().length) {
               return this.suffix4()[i4][i3][i2][i1];
            } else if (i3 < this.suffix3().length) {
               return this.suffix3()[i3][i2][i1];
            } else {
               return i2 < this.suffix2().length ? this.suffix2()[i2][i1] : this.suffix1()[i1];
            }
         } else if (index >= this.len123()) {
            int io = index - this.len123();
            return this.prefix4()[io >>> 15][io >>> 10 & 31][io >>> 5 & 31][io & 31];
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
         if (index >= this.len1234()) {
            int io = index - this.len1234();
            int i5 = io >>> 20;
            int i4 = io >>> 15 & 31;
            int i3 = io >>> 10 & 31;
            int i2 = io >>> 5 & 31;
            int i1 = io & 31;
            if (i5 < this.data5().length) {
               VectorInline$ var231 = VectorInline$.MODULE$;
               Object[][][][][] copyUpdate_a5 = this.data5();
               Object[][][][][] copyUpdate_a5c = ((Object;)copyUpdate_a5).clone();
               Object[][][][] copyUpdate_copyUpdate_a4c = copyUpdate_a5c[i5].clone();
               Object[][][] copyUpdate_copyUpdate_copyUpdate_a3c = copyUpdate_copyUpdate_a4c[i4].clone();
               Object[][] copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c = copyUpdate_copyUpdate_copyUpdate_a3c[i3].clone();
               Object[] copyUpdate_copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c[i2].clone();
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c[i1] = elem;
               Object[] var254 = copyUpdate_copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c;
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = null;
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c[i2] = var254;
               Object[][] var253 = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c;
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c = null;
               copyUpdate_copyUpdate_copyUpdate_a3c[i3] = var253;
               Object[][][] var250 = copyUpdate_copyUpdate_copyUpdate_a3c;
               copyUpdate_copyUpdate_copyUpdate_a3c = null;
               copyUpdate_copyUpdate_a4c[i4] = var250;
               Object[][][][] var244 = copyUpdate_copyUpdate_a4c;
               copyUpdate_copyUpdate_a4c = null;
               copyUpdate_a5c[i5] = var244;
               var231 = copyUpdate_a5c;
               Object var180 = null;
               copyUpdate_a5c = null;
               Object[][][][][] x$1 = var231;
               Object[] x$2 = this.copy$default$1();
               int x$3 = this.copy$default$2();
               Object[][] x$4 = this.copy$default$3();
               int x$5 = this.copy$default$4();
               Object[][][] x$6 = this.copy$default$5();
               int x$7 = this.copy$default$6();
               Object[][][][] x$8 = this.copy$default$7();
               int x$9 = this.copy$default$8();
               Object[][][][] x$10 = this.copy$default$10();
               Object[][][] x$11 = this.copy$default$11();
               Object[][] x$12 = this.copy$default$12();
               Object[] x$13 = this.copy$default$13();
               int x$14 = this.copy$default$14();
               return new Vector5(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$1, x$10, x$11, x$12, x$13, x$14);
            } else if (i4 < this.suffix4().length) {
               VectorInline$ var229 = VectorInline$.MODULE$;
               Object[][][][] copyUpdate_a4 = this.suffix4();
               Object[][][][] copyUpdate_a4c = (([[[[Ljava.lang.Object;)copyUpdate_a4).clone();
               Object[][][] copyUpdate_copyUpdate_a3c = copyUpdate_a4c[i4].clone();
               Object[][] copyUpdate_copyUpdate_copyUpdate_a2c = copyUpdate_copyUpdate_a3c[i3].clone();
               Object[] copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_copyUpdate_a2c[i2].clone();
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c[i1] = elem;
               Object[] var252 = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c;
               copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = null;
               copyUpdate_copyUpdate_copyUpdate_a2c[i2] = var252;
               Object[][] var249 = copyUpdate_copyUpdate_copyUpdate_a2c;
               copyUpdate_copyUpdate_copyUpdate_a2c = null;
               copyUpdate_copyUpdate_a3c[i3] = var249;
               Object[][][] var243 = copyUpdate_copyUpdate_a3c;
               copyUpdate_copyUpdate_a3c = null;
               copyUpdate_a4c[i4] = var243;
               var229 = copyUpdate_a4c;
               Object var186 = null;
               copyUpdate_a4c = null;
               Object[][][][] x$15 = var229;
               Object[] x$16 = this.copy$default$1();
               int x$17 = this.copy$default$2();
               Object[][] x$18 = this.copy$default$3();
               int x$19 = this.copy$default$4();
               Object[][][] x$20 = this.copy$default$5();
               int x$21 = this.copy$default$6();
               Object[][][][] x$22 = this.copy$default$7();
               int x$23 = this.copy$default$8();
               Object[][][][][] x$24 = this.copy$default$9();
               Object[][][] x$25 = this.copy$default$11();
               Object[][] x$26 = this.copy$default$12();
               Object[] x$27 = this.copy$default$13();
               int x$28 = this.copy$default$14();
               return new Vector5(x$16, x$17, x$18, x$19, x$20, x$21, x$22, x$23, x$24, x$15, x$25, x$26, x$27, x$28);
            } else if (i3 < this.suffix3().length) {
               VectorInline$ var227 = VectorInline$.MODULE$;
               Object[][][] copyUpdate_a3 = this.suffix3();
               Object[][][] copyUpdate_a3c = (([[[Ljava.lang.Object;)copyUpdate_a3).clone();
               Object[][] copyUpdate_copyUpdate_a2c = copyUpdate_a3c[i3].clone();
               Object[] copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_a2c[i2].clone();
               copyUpdate_copyUpdate_copyUpdate_a1c[i1] = elem;
               Object[] var248 = copyUpdate_copyUpdate_copyUpdate_a1c;
               copyUpdate_copyUpdate_copyUpdate_a1c = null;
               copyUpdate_copyUpdate_a2c[i2] = var248;
               Object[][] var242 = copyUpdate_copyUpdate_a2c;
               copyUpdate_copyUpdate_a2c = null;
               copyUpdate_a3c[i3] = var242;
               var227 = copyUpdate_a3c;
               Object var191 = null;
               copyUpdate_a3c = null;
               Object[][][] x$29 = var227;
               Object[] x$30 = this.copy$default$1();
               int x$31 = this.copy$default$2();
               Object[][] x$32 = this.copy$default$3();
               int x$33 = this.copy$default$4();
               Object[][][] x$34 = this.copy$default$5();
               int x$35 = this.copy$default$6();
               Object[][][][] x$36 = this.copy$default$7();
               int x$37 = this.copy$default$8();
               Object[][][][][] x$38 = this.copy$default$9();
               Object[][][][] x$39 = this.copy$default$10();
               Object[][] x$40 = this.copy$default$12();
               Object[] x$41 = this.copy$default$13();
               int x$42 = this.copy$default$14();
               return new Vector5(x$30, x$31, x$32, x$33, x$34, x$35, x$36, x$37, x$38, x$39, x$29, x$40, x$41, x$42);
            } else if (i2 < this.suffix2().length) {
               VectorInline$ var225 = VectorInline$.MODULE$;
               Object[][] copyUpdate_a2 = this.suffix2();
               Object[][] copyUpdate_a2c = (([[Ljava.lang.Object;)copyUpdate_a2).clone();
               Object[] copyUpdate_copyUpdate_a1c = copyUpdate_a2c[i2].clone();
               copyUpdate_copyUpdate_a1c[i1] = elem;
               Object[] var241 = copyUpdate_copyUpdate_a1c;
               copyUpdate_copyUpdate_a1c = null;
               copyUpdate_a2c[i2] = var241;
               var225 = copyUpdate_a2c;
               Object var195 = null;
               copyUpdate_a2c = null;
               Object[][] x$43 = var225;
               Object[] x$44 = this.copy$default$1();
               int x$45 = this.copy$default$2();
               Object[][] x$46 = this.copy$default$3();
               int x$47 = this.copy$default$4();
               Object[][][] x$48 = this.copy$default$5();
               int x$49 = this.copy$default$6();
               Object[][][][] x$50 = this.copy$default$7();
               int x$51 = this.copy$default$8();
               Object[][][][][] x$52 = this.copy$default$9();
               Object[][][][] x$53 = this.copy$default$10();
               Object[][][] x$54 = this.copy$default$11();
               Object[] x$55 = this.copy$default$13();
               int x$56 = this.copy$default$14();
               return new Vector5(x$44, x$45, x$46, x$47, x$48, x$49, x$50, x$51, x$52, x$53, x$54, x$43, x$55, x$56);
            } else {
               VectorInline$ var223 = VectorInline$.MODULE$;
               Object[] copyUpdate_a1 = this.suffix1();
               Object[] copyUpdate_a1c = (([Ljava.lang.Object;)copyUpdate_a1).clone();
               copyUpdate_a1c[i1] = elem;
               var223 = copyUpdate_a1c;
               Object var198 = null;
               copyUpdate_a1c = null;
               Object[] x$57 = var223;
               Object[] x$58 = this.copy$default$1();
               int x$59 = this.copy$default$2();
               Object[][] x$60 = this.copy$default$3();
               int x$61 = this.copy$default$4();
               Object[][][] x$62 = this.copy$default$5();
               int x$63 = this.copy$default$6();
               Object[][][][] x$64 = this.copy$default$7();
               int x$65 = this.copy$default$8();
               Object[][][][][] x$66 = this.copy$default$9();
               Object[][][][] x$67 = this.copy$default$10();
               Object[][][] x$68 = this.copy$default$11();
               Object[][] x$69 = this.copy$default$12();
               int x$70 = this.copy$default$14();
               return new Vector5(x$58, x$59, x$60, x$61, x$62, x$63, x$64, x$65, x$66, x$67, x$68, x$69, x$57, x$70);
            }
         } else if (index >= this.len123()) {
            int io = index - this.len123();
            VectorInline$ var220 = VectorInline$.MODULE$;
            var220 = this.prefix4();
            int var235 = io >>> 15;
            int var239 = io >>> 10 & 31;
            int var245 = io >>> 5 & 31;
            int copyUpdate_idx1 = io & 31;
            int copyUpdate_idx2 = var245;
            int copyUpdate_idx3 = var239;
            int copyUpdate_idx4 = var235;
            Object[][][][] copyUpdate_a4 = var220;
            Object[][][][] copyUpdate_a4c = (([[[[Ljava.lang.Object;)copyUpdate_a4).clone();
            Object[][][] copyUpdate_copyUpdate_a3c = copyUpdate_a4c[copyUpdate_idx4].clone();
            Object[][] copyUpdate_copyUpdate_copyUpdate_a2c = copyUpdate_copyUpdate_a3c[copyUpdate_idx3].clone();
            Object[] copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_copyUpdate_a2c[copyUpdate_idx2].clone();
            copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c[copyUpdate_idx1] = elem;
            Object[] var251 = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c;
            copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = null;
            copyUpdate_copyUpdate_copyUpdate_a2c[copyUpdate_idx2] = var251;
            Object[][] var247 = copyUpdate_copyUpdate_copyUpdate_a2c;
            copyUpdate_copyUpdate_copyUpdate_a2c = null;
            copyUpdate_copyUpdate_a3c[copyUpdate_idx3] = var247;
            Object[][][] var240 = copyUpdate_copyUpdate_a3c;
            copyUpdate_copyUpdate_a3c = null;
            copyUpdate_a4c[copyUpdate_idx4] = var240;
            var220 = copyUpdate_a4c;
            Object var200 = null;
            copyUpdate_a4c = null;
            Object[][][][] x$71 = var220;
            Object[] x$72 = this.copy$default$1();
            int x$73 = this.copy$default$2();
            Object[][] x$74 = this.copy$default$3();
            int x$75 = this.copy$default$4();
            Object[][][] x$76 = this.copy$default$5();
            int x$77 = this.copy$default$6();
            int x$78 = this.copy$default$8();
            Object[][][][][] x$79 = this.copy$default$9();
            Object[][][][] x$80 = this.copy$default$10();
            Object[][][] x$81 = this.copy$default$11();
            Object[][] x$82 = this.copy$default$12();
            Object[] x$83 = this.copy$default$13();
            int x$84 = this.copy$default$14();
            return new Vector5(x$72, x$73, x$74, x$75, x$76, x$77, x$71, x$78, x$79, x$80, x$81, x$82, x$83, x$84);
         } else if (index >= this.len12()) {
            int io = index - this.len12();
            VectorInline$ var217 = VectorInline$.MODULE$;
            var217 = this.prefix3();
            int var234 = io >>> 10;
            int var237 = io >>> 5 & 31;
            int copyUpdate_idx1 = io & 31;
            int copyUpdate_idx2 = var237;
            int copyUpdate_idx3 = var234;
            Object[][][] copyUpdate_a3 = var217;
            Object[][][] copyUpdate_a3c = (([[[Ljava.lang.Object;)copyUpdate_a3).clone();
            Object[][] copyUpdate_copyUpdate_a2c = copyUpdate_a3c[copyUpdate_idx3].clone();
            Object[] copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_a2c[copyUpdate_idx2].clone();
            copyUpdate_copyUpdate_copyUpdate_a1c[copyUpdate_idx1] = elem;
            Object[] var246 = copyUpdate_copyUpdate_copyUpdate_a1c;
            copyUpdate_copyUpdate_copyUpdate_a1c = null;
            copyUpdate_copyUpdate_a2c[copyUpdate_idx2] = var246;
            Object[][] var238 = copyUpdate_copyUpdate_a2c;
            copyUpdate_copyUpdate_a2c = null;
            copyUpdate_a3c[copyUpdate_idx3] = var238;
            var217 = copyUpdate_a3c;
            Object var205 = null;
            copyUpdate_a3c = null;
            Object[][][] x$85 = var217;
            Object[] x$86 = this.copy$default$1();
            int x$87 = this.copy$default$2();
            Object[][] x$88 = this.copy$default$3();
            int x$89 = this.copy$default$4();
            int x$90 = this.copy$default$6();
            Object[][][][] x$91 = this.copy$default$7();
            int x$92 = this.copy$default$8();
            Object[][][][][] x$93 = this.copy$default$9();
            Object[][][][] x$94 = this.copy$default$10();
            Object[][][] x$95 = this.copy$default$11();
            Object[][] x$96 = this.copy$default$12();
            Object[] x$97 = this.copy$default$13();
            int x$98 = this.copy$default$14();
            return new Vector5(x$86, x$87, x$88, x$89, x$85, x$90, x$91, x$92, x$93, x$94, x$95, x$96, x$97, x$98);
         } else if (index >= this.len1()) {
            int io = index - this.len1();
            VectorInline$ var214 = VectorInline$.MODULE$;
            var214 = this.prefix2();
            int var233 = io >>> 5;
            int copyUpdate_idx1 = io & 31;
            int copyUpdate_idx2 = var233;
            Object[][] copyUpdate_a2 = var214;
            Object[][] copyUpdate_a2c = (([[Ljava.lang.Object;)copyUpdate_a2).clone();
            Object[] copyUpdate_copyUpdate_a1c = copyUpdate_a2c[copyUpdate_idx2].clone();
            copyUpdate_copyUpdate_a1c[copyUpdate_idx1] = elem;
            Object[] var236 = copyUpdate_copyUpdate_a1c;
            copyUpdate_copyUpdate_a1c = null;
            copyUpdate_a2c[copyUpdate_idx2] = var236;
            var214 = copyUpdate_a2c;
            Object var209 = null;
            copyUpdate_a2c = null;
            Object[][] x$99 = var214;
            Object[] x$100 = this.copy$default$1();
            int x$101 = this.copy$default$2();
            int x$102 = this.copy$default$4();
            Object[][][] x$103 = this.copy$default$5();
            int x$104 = this.copy$default$6();
            Object[][][][] x$105 = this.copy$default$7();
            int x$106 = this.copy$default$8();
            Object[][][][][] x$107 = this.copy$default$9();
            Object[][][][] x$108 = this.copy$default$10();
            Object[][][] x$109 = this.copy$default$11();
            Object[][] x$110 = this.copy$default$12();
            Object[] x$111 = this.copy$default$13();
            int x$112 = this.copy$default$14();
            return new Vector5(x$100, x$101, x$99, x$102, x$103, x$104, x$105, x$106, x$107, x$108, x$109, x$110, x$111, x$112);
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
            int var10007 = this.copy$default$8();
            Object[][][][][] var10008 = this.copy$default$9();
            Object[][][][] var10009 = this.copy$default$10();
            Object[][][] var10010 = this.copy$default$11();
            Object[][] var10011 = this.copy$default$12();
            Object[] var10012 = this.copy$default$13();
            int copy_length0 = this.copy$default$14();
            Object[] copy_suffix1 = var10012;
            Object[][] copy_suffix2 = var10011;
            Object[][][] copy_suffix3 = var10010;
            Object[][][][] copy_suffix4 = var10009;
            Object[][][][][] copy_data5 = var10008;
            int copy_len1234 = var10007;
            Object[][][][] copy_prefix4 = var10006;
            int copy_len123 = var10005;
            Object[][][] copy_prefix3 = var10004;
            int copy_len12 = var10003;
            Object[][] copy_prefix2 = var10002;
            int copy_len1 = var10001;
            Object[] copy_prefix1 = var10000;
            return new Vector5(copy_prefix1, copy_len1, copy_prefix2, copy_len12, copy_prefix3, copy_len123, copy_prefix4, copy_len1234, copy_data5, copy_suffix4, copy_suffix3, copy_suffix2, copy_suffix1, copy_length0);
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
         Object[][][][] x$9 = this.prefix4();
         int x$10 = this.len1234();
         Object[][][][][] x$11 = this.data5();
         Object[][][][] x$12 = this.suffix4();
         Object[][][] x$13 = this.suffix3();
         Object[][] x$14 = this.suffix2();
         return new Vector5(x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$1, x$2);
      } else if (this.suffix2().length < 31) {
         Object[][] x$15 = VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1());
         VectorInline$ var91 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var91 = wrap1_a;
         wrap1_a = null;
         Object[] x$16 = var91;
         int x$17 = this.length0() + 1;
         Object[] x$18 = this.prefix1();
         int x$19 = this.len1();
         Object[][] x$20 = this.prefix2();
         int x$21 = this.len12();
         Object[][][] x$22 = this.prefix3();
         int x$23 = this.len123();
         Object[][][][] x$24 = this.prefix4();
         int x$25 = this.len1234();
         Object[][][][][] x$26 = this.data5();
         Object[][][][] x$27 = this.suffix4();
         Object[][][] x$28 = this.suffix3();
         return new Vector5(x$18, x$19, x$20, x$21, x$22, x$23, x$24, x$25, x$26, x$27, x$28, x$15, x$16, x$17);
      } else if (this.suffix3().length < 31) {
         Object[][][] x$29 = VectorStatics$.MODULE$.copyAppend(this.suffix3(), VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1()));
         Object[][] x$30 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var89 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var89 = wrap1_a;
         wrap1_a = null;
         Object[] x$31 = var89;
         int x$32 = this.length0() + 1;
         Object[] x$33 = this.prefix1();
         int x$34 = this.len1();
         Object[][] x$35 = this.prefix2();
         int x$36 = this.len12();
         Object[][][] x$37 = this.prefix3();
         int x$38 = this.len123();
         Object[][][][] x$39 = this.prefix4();
         int x$40 = this.len1234();
         Object[][][][][] x$41 = this.data5();
         Object[][][][] x$42 = this.suffix4();
         return new Vector5(x$33, x$34, x$35, x$36, x$37, x$38, x$39, x$40, x$41, x$42, x$29, x$30, x$31, x$32);
      } else if (this.suffix4().length < 31) {
         Object[][][][] x$43 = VectorStatics$.MODULE$.copyAppend(this.suffix4(), VectorStatics$.MODULE$.copyAppend(this.suffix3(), VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1())));
         Object[][][] x$44 = VectorStatics$.MODULE$.empty3();
         Object[][] x$45 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var87 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var87 = wrap1_a;
         wrap1_a = null;
         Object[] x$46 = var87;
         int x$47 = this.length0() + 1;
         Object[] x$48 = this.prefix1();
         int x$49 = this.len1();
         Object[][] x$50 = this.prefix2();
         int x$51 = this.len12();
         Object[][][] x$52 = this.copy$default$5();
         int x$53 = this.copy$default$6();
         Object[][][][] x$54 = this.copy$default$7();
         int x$55 = this.copy$default$8();
         Object[][][][][] x$56 = this.copy$default$9();
         return new Vector5(x$48, x$49, x$50, x$51, x$52, x$53, x$54, x$55, x$56, x$43, x$44, x$45, x$46, x$47);
      } else if (this.data5().length < 30) {
         Object[][][][][] x$57 = VectorStatics$.MODULE$.copyAppend(this.data5(), VectorStatics$.MODULE$.copyAppend(this.suffix4(), VectorStatics$.MODULE$.copyAppend(this.suffix3(), VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1()))));
         Object[][][][] x$58 = VectorStatics$.MODULE$.empty4();
         Object[][][] x$59 = VectorStatics$.MODULE$.empty3();
         Object[][] x$60 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var10000 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10000 = wrap1_a;
         wrap1_a = null;
         Object[] x$61 = var10000;
         int x$62 = this.length0() + 1;
         Object[] x$63 = this.copy$default$1();
         int x$64 = this.copy$default$2();
         Object[][] x$65 = this.copy$default$3();
         int x$66 = this.copy$default$4();
         Object[][][] x$67 = this.copy$default$5();
         int x$68 = this.copy$default$6();
         Object[][][][] x$69 = this.copy$default$7();
         int x$70 = this.copy$default$8();
         return new Vector5(x$63, x$64, x$65, x$66, x$67, x$68, x$69, x$70, x$57, x$58, x$59, x$60, x$61, x$62);
      } else {
         Object[] var10002 = this.prefix1();
         int var10003 = this.len1();
         Object[][] var10004 = this.prefix2();
         int var10005 = this.len12();
         Object[][][] var10006 = this.prefix3();
         int var10007 = this.len123();
         Object[][][][] var10008 = this.prefix4();
         int var10009 = this.len1234();
         Object[][][][][] var10010 = this.data5();
         int var10011 = 31457280 + this.len1234();
         Object[][][][][][] var10012 = VectorStatics$.MODULE$.empty6();
         VectorInline$ var10013 = VectorInline$.MODULE$;
         Object[][][][] wrap5_x = VectorStatics$.MODULE$.copyAppend(this.suffix4(), VectorStatics$.MODULE$.copyAppend(this.suffix3(), VectorStatics$.MODULE$.copyAppend(this.suffix2(), this.suffix1())));
         Object[][][][][] wrap5_a = new Object[1][][][][];
         wrap5_a[0] = wrap5_x;
         var10013 = wrap5_a;
         wrap5_x = null;
         wrap5_a = null;
         Object[][][][] var10014 = VectorStatics$.MODULE$.empty4();
         Object[][][] var10015 = VectorStatics$.MODULE$.empty3();
         Object[][] var10016 = VectorStatics$.MODULE$.empty2();
         VectorInline$ var10017 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10017 = wrap1_a;
         wrap1_a = null;
         return new Vector6(var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009, var10010, var10011, var10012, var10013, var10014, var10015, var10016, var10017, this.length0() + 1);
      }
   }

   public Vector prepended(final Object elem) {
      if (this.len1() < 32) {
         Object[] x$1 = VectorStatics$.MODULE$.copyPrepend1(elem, this.prefix1());
         int x$2 = this.len1() + 1;
         int x$3 = this.len12() + 1;
         int x$4 = this.len123() + 1;
         int x$5 = this.len1234() + 1;
         int x$6 = this.length0() + 1;
         Object[][] x$7 = this.prefix2();
         Object[][][] x$8 = this.prefix3();
         Object[][][][] x$9 = this.prefix4();
         Object[][][][][] x$10 = this.data5();
         Object[][][][] x$11 = this.suffix4();
         Object[][][] x$12 = this.suffix3();
         Object[][] x$13 = this.suffix2();
         Object[] x$14 = this.suffix1();
         return new Vector5(x$1, x$2, x$7, x$3, x$8, x$4, x$9, x$5, x$10, x$11, x$12, x$13, x$14, x$6);
      } else if (this.len12() < 1024) {
         VectorInline$ var91 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var91 = wrap1_a;
         wrap1_a = null;
         Object[] x$15 = var91;
         Object[][] x$17 = VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2());
         int x$18 = this.len12() + 1;
         int x$19 = this.len123() + 1;
         int x$20 = this.len1234() + 1;
         int x$21 = this.length0() + 1;
         Object[][][] x$22 = this.prefix3();
         Object[][][][] x$23 = this.prefix4();
         Object[][][][][] x$24 = this.data5();
         Object[][][][] x$25 = this.suffix4();
         Object[][][] x$26 = this.suffix3();
         Object[][] x$27 = this.suffix2();
         Object[] x$28 = this.suffix1();
         int copy_len1 = 1;
         return new Vector5(x$15, copy_len1, x$17, x$18, x$22, x$19, x$23, x$20, x$24, x$25, x$26, x$27, x$28, x$21);
      } else if (this.len123() < 32768) {
         VectorInline$ var89 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var89 = wrap1_a;
         wrap1_a = null;
         Object[] x$29 = var89;
         Object[][] x$31 = VectorStatics$.MODULE$.empty2();
         Object[][][] x$33 = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2()), this.prefix3());
         int x$34 = this.len123() + 1;
         int x$35 = this.len1234() + 1;
         int x$36 = this.length0() + 1;
         Object[][][][] x$37 = this.prefix4();
         Object[][][][][] x$38 = this.data5();
         Object[][][][] x$39 = this.suffix4();
         Object[][][] x$40 = this.suffix3();
         Object[][] x$41 = this.suffix2();
         Object[] x$42 = this.suffix1();
         byte copy_len12 = 1;
         int copy_len1 = 1;
         return new Vector5(x$29, copy_len1, x$31, copy_len12, x$33, x$34, x$37, x$35, x$38, x$39, x$40, x$41, x$42, x$36);
      } else if (this.len1234() < 1048576) {
         VectorInline$ var87 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var87 = wrap1_a;
         wrap1_a = null;
         Object[] x$43 = var87;
         Object[][] x$45 = VectorStatics$.MODULE$.empty2();
         Object[][][] x$47 = VectorStatics$.MODULE$.empty3();
         Object[][][][] x$49 = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2()), this.prefix3()), this.prefix4());
         int x$50 = this.len1234() + 1;
         int x$51 = this.length0() + 1;
         Object[][][][][] x$52 = this.data5();
         Object[][][][] x$53 = this.suffix4();
         Object[][][] x$54 = this.suffix3();
         Object[][] x$55 = this.suffix2();
         Object[] x$56 = this.suffix1();
         byte copy_len123 = 1;
         byte copy_len12 = 1;
         int copy_len1 = 1;
         return new Vector5(x$43, copy_len1, x$45, copy_len12, x$47, copy_len123, x$49, x$50, x$52, x$53, x$54, x$55, x$56, x$51);
      } else if (this.data5().length < 30) {
         VectorInline$ var10000 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10000 = wrap1_a;
         wrap1_a = null;
         Object[] x$57 = var10000;
         Object[][] x$59 = VectorStatics$.MODULE$.empty2();
         Object[][][] x$61 = VectorStatics$.MODULE$.empty3();
         Object[][][][] x$63 = VectorStatics$.MODULE$.empty4();
         Object[][][][][] x$65 = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2()), this.prefix3()), this.prefix4()), this.data5());
         int x$66 = this.length0() + 1;
         Object[][][][] x$67 = this.suffix4();
         Object[][][] x$68 = this.suffix3();
         Object[][] x$69 = this.suffix2();
         Object[] x$70 = this.suffix1();
         byte copy_len1234 = 1;
         byte copy_len123 = 1;
         byte copy_len12 = 1;
         int copy_len1 = 1;
         return new Vector5(x$57, copy_len1, x$59, copy_len12, x$61, copy_len123, x$63, copy_len1234, x$65, x$67, x$68, x$69, x$70, x$66);
      } else {
         VectorInline$ var10002 = VectorInline$.MODULE$;
         Object[] wrap1_a = new Object[1];
         wrap1_a[0] = elem;
         var10002 = wrap1_a;
         wrap1_a = null;
         Object[][] var10004 = VectorStatics$.MODULE$.empty2();
         Object[][][] var10006 = VectorStatics$.MODULE$.empty3();
         Object[][][][] var10008 = VectorStatics$.MODULE$.empty4();
         VectorInline$ var10010 = VectorInline$.MODULE$;
         Object[][][][] wrap5_x = VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(VectorStatics$.MODULE$.copyPrepend(this.prefix1(), this.prefix2()), this.prefix3()), this.prefix4());
         Object[][][][][] wrap5_a = new Object[1][][][][];
         wrap5_a[0] = wrap5_x;
         var10010 = wrap5_a;
         wrap5_x = null;
         wrap5_a = null;
         return new Vector6(var10002, 1, var10004, 1, var10006, 1, var10008, 1, var10010, this.len1234() + 1, VectorStatics$.MODULE$.empty6(), this.data5(), this.suffix4(), this.suffix3(), this.suffix2(), this.suffix1(), this.length0() + 1);
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
      Object var106 = null;
      Object var107 = null;
      Object var109 = null;
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

         Object var114 = null;
         Object var116 = null;
         Object var119 = null;
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

      Object var110 = null;
      mapElems_a = null;
      Object var112 = null;
      Object var113 = null;
      Object var115 = null;
      Object var117 = null;
      Object var120 = null;
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

         Object var125 = null;
         Object var127 = null;
         Object var130 = null;
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

      Object var121 = null;
      mapElems_a = null;
      Object var123 = null;
      Object var124 = null;
      Object var126 = null;
      Object var128 = null;
      Object var131 = null;
      Object[][][] x$3 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][][][] mapElems_a = this.prefix4();
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

         Object var136 = null;
         Object var138 = null;
         Object var141 = null;
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

      Object var132 = null;
      mapElems_a = null;
      Object var134 = null;
      Object var135 = null;
      Object var137 = null;
      Object var139 = null;
      Object var142 = null;
      Object[][][][] x$4 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][][][][] mapElems_a = this.data5();
      byte mapElems_n = 5;
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

         Object var147 = null;
         Object var149 = null;
         Object var152 = null;
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

      Object var143 = null;
      mapElems_a = null;
      Object var145 = null;
      Object var146 = null;
      Object var148 = null;
      Object var150 = null;
      Object var153 = null;
      Object[][][][][] x$5 = var10000;
      var10000 = VectorStatics$.MODULE$;
      Object[][][][] mapElems_a = this.suffix4();
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

         Object var158 = null;
         Object var160 = null;
         Object var163 = null;
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

      Object var154 = null;
      mapElems_a = null;
      Object var156 = null;
      Object var157 = null;
      Object var159 = null;
      Object var161 = null;
      Object var164 = null;
      Object[][][][] x$6 = var10000;
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

         Object var169 = null;
         Object var171 = null;
         Object var174 = null;
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

      Object var165 = null;
      mapElems_a = null;
      Object var167 = null;
      Object var168 = null;
      Object var170 = null;
      Object var172 = null;
      Object var175 = null;
      Object[][][] x$7 = var10000;
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

         Object var180 = null;
         Object var182 = null;
         Object var185 = null;
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

      Object var176 = null;
      mapElems_a = null;
      Object var178 = null;
      Object var179 = null;
      Object var181 = null;
      Object var183 = null;
      Object var186 = null;
      Object[][] x$8 = var10000;
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
      Object var188 = null;
      Object var189 = null;
      Object var191 = null;
      Object[] x$9 = var10000;
      int x$10 = this.copy$default$2();
      int x$11 = this.copy$default$4();
      int x$12 = this.copy$default$6();
      int x$13 = this.copy$default$8();
      int x$14 = this.copy$default$14();
      return new Vector5(x$1, x$10, x$2, x$11, x$3, x$12, x$4, x$13, x$5, x$6, x$7, x$8, x$9, x$14);
   }

   public Vector slice0(final int lo, final int hi) {
      VectorSliceBuilder b = new VectorSliceBuilder(lo, hi);
      b.consider(1, this.prefix1());
      b.consider(2, this.prefix2());
      b.consider(3, this.prefix3());
      b.consider(4, this.prefix4());
      b.consider(5, this.data5());
      b.consider(4, this.suffix4());
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
         int x$5 = this.len1234() - 1;
         int x$6 = this.length0() - 1;
         Object[][] x$7 = this.prefix2();
         Object[][][] x$8 = this.prefix3();
         Object[][][][] x$9 = this.prefix4();
         Object[][][][][] x$10 = this.data5();
         Object[][][][] x$11 = this.suffix4();
         Object[][][] x$12 = this.suffix3();
         Object[][] x$13 = this.suffix2();
         Object[] x$14 = this.suffix1();
         return new Vector5(x$1, x$2, x$7, x$3, x$8, x$4, x$9, x$5, x$10, x$11, x$12, x$13, x$14, x$6);
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
         Object[][][][] x$9 = this.prefix4();
         int x$10 = this.len1234();
         Object[][][][][] x$11 = this.data5();
         Object[][][][] x$12 = this.suffix4();
         Object[][][] x$13 = this.suffix3();
         Object[][] x$14 = this.suffix2();
         return new Vector5(x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$1, x$2);
      } else {
         return this.slice0(0, this.length0() - 1);
      }
   }

   public int vectorSliceCount() {
      return 9;
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
            return this.prefix4();
         case 4:
            return this.data5();
         case 5:
            return this.suffix4();
         case 6:
            return this.suffix3();
         case 7:
            return this.suffix2();
         case 8:
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
            return this.len1234();
         case 4:
            return this.len1234() + this.data5().length * 1048576;
         case 5:
            return this.len1234() + this.data5().length * 1048576 + this.suffix4().length * '耀';
         case 6:
            return this.len1234() + this.data5().length * 1048576 + this.suffix4().length * '耀' + this.suffix3().length * 1024;
         case 7:
            return this.length0() - this.suffix1().length;
         case 8:
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
         int x$5 = this.len1234() + diff;
         int x$6 = this.length0() + diff;
         Object[][] x$7 = this.prefix2();
         Object[][][] x$8 = this.prefix3();
         Object[][][][] x$9 = this.prefix4();
         Object[][][][][] x$10 = this.data5();
         Object[][][][] x$11 = this.suffix4();
         Object[][][] x$12 = this.suffix3();
         Object[][] x$13 = this.suffix2();
         Object[] x$14 = this.suffix1();
         return new Vector5(var3, x$2, x$7, x$3, x$8, x$4, x$9, x$5, x$10, x$11, x$12, x$13, x$14, x$6);
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
         Object[][][][] x$9 = this.prefix4();
         int x$10 = this.len1234();
         Object[][][][][] x$11 = this.data5();
         Object[][][][] x$12 = this.suffix4();
         Object[][][] x$13 = this.suffix3();
         Object[][] x$14 = this.suffix2();
         return new Vector5(x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, suffix1b, x$2);
      } else {
         return super.appendedAll0(suffix, k);
      }
   }

   public Vector5(final Object[] _prefix1, final int len1, final Object[][] prefix2, final int len12, final Object[][][] prefix3, final int len123, final Object[][][][] prefix4, final int len1234, final Object[][][][][] data5, final Object[][][][] suffix4, final Object[][][] suffix3, final Object[][] suffix2, final Object[] _suffix1, final int _length0) {
      super(_prefix1, _suffix1, _length0);
      this.len1 = len1;
      this.prefix2 = prefix2;
      this.len12 = len12;
      this.prefix3 = prefix3;
      this.len123 = len123;
      this.prefix4 = prefix4;
      this.len1234 = len1234;
      this.data5 = data5;
      this.suffix4 = suffix4;
      this.suffix3 = suffix3;
      this.suffix2 = suffix2;
   }
}
