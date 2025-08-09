package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class EntityRef$ extends AbstractFunction1 implements Serializable {
   public static final EntityRef$ MODULE$ = new EntityRef$();

   public final String toString() {
      return "EntityRef";
   }

   public EntityRef apply(final String entityName) {
      return new EntityRef(entityName);
   }

   public Option unapply(final EntityRef x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.entityName()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EntityRef$.class);
   }

   private EntityRef$() {
   }
}
