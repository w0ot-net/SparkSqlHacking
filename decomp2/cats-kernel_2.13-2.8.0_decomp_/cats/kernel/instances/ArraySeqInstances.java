package cats.kernel.instances;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.ArraySeq.;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\teaa\u0002\u0013&!\u0003\r\t\u0001\f\u0005\u0006\u0003\u0002!\tA\u0011\u0005\b\u0003?\u0004A1AAq\u0011\u001d\t\u0019\u0010\u0001C\u0002\u0003k<QAN\u0013\t\u0002]2Q\u0001J\u0013\t\u0002aBQ!O\u0003\u0005\u0002i2\u0001bO\u0003\u0011\u0002\u0007\u0005Q\u0005\u0010\u0005\u0006\u0003\u001e!\tA\u0011\u0005\u0006G\u001e!\u0019\u0001\u001a\u0005\u0006_\u001e!\u0019\u0001\u001d\u0004\t\u007f\u0015\u0001\n1!\u0001&\u0001\")\u0011i\u0003C\u0001\u0005\")ai\u0003C\u0002\u000f\u001a!10\u0002\u0004}\u0011)\tIA\u0004B\u0001B\u0003-\u00111\u0002\u0005\u0007s9!\t!!\u0004\t\u000f\u0005Ua\u0002\"\u0002\u0002\u0018\u00191\u0011qE\u0003\u0005\u0003SA!\"!\u0003\u0013\u0005\u0003\u0005\u000b1BA\u001b\u0011\u0019I$\u0003\"\u0001\u00028!9\u0011q\b\n\u0005\u0006\u0005\u0005cABA'\u000b\u0011\ty\u0005\u0003\u0006\u0002\nY\u0011\t\u0011)A\u0006\u0003\u0007Ca!\u000f\f\u0005\u0002\u0005\u0015\u0005bBAG-\u0011\u0015\u0011q\u0012\u0004\u0007\u0003+*A!a\u0016\t\u0015\u0005%!D!A!\u0002\u0017\t\u0019\u0007\u0003\u0004:5\u0011\u0005\u0011Q\r\u0005\b\u0003[RBQAA8\r\u001d\t\u0019*\u0002\u0002*\u0003+Ca!\u000f\u0010\u0005\u0002\u0005\u0015\u0006bBAU=\u0011\u0005\u00111\u0016\u0005\b\u0003[sB\u0011AAX\u0011\u001d\t)L\bC!\u0003oCq!!1\u001f\t\u0003\n\u0019MA\tBeJ\f\u0017pU3r\u0013:\u001cH/\u00198dKNT!AJ\u0014\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0015*\u0003\u0019YWM\u001d8fY*\t!&\u0001\u0003dCR\u001c8\u0001A\n\u0004\u00015\u001a\u0004C\u0001\u00182\u001b\u0005y#\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iz#AB!osJ+g\r\u0005\u00025\u000f9\u0011Q\u0007B\u0007\u0002K\u0005\t\u0012I\u001d:bsN+\u0017/\u00138ti\u0006t7-Z:\u0011\u0005U*1CA\u0003.\u0003\u0019a\u0014N\\5u}Q\tqG\u0001\nBeJ\f\u0017pU3r\u0013:\u001cH/\u00198dKN\f4cA\u0004.{A\u0011ahC\u0007\u0002\u000b\t\u0011\u0012I\u001d:bsN+\u0017/\u00138ti\u0006t7-Z:3'\tYQ&\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0007B\u0011a\u0006R\u0005\u0003\u000b>\u0012A!\u00168ji\u0006Q2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012,\u0015OR8s\u0003J\u0014\u0018-_*fcV\u0011\u0001j\u0016\u000b\u0003\u0013\u0002\u00042AS&N\u001b\u00059\u0013B\u0001'(\u0005\t)\u0015\u000fE\u0002O'Vk\u0011a\u0014\u0006\u0003!F\u000b\u0011\"[7nkR\f'\r\\3\u000b\u0005I{\u0013AC2pY2,7\r^5p]&\u0011Ak\u0014\u0002\t\u0003J\u0014\u0018-_*fcB\u0011ak\u0016\u0007\u0001\t\u0015AVB1\u0001Z\u0005\u0005\t\u0015C\u0001.^!\tq3,\u0003\u0002]_\t9aj\u001c;iS:<\u0007C\u0001\u0018_\u0013\tyvFA\u0002B]fDq!Y\u0007\u0002\u0002\u0003\u000f!-\u0001\u0006fm&$WM\\2fIQ\u00022AS&V\u0003\u0011\u001a\u0017\r^:LKJtW\r\\*uIB\u000b'\u000f^5bY>\u0013H-\u001a:G_J\f%O]1z'\u0016\fXCA3l)\t1G\u000eE\u0002KO&L!\u0001[\u0014\u0003\u0019A\u000b'\u000f^5bY>\u0013H-\u001a:\u0011\u00079\u001b&\u000e\u0005\u0002WW\u0012)\u0001,\u0003b\u00013\"9Q.CA\u0001\u0002\bq\u0017AC3wS\u0012,gnY3%eA\u0019!j\u001a6\u00029\r\fGo]&fe:,Gn\u0015;e\u0011\u0006\u001c\bNR8s\u0003J\u0014\u0018-_*fcV\u0011\u0011o\u001e\u000b\u0003eb\u00042AS:v\u0013\t!xE\u0001\u0003ICND\u0007c\u0001(TmB\u0011ak\u001e\u0003\u00061*\u0011\r!\u0017\u0005\bs*\t\t\u0011q\u0001{\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004\u0015N4(!D!se\u0006L8+Z9Pe\u0012,'/F\u0002~\u0003\u000f\u00192AD\u0017\u007f!\u0011Qu0a\u0001\n\u0007\u0005\u0005qEA\u0003Pe\u0012,'\u000f\u0005\u0003O'\u0006\u0015\u0001c\u0001,\u0002\b\u0011)\u0001L\u0004b\u00013\u0006\u0011QM\u001e\t\u0005\u0015~\f)\u0001\u0006\u0002\u0002\u0010Q!\u0011\u0011CA\n!\u0011qd\"!\u0002\t\u000f\u0005%\u0001\u0003q\u0001\u0002\f\u000591m\\7qCJ,GCBA\r\u0003?\t\u0019\u0003E\u0002/\u00037I1!!\b0\u0005\rIe\u000e\u001e\u0005\b\u0003C\t\u0002\u0019AA\u0002\u0003\tA8\u000fC\u0004\u0002&E\u0001\r!a\u0001\u0002\u0005e\u001c(\u0001F!se\u0006L8+Z9QCJ$\u0018.\u00197Pe\u0012,'/\u0006\u0003\u0002,\u0005M2\u0003\u0002\n.\u0003[\u0001BAS4\u00020A!ajUA\u0019!\r1\u00161\u0007\u0003\u00061J\u0011\r!\u0017\t\u0005\u0015\u001e\f\t\u0004\u0006\u0002\u0002:Q!\u00111HA\u001f!\u0011q$#!\r\t\u000f\u0005%A\u0003q\u0001\u00026\u0005q\u0001/\u0019:uS\u0006d7i\\7qCJ,GCBA\"\u0003\u0013\nY\u0005E\u0002/\u0003\u000bJ1!a\u00120\u0005\u0019!u.\u001e2mK\"9\u0011\u0011E\u000bA\u0002\u0005=\u0002bBA\u0013+\u0001\u0007\u0011q\u0006\u0002\r\u0003J\u0014\u0018-_*fc\"\u000b7\u000f[\u000b\u0005\u0003#\nihE\u0003\u0017\u0003'\ny\b\u0005\u0003?5\u0005m$AC!se\u0006L8+Z9FcV!\u0011\u0011LA1'\u0011QR&a\u0017\u0011\t)[\u0015Q\f\t\u0005\u001dN\u000by\u0006E\u0002W\u0003C\"Q\u0001\u0017\u000eC\u0002e\u0003BAS&\u0002`Q\u0011\u0011q\r\u000b\u0005\u0003S\nY\u0007\u0005\u0003?5\u0005}\u0003bBA\u00059\u0001\u000f\u00111M\u0001\u0004KF4HCBA9\u0003o\nI\bE\u0002/\u0003gJ1!!\u001e0\u0005\u001d\u0011un\u001c7fC:Dq!!\t\u001e\u0001\u0004\ti\u0006C\u0004\u0002&u\u0001\r!!\u0018\u0011\u0007Y\u000bi\bB\u0003Y-\t\u0007\u0011\f\u0005\u0003Kg\u0006\u0005\u0005\u0003\u0002(T\u0003w\u0002BAS:\u0002|Q\u0011\u0011q\u0011\u000b\u0005\u0003\u0013\u000bY\t\u0005\u0003?-\u0005m\u0004bBA\u00051\u0001\u000f\u00111Q\u0001\u0005Q\u0006\u001c\b\u000e\u0006\u0003\u0002\u001a\u0005E\u0005bBA\u00113\u0001\u0007\u0011\u0011\u0011\u0002\u000f\u0003J\u0014\u0018-_*fc6{gn\\5e+\u0011\t9*a)\u0014\tyi\u0013\u0011\u0014\t\u0006\u0015\u0006m\u0015qT\u0005\u0004\u0003;;#AB'p]>LG\r\u0005\u0003O'\u0006\u0005\u0006c\u0001,\u0002$\u0012)\u0001L\bb\u00013R\u0011\u0011q\u0015\t\u0005}y\t\t+A\u0003f[B$\u00180\u0006\u0002\u0002 \u000691m\\7cS:,GCBAP\u0003c\u000b\u0019\fC\u0004\u0002\"\u0005\u0002\r!a(\t\u000f\u0005\u0015\u0012\u00051\u0001\u0002 \u0006A1m\\7cS:,g\n\u0006\u0004\u0002 \u0006e\u0016Q\u0018\u0005\b\u0003w\u0013\u0003\u0019AAP\u0003\u0005A\bbBA`E\u0001\u0007\u0011\u0011D\u0001\u0002]\u0006Q1m\\7cS:,\u0017\t\u001c7\u0015\t\u0005}\u0015Q\u0019\u0005\b\u0003C\u0019\u0003\u0019AAd!\u0019\tI-!7\u0002 :!\u00111ZAk\u001d\u0011\ti-a5\u000e\u0005\u0005='bAAiW\u00051AH]8pizJ\u0011\u0001M\u0005\u0004\u0003/|\u0013a\u00029bG.\fw-Z\u0005\u0005\u00037\fiN\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cWMC\u0002\u0002X>\nQdY1ug.+'O\\3m'R$wJ\u001d3fe\u001a{'/\u0011:sCf\u001cV-]\u000b\u0005\u0003G\fY\u000f\u0006\u0003\u0002f\u00065\b\u0003\u0002&\u0000\u0003O\u0004BAT*\u0002jB\u0019a+a;\u0005\u000ba\u0013!\u0019A-\t\u0013\u0005=(!!AA\u0004\u0005E\u0018AC3wS\u0012,gnY3%cA!!j`Au\u0003y\u0019\u0017\r^:LKJtW\r\\*uI6{gn\\5e\r>\u0014\u0018I\u001d:bsN+\u0017/\u0006\u0003\u0002x\u0006}XCAA}!\u0015Q\u00151TA~!\u0011q5+!@\u0011\u0007Y\u000by\u0010B\u0003Y\u0007\t\u0007\u0011\fK\u0002\u0001\u0005\u0007\u0001BA!\u0002\u0003\u00149!!q\u0001B\u0007\u001d\rQ%\u0011B\u0005\u0004\u0005\u00179\u0013AB2p[B\fG/\u0003\u0003\u0003\u0010\tE\u0011\u0001F:dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7MC\u0002\u0003\f\u001dJAA!\u0006\u0003\u0018\t\u00114/\u001e9qe\u0016\u001c8/\u00168vg\u0016$\u0017*\u001c9peR<\u0016M\u001d8j]\u001e4uN]*dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7M\u0003\u0003\u0003\u0010\tE\u0001"
)
public interface ArraySeqInstances extends ArraySeqInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForArraySeq$(final ArraySeqInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForArraySeq(evidence$1);
   }

   default Order catsKernelStdOrderForArraySeq(final Order evidence$1) {
      return new ArraySeqOrder(evidence$1);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForArraySeq$(final ArraySeqInstances $this) {
      return $this.catsKernelStdMonoidForArraySeq();
   }

   default Monoid catsKernelStdMonoidForArraySeq() {
      return new ArraySeqMonoid();
   }

   static void $init$(final ArraySeqInstances $this) {
   }

   public interface ArraySeqInstances1 extends ArraySeqInstances2 {
      // $FF: synthetic method
      static PartialOrder catsKernelStdPartialOrderForArraySeq$(final ArraySeqInstances1 $this, final PartialOrder evidence$2) {
         return $this.catsKernelStdPartialOrderForArraySeq(evidence$2);
      }

      default PartialOrder catsKernelStdPartialOrderForArraySeq(final PartialOrder evidence$2) {
         return new ArraySeqPartialOrder(evidence$2);
      }

      // $FF: synthetic method
      static Hash catsKernelStdHashForArraySeq$(final ArraySeqInstances1 $this, final Hash evidence$3) {
         return $this.catsKernelStdHashForArraySeq(evidence$3);
      }

      default Hash catsKernelStdHashForArraySeq(final Hash evidence$3) {
         return new ArraySeqHash(evidence$3);
      }

      static void $init$(final ArraySeqInstances1 $this) {
      }
   }

   public interface ArraySeqInstances2 {
      // $FF: synthetic method
      static Eq catsKernelStdEqForArraySeq$(final ArraySeqInstances2 $this, final Eq evidence$4) {
         return $this.catsKernelStdEqForArraySeq(evidence$4);
      }

      default Eq catsKernelStdEqForArraySeq(final Eq evidence$4) {
         return new ArraySeqEq(evidence$4);
      }

      static void $init$(final ArraySeqInstances2 $this) {
      }
   }

   private static final class ArraySeqOrder implements Order {
      private final Order ev;

      public int compare$mcZ$sp(final boolean x, final boolean y) {
         return Order.compare$mcZ$sp$(this, x, y);
      }

      public int compare$mcB$sp(final byte x, final byte y) {
         return Order.compare$mcB$sp$(this, x, y);
      }

      public int compare$mcC$sp(final char x, final char y) {
         return Order.compare$mcC$sp$(this, x, y);
      }

      public int compare$mcD$sp(final double x, final double y) {
         return Order.compare$mcD$sp$(this, x, y);
      }

      public int compare$mcF$sp(final float x, final float y) {
         return Order.compare$mcF$sp$(this, x, y);
      }

      public int compare$mcI$sp(final int x, final int y) {
         return Order.compare$mcI$sp$(this, x, y);
      }

      public int compare$mcJ$sp(final long x, final long y) {
         return Order.compare$mcJ$sp$(this, x, y);
      }

      public int compare$mcS$sp(final short x, final short y) {
         return Order.compare$mcS$sp$(this, x, y);
      }

      public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.compare$mcV$sp$(this, x, y);
      }

      public Comparison comparison(final Object x, final Object y) {
         return Order.comparison$(this, x, y);
      }

      public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
         return Order.comparison$mcZ$sp$(this, x, y);
      }

      public Comparison comparison$mcB$sp(final byte x, final byte y) {
         return Order.comparison$mcB$sp$(this, x, y);
      }

      public Comparison comparison$mcC$sp(final char x, final char y) {
         return Order.comparison$mcC$sp$(this, x, y);
      }

      public Comparison comparison$mcD$sp(final double x, final double y) {
         return Order.comparison$mcD$sp$(this, x, y);
      }

      public Comparison comparison$mcF$sp(final float x, final float y) {
         return Order.comparison$mcF$sp$(this, x, y);
      }

      public Comparison comparison$mcI$sp(final int x, final int y) {
         return Order.comparison$mcI$sp$(this, x, y);
      }

      public Comparison comparison$mcJ$sp(final long x, final long y) {
         return Order.comparison$mcJ$sp$(this, x, y);
      }

      public Comparison comparison$mcS$sp(final short x, final short y) {
         return Order.comparison$mcS$sp$(this, x, y);
      }

      public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.comparison$mcV$sp$(this, x, y);
      }

      public double partialCompare(final Object x, final Object y) {
         return Order.partialCompare$(this, x, y);
      }

      public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
         return Order.partialCompare$mcZ$sp$(this, x, y);
      }

      public double partialCompare$mcB$sp(final byte x, final byte y) {
         return Order.partialCompare$mcB$sp$(this, x, y);
      }

      public double partialCompare$mcC$sp(final char x, final char y) {
         return Order.partialCompare$mcC$sp$(this, x, y);
      }

      public double partialCompare$mcD$sp(final double x, final double y) {
         return Order.partialCompare$mcD$sp$(this, x, y);
      }

      public double partialCompare$mcF$sp(final float x, final float y) {
         return Order.partialCompare$mcF$sp$(this, x, y);
      }

      public double partialCompare$mcI$sp(final int x, final int y) {
         return Order.partialCompare$mcI$sp$(this, x, y);
      }

      public double partialCompare$mcJ$sp(final long x, final long y) {
         return Order.partialCompare$mcJ$sp$(this, x, y);
      }

      public double partialCompare$mcS$sp(final short x, final short y) {
         return Order.partialCompare$mcS$sp$(this, x, y);
      }

      public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.partialCompare$mcV$sp$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Order.min$(this, x, y);
      }

      public boolean min$mcZ$sp(final boolean x, final boolean y) {
         return Order.min$mcZ$sp$(this, x, y);
      }

      public byte min$mcB$sp(final byte x, final byte y) {
         return Order.min$mcB$sp$(this, x, y);
      }

      public char min$mcC$sp(final char x, final char y) {
         return Order.min$mcC$sp$(this, x, y);
      }

      public double min$mcD$sp(final double x, final double y) {
         return Order.min$mcD$sp$(this, x, y);
      }

      public float min$mcF$sp(final float x, final float y) {
         return Order.min$mcF$sp$(this, x, y);
      }

      public int min$mcI$sp(final int x, final int y) {
         return Order.min$mcI$sp$(this, x, y);
      }

      public long min$mcJ$sp(final long x, final long y) {
         return Order.min$mcJ$sp$(this, x, y);
      }

      public short min$mcS$sp(final short x, final short y) {
         return Order.min$mcS$sp$(this, x, y);
      }

      public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         Order.min$mcV$sp$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Order.max$(this, x, y);
      }

      public boolean max$mcZ$sp(final boolean x, final boolean y) {
         return Order.max$mcZ$sp$(this, x, y);
      }

      public byte max$mcB$sp(final byte x, final byte y) {
         return Order.max$mcB$sp$(this, x, y);
      }

      public char max$mcC$sp(final char x, final char y) {
         return Order.max$mcC$sp$(this, x, y);
      }

      public double max$mcD$sp(final double x, final double y) {
         return Order.max$mcD$sp$(this, x, y);
      }

      public float max$mcF$sp(final float x, final float y) {
         return Order.max$mcF$sp$(this, x, y);
      }

      public int max$mcI$sp(final int x, final int y) {
         return Order.max$mcI$sp$(this, x, y);
      }

      public long max$mcJ$sp(final long x, final long y) {
         return Order.max$mcJ$sp$(this, x, y);
      }

      public short max$mcS$sp(final short x, final short y) {
         return Order.max$mcS$sp$(this, x, y);
      }

      public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         Order.max$mcV$sp$(this, x, y);
      }

      public boolean eqv(final Object x, final Object y) {
         return Order.eqv$(this, x, y);
      }

      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return Order.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return Order.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return Order.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return Order.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return Order.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return Order.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return Order.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.eqv$mcV$sp$(this, x, y);
      }

      public boolean neqv(final Object x, final Object y) {
         return Order.neqv$(this, x, y);
      }

      public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.neqv$mcZ$sp$(this, x, y);
      }

      public boolean neqv$mcB$sp(final byte x, final byte y) {
         return Order.neqv$mcB$sp$(this, x, y);
      }

      public boolean neqv$mcC$sp(final char x, final char y) {
         return Order.neqv$mcC$sp$(this, x, y);
      }

      public boolean neqv$mcD$sp(final double x, final double y) {
         return Order.neqv$mcD$sp$(this, x, y);
      }

      public boolean neqv$mcF$sp(final float x, final float y) {
         return Order.neqv$mcF$sp$(this, x, y);
      }

      public boolean neqv$mcI$sp(final int x, final int y) {
         return Order.neqv$mcI$sp$(this, x, y);
      }

      public boolean neqv$mcJ$sp(final long x, final long y) {
         return Order.neqv$mcJ$sp$(this, x, y);
      }

      public boolean neqv$mcS$sp(final short x, final short y) {
         return Order.neqv$mcS$sp$(this, x, y);
      }

      public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.neqv$mcV$sp$(this, x, y);
      }

      public boolean lteqv(final Object x, final Object y) {
         return Order.lteqv$(this, x, y);
      }

      public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.lteqv$mcZ$sp$(this, x, y);
      }

      public boolean lteqv$mcB$sp(final byte x, final byte y) {
         return Order.lteqv$mcB$sp$(this, x, y);
      }

      public boolean lteqv$mcC$sp(final char x, final char y) {
         return Order.lteqv$mcC$sp$(this, x, y);
      }

      public boolean lteqv$mcD$sp(final double x, final double y) {
         return Order.lteqv$mcD$sp$(this, x, y);
      }

      public boolean lteqv$mcF$sp(final float x, final float y) {
         return Order.lteqv$mcF$sp$(this, x, y);
      }

      public boolean lteqv$mcI$sp(final int x, final int y) {
         return Order.lteqv$mcI$sp$(this, x, y);
      }

      public boolean lteqv$mcJ$sp(final long x, final long y) {
         return Order.lteqv$mcJ$sp$(this, x, y);
      }

      public boolean lteqv$mcS$sp(final short x, final short y) {
         return Order.lteqv$mcS$sp$(this, x, y);
      }

      public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.lteqv$mcV$sp$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return Order.lt$(this, x, y);
      }

      public boolean lt$mcZ$sp(final boolean x, final boolean y) {
         return Order.lt$mcZ$sp$(this, x, y);
      }

      public boolean lt$mcB$sp(final byte x, final byte y) {
         return Order.lt$mcB$sp$(this, x, y);
      }

      public boolean lt$mcC$sp(final char x, final char y) {
         return Order.lt$mcC$sp$(this, x, y);
      }

      public boolean lt$mcD$sp(final double x, final double y) {
         return Order.lt$mcD$sp$(this, x, y);
      }

      public boolean lt$mcF$sp(final float x, final float y) {
         return Order.lt$mcF$sp$(this, x, y);
      }

      public boolean lt$mcI$sp(final int x, final int y) {
         return Order.lt$mcI$sp$(this, x, y);
      }

      public boolean lt$mcJ$sp(final long x, final long y) {
         return Order.lt$mcJ$sp$(this, x, y);
      }

      public boolean lt$mcS$sp(final short x, final short y) {
         return Order.lt$mcS$sp$(this, x, y);
      }

      public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.lt$mcV$sp$(this, x, y);
      }

      public boolean gteqv(final Object x, final Object y) {
         return Order.gteqv$(this, x, y);
      }

      public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.gteqv$mcZ$sp$(this, x, y);
      }

      public boolean gteqv$mcB$sp(final byte x, final byte y) {
         return Order.gteqv$mcB$sp$(this, x, y);
      }

      public boolean gteqv$mcC$sp(final char x, final char y) {
         return Order.gteqv$mcC$sp$(this, x, y);
      }

      public boolean gteqv$mcD$sp(final double x, final double y) {
         return Order.gteqv$mcD$sp$(this, x, y);
      }

      public boolean gteqv$mcF$sp(final float x, final float y) {
         return Order.gteqv$mcF$sp$(this, x, y);
      }

      public boolean gteqv$mcI$sp(final int x, final int y) {
         return Order.gteqv$mcI$sp$(this, x, y);
      }

      public boolean gteqv$mcJ$sp(final long x, final long y) {
         return Order.gteqv$mcJ$sp$(this, x, y);
      }

      public boolean gteqv$mcS$sp(final short x, final short y) {
         return Order.gteqv$mcS$sp$(this, x, y);
      }

      public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.gteqv$mcV$sp$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return Order.gt$(this, x, y);
      }

      public boolean gt$mcZ$sp(final boolean x, final boolean y) {
         return Order.gt$mcZ$sp$(this, x, y);
      }

      public boolean gt$mcB$sp(final byte x, final byte y) {
         return Order.gt$mcB$sp$(this, x, y);
      }

      public boolean gt$mcC$sp(final char x, final char y) {
         return Order.gt$mcC$sp$(this, x, y);
      }

      public boolean gt$mcD$sp(final double x, final double y) {
         return Order.gt$mcD$sp$(this, x, y);
      }

      public boolean gt$mcF$sp(final float x, final float y) {
         return Order.gt$mcF$sp$(this, x, y);
      }

      public boolean gt$mcI$sp(final int x, final int y) {
         return Order.gt$mcI$sp$(this, x, y);
      }

      public boolean gt$mcJ$sp(final long x, final long y) {
         return Order.gt$mcJ$sp$(this, x, y);
      }

      public boolean gt$mcS$sp(final short x, final short y) {
         return Order.gt$mcS$sp$(this, x, y);
      }

      public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.gt$mcV$sp$(this, x, y);
      }

      public Ordering toOrdering() {
         return Order.toOrdering$(this);
      }

      public Option partialComparison(final Object x, final Object y) {
         return PartialOrder.partialComparison$(this, x, y);
      }

      public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
      }

      public Option partialComparison$mcB$sp(final byte x, final byte y) {
         return PartialOrder.partialComparison$mcB$sp$(this, x, y);
      }

      public Option partialComparison$mcC$sp(final char x, final char y) {
         return PartialOrder.partialComparison$mcC$sp$(this, x, y);
      }

      public Option partialComparison$mcD$sp(final double x, final double y) {
         return PartialOrder.partialComparison$mcD$sp$(this, x, y);
      }

      public Option partialComparison$mcF$sp(final float x, final float y) {
         return PartialOrder.partialComparison$mcF$sp$(this, x, y);
      }

      public Option partialComparison$mcI$sp(final int x, final int y) {
         return PartialOrder.partialComparison$mcI$sp$(this, x, y);
      }

      public Option partialComparison$mcJ$sp(final long x, final long y) {
         return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
      }

      public Option partialComparison$mcS$sp(final short x, final short y) {
         return PartialOrder.partialComparison$mcS$sp$(this, x, y);
      }

      public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.partialComparison$mcV$sp$(this, x, y);
      }

      public Option tryCompare(final Object x, final Object y) {
         return PartialOrder.tryCompare$(this, x, y);
      }

      public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
      }

      public Option tryCompare$mcB$sp(final byte x, final byte y) {
         return PartialOrder.tryCompare$mcB$sp$(this, x, y);
      }

      public Option tryCompare$mcC$sp(final char x, final char y) {
         return PartialOrder.tryCompare$mcC$sp$(this, x, y);
      }

      public Option tryCompare$mcD$sp(final double x, final double y) {
         return PartialOrder.tryCompare$mcD$sp$(this, x, y);
      }

      public Option tryCompare$mcF$sp(final float x, final float y) {
         return PartialOrder.tryCompare$mcF$sp$(this, x, y);
      }

      public Option tryCompare$mcI$sp(final int x, final int y) {
         return PartialOrder.tryCompare$mcI$sp$(this, x, y);
      }

      public Option tryCompare$mcJ$sp(final long x, final long y) {
         return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
      }

      public Option tryCompare$mcS$sp(final short x, final short y) {
         return PartialOrder.tryCompare$mcS$sp$(this, x, y);
      }

      public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.tryCompare$mcV$sp$(this, x, y);
      }

      public Option pmin(final Object x, final Object y) {
         return PartialOrder.pmin$(this, x, y);
      }

      public Option pmin$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmin$mcZ$sp$(this, x, y);
      }

      public Option pmin$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmin$mcB$sp$(this, x, y);
      }

      public Option pmin$mcC$sp(final char x, final char y) {
         return PartialOrder.pmin$mcC$sp$(this, x, y);
      }

      public Option pmin$mcD$sp(final double x, final double y) {
         return PartialOrder.pmin$mcD$sp$(this, x, y);
      }

      public Option pmin$mcF$sp(final float x, final float y) {
         return PartialOrder.pmin$mcF$sp$(this, x, y);
      }

      public Option pmin$mcI$sp(final int x, final int y) {
         return PartialOrder.pmin$mcI$sp$(this, x, y);
      }

      public Option pmin$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmin$mcJ$sp$(this, x, y);
      }

      public Option pmin$mcS$sp(final short x, final short y) {
         return PartialOrder.pmin$mcS$sp$(this, x, y);
      }

      public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmin$mcV$sp$(this, x, y);
      }

      public Option pmax(final Object x, final Object y) {
         return PartialOrder.pmax$(this, x, y);
      }

      public Option pmax$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmax$mcZ$sp$(this, x, y);
      }

      public Option pmax$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmax$mcB$sp$(this, x, y);
      }

      public Option pmax$mcC$sp(final char x, final char y) {
         return PartialOrder.pmax$mcC$sp$(this, x, y);
      }

      public Option pmax$mcD$sp(final double x, final double y) {
         return PartialOrder.pmax$mcD$sp$(this, x, y);
      }

      public Option pmax$mcF$sp(final float x, final float y) {
         return PartialOrder.pmax$mcF$sp$(this, x, y);
      }

      public Option pmax$mcI$sp(final int x, final int y) {
         return PartialOrder.pmax$mcI$sp$(this, x, y);
      }

      public Option pmax$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmax$mcJ$sp$(this, x, y);
      }

      public Option pmax$mcS$sp(final short x, final short y) {
         return PartialOrder.pmax$mcS$sp$(this, x, y);
      }

      public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmax$mcV$sp$(this, x, y);
      }

      public final int compare(final ArraySeq xs, final ArraySeq ys) {
         return xs == ys ? 0 : this.loop$1(0, xs, ys);
      }

      private final int loop$1(final int i, final ArraySeq xs$1, final ArraySeq ys$1) {
         while(true) {
            Tuple2.mcZZ.sp var6 = new Tuple2.mcZZ.sp(i < xs$1.length(), i < ys$1.length());
            int var5;
            if (var6 != null) {
               boolean var7 = ((Tuple2)var6)._1$mcZ$sp();
               boolean var8 = ((Tuple2)var6)._2$mcZ$sp();
               if (var7 && var8) {
                  int n = this.ev.compare(xs$1.apply(i), ys$1.apply(i));
                  if (n == 0) {
                     ++i;
                     continue;
                  }

                  var5 = n;
                  return var5;
               }
            }

            if (var6 != null) {
               boolean var10 = ((Tuple2)var6)._1$mcZ$sp();
               boolean var11 = ((Tuple2)var6)._2$mcZ$sp();
               if (var10 && !var11) {
                  var5 = 1;
                  return var5;
               }
            }

            if (var6 != null) {
               boolean var12 = ((Tuple2)var6)._1$mcZ$sp();
               boolean var13 = ((Tuple2)var6)._2$mcZ$sp();
               if (!var12 && var13) {
                  var5 = -1;
                  return var5;
               }
            }

            if (var6 == null) {
               throw new MatchError(var6);
            }

            boolean var14 = ((Tuple2)var6)._1$mcZ$sp();
            boolean var15 = ((Tuple2)var6)._2$mcZ$sp();
            if (var14 || var15) {
               throw new MatchError(var6);
            }

            var5 = 0;
            return var5;
         }
      }

      public ArraySeqOrder(final Order ev) {
         this.ev = ev;
         Eq.$init$(this);
         PartialOrder.$init$(this);
         Order.$init$(this);
      }
   }

   private static class ArraySeqPartialOrder implements PartialOrder {
      private final PartialOrder ev;

      public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.partialCompare$mcZ$sp$(this, x, y);
      }

      public double partialCompare$mcB$sp(final byte x, final byte y) {
         return PartialOrder.partialCompare$mcB$sp$(this, x, y);
      }

      public double partialCompare$mcC$sp(final char x, final char y) {
         return PartialOrder.partialCompare$mcC$sp$(this, x, y);
      }

      public double partialCompare$mcD$sp(final double x, final double y) {
         return PartialOrder.partialCompare$mcD$sp$(this, x, y);
      }

      public double partialCompare$mcF$sp(final float x, final float y) {
         return PartialOrder.partialCompare$mcF$sp$(this, x, y);
      }

      public double partialCompare$mcI$sp(final int x, final int y) {
         return PartialOrder.partialCompare$mcI$sp$(this, x, y);
      }

      public double partialCompare$mcJ$sp(final long x, final long y) {
         return PartialOrder.partialCompare$mcJ$sp$(this, x, y);
      }

      public double partialCompare$mcS$sp(final short x, final short y) {
         return PartialOrder.partialCompare$mcS$sp$(this, x, y);
      }

      public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.partialCompare$mcV$sp$(this, x, y);
      }

      public Option partialComparison(final Object x, final Object y) {
         return PartialOrder.partialComparison$(this, x, y);
      }

      public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
      }

      public Option partialComparison$mcB$sp(final byte x, final byte y) {
         return PartialOrder.partialComparison$mcB$sp$(this, x, y);
      }

      public Option partialComparison$mcC$sp(final char x, final char y) {
         return PartialOrder.partialComparison$mcC$sp$(this, x, y);
      }

      public Option partialComparison$mcD$sp(final double x, final double y) {
         return PartialOrder.partialComparison$mcD$sp$(this, x, y);
      }

      public Option partialComparison$mcF$sp(final float x, final float y) {
         return PartialOrder.partialComparison$mcF$sp$(this, x, y);
      }

      public Option partialComparison$mcI$sp(final int x, final int y) {
         return PartialOrder.partialComparison$mcI$sp$(this, x, y);
      }

      public Option partialComparison$mcJ$sp(final long x, final long y) {
         return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
      }

      public Option partialComparison$mcS$sp(final short x, final short y) {
         return PartialOrder.partialComparison$mcS$sp$(this, x, y);
      }

      public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.partialComparison$mcV$sp$(this, x, y);
      }

      public Option tryCompare(final Object x, final Object y) {
         return PartialOrder.tryCompare$(this, x, y);
      }

      public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
      }

      public Option tryCompare$mcB$sp(final byte x, final byte y) {
         return PartialOrder.tryCompare$mcB$sp$(this, x, y);
      }

      public Option tryCompare$mcC$sp(final char x, final char y) {
         return PartialOrder.tryCompare$mcC$sp$(this, x, y);
      }

      public Option tryCompare$mcD$sp(final double x, final double y) {
         return PartialOrder.tryCompare$mcD$sp$(this, x, y);
      }

      public Option tryCompare$mcF$sp(final float x, final float y) {
         return PartialOrder.tryCompare$mcF$sp$(this, x, y);
      }

      public Option tryCompare$mcI$sp(final int x, final int y) {
         return PartialOrder.tryCompare$mcI$sp$(this, x, y);
      }

      public Option tryCompare$mcJ$sp(final long x, final long y) {
         return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
      }

      public Option tryCompare$mcS$sp(final short x, final short y) {
         return PartialOrder.tryCompare$mcS$sp$(this, x, y);
      }

      public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.tryCompare$mcV$sp$(this, x, y);
      }

      public Option pmin(final Object x, final Object y) {
         return PartialOrder.pmin$(this, x, y);
      }

      public Option pmin$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmin$mcZ$sp$(this, x, y);
      }

      public Option pmin$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmin$mcB$sp$(this, x, y);
      }

      public Option pmin$mcC$sp(final char x, final char y) {
         return PartialOrder.pmin$mcC$sp$(this, x, y);
      }

      public Option pmin$mcD$sp(final double x, final double y) {
         return PartialOrder.pmin$mcD$sp$(this, x, y);
      }

      public Option pmin$mcF$sp(final float x, final float y) {
         return PartialOrder.pmin$mcF$sp$(this, x, y);
      }

      public Option pmin$mcI$sp(final int x, final int y) {
         return PartialOrder.pmin$mcI$sp$(this, x, y);
      }

      public Option pmin$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmin$mcJ$sp$(this, x, y);
      }

      public Option pmin$mcS$sp(final short x, final short y) {
         return PartialOrder.pmin$mcS$sp$(this, x, y);
      }

      public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmin$mcV$sp$(this, x, y);
      }

      public Option pmax(final Object x, final Object y) {
         return PartialOrder.pmax$(this, x, y);
      }

      public Option pmax$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmax$mcZ$sp$(this, x, y);
      }

      public Option pmax$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmax$mcB$sp$(this, x, y);
      }

      public Option pmax$mcC$sp(final char x, final char y) {
         return PartialOrder.pmax$mcC$sp$(this, x, y);
      }

      public Option pmax$mcD$sp(final double x, final double y) {
         return PartialOrder.pmax$mcD$sp$(this, x, y);
      }

      public Option pmax$mcF$sp(final float x, final float y) {
         return PartialOrder.pmax$mcF$sp$(this, x, y);
      }

      public Option pmax$mcI$sp(final int x, final int y) {
         return PartialOrder.pmax$mcI$sp$(this, x, y);
      }

      public Option pmax$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmax$mcJ$sp$(this, x, y);
      }

      public Option pmax$mcS$sp(final short x, final short y) {
         return PartialOrder.pmax$mcS$sp$(this, x, y);
      }

      public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmax$mcV$sp$(this, x, y);
      }

      public boolean eqv(final Object x, final Object y) {
         return PartialOrder.eqv$(this, x, y);
      }

      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return PartialOrder.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return PartialOrder.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return PartialOrder.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return PartialOrder.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return PartialOrder.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return PartialOrder.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return PartialOrder.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.eqv$mcV$sp$(this, x, y);
      }

      public boolean lteqv(final Object x, final Object y) {
         return PartialOrder.lteqv$(this, x, y);
      }

      public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.lteqv$mcZ$sp$(this, x, y);
      }

      public boolean lteqv$mcB$sp(final byte x, final byte y) {
         return PartialOrder.lteqv$mcB$sp$(this, x, y);
      }

      public boolean lteqv$mcC$sp(final char x, final char y) {
         return PartialOrder.lteqv$mcC$sp$(this, x, y);
      }

      public boolean lteqv$mcD$sp(final double x, final double y) {
         return PartialOrder.lteqv$mcD$sp$(this, x, y);
      }

      public boolean lteqv$mcF$sp(final float x, final float y) {
         return PartialOrder.lteqv$mcF$sp$(this, x, y);
      }

      public boolean lteqv$mcI$sp(final int x, final int y) {
         return PartialOrder.lteqv$mcI$sp$(this, x, y);
      }

      public boolean lteqv$mcJ$sp(final long x, final long y) {
         return PartialOrder.lteqv$mcJ$sp$(this, x, y);
      }

      public boolean lteqv$mcS$sp(final short x, final short y) {
         return PartialOrder.lteqv$mcS$sp$(this, x, y);
      }

      public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.lteqv$mcV$sp$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return PartialOrder.lt$(this, x, y);
      }

      public boolean lt$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.lt$mcZ$sp$(this, x, y);
      }

      public boolean lt$mcB$sp(final byte x, final byte y) {
         return PartialOrder.lt$mcB$sp$(this, x, y);
      }

      public boolean lt$mcC$sp(final char x, final char y) {
         return PartialOrder.lt$mcC$sp$(this, x, y);
      }

      public boolean lt$mcD$sp(final double x, final double y) {
         return PartialOrder.lt$mcD$sp$(this, x, y);
      }

      public boolean lt$mcF$sp(final float x, final float y) {
         return PartialOrder.lt$mcF$sp$(this, x, y);
      }

      public boolean lt$mcI$sp(final int x, final int y) {
         return PartialOrder.lt$mcI$sp$(this, x, y);
      }

      public boolean lt$mcJ$sp(final long x, final long y) {
         return PartialOrder.lt$mcJ$sp$(this, x, y);
      }

      public boolean lt$mcS$sp(final short x, final short y) {
         return PartialOrder.lt$mcS$sp$(this, x, y);
      }

      public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.lt$mcV$sp$(this, x, y);
      }

      public boolean gteqv(final Object x, final Object y) {
         return PartialOrder.gteqv$(this, x, y);
      }

      public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.gteqv$mcZ$sp$(this, x, y);
      }

      public boolean gteqv$mcB$sp(final byte x, final byte y) {
         return PartialOrder.gteqv$mcB$sp$(this, x, y);
      }

      public boolean gteqv$mcC$sp(final char x, final char y) {
         return PartialOrder.gteqv$mcC$sp$(this, x, y);
      }

      public boolean gteqv$mcD$sp(final double x, final double y) {
         return PartialOrder.gteqv$mcD$sp$(this, x, y);
      }

      public boolean gteqv$mcF$sp(final float x, final float y) {
         return PartialOrder.gteqv$mcF$sp$(this, x, y);
      }

      public boolean gteqv$mcI$sp(final int x, final int y) {
         return PartialOrder.gteqv$mcI$sp$(this, x, y);
      }

      public boolean gteqv$mcJ$sp(final long x, final long y) {
         return PartialOrder.gteqv$mcJ$sp$(this, x, y);
      }

      public boolean gteqv$mcS$sp(final short x, final short y) {
         return PartialOrder.gteqv$mcS$sp$(this, x, y);
      }

      public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.gteqv$mcV$sp$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return PartialOrder.gt$(this, x, y);
      }

      public boolean gt$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.gt$mcZ$sp$(this, x, y);
      }

      public boolean gt$mcB$sp(final byte x, final byte y) {
         return PartialOrder.gt$mcB$sp$(this, x, y);
      }

      public boolean gt$mcC$sp(final char x, final char y) {
         return PartialOrder.gt$mcC$sp$(this, x, y);
      }

      public boolean gt$mcD$sp(final double x, final double y) {
         return PartialOrder.gt$mcD$sp$(this, x, y);
      }

      public boolean gt$mcF$sp(final float x, final float y) {
         return PartialOrder.gt$mcF$sp$(this, x, y);
      }

      public boolean gt$mcI$sp(final int x, final int y) {
         return PartialOrder.gt$mcI$sp$(this, x, y);
      }

      public boolean gt$mcJ$sp(final long x, final long y) {
         return PartialOrder.gt$mcJ$sp$(this, x, y);
      }

      public boolean gt$mcS$sp(final short x, final short y) {
         return PartialOrder.gt$mcS$sp$(this, x, y);
      }

      public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.gt$mcV$sp$(this, x, y);
      }

      public boolean neqv(final Object x, final Object y) {
         return Eq.neqv$(this, x, y);
      }

      public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
         return Eq.neqv$mcZ$sp$(this, x, y);
      }

      public boolean neqv$mcB$sp(final byte x, final byte y) {
         return Eq.neqv$mcB$sp$(this, x, y);
      }

      public boolean neqv$mcC$sp(final char x, final char y) {
         return Eq.neqv$mcC$sp$(this, x, y);
      }

      public boolean neqv$mcD$sp(final double x, final double y) {
         return Eq.neqv$mcD$sp$(this, x, y);
      }

      public boolean neqv$mcF$sp(final float x, final float y) {
         return Eq.neqv$mcF$sp$(this, x, y);
      }

      public boolean neqv$mcI$sp(final int x, final int y) {
         return Eq.neqv$mcI$sp$(this, x, y);
      }

      public boolean neqv$mcJ$sp(final long x, final long y) {
         return Eq.neqv$mcJ$sp$(this, x, y);
      }

      public boolean neqv$mcS$sp(final short x, final short y) {
         return Eq.neqv$mcS$sp$(this, x, y);
      }

      public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Eq.neqv$mcV$sp$(this, x, y);
      }

      public final double partialCompare(final ArraySeq xs, final ArraySeq ys) {
         return xs == ys ? (double)0.0F : this.loop$2(0, xs, ys);
      }

      private final double loop$2(final int i, final ArraySeq xs$2, final ArraySeq ys$2) {
         while(true) {
            Tuple2.mcZZ.sp var7 = new Tuple2.mcZZ.sp(i < xs$2.length(), i < ys$2.length());
            double var5;
            if (var7 != null) {
               boolean var8 = ((Tuple2)var7)._1$mcZ$sp();
               boolean var9 = ((Tuple2)var7)._2$mcZ$sp();
               if (var8 && var9) {
                  double n = this.ev.partialCompare(xs$2.apply(i), ys$2.apply(i));
                  if (n == (double)0) {
                     ++i;
                     continue;
                  }

                  var5 = n;
                  return var5;
               }
            }

            if (var7 != null) {
               boolean var12 = ((Tuple2)var7)._1$mcZ$sp();
               boolean var13 = ((Tuple2)var7)._2$mcZ$sp();
               if (var12 && !var13) {
                  var5 = (double)1.0F;
                  return var5;
               }
            }

            if (var7 != null) {
               boolean var14 = ((Tuple2)var7)._1$mcZ$sp();
               boolean var15 = ((Tuple2)var7)._2$mcZ$sp();
               if (!var14 && var15) {
                  var5 = (double)-1.0F;
                  return var5;
               }
            }

            if (var7 == null) {
               throw new MatchError(var7);
            }

            boolean var16 = ((Tuple2)var7)._1$mcZ$sp();
            boolean var17 = ((Tuple2)var7)._2$mcZ$sp();
            if (var16 || var17) {
               throw new MatchError(var7);
            }

            var5 = (double)0.0F;
            return var5;
         }
      }

      public ArraySeqPartialOrder(final PartialOrder ev) {
         this.ev = ev;
         Eq.$init$(this);
         PartialOrder.$init$(this);
      }
   }

   private static class ArraySeqHash extends ArraySeqEq implements Hash {
      private final Hash ev;

      public int hash$mcZ$sp(final boolean x) {
         return Hash.hash$mcZ$sp$(this, x);
      }

      public int hash$mcB$sp(final byte x) {
         return Hash.hash$mcB$sp$(this, x);
      }

      public int hash$mcC$sp(final char x) {
         return Hash.hash$mcC$sp$(this, x);
      }

      public int hash$mcD$sp(final double x) {
         return Hash.hash$mcD$sp$(this, x);
      }

      public int hash$mcF$sp(final float x) {
         return Hash.hash$mcF$sp$(this, x);
      }

      public int hash$mcI$sp(final int x) {
         return Hash.hash$mcI$sp$(this, x);
      }

      public int hash$mcJ$sp(final long x) {
         return Hash.hash$mcJ$sp$(this, x);
      }

      public int hash$mcS$sp(final short x) {
         return Hash.hash$mcS$sp$(this, x);
      }

      public int hash$mcV$sp(final BoxedUnit x) {
         return Hash.hash$mcV$sp$(this, x);
      }

      public final int hash(final ArraySeq xs) {
         return StaticMethods$.MODULE$.orderedHash(xs, this.ev);
      }

      public ArraySeqHash(final Hash ev) {
         super(ev);
         this.ev = ev;
      }
   }

   private static class ArraySeqEq implements Eq {
      private final Eq ev;

      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return Eq.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return Eq.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return Eq.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return Eq.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return Eq.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return Eq.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return Eq.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return Eq.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Eq.eqv$mcV$sp$(this, x, y);
      }

      public boolean neqv(final Object x, final Object y) {
         return Eq.neqv$(this, x, y);
      }

      public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
         return Eq.neqv$mcZ$sp$(this, x, y);
      }

      public boolean neqv$mcB$sp(final byte x, final byte y) {
         return Eq.neqv$mcB$sp$(this, x, y);
      }

      public boolean neqv$mcC$sp(final char x, final char y) {
         return Eq.neqv$mcC$sp$(this, x, y);
      }

      public boolean neqv$mcD$sp(final double x, final double y) {
         return Eq.neqv$mcD$sp$(this, x, y);
      }

      public boolean neqv$mcF$sp(final float x, final float y) {
         return Eq.neqv$mcF$sp$(this, x, y);
      }

      public boolean neqv$mcI$sp(final int x, final int y) {
         return Eq.neqv$mcI$sp$(this, x, y);
      }

      public boolean neqv$mcJ$sp(final long x, final long y) {
         return Eq.neqv$mcJ$sp$(this, x, y);
      }

      public boolean neqv$mcS$sp(final short x, final short y) {
         return Eq.neqv$mcS$sp$(this, x, y);
      }

      public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Eq.neqv$mcV$sp$(this, x, y);
      }

      public final boolean eqv(final ArraySeq xs, final ArraySeq ys) {
         return xs == ys || this.loop$3(0, xs, ys);
      }

      private final boolean loop$3(final int i, final ArraySeq xs$3, final ArraySeq ys$3) {
         while(true) {
            Tuple2.mcZZ.sp var6 = new Tuple2.mcZZ.sp(i < xs$3.length(), i < ys$3.length());
            boolean var5;
            if (var6 != null) {
               boolean var7 = ((Tuple2)var6)._1$mcZ$sp();
               boolean var8 = ((Tuple2)var6)._2$mcZ$sp();
               if (var7 && var8) {
                  if (this.ev.eqv(xs$3.apply(i), ys$3.apply(i))) {
                     ++i;
                     continue;
                  }

                  var5 = false;
                  return var5;
               }
            }

            if (var6 != null) {
               boolean var9 = ((Tuple2)var6)._1$mcZ$sp();
               boolean var10 = ((Tuple2)var6)._2$mcZ$sp();
               if (var9 && !var10) {
                  var5 = false;
                  return var5;
               }
            }

            if (var6 != null) {
               boolean var11 = ((Tuple2)var6)._1$mcZ$sp();
               boolean var12 = ((Tuple2)var6)._2$mcZ$sp();
               if (!var11 && var12) {
                  var5 = false;
                  return var5;
               }
            }

            if (var6 == null) {
               throw new MatchError(var6);
            }

            boolean var13 = ((Tuple2)var6)._1$mcZ$sp();
            boolean var14 = ((Tuple2)var6)._2$mcZ$sp();
            if (var13 || var14) {
               throw new MatchError(var6);
            }

            var5 = true;
            return var5;
         }
      }

      public ArraySeqEq(final Eq ev) {
         this.ev = ev;
         Eq.$init$(this);
      }
   }

   public static final class ArraySeqMonoid implements Monoid {
      public double empty$mcD$sp() {
         return Monoid.empty$mcD$sp$(this);
      }

      public float empty$mcF$sp() {
         return Monoid.empty$mcF$sp$(this);
      }

      public int empty$mcI$sp() {
         return Monoid.empty$mcI$sp$(this);
      }

      public long empty$mcJ$sp() {
         return Monoid.empty$mcJ$sp$(this);
      }

      public boolean isEmpty(final Object a, final Eq ev) {
         return Monoid.isEmpty$(this, a, ev);
      }

      public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
         return Monoid.isEmpty$mcD$sp$(this, a, ev);
      }

      public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
         return Monoid.isEmpty$mcF$sp$(this, a, ev);
      }

      public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
         return Monoid.isEmpty$mcI$sp$(this, a, ev);
      }

      public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
         return Monoid.isEmpty$mcJ$sp$(this, a, ev);
      }

      public double combineN$mcD$sp(final double a, final int n) {
         return Monoid.combineN$mcD$sp$(this, a, n);
      }

      public float combineN$mcF$sp(final float a, final int n) {
         return Monoid.combineN$mcF$sp$(this, a, n);
      }

      public int combineN$mcI$sp(final int a, final int n) {
         return Monoid.combineN$mcI$sp$(this, a, n);
      }

      public long combineN$mcJ$sp(final long a, final int n) {
         return Monoid.combineN$mcJ$sp$(this, a, n);
      }

      public double combineAll$mcD$sp(final IterableOnce as) {
         return Monoid.combineAll$mcD$sp$(this, as);
      }

      public float combineAll$mcF$sp(final IterableOnce as) {
         return Monoid.combineAll$mcF$sp$(this, as);
      }

      public int combineAll$mcI$sp(final IterableOnce as) {
         return Monoid.combineAll$mcI$sp$(this, as);
      }

      public long combineAll$mcJ$sp(final IterableOnce as) {
         return Monoid.combineAll$mcJ$sp$(this, as);
      }

      public Option combineAllOption(final IterableOnce as) {
         return Monoid.combineAllOption$(this, as);
      }

      public Monoid reverse() {
         return Monoid.reverse$(this);
      }

      public Monoid reverse$mcD$sp() {
         return Monoid.reverse$mcD$sp$(this);
      }

      public Monoid reverse$mcF$sp() {
         return Monoid.reverse$mcF$sp$(this);
      }

      public Monoid reverse$mcI$sp() {
         return Monoid.reverse$mcI$sp$(this);
      }

      public Monoid reverse$mcJ$sp() {
         return Monoid.reverse$mcJ$sp$(this);
      }

      public double combine$mcD$sp(final double x, final double y) {
         return Semigroup.combine$mcD$sp$(this, x, y);
      }

      public float combine$mcF$sp(final float x, final float y) {
         return Semigroup.combine$mcF$sp$(this, x, y);
      }

      public int combine$mcI$sp(final int x, final int y) {
         return Semigroup.combine$mcI$sp$(this, x, y);
      }

      public long combine$mcJ$sp(final long x, final long y) {
         return Semigroup.combine$mcJ$sp$(this, x, y);
      }

      public Object repeatedCombineN(final Object a, final int n) {
         return Semigroup.repeatedCombineN$(this, a, n);
      }

      public double repeatedCombineN$mcD$sp(final double a, final int n) {
         return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
      }

      public float repeatedCombineN$mcF$sp(final float a, final int n) {
         return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
      }

      public int repeatedCombineN$mcI$sp(final int a, final int n) {
         return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
      }

      public long repeatedCombineN$mcJ$sp(final long a, final int n) {
         return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
      }

      public Semigroup intercalate(final Object middle) {
         return Semigroup.intercalate$(this, middle);
      }

      public Semigroup intercalate$mcD$sp(final double middle) {
         return Semigroup.intercalate$mcD$sp$(this, middle);
      }

      public Semigroup intercalate$mcF$sp(final float middle) {
         return Semigroup.intercalate$mcF$sp$(this, middle);
      }

      public Semigroup intercalate$mcI$sp(final int middle) {
         return Semigroup.intercalate$mcI$sp$(this, middle);
      }

      public Semigroup intercalate$mcJ$sp(final long middle) {
         return Semigroup.intercalate$mcJ$sp$(this, middle);
      }

      public ArraySeq empty() {
         return (ArraySeq).MODULE$.untagged().empty();
      }

      public ArraySeq combine(final ArraySeq xs, final ArraySeq ys) {
         return (ArraySeq)xs.concat(ys);
      }

      public ArraySeq combineN(final ArraySeq x, final int n) {
         return (ArraySeq)StaticMethods$.MODULE$.combineNIterable(.MODULE$.untagged().newBuilder(), x, n);
      }

      public ArraySeq combineAll(final IterableOnce xs) {
         return (ArraySeq)StaticMethods$.MODULE$.combineAllIterable(.MODULE$.untagged().newBuilder(), xs);
      }

      public ArraySeqMonoid() {
         Semigroup.$init$(this);
         Monoid.$init$(this);
      }
   }
}
