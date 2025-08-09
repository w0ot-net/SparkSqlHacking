package spire.algebra.free;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tEf\u0001B\u0011#\u0005%B\u0001\"\r\u0001\u0003\u0006\u0004%\tA\r\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005g!aQ\n\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u0005\u001d\")!\u000b\u0001C\u0001'\")\u0001\u000e\u0001C\u0001S\")q\u000f\u0001C\u0001q\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\u0012\u0001\u0011\u0005\u0011Q\u0005\u0005\b\u0003w\u0001A\u0011AA\u001f\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bBq!!\u0013\u0001\t\u0003\tY\u0005C\u0004\u0002N\u0001!\t%a\u0014\t\u0013\u0005]\u0003!!A\u0005B\u0005e\u0003\"CA.\u0001\u0005\u0005I\u0011IA/\u000f\u001d\tIG\tE\u0001\u0003W2a!\t\u0012\t\u0002\u00055\u0004BB'\u0011\t\u0003\t)\bC\u0004\u0002xA!)!!\u001f\t\u000f\u0005\r\u0005\u0003\"\u0002\u0002\u0006\"9\u00111\u0013\t\u0005\u0006\u0005U\u0005bBAQ!\u0011\r\u00111\u0015\u0005\b\u0003_\u0003BQAAY\u0011\u001d\ty\r\u0005C\u0003\u0003#Dq!a<\u0011\t\u000b\t\t\u0010C\u0004\u0003\u0010A!)A!\u0005\t\u000f\t=\u0002\u0003\"\u0002\u00032!9!\u0011\u000b\t\u0005\u0006\tM\u0003b\u0002B2!\u0011\u0015!Q\r\u0005\b\u0005k\u0002BQ\u0001B<\u0011\u001d\u0011\u0019\t\u0005C\u0003\u0005\u000bC\u0011B!%\u0011\u0003\u0003%)Aa%\t\u0013\t}\u0005#!A\u0005\u0006\t\u0005&a\u0003$sK\u0016\f%m\u0012:pkBT!a\t\u0013\u0002\t\u0019\u0014X-\u001a\u0006\u0003K\u0019\nq!\u00197hK\n\u0014\u0018MC\u0001(\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"A\u000b!\u0014\u0005\u0001Y\u0003C\u0001\u00170\u001b\u0005i#\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj#AB!osZ\u000bG.A\u0003uKJl7/F\u00014!\u0011!4HP%\u000f\u0005UJ\u0004C\u0001\u001c.\u001b\u00059$B\u0001\u001d)\u0003\u0019a$o\\8u}%\u0011!(L\u0001\u0007!J,G-\u001a4\n\u0005qj$aA'ba*\u0011!(\f\t\u0003\u007f\u0001c\u0001\u0001B\u0003B\u0001\t\u0007!IA\u0001B#\t\u0019e\t\u0005\u0002-\t&\u0011Q)\f\u0002\b\u001d>$\b.\u001b8h!\tas)\u0003\u0002I[\t\u0019\u0011I\\=\u0011\u00051R\u0015BA&.\u0005\rIe\u000e^\u0001\u0007i\u0016\u0014Xn\u001d\u0011\u0002\rqJg.\u001b;?)\ty\u0015\u000bE\u0002Q\u0001yj\u0011A\t\u0005\u0006c\r\u0001\raM\u0001\u0004eVtWC\u0001+X)\t)6\r\u0006\u0002W3B\u0011qh\u0016\u0003\u00061\u0012\u0011\rA\u0011\u0002\u0002\u0005\")!\f\u0002a\u00027\u0006\t!\tE\u0002]AZs!!\u00180\u000e\u0003\u0011J!a\u0018\u0013\u0002\u000fA\f7m[1hK&\u0011\u0011M\u0019\u0002\b\u0003\n<%o\\;q\u0015\tyF\u0005C\u0003e\t\u0001\u0007Q-A\u0001g!\u0011acM\u0010,\n\u0005\u001dl#!\u0003$v]\u000e$\u0018n\u001c82\u0003%\u0011XO\\'p]>LG-\u0006\u0002kaR\u00111.\u001e\u000b\u0003YF\u00042\u0001L7p\u0013\tqWF\u0001\u0004PaRLwN\u001c\t\u0003\u007fA$Q\u0001W\u0003C\u0002\tCQAW\u0003A\u0004I\u00042\u0001X:p\u0013\t!(MA\u0004D\u001b>tw.\u001b3\t\u000b\u0011,\u0001\u0019\u0001<\u0011\t12gh\\\u0001\reVt7+Z7jOJ|W\u000f]\u000b\u0003sv$2A_A\u0003)\tYh\u0010E\u0002-[r\u0004\"aP?\u0005\u000ba3!\u0019\u0001\"\t\u000bi3\u00019A@\u0011\tq\u000b\t\u0001`\u0005\u0004\u0003\u0007\u0011'AC\"TK6LwM]8va\"1AM\u0002a\u0001\u0003\u000f\u0001B\u0001\f4?y\u0006)1\u000f\u001d7jiV!\u0011QBA\r)\u0011\ty!a\b\u0015\t\u0005E\u00111\u0004\t\bY\u0005M\u0011qCA\f\u0013\r\t)\"\f\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007}\nI\u0002B\u0003Y\u000f\t\u0007!\t\u0003\u0004[\u000f\u0001\u000f\u0011Q\u0004\t\u00059N\f9\u0002\u0003\u0004e\u000f\u0001\u0007\u0011\u0011\u0005\t\u0006Y\u0019t\u0014qC\u0001\u000fgBd\u0017\u000e^*f[&<'o\\;q+\u0011\t9#!\r\u0015\t\u0005%\u0012q\u0007\u000b\u0005\u0003W\t\u0019\u0004E\u0004-\u0003'\ti#!\f\u0011\t1j\u0017q\u0006\t\u0004\u007f\u0005EB!\u0002-\t\u0005\u0004\u0011\u0005B\u0002.\t\u0001\b\t)\u0004E\u0003]\u0003\u0003\ty\u0003\u0003\u0004e\u0011\u0001\u0007\u0011\u0011\b\t\u0006Y\u0019t\u0014qF\u0001\u000eI\t\f'\u000f\n9mkN$#-\u0019:\u0015\u0007=\u000by\u0004\u0003\u0004\u0002B%\u0001\raT\u0001\u0004e\"\u001c\u0018A\u0004\u0013cCJ$S.\u001b8vg\u0012\u0012\u0017M\u001d\u000b\u0004\u001f\u0006\u001d\u0003BBA!\u0015\u0001\u0007q*A\u0004j]Z,'o]3\u0016\u0003=\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003#\u00022\u0001NA*\u0013\r\t)&\u0010\u0002\u0007'R\u0014\u0018N\\4\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!S\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005}\u0013Q\r\t\u0004Y\u0005\u0005\u0014bAA2[\t9!i\\8mK\u0006t\u0007\u0002CA4\u001d\u0005\u0005\t\u0019\u0001$\u0002\u0007a$\u0013'A\u0006Ge\u0016,\u0017IY$s_V\u0004\bC\u0001)\u0011'\r\u0001\u0012q\u000e\t\u0004Y\u0005E\u0014bAA:[\t1\u0011I\\=SK\u001a$\"!a\u001b\u0002\u0005%$W\u0003BA>\u0003\u0003+\"!! \u0011\tA\u0003\u0011q\u0010\t\u0004\u007f\u0005\u0005E!B!\u0013\u0005\u0004\u0011\u0015!B1qa2LX\u0003BAD\u0003\u001b#B!!#\u0002\u0010B!\u0001\u000bAAF!\ry\u0014Q\u0012\u0003\u0006\u0003N\u0011\rA\u0011\u0005\b\u0003#\u001b\u0002\u0019AAF\u0003\u0005\t\u0017\u0001\u00027jMR,B!a&\u0002\u001eR!\u0011\u0011TAP!\u0011\u0001\u0006!a'\u0011\u0007}\ni\nB\u0003B)\t\u0007!\tC\u0004\u0002\u0012R\u0001\r!a'\u0002!\u0019\u0013X-Z!c\u000fJ|W\u000f]$s_V\u0004X\u0003BAS\u0003[+\"!a*\u0011\tq\u0003\u0017\u0011\u0016\t\u0005!\u0002\tY\u000bE\u0002@\u0003[#Q!Q\u000bC\u0002\t\u000bQB];oI\u0015DH/\u001a8tS>tWCBAZ\u0003w\u000b9\r\u0006\u0003\u00026\u0006%G\u0003BA\\\u0003\u0003$B!!/\u0002>B\u0019q(a/\u0005\u000ba3\"\u0019\u0001\"\t\ri3\u00029AA`!\u0011a\u0006-!/\t\r\u00114\u0002\u0019AAb!\u0019ac-!2\u0002:B\u0019q(a2\u0005\u000b\u00053\"\u0019\u0001\"\t\u000f\u0005-g\u00031\u0001\u0002N\u0006)A\u0005\u001e5jgB!\u0001\u000bAAc\u0003M\u0011XO\\'p]>LG\rJ3yi\u0016t7/[8o+\u0019\t\u0019.!8\u0002jR!\u0011Q[Av)\u0011\t9.a9\u0015\t\u0005e\u0017q\u001c\t\u0005Y5\fY\u000eE\u0002@\u0003;$Q\u0001W\fC\u0002\tCaAW\fA\u0004\u0005\u0005\b\u0003\u0002/t\u00037Da\u0001Z\fA\u0002\u0005\u0015\bC\u0002\u0017g\u0003O\fY\u000eE\u0002@\u0003S$Q!Q\fC\u0002\tCq!a3\u0018\u0001\u0004\ti\u000f\u0005\u0003Q\u0001\u0005\u001d\u0018A\u0006:v]N+W.[4s_V\u0004H%\u001a=uK:\u001c\u0018n\u001c8\u0016\r\u0005M\u0018Q B\u0005)\u0011\t)Pa\u0003\u0015\t\u0005](1\u0001\u000b\u0005\u0003s\fy\u0010\u0005\u0003-[\u0006m\bcA \u0002~\u0012)\u0001\f\u0007b\u0001\u0005\"1!\f\u0007a\u0002\u0005\u0003\u0001R\u0001XA\u0001\u0003wDa\u0001\u001a\rA\u0002\t\u0015\u0001C\u0002\u0017g\u0005\u000f\tY\u0010E\u0002@\u0005\u0013!Q!\u0011\rC\u0002\tCq!a3\u0019\u0001\u0004\u0011i\u0001\u0005\u0003Q\u0001\t\u001d\u0011aD:qY&$H%\u001a=uK:\u001c\u0018n\u001c8\u0016\r\tM!Q\u0004B\u0015)\u0011\u0011)Ba\u000b\u0015\t\t]!1\u0005\u000b\u0005\u00053\u0011y\u0002E\u0004-\u0003'\u0011YBa\u0007\u0011\u0007}\u0012i\u0002B\u0003Y3\t\u0007!\t\u0003\u0004[3\u0001\u000f!\u0011\u0005\t\u00059N\u0014Y\u0002\u0003\u0004e3\u0001\u0007!Q\u0005\t\u0007Y\u0019\u00149Ca\u0007\u0011\u0007}\u0012I\u0003B\u0003B3\t\u0007!\tC\u0004\u0002Lf\u0001\rA!\f\u0011\tA\u0003!qE\u0001\u0019gBd\u0017\u000e^*f[&<'o\\;qI\u0015DH/\u001a8tS>tWC\u0002B\u001a\u0005\u007f\u0011Y\u0005\u0006\u0003\u00036\t5C\u0003\u0002B\u001c\u0005\u000b\"BA!\u000f\u0003BA9A&a\u0005\u0003<\tm\u0002\u0003\u0002\u0017n\u0005{\u00012a\u0010B \t\u0015A&D1\u0001C\u0011\u0019Q&\u0004q\u0001\u0003DA)A,!\u0001\u0003>!1AM\u0007a\u0001\u0005\u000f\u0002b\u0001\f4\u0003J\tu\u0002cA \u0003L\u0011)\u0011I\u0007b\u0001\u0005\"9\u00111\u001a\u000eA\u0002\t=\u0003\u0003\u0002)\u0001\u0005\u0013\nq\u0003\n2be\u0012\u0002H.^:%E\u0006\u0014H%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\tU#Q\f\u000b\u0005\u0005/\u0012\t\u0007\u0006\u0003\u0003Z\t}\u0003\u0003\u0002)\u0001\u00057\u00022a\u0010B/\t\u0015\t5D1\u0001C\u0011\u001d\t\te\u0007a\u0001\u00053Bq!a3\u001c\u0001\u0004\u0011I&\u0001\r%E\u0006\u0014H%\\5okN$#-\u0019:%Kb$XM\\:j_:,BAa\u001a\u0003pQ!!\u0011\u000eB:)\u0011\u0011YG!\u001d\u0011\tA\u0003!Q\u000e\t\u0004\u007f\t=D!B!\u001d\u0005\u0004\u0011\u0005bBA!9\u0001\u0007!1\u000e\u0005\b\u0003\u0017d\u0002\u0019\u0001B6\u0003EIgN^3sg\u0016$S\r\u001f;f]NLwN\\\u000b\u0005\u0005s\u0012y\b\u0006\u0003\u0003|\t\u0005\u0005\u0003\u0002)\u0001\u0005{\u00022a\u0010B@\t\u0015\tUD1\u0001C\u0011\u001d\tY-\ba\u0001\u0005w\n!\u0003^8TiJLgn\u001a\u0013fqR,gn]5p]V!!q\u0011BH)\u0011\tyE!#\t\u000f\u0005-g\u00041\u0001\u0003\fB!\u0001\u000b\u0001BG!\ry$q\u0012\u0003\u0006\u0003z\u0011\rAQ\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g.\u0006\u0003\u0003\u0016\nuE\u0003BA-\u0005/Cq!a3 \u0001\u0004\u0011I\n\u0005\u0003Q\u0001\tm\u0005cA \u0003\u001e\u0012)\u0011i\bb\u0001\u0005\u0006\u0001R-];bYN$S\r\u001f;f]NLwN\\\u000b\u0005\u0005G\u0013y\u000b\u0006\u0003\u0003&\n%F\u0003BA0\u0005OC\u0001\"a\u001a!\u0003\u0003\u0005\rA\u0012\u0005\b\u0003\u0017\u0004\u0003\u0019\u0001BV!\u0011\u0001\u0006A!,\u0011\u0007}\u0012y\u000bB\u0003BA\t\u0007!\t"
)
public final class FreeAbGroup {
   private final Map terms;

