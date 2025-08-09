package org.apache.spark.sql.internal;

import java.util.concurrent.atomic.AtomicReference;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019<aa\u0005\u000b\t\u0002YqbA\u0002\u0011\u0015\u0011\u00031\u0012\u0005C\u0003)\u0003\u0011\u0005!\u0006C\u0004,\u0003\t\u0007I\u0011\u0001\u0017\t\ra\n\u0001\u0015!\u0003.\u0011\u001dI\u0014A1A\u0005\u00021BaAO\u0001!\u0002\u0013i\u0003bB\u001e\u0002\u0005\u0004%\t\u0001\f\u0005\u0007y\u0005\u0001\u000b\u0011B\u0017\t\u000fu\n!\u0019!C\u0001Y!1a(\u0001Q\u0001\n5BqaP\u0001C\u0002\u0013\u0005A\u0006\u0003\u0004A\u0003\u0001\u0006I!\f\u0005\b\u0003\u0006\u0011\r\u0011\"\u0001C\u0011\u0019Q\u0015\u0001)A\u0005\u0007\"91*\u0001b\u0001\n\u0003a\u0005BB/\u0002A\u0003%Q\nC\u0003_\u0003\u0011\u0005A\nC\u0003`\u0003\u0011\u0005\u0001-\u0001\tTc2\f\u0005/[\"p]\u001aDU\r\u001c9fe*\u0011QCF\u0001\tS:$XM\u001d8bY*\u0011q\u0003G\u0001\u0004gFd'BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0011\u0005}\tQ\"\u0001\u000b\u0003!M\u000bH.\u00119j\u0007>tg\rS3ma\u0016\u00148CA\u0001#!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u001f\u0003A\tejU%`\u000b:\u000b%\tT#E?.+\u0015,F\u0001.!\tqSG\u0004\u00020gA\u0011\u0001\u0007J\u0007\u0002c)\u0011!'K\u0001\u0007yI|w\u000e\u001e \n\u0005Q\"\u0013A\u0002)sK\u0012,g-\u0003\u00027o\t11\u000b\u001e:j]\u001eT!\u0001\u000e\u0013\u0002#\u0005s5+S0F\u001d\u0006\u0013E*\u0012#`\u0017\u0016K\u0006%A\u000fM\u000b\u001e\u000b5)W0U\u00136+u\fU!S'\u0016\u0013v\fU(M\u0013\u000eKvlS#Z\u0003yaUiR!D3~#\u0016*T#`!\u0006\u00136+\u0012*`!>c\u0015jQ-`\u0017\u0016K\u0006%\u0001\nD\u0003N+ulU#O'&#\u0016JV#`\u0017\u0016K\u0016aE\"B'\u0016{6+\u0012(T\u0013RKe+R0L\u000bf\u0003\u0013AG*F'NKuJT0M\u001f\u000e\u000bEj\u0018+J\u001b\u0016SvJT#`\u0017\u0016K\u0016aG*F'NKuJT0M\u001f\u000e\u000bEj\u0018+J\u001b\u0016SvJT#`\u0017\u0016K\u0006%\u0001\u0012M\u001f\u000e\u000bEj\u0018*F\u0019\u0006#\u0016j\u0014(`\u0007\u0006\u001b\u0005*R0U\u0011J+5\u000bS(M\t~[U)W\u0001$\u0019>\u001b\u0015\tT0S\u000b2\u000bE+S(O?\u000e\u000b5\tS#`)\"\u0013Vi\u0015%P\u0019\u0012{6*R-!\u0003\r\n%KU(X?\u0016CViQ+U\u0013>su,V*F?2\u000b%kR#`-\u0006\u0013v\fV-Q\u000bN+\u0012a\u0011\t\u0003\t&k\u0011!\u0012\u0006\u0003\r\u001e\u000bA\u0001\\1oO*\t\u0001*\u0001\u0003kCZ\f\u0017B\u0001\u001cF\u0003\u0011\n%KU(X?\u0016CViQ+U\u0013>su,V*F?2\u000b%kR#`-\u0006\u0013v\fV-Q\u000bN\u0003\u0013AC2p]\u001a<U\r\u001e;feV\tQ\nE\u0002O+^k\u0011a\u0014\u0006\u0003!F\u000ba!\u0019;p[&\u001c'B\u0001*T\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003)\u001e\u000bA!\u001e;jY&\u0011ak\u0014\u0002\u0010\u0003R|W.[2SK\u001a,'/\u001a8dKB\u00191\u0005\u0017.\n\u0005e##!\u0003$v]\u000e$\u0018n\u001c81!\ty2,\u0003\u0002])\tQ1+\u001d7Ba&\u001cuN\u001c4\u0002\u0017\r|gNZ$fiR,'\u000fI\u0001\u000eO\u0016$8i\u001c8g\u000f\u0016$H/\u001a:\u0002\u001bM,GoQ8oM\u001e+G\u000f^3s)\t\tG\r\u0005\u0002$E&\u00111\r\n\u0002\u0005+:LG\u000fC\u0003f%\u0001\u0007q+\u0001\u0004hKR$XM\u001d"
)
public final class SqlApiConfHelper {
   public static void setConfGetter(final Function0 getter) {
      SqlApiConfHelper$.MODULE$.setConfGetter(getter);
   }

   public static AtomicReference getConfGetter() {
      return SqlApiConfHelper$.MODULE$.getConfGetter();
   }

   public static AtomicReference confGetter() {
      return SqlApiConfHelper$.MODULE$.confGetter();
   }

   public static String ARROW_EXECUTION_USE_LARGE_VAR_TYPES() {
      return SqlApiConfHelper$.MODULE$.ARROW_EXECUTION_USE_LARGE_VAR_TYPES();
   }

   public static String LOCAL_RELATION_CACHE_THRESHOLD_KEY() {
      return SqlApiConfHelper$.MODULE$.LOCAL_RELATION_CACHE_THRESHOLD_KEY();
   }

   public static String SESSION_LOCAL_TIMEZONE_KEY() {
      return SqlApiConfHelper$.MODULE$.SESSION_LOCAL_TIMEZONE_KEY();
   }

   public static String CASE_SENSITIVE_KEY() {
      return SqlApiConfHelper$.MODULE$.CASE_SENSITIVE_KEY();
   }

   public static String LEGACY_TIME_PARSER_POLICY_KEY() {
      return SqlApiConfHelper$.MODULE$.LEGACY_TIME_PARSER_POLICY_KEY();
   }

   public static String ANSI_ENABLED_KEY() {
      return SqlApiConfHelper$.MODULE$.ANSI_ENABLED_KEY();
   }
}
