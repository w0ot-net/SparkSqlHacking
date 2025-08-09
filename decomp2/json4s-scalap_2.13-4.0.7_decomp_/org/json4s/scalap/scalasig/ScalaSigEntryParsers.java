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
   bytes = "\u0006\u0005\t\u0005w!B!C\u0011\u0003Ye!B'C\u0011\u0003q\u0005\"\u0002/\u0002\t\u0003iV\u0001\u00020\u0002\u0001}+A!Z\u0001\u0001M\"9\u0011qA\u0001\u0005\u0004\u0005%\u0001bBA\u0014\u0003\u0011\u0005\u0011\u0011\u0006\u0005\b\u0003\u007f\tA\u0011AA!\u0011\u001d\t)&\u0001C\u0002\u0003/B\u0011\"a\u000f\u0002\u0005\u0004%\t!!\u0018\t\u0011\u0005}\u0013\u0001)A\u0005\u0003[A\u0011\"!\u0019\u0002\u0005\u0004%\t!!\u0018\t\u0011\u0005\r\u0014\u0001)A\u0005\u0003[A!\"!\u001a\u0002\u0011\u000b\u0007I\u0011AA4\u0011%\tY'\u0001b\u0001\n\u0003\ti\u0007\u0003\u0005\u0002r\u0005\u0001\u000b\u0011BA8\u0011%\t\u0019(\u0001b\u0001\n\u0003\t)\b\u0003\u0005\u0002z\u0005\u0001\u000b\u0011BA<\u0011%\tY(\u0001b\u0001\n\u0003\t)\b\u0003\u0005\u0002~\u0005\u0001\u000b\u0011BA<\u0011%\ty(\u0001b\u0001\n\u0003\t\t\t\u0003\u0005\u0002\u0014\u0006\u0001\u000b\u0011BAB\u0011\u001d\t)*\u0001C\u0001\u0003/C!\"a)\u0002\u0011\u000b\u0007I\u0011AAS\u0011)\tI+\u0001EC\u0002\u0013\u0005\u00111\u0016\u0005\u000b\u0003k\u000b\u0001R1A\u0005\u0002\u0005]\u0006BCAa\u0003!\u0015\r\u0011\"\u0001\u0002h!I\u00111Y\u0001C\u0002\u0013\u0005\u0011Q\u0019\u0005\t\u0003\u001f\f\u0001\u0015!\u0003\u0002H\"9\u0011\u0011[\u0001\u0005\u0002\u0005M\u0007bBAl\u0003\u0011\u0005\u0011\u0011\u001c\u0005\n\u0003;\f!\u0019!C\u0001\u0003?D\u0001\"!;\u0002A\u0003%\u0011\u0011\u001d\u0005\n\u0003W\f!\u0019!C\u0001\u0003[D\u0001\"a?\u0002A\u0003%\u0011q\u001e\u0005\n\u0003{\f!\u0019!C\u0001\u0003\u007fD\u0001B!\u0004\u0002A\u0003%!\u0011\u0001\u0005\n\u0005\u001f\t!\u0019!C\u0001\u0005#A\u0001Ba\b\u0002A\u0003%!1\u0003\u0005\n\u0005C\t!\u0019!C\u0001\u0005GA\u0001B!\r\u0002A\u0003%!Q\u0005\u0005\n\u0005g\t!\u0019!C\u0001\u0005kA\u0001Ba\u0011\u0002A\u0003%!q\u0007\u0005\n\u0005\u000b\n!\u0019!C\u0001\u0005\u000fB\u0001B!\u0016\u0002A\u0003%!\u0011\n\u0005\n\u0005/\n!\u0019!C\u0001\u00053B\u0001Ba\u0018\u0002A\u0003%!1\f\u0005\u000b\u0005C\n\u0001R1A\u0005\u0002\u0005-\u0006\"\u0003B2\u0003\t\u0007I\u0011\u0001B3\u0011!\u0011I'\u0001Q\u0001\n\t\u001d\u0004\"\u0003B6\u0003\t\u0007I\u0011AA7\u0011!\u0011i'\u0001Q\u0001\n\u0005=\u0004\"\u0003B8\u0003\t\u0007I\u0011\u0001B9\u0011!\u0011I(\u0001Q\u0001\n\tM\u0004\"\u0003B>\u0003\t\u0007I\u0011\u0001B9\u0011!\u0011i(\u0001Q\u0001\n\tM\u0004B\u0003B@\u0003!\u0015\r\u0011\"\u0001\u00028\"Q!\u0011Q\u0001\t\u0006\u0004%\t!a\u001a\t\u0015\t\r\u0015\u0001#b\u0001\n\u0003\u0011)\t\u0003\u0006\u0003\u0010\u0006A)\u0019!C\u0001\u0005#C!Ba'\u0002\u0011\u000b\u0007I\u0011\u0001BO\u0011)\u00119+\u0001EC\u0002\u0013\u0005!\u0011\u0016\u0005\u000b\u0005W\u000b\u0001R1A\u0005\u0002\t5\u0006b\u0002BX\u0003\u0011\u0005!\u0011\u0017\u0005\b\u0005w\u000bA\u0011\u0001B_\u0003Q\u00196-\u00197b'&<WI\u001c;ssB\u000b'o]3sg*\u00111\tR\u0001\tg\u000e\fG.Y:jO*\u0011QIR\u0001\u0007g\u000e\fG.\u00199\u000b\u0005\u001dC\u0015A\u00026t_:$4OC\u0001J\u0003\ry'oZ\u0002\u0001!\ta\u0015!D\u0001C\u0005Q\u00196-\u00197b'&<WI\u001c;ssB\u000b'o]3sgN!\u0011aT+Z!\t\u00016+D\u0001R\u0015\u0005\u0011\u0016!B:dC2\f\u0017B\u0001+R\u0005\u0019\te.\u001f*fMB\u0011akV\u0007\u0002\t&\u0011\u0001\f\u0012\u0002\u000f%VdWm],ji\"\u001cF/\u0019;f!\t1&,\u0003\u0002\\\t\nyQ*Z7pSN\f'\r\\3Sk2,7/\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\n\t1\u000b\u0005\u0002aGB\u0011A*Y\u0005\u0003E\n\u0013\u0001bU2bY\u0006\u001c\u0016nZ\u0005\u0003I\u0006\u0014Q!\u00128uef\u00141\"\u00128uef\u0004\u0016M]:feV\u0011qm\u001c\t\u0005Q&l\u00070D\u0001\u0002\u0013\tQ7N\u0001\u0003Sk2,\u0017B\u00017E\u0005)\u0019F/\u0019;f%VdWm\u001d\t\u0003]>d\u0001\u0001B\u0003q\t\t\u0007\u0011OA\u0001B#\t\u0011X\u000f\u0005\u0002Qg&\u0011A/\u0015\u0002\b\u001d>$\b.\u001b8h!\t\u0001f/\u0003\u0002x#\n\u0019\u0011I\\=\u0011\u0007e\f\tA\u0004\u0002{}B\u001110U\u0007\u0002y*\u0011QPS\u0001\u0007yI|w\u000e\u001e \n\u0005}\f\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0004\u0005\u0015!AB*ue&twM\u0003\u0002\u0000#\u0006\u0019\"-\u001f;f\u0007>$W-\u00128uef\u0004\u0016M]:feV!\u00111BA\t)\u0011\ti!a\u0005\u0011\t!$\u0011q\u0002\t\u0004]\u0006EA!\u00029\u0006\u0005\u0004\t\bbBA\u000b\u000b\u0001\u0007\u0011qC\u0001\u0005eVdW\r\u0005\u0004\u0002\u001a\u0005}\u0011q\u0002\b\u0004\u0019\u0006m\u0011bAA\u000f\u0005\u0006A2kY1mCNKw-\u0011;ue&\u0014W\u000f^3QCJ\u001cXM]:\n\t\u0005\u0005\u00121\u0005\u0002\u0007!\u0006\u00148/\u001a:\n\u0007\u0005\u0015\"I\u0001\bCsR,7i\u001c3f%\u0016\fG-\u001a:\u0002\u000fQ|WI\u001c;ssV!\u00111FA\u001f)\u0011\ti#!\u000f\u0011\u0015Y\u000by#!\r\u00022\u0005M\"/\u0003\u0002k\tB\u0011\u0001n\u0001\t\u0004!\u0006U\u0012bAA\u001c#\n\u0019\u0011J\u001c;\t\u000f\u0005mb\u00011\u0001\u00024\u0005)\u0011N\u001c3fq\u0012)\u0001O\u0002b\u0001c\u0006Q\u0001/\u0019:tK\u0016sGO]=\u0016\t\u0005\r\u00131\n\u000b\u0005\u0003\u000b\ny\u0005\u0006\u0003\u0002H\u00055\u0003C\u0003,\u00020\u0005E\u0012\u0011GA%qB\u0019a.a\u0013\u0005\u000bA<!\u0019A9\t\u000f\u0005mr\u00011\u0001\u00024!9\u0011\u0011K\u0004A\u0002\u0005M\u0013A\u00029beN,'\u000f\u0005\u0003i\t\u0005%\u0013!C3oiJLH+\u001f9f)\u0011\ti#!\u0017\t\u000f\u0005m\u0003\u00021\u0001\u00024\u0005!1m\u001c3f+\t\ti#\u0001\u0004j]\u0012,\u0007\u0010I\u0001\u0004W\u0016L\u0018\u0001B6fs\u0002\nQ!\u001a8uef,\"!!\u001b\u0011\u0007!$Q/A\u0002sK\u001a,\"!a\u001c\u0011\t!$\u00111G\u0001\u0005e\u00164\u0007%\u0001\u0005uKJlg*Y7f+\t\t9\bE\u0005W\u0003_\t\t$!\ryq\u0006IA/\u001a:n\u001d\u0006lW\rI\u0001\tif\u0004XMT1nK\u0006IA/\u001f9f\u001d\u0006lW\rI\u0001\u0005]\u0006lW-\u0006\u0002\u0002\u0004J1\u0011QQA<\u0003\u001b3a!a\"\u0001\u0001\u0005\r%\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0014bAAF\t\n)!+\u001e7fgB\u0019a+a$\n\u0007\u0005EEI\u0001\u0003OC6,\u0017!\u00028b[\u0016\u0004\u0013!\u0002:fMR{W\u0003BAM\u0003?#B!a'\u0002\"B!\u0001\u000eBAO!\rq\u0017q\u0014\u0003\u0006aZ\u0011\r!\u001d\u0005\b\u0003+1\u0002\u0019AAN\u0003\u001dq\u0017-\\3SK\u001a,\"!a*\u0011\u0007!$\u00010A\u0005ts6\u0014w\u000e\u001c*fMV\u0011\u0011Q\u0016\t\u0005Q\u0012\ty\u000bE\u0002M\u0003cK1!a-C\u0005\u0019\u0019\u00160\u001c2pY\u00069A/\u001f9f%\u00164WCAA]!\u0011AG!a/\u0011\u00071\u000bi,C\u0002\u0002@\n\u0013A\u0001V=qK\u0006Y1m\u001c8ti\u0006tGOU3g\u0003)\u0019\u00180\u001c2pY&sgm\\\u000b\u0003\u0003\u000f\u0004\"BVA\u0018\u0003c\t\t$!3y!\ra\u00151Z\u0005\u0004\u0003\u001b\u0014%AC*z[\n|G.\u00138g_\u0006Y1/_7c_2LeNZ8!\u0003%\u0019\u00180\u001c%fC\u0012,'\u000f\u0006\u0003\u0002j\u0005U\u0007bBA1;\u0001\u0007\u00111G\u0001\fgfl'm\u001c7F]R\u0014\u0018\u0010\u0006\u0003\u0002H\u0006m\u0007bBA1=\u0001\u0007\u00111G\u0001\t]>\u001c\u00160\u001c2pYV\u0011\u0011\u0011\u001d\t\u000b-\u0006=\u0012\u0011GA\u0019\u0003G\u0014hb\u0001'\u0002f&\u0019\u0011q\u001d\"\u0002\u00119{7+_7c_2\f\u0011B\\8Ts6\u0014w\u000e\u001c\u0011\u0002\u0015QL\b/Z*z[\n|G.\u0006\u0002\u0002pJ1\u0011\u0011_Az\u0003\u001b3a!a\"\u0001\u0001\u0005=\bC\u0003,\u00020\u0005E\u0012\u0011GA{qB\u0019A*a>\n\u0007\u0005e(I\u0001\u0006UsB,7+_7c_2\f1\u0002^=qKNKXNY8mA\u0005Y\u0011\r\\5bgNKXNY8m+\t\u0011\tA\u0005\u0004\u0003\u0004\t\u0015\u0011Q\u0012\u0004\u0007\u0003\u000f\u0003\u0001A!\u0001\u0011\u0015Y\u000by#!\r\u00022\t\u001d\u0001\u0010E\u0002M\u0005\u0013I1Aa\u0003C\u0005-\tE.[1t'fl'm\u001c7\u0002\u0019\u0005d\u0017.Y:Ts6\u0014w\u000e\u001c\u0011\u0002\u0017\rd\u0017m]:Ts6\u0014w\u000e\\\u000b\u0003\u0005'\u0011bA!\u0006\u0003\u0018\u00055eABAD\u0001\u0001\u0011\u0019\u0002\u0005\u0006W\u0003_\t\t$!\r\u0003\u001aa\u00042\u0001\u0014B\u000e\u0013\r\u0011iB\u0011\u0002\f\u00072\f7o]*z[\n|G.\u0001\u0007dY\u0006\u001c8oU=nE>d\u0007%\u0001\u0007pE*,7\r^*z[\n|G.\u0006\u0002\u0003&I1!q\u0005B\u0015\u0003\u001b3a!a\"\u0001\u0001\t\u0015\u0002C\u0003,\u00020\u0005E\u0012\u0011\u0007B\u0016qB\u0019AJ!\f\n\u0007\t=\"I\u0001\u0007PE*,7\r^*z[\n|G.A\u0007pE*,7\r^*z[\n|G\u000eI\u0001\r[\u0016$\bn\u001c3Ts6\u0014w\u000e\\\u000b\u0003\u0005o\u0011bA!\u000f\u0003<\u00055eABAD\u0001\u0001\u00119\u0004\u0005\u0006W\u0003_\t\t$!\r\u0003>a\u00042\u0001\u0014B \u0013\r\u0011\tE\u0011\u0002\r\u001b\u0016$\bn\u001c3Ts6\u0014w\u000e\\\u0001\u000e[\u0016$\bn\u001c3Ts6\u0014w\u000e\u001c\u0011\u0002\r\u0015DHOU3g+\t\u0011IE\u0005\u0004\u0003L\t5\u0013Q\u0012\u0004\u0007\u0003\u000f\u0003\u0001A!\u0013\u0011\u0015Y\u000by#!\r\u00022\t=\u0003\u0010E\u0002M\u0005#J1Aa\u0015C\u00059)\u0005\u0010^3s]\u0006d7+_7c_2\fq!\u001a=u%\u00164\u0007%\u0001\bfqRlu\u000eZ\"mCN\u001c(+\u001a4\u0016\u0005\tm#C\u0002B/\u0005\u001b\niI\u0002\u0004\u0002\b\u0002\u0001!1L\u0001\u0010Kb$Xj\u001c3DY\u0006\u001c8OU3gA\u000511/_7c_2\f1b\u00197bgN\u001c\u00160\u001c*fMV\u0011!q\r\t\u0005Q\u0012\u0011I\"\u0001\u0007dY\u0006\u001c8oU=n%\u00164\u0007%A\u0007biR\u0014\u0018N\u0019+sK\u0016\u0014VMZ\u0001\u000fCR$(/\u001b2Ue\u0016,'+\u001a4!\u0003%!\u0018\u0010]3MKZ,G.\u0006\u0002\u0003tAQa+a\f\u0003v\tU\u00141\u0007:\u0011\t\u0005e!qO\u0005\u0004=\u0006\r\u0012A\u0003;za\u0016dUM^3mA\u0005IA/\u001f9f\u0013:$W\r_\u0001\u000bif\u0004X-\u00138eKb\u0004\u0013!\u0003;za\u0016,e\u000e\u001e:z\u0003\u001da\u0017\u000e^3sC2\fQ\"\u0019;ue&\u0014W\u000f^3J]\u001a|WC\u0001BD!)1\u0016qFA\u0019\u0003c\u0011I\t\u001f\t\u0004\u0019\n-\u0015b\u0001BG\u0005\ni\u0011\t\u001e;sS\n,H/Z%oM>\f\u0001b\u00195jY\u0012\u0014XM\\\u000b\u0003\u0005'\u0003\"BVA\u0018\u0003c\t\tD!&y!\ra%qS\u0005\u0004\u00053\u0013%\u0001C\"iS2$'/\u001a8\u0002\u0013\u0005tgn\u001c;J]\u001a|WC\u0001BP!)1\u0016qFA\u0019\u0003c\u0011\t\u000b\u001f\t\u0004\u0019\n\r\u0016b\u0001BS\u0005\nI\u0011I\u001c8pi&sgm\\\u0001\u000ei>\u0004H*\u001a<fY\u000ec\u0017m]:\u0016\u0005\t]\u0011A\u0004;pa2+g/\u001a7PE*,7\r^\u000b\u0003\u0005S\t!\"[:U_BdUM^3m)\u0011\u0011\u0019L!/\u0011\u0007A\u0013),C\u0002\u00038F\u0013qAQ8pY\u0016\fg\u000eC\u0004\u0003b}\u0002\r!a,\u0002\u001f%\u001cHk\u001c9MKZ,Gn\u00117bgN$BAa-\u0003@\"9!\u0011\r!A\u0002\u0005=\u0006"
)
public final class ScalaSigEntryParsers {
   public static boolean isTopLevelClass(final Symbol symbol) {
      return ScalaSigEntryParsers$.MODULE$.isTopLevelClass(symbol);
   }

