package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Text$ implements Serializable {
   public static final Text$ MODULE$ = new Text$();

   public Text apply(final String data) {
      return new Text(data);
   }

   public Option unapply(final Object other) {
      if (other instanceof Text) {
         Text var4 = (Text)other;
         return new Some(var4.data());
      } else {
         return .MODULE$;
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Text$.class);
   }

   private Text$() {
   }
}
