package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.LazyZip2;
import scala.collection.MapFactory;
import scala.collection.MapFactoryDefaults;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r\rc\u0001\u0002 @\u0005\u0019C\u0011B\u001b\u0001\u0003\u0006\u0004%\taP6\t\u0011=\u0004!\u0011!Q\u0001\n1D\u0011\u0002\u001d\u0001\u0003\u0006\u0004%\taP9\t\u0011m\u0004!\u0011!Q\u0001\nID\u0001\u0002 \u0001\u0003\u0002\u0003\u0006I\u0001\u001f\u0005\u0006{\u0002!IA \u0005\t\u0003\u000b\u0001\u0001\u0015\"\u0015\u0002\b!9Q\u0010\u0001C\u0001\u007f\u0005}\u0001\"CA\u0014\u0001\t\u0007I\u0011IA\u0015\u0011\u001d\tY\u0003\u0001Q\u0001\naDq!!\f\u0001\t\u0003\nI\u0003C\u0004\u00020\u0001!\t%!\r\t\u000f\u0005e\u0002\u0001\"\u0001\u0002<!9\u0011\u0011\u000b\u0001\u0005B\u0005M\u0003bBA4\u0001\u0011\u0005\u0013\u0011\u000e\u0005\b\u0003k\u0002A\u0011AA<\u0011\u001d\t\t\t\u0001C\u0005\u0003\u0007Cq!!'\u0001\t\u0003\tY\nC\u0004\u0002&\u0002!\t%a*\t\u000f\u0005-\u0007\u0001\"\u0011\u0002N\"9\u0011Q\u001d\u0001\u0005B\u0005\u001d\bbBA\u0000\u0001\u0011\u0005!\u0011\u0001\u0005\b\u0005\u000b\u0001A\u0011\tB\u0004\u0011\u001d\u0011y\u0001\u0001C!\u0005#AqA!\u0006\u0001\t\u0003\u00129\u0002C\u0004\u0003\u001a\u0001!\tEa\u0006\t\u000f\tm\u0001\u0001\"\u0011\u0003\u001e!9!\u0011\u0005\u0001\u0005B\t\r\u0002b\u0002B\u0013\u0001\u0011\u0005#1\u0005\u0005\b\u0005O\u0001A\u0011\tB\u0015\u0011\u001d\u0011Y\u0003\u0001C!\u0005[9qA!\r@\u0011\u0003\u0011\u0019D\u0002\u0004?\u007f!\u0005!Q\u0007\u0005\u0007{\u0006\"\tA!\u0010\u0007\u000f\t}\u0012EQ\u0011\u0003B!Q!1L\u0012\u0003\u0016\u0004%\t!!\u000b\t\u0013\tu3E!E!\u0002\u0013A\bBB?$\t\u0003\u0011y\u0006C\u0005\u0003h\r\n\t\u0011\"\u0001\u0003j!I!QN\u0012\u0012\u0002\u0013\u0005!q\u000e\u0005\n\u0005\u0003\u001b\u0013\u0011!C!\u0005\u0007C\u0011Ba%$\u0003\u0003%\t!!\u000b\t\u0013\tU5%!A\u0005\u0002\t]\u0005\"\u0003BOG\u0005\u0005I\u0011\tBP\u0011%\u0011\u0019kIA\u0001\n\u0003\u0011)\u000bC\u0005\u0003*\u000e\n\t\u0011\"\u0011\u0003,\"I!qV\u0012\u0002\u0002\u0013\u0005#\u0011\u0017\u0005\n\u0005g\u001b\u0013\u0011!C!\u0005kC\u0011Ba.$\u0003\u0003%\tE!/\b\u0015\tu\u0016%!A\t\u0002\u0005\u0012yL\u0002\u0006\u0003@\u0005\n\t\u0011#\u0001\"\u0005\u0003Da!`\u001a\u0005\u0002\te\u0007\"\u0003BZg\u0005\u0005IQ\tB[\u0011%\u0011YnMA\u0001\n\u0003\u0013i\u000eC\u0005\u0003bN\n\t\u0011\"!\u0003d\"I!1^\u001a\u0002\u0002\u0013%!Q\u001e\u0005\t\u0005k\f\u0003\u0015!\u0004\u0003x\"9!\u0011`\u0011\u0005\u0002\tm\bbBB\u0005C\u0011\u000511\u0002\u0005\b\u0007K\tC\u0011AB\u0014\u0011%\u0011Y/IA\u0001\n\u0013\u0011iOA\u0005WK\u000e$xN]'ba*\u0011\u0001)Q\u0001\nS6lW\u000f^1cY\u0016T!AQ\"\u0002\u0015\r|G\u000e\\3di&|gNC\u0001E\u0003\u0015\u00198-\u00197b\u0007\u0001)2a\u0012(Z'\u0015\u0001\u0001j\u00170d!\u0011I%\n\u0014-\u000e\u0003}J!aS \u0003\u0017\u0005\u00137\u000f\u001e:bGRl\u0015\r\u001d\t\u0003\u001b:c\u0001\u0001B\u0003P\u0001\t\u0007\u0001KA\u0001L#\t\tV\u000b\u0005\u0002S'6\t1)\u0003\u0002U\u0007\n9aj\u001c;iS:<\u0007C\u0001*W\u0013\t96IA\u0002B]f\u0004\"!T-\u0005\ri\u0003AQ1\u0001Q\u0005\u00051\u0006\u0003B%]\u0019bK!!X \u0003\rM+\u0017/T1q!\u0019Iu\f\u0014-bE&\u0011\u0001m\u0010\u0002\u0016'R\u0014\u0018n\u0019;PaRLW.\u001b>fI6\u000b\u0007o\u00149t!\tI\u0005\u0001\u0005\u0003J\u00011C\u0006C\u00023f\u0019b\u000bw-D\u0001B\u0013\t1\u0017I\u0001\nNCB4\u0015m\u0019;pef$UMZ1vYR\u001c\bCA%i\u0013\tIwH\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0003\u00191\u0017.\u001a7egV\tA\u000eE\u0002J[VK!A\\ \u0003\rY+7\r^8s\u0003\u001d1\u0017.\u001a7eg\u0002\n!\"\u001e8eKJd\u00170\u001b8h+\u0005\u0011\b\u0003B%t\u0019VL!\u0001^ \u0003\u00075\u000b\u0007\u000f\u0005\u0003SmbD\u0016BA<D\u0005\u0019!V\u000f\u001d7feA\u0011!+_\u0005\u0003u\u000e\u00131!\u00138u\u0003-)h\u000eZ3sYfLgn\u001a\u0011\u0002\u000f\u0011\u0014x\u000e\u001d9fI\u00061A(\u001b8jiz\"bAY@\u0002\u0002\u0005\r\u0001\"\u00026\u0007\u0001\u0004a\u0007\"\u00029\u0007\u0001\u0004\u0011\b\"\u0002?\u0007\u0001\u0004A\u0018!C2mCN\u001ch*Y7f+\t\tI\u0001\u0005\u0003\u0002\f\u0005ea\u0002BA\u0007\u0003+\u00012!a\u0004D\u001b\t\t\tBC\u0002\u0002\u0014\u0015\u000ba\u0001\u0010:p_Rt\u0014bAA\f\u0007\u00061\u0001K]3eK\u001aLA!a\u0007\u0002\u001e\t11\u000b\u001e:j]\u001eT1!a\u0006D)\u0015\u0011\u0017\u0011EA\u0013\u0011\u0019Q\u0007\u00021\u0001\u0002$A\u0019\u0011*\u001c'\t\u000bAD\u0001\u0019\u0001:\u0002\tML'0Z\u000b\u0002q\u0006)1/\u001b>fA\u0005I1N\\8x]NK'0Z\u0001\bSN,U\u000e\u001d;z+\t\t\u0019\u0004E\u0002S\u0003kI1!a\u000eD\u0005\u001d\u0011un\u001c7fC:\fq!\u001e9eCR,G-\u0006\u0003\u0002>\u0005\rCCBA \u0003\u0013\ni\u0005E\u0003J\u00011\u000b\t\u0005E\u0002N\u0003\u0007\"q!!\u0012\u000e\u0005\u0004\t9E\u0001\u0002WcE\u0011\u0001,\u0016\u0005\u0007\u0003\u0017j\u0001\u0019\u0001'\u0002\u0007-,\u0017\u0010C\u0004\u0002P5\u0001\r!!\u0011\u0002\u000bY\fG.^3\u0002\u0017]LG\u000f\u001b#fM\u0006,H\u000e^\u000b\u0005\u0003+\nY\u0006\u0006\u0003\u0002X\u0005u\u0003#B%t\u0019\u0006e\u0003cA'\u0002\\\u00119\u0011Q\t\bC\u0002\u0005\u001d\u0003bBA0\u001d\u0001\u0007\u0011\u0011M\u0001\u0002IB1!+a\u0019M\u00033J1!!\u001aD\u0005%1UO\\2uS>t\u0017'\u0001\txSRDG)\u001a4bk2$h+\u00197vKV!\u00111NA9)\u0011\ti'a\u001d\u0011\u000b%\u001bH*a\u001c\u0011\u00075\u000b\t\bB\u0004\u0002F=\u0011\r!a\u0012\t\u000f\u0005}s\u00021\u0001\u0002p\u0005\u0019q-\u001a;\u0015\t\u0005e\u0014q\u0010\t\u0005%\u0006m\u0004,C\u0002\u0002~\r\u0013aa\u00149uS>t\u0007BBA&!\u0001\u0007A*\u0001\boKb$h+\u00197jI\u001aKW\r\u001c3\u0015\t\u0005\u0015\u0015q\u0011\t\u0005%ZDH\n\u0003\u0004\u0002\nF\u0001\r\u0001_\u0001\u0005g2|G\u000fK\u0002\u0012\u0003\u001b\u0003B!a$\u0002\u00166\u0011\u0011\u0011\u0013\u0006\u0004\u0003'\u001b\u0015AC1o]>$\u0018\r^5p]&!\u0011qSAI\u0005\u001d!\u0018-\u001b7sK\u000e\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003;\u0003R\u0001ZAP\u0003GK1!!)B\u0005!IE/\u001a:bi>\u0014\b\u0003\u0002*w\u0019b\u000bqa\u001d;faB,'/\u0006\u0003\u0002*\u00065F\u0003BAV\u0003\u0003\u00042!TAW\t\u001d\tyk\u0005b\u0001\u0003c\u0013\u0011aU\t\u0004#\u0006M\u0006\u0007BA[\u0003{\u0003R\u0001ZA\\\u0003wK1!!/B\u0005\u001d\u0019F/\u001a9qKJ\u00042!TA_\t-\ty,!,\u0002\u0002\u0003\u0005)\u0011\u0001)\u0003\u0007}#\u0013\u0007C\u0004\u0002DN\u0001\u001d!!2\u0002\u000bMD\u0017\r]3\u0011\u000f\u0011\f9-a)\u0002,&\u0019\u0011\u0011Z!\u0003\u0019M#X\r\u001d9feNC\u0017\r]3\u0002\u0015-,\u0017p\u0015;faB,'/\u0006\u0003\u0002P\u0006MG\u0003BAi\u0003C\u00042!TAj\t\u001d\ty\u000b\u0006b\u0001\u0003+\f2!UAla\u0011\tI.!8\u0011\u000b\u0011\f9,a7\u0011\u00075\u000bi\u000eB\u0006\u0002`\u0006M\u0017\u0011!A\u0001\u0006\u0003\u0001&aA0%e!9\u00111\u0019\u000bA\u0004\u0005\r\bC\u00023\u0002H2\u000b\t.\u0001\u0007wC2,Xm\u0015;faB,'/\u0006\u0003\u0002j\u00065H\u0003BAv\u0003w\u00042!TAw\t\u001d\ty+\u0006b\u0001\u0003_\f2!UAya\u0011\t\u00190a>\u0011\u000b\u0011\f9,!>\u0011\u00075\u000b9\u0010B\u0006\u0002z\u00065\u0018\u0011!A\u0001\u0006\u0003\u0001&aA0%g!9\u00111Y\u000bA\u0004\u0005u\bC\u00023\u0002Hb\u000bY/A\u0004sK6|g/\u001a3\u0015\u0007\t\u0014\u0019\u0001\u0003\u0004\u0002LY\u0001\r\u0001T\u0001\u000b[\u0006\u0004h)Y2u_JLXC\u0001B\u0005!\u0011!'1B1\n\u0007\t5\u0011I\u0001\u0006NCB4\u0015m\u0019;pef\f\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u0003g\u0011\u0019\u0002\u0003\u0004\u0002La\u0001\r\u0001T\u0001\u0005Q\u0016\fG-\u0006\u0002\u0002$\u0006!A.Y:u\u0003)a\u0017m\u001d;PaRLwN\\\u000b\u0003\u0005?\u0001RAUA>\u0003G\u000bA\u0001^1jYV\t!-\u0001\u0003j]&$\u0018\u0001B6fsN,\"!a\t\u0002\rY\fG.^3t+\t\u0011y\u0003E\u0002JQb\u000b\u0011BV3di>\u0014X*\u00199\u0011\u0005%\u000b3#B\u0011\u00038\t%\u0001c\u0001*\u0003:%\u0019!1H\"\u0003\r\u0005s\u0017PU3g)\t\u0011\u0019DA\u0005U_6\u00147\u000f^8oKN91Ea\u000e\u0003D\t%\u0003c\u0001*\u0003F%\u0019!qI\"\u0003\u000fA\u0013x\u000eZ;diB!!1\nB+\u001d\u0011\u0011iE!\u0015\u000f\t\u0005=!qJ\u0005\u0002\t&\u0019!1K\"\u0002\u000fA\f7m[1hK&!!q\u000bB-\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\r\u0011\u0019fQ\u0001\tI&\u001cH/\u00198dK\u0006IA-[:uC:\u001cW\r\t\u000b\u0005\u0005C\u0012)\u0007E\u0002\u0003d\rj\u0011!\t\u0005\u0007\u000572\u0003\u0019\u0001=\u0002\t\r|\u0007/\u001f\u000b\u0005\u0005C\u0012Y\u0007\u0003\u0005\u0003\\\u001d\u0002\n\u00111\u0001y\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"A!\u001d+\u0007a\u0014\u0019h\u000b\u0002\u0003vA!!q\u000fB?\u001b\t\u0011IH\u0003\u0003\u0003|\u0005E\u0015!C;oG\",7m[3e\u0013\u0011\u0011yH!\u001f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005\u000b\u0003BAa\"\u0003\u00126\u0011!\u0011\u0012\u0006\u0005\u0005\u0017\u0013i)\u0001\u0003mC:<'B\u0001BH\u0003\u0011Q\u0017M^1\n\t\u0005m!\u0011R\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\r)&\u0011\u0014\u0005\t\u00057[\u0013\u0011!a\u0001q\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!)\u0011\t\u0011\fy*V\u0001\tG\u0006tW)];bYR!\u00111\u0007BT\u0011!\u0011Y*LA\u0001\u0002\u0004)\u0016A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BA!\"\u0003.\"A!1\u0014\u0018\u0002\u0002\u0003\u0007\u00010\u0001\u0005iCND7i\u001c3f)\u0005A\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\t\u0015\u0015AB3rk\u0006d7\u000f\u0006\u0003\u00024\tm\u0006\u0002\u0003BNc\u0005\u0005\t\u0019A+\u0002\u0013Q{WNY:u_:,\u0007c\u0001B2gM)1Ga1\u0003PB9!Q\u0019Bfq\n\u0005TB\u0001Bd\u0015\r\u0011ImQ\u0001\beVtG/[7f\u0013\u0011\u0011iMa2\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0003R\n]WB\u0001Bj\u0015\u0011\u0011)N!$\u0002\u0005%|\u0017\u0002\u0002B,\u0005'$\"Aa0\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\t\u0005$q\u001c\u0005\u0007\u000572\u0004\u0019\u0001=\u0002\u000fUt\u0017\r\u001d9msR!!Q\u001dBt!\u0011\u0011\u00161\u0010=\t\u0013\t%x'!AA\u0002\t\u0005\u0014a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u001e\t\u0005\u0005\u000f\u0013\t0\u0003\u0003\u0003t\n%%AB(cU\u0016\u001cG/\u0001\u0005F[B$\u00180T1q!\u0011I\u0005!U)\u0002\u000b\u0015l\u0007\u000f^=\u0016\r\tu81AB\u0004+\t\u0011y\u0010\u0005\u0004J\u0001\r\u00051Q\u0001\t\u0004\u001b\u000e\rA!B(;\u0005\u0004\u0001\u0006cA'\u0004\b\u0011)!L\u000fb\u0001!\u0006!aM]8n+\u0019\u0019iaa\u0005\u0004\u0018Q!1qBB\r!\u0019I\u0005a!\u0005\u0004\u0016A\u0019Qja\u0005\u0005\u000b=[$\u0019\u0001)\u0011\u00075\u001b9\u0002B\u0003[w\t\u0007\u0001\u000bC\u0004\u0004\u001cm\u0002\ra!\b\u0002\u0005%$\b#\u00023\u0004 \r\r\u0012bAB\u0011\u0003\na\u0011\n^3sC\ndWm\u00148dKB1!K^B\t\u0007+\t!B\\3x\u0005VLG\u000eZ3s+\u0019\u0019Ica\u000f\u0004@U\u001111\u0006\t\t\u0007[\u0019\u0019da\u000e\u0004B5\u00111q\u0006\u0006\u0004\u0007c\t\u0015aB7vi\u0006\u0014G.Z\u0005\u0005\u0007k\u0019yCA\u0004Ck&dG-\u001a:\u0011\rI38\u0011HB\u001f!\ri51\b\u0003\u0006\u001fr\u0012\r\u0001\u0015\t\u0004\u001b\u000e}B!\u0002.=\u0005\u0004\u0001\u0006CB%\u0001\u0007s\u0019i\u0004"
)
public final class VectorMap extends AbstractMap implements SeqMap, StrictOptimizedMapOps {
   private final Vector fields;
   private final Map underlying;
   private final int dropped;
   private final int size;

