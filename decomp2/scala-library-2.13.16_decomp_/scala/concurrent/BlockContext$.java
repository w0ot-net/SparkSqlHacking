package scala.concurrent;

import scala.Function0;
import scala.Function1;

public final class BlockContext$ {
   public static final BlockContext$ MODULE$ = new BlockContext$();
   private static final ThreadLocal contextLocal = new ThreadLocal();

   public final BlockContext defaultBlockContext() {
      return BlockContext.DefaultBlockContext$.MODULE$;
   }

   private final BlockContext prefer(final BlockContext candidate) {
      if (candidate != null) {
         return candidate;
      } else {
         Thread t = Thread.currentThread();
         return (BlockContext)(t instanceof BlockContext ? (BlockContext)t : BlockContext.DefaultBlockContext$.MODULE$);
      }
   }

   public final BlockContext current() {
      return this.prefer((BlockContext)contextLocal.get());
   }

   public final Object withBlockContext(final BlockContext blockContext, final Function0 body) {
      BlockContext old = (BlockContext)contextLocal.get();
      if (old == blockContext) {
         return body.apply();
      } else {
         contextLocal.set(blockContext);

         Object var10000;
         try {
            var10000 = body.apply();
         } finally {
            contextLocal.set(old);
         }

         return var10000;
      }
   }

   public final Object usingBlockContext(final BlockContext blockContext, final Function1 f) {
      BlockContext old = (BlockContext)contextLocal.get();
      if (old == blockContext) {
         return f.apply(this.prefer(old));
      } else {
         contextLocal.set(blockContext);

         Object var10000;
         try {
            var10000 = f.apply(this.prefer(old));
         } finally {
            contextLocal.set(old);
         }

         return var10000;
      }
   }

   private BlockContext$() {
   }
}
