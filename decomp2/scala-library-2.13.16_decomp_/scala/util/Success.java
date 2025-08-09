package scala.util;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;
import scala.util.control.NonFatal$;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\tud\u0001\u0002\u0015*\u0005:B\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t#\u0002\u0011\t\u0012)A\u0005i!)!\u000b\u0001C\u0001'\")a\u000b\u0001C!/\")1\f\u0001C!/\")A\f\u0001C!!\")Q\f\u0001C!=\")\u0011\u000e\u0001C!U\")\u0011\u000f\u0001C!e\")A\u0010\u0001C!{\"9\u0011q\u0002\u0001\u0005B\u0005E\u0001bBA\u0012\u0001\u0011\u0005\u0013Q\u0005\u0005\b\u0003\u0003\u0002A\u0011IA\"\u0011\u001d\t\t\u0006\u0001C!\u0003'Bq!a\u001a\u0001\t\u0003\nI\u0007C\u0004\u0002r\u0001!\t%a\u001d\t\u000f\u0005\u0005\u0005\u0001\"\u0011\u0002\u0004\"9\u0011\u0011\u0013\u0001\u0005B\u0005M\u0005bBAL\u0001\u0011\u0005\u0013\u0011\u0014\u0005\b\u0003C\u0003A\u0011IAR\u0011\u001d\tY\u000b\u0001C!\u0003[C\u0011\"!1\u0001\u0003\u0003%\t!a1\t\u0013\u0005=\u0007!%A\u0005\u0002\u0005E\u0007\"CAv\u0001\u0005\u0005I\u0011IAw\u0011%\ty\u0010AA\u0001\n\u0003\u0011\t\u0001C\u0005\u0003\n\u0001\t\t\u0011\"\u0001\u0003\f!I!\u0011\u0003\u0001\u0002\u0002\u0013\u0005#1\u0003\u0005\n\u0005C\u0001\u0011\u0011!C\u0001\u0005GA\u0011Ba\n\u0001\u0003\u0003%\tE!\u000b\t\u0013\t5\u0002!!A\u0005B\t=\u0002\"\u0003B\u0019\u0001\u0005\u0005I\u0011\tB\u001a\u0011%\u0011)\u0004AA\u0001\n\u0003\u00129dB\u0005\u0003<%\n\t\u0011#\u0001\u0003>\u0019A\u0001&KA\u0001\u0012\u0003\u0011y\u0004\u0003\u0004SE\u0011\u0005!\u0011\u000b\u0005\n\u0005c\u0011\u0013\u0011!C#\u0005gA\u0011Ba\u0015#\u0003\u0003%\tI!\u0016\t\u0013\t\u0005$%!A\u0005\u0002\n\r\u0004\"\u0003B:E\u0005\u0005I\u0011\u0002B;\u0005\u001d\u0019VoY2fgNT!AK\u0016\u0002\tU$\u0018\u000e\u001c\u0006\u0002Y\u0005)1oY1mC\u000e\u0001QCA\u00187'\u0011\u0001\u0001\u0007Q\"\u0011\u0007E\u0012D'D\u0001*\u0013\t\u0019\u0014FA\u0002Uef\u0004\"!\u000e\u001c\r\u0001\u00111q\u0007\u0001CC\u0002a\u0012\u0011\u0001V\t\u0003su\u0002\"AO\u001e\u000e\u0003-J!\u0001P\u0016\u0003\u000f9{G\u000f[5oOB\u0011!HP\u0005\u0003\u007f-\u00121!\u00118z!\tQ\u0014)\u0003\u0002CW\t9\u0001K]8ek\u000e$\bC\u0001#M\u001d\t)%J\u0004\u0002G\u00136\tqI\u0003\u0002I[\u00051AH]8pizJ\u0011\u0001L\u0005\u0003\u0017.\nq\u0001]1dW\u0006<W-\u0003\u0002N\u001d\na1+\u001a:jC2L'0\u00192mK*\u00111jK\u0001\u0006m\u0006dW/Z\u000b\u0002i\u00051a/\u00197vK\u0002\na\u0001P5oSRtDC\u0001+V!\r\t\u0004\u0001\u000e\u0005\u0006\u001f\u000e\u0001\r\u0001N\u0001\nSN4\u0015-\u001b7ve\u0016,\u0012\u0001\u0017\t\u0003ueK!AW\u0016\u0003\u000f\t{w\u000e\\3b]\u0006I\u0011n]*vG\u000e,7o]\u0001\u0004O\u0016$\u0018!C4fi>\u0013X\t\\:f+\ty\u0016\r\u0006\u0002aIB\u0011Q'\u0019\u0003\u0006E\u001e\u0011\ra\u0019\u0002\u0002+F\u0011A'\u0010\u0005\u0007K\u001e!\t\u0019\u00014\u0002\u000f\u0011,g-Y;miB\u0019!h\u001a1\n\u0005!\\#\u0001\u0003\u001fcs:\fW.\u001a \u0002\r=\u0014X\t\\:f+\tYg\u000e\u0006\u0002m_B\u0019\u0011GM7\u0011\u0005UrG!\u00022\t\u0005\u0004\u0019\u0007BB3\t\t\u0003\u0007\u0001\u000fE\u0002;O2\fqA\u001a7bi6\u000b\u0007/\u0006\u0002tmR\u0011Ao\u001e\t\u0004cI*\bCA\u001bw\t\u0015\u0011\u0017B1\u00019\u0011\u0015A\u0018\u00021\u0001z\u0003\u00051\u0007\u0003\u0002\u001e{iQL!a_\u0016\u0003\u0013\u0019+hn\u0019;j_:\f\u0014a\u00024mCR$XM\\\u000b\u0004}\u0006\rAcA@\u0002\u0006A!\u0011GMA\u0001!\r)\u00141\u0001\u0003\u0006E*\u0011\r\u0001\u000f\u0005\b\u0003\u000fQ\u00019AA\u0005\u0003\t)g\u000fE\u0003;\u0003\u0017!t0C\u0002\u0002\u000e-\u0012\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0002\u000f\u0019|'/Z1dQV!\u00111CA\u0011)\u0011\t)\"a\u0007\u0011\u0007i\n9\"C\u0002\u0002\u001a-\u0012A!\u00168ji\"1\u0001p\u0003a\u0001\u0003;\u0001RA\u000f>5\u0003?\u00012!NA\u0011\t\u0015\u00117B1\u00019\u0003%!(/\u00198tM>\u0014X.\u0006\u0003\u0002(\u00055BCBA\u0015\u0003_\t)\u0004\u0005\u00032e\u0005-\u0002cA\u001b\u0002.\u0011)!\r\u0004b\u0001q!9\u0011\u0011\u0007\u0007A\u0002\u0005M\u0012!A:\u0011\u000biRH'!\u000b\t\rad\u0001\u0019AA\u001c!\u0019Q$0!\u000f\u0002*A!\u00111HA\u001f\u001d\tQ$*C\u0002\u0002@9\u0013\u0011\u0002\u00165s_^\f'\r\\3\u0002\u00075\f\u0007/\u0006\u0003\u0002F\u0005-C\u0003BA$\u0003\u001b\u0002B!\r\u001a\u0002JA\u0019Q'a\u0013\u0005\u000b\tl!\u0019\u0001\u001d\t\ral\u0001\u0019AA(!\u0015Q$\u0010NA%\u0003\u001d\u0019w\u000e\u001c7fGR,B!!\u0016\u0002\\Q!\u0011qKA/!\u0011\t$'!\u0017\u0011\u0007U\nY\u0006B\u0003c\u001d\t\u0007\u0001\bC\u0004\u0002`9\u0001\r!!\u0019\u0002\u0005A4\u0007C\u0002\u001e\u0002dQ\nI&C\u0002\u0002f-\u0012q\u0002U1si&\fGNR;oGRLwN\\\u0001\u0007M&dG/\u001a:\u0015\u0007A\nY\u0007C\u0004\u0002n=\u0001\r!a\u001c\u0002\u0003A\u0004BA\u000f>51\u00069!/Z2pm\u0016\u0014X\u0003BA;\u0003w\"B!a\u001e\u0002~A!\u0011GMA=!\r)\u00141\u0010\u0003\u0006EB\u0011\ra\u0019\u0005\b\u0003?\u0002\u0002\u0019AA@!\u001dQ\u00141MA\u001d\u0003s\n1B]3d_Z,'oV5uQV!\u0011QQAF)\u0011\t9)!$\u0011\tE\u0012\u0014\u0011\u0012\t\u0004k\u0005-E!\u00022\u0012\u0005\u0004\u0019\u0007bBA0#\u0001\u0007\u0011q\u0012\t\bu\u0005\r\u0014\u0011HAD\u0003\u00191\u0017-\u001b7fIV\u0011\u0011Q\u0013\t\u0005cI\nI$\u0001\u0005u_>\u0003H/[8o+\t\tY\n\u0005\u0003;\u0003;#\u0014bAAPW\t1q\n\u001d;j_:\f\u0001\u0002^8FSRDWM]\u000b\u0003\u0003K\u0003b!MAT\u0003s!\u0014bAAUS\t1Q)\u001b;iKJ\fAAZ8mIV!\u0011qVAZ)\u0019\t\t,!.\u0002<B\u0019Q'a-\u0005\u000b\t,\"\u0019\u0001\u001d\t\u000f\u0005]V\u00031\u0001\u0002:\u0006\u0011a-\u0019\t\u0007ui\fI$!-\t\u000f\u0005uV\u00031\u0001\u0002@\u0006\u0011aM\u0019\t\u0006ui$\u0014\u0011W\u0001\u0005G>\u0004\u00180\u0006\u0003\u0002F\u0006-G\u0003BAd\u0003\u001b\u0004B!\r\u0001\u0002JB\u0019Q'a3\u0005\u000b]2\"\u0019\u0001\u001d\t\u0011=3\u0002\u0013!a\u0001\u0003\u0013\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0002T\u0006%XCAAkU\r!\u0014q[\u0016\u0003\u00033\u0004B!a7\u0002f6\u0011\u0011Q\u001c\u0006\u0005\u0003?\f\t/A\u0005v]\u000eDWmY6fI*\u0019\u00111]\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002h\u0006u'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)qg\u0006b\u0001q\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a<\u0011\t\u0005E\u00181`\u0007\u0003\u0003gTA!!>\u0002x\u0006!A.\u00198h\u0015\t\tI0\u0001\u0003kCZ\f\u0017\u0002BA\u007f\u0003g\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXC\u0001B\u0002!\rQ$QA\u0005\u0004\u0005\u000fY#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA\u001f\u0003\u000e!I!q\u0002\u000e\u0002\u0002\u0003\u0007!1A\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\tU\u0001#\u0002B\f\u0005;iTB\u0001B\r\u0015\r\u0011YbK\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B\u0010\u00053\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019\u0001L!\n\t\u0011\t=A$!AA\u0002u\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011q\u001eB\u0016\u0011%\u0011y!HA\u0001\u0002\u0004\u0011\u0019!\u0001\u0005iCND7i\u001c3f)\t\u0011\u0019!\u0001\u0005u_N#(/\u001b8h)\t\ty/\u0001\u0004fcV\fGn\u001d\u000b\u00041\ne\u0002\u0002\u0003B\bA\u0005\u0005\t\u0019A\u001f\u0002\u000fM+8mY3tgB\u0011\u0011GI\n\u0006E\t\u0005#q\t\t\u0004u\t\r\u0013b\u0001B#W\t1\u0011I\\=SK\u001a\u0004BA!\u0013\u0003P5\u0011!1\n\u0006\u0005\u0005\u001b\n90\u0001\u0002j_&\u0019QJa\u0013\u0015\u0005\tu\u0012!B1qa2LX\u0003\u0002B,\u0005;\"BA!\u0017\u0003`A!\u0011\u0007\u0001B.!\r)$Q\f\u0003\u0006o\u0015\u0012\r\u0001\u000f\u0005\u0007\u001f\u0016\u0002\rAa\u0017\u0002\u000fUt\u0017\r\u001d9msV!!Q\rB6)\u0011\u00119G!\u001c\u0011\u000bi\niJ!\u001b\u0011\u0007U\u0012Y\u0007B\u00038M\t\u0007\u0001\bC\u0005\u0003p\u0019\n\t\u00111\u0001\u0003r\u0005\u0019\u0001\u0010\n\u0019\u0011\tE\u0002!\u0011N\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005o\u0002B!!=\u0003z%!!1PAz\u0005\u0019y%M[3di\u0002"
)
public final class Success extends Try {
   private final Object value;

