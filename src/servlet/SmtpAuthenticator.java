package servlet;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author RayTing
 * @date 2019-11-12 13:17
 * 发送邮件使用，验证用户名和授权码
 */
public class SmtpAuthenticator extends Authenticator {
    private String username;
    private String password;


    public SmtpAuthenticator() {
        super();
    }


    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        if ((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0)) {
            return new PasswordAuthentication(username, password);
        }
        return null;
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

}