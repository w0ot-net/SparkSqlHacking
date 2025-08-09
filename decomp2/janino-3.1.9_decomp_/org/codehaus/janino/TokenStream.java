package org.codehaus.janino;

import java.io.IOException;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.nullanalysis.Nullable;

public interface TokenStream {
   Token peek() throws CompileException, IOException;

   boolean peek(String var1) throws CompileException, IOException;

   int peek(String... var1) throws CompileException, IOException;

   boolean peek(TokenType var1) throws CompileException, IOException;

   int peek(TokenType... var1) throws CompileException, IOException;

   Token peekNextButOne() throws CompileException, IOException;

   boolean peekNextButOne(String var1) throws CompileException, IOException;

   Token read() throws CompileException, IOException;

   void read(String var1) throws CompileException, IOException;

   int read(String... var1) throws CompileException, IOException;

   String read(TokenType var1) throws CompileException, IOException;

   int read(TokenType... var1) throws CompileException, IOException;

   boolean peekRead(String var1) throws CompileException, IOException;

   int peekRead(String... var1) throws CompileException, IOException;

   @Nullable
   String peekRead(TokenType var1) throws CompileException, IOException;

   int peekRead(TokenType... var1) throws CompileException, IOException;

   void setWarningHandler(@Nullable WarningHandler var1);

   Location location();
}