   public static boolean isTopLevel(final Symbol symbol) {
      return ScalaSigEntryParsers$.MODULE$.isTopLevel(symbol);
   }

   public static Rule topLevelObject() {
      return ScalaSigEntryParsers$.MODULE$.topLevelObject();
   }

   public static Rule topLevelClass() {
      return ScalaSigEntryParsers$.MODULE$.topLevelClass();
   }

   public static Rule annotInfo() {
      return ScalaSigEntryParsers$.MODULE$.annotInfo();
   }

   public static Rule children() {
      return ScalaSigEntryParsers$.MODULE$.children();
   }

   public static Rule attributeInfo() {
      return ScalaSigEntryParsers$.MODULE$.attributeInfo();
   }

   public static Rule literal() {
      return ScalaSigEntryParsers$.MODULE$.literal();
   }

   public static Rule typeEntry() {
      return ScalaSigEntryParsers$.MODULE$.typeEntry();
   }

   public static Rule typeIndex() {
      return ScalaSigEntryParsers$.MODULE$.typeIndex();
   }

   public static Rule typeLevel() {
      return ScalaSigEntryParsers$.MODULE$.typeLevel();
   }

   public static Rule attribTreeRef() {
      return ScalaSigEntryParsers$.MODULE$.attribTreeRef();
   }

