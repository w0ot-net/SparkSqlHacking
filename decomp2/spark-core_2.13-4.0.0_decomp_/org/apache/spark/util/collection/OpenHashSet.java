package org.apache.spark.util.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Private;
import org.sparkproject.guava.hash.Hashing;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ManifestFactory;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@Private
@ScalaSignature(
   bytes = "\u0006\u0005\r}a\u0001B'O\u0001eC\u0001\"\u001c\u0001\u0003\u0002\u0003\u0006IA\u001c\u0005\tc\u0002\u0011\t\u0011)A\u0005e\"AQ\u000f\u0001B\u0002B\u0003-a\u000fC\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005}\u0002\u0001\"\u0001\u0002P!9\u0011q\b\u0001\u0005\u0002\u0005e\u0003\"CA1\u0001\t\u0007I\u0011CA2\u0011!\u0011\u0019\u0005\u0001Q\u0001\n\u0005\u0015\u0004\"\u0003B#\u0001\u0001\u0007I\u0011CAA\u0011%\u00119\u0005\u0001a\u0001\n#\u0011I\u0005C\u0004\u0003P\u0001\u0001\u000b\u0015\u00028\t\u0013\tE\u0003\u00011A\u0005\u0012\u0005\u0005\u0005\"\u0003B*\u0001\u0001\u0007I\u0011\u0003B+\u0011\u001d\u0011I\u0006\u0001Q!\n9D\u0011Ba\u0017\u0001\u0001\u0004%\t\"!!\t\u0013\tu\u0003\u00011A\u0005\u0012\t}\u0003b\u0002B2\u0001\u0001\u0006KA\u001c\u0005\n\u0005K\u0002\u0001\u0019!C\t\u0003\u0003C\u0011Ba\u001a\u0001\u0001\u0004%\tB!\u001b\t\u000f\t5\u0004\u0001)Q\u0005]\"I!q\u000e\u0001A\u0002\u0013E!\u0011\u000f\u0005\n\u0005s\u0002\u0001\u0019!C\t\u0005wB\u0001Ba \u0001A\u0003&!1\u000f\u0005\b\u0005\u0003\u0003A\u0011\u0001B9\u0011-\u0011\u0019\t\u0001a\u0001\u0002\u0004%\tB!\"\t\u0017\t5\u0005\u00011AA\u0002\u0013E!q\u0012\u0005\f\u0005'\u0003\u0001\u0019!A!B\u0013\u00119\tC\u0004\u0003\u0016\u0002!\t!!!\t\u000f\t]\u0005\u0001\"\u0001\u0002\u0002\"9!\u0011\u0014\u0001\u0005\u0002\tm\u0005b\u0002BT\u0001\u0011\u0005!\u0011\u0016\u0005\b\u0005[\u0003A\u0011\u0001BX\u0011\u001d\u0011)\f\u0001C\u0005\u0005oCqAa0\u0001\t\u0003\u0011\t\rC\u0004\u0003F\u0002!\tAa2\t\u000f\tM\u0007\u0001\"\u0001\u0003V\"9!\u0011\u001c\u0001\u0005\u0002\tm\u0007b\u0002Bp\u0001\u0011\u0005!\u0011\u001d\u0005\b\u0005S\u0004A\u0011\u0001Bv\u0011\u001d\u0011y\u000f\u0001C\u0001\u0005cDqAa>\u0001\t\u0013\u0011I\u0010C\u0004\u0004\u0002\u0001!Iaa\u0001\t\u000f\r%\u0001\u0001\"\u0003\u0004\f\u001dA\u0011\u0011\u000e(\t\u0002I\u000bYGB\u0004N\u001d\"\u0005!+!\u001c\t\u000f\u0005}R\u0006\"\u0001\u0002~!I\u0011qP\u0017C\u0002\u0013\u0005\u0011\u0011\u0011\u0005\b\u0003\u0007k\u0003\u0015!\u0003o\u0011%\t))\fb\u0001\n\u0003\t\t\tC\u0004\u0002\b6\u0002\u000b\u0011\u00028\t\u0013\u0005%UF1A\u0005\u0002\u0005\u0005\u0005bBAF[\u0001\u0006IA\u001c\u0005\n\u0003\u001bk#\u0019!C\u0001\u0003\u0003Cq!a$.A\u0003%aN\u0002\u0004\u0002\u00126\u0002\u00121\u0013\u0005\b\u0003\u007f9D\u0011AAL\u0011\u001d\t\u0019l\u000eC\u0001\u0003k3a!!<.\u0001\u0005=\bbBA u\u0011\u0005\u0011\u0011 \u0005\b\u0003gSD\u0011IA\u007f\r\u0019\ty.\f\u0001\u0002b\"9\u0011qH\u001f\u0005\u0002\u0005\u0015\bbBAZ{\u0011\u0005\u0013\u0011\u001e\u0004\u0007\u0003{k\u0003!a0\t\u000f\u0005}\u0002\t\"\u0001\u0002D\"9\u00111\u0017!\u0005B\u0005\u001dgABAf[\u0001\ti\rC\u0004\u0002@\r#\t!a6\t\u000f\u0005M6\t\"\u0011\u0002\\\"9!\u0011A\u0017\u0005\n\t\r\u0001b\u0002B\b[\u0011%!\u0011\u0003\u0005\n\u00057i#\u0019!C\u0005\u0005;A\u0001B!\n.A\u0003%!q\u0004\u0005\n\u0005Oi#\u0019!C\u0005\u0005SA\u0001B!\r.A\u0003%!1\u0006\u0005\n\u0005gi\u0013\u0011!C\u0005\u0005k\u00111b\u00149f]\"\u000b7\u000f[*fi*\u0011q\nU\u0001\u000bG>dG.Z2uS>t'BA)S\u0003\u0011)H/\u001b7\u000b\u0005M#\u0016!B:qCJ\\'BA+W\u0003\u0019\t\u0007/Y2iK*\tq+A\u0002pe\u001e\u001c\u0001!\u0006\u0002[}N\u0019\u0001aW1\u0011\u0005q{V\"A/\u000b\u0003y\u000bQa]2bY\u0006L!\u0001Y/\u0003\r\u0005s\u0017PU3g!\t\u0011'N\u0004\u0002dQ:\u0011AmZ\u0007\u0002K*\u0011a\rW\u0001\u0007yI|w\u000e\u001e \n\u0003yK!![/\u0002\u000fA\f7m[1hK&\u00111\u000e\u001c\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Sv\u000bq\"\u001b8ji&\fGnQ1qC\u000eLG/\u001f\t\u00039>L!\u0001]/\u0003\u0007%sG/\u0001\u0006m_\u0006$g)Y2u_J\u0004\"\u0001X:\n\u0005Ql&A\u0002#pk\ndW-\u0001\u0006fm&$WM\\2fIE\u00022a\u001e>}\u001b\u0005A(BA=^\u0003\u001d\u0011XM\u001a7fGRL!a\u001f=\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"! @\r\u0001\u0011Qq\u0010\u0001Q\u0001\u0002\u0003\u0015\r!!\u0001\u0003\u0003Q\u000bB!a\u0001\u0002\nA\u0019A,!\u0002\n\u0007\u0005\u001dQLA\u0004O_RD\u0017N\\4\u0011\u0007q\u000bY!C\u0002\u0002\u000eu\u00131!\u00118zQ-q\u0018\u0011CA\f\u0003C\tY#!\u000e\u0011\u0007q\u000b\u0019\"C\u0002\u0002\u0016u\u00131b\u001d9fG&\fG.\u001b>fIFJ1%!\u0007\u0002\u001c\u0005}\u0011Q\u0004\b\u00049\u0006m\u0011bAA\u000f;\u0006!Aj\u001c8hc\u0011!3m\u001a02\u0013\r\n\u0019#!\n\u0002*\u0005\u001dbb\u0001/\u0002&%\u0019\u0011qE/\u0002\u0007%sG/\r\u0003%G\u001et\u0016'C\u0012\u0002.\u0005=\u00121GA\u0019\u001d\ra\u0016qF\u0005\u0004\u0003ci\u0016A\u0002#pk\ndW-\r\u0003%G\u001et\u0016'C\u0012\u00028\u0005e\u0012QHA\u001e\u001d\ra\u0016\u0011H\u0005\u0004\u0003wi\u0016!\u0002$m_\u0006$\u0018\u0007\u0002\u0013dOz\u000ba\u0001P5oSRtDCBA\"\u0003\u0017\ni\u0005\u0006\u0003\u0002F\u0005%\u0003\u0003BA$\u0001ql\u0011A\u0014\u0005\u0006k\u0012\u0001\u001dA\u001e\u0005\u0006[\u0012\u0001\rA\u001c\u0005\u0006c\u0012\u0001\rA\u001d\u000b\u0005\u0003#\n9\u0006\u0006\u0003\u0002F\u0005M\u0003\u0002CA+\u000b\u0005\u0005\t9\u0001<\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007C\u0003n\u000b\u0001\u0007a\u000e\u0006\u0002\u0002\\Q!\u0011QIA/\u0011!\tyFBA\u0001\u0002\b1\u0018AC3wS\u0012,gnY3%g\u00051\u0001.Y:iKJ,\"!!\u001a\u0011\t\u0005\u001dt\u0007 \b\u0004\u0003\u000fb\u0013aC(qK:D\u0015m\u001d5TKR\u00042!a\u0012.'\u0011i3,a\u001c\u0011\t\u0005E\u00141P\u0007\u0003\u0003gRA!!\u001e\u0002x\u0005\u0011\u0011n\u001c\u0006\u0003\u0003s\nAA[1wC&\u00191.a\u001d\u0015\u0005\u0005-\u0014\u0001D'B1~\u001b\u0015\tU!D\u0013RKV#\u00018\u0002\u001b5\u000b\u0005lX\"B!\u0006\u001b\u0015\nV-!\u0003-IeJV!M\u0013\u0012{\u0006kT*\u0002\u0019%se+\u0011'J\t~\u0003vj\u0015\u0011\u0002#9{e*\u0012-J'R+ejQ#`\u001b\u0006\u001b6*\u0001\nO\u001f:+\u0005,S*U\u000b:\u001bUiX'B'.\u0003\u0013!\u0004)P'&#\u0016j\u0014(`\u001b\u0006\u001b6*\u0001\bQ\u001fNKE+S(O?6\u000b5k\u0013\u0011\u0003\r!\u000b7\u000f[3s+\u0011\t)*a(\u0014\u0007]Z\u0016\r\u0006\u0002\u0002\u001aB)\u00111T\u001c\u0002\u001e6\tQ\u0006E\u0002~\u0003?#!b`\u001c!\u0002\u0003\u0005)\u0019AA\u0001Q1\ty*!\u0005\u0002$\u0006\u001d\u00161VAXc%\u0019\u0013\u0011DA\u000e\u0003K\u000bi\"\r\u0003%G\u001et\u0016'C\u0012\u0002$\u0005\u0015\u0012\u0011VA\u0014c\u0011!3m\u001a02\u0013\r\ni#a\f\u0002.\u0006E\u0012\u0007\u0002\u0013dOz\u000b\u0014bIA\u001c\u0003s\t\t,a\u000f2\t\u0011\u001awMX\u0001\u0005Q\u0006\u001c\b\u000eF\u0002o\u0003oCq!!/:\u0001\u0004\ti*A\u0001pS\u00159\u0004iQ\u001f;\u00051!u.\u001e2mK\"\u000b7\u000f[3s'\r\u0001\u0015\u0011\u0019\t\u0005\u00037;$\u000f\u0006\u0002\u0002FB\u0019\u00111\u0014!\u0015\u00079\fI\r\u0003\u0004\u0002:\n\u0003\rA\u001d\u0002\f\r2|\u0017\r\u001e%bg\",'oE\u0002D\u0003\u001f\u0004R!a'8\u0003#\u00042\u0001XAj\u0013\r\t).\u0018\u0002\u0006\r2|\u0017\r\u001e\u000b\u0003\u00033\u00042!a'D)\rq\u0017Q\u001c\u0005\b\u0003s+\u0005\u0019AAi\u0005%Ie\u000e\u001e%bg\",'oE\u0002>\u0003G\u0004B!a'8]R\u0011\u0011q\u001d\t\u0004\u00037kDc\u00018\u0002l\"1\u0011\u0011X A\u00029\u0014!\u0002T8oO\"\u000b7\u000f[3s'\rQ\u0014\u0011\u001f\t\u0006\u00037;\u00141\u001f\t\u00049\u0006U\u0018bAA|;\n!Aj\u001c8h)\t\tY\u0010E\u0002\u0002\u001cj\"2A\\A\u0000\u0011\u001d\tI\f\u0010a\u0001\u0003g\fQa\u001a:poF\"BA!\u0002\u0003\fA\u0019ALa\u0002\n\u0007\t%QL\u0001\u0003V]&$\bB\u0002B\u0007\r\u0002\u0007a.A\u0004oK^\u001c\u0016N_3\u0002\u000b5|g/Z\u0019\u0015\r\t\u0015!1\u0003B\f\u0011\u0019\u0011)b\u0012a\u0001]\u00061q\u000e\u001c3Q_NDaA!\u0007H\u0001\u0004q\u0017A\u00028foB{7/\u0001\u0003he><XC\u0001B\u0010!\u0019a&\u0011\u00058\u0003\u0006%\u0019!1E/\u0003\u0013\u0019+hn\u0019;j_:\f\u0014!B4s_^\u0004\u0013\u0001B7pm\u0016,\"Aa\u000b\u0011\u000fq\u0013iC\u001c8\u0003\u0006%\u0019!qF/\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0014!B7pm\u0016\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\u001c!\u0011\u0011IDa\u0010\u000e\u0005\tm\"\u0002\u0002B\u001f\u0003o\nA\u0001\\1oO&!!\u0011\tB\u001e\u0005\u0019y%M[3di\u00069\u0001.Y:iKJ\u0004\u0013!C0dCB\f7-\u001b;z\u00035y6-\u00199bG&$\u0018p\u0018\u0013fcR!!Q\u0001B&\u0011!\u0011iECA\u0001\u0002\u0004q\u0017a\u0001=%c\u0005QqlY1qC\u000eLG/\u001f\u0011\u0002\u000b}k\u0017m]6\u0002\u0013}k\u0017m]6`I\u0015\fH\u0003\u0002B\u0003\u0005/B\u0001B!\u0014\u000e\u0003\u0003\u0005\rA\\\u0001\u0007?6\f7o\u001b\u0011\u0002\u000b}\u001b\u0018N_3\u0002\u0013}\u001b\u0018N_3`I\u0015\fH\u0003\u0002B\u0003\u0005CB\u0001B!\u0014\u0011\u0003\u0003\u0005\rA\\\u0001\u0007?NL'0\u001a\u0011\u0002\u001d};'o\\<UQJ,7\u000f[8mI\u0006\u0011rl\u001a:poRC'/Z:i_2$w\fJ3r)\u0011\u0011)Aa\u001b\t\u0011\t53#!AA\u00029\fqbX4s_^$\u0006N]3tQ>dG\rI\u0001\b?\nLGo]3u+\t\u0011\u0019\b\u0005\u0003\u0002H\tU\u0014b\u0001B<\u001d\n1!)\u001b;TKR\f1b\u00182jiN,Go\u0018\u0013fcR!!Q\u0001B?\u0011%\u0011iEFA\u0001\u0002\u0004\u0011\u0019(\u0001\u0005`E&$8/\u001a;!\u0003%9W\r\u001e\"jiN+G/A\u0003`I\u0006$\u0018-\u0006\u0002\u0003\bB!AL!#}\u0013\r\u0011Y)\u0018\u0002\u0006\u0003J\u0014\u0018-_\u0001\n?\u0012\fG/Y0%KF$BA!\u0002\u0003\u0012\"I!Q\n\u000e\u0002\u0002\u0003\u0007!qQ\u0001\u0007?\u0012\fG/\u0019\u0011\u0002\tML'0Z\u0001\tG\u0006\u0004\u0018mY5us\u0006A1m\u001c8uC&t7\u000f\u0006\u0003\u0003\u001e\n\r\u0006c\u0001/\u0003 &\u0019!\u0011U/\u0003\u000f\t{w\u000e\\3b]\"1!Q\u0015\u0010A\u0002q\f\u0011a[\u0001\u0004C\u0012$G\u0003\u0002B\u0003\u0005WCaA!* \u0001\u0004a\u0018!B;oS>tG\u0003BA#\u0005cCqAa-!\u0001\u0004\t)%A\u0003pi\",'/\u0001\blKf,\u00050[:ug\u0006#\bk\\:\u0015\r\tu%\u0011\u0018B^\u0011\u0019\u0011)+\ta\u0001y\"1!QX\u0011A\u00029\f1\u0001]8t\u0003A\tG\rZ,ji\"|W\u000f\u001e*fg&TX\rF\u0002o\u0005\u0007DaA!*#\u0001\u0004a\u0018A\u0004:fQ\u0006\u001c\b.\u00134OK\u0016$W\r\u001a\u000b\t\u0005\u000b\u0011IMa3\u0003P\"1!QU\u0012A\u0002qDqA!4$\u0001\u0004\u0011y\"\u0001\u0007bY2|7-\u0019;f\rVt7\rC\u0004\u0003R\u000e\u0002\rAa\u000b\u0002\u00115|g/\u001a$v]\u000e\faaZ3u!>\u001cHc\u00018\u0003X\"1!Q\u0015\u0013A\u0002q\f\u0001bZ3u-\u0006dW/\u001a\u000b\u0004y\nu\u0007B\u0002B_K\u0001\u0007a.\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\u0011\u0019\u000f\u0005\u0003c\u0005Kd\u0018b\u0001BtY\nA\u0011\n^3sCR|'/\u0001\u0007hKR4\u0016\r\\;f'\u00064W\rF\u0002}\u0005[DaA!0(\u0001\u0004q\u0017a\u00028fqR\u0004vn\u001d\u000b\u0004]\nM\bB\u0002B{Q\u0001\u0007a.A\u0004ge>l\u0007k\\:\u0002\rI,\u0007.Y:i)!\u0011)Aa?\u0003~\n}\bB\u0002BSS\u0001\u0007A\u0010C\u0004\u0003N&\u0002\rAa\b\t\u000f\tE\u0017\u00061\u0001\u0003,\u0005A\u0001.Y:iG>$W\rF\u0002o\u0007\u000bAaaa\u0002+\u0001\u0004q\u0017!\u00015\u0002\u00199,\u0007\u0010\u001e)po\u0016\u0014xJ\u001a\u001a\u0015\u00079\u001ci\u0001\u0003\u0004\u0004\u0010-\u0002\rA\\\u0001\u0002]\"\u001a\u0001aa\u0005\u0011\t\rU11D\u0007\u0003\u0007/Q1a!\u0007S\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0007;\u00199BA\u0004Qe&4\u0018\r^3"
)
public class OpenHashSet implements Serializable {
   public final double org$apache$spark$util$collection$OpenHashSet$$loadFactor;
   public final ClassTag org$apache$spark$util$collection$OpenHashSet$$evidence$1;
   public final Hasher hasher;
   public int org$apache$spark$util$collection$OpenHashSet$$_capacity;
   public int org$apache$spark$util$collection$OpenHashSet$$_mask;
   public int org$apache$spark$util$collection$OpenHashSet$$_size;
   public int org$apache$spark$util$collection$OpenHashSet$$_growThreshold;
   public BitSet org$apache$spark$util$collection$OpenHashSet$$_bitset;
   public Object _data;

