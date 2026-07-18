@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {
                "Identity::identity-api",
                "Cart::cart-api"
        }
)
package com.example.ShlopApp.Commerce;