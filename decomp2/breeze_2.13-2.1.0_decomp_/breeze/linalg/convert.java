package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tMu!B,Y\u0011\u0003if!B0Y\u0011\u0003\u0001\u0007\"B7\u0002\t\u0003q\u0007bB8\u0002\u0005\u0004%\u0019\u0001\u001d\u0005\u0007{\u0006\u0001\u000b\u0011B9\t\u000fy\f!\u0019!C\u0002\u007f\"A\u0011\u0011B\u0001!\u0002\u0013\t\t\u0001C\u0005\u0002\f\u0005\u0011\r\u0011b\u0001\u0002\u000e!A\u0011qC\u0001!\u0002\u0013\ty\u0001C\u0005\u0002\u001a\u0005\u0011\r\u0011b\u0001\u0002\u001c!A\u0011QE\u0001!\u0002\u0013\ti\u0002C\u0005\u0002(\u0005\u0011\r\u0011b\u0001\u0002*!A\u00111G\u0001!\u0002\u0013\tY\u0003C\u0005\u00026\u0005\u0011\r\u0011b\u0001\u00028!A\u0011\u0011I\u0001!\u0002\u0013\tI\u0004C\u0005\u0002D\u0005\u0011\r\u0011b\u0001\u0002F!A\u0011qJ\u0001!\u0002\u0013\t9\u0005C\u0005\u0002R\u0005\u0011\r\u0011b\u0001\u0002T!A\u0011qK\u0001!\u0002\u0013\t)\u0006C\u0005\u0002Z\u0005\u0011\r\u0011b\u0001\u0002\\!A\u0011qL\u0001!\u0002\u0013\ti\u0006C\u0005\u0002b\u0005\u0011\r\u0011b\u0001\u0002d!A\u0011qM\u0001!\u0002\u0013\t)\u0007C\u0005\u0002j\u0005\u0011\r\u0011b\u0001\u0002l!A\u0011qN\u0001!\u0002\u0013\ti\u0007C\u0005\u0002r\u0005\u0011\r\u0011b\u0001\u0002t!A\u0011qO\u0001!\u0002\u0013\t)\bC\u0005\u0002z\u0005\u0011\r\u0011b\u0001\u0002|!A\u0011QQ\u0001!\u0002\u0013\ti\bC\u0005\u0002\b\u0006\u0011\r\u0011b\u0001\u0002\n\"A\u0011QR\u0001!\u0002\u0013\tY\tC\u0005\u0002\u0010\u0006\u0011\r\u0011b\u0001\u0002\u0012\"A\u0011QS\u0001!\u0002\u0013\t\u0019\nC\u0005\u0002\u0018\u0006\u0011\r\u0011b\u0001\u0002\u001a\"A\u0011QT\u0001!\u0002\u0013\tY\nC\u0005\u0002 \u0006\u0011\r\u0011b\u0001\u0002\"\"A\u0011QU\u0001!\u0002\u0013\t\u0019\u000bC\u0005\u0002(\u0006\u0011\r\u0011b\u0001\u0002*\"A\u0011QV\u0001!\u0002\u0013\tY\u000bC\u0005\u00020\u0006\u0011\r\u0011b\u0001\u00022\"A\u00111X\u0001!\u0002\u0013\t\u0019\fC\u0005\u0002>\u0006\u0011\r\u0011b\u0001\u0002@\"A\u00111Y\u0001!\u0002\u0013\t\t\rC\u0005\u0002F\u0006\u0011\r\u0011b\u0001\u0002H\"A\u00111Z\u0001!\u0002\u0013\tI\rC\u0005\u0002N\u0006\u0011\r\u0011b\u0001\u0002P\"A\u00111[\u0001!\u0002\u0013\t\t\u000eC\u0005\u0002V\u0006\u0011\r\u0011b\u0001\u0002X\"A\u00111\\\u0001!\u0002\u0013\tI\u000eC\u0005\u0002^\u0006\u0011\r\u0011b\u0001\u0002`\"A\u00111]\u0001!\u0002\u0013\t\t\u000fC\u0005\u0002f\u0006\u0011\r\u0011b\u0001\u0002h\"A\u0011\u0011_\u0001!\u0002\u0013\tI\u000fC\u0005\u0002t\u0006\u0011\r\u0011b\u0001\u0002v\"A\u0011\u0011`\u0001!\u0002\u0013\t9\u0010C\u0005\u0002|\u0006\u0011\r\u0011b\u0001\u0002~\"A!\u0011A\u0001!\u0002\u0013\ty\u0010C\u0005\u0003\u0004\u0005\u0011\r\u0011b\u0001\u0003\u0006!A!\u0011B\u0001!\u0002\u0013\u00119\u0001C\u0005\u0003\f\u0005\u0011\r\u0011b\u0001\u0003\u000e!A!\u0011C\u0001!\u0002\u0013\u0011y\u0001C\u0005\u0003\u0014\u0005\u0011\r\u0011b\u0001\u0003\u0016!A!\u0011D\u0001!\u0002\u0013\u00119\u0002C\u0005\u0003\u001c\u0005\u0011\r\u0011b\u0001\u0003\u001e!A!qE\u0001!\u0002\u0013\u0011y\u0002C\u0005\u0003*\u0005\u0011\r\u0011b\u0001\u0003,!A!qF\u0001!\u0002\u0013\u0011i\u0003C\u0005\u00032\u0005\u0011\r\u0011b\u0001\u00034!A!qG\u0001!\u0002\u0013\u0011)\u0004C\u0005\u0003:\u0005\u0011\r\u0011b\u0001\u0003<!A!qH\u0001!\u0002\u0013\u0011i\u0004C\u0005\u0003B\u0005\u0011\r\u0011b\u0001\u0003D!A!qI\u0001!\u0002\u0013\u0011)\u0005C\u0005\u0003J\u0005\u0011\r\u0011b\u0001\u0003L!A!qJ\u0001!\u0002\u0013\u0011i\u0005C\u0005\u0003R\u0005\u0011\r\u0011b\u0001\u0003T!A!\u0011N\u0001!\u0002\u0013\u0011)\u0006C\u0005\u0003l\u0005\u0011\r\u0011b\u0001\u0003n!A!\u0011O\u0001!\u0002\u0013\u0011y\u0007C\u0005\u0003t\u0005\u0011\r\u0011b\u0001\u0003v!A!\u0011P\u0001!\u0002\u0013\u00119\bC\u0005\u0003|\u0005\u0011\r\u0011b\u0001\u0003~!A!\u0011Q\u0001!\u0002\u0013\u0011y\bC\u0005\u0003\u0004\u0006\u0011\r\u0011b\u0001\u0003\u0006\"A!\u0011R\u0001!\u0002\u0013\u00119\tC\u0005\u0003\f\u0006\u0011\r\u0011b\u0001\u0003\u000e\"A!\u0011S\u0001!\u0002\u0013\u0011y)A\u0004d_:4XM\u001d;\u000b\u0005eS\u0016A\u00027j]\u0006dwMC\u0001\\\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u00010\u0002\u001b\u0005A&aB2p]Z,'\u000f^\n\u0004\u0003\u0005<\u0007C\u00012f\u001b\u0005\u0019'\"\u00013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001c'AB!osJ+g\r\u0005\u0002iW6\t\u0011N\u0003\u0002k5\u00069q-\u001a8fe&\u001c\u0017B\u00017j\u00051i\u0015\r\u001d9j]\u001e,f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\tQ,A\u0007j[Bd'gX%oi~Ke\u000e^\u000b\u0002cB)!o]<{o6\t\u0011!\u0003\u0002uk\n)\u0011*\u001c9me%\u0011a/\u001b\u0002\u0006+\u001a+hn\u0019\t\u0003EbL!!_2\u0003\u0007%sGO\u0004\u0002cw&\u0011ApY\u0001\u0004\u0013:$\u0018AD5na2\u0014t,\u00138u?&sG\u000fI\u0001\u0011S6\u0004HNM0E_V\u0014G.Z0J]R,\"!!\u0001\u0011\rI\u001c\u00181\u0001>x!\r\u0011\u0017QA\u0005\u0004\u0003\u000f\u0019'A\u0002#pk\ndW-A\tj[Bd'g\u0018#pk\ndWmX%oi\u0002\nq\"[7qYJzf\t\\8bi~Ke\u000e^\u000b\u0003\u0003\u001f\u0001bA]:\u0002\u0012i<\bc\u00012\u0002\u0014%\u0019\u0011QC2\u0003\u000b\u0019cw.\u0019;\u0002!%l\u0007\u000f\u001c\u001a`\r2|\u0017\r^0J]R\u0004\u0013AD5na2\u0014t\fT8oO~Ke\u000e^\u000b\u0003\u0003;\u0001bA]:\u0002 i<\bc\u00012\u0002\"%\u0019\u00111E2\u0003\t1{gnZ\u0001\u0010S6\u0004HNM0M_:<w,\u00138uA\u0005q\u0011.\u001c9me}\u001b\u0005.\u0019:`\u0013:$XCAA\u0016!\u0019\u00118/!\f{oB\u0019!-a\f\n\u0007\u0005E2M\u0001\u0003DQ\u0006\u0014\u0018aD5na2\u0014tl\u00115be~Ke\u000e\u001e\u0011\u0002\u001f%l\u0007\u000f\u001c\u001a`'\"|'\u000f^0J]R,\"!!\u000f\u0011\rI\u001c\u00181\b>x!\r\u0011\u0017QH\u0005\u0004\u0003\u007f\u0019'!B*i_J$\u0018\u0001E5na2\u0014tl\u00155peR|\u0016J\u001c;!\u0003AIW\u000e\u001d73?&sGo\u0018#pk\ndW-\u0006\u0002\u0002HA9!o]<\u0002J\u0005\rab\u00012\u0002L%\u0019\u0011QJ2\u0002\r\u0011{WO\u00197f\u0003EIW\u000e\u001d73?&sGo\u0018#pk\ndW\rI\u0001\u0014S6\u0004HNM0E_V\u0014G.Z0E_V\u0014G.Z\u000b\u0003\u0003+\u0002\u0002B]:\u0002\u0004\u0005%\u00131A\u0001\u0015S6\u0004HNM0E_V\u0014G.Z0E_V\u0014G.\u001a\u0011\u0002%%l\u0007\u000f\u001c\u001a`\r2|\u0017\r^0E_V\u0014G.Z\u000b\u0003\u0003;\u0002\u0002B]:\u0002\u0012\u0005%\u00131A\u0001\u0014S6\u0004HNM0GY>\fGo\u0018#pk\ndW\rI\u0001\u0012S6\u0004HNM0M_:<w\fR8vE2,WCAA3!!\u00118/a\b\u0002J\u0005\r\u0011AE5na2\u0014t\fT8oO~#u.\u001e2mK\u0002\n\u0011#[7qYJz6\t[1s?\u0012{WO\u00197f+\t\ti\u0007\u0005\u0005sg\u00065\u0012\u0011JA\u0002\u0003IIW\u000e\u001d73?\u000eC\u0017M]0E_V\u0014G.\u001a\u0011\u0002%%l\u0007\u000f\u001c\u001a`'\"|'\u000f^0E_V\u0014G.Z\u000b\u0003\u0003k\u0002\u0002B]:\u0002<\u0005%\u00131A\u0001\u0014S6\u0004HNM0TQ>\u0014Ho\u0018#pk\ndW\rI\u0001\u0010S6\u0004HNM0J]R|f\t\\8biV\u0011\u0011Q\u0010\t\beN<\u0018qPA\t\u001d\r\u0011\u0017\u0011Q\u0005\u0004\u0003\u0007\u001b\u0017!\u0002$m_\u0006$\u0018\u0001E5na2\u0014t,\u00138u?\u001acw.\u0019;!\u0003IIW\u000e\u001d73?\u0012{WO\u00197f?\u001acw.\u0019;\u0016\u0005\u0005-\u0005\u0003\u0003:t\u0003\u0007\ty(!\u0005\u0002'%l\u0007\u000f\u001c\u001a`\t>,(\r\\3`\r2|\u0017\r\u001e\u0011\u0002#%l\u0007\u000f\u001c\u001a`\r2|\u0017\r^0GY>\fG/\u0006\u0002\u0002\u0014BA!o]A\t\u0003\u007f\n\t\"\u0001\nj[Bd'g\u0018$m_\u0006$xL\u00127pCR\u0004\u0013\u0001E5na2\u0014t\fT8oO~3En\\1u+\t\tY\n\u0005\u0005sg\u0006}\u0011qPA\t\u0003EIW\u000e\u001d73?2{gnZ0GY>\fG\u000fI\u0001\u0011S6\u0004HNM0DQ\u0006\u0014xL\u00127pCR,\"!a)\u0011\u0011I\u001c\u0018QFA@\u0003#\t\u0011#[7qYJz6\t[1s?\u001acw.\u0019;!\u0003EIW\u000e\u001d73?NCwN\u001d;`\r2|\u0017\r^\u000b\u0003\u0003W\u0003\u0002B]:\u0002<\u0005}\u0014\u0011C\u0001\u0013S6\u0004HNM0TQ>\u0014Ho\u0018$m_\u0006$\b%\u0001\bj[Bd'gX%oi~cuN\\4\u0016\u0005\u0005M\u0006c\u0002:to\u0006U\u0016q\u0004\b\u0004E\u0006]\u0016bAA]G\u0006!Aj\u001c8h\u0003=IW\u000e\u001d73?&sGo\u0018'p]\u001e\u0004\u0013!E5na2\u0014t\fR8vE2,w\fT8oOV\u0011\u0011\u0011\u0019\t\teN\f\u0019!!.\u0002 \u0005\u0011\u0012.\u001c9me}#u.\u001e2mK~cuN\\4!\u0003AIW\u000e\u001d73?\u001acw.\u0019;`\u0019>tw-\u0006\u0002\u0002JBA!o]A\t\u0003k\u000by\"A\tj[Bd'g\u0018$m_\u0006$x\fT8oO\u0002\nq\"[7qYJzFj\u001c8h?2{gnZ\u000b\u0003\u0003#\u0004\u0002B]:\u0002 \u0005U\u0016qD\u0001\u0011S6\u0004HNM0M_:<w\fT8oO\u0002\nq\"[7qYJz6\t[1s?2{gnZ\u000b\u0003\u00033\u0004\u0002B]:\u0002.\u0005U\u0016qD\u0001\u0011S6\u0004HNM0DQ\u0006\u0014x\fT8oO\u0002\n\u0001#[7qYJz6\u000b[8si~cuN\\4\u0016\u0005\u0005\u0005\b\u0003\u0003:t\u0003w\t),a\b\u0002#%l\u0007\u000f\u001c\u001a`'\"|'\u000f^0M_:<\u0007%\u0001\bj[Bd'gX%oi~\u001b\u0005.\u0019:\u0016\u0005\u0005%\bc\u0002:to\u0006-\u0018Q\u0006\b\u0004E\u00065\u0018bAAxG\u0006!1\t[1s\u0003=IW\u000e\u001d73?&sGoX\"iCJ\u0004\u0013!E5na2\u0014t\fR8vE2,wl\u00115beV\u0011\u0011q\u001f\t\teN\f\u0019!a;\u0002.\u0005\u0011\u0012.\u001c9me}#u.\u001e2mK~\u001b\u0005.\u0019:!\u0003AIW\u000e\u001d73?\u001acw.\u0019;`\u0007\"\f'/\u0006\u0002\u0002\u0000BA!o]A\t\u0003W\fi#A\tj[Bd'g\u0018$m_\u0006$xl\u00115be\u0002\nq\"[7qYJzFj\u001c8h?\u000eC\u0017M]\u000b\u0003\u0005\u000f\u0001\u0002B]:\u0002 \u0005-\u0018QF\u0001\u0011S6\u0004HNM0M_:<wl\u00115be\u0002\nq\"[7qYJz6\t[1s?\u000eC\u0017M]\u000b\u0003\u0005\u001f\u0001\u0002B]:\u0002.\u0005-\u0018QF\u0001\u0011S6\u0004HNM0DQ\u0006\u0014xl\u00115be\u0002\n\u0001#[7qYJz6\u000b[8si~\u001b\u0005.\u0019:\u0016\u0005\t]\u0001\u0003\u0003:t\u0003w\tY/!\f\u0002#%l\u0007\u000f\u001c\u001a`'\"|'\u000f^0DQ\u0006\u0014\b%A\bj[Bd'gX%oi~\u001b\u0006n\u001c:u+\t\u0011y\u0002E\u0004sg^\u0014\t#a\u000f\u000f\u0007\t\u0014\u0019#C\u0002\u0003&\r\fQa\u00155peR\f\u0001#[7qYJz\u0016J\u001c;`'\"|'\u000f\u001e\u0011\u0002%%l\u0007\u000f\u001c\u001a`\t>,(\r\\3`'\"|'\u000f^\u000b\u0003\u0005[\u0001\u0002B]:\u0002\u0004\t\u0005\u00121H\u0001\u0014S6\u0004HNM0E_V\u0014G.Z0TQ>\u0014H\u000fI\u0001\u0012S6\u0004HNM0GY>\fGoX*i_J$XC\u0001B\u001b!!\u00118/!\u0005\u0003\"\u0005m\u0012AE5na2\u0014tL\u00127pCR|6\u000b[8si\u0002\n\u0001#[7qYJzFj\u001c8h?NCwN\u001d;\u0016\u0005\tu\u0002\u0003\u0003:t\u0003?\u0011\t#a\u000f\u0002#%l\u0007\u000f\u001c\u001a`\u0019>twmX*i_J$\b%\u0001\tj[Bd'gX\"iCJ|6\u000b[8siV\u0011!Q\t\t\teN\fiC!\t\u0002<\u0005\t\u0012.\u001c9me}\u001b\u0005.\u0019:`'\"|'\u000f\u001e\u0011\u0002#%l\u0007\u000f\u001c\u001a`'\"|'\u000f^0TQ>\u0014H/\u0006\u0002\u0003NAA!o]A\u001e\u0005C\tY$\u0001\nj[Bd'gX*i_J$xl\u00155peR\u0004\u0013!E5na2\u0014t,\u00138u?\u000e{W\u000e\u001d7fqV\u0011!Q\u000b\t\beN<(q\u000bB2\u001d\u0011\u0011IFa\u0018\u000e\u0005\tm#b\u0001B/5\u0006!Q.\u0019;i\u0013\u0011\u0011\tGa\u0017\u0002\u000f\r{W\u000e\u001d7fqB!!\u0011\fB3\u0013\u0011\u00119Ga\u0017\u0003\u000f\r{W\u000e\u001d7fq\u0006\u0011\u0012.\u001c9me}Ke\u000e^0D_6\u0004H.\u001a=!\u0003QIW\u000e\u001d73?\u0012{WO\u00197f?\u000e{W\u000e\u001d7fqV\u0011!q\u000e\t\teN\f\u0019Aa\u0016\u0003d\u0005)\u0012.\u001c9me}#u.\u001e2mK~\u001bu.\u001c9mKb\u0004\u0013aE5na2\u0014tL\u00127pCR|6i\\7qY\u0016DXC\u0001B<!!\u00118/!\u0005\u0003X\t\r\u0014\u0001F5na2\u0014tL\u00127pCR|6i\\7qY\u0016D\b%\u0001\nj[Bd'g\u0018'p]\u001e|6i\\7qY\u0016DXC\u0001B@!!\u00118/a\b\u0003X\t\r\u0014aE5na2\u0014t\fT8oO~\u001bu.\u001c9mKb\u0004\u0013AE5na2\u0014tl\u00115be~\u001bu.\u001c9mKb,\"Aa\"\u0011\u0011I\u001c\u0018Q\u0006B,\u0005G\n1#[7qYJz6\t[1s?\u000e{W\u000e\u001d7fq\u0002\n1#[7qYJz6\u000b[8si~\u001bu.\u001c9mKb,\"Aa$\u0011\u0011I\u001c\u00181\bB,\u0005G\nA#[7qYJz6\u000b[8si~\u001bu.\u001c9mKb\u0004\u0003"
)
public final class convert {
   public static UFunc.UImpl2 impl2_Short_Complex() {
      return convert$.MODULE$.impl2_Short_Complex();
   }

