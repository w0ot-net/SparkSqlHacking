package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0001\u0003\u0014\u0005\u0006+\u0001!\tA\u0006\u0005\u00065\u0001!\ta\u0007\u0005\u0006I\u0001!\t!\n\u0005\u0006Y\u0001!\t!\f\u0005\u0006Y\u0001!\t\u0001\u000e\u0005\b{\u0001\t\n\u0011\"\u0001?\u0011\u001dI\u0005!%A\u0005\u0002)\u0013\u0011cQ1qiV\u0014X\r\u001a,be&\f'\r\\3t\u0015\tQ1\"\u0001\u0005j]R,'O\\1m\u0015\taQ\"A\u0004sK\u001adWm\u0019;\u000b\u00039\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001#A\u0011!cE\u0007\u0002\u001b%\u0011A#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u00059\u0002C\u0001\n\u0019\u0013\tIRB\u0001\u0003V]&$\u0018aD2baR,(/\u001a,be&\f'\r\\3\u0015\u0005]a\u0002\"B\u000f\u0003\u0001\u0004q\u0012\u0001\u0002<cY\u0016\u0004\"a\b\u0011\u000e\u0003\u0001I!!\t\u0012\u0003\rMKXNY8m\u0013\t\u0019\u0013BA\u0004Ts6\u0014w\u000e\\:\u00023I,g-\u001a:f]\u000e,7)\u00199ukJ,GMV1sS\u0006\u0014G.\u001a\u000b\u0003M-\u0002\"aH\u0014\n\u0005!J#\u0001\u0002+sK\u0016L!AK\u0005\u0003\u000bQ\u0013X-Z:\t\u000bu\u0019\u0001\u0019\u0001\u0010\u0002)\r\f\u0007\u000f^;sK\u00124\u0016M]5bE2,G+\u001f9f)\tq3\u0007\u0005\u0002 _%\u0011\u0001'\r\u0002\u0005)f\u0004X-\u0003\u00023\u0013\t)A+\u001f9fg\")Q\u0004\u0002a\u0001=Q!a&\u000e\u001c9\u0011\u0015iR\u00011\u0001\u001f\u0011\u001d9T\u0001%AA\u00029\n1\u0001\u001e9f\u0011\u001dIT\u0001%AA\u0002i\n1\"\u001a:bg\u0016$G+\u001f9fgB\u0011!cO\u0005\u0003y5\u0011qAQ8pY\u0016\fg.\u0001\u0010dCB$XO]3e-\u0006\u0014\u0018.\u00192mKRK\b/\u001a\u0013eK\u001a\fW\u000f\u001c;%eU\tqH\u000b\u0002/\u0001.\n\u0011\t\u0005\u0002C\u000f6\t1I\u0003\u0002E\u000b\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\r6\t!\"\u00198o_R\fG/[8o\u0013\tA5IA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fadY1qiV\u0014X\r\u001a,be&\f'\r\\3UsB,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003-S#A\u000f!\u0011\u00055sU\"A\u0005\n\u0005=K!aC*z[\n|G\u000eV1cY\u0016\u0004"
)
public interface CapturedVariables {
   // $FF: synthetic method
   static void captureVariable$(final CapturedVariables $this, final Symbols.Symbol vble) {
      $this.captureVariable(vble);
   }

   default void captureVariable(final Symbols.Symbol vble) {
      vble.setFlag(65536L);
   }

   // $FF: synthetic method
   static Trees.Tree referenceCapturedVariable$(final CapturedVariables $this, final Symbols.Symbol vble) {
      return $this.referenceCapturedVariable(vble);
   }

   default Trees.Tree referenceCapturedVariable(final Symbols.Symbol vble) {
      return (SymbolTable)this.new ReferenceToBoxed(((Trees)this).Ident(vble));
   }

   // $FF: synthetic method
   static Types.Type capturedVariableType$(final CapturedVariables $this, final Symbols.Symbol vble) {
      return $this.capturedVariableType(vble);
   }

   default Types.Type capturedVariableType(final Symbols.Symbol vble) {
      return this.capturedVariableType(vble, ((Types)this).NoType(), false);
   }

   // $FF: synthetic method
   static Types.Type capturedVariableType$(final CapturedVariables $this, final Symbols.Symbol vble, final Types.Type tpe, final boolean erasedTypes) {
      return $this.capturedVariableType(vble, tpe, erasedTypes);
   }

   default Types.Type capturedVariableType(final Symbols.Symbol vble, final Types.Type tpe, final boolean erasedTypes) {
      Types.Type var10000;
      label25: {
         label28: {
            Types.NoType$ var5 = ((Types)this).NoType();
            if (tpe == null) {
               if (var5 != null) {
                  break label28;
               }
            } else if (!tpe.equals(var5)) {
               break label28;
            }

            if (vble == null) {
               throw null;
            }

            var10000 = vble.tpe_$times();
            break label25;
         }

         var10000 = tpe;
      }

      Types.Type tpe1 = var10000;
      Symbols.Symbol symClass = tpe1.typeSymbol();
      return vble.hasAnnotation(((Definitions)this).definitions().VolatileAttr()) ? this.refType$1(((Definitions)this).definitions().volatileRefClass(), ((Definitions)this).definitions().VolatileObjectRefClass(), symClass, erasedTypes, tpe1) : this.refType$1(((Definitions)this).definitions().refClass(), ((Definitions)this).definitions().ObjectRefClass(), symClass, erasedTypes, tpe1);
   }

   // $FF: synthetic method
   static Types.Type capturedVariableType$default$2$(final CapturedVariables $this) {
      return $this.capturedVariableType$default$2();
   }

   default Types.Type capturedVariableType$default$2() {
      return ((Types)this).NoType();
   }

   // $FF: synthetic method
   static boolean capturedVariableType$default$3$(final CapturedVariables $this) {
      return $this.capturedVariableType$default$3();
   }

   default boolean capturedVariableType$default$3() {
      return false;
   }

   private Types.Type refType$1(final Map valueRef, final Symbols.Symbol objectRefClass, final Symbols.Symbol symClass$1, final boolean erasedTypes$1, final Types.Type tpe1$1) {
      label31: {
         if (((Definitions)this).definitions().isPrimitiveValueClass(symClass$1)) {
            Symbols.ClassSymbol var6 = ((Definitions)this).definitions().UnitClass();
            if (symClass$1 == null) {
               if (var6 != null) {
                  break label31;
               }
            } else if (!symClass$1.equals(var6)) {
               break label31;
            }
         }

         if (erasedTypes$1) {
            if (objectRefClass == null) {
               throw null;
            }

            return objectRefClass.tpe_$times();
         }

         Types var10000 = (Types)this;
         List $colon$colon_this = .MODULE$;
         scala.collection.immutable..colon.colon var10002 = new scala.collection.immutable..colon.colon(tpe1$1, $colon$colon_this);
         $colon$colon_this = null;
         return var10000.appliedType((Symbols.Symbol)objectRefClass, (List)var10002);
      }

      Symbols.Symbol var9 = (Symbols.Symbol)valueRef.apply(symClass$1);
      if (var9 == null) {
         throw null;
      } else {
         return var9.tpe_$times();
      }
   }

   static void $init$(final CapturedVariables $this) {
   }
}
