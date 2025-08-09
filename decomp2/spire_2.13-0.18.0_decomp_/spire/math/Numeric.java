package spire.math;

import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.Ring;
import scala.reflect.ScalaSignature;
import spire.algebra.IsReal;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%ga\u0002\u000f\u001e!\u0003\r\nAI\u0004\u0006avA\t!\u001d\u0004\u00069uA\tA\u001d\u0005\u0006}\n!\ta \u0005\n\u0003\u0003\u0011!\u0019!C\u0004\u0003\u0007A\u0001\"!\u0004\u0003A\u00035\u0011Q\u0001\u0005\n\u0003\u001f\u0011!\u0019!C\u0004\u0003#A\u0001\"a\u0007\u0003A\u00035\u00111\u0003\u0005\n\u0003;\u0011!\u0019!C\u0004\u0003?A\u0001\"!\u000b\u0003A\u00035\u0011\u0011\u0005\u0005\n\u0003W\u0011!\u0019!C\u0004\u0003[A\u0001\"a\u000e\u0003A\u00035\u0011q\u0006\u0005\n\u0003s\u0011!\u0019!C\u0004\u0003wA\u0001\"!\u0012\u0003A\u00035\u0011Q\b\u0005\n\u0003\u000f\u0012!\u0019!C\u0004\u0003\u0013B\u0001\"a\u0015\u0003A\u00035\u00111\n\u0005\n\u0003+\u0012!\u0019!C\u0004\u0003/B\u0001\"a\u001a\u0003A\u00035\u0011\u0011\f\u0005\n\u0003S\u0012!\u0019!C\u0004\u0003WB\u0001\"!\u001e\u0003A\u00035\u0011Q\u000e\u0005\n\u0003o\u0012!\u0019!C\u0004\u0003sB\u0001\"a!\u0003A\u00035\u00111\u0010\u0005\n\u0003\u000b\u0013!\u0019!C\u0004\u0003\u000fC\u0001\"!%\u0003A\u00035\u0011\u0011\u0012\u0005\n\u0003'\u0013!\u0019!C\u0004\u0003+C\u0001\"a(\u0003A\u00035\u0011q\u0013\u0005\b\u0003C\u0013AQAAR\u0011%\tILAA\u0001\n\u0013\tYLA\u0004Ok6,'/[2\u000b\u0005yy\u0012\u0001B7bi\"T\u0011\u0001I\u0001\u0006gBL'/Z\u0002\u0001+\t\u0019ChE\u0005\u0001I)bvL\u00194k[B\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t\u0019\u0011I\\=\u0011\u0007-:$H\u0004\u0002-i9\u0011QF\r\b\u0003]Ej\u0011a\f\u0006\u0003a\u0005\na\u0001\u0010:p_Rt\u0014\"\u0001\u0011\n\u0005Mz\u0012aB1mO\u0016\u0014'/Y\u0005\u0003kY\nq\u0001]1dW\u0006<WM\u0003\u00024?%\u0011\u0001(\u000f\u0002\u0005%&twM\u0003\u00026mA\u00111\b\u0010\u0007\u0001\t%i\u0004\u0001)A\u0001\u0002\u000b\u0007aHA\u0001B#\tyD\u0005\u0005\u0002&\u0001&\u0011\u0011I\n\u0002\b\u001d>$\b.\u001b8hQ\u0019a4IR'S/B\u0011Q\u0005R\u0005\u0003\u000b\u001a\u00121b\u001d9fG&\fG.\u001b>fIF*1e\u0012%K\u0013:\u0011Q\u0005S\u0005\u0003\u0013\u001a\n1!\u00138uc\u0011!3\nT\u0014\u000f\u00059b\u0015\"A\u00142\u000b\rru*\u0015)\u000f\u0005\u0015z\u0015B\u0001)'\u0003\u0011auN\\42\t\u0011ZEjJ\u0019\u0006GM#f+\u0016\b\u0003KQK!!\u0016\u0014\u0002\u000b\u0019cw.\u0019;2\t\u0011ZEjJ\u0019\u0006GaK6L\u0017\b\u0003KeK!A\u0017\u0014\u0002\r\u0011{WO\u00197fc\u0011!3\nT\u0014\u0011\u0007-j&(\u0003\u0002_s\ty\u0011\t\u001a3ji&4X-\u00112He>,\b\u000fE\u0002,AjJ!!Y\u001d\u0003+5+H\u000e^5qY&\u001c\u0017\r^5wK\u0006\u0013wI]8vaB\u00191\r\u001a\u001e\u000e\u0003YJ!!\u001a\u001c\u0003\u000b9\u0013vn\u001c;\u0011\u0007\u001dD'(D\u0001\u001e\u0013\tIWDA\bD_:4XM\u001d;bE2,gI]8n!\r97NO\u0005\u0003Yv\u0011QbQ8om\u0016\u0014H/\u00192mKR{\u0007cA2ou%\u0011qN\u000e\u0002\u0007\u0013N\u0014V-\u00197\u0002\u000f9+X.\u001a:jGB\u0011qMA\n\u0004\u0005M4\bCA\u0013u\u0013\t)hE\u0001\u0004B]f\u0014VM\u001a\t\u0003orl\u0011\u0001\u001f\u0006\u0003sj\f!![8\u000b\u0003m\fAA[1wC&\u0011Q\u0010\u001f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\fQBQ=uK&\u001bh*^7fe&\u001cWCAA\u0003!\u00119\u0007!a\u0002\u0011\u0007\u0015\nI!C\u0002\u0002\f\u0019\u0012AAQ=uK\u0006q!)\u001f;f\u0013NtU/\\3sS\u000e\u0004\u0013AD*i_J$\u0018j\u001d(v[\u0016\u0014\u0018nY\u000b\u0003\u0003'\u0001Ba\u001a\u0001\u0002\u0016A\u0019Q%a\u0006\n\u0007\u0005eaEA\u0003TQ>\u0014H/A\bTQ>\u0014H/S:Ok6,'/[2!\u00031Ie\u000e^%t\u001dVlWM]5d+\t\t\t\u0003\u0005\u0003h\u0001\u0005\r\u0002cA\u0013\u0002&%\u0019\u0011q\u0005\u0014\u0003\u0007%sG/A\u0007J]RL5OT;nKJL7\rI\u0001\u000e\u0019>tw-S:Ok6,'/[2\u0016\u0005\u0005=\u0002\u0003B4\u0001\u0003c\u00012!JA\u001a\u0013\r\t)D\n\u0002\u0005\u0019>tw-\u0001\bM_:<\u0017j\u001d(v[\u0016\u0014\u0018n\u0019\u0011\u0002\u001d\u0019cw.\u0019;Jg:+X.\u001a:jGV\u0011\u0011Q\b\t\u0005O\u0002\ty\u0004E\u0002&\u0003\u0003J1!a\u0011'\u0005\u00151En\\1u\u0003=1En\\1u\u0013NtU/\\3sS\u000e\u0004\u0013a\u0004#pk\ndW-S:Ok6,'/[2\u0016\u0005\u0005-\u0003\u0003B4\u0001\u0003\u001b\u00022!JA(\u0013\r\t\tF\n\u0002\u0007\t>,(\r\\3\u0002!\u0011{WO\u00197f\u0013NtU/\\3sS\u000e\u0004\u0013a\u0004\"jO&sG/S:Ok6,'/[2\u0016\u0005\u0005e\u0003\u0003B4\u0001\u00037\u0002B!!\u0018\u0002b9\u00191*a\u0018\n\u0005U2\u0013\u0002BA2\u0003K\u0012aAQ5h\u0013:$(BA\u001b'\u0003A\u0011\u0015nZ%oi&\u001bh*^7fe&\u001c\u0007%A\nCS\u001e$UmY5nC2L5OT;nKJL7-\u0006\u0002\u0002nA!q\rAA8!\u0011\ti&!\u001d\n\t\u0005M\u0014Q\r\u0002\u000b\u0005&<G)Z2j[\u0006d\u0017\u0001\u0006\"jO\u0012+7-[7bY&\u001bh*^7fe&\u001c\u0007%\u0001\nBY\u001e,'M]1jG&\u001bh*^7fe&\u001cWCAA>!\u00119\u0007!! \u0011\u0007\u001d\fy(C\u0002\u0002\u0002v\u0011\u0011\"\u00117hK\n\u0014\u0018-[2\u0002'\u0005cw-\u001a2sC&\u001c\u0017j\u001d(v[\u0016\u0014\u0018n\u0019\u0011\u0002\u001bI+\u0017\r\\%t\u001dVlWM]5d+\t\tI\t\u0005\u0003h\u0001\u0005-\u0005cA4\u0002\u000e&\u0019\u0011qR\u000f\u0003\tI+\u0017\r\\\u0001\u000f%\u0016\fG.S:Ok6,'/[2!\u0003E\u0011\u0016\r^5p]\u0006d\u0017j\u001d(v[\u0016\u0014\u0018nY\u000b\u0003\u0003/\u0003Ba\u001a\u0001\u0002\u001aB\u0019q-a'\n\u0007\u0005uUD\u0001\u0005SCRLwN\\1m\u0003I\u0011\u0016\r^5p]\u0006d\u0017j\u001d(v[\u0016\u0014\u0018n\u0019\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005\u0015\u00161\u0016\u000b\u0005\u0003O\u000bi\u000b\u0005\u0003h\u0001\u0005%\u0006cA\u001e\u0002,\u0012)QH\u0007b\u0001}!9\u0011q\u0016\u000eA\u0004\u0005\u001d\u0016AA3wQ\rQ\u00121\u0017\t\u0004K\u0005U\u0016bAA\\M\t1\u0011N\u001c7j]\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!0\u0011\t\u0005}\u0016QY\u0007\u0003\u0003\u0003T1!a1{\u0003\u0011a\u0017M\\4\n\t\u0005\u001d\u0017\u0011\u0019\u0002\u0007\u001f\nTWm\u0019;"
)
public interface Numeric extends Ring, MultiplicativeCommutativeGroup, NRoot, ConvertableFrom, ConvertableTo, IsReal {
   static Numeric apply(final Numeric ev) {
      return Numeric$.MODULE$.apply(ev);
   }