   public static UFunc.UImpl2 impl2_Char_Complex() {
      return convert$.MODULE$.impl2_Char_Complex();
   }

   public static UFunc.UImpl2 impl2_Long_Complex() {
      return convert$.MODULE$.impl2_Long_Complex();
   }

   public static UFunc.UImpl2 impl2_Float_Complex() {
      return convert$.MODULE$.impl2_Float_Complex();
   }

   public static UFunc.UImpl2 impl2_Double_Complex() {
      return convert$.MODULE$.impl2_Double_Complex();
   }

   public static UFunc.UImpl2 impl2_Int_Complex() {
      return convert$.MODULE$.impl2_Int_Complex();
   }

   public static UFunc.UImpl2 impl2_Short_Short() {
      return convert$.MODULE$.impl2_Short_Short();
   }

   public static UFunc.UImpl2 impl2_Char_Short() {
      return convert$.MODULE$.impl2_Char_Short();
   }

   public static UFunc.UImpl2 impl2_Long_Short() {
      return convert$.MODULE$.impl2_Long_Short();
   }

   public static UFunc.UImpl2 impl2_Float_Short() {
      return convert$.MODULE$.impl2_Float_Short();
   }

   public static UFunc.UImpl2 impl2_Double_Short() {
      return convert$.MODULE$.impl2_Double_Short();
   }

