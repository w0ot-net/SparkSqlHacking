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
   bytes = "\u0006\u0005\u0005eu!\u0002\n\u0014\u0011\u0003ab!\u0002\u0010\u0014\u0011\u0003y\u0002\"B\u0017\u0002\t\u0003qS\u0001B\u0018\u0002\u0001A*AaM\u0001\u0001i!9\u0011+\u0001b\u0001\n\u0003\u0011\u0006B\u00025\u0002A\u0003%1\u000bC\u0004j\u0003\t\u0007I\u0011\u00016\t\r1\f\u0001\u0015!\u0003l\u0011\u0015i\u0017\u0001\"\u0001o\u0011\u0015)\u0018\u0001\"\u0001w\u0011\u001d\ti!\u0001C\u0001\u0003\u001fA!\"a\f\u0002\u0011\u000b\u0007I\u0011AA\u0019\u0011)\t9%\u0001EC\u0002\u0013\u0005\u0011\u0011\n\u0005\u000b\u00033\n\u0001R1A\u0005\u0002\u0005m\u0003BCA6\u0003!\u0015\r\u0011\"\u0001\u0002n!Q\u0011QP\u0001\t\u0006\u0004%\t!a \t\u0015\u0005-\u0015\u0001#b\u0001\n\u0003\ti)A\bTG\u0006d\u0017mU5h!\u0006\u00148/\u001a:t\u0015\t!R#\u0001\u0005tG\u0006d\u0017m]5h\u0015\t1r#\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u00031e\taA[:p]R\u001a(\"\u0001\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005u\tQ\"A\n\u0003\u001fM\u001b\u0017\r\\1TS\u001e\u0004\u0016M]:feN\u001cB!\u0001\u0011'UA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u0004\"a\n\u0015\u000e\u0003UI!!K\u000b\u0003\u001dI+H.Z:XSRD7\u000b^1uKB\u0011qeK\u0005\u0003YU\u0011q\"T3n_&\u001c\u0018M\u00197f%VdWm]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003q\u0011\u0011a\u0015\t\u0003;EJ!AM\n\u0003\u0011M\u001b\u0017\r\\1TS\u001e\u0014a\u0001U1sg\u0016\u0014XCA\u001b>!\u00111tg\u000f$\u000e\u0003\u0005I!\u0001O\u001d\u0003\tI+H.Z\u0005\u0003uU\u0011!b\u0015;bi\u0016\u0014V\u000f\\3t!\taT\b\u0004\u0001\u0005\u000by\"!\u0019A \u0003\u0003\u0005\u000b\"\u0001Q\"\u0011\u0005\u0005\n\u0015B\u0001\"#\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\t#\n\u0005\u0015\u0013#aA!osB\u0011qI\u0014\b\u0003\u00112\u0003\"!\u0013\u0012\u000e\u0003)S!aS\u000e\u0002\rq\u0012xn\u001c;?\u0013\ti%%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001fB\u0013aa\u0015;sS:<'BA'#\u0003\u0019\u0019\u00180\u001c+bEV\t1\u000b\u0005\u0004()V+f\u000bQ\u0005\u0003qU\u0001\"AN\u0002\u0011\u0007]cvL\u0004\u0002Y5:\u0011\u0011*W\u0005\u0002G%\u00111LI\u0001\ba\u0006\u001c7.Y4f\u0013\tifLA\u0002TKFT!a\u0017\u0012\u0011\t\u001d\u0002'-Z\u0005\u0003CV\u0011a\u0001\n;jY\u0012,\u0007CA\u0011d\u0013\t!'EA\u0002J]R\u0004\"!\b4\n\u0005\u001d\u001c\"\u0001\u0003\"zi\u0016\u001cu\u000eZ3\u0002\u000fMLX\u000eV1cA\u0005!1/\u001b>f+\u0005Y\u0007CB\u0014U+V\u0013\u0007)A\u0003tSj,\u0007%A\u0003f]R\u0014\u0018\u0010\u0006\u0002pgB1q\u0005V+qE\u0002\u0003\"!V9\n\u0005I\f$!B#oiJL\b\"\u0002;\n\u0001\u0004\u0011\u0017!B5oI\u0016D\u0018A\u00039beN,WI\u001c;ssV\u0011qo\u001f\u000b\u0003qv$\"!\u001f?\u0011\u0007Y\"!\u0010\u0005\u0002=w\u0012)aH\u0003b\u0001\u007f!)AO\u0003a\u0001E\")aP\u0003a\u0001\u007f\u00061\u0001/\u0019:tKJ\u0004R!!\u0001\u0002\bit1!HA\u0002\u0013\r\t)aE\u0001\u0015'\u000e\fG.Y*jO\u0016sGO]=QCJ\u001cXM]:\n\t\u0005%\u00111\u0002\u0002\f\u000b:$(/\u001f)beN,'OC\u0002\u0002\u0006M\t!\"\u00197m\u000b:$(/[3t+\u0011\t\t\"a\n\u0015\t\u0005M\u0011\u0011\u0006\t\bOQ+V+!\u0006G!\u0019\t9\"!\t\u0002&5\u0011\u0011\u0011\u0004\u0006\u0005\u00037\ti\"A\u0005j[6,H/\u00192mK*\u0019\u0011q\u0004\u0012\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002$\u0005e!\u0001\u0002'jgR\u00042\u0001PA\u0014\t\u0015q4B1\u0001@\u0011\u001d\tYc\u0003a\u0001\u0003[\t\u0011A\u001a\t\u0007\u0003\u0003\t9!!\n\u0002\u000f\u0015tGO]5fgV\u0011\u00111\u0007\n\u0007\u0003k\ti$!\u0011\u0007\r\u0005]\u0002\u0001AA\u001a\u00051a$/\u001a4j]\u0016lWM\u001c;?\u0013\r\tY$\u0006\u0002\u0006%VdWm\u001d\t\bOQ+V+a\u0010G!\u0015\t9\"!\tD!\r9\u00131I\u0005\u0004\u0003\u000b*\"\u0001\u0002(b[\u0016\fqa]=nE>d7/\u0006\u0002\u0002LI1\u0011QJA(\u0003\u00032a!a\u000e\u0001\u0001\u0005-\u0003cB\u0014U+V\u000b\tF\u0012\t\u0007\u0003/\t\t#a\u0015\u0011\u0007u\t)&C\u0002\u0002XM\u0011aaU=nE>d\u0017aB7fi\"|Gm]\u000b\u0003\u0003;\u0012b!a\u0018\u0002b\u0005\u0005cABA\u001c\u0001\u0001\ti\u0006E\u0004()V+\u00161\r$\u0011\r\u0005]\u0011\u0011EA3!\ri\u0012qM\u0005\u0004\u0003S\u001a\"\u0001D'fi\"|GmU=nE>d\u0017AC1uiJL'-\u001e;fgV\u0011\u0011q\u000e\n\u0007\u0003c\n\u0019(!\u0011\u0007\r\u0005]\u0002\u0001AA8!\u001d9C+V+\u0002v\u0019\u0003b!a\u0006\u0002\"\u0005]\u0004cA\u000f\u0002z%\u0019\u00111P\n\u0003\u001b\u0005#HO]5ckR,\u0017J\u001c4p\u0003=!x\u000e\u001d'fm\u0016d7\t\\1tg\u0016\u001cXCAAA!\u001d9C+V+\u0002\u0004\u001a\u0003b!a\u0006\u0002\"\u0005\u0015\u0005cA\u000f\u0002\b&\u0019\u0011\u0011R\n\u0003\u0017\rc\u0017m]:Ts6\u0014w\u000e\\\u0001\u0010i>\u0004H*\u001a<fY>\u0013'.Z2ugV\u0011\u0011q\u0012\t\bOQ+V+!%G!\u0019\t9\"!\t\u0002\u0014B\u0019Q$!&\n\u0007\u0005]5C\u0001\u0007PE*,7\r^*z[\n|G\u000e"
)
public final class ScalaSigParsers {
   public static Rule topLevelObjects() {
      return ScalaSigParsers$.MODULE$.topLevelObjects();
   }

