package breeze.util;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.math.Complex;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tmq!B\u000e\u001d\u0011\u0003\tc!B\u0012\u001d\u0011\u0003!\u0003\"B\u0016\u0002\t\u0003a\u0003\"B\u0017\u0002\t\u0003q\u0003\"\u0002!\u0002\t\u0003\t\u0005\"\u0002%\u0002\t\u0003I\u0005\"\u0002)\u0002\t\u0003\t\u0006\"\u0002-\u0002\t\u0003I\u0006\"\u00021\u0002\t\u0003\t\u0007\"B4\u0002\t\u0003A\u0007\"\u00027\u0002\t\u0003i\u0007\"B9\u0002\t\u0003\u0011\b\"\u0002<\u0002\t\u00039\b\"B>\u0002\t\u0003a\bBB@\u0002\t\u0003\t\t\u0001C\u0004\u0002\u0006\u0005!\t!a\u0002\t\u000f\u0005-\u0011\u0001\"\u0001\u0002\u000e!9\u0011\u0011C\u0001\u0005\u0002\u0005M\u0001bBA\f\u0003\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003;\tA\u0011AA\u0010\u0011\u001d\t\u0019#\u0001C\u0001\u0003KAq!!\u000b\u0002\t\u0003\tY\u0003C\u0004\u00020\u0005!\t!!\r\t\u000f\u0005U\u0012\u0001\"\u0001\u00028!9\u00111S\u0001\u0005\u0002\u0005U\u0005bBA]\u0003\u0011\u0005\u00111\u0018\u0005\b\u0003[\fA\u0011AAx\u00031Q\u0015M^1BeJ\f\u0017p\u00149t\u0015\tib$\u0001\u0003vi&d'\"A\u0010\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AI\u0001\u000e\u0003q\u0011ABS1wC\u0006\u0013(/Y=PaN\u001c\"!A\u0013\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t\u0011%\u0001\u0006em\u000e#v.\u0011:sCf$\"a\f\u001d\u0011\u0007\u0019\u0002$'\u0003\u00022O\t)\u0011I\u001d:bsB\u00111GN\u0007\u0002i)\u0011QGH\u0001\u0005[\u0006$\b.\u0003\u00028i\t91i\\7qY\u0016D\b\"B\u001d\u0004\u0001\u0004Q\u0014\u0001\u00023bi\u0006\u00042a\u000f 3\u001b\u0005a$BA\u001f\u001f\u0003\u0019a\u0017N\\1mO&\u0011q\b\u0010\u0002\f\t\u0016t7/\u001a,fGR|'/\u0001\u0006em\u0012#v.\u0011:sCf$\"A\u0011$\u0011\u0007\u0019\u00024\t\u0005\u0002'\t&\u0011Qi\n\u0002\u0007\t>,(\r\\3\t\u000be\"\u0001\u0019A$\u0011\u0007mr4)\u0001\u0006em\u001a#v.\u0011:sCf$\"A\u0013(\u0011\u0007\u0019\u00024\n\u0005\u0002'\u0019&\u0011Qj\n\u0002\u0006\r2|\u0017\r\u001e\u0005\u0006s\u0015\u0001\ra\u0014\t\u0004wyZ\u0015A\u00033w\u0013R{\u0017I\u001d:bsR\u0011!K\u0016\t\u0004MA\u001a\u0006C\u0001\u0014U\u0013\t)vEA\u0002J]RDQ!\u000f\u0004A\u0002]\u00032a\u000f T\u0003)!g\u000f\u0014+p\u0003J\u0014\u0018-\u001f\u000b\u00035z\u00032A\n\u0019\\!\t1C,\u0003\u0002^O\t!Aj\u001c8h\u0011\u0015It\u00011\u0001`!\rYdhW\u0001\fI6\u001cEk\\!se\u0006L(\u0007\u0006\u0002cGB\u0019a\u0005M\u0018\t\u000beB\u0001\u0019\u00013\u0011\u0007m*''\u0003\u0002gy\tYA)\u001a8tK6\u000bGO]5y\u0003-!W\u000e\u0012+p\u0003J\u0014\u0018-\u001f\u001a\u0015\u0005%T\u0007c\u0001\u00141\u0005\")\u0011(\u0003a\u0001WB\u00191(Z\"\u0002\u0017\u0011lg\tV8BeJ\f\u0017P\r\u000b\u0003]>\u00042A\n\u0019K\u0011\u0015I$\u00021\u0001q!\rYTmS\u0001\fI6LEk\\!se\u0006L(\u0007\u0006\u0002tiB\u0019a\u0005\r*\t\u000beZ\u0001\u0019A;\u0011\u0007m*7+A\u0006e[2#v.\u0011:sCf\u0014DC\u0001=z!\r1\u0003G\u0017\u0005\u0006s1\u0001\rA\u001f\t\u0004w\u0015\\\u0016AC1se\u0006L8\tV8EmR\u0011!( \u0005\u0006}6\u0001\raL\u0001\u0006CJ\u0014\u0018-_\u0001\u000bCJ\u0014\u0018-\u001f#U_\u00123HcA$\u0002\u0004!)aP\u0004a\u0001\u0005\u0006Q\u0011M\u001d:bs\u001a#v\u000e\u0012<\u0015\u0007=\u000bI\u0001C\u0003\u007f\u001f\u0001\u0007!*\u0001\u0006beJ\f\u00170\u0013+p\tZ$2aVA\b\u0011\u0015q\b\u00031\u0001S\u0003)\t'O]1z\u0019R{GI\u001e\u000b\u0004?\u0006U\u0001\"\u0002@\u0012\u0001\u0004Q\u0016aC1se\u0006L(g\u0011+p\t6$2\u0001ZA\u000e\u0011\u0015q(\u00031\u0001c\u0003-\t'O]1ze\u0011#v\u000eR7\u0015\u0007-\f\t\u0003C\u0003\u007f'\u0001\u0007\u0011.A\u0006beJ\f\u0017P\r$U_\u0012kGc\u00019\u0002(!)a\u0010\u0006a\u0001]\u0006Y\u0011M\u001d:bsJJEk\u001c#n)\r)\u0018Q\u0006\u0005\u0006}V\u0001\ra]\u0001\fCJ\u0014\u0018-\u001f\u001aM)>$U\u000eF\u0002{\u0003gAQA \fA\u0002a\f\u0011\u0002\u001a<U_\u0006\u0013(/Y=\u0016\t\u0005e\u0012\u0011\t\u000b\u0005\u0003w\ti\t\u0005\u0003'a\u0005u\u0002\u0003BA \u0003\u0003b\u0001\u0001B\u0006\u0002D]\u0001\u000b\u0011!AC\u0002\u0005\u0015#!\u0001,\u0012\t\u0005\u001d\u0013Q\n\t\u0004M\u0005%\u0013bAA&O\t9aj\u001c;iS:<\u0007c\u0001\u0014\u0002P%\u0019\u0011\u0011K\u0014\u0003\u0007\u0005s\u0017\u0010\u000b\u0007\u0002B\u0005U\u00131LA8\u0003s\n\u0019\tE\u0002'\u0003/J1!!\u0017(\u0005-\u0019\b/Z2jC2L'0\u001a32\u0013\r\ni&a\u0018\u0002d\u0005\u0005db\u0001\u0014\u0002`%\u0019\u0011\u0011M\u0014\u0002\u0007%sG/\r\u0004%\u0003K\ni\u0007\u000b\b\u0005\u0003O\ni'\u0004\u0002\u0002j)\u0019\u00111\u000e\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013'C\u0012\u0002r\u0005M\u0014qOA;\u001d\r1\u00131O\u0005\u0004\u0003k:\u0013A\u0002#pk\ndW-\r\u0004%\u0003K\ni\u0007K\u0019\nG\u0005m\u0014QPAA\u0003\u007fr1AJA?\u0013\r\tyhJ\u0001\u0005\u0019>tw-\r\u0004%\u0003K\ni\u0007K\u0019\nG\u0005\u0015\u0015qQAF\u0003\u0013s1AJAD\u0013\r\tIiJ\u0001\u0006\r2|\u0017\r^\u0019\u0007I\u0005\u0015\u0014Q\u000e\u0015\t\u000f\u0005=u\u00031\u0001\u0002\u0012\u0006\u0011AM\u001e\t\u0005wy\ni$\u0001\u0006e[R{\u0017I\u001d:bsJ*B!a&\u0002 R!\u0011\u0011TAZ!\u00111\u0003'a'\u0011\t\u0019\u0002\u0014Q\u0014\t\u0005\u0003\u007f\ty\nB\u0006\u0002Da\u0001\u000b\u0011!AC\u0002\u0005\u0015\u0003\u0006DAP\u0003+\n\u0019+a*\u0002,\u0006=\u0016'C\u0012\u0002^\u0005}\u0013QUA1c\u0019!\u0013QMA7QEJ1%!\u001d\u0002t\u0005%\u0016QO\u0019\u0007I\u0005\u0015\u0014Q\u000e\u00152\u0013\r\nY(! \u0002.\u0006}\u0014G\u0002\u0013\u0002f\u00055\u0004&M\u0005$\u0003\u000b\u000b9)!-\u0002\nF2A%!\u001a\u0002n!Bq!!.\u0019\u0001\u0004\t9,\u0001\u0002e[B!1(ZAO\u0003%\t'O]1z)>$e/\u0006\u0003\u0002>\u0006\u0015G\u0003BA`\u0003S$B!!1\u0002ZB!1HPAb!\u0011\ty$!2\u0005\u0017\u0005\r\u0013\u0004)A\u0001\u0002\u000b\u0007\u0011Q\t\u0015\r\u0003\u000b\f)&!3\u0002N\u0006E\u0017Q[\u0019\nG\u0005u\u0013qLAf\u0003C\nd\u0001JA3\u0003[B\u0013'C\u0012\u0002r\u0005M\u0014qZA;c\u0019!\u0013QMA7QEJ1%a\u001f\u0002~\u0005M\u0017qP\u0019\u0007I\u0005\u0015\u0014Q\u000e\u00152\u0013\r\n))a\"\u0002X\u0006%\u0015G\u0002\u0013\u0002f\u00055\u0004\u0006C\u0005\u0002\\f\t\t\u0011q\u0001\u0002^\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005}\u0017Q]Ab\u001b\t\t\tOC\u0002\u0002d\u001e\nqA]3gY\u0016\u001cG/\u0003\u0003\u0002h\u0006\u0005(\u0001C\"mCN\u001cH+Y4\t\ryL\u0002\u0019AAv!\u00111\u0003'a1\u0002\u0015\u0005\u0014(/Y=3)>$U.\u0006\u0003\u0002r\u0006eH\u0003BAz\u0005'!B!!>\u0003\u000eA!1(ZA|!\u0011\ty$!?\u0005\u0017\u0005\r#\u0004)A\u0001\u0002\u000b\u0007\u0011Q\t\u0015\r\u0003s\f)&!@\u0003\u0002\t\u0015!\u0011B\u0019\nG\u0005u\u0013qLA\u0000\u0003C\nd\u0001JA3\u0003[B\u0013'C\u0012\u0002r\u0005M$1AA;c\u0019!\u0013QMA7QEJ1%a\u001f\u0002~\t\u001d\u0011qP\u0019\u0007I\u0005\u0015\u0014Q\u000e\u00152\u0013\r\n))a\"\u0003\f\u0005%\u0015G\u0002\u0013\u0002f\u00055\u0004\u0006C\u0005\u0003\u0010i\t\t\u0011q\u0001\u0003\u0012\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\r\u0005}\u0017Q]A|\u0011\u001d\u0011)B\u0007a\u0001\u0005/\taA^1mk\u0016\u001c\b\u0003\u0002\u00141\u00053\u0001BA\n\u0019\u0002x\u0002"
)
public final class JavaArrayOps {
   public static DenseMatrix array2ToDm(final Object[] values, final ClassTag evidence$2) {
      return JavaArrayOps$.MODULE$.array2ToDm(values, evidence$2);
   }

