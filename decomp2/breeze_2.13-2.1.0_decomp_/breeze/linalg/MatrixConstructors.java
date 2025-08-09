package breeze.linalg;

import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.LiteralRow;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.Rand$;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tMfa\u0002\u0007\u000e!\u0003\r\tA\u0005\u0005\u00065\u0001!\ta\u0007\u0005\u0006?\u00011\t\u0001\t\u0005\u0006]\u00021\ta\u001c\u0005\b\u0003#\u0001A\u0011AA\n\u0011\u001d\t\t\u0006\u0001C\u0001\u0003'Bq!!$\u0001\t\u0003\ty\tC\u0004\u0002J\u0002!\t!a3\t\u0013\u0005e\b!%A\u0005\u0002\u0005m\bb\u0002B\u000f\u0001\u0011\u0005!q\u0004\u0005\b\u0005O\u0002A1\u0001B5\u0011\u001d\u0011Y\t\u0001C\u0005\u0005\u001b\u0013!#T1ue&D8i\u001c8tiJ,8\r^8sg*\u0011abD\u0001\u0007Y&t\u0017\r\\4\u000b\u0003A\taA\u0019:fKj,7\u0001A\u000b\u0003'\u0015\u001a\"\u0001\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tA\u0004\u0005\u0002\u0016;%\u0011aD\u0006\u0002\u0005+:LG/A\u0003{KJ|7/\u0006\u0002\"qQ\u0019!e\u001a7\u0015\u0007\r:v\fE\u0002%K]b\u0001\u0001B\u0003'\u0001\t\u0007qEA\u0002NCR,\"\u0001K\u0019\u0012\u0005%b\u0003CA\u000b+\u0013\tYcCA\u0004O_RD\u0017N\\4\u0011\u00075r\u0003'D\u0001\u000e\u0013\tySB\u0001\u0004NCR\u0014\u0018\u000e\u001f\t\u0003IE\"QAM\u0013C\u0002M\u0012\u0011\u0001V\t\u0003SQ\u0002\"!F\u001b\n\u0005Y2\"aA!osB\u0011A\u0005\u000f\u0003\ns\t\u0001\u000b\u0011!AC\u0002M\u0012\u0011A\u0016\u0015\u0007qmr\u0004*\u0014*\u0011\u0005Ua\u0014BA\u001f\u0017\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rz\u0004IQ!\u000f\u0005U\u0001\u0015BA!\u0017\u0003\u0019!u.\u001e2mKF\"AeQ$\u0018\u001d\t!u)D\u0001F\u0015\t1\u0015#\u0001\u0004=e>|GOP\u0005\u0002/E*1%\u0013&M\u0017:\u0011QCS\u0005\u0003\u0017Z\t1!\u00138uc\u0011!3iR\f2\u000b\rru*\u0015)\u000f\u0005Uy\u0015B\u0001)\u0017\u0003\u00151En\\1uc\u0011!3iR\f2\u000b\r\u001aFKV+\u000f\u0005U!\u0016BA+\u0017\u0003\u0011auN\\42\t\u0011\u001aui\u0006\u0005\b1\n\t\t\u0011q\u0001Z\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u00045v;T\"A.\u000b\u0005q3\u0012a\u0002:fM2,7\r^\u0005\u0003=n\u0013\u0001b\u00117bgN$\u0016m\u001a\u0005\bA\n\t\t\u0011q\u0001b\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0004E\u0016<T\"A2\u000b\u0005\u0011|\u0011aB:u_J\fw-Z\u0005\u0003M\u000e\u0014AAW3s_\")\u0001N\u0001a\u0001S\u0006!!o\\<t!\t)\".\u0003\u0002l-\t\u0019\u0011J\u001c;\t\u000b5\u0014\u0001\u0019A5\u0002\t\r|Gn]\u0001\u0007GJ,\u0017\r^3\u0016\u0005A$HcB9\u0002\u0004\u0005\u0015\u0011q\u0001\u000b\u0003ez\u00042\u0001J\u0013t!\t!C\u000fB\u0005:\u0007\u0001\u0006\t\u0011!b\u0001g!2Ao\u000f<yur\fTaI Ao\u0006\u000bD\u0001J\"H/E*1%\u0013&z\u0017F\"AeQ$\u0018c\u0015\u0019cjT>Qc\u0011!3iR\f2\u000b\r\u001aF+`+2\t\u0011\u001aui\u0006\u0005\t\u007f\u000e\t\t\u0011q\u0001\u0002\u0002\u0005QQM^5eK:\u001cW\rJ\u001d\u0011\u0007\t,7\u000fC\u0003i\u0007\u0001\u0007\u0011\u000eC\u0003n\u0007\u0001\u0007\u0011\u000eC\u0004\u0002\n\r\u0001\r!a\u0003\u0002\t\u0011\fG/\u0019\t\u0005+\u000551/C\u0002\u0002\u0010Y\u0011Q!\u0011:sCf\fAa\u001c8fgV!\u0011QCA\u000f)\u0019\t9\"!\u0014\u0002PQA\u0011\u0011DA\u0019\u0003o\ti\u0004\u0005\u0003%K\u0005m\u0001c\u0001\u0013\u0002\u001e\u0011I\u0011\b\u0002Q\u0001\u0002\u0003\u0015\ra\r\u0015\f\u0003;Y\u0014\u0011EA\u0013\u0003S\ti#\r\u0004$\u007f\u0001\u000b\u0019#Q\u0019\u0005I\r;u#\r\u0004$\u0013*\u000b9cS\u0019\u0005I\r;u#\r\u0004$\u001d>\u000bY\u0003U\u0019\u0005I\r;u#\r\u0004$'R\u000by#V\u0019\u0005I\r;u\u0003C\u0005\u00024\u0011\t\t\u0011q\u0001\u00026\u0005YQM^5eK:\u001cW\rJ\u00191!\u0011QV,a\u0007\t\u0013\u0005eB!!AA\u0004\u0005m\u0012aC3wS\u0012,gnY3%cE\u0002BAY3\u0002\u001c!I\u0011q\b\u0003\u0002\u0002\u0003\u000f\u0011\u0011I\u0001\fKZLG-\u001a8dK\u0012\n$\u0007\u0005\u0004\u0002D\u0005%\u00131D\u0007\u0003\u0003\u000bR1!a\u0012\u0010\u0003\u0011i\u0017\r\u001e5\n\t\u0005-\u0013Q\t\u0002\t'\u0016l\u0017N]5oO\")\u0001\u000e\u0002a\u0001S\")Q\u000e\u0002a\u0001S\u0006!a-\u001b7m+\u0011\t)&a\u0018\u0015\r\u0005]\u0013\u0011RAF)\u0011\tI&a \u0015\r\u0005m\u00131OA=!\u0011!S%!\u0018\u0011\u0007\u0011\ny\u0006B\u0005:\u000b\u0001\u0006\t\u0011!b\u0001g!Z\u0011qL\u001e\u0002d\u0005\u001d\u00141NA8c\u0019\u0019s\bQA3\u0003F\"AeQ$\u0018c\u0019\u0019\u0013JSA5\u0017F\"AeQ$\u0018c\u0019\u0019cjTA7!F\"AeQ$\u0018c\u0019\u00193\u000bVA9+F\"AeQ$\u0018\u0011%\t)(BA\u0001\u0002\b\t9(A\u0006fm&$WM\\2fIE\u001a\u0004\u0003\u0002.^\u0003;B\u0011\"a\u001f\u0006\u0003\u0003\u0005\u001d!! \u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000e\t\u0005E\u0016\fi\u0006\u0003\u0005\u0002\u0002\u0016!\t\u0019AAB\u0003\u00051\b#B\u000b\u0002\u0006\u0006u\u0013bAAD-\tAAHY=oC6,g\bC\u0003i\u000b\u0001\u0007\u0011\u000eC\u0003n\u000b\u0001\u0007\u0011.\u0001\u0005uC\n,H.\u0019;f+\u0011\t\t*a'\u0015\r\u0005M\u0015QYAd)\u0011\t)*a/\u0015\r\u0005]\u0015qVA[!\u0011!S%!'\u0011\u0007\u0011\nY\nB\u0005:\r\u0001\u0006\t\u0011!b\u0001g!Z\u00111T\u001e\u0002 \u0006\r\u0016qUAVc\u0019\u0019s\bQAQ\u0003F\"AeQ$\u0018c\u0019\u0019\u0013JSAS\u0017F\"AeQ$\u0018c\u0019\u0019cjTAU!F\"AeQ$\u0018c\u0019\u00193\u000bVAW+F\"AeQ$\u0018\u0011%\t\tLBA\u0001\u0002\b\t\u0019,A\u0006fm&$WM\\2fIE*\u0004\u0003\u0002.^\u00033C\u0011\"a.\u0007\u0003\u0003\u0005\u001d!!/\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\u000e\t\u0005E\u0016\fI\nC\u0004\u0002>\u001a\u0001\r!a0\u0002\u0003\u0019\u0004r!FAaS&\fI*C\u0002\u0002DZ\u0011\u0011BR;oGRLwN\u001c\u001a\t\u000b!4\u0001\u0019A5\t\u000b54\u0001\u0019A5\u0002\tI\fg\u000eZ\u000b\u0005\u0003\u001b\f)\u000e\u0006\u0005\u0002P\u0006\r\u0018Q]At)\u0019\t\t.a6\u0002^B!A%JAj!\r!\u0013Q\u001b\u0003\u0006e\u001d\u0011\ra\r\u0005\n\u00033<\u0011\u0011!a\u0002\u00037\f1\"\u001a<jI\u0016t7-\u001a\u00132oA!!,XAj\u0011%\tynBA\u0001\u0002\b\t\t/A\u0006fm&$WM\\2fIEB\u0004\u0003\u00022f\u0003'DQ\u0001[\u0004A\u0002%DQ!\\\u0004A\u0002%D\u0011\"!3\b!\u0003\u0005\r!!;\u0011\r\u0005-\u0018Q_Aj\u001b\t\tiO\u0003\u0003\u0002p\u0006E\u0018!\u00043jgR\u0014\u0018NY;uS>t7OC\u0002\u0002t>\tQa\u001d;biNLA!a>\u0002n\n!!+\u00198e\u00039\u0011\u0018M\u001c3%I\u00164\u0017-\u001e7uIM*B!!@\u0003\u001cU\u0011\u0011q \u0016\u0005\u0005\u0003\u0011I\u0001\u0005\u0004\u0002l\u0006U(1\u0001\t\u0004+\t\u0015\u0011b\u0001B\u0004-\t1Ai\\;cY\u0016\\#Aa\u0003\u0011\t\t5!qC\u0007\u0003\u0005\u001fQAA!\u0005\u0003\u0014\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005+1\u0012AC1o]>$\u0018\r^5p]&!!\u0011\u0004B\b\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006e!\u0011\raM\u0001\u0006CB\u0004H._\u000b\u0007\u0005C\u0011yE!\u000b\u0015\t\t\r\"q\f\u000b\t\u0005K\u0011iDa\u0015\u0003ZA!A%\nB\u0014!\r!#\u0011\u0006\u0003\ns%\u0001\u000b\u0011!AC\u0002MB3B!\u000b<\u0005[\u0011\tD!\u000e\u0003:E21e\u0010!\u00030\u0005\u000bD\u0001J\"H/E21%\u0013&\u00034-\u000bD\u0001J\"H/E21ET(\u00038A\u000bD\u0001J\"H/E21e\u0015+\u0003<U\u000bD\u0001J\"H/!9!qH\u0005A\u0004\t\u0005\u0013A\u0001:m!!\u0011\u0019E!\u0013\u0003N\t\u001dRB\u0001B#\u0015\r\u00119%D\u0001\bgV\u0004\bo\u001c:u\u0013\u0011\u0011YE!\u0012\u0003\u00151KG/\u001a:bYJ{w\u000fE\u0002%\u0005\u001f\"aA!\u0015\n\u0005\u0004\u0019$!\u0001*\t\u000f\tU\u0013\u0002q\u0001\u0003X\u0005\u0019Q.\u00198\u0011\tik&q\u0005\u0005\b\u00057J\u00019\u0001B/\u0003\u0011QXM]8\u0011\t\t,'q\u0005\u0005\u0007Q&\u0001\rA!\u0019\u0011\u000bU\u0011\u0019G!\u0014\n\u0007\t\u0015dC\u0001\u0006=e\u0016\u0004X-\u0019;fIz\nabY1o\u0007J,\u0017\r^3[KJ|7/\u0006\u0003\u0003l\t]DC\u0002B7\u0005\u007f\u0012)\t\u0005\u0005\u0003D\t=$1\u000fB=\u0013\u0011\u0011\tH!\u0012\u0003\u001d\r\u000bgn\u0011:fCR,',\u001a:pgB!A%\nB;!\r!#q\u000f\u0003\u0006e)\u0011\ra\r\t\u0006+\tm\u0014.[\u0005\u0004\u0005{2\"A\u0002+va2,'\u0007C\u0005\u0003\u0002*\t\t\u0011q\u0001\u0003\u0004\u0006YQM^5eK:\u001cW\rJ\u0019:!\u0011QVL!\u001e\t\u0013\t\u001d%\"!AA\u0004\t%\u0015aC3wS\u0012,gnY3%eA\u0002BAY3\u0003v\u0005ia-\u001b8jg\"d\u0015\u000e^3sC2,bAa$\u0003\u001a\n\u0005Fc\u0002\u000f\u0003\u0012\nm%1\u0015\u0005\b\u0005'[\u0001\u0019\u0001BK\u0003\t\u0011h\u000f\u0005\u0003.]\t]\u0005c\u0001\u0013\u0003\u001a\u0012)\u0011h\u0003b\u0001g!9!qH\u0006A\u0002\tu\u0005\u0003\u0003B\"\u0005\u0013\u0012yJa&\u0011\u0007\u0011\u0012\t\u000b\u0002\u0004\u0003R-\u0011\ra\r\u0005\u0007Q.\u0001\rA!*\u0011\r\t\u001d&Q\u0016BP\u001d\r\u0019%\u0011V\u0005\u0004\u0005W3\u0012a\u00029bG.\fw-Z\u0005\u0005\u0005_\u0013\tLA\u0002TKFT1Aa+\u0017\u0001"
)
public interface MatrixConstructors {
   Matrix zeros(final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8);

