package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005):Q!\u0002\u0004\t\u000251Qa\u0004\u0004\t\u0002AAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\u0002iAQ\u0001K\u0001\u0005\u0002%\n!b\u00159be.4\u0015\u000e\\3t\u0015\t9\u0001\"A\u0003ta\u0006\u00148N\u0003\u0002\n\u0015\u00051\u0011\r]1dQ\u0016T\u0011aC\u0001\u0004_J<7\u0001\u0001\t\u0003\u001d\u0005i\u0011A\u0002\u0002\u000b'B\f'o\u001b$jY\u0016\u001c8CA\u0001\u0012!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!D\u0001\u0004O\u0016$HCA\u000e'!\ta2E\u0004\u0002\u001eCA\u0011adE\u0007\u0002?)\u0011\u0001\u0005D\u0001\u0007yI|w\u000e\u001e \n\u0005\t\u001a\u0012A\u0002)sK\u0012,g-\u0003\u0002%K\t11\u000b\u001e:j]\u001eT!AI\n\t\u000b\u001d\u001a\u0001\u0019A\u000e\u0002\u0011\u0019LG.\u001a8b[\u0016\f\u0001cZ3u%>|G\u000fR5sK\u000e$xN]=\u0015\u0003m\u0001"
)
public final class SparkFiles {
   public static String getRootDirectory() {
      return SparkFiles$.MODULE$.getRootDirectory();
   }

   public static String get(final String filename) {
      return SparkFiles$.MODULE$.get(filename);
   }
}
