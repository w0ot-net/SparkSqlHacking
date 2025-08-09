package org.apache.spark.sql.catalyst.util;

import org.apache.spark.sql.errors.DataTypeErrors$;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}2qAB\u0004\u0011\u0002\u0007\u0005A\u0003C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011eB\u00039\u000f!\u0005\u0011HB\u0003\u0007\u000f!\u00051\bC\u0003>\t\u0011\u0005aHA\nBiR\u0014\u0018NY;uK:\u000bW.\u001a)beN,'O\u0003\u0002\t\u0013\u0005!Q\u000f^5m\u0015\tQ1\"\u0001\u0005dCR\fG._:u\u0015\taQ\"A\u0002tc2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0003\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003u\u0001\"A\u0006\u0010\n\u0005}9\"\u0001B+oSR\f!\u0003]1sg\u0016\fE\u000f\u001e:jEV$XMT1nKR\u0011!E\u000e\t\u0004G-rcB\u0001\u0013*\u001d\t)\u0003&D\u0001'\u0015\t93#\u0001\u0004=e>|GOP\u0005\u00021%\u0011!fF\u0001\ba\u0006\u001c7.Y4f\u0013\taSFA\u0002TKFT!AK\f\u0011\u0005=\u001adB\u0001\u00192!\t)s#\u0003\u00023/\u00051\u0001K]3eK\u001aL!\u0001N\u001b\u0003\rM#(/\u001b8h\u0015\t\u0011t\u0003C\u00038\u0005\u0001\u0007a&\u0001\u0003oC6,\u0017aE!uiJL'-\u001e;f\u001d\u0006lW\rU1sg\u0016\u0014\bC\u0001\u001e\u0005\u001b\u000591c\u0001\u0003\u0016yA\u0011!\bA\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003e\u0002"
)
public interface AttributeNameParser {
   // $FF: synthetic method
   static Seq parseAttributeName$(final AttributeNameParser $this, final String name) {
      return $this.parseAttributeName(name);
   }

   default Seq parseAttributeName(final String name) {
      ArrayBuffer nameParts = .MODULE$.empty();
      ArrayBuffer tmp = .MODULE$.empty();
      boolean inBacktick = false;

      for(int i = 0; i < name.length(); ++i) {
         char var6 = scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(name), i);
         if (inBacktick) {
            if (var6 == '`') {
               if (i + 1 < name.length() && scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(name), i + 1) == '`') {
                  tmp.$plus$eq(BoxesRunTime.boxToCharacter('`'));
                  ++i;
               } else {
                  inBacktick = false;
                  if (i + 1 < name.length() && scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(name), i + 1) != '.') {
                     throw e$1(name);
                  }
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               tmp.$plus$eq(BoxesRunTime.boxToCharacter(var6));
            }
         } else if (var6 == '`') {
            if (tmp.nonEmpty()) {
               throw e$1(name);
            }

            inBacktick = true;
            BoxedUnit var7 = BoxedUnit.UNIT;
         } else if (var6 == '.') {
            if (i == 0 || scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(name), i - 1) == '.' || i == name.length() - 1) {
               throw e$1(name);
            }

            nameParts.$plus$eq(tmp.mkString());
            tmp.clear();
            BoxedUnit var8 = BoxedUnit.UNIT;
         } else {
            tmp.$plus$eq(BoxesRunTime.boxToCharacter(var6));
         }
      }

      if (inBacktick) {
         throw e$1(name);
      } else {
         nameParts.$plus$eq(tmp.mkString());
         return nameParts.toSeq();
      }
   }

   private static Throwable e$1(final String name$1) {
      return DataTypeErrors$.MODULE$.attributeNameSyntaxError(name$1);
   }

   static void $init$(final AttributeNameParser $this) {
   }
}
