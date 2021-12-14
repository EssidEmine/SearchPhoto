import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugin.Id.JAVA_LIBRARY)
    id(Plugin.Id.KOTLIN)
}


repositories {
    mavenCentral()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}

dependencies {
    implementation(LibraryDependency.kotlinStdlib)
}