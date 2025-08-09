package org.apache.spark.sql.catalyst.parser;

import java.io.Reader;
import org.apache.spark.sql.types.DataType;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;
import scala.util.parsing.combinator.Parsers;

@ScalaSignature(
   bytes = "\u0006\u0005y;Q\u0001D\u0007\t\u0002i1Q\u0001H\u0007\t\u0002uAQAL\u0001\u0005\u0002=B\u0001\u0002M\u0001\t\u0006\u0004%\t\"\r\u0005\t}\u0005A)\u0019!C\tc!Aq(\u0001EC\u0002\u0013E\u0011\u0007\u0003\u0005A\u0003!\u0015\r\u0011\"\u00052\u0011!\t\u0015\u0001#b\u0001\n#\u0011\u0005\u0002C$\u0002\u0011\u000b\u0007I\u0011\u0003%\t\u00115\u000b\u0001R1A\u0005\u0012EB\u0001BT\u0001\t\u0006\u0004%\t\"\r\u0005\u0006\u001f\u0006!\t\u0001U\u0001\u0017\u0019\u0016<\u0017mY=UsB,7\u000b\u001e:j]\u001e\u0004\u0016M]:fe*\u0011abD\u0001\u0007a\u0006\u00148/\u001a:\u000b\u0005A\t\u0012\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005I\u0019\u0012aA:rY*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005m\tQ\"A\u0007\u0003-1+w-Y2z)f\u0004Xm\u0015;sS:<\u0007+\u0019:tKJ\u001c2!\u0001\u0010%!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0011Q\u0005L\u0007\u0002M)\u0011q\u0005K\u0001\u000bG>l'-\u001b8bi>\u0014(BA\u0015+\u0003\u001d\u0001\u0018M]:j]\u001eT!a\u000b\u0011\u0002\tU$\u0018\u000e\\\u0005\u0003[\u0019\u0012ABU3hKb\u0004\u0016M]:feN\fa\u0001P5oSRtD#\u0001\u000e\u0002\u001bA\u0014\u0018.\\5uSZ,G+\u001f9f+\u0005\u0011\u0004cA\u001a5q5\t\u0011!\u0003\u00026m\t1\u0001+\u0019:tKJL!a\u000e\u0014\u0003\u000fA\u000b'o]3sgB\u0011\u0011\bP\u0007\u0002u)\u00111(E\u0001\u0006if\u0004Xm]\u0005\u0003{i\u0012\u0001\u0002R1uCRK\b/Z\u0001\u0011M&DX\r\u001a#fG&l\u0017\r\u001c+za\u0016\f\u0011\"\u0019:sCf$\u0016\u0010]3\u0002\u000f5\f\u0007\u000fV=qK\u0006Y1\u000f\u001e:vGR4\u0015.\u001a7e+\u0005\u0019\u0005cA\u001a5\tB\u0011\u0011(R\u0005\u0003\rj\u00121b\u0015;sk\u000e$h)[3mI\u00069!m\\8m-\u0006dW#A%\u0011\u0007M\"$\n\u0005\u0002 \u0017&\u0011A\n\t\u0002\b\u0005>|G.Z1o\u0003)\u0019HO];diRK\b/Z\u0001\tI\u0006$\u0018\rV=qK\u0006Y\u0001/\u0019:tKN#(/\u001b8h)\tA\u0014\u000bC\u0003S\u0017\u0001\u00071+\u0001\u0005bgN#(/\u001b8h!\t!6L\u0004\u0002V3B\u0011a\u000bI\u0007\u0002/*\u0011\u0001,G\u0001\u0007yI|w\u000e\u001e \n\u0005i\u0003\u0013A\u0002)sK\u0012,g-\u0003\u0002];\n11\u000b\u001e:j]\u001eT!A\u0017\u0011"
)
public final class LegacyTypeStringParser {
   public static DataType parseString(final String asString) {
      return LegacyTypeStringParser$.MODULE$.parseString(asString);
   }

   public static Parsers.ParseResult parseAll(final Parsers.Parser p, final CharSequence in) {
      return LegacyTypeStringParser$.MODULE$.parseAll(p, in);
   }

   public static Parsers.ParseResult parseAll(final Parsers.Parser p, final Reader in) {
      return LegacyTypeStringParser$.MODULE$.parseAll(p, in);
   }

   public static Parsers.ParseResult parseAll(final Parsers.Parser p, final scala.util.parsing.input.Reader in) {
      return LegacyTypeStringParser$.MODULE$.parseAll(p, in);
   }

   public static Parsers.ParseResult parse(final Parsers.Parser p, final Reader in) {
      return LegacyTypeStringParser$.MODULE$.parse(p, in);
   }

   public static Parsers.ParseResult parse(final Parsers.Parser p, final CharSequence in) {
      return LegacyTypeStringParser$.MODULE$.parse(p, in);
   }

   public static Parsers.ParseResult parse(final Parsers.Parser p, final scala.util.parsing.input.Reader in) {
      return LegacyTypeStringParser$.MODULE$.parse(p, in);
   }

   public static Parsers.Parser phrase(final Parsers.Parser p) {
      return LegacyTypeStringParser$.MODULE$.phrase(p);
   }