   public static Builder newBuilder() {
      VectorMap$ var10000 = VectorMap$.MODULE$;
      return new VectorMapBuilder();
   }

   public static VectorMap from(final IterableOnce it) {
      return VectorMap$.MODULE$.from(it);
   }

   public MapOps concat(final IterableOnce that) {
      return StrictOptimizedMapOps.concat$(this, that);
   }

   public IterableOps map(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return scala.collection.StrictOptimizedMapOps.collect$(this, pf);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
      return scala.collection.StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
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

   public String stringPrefix() {
      return scala.collection.SeqMap.stringPrefix$(this);
   }

   public Vector fields() {
      return this.fields;
   }

   public Map underlying() {
      return this.underlying;
   }

   public String className() {
      return "VectorMap";
   }

   public int size() {
      return this.size;
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public VectorMap updated(final Object key, final Object value) {
      Option var3 = this.underlying().get(key);
      if (var3 instanceof Some) {
         Tuple2 var4 = (Tuple2)((Some)var3).value();
         if (var4 != null) {
            int slot = var4._1$mcI$sp();
            return new VectorMap(this.fields(), (Map)this.underlying().updated(key, new Tuple2(slot, value)), this.dropped);
         }
      }

      if (None$.MODULE$.equals(var3)) {
         VectorMap var10000 = new VectorMap;
         Vector var10002 = this.fields();
         if (var10002 == null) {
            throw null;
         } else {
            var10000.<init>((Vector)var10002.appended(key), (Map)this.underlying().updated(key, new Tuple2(this.fields().length() + this.dropped, value)), this.dropped);
            return var10000;
         }
      } else {
         throw new MatchError(var3);
      }
   }

   public Map withDefault(final Function1 d) {
      return new Map.WithDefault(this, d);
   }

   public Map withDefaultValue(final Object d) {
      return new Map.WithDefault(this, (x$1) -> d);
   }

   public Option get(final Object key) {
      Option var2 = this.underlying().get(key);
      if (var2 instanceof Some) {
         Tuple2 v = (Tuple2)((Some)var2).value();
         return new Some(v._2());
      } else if (None$.MODULE$.equals(var2)) {
         return None$.MODULE$;
      } else {
         throw new MatchError(var2);
      }
   }

   public Tuple2 scala$collection$immutable$VectorMap$$nextValidField(final int slot) {
      while(true) {
         Vector var10001 = this.fields();
         if (var10001 == null) {
            throw null;
         }

         if (slot >= var10001.length()) {
            return new Tuple2(-1, (Object)null);
         }

         Object var2 = this.fields().apply(slot);
         if (!(var2 instanceof Tombstone)) {
            return new Tuple2(slot, var2);
         }

         int distance = ((Tombstone)var2).distance();
         slot += distance;
      }
   }

   public Iterator iterator() {
      return new AbstractIterator() {
         private final int fieldsLength;
         private int slot;
         private Object key;
         // $FF: synthetic field
         private final VectorMap $outer;

         private void advance() {
            int nextSlot = this.slot + 1;
            if (nextSlot >= this.fieldsLength) {
               this.slot = this.fieldsLength;
               this.key = null;
            } else {
               Tuple2 var2 = this.$outer.scala$collection$immutable$VectorMap$$nextValidField(nextSlot);
               if (var2 != null) {
                  int var3 = var2._1$mcI$sp();
                  if (-1 == var3) {
                     this.slot = this.fieldsLength;
                     this.key = null;
                     return;
                  }
               }

               if (var2 != null) {
                  int s = var2._1$mcI$sp();
                  Object k = var2._2();
                  this.slot = s;
                  this.key = k;
               } else {
                  throw new MatchError((Object)null);
               }
            }
         }

         public boolean hasNext() {
            return this.slot < this.fieldsLength;
         }

         public Tuple2 next() {
            if (!this.hasNext()) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (Tuple2)Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               Tuple2 result = new Tuple2(this.key, ((Tuple2)this.$outer.underlying().apply(this.key))._2());
               this.advance();
               return result;
            }
         }

         public {
            if (VectorMap.this == null) {
               throw null;
            } else {
               this.$outer = VectorMap.this;
               this.fieldsLength = VectorMap.this.fields().length();
               this.slot = -1;
               this.key = null;
               this.advance();
            }
         }
      };
   }

   public Stepper stepper(final StepperShape shape) {
      return IterableOnce.stepper$(this, shape);
   }

   public Stepper keyStepper(final StepperShape shape) {
      return scala.collection.MapOps.keyStepper$(this, shape);
   }

   public Stepper valueStepper(final StepperShape shape) {
      return scala.collection.MapOps.valueStepper$(this, shape);
   }

   public VectorMap removed(final Object key) {
      if (this.isEmpty()) {
         return (VectorMap)MapFactoryDefaults.empty$(this);
      } else {
         Vector fs = this.fields();
         if (fs == null) {
            throw null;
         } else {
            int sz = fs.length();
            boolean var4 = false;
            Some var5 = null;
            Option var6 = this.underlying().get(key);
            if (var6 instanceof Some) {
               var4 = true;
               var5 = (Some)var6;
               if (this.size() == 1) {
                  return (VectorMap)MapFactoryDefaults.empty$(this);
               }
            }

            if (var4) {
               Tuple2 var7 = (Tuple2)var5.value();
               if (var7 != null) {
                  int s = var7._1$mcI$sp() - this.dropped;
                  int var10000;
                  if (s < sz - 1) {
                     Object var10 = fs.apply(s + 1);
                     if (var10 instanceof Tombstone) {
                        int d = ((Tombstone)var10).distance();
                        var10000 = s + d + 1;
                     } else {
                        var10000 = s + 1;
                     }
                  } else {
                     var10000 = s + 1;
                  }

                  int next = var10000;
                  fs = fs.updated(s, new Tombstone(next - s));
                  if (s > 0) {
                     label69: {
                        boolean var13 = false;
                        Tombstone var14 = null;
                        Object var15 = fs.apply(s - 1);
                        if (var15 instanceof Tombstone) {
                           var13 = true;
                           var14 = (Tombstone)var15;
                           int d = var14.distance();
                           if (d < 0) {
                              var10000 = s + d >= 0 ? s + d : 0;
                              break label69;
                           }
                        }

                        if (var13 && var14.distance() == 1) {
                           var10000 = s - 1;
                        } else {
                           if (var13) {
                              int d = var14.distance();
                              throw new IllegalStateException((new StringBuilder(35)).append("tombstone indicate wrong position: ").append(d).toString());
                           }

                           var10000 = s;
                        }
                     }
                  } else {
                     var10000 = s;
                  }

                  int first = var10000;
                  fs = fs.updated(first, new Tombstone(next - first));
                  int last = next - 1;
                  if (last != first) {
                     fs = fs.updated(last, new Tombstone(first - 1 - last));
                  }

                  VectorMap var22 = new VectorMap;
                  Map var10003 = this.underlying();
                  if (var10003 == null) {
                     throw null;
                  }

                  var22.<init>(fs, (Map)var10003.removed(key), this.dropped);
                  return var22;
               }
            }

            return this;
         }
      }
   }

   public MapFactory mapFactory() {
      return VectorMap$.MODULE$;
   }

   public boolean contains(final Object key) {
      return this.underlying().contains(key);
   }

   public Tuple2 head() {
      return (new AbstractIterator() {
         private final int fieldsLength;
         private int slot;
         private Object key;
         // $FF: synthetic field
         private final VectorMap $outer;

         private void advance() {
            int nextSlot = this.slot + 1;
            if (nextSlot >= this.fieldsLength) {
               this.slot = this.fieldsLength;
               this.key = null;
            } else {
               Tuple2 var2 = this.$outer.scala$collection$immutable$VectorMap$$nextValidField(nextSlot);
               if (var2 != null) {
                  int var3 = var2._1$mcI$sp();
                  if (-1 == var3) {
                     this.slot = this.fieldsLength;
                     this.key = null;
                     return;
                  }
               }

               if (var2 != null) {
                  int s = var2._1$mcI$sp();
                  Object k = var2._2();
                  this.slot = s;
                  this.key = k;
               } else {
                  throw new MatchError((Object)null);
               }
            }
         }

         public boolean hasNext() {
            return this.slot < this.fieldsLength;
         }

         public Tuple2 next() {
            if (!this.hasNext()) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (Tuple2)Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               Tuple2 result = new Tuple2(this.key, ((Tuple2)this.$outer.underlying().apply(this.key))._2());
               this.advance();
               return result;
            }
         }

         public {
            if (VectorMap.this == null) {
               throw null;
            } else {
               this.$outer = VectorMap.this;
               this.fieldsLength = VectorMap.this.fields().length();
               this.slot = -1;
               this.key = null;
               this.advance();
            }
         }
      }).next();
   }

