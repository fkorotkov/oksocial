package com.baulsupp.oksocial.twitter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TwitterAuthInterceptorTest.class,
    TwurlCredentialsStoreTest.class
})
public class TwitterTestSuite {
}
