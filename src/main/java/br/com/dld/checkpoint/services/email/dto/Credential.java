package br.com.dld.checkpoint.services.email.dto;

/**
 *
 * @author David Duarte
 */
public class Credential {

    private String host;
    private String email;
    private String username;
    private String password;
    private int port = 587;
    private boolean requiresSSL = false;
    private boolean requiresTLS = false;
    private int type = 0; // 0 = SMTP; 1 = SPARKPOST

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isRequiresSSL() {
        return requiresSSL;
    }

    public void setRequiresSSL(boolean requiresSSL) {
        this.requiresSSL = requiresSSL;
    }

    public boolean isRequiresTLS() {
        return requiresTLS;
    }

    public void setRequiresTLS(boolean requiresTLS) {
        this.requiresTLS = requiresTLS;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
