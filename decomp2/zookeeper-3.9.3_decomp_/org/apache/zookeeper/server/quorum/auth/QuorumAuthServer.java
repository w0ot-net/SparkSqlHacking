package org.apache.zookeeper.server.quorum.auth;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public interface QuorumAuthServer {
   void authenticate(Socket var1, DataInputStream var2) throws IOException;
}
