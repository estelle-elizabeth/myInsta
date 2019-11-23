package com.example.myinsta;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATE_AT = "createdAt";

    public Post() {
        super();
    }

    // Add a constructor that contains core properties
    public Post(String description) {
        super();
    }

    // Use getString and others to access fields
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    

    // Use put to modify field values
    public void setDescription(String value) {
        put(KEY_DESCRIPTION, value);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    // Get the user for this item
    public ParseUser getUser()  {
        return getParseUser(KEY_USER);
    }

    // Associate each item with a user
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
}
