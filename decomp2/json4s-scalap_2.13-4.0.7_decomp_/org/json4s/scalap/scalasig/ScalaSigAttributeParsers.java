package org.json4s.scalap.scalasig;

import org.json4s.scalap.InRule;
import org.json4s.scalap.Rule;
import org.json4s.scalap.Rules;
import org.json4s.scalap.RulesWithState;
import org.json4s.scalap.SeqRule;
import org.json4s.scalap.StateRules;
import scala.Function0;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a<QAE\n\t\u0002q1QAH\n\t\u0002}AQ!K\u0001\u0005\u0002)BQaK\u0001\u0005\u00021Bq!N\u0001C\u0002\u0013\u0005a\u0007\u0003\u0004F\u0003\u0001\u0006Ia\u000e\u0005\b\r\u0006\u0011\r\u0011\"\u0001H\u0011\u0019I\u0015\u0001)A\u0005\u0011\"9!*\u0001b\u0001\n\u0003Y\u0005B\u0002)\u0002A\u0003%A\nC\u0004R\u0003\t\u0007I\u0011\u0001*\t\r\u0001\f\u0001\u0015!\u0003T\u0011\u001d\t\u0017A1A\u0005\u0002\tDa\u0001Z\u0001!\u0002\u0013\u0019\u0007bB3\u0002\u0005\u0004%\tA\u001a\u0005\u0007a\u0006\u0001\u000b\u0011B4\t\u000fE\f!\u0019!C\u0001e\"1q/\u0001Q\u0001\nM\f\u0001dU2bY\u0006\u001c\u0016nZ!uiJL'-\u001e;f!\u0006\u00148/\u001a:t\u0015\t!R#\u0001\u0005tG\u0006d\u0017m]5h\u0015\t1r#\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u00031e\taA[:p]R\u001a(\"\u0001\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005u\tQ\"A\n\u00031M\u001b\u0017\r\\1TS\u001e\fE\u000f\u001e:jEV$X\rU1sg\u0016\u00148oE\u0002\u0002A\u0019\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z%\u00164\u0007CA\u000f(\u0013\tA3C\u0001\bCsR,7i\u001c3f%\u0016\fG-\u001a:\u0002\rqJg.\u001b;?)\u0005a\u0012!\u00029beN,GCA\u00171!\tib&\u0003\u00020'\tA1kY1mCNKw\rC\u00032\u0007\u0001\u0007!'\u0001\u0005csR,7i\u001c3f!\ti2'\u0003\u00025'\tA!)\u001f;f\u0007>$W-A\u0002oCR,\u0012a\u000e\t\u0007qeZ4h\u0010\"\u000e\u0003UI!AO\u000b\u0003\tI+H.\u001a\t\u0003yuj\u0011!A\u0005\u0003}\u001d\u0012\u0011a\u0015\t\u0003C\u0001K!!\u0011\u0012\u0003\u0007%sG\u000f\u0005\u0002\"\u0007&\u0011AI\t\u0002\b\u001d>$\b.\u001b8h\u0003\u0011q\u0017\r\u001e\u0011\u0002\u0011I\fwOQ=uKN,\u0012\u0001\u0013\t\u0007qeZ4H\r\"\u0002\u0013I\fwOQ=uKN\u0004\u0013!B3oiJLX#\u0001'\u0011\raJ4hO'C!\u0011Adj\u0010\u001a\n\u0005=+\"A\u0002\u0013uS2$W-\u0001\u0004f]R\u0014\u0018\u0010I\u0001\u0007gflG/\u00192\u0016\u0003M\u0003b\u0001O\u001d<wQ\u0013\u0005cA+^\u001b:\u0011ak\u0017\b\u0003/jk\u0011\u0001\u0017\u0006\u00033n\ta\u0001\u0010:p_Rt\u0014\"A\u0012\n\u0005q\u0013\u0013a\u00029bG.\fw-Z\u0005\u0003=~\u00131aU3r\u0015\ta&%A\u0004ts6$\u0018M\u0019\u0011\u0002\u0011M\u001c\u0017\r\\1TS\u001e,\u0012a\u0019\t\u0007qeZ4(\f\"\u0002\u0013M\u001c\u0017\r\\1TS\u001e\u0004\u0013\u0001B;uMb*\u0012a\u001a\t\u0007qeZ4\b\u001b\"\u0011\u0005%lgB\u00016l!\t9&%\u0003\u0002mE\u00051\u0001K]3eK\u001aL!A\\8\u0003\rM#(/\u001b8h\u0015\ta'%A\u0003vi\u001aD\u0004%A\u0005m_:<g+\u00197vKV\t1\u000f\u0005\u00049smZDO\u0011\t\u0003CUL!A\u001e\u0012\u0003\t1{gnZ\u0001\u000bY>twMV1mk\u0016\u0004\u0003"
)
public final class ScalaSigAttributeParsers {
   public static Rule longValue() {
      return ScalaSigAttributeParsers$.MODULE$.longValue();
   }

