package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps$;
import scala.collection.BitSetOps;
import scala.collection.BitSetOps$;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SortedIterableFactory;
import scala.collection.SortedOps;
import scala.collection.SortedSetFactoryDefaults;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSetOps;
import scala.collection.StrictOptimizedSortedSetOps;
import scala.collection.View;
import scala.collection.immutable.Range;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\teh\u0001B\u001c9\u0001}B\u0011B\u001a\u0001\u0003\u0002\u0004%)BO4\t\u00139\u0004!\u00111A\u0005\u0016iz\u0007\u0002C;\u0001\u0005\u0003\u0005\u000bU\u00025\t\u000bY\u0004A\u0011A<\t\u000bY\u0004A\u0011A=\t\u000bY\u0004A\u0011\u0001?\t\u000bu\u0004A\u0011\u000b@\t\u000f\u0005%\u0001\u0001\"\u0015\u0002\f!9\u00111\u0003\u0001\u0005B\u0005U\u0001bBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003G\u0003A\u0011IAS\u0011!\tI\u000b\u0001C\u000bu\u0005-\u0006\u0002CAW\u0001\u0011U!(a,\t\u0011\u00055\u0003\u0001\"\u0005;\u0003kCq!!/\u0001\t\u0003\tY\fC\u0004\u0002D\u0002!\t!!2\t\u000f\u0005%\u0007\u0001\"\u0001\u0002L\"9\u0011Q\u001a\u0001\u0005\u0016\u0005=\u0007bBAl\u0001\u0011U\u0011\u0011\u001c\u0005\b\u0003;\u0004A\u0011AAp\u0011\u001d\t)\u000f\u0001C\u0001\u0003ODq!!<\u0001\t\u0003\ty\u000fC\u0004\u0002t\u0002!\t!!>\t\u000f\u0005e\b\u0001\"\u0001\u0002|\"1\u0011q \u0001\u0005BqDqA!\u0001\u0001\t\u0003\u0011\u0019\u0001C\u0004\u0003\u0010\u0001!\tE!\u0005\t\u000f\t=\u0001\u0001\"\u0011\u0003\u001e!9!q\u000b\u0001\u0005B\te\u0003b\u0002B,\u0001\u0011\u0005#q\f\u0005\b\u0005o\u0002A\u0011\tB=\u0011\u001d\u00119\b\u0001C!\u0005\u000bCqAa'\u0001\t\u0003\u0012i\nC\u0004\u0003@\u0002!\tE!1\t\u000f\t\u001d\u0007\u0001\"\u0011\u0003J\"9!1\u001b\u0001\u0005B\tU\u0007\u0002CAH\u0001\u0001&\tB!7\t\u000f\tm\u0007\u0001\"\u0011\u0003^\"9!\u0011\u001d\u0001\u0005B\t\r\bb\u0002Bx\u0001\u0011\u0005#\u0011\u001f\u0005\u0007\u0005o\u0004A\u0011I4\b\u000f\u0005u\u0001\b#\u0001\u0002 \u00191q\u0007\u000fE\u0001\u0003CAaA^\u0016\u0005\u0002\u0005u\u0002BB?,\t\u0003\ty\u0004C\u0004\u0002\u0014-\"\t!!\u0006\t\u000f\u0005\u00153\u0006\"\u0001\u0002\f!9\u0011qI\u0016\u0005\u0002\u0005%\u0003bBA'W\u0011\u0005\u0011q\n\u0004\u0007\u0003'Zc!!\u0016\t\u0017\u0005\u0005!G!A!\u0002\u0013y\u0015q\u000e\u0005\u0007mJ\"\t!!\u001d\t\u0011\u0005e$\u0007)C\t\u0003wB\u0011\"a$,\u0003\u0003%I!!%\u0003\r\tKGoU3u\u0015\tI$(A\u0004nkR\f'\r\\3\u000b\u0005mb\u0014AC2pY2,7\r^5p]*\tQ(A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0013\u0001\u0001\u0005j\u0013)X5r{\u0006cA!C\t6\t\u0001(\u0003\u0002Dq\tY\u0011IY:ue\u0006\u001cGoU3u!\t)e)D\u0001=\u0013\t9EHA\u0002J]R\u00042!Q%E\u0013\tQ\u0005HA\u0005T_J$X\rZ*fiB)\u0011\t\u0014#O\u001f&\u0011Q\n\u000f\u0002\r'>\u0014H/\u001a3TKR|\u0005o\u001d\t\u0003\u0003&\u0003\"!\u0011\u0001\u0011\u000bE\u0013F\tV(\u000e\u0003iJ!a\u0015\u001e\u00035M#(/[2u\u001fB$\u0018.\\5{K\u0012LE/\u001a:bE2,w\n]:\u0011\u0005\u0005+\u0016B\u0001,9\u0005\r\u0019V\r\u001e\t\u0006#b#ejT\u0005\u00033j\u00121d\u0015;sS\u000e$x\n\u001d;j[&TX\rZ*peR,GmU3u\u001fB\u001c\bCA)\\\u0013\t9$\bE\u0002R;>K!A\u0018\u001e\u0003\u0013\tKGoU3u\u001fB\u001c\bC\u00011d\u001d\t)\u0015-\u0003\u0002cy\u00059\u0001/Y2lC\u001e,\u0017B\u00013f\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0011G(A\u0003fY\u0016l7/F\u0001i!\r)\u0015n[\u0005\u0003Ur\u0012Q!\u0011:sCf\u0004\"!\u00127\n\u00055d$\u0001\u0002'p]\u001e\f\u0011\"\u001a7f[N|F%Z9\u0015\u0005A\u001c\bCA#r\u0013\t\u0011HH\u0001\u0003V]&$\bb\u0002;\u0003\u0003\u0003\u0005\r\u0001[\u0001\u0004q\u0012\n\u0014AB3mK6\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\u001fbDQA\u001a\u0003A\u0002!$\"a\u0014>\t\u000bm,\u0001\u0019\u0001#\u0002\u0011%t\u0017\u000e^*ju\u0016$\u0012aT\u0001\rMJ|Wn\u00159fG&4\u0017n\u0019\u000b\u0003\u001f~Dq!!\u0001\b\u0001\u0004\t\u0019!\u0001\u0003d_2d\u0007\u0003B)\u0002\u0006\u0011K1!a\u0002;\u00051IE/\u001a:bE2,wJ\\2f\u0003IqWm^*qK\u000eLg-[2Ck&dG-\u001a:\u0016\u0005\u00055\u0001#B!\u0002\u0010\u0011{\u0015bAA\tq\t9!)^5mI\u0016\u0014\u0018!B3naRLX#A(\u0002\u001b\tLGoU3u\r\u0006\u001cGo\u001c:z+\t\tYB\u0004\u0002BU\u00051!)\u001b;TKR\u0004\"!Q\u0016\u0014\u000f-\n\u0019#!\u000b\u00020A\u0019Q)!\n\n\u0007\u0005\u001dBH\u0001\u0004B]f\u0014VM\u001a\t\u0006#\u0006-BiT\u0005\u0004\u0003[Q$aF*qK\u000eLg-[2Ji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z!\u0011\t\t$a\u000f\u000e\u0005\u0005M\"\u0002BA\u001b\u0003o\t!![8\u000b\u0005\u0005e\u0012\u0001\u00026bm\u0006L1\u0001ZA\u001a)\t\ty\u0002F\u0002P\u0003\u0003Bq!a\u0011.\u0001\u0004\t\u0019!\u0001\u0002ji\u0006Qa.Z<Ck&dG-\u001a:\u0002\u0017\u0019\u0014x.\u001c\"ji6\u000b7o\u001b\u000b\u0004\u001f\u0006-\u0003\"\u000241\u0001\u0004A\u0017!\u00054s_6\u0014\u0015\u000e^'bg.tunQ8qsR\u0019q*!\u0015\t\u000b\u0019\f\u0004\u0019\u00015\u0003%M+'/[1mSj\fG/[8o!J|\u00070_\n\u0004e\u0005]\u0003\u0003BA-\u0003WrA!a\u0017\u0002j9!\u0011QLA4\u001d\u0011\ty&!\u001a\u000e\u0005\u0005\u0005$bAA2}\u00051AH]8pizJ\u0011!P\u0005\u0003wqJ1!!\b;\u0013\u0011\t\u0019&!\u001c\u000b\u0007\u0005u!(\u0003\u0003\u0002\u0002\u0005-D\u0003BA:\u0003o\u00022!!\u001e3\u001b\u0005Y\u0003BBA\u0001i\u0001\u0007q*A\u0006sK\u0006$'+Z:pYZ,GCAA?!\r)\u0015qP\u0005\u0004\u0003\u0003c$aA!os\":!'!\"\u0002\f\u00065\u0005cA#\u0002\b&\u0019\u0011\u0011\u0012\u001f\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0002\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005M\u0005\u0003BAK\u00037k!!a&\u000b\t\u0005e\u0015qG\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001e\u0006]%AB(cU\u0016\u001cG\u000fK\u0004,\u0003\u000b\u000bY)!$)\u000f)\n))a#\u0002\u000e\u0006AQO\\:peR,G-\u0006\u0002\u0002(B\u0019\u0011)\u0016#\u0002\r9<xN\u001d3t+\u0005!\u0015\u0001B<pe\u0012$2a[AY\u0011\u0019\t\u0019,\u0004a\u0001\t\u0006\u0019\u0011\u000e\u001a=\u0015\u0007=\u000b9\fC\u0003g\u001d\u0001\u0007\u0001.\u0001\u0004bI\u0012|e.\u001a\u000b\u0005\u0003{\u000by,D\u0001\u0001\u0011\u0019\t\tm\u0004a\u0001\t\u0006!Q\r\\3n\u0003-\u0019XO\u0019;sC\u000e$xJ\\3\u0015\t\u0005u\u0016q\u0019\u0005\u0007\u0003\u0003\u0004\u0002\u0019\u0001#\u0002\u000b\rdW-\u0019:\u0015\u0003A\f!\"\u001e9eCR,wk\u001c:e)\u0015\u0001\u0018\u0011[Aj\u0011\u0019\t\u0019L\u0005a\u0001\t\"1\u0011Q\u001b\nA\u0002-\f\u0011a^\u0001\u000fK:\u001cXO]3DCB\f7-\u001b;z)\r\u0001\u00181\u001c\u0005\u0007\u0003g\u001b\u0002\u0019\u0001#\u0002\u001bUt7m\u001c8tiJ\f\u0017N\\3e+\t\t\t\u000f\u0005\u0003R\u0003G$\u0015B\u0001,;\u0003\u001d!#-\u0019:%KF$B!!0\u0002j\"1\u00111^\u000bA\u0002i\u000bQa\u001c;iKJ\fq\u0001J1na\u0012*\u0017\u000f\u0006\u0003\u0002>\u0006E\bBBAv-\u0001\u0007!,\u0001\u0004%kB$S-\u001d\u000b\u0005\u0003{\u000b9\u0010\u0003\u0004\u0002l^\u0001\rAW\u0001\u000eI\u0005l\u0007\u000f\n;jY\u0012,G%Z9\u0015\t\u0005u\u0016Q \u0005\u0007\u0003WD\u0002\u0019\u0001.\u0002\u000b\rdwN\\3\u0002\u0017Q|\u0017*\\7vi\u0006\u0014G.Z\u000b\u0003\u0005\u000b\u0001BAa\u0002\u0003\u000e5\u0011!\u0011\u0002\u0006\u0004\u0005\u0017Q\u0014!C5n[V$\u0018M\u00197f\u0013\r9$\u0011B\u0001\u0004[\u0006\u0004HcA(\u0003\u0014!9!QC\u000eA\u0002\t]\u0011!\u00014\u0011\u000b\u0015\u0013I\u0002\u0012#\n\u0007\tmAHA\u0005Gk:\u001cG/[8ocU!!q\u0004B\u0015)\u0011\u0011\tCa\u0015\u0015\t\t\r\"Q\u0007\t\u0005\u0003&\u0013)\u0003\u0005\u0003\u0003(\t%B\u0002\u0001\u0003\b\u0005Wa\"\u0019\u0001B\u0017\u0005\u0005\u0011\u0015\u0003\u0002B\u0018\u0003{\u00022!\u0012B\u0019\u0013\r\u0011\u0019\u0004\u0010\u0002\b\u001d>$\b.\u001b8h\u0011\u001d\u00119\u0004\ba\u0002\u0005s\t!!\u001a<\u0011\u000b\u0001\u0014YD!\n\n\u0007\tuRM\u0001\u0005Pe\u0012,'/\u001b8hQ!\u0011)D!\u0011\u0003N\t=\u0003\u0003\u0002B\"\u0005\u0013j!A!\u0012\u000b\u0007\t\u001dC(\u0001\u0006b]:|G/\u0019;j_:LAAa\u0013\u0003F\t\u0001\u0012.\u001c9mS\u000eLGOT8u\r>,h\u000eZ\u0001\u0004[N<\u0017E\u0001B)\u0003ytu\u000eI5na2L7-\u001b;!\u001fJ$WM]5oOn#3PQ?^A\u0019|WO\u001c3!i>\u0004#-^5mI\u0002\n\u0007eU8si\u0016$7+\u001a;\\Im\u0014U0\u0018\u0018!3>,\b%\\1zA]\fg\u000e\u001e\u0011u_\u0002*\boY1ti\u0002\"x\u000eI1!'\u0016$8,\u00138u;\u00022\u0017N]:uA\tL\beY1mY&tw\r\t1v]N|'\u000f^3eA:BqA!\u0006\u001d\u0001\u0004\u0011)\u0006\u0005\u0004F\u00053!%QE\u0001\bM2\fG/T1q)\ry%1\f\u0005\b\u0005+i\u0002\u0019\u0001B/!\u0019)%\u0011\u0004#\u0002\u0004U!!\u0011\rB5)\u0011\u0011\u0019G!\u001d\u0015\t\t\u0015$1\u000e\t\u0005\u0003&\u00139\u0007\u0005\u0003\u0003(\t%Da\u0002B\u0016=\t\u0007!Q\u0006\u0005\b\u0005oq\u00029\u0001B7!\u0015\u0001'1\bB4Q!\u0011YG!\u0011\u0003N\t=\u0003b\u0002B\u000b=\u0001\u0007!1\u000f\t\u0007\u000b\neAI!\u001e\u0011\u000bE\u000b)Aa\u001a\u0002\u000f\r|G\u000e\\3diR\u0019qJa\u001f\t\u000f\tut\u00041\u0001\u0003\u0000\u0005\u0011\u0001O\u001a\t\u0006\u000b\n\u0005E\tR\u0005\u0004\u0005\u0007c$a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u0016\t\t\u001d%q\u0012\u000b\u0005\u0005\u0013\u00139\n\u0006\u0003\u0003\f\nE\u0005\u0003B!J\u0005\u001b\u0003BAa\n\u0003\u0010\u00129!1\u0006\u0011C\u0002\t5\u0002b\u0002B\u001cA\u0001\u000f!1\u0013\t\u0006A\nm\"Q\u0012\u0015\t\u0005#\u0013\tE!\u0014\u0003P!9!Q\u0010\u0011A\u0002\te\u0005CB#\u0003\u0002\u0012\u0013i)A\u0002{SB,BAa(\u0003.R!!\u0011\u0015B])\u0011\u0011\u0019Ka,\u0011\t\u0005K%Q\u0015\t\u0007\u000b\n\u001dFIa+\n\u0007\t%FH\u0001\u0004UkBdWM\r\t\u0005\u0005O\u0011i\u000bB\u0004\u0003,\u0005\u0012\rA!\f\t\u000f\t]\u0012\u0005q\u0001\u00032B)\u0001Ma\u000f\u0003&\"B!q\u0016B!\u0005\u001b\u0012),\t\u0002\u00038\u0006\tYAT8!S6\u0004H.[2ji\u0002z%\u000fZ3sS:<7\fJ>C{v\u0003cm\\;oI\u0002\"x\u000e\t2vS2$\u0007%\u0019\u0011T_J$X\rZ*finC\u0013J\u001c;-A\u0011Z()`\u0015^]\u0001Jv.\u001e\u0011nCf\u0004s/\u00198uAQ|\u0007%\u001e9dCN$\b\u0005^8!C\u0002\u001aV\r^.J]Rl\u0006EZ5sgR\u0004#-\u001f\u0011dC2d\u0017N\\4!AVt7o\u001c:uK\u0012\u0004g\u0006C\u0004\u0003<\u0006\u0002\rA!0\u0002\tQD\u0017\r\u001e\t\u0006#\u0006\u0015!1V\u0001\u0007C\u0012$\u0017\t\u001c7\u0015\t\u0005u&1\u0019\u0005\b\u0005\u000b\u0014\u0003\u0019AA\u0002\u0003\tA8/\u0001\u0005tk\n\u001cX\r^(g)\u0011\u0011YM!5\u0011\u0007\u0015\u0013i-C\u0002\u0003Pr\u0012qAQ8pY\u0016\fg\u000eC\u0004\u0003<\u000e\u0002\r!!9\u0002\u0017M,(\r\u001e:bGR\fE\u000e\u001c\u000b\u0005\u0003{\u00139\u000eC\u0004\u0003F\u0012\u0002\r!a\u0001\u0015\u0005\u0005\r\u0012\u0001\u00023jM\u001a$2a\u0014Bp\u0011\u001d\u0011YL\na\u0001\u0003C\f!BZ5mi\u0016\u0014\u0018*\u001c9m)\u0015y%Q\u001dBv\u0011\u001d\u00119o\na\u0001\u0005S\fA\u0001\u001d:fIB1QI!\u0007E\u0005\u0017DqA!<(\u0001\u0004\u0011Y-A\u0005jg\u001ac\u0017\u000e\u001d9fI\u0006ia-\u001b7uKJLe\u000e\u00157bG\u0016$B!!0\u0003t\"9!Q\u001f\u0015A\u0002\t%\u0018!\u00019\u0002\u0013Q|')\u001b;NCN\\\u0007"
)
public class BitSet extends AbstractSet implements SortedSet, StrictOptimizedSortedSetOps, scala.collection.BitSet, Serializable {
   private long[] elems;

