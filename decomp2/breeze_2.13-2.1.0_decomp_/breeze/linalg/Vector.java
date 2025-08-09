package breeze.linalg;

import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.ScalarOf;
import breeze.math.Field;
import breeze.math.MutableFiniteCoordinateField;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.collection.immutable.BitSet.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tMhaB\u0012%!\u0003\r\t!\u000b\u0005\u00063\u0002!\tA\u0017\u0005\u0006=\u0002!\ta\u0018\u0005\u0006W\u00021\t\u0001\u001c\u0005\u0006[\u0002!\t\u0005\u001c\u0005\u0006]\u0002!\ta\u001c\u0005\u0006s\u0002!\tA\u001f\u0005\u0006y\u0002!\t! \u0005\u0007\u007f\u00021\t!!\u0001\t\u000f\u0005\r\u0001\u0001\"\u0011\u0002\u0006!9\u0011\u0011\u0003\u0001\u0005B\u0005M\u0001bBA\u000b\u0001\u0011\u0005\u0011q\u0003\u0005\b\u0003_\u0001A\u0011AA\u0019\u0011\u001d\ty\u0004\u0001C\u0001\u0003\u0003Bq!a\u0013\u0001\t\u0003\ti\u0005C\u0004\u0002R\u0001!\t!a\u0015\t\u000f\u0005\u0005\u0004\u0001\"\u0001\u0002d!9\u0011q\u000e\u0001\u0005B\u0005E\u0004bBA;\u0001\u0011\u0005\u0011q\u000f\u0005\b\u0003'\u0003A\u0011AAK\u0011\u001d\t9\u000b\u0001C\u0001\u0003SCq!!/\u0001\t\u0003\tY\fC\u0004\u0002H\u0002!\t!!3\t\u000f\u0005U\u0007\u0001\"\u0001\u0002X\"9\u00111\u001d\u0001\u0005\u0002\u0005\u0015\bb\u0002B\u0001\u0001\u0011\u0005!1\u0001\u0005\b\u00057\u0001A\u0011\u0001B\u000f\u000f\u001d\u0011)\u0004\nE\u0001\u0005o1aa\t\u0013\t\u0002\te\u0002b\u0002B\"9\u0011\u0005!Q\t\u0005\b\u0005\u000fbB\u0011\u0001B%\u0011\u001d\u0011i\u0007\bC\u0001\u0005_BqAa&\u001d\t\u0007\u0011I\nC\u0004\u00032r!\u0019Aa-\t\u000f\t}G\u0004b\u0001\u0003b\n1a+Z2u_JT!!\n\u0014\u0002\r1Lg.\u00197h\u0015\u00059\u0013A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005):4c\u0001\u0001,cA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t1\u0011I\\=SK\u001a\u0004BAM\u001a616\tA%\u0003\u00025I\tQa+Z2u_Jd\u0015n[3\u0011\u0005Y:D\u0002\u0001\u0003\nq\u0001\u0001\u000b\u0011!AC\u0002e\u0012\u0011AV\t\u0003uu\u0002\"\u0001L\u001e\n\u0005qj#a\u0002(pi\"Lgn\u001a\t\u0003YyJ!aP\u0017\u0003\u0007\u0005s\u0017\u0010K\u00038\u0003\u0012s5\u000b\u0005\u0002-\u0005&\u00111)\f\u0002\fgB,7-[1mSj,G-M\u0003$\u000b\u001aCuI\u0004\u0002-\r&\u0011q)L\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013J\u001b:r!AS'\u000e\u0003-S!\u0001\u0014\u0015\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0013'B\u0012P!J\u000bfB\u0001\u0017Q\u0013\t\tV&\u0001\u0004E_V\u0014G.Z\u0019\u0005I%ke&M\u0003$)V;fK\u0004\u0002-+&\u0011a+L\u0001\u0006\r2|\u0017\r^\u0019\u0005I%ke\u0006E\u00023\u0001U\na\u0001J5oSR$C#A.\u0011\u00051b\u0016BA/.\u0005\u0011)f.\u001b;\u0002\r-,\u0017pU3u+\u0005\u0001\u0007cA1fQ:\u0011!m\u0019\t\u0003\u00156J!\u0001Z\u0017\u0002\rA\u0013X\rZ3g\u0013\t1wMA\u0002TKRT!\u0001Z\u0017\u0011\u00051J\u0017B\u00016.\u0005\rIe\u000e^\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0003!\fAa]5{K\u0006A\u0011\u000e^3sCR|'/F\u0001q!\r\tHO^\u0007\u0002e*\u00111/L\u0001\u000bG>dG.Z2uS>t\u0017BA;s\u0005!IE/\u001a:bi>\u0014\b\u0003\u0002\u0017xQVJ!\u0001_\u0017\u0003\rQ+\b\u000f\\33\u000391\u0018\r\\;fg&#XM]1u_J,\u0012a\u001f\t\u0004cR,\u0014\u0001D6fsNLE/\u001a:bi>\u0014X#\u0001@\u0011\u0007E$\b.\u0001\u0003d_BLX#\u0001-\u0002\r\u0015\fX/\u00197t)\u0011\t9!!\u0004\u0011\u00071\nI!C\u0002\u0002\f5\u0012qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002\u0010%\u0001\r!P\u0001\u0003aF\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002Q\u0006iAo\u001c#f]N,g+Z2u_J$B!!\u0007\u0002 A!!'a\u00076\u0013\r\ti\u0002\n\u0002\f\t\u0016t7/\u001a,fGR|'\u000fC\u0004\u0002\"-\u0001\u001d!a\t\u0002\u0005\rl\u0007#BA\u0013\u0003W)TBAA\u0014\u0015\r\tI#L\u0001\be\u00164G.Z2u\u0013\u0011\ti#a\n\u0003\u0011\rc\u0017m]:UC\u001e\fQ\u0002^8TG\u0006d\u0017MV3di>\u0014XCAA\u001a!\u0015\t)$a\u000f6\u001d\rI\u0015qG\u0005\u0004\u0003si\u0013a\u00029bG.\fw-Z\u0005\u0004G\u0005u\"bAA\u001d[\u00059Ao\\!se\u0006LH\u0003BA\"\u0003\u0013\u0002B\u0001LA#k%\u0019\u0011qI\u0017\u0003\u000b\u0005\u0013(/Y=\t\u000f\u0005\u0005R\u0002q\u0001\u0002$\u0005AAo\u001c,fGR|'\u000fF\u0002Y\u0003\u001fBq!!\t\u000f\u0001\b\t\u0019#A\u0003qC\u0012$v\u000e\u0006\u0004\u0002V\u0005e\u0013Q\f\u000b\u00041\u0006]\u0003bBA\u0011\u001f\u0001\u000f\u00111\u0005\u0005\u0007\u00037z\u0001\u0019\u00015\u0002\u00071,g\u000e\u0003\u0004\u0002`=\u0001\r!N\u0001\u0005K2,W.\u0001\u0004fq&\u001cHo\u001d\u000b\u0005\u0003\u000f\t)\u0007C\u0004\u0002hA\u0001\r!!\u001b\u0002\u0003\u0019\u0004b\u0001LA6k\u0005\u001d\u0011bAA7[\tIa)\u001e8di&|g.M\u0001\u0007M>\u0014\u0018\r\u001c7\u0015\t\u0005\u001d\u00111\u000f\u0005\b\u0003O\n\u0002\u0019AA5\u0003\u00111w\u000e\u001c3\u0016\t\u0005e\u0014q\u0010\u000b\u0005\u0003w\ny\t\u0006\u0003\u0002~\u0005\u0015\u0005c\u0001\u001c\u0002\u0000\u00119\u0011\u0011\u0011\nC\u0002\u0005\r%AA#2#\t)T\bC\u0004\u0002\bJ\u0001\r!!#\u0002\u0005=\u0004\b#\u0003\u0017\u0002\f\u0006u\u0014QPA?\u0013\r\ti)\f\u0002\n\rVt7\r^5p]JBq!!%\u0013\u0001\u0004\ti(A\u0001{\u0003!1w\u000e\u001c3MK\u001a$X\u0003BAL\u0003;#B!!'\u0002&R!\u00111TAQ!\r1\u0014Q\u0014\u0003\u0007\u0003?\u001b\"\u0019A\u001d\u0003\u0003\tCq!a\"\u0014\u0001\u0004\t\u0019\u000b\u0005\u0005-\u0003\u0017\u000bY*NAN\u0011\u001d\t\tj\u0005a\u0001\u00037\u000b\u0011BZ8mIJKw\r\u001b;\u0016\t\u0005-\u0016\u0011\u0017\u000b\u0005\u0003[\u000b9\f\u0006\u0003\u00020\u0006M\u0006c\u0001\u001c\u00022\u00121\u0011q\u0014\u000bC\u0002eBq!a\"\u0015\u0001\u0004\t)\f\u0005\u0005-\u0003\u0017+\u0014qVAX\u0011\u001d\t\t\n\u0006a\u0001\u0003_\u000baA]3ek\u000e,W\u0003BA_\u0003\u0003$B!a0\u0002DB\u0019a'!1\u0005\u000f\u0005\u0005UC1\u0001\u0002\u0004\"9\u0011qQ\u000bA\u0002\u0005\u0015\u0007#\u0003\u0017\u0002\f\u0006}\u0016qXA`\u0003)\u0011X\rZ;dK2+g\r^\u000b\u0005\u0003\u0017\fy\r\u0006\u0003\u0002N\u0006E\u0007c\u0001\u001c\u0002P\u00129\u0011q\u0014\fC\u0002\u0005\r\u0005bBAD-\u0001\u0007\u00111\u001b\t\tY\u0005-\u0015QZ\u001b\u0002N\u0006Y!/\u001a3vG\u0016\u0014\u0016n\u001a5u+\u0011\tI.!8\u0015\t\u0005m\u0017q\u001c\t\u0004m\u0005uGaBAP/\t\u0007\u00111\u0011\u0005\b\u0003\u000f;\u0002\u0019AAq!!a\u00131R\u001b\u0002\\\u0006m\u0017\u0001B:dC:,B!a:\u0002rR!\u0011\u0011^A\u0000)\u0011\tY/a?\u0015\r\u00055\u00181_A{!\u0011\u0011\u0004!a<\u0011\u0007Y\n\t\u0010B\u0004\u0002\u0002b\u0011\r!a!\t\u000f\u0005\u0005\u0002\u0004q\u0001\u0002$!9\u0011q\u001f\rA\u0004\u0005e\u0018aA2ncA1\u0011QEA\u0016\u0003_Dq!a\"\u0019\u0001\u0004\ti\u0010E\u0005-\u0003\u0017\u000by/a<\u0002p\"9\u0011\u0011\u0013\rA\u0002\u0005=\u0018\u0001C:dC:dUM\u001a;\u0016\t\t\u0015!q\u0002\u000b\u0005\u0005\u000f\u0011I\u0002\u0006\u0003\u0003\n\tUA\u0003\u0002B\u0006\u0005#\u0001BA\r\u0001\u0003\u000eA\u0019aGa\u0004\u0005\r\u0005}\u0015D1\u0001:\u0011\u001d\t90\u0007a\u0002\u0005'\u0001b!!\n\u0002,\t5\u0001bBAD3\u0001\u0007!q\u0003\t\tY\u0005-%QB\u001b\u0003\u000e!9\u0011\u0011S\rA\u0002\t5\u0011!C:dC:\u0014\u0016n\u001a5u+\u0011\u0011yB!\u000b\u0015\t\t\u0005\"1\u0007\u000b\u0005\u0005G\u0011y\u0003\u0006\u0003\u0003&\t-\u0002\u0003\u0002\u001a\u0001\u0005O\u00012A\u000eB\u0015\t\u0019\tyJ\u0007b\u0001s!9\u0011q\u001f\u000eA\u0004\t5\u0002CBA\u0013\u0003W\u00119\u0003C\u0004\u0002\bj\u0001\rA!\r\u0011\u00111\nY)\u000eB\u0014\u0005OAq!!%\u001b\u0001\u0004\u00119#\u0001\u0004WK\u000e$xN\u001d\t\u0003eq\u0019B\u0001H\u0016\u0003<A)!G!\u0010\u0003B%\u0019!q\b\u0013\u0003%Y+7\r^8s\u0007>t7\u000f\u001e:vGR|'o\u001d\t\u0003e\u0001\ta\u0001P5oSRtDC\u0001B\u001c\u0003\u0015QXM]8t+\u0011\u0011YEa\u0015\u0015\t\t5#1\u000e\u000b\u0007\u0005\u001f\u0012)Fa\u0017\u0011\tI\u0002!\u0011\u000b\t\u0004m\tMC!\u0002\u001d\u001f\u0005\u0004I\u0004\"\u0003B,=\u0005\u0005\t9\u0001B-\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003K\tYC!\u0015\t\u0013\tuc$!AA\u0004\t}\u0013AC3wS\u0012,gnY3%eA1!\u0011\rB4\u0005#j!Aa\u0019\u000b\u0007\t\u0015d%A\u0004ti>\u0014\u0018mZ3\n\t\t%$1\r\u0002\u00055\u0016\u0014x\u000eC\u0003n=\u0001\u0007\u0001.A\u0003baBd\u00170\u0006\u0003\u0003r\t]D\u0003\u0002B:\u0005#\u0003BA\r\u0001\u0003vA\u0019aGa\u001e\u0005\u0013az\u0002\u0015!A\u0001\u0006\u0004I\u0004f\u0003B<\u0003\nm$q\u0010BB\u0005\u000f\u000bdaI(Q\u0005{\n\u0016\u0007\u0002\u0013J\u001b:\ndaI#G\u0005\u0003;\u0015\u0007\u0002\u0013J\u001b:\nda\t+V\u0005\u000b3\u0016\u0007\u0002\u0013J\u001b:\n\u0014b\tBE\u0005\u0017\u0013yI!$\u000f\u00071\u0012Y)C\u0002\u0003\u000e6\nA\u0001T8oOF\"A%S'/\u0011\u001d\u0011\u0019j\ba\u0001\u0005+\u000baA^1mk\u0016\u001c\b#\u0002\u0017\u0002F\tU\u0014aB2b]\u000e{\u0007/_\u000b\u0005\u00057\u0013i+\u0006\u0002\u0003\u001eB1!q\u0014BS\u0005Sk!A!)\u000b\u0007\t\rF%A\u0004tkB\u0004xN\u001d;\n\t\t\u001d&\u0011\u0015\u0002\b\u0007\u0006t7i\u001c9z!\u0011\u0011\u0004Aa+\u0011\u0007Y\u0012i\u000b\u0002\u0004\u00030\u0002\u0012\r!\u000f\u0002\u0002\u000b\u0006)1\u000f]1dKV!!Q\u0017Bd)!\u00119L!3\u0003T\ne\u0007#\u0003B]\u0005\u007f\u0013\u0019\r\u001bBc\u001b\t\u0011YLC\u0002\u0003>\u001a\nA!\\1uQ&!!\u0011\u0019B^\u0005qiU\u000f^1cY\u00164\u0015N\\5uK\u000e{wN\u001d3j]\u0006$XMR5fY\u0012\u0004BA\r\u0001\u0003FB\u0019aGa2\u0005\u000ba\n#\u0019A\u001d\t\u0013\t-\u0017%!AA\u0004\t5\u0017AC3wS\u0012,gnY3%gA1!\u0011\u0018Bh\u0005\u000bLAA!5\u0003<\n)a)[3mI\"I!Q[\u0011\u0002\u0002\u0003\u000f!q[\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004C\u0002B1\u0005O\u0012)\rC\u0005\u0003\\\u0006\n\t\u0011q\u0001\u0003^\u0006QQM^5eK:\u001cW\rJ\u001b\u0011\r\u0005\u0015\u00121\u0006Bc\u0003!\u00198-\u00197be>3W\u0003\u0002Br\u0005_,\"A!:\u0011\u0011\t}%q\u001dBv\u0005[LAA!;\u0003\"\nA1kY1mCJ|e\r\u0005\u00033\u0001\t5\bc\u0001\u001c\u0003p\u00121!\u0011\u001f\u0012C\u0002e\u0012\u0011\u0001\u0016"
)
public interface Vector extends VectorLike {
   static ScalarOf scalarOf() {
      return Vector$.MODULE$.scalarOf();
   }