   public static UFunc.UImpl2 impl2_Int_Short() {
      return convert$.MODULE$.impl2_Int_Short();
   }

   public static UFunc.UImpl2 impl2_Short_Char() {
      return convert$.MODULE$.impl2_Short_Char();
   }

   public static UFunc.UImpl2 impl2_Char_Char() {
      return convert$.MODULE$.impl2_Char_Char();
   }

   public static UFunc.UImpl2 impl2_Long_Char() {
      return convert$.MODULE$.impl2_Long_Char();
   }

   public static UFunc.UImpl2 impl2_Float_Char() {
      return convert$.MODULE$.impl2_Float_Char();
   }

   public static UFunc.UImpl2 impl2_Double_Char() {
      return convert$.MODULE$.impl2_Double_Char();
   }

   public static UFunc.UImpl2 impl2_Int_Char() {
      return convert$.MODULE$.impl2_Int_Char();
   }

   public static UFunc.UImpl2 impl2_Short_Long() {
      return convert$.MODULE$.impl2_Short_Long();
   }

   public static UFunc.UImpl2 impl2_Char_Long() {
      return convert$.MODULE$.impl2_Char_Long();
   }

   public static UFunc.UImpl2 impl2_Long_Long() {
      return convert$.MODULE$.impl2_Long_Long();
   }

