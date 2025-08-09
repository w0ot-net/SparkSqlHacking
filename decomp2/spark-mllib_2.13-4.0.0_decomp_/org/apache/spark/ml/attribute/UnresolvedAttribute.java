package org.apache.spark.ml.attribute;

import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019<QAD\b\t\u0002i1Q\u0001H\b\t\u0002uAQ!I\u0001\u0005\u0002\tBQaI\u0001\u0005B\u0011BQ\u0001K\u0001\u0005B%BQAM\u0001\u0005BMBQaN\u0001\u0005BaBQ!O\u0001\u0005BMBQAO\u0001\u0005BmBaAS\u0001\u0005B=Y\u0005\"\u0002,\u0002\t\u0003B\u0004\"B\u0016\u0002\t\u0003:\u0006\"B-\u0002\t\u0003R\u0006b\u0002/\u0002\u0003\u0003%I!X\u0001\u0014+:\u0014Xm]8mm\u0016$\u0017\t\u001e;sS\n,H/\u001a\u0006\u0003!E\t\u0011\"\u0019;ue&\u0014W\u000f^3\u000b\u0005I\u0019\u0012AA7m\u0015\t!R#A\u0003ta\u0006\u00148N\u0003\u0002\u0017/\u00051\u0011\r]1dQ\u0016T\u0011\u0001G\u0001\u0004_J<7\u0001\u0001\t\u00037\u0005i\u0011a\u0004\u0002\u0014+:\u0014Xm]8mm\u0016$\u0017\t\u001e;sS\n,H/Z\n\u0003\u0003y\u0001\"aG\u0010\n\u0005\u0001z!!C!uiJL'-\u001e;f\u0003\u0019a\u0014N\\5u}Q\t!$\u0001\u0005biR\u0014H+\u001f9f+\u0005)\u0003CA\u000e'\u0013\t9sBA\u0007BiR\u0014\u0018NY;uKRK\b/Z\u0001\no&$\b.\u00138eKb$\"A\b\u0016\t\u000b-\"\u0001\u0019\u0001\u0017\u0002\u000b%tG-\u001a=\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0003=\nQa]2bY\u0006L!!\r\u0018\u0003\u0007%sG/A\u0005jg:+X.\u001a:jGV\tA\u0007\u0005\u0002.k%\u0011aG\f\u0002\b\u0005>|G.Z1o\u000319\u0018\u000e\u001e5pkRLe\u000eZ3y+\u0005q\u0012!C5t\u001d>l\u0017N\\1m\u0003\u0011q\u0017-\\3\u0016\u0003q\u00022!L\u001f@\u0013\tqdF\u0001\u0004PaRLwN\u001c\t\u0003\u0001\u001es!!Q#\u0011\u0005\tsS\"A\"\u000b\u0005\u0011K\u0012A\u0002\u001fs_>$h(\u0003\u0002G]\u00051\u0001K]3eK\u001aL!\u0001S%\u0003\rM#(/\u001b8h\u0015\t1e&\u0001\bu_6+G/\u00193bi\u0006LU\u000e\u001d7\u0015\u00051#\u0006CA'S\u001b\u0005q%BA(Q\u0003\u0015!\u0018\u0010]3t\u0015\t\t6#A\u0002tc2L!a\u0015(\u0003\u00115+G/\u00193bi\u0006DQ!V\u0005A\u0002Q\n\u0001b^5uQRK\b/Z\u0001\fo&$\bn\\;u\u001d\u0006lW-F\u0001Y!\riS\bL\u0001\to&$\bNT1nKR\u0011ad\u0017\u0005\u0006u1\u0001\raP\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002=B\u0011q\fZ\u0007\u0002A*\u0011\u0011MY\u0001\u0005Y\u0006twMC\u0001d\u0003\u0011Q\u0017M^1\n\u0005\u0015\u0004'AB(cU\u0016\u001cG\u000f"
)
public final class UnresolvedAttribute {
   public static Attribute withName(final String name) {
      return UnresolvedAttribute$.MODULE$.withName(name);
   }

   public static Option index() {
      return UnresolvedAttribute$.MODULE$.index();
   }

   public static Attribute withoutName() {
      return UnresolvedAttribute$.MODULE$.withoutName();
   }

   public static Option name() {
      return UnresolvedAttribute$.MODULE$.name();
   }

   public static boolean isNominal() {
      return UnresolvedAttribute$.MODULE$.isNominal();
   }

   public static Attribute withoutIndex() {
      return UnresolvedAttribute$.MODULE$.withoutIndex();
   }

   public static boolean isNumeric() {
      return UnresolvedAttribute$.MODULE$.isNumeric();
   }

   public static Attribute withIndex(final int index) {
      return UnresolvedAttribute$.MODULE$.withIndex(index);
   }

   public static AttributeType attrType() {
      return UnresolvedAttribute$.MODULE$.attrType();
   }

   public static String toString() {
      return UnresolvedAttribute$.MODULE$.toString();
   }

   public static StructField toStructField() {
      return UnresolvedAttribute$.MODULE$.toStructField();
   }

   public static StructField toStructField(final Metadata existingMetadata) {
      return UnresolvedAttribute$.MODULE$.toStructField(existingMetadata);
   }

   public static Metadata toMetadata() {
      return UnresolvedAttribute$.MODULE$.toMetadata();
   }

   public static Metadata toMetadata(final Metadata existingMetadata) {
      return UnresolvedAttribute$.MODULE$.toMetadata(existingMetadata);
   }
}
