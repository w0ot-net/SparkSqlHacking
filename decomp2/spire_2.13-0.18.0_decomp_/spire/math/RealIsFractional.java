package spire.math;

import algebra.ring.TruncatedDivision;
import java.math.MathContext;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.Trig;
import spire.math.poly.RootFinder$;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\r%baB\"E!\u0003\r\t!\u0013\u0005\u0006c\u0002!\tA\u001d\u0005\u0006m\u0002!\ta\u001e\u0005\u0006s\u0002!\tE\u001f\u0005\u0006{\u0002!\tE \u0005\b\u0003\u000f\u0001A\u0011IA\u0005\u0011\u001d\t9\u0002\u0001C\u0001\u00033Aq!a\b\u0001\t\u0003\t\t\u0003C\u0004\u0002$\u0001!\t!!\t\t\u000f\u0005\u0015\u0002\u0001\"\u0001\u0002(!9\u00111\u0006\u0001\u0005\u0002\u00055\u0002bBA\u001a\u0001\u0011\u0005\u0013Q\u0007\u0005\b\u0003w\u0001A\u0011AA\u001f\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bBq!!\u001a\u0001\t\u0003\t9\u0007C\u0004\u0002n\u0001!\t!a\u001c\t\u000f\u0005U\u0004\u0001\"\u0011\u0002x!9\u00111\u0010\u0001\u0005\u0002\u0005u\u0004bBAB\u0001\u0011\u0005\u0013Q\u0011\u0005\b\u0003\u0013\u0003A\u0011AAF\u0011\u001d\t\u0019\n\u0001C\u0001\u0003+Cq!a'\u0001\t\u0003\ti\nC\u0004\u0002$\u0002!\t!!*\t\u000f\u0005%\u0006\u0001\"\u0001\u0002,\"9\u0011q\u0016\u0001\u0005\u0002\u0005E\u0006bBA\\\u0001\u0011\u0005\u0011\u0011\u0018\u0005\b\u0003{\u0003A\u0011AA`\u0011\u001d\t\u0019\r\u0001C\u0001\u0003CAq!!2\u0001\t\u0003\t9\rC\u0004\u0002L\u0002!\t!!4\t\u000f\u0005E\u0007\u0001\"\u0001\u0002T\"9\u0011q\u001b\u0001\u0005\u0002\u0005e\u0007bBAo\u0001\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003?\u0004A\u0011AAq\u0011\u001d\t)\u000f\u0001C\u0001\u0003ODq!a;\u0001\t\u0003\ti\u000fC\u0004\u0002r\u0002!\t!a=\t\u000f\u0005]\b\u0001\"\u0001\u0002z\"9\u0011Q \u0001\u0005\u0002\u0005}\bb\u0002B\u0002\u0001\u0011\u0005!Q\u0001\u0005\b\u0005\u0013\u0001A\u0011\u0001B\u0006\u0011\u001d\u0011y\u0001\u0001C\u0001\u0005#AqA!\u0006\u0001\t\u0003\u00119\u0002C\u0004\u0003\u001c\u0001!\tA!\b\t\u000f\t\u001d\u0002\u0001\"\u0001\u0003*!9!Q\u0006\u0001\u0005\u0002\t=\u0002b\u0002B\u001d\u0001\u0011\u0005!1\b\u0005\b\u0005\u000b\u0002A\u0011\u0001B$\u0011\u001d\u0011\t\u0006\u0001C\u0001\u0005'BqA!\u0018\u0001\t\u0003\u0011y\u0006C\u0004\u0003d\u0001!\tA!\u001a\t\u000f\t=\u0004\u0001\"\u0001\u0003r!9!1\u0010\u0001\u0005\u0002\tu\u0004b\u0002BD\u0001\u0011\u0005!\u0011\u0012\u0005\b\u0005\u001b\u0003A\u0011\u0001BH\u0011\u001d\u0011I\n\u0001C\u0001\u00057CqAa,\u0001\t\u0003\u0011\t\fC\u0004\u0003Z\u0002!\tAa7\t\u000f\t\u0005\b\u0001\"\u0001\u0003d\"9!q\u001d\u0001\u0005\u0002\t%\bb\u0002Bw\u0001\u0011\u0005!q\u001e\u0005\b\u0005g\u0004A\u0011\tB{\u0011\u001d\u0011I\u0010\u0001C\u0001\u0005wDqAa@\u0001\t\u0003\u0019\t\u0001C\u0004\u0004\u0006\u0001!\taa\u0002\t\u000f\r-\u0001\u0001\"\u0001\u0004\u000e!91\u0011\u0003\u0001\u0005\u0002\rM!\u0001\u0005*fC2L5O\u0012:bGRLwN\\1m\u0015\t)e)\u0001\u0003nCRD'\"A$\u0002\u000bM\u0004\u0018N]3\u0004\u0001M9\u0001A\u0013)XO.t\u0007CA&O\u001b\u0005a%\"A'\u0002\u000bM\u001c\u0017\r\\1\n\u0005=c%AB!osJ+g\rE\u0002R%Rk\u0011\u0001R\u0005\u0003'\u0012\u0013!B\u0012:bGRLwN\\1m!\t\tV+\u0003\u0002W\t\n!!+Z1m!\rAF\r\u0016\b\u00033\u0006t!AW0\u000f\u0005msV\"\u0001/\u000b\u0005uC\u0015A\u0002\u001fs_>$h(C\u0001H\u0013\t\u0001g)A\u0004bY\u001e,'M]1\n\u0005\t\u001c\u0017a\u00029bG.\fw-\u001a\u0006\u0003A\u001aK!!\u001a4\u0003-Q\u0013XO\\2bi\u0016$G)\u001b<jg&|gn\u0011*j]\u001eT!AY2\u0011\u0007!LG+D\u0001d\u0013\tQ7M\u0001\u0003Ue&<\u0007c\u0001-m)&\u0011QN\u001a\u0002\u0006\r&,G\u000e\u001a\t\u00041>$\u0016B\u00019g\u0005\u0015y%\u000fZ3s\u0003\u0019!\u0013N\\5uIQ\t1\u000f\u0005\u0002Li&\u0011Q\u000f\u0014\u0002\u0005+:LG/A\u0003pe\u0012,'/F\u0001y!\t\t\u0006!A\u0002bEN$\"\u0001V>\t\u000bq\u001c\u0001\u0019\u0001+\u0002\u0003a\faa]5h]VlGcA@\u0002\u0006A\u00191*!\u0001\n\u0007\u0005\rAJA\u0002J]RDQ\u0001 \u0003A\u0002Q\u000b1!Z9w)\u0019\tY!!\u0005\u0002\u0014A\u00191*!\u0004\n\u0007\u0005=AJA\u0004C_>dW-\u00198\t\u000bq,\u0001\u0019\u0001+\t\r\u0005UQ\u00011\u0001U\u0003\u0005I\u0018aB2p[B\f'/\u001a\u000b\u0006\u007f\u0006m\u0011Q\u0004\u0005\u0006y\u001a\u0001\r\u0001\u0016\u0005\u0007\u0003+1\u0001\u0019\u0001+\u0002\ti,'o\\\u000b\u0002)\u0006\u0019qN\\3\u0002\r9,w-\u0019;f)\r!\u0016\u0011\u0006\u0005\u0006y&\u0001\r\u0001V\u0001\u0005a2,8\u000fF\u0003U\u0003_\t\t\u0004C\u0003}\u0015\u0001\u0007A\u000b\u0003\u0004\u0002\u0016)\u0001\r\u0001V\u0001\u0006[&tWo\u001d\u000b\u0006)\u0006]\u0012\u0011\b\u0005\u0006y.\u0001\r\u0001\u0016\u0005\u0007\u0003+Y\u0001\u0019\u0001+\u0002\u000bQLW.Z:\u0015\u000bQ\u000by$!\u0011\t\u000bqd\u0001\u0019\u0001+\t\r\u0005UA\u00021\u0001U\u0003-!xNQ5h\u0013:$x\n\u001d;\u0015\t\u0005\u001d\u00131\r\t\u0007\u0003\u0013\ny%a\u0015\u000e\u0005\u0005-#bAA'\r\u0006!Q\u000f^5m\u0013\u0011\t\t&a\u0013\u0003\u0007=\u0003H\u000f\u0005\u0003\u0002V\u0005uc\u0002BA,\u00037r1aWA-\u0013\u0005i\u0015B\u00012M\u0013\u0011\ty&!\u0019\u0003\r\tKw-\u00138u\u0015\t\u0011G\nC\u0003}\u001b\u0001\u0007A+A\u0003ucV|G\u000fF\u0003U\u0003S\nY\u0007C\u0003}\u001d\u0001\u0007A\u000b\u0003\u0004\u0002\u00169\u0001\r\u0001V\u0001\u0005i6|G\rF\u0003U\u0003c\n\u0019\bC\u0003}\u001f\u0001\u0007A\u000b\u0003\u0004\u0002\u0016=\u0001\r\u0001V\u0001\u000be\u0016\u001c\u0017\u000e\u001d:pG\u0006dGc\u0001+\u0002z!)A\u0010\u0005a\u0001)\u0006\u0019A-\u001b<\u0015\u000bQ\u000by(!!\t\u000bq\f\u0002\u0019\u0001+\t\r\u0005U\u0011\u00031\u0001U\u0003\u0011\u0019\u0018O\u001d;\u0015\u0007Q\u000b9\tC\u0003}%\u0001\u0007A+A\u0003oe>|G\u000fF\u0003U\u0003\u001b\u000by\tC\u0003}'\u0001\u0007A\u000b\u0003\u0004\u0002\u0012N\u0001\ra`\u0001\u0002W\u0006!a\r]8x)\u0015!\u0016qSAM\u0011\u0015aH\u00031\u0001U\u0011\u0019\t)\u0002\u0006a\u0001)\u0006!\u0011mY8t)\r!\u0016q\u0014\u0005\u0007\u0003C+\u0002\u0019\u0001+\u0002\u0003\u0005\fA!Y:j]R\u0019A+a*\t\r\u0005\u0005f\u00031\u0001U\u0003\u0011\tG/\u00198\u0015\u0007Q\u000bi\u000b\u0003\u0004\u0002\"^\u0001\r\u0001V\u0001\u0006CR\fgN\r\u000b\u0006)\u0006M\u0016Q\u0017\u0005\u0007\u0003+A\u0002\u0019\u0001+\t\u000bqD\u0002\u0019\u0001+\u0002\u0007\r|7\u000fF\u0002U\u0003wCa!!)\u001a\u0001\u0004!\u0016\u0001B2pg\"$2\u0001VAa\u0011\u0015a(\u00041\u0001U\u0003\u0005)\u0017aA3yaR\u0019A+!3\t\u000bqd\u0002\u0019\u0001+\u0002\u000b\u0015D\b/\\\u0019\u0015\u0007Q\u000by\rC\u0003};\u0001\u0007A+A\u0002m_\u001e$2\u0001VAk\u0011\u0015ah\u00041\u0001U\u0003\u0015awnZ\u0019q)\r!\u00161\u001c\u0005\u0006y~\u0001\r\u0001V\u0001\u0003a&\f1a]5o)\r!\u00161\u001d\u0005\u0006y\u0006\u0002\r\u0001V\u0001\u0005g&t\u0007\u000eF\u0002U\u0003SDQ\u0001 \u0012A\u0002Q\u000b1\u0001^1o)\r!\u0016q\u001e\u0005\u0006y\u000e\u0002\r\u0001V\u0001\u0005i\u0006t\u0007\u000eF\u0002U\u0003kDQ\u0001 \u0013A\u0002Q\u000b\u0011\u0002^8EK\u001e\u0014X-Z:\u0015\u0007Q\u000bY\u0010\u0003\u0004\u0002\"\u0016\u0002\r\u0001V\u0001\ni>\u0014\u0016\rZ5b]N$2\u0001\u0016B\u0001\u0011\u0019\t\tK\na\u0001)\u0006!1-Z5m)\r!&q\u0001\u0005\u0006y\u001e\u0002\r\u0001V\u0001\u0006M2|wN\u001d\u000b\u0004)\n5\u0001\"\u0002?)\u0001\u0004!\u0016aB5t/\"|G.\u001a\u000b\u0005\u0003\u0017\u0011\u0019\u0002C\u0003}S\u0001\u0007A+A\u0003s_VtG\rF\u0002U\u00053AQ\u0001 \u0016A\u0002Q\u000ba\u0001^8CsR,G\u0003\u0002B\u0010\u0005K\u00012a\u0013B\u0011\u0013\r\u0011\u0019\u0003\u0014\u0002\u0005\u0005f$X\rC\u0003}W\u0001\u0007A+A\u0003u_&sG\u000fF\u0002\u0000\u0005WAQ\u0001 \u0017A\u0002Q\u000bq\u0001^8TQ>\u0014H\u000f\u0006\u0003\u00032\t]\u0002cA&\u00034%\u0019!Q\u0007'\u0003\u000bMCwN\u001d;\t\u000bql\u0003\u0019\u0001+\u0002\rQ|Gj\u001c8h)\u0011\u0011iDa\u0011\u0011\u0007-\u0013y$C\u0002\u0003B1\u0013A\u0001T8oO\")AP\fa\u0001)\u00069Ao\u001c$m_\u0006$H\u0003\u0002B%\u0005\u001f\u00022a\u0013B&\u0013\r\u0011i\u0005\u0014\u0002\u0006\r2|\u0017\r\u001e\u0005\u0006y>\u0002\r\u0001V\u0001\ti>$u.\u001e2mKR!!Q\u000bB.!\rY%qK\u0005\u0004\u00053b%A\u0002#pk\ndW\rC\u0003}a\u0001\u0007A+\u0001\u0005u_\nKw-\u00138u)\u0011\t\u0019F!\u0019\t\u000bq\f\u0004\u0019\u0001+\u0002\u0019Q|')[4EK\u000eLW.\u00197\u0015\t\t\u001d$Q\u000e\t\u0005\u0003+\u0012I'\u0003\u0003\u0003l\u0005\u0005$A\u0003\"jO\u0012+7-[7bY\")AP\ra\u0001)\u0006QAo\u001c*bi&|g.\u00197\u0015\t\tM$\u0011\u0010\t\u0004#\nU\u0014b\u0001B<\t\nA!+\u0019;j_:\fG\u000eC\u0003}g\u0001\u0007A+A\u0006u_\u0006cw-\u001a2sC&\u001cG\u0003\u0002B@\u0005\u000b\u00032!\u0015BA\u0013\r\u0011\u0019\t\u0012\u0002\n\u00032<WM\u0019:bS\u000eDQ\u0001 \u001bA\u0002Q\u000ba\u0001^8SK\u0006dGc\u0001+\u0003\f\")A0\u000ea\u0001)\u0006AAo\u001c(v[\n,'\u000f\u0006\u0003\u0003\u0012\n]\u0005cA)\u0003\u0014&\u0019!Q\u0013#\u0003\r9+XNY3s\u0011\u0015ah\u00071\u0001U\u0003!!xn\u0015;sS:<G\u0003\u0002BO\u0005[\u0003BAa(\u0003(:!!\u0011\u0015BR!\tYF*C\u0002\u0003&2\u000ba\u0001\u0015:fI\u00164\u0017\u0002\u0002BU\u0005W\u0013aa\u0015;sS:<'b\u0001BS\u0019\")Ap\u000ea\u0001)\u00061Ao\u001c+za\u0016,BAa-\u0003<R!!Q\u0017Bl)\u0011\u00119L!4\u0011\t\te&1\u0018\u0007\u0001\t\u001d\u0011i\f\u000fb\u0001\u0005\u007f\u0013\u0011AQ\t\u0005\u0005\u0003\u00149\rE\u0002L\u0005\u0007L1A!2M\u0005\u001dqu\u000e\u001e5j]\u001e\u00042a\u0013Be\u0013\r\u0011Y\r\u0014\u0002\u0004\u0003:L\bb\u0002Bhq\u0001\u000f!\u0011[\u0001\u0003KZ\u0004R!\u0015Bj\u0005oK1A!6E\u00055\u0019uN\u001c<feR\f'\r\\3U_\")A\u0010\u000fa\u0001)\u0006AaM]8n\u0005f$X\rF\u0002U\u0005;DqAa8:\u0001\u0004\u0011y\"A\u0001o\u0003%1'o\\7TQ>\u0014H\u000fF\u0002U\u0005KDqAa8;\u0001\u0004\u0011\t$A\u0005ge>lg\t\\8biR\u0019AKa;\t\u000f\t}7\b1\u0001\u0003J\u0005AaM]8n\u0019>tw\rF\u0002U\u0005cDqAa8=\u0001\u0004\u0011i$\u0001\u0006ge>l')[4J]R$2\u0001\u0016B|\u0011\u001d\u0011y.\u0010a\u0001\u0003'\naB\u001a:p[\nKw\rR3dS6\fG\u000eF\u0002U\u0005{DqAa8?\u0001\u0004\u00119'\u0001\u0007ge>l'+\u0019;j_:\fG\u000eF\u0002U\u0007\u0007AqAa8@\u0001\u0004\u0011\u0019(A\u0007ge>l\u0017\t\\4fEJ\f\u0017n\u0019\u000b\u0004)\u000e%\u0001b\u0002Bp\u0001\u0002\u0007!qP\u0001\tMJ|WNU3bYR\u0019Aka\u0004\t\r\t}\u0017\t1\u0001U\u0003!1'o\\7UsB,W\u0003BB\u000b\u0007G!Baa\u0006\u0004&Q\u0019Ak!\u0007\t\u000f\t='\tq\u0001\u0004\u001cA)\u0011k!\b\u0004\"%\u00191q\u0004#\u0003\u001f\r{gN^3si\u0006\u0014G.\u001a$s_6\u0004BA!/\u0004$\u00119!Q\u0018\"C\u0002\t}\u0006bBB\u0014\u0005\u0002\u00071\u0011E\u0001\u0002E\u0002"
)
public interface RealIsFractional extends Fractional, TruncatedDivision.forCommutativeRing, Trig {
   // $FF: synthetic method
   static RealIsFractional order$(final RealIsFractional $this) {
      return $this.order();
   }

