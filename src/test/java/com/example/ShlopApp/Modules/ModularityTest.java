package com.example.ShlopApp.Modules;

import com.example.ShlopApp.ShlopAppApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {


    @Test
    void verifyModules() {

        ApplicationModules.of(ShlopAppApplication.class)
                .verify();
    }
}
