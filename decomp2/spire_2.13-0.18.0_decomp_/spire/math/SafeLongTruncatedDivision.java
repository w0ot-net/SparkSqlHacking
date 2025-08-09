package spire.math;

import algebra.ring.TruncatedDivision;
import scala.MatchError;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\r4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005!B\u0004\u0005\u0006[\u0001!\tA\f\u0005\u0006e\u0001!\ta\r\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0006\u001d\u0002!\te\u0014\u0005\u0006+\u0002!\tE\u0016\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006?\u0002!\t\u0001\u0019\u0002\u001a'\u00064W\rT8oOR\u0013XO\\2bi\u0016$G)\u001b<jg&|gN\u0003\u0002\f\u0019\u0005!Q.\u0019;i\u0015\u0005i\u0011!B:qSJ,7\u0003\u0002\u0001\u0010+)\u0002\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f$M9\u0011q\u0003\t\b\u00031yq!!G\u000f\u000e\u0003iQ!a\u0007\u000f\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!D\u0005\u0003?1\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\"E\u00059\u0001/Y2lC\u001e,'BA\u0010\r\u0013\t!SEA\tUeVt7-\u0019;fI\u0012Kg/[:j_:T!!\t\u0012\u0011\u0005\u001dBS\"\u0001\u0006\n\u0005%R!\u0001C*bM\u0016duN\\4\u0011\u0005\u001dZ\u0013B\u0001\u0017\u000b\u00059\u0019\u0016MZ3M_:<7+[4oK\u0012\fa\u0001J5oSR$C#A\u0018\u0011\u0005A\u0001\u0014BA\u0019\u0012\u0005\u0011)f.\u001b;\u0002\u0017Q|')[4J]R|\u0005\u000f\u001e\u000b\u0003i\t\u00032!\u000e\u001d;\u001b\u00051$BA\u001c\r\u0003\u0011)H/\u001b7\n\u0005e2$aA(qiB\u00111h\u0010\b\u0003yyr!!G\u001f\n\u0003II!!I\t\n\u0005\u0001\u000b%A\u0002\"jO&sGO\u0003\u0002\"#!)1I\u0001a\u0001M\u0005\ta.A\u0003ucV|G\u000fF\u0002'\r\"CQaR\u0002A\u0002\u0019\n\u0011\u0001\u001f\u0005\u0006\u0013\u000e\u0001\rAJ\u0001\u0002s\u0006!A/\\8e)\r1C*\u0014\u0005\u0006\u000f\u0012\u0001\rA\n\u0005\u0006\u0013\u0012\u0001\rAJ\u0001\tiF,x\u000e^7pIR\u0019\u0001k\u0015+\u0011\tA\tfEJ\u0005\u0003%F\u0011a\u0001V;qY\u0016\u0014\u0004\"B$\u0006\u0001\u00041\u0003\"B%\u0006\u0001\u00041\u0013\u0001\u00034rk>$Xn\u001c3\u0015\u0007A;\u0016\fC\u0003Y\r\u0001\u0007a%A\u0002mQNDQA\u0017\u0004A\u0002\u0019\n1A\u001d5t\u0003\u00151\u0017/^8u)\r1SL\u0018\u0005\u00061\u001e\u0001\rA\n\u0005\u00065\u001e\u0001\rAJ\u0001\u0005M6|G\rF\u0002'C\nDQ\u0001\u0017\u0005A\u0002\u0019BQA\u0017\u0005A\u0002\u0019\u0002"
)
public interface SafeLongTruncatedDivision extends TruncatedDivision, SafeLongSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final SafeLongTruncatedDivision $this, final SafeLong n) {
      return $this.toBigIntOpt(n);
   }

   default BigInt toBigIntOpt(final SafeLong n) {
      return (BigInt).MODULE$.apply(n.toBigInt());
   }

   // $FF: synthetic method
   static SafeLong tquot$(final SafeLongTruncatedDivision $this, final SafeLong x, final SafeLong y) {
      return $this.tquot(x, y);
   }

   default SafeLong tquot(final SafeLong x, final SafeLong y) {
      return x.$div$tilde(y);
   }

   // $FF: synthetic method
   static SafeLong tmod$(final SafeLongTruncatedDivision $this, final SafeLong x, final SafeLong y) {
      return $this.tmod(x, y);
   }

   default SafeLong tmod(final SafeLong x, final SafeLong y) {
      return x.$percent(y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$(final SafeLongTruncatedDivision $this, final SafeLong x, final SafeLong y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final SafeLong x, final SafeLong y) {
      return x.$div$percent(y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final SafeLongTruncatedDivision $this, final SafeLong lhs, final SafeLong rhs) {
      return $this.fquotmod(lhs, rhs);
   }

   default Tuple2 fquotmod(final SafeLong lhs, final SafeLong rhs) {
      Tuple2 var5 = lhs.$div$percent(rhs);
      if (var5 != null) {
         SafeLong tq = (SafeLong)var5._1();
         SafeLong tm = (SafeLong)var5._2();
         Tuple2 var3 = new Tuple2(tq, tm);
         SafeLong tq = (SafeLong)var3._1();
         SafeLong tm = (SafeLong)var3._2();
         boolean signsDiffer = tm.signum() == -rhs.signum();
         SafeLong fq = signsDiffer ? tq.$minus(1L) : tq;
         SafeLong fm = signsDiffer ? tm.$plus(rhs) : tm;
         return new Tuple2(fq, fm);
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static SafeLong fquot$(final SafeLongTruncatedDivision $this, final SafeLong lhs, final SafeLong rhs) {
      return $this.fquot(lhs, rhs);
   }

   default SafeLong fquot(final SafeLong lhs, final SafeLong rhs) {
      Tuple2 var5 = lhs.$div$percent(rhs);
      if (var5 != null) {
         SafeLong tq = (SafeLong)var5._1();
         SafeLong tm = (SafeLong)var5._2();
         Tuple2 var3 = new Tuple2(tq, tm);
         SafeLong tq = (SafeLong)var3._1();
         SafeLong tm = (SafeLong)var3._2();
         return tm.signum() == -rhs.signum() ? tq.$minus(1L) : tq;
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static SafeLong fmod$(final SafeLongTruncatedDivision $this, final SafeLong lhs, final SafeLong rhs) {
      return $this.fmod(lhs, rhs);
   }

   default SafeLong fmod(final SafeLong lhs, final SafeLong rhs) {
      SafeLong tm = lhs.$percent(rhs);
      return tm.signum() == -rhs.signum() ? tm.$plus(rhs) : tm;
   }

   static void $init$(final SafeLongTruncatedDivision $this) {
   }
}
