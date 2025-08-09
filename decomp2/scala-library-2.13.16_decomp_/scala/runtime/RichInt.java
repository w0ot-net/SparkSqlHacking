package scala.runtime;

import scala.Proxy;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.math.Numeric;
import scala.math.Ordered;
import scala.math.Ordering;
import scala.math.ScalaNumericAnyConversions;
import scala.math.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t]d\u0001\u0002\u001d:\u0005yB\u0001\"\u0014\u0001\u0003\u0006\u0004%\tA\u0014\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005\u000f\")\u0001\u000b\u0001C\u0001#\")A\u000b\u0001C\t+\")a\r\u0001C\tO\")q\u000e\u0001C!a\")A\u000f\u0001C!k\")\u0011\u0010\u0001C!u\")a\u0010\u0001C!\u001d\"1q\u0010\u0001C!\u0003\u0003Aq!!\u0003\u0001\t\u0003\nY\u0001C\u0004\u0002\u0014\u0001!\t!!\u0006\t\u000f\u0005E\u0002\u0001\"\u0011\u0002\u0016!9\u00111\u0007\u0001\u0005\u0002\u0005U\u0001BBA\u001b\u0001\u0011\u0005c\nC\u0004\u00028\u0001!\t%!\u000f\t\u000f\u0005}\u0002\u0001\"\u0011\u0002B!1\u0011Q\t\u0001\u0005\u00029Cq!!\u0015\u0001\t\u0003\t\u0019\u0006C\u0004\u0002f\u0001!\t!a\u0015\t\u000f\u0005\u001d\u0004\u0001\"\u0001\u0002T\u00151\u0011\u0011\u000e\u0001\u0001\u0003WBq!a\u001f\u0001\t\u0003\ti\bC\u0004\u0002|\u0001!\t!a!\t\u000f\u0005-\u0005\u0001\"\u0001\u0002\u000e\"9\u00111\u0012\u0001\u0005\u0002\u0005}\u0005\"CAS\u0001\u0005\u0005I\u0011IAT\u0011%\tI\u000bAA\u0001\n\u0003\nYkB\u0005\u00028f\n\t\u0011#\u0001\u0002:\u001aA\u0001(OA\u0001\u0012\u0003\tY\f\u0003\u0004Q=\u0011\u0005\u00111\u0019\u0005\b\u0003\u000btBQAAd\u0011\u001d\tiM\bC\u0003\u0003\u001fDq!a5\u001f\t\u000b\t)\u000eC\u0004\u0002Zz!)!a7\t\u000f\u0005}g\u0004\"\u0002\u0002b\"9\u0011Q\u001d\u0010\u0005\u0006\u0005\u001d\bbBAv=\u0011\u0015\u0011Q\u001e\u0005\b\u0003ctBQAAz\u0011\u001d\t9P\bC\u0003\u0003sDq!a@\u001f\t\u000b\u0011\t\u0001C\u0004\u0003\u0006y!)Aa\u0002\t\u000f\t-a\u0004\"\u0002\u0003\u000e!9!\u0011\u0003\u0010\u0005\u0006\tM\u0001b\u0002B\u000e=\u0011\u0015!Q\u0004\u0005\b\u0005KqBQ\u0001B\u0014\u0011\u001d\u0011iC\bC\u0003\u0005_AqAa\r\u001f\t\u000b\u0011)\u0004C\u0004\u0003:y!)Aa\u000f\t\u000f\t}b\u0004\"\u0002\u0003B!9!q\b\u0010\u0005\u0006\t%\u0003b\u0002B*=\u0011\u0015!Q\u000b\u0005\b\u0005'rBQ\u0001B/\u0011%\u00119GHA\u0001\n\u000b\u0011I\u0007C\u0005\u0003ny\t\t\u0011\"\u0002\u0003p\t9!+[2i\u0013:$(B\u0001\u001e<\u0003\u001d\u0011XO\u001c;j[\u0016T\u0011\u0001P\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001qh\u0011&\u0011\u0005\u0001\u000bU\"A\u001e\n\u0005\t[$AB!osZ\u000bG\u000eE\u0002E\u000b\u001ek\u0011!O\u0005\u0003\rf\u0012\u0001cU2bY\u0006tU/\u001c2feB\u0013x\u000e_=\u0011\u0005\u0001C\u0015BA%<\u0005\rIe\u000e\u001e\t\u0004\t.;\u0015B\u0001':\u0005-\u0011\u0016M\\4fIB\u0013x\u000e_=\u0002\tM,GNZ\u000b\u0002\u000f\u0006)1/\u001a7gA\u00051A(\u001b8jiz\"\"AU*\u0011\u0005\u0011\u0003\u0001\"B'\u0004\u0001\u00049\u0015a\u00018v[V\taK\u0004\u0002XG:\u0011\u0001\f\u0019\b\u00033zs!AW/\u000e\u0003mS!\u0001X\u001f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0014BA0<\u0003\u0011i\u0017\r\u001e5\n\u0005\u0005\u0014\u0017a\u0002(v[\u0016\u0014\u0018n\u0019\u0006\u0003?nJ!\u0001Z3\u0002\u001b%sG/S:J]R,wM]1m\u0015\t\t'-A\u0002pe\u0012,\u0012\u0001\u001b\b\u0003S2t!\u0001\u00176\n\u0005-\u0014\u0017\u0001C(sI\u0016\u0014\u0018N\\4\n\u00055t\u0017aA%oi*\u00111NY\u0001\fI>,(\r\\3WC2,X-F\u0001r!\t\u0001%/\u0003\u0002tw\t1Ai\\;cY\u0016\f!B\u001a7pCR4\u0016\r\\;f+\u00051\bC\u0001!x\u0013\tA8HA\u0003GY>\fG/A\u0005m_:<g+\u00197vKV\t1\u0010\u0005\u0002Ay&\u0011Qp\u000f\u0002\u0005\u0019>tw-\u0001\u0005j]R4\u0016\r\\;f\u0003%\u0011\u0017\u0010^3WC2,X-\u0006\u0002\u0002\u0004A\u0019\u0001)!\u0002\n\u0007\u0005\u001d1H\u0001\u0003CsR,\u0017AC:i_J$h+\u00197vKV\u0011\u0011Q\u0002\t\u0004\u0001\u0006=\u0011bAA\tw\t)1\u000b[8si\u00069\u0011n],i_2,WCAA\f!\r\u0001\u0015\u0011D\u0005\u0004\u00037Y$a\u0002\"p_2,\u0017M\u001c\u0015\f\u0019\u0005}\u0011QEA\u0014\u0003W\ti\u0003E\u0002A\u0003CI1!a\t<\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\tI#A\u0015jg^Cw\u000e\\3!_:\u0004\u0013M\u001c\u0011j]R,w-\u001a:!if\u0004X\rI5tA\u0005dw/Y=tAQ\u0014X/Z\u0001\u0006g&t7-Z\u0011\u0003\u0003_\tqA\r\u00182e9\nT'\u0001\u0006jgZ\u000bG.\u001b3J]R\f1\"[:WC2LG\rT8oO\u0006\u0019\u0011MY:\u0002\u00075\f\u0007\u0010F\u0002H\u0003wAa!!\u0010\u0011\u0001\u00049\u0015\u0001\u0002;iCR\f1!\\5o)\r9\u00151\t\u0005\u0007\u0003{\t\u0002\u0019A$\u0002\u000bI|WO\u001c3)\u0017I\ty\"!\n\u0002J\u0005-\u0012QJ\u0011\u0003\u0003\u0017\n1\u000f\u001e5jg\u0002J7\u000fI1oA%tG/Z4fe\u0002\"\u0018\u0010]3<AQDWM]3!SN\u0004cn\u001c\u0011sK\u0006\u001cxN\u001c\u0011u_\u0002\u0012x.\u001e8eA%$h\u0006\t\u0011QKJD\u0017\r]:!s>,\b%\\3b]R\u0004Co\u001c\u0011dC2d\u0007\u0005\u001e5jg\u0002zg\u000eI1!M2|\u0017\r^5oO6\u0002x.\u001b8uAY\fG.^3@C\t\ty%\u0001\u00043]E\nd\u0006M\u0001\u000fi>\u0014\u0015N\\1ssN#(/\u001b8h+\t\t)\u0006\u0005\u0003\u0002X\u0005}c\u0002BA-\u00037\u0002\"AW\u001e\n\u0007\u0005u3(\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003C\n\u0019G\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003;Z\u0014a\u0003;p\u0011\u0016D8\u000b\u001e:j]\u001e\fQ\u0002^8PGR\fGn\u0015;sS:<'!\u0005*fgVdGoV5uQ>,Ho\u0015;faB!\u0011QNA<\u001b\t\tyG\u0003\u0003\u0002r\u0005M\u0014!C5n[V$\u0018M\u00197f\u0015\r\t)hO\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA=\u0003_\u0012QAU1oO\u0016\fQ!\u001e8uS2$B!a\u001b\u0002\u0000!1\u0011\u0011Q\fA\u0002\u001d\u000b1!\u001a8e)\u0019\tY'!\"\u0002\b\"1\u0011\u0011\u0011\rA\u0002\u001dCa!!#\u0019\u0001\u00049\u0015\u0001B:uKB\f!\u0001^8\u0015\t\u0005=\u0015Q\u0014\t\u0005\u0003#\u000b9J\u0004\u0003\u0002n\u0005M\u0015\u0002BAK\u0003_\nQAU1oO\u0016LA!!'\u0002\u001c\nI\u0011J\\2mkNLg/\u001a\u0006\u0005\u0003+\u000by\u0007\u0003\u0004\u0002\u0002f\u0001\ra\u0012\u000b\u0007\u0003\u001f\u000b\t+a)\t\r\u0005\u0005%\u00041\u0001H\u0011\u0019\tII\u0007a\u0001\u000f\u0006A\u0001.Y:i\u0007>$W\rF\u0001H\u0003\u0019)\u0017/^1mgR!\u0011qCAW\u0011%\ty\u000bHA\u0001\u0002\u0004\t\t,A\u0002yIE\u00022\u0001QAZ\u0013\r\t)l\u000f\u0002\u0004\u0003:L\u0018a\u0002*jG\"Le\u000e\u001e\t\u0003\tz\u00192AHA_!\r\u0001\u0015qX\u0005\u0004\u0003\u0003\\$AB!osJ+g\r\u0006\u0002\u0002:\u0006ia.^7%Kb$XM\\:j_:$2AVAe\u0011\u0019\tY\r\ta\u0001%\u0006)A\u0005\u001e5jg\u0006iqN\u001d3%Kb$XM\\:j_:$2\u0001[Ai\u0011\u0019\tY-\ta\u0001%\u0006)Bm\\;cY\u00164\u0016\r\\;fI\u0015DH/\u001a8tS>tGcA9\u0002X\"1\u00111\u001a\u0012A\u0002I\u000bAC\u001a7pCR4\u0016\r\\;fI\u0015DH/\u001a8tS>tGc\u0001<\u0002^\"1\u00111Z\u0012A\u0002I\u000b1\u0003\\8oOZ\u000bG.^3%Kb$XM\\:j_:$2a_Ar\u0011\u0019\tY\r\na\u0001%\u0006\u0011\u0012N\u001c;WC2,X\rJ3yi\u0016t7/[8o)\r9\u0015\u0011\u001e\u0005\u0007\u0003\u0017,\u0003\u0019\u0001*\u0002'\tLH/\u001a,bYV,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005\r\u0011q\u001e\u0005\u0007\u0003\u00174\u0003\u0019\u0001*\u0002)MDwN\u001d;WC2,X\rJ3yi\u0016t7/[8o)\u0011\ti!!>\t\r\u0005-w\u00051\u0001S\u0003EI7o\u00165pY\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003/\tY\u0010\u0003\u0004\u0002L\"\u0002\rA\u0015\u0015\fQ\u0005}\u0011QEA\u0014\u0003W\ti#\u0001\u000bjgZ\u000bG.\u001b3J]R$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003/\u0011\u0019\u0001\u0003\u0004\u0002L&\u0002\rAU\u0001\u0016SN4\u0016\r\\5e\u0019>tw\rJ3yi\u0016t7/[8o)\u0011\t9B!\u0003\t\r\u0005-'\u00061\u0001S\u00035\t'm\u001d\u0013fqR,gn]5p]R\u0019qIa\u0004\t\r\u0005-7\u00061\u0001S\u00035i\u0017\r\u001f\u0013fqR,gn]5p]R!!Q\u0003B\r)\r9%q\u0003\u0005\u0007\u0003{a\u0003\u0019A$\t\r\u0005-G\u00061\u0001S\u00035i\u0017N\u001c\u0013fqR,gn]5p]R!!q\u0004B\u0012)\r9%\u0011\u0005\u0005\u0007\u0003{i\u0003\u0019A$\t\r\u0005-W\u00061\u0001S\u0003=\u0011x.\u001e8eI\u0015DH/\u001a8tS>tGcA$\u0003*!1\u00111\u001a\u0018A\u0002IC3BLA\u0010\u0003K\tI%a\u000b\u0002N\u0005ABo\u001c\"j]\u0006\u0014\u0018p\u0015;sS:<G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005U#\u0011\u0007\u0005\u0007\u0003\u0017|\u0003\u0019\u0001*\u0002+Q|\u0007*\u001a=TiJLgn\u001a\u0013fqR,gn]5p]R!\u0011Q\u000bB\u001c\u0011\u0019\tY\r\ra\u0001%\u00069Bo\\(di\u0006d7\u000b\u001e:j]\u001e$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003+\u0012i\u0004\u0003\u0004\u0002LF\u0002\rAU\u0001\u0010k:$\u0018\u000e\u001c\u0013fqR,gn]5p]R!!1\tB$)\u0011\tYG!\u0012\t\r\u0005\u0005%\u00071\u0001H\u0011\u0019\tYM\ra\u0001%R!!1\nB))\u0019\tYG!\u0014\u0003P!1\u0011\u0011Q\u001aA\u0002\u001dCa!!#4\u0001\u00049\u0005BBAfg\u0001\u0007!+\u0001\u0007u_\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003X\tmC\u0003BAH\u00053Ba!!!5\u0001\u00049\u0005BBAfi\u0001\u0007!\u000b\u0006\u0003\u0003`\t\u0015DCBAH\u0005C\u0012\u0019\u0007\u0003\u0004\u0002\u0002V\u0002\ra\u0012\u0005\u0007\u0003\u0013+\u0004\u0019A$\t\r\u0005-W\u00071\u0001S\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005\u001d&1\u000e\u0005\u0007\u0003\u00174\u0004\u0019\u0001*\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tG\u0003\u0002B9\u0005k\"B!a\u0006\u0003t!I\u0011qV\u001c\u0002\u0002\u0003\u0007\u0011\u0011\u0017\u0005\u0007\u0003\u0017<\u0004\u0019\u0001*"
)
public final class RichInt implements ScalaNumberProxy, RangedProxy {
   private final int self;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return RichInt$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.hashCode($this);
   }

   public static Range.Inclusive to$extension(final int $this, final int end, final int step) {
      RichInt$ var10000 = RichInt$.MODULE$;
      Range$ var3 = Range$.MODULE$;
      return new Range.Inclusive($this, end, step);
   }

   public static Range.Inclusive to$extension(final int $this, final int end) {
      RichInt$ var10000 = RichInt$.MODULE$;
      Range$ var2 = Range$.MODULE$;
      return new Range.Inclusive($this, end, 1);
   }

   public static Range until$extension(final int $this, final int end, final int step) {
      RichInt$ var10000 = RichInt$.MODULE$;
      Range$ var3 = Range$.MODULE$;
      return new Range.Exclusive($this, end, step);
   }

   public static Range until$extension(final int $this, final int end) {
      RichInt$ var10000 = RichInt$.MODULE$;
      Range$ var2 = Range$.MODULE$;
      return new Range.Exclusive($this, end, 1);
   }

   public static String toOctalString$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.toOctalString($this);
   }

   public static String toHexString$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.toHexString($this);
   }

   public static String toBinaryString$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.toBinaryString($this);
   }

   /** @deprecated */
   public static int round$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return $this;
   }

   public static int min$extension(final int $this, final int that) {
      RichInt$ var10000 = RichInt$.MODULE$;
      package$ var2 = package$.MODULE$;
      return Math.min($this, that);
   }

   public static int max$extension(final int $this, final int that) {
      RichInt$ var10000 = RichInt$.MODULE$;
      package$ var2 = package$.MODULE$;
      return Math.max($this, that);
   }

   public static int abs$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      package$ var1 = package$.MODULE$;
      return Math.abs($this);
   }

   public static boolean isValidLong$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return true;
   }

   public static boolean isValidInt$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return true;
   }

   /** @deprecated */
   public static boolean isWhole$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return true;
   }

   public static short shortValue$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (short)$this;
   }

   public static byte byteValue$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (byte)$this;
   }

   public static int intValue$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return $this;
   }

   public static long longValue$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (long)$this;
   }

   public static float floatValue$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (float)$this;
   }

   public static double doubleValue$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (double)$this;
   }

   public static Ordering.Int$ ord$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Ordering.Int$.MODULE$;
   }

   public static Numeric.IntIsIntegral$ num$extension(final int $this) {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Numeric.IntIsIntegral$.MODULE$;
   }

   public Object sign() {
      return ScalaNumberProxy.sign$(this);
   }

   /** @deprecated */
   public int signum() {
      return ScalaNumberProxy.signum$(this);
   }

   public int compare(final Object y) {
      return OrderedProxy.compare$(this, y);
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public String toString() {
      return Proxy.toString$(this);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public int toInt() {
      return ScalaNumericAnyConversions.toInt$(this);
   }

   public long toLong() {
      return ScalaNumericAnyConversions.toLong$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public boolean isValidByte() {
      return ScalaNumericAnyConversions.isValidByte$(this);
   }

   public boolean isValidShort() {
      return ScalaNumericAnyConversions.isValidShort$(this);
   }

   public boolean isValidChar() {
      return ScalaNumericAnyConversions.isValidChar$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public int self() {
      return this.self;
   }

   public Numeric.IntIsIntegral$ num() {
      RichInt$ var10000 = RichInt$.MODULE$;
      this.self();
      return Numeric.IntIsIntegral$.MODULE$;
   }

   public Ordering.Int$ ord() {
      RichInt$ var10000 = RichInt$.MODULE$;
      this.self();
      return Ordering.Int$.MODULE$;
   }

   public double doubleValue() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (double)this.self();
   }

   public float floatValue() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (float)this.self();
   }

   public long longValue() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (long)this.self();
   }

   public int intValue() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return this.self();
   }

   public byte byteValue() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (byte)this.self();
   }

   public short shortValue() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return (short)this.self();
   }

   /** @deprecated */
   public boolean isWhole() {
      RichInt$ var10000 = RichInt$.MODULE$;
      this.self();
      return true;
   }

   public boolean isValidInt() {
      RichInt$ var10000 = RichInt$.MODULE$;
      this.self();
      return true;
   }

   public boolean isValidLong() {
      RichInt$ var10000 = RichInt$.MODULE$;
      this.self();
      return true;
   }

   public int abs() {
      RichInt$ var10000 = RichInt$.MODULE$;
      int abs$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return Math.abs(abs$extension_$this);
   }

   public int max(final int that) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int max$extension_$this = this.self();
      package$ var3 = package$.MODULE$;
      return Math.max(max$extension_$this, that);
   }

   public int min(final int that) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int min$extension_$this = this.self();
      package$ var3 = package$.MODULE$;
      return Math.min(min$extension_$this, that);
   }

   /** @deprecated */
   public int round() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return this.self();
   }

   public String toBinaryString() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.toBinaryString(this.self());
   }

   public String toHexString() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.toHexString(this.self());
   }

   public String toOctalString() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.toOctalString(this.self());
   }

   public Range until(final int end) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int until$extension_$this = this.self();
      Range$ var3 = Range$.MODULE$;
      return new Range.Exclusive(until$extension_$this, end, 1);
   }

   public Range until(final int end, final int step) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int until$extension_$this = this.self();
      Range$ var4 = Range$.MODULE$;
      return new Range.Exclusive(until$extension_$this, end, step);
   }

   public Range.Inclusive to(final int end) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int to$extension_$this = this.self();
      Range$ var3 = Range$.MODULE$;
      return new Range.Inclusive(to$extension_$this, end, 1);
   }

   public Range.Inclusive to(final int end, final int step) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int to$extension_$this = this.self();
      Range$ var4 = Range$.MODULE$;
      return new Range.Inclusive(to$extension_$this, end, step);
   }

   public int hashCode() {
      RichInt$ var10000 = RichInt$.MODULE$;
      return Integer.hashCode(this.self());
   }

   public boolean equals(final Object x$1) {
      return RichInt$.MODULE$.equals$extension(this.self(), x$1);
   }

   public RichInt(final int self) {
      this.self = self;
   }
}
