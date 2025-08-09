package scala.xml;

import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;Q!\u0003\u0006\t\u0002=1Q!\u0005\u0006\t\u0002IAQAF\u0001\u0005\u0002]AQ\u0001G\u0001\u0005BeAQaJ\u0001\u0005B!BQaK\u0001\u0005B1BQ!L\u0001\u0005B9BQ!L\u0001\u0005BEBq!Q\u0001\u0002\u0002\u0013%!)\u0001\u0005U_B\u001c6m\u001c9f\u0015\tYA\"A\u0002y[2T\u0011!D\u0001\u0006g\u000e\fG.Y\u0002\u0001!\t\u0001\u0012!D\u0001\u000b\u0005!!v\u000e]*d_B,7CA\u0001\u0014!\t\u0001B#\u0003\u0002\u0016\u0015\t\u0001b*Y7fgB\f7-\u001a\"j]\u0012LgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003=\taaZ3u+JKEC\u0001\u000e&!\tY\"E\u0004\u0002\u001dAA\u0011Q\u0004D\u0007\u0002=)\u0011qDD\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005b\u0011A\u0002)sK\u0012,g-\u0003\u0002$I\t11\u000b\u001e:j]\u001eT!!\t\u0007\t\u000b\u0019\u001a\u0001\u0019\u0001\u000e\u0002\u000fA\u0014XMZ5yc\u0005Iq-\u001a;Qe\u00164\u0017\u000e\u001f\u000b\u00035%BQA\u000b\u0003A\u0002i\tA!\u001e:jc\u0005AAo\\*ue&tw\rF\u0001\u001b\u0003-\u0011W/\u001b7e'R\u0014\u0018N\\4\u0015\u0005iy\u0003\"\u0002\u0019\u0007\u0001\u0004\u0019\u0012\u0001B:u_B$2A\r\u001c@!\t\u0019D'D\u0001\r\u0013\t)DB\u0001\u0003V]&$\b\"B\u001c\b\u0001\u0004A\u0014AA:c!\tIDH\u0004\u00024u%\u00111\bD\u0001\ba\u0006\u001c7.Y4f\u0013\tidHA\u0007TiJLgn\u001a\"vS2$WM\u001d\u0006\u0003w1AQ\u0001Q\u0004A\u0002M\ta![4o_J,\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#A\"\u0011\u0005\u0011KU\"A#\u000b\u0005\u0019;\u0015\u0001\u00027b]\u001eT\u0011\u0001S\u0001\u0005U\u00064\u0018-\u0003\u0002K\u000b\n1qJ\u00196fGR\u0004"
)
public final class TopScope {
   public static void buildString(final StringBuilder sb, final NamespaceBinding ignore) {
      TopScope$.MODULE$.buildString(sb, ignore);
   }

   public static String buildString(final NamespaceBinding stop) {
      return TopScope$.MODULE$.buildString(stop);
   }

   public static String toString() {
      return TopScope$.MODULE$.toString();
   }

   public static String getPrefix(final String uri1) {
      return TopScope$.MODULE$.getPrefix(uri1);
   }

   public static String getURI(final String prefix1) {
      return TopScope$.MODULE$.getURI(prefix1);
   }

   public static String productElementName(final int x$1) {
      return TopScope$.MODULE$.productElementName(x$1);
   }

   public static Iterator productIterator() {
      return TopScope$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return TopScope$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return TopScope$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return TopScope$.MODULE$.productPrefix();
   }

   public static NamespaceBinding copy$default$3() {
      return TopScope$.MODULE$.copy$default$3();
   }

   public static String copy$default$2() {
      return TopScope$.MODULE$.copy$default$2();
   }

   public static String copy$default$1() {
      return TopScope$.MODULE$.copy$default$1();
   }

   public static NamespaceBinding copy(final String prefix, final String uri, final NamespaceBinding parent) {
      return TopScope$.MODULE$.copy(prefix, uri, parent);
   }

   public static Seq basisForHashCode() {
      return TopScope$.MODULE$.basisForHashCode();
   }

   public static boolean strict_$eq$eq(final Equality other) {
      return TopScope$.MODULE$.strict_$eq$eq(other);
   }

   public static boolean canEqual(final Object other) {
      return TopScope$.MODULE$.canEqual(other);
   }

   public static NamespaceBinding parent() {
      return TopScope$.MODULE$.parent();
   }

   public static String uri() {
      return TopScope$.MODULE$.uri();
   }

   public static String prefix() {
      return TopScope$.MODULE$.prefix();
   }

   public static Iterator productElementNames() {
      return TopScope$.MODULE$.productElementNames();
   }

   public static boolean xml_$bang$eq(final Object other) {
      return TopScope$.MODULE$.xml_$bang$eq(other);
   }

   public static boolean xml_$eq$eq(final Object other) {
      return TopScope$.MODULE$.xml_$eq$eq(other);
   }

   public static boolean equals(final Object other) {
      return TopScope$.MODULE$.equals(other);
   }

   public static int hashCode() {
      return TopScope$.MODULE$.hashCode();
   }

   public static boolean strict_$bang$eq(final Equality other) {
      return TopScope$.MODULE$.strict_$bang$eq(other);
   }
}
