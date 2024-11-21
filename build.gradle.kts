plugins {
    java
    kotlin("jvm") version "2.0.20"
}

group = "pl.piotrkociakx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

dependencies {
    // hooki
    compileOnly("me.clip:placeholderapi:2.11.6")

    // paper
    compileOnly ("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")

    // mojang authlib
    implementation ("com.mojang:authlib:1.5.25")


    // lombok
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    // kotlin
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}