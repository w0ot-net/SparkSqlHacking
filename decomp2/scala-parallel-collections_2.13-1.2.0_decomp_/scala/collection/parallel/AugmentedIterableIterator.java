package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\rea\u0001\u0003\u000f\u001e!\u0003\r\taH\u0012\t\u000be\u0002A\u0011\u0001\u001e\t\u000by\u0002A\u0011I \t\u000b-\u0003A\u0011\t'\t\u000b]\u0003A\u0011\t-\t\u000b\u0005\u0004A\u0011\t2\t\u000b=\u0004A\u0011\t9\t\u000bY\u0004A\u0011I<\t\u000f\u0005\u0005\u0001\u0001\"\u0011\u0002\u0004!9\u0011q\u0002\u0001\u0005\u0002\u0005E\u0001bBA\u0011\u0001\u0011\u0005\u00111\u0005\u0005\b\u0003\u0007\u0002A\u0011AA#\u0011\u001d\ty\u0006\u0001C\u0001\u0003CBq!a\u001f\u0001\t\u0003\ti\bC\u0004\u0002$\u0002!\t!!*\t\u000f\u0005m\u0006\u0001\"\u0001\u0002>\"9\u0011q\u001a\u0001\u0005\u0002\u0005E\u0007bBAx\u0001\u0011\u0005\u0011\u0011\u001f\u0005\b\u0005\u000b\u0001A\u0011\u0001B\u0004\u0011\u001d\u0011I\u0002\u0001C\u0001\u00057AqAa\r\u0001\t\u0003\u0011)\u0004C\u0004\u0003R\u0001!\tAa\u0015\t\u000f\t\u001d\u0004\u0001\"\u0001\u0003j!9!q\u0010\u0001\u0005\u0002\t\u0005\u0005b\u0002BR\u0001\u0011\u0005!Q\u0015\u0005\b\u0005G\u0003A\u0011\u0001B_\u0011\u001d\u0011)\u000e\u0001C\u0001\u0005/DqAa=\u0001\t\u0003\u0011)PA\rBk\u001elWM\u001c;fI&#XM]1cY\u0016LE/\u001a:bi>\u0014(B\u0001\u0010 \u0003!\u0001\u0018M]1mY\u0016d'B\u0001\u0011\"\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002E\u0005)1oY1mCV\u0011AeL\n\u0004\u0001\u0015J\u0003C\u0001\u0014(\u001b\u0005\t\u0013B\u0001\u0015\"\u0005\u0019\te.\u001f*fMB\u0019!fK\u0017\u000e\u0003uI!\u0001L\u000f\u0003\u001fI+W.Y5og&#XM]1u_J\u0004\"AL\u0018\r\u0001\u00111\u0001\u0007\u0001CC\u0002I\u0012\u0011\u0001V\u0002\u0001#\t\u0019d\u0007\u0005\u0002'i%\u0011Q'\t\u0002\b\u001d>$\b.\u001b8h!\t1s'\u0003\u00029C\t\u0019\u0011I\\=\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0004C\u0001\u0014=\u0013\ti\u0014E\u0001\u0003V]&$\u0018!B2pk:$HC\u0001!D!\t1\u0013)\u0003\u0002CC\t\u0019\u0011J\u001c;\t\u000b\u0011\u0013\u0001\u0019A#\u0002\u0003A\u0004BA\n$.\u0011&\u0011q)\t\u0002\n\rVt7\r^5p]F\u0002\"AJ%\n\u0005)\u000b#a\u0002\"p_2,\u0017M\\\u0001\u0007e\u0016$WoY3\u0016\u00055{EC\u0001(S!\tqs\nB\u0003Q\u0007\t\u0007\u0011KA\u0001V#\tic\u0007C\u0003T\u0007\u0001\u0007A+\u0001\u0002paB)a%\u0016(O\u001d&\u0011a+\t\u0002\n\rVt7\r^5p]J\nAAZ8mIV\u0011\u0011\f\u0018\u000b\u00035~#\"aW/\u0011\u00059bF!\u0002)\u0005\u0005\u0004\t\u0006\"B*\u0005\u0001\u0004q\u0006#\u0002\u0014V7n[\u0006\"\u00021\u0005\u0001\u0004Y\u0016!\u0001>\u0002\u0007M,X.\u0006\u0002dKR\u0011AM\u001a\t\u0003]\u0015$Q\u0001U\u0003C\u0002ECQaZ\u0003A\u0004!\f1A\\;n!\rIG\u000e\u001a\b\u0003M)L!a[\u0011\u0002\u000fA\f7m[1hK&\u0011QN\u001c\u0002\b\u001dVlWM]5d\u0015\tY\u0017%A\u0004qe>$Wo\u0019;\u0016\u0005E\u001cHC\u0001:u!\tq3\u000fB\u0003Q\r\t\u0007\u0011\u000bC\u0003h\r\u0001\u000fQ\u000fE\u0002jYJ\f1!\\5o+\tAx\u0010\u0006\u0002.s\")!p\u0002a\u0002w\u0006\u0019qN\u001d3\u0011\u0007%dh0\u0003\u0002~]\nAqJ\u001d3fe&tw\r\u0005\u0002/\u007f\u0012)\u0001k\u0002b\u0001#\u0006\u0019Q.\u0019=\u0016\t\u0005\u0015\u0011Q\u0002\u000b\u0004[\u0005\u001d\u0001B\u0002>\t\u0001\b\tI\u0001\u0005\u0003jy\u0006-\u0001c\u0001\u0018\u0002\u000e\u0011)\u0001\u000b\u0003b\u0001#\u0006Q!/\u001a3vG\u0016dUM\u001a;\u0016\t\u0005M\u0011q\u0003\u000b\u0007\u0003+\tI\"!\b\u0011\u00079\n9\u0002B\u0003Q\u0013\t\u0007\u0011\u000b\u0003\u0004\u0002\u001c%\u0001\r\u0001Q\u0001\bQ><X.\u00198z\u0011\u0019\u0019\u0016\u00021\u0001\u0002 AAa%VA\u000b\u0003+\t)\"\u0001\u0007nCB\u00144m\\7cS:,'/\u0006\u0004\u0002&\u0005=\u0012Q\u0007\u000b\u0007\u0003O\tI$a\u0010\u0011\u000f)\nI#!\f\u00024%\u0019\u00111F\u000f\u0003\u0011\r{WNY5oKJ\u00042ALA\u0018\t\u0019\t\tD\u0003b\u0001e\t\t1\u000bE\u0002/\u0003k!a!a\u000e\u000b\u0005\u0004\u0011$\u0001\u0002+iCRDq!a\u000f\u000b\u0001\u0004\ti$A\u0001g!\u00151c)LA\u0017\u0011\u001d\t\tE\u0003a\u0001\u0003O\t!a\u00192\u0002!\r|G\u000e\\3diJ\u001aw.\u001c2j]\u0016\u0014XCBA$\u0003\u001b\n\t\u0006\u0006\u0004\u0002J\u0005M\u0013Q\f\t\bU\u0005%\u00121JA(!\rq\u0013Q\n\u0003\u0007\u0003cY!\u0019\u0001\u001a\u0011\u00079\n\t\u0006\u0002\u0004\u00028-\u0011\rA\r\u0005\b\u0003+Z\u0001\u0019AA,\u0003\t\u0001h\r\u0005\u0004'\u00033j\u00131J\u0005\u0004\u00037\n#a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\t\u000f\u0005\u00053\u00021\u0001\u0002J\u0005\u0001b\r\\1u[\u0006\u0004(gY8nE&tWM]\u000b\u0007\u0003G\nI'!\u001c\u0015\r\u0005\u0015\u0014qNA=!\u001dQ\u0013\u0011FA4\u0003W\u00022ALA5\t\u0019\t\t\u0004\u0004b\u0001eA\u0019a&!\u001c\u0005\r\u0005]BB1\u00013\u0011\u001d\tY\u0004\u0004a\u0001\u0003c\u0002RA\n$.\u0003g\u0002R![A;\u0003OJ1!a\u001eo\u00051IE/\u001a:bE2,wJ\\2f\u0011\u001d\t\t\u0005\u0004a\u0001\u0003K\nAbY8qsJ\u0012W/\u001b7eKJ,\u0002\"a \u0002\u0018\u0006m\u00151\u0011\u000b\u0005\u0003\u0003\u000by\nE\u0002/\u0003\u0007#q!!\"\u000e\u0005\u0004\t9IA\u0002CY\u0012\f2aMAE!!\tY)!%\u0002\u0016\u0006eUBAAG\u0015\r\tyiH\u0001\b[V$\u0018M\u00197f\u0013\u0011\t\u0019*!$\u0003\u000f\t+\u0018\u000e\u001c3feB\u0019a&a&\u0005\u000bAk!\u0019A)\u0011\u00079\nY\n\u0002\u0004\u0002\u001e6\u0011\rA\r\u0002\u0005\u0007>dG\u000eC\u0004\u0002\"6\u0001\r!!!\u0002\u0003\t\fqBZ5mi\u0016\u0014(gY8nE&tWM]\u000b\u0007\u0003O\u000bi+!-\u0015\r\u0005%\u0016QWA]!\u001dQ\u0013\u0011FAV\u0003_\u00032ALAW\t\u0015\u0001fB1\u0001R!\rq\u0013\u0011\u0017\u0003\u0007\u0003gs!\u0019\u0001\u001a\u0003\tQC\u0017n\u001d\u0005\u0007\u0003os\u0001\u0019A#\u0002\tA\u0014X\r\u001a\u0005\b\u0003\u0003r\u0001\u0019AAU\u0003I1\u0017\u000e\u001c;fe:{GOM2p[\nLg.\u001a:\u0016\r\u0005}\u0016QYAe)\u0019\t\t-a3\u0002NB9!&!\u000b\u0002D\u0006\u001d\u0007c\u0001\u0018\u0002F\u0012)\u0001k\u0004b\u0001#B\u0019a&!3\u0005\r\u0005MvB1\u00013\u0011\u0019\t9l\u0004a\u0001\u000b\"9\u0011\u0011I\bA\u0002\u0005\u0005\u0017a\u00059beRLG/[8oe\r|WNY5oKJ\u001cXCBAj\u0003?\f\u0019\u000f\u0006\u0005\u0002V\u0006\u0015\u0018q]Av!\u001d1\u0013q[An\u00037L1!!7\"\u0005\u0019!V\u000f\u001d7feA9!&!\u000b\u0002^\u0006\u0005\bc\u0001\u0018\u0002`\u0012)\u0001\u000b\u0005b\u0001#B\u0019a&a9\u0005\r\u0005M\u0006C1\u00013\u0011\u0019\t9\f\u0005a\u0001\u000b\"9\u0011\u0011\u001e\tA\u0002\u0005m\u0017!\u00022ueV,\u0007bBAw!\u0001\u0007\u00111\\\u0001\u0007E\u001a\fGn]3\u0002\u001bQ\f7.\u001a\u001ad_6\u0014\u0017N\\3s+\u0019\t\u00190!?\u0002~R1\u0011Q_A\u0000\u0005\u0007\u0001rAKA\u0015\u0003o\fY\u0010E\u0002/\u0003s$Q\u0001U\tC\u0002E\u00032ALA\u007f\t\u0019\t\u0019,\u0005b\u0001e!1!\u0011A\tA\u0002\u0001\u000b\u0011A\u001c\u0005\b\u0003\u0003\n\u0002\u0019AA{\u00035!'o\u001c93G>l'-\u001b8feV1!\u0011\u0002B\b\u0005'!bAa\u0003\u0003\u0016\t]\u0001c\u0002\u0016\u0002*\t5!\u0011\u0003\t\u0004]\t=A!\u0002)\u0013\u0005\u0004\t\u0006c\u0001\u0018\u0003\u0014\u00111\u00111\u0017\nC\u0002IBaA!\u0001\u0013\u0001\u0004\u0001\u0005bBA!%\u0001\u0007!1B\u0001\u000fg2L7-\u001a\u001ad_6\u0014\u0017N\\3s+\u0019\u0011iBa\t\u0003(QA!q\u0004B\u0015\u0005[\u0011\t\u0004E\u0004+\u0003S\u0011\tC!\n\u0011\u00079\u0012\u0019\u0003B\u0003Q'\t\u0007\u0011\u000bE\u0002/\u0005O!a!a-\u0014\u0005\u0004\u0011\u0004B\u0002B\u0016'\u0001\u0007\u0001)\u0001\u0003ge>l\u0007B\u0002B\u0018'\u0001\u0007\u0001)A\u0003v]RLG\u000eC\u0004\u0002BM\u0001\rAa\b\u0002#M\u0004H.\u001b;BiJ\u001aw.\u001c2j]\u0016\u00148/\u0006\u0004\u00038\t}\"1\t\u000b\t\u0005s\u0011)E!\u0013\u0003NA9a%a6\u0003<\tm\u0002c\u0002\u0016\u0002*\tu\"\u0011\t\t\u0004]\t}B!\u0002)\u0015\u0005\u0004\t\u0006c\u0001\u0018\u0003D\u00111\u00111\u0017\u000bC\u0002IBaAa\u0012\u0015\u0001\u0004\u0001\u0015AA1u\u0011\u001d\u0011Y\u0005\u0006a\u0001\u0005w\taAY3g_J,\u0007b\u0002B()\u0001\u0007!1H\u0001\u0006C\u001a$XM]\u0001\u0013i\u0006\\Wm\u00165jY\u0016\u00144m\\7cS:,'/\u0006\u0004\u0003V\tu#\u0011\r\u000b\u0007\u0005/\u0012\u0019G!\u001a\u0011\r\u0019\n9N!\u0017I!\u001dQ\u0013\u0011\u0006B.\u0005?\u00022A\fB/\t\u0015\u0001VC1\u0001R!\rq#\u0011\r\u0003\u0007\u0003g+\"\u0019\u0001\u001a\t\u000b\u0011+\u0002\u0019A#\t\u000f\u0005\u0005S\u00031\u0001\u0003Z\u0005q1\u000f]1oe\r|WNY5oKJ\u001cXC\u0002B6\u0005g\u00129\b\u0006\u0005\u0003n\te$1\u0010B?!\u001d1\u0013q\u001bB8\u0005_\u0002rAKA\u0015\u0005c\u0012)\bE\u0002/\u0005g\"Q\u0001\u0015\fC\u0002E\u00032A\fB<\t\u0019\t\u0019L\u0006b\u0001e!)AI\u0006a\u0001\u000b\"9!1\n\fA\u0002\t=\u0004b\u0002B(-\u0001\u0007!qN\u0001\fg\u000e\fg\u000eV8BeJ\f\u00170\u0006\u0004\u0003\u0004\n%%1\u0014\u000b\nw\t\u0015%1\u0012BH\u0005CCa\u0001Y\fA\u0002\t\u001d\u0005c\u0001\u0018\u0003\n\u0012)\u0001k\u0006b\u0001#\"11k\u0006a\u0001\u0005\u001b\u0003\u0002BJ+\u0003\b\n\u001d%q\u0011\u0005\b\u0005#;\u0002\u0019\u0001BJ\u0003\u0015\t'O]1z!\u00151#Q\u0013BM\u0013\r\u00119*\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004]\tmEa\u0002BO/\t\u0007!q\u0014\u0002\u0002\u0003F\u0019!q\u0011\u001c\t\r\t-r\u00031\u0001A\u00039\u00198-\u00198U_\u000e{WNY5oKJ,bAa*\u0003.\nEF\u0003\u0003BU\u0005g\u00139La/\u0011\u000f)\nICa+\u00030B\u0019aF!,\u0005\u000bAC\"\u0019A)\u0011\u00079\u0012\t\f\u0002\u0004\u00028a\u0011\rA\r\u0005\b\u0005kC\u0002\u0019\u0001BV\u0003)\u0019H/\u0019:u-\u0006dW/\u001a\u0005\u0007'b\u0001\rA!/\u0011\u0011\u0019*&1\u0016BV\u0005WCq!!\u0011\u0019\u0001\u0004\u0011I+\u0006\u0004\u0003@\n\u0015'\u0011\u001a\u000b\u000b\u0005\u0003\u0014YM!4\u0003P\nM\u0007c\u0002\u0016\u0002*\t\r'q\u0019\t\u0004]\t\u0015G!\u0002)\u001a\u0005\u0004\t\u0006c\u0001\u0018\u0003J\u00121\u0011qG\rC\u0002IBa!a\u0007\u001a\u0001\u0004\u0001\u0005b\u0002B[3\u0001\u0007!1\u0019\u0005\u0007'f\u0001\rA!5\u0011\u0011\u0019*&1\u0019Bb\u0005\u0007Dq!!\u0011\u001a\u0001\u0004\u0011\t-\u0001\u0007{SB\u00144m\\7cS:,'/\u0006\u0005\u0003Z\n\u0005(Q\u001dBu)\u0019\u0011YNa;\u0003rB9!&!\u000b\u0003^\n\u001d\bc\u0002\u0014\u0002X\n}'1\u001d\t\u0004]\t\u0005H!\u0002)\u001b\u0005\u0004\t\u0006c\u0001\u0018\u0003f\u00121\u0011\u0011\u0007\u000eC\u0002I\u00022A\fBu\t\u0019\t9D\u0007b\u0001e!9!Q\u001e\u000eA\u0002\t=\u0018\u0001C8uQ\u0016\u0014\b/\u001b;\u0011\t)Z#1\u001d\u0005\b\u0003\u0003R\u0002\u0019\u0001Bn\u0003=Q\u0018\u000e]!mYJ\u001aw.\u001c2j]\u0016\u0014X\u0003\u0003B|\u0005\u007f\u001c\u0019aa\u0002\u0015\u0015\te8\u0011BB\b\u0007'\u00199\u0002E\u0004+\u0003S\u0011Yp!\u0002\u0011\u000f\u0019\n9N!@\u0004\u0002A\u0019aFa@\u0005\u000bA[\"\u0019A)\u0011\u00079\u001a\u0019\u0001\u0002\u0004\u00022m\u0011\rA\r\t\u0004]\r\u001dAABA\u001c7\t\u0007!\u0007C\u0004\u0004\fm\u0001\ra!\u0004\u0002\tQD\u0017\r\u001e\t\u0005U-\u001a\t\u0001C\u0004\u0004\u0012m\u0001\rA!@\u0002\u0011QD\u0017n]3mK6Dqa!\u0006\u001c\u0001\u0004\u0019\t!\u0001\u0005uQ\u0006$X\r\\3n\u0011\u001d\t\te\u0007a\u0001\u0005s\u0004"
)
public interface AugmentedIterableIterator extends RemainsIterator {
   // $FF: synthetic method
   static int count$(final AugmentedIterableIterator $this, final Function1 p) {
      return $this.count(p);
   }