   public static Option unapply(final Success x$0) {
      return Success$.MODULE$.unapply(x$0);
   }

   public static Success apply(final Object value) {
      Success$ var10000 = Success$.MODULE$;
      return new Success(value);
   }

   public Object value() {
      return this.value;
   }

   public boolean isFailure() {
      return false;
   }

   public boolean isSuccess() {
      return true;
   }

   public Object get() {
      return this.value();
   }

   public Object getOrElse(final Function0 default) {
      return this.value();
   }

   public Try orElse(final Function0 default) {
      return this;
   }

   public Try flatMap(final Function1 f) {
      try {
         return (Try)f.apply(this.value());
      } catch (Throwable var3) {
         if (var3 != null && NonFatal$.MODULE$.apply(var3)) {
            return new Failure(var3);
         } else {
            throw var3;
         }
      }
   }

   public Try flatten(final $less$colon$less ev) {
      return (Try)ev.apply(this.value());
   }

   public void foreach(final Function1 f) {
      f.apply(this.value());
   }

   public Try transform(final Function1 s, final Function1 f) {
      try {
         return (Try)s.apply(this.value());
      } catch (Throwable var4) {
         if (var4 != null && NonFatal$.MODULE$.apply(var4)) {
            return new Failure(var4);
         } else {
            throw var4;
         }
      }
   }

