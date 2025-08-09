package scala.reflect.macros;

import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.api.Scopes;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.internal.util.SourceFile;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015=hAB?\u007f\u0003\u0003\tY\u0001C\u0004\u0002\u0018\u0001!\t!!\u0007\u0005\u000f\u0005}\u0001A!\u0011\u0002\"\u0019I\u0011q\u0006\u0001\u0011\u0002\u0007\u0005\u0011\u0011\u0007\u0005\b\u0003\u0007\u001aA\u0011AA#\u0011\u001d\tie\u0001D\u0001\u0003\u001fBq!!\u001d\u0004\r\u0003\t\u0019\bC\u0004\u0002|\r1\t!! \t\u0013\u0005]5A1A\u0007\u0002\u0005e\u0005b\u0002BD\u0007\u0019\u0005!\u0011\u0012\u0005\b\u0005S\u001ba\u0011\u0001BV\u0011\u001d\u0011\u0019n\u0001D\u0001\u0005+DqA!;\u0004\r\u0003\u0011Y\u000fC\u0004\u0003v\u000e1\tAa>\t\u000f\t}8A\"\u0001\u0004\u0002!911D\u0002\u0007\u0002\ru\u0001bBB\u0014\u0007\u0019\u00051\u0011\u0006\u0005\b\u0007c\u0019a\u0011AB\u001a\u0011\u001d\u00199e\u0001D\u0001\u0007\u0013BqAa\"\u0004\r\u0003\u0019\t\u0006C\u0004\u0003*\u000e1\taa\u0017\t\u000f\tM7A\"\u0001\u0004r!91QQ\u0002\u0007\u0002\r\u001d\u0005bBBI\u0007\u0019\u000511\u0013\u0005\b\u00077\u001ba\u0011ABO\u0011\u001d\u0019)k\u0001D\u0001\u0007OCqaa,\u0004\r\u0003\u0019\t\fC\u0004\u0004B\u000e1\taa1\t\u000f\r%7A\"\u0001\u0004L\"91qZ\u0002\u0007\u0002\rE\u0007bBBk\u0007\u0019\u00051q\u001b\u0003\b\u0007C\u001c!\u0011IBr\r%\u0019Io\u0001I\u0001\u0004\u0003\u0019Y\u000fC\u0004\u0002D\u0001\"\t!!\u0012\u0005\u000f\rM\bE!\u0001\u0004v\"9Aq\u0004\u0011\u0007\u0004\u0011\u0005bABB\u007fA\u0001\u0019y\u0010\u0003\u0006\u0002X\u0011\u0012)\u0019!C\u0001\t\u0007A!\u0002b\u0003%\u0005\u0003\u0005\u000b\u0011\u0002C\u0003\u0011\u001d\t9\u0002\nC\u0001\t\u001bAq!!\u0014%\t\u0003!\u0019\u0002C\u0004\u0002r\u0011\"\t\u0001b\u0006\u0005\u000f\u00115\u0002E!\u0011\u00050\u00191AQ\u0007\u0011\u0001\toA!\"a!,\u0005\u000b\u0007I\u0011\tC$\u00111!Ie\u000bB\u0001B\u0003%A\u0011\tC&\u0011\u001d\t9b\u000bC\u0001\t\u001bBq!a\u001f,\t\u0003!\u0019\u0006C\u0004\u0003\b.\"\t\u0001\"\u0018\t\u000f\t%6\u0006\"\u0001\u0005f!9!1[\u0016\u0005\u0002\u0011e\u0004bBBCW\u0011\u0005Aq\u0011\u0005\b\u0007#[C\u0011\u0001CF\u0011\u001d\u0019Yj\u000bC\u0001\t\u001fCqa!*,\t\u0003!\u0019\nB\u0004\u0005\u001c\u0002\u0012\t\u0001\"(\t\u000f\u0011\u0005\u0007Eb\u0001\u0005D\u001a1A1\u0015\u0011\u0001\tKC!ba/:\u0005\u000b\u0007I\u0011\u0001CU\u0011)!\t,\u000fB\u0001B\u0003%A1\u0016\u0005\b\u0003/ID\u0011\u0001CZ\u0011\u001d\u0019y+\u000fC\u0001\ts#q\u0001b4!\u0005\u0003\"\tN\u0002\u0004\u0005X\u0002\u0002A\u0011\u001c\u0005\u000b\u0005O{$Q1A\u0005B\u0011%\b\u0002\u0004Cv\u007f\t\u0005\t\u0015!\u0003\u0005d\u00125\bbBA\f\u007f\u0011\u0005Aq\u001e\u0005\b\u0005\u000f{D\u0011\u0001C{\u0011\u001d\u0011Ik\u0010C\u0001\t{DqAa5@\t\u0003)y\u0001C\u0004\u0003j~\"\t!\"\b\t\u000f\tUx\b\"\u0001\u0006\"!9!q` \u0005\u0002\u0015\u0015\u0002bBB\u000e\u007f\u0011\u0005Q\u0011\u0006\u0005\b\u0007OyD\u0011AC\u0017\u0011\u001d\u0019\td\u0010C\u0001\u000bcAqaa\u0012@\t\u0003))DB\u0005\u0002\u001e\u0002\u0001\n1%\u0001\u0002 \"9\u0011\u0011U'\u0007\u0002\u0005\r\u0006bBAQ\u001b\u001a\u0005\u00111\u0017\u0005\b\u0003wke\u0011AA_\u0011\u001d\tY,\u0014D\u0001\u0003\u0017Dq!a4N\r\u0003\t\t\u000eC\u0004\u0002V63\t!a6\t\u000f\u0005UWJ\"\u0001\u0002^\"9\u0011\u0011]'\u0007\u0002\u0005\r\bbBAq\u001b\u001a\u0005\u0011q\u001d\u0005\b\u0003ole\u0011AA}\u0011\u001d\u0011\u0019!\u0014D\u0001\u0005\u000bAqA!\u0003N\r\u0003\u0011Y\u0001C\u0004\u0003\u001453\tA!\u0006\t\u000f\tMQJ\"\u0001\u00038!9!1C'\u0007\u0002\t\u0005\u0003b\u0002B\n\u001b\u001a\u0005!q\t\u0005\b\u0005'ie\u0011\u0001B(\u0011\u001d\u0011\u0019\"\u0014D\u0001\u0005/BqAa\u0005N\r\u0003\u0011\t\u0007C\u0004\u0003j53\tAa\u001b\t\u000f\tETJ\"\u0001\u0003t!9!QO'\u0007\u0002\t]\u0004b\u0002B?\u001b\u001a\u0005!q\u0010\u0005\n\u000b{\u0001!\u0019!D\u0001\u00033#q!b\u0015\u0001\u0005\u0003))FB\u0005\u0006Z\u0001\u0001\n1!\u0001\u0006\\!9\u00111I4\u0005\u0002\u0005\u0015cABC2O\u0006))\u0007\u0003\u0006\u0003(&\u0014\t\u0011)A\u0005\u0003OBq!a\u0006j\t\u0003)9\u0007C\u0005\u0006p\u001d\f\t\u0011b\u0001\u0006r\u00191QQO4\u0002\u000boB!\"a!n\u0005\u0003\u0005\u000b\u0011BAC\u0011\u001d\t9\"\u001cC\u0001\u000bsB\u0011\"b h\u0003\u0003%\u0019!\"!\u0007\r\u0015\u0015u-ACD\u0011)\u0019Y,\u001dB\u0001B\u0003%11\u0017\u0005\b\u0003/\tH\u0011ACE\u0011%)yiZA\u0001\n\u0007)\t\nB\u0004\u0006\"\u0002\u0011\t!b)\u0007\u0013\u0015\u001d\u0006\u0001%A\u0012\u0002\u0015%\u0006bBCVm\u001a\u0005QQ\u0016\u0005\b\u000b?4h\u0011ACq\t\u001d)\t\f\u0001B\u0001\u000bg3\u0011\"b.\u0001!\u0003\r\n!\"/\t\u000f\u0015m&P\"\u0001\u0006>\"9QQ\u001b>\u0007\u0002\tM$\u0001C+oSZ,'o]3\u000b\u0007}\f\t!\u0001\u0004nC\u000e\u0014xn\u001d\u0006\u0005\u0003\u0007\t)!A\u0004sK\u001adWm\u0019;\u000b\u0005\u0005\u001d\u0011!B:dC2\f7\u0001A\n\u0004\u0001\u00055\u0001\u0003BA\b\u0003+i!!!\u0005\u000b\t\u0005M\u0011\u0011A\u0001\u0004CBL\u0017bA?\u0002\u0012\u00051A(\u001b8jiz\"\"!a\u0007\u0011\u0007\u0005u\u0001!D\u0001\u007f\u0005!Ie\u000e^3s]\u0006d\u0017\u0003BA\u0012\u0003W\u0001B!!\n\u0002(5\u0011\u0011QA\u0005\u0005\u0003S\t)AA\u0004O_RD\u0017N\\4\u0011\u0007\u000552!D\u0001\u0001\u0005Ai\u0015m\u0019:p\u0013:$XM\u001d8bY\u0006\u0003\u0018nE\u0003\u0004\u0003g\tI\u0004\u0005\u0003\u0002&\u0005U\u0012\u0002BA\u001c\u0003\u000b\u0011a!\u00118z%\u00164\u0007\u0003BA\u0017\u0003wIA!!\u0010\u0002@\tY\u0011J\u001c;fe:\fG.\u00119j\u0013\u0011\t\t%!\u0005\u0003\u0013%sG/\u001a:oC2\u001c\u0018A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002HA!\u0011QEA%\u0013\u0011\tY%!\u0002\u0003\tUs\u0017\u000e^\u0001\u0006K:$XM\u001d\u000b\u0007\u0003#\n)&a\u0019\u000f\t\u0005M\u0013Q\u000b\u0007\u0001\u0011\u001d\t9&\u0002a\u0001\u00033\nQa]2pa\u0016\u0004B!!\f\u0002\\%!\u0011QLA0\u0005\u0015\u00196m\u001c9f\u0013\u0011\t\t'!\u0005\u0003\rM\u001bw\u000e]3t\u0011\u001d\t)'\u0002a\u0001\u0003O\n1a]=n!\u0011\ti#!\u001b\n\t\u0005-\u0014Q\u000e\u0002\u0007'fl'm\u001c7\n\t\u0005=\u0014\u0011\u0003\u0002\b'fl'm\u001c7t\u0003\u0019)h\u000e\\5oWR1\u0011QOA<\u0003srA!a\u0015\u0002x!9\u0011q\u000b\u0004A\u0002\u0005e\u0003bBA3\r\u0001\u0007\u0011qM\u0001\fG\"\fgnZ3Po:,'\u000f\u0006\u0005\u0002\u0000\u0005\u0005\u0015qRAJ\u001d\u0011\t\u0019&!!\t\u000f\u0005\ru\u00011\u0001\u0002\u0006\u0006!AO]3f!\u0011\ti#a\"\n\t\u0005%\u00151\u0012\u0002\u0005)J,W-\u0003\u0003\u0002\u000e\u0006E!!\u0002+sK\u0016\u001c\bbBAI\u000f\u0001\u0007\u0011qM\u0001\u0005aJ,g\u000fC\u0004\u0002\u0016\u001e\u0001\r!a\u001a\u0002\t9,\u0007\u0010^\u0001\u0004O\u0016tWCAAN!\r\ti#\u0014\u0002\b)J,WmR3o'\ri\u00151G\u0001\u0016[.\fE\u000f\u001e:jEV$X\rZ)vC2Lg-[3s)\u0011\t))!*\t\u000f\u0005\u001df\n1\u0001\u0002*\u0006\u0019A\u000f]3\u0011\t\u00055\u00121V\u0005\u0005\u0003[\u000byK\u0001\u0003UsB,\u0017\u0002BAY\u0003#\u0011Q\u0001V=qKN$b!!\"\u00026\u0006]\u0006bBAT\u001f\u0002\u0007\u0011\u0011\u0016\u0005\b\u0003s{\u0005\u0019AA4\u0003\u001d!XM]7Ts6\fq\"\\6BiR\u0014\u0018NY;uK\u0012\u0014VM\u001a\u000b\u0007\u0003\u007f\u000b)-!3\u0011\t\u00055\u0012\u0011Y\u0005\u0005\u0003\u0007\fYIA\u0004SK\u001a$&/Z3\t\u000f\u0005\u001d\u0007\u000b1\u0001\u0002*\u0006\u0019\u0001O]3\t\u000f\u0005\u0015\u0004\u000b1\u0001\u0002hQ!\u0011qXAg\u0011\u001d\t)'\u0015a\u0001\u0003O\n\u0011b\u001d;bE&d\u0017N_3\u0015\t\u0005\u0015\u00151\u001b\u0005\b\u0003\u0007\u0013\u0006\u0019AAC\u0003Ui7.\u0011;ue&\u0014W\u000f^3e'R\f'\r\\3SK\u001a$b!!\"\u0002Z\u0006m\u0007bBAd'\u0002\u0007\u0011\u0011\u0016\u0005\b\u0003K\u001a\u0006\u0019AA4)\u0011\t))a8\t\u000f\u0005\u0015D\u000b1\u0001\u0002h\u0005\tRn[+oCR$(/\u001b2vi\u0016$'+\u001a4\u0015\t\u0005}\u0016Q\u001d\u0005\b\u0003K*\u0006\u0019AA4)\u0011\ty,!;\t\u000f\u0005-h\u000b1\u0001\u0002n\u0006Aa-\u001e7m\u001d\u0006lW\r\u0005\u0003\u0002.\u0005=\u0018\u0002BAy\u0003g\u0014AAT1nK&!\u0011Q_A\t\u0005\u0015q\u0015-\\3t\u0003Ai7.\u0011;ue&\u0014W\u000f^3e)\"L7\u000f\u0006\u0003\u0002|\n\u0005\u0001\u0003BA\u0017\u0003{LA!a@\u0002\f\n!A\u000b[5t\u0011\u001d\t)g\u0016a\u0001\u0003O\n\u0011#\\6BiR\u0014\u0018NY;uK\u0012LE-\u001a8u)\u0011\tyLa\u0002\t\u000f\u0005\u0015\u0004\f1\u0001\u0002h\u0005\u0011Rn[!uiJL'-\u001e;fIN+G.Z2u)\u0019\tyL!\u0004\u0003\u0012!9!qB-A\u0002\u0005\u0015\u0015\u0001B9vC2Dq!!\u001aZ\u0001\u0004\t9'\u0001\u0007nW6+G\u000f[8e\u0007\u0006dG\u000e\u0006\u0006\u0002\u0006\n]!1\u0004B\u0010\u0005cAqA!\u0007[\u0001\u0004\t9'\u0001\u0005sK\u000e,\u0017N^3s\u0011\u001d\u0011iB\u0017a\u0001\u0003[\f!\"\\3uQ>$g*Y7f\u0011\u001d\u0011\tC\u0017a\u0001\u0005G\tQ\u0001^1sON\u0004bA!\n\u0003,\u0005%f\u0002BA\u0013\u0005OIAA!\u000b\u0002\u0006\u00059\u0001/Y2lC\u001e,\u0017\u0002\u0002B\u0017\u0005_\u0011A\u0001T5ti*!!\u0011FA\u0003\u0011\u001d\u0011\u0019D\u0017a\u0001\u0005k\tA!\u0019:hgB1!Q\u0005B\u0016\u0003\u000b#\u0002\"!\"\u0003:\tu\"q\b\u0005\b\u0005wY\u0006\u0019AA4\u0003\u0019iW\r\u001e5pI\"9!\u0011E.A\u0002\t\r\u0002b\u0002B\u001a7\u0002\u0007!Q\u0007\u000b\u0007\u0003\u000b\u0013\u0019E!\u0012\t\u000f\tmB\f1\u0001\u0002h!9!1\u0007/A\u0002\tUBCBAC\u0005\u0013\u0012i\u0005C\u0004\u0003Lu\u0003\r!!\"\u0002\rQ\f'oZ3u\u0011\u001d\u0011\u0019$\u0018a\u0001\u0005k!\u0002\"!\"\u0003R\tM#Q\u000b\u0005\b\u00053q\u0006\u0019AA4\u0011\u001d\u0011iB\u0018a\u0001\u0003[DqAa\r_\u0001\u0004\u0011)\u0004\u0006\u0006\u0002\u0006\ne#1\fB/\u0005?BqA!\u0007`\u0001\u0004\t)\tC\u0004\u0003<}\u0003\r!a\u001a\t\u000f\t\u0005r\f1\u0001\u0003$!9!1G0A\u0002\tUB\u0003CAC\u0005G\u0012)Ga\u001a\t\u000f\t-\u0003\r1\u0001\u0002\u0006\"9!\u0011\u00051A\u0002\t\r\u0002b\u0002B\u001aA\u0002\u0007!QG\u0001\u000e[.tU\u000f\u001c7bef\u001c\u0015\r\u001c7\u0015\r\u0005\u0015%Q\u000eB8\u0011\u001d\u0011Y$\u0019a\u0001\u0003OBqA!\tb\u0001\u0004\u0011\u0019#\u0001\u000bnWJ+h\u000e^5nKVs\u0017N^3sg\u0016\u0014VMZ\u000b\u0003\u0003\u000b\u000ba!\\6[KJ|G\u0003BAC\u0005sBqAa\u001fd\u0001\u0004\tI+\u0001\u0002ua\u00061Qn[\"bgR$b!!\"\u0003\u0002\n\r\u0005bBABI\u0002\u0007\u0011Q\u0011\u0005\b\u0005\u000b#\u0007\u0019AAU\u0003\t\u0001H/A\u0006biR\f7\r[7f]R\u001cH\u0003\u0002BF\u0005K\u0013BA!$\u0003\u0012\u001a1!qR\u0002\u0001\u0005\u0017\u0013A\u0002\u0010:fM&tW-\\3oiz\u0002B!!\b\u0003\u0014&\u0019!Q\u0013@\u0003\u0017\u0005#H/Y2i[\u0016tGo]\u0003\b\u00053\u0013i\t\tBN\u0005\r\u0001vn\u001d\t\u0005\u0003[\u0011i*\u0003\u0003\u0003 \n\u0005&\u0001\u0003)pg&$\u0018n\u001c8\n\t\t\r\u0016\u0011\u0003\u0002\n!>\u001c\u0018\u000e^5p]NDqAa*\n\u0001\u0004\t9'\u0001\u0004ts6\u0014w\u000e\\\u0001\u0011kB$\u0017\r^3BiR\f7\r[7f]R,BA!,\u0003DR1!q\u0016BZ\u0005\u001f$BA!-\u00036:!\u00111\u000bBZ\u0011\u001d\u00119K\u0003a\u0001\u0003OB\u0011Ba.\u000b\u0003\u0003\u0005\u001dA!/\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0003<\nu&\u0011Y\u0007\u0003\u0003\u0003IAAa0\u0002\u0002\tA1\t\\1tgR\u000bw\r\u0005\u0003\u0002T\t\rGa\u0002Bc\u0015\t\u0007!q\u0019\u0002\u0002)F!\u00111\u0005Be!\u0011\t)Ca3\n\t\t5\u0017Q\u0001\u0002\u0004\u0003:L\bb\u0002Bi\u0015\u0001\u0007!\u0011Y\u0001\u000bCR$\u0018m\u00195nK:$\u0018\u0001\u0005:f[>4X-\u0011;uC\u000eDW.\u001a8u+\u0011\u00119Na:\u0015\t\te'Q\u001c\u000b\u0005\u00057\u0014yN\u0004\u0003\u0002T\tu\u0007b\u0002BT\u0017\u0001\u0007\u0011q\r\u0005\n\u0005C\\\u0011\u0011!a\u0002\u0005G\f!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\u0011YL!0\u0003fB!\u00111\u000bBt\t\u001d\u0011)m\u0003b\u0001\u0005\u000f\f\u0001b]3u\u001f^tWM\u001d\u000b\u0007\u0005[\u0014yO!=\u000f\t\u0005M#q\u001e\u0005\b\u0005Oc\u0001\u0019AA4\u0011\u001d\u0011\u0019\u0010\u0004a\u0001\u0003O\n\u0001B\\3x_^tWM]\u0001\bg\u0016$\u0018J\u001c4p)\u0019\u0011IPa?\u0003~:!\u00111\u000bB~\u0011\u001d\u00119+\u0004a\u0001\u0003OBq!a*\u000e\u0001\u0004\tI+\u0001\btKR\feN\\8uCRLwN\\:\u0015\r\r\r1QAB\u0004\u001d\u0011\t\u0019f!\u0002\t\u000f\t\u001df\u00021\u0001\u0002h!91\u0011\u0002\bA\u0002\r-\u0011AB1o]>$8\u000f\u0005\u0004\u0002&\r51\u0011C\u0005\u0005\u0007\u001f\t)A\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002B!!\f\u0004\u0014%!1QCB\f\u0005)\teN\\8uCRLwN\\\u0005\u0005\u00073\t\tBA\u0006B]:|G/\u0019;j_:\u001c\u0018aB:fi:\u000bW.\u001a\u000b\u0007\u0007?\u0019\tca\t\u000f\t\u0005M3\u0011\u0005\u0005\b\u0005O{\u0001\u0019AA4\u0011\u001d\u0019)c\u0004a\u0001\u0003[\fAA\\1nK\u0006\u00012/\u001a;Qe&4\u0018\r^3XSRD\u0017N\u001c\u000b\u0007\u0007W\u0019ica\f\u000f\t\u0005M3Q\u0006\u0005\b\u0005O\u0003\u0002\u0019AA4\u0011\u001d\t)\u0007\u0005a\u0001\u0003O\nqa]3u\r2\fw\r\u0006\u0004\u00046\r]2\u0011\b\b\u0005\u0003'\u001a9\u0004C\u0004\u0003(F\u0001\r!a\u001a\t\u000f\rm\u0012\u00031\u0001\u0004>\u0005)a\r\\1hgB!\u0011QFB \u0013\u0011\u0019\tea\u0011\u0003\u000f\u0019c\u0017mZ*fi&!1QIA\t\u0005!1E.Y4TKR\u001c\u0018!\u0003:fg\u0016$h\t\\1h)\u0019\u0019Ye!\u0014\u0004P9!\u00111KB'\u0011\u001d\u00119K\u0005a\u0001\u0003OBqaa\u000f\u0013\u0001\u0004\u0019i\u0004\u0006\u0003\u0004T\re#\u0003BB+\u0005#3aAa$\u0004\u0001\rMSa\u0002BM\u0007+\u0002#1\u0014\u0005\b\u0003\u0007\u001b\u0002\u0019AAC+\u0011\u0019if!\u001c\u0015\r\r}31MB8)\u0011\u0019\tg!\u001a\u000f\t\u0005M31\r\u0005\b\u0003\u0007#\u0002\u0019AAC\u0011%\u00199\u0007FA\u0001\u0002\b\u0019I'\u0001\u0006fm&$WM\\2fIM\u0002bAa/\u0003>\u000e-\u0004\u0003BA*\u0007[\"qA!2\u0015\u0005\u0004\u00119\rC\u0004\u0003RR\u0001\raa\u001b\u0016\t\rM41\u0011\u000b\u0005\u0007k\u001aI\b\u0006\u0003\u0004x\rmd\u0002BA*\u0007sBq!a!\u0016\u0001\u0004\t)\tC\u0005\u0004~U\t\t\u0011q\u0001\u0004\u0000\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\r\tm&QXBA!\u0011\t\u0019fa!\u0005\u000f\t\u0015WC1\u0001\u0003H\u000611/\u001a;Q_N$ba!#\u0004\f\u000e5e\u0002BA*\u0007\u0017Cq!a!\u0017\u0001\u0004\t)\tC\u0004\u0004\u0010Z\u0001\rAa'\u0002\r9,w\u000f]8t\u0003\u001d\u0019X\r\u001e+za\u0016$ba!&\u0004\u0018\u000eee\u0002BA*\u0007/Cq!a!\u0018\u0001\u0004\t)\tC\u0004\u0003|]\u0001\r!!+\u0002\u0015\u0011,g-\u001b8f)f\u0004X\r\u0006\u0004\u0004 \u000e\u000561\u0015\b\u0005\u0003'\u001a\t\u000bC\u0004\u0002\u0004b\u0001\r!!\"\t\u000f\tm\u0004\u00041\u0001\u0002*\u0006I1/\u001a;Ts6\u0014w\u000e\u001c\u000b\u0007\u0007S\u001bYk!,\u000f\t\u0005M31\u0016\u0005\b\u0003\u0007K\u0002\u0019AAC\u0011\u001d\t)'\u0007a\u0001\u0003O\n1b]3u\u001fJLw-\u001b8bYR111WB]\u0007{\u0003B!!\f\u00046&!1qWAF\u0005!!\u0016\u0010]3Ue\u0016,\u0007bBB^5\u0001\u000711W\u0001\u0003iRDqaa0\u001b\u0001\u0004\t))\u0001\u0005pe&<\u0017N\\1m\u0003=\u0019\u0017\r\u001d;ve\u00164\u0016M]5bE2,G\u0003BA$\u0007\u000bDqaa2\u001c\u0001\u0004\t9'\u0001\u0003wE2,\u0017!\u0007:fM\u0016\u0014XM\\2f\u0007\u0006\u0004H/\u001e:fIZ\u000b'/[1cY\u0016$B!!\"\u0004N\"91q\u0019\u000fA\u0002\u0005\u001d\u0014\u0001F2baR,(/\u001a3WCJL\u0017M\u00197f)f\u0004X\r\u0006\u0003\u0002*\u000eM\u0007bBBd;\u0001\u0007\u0011qM\u0001\fgV\u0014\u0007/\u0019;uKJt7\u000f\u0006\u0003\u0004Z\u000e}\u0007CBA\u0013\u00077\u0014)$\u0003\u0003\u0004^\u0006\u0015!AB(qi&|g\u000eC\u0004\u0002\u0004z\u0001\r!!\"\u0003\u0015\u0011+7m\u001c:bi>\u00148/\u0005\u0003\u0002$\r\u0015\bcABtA5\t1AA\tNC\u000e\u0014x\u000eR3d_J\fGo\u001c:Ba&\u001cR\u0001IA\u001a\u0007[\u0004Baa:\u0004p&!1\u0011_A\u001e\u00051!UmY8sCR|'/\u00119j\u00059\u00196m\u001c9f\t\u0016\u001cwN]1u_J,Baa>\u0005\u001eE!\u00111EB}!\u0015\u0019Y\u0010\nC\u000e\u001b\u0005\u0001#AF'bGJ|7kY8qK\u0012+7m\u001c:bi>\u0014\u0018\t]5\u0016\t\u0011\u0005AqA\n\u0004I\u0005MRC\u0001C\u0003!\u0011\t\u0019\u0006b\u0002\u0005\u000f\t\u0015GE1\u0001\u0005\nE!\u00111EA-\u0003\u0019\u00198m\u001c9fAQ!Aq\u0002C\t!\u0015\u0019Y\u0010\nC\u0003\u0011\u001d\t9f\na\u0001\t\u000b!B\u0001\"\u0002\u0005\u0016!9\u0011Q\r\u0015A\u0002\u0005\u001dD\u0003\u0002C\u0003\t3Aq!!\u001a*\u0001\u0004\t9\u0007\u0005\u0003\u0002T\u0011uAa\u0002BcE\t\u0007A\u0011B\u0001\u000fg\u000e|\u0007/\u001a#fG>\u0014\u0018\r^8s+\u0011!\u0019\u0003\"\u000b\u0015\t\u0011\u0015B1\u0006\t\u0006\u0007w\u0014Cq\u0005\t\u0005\u0003'\"I\u0003B\u0004\u0003F\u000e\u0012\r\u0001\"\u0003\t\u000f\u0005\r5\u00051\u0001\u0005(\tiAK]3f\t\u0016\u001cwN]1u_J,B\u0001\"\r\u0005\u001aF!\u00111\u0005C\u001a!\u0015\u0019Yp\u000bCL\u0005Ui\u0015m\u0019:p)J,W\rR3d_J\fGo\u001c:Ba&,B\u0001\"\u000f\u0005DM\u00191\u0006b\u000f\u0011\r\rmHQ\bC!\u0013\u0011!yda<\u0003!Q\u0013X-\u001a#fG>\u0014\u0018\r^8s\u0003BL\u0007\u0003BA*\t\u0007\"qA!2,\u0005\u0004!)%\u0005\u0003\u0002$\u0005\u0015UC\u0001C!\u0003\u0015!(/Z3!\u0013\u0011\t\u0019\t\"\u0010\u0015\t\u0011=C\u0011\u000b\t\u0006\u0007w\\C\u0011\t\u0005\b\u0003\u0007s\u0003\u0019\u0001C!)\u0019!)\u0006\"\u0017\u0005\\9\u0019Aq\u000b\u0017\u000e\u0003-Bq!!%0\u0001\u0004\t9\u0007C\u0004\u0002\u0016>\u0002\r!a\u001a\u0016\u0005\u0011}#\u0003\u0002C1\u0005#3aAa$,\u0001\u0011}Sa\u0002BM\tC\u0002#1T\u000b\u0005\tO\"\u0019\b\u0006\u0003\u0005j\u0011]D\u0003\u0002C+\tWB\u0011\u0002\"\u001c2\u0003\u0003\u0005\u001d\u0001b\u001c\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0003<\nuF\u0011\u000f\t\u0005\u0003'\"\u0019\bB\u0004\u0005vE\u0012\rAa2\u0003\u0003\u0005CqA!52\u0001\u0004!\t(\u0006\u0003\u0005|\u0011\u0015E\u0003\u0002C!\t{B\u0011\u0002b 3\u0003\u0003\u0005\u001d\u0001\"!\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007\u0005\u0004\u0003<\nuF1\u0011\t\u0005\u0003'\")\tB\u0004\u0005vI\u0012\rAa2\u0015\t\u0011\u0005C\u0011\u0012\u0005\b\u0007\u001f\u001b\u0004\u0019\u0001BN)\u0011!\t\u0005\"$\t\u000f\tmD\u00071\u0001\u0002*R!A\u0011\tCI\u0011\u001d\u0011Y(\u000ea\u0001\u0003S#B\u0001\"\u0011\u0005\u0016\"9\u0011Q\r\u001cA\u0002\u0005\u001d\u0004\u0003BA*\t3#qA!2+\u0005\u0004!)EA\tUsB,GK]3f\t\u0016\u001cwN]1u_J,B\u0001b(\u0005@F!\u00111\u0005CQ!\u0015\u0019Y0\u000fC_\u0005ei\u0015m\u0019:p)f\u0004X\r\u0016:fK\u0012+7m\u001c:bi>\u0014\u0018\t]5\u0016\t\u0011\u001dFQV\n\u0004s\u0005MRC\u0001CV!\u0011\t\u0019\u0006\",\u0005\u000f\t\u0015\u0017H1\u0001\u00050F!\u00111EBZ\u0003\r!H\u000f\t\u000b\u0005\tk#9\fE\u0003\u0004|f\"Y\u000bC\u0004\u0004<r\u0002\r\u0001b+\u0015\t\rMF1\u0018\u0005\b\u0003\u0007k\u0004\u0019AAC!\u0011\t\u0019\u0006b0\u0005\u000f\t\u0015wG1\u0001\u00050\u0006\tB/\u001f9f)J,W\rR3d_J\fGo\u001c:\u0016\t\u0011\u0015G1\u001a\u000b\u0005\t\u000f$i\rE\u0003\u0004|^\"I\r\u0005\u0003\u0002T\u0011-Ga\u0002Bcq\t\u0007Aq\u0016\u0005\b\u0007wC\u0004\u0019\u0001Ce\u0005=\u0019\u00160\u001c2pY\u0012+7m\u001c:bi>\u0014X\u0003\u0002Cj\u000bw\tB!a\t\u0005VB)11` \u0006:\t9R*Y2s_NKXNY8m\t\u0016\u001cwN]1u_J\f\u0005/[\u000b\u0005\t7$)oE\u0002@\t;\u0004baa?\u0005`\u0012\r\u0018\u0002\u0002Cq\u0007_\u0014!cU=nE>dG)Z2pe\u0006$xN]!qSB!\u00111\u000bCs\t\u001d\u0011)m\u0010b\u0001\tO\fB!a\t\u0002hU\u0011A1]\u0001\bgfl'm\u001c7!\u0013\u0011\u00119\u000bb8\u0015\t\u0011EH1\u001f\t\u0006\u0007w|D1\u001d\u0005\b\u0005O\u0013\u0005\u0019\u0001Cr+\t!9P\u0005\u0003\u0005z\nEeA\u0002BH\u007f\u0001!90B\u0004\u0003\u001a\u0012e\bEa'\u0016\t\u0011}X1\u0002\u000b\u0005\u000b\u0003)i\u0001\u0006\u0003\u0005d\u0016\r\u0001\"CC\u0003\t\u0006\u0005\t9AC\u0004\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0007\u0005w\u0013i,\"\u0003\u0011\t\u0005MS1\u0002\u0003\b\tk\"%\u0019\u0001Bd\u0011\u001d\u0011\t\u000e\u0012a\u0001\u000b\u0013)B!\"\u0005\u0006\u001cQ!A1]C\n\u0011%))\"RA\u0001\u0002\b)9\"\u0001\u0006fm&$WM\\2fIa\u0002bAa/\u0003>\u0016e\u0001\u0003BA*\u000b7!q\u0001\"\u001eF\u0005\u0004\u00119\r\u0006\u0003\u0005d\u0016}\u0001b\u0002Bz\r\u0002\u0007\u0011q\r\u000b\u0005\tG,\u0019\u0003C\u0004\u0002(\u001e\u0003\r!!+\u0015\t\u0011\rXq\u0005\u0005\b\u0007\u0013A\u0005\u0019AB\u0006)\u0011!\u0019/b\u000b\t\u000f\r\u0015\u0012\n1\u0001\u0002nR!A1]C\u0018\u0011\u001d\t)G\u0013a\u0001\u0003O\"B\u0001b9\u00064!911H&A\u0002\ruB\u0003\u0002Cr\u000boAqaa\u000fM\u0001\u0004\u0019i\u0004\u0005\u0003\u0002T\u0015mBa\u0002Bc}\t\u0007Aq]\u0001\niJ,WMQ;jY\u0012D3\"ZC!\u000b\u000f*I%\"\u0014\u0006PA!\u0011QEC\"\u0013\u0011))%!\u0002\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0015-\u0013AG;tK\u0002\u0002\u0017N\u001c;fe:\fGNL4f]\u0002\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017EAC)\u0003\u0019\u0011d&M\u0019/a\t11i\\7qCR\fB!a\t\u0006XA\u0019\u0011QF4\u0003\u001d5\u000b7M]8D_6\u0004\u0018\r^!qSN)q-a\r\u0006^A!\u0011QFC0\u0013\u0011)\t'a\u0010\u0003\u0013\r{W\u000e]1u\u0003BL'!F'bGJ|7i\\7qCRL'\r\\3Ts6\u0014w\u000e\\\n\u0004S\u0006MB\u0003BC5\u000b[\u00022!b\u001bj\u001b\u00059\u0007b\u0002BTW\u0002\u0007\u0011qM\u0001\u0016\u001b\u0006\u001c'o\\\"p[B\fG/\u001b2mKNKXNY8m)\u0011)I'b\u001d\t\u000f\t\u001dF\u000e1\u0001\u0002h\t\u0019R*Y2s_\u000e{W\u000e]1uS\ndW\r\u0016:fKN\u0019Q.a\r\u0015\t\u0015mTQ\u0010\t\u0004\u000bWj\u0007bBAB_\u0002\u0007\u0011QQ\u0001\u0014\u001b\u0006\u001c'o\\\"p[B\fG/\u001b2mKR\u0013X-\u001a\u000b\u0005\u000bw*\u0019\tC\u0004\u0002\u0004B\u0004\r!!\"\u0003%\r{W\u000e]1uS\ndW\rV=qKR\u0013X-Z\n\u0004c\u0006MB\u0003BCF\u000b\u001b\u00032!b\u001br\u0011\u001d\u0019Yl\u001da\u0001\u0007g\u000b!cQ8na\u0006$\u0018N\u00197f)f\u0004X\r\u0016:fKR!Q1RCJ\u0011\u001d\u0019Y\f\u001ea\u0001\u0007gC3bZC!\u000b\u000f*9*\"\u0014\u0006\u001c\u0006\u0012Q\u0011T\u0001\"G>l\u0007/\u0019;jE&d\u0017\u000e^=!o&$\b\u000eI*dC2\f\u0007E\r\u00182a\u0001*u\nT\u0011\u0003\u000b;\u000baA\r\u00182g9\u0002\u0004f\u00034\u0006B\u0015\u001dSqSC'\u000b7\u00131AU;o#\u0011\t\u0019#\"*\u0011\u0007\u00055bOA\u0007Sk:\u001cuN\u001c;fqR\f\u0005/[\n\u0004m\u0006M\u0012aC2veJ,g\u000e^+oSR,\"!b,\u0011\u0007\u00055\u0012PA\bD_6\u0004\u0018\u000e\\1uS>tWK\\5u#\u0011\t\u0019#\".\u0011\u0007\u00055\"PA\rD_6\u0004\u0018\u000e\\1uS>tWK\\5u\u0007>tG/\u001a=u\u0003BL7c\u0001>\u00024\u000511o\\;sG\u0016,\"!b0\u0011\t\u0015\u0005W1Z\u0007\u0003\u000b\u0007TA!\"2\u0006H\u0006!Q\u000f^5m\u0015\u0011)I-!\u0001\u0002\u0011%tG/\u001a:oC2LA!\"4\u0006D\nQ1k\\;sG\u00164\u0015\u000e\\3)\u0017m,\t%b\u0012\u0006R\u00165SqJ\u0011\u0003\u000b'\f\u0001l\u0019\u0018f]\u000edwn]5oOR\u0013X-Z\u0017tifdW\rI!Q\u0013N\u0004\u0013M]3!]><\b\u0005Z3qe\u0016\u001c\u0017\r^3ew\u0001\u001awN\\:vYR\u0004C\u000f[3!g\u000e\fG.\u00193pG\u00022wN\u001d\u0011n_J,\u0007%\u001b8g_Jl\u0017\r^5p]\u0006!!m\u001c3zQ-aX\u0011IC$\u000b#,i%b\u0014)\u0017i,\t%b\u0012\u0006R\u00165Sq\n\u0015\fs\u0016\u0005SqICi\u000b\u001b*y\u0005K\u0006x\u000b\u0003*9%\"5\u0006N\u0015=\u0013!B;oSR\u001cXCACr!\u0019\u0011)#\":\u00060&!Qq\u001dB\u0018\u0005!IE/\u001a:bi>\u0014\bf\u0003=\u0006B\u0015\u001dS\u0011[C'\u000b\u001fB3B^C!\u000b\u000f*\t.\"\u0014\u0006P!ZQ/\"\u0011\u0006H\u0015EWQJC(\u0001"
)
public abstract class Universe extends scala.reflect.api.Universe {
   /** @deprecated */
   public abstract TreeGen treeBuild();

