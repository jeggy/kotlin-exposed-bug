import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

group = "com.apurebase"
version = "1.0"

repositories {
    jcenter()
}

dependencies {
    val junitVersion = "5.5.0"
    val hikariVersion = "3.3.1"
    val exposedVersion = "0.16.1"
    val mariadbVersion = "2.4.2"

    implementation(kotlin("stdlib-jdk8"))

    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testCompile("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testCompile("com.zaxxer:HikariCP:$hikariVersion")
    testCompile("org.jetbrains.exposed:exposed:$exposedVersion")
    testCompile("org.mariadb.jdbc:mariadb-java-client:$mariadbVersion")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
