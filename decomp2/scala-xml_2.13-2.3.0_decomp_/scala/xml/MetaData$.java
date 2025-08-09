package scala.xml;

import java.io.Serializable;
import scala.MatchError;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.runtime.ModuleSerializationProxy;

public final class MetaData$ implements Serializable {
   public static final MetaData$ MODULE$ = new MetaData$();

   public MetaData concatenate(final MetaData attribs, final MetaData new_tail) {
      while(!attribs.isNull()) {
         MetaData var10000 = attribs.next();
         new_tail = attribs.copy(new_tail);
         attribs = var10000;
      }

      return new_tail;
   }

   public MetaData normalize(final MetaData attribs, final NamespaceBinding scope) {
      return this.iterate$1(attribs, Null$.MODULE$, (Set).MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$), scope);
   }

   public String getUniversalKey(final MetaData attrib, final NamespaceBinding scope) {
      if (attrib instanceof PrefixedAttribute) {
         PrefixedAttribute var5 = (PrefixedAttribute)attrib;
         return (new StringBuilder(0)).append(scope.getURI(var5.pre())).append(var5.key()).toString();
      } else if (attrib instanceof UnprefixedAttribute) {
         UnprefixedAttribute var6 = (UnprefixedAttribute)attrib;
         return var6.key();
      } else {
         throw new MatchError(attrib);
      }
   }

   public MetaData update(final MetaData attribs, final NamespaceBinding scope, final MetaData updates) {
      return this.normalize(this.concatenate(updates, attribs), scope);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MetaData$.class);
   }

   private final MetaData iterate$1(final MetaData md, final MetaData normalized_attribs, final Set set, final NamespaceBinding scope$1) {
      while(!md.isNull()) {
         if (md.value() == null) {
            MetaData var7 = md.next();
            set = set;
            normalized_attribs = normalized_attribs;
            md = var7;
         } else {
            String key = this.getUniversalKey(md, scope$1);
            if (!set.apply(key)) {
               return md.copy(this.iterate$1(md.next(), normalized_attribs, (Set)set.$plus(key), scope$1));
            }

            MetaData var10000 = md.next();
            set = set;
            normalized_attribs = normalized_attribs;
            md = var10000;
         }
      }

      return normalized_attribs;
   }

   private MetaData$() {
   }
}
