package cn.lhx.dishsys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lee549
 * @date 2020/6/8 12:42
 */
@Component
public class ServerConfig {
    @Value("${server.port}")
    private int serverPort;

    public String getServerUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://"+address.getHostAddress() +":"+this.serverPort;
    }

}
