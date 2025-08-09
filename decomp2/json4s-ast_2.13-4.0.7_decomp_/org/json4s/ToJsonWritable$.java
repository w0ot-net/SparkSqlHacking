package org.json4s;

import scala.runtime.BoxesRunTime;

public final class ToJsonWritable$ {
   public static final ToJsonWritable$ MODULE$ = new ToJsonWritable$();

   public final JValue asJValue$extension(final Object $this, final Writer writer) {
      return writer.write($this);
   }

   public final int hashCode$extension(final Object $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Object $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof ToJsonWritable) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var10000;
      if (var3) {
         Object var5 = x$1 == null ? null : ((ToJsonWritable)x$1).org$json4s$ToJsonWritable$$a();
         if (BoxesRunTime.equals($this, var5)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private ToJsonWritable$() {
   }
}
