package nhom9.gym.security;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";
    public static final String[] PUBLIC_GET_ENDPOINS = {
            "/account/login",
            "/account/signup",
            "/user",
            "/user/**",
            "/role",
            "/role/**",
            "/account/active",
            "/pack",

            "/pack/**",

            "/notification",
            "/notification/**",
            "/trainer",
            "/trainer/**",
            "/room",
            "/room/**",
            "/equipment",
            "/equipment/**",
            "/pack-registration",
            "/pack-registration/**",
            "/trainer-registration",
            "/trainer-registration/**",
            "/pack-registration",
            "/pack-registration/**",
            "/pack-registrations",
            "/pack-registrations/**",
            "/vnpay",
            "/vnpay/**",
            "/feedback",
            "/feedback/**",
            "/registration",
            "/registration/**",
            "/trainingslots",
            "/trainingslots/**",
            "/training-slot",
            "/training-slot/**"

    };


    public static final String[] PUBLIC_POST_ENDPOINS = {
            "/account/login",
            "/account/signup",
            "/pack",
            "/pack/**",
            "/notification",
            "/notification/**",
            "/trainer",
            "/trainer/**",
            "/room",
            "/room/**",
            "/equipment",
            "/equipment/**",
            "/pack-registration",
            "/pack-registration/**",
            "/trainer-registration",
            "/trainer-registration/**",
            "/pack-registration",
            "/pack-registration/**",
            "/pack-registrations",
            "/pack-registrations/**",
            "users/add",
            "/vnpay",
            "/vnpay/**",
            "/feedbacks",
            "/feedbacks/**",
            "/registration",
            "/registration/**",
            "/trainingslots",
            "/trainingslots/**",
            "/training-slot",
            "/training-slot/**"

    };

    public static final String[] PUBLIC_PUT_ENDPOINS = {
//
            "/user/**",
            "/user",
    };
    public static final String[] PUBLIC_DELETE_ENDPOINS = {
//

    };
    public static final String[] ADMIN_GET_ENDPOINS = {
            "/user",
            "/role",
            "/role/**",
            "/user/**",
            "/pack",
            "/task",
            "/pack/**",
            "/task/**",
            "/statistics",
            "/statistics/**"

    };
    public static final String[] ADMIN_POST_ENDPOINS = {
            "/user",
            "/role",
            "/role/**",
            "/user/**",
            "/pack",
            "/task",
            "/pack/**",
            "/task/**",
            "/equipment",
            "/equipment/**",
            "/statistics",
            "/statistics/**"

    };
    public static final String[] ADMIN_PATCH_ENDPOINS = {
            "/user",
            "/role",
            "/role/**",
            "/user/**",
            "/pack",

            "/pack/**",
            "/equipment",
            "/equipment/**",
            "/room",
            "/room/**",
            "/trainer",
            "/trainer/**"


    };
    public static final String[] ADMIN_DELETE_ENDPOINS = {
            "/user",
            "/role",
            "/role/**",
            "/user/**",
            "/pack",
            "/task",
            "/pack/**",
            "/task/**",
            "/users/delete",
            "/users/delete/**",
            "/packs/delete",
            "/packs/delete/**",
            "/equipments/delete",
            "/rooms/delete",
            "/trainers/delete",

    };
}