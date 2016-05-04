jvm_binary(name = 'main',
  dependencies = [
    'src/main/java/com/baulsupp/oksocial',
  ],
  main = 'com.baulsupp.oksocial.Main',
  strict_deps=True,
)

jvm_app(name='oksocial',
  dependencies = [
    ':main',
  ],
  basename = 'oksocial-bundle',
  bundles = [
    bundle(relative_to='src/main', fileset=globs('bin/*')),
  ]
)