load("@bazel_tools//tools/build_defs/pkg:pkg.bzl", "pkg_tar")

pkg_tar(
    name = "bundle-libs",
    package_dir = "lib",
    files = [
      '//src/main/java/com/baulsupp/oksocial',
      '@com_squareup_okhttp3_okhttp//jar',
      '@com_squareup_okhttp3_logging_interceptor//jar',
      '@com_squareup_okio_okio//jar',
      '@com_fasterxml_jackson_core_jackson_databind//jar',
      '@com_fasterxml_jackson_dataformat_jackson_dataformat_yaml//jar',
      '@com_twitter_joauth//jar',
      '@com_google_oauth_client_google_oauth_client_jetty//jar',
      '@com_google_guava_guava//jar',
      '@org_mortbay_jetty_jetty//jar',
      '@com_github_mrmike_ok2curl//jar',
      '@commons_cli_commons_cli//jar',
      '@commons_lang_commons_lang//jar',
      '@commons_io_commons_io//jar',
      '@org_slf4j_slf4j_jcl//jar',
      '@io_airlift_airline//jar',
      '@javax_inject_javax_inject//jar',
    ],
    mode = "0644",
)

pkg_tar(
    name = "bundle-alpn",
    package_dir = "alpn",
    files = ["@alpn_boot//jar"],
    mode = "0644",
)

pkg_tar(
    name = "bundle-bin",
    package_dir = "bin",
    files = glob(["src/main/scripts/**"]),
    mode = "0755",
)

pkg_tar(
    name = "oksocial-bundle",
    extension = "tgz",
    deps = [
        ":bundle-bin",
        ":bundle-libs",
        ":bundle-alpn",
    ],
)

java_library(
    name = "alpn_boot",
    visibility = ["//visibility:public"],
    exports = [
        "@alpn_boot//jar",
    ],
)

java_library(
    name = "com_google_code_findbugs_jsr305",
    visibility = ["//visibility:public"],
    exports = [
        "@com_google_code_findbugs_jsr305//jar",
    ],
)

java_library(
    name = "org_apache_httpcomponents_httpcore",
    visibility = ["//visibility:public"],
    exports = [
        "@org_apache_httpcomponents_httpcore//jar",
    ],
)

java_library(
    name = "com_google_oauth_client_google_oauth_client_jetty",
    visibility = ["//visibility:public"],
    exports = [
        "@com_google_oauth_client_google_oauth_client_jetty//jar",
        "@com_google_code_findbugs_jsr305//jar",
        "@com_google_http_client_google_http_client//jar",
        "@com_google_oauth_client_google_oauth_client//jar",
        "@com_google_oauth_client_google_oauth_client_java6//jar",
        "@commons_codec_commons_codec//jar",
        "@commons_logging_commons_logging//jar",
        "@org_apache_httpcomponents_httpclient//jar",
        "@org_apache_httpcomponents_httpcore//jar",
        "@org_mortbay_jetty_jetty//jar",
        "@org_mortbay_jetty_jetty_util//jar",
        "@org_mortbay_jetty_servlet_api//jar",
    ],
)

java_library(
    name = "commons_io_commons_io",
    visibility = ["//visibility:public"],
    exports = [
        "@commons_io_commons_io//jar",
    ],
)

java_library(
    name = "com_github_mrmike_ok2curl",
    visibility = ["//visibility:public"],
    exports = [
        "@com_github_mrmike_ok2curl//jar",
    ],
)

java_library(
    name = "commons_codec_commons_codec",
    visibility = ["//visibility:public"],
    exports = [
        "@commons_codec_commons_codec//jar",
    ],
)

java_library(
    name = "com_google_oauth_client_google_oauth_client",
    visibility = ["//visibility:public"],
    exports = [
        "@com_google_oauth_client_google_oauth_client//jar",
        "@com_google_code_findbugs_jsr305//jar",
        "@com_google_http_client_google_http_client//jar",
        "@commons_codec_commons_codec//jar",
        "@commons_logging_commons_logging//jar",
        "@org_apache_httpcomponents_httpclient//jar",
        "@org_apache_httpcomponents_httpcore//jar",
    ],
)

java_library(
    name = "commons_cli_commons_cli",
    visibility = ["//visibility:public"],
    exports = [
        "@commons_cli_commons_cli//jar",
    ],
)

java_library(
    name = "com_twitter_joauth",
    visibility = ["//visibility:public"],
    exports = [
        "@com_twitter_joauth//jar",
    ],
)

java_library(
    name = "org_mortbay_jetty_jetty_util",
    visibility = ["//visibility:public"],
    exports = [
        "@org_mortbay_jetty_jetty_util//jar",
    ],
)

java_library(
    name = "com_squareup_okio_okio",
    visibility = ["//visibility:public"],
    exports = [
        "@com_squareup_okio_okio//jar",
    ],
)

