package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.xml.dtd.ANY$;
import scala.xml.dtd.ContentModel;
import scala.xml.dtd.ContentModel$;
import scala.xml.dtd.ELEMENTS;
import scala.xml.dtd.EMPTY$;
import scala.xml.dtd.MIXED;
import scala.xml.dtd.PCDATA$;
import scala.xml.dtd.impl.Base;
import scala.xml.dtd.impl.WordExp;

public final class ElementContentModel$ {
   public static final ElementContentModel$ MODULE$ = new ElementContentModel$();

   public ContentModel parseContentModel(final String model) {
      ElementContentModel.ContentSpec var3 = ElementContentModel.ContentSpec$.MODULE$.parse(model);
      if (ElementContentModel$ContentSpec$Empty$.MODULE$.equals(var3)) {
         return EMPTY$.MODULE$;
      } else if (ElementContentModel$ContentSpec$Any$.MODULE$.equals(var3)) {
         return ANY$.MODULE$;
      } else if (ElementContentModel$ContentSpec$PCData$.MODULE$.equals(var3)) {
         return PCDATA$.MODULE$;
      } else if (var3 instanceof ElementContentModel$ContentSpec$Children) {
         ElementContentModel$ContentSpec$Children var4 = (ElementContentModel$ContentSpec$Children)var3;
         ElementContentModel$Elements$Many elements = var4.elements();
         ElementContentModel.Occurrence occurrence = var4.occurrence();
         return new ELEMENTS(this.convertOccurrence(elements, occurrence));
      } else if (var3 instanceof ElementContentModel$ContentSpec$Mixed) {
         ElementContentModel$ContentSpec$Mixed var7 = (ElementContentModel$ContentSpec$Mixed)var3;
         List elements = var7.elements();
         WordExp.Letter var10 = ContentModel$.MODULE$.new Letter(new ContentModel.ElemName(ElementContentModel$ContentSpec$PCData$.MODULE$.value()));
         List result = (List)elements.map((elementsx) -> MODULE$.convertElements(elementsx)).$plus$colon(var10);
         return new MIXED(ContentModel$.MODULE$.Alt().apply(result));
      } else {
         throw new MatchError(var3);
      }
   }

   private Base.RegExp convertElements(final ElementContentModel.Elements elements) {
      if (elements instanceof ElementContentModel$Elements$Element) {
         ElementContentModel$Elements$Element var4 = (ElementContentModel$Elements$Element)elements;
         String name = var4.name();
         return ContentModel$.MODULE$.new Letter(new ContentModel.ElemName(name));
      } else if (elements instanceof ElementContentModel$Elements$Choice) {
         ElementContentModel$Elements$Choice var6 = (ElementContentModel$Elements$Choice)elements;
         List children = var6.children();
         return ContentModel$.MODULE$.Alt().apply(children.map((cp) -> this.convertCp$1(cp)));
      } else if (elements instanceof ElementContentModel$Elements$Sequence) {
         ElementContentModel$Elements$Sequence var8 = (ElementContentModel$Elements$Sequence)elements;
         List children = var8.children();
         return ContentModel$.MODULE$.Sequ().apply(children.map((cp) -> this.convertCp$1(cp)));
      } else {
         throw new MatchError(elements);
      }
   }

   private Base.RegExp convertOccurrence(final ElementContentModel.Elements elements, final ElementContentModel.Occurrence occurrence) {
      Base.RegExp result = this.convertElements(elements);
      if (ElementContentModel$Occurrence$Once$.MODULE$.equals(occurrence)) {
         return result;
      } else if (ElementContentModel$Occurrence$RepeatOptional$.MODULE$.equals(occurrence)) {
         return ContentModel$.MODULE$.new Star(result);
      } else if (ElementContentModel$Occurrence$OnceOptional$.MODULE$.equals(occurrence)) {
         return ContentModel$.MODULE$.new Star(result);
      } else if (ElementContentModel$Occurrence$Repeat$.MODULE$.equals(occurrence)) {
         return ContentModel$.MODULE$.new Star(result);
      } else {
         throw new MatchError(occurrence);
      }
   }

   public boolean scala$xml$parsing$ElementContentModel$$isParenthesized(final String string) {
      return string.startsWith("(") && this.isParenthesized$1(0, string);
   }

   public String scala$xml$parsing$ElementContentModel$$removeParentheses(final String string) {
      while(this.scala$xml$parsing$ElementContentModel$$isParenthesized(string)) {
         string = .MODULE$.init$extension(scala.Predef..MODULE$.augmentString(.MODULE$.tail$extension(scala.Predef..MODULE$.augmentString(string))));
      }

      return string;
   }

   public List scala$xml$parsing$ElementContentModel$$split(final String string, final char separator) {
      return this.split$1(scala.package..MODULE$.List().empty(), 0, "", string, separator);
   }

   private final Base.RegExp convertCp$1(final ElementContentModel.Cp cp) {
      return this.convertOccurrence(cp.elements(), cp.occurrence());
   }

   private final boolean isParenthesized$1(final int level, final String tail) {
      while(true) {
         char current = .MODULE$.head$extension(scala.Predef..MODULE$.augmentString(tail));
         String nextTail = .MODULE$.tail$extension(scala.Predef..MODULE$.augmentString(tail));
         int nextLevel = current == '(' ? level + 1 : (current == ')' ? level - 1 : level);
         if (nextTail.isEmpty()) {
            return nextLevel == 0;
         }

         if (nextLevel == 0) {
            return false;
         }

         tail = nextTail;
         level = nextLevel;
      }
   }

   private final List split$1(final List result, final int level, final String init, final String tail, final char separator$1) {
      while(!tail.isEmpty()) {
         char current = .MODULE$.head$extension(scala.Predef..MODULE$.augmentString(tail));
         String nextTail = .MODULE$.tail$extension(scala.Predef..MODULE$.augmentString(tail));
         if (level == 0 && current == separator$1) {
            List var10000 = (List)result.$colon$plus(init);
            tail = nextTail;
            init = "";
            level = level;
            result = var10000;
         } else {
            int var10001 = current == '(' ? level + 1 : (current == ')' ? level - 1 : level);
            String var10002 = .MODULE$.$colon$plus$extension(scala.Predef..MODULE$.augmentString(init), current);
            tail = nextTail;
            init = var10002;
            level = var10001;
            result = result;
         }
      }

      if (init.isEmpty()) {
         return result;
      } else {
         return (List)result.$colon$plus(init);
      }
   }

   private ElementContentModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
