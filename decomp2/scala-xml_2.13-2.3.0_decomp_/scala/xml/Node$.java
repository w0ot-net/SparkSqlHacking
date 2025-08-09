package scala.xml;

import java.io.Serializable;
import scala.Some;
import scala.Tuple3;
import scala.runtime.ModuleSerializationProxy;

public final class Node$ implements Serializable {
   public static final Node$ MODULE$ = new Node$();
   private static final String EmptyNamespace = "";

   public final MetaData NoAttributes() {
      return Null$.MODULE$;
   }

   public String EmptyNamespace() {
      return EmptyNamespace;
   }

   public Some unapplySeq(final Node n) {
      return new Some(new Tuple3(n.label(), n.attributes(), n.child().toSeq()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Node$.class);
   }

   private Node$() {
   }
}
