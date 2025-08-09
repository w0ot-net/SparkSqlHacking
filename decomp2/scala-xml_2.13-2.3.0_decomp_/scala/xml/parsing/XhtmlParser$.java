package scala.xml.parsing;

import scala.io.Source;
import scala.xml.NodeSeq;

public final class XhtmlParser$ {
   public static final XhtmlParser$ MODULE$ = new XhtmlParser$();

   public NodeSeq apply(final Source source) {
      return ((MarkupParser)(new XhtmlParser(source)).initialize()).document();
   }

   private XhtmlParser$() {
   }
}
