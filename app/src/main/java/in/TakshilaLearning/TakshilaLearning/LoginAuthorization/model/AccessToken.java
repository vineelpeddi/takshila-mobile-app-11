package in.TakshilaLearning.TakshilaLearning.LoginAuthorization.model;

import com.google.gson.annotations.SerializedName;

public class AccessToken {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private String expiresIn;
    @SerializedName("scope")
    private String scope;
    @SerializedName("grant_type")
    String grant_type;
    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;
    @SerializedName("client_id")
    String client_id;
    @SerializedName("client_secret")
    String client_secret;

    public AccessToken(String grant_type, String username, String password, String client_id, String client_secret) {
        this.grant_type = grant_type;
        this.username = username;
        this.password = password;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    public String getAccesstoken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getScope() {
        return scope;
    }
}