   public Tuple2 last() {
      if (this.isEmpty()) {
         throw new UnsupportedOperationException("empty.last");
      } else {
         Object var10000;
         label30: {
            int lastSlot = this.fields().length() - 1;
            boolean var3 = false;
            Tombstone var4 = null;
            Object var5 = this.fields().last();
            if (var5 instanceof Tombstone) {
               var3 = true;
               var4 = (Tombstone)var5;
               int d = var4.distance();
               if (d < 0) {
                  var10000 = this.fields().apply(lastSlot + d);
                  break label30;
               }
            }

            if (var3 && var4.distance() == 1) {
               var10000 = this.fields().apply(lastSlot - 1);
            } else {
               if (var3) {
                  int d = var4.distance();
                  throw new IllegalStateException((new StringBuilder(35)).append("tombstone indicate wrong position: ").append(d).toString());
               }

               var10000 = var5;
            }
         }

         Object last = var10000;
         return new Tuple2(last, ((Tuple2)this.underlying().apply(last))._2());
      }
   }

   public Option lastOption() {
      return (Option)(this.isEmpty() ? None$.MODULE$ : new Some(this.last()));
   }

   public VectorMap tail() {
      if (this.isEmpty()) {
         throw new UnsupportedOperationException("empty.tail");
      } else {
         Tuple2 var1 = this.scala$collection$immutable$VectorMap$$nextValidField(0);
         if (var1 != null) {
            int slot = var1._1$mcI$sp();
            Object key = var1._2();
            VectorMap var10000 = new VectorMap;
            Vector var10002 = this.fields();
            int drop_n = slot + 1;
            if (var10002 == null) {
               throw null;
            } else {
               Vector drop_this = var10002;
               var10002 = (Vector)drop_this.slice(drop_n, drop_this.length());
               Object var6 = null;
               Map var10003 = this.underlying();
               if (var10003 == null) {
                  throw null;
               } else {
                  var10000.<init>(var10002, (Map)var10003.removed(key), this.dropped + slot + 1);
                  return var10000;
               }
            }
         } else {
            throw new MatchError((Object)null);
         }
      }
   }