   public static DenseVector arrayToDv(final Object array, final ClassTag evidence$1) {
      return JavaArrayOps$.MODULE$.arrayToDv(array, evidence$1);
   }

   public static Object[] dmToArray2(final DenseMatrix dm) {
      return JavaArrayOps$.MODULE$.dmToArray2(dm);
   }

   public static Object dvToArray(final DenseVector dv) {
      return JavaArrayOps$.MODULE$.dvToArray(dv);
   }

   public static DenseMatrix array2LToDm(final long[][] array) {
      return JavaArrayOps$.MODULE$.array2LToDm(array);
   }

   public static DenseMatrix array2IToDm(final int[][] array) {
      return JavaArrayOps$.MODULE$.array2IToDm(array);
   }

   public static DenseMatrix array2FToDm(final float[][] array) {
      return JavaArrayOps$.MODULE$.array2FToDm(array);
   }

   public static DenseMatrix array2DToDm(final double[][] array) {
      return JavaArrayOps$.MODULE$.array2DToDm(array);
   }

   public static DenseMatrix array2CToDm(final Complex[][] array) {
      return JavaArrayOps$.MODULE$.array2CToDm(array);
   }

   public static DenseVector arrayLToDv(final long[] array) {
      return JavaArrayOps$.MODULE$.arrayLToDv(array);
   }

