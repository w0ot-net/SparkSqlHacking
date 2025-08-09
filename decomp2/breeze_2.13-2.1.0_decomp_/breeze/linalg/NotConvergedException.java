package breeze.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4A\u0001F\u000b\u00015!AQ\u0006\u0001BC\u0002\u0013\u0005a\u0006\u0003\u0005r\u0001\t\u0005\t\u0015!\u00030\u0011!\u0011\bA!A!\u0002\u0013A\u0006\"\u0002!\u0001\t\u0003\u0019x!B\u0019\u0016\u0011\u0003\u0011d!\u0002\u000b\u0016\u0011\u0003\u0019\u0004\"\u0002!\u0007\t\u0003\tea\u0002\"\u0007!\u0003\r\naQ\u0004\u0006\t\u001aA\t!\u0012\u0004\u0006\u000f\u001aA\t\u0001\u0013\u0005\u0006\u0001*!\tAS\u0004\u0006\u0017\u001aA\t\u0001\u0014\u0004\u0006\u001b\u001aA\tA\u0014\u0005\u0006\u00016!\taT\u0004\u0006!\u001aA\t!\u0015\u0004\u0006%\u001aA\ta\u0015\u0005\u0006\u0001B!\t\u0001\u0016\u0005\b+\u001a\t\n\u0011\"\u0001W\u0011\u001dIg!!A\u0005\n)\u0014QCT8u\u0007>tg/\u001a:hK\u0012,\u0005pY3qi&|gN\u0003\u0002\u0017/\u00051A.\u001b8bY\u001eT\u0011\u0001G\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001aG\u0015\u0011\u0005q1cBA\u000f$\u001d\tq\u0012%D\u0001 \u0015\t\u0001\u0013$\u0001\u0004=e>|GOP\u0005\u0002E\u0005)1oY1mC&\u0011A%J\u0001\ba\u0006\u001c7.Y4f\u0015\u0005\u0011\u0013BA\u0014)\u0005A\u0011VO\u001c;j[\u0016,\u0005pY3qi&|gN\u0003\u0002%KA\u0011!fK\u0007\u0002+%\u0011A&\u0006\u0002\u0017\u0019&tW-\u0019:BY\u001e,'M]1Fq\u000e,\u0007\u000f^5p]\u00061!/Z1t_:,\u0012a\f\t\u0003a!q!AK\u0003\u0002+9{GoQ8om\u0016\u0014x-\u001a3Fq\u000e,\u0007\u000f^5p]B\u0011!FB\n\u0004\rQB\u0004CA\u001b7\u001b\u0005)\u0013BA\u001c&\u0005\u0019\te.\u001f*fMB\u0011\u0011HP\u0007\u0002u)\u00111\bP\u0001\u0003S>T\u0011!P\u0001\u0005U\u00064\u0018-\u0003\u0002@u\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012A\r\u0002\u0007%\u0016\f7o\u001c8\u0014\u0005!!\u0014AC%uKJ\fG/[8ogB\u0011aIC\u0007\u0002\r\tQ\u0011\n^3sCRLwN\\:\u0014\u0007)!\u0014\n\u0005\u0002G\u0011Q\tQ)\u0001\u0006ESZ,'oZ3oG\u0016\u0004\"AR\u0007\u0003\u0015\u0011Kg/\u001a:hK:\u001cWmE\u0002\u000ei%#\u0012\u0001T\u0001\n\u0005J,\u0017m\u001b3po:\u0004\"A\u0012\t\u0003\u0013\t\u0013X-Y6e_^t7c\u0001\t5\u0013R\t\u0011+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0002/*\u0012\u0001\f\u0019\t\u00033vs!AW.\u0011\u0005y)\u0013B\u0001/&\u0003\u0019\u0001&/\u001a3fM&\u0011al\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005q+3&A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017!C;oG\",7m[3e\u0015\t1W%\u0001\u0006b]:|G/\u0019;j_:L!\u0001[2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001l!\taw.D\u0001n\u0015\tqG(\u0001\u0003mC:<\u0017B\u00019n\u0005\u0019y%M[3di\u00069!/Z1t_:\u0004\u0013aA7tOR\u0019A/\u001e<\u0011\u0005)\u0002\u0001\"B\u0017\u0005\u0001\u0004y\u0003b\u0002:\u0005!\u0003\u0005\r\u0001\u0017"
)
public class NotConvergedException extends RuntimeException implements LinearAlgebraException {
   private final Reason reason;

   public static String $lessinit$greater$default$2() {
      return NotConvergedException$.MODULE$.$lessinit$greater$default$2();
   }

   public Reason reason() {
      return this.reason;
   }

   public NotConvergedException(final Reason reason, final String msg) {
      super(msg);
      this.reason = reason;
   }

   public static class Iterations$ implements Reason {
      public static final Iterations$ MODULE$ = new Iterations$();
   }

   public static class Divergence$ implements Reason {
      public static final Divergence$ MODULE$ = new Divergence$();
   }

   public static class Breakdown$ implements Reason {
      public static final Breakdown$ MODULE$ = new Breakdown$();
   }

   public interface Reason {
   }
}
