package org.apache.spark.ml.attribute;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m;a!\b\u0010\t\u0002yAcA\u0002\u0016\u001f\u0011\u0003q2\u0006C\u00033\u0003\u0011\u0005A\u0007C\u00046\u0003\t\u0007I\u0011\u0001\u001c\t\r\t\u000b\u0001\u0015!\u00038\u0011\u001d\u0019\u0015A1A\u0005\u0002YBa\u0001R\u0001!\u0002\u00139\u0004bB#\u0002\u0005\u0004%\tA\u000e\u0005\u0007\r\u0006\u0001\u000b\u0011B\u001c\t\u000f\u001d\u000b!\u0019!C\u0001m!1\u0001*\u0001Q\u0001\n]Bq!S\u0001C\u0002\u0013\u0005a\u0007\u0003\u0004K\u0003\u0001\u0006Ia\u000e\u0005\b\u0017\u0006\u0011\r\u0011\"\u00017\u0011\u0019a\u0015\u0001)A\u0005o!9Q*\u0001b\u0001\n\u00031\u0004B\u0002(\u0002A\u0003%q\u0007C\u0004P\u0003\t\u0007I\u0011\u0001\u001c\t\rA\u000b\u0001\u0015!\u00038\u0011\u001d\t\u0016A1A\u0005\u0002YBaAU\u0001!\u0002\u00139\u0004bB*\u0002\u0005\u0004%\tA\u000e\u0005\u0007)\u0006\u0001\u000b\u0011B\u001c\t\u000fU\u000b!\u0019!C\u0001m!1a+\u0001Q\u0001\n]BqaV\u0001C\u0002\u0013\u0005a\u0007\u0003\u0004Y\u0003\u0001\u0006Ia\u000e\u0005\b3\u0006\u0011\r\u0011\"\u00017\u0011\u0019Q\u0016\u0001)A\u0005o\u0005i\u0011\t\u001e;sS\n,H/Z&fsNT!a\b\u0011\u0002\u0013\u0005$HO]5ckR,'BA\u0011#\u0003\tiGN\u0003\u0002$I\u0005)1\u000f]1sW*\u0011QEJ\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\n1a\u001c:h!\tI\u0013!D\u0001\u001f\u00055\tE\u000f\u001e:jEV$XmS3zgN\u0011\u0011\u0001\f\t\u0003[Aj\u0011A\f\u0006\u0002_\u0005)1oY1mC&\u0011\u0011G\f\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001K\u0001\b\u001b2{\u0016\t\u0016+S+\u00059\u0004C\u0001\u001d@\u001d\tIT\b\u0005\u0002;]5\t1H\u0003\u0002=g\u00051AH]8pizJ!A\u0010\u0018\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0015I\u0001\u0004TiJLgn\u001a\u0006\u0003}9\n\u0001\"\u0014'`\u0003R#&\u000bI\u0001\u0005)f\u0003V)A\u0003U3B+\u0005%\u0001\u0003O\u00036+\u0015!\u0002(B\u001b\u0016\u0003\u0013!B%O\t\u0016C\u0016AB%O\t\u0016C\u0006%A\u0002N\u0013:\u000bA!T%OA\u0005\u0019Q*\u0011-\u0002\t5\u000b\u0005\fI\u0001\u0004'R#\u0015\u0001B*U\t\u0002\n\u0001b\u0015)B%NKE+W\u0001\n'B\u000b%kU%U3\u0002\nqa\u0014*E\u0013:\u000bE*\u0001\u0005P%\u0012Ke*\u0011'!\u0003\u00191\u0016\tT+F'\u00069a+\u0011'V\u000bN\u0003\u0013A\u0003(V\u001b~3\u0016\tT+F'\u0006Ya*V'`-\u0006cU+R*!\u0003)\tE\u000b\u0016*J\u0005V#ViU\u0001\f\u0003R#&+\u0013\"V)\u0016\u001b\u0006%\u0001\bO+6{\u0016\t\u0016+S\u0013\n+F+R*\u0002\u001f9+VjX!U)JK%)\u0016+F'\u0002\u0002"
)
public final class AttributeKeys {
   public static String NUM_ATTRIBUTES() {
      return AttributeKeys$.MODULE$.NUM_ATTRIBUTES();
   }

   public static String ATTRIBUTES() {
      return AttributeKeys$.MODULE$.ATTRIBUTES();
   }

   public static String NUM_VALUES() {
      return AttributeKeys$.MODULE$.NUM_VALUES();
   }

   public static String VALUES() {
      return AttributeKeys$.MODULE$.VALUES();
   }

   public static String ORDINAL() {
      return AttributeKeys$.MODULE$.ORDINAL();
   }

   public static String SPARSITY() {
      return AttributeKeys$.MODULE$.SPARSITY();
   }

   public static String STD() {
      return AttributeKeys$.MODULE$.STD();
   }

   public static String MAX() {
      return AttributeKeys$.MODULE$.MAX();
   }

   public static String MIN() {
      return AttributeKeys$.MODULE$.MIN();
   }

   public static String INDEX() {
      return AttributeKeys$.MODULE$.INDEX();
   }

   public static String NAME() {
      return AttributeKeys$.MODULE$.NAME();
   }

   public static String TYPE() {
      return AttributeKeys$.MODULE$.TYPE();
   }

   public static String ML_ATTR() {
      return AttributeKeys$.MODULE$.ML_ATTR();
   }
}
