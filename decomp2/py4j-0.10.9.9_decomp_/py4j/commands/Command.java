package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import py4j.Gateway;
import py4j.Py4JException;
import py4j.Py4JServerConnection;

public interface Command {
   void execute(String var1, BufferedReader var2, BufferedWriter var3) throws Py4JException, IOException;

   String getCommandName();

   void init(Gateway var1, Py4JServerConnection var2);
}
