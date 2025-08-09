package org.json4s;

import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}s!B\u0014)\u0011\u0003ic!B\u0018)\u0011\u0003\u0001\u0004\"B\u001c\u0002\t\u0003A\u0004\"B\u001d\u0002\t\u0003QT\u0001B\u001f\u0002\u0001yBq!R\u0001C\u0002\u0013\u0005a\t\u0003\u0004Q\u0003\u0001\u0006Ia\u0012\u0005\b#\u0006\u0011\r\u0011\"\u0001S\u0011\u0019)\u0016\u0001)A\u0005'\u0016!a+\u0001\u0001X\u0011\u001dI\u0016A1A\u0005\u0002iCa!X\u0001!\u0002\u0013YV\u0001\u00020\u0002\u0001}Cq!Y\u0001C\u0002\u0013\u0005!\r\u0003\u0004f\u0003\u0001\u0006IaY\u0003\u0005M\u0006\u0001q\rC\u0004j\u0003\t\u0007I\u0011\u00016\t\r5\f\u0001\u0015!\u0003l\u000b\u0011q\u0017\u0001A8\t\u000fE\f!\u0019!C\u0001e\"1Q/\u0001Q\u0001\nM,AA^\u0001\u0001o\"9\u00110\u0001b\u0001\n\u0003Q\bBB?\u0002A\u0003%10\u0002\u0003\u007f\u0003\u0001y\b\"CA\u0002\u0003\t\u0007I\u0011AA\u0003\u0011!\tY!\u0001Q\u0001\n\u0005\u001dQABA\u0007\u0003\u0001\ty\u0001C\u0005\u0002&\u0005\u0011\r\u0011\"\u0001\u0002(!A\u0011QF\u0001!\u0002\u0013\tI#\u0002\u0004\u00020\u0005\u0001\u0011\u0011\u0007\u0005\n\u0003k\t!\u0019!C\u0001\u0003oA\u0001\"!\u0010\u0002A\u0003%\u0011\u0011H\u0003\u0007\u0003\u007f\t\u0001!!\u0011\t\u0013\u0005\u0015\u0013A1A\u0005\u0002\u0005\u001d\u0003\u0002CA'\u0003\u0001\u0006I!!\u0013\u0006\r\u0005=\u0013\u0001AA)\u0011%\t)&\u0001b\u0001\n\u0003\t9\u0006\u0003\u0005\u0002^\u0005\u0001\u000b\u0011BA-\u0003\u001dQ5o\u001c8B'RS!!\u000b\u0016\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005Y\u0013aA8sO\u000e\u0001\u0001C\u0001\u0018\u0002\u001b\u0005A#a\u0002&t_:\f5\u000bV\n\u0003\u0003E\u0002\"AM\u001b\u000e\u0003MR\u0011\u0001N\u0001\u0006g\u000e\fG.Y\u0005\u0003mM\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001.\u0003\u0019\u0019wN\\2biR\u00111\b\u0011\t\u0003y\u0011i\u0011!\u0001\u0002\u0007\u0015Z\u000bG.^3\u0011\u00059z\u0014BA\u001f)\u0011\u0015\t5\u00011\u0001C\u0003\tA8\u000fE\u00023\u0007nJ!\u0001R\u001a\u0003\u0015q\u0012X\r]3bi\u0016$g(\u0001\u0005K\u001d>$\b.\u001b8h+\u00059eB\u0001%P\u001d\tIeJ\u0004\u0002K\u001b6\t1J\u0003\u0002MY\u00051AH]8pizJ\u0011aK\u0005\u0003S)J!!\u0012\u0015\u0002\u0013)su\u000e\u001e5j]\u001e\u0004\u0013!\u0002&Ok2dW#A*\u000f\u0005!#\u0016BA))\u0003\u0019Qe*\u001e7mA\t9!j\u0015;sS:<\u0007C\u0001\u0018Y\u0013\t1\u0006&A\u0004K'R\u0014\u0018N\\4\u0016\u0003ms!\u0001\u0013/\n\u0005eC\u0013\u0001\u0003&TiJLgn\u001a\u0011\u0003\u000f)#u.\u001e2mKB\u0011a\u0006Y\u0005\u0003=\"\nqA\u0013#pk\ndW-F\u0001d\u001d\tAE-\u0003\u0002bQ\u0005A!\nR8vE2,\u0007E\u0001\u0005K\t\u0016\u001c\u0017.\\1m!\tq\u0003.\u0003\u0002gQ\u0005A!\nR3dS6\fG.F\u0001l\u001d\tAE.\u0003\u0002jQ\u0005I!\nR3dS6\fG\u000e\t\u0002\u0006\u00152{gn\u001a\t\u0003]AL!A\u001c\u0015\u0002\u000b)cuN\\4\u0016\u0003Mt!\u0001\u0013;\n\u0005ED\u0013A\u0002&M_:<\u0007E\u0001\u0003K\u0013:$\bC\u0001\u0018y\u0013\t1\b&\u0001\u0003K\u0013:$X#A>\u000f\u0005!c\u0018BA=)\u0003\u0015Q\u0015J\u001c;!\u0005\u0015Q%i\\8m!\rq\u0013\u0011A\u0005\u0003}\"\nQA\u0013\"p_2,\"!a\u0002\u000f\u0007!\u000bI!C\u0002\u0002\u0004!\naA\u0013\"p_2\u0004#A\u0002&GS\u0016dG\r\u0005\u00043\u0003#\t)bO\u0005\u0004\u0003'\u0019$A\u0002+va2,'\u0007\u0005\u0003\u0002\u0018\u0005}a\u0002BA\r\u00037\u0001\"AS\u001a\n\u0007\u0005u1'\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003C\t\u0019C\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003;\u0019\u0014A\u0002&GS\u0016dG-\u0006\u0002\u0002*9\u0019\u0001*a\u000b\n\u0007\u0005\u0015\u0002&A\u0004K\r&,G\u000e\u001a\u0011\u0003\u000f){%M[3diB\u0019a&a\r\n\u0007\u0005=\u0002&A\u0004K\u001f\nTWm\u0019;\u0016\u0005\u0005ebb\u0001%\u0002<%\u0019\u0011Q\u0007\u0015\u0002\u0011){%M[3di\u0002\u0012aAS!se\u0006L\bc\u0001\u0018\u0002D%\u0019\u0011q\b\u0015\u0002\r)\u000b%O]1z+\t\tIED\u0002I\u0003\u0017J1!!\u0012)\u0003\u001dQ\u0015I\u001d:bs\u0002\u0012AAS*fiB\u0019a&a\u0015\n\u0007\u0005=\u0003&\u0001\u0003K'\u0016$XCAA-\u001d\rA\u00151L\u0005\u0004\u0003+B\u0013!\u0002&TKR\u0004\u0003"
)
public final class JsonAST {
   public static JSet$ JSet() {
      return JsonAST$.MODULE$.JSet();
   }

   public static JArray$ JArray() {
      return JsonAST$.MODULE$.JArray();
   }

   public static JObject$ JObject() {
      return JsonAST$.MODULE$.JObject();
   }

   public static JField$ JField() {
      return JsonAST$.MODULE$.JField();
   }

   public static JBool$ JBool() {
      return JsonAST$.MODULE$.JBool();
   }

   public static JInt$ JInt() {
      return JsonAST$.MODULE$.JInt();
   }

   public static JLong$ JLong() {
      return JsonAST$.MODULE$.JLong();
   }

   public static JDecimal$ JDecimal() {
      return JsonAST$.MODULE$.JDecimal();
   }

   public static JDouble$ JDouble() {
      return JsonAST$.MODULE$.JDouble();
   }

   public static JString$ JString() {
      return JsonAST$.MODULE$.JString();
   }

   public static JNull$ JNull() {
      return JsonAST$.MODULE$.JNull();
   }

   public static JNothing$ JNothing() {
      return JsonAST$.MODULE$.JNothing();
   }

   public static JValue concat(final Seq xs) {
      return JsonAST$.MODULE$.concat(xs);
   }
}