   public static boolean equals$extension(final Map $this, final Object x$1) {
      return FreeAbGroup$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final Map $this) {
      return FreeAbGroup$.MODULE$.hashCode$extension($this);
   }

   public static String toString$extension(final Map $this) {
      return FreeAbGroup$.MODULE$.toString$extension($this);
   }

   public static Map inverse$extension(final Map $this) {
      return FreeAbGroup$.MODULE$.inverse$extension($this);
   }

   public static Map $bar$minus$bar$extension(final Map $this, final Map rhs) {
      return FreeAbGroup$.MODULE$.$bar$minus$bar$extension($this, rhs);
   }

   public static Map $bar$plus$bar$extension(final Map $this, final Map rhs) {
      return FreeAbGroup$.MODULE$.$bar$plus$bar$extension($this, rhs);
   }

   public static Tuple2 splitSemigroup$extension(final Map $this, final Function1 f, final CommutativeSemigroup B) {
      return FreeAbGroup$.MODULE$.splitSemigroup$extension($this, f, B);
   }

   public static Tuple2 split$extension(final Map $this, final Function1 f, final CommutativeMonoid B) {
      return FreeAbGroup$.MODULE$.split$extension($this, f, B);
   }

   public static Option runSemigroup$extension(final Map $this, final Function1 f, final CommutativeSemigroup B) {
      return FreeAbGroup$.MODULE$.runSemigroup$extension($this, f, B);
   }

