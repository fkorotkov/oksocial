package(default_visibility = ["//visibility:public"])

java_library(
  name = 'oksocial',
  srcs = glob([
    '**/*.java'
  ]),
  deps = [
    '//:com_squareup_okhttp3_okhttp',
    '//:com_squareup_okhttp3_logging_interceptor',
    '//:com_fasterxml_jackson_core_jackson_databind',
    '//:com_fasterxml_jackson_dataformat_jackson_dataformat_yaml',
    '//:com_twitter_joauth',
    '//:com_google_oauth_client_google_oauth_client_jetty',
    '//:com_google_guava_guava',
    '//:org_mortbay_jetty_jetty',
    '//:com_github_mrmike_ok2curl',
    '//:commons_cli_commons_cli',
    '//:commons_lang_commons_lang',
    '//:commons_io_commons_io',
    '//:org_slf4j_slf4j_jcl',
    '//:io_airlift_airline',
    '//:javax_inject_javax_inject',
  ],
  resources = ['//src/main/resources'],
)

java_binary(
    name = 'oksocial-main',
    runtime_deps = [':oksocial'],
    main_class = 'com.baulsupp.oksocial.Main',
)

java_toolchain(
    name = 'java8',
    encoding = 'UTF-8',
    source_version = '8',
    target_version = '8',
)