   public static BitSet fromBitMask(final long[] elems) {
      return BitSet$.MODULE$.fromBitMask(elems);
   }

   public static Builder newBuilder() {
      return BitSet$.MODULE$.newBuilder();
   }

   public static Factory specificIterableFactory() {
      return BitSet$.MODULE$;
   }

   public static Object fill(final int n, final Function0 elem) {
      BitSet$ fill_this = BitSet$.MODULE$;
      IterableOnce fromSpecific_it = new View.Fill(n, elem);
      return fill_this.fromSpecific(fromSpecific_it);
   }

   public String stringPrefix() {
      return scala.collection.BitSet.stringPrefix$(this);
   }

   // $FF: synthetic method
   public int scala$collection$BitSetOps$$super$max(final Ordering ord) {
      return BoxesRunTime.unboxToInt(scala.collection.SortedSetOps.max$(this, ord));
   }

   // $FF: synthetic method
   public int scala$collection$BitSetOps$$super$min(final Ordering ord) {
      return BoxesRunTime.unboxToInt(scala.collection.SortedSetOps.min$(this, ord));
   }

   // $FF: synthetic method
   public scala.collection.BitSet scala$collection$BitSetOps$$super$concat(final IterableOnce that) {
      return (scala.collection.BitSet)StrictOptimizedSetOps.concat$(this, that);
   }

