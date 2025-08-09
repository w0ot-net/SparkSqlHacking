package spire.math;

import algebra.ring.Field;
import java.io.Serializable;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.ScalaNumericConversions;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015v!B&M\u0011\u0003\tf!B*M\u0011\u0003!\u0006\"\u00024\u0002\t\u00039\u0007b\u00025\u0002\u0005\u0004%)!\u001b\u0005\b\u0005'\t\u0001\u0015!\u0004k\u0011!\u0011)\"\u0001b\u0001\n\u000bI\u0007b\u0002B\f\u0003\u0001\u0006iA\u001b\u0005\b\u00053\tA1\u0001B\u000e\u0011\u001d\u0011I\"\u0001C\u0002\u0005CAqA!\u0007\u0002\t\u0007\u0011Y\u0003C\u0004\u0003\u001a\u0005!\u0019Aa\f\t\u000f\te\u0011\u0001b\u0001\u0003:!9!\u0011D\u0001\u0005\u0004\tu\u0002b\u0002B\r\u0003\u0011\r!\u0011\t\u0005\b\u00053\tA1\u0001B&\u0011\u001d\u0011I\"\u0001C\u0002\u0005+BqA!\u0007\u0002\t\u0003\u0011y\u0006\u0003\u0006\u0003v\u0005\u0011\r\u0011\"\u0001M\u0005oB\u0001B!\u001f\u0002A\u0003%!1\u0007\u0005\u000b\u0005w\n!\u0019!C\u0001\u0019\n]\u0004\u0002\u0003B?\u0003\u0001\u0006IAa\r\t\u0015\t}\u0014A1A\u0005\u00021\u00139\b\u0003\u0005\u0003\u0002\u0006\u0001\u000b\u0011\u0002B\u001a\u0011)\u0011\u0019)\u0001b\u0001\n\u0003a%q\u000f\u0005\t\u0005\u000b\u000b\u0001\u0015!\u0003\u00034!Q!qQ\u0001C\u0002\u0013\u0005AJ!#\t\u0011\t=\u0015\u0001)A\u0005\u0005\u0017C!B!%\u0002\u0005\u0004%\t\u0001\u0014BE\u0011!\u0011\u0019*\u0001Q\u0001\n\t-\u0005\"\u0003BK\u0003\u0005\u0005I\u0011\u0002BL\r\u001d\u0019F\n%A\u0002\"-Daa \u0010\u0005\u0002\u0005\u0005\u0001BBA\u0005=\u0019\u0005\u0011\u000eC\u0004\u0002\fy1\t!!\u0004\t\u000f\u0005UaD\"\u0001\u0002\u0018!9\u0011q\u0004\u0010\u0007\u0002\u0005]\u0001bBA\u0011=\u0019\u0005\u0011q\u0003\u0005\b\u0003Gqb\u0011AA\f\u0011\u001d\t)C\bD\u0001\u0003/Aq!a\n\u001f\r\u0003\t9\u0002\u0003\u0004\u0002*y1\t!\u001b\u0005\b\u0003Wqb\u0011AA\u0017\u0011\u001d\t)D\bD\u0001\u0003oAq!a\u0010\u001f\r\u0003\t\t\u0005C\u0004\u0002Jy1\t!a\u0013\t\u000f\u0005EcD\"\u0001\u0002T!9\u0011q\u000b\u0010\u0007\u0002\u0005e\u0003bBA/=\u0019\u0005\u0011q\f\u0005\b\u0003Grb\u0011AA3\u0011\u001d\tIG\bD\u0001\u0003WBq!a\u001c\u001f\r\u0003\t\t\b\u0003\u0005\u0002|y1\t\u0001TA?\u0011!\t\u0019I\bD\u0001\u0019\u0006\u0015\u0005\u0002CAE=\u0019\u0005A*a#\t\u0011\u0005=eD\"\u0001M\u0003#C\u0001\"!&\u001f\r\u0003a\u0015q\u0013\u0005\b\u00037sb\u0011AAO\u0011\u001d\t\tK\bC\u0003\u0003GCq!a*\u001f\r\u0003\tI\u000bC\u0004\u0002.z!\t!a,\t\u000f\u0005MfD\"\u0001\u00026\"9\u0011\u0011\u0018\u0010\u0005\u0002\u0005m\u0006bBA`=\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u000btBQAAd\u0011\u001d\tYM\bC\u0003\u0003\u001bDq!!5\u001f\t\u000b\t\u0019\u000eC\u0004\u0002Xz!)!!7\t\u000f\u0005ug\u0004\"\u0001\u0002`\"9\u00111\u001d\u0010\u0005\u0002\u0005\u0015\bbBAu=\u0011\u0005\u00111\u001e\u0005\b\u0003_tB\u0011AAy\u0011\u001d\t)P\bC\u0001\u0003oDa!a?\u001f\r\u0003I\u0007BBA\u007f=\u0019\u0005\u0011\u000e\u0003\u0004\u0002\u0000z1\t![\u0001\u0007\u001dVl'-\u001a:\u000b\u00055s\u0015\u0001B7bi\"T\u0011aT\u0001\u0006gBL'/Z\u0002\u0001!\t\u0011\u0016!D\u0001M\u0005\u0019qU/\u001c2feN!\u0011!V._!\t1\u0016,D\u0001X\u0015\u0005A\u0016!B:dC2\f\u0017B\u0001.X\u0005\u0019\te.\u001f*fMB\u0011!\u000bX\u0005\u0003;2\u0013qBT;nE\u0016\u0014\u0018J\\:uC:\u001cWm\u001d\t\u0003?\u0012l\u0011\u0001\u0019\u0006\u0003C\n\f!![8\u000b\u0003\r\fAA[1wC&\u0011Q\r\u0019\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\u000bAA_3s_V\t!\u000e\u0005\u0002S=M!a\u0004\\9u!\tiw.D\u0001o\u0015\tiu+\u0003\u0002q]\nY1kY1mC:+XNY3s!\ti'/\u0003\u0002t]\n92kY1mC:+X.\u001a:jG\u000e{gN^3sg&|gn\u001d\t\u0003kvt!A^>\u000f\u0005]TX\"\u0001=\u000b\u0005e\u0004\u0016A\u0002\u001fs_>$h(C\u0001Y\u0013\tax+A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0015t(B\u0001?X\u0003\u0019!\u0013N\\5uIQ\u0011\u00111\u0001\t\u0004-\u0006\u0015\u0011bAA\u0004/\n!QK\\5u\u0003\r\t'm]\u0001\u0007g&<g.^7\u0016\u0005\u0005=\u0001c\u0001,\u0002\u0012%\u0019\u00111C,\u0003\u0007%sG/A\u0005xSRD\u0017N\\%oiV\u0011\u0011\u0011\u0004\t\u0004-\u0006m\u0011bAA\u000f/\n9!i\\8mK\u0006t\u0017AC<ji\"Lg\u000eT8oO\u0006aq/\u001b;iS:$u.\u001e2mK\u0006A1-\u00198CK&sG/A\u0005dC:\u0014U\rT8oO\u00069\u0011n]#yC\u000e$\u0018\u0001D;oCJLx\fJ7j]V\u001c\u0018\u0001\u0003;p\u0005&<\u0017J\u001c;\u0016\u0005\u0005=\u0002cA;\u00022%\u0019\u00111\u0007@\u0003\r\tKw-\u00138u\u00031!xNQ5h\t\u0016\u001c\u0017.\\1m+\t\tI\u0004E\u0002v\u0003wI1!!\u0010\u007f\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\\\u0001\u000bi>\u0014\u0016\r^5p]\u0006dWCAA\"!\r\u0011\u0016QI\u0005\u0004\u0003\u000fb%\u0001\u0003*bi&|g.\u00197\u0002\u000b\u0011\u0002H.^:\u0015\u0007)\fi\u0005\u0003\u0004\u0002P1\u0002\rA[\u0001\u0004e\"\u001c\u0018A\u0002\u0013uS6,7\u000fF\u0002k\u0003+Ba!a\u0014.\u0001\u0004Q\u0017A\u0002\u0013nS:,8\u000fF\u0002k\u00037Ba!a\u0014/\u0001\u0004Q\u0017\u0001\u0002\u0013eSZ$2A[A1\u0011\u0019\tye\fa\u0001U\u0006)A/];piR\u0019!.a\u001a\t\r\u0005=\u0003\u00071\u0001k\u0003\u0011!Xn\u001c3\u0015\u0007)\fi\u0007\u0003\u0004\u0002PE\u0002\rA[\u0001\tiF,x\u000e^7pIR!\u00111OA=!\u00151\u0016Q\u000f6k\u0013\r\t9h\u0016\u0002\u0007)V\u0004H.\u001a\u001a\t\r\u0005=#\u00071\u0001k\u0003!\u0011x\fJ7j]V\u001cHc\u00016\u0002\u0000!1\u0011\u0011Q\u001aA\u0002)\f1\u0001\u001c5t\u0003\u0019\u0011x\f\n3jmR\u0019!.a\"\t\r\u0005\u0005E\u00071\u0001k\u0003\u001d\u0011x\f^9v_R$2A[AG\u0011\u0019\t\t)\u000ea\u0001U\u00061!o\u0018;n_\u0012$2A[AJ\u0011\u0019\t\tI\u000ea\u0001U\u0006Q!o\u0018;rk>$Xn\u001c3\u0015\t\u0005M\u0014\u0011\u0014\u0005\u0007\u0003\u0003;\u0004\u0019\u00016\u0002\u0007A|w\u000fF\u0002k\u0003?Ca!a\u00149\u0001\u0004Q\u0017\u0001\u0004\u0013uS6,7\u000f\n;j[\u0016\u001cHc\u00016\u0002&\"1\u0011qJ\u001dA\u0002)\f\u0011\u0002J3rI\u0015\fH%Z9\u0015\t\u0005e\u00111\u0016\u0005\u0007\u0003\u001fR\u0004\u0019\u00016\u0002\u0017\u0011*\u0017\u000f\n2b]\u001e$S-\u001d\u000b\u0005\u00033\t\t\f\u0003\u0004\u0002Pm\u0002\rA[\u0001\bG>l\u0007/\u0019:f)\u0011\ty!a.\t\r\u0005=C\b1\u0001k\u0003\ri\u0017N\u001c\u000b\u0004U\u0006u\u0006BBA({\u0001\u0007!.A\u0002nCb$2A[Ab\u0011\u0019\tyE\u0010a\u0001U\u0006)A\u0005\\3tgR!\u0011\u0011DAe\u0011\u0019\tye\u0010a\u0001U\u0006AA\u0005\\3tg\u0012*\u0017\u000f\u0006\u0003\u0002\u001a\u0005=\u0007BBA(\u0001\u0002\u0007!.\u0001\u0005%OJ,\u0017\r^3s)\u0011\tI\"!6\t\r\u0005=\u0013\t1\u0001k\u0003-!sM]3bi\u0016\u0014H%Z9\u0015\t\u0005e\u00111\u001c\u0005\u0007\u0003\u001f\u0012\u0005\u0019\u00016\u0002\t\u0011\nW\u000e\u001d\u000b\u0004U\u0006\u0005\bBBA(\u0007\u0002\u0007!.\u0001\u0003%E\u0006\u0014Hc\u00016\u0002h\"1\u0011q\n#A\u0002)\f1\u0001J;q)\rQ\u0017Q\u001e\u0005\u0007\u0003\u001f*\u0005\u0019\u00016\u0002\u0015\u0011bWm]:%Y\u0016\u001c8\u000fF\u0002k\u0003gDa!a\u0014G\u0001\u0004Q\u0017\u0001\u0005\u0013he\u0016\fG/\u001a:%OJ,\u0017\r^3s)\rQ\u0017\u0011 \u0005\u0007\u0003\u001f:\u0005\u0019\u00016\u0002\u000b\u0019dwn\u001c:\u0002\t\r,\u0017\u000e\\\u0001\u0006e>,h\u000eZ\u0015\n=\t\r!q\u0001B\u0006\u0005\u001fI1A!\u0002M\u00055!UmY5nC2tU/\u001c2fe&\u0019!\u0011\u0002'\u0003\u0017\u0019cw.\u0019;Ok6\u0014WM]\u0005\u0004\u0005\u001ba%!C%oi:+XNY3s\u0013\r\u0011\t\u0002\u0014\u0002\u000f%\u0006$\u0018n\u001c8bY:+XNY3s\u0003\u0015QXM]8!\u0003\ryg.Z\u0001\u0005_:,\u0007%A\u0003baBd\u0017\u0010F\u0002k\u0005;AqAa\b\b\u0001\u0004\ty!A\u0001o)\rQ'1\u0005\u0005\b\u0005?A\u0001\u0019\u0001B\u0013!\r1&qE\u0005\u0004\u0005S9&\u0001\u0002'p]\u001e$2A\u001bB\u0017\u0011\u001d\u0011y\"\u0003a\u0001\u0003_!2A\u001bB\u0019\u0011\u001d\u0011yB\u0003a\u0001\u0005g\u00012A\u0015B\u001b\u0013\r\u00119\u0004\u0014\u0002\t'\u00064W\rT8oOR\u0019!Na\u000f\t\u000f\t}1\u00021\u0001\u0002:Q\u0019!Na\u0010\t\u000f\t}A\u00021\u0001\u0002DQ\u0019!Na\u0011\t\u000f\t}Q\u00021\u0001\u0003FA\u0019!Ka\u0012\n\u0007\t%CJA\u0004OCR,(/\u00197\u0015\u0007)\u0014i\u0005C\u0004\u0003 9\u0001\rAa\u0014\u0011\u0007Y\u0013\t&C\u0002\u0003T]\u0013QA\u00127pCR$2A\u001bB,\u0011\u001d\u0011yb\u0004a\u0001\u00053\u00022A\u0016B.\u0013\r\u0011if\u0016\u0002\u0007\t>,(\r\\3\u0015\u0007)\u0014\t\u0007C\u0004\u0003dA\u0001\rA!\u001a\u0002\u0003M\u0004BAa\u001a\u0003p9!!\u0011\u000eB6!\t9x+C\u0002\u0003n]\u000ba\u0001\u0015:fI\u00164\u0017\u0002\u0002B9\u0005g\u0012aa\u0015;sS:<'b\u0001B7/\u00061Q.\u001b8J]R,\"Aa\r\u0002\u000f5Lg.\u00138uA\u00051Q.\u0019=J]R\fq!\\1y\u0013:$\b%A\u0004nS:duN\\4\u0002\u00115Lg\u000eT8oO\u0002\nq!\\1y\u0019>tw-\u0001\u0005nCbduN\\4!\u0003%i\u0017N\u001c#pk\ndW-\u0006\u0002\u0003\fB\u0019QN!$\n\u0007\u0005ub.\u0001\u0006nS:$u.\u001e2mK\u0002\n\u0011\"\\1y\t>,(\r\\3\u0002\u00155\f\u0007\u0010R8vE2,\u0007%\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u001aB!!1\u0014BQ\u001b\t\u0011iJC\u0002\u0003 \n\fA\u0001\\1oO&!!1\u0015BO\u0005\u0019y%M[3di\u0002"
)
public interface Number extends ScalaNumericConversions, Serializable {
   static Number apply(final String s) {
      return Number$.MODULE$.apply(s);
   }

