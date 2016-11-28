package po;

/**
 * Created by apple on 2016/11/22.
 */
public class SalerPO {
    private String account;
    private String password;

    public SalerPO(String acc,String pass){
        account = acc;
        password = pass;
    }

    public String getAccount(){
        return account;
    }

    public String getPassword(){
        return password;
    }

}
