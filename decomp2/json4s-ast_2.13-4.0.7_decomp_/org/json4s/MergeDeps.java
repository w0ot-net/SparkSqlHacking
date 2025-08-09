package org.json4s;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006)\u0001!\tA\u0006\u0005\b5\u0001\u0011\r\u0011b\u0001\u001c\u0011\u001d1\u0003A1A\u0005\u0004\u001d\u0012\u0011\"T3sO\u0016$U\r]:\u000b\u0005\u00199\u0011A\u00026t_:$4OC\u0001\t\u0003\ry'oZ\n\u0004\u0001)\u0001\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\r\u0005\u0002\u0012%5\tQ!\u0003\u0002\u0014\u000b\t\u0019Bj\\<Qe&|'/\u001b;z\u001b\u0016\u0014x-\u001a#fa\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0018!\tY\u0001$\u0003\u0002\u001a\u0019\t!QK\\5u\u0003\rywn\\\u000b\u00029A)\u0011#H\u0010 ?%\u0011a$\u0002\u0002\t\u001b\u0016\u0014x-\u001a#faB\u0011\u0001e\t\b\u0003#\u0005J!AI\u0003\u0002\u000f)\u001bxN\\!T)&\u0011A%\n\u0002\b\u0015>\u0013'.Z2u\u0015\t\u0011S!A\u0002bC\u0006,\u0012\u0001\u000b\t\u0006#uI\u0013&\u000b\t\u0003A)J!aK\u0013\u0003\r)\u000b%O]1z\u0001"
)
public interface MergeDeps extends LowPriorityMergeDep {
   void org$json4s$MergeDeps$_setter_$ooo_$eq(final MergeDep x$1);

   void org$json4s$MergeDeps$_setter_$aaa_$eq(final MergeDep x$1);

   MergeDep ooo();

   MergeDep aaa();

   static void $init$(final MergeDeps $this) {
      $this.org$json4s$MergeDeps$_setter_$ooo_$eq(new MergeDep() {
         public JObject apply(final JObject val1, final JObject val2) {
            return JsonAST$.MODULE$.JObject().apply(Merge$.MODULE$.mergeFields(val1.obj(), val2.obj()));
         }
      });
      $this.org$json4s$MergeDeps$_setter_$aaa_$eq(new MergeDep() {
         public JArray apply(final JArray val1, final JArray val2) {
            return JsonAST$.MODULE$.JArray().apply(Merge$.MODULE$.mergeVals(val1.arr(), val2.arr()));
         }
      });
   }
}
