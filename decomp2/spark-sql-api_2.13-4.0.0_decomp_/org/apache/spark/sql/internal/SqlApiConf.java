package org.apache.spark.sql.internal;

import org.apache.spark.sql.types.AtomicType;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4\u0001BI\u0012\u0011\u0002G\u0005Q%\f\u0005\u0006i\u00011\tA\u000e\u0005\u0006u\u00011\tA\u000e\u0005\u0006w\u00011\t\u0001\u0010\u0005\u0006\u0001\u00021\tA\u000e\u0005\u0006\u0003\u00021\tA\u000e\u0005\u0006\u0005\u00021\tA\u000e\u0005\u0006\u0007\u00021\tA\u000e\u0005\u0006\t\u00021\t!\u0012\u0005\u0006\u0019\u00021\tA\u000e\u0005\u0006\u001b\u00021\tA\u000e\u0005\u0006\u001d\u00021\tA\u000e\u0005\u0006\u001f\u00021\tA\u000e\u0005\u0006!\u00021\t!\u0015\u0005\u0006;\u00021\tA\u0018\u0005\u0006Q\u00021\t\u0001\u0010\u0005\u0006S\u00021\tA\u000e\u0005\u0006U\u00021\tAN\u0004\u0007W\u000eB\t!\n7\u0007\r\t\u001a\u0003\u0012A\u0013n\u0011\u0015q7\u0003\"\u0001p\u0011\u001d\u00018C1A\u0005\u0002ECa!]\n!\u0002\u0013\u0011\u0006b\u0002:\u0014\u0005\u0004%\t!\u0015\u0005\u0007gN\u0001\u000b\u0011\u0002*\t\u000fQ\u001c\"\u0019!C\u0001#\"1Qo\u0005Q\u0001\nICqA^\nC\u0002\u0013\u0005\u0011\u000b\u0003\u0004x'\u0001\u0006IA\u0015\u0005\bqN\u0011\r\u0011\"\u0001R\u0011\u0019I8\u0003)A\u0005%\"9!p\u0005b\u0001\n\u0003\t\u0006BB>\u0014A\u0003%!\u000bC\u0003}'\u0011\u0005QP\u0001\u0006Tc2\f\u0005/[\"p]\u001aT!\u0001J\u0013\u0002\u0011%tG/\u001a:oC2T!AJ\u0014\u0002\u0007M\fHN\u0003\u0002)S\u0005)1\u000f]1sW*\u0011!fK\u0001\u0007CB\f7\r[3\u000b\u00031\n1a\u001c:h'\t\u0001a\u0006\u0005\u00020e5\t\u0001GC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004G\u0001\u0004B]f\u0014VMZ\u0001\fC:\u001c\u0018.\u00128bE2,Gm\u0001\u0001\u0016\u0003]\u0002\"a\f\u001d\n\u0005e\u0002$a\u0002\"p_2,\u0017M\\\u0001\u0016G\u0006\u001cXmU3og&$\u0018N^3B]\u0006d\u0017p]5t\u0003Ei\u0017\r\u001f+p'R\u0014\u0018N\\4GS\u0016dGm]\u000b\u0002{A\u0011qFP\u0005\u0003\u007fA\u00121!\u00138u\u0003a\u0019X\r^(qgB\u0013XmY3eK:\u001cW-\u00128g_J\u001cW\rZ\u0001 Kb\u0004xN\\3oi2KG/\u001a:bY\u0006\u001bH)Z2j[\u0006dWI\\1cY\u0016$\u0017aF3oM>\u00148-\u001a*fg\u0016\u0014h/\u001a3LKf<xN\u001d3t\u0003]!w.\u001e2mKF+x\u000e^3e\u0013\u0012,g\u000e^5gS\u0016\u00148/A\u0007uS6,7\u000f^1naRK\b/Z\u000b\u0002\rB\u0011qIS\u0007\u0002\u0011*\u0011\u0011*J\u0001\u0006if\u0004Xm]\u0005\u0003\u0017\"\u0013!\"\u0011;p[&\u001cG+\u001f9f\u0003\t\nG\u000e\\8x\u001d\u0016<\u0017\r^5wKN\u001b\u0017\r\\3PM\u0012+7-[7bY\u0016s\u0017M\u00197fI\u0006\u00192\r[1s-\u0006\u00148\r[1s\u0003N\u001cFO]5oO\u0006Y\u0002O]3tKJ4Xm\u00115beZ\u000b'o\u00195beRK\b/Z%oM>\fq\u0003Z1uKRLW.\u001a&bm\u0006D\u0014\t]5F]\u0006\u0014G.\u001a3\u0002)M,7o]5p]2{7-\u00197US6,'l\u001c8f+\u0005\u0011\u0006CA*[\u001d\t!\u0006\f\u0005\u0002Va5\taK\u0003\u0002Xk\u00051AH]8pizJ!!\u0017\u0019\u0002\rA\u0013X\rZ3g\u0013\tYFL\u0001\u0004TiJLgn\u001a\u0006\u00033B\na\u0003\\3hC\u000eLH+[7f!\u0006\u00148/\u001a:Q_2L7-_\u000b\u0002?B\u0011\u0001\r\u001a\b\u0003C\nl\u0011aI\u0005\u0003G\u000e\nA\u0003T3hC\u000eL()\u001a5bm&|'\u000fU8mS\u000eL\u0018BA3g\u0005\u00151\u0016\r\\;f\u0013\t9\u0007GA\u0006F]VlWM]1uS>t\u0017!H:uC\u000e\\GK]1dKNLe\u000eR1uC\u001a\u0013\u0018-\\3D_:$X\r\u001f;\u00029\u0011\fG/\u0019$sC6,\u0017+^3ss\u000e{g\u000e^3yi\u0016s\u0017M\u00197fI\u0006YB.Z4bGf\fE\u000e\\8x+:$\u0018\u0010]3e'\u000e\fG.Y+E\rN\f!bU9m\u0003BL7i\u001c8g!\t\t7c\u0005\u0002\u0014]\u00051A(\u001b8jiz\"\u0012\u0001\\\u0001\u0011\u0003:\u001b\u0016jX#O\u0003\ncU\tR0L\u000bf\u000b\u0011#\u0011(T\u0013~+e*\u0011\"M\u000b\u0012{6*R-!\u0003uaUiR!D3~#\u0016*T#`!\u0006\u00136+\u0012*`!>c\u0015jQ-`\u0017\u0016K\u0016A\b'F\u000f\u0006\u001b\u0015l\u0018+J\u001b\u0016{\u0006+\u0011*T\u000bJ{\u0006k\u0014'J\u0007f{6*R-!\u0003I\u0019\u0015iU#`'\u0016s5+\u0013+J-\u0016{6*R-\u0002'\r\u000b5+R0T\u000b:\u001b\u0016\nV%W\u000b~[U)\u0017\u0011\u00025M+5kU%P\u001d~cujQ!M?RKU*\u0012.P\u001d\u0016{6*R-\u00027M+5kU%P\u001d~cujQ!M?RKU*\u0012.P\u001d\u0016{6*R-!\u0003\r\n%KU(X?\u0016CViQ+U\u0013>su,V*F?2\u000b%kR#`-\u0006\u0013v\fV-Q\u000bN\u000bA%\u0011*S\u001f^{V\tW#D+RKuJT0V'\u0016{F*\u0011*H\u000b~3\u0016IU0U3B+5\u000bI\u0001#\u0019>\u001b\u0015\tT0S\u000b2\u000bE+S(O?\u000e\u000b5\tS#`)\"\u0013Vi\u0015%P\u0019\u0012{6*R-\u0002G1{5)\u0011'`%\u0016c\u0015\tV%P\u001d~\u001b\u0015i\u0011%F?RC%+R*I\u001f2#ulS#ZA\u0005\u0019q-\u001a;\u0016\u0003y\u0004\"!\u0019\u0001"
)
public interface SqlApiConf {
   static SqlApiConf get() {
      return SqlApiConf$.MODULE$.get();
   }

