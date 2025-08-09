package scala.reflect.macros;

import scala.Function0;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a!C\t\u0013!\u0003\r\t!GA\u0005\u0011\u0015q\u0002\u0001\"\u0001 \u0011\u001d\u0019\u0003A1A\u0007\u0002\u00112qa\n\u0001\u0011\u0002\u0007\u0005\u0001\u0006C\u0003\u001f\u0007\u0011\u0005q\u0004C\u00036\u0007\u0019\u0005aGB\u0004=\u0007A\u0005\u0019\u0013A\u001f\t\u000by2a\u0011A \t\u000b\u00153a\u0011\u0001$\t\u000b!\u001ba\u0011A%\u0007\u000fM\u001b\u0001\u0013aI\u0001)\")QK\u0003D\u0001-\")QK\u0003D\u0001W\")AO\u0003D\u0001m!)QO\u0003D\u0001m\")\u0001p\u0001D\u0001s\")\u0001p\u0001D\u0001\u007f\nI\u0011J\u001c;fe:\fGn\u001d\u0006\u0003'Q\ta!\\1de>\u001c(BA\u000b\u0017\u0003\u001d\u0011XM\u001a7fGRT\u0011aF\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001!\u0004\u0005\u0002\u001c95\ta#\u0003\u0002\u001e-\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u0011\u0011\u0005m\t\u0013B\u0001\u0012\u0017\u0005\u0011)f.\u001b;\u0002\u0011%tG/\u001a:oC2,\u0012!\n\t\u0003M\ri\u0011\u0001\u0001\u0002\u0013\u0007>tG/\u001a=u\u0013:$XM\u001d8bY\u0006\u0003\u0018nE\u0002\u00045%\u0002\"AK\u0019\u000f\u0005\u0019Z\u0013B\u0001\u0017.\u0003!)h.\u001b<feN,\u0017B\u0001\u00180\u0005\u001d\u0019uN\u001c;fqRT!\u0001\r\n\u0002\u0011\td\u0017mY6c_bL!AM\u001a\u0003!5\u000b7M]8J]R,'O\\1m\u0003BL\u0017B\u0001\u001b\u0013\u0005!)f.\u001b<feN,\u0017AD3oG2|7/\u001b8h\u001f^tWM]\u000b\u0002oA\u0011a\u0005O\u0005\u0003si\u0012aaU=nE>d\u0017BA\u001e\u0013\u0005\u001d\tE.[1tKN\u0014A\u0002\u0016:b]N4wN]7Ba&\u001c\"A\u0002\u000e\u0002\u000bI,7-\u001e:\u0015\u0005\u0001\u001b\u0005C\u0001\u0014B\u0013\t\u0011%H\u0001\u0003Ue\u0016,\u0007\"\u0002#\b\u0001\u0004\u0001\u0015\u0001\u0002;sK\u0016\fq\u0001Z3gCVdG\u000f\u0006\u0002A\u000f\")A\t\u0003a\u0001\u0001\u0006IAO]1og\u001a|'/\u001c\u000b\u0003\u0015J#\"\u0001Q&\t\u000b1K\u0001\u0019A'\u0002\u0017Q\u0014\u0018M\\:g_JlWM\u001d\t\u000679\u0003\u0005\u000bQ\u0005\u0003\u001fZ\u0011\u0011BR;oGRLwN\u001c\u001a\u0011\u0005E3Q\"A\u0002\t\u000b\u0011K\u0001\u0019\u0001!\u0003%QK\b/\u001b8h)J\fgn\u001d4pe6\f\u0005/[\n\u0004\u0015i\u0001\u0016aB1u\u001f^tWM]\u000b\u0003/n#\"\u0001W5\u0015\u0005e#\u0007C\u0001.\\\u0019\u0001!Q\u0001X\u0006C\u0002u\u0013\u0011\u0001V\t\u0003=\u0006\u0004\"aG0\n\u0005\u00014\"a\u0002(pi\"Lgn\u001a\t\u00037\tL!a\u0019\f\u0003\u0007\u0005s\u0017\u0010\u0003\u0004f\u0017\u0011\u0005\rAZ\u0001\u0003_B\u00042aG4Z\u0013\tAgC\u0001\u0005=Eft\u0017-\\3?\u0011\u0015Q7\u00021\u00018\u0003\u0015ywO\\3s+\taw\u000eF\u0002neN$\"A\u001c9\u0011\u0005i{G!\u0002/\r\u0005\u0004i\u0006BB3\r\t\u0003\u0007\u0011\u000fE\u0002\u001cO:DQ\u0001\u0012\u0007A\u0002\u0001CQA\u001b\u0007A\u0002]\nAbY;se\u0016tGoT<oKJ\f\u0011\u0002^=qK\u000eDWmY6\u0015\u0005\u0001;\b\"\u0002#\u000f\u0001\u0004\u0001\u0015a\u0004;za&tw\r\u0016:b]N4wN]7\u0015\u0005itHC\u0001!|\u0011\u0015au\u00021\u0001}!\u0015Yb\nQ?A!\t\t&\u0002C\u0003E\u001f\u0001\u0007\u0001\t\u0006\u0004\u0002\u0002\u0005\u0015\u0011q\u0001\u000b\u0004\u0001\u0006\r\u0001\"\u0002'\u0011\u0001\u0004a\b\"\u0002#\u0011\u0001\u0004\u0001\u0005\"\u00026\u0011\u0001\u00049\u0004cAA\u0006[5\tq\u0006"
)
public interface Internals {
   ContextInternalApi internal();

   static void $init$(final Internals $this) {
   }

   public interface ContextInternalApi extends Universe.MacroInternalApi {
      Symbols.SymbolApi enclosingOwner();

      Trees.TreeApi transform(final Trees.TreeApi tree, final Function2 transformer);

      Trees.TreeApi typingTransform(final Trees.TreeApi tree, final Function2 transformer);

      Trees.TreeApi typingTransform(final Trees.TreeApi tree, final Symbols.SymbolApi owner, final Function2 transformer);

      // $FF: synthetic method
      Internals scala$reflect$macros$Internals$ContextInternalApi$$$outer();

      static void $init$(final ContextInternalApi $this) {
      }

      public interface TransformApi {
         Trees.TreeApi recur(final Trees.TreeApi tree);

         Trees.TreeApi default(final Trees.TreeApi tree);
      }

      public interface TypingTransformApi extends TransformApi {
         Object atOwner(final Symbols.SymbolApi owner, final Function0 op);

         Object atOwner(final Trees.TreeApi tree, final Symbols.SymbolApi owner, final Function0 op);

         Symbols.SymbolApi currentOwner();

         Trees.TreeApi typecheck(final Trees.TreeApi tree);
      }
   }
}