   public VectorMap init() {
      if (this.isEmpty()) {
         throw new UnsupportedOperationException("empty.init");
      } else {
         Vector var10000 = this.fields();
         if (var10000 == null) {
            throw null;
         } else {
            Object var10001;
            label46: {
               int lastSlot = var10000.length() - 1;
               boolean var2 = false;
               Tombstone var3 = null;
               Object var4 = this.fields().last();
               if (var4 instanceof Tombstone) {
                  var2 = true;
                  var3 = (Tombstone)var4;
                  int d = var3.distance();
                  if (d < 0) {
                     var12 = lastSlot + d;
                     var10001 = this.fields().apply(lastSlot + d);
                     break label46;
                  }
               }

               if (var2 && var3.distance() == 1) {
                  var12 = lastSlot - 1;
                  var10001 = this.fields().apply(lastSlot - 1);
               } else {
                  if (var2) {
                     int d = var3.distance();
                     throw new IllegalStateException((new StringBuilder(35)).append("tombstone indicate wrong position: ").append(d).toString());
                  }

                  var12 = lastSlot;
                  var10001 = var4;
               }
            }

            Object var10 = var10001;
            int slot = var12;
            VectorMap var13 = new VectorMap;
            Vector var10002 = this.fields();
            Vector var10003 = this.fields();
            if (var10003 == null) {
               throw null;
            } else {
               int dropRight_n = var10003.length() - slot;
               if (var10002 == null) {
                  throw null;
               } else {
                  Vector dropRight_this = var10002;
                  var10002 = (Vector)dropRight_this.slice(0, dropRight_this.length() - Math.max(dropRight_n, 0));
                  Object var11 = null;
                  Map var15 = this.underlying();
                  if (var15 == null) {
                     throw null;
                  } else {
                     var13.<init>(var10002, (Map)var15.removed(var10), this.dropped);
                     return var13;
                  }
               }
            }
         }
      }
   }

