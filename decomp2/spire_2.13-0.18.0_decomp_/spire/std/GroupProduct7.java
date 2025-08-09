package spire.std;

import cats.kernel.Group;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0004\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u0002!\ta\u001a\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r^\u001c\u000b\u00051i\u0011aA:uI*\ta\"A\u0003ta&\u0014X-\u0006\u0005\u0011[]RT\bQ\"G'\u0011\u0001\u0011c\u0006%\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\rAR\u0005\u000b\b\u00033\tr!A\u0007\u0011\u000f\u0005myR\"\u0001\u000f\u000b\u0005uq\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u00039I!!I\u0007\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0005J\u0001\ba\u0006\u001c7.Y4f\u0015\t\tS\"\u0003\u0002'O\t)qI]8va*\u00111\u0005\n\t\n%%Zc'\u000f\u001f@\u0005\u0016K!AK\n\u0003\rQ+\b\u000f\\38!\taS\u0006\u0004\u0001\u0005\u000b9\u0002!\u0019A\u0018\u0003\u0003\u0005\u000b\"\u0001M\u001a\u0011\u0005I\t\u0014B\u0001\u001a\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0005\u001b\n\u0005U\u001a\"aA!osB\u0011Af\u000e\u0003\u0006q\u0001\u0011\ra\f\u0002\u0002\u0005B\u0011AF\u000f\u0003\u0006w\u0001\u0011\ra\f\u0002\u0002\u0007B\u0011A&\u0010\u0003\u0006}\u0001\u0011\ra\f\u0002\u0002\tB\u0011A\u0006\u0011\u0003\u0006\u0003\u0002\u0011\ra\f\u0002\u0002\u000bB\u0011Af\u0011\u0003\u0006\t\u0002\u0011\ra\f\u0002\u0002\rB\u0011AF\u0012\u0003\u0006\u000f\u0002\u0011\ra\f\u0002\u0002\u000fBI\u0011JS\u00167sqz$)R\u0007\u0002\u0017%\u00111j\u0003\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;8\u0003\u0019!\u0013N\\5uIQ\ta\n\u0005\u0002\u0013\u001f&\u0011\u0001k\u0005\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012a\u0015\t\u00041\u0015Z\u0013AC:ueV\u001cG/\u001e:feU\ta\u000bE\u0002\u0019KY\n!b\u001d;sk\u000e$XO]34+\u0005I\u0006c\u0001\r&s\u0005Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0003q\u00032\u0001G\u0013=\u0003)\u0019HO];diV\u0014X-N\u000b\u0002?B\u0019\u0001$J \u0002\u0015M$(/^2ukJ,g'F\u0001c!\rAREQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016<T#A3\u0011\u0007a)S)A\u0004j]Z,'o]3\u0015\u0005!B\u0007\"B5\n\u0001\u0004A\u0013A\u0001=1\u0001"
)
public interface GroupProduct7 extends Group, MonoidProduct7 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   // $FF: synthetic method
   static Tuple7 inverse$(final GroupProduct7 $this, final Tuple7 x0) {
      return $this.inverse(x0);
   }

   default Tuple7 inverse(final Tuple7 x0) {
      return new Tuple7(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()));
   }

   static void $init$(final GroupProduct7 $this) {
   }
}
