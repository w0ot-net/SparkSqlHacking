package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ElemDecl$ extends AbstractFunction2 implements Serializable {
   public static final ElemDecl$ MODULE$ = new ElemDecl$();

   public final String toString() {
      return "ElemDecl";
   }

   public ElemDecl apply(final String name, final ContentModel contentModel) {
      return new ElemDecl(name, contentModel);
   }

   public Option unapply(final ElemDecl x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.contentModel())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElemDecl$.class);
   }

   private ElemDecl$() {
   }
}