   public Try map(final Function1 f) {
      Try$ var10000 = Try$.MODULE$;

      try {
         Object apply_r1 = f.apply(this.value());
         return new Success(apply_r1);
      } catch (Throwable var4) {
         if (var4 != null && NonFatal$.MODULE$.apply(var4)) {
            return new Failure(var4);
         } else {
            throw var4;
         }
      }
   }

   public Try collect(final PartialFunction pf) {
      Object marker = Statics.pfMarker;

      try {
         Object v = pf.applyOrElse(this.value(), (x) -> marker);
         return (Try)(marker != v ? new Success(v) : new Failure(new NoSuchElementException((new StringBuilder(28)).append("Predicate does not hold for ").append(this.value()).toString())));
      } catch (Throwable var5) {
         if (var5 != null && NonFatal$.MODULE$.apply(var5)) {
            return new Failure(var5);
         } else {
            throw var5;
         }
      }
   }

   public Try filter(final Function1 p) {
      try {
         return (Try)(BoxesRunTime.unboxToBoolean(p.apply(this.value())) ? this : new Failure(new NoSuchElementException((new StringBuilder(28)).append("Predicate does not hold for ").append(this.value()).toString())));
      } catch (Throwable var3) {
         if (var3 != null && NonFatal$.MODULE$.apply(var3)) {
            return new Failure(var3);
         } else {
            throw var3;
         }
      }
   }

   public Try recover(final PartialFunction pf) {
      return this;
   }

   public Try recoverWith(final PartialFunction pf) {
      return this;
   }

   public Try failed() {
      return new Failure(new UnsupportedOperationException("Success.failed"));
   }

   public Option toOption() {
      return new Some(this.value());
   }

   public Either toEither() {
      return new Right(this.value());
   }

   public Object fold(final Function1 fa, final Function1 fb) {
      try {
         return fb.apply(this.value());
      } catch (Throwable var4) {
         if (var4 != null && NonFatal$.MODULE$.apply(var4)) {
            return fa.apply(var4);
         } else {
            throw var4;
         }
      }
   }

   public Success copy(final Object value) {
      return new Success(value);
   }

   public Object copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "Success";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.value();
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
      return x$1 instanceof Success;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "value";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return MurmurHash3$.MODULE$.productHash(this);
   }

   public String toString() {
      return ScalaRunTime$.MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof Success) {
            Success var2 = (Success)x$1;
            if (BoxesRunTime.equals(this.value(), var2.value())) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$map$1(final Success $this, final Function1 f$1) {
      return f$1.apply($this.value());
   }

   public Success(final Object value) {
      this.value = value;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
