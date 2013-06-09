package am.ik.moneyger4u.app.user;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.validator.constraints.Email;

@Data
public class UserForm implements Serializable {
    /**
     * Validation group for create user
     */
    public static interface UserCreateGroup {
    }

    /**
     * Validation group for update user
     */
    public static interface UserUpdateGroup {
    }

    /**
     * Validation group for delete user
     */
    public static interface UserDeleteGroup {
    }

    /**
     * serial version uid.
     */
    private static final long serialVersionUID = 1L;

    @Null(groups = { UserCreateGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserDeleteGroup.class })
    @Min(0)
    private Integer userId;

    @Null(groups = { UserDeleteGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserCreateGroup.class })
    @Size(min = 1, max = 30)
    private String firstName;

    @Null(groups = { UserDeleteGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserCreateGroup.class })
    @Size(min = 1, max = 30)
    private String lastName;

    @Null(groups = { UserDeleteGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserCreateGroup.class })
    private Integer familyId;

    @Null(groups = { UserDeleteGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserCreateGroup.class })
    @Size(min = 1, max = 50)
    @Email
    private String email;

    @Null(groups = { UserDeleteGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserCreateGroup.class })
    @Size(min = 6, max = 30)
    private String password;

    @Null(groups = { UserDeleteGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserCreateGroup.class })
    @Size(min = 6, max = 30)
    private String confirmPassword;

    @Null(groups = { UserCreateGroup.class })
    @NotNull(groups = { UserUpdateGroup.class, UserDeleteGroup.class })
    @Min(0)
    private Integer version;
}
