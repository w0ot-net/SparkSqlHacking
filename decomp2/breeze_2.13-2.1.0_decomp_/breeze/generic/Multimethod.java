package breeze.generic;

import scala.collection.immutable.Map;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005A4\u0001\u0002C\u0005\u0011\u0002\u0007\u0005a\u0002\u001c\u0005\u0006K\u0001!\tA\n\u0005\u0006U\u0001!\tb\u000b\u0005\u0006k\u0001!\tB\u000e\u0005\u0006\u0019\u00021\t\"\u0014\u0005\u0006!\u0002!\t!\u0015\u0005\u0006'\u0002!\t\u0001\u0016\u0005\fE\u0002\u0001\n1!A\u0001\n\u0013\u00197NA\u0006Nk2$\u0018.\\3uQ>$'B\u0001\u0006\f\u0003\u001d9WM\\3sS\u000eT\u0011\u0001D\u0001\u0007EJ,WM_3\u0004\u0001U!q\u0002\b\u001a.'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]A\"$D\u0001\n\u0013\tI\u0012BA\u0006N\u001bJ+w-[:uef\f\u0004CA\u000e\u001d\u0019\u0001!Q!\b\u0001C\u0002y\u0011a!T3uQ>$\u0017CA\u0010#!\t\t\u0002%\u0003\u0002\"%\t9aj\u001c;iS:<\u0007CA\t$\u0013\t!#CA\u0002B]f\fa\u0001J5oSR$C#A\u0014\u0011\u0005EA\u0013BA\u0015\u0013\u0005\u0011)f.\u001b;\u0002\u001d\tLg\u000eZ5oO6K7o]5oOR\u0011Af\f\t\u000375\"QA\f\u0001C\u0002y\u0011\u0011A\u0015\u0005\u0006a\t\u0001\r!M\u0001\u0002CB\u00111D\r\u0003\u0006g\u0001\u0011\r\u0001\u000e\u0002\u0002\u0003F\u0011q\u0004E\u0001\u0010[VdG/\u001b9mK>\u0003H/[8ogR\u0019qd\u000e\u001d\t\u000bA\u001a\u0001\u0019A\u0019\t\u000be\u001a\u0001\u0019\u0001\u001e\u0002\u00035\u0004Ba\u000f\"F59\u0011A\b\u0011\t\u0003{Ii\u0011A\u0010\u0006\u0003\u007f5\ta\u0001\u0010:p_Rt\u0014BA!\u0013\u0003\u0019\u0001&/\u001a3fM&\u00111\t\u0012\u0002\u0004\u001b\u0006\u0004(BA!\u0013a\t1%\nE\u0002<\u000f&K!\u0001\u0013#\u0003\u000b\rc\u0017m]:\u0011\u0005mQE!C&9\u0003\u0003\u0005\tQ!\u0001\u001f\u0005\ryF%M\u0001\tI>lU\r\u001e5pIR\u0019AFT(\t\u000be\"\u0001\u0019\u0001\u000e\t\u000bA\"\u0001\u0019A\u0019\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u00051\u0012\u0006\"\u0002\u0019\u0006\u0001\u0004\t\u0014\u0001\u0003:fO&\u001cH/\u001a:\u0016\u0005UkFC\u0001,a)\t9s\u000bC\u0003Y\r\u0001\u000f\u0011,\u0001\u0003nC:\f\u0005cA\u001e[9&\u00111\f\u0012\u0002\t\u001b\u0006t\u0017NZ3tiB\u00111$\u0018\u0003\u0006=\u001a\u0011\ra\u0018\u0002\u0003\u0003\u0006\u000b\"aH\u0019\t\u000b\u00054\u0001\u0019\u0001\u000e\u0002\u0005=\u0004\u0018AD:va\u0016\u0014HE]3hSN$XM\u001d\u000b\u0004O\u0011T\u0007\"\u0002\u0019\b\u0001\u0004)\u0007G\u00014i!\rYti\u001a\t\u00037!$\u0011\"\u001b3\u0002\u0002\u0003\u0005)\u0011\u0001\u0010\u0003\t}#SG\u000e\u0005\u0006C\u001e\u0001\rAG\u0005\u0003'b\u00112!\\8\u001b\r\u0011q\u0007\u0001\u00017\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u000b]\u0001!$\r\u0017"
)
public interface Multimethod extends MMRegistry1 {
   // $FF: synthetic method
   void breeze$generic$Multimethod$$super$register(final Class a, final Object op);

   // $FF: synthetic method
   static Object bindingMissing$(final Multimethod $this, final Object a) {
      return $this.bindingMissing(a);
   }

   default Object bindingMissing(final Object a) {
      throw new UnsupportedOperationException("Types not found!");
   }

   // $FF: synthetic method
   static Nothing multipleOptions$(final Multimethod $this, final Object a, final Map m) {
      return $this.multipleOptions(a, m);
   }

   default Nothing multipleOptions(final Object a, final Map m) {
      throw new RuntimeException((new StringBuilder(30)).append("Multiple bindings for method: ").append(m).toString());
   }

   Object doMethod(final Object m, final Object a);

   // $FF: synthetic method
   static Object apply$(final Multimethod $this, final Object a) {
      return $this.apply(a);
   }

   default Object apply(final Object a) {
      Class ac = a.getClass();
      Object cached = this.cache().get(ac);
      if (cached != null) {
         throw (Nothing)cached;
      } else {
         Map options = this.resolve(ac, this.resolve$default$2());
         int var5 = options.size();
         Object var10000;
         switch (var5) {
            case 0:
               var10000 = this.bindingMissing(a);
               break;
            case 1:
               Object method = options.values().head();
               this.cache().put(ac, method);
               var10000 = this.doMethod(method, a);
               break;
            default:
               Map selected = this.selectBestOption(options);
               if (selected.size() != 1) {
                  throw this.multipleOptions(a, options);
               }

               Object method = selected.values().head();
               this.cache().put(ac, method);
               var10000 = this.doMethod(method, a);
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   static void register$(final Multimethod $this, final Object op, final Manifest manA) {
      $this.register(op, manA);
   }

   default void register(final Object op, final Manifest manA) {
      this.breeze$generic$Multimethod$$super$register(manA.runtimeClass(), op);
   }

   static void $init$(final Multimethod $this) {
   }
}
