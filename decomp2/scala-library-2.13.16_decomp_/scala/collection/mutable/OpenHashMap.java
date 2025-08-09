package scala.collection.mutable;

import java.util.ConcurrentModificationException;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.MapFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.generic.DefaultSerializable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\rEq!\u0002!B\u0011\u0003Ae!\u0002&B\u0011\u0003Y\u0005bBA\u0003\u0003\u0011\u0005!Q\u0015\u0005\b\u0005O\u000bA\u0011\u0001BU\u0011\u001d\u0011Y,\u0001C\u0001\u0005{CqAa6\u0002\t\u0003\u0011IN\u0002\u0004\u0002\u0012\u00051\u00111\u0003\u0005\u000b\u0003/1!\u00111A\u0005\u0002\u0005e\u0001BCA\u0010\r\t\u0005\r\u0011\"\u0001\u0002\"!Q\u0011Q\u0006\u0004\u0003\u0002\u0003\u0006K!a\u0007\t\u0015\u0005=bA!a\u0001\n\u0003\t\t\u0004\u0003\u0006\u00024\u0019\u0011\t\u0019!C\u0001\u0003kA\u0011\"!\u000f\u0007\u0005\u0003\u0005\u000b\u0015B@\t\u0015\u0005mbA!a\u0001\n\u0003\ti\u0004\u0003\u0006\u0002J\u0019\u0011\t\u0019!C\u0001\u0003\u0017B!\"a\u0014\u0007\u0005\u0003\u0005\u000b\u0015BA \u0011\u001d\t)A\u0002C\u0001\u0003#B\u0001Ba<\u0002\t\u0003\t%\u0011\u001f\u0005\n\u0005o\f\u0011\u0011!C\u0005\u0005s4AAS!\u0001+\"Aap\u0005B\u0001B\u0003%q\u0010C\u0004\u0002\u0006M!\t!a\u0002\u0006\r\u0005-1\u0003BA\u0007\u0011\u001d\t)a\u0005C\u0001\u0003;Bq!a\u0018\u0014\t\u0003\n\t\u0007C\u0004\u0002dM\u0001\u000b\u0011B@\t\u000f\u0005\u00154\u0003)Q\u0005\u007f\"A\u0011qM\n!B\u0013\tI\u0007C\u0004\u0002tM\u0001\u000b\u0015B@\t\u001b\u0005U4\u0003\"A\u0001\u0006\u0003\u0005\t\u0015)\u0003\u0000\u0011\u001d\t9h\u0005Q!\n}Dq!!\u001f\u0014\t\u0003\n\t\u0004C\u0004\u0002|M!\t%!\r\t\u001d\u0005u4\u0003\"A\u0001\u0006\u0003\u0005\t\u0015\"\u0003\u0002\u0000!9\u0011QQ\n\u0005B\u0005\u001d\u0005bBAH'\u0011E\u0011\u0011\u0013\u0005\t\u0003+\u001b\u0002\u0015\"\u0003\u0002\u0018\"A\u0011\u0011T\n!\n\u0013\tY\nC\u0004\u0002\"N!\t%a)\t\u000f\u0005%6\u0003\"\u0001\u0002,\"9\u0011QY\n\u0005\u0002\u0005\u001d\u0007bBAi'\u0011\u0005\u00131\u001b\u0005\b\u0003#\u001cB\u0011BAn\u0011!\t\u0019o\u0005Q\u0005\n\u0005\u0015\bbBAz'\u0011\u0005\u0013Q\u001f\u0005\b\u0003s\u001cB\u0011AA~\u0011\u001d\typ\u0005C\u0001\u0005\u0003AqA!\u0003\u0014\t\u0003\u0012Y\u0001C\u0004\u0003\u0010M!\tE!\u0005\u0007\u000f\tU1#!\u0003\u0003\u0018!9\u0011QA\u0019\u0005\u0002\t\u001d\u0002b\u0002B\u0016c\u0001\u0006Ka \u0005\b\u0005[\t\u0004\u0015!\u0003\u0000\u0011!\u0011y#\rQ\u0005\n\u0005]\u0005b\u0002B\u0019c\u0011\u0005\u0011q\u0011\u0005\b\u0005g\tD\u0011\u0001B\u001b\u0011\u001d\u00119$\rD\t\u0005sAqAa\u0010\u0014\t\u0003\ni\u0006C\u0004\u0003BM!\tEa\u0011\t\u000f\t]3\u0003\"\u0011\u0003Z!A!\u0011N\n!\n\u0013\u0011Y\u0007C\u0004\u0003rM!\tEa\u001d\t\u000f\te4\u0003\"\u0011\u0003|!A!\u0011Q\n!\n#\u0012\u0019)A\u0006Pa\u0016t\u0007*Y:i\u001b\u0006\u0004(B\u0001\"D\u0003\u001diW\u000f^1cY\u0016T!\u0001R#\u0002\u0015\r|G\u000e\\3di&|gNC\u0001G\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!S\u0001\u000e\u0003\u0005\u00131b\u00149f]\"\u000b7\u000f['baN\u0019\u0011\u0001\u0014)\u0011\u00055sU\"A#\n\u0005=+%AB!osJ+g\rE\u0002R%Rk\u0011aQ\u0005\u0003'\u000e\u0013!\"T1q\r\u0006\u001cGo\u001c:z!\tI5#F\u0002W9\u001a\u001cbaE,iYVD\b\u0003B%Y5\u0016L!!W!\u0003\u0017\u0005\u00137\u000f\u001e:bGRl\u0015\r\u001d\t\u00037rc\u0001\u0001B\u0003^'\t\u0007aLA\u0002LKf\f\"a\u00182\u0011\u00055\u0003\u0017BA1F\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!T2\n\u0005\u0011,%aA!osB\u00111L\u001a\u0003\u0006ON\u0011\rA\u0018\u0002\u0006-\u0006dW/\u001a\t\u0007\u0013&TV\rV6\n\u0005)\f%AB'ba>\u00038\u000f\u0005\u0003J'i+\u0007#B)n_J\\\u0017B\u00018D\u0005i\u0019FO]5di>\u0003H/[7ju\u0016$\u0017\n^3sC\ndWm\u00149t!\u0011i\u0005OW3\n\u0005E,%A\u0002+va2,'\u0007\u0005\u0002Jg&\u0011A/\u0011\u0002\t\u0013R,'/\u00192mKB1\u0011K\u001e.f)JL!a^\"\u0003%5\u000b\u0007OR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0003srl\u0011A\u001f\u0006\u0003w\u000e\u000bqaZ3oKJL7-\u0003\u0002~u\n\u0019B)\u001a4bk2$8+\u001a:jC2L'0\u00192mK\u0006Y\u0011N\\5uS\u0006d7+\u001b>f!\ri\u0015\u0011A\u0005\u0004\u0003\u0007)%aA%oi\u00061A(\u001b8jiz\"2a[A\u0005\u0011\u0015qX\u00031\u0001\u0000\u0005\u0015)e\u000e\u001e:z!\u0015\tyA\u0002.f\u001d\tI\u0005AA\u0005Pa\u0016tWI\u001c;ssV1\u0011QCA\u000f\u0003\u000f\u001a\"A\u0002'\u0002\u0007-,\u00170\u0006\u0002\u0002\u001cA\u00191,!\b\u0005\u000bu3!\u0019\u00010\u0002\u000f-,\u0017p\u0018\u0013fcR!\u00111EA\u0015!\ri\u0015QE\u0005\u0004\u0003O)%\u0001B+oSRD\u0011\"a\u000b\t\u0003\u0003\u0005\r!a\u0007\u0002\u0007a$\u0013'\u0001\u0003lKf\u0004\u0013\u0001\u00025bg\",\u0012a`\u0001\tQ\u0006\u001c\bn\u0018\u0013fcR!\u00111EA\u001c\u0011!\tYcCA\u0001\u0002\u0004y\u0018!\u00025bg\"\u0004\u0013!\u0002<bYV,WCAA !\u0015i\u0015\u0011IA#\u0013\r\t\u0019%\u0012\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007m\u000b9\u0005B\u0003h\r\t\u0007a,A\u0005wC2,Xm\u0018\u0013fcR!\u00111EA'\u0011%\tYCDA\u0001\u0002\u0004\ty$\u0001\u0004wC2,X\r\t\u000b\t\u0003'\n9&!\u0017\u0002\\A9\u0011Q\u000b\u0004\u0002\u001c\u0005\u0015S\"A\u0001\t\u000f\u0005]\u0001\u00031\u0001\u0002\u001c!1\u0011q\u0006\tA\u0002}Dq!a\u000f\u0011\u0001\u0004\ty\u0004F\u0001l\u0003)i\u0017\r\u001d$bGR|'/_\u000b\u0002!\u0006\t\u0012m\u0019;vC2Le.\u001b;jC2\u001c\u0016N_3\u0002\t5\f7o[\u0001\u0006i\u0006\u0014G.\u001a\t\u0006\u001b\u0006-\u0014qN\u0005\u0004\u0003[*%!B!se\u0006L\bcAA9-5\t1#A\u0003`g&TX-A\u0017tG\u0006d\u0017\rJ2pY2,7\r^5p]\u0012jW\u000f^1cY\u0016$s\n]3o\u0011\u0006\u001c\b.T1qI\u0011\"W\r\\3uK\u0012\f\u0001\"\\8e\u0007>,h\u000e^\u0001\u0005g&TX-A\u0005l]><hnU5{K\u0006q3oY1mC\u0012\u001aw\u000e\u001c7fGRLwN\u001c\u0013nkR\f'\r\\3%\u001fB,g\u000eS1tQ6\u000b\u0007\u000f\n\u0013tSj,w\fJ3r)\u0011\t\u0019#!!\t\r\u0005\r\u0015\u00051\u0001\u0000\u0003\u0005\u0019\u0018aB5t\u000b6\u0004H/_\u000b\u0003\u0003\u0013\u00032!TAF\u0013\r\ti)\u0012\u0002\b\u0005>|G.Z1o\u0003\u0019A\u0017m\u001d5PMR\u0019q0a%\t\r\u0005]1\u00051\u0001[\u0003%9'o\\<UC\ndW\r\u0006\u0002\u0002$\u0005Ia-\u001b8e\u0013:$W\r\u001f\u000b\u0006\u007f\u0006u\u0015q\u0014\u0005\u0007\u0003/)\u0003\u0019\u0001.\t\r\u0005=R\u00051\u0001\u0000\u0003\u0019)\b\u000fZ1uKR1\u00111EAS\u0003OCa!a\u0006'\u0001\u0004Q\u0006BBA\u001eM\u0001\u0007Q-\u0001\u0004bI\u0012|e.\u001a\u000b\u0005\u0003c\ni\u000b\u0003\u0004\u00020\u001e\u0002\ra\\\u0001\u0003WZD3bJAZ\u0003s\u000bY,a0\u0002BB\u0019Q*!.\n\u0007\u0005]VI\u0001\u000beKB\u0014XmY1uK\u0012|e/\u001a:sS\u0012LgnZ\u0001\b[\u0016\u001c8/Y4fC\t\ti,\u0001&bI\u0012|e.\u001a\u0011tQ>,H\u000e\u001a\u0011o_R\u0004#-\u001a\u0011pm\u0016\u0014(/\u001b3eK:\u0004\u0013N\u001c\u0011pe\u0012,'\u000f\t;pA5\f\u0017N\u001c;bS:\u00043m\u001c8tSN$XM\\2zA]LG\u000f\u001b\u0011qkRt\u0013!B:j]\u000e,\u0017EAAb\u0003\u0019\u0011d&M\u0019/a\u0005Y1/\u001e2ue\u0006\u001cGo\u00148f)\u0011\t\t(!3\t\r\u0005]\u0001\u00061\u0001[Q-A\u00131WA]\u0003\u001b\fy,!1\"\u0005\u0005=\u0017AU:vER\u0014\u0018m\u0019;P]\u0016\u00043\u000f[8vY\u0012\u0004cn\u001c;!E\u0016\u0004sN^3se&$G-\u001a8!S:\u0004sN\u001d3fe\u0002\"x\u000eI7bS:$\u0018-\u001b8!G>t7/[:uK:\u001c\u0017\u0010I<ji\"\u0004#/Z7pm\u0016t\u0013a\u00019viR1\u0011Q[Al\u00033\u0004B!TA!K\"1\u0011qC\u0015A\u0002iCa!a\u000f*\u0001\u0004)G\u0003CAk\u0003;\fy.!9\t\r\u0005]!\u00061\u0001[\u0011\u0019\tyC\u000ba\u0001\u007f\"1\u00111\b\u0016A\u0002\u0015\f!\u0002Z3mKR,7\u000b\\8u)\u0011\t\u0019#a:\t\u000f\u0005%8\u00061\u0001\u0002p\u0005)QM\u001c;ss\"\u001a1&!<\u0011\u00075\u000by/C\u0002\u0002r\u0016\u0013a!\u001b8mS:,\u0017A\u0002:f[>4X\r\u0006\u0003\u0002V\u0006]\bBBA\fY\u0001\u0007!,A\u0002hKR$B!!6\u0002~\"1\u0011qC\u0017A\u0002i\u000b\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0005\u0007\u0001B!\u0015B\u0003_&\u0019!qA\"\u0003\u0011%#XM]1u_J\fAb[3zg&#XM]1u_J,\"A!\u0004\u0011\tE\u0013)AW\u0001\u000fm\u0006dW/Z:Ji\u0016\u0014\u0018\r^8s+\t\u0011\u0019\u0002\u0005\u0003R\u0005\u000b)'aE(qK:D\u0015m\u001d5NCBLE/\u001a:bi>\u0014X\u0003\u0002B\r\u0005G\u00192!\rB\u000e!\u0015\t&Q\u0004B\u0011\u0013\r\u0011yb\u0011\u0002\u0011\u0003\n\u001cHO]1di&#XM]1u_J\u00042a\u0017B\u0012\t\u0019\u0011)#\rb\u0001=\n\t\u0011\t\u0006\u0002\u0003*A)\u0011\u0011O\u0019\u0003\"\u0005)\u0011N\u001c3fq\u0006y\u0011N\\5uS\u0006dWj\u001c3D_VtG/A\u0004bIZ\fgnY3\u0002\u000f!\f7OT3yi\u0006!a.\u001a=u)\t\u0011\t#\u0001\u0006oKb$(+Z:vYR$BA!\t\u0003<!9!Q\b\u001dA\u0002\u0005=\u0014\u0001\u00028pI\u0016\fQa\u00197p]\u0016\fqAZ8sK\u0006\u001c\u0007.\u0006\u0003\u0003F\tMC\u0003BA\u0012\u0005\u000fBqA!\u0013;\u0001\u0004\u0011Y%A\u0001g!\u0019i%QJ8\u0003R%\u0019!qJ#\u0003\u0013\u0019+hn\u0019;j_:\f\u0004cA.\u0003T\u00111!Q\u000b\u001eC\u0002y\u0013\u0011!V\u0001\rM>\u0014X-Y2i\u000b:$(/_\u000b\u0005\u00057\u00129\u0007\u0006\u0003\u0002$\tu\u0003b\u0002B%w\u0001\u0007!q\f\t\b\u001b\n\u0005$,\u001aB3\u0013\r\u0011\u0019'\u0012\u0002\n\rVt7\r^5p]J\u00022a\u0017B4\t\u0019\u0011)f\u000fb\u0001=\u0006)bm\u001c:fC\u000eDWK\u001c3fY\u0016$X\rZ#oiJLH\u0003BA\u0012\u0005[BqA!\u0013=\u0001\u0004\u0011y\u0007E\u0004N\u0005\u001b\ny'a\t\u0002!5\f\u0007OV1mk\u0016\u001c\u0018J\u001c)mC\u000e,G\u0003BA9\u0005kBqA!\u0013>\u0001\u0004\u00119\b\u0005\u0004N\u0005CRV-Z\u0001\u000eM&dG/\u001a:J]Bc\u0017mY3\u0015\t\u0005E$Q\u0010\u0005\b\u0005\u0013r\u0004\u0019\u0001B@!\u001di%\u0011\r.f\u0003\u0013\u000bAb\u001d;sS:<\u0007K]3gSb,\"A!\"\u0011\t\t\u001d%\u0011S\u0007\u0003\u0005\u0013SAAa#\u0003\u000e\u0006!A.\u00198h\u0015\t\u0011y)\u0001\u0003kCZ\f\u0017\u0002\u0002BJ\u0005\u0013\u0013aa\u0015;sS:<\u0007fC\n\u0003\u0018\u0006e&QTA`\u0005C\u00032!\u0014BM\u0013\r\u0011Y*\u0012\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017E\u0001BP\u0003i+6/\u001a\u0011ICNDW*\u00199!_J\u0004sN\\3!_\u001a\u0004C\u000f[3!gB,7-[1mSj,G\r\t<feNLwN\\:!Q1{gnZ'ba2\u0002\u0013I\\=SK\u001al\u0015\r]\u0015!S:\u001cH/Z1eA=4\u0007e\u00149f]\"\u000b7\u000f['ba\u0006\u0012!1U\u0001\u0007e9\n4G\f\u0019\u0015\u0003!\u000bQ!Z7qif,bAa+\u00032\n]VC\u0001BW!\u0019I5Ca,\u00036B\u00191L!-\u0005\r\tM6A1\u0001_\u0005\u0005Y\u0005cA.\u00038\u00121!\u0011X\u0002C\u0002y\u0013\u0011AV\u0001\u0005MJ|W.\u0006\u0004\u0003@\n\u0015'\u0011\u001a\u000b\u0005\u0005\u0003\u0014Y\r\u0005\u0004J'\t\r'q\u0019\t\u00047\n\u0015GA\u0002BZ\t\t\u0007a\fE\u0002\\\u0005\u0013$aA!/\u0005\u0005\u0004q\u0006b\u0002Bg\t\u0001\u0007!qZ\u0001\u0003SR\u0004R!\u0015Bi\u0005+L1Aa5D\u00051IE/\u001a:bE2,wJ\\2f!\u0019i\u0005Oa1\u0003H\u0006Qa.Z<Ck&dG-\u001a:\u0016\r\tm'q\u001dBv+\t\u0011i\u000eE\u0004J\u0005?\u0014\u0019O!<\n\u0007\t\u0005\u0018IA\u0004Ck&dG-\u001a:\u0011\r5\u0003(Q\u001dBu!\rY&q\u001d\u0003\u0007\u0005g+!\u0019\u00010\u0011\u0007m\u0013Y\u000f\u0002\u0004\u0003:\u0016\u0011\rA\u0018\t\u0007\u0013N\u0011)O!;\u0002-9,\u0007\u0010\u001e)pg&$\u0018N^3Q_^,'o\u00144Uo>$2a Bz\u0011\u0019\u0011)0\u0005a\u0001\u007f\u00061A/\u0019:hKR\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa?\u0011\t\t\u001d%Q`\u0005\u0005\u0005\u007f\u0014II\u0001\u0004PE*,7\r\u001e\u0015\f\u0003\t]\u0015\u0011\u0018BO\u0003\u007f\u0013\t\u000bK\u0004\u0002\u0007\u000b\tYda\u0003\u0011\u00075\u001b9!C\u0002\u0004\n\u0015\u0013\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u001f\u0003\rA3\u0002\u0001BL\u0003s\u0013i*a0\u0003\"\":\u0001a!\u0002\u0002<\r-\u0001"
)
public class OpenHashMap extends AbstractMap implements StrictOptimizedIterableOps, DefaultSerializable {
   private final int actualInitialSize;
   public int scala$collection$mutable$OpenHashMap$$mask;
   public OpenEntry[] scala$collection$mutable$OpenHashMap$$table;
   private int _size;
   public int scala$collection$mutable$OpenHashMap$$deleted;
   public int scala$collection$mutable$OpenHashMap$$modCount;

