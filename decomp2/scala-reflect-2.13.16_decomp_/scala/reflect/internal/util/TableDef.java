package scala.reflect.internal.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterable;
import scala.collection.BuildFromLowPriority2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.immutable.AbstractSeq;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.immutable.IndexedSeq.;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\rEa\u0001B$I\u0001EC\u0001b\u0016\u0001\u0003\u0002\u0003\u0006I\u0001\u0017\u0005\u0007C\u0002!\tAa\u0012\t\u000f\t5\u0003\u0001\"\u0001\u0003P!A!Q\b\u0001!B\u0013\u0011)\u0006C\u0004\u0003\\\u0001!\tA!\u0018\t\u000f\t\r\u0004\u0001\"\u0001\u0003f!9!1\u000e\u0001\u0005\u0002\t5\u0004b\u0002B>\u0001\u0011\u0005!Q\u0010\u0005\b\u0005\u0007\u0003A\u0011\u0001BC\u0011\u001d\u0011Y\t\u0001C\u0001\u0005\u001bCqA!&\u0001\t\u0003\u00119J\u0002\u0004\u0003,\u0002\u0001!Q\u0016\u0005\u000b\u0005kc!Q1A\u0005\u0002\t]\u0006B\u0003B^\u0019\t\u0005\t\u0015!\u0003\u0003:\"1\u0011\r\u0004C\u0001\u0005{CqAa1\r\t\u0003\u0011)\rC\u0004\u0002v2!\tA!3\t\u000f\t5G\u0002\"\u0001\u00024\"9!q\u001a\u0007\u0005\u0002\tE\u0007b\u0002Bl\u0019\u0011\u0005!Q\u0010\u0005\n\u00053d!\u0019!C\u0001\u00057D\u0001Ba8\rA\u0003%!Q\u001c\u0005\t\u0005Cd!\u0019!C\u0001m\"9!1\u001d\u0007!\u0002\u00139\b\u0002\u0003Bs\u0019\t\u0007I\u0011\u0001<\t\u000f\t\u001dH\u0002)A\u0005o\"I!\u0011\u001e\u0007C\u0002\u0013\u0005!1\u001e\u0005\t\u0005cd\u0001\u0015!\u0003\u0003n\"I!1\u001f\u0007C\u0002\u0013\u0005!Q\u0010\u0005\t\u0005kd\u0001\u0015!\u0003\u0003\u0000!9!q\u001f\u0007\u0005\u0002\te\bbBB\u0001\u0019\u0011\u000511\u0001\u0005\b\u0007\u000faA\u0011\u0001B?\u0011\u001d\t9\u0006\u0004C!\u0007\u0013Aqaa\u0003\u0001\t\u0003\u0019i\u0001C\u0004\u0002X\u0001!\te!\u0003\b\u000byC\u0005\u0012A0\u0007\u000b\u001dC\u0005\u0012\u00011\t\u000b\u00054C\u0011\u00012\u0007\t\r4\u0003\t\u001a\u0005\tk\"\u0012)\u001a!C\u0001m\"Aq\u0010\u000bB\tB\u0003%q\u000f\u0003\u0006\u0002\u0002!\u0012)\u001a!C\u0001\u0003\u0007A!\"!\t)\u0005#\u0005\u000b\u0011BA\u0003\u0011)\t\u0019\u0003\u000bBK\u0002\u0013\u0005\u0011Q\u0005\u0005\u000b\u0003[A#\u0011#Q\u0001\n\u0005\u001d\u0002BB1)\t\u0003\ty\u0003C\u0004\u0002<!\"\t!!\u0010\t\u000f\u0005E\u0003\u0006\"\u0001\u0002T!9\u0011q\u000b\u0015\u0005B\u0005e\u0003\"CA5Q\u0005\u0005I\u0011AA6\u0011%\ti\bKI\u0001\n\u0003\ty\bC\u0005\u0002\u001a\"\n\n\u0011\"\u0001\u0002\u001c\"I\u00111\u0015\u0015\u0012\u0002\u0013\u0005\u0011Q\u0015\u0005\n\u0003[C\u0013\u0011!C!\u0003_C\u0011\"!-)\u0003\u0003%\t!a-\t\u0013\u0005U\u0006&!A\u0005\u0002\u0005]\u0006\"CA_Q\u0005\u0005I\u0011IA`\u0011%\ti\rKA\u0001\n\u0003\ty\rC\u0005\u0002T\"\n\t\u0011\"\u0011\u0002V\"I\u0011\u0011\u001c\u0015\u0002\u0002\u0013\u0005\u00131\u001c\u0005\n\u0003;D\u0013\u0011!C!\u0003?<\u0011\"a9'\u0003\u0003E\t!!:\u0007\u0011\r4\u0013\u0011!E\u0001\u0003ODa!\u0019!\u0005\u0002\u0005M\b\"CA,\u0001\u0006\u0005IQIA-\u0011%\t)\u0010QA\u0001\n\u0003\u000b9\u0010C\u0005\u0003\n\u0001\u000b\t\u0011\"!\u0003\f!I!q\u0005!\u0002\u0002\u0013%!\u0011\u0006\u0005\b\u0003k4C\u0011\u0001B\u0019\u0005!!\u0016M\u00197f\t\u00164'BA%K\u0003\u0011)H/\u001b7\u000b\u0005-c\u0015\u0001C5oi\u0016\u0014h.\u00197\u000b\u00055s\u0015a\u0002:fM2,7\r\u001e\u0006\u0002\u001f\u0006)1oY1mC\u000e\u0001Qc\u0001*\u0003FM\u0011\u0001a\u0015\t\u0003)Vk\u0011AT\u0005\u0003-:\u0013a!\u00118z%\u00164\u0017!B0d_2\u001c\bc\u0001+Z7&\u0011!L\u0014\u0002\u000byI,\u0007/Z1uK\u0012t\u0004\u0003\u0002/)\u0005\u0007r!!X\u0013\u000e\u0003!\u000b\u0001\u0002V1cY\u0016$UM\u001a\t\u0003;\u001a\u001a\"AJ*\u0002\rqJg.\u001b;?)\u0005y&AB\"pYVlg.F\u0002f\u0003\u001f\u0019B\u0001K*gSB\u0011AkZ\u0005\u0003Q:\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002ke:\u00111\u000e\u001d\b\u0003Y>l\u0011!\u001c\u0006\u0003]B\u000ba\u0001\u0010:p_Rt\u0014\"A(\n\u0005Et\u0015a\u00029bG.\fw-Z\u0005\u0003gR\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!!\u001d(\u0002\t9\fW.Z\u000b\u0002oB\u0011\u0001\u0010 \b\u0003sj\u0004\"\u0001\u001c(\n\u0005mt\u0015A\u0002)sK\u0012,g-\u0003\u0002~}\n11\u000b\u001e:j]\u001eT!a\u001f(\u0002\u000b9\fW.\u001a\u0011\u0002\u0003\u0019,\"!!\u0002\u0011\u000fQ\u000b9!a\u0003\u0002\u001c%\u0019\u0011\u0011\u0002(\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003BA\u0007\u0003\u001fa\u0001\u0001\u0002\u0005\u0002\u0012!B)\u0019AA\n\u0005\u0005!\u0016\u0003BA\u000b\u00037\u00012\u0001VA\f\u0013\r\tIB\u0014\u0002\b\u001d>$\b.\u001b8h!\r!\u0016QD\u0005\u0004\u0003?q%aA!os\u0006\u0011a\rI\u0001\u0005Y\u00164G/\u0006\u0002\u0002(A\u0019A+!\u000b\n\u0007\u0005-bJA\u0004C_>dW-\u00198\u0002\u000b1,g\r\u001e\u0011\u0015\u0011\u0005E\u0012QGA\u001c\u0003s\u0001R!a\r)\u0003\u0017i\u0011A\n\u0005\u0006k>\u0002\ra\u001e\u0005\b\u0003\u0003y\u0003\u0019AA\u0003\u0011\u001d\t\u0019c\fa\u0001\u0003O\t\u0001\"\\1y/&$G\u000f\u001b\u000b\u0005\u0003\u007f\t)\u0005E\u0002U\u0003\u0003J1!a\u0011O\u0005\rIe\u000e\u001e\u0005\b\u0003\u000f\u0002\u0004\u0019AA%\u0003\u0015)G.Z7t!\u0019\tY%!\u0014\u0002\f9\u0011A\u000b]\u0005\u0004\u0003\u001f\"(aA*fc\u0006Qam\u001c:nCR\u001c\u0006/Z2\u0015\u0007]\f)\u0006C\u0004\u0002HE\u0002\r!!\u0013\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0017\u0011\t\u0005u\u0013qM\u0007\u0003\u0003?RA!!\u0019\u0002d\u0005!A.\u00198h\u0015\t\t)'\u0001\u0003kCZ\f\u0017bA?\u0002`\u0005!1m\u001c9z+\u0011\ti'a\u001d\u0015\u0011\u0005=\u0014QOA<\u0003w\u0002R!a\r)\u0003c\u0002B!!\u0004\u0002t\u00119\u0011\u0011C\u001aC\u0002\u0005M\u0001bB;4!\u0003\u0005\ra\u001e\u0005\n\u0003\u0003\u0019\u0004\u0013!a\u0001\u0003s\u0002r\u0001VA\u0004\u0003c\nY\u0002C\u0005\u0002$M\u0002\n\u00111\u0001\u0002(\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT\u0003BAA\u0003/+\"!a!+\u0007]\f)i\u000b\u0002\u0002\bB!\u0011\u0011RAJ\u001b\t\tYI\u0003\u0003\u0002\u000e\u0006=\u0015!C;oG\",7m[3e\u0015\r\t\tJT\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAK\u0003\u0017\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u001d\t\t\u0002\u000eb\u0001\u0003'\tabY8qs\u0012\"WMZ1vYR$#'\u0006\u0003\u0002\u001e\u0006\u0005VCAAPU\u0011\t)!!\"\u0005\u000f\u0005EQG1\u0001\u0002\u0014\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT\u0003BAT\u0003W+\"!!++\t\u0005\u001d\u0012Q\u0011\u0003\b\u0003#1$\u0019AA\n\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111L\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u007f\ta\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u001c\u0005e\u0006\"CA^s\u0005\u0005\t\u0019AA \u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0019\t\u0007\u0003\u0007\fI-a\u0007\u000e\u0005\u0005\u0015'bAAd\u001d\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0017Q\u0019\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002(\u0005E\u0007\"CA^w\u0005\u0005\t\u0019AA\u000e\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005m\u0013q\u001b\u0005\n\u0003wc\u0014\u0011!a\u0001\u0003\u007f\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u007f\ta!Z9vC2\u001cH\u0003BA\u0014\u0003CD\u0011\"a/?\u0003\u0003\u0005\r!a\u0007\u0002\r\r{G.^7o!\r\t\u0019\u0004Q\n\u0005\u0001N\u000bI\u000f\u0005\u0003\u0002l\u0006EXBAAw\u0015\u0011\ty/a\u0019\u0002\u0005%|\u0017bA:\u0002nR\u0011\u0011Q]\u0001\u0006CB\u0004H._\u000b\u0005\u0003s\fy\u0010\u0006\u0005\u0002|\n\u0005!1\u0001B\u0004!\u0015\t\u0019\u0004KA\u007f!\u0011\ti!a@\u0005\u000f\u0005E1I1\u0001\u0002\u0014!)Qo\u0011a\u0001o\"9\u0011\u0011A\"A\u0002\t\u0015\u0001c\u0002+\u0002\b\u0005u\u00181\u0004\u0005\b\u0003G\u0019\u0005\u0019AA\u0014\u0003\u001d)h.\u00199qYf,BA!\u0004\u0003 Q!!q\u0002B\u0011!\u0015!&\u0011\u0003B\u000b\u0013\r\u0011\u0019B\u0014\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0011Q\u00139b\u001eB\u000e\u0003OI1A!\u0007O\u0005\u0019!V\u000f\u001d7fgA9A+a\u0002\u0003\u001e\u0005m\u0001\u0003BA\u0007\u0005?!q!!\u0005E\u0005\u0004\t\u0019\u0002C\u0005\u0003$\u0011\u000b\t\u00111\u0001\u0003&\u0005\u0019\u0001\u0010\n\u0019\u0011\u000b\u0005M\u0002F!\b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t-\u0002\u0003BA/\u0005[IAAa\f\u0002`\t1qJ\u00196fGR,BAa\r\u0003:Q!!Q\u0007B\u001e!\u0011i\u0006Aa\u000e\u0011\t\u00055!\u0011\b\u0003\b\u0003#1%\u0019AA\n\u0011\u001d\u0011iD\u0012a\u0001\u0005\u007f\tAaY8mgB!A+\u0017B!!\u0015\t\u0019\u0004\u000bB\u001c!\u0011\tiA!\u0012\u0005\u000f\u0005E\u0001A1\u0001\u0002\u0014Q!!\u0011\nB&!\u0011i\u0006Aa\u0011\t\u000b]\u0013\u0001\u0019\u0001-\u0002\r\u0011\"\u0018\u000e\u001c3f)\u0011\u0011IE!\u0015\t\r\tM3\u00011\u0001\\\u0003\u0011qW\r\u001f;\u0011\u000b\u0005-#qK.\n\u0007\teCO\u0001\u0003MSN$\u0018A\u00033fM\u0006,H\u000e^*faR!\u00111\fB0\u0011\u001d\u0011\t'\u0002a\u0001\u0003\u007f\tQ!\u001b8eKb\f\u0001b]3q\u0003\u001a$XM\u001d\u000b\u0004o\n\u001d\u0004b\u0002B5\r\u0001\u0007\u0011qH\u0001\u0002S\u0006I1/\u001a9XS\u0012$\bn]\u000b\u0003\u0005_\u0002bA!\u001d\u0003x\u0005}RB\u0001B:\u0015\u0011\u0011)(!2\u0002\u0013%lW.\u001e;bE2,\u0017\u0002\u0002B=\u0005g\u0012!\"\u00138eKb,GmU3r\u0003!\u0019w\u000e\u001c(b[\u0016\u001cXC\u0001B@!\u0015\u0011\tH!!x\u0013\u0011\u0011IFa\u001d\u0002\u0019\r|GNR;oGRLwN\\:\u0016\u0005\t\u001d\u0005C\u0002B9\u0005\u0003\u0013I\tE\u0004U\u0003\u000f\u0011\u0019%a\u0007\u0002\u0011\r|G.\u00119qYf$BAa$\u0003\u0012B1!\u0011\u000fBA\u00037AqAa%\u000b\u0001\u0004\u0011\u0019%\u0001\u0002fY\u00069!/\u001a;UQ&\u001cH\u0003\u0002BM\u00057k\u0011\u0001\u0001\u0005\t\u0005;[A\u00111\u0001\u0003 \u0006!!m\u001c3z!\u0015!&\u0011\u0015BS\u0013\r\u0011\u0019K\u0014\u0002\ty\tLh.Y7f}A\u0019AKa*\n\u0007\t%fJ\u0001\u0003V]&$(!\u0002+bE2,7c\u0001\u0007\u00030B1!\u0011\u000fBY\u0005\u0007JAAa-\u0003t\tY\u0011IY:ue\u0006\u001cGoU3r\u0003\u0011\u0011xn^:\u0016\u0005\te\u0006CBA&\u0003\u001b\u0012\u0019%A\u0003s_^\u001c\b\u0005\u0006\u0003\u0003@\n\u0005\u0007c\u0001BM\u0019!9!QW\bA\u0002\te\u0016\u0001C5uKJ\fGo\u001c:\u0016\u0005\t\u001d\u0007CBAb\u0003\u0013\u0014\u0019\u0005\u0006\u0003\u0003D\t-\u0007b\u0002B1#\u0001\u0007\u0011qH\u0001\u0007Y\u0016tw\r\u001e5\u0002\u00175\f\u0007pQ8m/&$G\u000f\u001b\u000b\u0005\u0003\u007f\u0011\u0019\u000e\u0003\u0004\u0003VN\u0001\raW\u0001\u0004G>d\u0017!B:qK\u000e\u001c\u0018!C2pY^KG\r\u001e5t+\t\u0011i\u000e\u0005\u0004\u0003r\t\u0005\u0015qH\u0001\u000bG>dw+\u001b3uQN\u0004\u0013!\u0003:po\u001a{'/\\1u\u0003)\u0011xn\u001e$pe6\fG\u000fI\u0001\u000bQ\u0016\fGMR8s[\u0006$\u0018a\u00035fC\u00124uN]7bi\u0002\n\u0001\"\u0019:h\u0019&\u001cHo]\u000b\u0003\u0005[\u0004bA!\u001d\u0003p\n=\u0015\u0002BA(\u0005g\n\u0011\"\u0019:h\u0019&\u001cHo\u001d\u0011\u0002\u000f!,\u0017\rZ3sg\u0006A\u0001.Z1eKJ\u001c\b%\u0001\bnW\u001a{'/\\1u'R\u0014\u0018N\\4\u0015\u0007]\u0014Y\u0010C\u0004\u0003~~\u0001\rAa@\u0002\tM,\u0007O\u001a\t\u0007)\u0006\u001d\u0011qH<\u0002\u001dQ|gi\u001c:nCR$X\rZ*fcV\u00111Q\u0001\t\u0006\u0005c\u0012yo^\u0001\tC2dGk\\*fcR\tq/A\u0003uC\ndW\r\u0006\u0003\u0003@\u000e=\u0001b\u0002B[G\u0001\u0007!\u0011\u0018"
)
public class TableDef {
   public List scala$reflect$internal$util$TableDef$$cols;

