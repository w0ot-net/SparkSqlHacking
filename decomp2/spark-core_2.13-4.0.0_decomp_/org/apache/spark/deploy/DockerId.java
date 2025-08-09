package org.apache.spark.deploy;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2A!\u0002\u0004\u0005\u001f!Aa\u0003\u0001BC\u0002\u0013\u0005q\u0003\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003\u0019\u0011\u0015!\u0003\u0001\"\u0001&\u0011\u0015I\u0003\u0001\"\u0011+\u0005!!unY6fe&#'BA\u0004\t\u0003\u0019!W\r\u001d7ps*\u0011\u0011BC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00171\ta!\u00199bG\",'\"A\u0007\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0001\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g-\u0001\u0002jIV\t\u0001\u0004\u0005\u0002\u001aA9\u0011!D\b\t\u00037Ii\u0011\u0001\b\u0006\u0003;9\ta\u0001\u0010:p_Rt\u0014BA\u0010\u0013\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011E\t\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005}\u0011\u0012aA5eA\u00051A(\u001b8jiz\"\"A\n\u0015\u0011\u0005\u001d\u0002Q\"\u0001\u0004\t\u000bY\u0019\u0001\u0019\u0001\r\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\u0007"
)
public class DockerId {
   private final String id;

   public String id() {
      return this.id;
   }

   public String toString() {
      return this.id();
   }

   public DockerId(final String id) {
      this.id = id;
   }
}
