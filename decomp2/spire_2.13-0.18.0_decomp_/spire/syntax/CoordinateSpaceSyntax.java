package spire.syntax;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\u000bD_>\u0014H-\u001b8bi\u0016\u001c\u0006/Y2f'ftG/\u0019=\u000b\u0005\u00151\u0011AB:z]R\f\u0007PC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011\u0011CE\u0007\u0002\t%\u00111\u0003\u0002\u0002\u0018\u0013:tWM\u001d)s_\u0012,8\r^*qC\u000e,7+\u001f8uCb\fa\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\r\u0005\u0011)f.\u001b;\u0002%\r|wN\u001d3j]\u0006$Xm\u00159bG\u0016|\u0005o]\u000b\u00037\u0005\"\"\u0001\b\u0016\u0011\u0007Eir$\u0003\u0002\u001f\t\t\u00112i\\8sI&t\u0017\r^3Ta\u0006\u001cWm\u00149t!\t\u0001\u0013\u0005\u0004\u0001\u0005\u000b\t\u0012!\u0019A\u0012\u0003\u0003Y\u000b\"\u0001J\u0014\u0011\u0005-)\u0013B\u0001\u0014\r\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0003\u0015\n\u0005%b!aA!os\")1F\u0001a\u0001?\u0005\ta\u000f"
)
public interface CoordinateSpaceSyntax extends InnerProductSpaceSyntax {
   // $FF: synthetic method
   static CoordinateSpaceOps coordinateSpaceOps$(final CoordinateSpaceSyntax $this, final Object v) {
      return $this.coordinateSpaceOps(v);
   }

   default CoordinateSpaceOps coordinateSpaceOps(final Object v) {
      return new CoordinateSpaceOps(v);
   }

   static void $init$(final CoordinateSpaceSyntax $this) {
   }
}