   public Vector keys() {
      return (new AbstractIterator() {
         private final Iterator iter = VectorMap.this.iterator();

         private Iterator iter() {
            return this.iter;
         }

         public boolean hasNext() {
            return this.iter().hasNext();
         }

         public Object next() {
            return ((Tuple2)this.iter().next())._1();
         }
      }).toVector();
   }

   public Iterable values() {
      return new Iterable() {
         // $FF: synthetic field
         private final VectorMap $outer;

         public IterableFactory iterableFactory() {
            return Iterable.iterableFactory$(this);
         }

         /** @deprecated */
         public final scala.collection.Iterable toIterable() {
            return scala.collection.Iterable.toIterable$(this);
         }

         public final scala.collection.Iterable coll() {
            return scala.collection.Iterable.coll$(this);
         }

         /** @deprecated */
         public scala.collection.Iterable seq() {
            return scala.collection.Iterable.seq$(this);
         }

         public String className() {
            return scala.collection.Iterable.className$(this);
         }

         public final String collectionClassName() {
            return scala.collection.Iterable.collectionClassName$(this);
         }

         public String stringPrefix() {
            return scala.collection.Iterable.stringPrefix$(this);
         }

         public String toString() {
            return scala.collection.Iterable.toString$(this);
         }

         public LazyZip2 lazyZip(final scala.collection.Iterable that) {
            return scala.collection.Iterable.lazyZip$(this, that);
         }

         public IterableOps fromSpecific(final IterableOnce coll) {
            return IterableFactoryDefaults.fromSpecific$(this, coll);
         }

         public Builder newSpecificBuilder() {
            return IterableFactoryDefaults.newSpecificBuilder$(this);
         }

         public IterableOps empty() {
            return IterableFactoryDefaults.empty$(this);
         }

         /** @deprecated */
         public final scala.collection.Iterable toTraversable() {
            return IterableOps.toTraversable$(this);
         }

         public boolean isTraversableAgain() {
            return IterableOps.isTraversableAgain$(this);
         }

         /** @deprecated */
         public final Object repr() {
            return IterableOps.repr$(this);
         }

         /** @deprecated */
         public IterableFactory companion() {
            return IterableOps.companion$(this);
         }

         public Object head() {
            return IterableOps.head$(this);
         }

         public Option headOption() {
            return IterableOps.headOption$(this);
         }

         public Object last() {
            return IterableOps.last$(this);
         }

         public Option lastOption() {
            return IterableOps.lastOption$(this);
         }

         public View view() {
            return IterableOps.view$(this);
         }

         public int sizeCompare(final int otherSize) {
            return IterableOps.sizeCompare$(this, otherSize);
         }

         public final IterableOps sizeIs() {
            return IterableOps.sizeIs$(this);
         }

         public int sizeCompare(final scala.collection.Iterable that) {
            return IterableOps.sizeCompare$(this, that);
         }

         /** @deprecated */
         public View view(final int from, final int until) {
            return IterableOps.view$(this, from, until);
         }

         public Object transpose(final Function1 asIterable) {
            return IterableOps.transpose$(this, asIterable);
         }

         public Object filter(final Function1 pred) {
            return IterableOps.filter$(this, pred);
         }

         public Object filterNot(final Function1 pred) {
            return IterableOps.filterNot$(this, pred);
         }

         public scala.collection.WithFilter withFilter(final Function1 p) {
            return IterableOps.withFilter$(this, p);
         }

         public Tuple2 partition(final Function1 p) {
            return IterableOps.partition$(this, p);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOps.splitAt$(this, n);
         }

         public Object take(final int n) {
            return IterableOps.take$(this, n);
         }

         public Object takeRight(final int n) {
            return IterableOps.takeRight$(this, n);
         }

         public Object takeWhile(final Function1 p) {
            return IterableOps.takeWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return IterableOps.span$(this, p);
         }

         public Object drop(final int n) {
            return IterableOps.drop$(this, n);
         }

         public Object dropRight(final int n) {
            return IterableOps.dropRight$(this, n);
         }

         public Object dropWhile(final Function1 p) {
            return IterableOps.dropWhile$(this, p);
         }

         public Iterator grouped(final int size) {
            return IterableOps.grouped$(this, size);
         }

         public Iterator sliding(final int size) {
            return IterableOps.sliding$(this, size);
         }

         public Iterator sliding(final int size, final int step) {
            return IterableOps.sliding$(this, size, step);
         }

         public Object tail() {
            return IterableOps.tail$(this);
         }

         public Object init() {
            return IterableOps.init$(this);
         }

         public Object slice(final int from, final int until) {
            return IterableOps.slice$(this, from, until);
         }

         public Map groupBy(final Function1 f) {
            return IterableOps.groupBy$(this, f);
         }

         public Map groupMap(final Function1 key, final Function1 f) {
            return IterableOps.groupMap$(this, key, f);
         }

         public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
            return IterableOps.groupMapReduce$(this, key, f, reduce);
         }

         public Object scan(final Object z, final Function2 op) {
            return IterableOps.scan$(this, z, op);
         }

         public Object scanLeft(final Object z, final Function2 op) {
            return IterableOps.scanLeft$(this, z, op);
         }

         public Object scanRight(final Object z, final Function2 op) {
            return IterableOps.scanRight$(this, z, op);
         }

         public Object map(final Function1 f) {
            return IterableOps.map$(this, f);
         }

         public Object flatMap(final Function1 f) {
            return IterableOps.flatMap$(this, f);
         }

         public Object flatten(final Function1 asIterable) {
            return IterableOps.flatten$(this, asIterable);
         }

         public Object collect(final PartialFunction pf) {
            return IterableOps.collect$(this, pf);
         }

         public Tuple2 partitionMap(final Function1 f) {
            return IterableOps.partitionMap$(this, f);
         }

         public Object concat(final IterableOnce suffix) {
            return IterableOps.concat$(this, suffix);
         }

         public final Object $plus$plus(final IterableOnce suffix) {
            return IterableOps.$plus$plus$(this, suffix);
         }

         public Object zip(final IterableOnce that) {
            return IterableOps.zip$(this, that);
         }

         public Object zipWithIndex() {
            return IterableOps.zipWithIndex$(this);
         }

         public Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
            return IterableOps.zipAll$(this, that, thisElem, thatElem);
         }

