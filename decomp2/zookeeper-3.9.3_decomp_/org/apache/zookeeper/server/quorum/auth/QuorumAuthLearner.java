package org.apache.zookeeper.server.quorum.auth;

import java.io.IOException;
import java.net.Socket;

public interface QuorumAuthLearner {
   void authenticate(Socket var1, String var2) throws IOException;
}
