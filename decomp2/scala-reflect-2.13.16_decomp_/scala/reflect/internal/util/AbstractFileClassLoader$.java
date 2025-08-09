package scala.reflect.internal.util;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxedUnit;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;

public final class AbstractFileClassLoader$ {
   public static final AbstractFileClassLoader$ MODULE$ = new AbstractFileClassLoader$();

   public final AbstractFile lookupPath(final AbstractFile base, final Seq pathParts, final boolean directory) {
      Object var4 = new Object();

      try {
         ObjectRef file = new ObjectRef(base);
         ((IterableOnceOps)pathParts.init()).foreach((dirPart) -> {
            $anonfun$lookupPath$1(file, var4, dirPart);
            return BoxedUnit.UNIT;
         });
         return ((AbstractFile)file.elem).lookupName((String)pathParts.last(), directory);
      } catch (NonLocalReturnControl var7) {
         if (var7.key() == var4) {
            return (AbstractFile)var7.value();
         } else {
            throw var7;
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$lookupPath$1(final ObjectRef file$1, final Object nonLocalReturnKey1$1, final String dirPart) {
      file$1.elem = ((AbstractFile)file$1.elem).lookupName(dirPart, true);
      if ((AbstractFile)file$1.elem == null) {
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, (Object)null);
      }
   }

   private AbstractFileClassLoader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