   public static Builder newBuilder() {
      return OpenHashMap$.MODULE$.newBuilder();
   }

   public static OpenHashMap from(final IterableOnce it) {
      return OpenHashMap$.MODULE$.from(it);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public MapFactory mapFactory() {
      return OpenHashMap$.MODULE$;
   }

   public int size() {
      return this._size;
   }

   public int knownSize() {
      return this.size();
   }

   public void scala$collection$mutable$OpenHashMap$$size_$eq(final int s) {
      this._size = s;
   }

   public boolean isEmpty() {
      return this._size == 0;
   }

   public int hashOf(final Object key) {
      int h = Statics.anyHash(key);
      h ^= h >>> 20 ^ h >>> 12;
      return h ^ h >>> 7 ^ h >>> 4;
   }

   private void growTable() {
      int oldSize = this.scala$collection$mutable$OpenHashMap$$mask + 1;
      int newSize = 4 * oldSize;
      OpenEntry[] oldTable = this.scala$collection$mutable$OpenHashMap$$table;
      this.scala$collection$mutable$OpenHashMap$$table = new OpenEntry[newSize];
      this.scala$collection$mutable$OpenHashMap$$mask = newSize - 1;

      for(OpenEntry var6 : oldTable) {
         $anonfun$growTable$1(this, var6);
      }

      this.scala$collection$mutable$OpenHashMap$$deleted = 0;
   }

   private int findIndex(final Object key, final int hash) {
      int index = hash & this.scala$collection$mutable$OpenHashMap$$mask;
      int j = 0;
      int firstDeletedIndex = -1;
      OpenEntry entry = this.scala$collection$mutable$OpenHashMap$$table[index];

      while(true) {
         if (entry == null) {
            if (firstDeletedIndex == -1) {
               return index;
            }

            return firstDeletedIndex;
         }

         if (entry.hash() == hash && BoxesRunTime.equals(entry.key(), key)) {
            Option var10000 = entry.value();
            None$ var7 = None$.MODULE$;
            if (var10000 == null) {
               break;
            }

            if (!var10000.equals(var7)) {
               break;
            }
         }

         if (firstDeletedIndex == -1) {
            Option var9 = entry.value();
            None$ var8 = None$.MODULE$;
            if (var9 != null) {
               if (var9.equals(var8)) {
                  firstDeletedIndex = index;
               }
            }
         }

         ++j;
         index = index + j & this.scala$collection$mutable$OpenHashMap$$mask;
         entry = this.scala$collection$mutable$OpenHashMap$$table[index];
      }

      return index;
   }

   public void update(final Object key, final Object value) {
      this.put(key, value);
   }

   public OpenHashMap addOne(final Tuple2 kv) {
      this.put(kv._1(), kv._2());
      return this;
   }

   public OpenHashMap subtractOne(final Object key) {
      this.remove(key);
      return this;
   }

   public Option put(final Object key, final Object value) {
      return this.put(key, this.hashOf(key), value);
   }

   private Option put(final Object key, final int hash, final Object value) {
      if (2 * (this.size() + this.scala$collection$mutable$OpenHashMap$$deleted) > this.scala$collection$mutable$OpenHashMap$$mask) {
         this.growTable();
      }

      int index = this.findIndex(key, hash);
      OpenEntry entry = this.scala$collection$mutable$OpenHashMap$$table[index];
      if (entry == null) {
         this.scala$collection$mutable$OpenHashMap$$table[index] = new OpenEntry(key, hash, new Some(value));
         ++this.scala$collection$mutable$OpenHashMap$$modCount;
         this.scala$collection$mutable$OpenHashMap$$size_$eq(this.size() + 1);
         return None$.MODULE$;
      } else {
         Option res = entry.value();
         Option var10000 = entry.value();
         None$ var7 = None$.MODULE$;
         if (var10000 != null) {
            if (var10000.equals(var7)) {
               entry.key_$eq(key);
               entry.hash_$eq(hash);
               this.scala$collection$mutable$OpenHashMap$$size_$eq(this.size() + 1);
               --this.scala$collection$mutable$OpenHashMap$$deleted;
               ++this.scala$collection$mutable$OpenHashMap$$modCount;
            }
         }

         entry.value_$eq(new Some(value));
         return res;
      }
   }

   private void deleteSlot(final OpenEntry entry) {
      entry.key_$eq((Object)null);
      entry.hash_$eq(0);
      entry.value_$eq(None$.MODULE$);
      this.scala$collection$mutable$OpenHashMap$$size_$eq(this.size() - 1);
      ++this.scala$collection$mutable$OpenHashMap$$deleted;
   }

   public Option remove(final Object key) {
      OpenEntry entry;
      label15: {
         entry = this.scala$collection$mutable$OpenHashMap$$table[this.findIndex(key, this.hashOf(key))];
         if (entry != null) {
            Option var10000 = entry.value();
            None$ var3 = None$.MODULE$;
            if (var10000 == null) {
               break label15;
            }

            if (!var10000.equals(var3)) {
               break label15;
            }
         }

         return None$.MODULE$;
      }

      Option res = entry.value();
      entry.key_$eq((Object)null);
      entry.hash_$eq(0);
      entry.value_$eq(None$.MODULE$);
      this.scala$collection$mutable$OpenHashMap$$size_$eq(this.size() - 1);
      ++this.scala$collection$mutable$OpenHashMap$$deleted;
      return res;
   }

   public Option get(final Object key) {
      int hash = this.hashOf(key);
      int index = hash & this.scala$collection$mutable$OpenHashMap$$mask;
      OpenEntry entry = this.scala$collection$mutable$OpenHashMap$$table[index];

      for(int j = 0; entry != null; entry = this.scala$collection$mutable$OpenHashMap$$table[index]) {
         if (entry.hash() == hash && BoxesRunTime.equals(entry.key(), key)) {
            return entry.value();
         }

         ++j;
         index = index + j & this.scala$collection$mutable$OpenHashMap$$mask;
      }

      return None$.MODULE$;
   }

   public Iterator iterator() {
      return new OpenHashMapIterator() {
         public Tuple2 nextResult(final OpenEntry node) {
            return new Tuple2(node.key(), node.value().get());
         }
      };
   }

   public Iterator keysIterator() {
      return new OpenHashMapIterator() {
         public Object nextResult(final OpenEntry node) {
            return node.key();
         }
      };
   }

   public Iterator valuesIterator() {
      return new OpenHashMapIterator() {
         public Object nextResult(final OpenEntry node) {
            return node.value().get();
         }
      };
   }

   public OpenHashMap clone() {
      OpenHashMap it = new OpenHashMap();

      for(Object var5 : this.scala$collection$mutable$OpenHashMap$$table) {
         if (var5 != null) {
            label18: {
               Option var10000 = ((OpenEntry)var5).value();
               None$ var6 = None$.MODULE$;
               if (var10000 != null) {
                  if (var10000.equals(var6)) {
                     break label18;
                  }
               }

               $anonfun$clone$1(it, (OpenEntry)var5);
            }
         }

         Object var7 = null;
      }

      return it;
   }

   public void foreach(final Function1 f) {
      int startModCount = this.scala$collection$mutable$OpenHashMap$$modCount;

      for(Object var6 : this.scala$collection$mutable$OpenHashMap$$table) {
         if (var6 != null) {
            label29: {
               Option var10000 = ((OpenEntry)var6).value();
               None$ var7 = None$.MODULE$;
               if (var10000 != null) {
                  if (var10000.equals(var7)) {
                     break label29;
                  }
               }

               if (this.scala$collection$mutable$OpenHashMap$$modCount != startModCount) {
                  throw new ConcurrentModificationException();
               }

               f.apply(new Tuple2(((OpenEntry)var6).key(), ((OpenEntry)var6).value().get()));
            }
         }

         Object var8 = null;
      }

   }

   public void foreachEntry(final Function2 f) {
      int startModCount = this.scala$collection$mutable$OpenHashMap$$modCount;

      for(Object var6 : this.scala$collection$mutable$OpenHashMap$$table) {
         if (var6 != null) {
            label29: {
               Option var10000 = ((OpenEntry)var6).value();
               None$ var7 = None$.MODULE$;
               if (var10000 != null) {
                  if (var10000.equals(var7)) {
                     break label29;
                  }
               }

               if (this.scala$collection$mutable$OpenHashMap$$modCount != startModCount) {
                  throw new ConcurrentModificationException();
               }

               f.apply(((OpenEntry)var6).key(), ((OpenEntry)var6).value().get());
            }
         }

         Object var8 = null;
      }

   }

   private void foreachUndeletedEntry(final Function1 f) {
      for(Object var5 : this.scala$collection$mutable$OpenHashMap$$table) {
         if (var5 != null) {
            label18: {
               Option var10000 = ((OpenEntry)var5).value();
               None$ var6 = None$.MODULE$;
               if (var10000 != null) {
                  if (var10000.equals(var6)) {
                     break label18;
                  }
               }

               f.apply(var5);
            }
         }

         Object var7 = null;
      }

   }

   public OpenHashMap mapValuesInPlace(final Function2 f) {
      for(Object var5 : this.scala$collection$mutable$OpenHashMap$$table) {
         if (var5 != null) {
            label18: {
               Option var10000 = ((OpenEntry)var5).value();
               None$ var6 = None$.MODULE$;
               if (var10000 != null) {
                  if (var10000.equals(var6)) {
                     break label18;
                  }
               }

               ((OpenEntry)var5).value_$eq(new Some(f.apply(((OpenEntry)var5).key(), ((OpenEntry)var5).value().get())));
            }
         }

         Object var7 = null;
      }

      return this;
   }

   public OpenHashMap filterInPlace(final Function2 f) {
      for(Object var5 : this.scala$collection$mutable$OpenHashMap$$table) {
         if (var5 != null) {
            label20: {
               Option var10000 = ((OpenEntry)var5).value();
               None$ var6 = None$.MODULE$;
               if (var10000 != null) {
                  if (var10000.equals(var6)) {
                     break label20;
                  }
               }

               if (!BoxesRunTime.unboxToBoolean(f.apply(((OpenEntry)var5).key(), ((OpenEntry)var5).value().get()))) {
                  ((OpenEntry)var5).key_$eq((Object)null);
                  ((OpenEntry)var5).hash_$eq(0);
                  ((OpenEntry)var5).value_$eq(None$.MODULE$);
                  this.scala$collection$mutable$OpenHashMap$$size_$eq(this.size() - 1);
                  ++this.scala$collection$mutable$OpenHashMap$$deleted;
               }
            }
         }

         Object var7 = null;
      }

      return this;
   }

   public String stringPrefix() {
      return "OpenHashMap";
   }

   // $FF: synthetic method
   public static final void $anonfun$growTable$1(final OpenHashMap $this, final OpenEntry entry) {
      label15: {
         if (entry != null) {
            Option var10000 = entry.value();
            None$ var2 = None$.MODULE$;
            if (var10000 == null) {
               break label15;
            }

            if (!var10000.equals(var2)) {
               break label15;
            }
         }

         return;
      }

      $this.scala$collection$mutable$OpenHashMap$$table[$this.findIndex(entry.key(), entry.hash())] = entry;
   }

   // $FF: synthetic method
   public static final void $anonfun$clone$1(final OpenHashMap it$1, final OpenEntry entry) {
      it$1.put(entry.key(), entry.hash(), entry.value().get());
   }

   // $FF: synthetic method
   public static final void $anonfun$foreach$1(final OpenHashMap $this, final int startModCount$1, final Function1 f$1, final OpenEntry entry) {
      if ($this.scala$collection$mutable$OpenHashMap$$modCount != startModCount$1) {
         throw new ConcurrentModificationException();
      } else {
         f$1.apply(new Tuple2(entry.key(), entry.value().get()));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachEntry$1(final OpenHashMap $this, final int startModCount$2, final Function2 f$2, final OpenEntry entry) {
      if ($this.scala$collection$mutable$OpenHashMap$$modCount != startModCount$2) {
         throw new ConcurrentModificationException();
      } else {
         f$2.apply(entry.key(), entry.value().get());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$foreachUndeletedEntry$1(final Function1 f$3, final OpenEntry entry) {
      label15: {
         if (entry != null) {
            Option var10000 = entry.value();
            None$ var2 = None$.MODULE$;
            if (var10000 == null) {
               break label15;
            }

            if (!var10000.equals(var2)) {
               break label15;
            }
         }

         return;
      }

      f$3.apply(entry);
   }

   // $FF: synthetic method
   public static final void $anonfun$mapValuesInPlace$1(final Function2 f$4, final OpenEntry entry) {
      entry.value_$eq(new Some(f$4.apply(entry.key(), entry.value().get())));
   }

   // $FF: synthetic method
   public static final void $anonfun$filterInPlace$1(final OpenHashMap $this, final Function2 f$5, final OpenEntry entry) {
      if (!BoxesRunTime.unboxToBoolean(f$5.apply(entry.key(), entry.value().get()))) {
         entry.key_$eq((Object)null);
         entry.hash_$eq(0);
         entry.value_$eq(None$.MODULE$);
         $this.scala$collection$mutable$OpenHashMap$$size_$eq($this.size() - 1);
         ++$this.scala$collection$mutable$OpenHashMap$$deleted;
      }
   }

   public OpenHashMap(final int initialSize) {
      OpenHashMap$ var10001 = OpenHashMap$.MODULE$;
      this.actualInitialSize = 1 << -Integer.numberOfLeadingZeros(initialSize - 1);
      this.scala$collection$mutable$OpenHashMap$$mask = this.actualInitialSize - 1;
      this.scala$collection$mutable$OpenHashMap$$table = new OpenEntry[this.actualInitialSize];
      this._size = 0;
      this.scala$collection$mutable$OpenHashMap$$deleted = 0;
      this.scala$collection$mutable$OpenHashMap$$modCount = 0;
   }

   public OpenHashMap() {
      this(8);
   }

   // $FF: synthetic method
   public static final Object $anonfun$growTable$1$adapted(final OpenHashMap $this, final OpenEntry entry) {
      $anonfun$growTable$1($this, entry);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$clone$1$adapted(final OpenHashMap it$1, final OpenEntry entry) {
      $anonfun$clone$1(it$1, entry);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$foreach$1$adapted(final OpenHashMap $this, final int startModCount$1, final Function1 f$1, final OpenEntry entry) {
      $anonfun$foreach$1($this, startModCount$1, f$1, entry);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$foreachEntry$1$adapted(final OpenHashMap $this, final int startModCount$2, final Function2 f$2, final OpenEntry entry) {
      $anonfun$foreachEntry$1($this, startModCount$2, f$2, entry);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$foreachUndeletedEntry$1$adapted(final Function1 f$3, final OpenEntry entry) {
      $anonfun$foreachUndeletedEntry$1(f$3, entry);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$mapValuesInPlace$1$adapted(final Function2 f$4, final OpenEntry entry) {
      $anonfun$mapValuesInPlace$1(f$4, entry);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$filterInPlace$1$adapted(final OpenHashMap $this, final Function2 f$5, final OpenEntry entry) {
      $anonfun$filterInPlace$1($this, f$5, entry);
      return BoxedUnit.UNIT;
   }

   private static final class OpenEntry {
      private Object key;
      private int hash;
      private Option value;

      public Object key() {
         return this.key;
      }

      public void key_$eq(final Object x$1) {
         this.key = x$1;
      }

      public int hash() {
         return this.hash;
      }

      public void hash_$eq(final int x$1) {
         this.hash = x$1;
      }

      public Option value() {
         return this.value;
      }

      public void value_$eq(final Option x$1) {
         this.value = x$1;
      }

      public OpenEntry(final Object key, final int hash, final Option value) {
         this.key = key;
         this.hash = hash;
         this.value = value;
         super();
      }
   }

   private abstract class OpenHashMapIterator extends AbstractIterator {
      private int index;
      private final int initialModCount;
      // $FF: synthetic field
      public final OpenHashMap $outer;

      private void advance() {
         if (this.initialModCount != this.scala$collection$mutable$OpenHashMap$OpenHashMapIterator$$$outer().scala$collection$mutable$OpenHashMap$$modCount) {
            throw new ConcurrentModificationException();
         } else {
            for(; this.index <= this.scala$collection$mutable$OpenHashMap$OpenHashMapIterator$$$outer().scala$collection$mutable$OpenHashMap$$mask; ++this.index) {
               if (this.scala$collection$mutable$OpenHashMap$OpenHashMapIterator$$$outer().scala$collection$mutable$OpenHashMap$$table[this.index] != null) {
                  Option var10000 = this.scala$collection$mutable$OpenHashMap$OpenHashMapIterator$$$outer().scala$collection$mutable$OpenHashMap$$table[this.index].value();
                  None$ var1 = None$.MODULE$;
                  if (var10000 == null) {
                     return;
                  }

                  if (!var10000.equals(var1)) {
                     break;
                  }
               }
            }

         }
      }

      public boolean hasNext() {
         this.advance();
         return this.index <= this.scala$collection$mutable$OpenHashMap$OpenHashMapIterator$$$outer().scala$collection$mutable$OpenHashMap$$mask;
      }

      public Object next() {
         this.advance();
         OpenEntry result = this.scala$collection$mutable$OpenHashMap$OpenHashMapIterator$$$outer().scala$collection$mutable$OpenHashMap$$table[this.index];
         ++this.index;
         return this.nextResult(result);
      }

      public abstract Object nextResult(final OpenEntry node);

      // $FF: synthetic method
      public OpenHashMap scala$collection$mutable$OpenHashMap$OpenHashMapIterator$$$outer() {
         return this.$outer;
      }

      public OpenHashMapIterator() {
         if (OpenHashMap.this == null) {
            throw null;
         } else {
            this.$outer = OpenHashMap.this;
            super();
            this.index = 0;
            this.initialModCount = OpenHashMap.this.scala$collection$mutable$OpenHashMap$$modCount;
         }
      }
   }
}