   default int count(final Function1 p) {
      int i = 0;

      while(this.hasNext()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(this.next()))) {
            ++i;
         }
      }

      return i;
   }

   // $FF: synthetic method
   static Object reduce$(final AugmentedIterableIterator $this, final Function2 op) {
      return $this.reduce(op);
   }

   default Object reduce(final Function2 op) {
      Object r;
      for(r = this.next(); this.hasNext(); r = op.apply(r, this.next())) {
      }

      return r;
   }

   // $FF: synthetic method
   static Object fold$(final AugmentedIterableIterator $this, final Object z, final Function2 op) {
      return $this.fold(z, op);
   }

   default Object fold(final Object z, final Function2 op) {
      Object r;
      for(r = z; this.hasNext(); r = op.apply(r, this.next())) {
      }

      return r;
   }

   // $FF: synthetic method
   static Object sum$(final AugmentedIterableIterator $this, final Numeric num) {
      return $this.sum(num);
   }

   default Object sum(final Numeric num) {
      Object r;
      for(r = num.zero(); this.hasNext(); r = num.plus(r, this.next())) {
      }

      return r;
   }

   // $FF: synthetic method
   static Object product$(final AugmentedIterableIterator $this, final Numeric num) {
      return $this.product(num);
   }

   default Object product(final Numeric num) {
      Object r;
      for(r = num.one(); this.hasNext(); r = num.times(r, this.next())) {
      }

      return r;
   }

   // $FF: synthetic method
   static Object min$(final AugmentedIterableIterator $this, final Ordering ord) {
      return $this.min(ord);
   }

   default Object min(final Ordering ord) {
      Object r = this.next();

      while(this.hasNext()) {
         Object curr = this.next();
         if (ord.lteq(curr, r)) {
            r = curr;
         }
      }

      return r;
   }

   // $FF: synthetic method
   static Object max$(final AugmentedIterableIterator $this, final Ordering ord) {
      return $this.max(ord);
   }

   default Object max(final Ordering ord) {
      Object r = this.next();

      while(this.hasNext()) {
         Object curr = this.next();
         if (ord.gteq(curr, r)) {
            r = curr;
         }
      }

      return r;
   }

   // $FF: synthetic method
   static Object reduceLeft$(final AugmentedIterableIterator $this, final int howmany, final Function2 op) {
      return $this.reduceLeft(howmany, op);
   }

   default Object reduceLeft(final int howmany, final Function2 op) {
      int i = howmany - 1;

      Object u;
      for(u = this.next(); i > 0 && this.hasNext(); --i) {
         u = op.apply(u, this.next());
      }

      return u;
   }

   // $FF: synthetic method
   static Combiner map2combiner$(final AugmentedIterableIterator $this, final Function1 f, final Combiner cb) {
      return $this.map2combiner(f, cb);
   }

   default Combiner map2combiner(final Function1 f, final Combiner cb) {
      if (this.isRemainingCheap()) {
         cb.sizeHint(this.remaining());
      }

      while(this.hasNext()) {
         cb.$plus$eq(f.apply(this.next()));
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner collect2combiner$(final AugmentedIterableIterator $this, final PartialFunction pf, final Combiner cb) {
      return $this.collect2combiner(pf, cb);
   }

   default Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
      Function1 runWith = pf.runWith((x$1) -> (Combiner)cb.$plus$eq(x$1));

      while(this.hasNext()) {
         Object curr = this.next();
         runWith.apply(curr);
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner flatmap2combiner$(final AugmentedIterableIterator $this, final Function1 f, final Combiner cb) {
      return $this.flatmap2combiner(f, cb);
   }

   default Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
      while(this.hasNext()) {
         cb.$plus$plus$eq((IterableOnce)f.apply(this.next()));
      }

      return cb;
   }

   // $FF: synthetic method
   static Builder copy2builder$(final AugmentedIterableIterator $this, final Builder b) {
      return $this.copy2builder(b);
   }

   default Builder copy2builder(final Builder b) {
      if (this.isRemainingCheap()) {
         b.sizeHint(this.remaining());
      }

      while(this.hasNext()) {
         b.$plus$eq(this.next());
      }

      return b;
   }

   // $FF: synthetic method
   static Combiner filter2combiner$(final AugmentedIterableIterator $this, final Function1 pred, final Combiner cb) {
      return $this.filter2combiner(pred, cb);
   }

   default Combiner filter2combiner(final Function1 pred, final Combiner cb) {
      while(this.hasNext()) {
         Object curr = this.next();
         if (BoxesRunTime.unboxToBoolean(pred.apply(curr))) {
            cb.$plus$eq(curr);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner filterNot2combiner$(final AugmentedIterableIterator $this, final Function1 pred, final Combiner cb) {
      return $this.filterNot2combiner(pred, cb);
   }

   default Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
      while(this.hasNext()) {
         Object curr = this.next();
         if (!BoxesRunTime.unboxToBoolean(pred.apply(curr))) {
            cb.$plus$eq(curr);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      return cb;
   }

   // $FF: synthetic method
   static Tuple2 partition2combiners$(final AugmentedIterableIterator $this, final Function1 pred, final Combiner btrue, final Combiner bfalse) {
      return $this.partition2combiners(pred, btrue, bfalse);
   }

   default Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
      while(this.hasNext()) {
         Object curr = this.next();
         if (BoxesRunTime.unboxToBoolean(pred.apply(curr))) {
            btrue.$plus$eq(curr);
         } else {
            bfalse.$plus$eq(curr);
         }
      }

      return new Tuple2(btrue, bfalse);
   }

   // $FF: synthetic method
   static Combiner take2combiner$(final AugmentedIterableIterator $this, final int n, final Combiner cb) {
      return $this.take2combiner(n, cb);
   }

   default Combiner take2combiner(final int n, final Combiner cb) {
      cb.sizeHint(n);

      for(int left = n; left > 0; --left) {
         cb.$plus$eq(this.next());
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner drop2combiner$(final AugmentedIterableIterator $this, final int n, final Combiner cb) {
      return $this.drop2combiner(n, cb);
   }

   default Combiner drop2combiner(final int n, final Combiner cb) {
      this.drop(n);
      if (this.isRemainingCheap()) {
         cb.sizeHint(this.remaining());
      }

      while(this.hasNext()) {
         cb.$plus$eq(this.next());
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner slice2combiner$(final AugmentedIterableIterator $this, final int from, final int until, final Combiner cb) {
      return $this.slice2combiner(from, until, cb);
   }

   default Combiner slice2combiner(final int from, final int until, final Combiner cb) {
      this.drop(from);
      int left = .MODULE$.max(until - from, 0);
      cb.sizeHint(left);

      while(left > 0) {
         cb.$plus$eq(this.next());
         --left;
      }

      return cb;
   }

   // $FF: synthetic method
   static Tuple2 splitAt2combiners$(final AugmentedIterableIterator $this, final int at, final Combiner before, final Combiner after) {
      return $this.splitAt2combiners(at, before, after);
   }

   default Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
      before.sizeHint(at);
      if (this.isRemainingCheap()) {
         after.sizeHint(this.remaining() - at);
      }

      for(int left = at; left > 0; --left) {
         before.$plus$eq(this.next());
      }

      while(this.hasNext()) {
         after.$plus$eq(this.next());
      }

      return new Tuple2(before, after);
   }

   // $FF: synthetic method
   static Tuple2 takeWhile2combiner$(final AugmentedIterableIterator $this, final Function1 p, final Combiner cb) {
      return $this.takeWhile2combiner(p, cb);
   }

   default Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
      boolean loop = true;

      while(this.hasNext() && loop) {
         Object curr = this.next();
         if (BoxesRunTime.unboxToBoolean(p.apply(curr))) {
            cb.$plus$eq(curr);
         } else {
            loop = false;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      return new Tuple2(cb, BoxesRunTime.boxToBoolean(loop));
   }

   // $FF: synthetic method
   static Tuple2 span2combiners$(final AugmentedIterableIterator $this, final Function1 p, final Combiner before, final Combiner after) {
      return $this.span2combiners(p, before, after);
   }

   default Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
      boolean isBefore = true;

      while(this.hasNext() && isBefore) {
         Object curr = this.next();
         if (BoxesRunTime.unboxToBoolean(p.apply(curr))) {
            before.$plus$eq(curr);
         } else {
            if (this.isRemainingCheap()) {
               after.sizeHint(this.remaining() + 1);
            }

            after.$plus$eq(curr);
            isBefore = false;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      while(this.hasNext()) {
         after.$plus$eq(this.next());
      }

      return new Tuple2(before, after);
   }

   // $FF: synthetic method
   static void scanToArray$(final AugmentedIterableIterator $this, final Object z, final Function2 op, final Object array, final int from) {
      $this.scanToArray(z, op, array, from);
   }

   default void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
      Object last = z;

      for(int i = from; this.hasNext(); ++i) {
         last = op.apply(last, this.next());
         scala.runtime.ScalaRunTime..MODULE$.array_update(array, i, last);
      }

   }

   // $FF: synthetic method
   static Combiner scanToCombiner$(final AugmentedIterableIterator $this, final Object startValue, final Function2 op, final Combiner cb) {
      return $this.scanToCombiner(startValue, op, cb);
   }

   default Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
      Object curr = startValue;

      while(this.hasNext()) {
         curr = op.apply(curr, this.next());
         cb.$plus$eq(curr);
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner scanToCombiner$(final AugmentedIterableIterator $this, final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
      return $this.scanToCombiner(howmany, startValue, op, cb);
   }

   default Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
      Object curr = startValue;

      for(int left = howmany; left > 0; --left) {
         curr = op.apply(curr, this.next());
         cb.$plus$eq(curr);
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner zip2combiner$(final AugmentedIterableIterator $this, final RemainsIterator otherpit, final Combiner cb) {
      return $this.zip2combiner(otherpit, cb);
   }

   default Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
      if (this.isRemainingCheap() && otherpit.isRemainingCheap()) {
         cb.sizeHint(scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.remaining()), otherpit.remaining()));
      }

      while(this.hasNext() && otherpit.hasNext()) {
         cb.$plus$eq(new Tuple2(this.next(), otherpit.next()));
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner zipAll2combiner$(final AugmentedIterableIterator $this, final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
      return $this.zipAll2combiner(that, thiselem, thatelem, cb);
   }

   default Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
      if (this.isRemainingCheap() && that.isRemainingCheap()) {
         cb.sizeHint(scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(this.remaining()), that.remaining()));
      }

      while(this.hasNext() && that.hasNext()) {
         cb.$plus$eq(new Tuple2(this.next(), that.next()));
      }

      while(this.hasNext()) {
         cb.$plus$eq(new Tuple2(this.next(), thatelem));
      }

      while(that.hasNext()) {
         cb.$plus$eq(new Tuple2(thiselem, that.next()));
      }

      return cb;
   }

   static void $init$(final AugmentedIterableIterator $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