   // $FF: synthetic method
   public scala.collection.BitSet scala$collection$BitSetOps$$super$intersect(final scala.collection.Set that) {
      return (scala.collection.BitSet)scala.collection.SetOps.intersect$(this, that);
   }

   // $FF: synthetic method
   public scala.collection.BitSet scala$collection$BitSetOps$$super$diff(final scala.collection.Set that) {
      return (scala.collection.BitSet)SetOps.diff$(this, that);
   }

   public final Ordering ordering() {
      return BitSetOps.ordering$(this);
   }

   public boolean contains(final int elem) {
      return BitSetOps.contains$(this, elem);
   }

   public Iterator iterator() {
      return BitSetOps.iterator$(this);
   }

   public Iterator iteratorFrom(final int start) {
      return BitSetOps.iteratorFrom$(this, start);
   }

   public Stepper stepper(final StepperShape shape) {
      return BitSetOps.stepper$(this, shape);
   }

   public int size() {
      return BitSetOps.size$(this);
   }

   public boolean isEmpty() {
      return BitSetOps.isEmpty$(this);
   }

   public int max(final Ordering ord) {
      return BitSetOps.max$(this, ord);
   }

   public int min(final Ordering ord) {
      return BitSetOps.min$(this, ord);
   }

   public void foreach(final Function1 f) {
      BitSetOps.foreach$(this, f);
   }