   public static Option runMonoid$extension(final Map $this, final Function1 f, final CommutativeMonoid B) {
      return FreeAbGroup$.MODULE$.runMonoid$extension($this, f, B);
   }

   public static Object run$extension(final Map $this, final Function1 f, final CommutativeGroup B) {
      return FreeAbGroup$.MODULE$.run$extension($this, f, B);
   }

   public static CommutativeGroup FreeAbGroupGroup() {
      return FreeAbGroup$.MODULE$.FreeAbGroupGroup();
   }

   public static Map lift(final Object a) {
      return FreeAbGroup$.MODULE$.lift(a);
   }

   public static Map apply(final Object a) {
      return FreeAbGroup$.MODULE$.apply(a);
   }

   public static Map id() {
      return FreeAbGroup$.MODULE$.id();
   }

   public Map terms() {
      return this.terms;
   }

   public Object run(final Function1 f, final CommutativeGroup B) {
      return FreeAbGroup$.MODULE$.run$extension(this.terms(), f, B);
   }

   public Option runMonoid(final Function1 f, final CommutativeMonoid B) {
      return FreeAbGroup$.MODULE$.runMonoid$extension(this.terms(), f, B);
   }

   public Option runSemigroup(final Function1 f, final CommutativeSemigroup B) {
      return FreeAbGroup$.MODULE$.runSemigroup$extension(this.terms(), f, B);
   }

   public Tuple2 split(final Function1 f, final CommutativeMonoid B) {
      return FreeAbGroup$.MODULE$.split$extension(this.terms(), f, B);
   }

   public Tuple2 splitSemigroup(final Function1 f, final CommutativeSemigroup B) {
      return FreeAbGroup$.MODULE$.splitSemigroup$extension(this.terms(), f, B);
   }

   public Map $bar$plus$bar(final Map rhs) {
      return FreeAbGroup$.MODULE$.$bar$plus$bar$extension(this.terms(), rhs);
   }

   public Map $bar$minus$bar(final Map rhs) {
      return FreeAbGroup$.MODULE$.$bar$minus$bar$extension(this.terms(), rhs);
   }

   public Map inverse() {
      return FreeAbGroup$.MODULE$.inverse$extension(this.terms());
   }

   public String toString() {
      return FreeAbGroup$.MODULE$.toString$extension(this.terms());
   }

   public int hashCode() {
      return FreeAbGroup$.MODULE$.hashCode$extension(this.terms());
   }

   public boolean equals(final Object x$1) {
      return FreeAbGroup$.MODULE$.equals$extension(this.terms(), x$1);
   }

   public FreeAbGroup(final Map terms) {
      this.terms = terms;
   }
}
