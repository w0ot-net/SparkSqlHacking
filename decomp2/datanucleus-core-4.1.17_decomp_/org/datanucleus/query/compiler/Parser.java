package org.datanucleus.query.compiler;

public interface Parser {
   Node parse(String var1);

   Node[] parseFrom(String var1);

   Node[] parseUpdate(String var1);

   Node[] parseOrder(String var1);

   Node[] parseResult(String var1);

   Node[] parseTuple(String var1);

   Node[][] parseVariables(String var1);

   Node parseVariable(String var1);

   Node[][] parseParameters(String var1);
}