   public scala.collection.BitSet rangeImpl(final Option from, final Option until) {
      return BitSetOps.rangeImpl$(this, from, until);
   }

   public scala.collection.BitSet concat(final IterableOnce other) {
      return BitSetOps.concat$(this, other);
   }

   public scala.collection.BitSet intersect(final scala.collection.Set other) {
      return BitSetOps.intersect$(this, other);
   }

   public scala.collection.BitSet xor(final scala.collection.BitSet other) {
      return BitSetOps.xor$(this, other);
   }

   public final scala.collection.BitSet $up(final scala.collection.BitSet other) {
      return BitSetOps.$up$(this, other);
   }

   public Tuple2 partition(final Function1 p) {
      return BitSetOps.partition$(this, p);
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

   public SortedIterableFactory sortedIterableFactory() {
      return SortedSet.sortedIterableFactory$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$SortedSet$$super$equals(final Object that) {
      return scala.collection.Set.equals$(this, that);
   }

   public boolean equals(final Object that) {
      return scala.collection.SortedSet.equals$(this, that);
   }

   public scala.collection.SortedSetOps.WithFilter withFilter(final Function1 p) {
      return SortedSetFactoryDefaults.withFilter$(this, p);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   /** @deprecated */
   public Iterator keysIteratorFrom(final Object start) {
      return scala.collection.SortedSetOps.keysIteratorFrom$(this, start);
   }

   public Object firstKey() {
      return scala.collection.SortedSetOps.firstKey$(this);
   }

   public Object lastKey() {
      return scala.collection.SortedSetOps.lastKey$(this);
   }

   public Option minAfter(final Object key) {
      return scala.collection.SortedSetOps.minAfter$(this, key);
   }

   public Option maxBefore(final Object key) {
      return scala.collection.SortedSetOps.maxBefore$(this, key);
   }

   public scala.collection.SortedSetOps rangeTo(final Object to) {
      return scala.collection.SortedSetOps.rangeTo$(this, to);
   }

   /** @deprecated */
   public int compare(final Object k0, final Object k1) {
      return SortedOps.compare$(this, k0, k1);
   }

   public Object range(final Object from, final Object until) {
      return SortedOps.range$(this, from, until);
   }

   /** @deprecated */
   public final Object from(final Object from) {
      return SortedOps.from$(this, from);
   }

   public Object rangeFrom(final Object from) {
      return SortedOps.rangeFrom$(this, from);
   }

   /** @deprecated */
   public final Object until(final Object until) {
      return SortedOps.until$(this, until);
   }

   public Object rangeUntil(final Object until) {
      return SortedOps.rangeUntil$(this, until);
   }

   /** @deprecated */
   public final Object to(final Object to) {
      return SortedOps.to$(this, to);
   }

   public final long[] elems() {
      return this.elems;
   }

   public final void elems_$eq(final long[] x$1) {
      this.elems = x$1;
   }

   public BitSet fromSpecific(final IterableOnce coll) {
      return this.bitSetFactory().fromSpecific(coll);
   }

   public Builder newSpecificBuilder() {
      return this.bitSetFactory().newBuilder();
   }

   public BitSet empty() {
      if (this.bitSetFactory() == null) {
         throw null;
      } else {
         return new BitSet();
      }
   }

   public BitSet$ bitSetFactory() {
      return BitSet$.MODULE$;
   }

   public Set unsorted() {
      return this;
   }

   public final int nwords() {
      return this.elems().length;
   }

   public final long word(final int idx) {
      return idx < this.elems().length ? this.elems()[idx] : 0L;
   }

   public BitSet fromBitMaskNoCopy(final long[] elems) {
      return elems.length == 0 ? this.empty() : new BitSet(elems);
   }

   public BitSet addOne(final int elem) {
      Predef$.MODULE$.require(elem >= 0);
      if (!this.contains(elem)) {
         int idx = elem >> 6;
         this.updateWord(idx, this.word(idx) | 1L << elem);
      }

      return this;
   }

   public BitSet subtractOne(final int elem) {
      Predef$.MODULE$.require(elem >= 0);
      if (this.contains(elem)) {
         int idx = elem >> 6;
         this.updateWord(idx, this.word(idx) & ~(1L << elem));
      }

      return this;
   }

   public void clear() {
      this.elems_$eq(new long[this.elems().length]);
   }

   public final void updateWord(final int idx, final long w) {
      this.ensureCapacity(idx);
      this.elems()[idx] = w;
   }

   public final void ensureCapacity(final int idx) {
      Predef$.MODULE$.require(idx < 33554432);
      if (idx >= this.elems().length) {
         int newlen;
         for(newlen = this.elems().length; idx >= newlen; newlen = Math.min(newlen * 2, 33554432)) {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
         }

         long[] elems1 = new long[newlen];
         Array$.MODULE$.copy(this.elems(), 0, elems1, 0, this.elems().length);
         this.elems_$eq(elems1);
      }
   }

   public scala.collection.Set unconstrained() {
      return this;
   }

   public BitSet $bar$eq(final scala.collection.BitSet other) {
      this.ensureCapacity(other.nwords() - 1);
      int i = 0;

      for(int othernwords = other.nwords(); i < othernwords; ++i) {
         this.elems()[i] |= other.word(i);
      }

      return this;
   }

   public BitSet $amp$eq(final scala.collection.BitSet other) {
      int i = 0;

      for(int thisnwords = this.elems().length; i < thisnwords; ++i) {
         this.elems()[i] &= other.word(i);
      }

      return this;
   }

   public BitSet $up$eq(final scala.collection.BitSet other) {
      this.ensureCapacity(other.nwords() - 1);
      int i = 0;

      for(int othernwords = other.nwords(); i < othernwords; ++i) {
         this.elems()[i] ^= other.word(i);
      }

      return this;
   }

   public BitSet $amp$tilde$eq(final scala.collection.BitSet other) {
      int i = 0;

      for(int max = Math.min(this.elems().length, other.nwords()); i < max; ++i) {
         this.elems()[i] &= ~other.word(i);
      }

      return this;
   }

   public BitSet clone() {
      return new BitSet(Arrays.copyOf(this.elems(), this.elems().length));
   }

   public scala.collection.immutable.BitSet toImmutable() {
      return scala.collection.immutable.BitSet$.MODULE$.fromBitMask(this.elems());
   }

   public BitSet map(final Function1 f) {
      Builder strictOptimizedMap_b = this.newSpecificBuilder();

      Object var5;
      for(Iterator strictOptimizedMap_it = this.iterator(); strictOptimizedMap_it.hasNext(); var5 = null) {
         var5 = f.apply(strictOptimizedMap_it.next());
         if (strictOptimizedMap_b == null) {
            throw null;
         }

         strictOptimizedMap_b.addOne(var5);
      }

      return (BitSet)strictOptimizedMap_b.result();
   }

   public SortedSet map(final Function1 f, final Ordering ev) {
      return (SortedSet)StrictOptimizedSortedSetOps.map$(this, f, ev);
   }

   public BitSet flatMap(final Function1 f) {
      Builder strictOptimizedFlatMap_b = this.newSpecificBuilder();

      Object var5;
      for(Iterator strictOptimizedFlatMap_it = this.iterator(); strictOptimizedFlatMap_it.hasNext(); var5 = null) {
         IterableOnce strictOptimizedFlatMap_$plus$plus$eq_elems = (IterableOnce)f.apply(strictOptimizedFlatMap_it.next());
         if (strictOptimizedFlatMap_b == null) {
            throw null;
         }

         strictOptimizedFlatMap_b.addAll(strictOptimizedFlatMap_$plus$plus$eq_elems);
      }

      return (BitSet)strictOptimizedFlatMap_b.result();
   }

   public SortedSet flatMap(final Function1 f, final Ordering ev) {
      return (SortedSet)StrictOptimizedSortedSetOps.flatMap$(this, f, ev);
   }

   public BitSet collect(final PartialFunction pf) {
      Builder strictOptimizedCollect_b = this.newSpecificBuilder();
      Object strictOptimizedCollect_marker = Statics.pfMarker;
      Iterator strictOptimizedCollect_it = this.iterator();

      while(strictOptimizedCollect_it.hasNext()) {
         Object strictOptimizedCollect_elem = strictOptimizedCollect_it.next();
         Object strictOptimizedCollect_v = pf.applyOrElse(strictOptimizedCollect_elem, StrictOptimizedIterableOps::$anonfun$strictOptimizedCollect$1);
         if (strictOptimizedCollect_marker != strictOptimizedCollect_v) {
            if (strictOptimizedCollect_b == null) {
               throw null;
            }

            strictOptimizedCollect_b.addOne(strictOptimizedCollect_v);
         }
      }

      return (BitSet)strictOptimizedCollect_b.result();
   }

   public SortedSet collect(final PartialFunction pf, final Ordering ev) {
      return (SortedSet)StrictOptimizedSortedSetOps.collect$(this, pf, ev);
   }

   public SortedSet zip(final IterableOnce that, final Ordering ev) {
      return (SortedSet)StrictOptimizedSortedSetOps.zip$(this, that, ev);
   }

   public BitSet addAll(final IterableOnce xs) {
      if (xs instanceof scala.collection.BitSet) {
         scala.collection.BitSet var2 = (scala.collection.BitSet)xs;
         return this.$bar$eq(var2);
      } else if (xs instanceof Range) {
         Range var3 = (Range)xs;
         if (var3.nonEmpty()) {
            int start = var3.min(Ordering.Int$.MODULE$);
            if (start >= 0) {
               int end = var3.max(Ordering.Int$.MODULE$);
               int endIdx = end >> 6;
               this.ensureCapacity(endIdx);
               if (var3.step() != 1 && var3.step() != -1) {
                  Growable.addAll$(this, var3);
               } else {
                  int startIdx = start >> 6;
                  int wordStart = startIdx * 64;
                  long wordMask = -1L << start - wordStart;
                  if (endIdx > startIdx) {
                     long[] var11 = this.elems();
                     var11[startIdx] |= wordMask;
                     Arrays.fill(this.elems(), startIdx + 1, endIdx, -1L);
                     long[] var12 = this.elems();
                     var12[endIdx] |= -1L >>> 64 - (end - endIdx * 64) - 1;
                  } else {
                     long[] var13 = this.elems();
                     var13[endIdx] |= wordMask & -1L >>> 64 - (end - wordStart) - 1;
                  }
               }
            } else {
               Growable.addAll$(this, var3);
            }
         }

         return this;
      } else if (!(xs instanceof scala.collection.SortedSet)) {
         return (BitSet)Growable.addAll$(this, xs);
      } else {
         scala.collection.SortedSet var14 = (scala.collection.SortedSet)xs;
         if (var14.nonEmpty()) {
            Ordering ord = var14.ordering();
            if (ord == Ordering.Int$.MODULE$) {
               this.ensureCapacity(BoxesRunTime.unboxToInt(var14.lastKey()) >> 6);
            } else if (ord == Ordering.Int$.MODULE$.scala$math$Ordering$CachedReverse$$_reverse()) {
               this.ensureCapacity(BoxesRunTime.unboxToInt(var14.firstKey()) >> 6);
            }

            Iterator iter = var14.iterator();

            while(iter.hasNext()) {
               this.addOne(BoxesRunTime.unboxToInt(iter.next()));
            }
         }

         return this;
      }
   }

   public boolean subsetOf(final scala.collection.Set that) {
      if (that instanceof scala.collection.BitSet) {
         scala.collection.BitSet var2 = (scala.collection.BitSet)that;
         int thisnwords = this.elems().length;
         int bsnwords = var2.nwords();
         int minWords = Math.min(thisnwords, bsnwords);

         for(int i = bsnwords; i < thisnwords; ++i) {
            if (this.word(i) != 0L) {
               return false;
            }
         }

         for(int j = 0; j < minWords; ++j) {
            if ((this.word(j) & ~var2.word(j)) != 0L) {
               return false;
            }
         }

         return true;
      } else {
         return this.forall(that);
      }
   }

   public BitSet subtractAll(final IterableOnce xs) {
      if (xs instanceof scala.collection.BitSet) {
         scala.collection.BitSet var2 = (scala.collection.BitSet)xs;
         return this.$amp$tilde$eq(var2);
      } else {
         return (BitSet)Shrinkable.subtractAll$(this, xs);
      }
   }

   public Object writeReplace() {
      return new SerializationProxy(this);
   }

   public BitSet diff(final scala.collection.Set that) {
      if (!(that instanceof scala.collection.BitSet)) {
         return (BitSet)BitSetOps.diff$(this, that);
      } else {
         scala.collection.BitSet var2 = (scala.collection.BitSet)that;
         int bsnwords = var2.nwords();
         int thisnwords = this.elems().length;
         if (bsnwords >= thisnwords) {
            int i = thisnwords - 1;

            long currentWord;
            for(currentWord = 0L; i >= 0 && currentWord == 0L; --i) {
               currentWord = this.word(i) & ~var2.word(i);
            }

            if (i < 0) {
               return this.fromBitMaskNoCopy(new long[]{currentWord});
            } else {
               int minimumNonZeroIndex = i + 1;
               ArrayOps$ var10000 = ArrayOps$.MODULE$;
               long[] var10001 = this.elems();
               int take$extension_n = minimumNonZeroIndex + 1;
               long[] newArray = (long[])var10000.slice$extension(var10001, 0, take$extension_n);

               for(newArray[i + 1] = currentWord; i >= 0; --i) {
                  newArray[i] = this.word(i) & ~var2.word(i);
               }

               return this.fromBitMaskNoCopy(newArray);
            }
         } else {
            long[] newElems = (long[])this.elems().clone();

            for(int i = bsnwords - 1; i >= 0; --i) {
               newElems[i] = this.word(i) & ~var2.word(i);
            }

            return this.fromBitMaskNoCopy(newElems);
         }
      }
   }

   public BitSet filterImpl(final Function1 pred, final boolean isFlipped) {
      int i = this.elems().length - 1;

      long[] newArray;
      for(newArray = null; i >= 0; --i) {
         BitSetOps$ var10000 = BitSetOps$.MODULE$;
         long computeWordForFilter_oldWord = this.word(i);
         long var16;
         if (computeWordForFilter_oldWord == 0L) {
            var16 = 0L;
         } else {
            long computeWordForFilter_w = computeWordForFilter_oldWord;
            int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(computeWordForFilter_oldWord);
            long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
            int computeWordForFilter_j = i * 64 + computeWordForFilter_trailingZeroes;

            for(int computeWordForFilter_maxJ = (i + 1) * 64 - Long.numberOfLeadingZeros(computeWordForFilter_oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
               if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                  computeWordForFilter_w &= ~computeWordForFilter_jmask;
               }

               computeWordForFilter_jmask <<= 1;
            }

            var16 = computeWordForFilter_w;
         }

         long w = var16;
         if (w != 0L) {
            if (newArray == null) {
               newArray = new long[i + 1];
            }

            newArray[i] = w;
         }
      }

      if (newArray == null) {
         return this.empty();
      } else {
         return this.fromBitMaskNoCopy(newArray);
      }
   }

   public BitSet filterInPlace(final Function1 p) {
      int thisnwords = this.elems().length;

      for(int i = 0; i < thisnwords; ++i) {
         long[] var10000 = this.elems();
         BitSetOps$ var10002 = BitSetOps$.MODULE$;
         long computeWordForFilter_oldWord = this.elems()[i];
         boolean computeWordForFilter_isFlipped = false;
         long var14;
         if (computeWordForFilter_oldWord == 0L) {
            var14 = 0L;
         } else {
            long computeWordForFilter_w = computeWordForFilter_oldWord;
            int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(computeWordForFilter_oldWord);
            long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
            int computeWordForFilter_j = i * 64 + computeWordForFilter_trailingZeroes;

            for(int computeWordForFilter_maxJ = (i + 1) * 64 - Long.numberOfLeadingZeros(computeWordForFilter_oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
               if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && p.apply$mcZI$sp(computeWordForFilter_j) == computeWordForFilter_isFlipped) {
                  computeWordForFilter_w &= ~computeWordForFilter_jmask;
               }

               computeWordForFilter_jmask <<= 1;
            }

            var14 = computeWordForFilter_w;
         }

         var10000[i] = var14;
      }

      return this;
   }

   public long[] toBitMask() {
      return (long[])this.elems().clone();
   }

   public BitSet(final long[] elems) {
      this.elems = elems;
      super();
   }

   public BitSet(final int initSize) {
      scala.math.package$ var10001 = scala.math.package$.MODULE$;
      this(new long[Math.max(initSize + 63 >> 6, 1)]);
   }

   public BitSet() {
      this(0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private static final class SerializationProxy extends scala.collection.BitSet.SerializationProxy {
      private static final long serialVersionUID = 3L;

      public Object readResolve() {
         return BitSet$.MODULE$.fromBitMaskNoCopy(this.elems());
      }

      public SerializationProxy(final BitSet coll) {
         super(coll);
      }
   }
}
