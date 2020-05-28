plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}
//apply(plugin = "com.android.application")
android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")
    defaultConfig {
        applicationId = "com.ddmeng.kotlindslsample"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

//dependencies {
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
//    implementation 'androidx.appcompat:appcompat:1.1.0'
//    implementation 'androidx.core:core-ktx:1.2.0'
//    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
//    testImplementation 'junit:junit:4.12'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
//}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}

afterEvaluate {
    println("afaterEvaluate is working")
    android.applicationVariants.all {
        println("varient: " + this.toString())
        var varientMap = mapOf(
            "name" to this.name,
            "varientnameCapitalize" to this.name?.capitalize(),
            "builteType" to this.buildType.name,
            "flavor" to this.flavorName
        )
        var variantNameCapitalize = this.name.capitalize()
        var variantNameLowerCase = this.name.toLowerCase()
        println(varientMap.toString())
        if ("release".equals(buildType.name)) {
            println("====buildType is release")
            mergeAssetsProvider.get().doLast {
                println("deleting css")
            }
        } else if ("debug".equals(buildType.name)) {
            println("=====buildType is debug")
        } else {
            println("=====buildType is ${buildType.name}")
        }

        var buildApkTask = tasks.findByName("assemble${variantNameCapitalize}")
        buildApkTask?.doLast {
            println("buildApkTask.doLast()")
        }

        var variant = this;
        var prepareBuildTask = tasks.findByName("pre${variantNameCapitalize}Build")
        prepareBuildTask.let {
            prepareBuildTask?.outputs?.upToDateWhen { false }
            prepareBuildTask?.doFirst {
                println("task, do first" + variantNameLowerCase)
                beforePrepareDependencies(variant, variantNameLowerCase)
            }
            prepareBuildTask?.doLast {
                println("task, do last" + variantNameLowerCase)
                afterPrepareDependencies(variant, variantNameLowerCase)
            }
        }
    }
}

fun beforePrepareDependencies(
    variant: com.android.build.gradle.api.ApplicationVariant,
    variantName: String
) {

}

fun afterPrepareDependencies(
    variant: com.android.build.gradle.api.ApplicationVariant,
    variantName: String
) {

}
