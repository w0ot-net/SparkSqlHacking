package spire.syntax;

import cats.kernel.Group;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\u0006He>,\boU=oi\u0006D(BA\u0003\u0007\u0003\u0019\u0019\u0018P\u001c;bq*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001Q\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011A\"T8o_&$7+\u001f8uCb\fa\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\r\u0005\u0011)f.\u001b;\u0002\u0011\u001d\u0014x.\u001e9PaN,\"a\u0007\u0012\u0015\u0005qiDCA\u000f,!\r\tb\u0004I\u0005\u0003?\u0011\u0011\u0001b\u0012:pkB|\u0005o\u001d\t\u0003C\tb\u0001\u0001B\u0003$\u0005\t\u0007AEA\u0001B#\t)\u0003\u0006\u0005\u0002\fM%\u0011q\u0005\u0004\u0002\b\u001d>$\b.\u001b8h!\tY\u0011&\u0003\u0002+\u0019\t\u0019\u0011I\\=\t\u000f1\u0012\u0011\u0011!a\u0002[\u0005YQM^5eK:\u001cW\rJ\u00191!\rq#\b\t\b\u0003_]r!\u0001M\u001b\u000f\u0005E\"T\"\u0001\u001a\u000b\u0005MB\u0011A\u0002\u001fs_>$h(C\u0001\b\u0013\t1d!A\u0004bY\u001e,'M]1\n\u0005aJ\u0014a\u00029bG.\fw-\u001a\u0006\u0003m\u0019I!a\u000f\u001f\u0003\u000b\u001d\u0013x.\u001e9\u000b\u0005aJ\u0004\"\u0002 \u0003\u0001\u0004\u0001\u0013!A1"
)
public interface GroupSyntax extends MonoidSyntax {
   // $FF: synthetic method
   static GroupOps groupOps$(final GroupSyntax $this, final Object a, final Group evidence$10) {
      return $this.groupOps(a, evidence$10);
   }

   default GroupOps groupOps(final Object a, final Group evidence$10) {
      return new GroupOps(a, evidence$10);
   }

   static void $init$(final GroupSyntax $this) {
   }
}
