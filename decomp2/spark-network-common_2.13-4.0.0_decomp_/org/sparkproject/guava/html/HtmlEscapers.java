package org.sparkproject.guava.html;

import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.escape.Escaper;
import org.sparkproject.guava.escape.Escapers;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class HtmlEscapers {
   private static final Escaper HTML_ESCAPER = Escapers.builder().addEscape('"', "&quot;").addEscape('\'', "&#39;").addEscape('&', "&amp;").addEscape('<', "&lt;").addEscape('>', "&gt;").build();

   public static Escaper htmlEscaper() {
      return HTML_ESCAPER;
   }

   private HtmlEscapers() {
   }
}