   default RealIsFractional order() {
      return this;
   }

   // $FF: synthetic method
   static Real abs$(final RealIsFractional $this, final Real x) {
      return $this.abs(x);
   }

   default Real abs(final Real x) {
      return x.abs();
   }

   // $FF: synthetic method
   static int signum$(final RealIsFractional $this, final Real x) {
      return $this.signum(x);
   }

   default int signum(final Real x) {
      return x.signum();
   }

   // $FF: synthetic method
   static boolean eqv$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Real x, final Real y) {
      return x.$eq$eq$eq(y);
   }

   // $FF: synthetic method
   static int compare$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.compare(x, y);
   }

   default int compare(final Real x, final Real y) {
      return x.compare(y);
   }

   // $FF: synthetic method
   static Real zero$(final RealIsFractional $this) {
      return $this.zero();
   }

   default Real zero() {
      return Real$.MODULE$.zero();
   }

   // $FF: synthetic method
   static Real one$(final RealIsFractional $this) {
      return $this.one();
   }

   default Real one() {
      return Real$.MODULE$.one();
   }

   // $FF: synthetic method
   static Real negate$(final RealIsFractional $this, final Real x) {
      return $this.negate(x);
   }

   default Real negate(final Real x) {
      return x.unary_$minus();
   }

   // $FF: synthetic method
   static Real plus$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.plus(x, y);
   }

   default Real plus(final Real x, final Real y) {
      return x.$plus(y);
   }

   // $FF: synthetic method
   static Real minus$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.minus(x, y);
   }

   default Real minus(final Real x, final Real y) {
      return x.$minus(y);
   }

   // $FF: synthetic method
   static Real times$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.times(x, y);
   }

   default Real times(final Real x, final Real y) {
      return x.$times(y);
   }

   // $FF: synthetic method
   static BigInt toBigIntOpt$(final RealIsFractional $this, final Real x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final Real x) {
      return x.isWhole() ? (BigInt).MODULE$.apply(x.toRational().toBigInt()) : (BigInt).MODULE$.empty();
   }

   // $FF: synthetic method
   static Real tquot$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.tquot(x, y);
   }

   default Real tquot(final Real x, final Real y) {
      return x.tquot(y);
   }

   // $FF: synthetic method
   static Real tmod$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.tmod(x, y);
   }

   default Real tmod(final Real x, final Real y) {
      return x.tmod(y);
   }

   // $FF: synthetic method
   static Real reciprocal$(final RealIsFractional $this, final Real x) {
      return $this.reciprocal(x);
   }

   default Real reciprocal(final Real x) {
      return x.reciprocal();
   }

   // $FF: synthetic method
   static Real div$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.div(x, y);
   }

   default Real div(final Real x, final Real y) {
      return x.$div(y);
   }

   // $FF: synthetic method
   static Real sqrt$(final RealIsFractional $this, final Real x) {
      return $this.sqrt(x);
   }

   default Real sqrt(final Real x) {
      return x.sqrt();
   }

   // $FF: synthetic method
   static Real nroot$(final RealIsFractional $this, final Real x, final int k) {
      return $this.nroot(x, k);
   }

   default Real nroot(final Real x, final int k) {
      return x.nroot(k);
   }

   // $FF: synthetic method
   static Real fpow$(final RealIsFractional $this, final Real x, final Real y) {
      return $this.fpow(x, y);
   }

   default Real fpow(final Real x, final Real y) {
      return x.fpow(y);
   }

   // $FF: synthetic method
   static Real acos$(final RealIsFractional $this, final Real a) {
      return $this.acos(a);
   }

   default Real acos(final Real a) {
      return Real$.MODULE$.acos(a);
   }

   // $FF: synthetic method
   static Real asin$(final RealIsFractional $this, final Real a) {
      return $this.asin(a);
   }

   default Real asin(final Real a) {
      return Real$.MODULE$.asin(a);
   }

   // $FF: synthetic method
   static Real atan$(final RealIsFractional $this, final Real a) {
      return $this.atan(a);
   }

   default Real atan(final Real a) {
      return Real$.MODULE$.atan(a);
   }

   // $FF: synthetic method
   static Real atan2$(final RealIsFractional $this, final Real y, final Real x) {
      return $this.atan2(y, x);
   }

   default Real atan2(final Real y, final Real x) {
      return Real$.MODULE$.atan2(y, x);
   }

   // $FF: synthetic method
   static Real cos$(final RealIsFractional $this, final Real a) {
      return $this.cos(a);
   }

   default Real cos(final Real a) {
      return Real$.MODULE$.cos(a);
   }

   // $FF: synthetic method
   static Real cosh$(final RealIsFractional $this, final Real x) {
      return $this.cosh(x);
   }

   default Real cosh(final Real x) {
      return Real$.MODULE$.cosh(x);
   }

   // $FF: synthetic method
   static Real e$(final RealIsFractional $this) {
      return $this.e();
   }

   default Real e() {
      return Real$.MODULE$.e();
   }

   // $FF: synthetic method
   static Real exp$(final RealIsFractional $this, final Real x) {
      return $this.exp(x);
   }

   default Real exp(final Real x) {
      return Real$.MODULE$.exp(x);
   }

   // $FF: synthetic method
   static Real expm1$(final RealIsFractional $this, final Real x) {
      return $this.expm1(x);
   }

   default Real expm1(final Real x) {
      return Real$.MODULE$.exp(x).$minus(Real$.MODULE$.one());
   }

   // $FF: synthetic method
   static Real log$(final RealIsFractional $this, final Real x) {
      return $this.log(x);
   }

   default Real log(final Real x) {
      return Real$.MODULE$.log(x);
   }

   // $FF: synthetic method
   static Real log1p$(final RealIsFractional $this, final Real x) {
      return $this.log1p(x);
   }

   default Real log1p(final Real x) {
      return Real$.MODULE$.log(Real$.MODULE$.one().$plus(x));
   }

   // $FF: synthetic method
   static Real pi$(final RealIsFractional $this) {
      return $this.pi();
   }

   default Real pi() {
      return Real$.MODULE$.pi();
   }

   // $FF: synthetic method
   static Real sin$(final RealIsFractional $this, final Real x) {
      return $this.sin(x);
   }

   default Real sin(final Real x) {
      return Real$.MODULE$.sin(x);
   }

   // $FF: synthetic method
   static Real sinh$(final RealIsFractional $this, final Real x) {
      return $this.sinh(x);
   }

   default Real sinh(final Real x) {
      return Real$.MODULE$.sinh(x);
   }

   // $FF: synthetic method
   static Real tan$(final RealIsFractional $this, final Real x) {
      return $this.tan(x);
   }

   default Real tan(final Real x) {
      return Real$.MODULE$.tan(x);
   }

   // $FF: synthetic method
   static Real tanh$(final RealIsFractional $this, final Real x) {
      return $this.tanh(x);
   }

   default Real tanh(final Real x) {
      return Real$.MODULE$.tanh(x);
   }

   // $FF: synthetic method
   static Real toDegrees$(final RealIsFractional $this, final Real a) {
      return $this.toDegrees(a);
   }

   default Real toDegrees(final Real a) {
      return a.$div(Real$.MODULE$.two().$times(Real$.MODULE$.pi())).$times(Real$.MODULE$.apply(360));
   }

   // $FF: synthetic method
   static Real toRadians$(final RealIsFractional $this, final Real a) {
      return $this.toRadians(a);
   }

   default Real toRadians(final Real a) {
      return a.$div(Real$.MODULE$.apply(360)).$times(Real$.MODULE$.two().$times(Real$.MODULE$.pi()));
   }

   // $FF: synthetic method
   static Real ceil$(final RealIsFractional $this, final Real x) {
      return $this.ceil(x);
   }

   default Real ceil(final Real x) {
      return x.ceil();
   }

   // $FF: synthetic method
   static Real floor$(final RealIsFractional $this, final Real x) {
      return $this.floor(x);
   }

   default Real floor(final Real x) {
      return x.floor();
   }

   // $FF: synthetic method
   static boolean isWhole$(final RealIsFractional $this, final Real x) {
      return $this.isWhole(x);
   }

   default boolean isWhole(final Real x) {
      return x.isWhole();
   }

   // $FF: synthetic method
   static Real round$(final RealIsFractional $this, final Real x) {
      return $this.round(x);
   }

   default Real round(final Real x) {
      return x.round();
   }

   // $FF: synthetic method
   static byte toByte$(final RealIsFractional $this, final Real x) {
      return $this.toByte(x);
   }

   default byte toByte(final Real x) {
      return x.toRational().toByte();
   }

   // $FF: synthetic method
   static int toInt$(final RealIsFractional $this, final Real x) {
      return $this.toInt(x);
   }

   default int toInt(final Real x) {
      return x.toRational().toInt();
   }

   // $FF: synthetic method
   static short toShort$(final RealIsFractional $this, final Real x) {
      return $this.toShort(x);
   }

   default short toShort(final Real x) {
      return x.toRational().toShort();
   }

   // $FF: synthetic method
   static long toLong$(final RealIsFractional $this, final Real x) {
      return $this.toLong(x);
   }

   default long toLong(final Real x) {
      return x.toRational().toLong();
   }

   // $FF: synthetic method
   static float toFloat$(final RealIsFractional $this, final Real x) {
      return $this.toFloat(x);
   }

   default float toFloat(final Real x) {
      return x.toRational().toFloat();
   }

   // $FF: synthetic method
   static double toDouble$(final RealIsFractional $this, final Real x) {
      return $this.toDouble(x);
   }

   default double toDouble(final Real x) {
      return x.toRational().toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final RealIsFractional $this, final Real x) {
      return $this.toBigInt(x);
   }

   default BigInt toBigInt(final Real x) {
      return x.toRational().toBigInt();
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final RealIsFractional $this, final Real x) {
      return $this.toBigDecimal(x);
   }

   default BigDecimal toBigDecimal(final Real x) {
      return x.toRational().toBigDecimal(MathContext.DECIMAL64);
   }

   // $FF: synthetic method
   static Rational toRational$(final RealIsFractional $this, final Real x) {
      return $this.toRational(x);
   }

   default Rational toRational(final Real x) {
      return x.toRational();
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final RealIsFractional $this, final Real x) {
      return $this.toAlgebraic(x);
   }

   default Algebraic toAlgebraic(final Real x) {
      return Algebraic$.MODULE$.apply(x.toRational());
   }

   // $FF: synthetic method
   static Real toReal$(final RealIsFractional $this, final Real x) {
      return $this.toReal(x);
   }

   default Real toReal(final Real x) {
      return x;
   }

   // $FF: synthetic method
   static Number toNumber$(final RealIsFractional $this, final Real x) {
      return $this.toNumber(x);
   }

   default Number toNumber(final Real x) {
      return Number$.MODULE$.apply(x.toRational());
   }

   // $FF: synthetic method
   static String toString$(final RealIsFractional $this, final Real x) {
      return $this.toString(x);
   }

   default String toString(final Real x) {
      return x.toString();
   }

   // $FF: synthetic method
   static Object toType$(final RealIsFractional $this, final Real x, final ConvertableTo ev) {
      return $this.toType(x, ev);
   }

   default Object toType(final Real x, final ConvertableTo ev) {
      return ev.fromReal(x);
   }

   // $FF: synthetic method
   static Real fromByte$(final RealIsFractional $this, final byte n) {
      return $this.fromByte(n);
   }

   default Real fromByte(final byte n) {
      return Real$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Real fromShort$(final RealIsFractional $this, final short n) {
      return $this.fromShort(n);
   }

   default Real fromShort(final short n) {
      return Real$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Real fromFloat$(final RealIsFractional $this, final float n) {
      return $this.fromFloat(n);
   }

   default Real fromFloat(final float n) {
      return Real$.MODULE$.apply((double)n);
   }

   // $FF: synthetic method
   static Real fromLong$(final RealIsFractional $this, final long n) {
      return $this.fromLong(n);
   }

   default Real fromLong(final long n) {
      return Real$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Real fromBigInt$(final RealIsFractional $this, final BigInt n) {
      return $this.fromBigInt(n);
   }

   default Real fromBigInt(final BigInt n) {
      return Real$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Real fromBigDecimal$(final RealIsFractional $this, final BigDecimal n) {
      return $this.fromBigDecimal(n);
   }

   default Real fromBigDecimal(final BigDecimal n) {
      return Real$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Real fromRational$(final RealIsFractional $this, final Rational n) {
      return $this.fromRational(n);
   }

   default Real fromRational(final Rational n) {
      return Real$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Real fromAlgebraic$(final RealIsFractional $this, final Algebraic n) {
      return $this.fromAlgebraic(n);
   }

   default Real fromAlgebraic(final Algebraic n) {
      return (Real)n.evaluateWith(Real$.MODULE$.algebra(), Real$.MODULE$.algebra(), RootFinder$.MODULE$.RealRootFinder(), Real$.MODULE$.algebra(), scala.reflect.ClassTag..MODULE$.apply(Real.class), Real$.MODULE$.algebra());
   }

   // $FF: synthetic method
   static Real fromReal$(final RealIsFractional $this, final Real n) {
      return $this.fromReal(n);
   }

   default Real fromReal(final Real n) {
      return n;
   }

   // $FF: synthetic method
   static Real fromType$(final RealIsFractional $this, final Object b, final ConvertableFrom ev) {
      return $this.fromType(b, ev);
   }

   default Real fromType(final Object b, final ConvertableFrom ev) {
      return ev.toReal(b);
   }

   static void $init$(final RealIsFractional $this) {
   }
}