   public static int POSITION_MASK() {
      return OpenHashSet$.MODULE$.POSITION_MASK();
   }

   public static int NONEXISTENCE_MASK() {
      return OpenHashSet$.MODULE$.NONEXISTENCE_MASK();
   }

   public static int INVALID_POS() {
      return OpenHashSet$.MODULE$.INVALID_POS();
   }

   public static int MAX_CAPACITY() {
      return OpenHashSet$.MODULE$.MAX_CAPACITY();
   }

   public Hasher hasher() {
      return this.hasher;
   }

   public int _capacity() {
      return this.org$apache$spark$util$collection$OpenHashSet$$_capacity;
   }

   public void _capacity_$eq(final int x$1) {
      this.org$apache$spark$util$collection$OpenHashSet$$_capacity = x$1;
   }

   public int _mask() {
      return this.org$apache$spark$util$collection$OpenHashSet$$_mask;
   }

   public void _mask_$eq(final int x$1) {
      this.org$apache$spark$util$collection$OpenHashSet$$_mask = x$1;
   }

   public int _size() {
      return this.org$apache$spark$util$collection$OpenHashSet$$_size;
   }

   public void _size_$eq(final int x$1) {
      this.org$apache$spark$util$collection$OpenHashSet$$_size = x$1;
   }

