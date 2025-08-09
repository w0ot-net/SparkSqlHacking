package scala.reflect.api;

import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.runtime.ReflectionUtils$;

@ScalaSignature(
   bytes = "\u0006\u0005a3qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u0003\u0018\u0001\u0011\u0005\u0001$\u0002\u0003\u001e\u0001\u0001q\u0002b\u0002\u001a\u0001\u0005\u0004%\u0019a\r\u0003\u0006u\u0001\u0011\te\u000f\u0004\b\u0001\u0002\u0001\n1!\u0001B\u0011\u00159R\u0001\"\u0001\u0019\u0011\u001dIUA1A\u0007\u0002)CQAT\u0003\u0005B=CQa\u0015\u0001\u0007\u0002Q\u0013ABS1wCVs\u0017N^3sg\u0016T!\u0001D\u0007\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u000f\u001f\u00059!/\u001a4mK\u000e$(\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001a\u0005\t\u0003)Ui\u0011aC\u0005\u0003--\u0011\u0001\"\u00168jm\u0016\u00148/Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003e\u0001\"AG\u000e\u000e\u0003=I!\u0001H\b\u0003\tUs\u0017\u000e\u001e\u0002\r%VtG/[7f\u00072\f7o\u001d\u0019\u0003?%\u00022\u0001I\u0013(\u001b\u0005\t#B\u0001\u0012$\u0003\u0011a\u0017M\\4\u000b\u0003\u0011\nAA[1wC&\u0011a%\t\u0002\u0006\u00072\f7o\u001d\t\u0003Q%b\u0001\u0001B\u0005+\u0005\u0005\u0005\t\u0011!B\u0001W\t\u0019q\fJ\u0019\u0012\u00051z\u0003C\u0001\u000e.\u0013\tqsBA\u0004O_RD\u0017N\\4\u0011\u0005i\u0001\u0014BA\u0019\u0010\u0005\r\te._\u0001\u0010%VtG/[7f\u00072\f7o\u001d+bOV\tA\u0007E\u00026maj\u0011!D\u0005\u0003o5\u0011\u0001b\u00117bgN$\u0016m\u001a\t\u0003s\ti\u0011\u0001\u0001\u0002\u0007\u001b&\u0014(o\u001c:\u0012\u0005qz\u0004C\u0001\u000e>\u0013\tqtB\u0001\u0003Ok2d\u0007CA\u001d\u0006\u0005)Q\u0015M^1NSJ\u0014xN]\n\u0004\u000b\t#\u0005c\u0001\u000bDs%\u0011!h\u0003\t\u0003s\u0015K!AR$\u0003\u001bI+h\u000e^5nK6K'O]8s\u0013\tA5BA\u0004NSJ\u0014xN]:\u0002\u0017\rd\u0017m]:M_\u0006$WM]\u000b\u0002\u0017B\u0011\u0001\u0005T\u0005\u0003\u001b\u0006\u00121b\u00117bgNdu.\u00193fe\u0006AAo\\*ue&tw\rF\u0001Q!\t\u0001\u0013+\u0003\u0002SC\t11\u000b\u001e:j]\u001e\fQB];oi&lW-T5se>\u0014HCA+W!\tID\u0001C\u0003X\u0013\u0001\u00071*\u0001\u0002dY\u0002"
)
public interface JavaUniverse {
   void scala$reflect$api$JavaUniverse$_setter_$RuntimeClassTag_$eq(final ClassTag x$1);

   ClassTag RuntimeClassTag();

   JavaMirror runtimeMirror(final ClassLoader cl);

   static void $init$(final JavaUniverse $this) {
      $this.scala$reflect$api$JavaUniverse$_setter_$RuntimeClassTag_$eq(.MODULE$.apply(Class.class));
   }

   public interface JavaMirror extends Mirrors.RuntimeMirror {
      ClassLoader classLoader();

      // $FF: synthetic method
      static String toString$(final JavaMirror $this) {
         return $this.toString();
      }

      default String toString() {
         return (new StringBuilder(16)).append("JavaMirror with ").append(ReflectionUtils$.MODULE$.show(this.classLoader())).toString();
      }

      // $FF: synthetic method
      JavaUniverse scala$reflect$api$JavaUniverse$JavaMirror$$$outer();

      static void $init$(final JavaMirror $this) {
      }
   }
}