   public static UFunc.UImpl2 impl2_Float_Long() {
      return convert$.MODULE$.impl2_Float_Long();
   }

   public static UFunc.UImpl2 impl2_Double_Long() {
      return convert$.MODULE$.impl2_Double_Long();
   }

   public static UFunc.UImpl2 impl2_Int_Long() {
      return convert$.MODULE$.impl2_Int_Long();
   }

   public static UFunc.UImpl2 impl2_Short_Float() {
      return convert$.MODULE$.impl2_Short_Float();
   }

   public static UFunc.UImpl2 impl2_Char_Float() {
      return convert$.MODULE$.impl2_Char_Float();
   }

   public static UFunc.UImpl2 impl2_Long_Float() {
      return convert$.MODULE$.impl2_Long_Float();
   }

   public static UFunc.UImpl2 impl2_Float_Float() {
      return convert$.MODULE$.impl2_Float_Float();
   }

   public static UFunc.UImpl2 impl2_Double_Float() {
      return convert$.MODULE$.impl2_Double_Float();
   }

   public static UFunc.UImpl2 impl2_Int_Float() {
      return convert$.MODULE$.impl2_Int_Float();
   }

   public static UFunc.UImpl2 impl2_Short_Double() {
      return convert$.MODULE$.impl2_Short_Double();
   }