         public Tuple2 unzip(final Function1 asPair) {
            return IterableOps.unzip$(this, asPair);
         }

         public Tuple3 unzip3(final Function1 asTriple) {
            return IterableOps.unzip3$(this, asTriple);
         }

         public Iterator tails() {
            return IterableOps.tails$(this);
         }

         public Iterator inits() {
            return IterableOps.inits$(this);
         }

         public Object tapEach(final Function1 f) {
            return IterableOps.tapEach$(this, f);
         }

         /** @deprecated */
         public Object $plus$plus$colon(final IterableOnce that) {
            return IterableOps.$plus$plus$colon$(this, that);
         }

         /** @deprecated */
         public boolean hasDefiniteSize() {
            return IterableOnceOps.hasDefiniteSize$(this);
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

         public boolean isEmpty() {
            return IterableOnceOps.isEmpty$(this);
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

         public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
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

         public Map toMap(final $less$colon$less ev) {
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

         public scala.collection.Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         public Iterator iterator() {
            VectorMap var10000 = this.$outer;
            if (var10000 == null) {
               throw null;
            } else {
               scala.collection.AbstractMap keysIterator_this = var10000;
               AbstractIterator var3 = new AbstractIterator() {
                  private final Iterator iter = VectorMap.this.iterator();

                  private Iterator iter() {
                     return this.iter;
                  }

                  public boolean hasNext() {
                     return this.iter().hasNext();
                  }

                  public Object next() {
                     return ((Tuple2)this.iter().next())._1();
                  }
               };
               keysIterator_this = null;
               return var3.map((x$4) -> ((Tuple2)this.$outer.underlying().apply(x$4))._2());
            }
         }

         public {
            if (VectorMap.this == null) {
               throw null;
            } else {
               this.$outer = VectorMap.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private VectorMap(final Vector fields, final Map underlying, final int dropped) {
      this.fields = fields;
      this.underlying = underlying;
      this.dropped = dropped;
      this.size = underlying.size();
   }

   public VectorMap(final Vector fields, final Map underlying) {
      this(fields, underlying, 0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class Tombstone implements Product, Serializable {
      private final int distance;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int distance() {
         return this.distance;
      }

      public Tombstone copy(final int distance) {
         return new Tombstone(distance);
      }

      public int copy$default$1() {
         return this.distance();
      }

      public String productPrefix() {
         return "Tombstone";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.distance();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Tombstone;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "distance";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, "Tombstone".hashCode());
         var1 = Statics.mix(var1, this.distance());
         int finalizeHash_length = 1;
         return Statics.avalanche(var1 ^ finalizeHash_length);
      }

      public String toString() {
         return ScalaRunTime$.MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof Tombstone) {
               Tombstone var2 = (Tombstone)x$1;
               if (this.distance() == var2.distance()) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public Tombstone(final int distance) {
         this.distance = distance;
      }
   }

   public static class Tombstone$ extends AbstractFunction1 implements Serializable {
      public static final Tombstone$ MODULE$ = new Tombstone$();

      public final String toString() {
         return "Tombstone";
      }

      public Tombstone apply(final int distance) {
         return new Tombstone(distance);
      }

      public Option unapply(final Tombstone x$0) {
         return (Option)(x$0 == null ? None$.MODULE$ : new Some(x$0.distance()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Tombstone$.class);
      }
   }
}
