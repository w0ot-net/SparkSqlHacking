package org.codehaus.janino.util.charstream;

import java.io.EOFException;
import java.io.IOException;

public interface CharStream {
   int EOI = -1;

   int peek() throws IOException;

   boolean peek(char var1) throws IOException;

   int peek(String var1) throws IOException;

   char read() throws EOFException, IOException;

   void read(char var1) throws EOFException, UnexpectedCharacterException;

   int read(String var1) throws EOFException, IOException, UnexpectedCharacterException;

   boolean peekRead(char var1) throws IOException;

   int peekRead(String var1) throws IOException;

   boolean atEoi() throws IOException;

   void eoi() throws UnexpectedCharacterException;
}