   public int _growThreshold() {
      return this.org$apache$spark$util$collection$OpenHashSet$$_growThreshold;
   }

   public void _growThreshold_$eq(final int x$1) {
      this.org$apache$spark$util$collection$OpenHashSet$$_growThreshold = x$1;
   }

   public BitSet _bitset() {
      return this.org$apache$spark$util$collection$OpenHashSet$$_bitset;
   }

   public void _bitset_$eq(final BitSet x$1) {
      this.org$apache$spark$util$collection$OpenHashSet$$_bitset = x$1;
   }

   public BitSet getBitSet() {
      return this._bitset();
   }

   public Object _data() {
      return this._data;
   }

   public void _data_$eq(final Object x$1) {
      this._data = x$1;
   }

   public int size() {
      return this._size();
   }

   public int capacity() {
      return this._capacity();
   }

   public boolean contains(final Object k) {
      return this.getPos(k) != OpenHashSet$.MODULE$.INVALID_POS();
   }

   public void add(final Object k) {
      this.addWithoutResize(k);
      this.rehashIfNeeded(k, OpenHashSet$.MODULE$.org$apache$spark$util$collection$OpenHashSet$$grow(), OpenHashSet$.MODULE$.org$apache$spark$util$collection$OpenHashSet$$move());
   }

