package org.apache.derby.impl.sql.compile;

import java.io.Reader;
import java.io.StringReader;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.shared.common.error.StandardException;

public class ParserImpl implements Parser {
   static final int LARGE_TOKEN_SIZE = 128;
   private SQLParser cachedParser;
   private Object cachedTokenManager;
   private CharStream charStream;
   protected String SQLtext;
   private final CompilerContext cc;

   public ParserImpl(CompilerContext var1) {
      this.cc = var1;
   }

   public Visitable parseStatement(String var1) throws StandardException {
      return this.parseStatement(var1, (Object[])null);
   }

   protected Object getTokenManager() {
      SQLParserTokenManager var1 = (SQLParserTokenManager)this.cachedTokenManager;
      if (var1 == null) {
         var1 = new SQLParserTokenManager(this.charStream);
         this.cachedTokenManager = var1;
      } else {
         var1.ReInit(this.charStream);
      }

      return var1;
   }

   private SQLParser getParser() {
      SQLParserTokenManager var1 = (SQLParserTokenManager)this.getTokenManager();
      SQLParser var2 = this.cachedParser;
      if (var2 == null) {
         var2 = new SQLParser(var1);
         var2.setCompilerContext(this.cc);
         this.cachedParser = var2;
      } else {
         var2.ReInit(var1);
      }

      return var2;
   }

   public Visitable parseStatement(String var1, Object[] var2) throws StandardException {
      return this.parseStatementOrSearchCondition(var1, var2, true);
   }

   private Visitable parseStatementOrSearchCondition(String var1, Object[] var2, boolean var3) throws StandardException {
      StringReader var4 = new StringReader(var1);
      if (this.charStream == null) {
         this.charStream = new UCode_CharStream(var4, 1, 1, 128);
      } else {
         this.charStream.ReInit((Reader)var4, 1, 1, 128);
      }

      this.SQLtext = var1;

      try {
         SQLParser var5 = this.getParser();
         return (Visitable)(var3 ? var5.Statement(var1, var2) : var5.SearchCondition(var1));
      } catch (ParseException var6) {
         throw StandardException.newException("42X01", new Object[]{var6.getMessage()});
      } catch (TokenMgrError var7) {
         this.cachedParser = null;
         throw StandardException.newException("42X02", new Object[]{var7.getMessage()});
      }
   }

   public Visitable parseSearchCondition(String var1) throws StandardException {
      return this.parseStatementOrSearchCondition(var1, (Object[])null, false);
   }

   public String getSQLtext() {
      return this.SQLtext;
   }
}
