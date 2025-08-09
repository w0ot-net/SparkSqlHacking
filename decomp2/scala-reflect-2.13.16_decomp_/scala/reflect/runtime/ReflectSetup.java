package scala.reflect.runtime;

import scala.collection.ArrayOps;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;
import scala.reflect.internal.SomePhase$;

@ScalaSignature(
   bytes = "\u0006\u0005u1\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\b1\u0001\u0011\r\u0011\"\u0011\u001a\u00051\u0011VM\u001a7fGR\u001cV\r^;q\u0015\t)a!A\u0004sk:$\u0018.\\3\u000b\u0005\u001dA\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0013\u0005)1oY1mCN\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0003\u001d\u0019\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003!5\u00111bU=nE>dG+\u00192mK\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0015!\t)b#D\u0001\t\u0013\t9\u0002B\u0001\u0003V]&$\u0018\u0001D2veJ,g\u000e\u001e*v]&#W#\u0001\u000e\u0011\u0005UY\u0012B\u0001\u000f\t\u0005\rIe\u000e\u001e"
)
public interface ReflectSetup {
   void scala$reflect$runtime$ReflectSetup$_setter_$currentRunId_$eq(final int x$1);

   int currentRunId();

   static void $init$(final ReflectSetup $this) {
      ArrayOps var10000 = .MODULE$;
      Phase[] var10001 = new Phase[]{NoPhase$.MODULE$, SomePhase$.MODULE$};
      Phase[] copyToArray$extension_xs = ((scala.reflect.internal.SymbolTable)$this).phaseWithId();
      Phase[] copyToArray$extension_$this = var10001;
      ArrayOps copyToArray$extension_this = var10000;
      int copyToArray$extension_copyToArray$extension_start = 0;
      copyToArray$extension_this.copyToArray$extension(copyToArray$extension_$this, copyToArray$extension_xs, copyToArray$extension_copyToArray$extension_start, Integer.MAX_VALUE);
      Object var5 = null;
      copyToArray$extension_$this = null;
      copyToArray$extension_xs = null;
      $this.scala$reflect$runtime$ReflectSetup$_setter_$currentRunId_$eq(1);
      ((scala.reflect.internal.SymbolTable)$this).phase_$eq(SomePhase$.MODULE$);
   }
}
