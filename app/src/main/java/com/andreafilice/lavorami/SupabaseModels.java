package com.andreafilice.lavorami;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class SupabaseModels {

    /// This class is a CallBack class that is called into AccountManagement for do AuthRequests to Supabase server.
    /// @ATTRIBUTES
    /// @String email is the user Email of his account or the account to log into.
    /// @String password is the user Password of his account.
    /// @Map<String,Object> data is the full_name attribute but Supabase accepts only HashMaps, so we have to cast to that type ofd metadata.
    public static class AuthRequest {
        public String email;
        public String password;
        public Map<String, Object> data;
        public AuthRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public AuthRequest(String email, String password, Map<String, Object> data){
            this.email = email;
            this.password = password;
            this.data = data;
        }
    }


    /// This class is a Callback succesful class that return user datas from our Database.
    /// @ATTRIBUTES
    /// @String access_token is the token from the LOGIN phase that grant to us the regolar permissions to the user.
    /// @User user is the User Object, it contains: name, email and password.

    public static class AuthResponse {
        public String access_token;
        public String refresh_token;
        public User user;

        public static class User {
            public String id;
            public String email;
            @SerializedName("user_metadata")
            public Map<String, Object> userMetadata;
        }
    }

    public static class RefreshTokenRequest {
        @SerializedName("refresh_token")
        public String refresh_token;

        public RefreshTokenRequest(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }

    /// This class is a Callback class from the 'loginWithGoogle' method.
    /// @ATTRIBUTES
    /// @String provider is the LoginProvider to contact for SignIn with that Provider.
    /// @String id_token is the Google Cloud Console Client API token, generated for our Application Client.
    public static class GoogleLoginRequest {
        public String provider = "google";
        public String id_token;

        public GoogleLoginRequest(String token){
            this.id_token = token;
        }
    }

    /// This class is a Callback class from the 'resetPassword' method.
    /// @ATTRIBUTES
    /// @String email is the user redirect mail to send the Email.
    public static class ResetPasswordRequest {
        public String email;
        public ResetPasswordRequest(String email) {this.email = email;}
    }

    /// This class represent the LinesFavoriteDatas table structure in Supabase.
    /// @ATTRIBUTES
    /// @String id_user is the UUID of the user account.
    /// @String user_email is the email of the user for query purposes.
    /// @ArrayList<String> lines is the list of favorite lines for the user.
    /// @ArrayList<String> your_lines is the list of user's custom lines.
    public static class LinesFavoriteDatas {
        @SerializedName("user_email")
        public String user_email;
        @SerializedName("lines")
        public java.util.ArrayList<String> lines;
        @SerializedName("your_lines")
        public java.util.ArrayList<String> your_lines;

        public LinesFavoriteDatas(String user_email, java.util.ArrayList<String> lines, java.util.ArrayList<String> your_lines) {
            this.user_email = user_email;
            this.lines = lines;
            this.your_lines = your_lines;
        }
    }

    /// This class represent the UserPreferencesDatas table structure in Supabase.
    /// @ATTRIBUTES
    /// @String user_email is the email of the user for query purposes.
    /// @boolean enable_favorites is the flag to enable/disable favorite lines.
    /// @boolean enable_your_lines is the flag to enable/disable custom lines.
    public static class UserPreferencesDatas {
        @SerializedName("user_email")
        public String user_email;
        @SerializedName("enable_favorites")
        public boolean enable_favorites;
        @SerializedName("enable_your_lines")
        public boolean enable_your_lines;

        public UserPreferencesDatas(String user_email, boolean enable_favorites, boolean enable_your_lines) {
            this.user_email = user_email;
            this.enable_favorites = enable_favorites;
            this.enable_your_lines = enable_your_lines;
        }
    }

    public static class UpdateUserRequest {
        public Map<String, Object> data;

        public UpdateUserRequest(String fullName) {
            this.data = new java.util.HashMap<>();
            this.data.put("full_name", fullName);
        }
    }
}
