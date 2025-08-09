package cats.kernel.instances;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007I1\u0001\r\u0003\u001b\rC\u0017M]%ogR\fgnY3t\u0015\t)a!A\u0005j]N$\u0018M\\2fg*\u0011q\u0001C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003%\tAaY1ug\u000e\u00011C\u0001\u0001\r!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0006\t\u0003\u001bUI!A\u0006\b\u0003\tUs\u0017\u000e^\u0001\u001aG\u0006$8oS3s]\u0016d7\u000b\u001e3Pe\u0012,'OR8s\u0007\"\f'/F\u0001\u001a%\u0011QB\u0004I\u0014\u0007\tm\u0001\u0001!\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003;yi\u0011\u0001B\u0005\u0003?\u0011\u0011\u0011b\u00115be>\u0013H-\u001a:\u0011\u0007\u0005\u0012C%D\u0001\u0007\u0013\t\u0019cA\u0001\u0003ICND\u0007CA\u0007&\u0013\t1cB\u0001\u0003DQ\u0006\u0014\bcA\u0011)I%\u0011\u0011F\u0002\u0002\u0012\u0005>,h\u000eZ3e\u000b:,X.\u001a:bE2,\u0007"
)
public interface CharInstances {
   void cats$kernel$instances$CharInstances$_setter_$catsKernelStdOrderForChar_$eq(final CharOrder x$1);

   CharOrder catsKernelStdOrderForChar();

   static void $init$(final CharInstances $this) {
      $this.cats$kernel$instances$CharInstances$_setter_$catsKernelStdOrderForChar_$eq(new CharOrder());
   }
}