   Matrix create(final int rows, final int cols, final Object data, final Zero evidence$9);

   // $FF: synthetic method
   static Matrix ones$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return $this.ones(rows, cols, evidence$10, evidence$11, evidence$12);
   }

   default Matrix ones(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return this.fill(rows, cols, () -> ((Semiring).MODULE$.implicitly(evidence$12)).one(), evidence$10, evidence$11);
   }

   // $FF: synthetic method
   static Matrix fill$(final MatrixConstructors $this, final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return $this.fill(rows, cols, v, evidence$13, evidence$14);
   }

   default Matrix fill(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return this.create(rows, cols, scala.Array..MODULE$.fill(rows * cols, v, evidence$13), evidence$14);
   }

   // $FF: synthetic method
   static Matrix tabulate$(final MatrixConstructors $this, final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return $this.tabulate(rows, cols, f, evidence$15, evidence$16);
   }

   default Matrix tabulate(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      Matrix z = this.zeros(rows, cols, evidence$15, evidence$16);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> z.update(r, c, f.apply(BoxesRunTime.boxToInteger(r), BoxesRunTime.boxToInteger(c)))));
      return z;
   }

   // $FF: synthetic method
   static Matrix rand$(final MatrixConstructors $this, final int rows, final int cols, final Rand rand, final ClassTag evidence$17, final Zero evidence$18) {
      return $this.rand(rows, cols, rand, evidence$17, evidence$18);
   }

   default Matrix rand(final int rows, final int cols, final Rand rand, final ClassTag evidence$17, final Zero evidence$18) {
      return this.fill(rows, cols, () -> rand.draw(), evidence$17, evidence$18);
   }

   // $FF: synthetic method
   static Rand rand$default$3$(final MatrixConstructors $this) {
      return $this.rand$default$3();
   }

   default Rand rand$default$3() {
      return Rand$.MODULE$.uniform();
   }

   // $FF: synthetic method
   static Matrix apply$(final MatrixConstructors $this, final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return $this.apply(rows, rl, man, zero);
   }

   default Matrix apply(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      int nRows = rows.length();
      Option var8 = rows.headOption();
      int ns;
      if (scala.None..MODULE$.equals(var8)) {
         ns = 0;
      } else {
         if (!(var8 instanceof Some)) {
            throw new MatchError(var8);
         }

         Some var9 = (Some)var8;
         Object firstRow = var9.value();
         ns = rl.length(firstRow);
      }

      Matrix rv = this.zeros(nRows, ns, man, zero);
      this.breeze$linalg$MatrixConstructors$$finishLiteral(rv, rl, rows);
      return rv;
   }

   // $FF: synthetic method
   static CanCreateZeros canCreateZeros$(final MatrixConstructors $this, final ClassTag evidence$19, final Zero evidence$20) {
      return $this.canCreateZeros(evidence$19, evidence$20);
   }

   default CanCreateZeros canCreateZeros(final ClassTag evidence$19, final Zero evidence$20) {
      return new CanCreateZeros(evidence$19, evidence$20) {
         // $FF: synthetic field
         private final MatrixConstructors $outer;
         private final ClassTag evidence$19$1;
         private final Zero evidence$20$1;

         public Matrix apply(final Tuple2 dims) {
            return this.$outer.zeros(dims._1$mcI$sp(), dims._2$mcI$sp(), this.evidence$19$1, this.evidence$20$1);
         }

         public {
            if (MatrixConstructors.this == null) {
               throw null;
            } else {
               this.$outer = MatrixConstructors.this;
               this.evidence$19$1 = evidence$19$1;
               this.evidence$20$1 = evidence$20$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static void breeze$linalg$MatrixConstructors$$finishLiteral$(final MatrixConstructors $this, final Matrix rv, final LiteralRow rl, final Seq rows) {
      $this.breeze$linalg$MatrixConstructors$$finishLiteral(rv, rl, rows);
   }

   default void breeze$linalg$MatrixConstructors$$finishLiteral(final Matrix rv, final LiteralRow rl, final Seq rows) {
      ((IterableOps)rows.zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$finishLiteral$1(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$finishLiteral$2(rl, rv, x$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static Matrix zeros$mDc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      return $this.zeros$mDc$sp(rows, cols, evidence$7, evidence$8);
   }

   default Matrix zeros$mDc$sp(final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix zeros$mFc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      return $this.zeros$mFc$sp(rows, cols, evidence$7, evidence$8);
   }

   default Matrix zeros$mFc$sp(final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix zeros$mIc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      return $this.zeros$mIc$sp(rows, cols, evidence$7, evidence$8);
   }

   default Matrix zeros$mIc$sp(final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix zeros$mJc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      return $this.zeros$mJc$sp(rows, cols, evidence$7, evidence$8);
   }

   default Matrix zeros$mJc$sp(final int rows, final int cols, final ClassTag evidence$7, final Zero evidence$8) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix create$mDc$sp$(final MatrixConstructors $this, final int rows, final int cols, final double[] data, final Zero evidence$9) {
      return $this.create$mDc$sp(rows, cols, data, evidence$9);
   }

   default Matrix create$mDc$sp(final int rows, final int cols, final double[] data, final Zero evidence$9) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix create$mFc$sp$(final MatrixConstructors $this, final int rows, final int cols, final float[] data, final Zero evidence$9) {
      return $this.create$mFc$sp(rows, cols, data, evidence$9);
   }

   default Matrix create$mFc$sp(final int rows, final int cols, final float[] data, final Zero evidence$9) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix create$mIc$sp$(final MatrixConstructors $this, final int rows, final int cols, final int[] data, final Zero evidence$9) {
      return $this.create$mIc$sp(rows, cols, data, evidence$9);
   }

   default Matrix create$mIc$sp(final int rows, final int cols, final int[] data, final Zero evidence$9) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix create$mJc$sp$(final MatrixConstructors $this, final int rows, final int cols, final long[] data, final Zero evidence$9) {
      return $this.create$mJc$sp(rows, cols, data, evidence$9);
   }

   default Matrix create$mJc$sp(final int rows, final int cols, final long[] data, final Zero evidence$9) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Matrix ones$mDc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return $this.ones$mDc$sp(rows, cols, evidence$10, evidence$11, evidence$12);
   }

   default Matrix ones$mDc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return this.fill$mDc$sp(rows, cols, (JFunction0.mcD.sp)() -> ((Semiring).MODULE$.implicitly(evidence$12)).one$mcD$sp(), evidence$10, evidence$11);
   }

   // $FF: synthetic method
   static Matrix ones$mFc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return $this.ones$mFc$sp(rows, cols, evidence$10, evidence$11, evidence$12);
   }

   default Matrix ones$mFc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return this.fill$mFc$sp(rows, cols, (JFunction0.mcF.sp)() -> ((Semiring).MODULE$.implicitly(evidence$12)).one$mcF$sp(), evidence$10, evidence$11);
   }

   // $FF: synthetic method
   static Matrix ones$mIc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return $this.ones$mIc$sp(rows, cols, evidence$10, evidence$11, evidence$12);
   }

   default Matrix ones$mIc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return this.fill$mIc$sp(rows, cols, (JFunction0.mcI.sp)() -> ((Semiring).MODULE$.implicitly(evidence$12)).one$mcI$sp(), evidence$10, evidence$11);
   }

   // $FF: synthetic method
   static Matrix ones$mJc$sp$(final MatrixConstructors $this, final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return $this.ones$mJc$sp(rows, cols, evidence$10, evidence$11, evidence$12);
   }

   default Matrix ones$mJc$sp(final int rows, final int cols, final ClassTag evidence$10, final Zero evidence$11, final Semiring evidence$12) {
      return this.fill$mJc$sp(rows, cols, (JFunction0.mcJ.sp)() -> ((Semiring).MODULE$.implicitly(evidence$12)).one$mcJ$sp(), evidence$10, evidence$11);
   }

   // $FF: synthetic method
   static Matrix fill$mDc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return $this.fill$mDc$sp(rows, cols, v, evidence$13, evidence$14);
   }

   default Matrix fill$mDc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return this.create$mDc$sp(rows, cols, (double[])scala.Array..MODULE$.fill(rows * cols, v, evidence$13), evidence$14);
   }

   // $FF: synthetic method
   static Matrix fill$mFc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return $this.fill$mFc$sp(rows, cols, v, evidence$13, evidence$14);
   }

   default Matrix fill$mFc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return this.create$mFc$sp(rows, cols, (float[])scala.Array..MODULE$.fill(rows * cols, v, evidence$13), evidence$14);
   }

   // $FF: synthetic method
   static Matrix fill$mIc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return $this.fill$mIc$sp(rows, cols, v, evidence$13, evidence$14);
   }

   default Matrix fill$mIc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return this.create$mIc$sp(rows, cols, (int[])scala.Array..MODULE$.fill(rows * cols, v, evidence$13), evidence$14);
   }

   // $FF: synthetic method
   static Matrix fill$mJc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return $this.fill$mJc$sp(rows, cols, v, evidence$13, evidence$14);
   }

   default Matrix fill$mJc$sp(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return this.create$mJc$sp(rows, cols, (long[])scala.Array..MODULE$.fill(rows * cols, v, evidence$13), evidence$14);
   }

   // $FF: synthetic method
   static Matrix tabulate$mDc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return $this.tabulate$mDc$sp(rows, cols, f, evidence$15, evidence$16);
   }

   default Matrix tabulate$mDc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      Matrix z = this.zeros$mDc$sp(rows, cols, evidence$15, evidence$16);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> z.update$mcD$sp(r, c, f.apply$mcDII$sp(r, c))));
      return z;
   }

   // $FF: synthetic method
   static Matrix tabulate$mFc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return $this.tabulate$mFc$sp(rows, cols, f, evidence$15, evidence$16);
   }

   default Matrix tabulate$mFc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      Matrix z = this.zeros$mFc$sp(rows, cols, evidence$15, evidence$16);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> z.update$mcF$sp(r, c, f.apply$mcFII$sp(r, c))));
      return z;
   }

   // $FF: synthetic method
   static Matrix tabulate$mIc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return $this.tabulate$mIc$sp(rows, cols, f, evidence$15, evidence$16);
   }

   default Matrix tabulate$mIc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      Matrix z = this.zeros$mIc$sp(rows, cols, evidence$15, evidence$16);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> z.update$mcI$sp(r, c, f.apply$mcIII$sp(r, c))));
      return z;
   }

   // $FF: synthetic method
   static Matrix tabulate$mJc$sp$(final MatrixConstructors $this, final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return $this.tabulate$mJc$sp(rows, cols, f, evidence$15, evidence$16);
   }

   default Matrix tabulate$mJc$sp(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      Matrix z = this.zeros$mJc$sp(rows, cols, evidence$15, evidence$16);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), cols).foreach$mVc$sp((JFunction1.mcVI.sp)(c) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), rows).foreach$mVc$sp((JFunction1.mcVI.sp)(r) -> z.update$mcJ$sp(r, c, f.apply$mcJII$sp(r, c))));
      return z;
   }

   // $FF: synthetic method
   static Matrix apply$mDc$sp$(final MatrixConstructors $this, final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return $this.apply$mDc$sp(rows, rl, man, zero);
   }

   default Matrix apply$mDc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      int nRows = rows.length();
      Option var8 = rows.headOption();
      int ns;
      if (scala.None..MODULE$.equals(var8)) {
         ns = 0;
      } else {
         if (!(var8 instanceof Some)) {
            throw new MatchError(var8);
         }

         Some var9 = (Some)var8;
         Object firstRow = var9.value();
         ns = rl.length(firstRow);
      }

      Matrix rv = this.zeros$mDc$sp(nRows, ns, man, zero);
      this.breeze$linalg$MatrixConstructors$$finishLiteral(rv, rl, rows);
      return rv;
   }

   // $FF: synthetic method
   static Matrix apply$mFc$sp$(final MatrixConstructors $this, final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return $this.apply$mFc$sp(rows, rl, man, zero);
   }

   default Matrix apply$mFc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      int nRows = rows.length();
      Option var8 = rows.headOption();
      int ns;
      if (scala.None..MODULE$.equals(var8)) {
         ns = 0;
      } else {
         if (!(var8 instanceof Some)) {
            throw new MatchError(var8);
         }

         Some var9 = (Some)var8;
         Object firstRow = var9.value();
         ns = rl.length(firstRow);
      }

      Matrix rv = this.zeros$mFc$sp(nRows, ns, man, zero);
      this.breeze$linalg$MatrixConstructors$$finishLiteral(rv, rl, rows);
      return rv;
   }

   // $FF: synthetic method
   static Matrix apply$mIc$sp$(final MatrixConstructors $this, final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return $this.apply$mIc$sp(rows, rl, man, zero);
   }

   default Matrix apply$mIc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      int nRows = rows.length();
      Option var8 = rows.headOption();
      int ns;
      if (scala.None..MODULE$.equals(var8)) {
         ns = 0;
      } else {
         if (!(var8 instanceof Some)) {
            throw new MatchError(var8);
         }

         Some var9 = (Some)var8;
         Object firstRow = var9.value();
         ns = rl.length(firstRow);
      }

      Matrix rv = this.zeros$mIc$sp(nRows, ns, man, zero);
      this.breeze$linalg$MatrixConstructors$$finishLiteral(rv, rl, rows);
      return rv;
   }

   // $FF: synthetic method
   static Matrix apply$mJc$sp$(final MatrixConstructors $this, final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      return $this.apply$mJc$sp(rows, rl, man, zero);
   }

   default Matrix apply$mJc$sp(final Seq rows, final LiteralRow rl, final ClassTag man, final Zero zero) {
      int nRows = rows.length();
      Option var8 = rows.headOption();
      int ns;
      if (scala.None..MODULE$.equals(var8)) {
         ns = 0;
      } else {
         if (!(var8 instanceof Some)) {
            throw new MatchError(var8);
         }

         Some var9 = (Some)var8;
         Object firstRow = var9.value();
         ns = rl.length(firstRow);
      }

      Matrix rv = this.zeros$mJc$sp(nRows, ns, man, zero);
      this.breeze$linalg$MatrixConstructors$$finishLiteral(rv, rl, rows);
      return rv;
   }

   // $FF: synthetic method
   static boolean $anonfun$finishLiteral$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$finishLiteral$3(final Matrix rv$2, final int i$4, final int j, final Object v) {
      rv$2.update(i$4, j, v);
   }

   // $FF: synthetic method
   static void $anonfun$finishLiteral$2(final LiteralRow rl$1, final Matrix rv$2, final Tuple2 x$1) {
      if (x$1 != null) {
         Object row = x$1._1();
         int i = x$1._2$mcI$sp();
         rl$1.foreach(row, (j, v) -> {
            $anonfun$finishLiteral$3(rv$2, i, BoxesRunTime.unboxToInt(j), v);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   static void $init$(final MatrixConstructors $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
