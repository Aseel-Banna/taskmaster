package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the UserModel type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UserModels")
public final class UserModel implements Model {
  public static final QueryField ID = field("UserModel", "id");
  public static final QueryField USERNAME = field("UserModel", "username");
  public static final QueryField EMAIL = field("UserModel", "email");
  public static final QueryField PASSWORD = field("UserModel", "password");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private final @ModelField(targetType="String", isRequired = true) String password;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getPassword() {
      return password;
  }
  
  private UserModel(String id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UserModel userModel = (UserModel) obj;
      return ObjectsCompat.equals(getId(), userModel.getId()) &&
              ObjectsCompat.equals(getUsername(), userModel.getUsername()) &&
              ObjectsCompat.equals(getEmail(), userModel.getEmail()) &&
              ObjectsCompat.equals(getPassword(), userModel.getPassword());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getEmail())
      .append(getPassword())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UserModel {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("password=" + String.valueOf(getPassword()))
      .append("}")
      .toString();
  }
  
  public static UsernameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static UserModel justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new UserModel(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      email,
      password);
  }
  public interface UsernameStep {
    EmailStep username(String username);
  }
  

  public interface EmailStep {
    PasswordStep email(String email);
  }
  

  public interface PasswordStep {
    BuildStep password(String password);
  }
  

  public interface BuildStep {
    UserModel build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements UsernameStep, EmailStep, PasswordStep, BuildStep {
    private String id;
    private String username;
    private String email;
    private String password;
    @Override
     public UserModel build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UserModel(
          id,
          username,
          email,
          password);
    }
    
    @Override
     public EmailStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public PasswordStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep password(String password) {
        Objects.requireNonNull(password);
        this.password = password;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String username, String email, String password) {
      super.id(id);
      super.username(username)
        .email(email)
        .password(password);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder password(String password) {
      return (CopyOfBuilder) super.password(password);
    }
  }
  
}