   public static Parsers.Parser err(final String msg) {
      return LegacyTypeStringParser$.MODULE$.err(msg);
   }

   public static Parsers.Parser positioned(final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.positioned(p);
   }

   public static Parsers.Parser regex(final Regex r) {
      return LegacyTypeStringParser$.MODULE$.regex(r);
   }

   public static Parsers.Parser literal(final String s) {
      return LegacyTypeStringParser$.MODULE$.literal(s);
   }

   public static boolean skipWhitespace() {
      return LegacyTypeStringParser$.MODULE$.skipWhitespace();
   }

   public static Parsers..tilde $tilde() {
      return LegacyTypeStringParser$.MODULE$.$tilde();
   }

   public static Function1 mkList() {
      return LegacyTypeStringParser$.MODULE$.mkList();
   }

   public static Parsers.Parser guard(final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.guard(p);
   }

   public static Parsers.Parser not(final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.not(p);
   }

   public static Parsers.Parser opt(final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.opt(p);
   }

   public static Parsers.Parser chainr1(final Function0 p, final Function0 q, final Function2 combine, final Object first) {
      return LegacyTypeStringParser$.MODULE$.chainr1(p, q, combine, first);
   }

   public static Parsers.Parser chainl1(final Function0 first, final Function0 p, final Function0 q) {
      return LegacyTypeStringParser$.MODULE$.chainl1(first, p, q);
   }

   public static Parsers.Parser chainl1(final Function0 p, final Function0 q) {
      return LegacyTypeStringParser$.MODULE$.chainl1(p, q);
   }

   public static Parsers.Parser rep1sep(final Function0 p, final Function0 q) {
      return LegacyTypeStringParser$.MODULE$.rep1sep(p, q);
   }

   public static Parsers.Parser repNM$default$4() {
      return LegacyTypeStringParser$.MODULE$.repNM$default$4();
   }

   public static Parsers.Parser repNM(final int n, final int m, final Parsers.Parser p, final Parsers.Parser sep) {
      return LegacyTypeStringParser$.MODULE$.repNM(n, m, p, sep);
   }

   public static Parsers.Parser repN(final int num, final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.repN(num, p);
   }

   public static Parsers.Parser rep1(final Function0 first, final Function0 p0) {
      return LegacyTypeStringParser$.MODULE$.rep1(first, p0);
   }

   public static Parsers.Parser rep1(final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.rep1(p);
   }

   public static Parsers.Parser repsep(final Function0 p, final Function0 q) {
      return LegacyTypeStringParser$.MODULE$.repsep(p, q);
   }

   public static Parsers.Parser rep(final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.rep(p);
   }

   public static Parsers.Parser log(final Function0 p, final String name) {
      return LegacyTypeStringParser$.MODULE$.log(p, name);
   }

   public static Parsers.Parser success(final Object v) {
      return LegacyTypeStringParser$.MODULE$.success(v);
   }

   public static Parsers.Parser failure(final String msg) {
      return LegacyTypeStringParser$.MODULE$.failure(msg);
   }

   public static Parsers.Parser acceptSeq(final Object es, final Function1 f) {
      return LegacyTypeStringParser$.MODULE$.acceptSeq(es, f);
   }

   public static Parsers.Parser acceptMatch(final String expected, final PartialFunction f) {
      return LegacyTypeStringParser$.MODULE$.acceptMatch(expected, f);
   }

   public static Parsers.Parser acceptIf(final Function1 p, final Function1 err) {
      return LegacyTypeStringParser$.MODULE$.acceptIf(p, err);
   }

   public static Parsers.Parser accept(final String expected, final PartialFunction f) {
      return LegacyTypeStringParser$.MODULE$.accept(expected, f);
   }

   public static Parsers.Parser accept(final Object es, final Function1 f) {
      return LegacyTypeStringParser$.MODULE$.accept(es, f);
   }

   public static Parsers.Parser accept(final Object e) {
      return LegacyTypeStringParser$.MODULE$.accept(e);
   }

   public static Parsers.Parser elem(final Object e) {
      return LegacyTypeStringParser$.MODULE$.elem(e);
   }

   public static Parsers.Parser elem(final String kind, final Function1 p) {
      return LegacyTypeStringParser$.MODULE$.elem(kind, p);
   }

   public static Parsers.Parser commit(final Function0 p) {
      return LegacyTypeStringParser$.MODULE$.commit(p);
   }

   public static Parsers.OnceParser OnceParser(final Function1 f) {
      return LegacyTypeStringParser$.MODULE$.OnceParser(f);
   }

   public static Parsers.Parser Parser(final Function1 f) {
      return LegacyTypeStringParser$.MODULE$.Parser(f);
   }

   public static Parsers.Error Error() {
      return LegacyTypeStringParser$.MODULE$.Error();
   }

   public static Parsers.Failure Failure() {
      return LegacyTypeStringParser$.MODULE$.Failure();
   }

   public static Parsers.NoSuccess NoSuccess() {
      return LegacyTypeStringParser$.MODULE$.NoSuccess();
   }

   public static Parsers.Success Success() {
      return LegacyTypeStringParser$.MODULE$.Success();
   }
}