   public interface MacroInternalApi extends scala.reflect.api.Internals.InternalApi {
      Scopes.ScopeApi enter(final Scopes.ScopeApi scope, final Symbols.SymbolApi sym);

      Scopes.ScopeApi unlink(final Scopes.ScopeApi scope, final Symbols.SymbolApi sym);

      Trees.TreeApi changeOwner(final Trees.TreeApi tree, final Symbols.SymbolApi prev, final Symbols.SymbolApi next);

      TreeGen gen();

      Attachments attachments(final Symbols.SymbolApi symbol);

      Symbols.SymbolApi updateAttachment(final Symbols.SymbolApi symbol, final Object attachment, final ClassTag evidence$1);

      Symbols.SymbolApi removeAttachment(final Symbols.SymbolApi symbol, final ClassTag evidence$2);

      Symbols.SymbolApi setOwner(final Symbols.SymbolApi symbol, final Symbols.SymbolApi newowner);

      Symbols.SymbolApi setInfo(final Symbols.SymbolApi symbol, final Types.TypeApi tpe);

      Symbols.SymbolApi setAnnotations(final Symbols.SymbolApi symbol, final Seq annots);

      Symbols.SymbolApi setName(final Symbols.SymbolApi symbol, final scala.reflect.api.Names.NameApi name);

