package org.json4s;

import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public final class ReaderSyntax$ {
   public static final ReaderSyntax$ MODULE$ = new ReaderSyntax$();

   public final Object as$extension(final JValue $this, final Reader reader) {
      Either var4 = reader.readEither($this);
      if (var4 instanceof Right) {
         Right var5 = (Right)var4;
         Object x = var5.value();
         return x;
      } else if (var4 instanceof Left) {
         Left var7 = (Left)var4;
         MappingException x = (MappingException)var7.value();
         throw x;
      } else {
         throw new MatchError(var4);
      }
   }

   public final Option getAs$extension(final JValue $this, final Reader reader) {
      Either var4 = reader.readEither($this);
      Object var3;
      if (var4 instanceof Right) {
         Right var5 = (Right)var4;
         Object x = var5.value();
         var3 = new Some(x);
      } else {
         if (!(var4 instanceof Left)) {
            throw new MatchError(var4);
         }

         var3 = .MODULE$;
      }

      return (Option)var3;
   }

   public final Object getAsOrElse$extension(final JValue $this, final Function0 default, final Reader reader) {
      return this.getAs$extension($this, reader).getOrElse(default);
   }

   public final int hashCode$extension(final JValue $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final JValue $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof ReaderSyntax) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               JValue var5 = x$1 == null ? null : ((ReaderSyntax)x$1).org$json4s$ReaderSyntax$$jv();
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

   private ReaderSyntax$() {
   }
}
