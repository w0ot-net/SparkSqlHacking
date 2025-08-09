package scala.collection;

import scala.collection.convert.impl.AnyIteratorStepper;
import scala.collection.convert.impl.DoubleIteratorStepper;
import scala.collection.convert.impl.IntIteratorStepper;
import scala.collection.convert.impl.LongIteratorStepper;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rbaB\u0007\u000f!\u0003\r\ta\u0005\u0005\u00063\u0001!\tA\u0007\u0005\u0006=\u00011\ta\b\u0005\u0006Y\u0001!\t!\f\u0005\u0006\u007f\u0001!\t\u0001Q\u0004\u0006\t:A\t!\u0012\u0004\u0006\u001b9A\tA\u0012\u0005\u0006\u0015\u001a!\ta\u0013\u0005\u0006\u0019\u001a!\u0019!\u0014\u0005\u00077\u001a!\tA\u0004/\t\r\u00194A\u0011\u0001\bh\u0011!Qh!%A\u0005\u00029Y\bBCA\f\rE\u0005I\u0011\u0001\b\u0002\u001a\ta\u0011\n^3sC\ndWm\u00148dK*\u0011q\u0002E\u0001\u000bG>dG.Z2uS>t'\"A\t\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011ACJ\n\u0003\u0001U\u0001\"AF\f\u000e\u0003AI!\u0001\u0007\t\u0003\u0007\u0005s\u00170\u0001\u0004%S:LG\u000f\n\u000b\u00027A\u0011a\u0003H\u0005\u0003;A\u0011A!\u00168ji\u0006A\u0011\u000e^3sCR|'/F\u0001!!\r\t#\u0005J\u0007\u0002\u001d%\u00111E\u0004\u0002\t\u0013R,'/\u0019;peB\u0011QE\n\u0007\u0001\t\u00199\u0003\u0001\"b\u0001Q\t\t\u0011)\u0005\u0002*+A\u0011aCK\u0005\u0003WA\u0011qAT8uQ&tw-A\u0004ti\u0016\u0004\b/\u001a:\u0016\u00059\u0002DCA\u0018;!\t)\u0003\u0007B\u00032\u0007\t\u0007!GA\u0001T#\tI3\u0007\r\u00025qA\u0019\u0011%N\u001c\n\u0005Yr!aB*uKB\u0004XM\u001d\t\u0003Ka\"\u0011\"\u000f\u0019\u0002\u0002\u0003\u0005)\u0011\u0001\u0015\u0003\u0007}#\u0013\u0007C\u0003<\u0007\u0001\u000fA(A\u0003tQ\u0006\u0004X\r\u0005\u0003\"{\u0011z\u0013B\u0001 \u000f\u00051\u0019F/\u001a9qKJ\u001c\u0006.\u00199f\u0003%Ygn\\<o'&TX-F\u0001B!\t1\")\u0003\u0002D!\t\u0019\u0011J\u001c;\u0002\u0019%#XM]1cY\u0016|enY3\u0011\u0005\u000521C\u0001\u0004H!\t1\u0002*\u0003\u0002J!\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A#\u00029%$XM]1cY\u0016|enY3FqR,gn]5p]6+G\u000f[8egV\u0011aj\u0015\u000b\u0003\u001fR\u00032!\t)S\u0013\t\tfB\u0001\u000fJi\u0016\u0014\u0018M\u00197f\u001f:\u001cW-\u0012=uK:\u001c\u0018n\u001c8NKRDw\u000eZ:\u0011\u0005\u0015\u001aF!B\u0014\t\u0005\u0004A\u0003\"B+\t\u0001\u00041\u0016AA5u!\r\t\u0003A\u0015\u0015\u0003\u0011a\u0003\"AF-\n\u0005i\u0003\"AB5oY&tW-\u0001\nfY\u0016l7\u000fV8D_BLHk\\!se\u0006LH#B!^?\u0006\u001c\u0007\"\u00020\n\u0001\u0004\t\u0015AB:sG2+g\u000eC\u0003a\u0013\u0001\u0007\u0011)A\u0004eKN$H*\u001a8\t\u000b\tL\u0001\u0019A!\u0002\u000bM$\u0018M\u001d;\t\u000b\u0011L\u0001\u0019A!\u0002\u00071,g\u000e\u000b\u0002\n1\u0006\u00012m\u001c9z\u000b2,Wn\u001d+p\u0003J\u0014\u0018-_\u000b\u0004Q6$H#B!j]^D\b\"\u00026\u000b\u0001\u0004Y\u0017!B3mK6\u001c\bcA\u0011\u0001YB\u0011Q%\u001c\u0003\u0006O)\u0011\r\u0001\u000b\u0005\u0006_*\u0001\r\u0001]\u0001\u0003qN\u00042AF9t\u0013\t\u0011\bCA\u0003BeJ\f\u0017\u0010\u0005\u0002&i\u0012)QO\u0003b\u0001m\n\t!)\u0005\u0002m+!9!M\u0003I\u0001\u0002\u0004\t\u0005b\u00023\u000b!\u0003\u0005\r!\u0011\u0015\u0003\u0015a\u000b!dY8qs\u0016cW-\\:U_\u0006\u0013(/Y=%I\u00164\u0017-\u001e7uIM*R\u0001`A\b\u0003#)\u0012! \u0016\u0003\u0003z\\\u0013a \t\u0005\u0003\u0003\tY!\u0004\u0002\u0002\u0004)!\u0011QAA\u0004\u0003%)hn\u00195fG.,GMC\u0002\u0002\nA\t!\"\u00198o_R\fG/[8o\u0013\u0011\ti!a\u0001\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003(\u0017\t\u0007\u0001\u0006\u0002\u0004v\u0017\t\u0007\u00111C\t\u0004\u0003+)\u0002cA\u0013\u0002\u0010\u0005Q2m\u001c9z\u000b2,Wn\u001d+p\u0003J\u0014\u0018-\u001f\u0013eK\u001a\fW\u000f\u001c;%iU)A0a\u0007\u0002\u001e\u0011)q\u0005\u0004b\u0001Q\u00111Q\u000f\u0004b\u0001\u0003?\t2!!\t\u0016!\r)\u00131\u0004"
)
public interface IterableOnce {
   static IterableOnce iterableOnceExtensionMethods(final IterableOnce it) {
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      return it;
   }

   Iterator iterator();

   // $FF: synthetic method
   static Stepper stepper$(final IterableOnce $this, final StepperShape shape) {
      return $this.stepper(shape);
   }

   default Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntIteratorStepper(this.iterator());
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongIteratorStepper(this.iterator());
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleIteratorStepper(this.iterator()) : shape.seqUnbox(new AnyIteratorStepper(this.iterator())));
      }
   }

   // $FF: synthetic method
   static int knownSize$(final IterableOnce $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return -1;
   }

   static void $init$(final IterableOnce $this) {
   }
}