   static Number apply(final double n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final float n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final Natural n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final Rational n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final BigDecimal n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final SafeLong n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final BigInt n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final long n) {
      return Number$.MODULE$.apply(n);
   }

   static Number apply(final int n) {
      return Number$.MODULE$.apply(n);
   }

   static Number one() {
      return Number$.MODULE$.one();
   }

   static Number zero() {
      return Number$.MODULE$.zero();
   }

   static Field NumberAlgebra() {
      return Number$.MODULE$.NumberAlgebra();
   }

   Number abs();

   int signum();

   boolean withinInt();

   boolean withinLong();

   boolean withinDouble();

   boolean canBeInt();

   boolean canBeLong();

   boolean isExact();

   Number unary_$minus();

   BigInt toBigInt();

   BigDecimal toBigDecimal();

   Rational toRational();

   Number $plus(final Number rhs);

   Number $times(final Number rhs);

   Number $minus(final Number rhs);

   Number $div(final Number rhs);

   Number tquot(final Number rhs);

   Number tmod(final Number rhs);

   Tuple2 tquotmod(final Number rhs);

   Number r_$minus(final Number lhs);

   Number r_$div(final Number lhs);

   Number r_tquot(final Number lhs);

   Number r_tmod(final Number lhs);

   Tuple2 r_tquotmod(final Number lhs);