      Symbols.SymbolApi setPrivateWithin(final Symbols.SymbolApi symbol, final Symbols.SymbolApi sym);

      Symbols.SymbolApi setFlag(final Symbols.SymbolApi symbol, final Object flags);

      Symbols.SymbolApi resetFlag(final Symbols.SymbolApi symbol, final Object flags);

      Attachments attachments(final Trees.TreeApi tree);

      Trees.TreeApi updateAttachment(final Trees.TreeApi tree, final Object attachment, final ClassTag evidence$3);

      Trees.TreeApi removeAttachment(final Trees.TreeApi tree, final ClassTag evidence$4);

      Trees.TreeApi setPos(final Trees.TreeApi tree, final Position newpos);

      Trees.TreeApi setType(final Trees.TreeApi tree, final Types.TypeApi tp);

      Trees.TreeApi defineType(final Trees.TreeApi tree, final Types.TypeApi tp);

      Trees.TreeApi setSymbol(final Trees.TreeApi tree, final Symbols.SymbolApi sym);

      Trees.TypeTreeApi setOriginal(final Trees.TypeTreeApi tt, final Trees.TreeApi original);

      void captureVariable(final Symbols.SymbolApi vble);

      Trees.TreeApi referenceCapturedVariable(final Symbols.SymbolApi vble);

