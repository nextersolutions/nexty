plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.nextersolutions.nexty"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.nextersolutions"
                artifactId = "nexty"
                version = "1.0.0"

                from(components["release"])

                pom {
                    name.set("Nexty")
                    description.set("Real-time local data storage")
                    url.set("https://github.com/nextersolutions/nexty")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("nextersolutions")
                            name.set("Andrii Dubovyk")
                            email.set("dubovyk.developer@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:github.com/nextersolutions/nexty.git")
                        developerConnection.set("scm:git:ssh://github.com/nextersolutions/nexty.git")
                        url.set("https://github.com/nextersolutions/nexty/tree/main")
                    }
                }
            }
        }
    }
}