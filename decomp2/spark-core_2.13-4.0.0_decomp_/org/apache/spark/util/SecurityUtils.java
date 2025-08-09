package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;a!\u0004\b\t\u0002A1bA\u0002\r\u000f\u0011\u0003\u0001\u0012\u0004C\u0003!\u0003\u0011\u0005!\u0005C\u0004$\u0003\t\u0007I\u0011\u0002\u0013\t\r5\n\u0001\u0015!\u0003&\u0011\u001dq\u0013A1A\u0005\n\u0011BaaL\u0001!\u0002\u0013)\u0003b\u0002\u0019\u0002\u0005\u0004%I\u0001\n\u0005\u0007c\u0005\u0001\u000b\u0011B\u0013\t\u000bI\nA\u0011A\u001a\t\u000bq\nA\u0011A\u001f\t\u000by\nA\u0011A \t\u000b)\u000bA\u0011B\u001f\u0002\u001bM+7-\u001e:jif,F/\u001b7t\u0015\ty\u0001#\u0001\u0003vi&d'BA\t\u0013\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019B#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002+\u0005\u0019qN]4\u0011\u0005]\tQ\"\u0001\b\u0003\u001bM+7-\u001e:jif,F/\u001b7t'\t\t!\u0004\u0005\u0002\u001c=5\tADC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0013\tyBD\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\ta#A\u0006K\u0003Z\u000buLV#O\t>\u0013V#A\u0013\u0011\u0005\u0019ZS\"A\u0014\u000b\u0005!J\u0013\u0001\u00027b]\u001eT\u0011AK\u0001\u0005U\u00064\u0018-\u0003\u0002-O\t11\u000b\u001e:j]\u001e\fABS!W\u0003~3VI\u0014#P%\u0002\nA#\u0013\"N?.\u0013&i\u0018#F\u0005V;ulQ(O\r&;\u0015!F%C\u001b~[%KQ0E\u000b\n+viX\"P\u001d\u001aKu\tI\u0001\u0015'Vsul\u0013*C?\u0012+%)V$`\u0007>se)S$\u0002+M+fjX&S\u0005~#UIQ+H?\u000e{eJR%HA\u0005\t2/\u001a;HY>\u0014\u0017\r\\&sE\u0012+'-^4\u0015\u0005Q:\u0004CA\u000e6\u0013\t1DD\u0001\u0003V]&$\b\"\u0002\u001d\n\u0001\u0004I\u0014aB3oC\ndW\r\u001a\t\u00037iJ!a\u000f\u000f\u0003\u000f\t{w\u000e\\3b]\u00069\u0012n]$m_\n\fGn\u0013:c\t\u0016\u0014WoZ#oC\ndW\r\u001a\u000b\u0002s\u00051r-\u001a;Le\n,Dj\\4j]6{G-\u001e7f\u001d\u0006lW\rF\u0001A!\t\t\u0005J\u0004\u0002C\rB\u00111\tH\u0007\u0002\t*\u0011Q)I\u0001\u0007yI|w\u000e\u001e \n\u0005\u001dc\u0012A\u0002)sK\u0012,g-\u0003\u0002-\u0013*\u0011q\tH\u0001\fSNL%)\u0014,f]\u0012|'\u000f"
)
public final class SecurityUtils {
   public static String getKrb5LoginModuleName() {
      return SecurityUtils$.MODULE$.getKrb5LoginModuleName();
   }

   public static boolean isGlobalKrbDebugEnabled() {
      return SecurityUtils$.MODULE$.isGlobalKrbDebugEnabled();
   }

   public static void setGlobalKrbDebug(final boolean enabled) {
      SecurityUtils$.MODULE$.setGlobalKrbDebug(enabled);
   }
}