   public static Rule classSymRef() {
      return ScalaSigEntryParsers$.MODULE$.classSymRef();
   }

   public static Rule symbol() {
      return ScalaSigEntryParsers$.MODULE$.symbol();
   }

   public static Rule extModClassRef() {
      return ScalaSigEntryParsers$.MODULE$.extModClassRef();
   }

   public static Rule extRef() {
      return ScalaSigEntryParsers$.MODULE$.extRef();
   }

   public static Rule methodSymbol() {
      return ScalaSigEntryParsers$.MODULE$.methodSymbol();
   }

   public static Rule objectSymbol() {
      return ScalaSigEntryParsers$.MODULE$.objectSymbol();
   }

   public static Rule classSymbol() {
      return ScalaSigEntryParsers$.MODULE$.classSymbol();
   }

   public static Rule aliasSymbol() {
      return ScalaSigEntryParsers$.MODULE$.aliasSymbol();
   }

   public static Rule typeSymbol() {
      return ScalaSigEntryParsers$.MODULE$.typeSymbol();
   }

   public static Rule noSymbol() {
      return ScalaSigEntryParsers$.MODULE$.noSymbol();
   }

   public static Rule symbolEntry(final int key) {
      return ScalaSigEntryParsers$.MODULE$.symbolEntry(key);
   }

