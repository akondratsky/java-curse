plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
}

group = "io.github.akondratsky"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("joda-time:joda-time:2.12.7")
}

tasks.test {
    useJUnitPlatform()
}