   public static TableDef apply(final Seq cols) {
      TableDef$ var10000 = TableDef$.MODULE$;
      return new TableDef(cols);
   }

   public TableDef $tilde(final Column next) {
      return this.retThis((JFunction0.mcV.sp)() -> {
         List var10001 = this.scala$reflect$internal$util$TableDef$$cols;
         if (var10001 == null) {
            throw null;
         } else {
            this.scala$reflect$internal$util$TableDef$$cols = (List)StrictOptimizedSeqOps.appended$(var10001, next);
         }
      });
   }

   public String defaultSep(final int index) {
      List var10001 = this.scala$reflect$internal$util$TableDef$$cols;
      if (var10001 == null) {
         throw null;
      } else {
         return index > SeqOps.size$(var10001) - 2 ? "" : " ";
      }
   }

   public String sepAfter(final int i) {
      return this.defaultSep(i);
   }

   public IndexedSeq sepWidths() {
      Range var10000 = this.scala$reflect$internal$util$TableDef$$cols.indices();
      if (var10000 == null) {
         throw null;
      } else {
         Range map_this = var10000;
         map_this.scala$collection$immutable$Range$$validateMaxLength();
         Builder map_strictOptimizedMap_b = .MODULE$.newBuilder();

         Object var6;
         for(Iterator map_strictOptimizedMap_it = map_this.iterator(); map_strictOptimizedMap_it.hasNext(); var6 = null) {
            int var5 = BoxesRunTime.unboxToInt(map_strictOptimizedMap_it.next());
            Integer map_strictOptimizedMap_$plus$eq_elem = BoxesRunTime.boxToInteger($anonfun$sepWidths$1(this, var5));
            if (map_strictOptimizedMap_b == null) {
               throw null;
            }

            map_strictOptimizedMap_b.addOne(map_strictOptimizedMap_$plus$eq_elem);
         }

         return (IndexedSeq)map_strictOptimizedMap_b.result();
      }
   }