   static String LOCAL_RELATION_CACHE_THRESHOLD_KEY() {
      return SqlApiConf$.MODULE$.LOCAL_RELATION_CACHE_THRESHOLD_KEY();
   }

   static String ARROW_EXECUTION_USE_LARGE_VAR_TYPES() {
      return SqlApiConf$.MODULE$.ARROW_EXECUTION_USE_LARGE_VAR_TYPES();
   }

   static String SESSION_LOCAL_TIMEZONE_KEY() {
      return SqlApiConf$.MODULE$.SESSION_LOCAL_TIMEZONE_KEY();
   }

   static String CASE_SENSITIVE_KEY() {
      return SqlApiConf$.MODULE$.CASE_SENSITIVE_KEY();
   }

   static String LEGACY_TIME_PARSER_POLICY_KEY() {
      return SqlApiConf$.MODULE$.LEGACY_TIME_PARSER_POLICY_KEY();
   }

   static String ANSI_ENABLED_KEY() {
      return SqlApiConf$.MODULE$.ANSI_ENABLED_KEY();
   }

   boolean ansiEnabled();

   boolean caseSensitiveAnalysis();

   int maxToStringFields();

   boolean setOpsPrecedenceEnforced();

   boolean exponentLiteralAsDecimalEnabled();

   boolean enforceReservedKeywords();

   boolean doubleQuotedIdentifiers();

   AtomicType timestampType();

   boolean allowNegativeScaleOfDecimalEnabled();

   boolean charVarcharAsString();

   boolean preserveCharVarcharTypeInfo();

   boolean datetimeJava8ApiEnabled();

   String sessionLocalTimeZone();

   Enumeration.Value legacyTimeParserPolicy();

   int stackTracesInDataFrameContext();

   boolean dataFrameQueryContextEnabled();

   boolean legacyAllowUntypedScalaUDFs();
}