   public static DenseVector arrayIToDv(final int[] array) {
      return JavaArrayOps$.MODULE$.arrayIToDv(array);
   }

   public static DenseVector arrayFToDv(final float[] array) {
      return JavaArrayOps$.MODULE$.arrayFToDv(array);
   }

   public static DenseVector arrayDToDv(final double[] array) {
      return JavaArrayOps$.MODULE$.arrayDToDv(array);
   }

   public static DenseVector arrayCToDv(final Complex[] array) {
      return JavaArrayOps$.MODULE$.arrayCToDv(array);
   }

   public static long[][] dmLToArray2(final DenseMatrix data) {
      return JavaArrayOps$.MODULE$.dmLToArray2(data);
   }

   public static int[][] dmIToArray2(final DenseMatrix data) {
      return JavaArrayOps$.MODULE$.dmIToArray2(data);
   }

   public static float[][] dmFToArray2(final DenseMatrix data) {
      return JavaArrayOps$.MODULE$.dmFToArray2(data);
   }

   public static double[][] dmDToArray2(final DenseMatrix data) {
      return JavaArrayOps$.MODULE$.dmDToArray2(data);
   }

   public static Complex[][] dmCToArray2(final DenseMatrix data) {
      return JavaArrayOps$.MODULE$.dmCToArray2(data);
   }

   public static long[] dvLToArray(final DenseVector data) {
      return JavaArrayOps$.MODULE$.dvLToArray(data);
   }

   public static int[] dvIToArray(final DenseVector data) {
      return JavaArrayOps$.MODULE$.dvIToArray(data);
   }

   public static float[] dvFToArray(final DenseVector data) {
      return JavaArrayOps$.MODULE$.dvFToArray(data);
   }

   public static double[] dvDToArray(final DenseVector data) {
      return JavaArrayOps$.MODULE$.dvDToArray(data);
   }

   public static Complex[] dvCToArray(final DenseVector data) {
      return JavaArrayOps$.MODULE$.dvCToArray(data);
   }
}