   public static Rule topLevelClasses() {
      return ScalaSigParsers$.MODULE$.topLevelClasses();
   }

   public static Rule attributes() {
      return ScalaSigParsers$.MODULE$.attributes();
   }

   public static Rule methods() {
      return ScalaSigParsers$.MODULE$.methods();
   }

   public static Rule symbols() {
      return ScalaSigParsers$.MODULE$.symbols();
   }

   public static Rule entries() {
      return ScalaSigParsers$.MODULE$.entries();
   }

   public static Rule allEntries(final Rule f) {
      return ScalaSigParsers$.MODULE$.allEntries(f);
   }

   public static Rule parseEntry(final Rule parser, final int index) {
      return ScalaSigParsers$.MODULE$.parseEntry(parser, index);
   }

   public static Rule entry(final int index) {
      return ScalaSigParsers$.MODULE$.entry(index);
   }

   public static Rule size() {
      return ScalaSigParsers$.MODULE$.size();
   }

   public static Rule symTab() {
      return ScalaSigParsers$.MODULE$.symTab();
   }

   public static Rule ruleWithName(final String name, final Function1 f) {
      return ScalaSigParsers$.MODULE$.ruleWithName(name, f);
   }

   public static Rule memo(final Object key, final Function0 toRule) {
      return ScalaSigParsers$.MODULE$.memo(key, toRule);
   }

