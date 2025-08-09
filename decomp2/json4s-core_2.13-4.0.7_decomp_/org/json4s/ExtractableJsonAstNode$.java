package org.json4s;

import scala.Function0;
import scala.Option;
import scala.reflect.Manifest;

public final class ExtractableJsonAstNode$ {
   public static final ExtractableJsonAstNode$ MODULE$ = new ExtractableJsonAstNode$();

   public final Object extract$extension(final JValue $this, final Formats formats, final Manifest mf) {
      return Extraction$.MODULE$.extract($this, formats, mf);
   }

   public final Option extractOpt$extension(final JValue $this, final Formats formats, final Manifest mf) {
      return Extraction$.MODULE$.extractOpt($this, formats, mf);
   }

   public final Object extractOrElse$extension(final JValue $this, final Function0 default, final Formats formats, final Manifest mf) {
      return Extraction$.MODULE$.extractOpt($this, formats, mf).getOrElse(default);
   }

   public final int hashCode$extension(final JValue $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final JValue $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof ExtractableJsonAstNode) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               JValue var5 = x$1 == null ? null : ((ExtractableJsonAstNode)x$1).org$json4s$ExtractableJsonAstNode$$jv();
               if ($this == null) {
                  if (var5 == null) {
                     break label31;
                  }
               } else if ($this.equals(var5)) {
                  break label31;
               }

               var7 = false;
               break label32;
            }

            var7 = true;
         }

         if (var7) {
            var7 = true;
            return var7;
         }
      }

      var7 = false;
      return var7;
   }

   private ExtractableJsonAstNode$() {
   }
}