   public OpenHashSet union(final OpenHashSet other) {
      Iterator iterator = other.iterator();

      while(iterator.hasNext()) {
         this.add(iterator.next());
      }

      return this;
   }

   public boolean keyExistsAtPos(final Object k, final int pos) {
      return .MODULE$.array_apply(this._data(), pos).equals(k);
   }

   public int addWithoutResize(final Object k) {
      int pos = this.org$apache$spark$util$collection$OpenHashSet$$hashcode(this.hasher().hash(k)) & this._mask();

      for(int delta = 1; this._bitset().get(pos); ++delta) {
         if (this.keyExistsAtPos(k, pos)) {
            return pos;
         }

         pos = pos + delta & this._mask();
      }

      .MODULE$.array_update(this._data(), pos, k);
      this._bitset().set(pos);
      this._size_$eq(this._size() + 1);
      return pos | OpenHashSet$.MODULE$.NONEXISTENCE_MASK();
   }

   public void rehashIfNeeded(final Object k, final Function1 allocateFunc, final Function2 moveFunc) {
      if (this._size() > this._growThreshold()) {
         this.rehash(k, allocateFunc, moveFunc);
      }
   }

   public int getPos(final Object k) {
      int pos = this.org$apache$spark$util$collection$OpenHashSet$$hashcode(this.hasher().hash(k)) & this._mask();

      for(int delta = 1; this._bitset().get(pos); ++delta) {
         if (this.keyExistsAtPos(k, pos)) {
            return pos;
         }

         pos = pos + delta & this._mask();
      }

      return OpenHashSet$.MODULE$.INVALID_POS();
   }