   public static Rule symHeader(final int key) {
      return ScalaSigEntryParsers$.MODULE$.symHeader(key);
   }

   public static Rule symbolInfo() {
      return ScalaSigEntryParsers$.MODULE$.symbolInfo();
   }

   public static Rule constantRef() {
      return ScalaSigEntryParsers$.MODULE$.constantRef();
   }

   public static Rule typeRef() {
      return ScalaSigEntryParsers$.MODULE$.typeRef();
   }

   public static Rule symbolRef() {
      return ScalaSigEntryParsers$.MODULE$.symbolRef();
   }

   public static Rule nameRef() {
      return ScalaSigEntryParsers$.MODULE$.nameRef();
   }

   public static Rule refTo(final Rule rule) {
      return ScalaSigEntryParsers$.MODULE$.refTo(rule);
   }

   public static Rule name() {
      return ScalaSigEntryParsers$.MODULE$.name();
   }

   public static Rule typeName() {
      return ScalaSigEntryParsers$.MODULE$.typeName();
   }

   public static Rule termName() {
      return ScalaSigEntryParsers$.MODULE$.termName();
   }

   public static Rule ref() {
      return ScalaSigEntryParsers$.MODULE$.ref();
   }

   public static Rule entry() {
      return ScalaSigEntryParsers$.MODULE$.entry();
   }