   static MutableFiniteCoordinateField space(final Field evidence$3, final Zero evidence$4, final ClassTag evidence$5) {
      return Vector$.MODULE$.space(evidence$3, evidence$4, evidence$5);
   }

   static CanCopy canCopy() {
      return Vector$.MODULE$.canCopy();
   }

   static Vector zeros(final int size, final ClassTag evidence$1, final Zero evidence$2) {
      return Vector$.MODULE$.zeros(size, evidence$1, evidence$2);
   }

   static double rangeD$default$3() {
      return Vector$.MODULE$.rangeD$default$3();
   }

   static Vector rangeD(final double start, final double end, final double step) {
      return Vector$.MODULE$.rangeD(start, end, step);
   }

   static float rangeF$default$3() {
      return Vector$.MODULE$.rangeF$default$3();
   }

   static Vector rangeF(final float start, final float end, final float step) {
      return Vector$.MODULE$.rangeF(start, end, step);
   }

   static Vector range(final int start, final int end, final int step) {
      return Vector$.MODULE$.range(start, end, step);
   }

   static Vector range(final int start, final int end) {
      return Vector$.MODULE$.range(start, end);
   }

   static Rand rand$default$2() {
      return Vector$.MODULE$.rand$default$2();
   }

   static Vector rand(final int size, final Rand rand, final ClassTag evidence$11) {
      return Vector$.MODULE$.rand(size, rand, evidence$11);
   }