   public Object getValue(final int pos) {
      return .MODULE$.array_apply(this._data(), pos);
   }

   public Iterator iterator() {
      return new Iterator() {
         private int pos;
         // $FF: synthetic field
         private final OpenHashSet $outer;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private int pos() {
            return this.pos;
         }

         private void pos_$eq(final int x$1) {
            this.pos = x$1;
         }

         public boolean hasNext() {
            return this.pos() != OpenHashSet$.MODULE$.INVALID_POS();
         }

         public Object next() {
            Object tmp = this.$outer.getValue(this.pos());
            this.pos_$eq(this.$outer.nextPos(this.pos() + 1));
            return tmp;
         }

         public {
            if (OpenHashSet.this == null) {
               throw null;
            } else {
               this.$outer = OpenHashSet.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = OpenHashSet.this.nextPos(0);
            }
         }
      };
   }

   public Object getValueSafe(final int pos) {
      scala.Predef..MODULE$.assert(this._bitset().get(pos));
      return .MODULE$.array_apply(this._data(), pos);
   }

   public int nextPos(final int fromPos) {
      return this._bitset().nextSetBit(fromPos);
   }

   public void rehash(final Object k, final Function1 allocateFunc, final Function2 moveFunc) {
      int newCapacity = this._capacity() * 2;
      scala.Predef..MODULE$.require(newCapacity > 0 && newCapacity <= OpenHashSet$.MODULE$.MAX_CAPACITY(), () -> {
         double var10000 = this.org$apache$spark$util$collection$OpenHashSet$$loadFactor;
         return "Can't contain more than " + (int)(var10000 * (double)OpenHashSet$.MODULE$.MAX_CAPACITY()) + " elements";
      });
      allocateFunc.apply$mcVI$sp(newCapacity);
      BitSet newBitset = new BitSet(newCapacity);
      Object newData = this.org$apache$spark$util$collection$OpenHashSet$$evidence$1.newArray(newCapacity);
      int newMask = newCapacity - 1;

      for(int oldPos = 0; oldPos < this.capacity(); ++oldPos) {
         if (this._bitset().get(oldPos)) {
            Object key = .MODULE$.array_apply(this._data(), oldPos);
            int newPos = this.org$apache$spark$util$collection$OpenHashSet$$hashcode(this.hasher().hash(key)) & newMask;
            int i = 1;
            boolean keepGoing = true;

            while(keepGoing) {
               if (!newBitset.get(newPos)) {
                  .MODULE$.array_update(newData, newPos, key);
                  newBitset.set(newPos);
                  moveFunc.apply$mcVII$sp(oldPos, newPos);
                  keepGoing = false;
               } else {
                  newPos = newPos + i & newMask;
                  ++i;
               }
            }
         }
      }

      this._bitset_$eq(newBitset);
      this._data_$eq(newData);
      this._capacity_$eq(newCapacity);
      this._mask_$eq(newMask);
      this._growThreshold_$eq((int)(this.org$apache$spark$util$collection$OpenHashSet$$loadFactor * (double)newCapacity));
   }