   static Numeric RationalIsNumeric() {
      return Numeric$.MODULE$.RationalIsNumeric();
   }

   static Numeric RealIsNumeric() {
      return Numeric$.MODULE$.RealIsNumeric();
   }

   static Numeric AlgebraicIsNumeric() {
      return Numeric$.MODULE$.AlgebraicIsNumeric();
   }

   static Numeric BigDecimalIsNumeric() {
      return Numeric$.MODULE$.BigDecimalIsNumeric();
   }

   static Numeric BigIntIsNumeric() {
      return Numeric$.MODULE$.BigIntIsNumeric();
   }

   static Numeric DoubleIsNumeric() {
      return Numeric$.MODULE$.DoubleIsNumeric();
   }

   static Numeric FloatIsNumeric() {
      return Numeric$.MODULE$.FloatIsNumeric();
   }

   static Numeric LongIsNumeric() {
      return Numeric$.MODULE$.LongIsNumeric();
   }

   static Numeric IntIsNumeric() {
      return Numeric$.MODULE$.IntIsNumeric();
   }

   static Numeric ShortIsNumeric() {
      return Numeric$.MODULE$.ShortIsNumeric();
   }

   static Numeric ByteIsNumeric() {
      return Numeric$.MODULE$.ByteIsNumeric();
   }
}
