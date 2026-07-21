@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {
                "Identity::identity-api",
                "Identity",
                "Commerce::commerce-api"
        }
)
package com.example.ShlopApp.Cart;