extra.set("kotlin_version","1.3.72")
apply(from = "custom.gradle")
apply(from = "hellotask.gradle")
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra.get("kotlin_version")}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("helloTask") {
    doLast() {

    }
}

afterEvaluate {
    var android = extensions.findByName("android")
    println("afterEvalute android is $android")
}