   public static UFunc.UImpl2 impl2_Char_Double() {
      return convert$.MODULE$.impl2_Char_Double();
   }

   public static UFunc.UImpl2 impl2_Long_Double() {
      return convert$.MODULE$.impl2_Long_Double();
   }

   public static UFunc.UImpl2 impl2_Float_Double() {
      return convert$.MODULE$.impl2_Float_Double();
   }

   public static UFunc.UImpl2 impl2_Double_Double() {
      return convert$.MODULE$.impl2_Double_Double();
   }

   public static UFunc.UImpl2 impl2_Int_Double() {
      return convert$.MODULE$.impl2_Int_Double();
   }

   public static UFunc.UImpl2 impl2_Short_Int() {
      return convert$.MODULE$.impl2_Short_Int();
   }

   public static UFunc.UImpl2 impl2_Char_Int() {
      return convert$.MODULE$.impl2_Char_Int();
   }

   public static UFunc.UImpl2 impl2_Long_Int() {
      return convert$.MODULE$.impl2_Long_Int();
   }

   public static UFunc.UImpl2 impl2_Float_Int() {
      return convert$.MODULE$.impl2_Float_Int();
   }

   public static UFunc.UImpl2 impl2_Double_Int() {
      return convert$.MODULE$.impl2_Double_Int();
   }

   public static UFunc.UImpl2 impl2_Int_Int() {
      return convert$.MODULE$.impl2_Int_Int();
   }

   public static Object withSink(final Object s) {
      return convert$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return convert$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return convert$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return convert$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return convert$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return convert$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return convert$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return convert$.MODULE$.apply(v, impl);
   }
}
