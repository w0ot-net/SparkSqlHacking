package org.json4s;

import scala.Dynamic;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3AAC\u0006\u0001!!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\t\u0003\u0001\"\u0001#\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u0015!\u0004\u0001\"\u00116\u0011\u0015I\u0004\u0001\"\u0011;\u000f\u0015\u00195\u0002#\u0001E\r\u0015Q1\u0002#\u0001F\u0011\u0015\t\u0003\u0002\"\u0001J\u00055!\u0015P\\1nS\u000eTe+\u00197vK*\u0011A\"D\u0001\u0007UN|g\u000eN:\u000b\u00039\t1a\u001c:h\u0007\u0001\u00192\u0001A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011!\u0003G\u0005\u00033M\u0011q\u0001R=oC6L7-A\u0002sC^,\u0012\u0001\b\t\u0003;yi\u0011aC\u0005\u0003?-\u0011aA\u0013,bYV,\u0017\u0001\u0002:bo\u0002\na\u0001P5oSRtDCA\u0012%!\ti\u0002\u0001C\u0003\u001b\u0007\u0001\u0007A$A\u0007tK2,7\r\u001e#z]\u0006l\u0017n\u0019\u000b\u0003G\u001dBQ\u0001\u000b\u0003A\u0002%\nAA\\1nKB\u0011!&\r\b\u0003W=\u0002\"\u0001L\n\u000e\u00035R!AL\b\u0002\rq\u0012xn\u001c;?\u0013\t\u00014#\u0001\u0004Qe\u0016$WMZ\u0005\u0003eM\u0012aa\u0015;sS:<'B\u0001\u0019\u0014\u0003!A\u0017m\u001d5D_\u0012,G#\u0001\u001c\u0011\u0005I9\u0014B\u0001\u001d\u0014\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\u0005mr\u0004C\u0001\n=\u0013\ti4CA\u0004C_>dW-\u00198\t\u000b}2\u0001\u0019\u0001!\u0002\u0005A\f\u0004C\u0001\nB\u0013\t\u00115CA\u0002B]f\fQ\u0002R=oC6L7M\u0013,bYV,\u0007CA\u000f\t'\rA\u0011C\u0012\t\u0003;\u001dK!\u0001S\u0006\u0003-\u0011Kh.Y7jG*3\u0016\r\\;f\u00136\u0004H.[2jiN$\u0012\u0001\u0012"
)
public class DynamicJValue implements Dynamic {
   private final JValue raw;

   public static DynamicJValue dyn(final JValue jv) {
      return DynamicJValue$.MODULE$.dyn(jv);
   }

   public static JValue dynamic2monadic(final DynamicJValue dynJv) {
      return DynamicJValue$.MODULE$.dynamic2monadic(dynJv);
   }

   public static JValue dynamic2Jv(final DynamicJValue dynJv) {
      return DynamicJValue$.MODULE$.dynamic2Jv(dynJv);
   }

   public JValue raw() {
      return this.raw;
   }

   public DynamicJValue selectDynamic(final String name) {
      return new DynamicJValue(MonadicJValue$.MODULE$.$bslash$extension(this.raw(), name));
   }

   public int hashCode() {
      return this.raw().hashCode();
   }

   public boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof DynamicJValue) {
         boolean var8;
         label39: {
            label38: {
               DynamicJValue var4 = (DynamicJValue)p1;
               JValue var10000 = this.raw();
               JValue var5 = var4.raw();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label38;
                  }
               } else if (var10000.equals(var5)) {
                  break label38;
               }

               var8 = false;
               break label39;
            }

            var8 = true;
         }

         var2 = var8;
      } else if (p1 instanceof JValue) {
         boolean var10;
         label30: {
            label29: {
               JValue var6 = (JValue)p1;
               JValue var9 = this.raw();
               if (var9 == null) {
                  if (var6 == null) {
                     break label29;
                  }
               } else if (var9.equals(var6)) {
                  break label29;
               }

               var10 = false;
               break label30;
            }

            var10 = true;
         }

         var2 = var10;
      } else {
         var2 = false;
      }

      return var2;
   }

   public DynamicJValue(final JValue raw) {
      this.raw = raw;
   }
}