   Number pow(final Number rhs);

   // $FF: synthetic method
   static Number $times$times$(final Number $this, final Number rhs) {
      return $this.$times$times(rhs);
   }

   default Number $times$times(final Number rhs) {
      return this.pow(rhs);
   }

   boolean $eq$eq$eq(final Number rhs);

   // $FF: synthetic method
   static boolean $eq$bang$eq$(final Number $this, final Number rhs) {
      return $this.$eq$bang$eq(rhs);
   }

   default boolean $eq$bang$eq(final Number rhs) {
      return !this.$eq$eq$eq(rhs);
   }

   int compare(final Number rhs);

   // $FF: synthetic method
   static Number min$(final Number $this, final Number rhs) {
      return $this.min(rhs);
   }

   default Number min(final Number rhs) {
      return this.$less(rhs) ? this : rhs;
   }

   // $FF: synthetic method
   static Number max$(final Number $this, final Number rhs) {
      return $this.max(rhs);
   }

   default Number max(final Number rhs) {
      return this.$greater(rhs) ? this : rhs;
   }

   // $FF: synthetic method
   static boolean $less$(final Number $this, final Number rhs) {
      return $this.$less(rhs);
   }

   default boolean $less(final Number rhs) {
      return this.compare(rhs) < 0;
   }