   public List colNames() {
      List var10000 = this.scala$reflect$internal$util$TableDef$$cols;
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Column)map_this.head()).name(), scala.collection.immutable.Nil..MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Column)map_rest.head()).name(), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   public List colFunctions() {
      List var10000 = this.scala$reflect$internal$util$TableDef$$cols;
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Column)map_this.head()).f(), scala.collection.immutable.Nil..MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Column)map_rest.head()).f(), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   public List colApply(final Object el) {
      List var10000 = this.colFunctions();
      if (var10000 == null) {
         throw null;
      } else {
         List map_this = var10000;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Function1)map_this.head()).apply(el), scala.collection.immutable.Nil..MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Function1)map_rest.head()).apply(el), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   public TableDef retThis(final Function0 body) {
      body.apply$mcV$sp();
      return this;
   }

   public Table table(final Seq rows) {
      return new Table(rows);
   }

   public String toString() {
      List var10000 = this.scala$reflect$internal$util$TableDef$$cols;
      String mkString_end = ")";
      String mkString_sep = ", ";
      String mkString_start = "TableDef(";
      if (var10000 == null) {
         throw null;
      } else {
         return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$sepWidths$1(final TableDef $this, final int i) {
      return $this.sepAfter(i).length();
   }

   // $FF: synthetic method
   public static final String $anonfun$colNames$1(final Column x$1) {
      return x$1.name();
   }

   // $FF: synthetic method
   public static final Function1 $anonfun$colFunctions$1(final Column x$2) {
      return x$2.f();
   }

   // $FF: synthetic method
   public static final Object $anonfun$colApply$1(final Object el$1, final Function1 f) {
      return f.apply(el$1);
   }

   public TableDef(final Seq _cols) {
      this.scala$reflect$internal$util$TableDef$$cols = _cols.toList();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class Table extends AbstractSeq {
      private final Seq rows;
      private final List colWidths;
      private final String rowFormat;
      private final String headFormat;
      private final Seq argLists;
      private final List headers;
      // $FF: synthetic field
      public final TableDef $outer;

      public Seq rows() {
         return this.rows;
      }

      public Iterator iterator() {
         return this.rows().iterator();
      }

      public Object apply(final int index) {
         return this.rows().apply(index);
      }

      public int length() {
         return this.rows().length();
      }

      public int maxColWidth(final Column col) {
         String var2 = col.name();
         SeqOps var10000 = (SeqOps)this.rows().map(col.f());
         if (var10000 == null) {
            throw null;
         } else {
            return BoxesRunTime.unboxToInt(((IterableOnceOps)((IterableOps)var10000.prepended(var2)).map((x$3) -> BoxesRunTime.boxToInteger($anonfun$maxColWidth$1(x$3)))).max(scala.math.Ordering.Int..MODULE$));
         }
      }

      public List specs() {
         List var10000 = this.scala$reflect$internal$util$TableDef$Table$$$outer().scala$reflect$internal$util$TableDef$$cols;
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               return scala.collection.immutable.Nil..MODULE$;
            } else {
               Column var6 = (Column)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$specs$1(this, var6), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var6 = (Column)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$specs$1(this, var6), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      }

      public List colWidths() {
         return this.colWidths;
      }

      public String rowFormat() {
         return this.rowFormat;
      }

      public String headFormat() {
         return this.headFormat;
      }

      public Seq argLists() {
         return this.argLists;
      }

      public List headers() {
         return this.headers;
      }

      public String mkFormatString(final Function1 sepf) {
         List var10000 = this.specs();
         if (var10000 == null) {
            throw null;
         } else {
            var10000 = (List)StrictOptimizedIterableOps.zipWithIndex$(var10000);
            if (var10000 == null) {
               throw null;
            } else {
               List map_this = var10000;
               Object var23;
               if (map_this == scala.collection.immutable.Nil..MODULE$) {
                  var23 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  scala.collection.immutable..colon.colon var24 = new scala.collection.immutable..colon.colon;
                  Tuple2 var9 = (Tuple2)map_this.head();
                  if (var9 == null) {
                     throw new MatchError((Object)null);
                  }

                  String $anonfun$mkFormatString$1_c = (String)var9._1();
                  int $anonfun$mkFormatString$1_i = var9._2$mcI$sp();
                  String var10002 = (new StringBuilder(0)).append($anonfun$mkFormatString$1_c).append(sepf.apply($anonfun$mkFormatString$1_i)).toString();
                  Object var20 = null;
                  var24.<init>(var10002, scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_h = var24;
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var24 = new scala.collection.immutable..colon.colon;
                     var9 = (Tuple2)map_rest.head();
                     if (var9 == null) {
                        throw new MatchError((Object)null);
                     }

                     String $anonfun$mkFormatString$1_c = (String)var9._1();
                     int $anonfun$mkFormatString$1_i = var9._2$mcI$sp();
                     var10002 = (new StringBuilder(0)).append($anonfun$mkFormatString$1_c).append(sepf.apply($anonfun$mkFormatString$1_i)).toString();
                     Object var21 = null;
                     var24.<init>(var10002, scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_nx = var24;
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var23 = map_h;
               }

               Object var14 = null;
               Object var15 = null;
               Object var16 = null;
               Object var17 = null;
               Object var18 = null;
               AbstractIterable mkString_this = (AbstractIterable)var23;
               String mkString_mkString_sep = "";
               return mkString_this.mkString("", mkString_mkString_sep, "");
            }
         }
      }

      public Seq toFormattedSeq() {
         return (Seq)this.argLists().map((xs) -> scala.collection.StringOps..MODULE$.format$extension(this.rowFormat(), xs));
      }

      public List allToSeq() {
         List var10000 = this.headers();
         Seq $plus$plus_suffix = this.toFormattedSeq();
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.appendedAll($plus$plus_suffix);
         }
      }

      public String toString() {
         List var10000 = this.allToSeq();
         String mkString_sep = "\n";
         if (var10000 == null) {
            throw null;
         } else {
            AbstractIterable mkString_this = var10000;
            String mkString_end = "";
            String mkString_start = "";
            return IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
         }
      }

      // $FF: synthetic method
      public TableDef scala$reflect$internal$util$TableDef$Table$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final int $anonfun$maxColWidth$1(final Object x$3) {
         return x$3.toString().length();
      }

      // $FF: synthetic method
      public static final String $anonfun$specs$1(final Table $this, final Column x$4) {
         return x$4.formatSpec($this.rows());
      }

      // $FF: synthetic method
      public static final int $anonfun$colWidths$1(final Table $this, final Column col) {
         return $this.maxColWidth(col);
      }

      // $FF: synthetic method
      public static final String $anonfun$rowFormat$1(final Table $this, final int i) {
         return $this.scala$reflect$internal$util$TableDef$Table$$$outer().sepAfter(i);
      }

      // $FF: synthetic method
      public static final String $anonfun$headFormat$1(final Table $this, final int i) {
         return scala.collection.StringOps..MODULE$.$times$extension(" ", BoxesRunTime.unboxToInt($this.scala$reflect$internal$util$TableDef$Table$$$outer().sepWidths().apply(i)));
      }

      // $FF: synthetic method
      public static final String $anonfun$headers$1(final int w1, final int w2) {
         return (new StringBuilder(0)).append(scala.collection.StringOps..MODULE$.$times$extension("-", w1)).append(scala.collection.StringOps..MODULE$.$times$extension(" ", w2)).toString();
      }

      // $FF: synthetic method
      public static final String $anonfun$mkFormatString$1(final Function1 sepf$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            String c = (String)x0$1._1();
            int i = x0$1._2$mcI$sp();
            return (new StringBuilder(0)).append(c).append(sepf$1.apply(i)).toString();
         } else {
            throw new MatchError((Object)null);
         }
      }

      public Table(final Seq rows) {
         this.rows = rows;
         if (TableDef.this == null) {
            throw null;
         } else {
            this.$outer = TableDef.this;
            super();
            List var10001 = TableDef.this.scala$reflect$internal$util$TableDef$$cols;
            if (var10001 == null) {
               throw null;
            } else {
               List map_this = var10001;
               Object var19;
               if (map_this == scala.collection.immutable.Nil..MODULE$) {
                  var19 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Column var10 = (Column)map_this.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.maxColWidth(var10), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var10 = (Column)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.maxColWidth(var10), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var19 = map_h;
               }

               Object var13 = null;
               Object var14 = null;
               Object var15 = null;
               Object var16 = null;
               Object var17 = null;
               this.colWidths = (List)var19;
               this.rowFormat = this.mkFormatString((i) -> $anonfun$rowFormat$1(this, BoxesRunTime.unboxToInt(i)));
               this.headFormat = this.mkFormatString((i) -> $anonfun$headFormat$1(this, BoxesRunTime.unboxToInt(i)));
               this.argLists = (Seq)rows.map((el) -> this.scala$reflect$internal$util$TableDef$Table$$$outer().colApply(el));
               scala.collection.immutable..colon.colon var20 = new scala.collection.immutable..colon.colon;
               String var10003 = scala.collection.StringOps..MODULE$.format$extension(this.headFormat(), TableDef.this.colNames());
               scala.collection.immutable..colon.colon var10004 = new scala.collection.immutable..colon.colon;
               IterableOnceOps var10006 = (IterableOnceOps)this.colWidths().lazyZip(TableDef.this.sepWidths()).map((w1, w2) -> $anonfun$headers$1(BoxesRunTime.unboxToInt(w1), BoxesRunTime.unboxToInt(w2)), BuildFromLowPriority2.buildFromIterableOps$(scala.collection.BuildFrom..MODULE$));
               if (var10006 == null) {
                  throw null;
               } else {
                  IterableOnceOps mkString_this = var10006;
                  String mkString_mkString_sep = "";
                  String var21 = mkString_this.mkString("", mkString_mkString_sep, "");
                  Object var11 = null;
                  Object var12 = null;
                  var10004.<init>(var21, scala.collection.immutable.Nil..MODULE$);
                  var20.<init>(var10003, var10004);
                  this.headers = var20;
               }
            }
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$colWidths$1$adapted(final Table $this, final Column col) {
         return BoxesRunTime.boxToInteger($anonfun$colWidths$1($this, col));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Column implements Product, Serializable {
      private final String name;
      private final Function1 f;
      private final boolean left;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String name() {
         return this.name;
      }

      public Function1 f() {
         return this.f;
      }

      public boolean left() {
         return this.left;
      }

      public int maxWidth(final Seq elems) {
         String var2 = this.name();
         SeqOps var10000 = (SeqOps)elems.map(this.f());
         if (var10000 == null) {
            throw null;
         } else {
            return BoxesRunTime.unboxToInt(((IterableOnceOps)((IterableOps)var10000.prepended(var2)).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$maxWidth$1(x$5)))).max(scala.math.Ordering.Int..MODULE$));
         }
      }

      public String formatSpec(final Seq elems) {
         String justify = this.left() ? "-" : "";
         return (new StringBuilder(2)).append("%").append(justify).append(this.maxWidth(elems)).append("s").toString();
      }

      public String toString() {
         String justify = this.left() ? "<<" : ">>";
         return (new StringBuilder(2)).append(justify).append("(").append(this.name()).append(")").toString();
      }

      public Column copy(final String name, final Function1 f, final boolean left) {
         return new Column(name, f, left);
      }

      public String copy$default$1() {
         return this.name();
      }

      public Function1 copy$default$2() {
         return this.f();
      }

      public boolean copy$default$3() {
         return this.left();
      }

      public String productPrefix() {
         return "Column";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.name();
            case 1:
               return this.f();
            case 2:
               return this.left();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Column;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "name";
            case 1:
               return "f";
            case 2:
               return "left";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.name()));
         var1 = Statics.mix(var1, Statics.anyHash(this.f()));
         var1 = Statics.mix(var1, this.left() ? 1231 : 1237);
         int finalizeHash_length = 3;
         return Statics.avalanche(var1 ^ finalizeHash_length);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof Column) {
               Column var2 = (Column)x$1;
               if (this.left() == var2.left()) {
                  String var10000 = this.name();
                  String var3 = var2.name();
                  if (var10000 == null) {
                     if (var3 != null) {
                        return false;
                     }
                  } else if (!var10000.equals(var3)) {
                     return false;
                  }

                  Function1 var5 = this.f();
                  Function1 var4 = var2.f();
                  if (var5 == null) {
                     if (var4 != null) {
                        return false;
                     }
                  } else if (!var5.equals(var4)) {
                     return false;
                  }

                  if (var2.canEqual(this)) {
                     return true;
                  }
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public static final int $anonfun$maxWidth$1(final Object x$5) {
         return x$5.toString().length();
      }

      public Column(final String name, final Function1 f, final boolean left) {
         this.name = name;
         this.f = f;
         this.left = left;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Column$ implements Serializable {
      public static final Column$ MODULE$ = new Column$();

      public final String toString() {
         return "Column";
      }

      public Column apply(final String name, final Function1 f, final boolean left) {
         return new Column(name, f, left);
      }

      public Option unapply(final Column x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.name(), x$0.f(), x$0.left())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Column$.class);
      }
   }
}
