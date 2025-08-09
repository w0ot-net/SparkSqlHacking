package scala.reflect.internal.util;

import scala.Function1;
import scala.Option;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;

public final class StringOps$ implements StringOps {
   public static final StringOps$ MODULE$ = new StringOps$();

   static {
      StringOps$ var10000 = MODULE$;
   }

   public Seq oempty(final Seq xs) {
      return StringOps.oempty$(this, xs);
   }

   public String ojoin(final Seq xs) {
      return StringOps.ojoin$(this, xs);
   }

   public String longestCommonPrefix(final List xs) {
      return StringOps.longestCommonPrefix$(this, xs);
   }

   public String trimTrailingSpace(final String s) {
      return StringOps.trimTrailingSpace$(this, s);
   }

   public String trimAllTrailingSpace(final String s) {
      return StringOps.trimAllTrailingSpace$(this, s);
   }

   public List decompose(final String str, final char sep) {
      return StringOps.decompose$(this, str, sep);
   }

   public List words(final String str) {
      return StringOps.words$(this, str);
   }

   public Option splitWhere(final String str, final Function1 f, final boolean doDropIndex) {
      return StringOps.splitWhere$(this, str, f, doDropIndex);
   }

   public boolean splitWhere$default$3() {
      return StringOps.splitWhere$default$3$(this);
   }

   public Option splitAround(final String str, final int idx) {
      return StringOps.splitAround$(this, str, idx);
   }

   public Option splitAt(final String str, final int idx, final boolean doDropIndex) {
      return StringOps.splitAt$(this, str, idx, doDropIndex);
   }

   public boolean splitAt$default$3() {
      return StringOps.splitAt$default$3$(this);
   }

   public String countElementsAsString(final int n, final String element) {
      return StringOps.countElementsAsString$(this, n, element);
   }

   public String countAsString(final int n) {
      return StringOps.countAsString$(this, n);
   }

   private StringOps$() {
   }
}