   // $FF: synthetic method
   static boolean $less$eq$(final Number $this, final Number rhs) {
      return $this.$less$eq(rhs);
   }

   default boolean $less$eq(final Number rhs) {
      return this.compare(rhs) <= 0;
   }

   // $FF: synthetic method
   static boolean $greater$(final Number $this, final Number rhs) {
      return $this.$greater(rhs);
   }

   default boolean $greater(final Number rhs) {
      return this.compare(rhs) > 0;
   }

   // $FF: synthetic method
   static boolean $greater$eq$(final Number $this, final Number rhs) {
      return $this.$greater$eq(rhs);
   }

   default boolean $greater$eq(final Number rhs) {
      return this.compare(rhs) >= 0;
   }

   // $FF: synthetic method
   static Number $amp$(final Number $this, final Number rhs) {
      return $this.$amp(rhs);
   }

   default Number $amp(final Number rhs) {
      throw new UnsupportedOperationException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this})));
   }

   // $FF: synthetic method
   static Number $bar$(final Number $this, final Number rhs) {
      return $this.$bar(rhs);
   }

   default Number $bar(final Number rhs) {
      throw new UnsupportedOperationException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this})));
   }

   // $FF: synthetic method
   static Number $up$(final Number $this, final Number rhs) {
      return $this.$up(rhs);
   }

   default Number $up(final Number rhs) {
      throw new UnsupportedOperationException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this})));
   }

   // $FF: synthetic method
   static Number $less$less$(final Number $this, final Number rhs) {
      return $this.$less$less(rhs);
   }

   default Number $less$less(final Number rhs) {
      throw new UnsupportedOperationException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this})));
   }

   // $FF: synthetic method
   static Number $greater$greater$(final Number $this, final Number rhs) {
      return $this.$greater$greater(rhs);
   }

   default Number $greater$greater(final Number rhs) {
      throw new UnsupportedOperationException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this})));
   }

   Number floor();

   Number ceil();

   Number round();

   static void $init$(final Number $this) {
   }
}