   public static RulesWithState factory() {
      return ScalaSigParsers$.MODULE$.factory();
   }

   public static Rule repeatUntil(final Rule rule, final Function1 finished, final Object initial) {
      return ScalaSigParsers$.MODULE$.repeatUntil(rule, finished, initial);
   }

   public static Rule anyOf(final Seq rules) {
      return ScalaSigParsers$.MODULE$.anyOf(rules);
   }

   public static Function1 allOf(final Seq rules) {
      return ScalaSigParsers$.MODULE$.allOf(rules);
   }

   public static Rule cond(final Function1 f) {
      return ScalaSigParsers$.MODULE$.cond(f);
   }

   public static Rule none() {
      return ScalaSigParsers$.MODULE$.none();
   }

   public static Rule nil() {
      return ScalaSigParsers$.MODULE$.nil();
   }

   public static Rule update(final Function1 f) {
      return ScalaSigParsers$.MODULE$.update(f);
   }

   public static Rule set(final Function0 s) {
      return ScalaSigParsers$.MODULE$.set(s);
   }

   public static Rule get() {
      return ScalaSigParsers$.MODULE$.get();
   }

   public static Rule read(final Function1 f) {
      return ScalaSigParsers$.MODULE$.read(f);
   }

   public static Rule unit(final Function0 a) {
      return ScalaSigParsers$.MODULE$.unit(a);
   }

   public static Rule apply(final Function1 f) {
      return ScalaSigParsers$.MODULE$.apply(f);
   }

   public static Function1 expect(final Rule rule) {
      return ScalaSigParsers$.MODULE$.expect(rule);
   }

   public static Rule oneOf(final Seq rules) {
      return ScalaSigParsers$.MODULE$.oneOf(rules);
   }

   public static Rule error(final Object err) {
      return ScalaSigParsers$.MODULE$.error(err);
   }

   public static Rule error() {
      return ScalaSigParsers$.MODULE$.error();
   }

   public static Rule failure() {
      return ScalaSigParsers$.MODULE$.failure();
   }

   public static Rule success(final Object out, final Object a) {
      return ScalaSigParsers$.MODULE$.success(out, a);
   }

   public static StateRules state() {
      return ScalaSigParsers$.MODULE$.state();
   }

   public static Rules.FromRule from() {
      return ScalaSigParsers$.MODULE$.from();
   }

   public static SeqRule seqRule(final Rule rule) {
      return ScalaSigParsers$.MODULE$.seqRule(rule);
   }

   public static InRule inRule(final Rule rule) {
      return ScalaSigParsers$.MODULE$.inRule(rule);
   }

   public static Rule rule(final Function1 f) {
      return ScalaSigParsers$.MODULE$.rule(f);
   }
}
