dependencies {
    api("com.fasterxml.jackson.core:jackson-databind:2.21.1")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.21.1")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.7")
}