   public static Rule key() {
      return ScalaSigEntryParsers$.MODULE$.key();
   }

   public static Rule index() {
      return ScalaSigEntryParsers$.MODULE$.index();
   }

   public static Rule entryType(final int code) {
      return ScalaSigEntryParsers$.MODULE$.entryType(code);
   }

   public static Rule parseEntry(final Rule parser, final int index) {
      return ScalaSigEntryParsers$.MODULE$.parseEntry(parser, index);
   }

   public static Rule toEntry(final int index) {
      return ScalaSigEntryParsers$.MODULE$.toEntry(index);
   }

   public static Rule byteCodeEntryParser(final Rule rule) {
      return ScalaSigEntryParsers$.MODULE$.byteCodeEntryParser(rule);
   }

   public static Rule ruleWithName(final String name, final Function1 f) {
      return ScalaSigEntryParsers$.MODULE$.ruleWithName(name, f);
   }

   public static Rule memo(final Object key, final Function0 toRule) {
      return ScalaSigEntryParsers$.MODULE$.memo(key, toRule);
   }

   public static RulesWithState factory() {
      return ScalaSigEntryParsers$.MODULE$.factory();
   }

   public static Rule repeatUntil(final Rule rule, final Function1 finished, final Object initial) {
      return ScalaSigEntryParsers$.MODULE$.repeatUntil(rule, finished, initial);
   }

