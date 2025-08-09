package org.apache.spark.ui.storage;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q:aa\u0004\t\t\u0002IQbA\u0002\u000f\u0011\u0011\u0003\u0011R\u0004C\u0003%\u0003\u0011\u0005a\u0005C\u0004(\u0003\t\u0007I\u0011\u0001\u0015\t\rE\n\u0001\u0015!\u0003*\u0011\u001d\u0011\u0014A1A\u0005\u0002!BaaM\u0001!\u0002\u0013I\u0003b\u0002\u001b\u0002\u0005\u0004%\t\u0001\u000b\u0005\u0007k\u0005\u0001\u000b\u0011B\u0015\t\u000fY\n!\u0019!C\u0001Q!1q'\u0001Q\u0001\n%Bq\u0001O\u0001C\u0002\u0013\u0005\u0001\u0006\u0003\u0004:\u0003\u0001\u0006I!\u000b\u0005\bu\u0005\u0011\r\u0011\"\u0001)\u0011\u0019Y\u0014\u0001)A\u0005S\u0005AAk\\8m)&\u00048O\u0003\u0002\u0012%\u000591\u000f^8sC\u001e,'BA\n\u0015\u0003\t)\u0018N\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h!\tY\u0012!D\u0001\u0011\u0005!!vn\u001c7USB\u001c8CA\u0001\u001f!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u001b\u0003!\u0011F\tR0O\u00036+U#A\u0015\u0011\u0005)zS\"A\u0016\u000b\u00051j\u0013\u0001\u00027b]\u001eT\u0011AL\u0001\u0005U\u00064\u0018-\u0003\u00021W\t11\u000b\u001e:j]\u001e\f\u0011B\u0015#E?:\u000bU*\u0012\u0011\u0002\u001bM#vJU!H\u000b~cUIV#M\u00039\u0019Fk\u0014*B\u000f\u0016{F*\u0012,F\u0019\u0002\n\u0011cQ!D\u0011\u0016#u\fU!S)&#\u0016j\u0014(T\u0003I\u0019\u0015i\u0011%F\t~\u0003\u0016I\u0015+J)&{ej\u0015\u0011\u0002\u001f\u0019\u0013\u0016i\u0011+J\u001f:{6)Q\"I\u000b\u0012\u000b\u0001C\u0012*B\u0007RKuJT0D\u0003\u000eCU\t\u0012\u0011\u0002\u001dMK%,R0J\u001d~kU)T(S3\u0006y1+\u0013.F?&su,T#N\u001fJK\u0006%\u0001\u0007T\u0013j+ul\u0014(`\t&\u001b6*A\u0007T\u0013j+ul\u0014(`\t&\u001b6\n\t"
)
public final class ToolTips {
   public static String SIZE_ON_DISK() {
      return ToolTips$.MODULE$.SIZE_ON_DISK();
   }

   public static String SIZE_IN_MEMORY() {
      return ToolTips$.MODULE$.SIZE_IN_MEMORY();
   }

   public static String FRACTION_CACHED() {
      return ToolTips$.MODULE$.FRACTION_CACHED();
   }

   public static String CACHED_PARTITIONS() {
      return ToolTips$.MODULE$.CACHED_PARTITIONS();
   }

   public static String STORAGE_LEVEL() {
      return ToolTips$.MODULE$.STORAGE_LEVEL();
   }

   public static String RDD_NAME() {
      return ToolTips$.MODULE$.RDD_NAME();
   }
}
