package com.petclinic;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources",
        glue = "stepdefs",
        plugin = "{pretty}")
public class RunTest extends AbstractTestNGCucumberTests {
}