java_library(
    name = "com_google_oauth_client_google_oauth_client_java6",
    visibility = ["//visibility:public"],
    exports = [
        "@com_google_oauth_client_google_oauth_client_java6//jar",
        "@com_google_code_findbugs_jsr305//jar",
        "@com_google_http_client_google_http_client//jar",
        "@com_google_oauth_client_google_oauth_client//jar",
        "@commons_codec_commons_codec//jar",
        "@commons_logging_commons_logging//jar",
        "@org_apache_httpcomponents_httpclient//jar",
        "@org_apache_httpcomponents_httpcore//jar",
    ],
)

java_library(
    name = "commons_logging_commons_logging",
    visibility = ["//visibility:public"],
    exports = [
        "@commons_logging_commons_logging//jar",
    ],
)

java_library(
    name = "org_slf4j_slf4j_api",
    visibility = ["//visibility:public"],
    exports = [
        "@org_slf4j_slf4j_api//jar",
    ],
)

java_library(
    name = "com_squareup_okhttp3_logging_interceptor",
    visibility = ["//visibility:public"],
    exports = [
        "@com_squareup_okhttp3_logging_interceptor//jar",
        "@com_squareup_okhttp3_okhttp//jar",
    ],
)

java_library(
    name = "org_slf4j_slf4j_jcl",
    visibility = ["//visibility:public"],
    exports = [
        "@org_slf4j_slf4j_jcl//jar",
        "@commons_logging_commons_logging//jar",
        "@org_slf4j_slf4j_api//jar",
    ],
)

java_library(
    name = "org_mortbay_jetty_jetty",
    visibility = ["//visibility:public"],
    exports = [
        "@org_mortbay_jetty_jetty//jar",
        "@org_mortbay_jetty_jetty_util//jar",
        "@org_mortbay_jetty_servlet_api//jar",
    ],
)

java_library(
    name = "com_fasterxml_jackson_core_jackson_annotations",
    visibility = ["//visibility:public"],
    exports = [
        "@com_fasterxml_jackson_core_jackson_annotations//jar",
    ],
)

java_library(
    name = "com_google_http_client_google_http_client",
    visibility = ["//visibility:public"],
    exports = [
        "@com_google_http_client_google_http_client//jar",
        "@com_google_code_findbugs_jsr305//jar",
        "@commons_codec_commons_codec//jar",
        "@commons_logging_commons_logging//jar",
        "@org_apache_httpcomponents_httpclient//jar",
        "@org_apache_httpcomponents_httpcore//jar",
    ],
)

java_library(
    name = "com_squareup_okhttp3_okhttp",
    visibility = ["//visibility:public"],
    exports = [
        "@com_squareup_okhttp3_okhttp//jar",
        "@com_squareup_okio_okio//jar",
    ],
)

java_library(
    name = "org_apache_httpcomponents_httpclient",
    visibility = ["//visibility:public"],
    exports = [
        "@org_apache_httpcomponents_httpclient//jar",
        "@commons_codec_commons_codec//jar",
        "@commons_logging_commons_logging//jar",
        "@org_apache_httpcomponents_httpcore//jar",
    ],
)

java_library(
    name = "com_fasterxml_jackson_core_jackson_databind",
    visibility = ["//visibility:public"],
    exports = [
        "@com_fasterxml_jackson_core_jackson_databind//jar",
        "@com_fasterxml_jackson_core_jackson_annotations//jar",
        "@com_fasterxml_jackson_core_jackson_core//jar",
    ],
)

java_library(
    name = "com_fasterxml_jackson_dataformat_jackson_dataformat_yaml",
    visibility = ["//visibility:public"],
    exports = [
        "@com_fasterxml_jackson_dataformat_jackson_dataformat_yaml//jar",
        "@com_fasterxml_jackson_core_jackson_core//jar",
        "@org_yaml_snakeyaml//jar",
    ],
)

java_library(
    name = "org_mortbay_jetty_servlet_api",
    visibility = ["//visibility:public"],
    exports = [
        "@org_mortbay_jetty_servlet_api//jar",
    ],
)

java_library(
    name = "com_fasterxml_jackson_core_jackson_core",
    visibility = ["//visibility:public"],
    exports = [
        "@com_fasterxml_jackson_core_jackson_core//jar",
    ],
)

java_library(
    name = "commons_lang_commons_lang",
    visibility = ["//visibility:public"],
    exports = [
        "@commons_lang_commons_lang//jar",
    ],
)

java_library(
    name = "org_yaml_snakeyaml",
    visibility = ["//visibility:public"],
    exports = [
        "@org_yaml_snakeyaml//jar",
    ],
)

java_library(
    name = "com_google_guava_guava",
    visibility = ["//visibility:public"],
    exports = [
        "@com_google_guava_guava//jar",
    ],
)

java_library(
    name = "io_airlift_airline",
    visibility = ["//visibility:public"],
    exports = [
        "@io_airlift_airline//jar",
    ],
)

java_library(
    name = "javax_inject_javax_inject",
    visibility = ["//visibility:public"],
    exports = [
        "@javax_inject_javax_inject//jar",
    ],
)

java_library(
    name = "junit_junit",
    visibility = ["//visibility:public"],
    exports = [
        "@junit_junit//jar",
    ],
)