      Types.TypeApi capturedVariableType(final Symbols.SymbolApi vble);

      Option subpatterns(final Trees.TreeApi tree);

      // $FF: synthetic method
      Universe scala$reflect$macros$Universe$MacroInternalApi$$$outer();

      static void $init$(final MacroInternalApi $this) {
      }

      public interface MacroDecoratorApi extends scala.reflect.api.Internals.InternalApi.DecoratorApi {
         MacroScopeDecoratorApi scopeDecorator(final Scopes.ScopeApi tree);

         MacroTypeTreeDecoratorApi typeTreeDecorator(final Trees.TypeTreeApi tt);

         // $FF: synthetic method
         MacroInternalApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer();

         static void $init$(final MacroDecoratorApi $this) {
         }

         public class MacroScopeDecoratorApi {
            private final Scopes.ScopeApi scope;
            // $FF: synthetic field
            public final MacroDecoratorApi $outer;

            public Scopes.ScopeApi scope() {
               return this.scope;
            }

            public Scopes.ScopeApi enter(final Symbols.SymbolApi sym) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroScopeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().enter(this.scope(), sym);
            }

            public Scopes.ScopeApi unlink(final Symbols.SymbolApi sym) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroScopeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().unlink(this.scope(), sym);
            }

            // $FF: synthetic method
            public MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroScopeDecoratorApi$$$outer() {
               return this.$outer;
            }

