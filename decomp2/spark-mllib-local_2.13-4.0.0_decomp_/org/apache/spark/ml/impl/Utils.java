package org.apache.spark.ml.impl;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059;a!\u0003\u0006\t\u00029!bA\u0002\f\u000b\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005\u0003\u0005\"\u0003!\u0015\r\u0011\"\u0001#\u0011\u00151\u0013\u0001\"\u0001(\u0011\u0015\u0011\u0014\u0001\"\u00014\u0011\u0015I\u0014\u0001\"\u0001;\u0011\u0015i\u0014\u0001\"\u0001?\u0011\u0015i\u0014\u0001\"\u0001E\u0003\u0015)F/\u001b7t\u0015\tYA\"\u0001\u0003j[Bd'BA\u0007\u000f\u0003\tiGN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h!\t)\u0012!D\u0001\u000b\u0005\u0015)F/\u001b7t'\t\t\u0001\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA#A\u0004F!NKEj\u0014(\u0016\u0003\r\u0002\"!\u0007\u0013\n\u0005\u0015R\"A\u0002#pk\ndW-A\u000bv]B\f7m[+qa\u0016\u0014HK]5b]\u001e,H.\u0019:\u0015\u0007!Z\u0003\u0007E\u0002\u001aS\rJ!A\u000b\u000e\u0003\u000b\u0005\u0013(/Y=\t\u000b1\"\u0001\u0019A\u0017\u0002\u00039\u0004\"!\u0007\u0018\n\u0005=R\"aA%oi\")\u0011\u0007\u0002a\u0001Q\u0005\u0001BO]5b]\u001e,H.\u0019:WC2,Xm]\u0001\u0015S:$W\r_+qa\u0016\u0014HK]5b]\u001e,H.\u0019:\u0015\t5\"Tg\u000e\u0005\u0006Y\u0015\u0001\r!\f\u0005\u0006m\u0015\u0001\r!L\u0001\u0002S\")\u0001(\u0002a\u0001[\u0005\t!.\u0001\u0005m_\u001e\f\u0004/\u0012=q)\t\u00193\bC\u0003=\r\u0001\u00071%A\u0001y\u0003\u001d\u0019xN\u001a;nCb$\"a\u0010\"\u0011\u0005e\u0001\u0015BA!\u001b\u0005\u0011)f.\u001b;\t\u000b\r;\u0001\u0019\u0001\u0015\u0002\u000b\u0005\u0014(/Y=\u0015\r}*u\t\u0013&M\u0011\u00151\u0005\u00021\u0001)\u0003\u0015Ig\u000e];u\u0011\u0015a\u0003\u00021\u0001.\u0011\u0015I\u0005\u00021\u0001.\u0003\u0019ygMZ:fi\")1\n\u0003a\u0001[\u0005!1\u000f^3q\u0011\u0015i\u0005\u00021\u0001)\u0003\u0019yW\u000f\u001e9vi\u0002"
)
public final class Utils {
   public static void softmax(final double[] input, final int n, final int offset, final int step, final double[] output) {
      Utils$.MODULE$.softmax(input, n, offset, step, output);
   }

   public static void softmax(final double[] array) {
      Utils$.MODULE$.softmax(array);
   }

   public static double log1pExp(final double x) {
      return Utils$.MODULE$.log1pExp(x);
   }

   public static int indexUpperTriangular(final int n, final int i, final int j) {
      return Utils$.MODULE$.indexUpperTriangular(n, i, j);
   }

   public static double[] unpackUpperTriangular(final int n, final double[] triangularValues) {
      return Utils$.MODULE$.unpackUpperTriangular(n, triangularValues);
   }

   public static double EPSILON() {
      return Utils$.MODULE$.EPSILON();
   }
}