   static CanCreateZeros canCreateZeros(final ClassTag evidence$9, final Zero evidence$10) {
      return Vector$.MODULE$.canCreateZeros(evidence$9, evidence$10);
   }

   // $FF: synthetic method
   static Set keySet$(final Vector $this) {
      return $this.keySet();
   }

   default Set keySet() {
      return (Set).MODULE$.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.length()));
   }

   int length();

   // $FF: synthetic method
   static int size$(final Vector $this) {
      return $this.size();
   }

   default int size() {
      return this.length();
   }

   // $FF: synthetic method
   static Iterator iterator$(final Vector $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return scala.package..MODULE$.Iterator().range(0, this.size()).map((i) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static Iterator valuesIterator$(final Vector $this) {
      return $this.valuesIterator();
   }

   default Iterator valuesIterator() {
      return scala.package..MODULE$.Iterator().range(0, this.size()).map((i) -> $anonfun$valuesIterator$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static Iterator keysIterator$(final Vector $this) {
      return $this.keysIterator();
   }

   default Iterator keysIterator() {
      return scala.package..MODULE$.Iterator().range(0, this.size());
   }

   Vector copy();

   // $FF: synthetic method
   static boolean equals$(final Vector $this, final Object p1) {
      return $this.equals(p1);
   }

   default boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof Vector) {
         Vector var4 = (Vector)p1;
         var2 = this.length() == var4.length() && this.valuesIterator().sameElements(var4.valuesIterator());
      } else {
         var2 = false;
      }

      return var2;
   }

   // $FF: synthetic method
   static int hashCode$(final Vector $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      throw new UnsupportedOperationException("hashCode has to be overridden for Vectors");
   }

   // $FF: synthetic method
   static DenseVector toDenseVector$(final Vector $this, final ClassTag cm) {
      return $this.toDenseVector(cm);
   }

   default DenseVector toDenseVector(final ClassTag cm) {
      return DenseVector$.MODULE$.apply(this.toArray(cm));
   }

   // $FF: synthetic method
   static scala.collection.immutable.Vector toScalaVector$(final Vector $this) {
      return $this.toScalaVector();
   }

   default scala.collection.immutable.Vector toScalaVector() {
      return (scala.collection.immutable.Vector)scala.package..MODULE$.Vector().empty().$plus$plus(this.valuesIterator());
   }

   // $FF: synthetic method
   static Object toArray$(final Vector $this, final ClassTag cm) {
      return $this.toArray(cm);
   }

   default Object toArray(final ClassTag cm) {
      Object result = cm.newArray(this.length());

      for(int i = 0; i < this.length(); ++i) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(result, i, this.apply(BoxesRunTime.boxToInteger(i)));
      }

      return result;
   }

   // $FF: synthetic method
   static Vector toVector$(final Vector $this, final ClassTag cm) {
      return $this.toVector(cm);
   }

   default Vector toVector(final ClassTag cm) {
      return Vector$.MODULE$.apply(this.toArray(cm));
   }

   // $FF: synthetic method
   static Vector padTo$(final Vector $this, final int len, final Object elem, final ClassTag cm) {
      return $this.padTo(len, elem, cm);
   }

   default Vector padTo(final int len, final Object elem, final ClassTag cm) {
      return Vector$.MODULE$.apply(scala.collection.ArrayOps..MODULE$.padTo$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray(cm)), len, elem, cm));
   }

   // $FF: synthetic method
   static boolean exists$(final Vector $this, final Function1 f) {
      return $this.exists(f);
   }

   default boolean exists(final Function1 f) {
      return this.valuesIterator().exists(f);
   }

   // $FF: synthetic method
   static boolean forall$(final Vector $this, final Function1 f) {
      return $this.forall(f);
   }

   default boolean forall(final Function1 f) {
      return this.valuesIterator().forall(f);
   }

   // $FF: synthetic method
   static Object fold$(final Vector $this, final Object z, final Function2 op) {
      return $this.fold(z, op);
   }

   default Object fold(final Object z, final Function2 op) {
      return this.valuesIterator().fold(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldLeft(z, op);
   }

   default Object foldLeft(final Object z, final Function2 op) {
      return this.valuesIterator().foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldRight(z, op);
   }

   default Object foldRight(final Object z, final Function2 op) {
      return this.valuesIterator().foldRight(z, op);
   }

   // $FF: synthetic method
   static Object reduce$(final Vector $this, final Function2 op) {
      return $this.reduce(op);
   }

   default Object reduce(final Function2 op) {
      return this.valuesIterator().reduce(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$(final Vector $this, final Function2 op) {
      return $this.reduceLeft(op);
   }

   default Object reduceLeft(final Function2 op) {
      return this.valuesIterator().reduceLeft(op);
   }

   // $FF: synthetic method
   static Object reduceRight$(final Vector $this, final Function2 op) {
      return $this.reduceRight(op);
   }

   default Object reduceRight(final Function2 op) {
      return this.valuesIterator().reduceRight(op);
   }

   // $FF: synthetic method
   static Vector scan$(final Vector $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan(z, op, cm, cm1);
   }

   default Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$.MODULE$.apply(scala.collection.ArrayOps..MODULE$.scan$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray(cm)), z, op, cm1));
   }

   // $FF: synthetic method
   static Vector scanLeft$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft(z, op, cm1);
   }

   default Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(this.valuesIterator().scanLeft(z, op).toArray(cm1));
   }

   // $FF: synthetic method
   static Vector scanRight$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight(z, op, cm1);
   }

   default Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(((IterableOnceOps)this.toScalaVector().scanRight(z, op)).toArray(cm1));
   }

   // $FF: synthetic method
   static Vector copy$mcD$sp$(final Vector $this) {
      return $this.copy$mcD$sp();
   }

   default Vector copy$mcD$sp() {
      return this.copy();
   }

   // $FF: synthetic method
   static Vector copy$mcF$sp$(final Vector $this) {
      return $this.copy$mcF$sp();
   }

   default Vector copy$mcF$sp() {
      return this.copy();
   }

   // $FF: synthetic method
   static Vector copy$mcI$sp$(final Vector $this) {
      return $this.copy$mcI$sp();
   }

   default Vector copy$mcI$sp() {
      return this.copy();
   }

   // $FF: synthetic method
   static DenseVector toDenseVector$mcD$sp$(final Vector $this, final ClassTag cm) {
      return $this.toDenseVector$mcD$sp(cm);
   }

   default DenseVector toDenseVector$mcD$sp(final ClassTag cm) {
      return this.toDenseVector(cm);
   }

   // $FF: synthetic method
   static DenseVector toDenseVector$mcF$sp$(final Vector $this, final ClassTag cm) {
      return $this.toDenseVector$mcF$sp(cm);
   }

   default DenseVector toDenseVector$mcF$sp(final ClassTag cm) {
      return this.toDenseVector(cm);
   }

   // $FF: synthetic method
   static DenseVector toDenseVector$mcI$sp$(final Vector $this, final ClassTag cm) {
      return $this.toDenseVector$mcI$sp(cm);
   }

   default DenseVector toDenseVector$mcI$sp(final ClassTag cm) {
      return this.toDenseVector(cm);
   }

   // $FF: synthetic method
   static double[] toArray$mcD$sp$(final Vector $this, final ClassTag cm) {
      return $this.toArray$mcD$sp(cm);
   }

   default double[] toArray$mcD$sp(final ClassTag cm) {
      return (double[])this.toArray(cm);
   }

   // $FF: synthetic method
   static float[] toArray$mcF$sp$(final Vector $this, final ClassTag cm) {
      return $this.toArray$mcF$sp(cm);
   }

   default float[] toArray$mcF$sp(final ClassTag cm) {
      return (float[])this.toArray(cm);
   }

   // $FF: synthetic method
   static int[] toArray$mcI$sp$(final Vector $this, final ClassTag cm) {
      return $this.toArray$mcI$sp(cm);
   }

   default int[] toArray$mcI$sp(final ClassTag cm) {
      return (int[])this.toArray(cm);
   }

   // $FF: synthetic method
   static Vector toVector$mcD$sp$(final Vector $this, final ClassTag cm) {
      return $this.toVector$mcD$sp(cm);
   }

   default Vector toVector$mcD$sp(final ClassTag cm) {
      return this.toVector(cm);
   }

   // $FF: synthetic method
   static Vector toVector$mcF$sp$(final Vector $this, final ClassTag cm) {
      return $this.toVector$mcF$sp(cm);
   }

   default Vector toVector$mcF$sp(final ClassTag cm) {
      return this.toVector(cm);
   }

   // $FF: synthetic method
   static Vector toVector$mcI$sp$(final Vector $this, final ClassTag cm) {
      return $this.toVector$mcI$sp(cm);
   }

   default Vector toVector$mcI$sp(final ClassTag cm) {
      return this.toVector(cm);
   }

   // $FF: synthetic method
   static Vector padTo$mcD$sp$(final Vector $this, final int len, final double elem, final ClassTag cm) {
      return $this.padTo$mcD$sp(len, elem, cm);
   }

   default Vector padTo$mcD$sp(final int len, final double elem, final ClassTag cm) {
      return this.padTo(len, BoxesRunTime.boxToDouble(elem), cm);
   }

   // $FF: synthetic method
   static Vector padTo$mcF$sp$(final Vector $this, final int len, final float elem, final ClassTag cm) {
      return $this.padTo$mcF$sp(len, elem, cm);
   }

   default Vector padTo$mcF$sp(final int len, final float elem, final ClassTag cm) {
      return this.padTo(len, BoxesRunTime.boxToFloat(elem), cm);
   }

   // $FF: synthetic method
   static Vector padTo$mcI$sp$(final Vector $this, final int len, final int elem, final ClassTag cm) {
      return $this.padTo$mcI$sp(len, elem, cm);
   }

   default Vector padTo$mcI$sp(final int len, final int elem, final ClassTag cm) {
      return this.padTo(len, BoxesRunTime.boxToInteger(elem), cm);
   }

   // $FF: synthetic method
   static boolean exists$mcD$sp$(final Vector $this, final Function1 f) {
      return $this.exists$mcD$sp(f);
   }

   default boolean exists$mcD$sp(final Function1 f) {
      return this.exists(f);
   }

   // $FF: synthetic method
   static boolean exists$mcF$sp$(final Vector $this, final Function1 f) {
      return $this.exists$mcF$sp(f);
   }

   default boolean exists$mcF$sp(final Function1 f) {
      return this.exists(f);
   }

   // $FF: synthetic method
   static boolean exists$mcI$sp$(final Vector $this, final Function1 f) {
      return $this.exists$mcI$sp(f);
   }

   default boolean exists$mcI$sp(final Function1 f) {
      return this.exists(f);
   }

   // $FF: synthetic method
   static boolean forall$mcD$sp$(final Vector $this, final Function1 f) {
      return $this.forall$mcD$sp(f);
   }

   default boolean forall$mcD$sp(final Function1 f) {
      return this.forall(f);
   }

   // $FF: synthetic method
   static boolean forall$mcF$sp$(final Vector $this, final Function1 f) {
      return $this.forall$mcF$sp(f);
   }

   default boolean forall$mcF$sp(final Function1 f) {
      return this.forall(f);
   }

   // $FF: synthetic method
   static boolean forall$mcI$sp$(final Vector $this, final Function1 f) {
      return $this.forall$mcI$sp(f);
   }

   default boolean forall$mcI$sp(final Function1 f) {
      return this.forall(f);
   }

   // $FF: synthetic method
   static Object fold$mcD$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.fold$mcD$sp(z, op);
   }

   default Object fold$mcD$sp(final Object z, final Function2 op) {
      return this.fold(z, op);
   }

   // $FF: synthetic method
   static Object fold$mcF$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.fold$mcF$sp(z, op);
   }

   default Object fold$mcF$sp(final Object z, final Function2 op) {
      return this.fold(z, op);
   }

   // $FF: synthetic method
   static Object fold$mcI$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.fold$mcI$sp(z, op);
   }

   default Object fold$mcI$sp(final Object z, final Function2 op) {
      return this.fold(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$mcD$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldLeft$mcD$sp(z, op);
   }

   default Object foldLeft$mcD$sp(final Object z, final Function2 op) {
      return this.foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$mcF$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldLeft$mcF$sp(z, op);
   }

   default Object foldLeft$mcF$sp(final Object z, final Function2 op) {
      return this.foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$mcI$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldLeft$mcI$sp(z, op);
   }

   default Object foldLeft$mcI$sp(final Object z, final Function2 op) {
      return this.foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$mcD$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldRight$mcD$sp(z, op);
   }

   default Object foldRight$mcD$sp(final Object z, final Function2 op) {
      return this.foldRight(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$mcF$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldRight$mcF$sp(z, op);
   }

   default Object foldRight$mcF$sp(final Object z, final Function2 op) {
      return this.foldRight(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$mcI$sp$(final Vector $this, final Object z, final Function2 op) {
      return $this.foldRight$mcI$sp(z, op);
   }

   default Object foldRight$mcI$sp(final Object z, final Function2 op) {
      return this.foldRight(z, op);
   }

   // $FF: synthetic method
   static Object reduce$mcD$sp$(final Vector $this, final Function2 op) {
      return $this.reduce$mcD$sp(op);
   }

   default Object reduce$mcD$sp(final Function2 op) {
      return this.reduce(op);
   }

   // $FF: synthetic method
   static Object reduce$mcF$sp$(final Vector $this, final Function2 op) {
      return $this.reduce$mcF$sp(op);
   }

   default Object reduce$mcF$sp(final Function2 op) {
      return this.reduce(op);
   }

   // $FF: synthetic method
   static Object reduce$mcI$sp$(final Vector $this, final Function2 op) {
      return $this.reduce$mcI$sp(op);
   }

   default Object reduce$mcI$sp(final Function2 op) {
      return this.reduce(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$mcD$sp$(final Vector $this, final Function2 op) {
      return $this.reduceLeft$mcD$sp(op);
   }

   default Object reduceLeft$mcD$sp(final Function2 op) {
      return this.reduceLeft(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$mcF$sp$(final Vector $this, final Function2 op) {
      return $this.reduceLeft$mcF$sp(op);
   }

   default Object reduceLeft$mcF$sp(final Function2 op) {
      return this.reduceLeft(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$mcI$sp$(final Vector $this, final Function2 op) {
      return $this.reduceLeft$mcI$sp(op);
   }

   default Object reduceLeft$mcI$sp(final Function2 op) {
      return this.reduceLeft(op);
   }

   // $FF: synthetic method
   static Object reduceRight$mcD$sp$(final Vector $this, final Function2 op) {
      return $this.reduceRight$mcD$sp(op);
   }

   default Object reduceRight$mcD$sp(final Function2 op) {
      return this.reduceRight(op);
   }

   // $FF: synthetic method
   static Object reduceRight$mcF$sp$(final Vector $this, final Function2 op) {
      return $this.reduceRight$mcF$sp(op);
   }

   default Object reduceRight$mcF$sp(final Function2 op) {
      return this.reduceRight(op);
   }

   // $FF: synthetic method
   static Object reduceRight$mcI$sp$(final Vector $this, final Function2 op) {
      return $this.reduceRight$mcI$sp(op);
   }

   default Object reduceRight$mcI$sp(final Function2 op) {
      return this.reduceRight(op);
   }

   // $FF: synthetic method
   static Vector scan$mcD$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan$mcD$sp(z, op, cm, cm1);
   }

   default Vector scan$mcD$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return this.scan(z, op, cm, cm1);
   }

   // $FF: synthetic method
   static Vector scan$mcF$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan$mcF$sp(z, op, cm, cm1);
   }

   default Vector scan$mcF$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return this.scan(z, op, cm, cm1);
   }

   // $FF: synthetic method
   static Vector scan$mcI$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan$mcI$sp(z, op, cm, cm1);
   }

   default Vector scan$mcI$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return this.scan(z, op, cm, cm1);
   }

   // $FF: synthetic method
   static Vector scanLeft$mcD$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft$mcD$sp(z, op, cm1);
   }

   default Vector scanLeft$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanLeft(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanLeft$mcF$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft$mcF$sp(z, op, cm1);
   }

   default Vector scanLeft$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanLeft(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanLeft$mcI$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft$mcI$sp(z, op, cm1);
   }

   default Vector scanLeft$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanLeft(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanRight$mcD$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight$mcD$sp(z, op, cm1);
   }

   default Vector scanRight$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanRight(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanRight$mcF$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight$mcF$sp(z, op, cm1);
   }

   default Vector scanRight$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanRight(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanRight$mcI$sp$(final Vector $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight$mcI$sp(z, op, cm1);
   }

   default Vector scanRight$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanRight(z, op, cm1);
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$iterator$1(final Vector $this, final int i) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(i)), $this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static Object $anonfun$valuesIterator$1(final Vector $this, final int i) {
      return $this.apply(BoxesRunTime.boxToInteger(i));
   }

   static void $init$(final Vector $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