   public static Rule utf8() {
      return ScalaSigAttributeParsers$.MODULE$.utf8();
   }

   public static Rule scalaSig() {
      return ScalaSigAttributeParsers$.MODULE$.scalaSig();
   }

   public static Rule symtab() {
      return ScalaSigAttributeParsers$.MODULE$.symtab();
   }

   public static Rule entry() {
      return ScalaSigAttributeParsers$.MODULE$.entry();
   }

   public static Rule rawBytes() {
      return ScalaSigAttributeParsers$.MODULE$.rawBytes();
   }

   public static Rule nat() {
      return ScalaSigAttributeParsers$.MODULE$.nat();
   }

   public static ScalaSig parse(final ByteCode byteCode) {
      return ScalaSigAttributeParsers$.MODULE$.parse(byteCode);
   }

   public static Rule bytes(final int n) {
      return ScalaSigAttributeParsers$.MODULE$.bytes(n);
   }

   public static Rule u4() {
      return ScalaSigAttributeParsers$.MODULE$.u4();
   }

   public static Rule u2() {
      return ScalaSigAttributeParsers$.MODULE$.u2();
   }

   public static Rule u1() {
      return ScalaSigAttributeParsers$.MODULE$.u1();
   }

   public static Rule byte() {
      return ScalaSigAttributeParsers$.MODULE$.byte();
   }

   public static RulesWithState factory() {
      return ScalaSigAttributeParsers$.MODULE$.factory();
   }

   public static Rule repeatUntil(final Rule rule, final Function1 finished, final Object initial) {
      return ScalaSigAttributeParsers$.MODULE$.repeatUntil(rule, finished, initial);
   }

   public static Rule anyOf(final Seq rules) {
      return ScalaSigAttributeParsers$.MODULE$.anyOf(rules);
   }

   public static Function1 allOf(final Seq rules) {
      return ScalaSigAttributeParsers$.MODULE$.allOf(rules);
   }

   public static Rule cond(final Function1 f) {
      return ScalaSigAttributeParsers$.MODULE$.cond(f);
   }

   public static Rule none() {
      return ScalaSigAttributeParsers$.MODULE$.none();
   }

   public static Rule nil() {
      return ScalaSigAttributeParsers$.MODULE$.nil();
   }

   public static Rule update(final Function1 f) {
      return ScalaSigAttributeParsers$.MODULE$.update(f);
   }

   public static Rule set(final Function0 s) {
      return ScalaSigAttributeParsers$.MODULE$.set(s);
   }

   public static Rule get() {
      return ScalaSigAttributeParsers$.MODULE$.get();
   }

   public static Rule read(final Function1 f) {
      return ScalaSigAttributeParsers$.MODULE$.read(f);
   }

   public static Rule unit(final Function0 a) {
      return ScalaSigAttributeParsers$.MODULE$.unit(a);
   }

   public static Rule apply(final Function1 f) {
      return ScalaSigAttributeParsers$.MODULE$.apply(f);
   }

   public static Function1 expect(final Rule rule) {
      return ScalaSigAttributeParsers$.MODULE$.expect(rule);
   }

   public static Rule ruleWithName(final String _name, final Function1 f) {
      return ScalaSigAttributeParsers$.MODULE$.ruleWithName(_name, f);
   }

   public static Rule oneOf(final Seq rules) {
      return ScalaSigAttributeParsers$.MODULE$.oneOf(rules);
   }

   public static Rule error(final Object err) {
      return ScalaSigAttributeParsers$.MODULE$.error(err);
   }

   public static Rule error() {
      return ScalaSigAttributeParsers$.MODULE$.error();
   }

   public static Rule failure() {
      return ScalaSigAttributeParsers$.MODULE$.failure();
   }

   public static Rule success(final Object out, final Object a) {
      return ScalaSigAttributeParsers$.MODULE$.success(out, a);
   }

   public static StateRules state() {
      return ScalaSigAttributeParsers$.MODULE$.state();
   }

   public static Rules.FromRule from() {
      return ScalaSigAttributeParsers$.MODULE$.from();
   }

   public static SeqRule seqRule(final Rule rule) {
      return ScalaSigAttributeParsers$.MODULE$.seqRule(rule);
   }

   public static InRule inRule(final Rule rule) {
      return ScalaSigAttributeParsers$.MODULE$.inRule(rule);
   }

   public static Rule rule(final Function1 f) {
      return ScalaSigAttributeParsers$.MODULE$.rule(f);
   }
}
