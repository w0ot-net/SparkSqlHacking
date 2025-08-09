package org.apache.zookeeper.server.quorum.auth;

import java.io.DataInputStream;
import java.net.Socket;

public class NullQuorumAuthServer implements QuorumAuthServer {
   public void authenticate(Socket sock, DataInputStream din) {
   }
}