   public static Rule anyOf(final Seq rules) {
      return ScalaSigEntryParsers$.MODULE$.anyOf(rules);
   }

   public static Function1 allOf(final Seq rules) {
      return ScalaSigEntryParsers$.MODULE$.allOf(rules);
   }

   public static Rule cond(final Function1 f) {
      return ScalaSigEntryParsers$.MODULE$.cond(f);
   }

   public static Rule none() {
      return ScalaSigEntryParsers$.MODULE$.none();
   }

   public static Rule nil() {
      return ScalaSigEntryParsers$.MODULE$.nil();
   }

   public static Rule update(final Function1 f) {
      return ScalaSigEntryParsers$.MODULE$.update(f);
   }

   public static Rule set(final Function0 s) {
      return ScalaSigEntryParsers$.MODULE$.set(s);
   }

   public static Rule get() {
      return ScalaSigEntryParsers$.MODULE$.get();
   }

   public static Rule read(final Function1 f) {
      return ScalaSigEntryParsers$.MODULE$.read(f);
   }

   public static Rule unit(final Function0 a) {
      return ScalaSigEntryParsers$.MODULE$.unit(a);
   }

   public static Rule apply(final Function1 f) {
      return ScalaSigEntryParsers$.MODULE$.apply(f);
   }

   public static Function1 expect(final Rule rule) {
      return ScalaSigEntryParsers$.MODULE$.expect(rule);
   }

   public static Rule oneOf(final Seq rules) {
      return ScalaSigEntryParsers$.MODULE$.oneOf(rules);
   }

   public static Rule error(final Object err) {
      return ScalaSigEntryParsers$.MODULE$.error(err);
   }

   public static Rule error() {
      return ScalaSigEntryParsers$.MODULE$.error();
   }

   public static Rule failure() {
      return ScalaSigEntryParsers$.MODULE$.failure();
   }

   public static Rule success(final Object out, final Object a) {
      return ScalaSigEntryParsers$.MODULE$.success(out, a);
   }

   public static StateRules state() {
      return ScalaSigEntryParsers$.MODULE$.state();
   }

   public static Rules.FromRule from() {
      return ScalaSigEntryParsers$.MODULE$.from();
   }

   public static SeqRule seqRule(final Rule rule) {
      return ScalaSigEntryParsers$.MODULE$.seqRule(rule);
   }

   public static InRule inRule(final Rule rule) {
      return ScalaSigEntryParsers$.MODULE$.inRule(rule);
   }

   public static Rule rule(final Function1 f) {
      return ScalaSigEntryParsers$.MODULE$.rule(f);
   }
}