   public int org$apache$spark$util$collection$OpenHashSet$$hashcode(final int h) {
      return Hashing.murmur3_32_fixed().hashInt(h).asInt();
   }

   public int org$apache$spark$util$collection$OpenHashSet$$nextPowerOf2(final int n) {
      if (n == 0) {
         return 1;
      } else {
         int highBit = Integer.highestOneBit(n);
         return highBit == n ? n : highBit << 1;
      }
   }

   public Hasher hasher$mcD$sp() {
      return this.hasher();
   }

   public Hasher hasher$mcF$sp() {
      return this.hasher();
   }

   public Hasher hasher$mcI$sp() {
      return this.hasher();
   }

   public Hasher hasher$mcJ$sp() {
      return this.hasher();
   }

   public double[] _data$mcD$sp() {
      return (double[])this._data();
   }

   public float[] _data$mcF$sp() {
      return (float[])this._data();
   }

   public int[] _data$mcI$sp() {
      return (int[])this._data();
   }

   public long[] _data$mcJ$sp() {
      return (long[])this._data();
   }

   public void _data$mcD$sp_$eq(final double[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcF$sp_$eq(final float[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcI$sp_$eq(final int[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcJ$sp_$eq(final long[] x$1) {
      this._data_$eq(x$1);
   }

   public boolean contains$mcD$sp(final double k) {
      return this.contains(BoxesRunTime.boxToDouble(k));
   }

   public boolean contains$mcF$sp(final float k) {
      return this.contains(BoxesRunTime.boxToFloat(k));
   }

   public boolean contains$mcI$sp(final int k) {
      return this.contains(BoxesRunTime.boxToInteger(k));
   }

   public boolean contains$mcJ$sp(final long k) {
      return this.contains(BoxesRunTime.boxToLong(k));
   }

   public void add$mcD$sp(final double k) {
      this.add(BoxesRunTime.boxToDouble(k));
   }

   public void add$mcF$sp(final float k) {
      this.add(BoxesRunTime.boxToFloat(k));
   }

   public void add$mcI$sp(final int k) {
      this.add(BoxesRunTime.boxToInteger(k));
   }

   public void add$mcJ$sp(final long k) {
      this.add(BoxesRunTime.boxToLong(k));
   }

   public OpenHashSet union$mcD$sp(final OpenHashSet other) {
      return this.union(other);
   }

   public OpenHashSet union$mcF$sp(final OpenHashSet other) {
      return this.union(other);
   }

   public OpenHashSet union$mcI$sp(final OpenHashSet other) {
      return this.union(other);
   }

   public OpenHashSet union$mcJ$sp(final OpenHashSet other) {
      return this.union(other);
   }

   public boolean keyExistsAtPos$mcD$sp(final double k, final int pos) {
      return this.keyExistsAtPos(BoxesRunTime.boxToDouble(k), pos);
   }

   public boolean keyExistsAtPos$mcF$sp(final float k, final int pos) {
      return this.keyExistsAtPos(BoxesRunTime.boxToFloat(k), pos);
   }

   public boolean keyExistsAtPos$mcI$sp(final int k, final int pos) {
      return this.keyExistsAtPos(BoxesRunTime.boxToInteger(k), pos);
   }

   public boolean keyExistsAtPos$mcJ$sp(final long k, final int pos) {
      return this.keyExistsAtPos(BoxesRunTime.boxToLong(k), pos);
   }

   public int addWithoutResize$mcD$sp(final double k) {
      return this.addWithoutResize(BoxesRunTime.boxToDouble(k));
   }

   public int addWithoutResize$mcF$sp(final float k) {
      return this.addWithoutResize(BoxesRunTime.boxToFloat(k));
   }

   public int addWithoutResize$mcI$sp(final int k) {
      return this.addWithoutResize(BoxesRunTime.boxToInteger(k));
   }

   public int addWithoutResize$mcJ$sp(final long k) {
      return this.addWithoutResize(BoxesRunTime.boxToLong(k));
   }

   public void rehashIfNeeded$mcD$sp(final double k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehashIfNeeded(BoxesRunTime.boxToDouble(k), allocateFunc, moveFunc);
   }

   public void rehashIfNeeded$mcF$sp(final float k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehashIfNeeded(BoxesRunTime.boxToFloat(k), allocateFunc, moveFunc);
   }

   public void rehashIfNeeded$mcI$sp(final int k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehashIfNeeded(BoxesRunTime.boxToInteger(k), allocateFunc, moveFunc);
   }

   public void rehashIfNeeded$mcJ$sp(final long k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehashIfNeeded(BoxesRunTime.boxToLong(k), allocateFunc, moveFunc);
   }

   public int getPos$mcD$sp(final double k) {
      return this.getPos(BoxesRunTime.boxToDouble(k));
   }

   public int getPos$mcF$sp(final float k) {
      return this.getPos(BoxesRunTime.boxToFloat(k));
   }

   public int getPos$mcI$sp(final int k) {
      return this.getPos(BoxesRunTime.boxToInteger(k));
   }

   public int getPos$mcJ$sp(final long k) {
      return this.getPos(BoxesRunTime.boxToLong(k));
   }

   public double getValue$mcD$sp(final int pos) {
      return BoxesRunTime.unboxToDouble(this.getValue(pos));
   }

   public float getValue$mcF$sp(final int pos) {
      return BoxesRunTime.unboxToFloat(this.getValue(pos));
   }

   public int getValue$mcI$sp(final int pos) {
      return BoxesRunTime.unboxToInt(this.getValue(pos));
   }

   public long getValue$mcJ$sp(final int pos) {
      return BoxesRunTime.unboxToLong(this.getValue(pos));
   }

   public double getValueSafe$mcD$sp(final int pos) {
      return BoxesRunTime.unboxToDouble(this.getValueSafe(pos));
   }

   public float getValueSafe$mcF$sp(final int pos) {
      return BoxesRunTime.unboxToFloat(this.getValueSafe(pos));
   }

   public int getValueSafe$mcI$sp(final int pos) {
      return BoxesRunTime.unboxToInt(this.getValueSafe(pos));
   }

   public long getValueSafe$mcJ$sp(final int pos) {
      return BoxesRunTime.unboxToLong(this.getValueSafe(pos));
   }

   public void rehash$mcD$sp(final double k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehash(BoxesRunTime.boxToDouble(k), allocateFunc, moveFunc);
   }

   public void rehash$mcF$sp(final float k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehash(BoxesRunTime.boxToFloat(k), allocateFunc, moveFunc);
   }

   public void rehash$mcI$sp(final int k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehash(BoxesRunTime.boxToInteger(k), allocateFunc, moveFunc);
   }

   public void rehash$mcJ$sp(final long k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehash(BoxesRunTime.boxToLong(k), allocateFunc, moveFunc);
   }

   public boolean specInstance$() {
      return false;
   }

   public OpenHashSet(final int initialCapacity, final double loadFactor, final ClassTag evidence$1) {
      this.org$apache$spark$util$collection$OpenHashSet$$loadFactor = loadFactor;
      this.org$apache$spark$util$collection$OpenHashSet$$evidence$1 = evidence$1;
      if (!this.specInstance$()) {
         Object var14;
         label78: {
            label84: {
               scala.Predef..MODULE$.require(initialCapacity <= OpenHashSet$.MODULE$.MAX_CAPACITY(), () -> "Can't make capacity bigger than " + OpenHashSet$.MODULE$.MAX_CAPACITY() + " elements");
               scala.Predef..MODULE$.require(initialCapacity >= 0, () -> "Invalid initial capacity");
               scala.Predef..MODULE$.require(loadFactor < (double)1.0F, () -> "Load factor must be less than 1.0");
               scala.Predef..MODULE$.require(loadFactor > (double)0.0F, () -> "Load factor must be greater than 0.0");
               ClassTag var6 = scala.reflect.package..MODULE$.classTag(evidence$1);
               ManifestFactory.LongManifest var10001 = scala.reflect.ClassTag..MODULE$.Long();
               if (var10001 == null) {
                  if (var6 == null) {
                     break label84;
                  }
               } else if (var10001.equals(var6)) {
                  break label84;
               }

               label85: {
                  ManifestFactory.IntManifest var11 = scala.reflect.ClassTag..MODULE$.Int();
                  if (var11 == null) {
                     if (var6 == null) {
                        break label85;
                     }
                  } else if (var11.equals(var6)) {
                     break label85;
                  }

                  label86: {
                     ManifestFactory.DoubleManifest var12 = scala.reflect.ClassTag..MODULE$.Double();
                     if (var12 == null) {
                        if (var6 == null) {
                           break label86;
                        }
                     } else if (var12.equals(var6)) {
                        break label86;
                     }

                     label57: {
                        ManifestFactory.FloatManifest var13 = scala.reflect.ClassTag..MODULE$.Float();
                        if (var13 == null) {
                           if (var6 == null) {
                              break label57;
                           }
                        } else if (var13.equals(var6)) {
                           break label57;
                        }

                        var14 = new Hasher();
                        break label78;
                     }

                     var14 = new FloatHasher();
                     break label78;
                  }

                  var14 = new DoubleHasher();
                  break label78;
               }

               var14 = new IntHasher();
               break label78;
            }

            var14 = new LongHasher();
         }

         this.hasher = (Hasher)var14;
         this.org$apache$spark$util$collection$OpenHashSet$$_capacity = this.org$apache$spark$util$collection$OpenHashSet$$nextPowerOf2(initialCapacity);
         this.org$apache$spark$util$collection$OpenHashSet$$_mask = this._capacity() - 1;
         this.org$apache$spark$util$collection$OpenHashSet$$_size = 0;
         this.org$apache$spark$util$collection$OpenHashSet$$_growThreshold = (int)(loadFactor * (double)this._capacity());
         this.org$apache$spark$util$collection$OpenHashSet$$_bitset = new BitSet(this._capacity());
         this._data_$eq(evidence$1.newArray(this._capacity()));
      }

   }

   public OpenHashSet(final int initialCapacity, final ClassTag evidence$2) {
      this(initialCapacity, 0.7, evidence$2);
   }

   public OpenHashSet(final ClassTag evidence$3) {
      this(64, evidence$3);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Hasher implements Serializable {
      public int hash(final Object o) {
         return o.hashCode();
      }

      public int hash$mcD$sp(final double o) {
         return this.hash(BoxesRunTime.boxToDouble(o));
      }

      public int hash$mcF$sp(final float o) {
         return this.hash(BoxesRunTime.boxToFloat(o));
      }

      public int hash$mcI$sp(final int o) {
         return this.hash(BoxesRunTime.boxToInteger(o));
      }

      public int hash$mcJ$sp(final long o) {
         return this.hash(BoxesRunTime.boxToLong(o));
      }
   }

   public static class LongHasher extends OpenHashSet$Hasher$mcJ$sp {
      public int hash(final long o) {
         return this.hash$mcJ$sp(o);
      }

      public int hash$mcJ$sp(final long o) {
         return (int)(o ^ o >>> 32);
      }
   }

   public static class IntHasher extends OpenHashSet$Hasher$mcI$sp {
      public int hash(final int o) {
         return this.hash$mcI$sp(o);
      }

      public int hash$mcI$sp(final int o) {
         return o;
      }
   }

   public static class DoubleHasher extends OpenHashSet$Hasher$mcD$sp {
      public int hash(final double o) {
         return this.hash$mcD$sp(o);
      }

      public int hash$mcD$sp(final double o) {
         long bits = Double.doubleToLongBits(o);
         return (int)(bits ^ bits >>> 32);
      }
   }

   public static class FloatHasher extends OpenHashSet$Hasher$mcF$sp {
      public int hash(final float o) {
         return this.hash$mcF$sp(o);
      }

      public int hash$mcF$sp(final float o) {
         return Float.floatToIntBits(o);
      }
   }
}
