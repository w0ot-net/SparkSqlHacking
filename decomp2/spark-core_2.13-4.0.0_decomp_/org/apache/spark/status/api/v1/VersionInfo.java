package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2A\u0001B\u0003\u0001%!AA\u0002\u0001BC\u0002\u0013\u0005\u0011\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003\u001b\u0011\u00191\u0003\u0001\"\u0001\fO\tYa+\u001a:tS>t\u0017J\u001c4p\u0015\t1q!\u0001\u0002wc)\u0011\u0001\"C\u0001\u0004CBL'B\u0001\u0006\f\u0003\u0019\u0019H/\u0019;vg*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g-F\u0001\u001b!\tY\"E\u0004\u0002\u001dAA\u0011Q$F\u0007\u0002=)\u0011q$E\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005*\u0012A\u0002)sK\u0012,g-\u0003\u0002$I\t11\u000b\u001e:j]\u001eT!!I\u000b\u0002\rM\u0004\u0018M]6!\u0003\u0019a\u0014N\\5u}Q\u0011\u0001F\u000b\t\u0003S\u0001i\u0011!\u0002\u0005\u0006\u0019\r\u0001\rA\u0007"
)
public class VersionInfo {
   private final String spark;

   public String spark() {
      return this.spark;
   }

   public VersionInfo(final String spark) {
      this.spark = spark;
   }
}