            public MacroScopeDecoratorApi(final Scopes.ScopeApi scope) {
               this.scope = scope;
               if (MacroDecoratorApi.this == null) {
                  throw null;
               } else {
                  this.$outer = MacroDecoratorApi.this;
                  super();
               }
            }
         }

         public class MacroTreeDecoratorApi extends scala.reflect.api.Internals.InternalApi.DecoratorApi.TreeDecoratorApi {
            public Trees.TreeApi tree() {
               return super.tree();
            }

            public Trees.TreeApi changeOwner(final Symbols.SymbolApi prev, final Symbols.SymbolApi next) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().changeOwner(this.tree(), prev, next);
            }

            public Attachments attachments() {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().attachments(this.tree());
            }

            public Trees.TreeApi updateAttachment(final Object attachment, final ClassTag evidence$5) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().updateAttachment(this.tree(), attachment, evidence$5);
            }

            public Trees.TreeApi removeAttachment(final ClassTag evidence$6) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().removeAttachment(this.tree(), evidence$6);
            }

            public Trees.TreeApi setPos(final Position newpos) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setPos(this.tree(), newpos);
            }

            public Trees.TreeApi setType(final Types.TypeApi tp) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setType(this.tree(), tp);
            }

            public Trees.TreeApi defineType(final Types.TypeApi tp) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().defineType(this.tree(), tp);
            }

            public Trees.TreeApi setSymbol(final Symbols.SymbolApi sym) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setSymbol(this.tree(), sym);
            }

            // $FF: synthetic method
            public MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer() {
               return (MacroDecoratorApi)this.$outer;
            }

            public MacroTreeDecoratorApi(final Trees.TreeApi tree) {
               super(tree);
            }
         }

         public class MacroTypeTreeDecoratorApi {
            private final Trees.TypeTreeApi tt;
            // $FF: synthetic field
            public final MacroDecoratorApi $outer;

            public Trees.TypeTreeApi tt() {
               return this.tt;
            }

            public Trees.TypeTreeApi setOriginal(final Trees.TreeApi tree) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTypeTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setOriginal(this.tt(), tree);
            }

            // $FF: synthetic method
            public MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTypeTreeDecoratorApi$$$outer() {
               return this.$outer;
            }

            public MacroTypeTreeDecoratorApi(final Trees.TypeTreeApi tt) {
               this.tt = tt;
               if (MacroDecoratorApi.this == null) {
                  throw null;
               } else {
                  this.$outer = MacroDecoratorApi.this;
                  super();
               }
            }
         }

         public class MacroSymbolDecoratorApi extends scala.reflect.api.Internals.InternalApi.DecoratorApi.SymbolDecoratorApi {
            public Symbols.SymbolApi symbol() {
               return super.symbol();
            }

            public Attachments attachments() {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().attachments(this.symbol());
            }

            public Symbols.SymbolApi updateAttachment(final Object attachment, final ClassTag evidence$7) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().updateAttachment(this.symbol(), attachment, evidence$7);
            }

            public Symbols.SymbolApi removeAttachment(final ClassTag evidence$8) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().removeAttachment(this.symbol(), evidence$8);
            }

            public Symbols.SymbolApi setOwner(final Symbols.SymbolApi newowner) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setOwner(this.symbol(), newowner);
            }

            public Symbols.SymbolApi setInfo(final Types.TypeApi tpe) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setInfo(this.symbol(), tpe);
            }

            public Symbols.SymbolApi setAnnotations(final Seq annots) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setAnnotations(this.symbol(), annots);
            }

            public Symbols.SymbolApi setName(final scala.reflect.api.Names.NameApi name) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setName(this.symbol(), name);
            }

            public Symbols.SymbolApi setPrivateWithin(final Symbols.SymbolApi sym) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setPrivateWithin(this.symbol(), sym);
            }

            public Symbols.SymbolApi setFlag(final Object flags) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setFlag(this.symbol(), flags);
            }

            public Symbols.SymbolApi resetFlag(final Object flags) {
               return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().resetFlag(this.symbol(), flags);
            }

            // $FF: synthetic method
            public MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer() {
               return (MacroDecoratorApi)this.$outer;
            }

            public MacroSymbolDecoratorApi(final Symbols.SymbolApi symbol) {
               super(symbol);
            }
         }
      }
   }

   /** @deprecated */
   public interface MacroCompatApi extends scala.reflect.api.Internals.CompatApi {
      // $FF: synthetic method
      static MacroCompatibleSymbol MacroCompatibleSymbol$(final MacroCompatApi $this, final Symbols.SymbolApi symbol) {
         return $this.MacroCompatibleSymbol(symbol);
      }

      default MacroCompatibleSymbol MacroCompatibleSymbol(final Symbols.SymbolApi symbol) {
         return new MacroCompatibleSymbol(symbol);
      }

      // $FF: synthetic method
      static MacroCompatibleTree MacroCompatibleTree$(final MacroCompatApi $this, final Trees.TreeApi tree) {
         return $this.MacroCompatibleTree(tree);
      }

      default MacroCompatibleTree MacroCompatibleTree(final Trees.TreeApi tree) {
         return new MacroCompatibleTree(tree);
      }

      // $FF: synthetic method
      static CompatibleTypeTree CompatibleTypeTree$(final MacroCompatApi $this, final Trees.TypeTreeApi tt) {
         return $this.CompatibleTypeTree(tt);
      }

      default CompatibleTypeTree CompatibleTypeTree(final Trees.TypeTreeApi tt) {
         return new CompatibleTypeTree(tt);
      }

      // $FF: synthetic method
      Universe scala$reflect$macros$Universe$MacroCompatApi$$$outer();

      static void $init$(final MacroCompatApi $this) {
      }

      public class MacroCompatibleSymbol {
         // $FF: synthetic field
         public final MacroCompatApi $outer;

         // $FF: synthetic method
         public MacroCompatApi scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer() {
            return this.$outer;
         }

         public MacroCompatibleSymbol(final Symbols.SymbolApi symbol) {
            if (MacroCompatApi.this == null) {
               throw null;
            } else {
               this.$outer = MacroCompatApi.this;
               super();
            }
         }
      }

      public class MacroCompatibleTree {
         // $FF: synthetic field
         public final MacroCompatApi $outer;

         // $FF: synthetic method
         public MacroCompatApi scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer() {
            return this.$outer;
         }

         public MacroCompatibleTree(final Trees.TreeApi tree) {
            if (MacroCompatApi.this == null) {
               throw null;
            } else {
               this.$outer = MacroCompatApi.this;
               super();
            }
         }
      }

      public class CompatibleTypeTree {
         // $FF: synthetic field
         public final MacroCompatApi $outer;

         // $FF: synthetic method
         public MacroCompatApi scala$reflect$macros$Universe$MacroCompatApi$CompatibleTypeTree$$$outer() {
            return this.$outer;
         }

         public CompatibleTypeTree(final Trees.TypeTreeApi tt) {
            if (MacroCompatApi.this == null) {
               throw null;
            } else {
               this.$outer = MacroCompatApi.this;
               super();
            }
         }
      }
   }

   /** @deprecated */
   public interface CompilationUnitContextApi {
      /** @deprecated */
      SourceFile source();

      /** @deprecated */
      Trees.TreeApi body();
   }

   /** @deprecated */
   public interface RunContextApi {
      /** @deprecated */
      CompilationUnitContextApi currentUnit();

      /** @deprecated */
      Iterator units();
   }

   public interface TreeGen {
      Trees.TreeApi mkAttributedQualifier(final Types.TypeApi tpe);

      Trees.TreeApi mkAttributedQualifier(final Types.TypeApi tpe, final Symbols.SymbolApi termSym);

      Trees.RefTreeApi mkAttributedRef(final Types.TypeApi pre, final Symbols.SymbolApi sym);

      Trees.RefTreeApi mkAttributedRef(final Symbols.SymbolApi sym);

      Trees.TreeApi stabilize(final Trees.TreeApi tree);

      Trees.TreeApi mkAttributedStableRef(final Types.TypeApi pre, final Symbols.SymbolApi sym);

      Trees.TreeApi mkAttributedStableRef(final Symbols.SymbolApi sym);

      Trees.RefTreeApi mkUnattributedRef(final Symbols.SymbolApi sym);

      Trees.RefTreeApi mkUnattributedRef(final scala.reflect.api.Names.NameApi fullName);

      Trees.ThisApi mkAttributedThis(final Symbols.SymbolApi sym);

      Trees.RefTreeApi mkAttributedIdent(final Symbols.SymbolApi sym);

      Trees.RefTreeApi mkAttributedSelect(final Trees.TreeApi qual, final Symbols.SymbolApi sym);

      Trees.TreeApi mkMethodCall(final Symbols.SymbolApi receiver, final scala.reflect.api.Names.NameApi methodName, final List targs, final List args);

      Trees.TreeApi mkMethodCall(final Symbols.SymbolApi method, final List targs, final List args);

      Trees.TreeApi mkMethodCall(final Symbols.SymbolApi method, final List args);

      Trees.TreeApi mkMethodCall(final Trees.TreeApi target, final List args);

      Trees.TreeApi mkMethodCall(final Symbols.SymbolApi receiver, final scala.reflect.api.Names.NameApi methodName, final List args);

      Trees.TreeApi mkMethodCall(final Trees.TreeApi receiver, final Symbols.SymbolApi method, final List targs, final List args);

      Trees.TreeApi mkMethodCall(final Trees.TreeApi target, final List targs, final List args);

      Trees.TreeApi mkNullaryCall(final Symbols.SymbolApi method, final List targs);

      Trees.TreeApi mkRuntimeUniverseRef();

      Trees.TreeApi mkZero(final Types.TypeApi tp);

      Trees.TreeApi mkCast(final Trees.TreeApi tree, final Types.TypeApi pt);
   }
}
